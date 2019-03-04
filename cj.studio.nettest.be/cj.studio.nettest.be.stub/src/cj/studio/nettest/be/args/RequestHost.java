package cj.studio.nettest.be.args;

public class RequestHost {
	String host;
	String mid;
	String creator;
	String dest;
	public RequestHost() {
	}
	public RequestHost(String host, String mid,String dest, String creator) {
		super();
		this.host = host;
		this.mid = mid;
		this.creator = creator;
		this.dest=dest;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
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
