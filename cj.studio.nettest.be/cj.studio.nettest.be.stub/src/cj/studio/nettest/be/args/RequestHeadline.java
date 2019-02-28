package cj.studio.nettest.be.args;

public class RequestHeadline {
	String mid;
	String cmd;
	String url;
	String protocol;
	String creator;
	public RequestHeadline() {
		// TODO Auto-generated constructor stub
	}
	
	public RequestHeadline(String mid, String cmd, String url, String protocol, String creator) {
		super();
		this.mid = mid;
		this.cmd = cmd;
		this.url = url;
		this.protocol = protocol;
		this.creator = creator;
	}

	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
}
