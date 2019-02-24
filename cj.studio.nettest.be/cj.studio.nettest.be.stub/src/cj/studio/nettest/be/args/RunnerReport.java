package cj.studio.nettest.be.args;

public class RunnerReport {
	String id;
	String mid;
	String creator;
	int samples;
	float avg;
	float min;
	float max;
	float errorRate;
	float passedRate;
	public RunnerReport() {
	}
	public RunnerReport(String id, String mid, String creator, int samples, float avg, float min, float max, float errorRate,
			float passedRate) {
		super();
		this.id = id;
		this.mid = mid;
		this.creator = creator;
		this.samples = samples;
		this.avg = avg;
		this.min = min;
		this.max = max;
		this.errorRate = errorRate;
		this.passedRate = passedRate;
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
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public int getSamples() {
		return samples;
	}
	public void setSamples(int samples) {
		this.samples = samples;
	}
	public float getAvg() {
		return avg;
	}
	public void setAvg(float avg) {
		this.avg = avg;
	}
	public float getMin() {
		return min;
	}
	public void setMin(float min) {
		this.min = min;
	}
	public float getMax() {
		return max;
	}
	public void setMax(float max) {
		this.max = max;
	}
	public float getErrorRate() {
		return errorRate;
	}
	public void setErrorRate(float errorRate) {
		this.errorRate = errorRate;
	}
	public float getPassedRate() {
		return passedRate;
	}
	public void setPassedRate(float passedRate) {
		this.passedRate = passedRate;
	}
	
}
