package cj.studio.nettest.be.args;

public class RequestNetprotocol {
	String protocol;
	String mid;
	String creator;
	public RequestNetprotocol() {
	}
	public RequestNetprotocol(String protocol, String mid, String creator) {
		super();
		this.protocol = protocol;
		this.mid = mid;
		this.creator = creator;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
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
