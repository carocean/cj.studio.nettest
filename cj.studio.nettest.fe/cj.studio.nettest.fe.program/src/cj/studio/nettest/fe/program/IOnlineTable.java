package cj.studio.nettest.fe.program;

public interface IOnlineTable {
	void on(String user,String pipelineName);
	void off(String user);
	String getUserOnPipeline(String user);
}
