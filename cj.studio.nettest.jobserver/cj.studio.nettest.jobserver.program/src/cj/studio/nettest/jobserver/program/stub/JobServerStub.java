package cj.studio.nettest.jobserver.program.stub;

import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;
import cj.studio.nettest.jobserver.args.JobSender;
import cj.studio.nettest.jobserver.service.IJobServerService;
import cj.studio.nettest.jobserver.stub.IJobServerStub;

@CjService(name = "/jobserver.service")
public class JobServerStub extends GatewayAppSiteRestStub implements IJobServerStub {
	@CjServiceRef(refByName = "nettestplugin.jobserver")
	IJobServerService jobserver;

	@Override
	public void addJob(String mid, JobSender sender) {
		jobserver.addJob(mid, sender);
	}

}
