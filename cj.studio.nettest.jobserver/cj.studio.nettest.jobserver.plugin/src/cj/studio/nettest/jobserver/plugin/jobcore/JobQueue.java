package cj.studio.nettest.jobserver.plugin.jobcore;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import cj.studio.ecm.CJSystem;
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
	CalReport pool;
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
	List<Future<?>> futures;
	private boolean hasTimeout;

	public JobQueue(ExecutorService mainExe, IRuntime runtime, IOutputSelector selector, JobSender sender,
			RequestFrame rf) {
		this.pool = new CalReport();
		this.selector = selector;
		this.sender = sender;
		this.rf = rf;
		this.runtime = runtime;
		this.mainexe = mainExe;
		this.isRunning = new AtomicBoolean(false);
		futures = new CopyOnWriteArrayList<>();
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
		mainexe.submit(this.pool);
	}

	@SuppressWarnings("static-access")
	@Override
	public Object call() throws Exception {
		isRunning.set(true);
		this.pool.getReport().setCreator(this.sender.getSender());
		this.pool.getReport().setMid(rf.getMid());
		if (this.alwaysLoop) {
			while (isRunning.get()) {
				IJob job = new Job(this.pool, this.toclient, this.todest, this.sender, this.rf, isRunning);
				Future<?> f = this.workexe.submit(job);
				Thread.currentThread().sleep(this.intervals);
				futures.add(f);
				removeDones();
			}
		} else {
			long loop = this.loopCount * this.threadCount;
			for (int i = 0; i < loop; i++) {
				if (!isRunning.get()) {
					break;
				}
				IJob job = new Job(this.pool, this.toclient, this.todest, this.sender, this.rf, isRunning);
				Future<?> f = this.workexe.submit(job);
				Thread.currentThread().sleep(this.intervals);
				futures.add(f);
				removeDones();
			}
		}
		// 等待完成，如果都完成则退出queue
		hasTimeout = false;
		for (Future<?> f : futures) {
			try {
				f.get(30000L, TimeUnit.MILLISECONDS);// 30秒内如果没完成则超时
			} catch (Exception e) {
				hasTimeout = true;
				CJSystem.logging().warn(getClass(), "超时。有job在等待30秒后仍未完成");
			}
			futures.remove(f);
		}
		pool.addCalTask(new CalTask(CalCommand.cmd_cal_done, true));
		stop();
		return null;
	}

	private void removeDones() {
		for (Future<?> f : futures) {
			if (f.isDone()) {
				futures.remove(f);
			}
		}
	}

	@Override
	public void stop() {
		if (!isRunning.get()) {
			return;
		}
		pool.stop();
		isRunning.set(false);
		workexe.shutdownNow();
		Destination d = this.runtime.getDestination(rf.getDest());
		if (d != null) {
			this.runtime.removeDestination(d.getName());
		}
//		try {
//			this.toclient.releasePipeline();// 到前端的通道不物理关闭，仅释放
//			this.todest.closePipeline();
		@SuppressWarnings("unchecked")
		Map<String, String> headers = (Map<String, String>) rf.getFrame().get("headers");
		if (hasTimeout) {
			CJSystem.logging().info(getClass(),
					String.format("请求：%s,%s,%s headline:%s %s %s 的处理队列已完成，但有超时退出的请求", rf.getMid(), rf.getDest(),
							rf.getHost(), headers.get("command"), headers.get("url"), headers.get("protocol")));
		} else {
			CJSystem.logging().info(getClass(), String.format("请求：%s,%s,%s headline:%s %s %s 的处理队列已完成", rf.getMid(),
					rf.getDest(), rf.getHost(), headers.get("command"), headers.get("url"), headers.get("protocol")));
		}
//		} catch (CircuitException e) {
//			throw new EcmException(e);
//		}

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
