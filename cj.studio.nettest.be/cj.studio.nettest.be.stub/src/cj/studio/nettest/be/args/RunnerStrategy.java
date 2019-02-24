package cj.studio.nettest.be.args;

public class RunnerStrategy {
	String id;// runnerId
	String mid;// methodId
	int loopcount;
	boolean alwaysloop;
	long intervals;
	int threadcount;
	String creator;

	public RunnerStrategy() {
	}

	public RunnerStrategy(String id, String mid, int loopcount, boolean alwaysloop, long intervals, int threadcount,
			String creator) {
		super();
		this.id = id;
		this.mid = mid;
		this.loopcount = loopcount;
		this.alwaysloop = alwaysloop;
		this.intervals = intervals;
		this.threadcount = threadcount;
		this.creator = creator;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public int getLoopcount() {
		return loopcount;
	}

	public void setLoopcount(int loopcount) {
		this.loopcount = loopcount;
	}

	public boolean getAlwaysloop() {
		return alwaysloop;
	}

	public void setAlwaysloop(boolean alwaysloop) {
		this.alwaysloop = alwaysloop;
	}

	public long getIntervals() {
		return intervals;
	}

	public void setIntervals(long intervals) {
		this.intervals = intervals;
	}

	public int getThreadcount() {
		return threadcount;
	}

	public void setThreadcount(int threadcount) {
		this.threadcount = threadcount;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

}
