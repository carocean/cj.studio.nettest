package cj.studio.nettest.be.program.stub;

import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;
import cj.studio.nettest.be.service.IRetrieveConfigService;
import cj.studio.nettest.be.stub.IRetrieveConfigStub;
@CjService(name = "/retrieveConfig.service")
public class RetrieveConfigStub  extends GatewayAppSiteRestStub  implements IRetrieveConfigStub {
	@CjServiceRef(refByName = "nettestplugin.retrieveConfigService")
	IRetrieveConfigService retrieveConfigService;
	@Override
	public void retrieveAll(String existsUser, String otherUser) {
		retrieveConfigService.retrieveAll(existsUser,otherUser);
	}

}
