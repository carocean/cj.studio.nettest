package cj.studio.nettest.fe.program.webview;

import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.net.Circuit;
import cj.studio.ecm.net.CircuitException;
import cj.studio.ecm.net.Frame;
import cj.studio.ecm.net.io.MemoryContentReciever;
import cj.studio.gateway.socket.app.IGatewayAppSiteResource;
import cj.studio.gateway.socket.app.IGatewayAppSiteWayWebView;
@CjBridge(aspects = "@rest")
@CjService(name = "/tcp/error")
public class TcpErrorWebView implements IGatewayAppSiteWayWebView {
	@Override
	public void flow(Frame f, Circuit c, IGatewayAppSiteResource ctx) throws CircuitException {
		f.content().accept(new MemoryContentReciever() {
			@Override
			public void done(byte[] b, int pos, int length) throws CircuitException {
				super.done(b, pos, length);
				byte[] all=readFully();
				System.out.println("Receiving remote TCP error:"+new String(all));
			}
		});
		
	}

}
