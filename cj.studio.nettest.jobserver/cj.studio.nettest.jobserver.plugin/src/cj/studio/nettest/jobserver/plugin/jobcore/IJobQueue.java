package cj.studio.nettest.jobserver.plugin.jobcore;

public interface IJobQueue {

	void start();

	void stop();

	void threadCount(int threadCount);
	/**
	 * 单位：秒
	 * @param intervals
	 */
	void intervals(long intervals);

	void loopCount(long loopCount);

	void alwaysLoop(boolean alwaysLoop);

}
