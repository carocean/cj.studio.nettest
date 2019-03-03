package cj.studio.nettest.jobserver.program;

import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.net.CircuitException;
import cj.studio.gateway.socket.Destination;
import cj.studio.gateway.socket.app.GatewayAppSiteProgram;
import cj.studio.gateway.socket.app.ProgramAdapterType;
@CjService(name="$.cj.studio.gateway.app",isExoteric=true)
public class AppSiteProgram extends GatewayAppSiteProgram{

	@Override
	protected void onstart(Destination arg0, String arg1, ProgramAdapterType arg2) throws CircuitException {
		// TODO Auto-generated method stub
		
	}
	
}
