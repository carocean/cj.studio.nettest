package cj.studio.nettest.fe.program;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cj.studio.ecm.annotation.CjService;

@CjService(name="online")
public class OnlineTable implements IOnlineTable {
	Map<String, String> table;
	public OnlineTable() {
		this.table=new ConcurrentHashMap<>();
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
	@Override
	public void offPipeline(String inputName) {
		for(String key:table.keySet()) {
			String v=table.get(key);
			if(v.equals(inputName)) {
				table.remove(key);
				System.out.println("用户已下线："+key);
			}
		}
		
	}
	
}
