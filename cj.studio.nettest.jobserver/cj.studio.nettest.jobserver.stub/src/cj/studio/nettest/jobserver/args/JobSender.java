package cj.studio.nettest.jobserver.args;

public class JobSender {
	String sender;
	String backendid;
	public JobSender() {
	}
	public JobSender(String sender,  String backendid) {
		super();
		this.sender = sender;
		this.backendid = backendid;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getBackendid() {
		return backendid;
	}
	public void setBackendid(String backendid) {
		this.backendid = backendid;
	}
	
}
