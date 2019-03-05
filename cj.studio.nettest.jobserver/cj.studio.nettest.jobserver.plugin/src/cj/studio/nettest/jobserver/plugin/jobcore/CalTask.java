package cj.studio.nettest.jobserver.plugin.jobcore;

public class CalTask {
	CalCommand cmd;
	Object data;
	
	public CalTask(CalCommand cmd, Object data) {
		this.cmd = cmd;
		this.data = data;
	}
	public CalCommand getCmd() {
		return cmd;
	}
	public Object getData() {
		return data;
	}
	
}
