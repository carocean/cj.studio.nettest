package cj.studio.nettest.fe.program;

import java.util.HashMap;
import java.util.Map;

import cj.studio.ecm.annotation.CjService;

@CjService(name="online")
public class OnlineTable implements IOnlineTable {
	Map<String, String> table;
	public OnlineTable() {
		this.table=new HashMap<>();
	}
	@Override
	public void on(String user, String pipelineName) {
		table.put(user, pipelineName);
	}

	@Override
	public void off(String user) {
		table.remove(user);
	}
	@Override
	public String getUserOnPipeline(String user) {
		return table.get(user);
	}
	
}
