package cj.studio.nettest.be.args;

public class RequestHost {
	String host;
	String mid;
	String creator;
	public RequestHost() {
	}
	public RequestHost(String host, String mid, String creator) {
		super();
		this.host = host;
		this.mid = mid;
		this.creator = creator;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
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
}
