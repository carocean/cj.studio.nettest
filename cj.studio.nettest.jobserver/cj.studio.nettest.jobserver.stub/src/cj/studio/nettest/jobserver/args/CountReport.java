package cj.studio.nettest.jobserver.args;

public class CountReport {
	String mid;
	String creator;
	volatile int simCount;
	volatile int passCount;
	volatile long avg;
	volatile long min;
	volatile long max;
	volatile long takeTimes;

	public CountReport() {
	}

	public CountReport(String mid, String creator) {
		this();
		this.mid = mid;
		this.creator = creator;
	}

	
	public int getSimCount() {
		return simCount;
	}

	public void setSimCount(int simCount) {
		this.simCount = simCount;
	}

	public int getPassCount() {
		return passCount;
	}

	public void setPassCount(int passCount) {
		this.passCount = passCount;
	}

	public long getAvg() {
		return avg;
	}

	public void setAvg(long avg) {
		this.avg = avg;
	}

	public long getMin() {
		return min;
	}

	public void setMin(long min) {
		this.min = min;
	}

	public long getMax() {
		return max;
	}

	public void setMax(long max) {
		this.max = max;
	}

	public long getTakeTimes() {
		return takeTimes;
	}

	public void setTakeTimes(long takeTimes) {
		this.takeTimes = takeTimes;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

}
