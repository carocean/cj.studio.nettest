package cj.studio.nettest.jobserver.plugin.jobcore;

import java.util.concurrent.atomic.AtomicBoolean;

import cj.studio.ecm.CJSystem;
import cj.studio.ecm.EcmException;
import cj.studio.ecm.net.Circuit;
import cj.studio.ecm.net.CircuitException;
import cj.studio.ecm.net.Frame;
import cj.studio.ecm.net.IInputChannel;
import cj.studio.ecm.net.io.MemoryContentReciever;
import cj.studio.ecm.net.io.MemoryInputChannel;
import cj.studio.ecm.net.io.MemoryOutputChannel;
import cj.studio.gateway.socket.pipeline.IOutputer;
import cj.studio.nettest.be.args.RequestFrame;
import cj.studio.nettest.be.args.SimpleReport;
import cj.studio.nettest.jobserver.args.CountReport;
import cj.studio.nettest.jobserver.args.JobSender;
import cj.ultimate.gson2.com.google.gson.Gson;

public class Job implements IJob, ICalDoneEvent {
	IOutputer outclient;
	IOutputer outdest;
	JobSender sender;
	RequestFrame rf;
	AtomicBoolean isRunning;
	private CalReport pool;

	public Job(CalReport pool, IOutputer toclient, IOutputer todest, JobSender sender, RequestFrame rf,
			AtomicBoolean isRunning) {
		this.outclient = toclient;
		this.pool = pool;
		this.outdest = todest;
		this.sender = sender;
		this.rf = rf;
		this.isRunning = isRunning;
		pool.setEvent(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		pool.addCalTask(new CalTask(CalCommand.cmd_add_simpleCount, 1));
		long before = System.currentTimeMillis();
		SimpleReport report = new SimpleReport();
		report.setState(200);
		report.setCreator(rf.getRequestor());
		report.setMid(rf.getMid());
		report.setCtime(before);
		IInputChannel in = new MemoryInputChannel();

		MemoryContentReciever reciever = new MemoryContentReciever();
		Frame frame = null;
		try {
			frame = new Frame(in, reciever, rf.getFrameRaw().getBytes());
			in.done(new byte[0], 0, 0);
		} catch (CircuitException e) {
			report.setState(Integer.valueOf(e.getStatus()));
			report.setMessage(e.getMessage());
			report.setResponse(e.messageCause());
			CJSystem.logging().error(getClass(), e);
			return;
		}

		
		MemoryOutputChannel output = new MemoryOutputChannel();
		Circuit circuit = new Circuit(output, String.format("%s 200 OK", frame.protocol()));

		try {
			this.outdest.send(frame, circuit);
		} catch (CircuitException e) {
			report.setState(Integer.valueOf(e.getStatus()));
			report.setMessage(e.getMessage());
			report.setResponse(e.messageCause());
			CJSystem.logging().error(getClass(), e);
			return;
		}
		int state = Integer.valueOf(circuit.status());
		report.setState(state);
		report.setMessage(circuit.message());
		if (state < 300) {
			if (frame.protocol().startsWith("HTTP/")) {// 仅有http协议请求有返回值
				byte[] ret = output.readFully();
				String text = new String(ret);
				report.setResponse(text);
			}
		} else {
			if (circuit.content().isAllInMemory() && circuit.content().isClosed()) {
				byte[] b = output.readFully();
				report.setResponse(new String(b));
			}
		}

		try {
			MemoryInputChannel in2 = new MemoryInputChannel();
			Frame f = new Frame(in2, "put /nettest/test-report.service tcp/1.0");
			f.parameter("sender", sender.getSender());
			f.content().accept(new MemoryContentReciever());
			in2.begin(f);

			long taketime = System.currentTimeMillis() - before;
			report.setTakeTime(taketime);

			pool.addCalTask(new CalTask(CalCommand.cmd_add_takeTime, taketime));
			pool.addCalTask(new CalTask(CalCommand.cmd_set_min, taketime));
			pool.addCalTask(new CalTask(CalCommand.cmd_set_max, taketime));
			pool.addCalTask(new CalTask(CalCommand.cmd_add_passCount, 1));

			byte[] b = new Gson().toJson(report).getBytes();
			in2.done(b, 0, b.length);

			MemoryOutputChannel out = new MemoryOutputChannel();
			Circuit c = new Circuit(out, "tcp/1.0 200 OK");

			outclient.send(f, c);

			sendCountReport(this.pool.getReport());
		} catch (CircuitException e) {
			e.printStackTrace();
			throw new EcmException(e);
		}
	}

	@Override
	public void done(CountReport report) {
		try {
			sendCountReport(report);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendCountReport(CountReport report) throws CircuitException {
		MemoryInputChannel in = new MemoryInputChannel();
		Frame f = new Frame(in, "put /nettest/count-report.service tcp/1.0");
		f.parameter("sender", sender.getSender());
		f.content().accept(new MemoryContentReciever());
		in.begin(f);
		byte[] b = new Gson().toJson(report).getBytes();
		in.done(b, 0, b.length);

		MemoryOutputChannel out = new MemoryOutputChannel();
		Circuit c = new Circuit(out, "tcp/1.0 200 OK");
		outclient.send(f, c);
	}

}
