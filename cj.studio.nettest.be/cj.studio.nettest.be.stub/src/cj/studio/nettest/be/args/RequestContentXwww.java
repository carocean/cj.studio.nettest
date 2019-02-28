package cj.studio.nettest.be.args;

public class RequestContentXwww {
	String id;
	String key;
	String value;
	String desc;
	String creator;
	String mid;
	public RequestContentXwww() {
		// TODO Auto-generated constructor stub
	}
	public RequestContentXwww(String mid,String id, String key, String value, String desc, String creator) {
		super();
		this.id = id;
		this.key = key;
		this.value = value;
		this.desc = desc;
		this.creator = creator;
		this.mid=mid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
