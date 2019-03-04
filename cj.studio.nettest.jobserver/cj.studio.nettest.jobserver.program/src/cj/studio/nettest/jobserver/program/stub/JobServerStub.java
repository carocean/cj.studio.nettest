package cj.studio.nettest.jobserver.program.stub;

import java.lang.reflect.Method;

import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.Circuit;
import cj.studio.ecm.net.CircuitException;
import cj.studio.ecm.net.Frame;
import cj.studio.gateway.socket.util.SocketContants;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;
import cj.studio.nettest.jobserver.args.JobSender;
import cj.studio.nettest.jobserver.service.IJobServerService;
import cj.studio.nettest.jobserver.stub.IJobServerStub;

@CjService(name = "/jobserver.service")
public class JobServerStub extends GatewayAppSiteRestStub implements IJobServerStub {
	@CjServiceRef(refByName = "nettestplugin.jobserver")
	IJobServerService jobserver;
	@Override
	protected void doMethodBefore(Method m, Object[] args, Frame frame, Circuit circuit) throws CircuitException {
		if(m.getName().equals("addJob")) {
			JobSender  sender=(JobSender)args[1];
			if(sender!=null) {
				sender.setBackendid(frame.head(SocketContants.__frame_fromPipelineName));
			}
		}
	}
	@Override
	public void addJob(String mid, JobSender sender) throws CircuitException {
		jobserver.addJob(mid, sender);
	}

}
