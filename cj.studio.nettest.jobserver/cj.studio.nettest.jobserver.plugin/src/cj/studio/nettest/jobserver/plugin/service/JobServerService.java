package cj.studio.nettest.jobserver.plugin.service;

import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.gateway.stub.annotation.CjStubRef;
import cj.studio.nettest.be.args.RequestFrame;
import cj.studio.nettest.be.stub.IRequestFrameStub;
import cj.studio.nettest.jobserver.args.JobSender;
import cj.studio.nettest.jobserver.service.IJobServerService;
@CjBridge(aspects = "@rest")
@CjService(name="jobserver")
public class JobServerService implements IJobServerService{
	@CjStubRef(remote = "rest://backend/nettest/", stub = IRequestFrameStub.class)
	IRequestFrameStub rfStub;
	@Override
	public void addJob(String mid, JobSender sender) {
		RequestFrame rf=rfStub.getMyRequestFrame(mid, sender.getSender());
		System.out.println("----jobserver:"+rf);
	}

}
