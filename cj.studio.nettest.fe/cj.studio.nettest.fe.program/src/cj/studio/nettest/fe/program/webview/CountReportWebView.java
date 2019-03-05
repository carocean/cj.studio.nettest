package cj.studio.nettest.fe.program.webview;

import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.Circuit;
import cj.studio.ecm.net.CircuitException;
import cj.studio.ecm.net.Frame;
import cj.studio.ecm.net.io.MemoryContentReciever;
import cj.studio.ecm.net.io.MemoryInputChannel;
import cj.studio.gateway.socket.app.IGatewayAppSiteResource;
import cj.studio.gateway.socket.app.IGatewayAppSiteWayWebView;
import cj.studio.gateway.socket.pipeline.IOutputSelector;
import cj.studio.nettest.fe.program.IOnlineTable;

@CjBridge(aspects = "@rest")
@CjService(name = "/count-report.service")
public class CountReportWebView implements IGatewayAppSiteWayWebView {
	@CjServiceRef(refByName = "online")
	IOnlineTable table;
	@CjServiceRef(refByName = "$.output.selector")
	IOutputSelector selector;

	@Override
	public void flow(Frame f, Circuit c, IGatewayAppSiteResource ctx) throws CircuitException {
		f.content().accept(new MemoryContentReciever() {
			@Override
			public void done(byte[] b, int pos, int length) throws CircuitException {
				super.done(b, pos, length);
				String sender = f.parameter("sender");
				String pipelineName = table.getUserOnPipeline(sender);
				MemoryInputChannel in = new MemoryInputChannel();
				MemoryContentReciever reciever = new MemoryContentReciever();
				Frame frame = new Frame(in, String.format("%s %s %s", f.command(), f.url(), f.protocol()));
				frame.content().accept(reciever);
				in.begin(frame);
				byte[] arr = readFully();
				in.done(arr, 0, arr.length);

				selector.select(pipelineName).send(frame, c);

//				System.out.println("report:" + sender+"  "+pipelineName);
			}
		});

	}

}
