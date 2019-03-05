package cj.studio.nettest.be.args;
/**
 * 一次取样报告
 * @author caroceanjofers
 *
 */
public class SimpleReport {
	int state;
	String message;
	String response;
	long takeTime;//耗时
	String mid;//请求的标识
	String creator;
	long ctime;
	public long getCtime() {
		return ctime;
	}
	public void setCtime(long ctime) {
		this.ctime = ctime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
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
	public long getTakeTime() {
		return takeTime;
	}
	public void setTakeTime(long takeTime) {
		this.takeTime = takeTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
}
