package cj.studio.nettest.be.args;

public class RequestContentAny {
	String content;
	String creator;
	String mid;
	public RequestContentAny() {
		// TODO Auto-generated constructor stub
	}
	public RequestContentAny(String mid,String content, String creator) {
		super();
		this.content = content;
		this.creator = creator;
		this.mid=mid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
