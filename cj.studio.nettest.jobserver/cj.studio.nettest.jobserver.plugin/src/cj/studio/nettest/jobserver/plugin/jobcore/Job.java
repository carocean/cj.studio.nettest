package cj.studio.nettest.jobserver.plugin.jobcore;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import cj.studio.ecm.EcmException;
import cj.studio.ecm.net.Circuit;
import cj.studio.ecm.net.CircuitException;
import cj.studio.ecm.net.Frame;
import cj.studio.ecm.net.IInputChannel;
import cj.studio.ecm.net.io.MemoryContentReciever;
import cj.studio.ecm.net.io.MemoryInputChannel;
import cj.studio.ecm.net.io.MemoryOutputChannel;
import cj.studio.gateway.socket.io.DefaultFeedbackToOutputer;
import cj.studio.gateway.socket.pipeline.IOutputer;
import cj.studio.nettest.be.args.RequestFrame;
import cj.studio.nettest.be.args.TextReport;
import cj.studio.nettest.jobserver.args.JobSender;
import cj.ultimate.gson2.com.google.gson.Gson;
import cj.ultimate.util.StringUtil;

public class Job implements IJob {
	IOutputer outclient;
	IOutputer outdest;
	JobSender sender;
	RequestFrame rf;
	AtomicBoolean isRunning;

	public Job(IOutputer toclient, IOutputer todest, JobSender sender, RequestFrame rf, AtomicBoolean isRunning) {
		this.outclient = toclient;
		this.outdest = todest;
		this.sender = sender;
		this.rf = rf;
		this.isRunning = isRunning;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		Map<String, Object> headers = (Map<String, Object>) rf.getFrame().get("headers");
		Map<String, Object> params = (Map<String, Object>) rf.getFrame().get("parameters");
		String content = (String) params.get("content");
		String protocol = (String) headers.get("protocol");
		IInputChannel in = new MemoryInputChannel();
		Frame frame = new Frame(in, String.format("%s %s %s", headers.get("command"), headers.get("url"), protocol));
		MemoryContentReciever reciever = new MemoryContentReciever();
		try {
			frame.content().accept(reciever);
		} catch (CircuitException e) {
			throw new EcmException(e);
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
				throw new EcmException(e);
			}
		}
		MemoryOutputChannel output = new MemoryOutputChannel();
		Circuit circuit = new Circuit(output, String.format("%s 200 OK", protocol));
		TextReport report = new TextReport();
		report.setState(200);

		try {
			this.outdest.send(frame, circuit);
		} catch (CircuitException e) {
			report.setState(Integer.valueOf(e.getStatus()));
			report.setError(e.getMessage());
			throw new EcmException(e);
		}
		if (protocol.startsWith("HTTP/")) {// 仅有http协议请求有返回值
			byte[] ret = output.readFully();
			String text = new String(ret);
			report.setResponse(text);
		}
		DefaultFeedbackToOutputer fb = new DefaultFeedbackToOutputer(outclient);
		try {
			Frame first = fb.createFirst("put /nettest/test-report.service tcp/1.0");
			first.parameter("sender", sender.getSender());
			fb.commitFirst(first);
			in = fb.createLast();
			byte[] b = new Gson().toJson(report).getBytes();
			in.writeBytes(b, 0, b.length);
			fb.commitLast(in);
		} catch (CircuitException e) {
			e.printStackTrace();
		}
	}

}
