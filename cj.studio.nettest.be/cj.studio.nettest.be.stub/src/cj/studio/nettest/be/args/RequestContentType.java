package cj.studio.nettest.be.args;

public class RequestContentType {
	String type;
	String creator;
	private String mid;
	public RequestContentType() {
		// TODO Auto-generated constructor stub
	}
	public RequestContentType(String mid,String type, String creator) {
		super();
		this.type = type;
		this.creator = creator;
		this.mid=mid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
