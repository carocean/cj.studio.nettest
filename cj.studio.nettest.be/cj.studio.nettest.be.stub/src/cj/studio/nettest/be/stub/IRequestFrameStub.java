package cj.studio.nettest.be.stub;

import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubService;
import cj.studio.nettest.be.args.RequestFrame;

@CjStubService(bindService = "/requestFrame.service", usage = "请求侦")
public interface IRequestFrameStub {
	@CjStubMethod(usage = "获取请求侦")
	RequestFrame getMyRequestFrame(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);
}
