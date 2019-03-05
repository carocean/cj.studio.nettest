package cj.studio.nettest.jobserver.plugin.service;

import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.gateway.stub.annotation.CjStubRef;
import cj.studio.nettest.be.args.RequestFrame;
import cj.studio.nettest.be.args.RunnerStrategy;
import cj.studio.nettest.be.stub.IProjectTreeStub;
import cj.studio.nettest.be.stub.IRequestFrameStub;
import cj.studio.nettest.jobserver.args.JobSender;
import cj.studio.nettest.jobserver.plugin.jobcore.IJobEngine;
import cj.studio.nettest.jobserver.plugin.jobcore.IJobQueue;
import cj.studio.nettest.jobserver.service.IJobServerService;

@CjBridge(aspects = "@rest")
@CjService(name = "jobserver")
public class JobServerService implements IJobServerService {
	@CjStubRef(remote = "rest://backend/nettest/", stub = IRequestFrameStub.class)
	IRequestFrameStub rfStub;
	@CjStubRef(remote = "rest://backend/nettest/", stub = IProjectTreeStub.class)
	IProjectTreeStub ptStub;
	@CjServiceRef
	IJobEngine jobEngine;

	@Override
	public void addJob(String mid, JobSender sender) throws CircuitException {
		RequestFrame rf = rfStub.getMyRequestFrame(mid, sender.getSender());
		IJobQueue jq = jobEngine.createJobQueue(sender, rf);
		jq.threadCount(1);
		jq.intervals(0);
		jq.loopCount(1);
		jq.alwaysLoop(false);
		jq.start();
	}

	@Override
	public void addJobByRunner(String mid, String runnerid, JobSender sender) throws CircuitException {
		RunnerStrategy rs = ptStub.getRunnerStrategy(mid, sender.getSender());
		if (rs == null)
			return;
		RequestFrame rf = rfStub.getMyRequestFrame(mid, sender.getSender());
		IJobQueue jq = jobEngine.createJobQueue(sender, rf);
		jq.threadCount(rs.getThreadcount());
		jq.intervals(rs.getIntervals());
		jq.loopCount(rs.getLoopcount());
		jq.alwaysLoop(rs.getAlwaysloop());
		jq.start();
	}

	@Override
	public void stopJobRunner(String mid, String sender) throws CircuitException {
		jobEngine.stopJobRunner(mid, sender);
	}

}
