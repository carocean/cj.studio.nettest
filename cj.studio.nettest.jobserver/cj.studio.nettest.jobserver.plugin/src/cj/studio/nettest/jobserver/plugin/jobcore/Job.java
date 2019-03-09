package cj.studio.nettest.jobserver.plugin.jobcore;

import java.util.Map;
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
import cj.ultimate.util.StringUtil;

public class Job implements IJob,ICalDoneEvent {
	IOutputer outclient;
	IOutputer outdest;
	JobSender sender;
	RequestFrame rf;
	AtomicBoolean isRunning;
	private CalReport pool;

	public Job(CalReport pool,IOutputer toclient, IOutputer todest, JobSender sender, RequestFrame rf, AtomicBoolean isRunning) {
		this.outclient = toclient;
		this.pool=pool;
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
		Map<String,Object> rframe=rf.getFrame();
		Map<String, Object> headers = (Map<String, Object>) rframe.get("headers");
		Map<String, Object> params = (Map<String, Object>) rframe.get("parameters");
		String content = (String) rframe.get("content");
		String protocol = (String) headers.get("protocol");
		IInputChannel in = new MemoryInputChannel();
		Frame frame = new Frame(in, String.format("%s %s %s", headers.get("command"), headers.get("url"), protocol));
		MemoryContentReciever reciever = new MemoryContentReciever();
		try {
			frame.content().accept(reciever);
		} catch (CircuitException e) {
			report.setState(Integer.valueOf(e.getStatus()));
			report.setMessage(e.getMessage());
			report.setResponse(e.messageCause());
			CJSystem.logging().error(getClass(), e);
			return;
		}

		for (String key : headers.keySet()) {
			String v = (String) headers.get(key);
			frame.head(key, v);
		}
		for (String key : params.keySet()) {
			String v = (String) params.get(key);
			frame.parameter(key, v);
		}
		if (!StringUtil.isEmpty(content)) {
			try {
				in.begin(frame);
				byte[] b = content.getBytes();
				in.done(b, 0, b.length);
			} catch (CircuitException e) {
				report.setState(Integer.valueOf(e.getStatus()));
				report.setMessage(e.getMessage());
				report.setResponse(e.messageCause());
				CJSystem.logging().error(getClass(), e);
				return;
			}
		}
		MemoryOutputChannel output = new MemoryOutputChannel();
		Circuit circuit = new Circuit(output, String.format("%s 200 OK", protocol));

		try {
			this.outdest.send(frame, circuit);
//			this.outdest.releasePipeline();
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
			if (protocol.startsWith("HTTP/")) {// 仅有http协议请求有返回值
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
			MemoryInputChannel in2=new MemoryInputChannel();
			Frame f = new Frame(in2,"put /nettest/test-report.service tcp/1.0");
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
			
			MemoryOutputChannel out=new MemoryOutputChannel();
			Circuit c=new Circuit(out,"tcp/1.0 200 OK");
			
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
		MemoryInputChannel in=new MemoryInputChannel();
		Frame f = new Frame(in,"put /nettest/count-report.service tcp/1.0");
		f.parameter("sender", sender.getSender());
		f.content().accept(new MemoryContentReciever());
		in.begin(f);
		byte[] b = new Gson().toJson(report).getBytes();
		in.done(b, 0, b.length);
		
		MemoryOutputChannel out=new MemoryOutputChannel();
		Circuit c=new Circuit(out,"tcp/1.0 200 OK");
		outclient.send(f, c);
	}

}
