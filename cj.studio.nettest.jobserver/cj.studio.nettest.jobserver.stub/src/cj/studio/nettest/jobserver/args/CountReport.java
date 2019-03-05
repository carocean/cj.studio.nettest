package cj.studio.nettest.jobserver.args;

public class CountReport {
	String mid;
	String creator;
	int simCount;
	int passCount;
	long min;
	long max;
	long takeTimes;

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
