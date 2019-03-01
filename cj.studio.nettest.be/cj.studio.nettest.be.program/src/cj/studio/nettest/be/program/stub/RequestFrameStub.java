package cj.studio.nettest.be.program.stub;

import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;
import cj.studio.nettest.be.args.RequestFrame;
import cj.studio.nettest.be.service.IRequestFrameService;
import cj.studio.nettest.be.stub.IRequestFrameStub;
@CjService(name="/requestFrame.service")
public class RequestFrameStub extends GatewayAppSiteRestStub implements IRequestFrameStub{
	@CjServiceRef(refByName = "nettestplugin.rfService")
	IRequestFrameService rfService;
	@Override
	public RequestFrame getMyRequestFrame(String mid, String creator) {
		return rfService.getMyRequestFrame(mid, creator);
	}

}
