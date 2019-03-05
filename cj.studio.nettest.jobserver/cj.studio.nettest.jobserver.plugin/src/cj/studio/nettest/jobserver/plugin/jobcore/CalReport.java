package cj.studio.nettest.jobserver.plugin.jobcore;

import java.util.concurrent.LinkedBlockingQueue;

import cj.studio.nettest.jobserver.args.CountReport;

public class CalReport implements Runnable {
	private CountReport report;
	private LinkedBlockingQueue<CalTask> tasks;
	private volatile boolean isRunning;
	ICalDoneEvent event;
	public CalReport() {
		this.report = new CountReport();
		this.tasks = new LinkedBlockingQueue<>();
		isRunning = true;
	}

	public CountReport getReport() {
		return report;
	}

	public void addCalTask(CalTask calTask) {
		tasks.offer(calTask);
	}

	public void stop() {
		isRunning = false;
	}
	public void setEvent(ICalDoneEvent event) {
		this.event = event;
	}
	@Override
	public void run() {
		while (isRunning) {
			CalTask task = null;
			try {
				task = tasks.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
			switch (task.getCmd()) {
			case cmd_add_simpleCount:
				report.setSimCount(report.getSimCount() + (int) task.getData());
				break;
			case cmd_add_passCount:
				report.setPassCount(report.getPassCount() + (int) task.getData());
				break;
			case cmd_add_takeTime:
				report.setTakeTimes(report.getTakeTimes() + (long) task.getData());
				break;
			case cmd_set_max:
				long v = (long) task.getData();
				if (v > report.getMax()) {
					report.setMax(v);
				}
				break;
			case cmd_set_min:
				v = (long) task.getData();
				if (v < report.getMin()) {
					report.setMin(v);
				}
				break;
			case cmd_cal_done:
				if(event!=null) {
					isRunning=false;
				event.done(report);
				}
				break;
			}
		}
	}

}
