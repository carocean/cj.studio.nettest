package cj.studio.nettest.jobserver.plugin.jobcore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.gateway.IRuntime;
import cj.studio.gateway.socket.pipeline.IOutputSelector;
import cj.studio.nettest.be.args.RequestFrame;
import cj.studio.nettest.jobserver.args.JobSender;
import cj.ultimate.IClosable;

@CjService(name = "jobEngine")
public class JobEngine implements IJobEngine, IClosable {
	@CjServiceRef(refByName = "$.output.selector")
	IOutputSelector selector;
	@CjServiceRef(refByName="$.gateway.runtime")
	IRuntime runtime;
	List<IJobQueue> queues;
	boolean running;
	ExecutorService mainexe;
	public JobEngine() {
		this.queues = new ArrayList<>();
		mainexe=Executors.newCachedThreadPool();
	}

	@Override
	public void run() {
		if(running)return;
		for (IJobQueue q : queues) {
			q.start();
		}
		running=true;
	}

	@Override
	public void stop() {
		if(!running)return;
		running=false;
		for (IJobQueue q : queues) {
			q.stop();
		}
		this.mainexe.shutdown();
	}

	@Override
	public void close() {
		if(running) {
			stop();
		}
		queues.clear();
	}

	@Override
	public IJobQueue createJobQueue(JobSender sender, RequestFrame rf) throws CircuitException {
		IJobQueue jq=new JobQueue(mainexe,runtime,this.selector,sender,rf);
		queues.add(jq);
		return jq;
	}
}
