package cj.studio.nettest.be.stub;

import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubService;

@CjStubService(bindService = "/retrieveConfig.service", usage = "从别的用户中找回该用户的配置，即从别的用户那拷贝一份")
public interface IRetrieveConfigStub {
	@CjStubMethod(usage = "从已存在用户那里找回所有配置到其它用户")
	void retrieveAll(@CjStubInParameter(key = "existsUser", usage = "将已存在的用户所创建的所有对象") String existsUser,
			@CjStubInParameter(key = "otherUser", usage = "设为其它用户") String otherUser);
}
