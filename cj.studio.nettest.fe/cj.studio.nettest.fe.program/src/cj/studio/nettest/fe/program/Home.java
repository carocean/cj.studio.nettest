package cj.studio.nettest.fe.program;

import org.jsoup.nodes.Document;

import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.net.Circuit;
import cj.studio.ecm.net.CircuitException;
import cj.studio.ecm.net.Frame;
import cj.studio.gateway.socket.app.IGatewayAppSiteResource;
import cj.studio.gateway.socket.app.IGatewayAppSiteWayWebView;

@CjService(name = "/")
public class Home implements IGatewayAppSiteWayWebView {

	@Override
	public void flow(Frame f, Circuit c, IGatewayAppSiteResource ctx) throws CircuitException {
		Document doc=ctx.html("/index.html");
		
		c.content().writeBytes(doc.toString().getBytes());
	}

}
