package cj.studio.nettest.jobserver.plugin.jobcore;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import cj.studio.ecm.net.CircuitException;
import cj.studio.gateway.IRuntime;
import cj.studio.gateway.socket.Destination;
import cj.studio.gateway.socket.pipeline.IOutputSelector;
import cj.studio.gateway.socket.pipeline.IOutputer;
import cj.studio.nettest.be.args.RequestFrame;
import cj.studio.nettest.jobserver.args.JobSender;

public class JobQueue implements IJobQueue, Callable<Object> {
	IOutputer todest;
	IOutputer toclient;
	IRuntime runtime;

	JobSender sender;
	RequestFrame rf;
	ExecutorService mainexe;
	ExecutorService workexe;
	int threadCount;
	long intervals;
	long loopCount;
	boolean alwaysLoop;
	AtomicBoolean isRunning;
	IOutputSelector selector;

	public JobQueue(ExecutorService mainExe, IRuntime runtime, IOutputSelector selector, JobSender sender,
			RequestFrame rf) {
		this.selector = selector;
		this.sender = sender;
		this.rf = rf;
		this.runtime = runtime;
		this.mainexe = mainExe;
		this.isRunning=new AtomicBoolean(false);
	}

	@Override
	public void start() {
		workexe = Executors.newFixedThreadPool(threadCount);
		String destName = rf.getDest();
		if (destName.startsWith("rest:/")) {
			destName = destName.substring("rest:/".length(), destName.length());
		}
		Destination d = this.runtime.getDestination(destName);
		if (d == null) {
			d = new Destination(destName);
			d.getUris().add(rf.getHost());
			this.runtime.addDestination(d);
		}
		try {
			todest = selector.select(destName);
			toclient = selector.select(sender.getBackendid());// 向测试端发送的输出器
		} catch (CircuitException e) {
			throw new RuntimeException(e);
		} // 请求测试目标的输出器

		
		mainexe.submit(this);
	}

	@SuppressWarnings("static-access")
	@Override
	public Object call() throws Exception {
		isRunning.set(true);
		if (this.alwaysLoop) {
			while (isRunning.get()) {
				IJob job = new Job(this.toclient, this.todest, this.sender, this.rf, isRunning);
				this.workexe.submit(job);
				Thread.currentThread().sleep(this.intervals * 1000L);
			}
		} else {
			for (int i = 0; i < this.loopCount; i++) {
				if (!isRunning.get()) {
					break;
				}
				IJob job = new Job(this.toclient, this.todest, this.sender, this.rf, isRunning);
				this.workexe.submit(job);
				Thread.currentThread().sleep(this.intervals * 1000L);
			}
		}
		return null;
	}

	@Override
	public void stop() {
		isRunning.set(false);
		workexe.shutdownNow();
		Destination d = this.runtime.getDestination(rf.getDest());
		if (d != null) {
			this.runtime.removeDestination(d.getName());
		}
		try {
			this.toclient.closePipeline();
			this.todest.closePipeline();
		} catch (CircuitException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void threadCount(int threadCount) {
		this.threadCount = threadCount;
	}

	@Override
	public void intervals(long intervals) {
		this.intervals = intervals;
	}

	@Override
	public void loopCount(long loopCount) {
		this.loopCount = loopCount;
	}

	@Override
	public void alwaysLoop(boolean alwaysLoop) {
		this.alwaysLoop = alwaysLoop;
	}

}
