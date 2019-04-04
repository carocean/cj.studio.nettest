package cj.studio.nettest.fe.program.webview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cj.studio.backend.uc.bo.Role;
import cj.studio.backend.uc.stub.IAuthenticationStub;
import cj.studio.backend.uc.stub.IRoleStub;
import cj.studio.backend.uc.stub.ITokenStub;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.net.Circuit;
import cj.studio.ecm.net.CircuitException;
import cj.studio.ecm.net.Frame;
import cj.studio.ecm.net.http.CookieHelper;
import cj.studio.ecm.net.http.HttpFrame;
import cj.studio.gateway.socket.app.IGatewayAppSiteResource;
import cj.studio.gateway.socket.app.IGatewayAppSiteWayWebView;
import cj.studio.gateway.socket.io.XwwwFormUrlencodedContentReciever;
import cj.studio.gateway.stub.annotation.CjStubRef;
import cj.studio.gateway.stub.util.StringTypeConverter;
import cj.ultimate.gson2.com.google.gson.Gson;
import cj.ultimate.gson2.com.google.gson.reflect.TypeToken;

@CjBridge(aspects = "@rest")
@CjService(name = "/public/auth.service")
public class AuthWebView implements IGatewayAppSiteWayWebView, StringTypeConverter {
	@CjStubRef(remote = "rest://backend/uc/", stub = IAuthenticationStub.class)
	IAuthenticationStub auth;
	@CjStubRef(remote = "rest://backend/uc/", stub = ITokenStub.class)
	ITokenStub token;
	@CjStubRef(remote = "rest://backend/uc/", stub = IRoleStub.class)
	IRoleStub role;

	@Override
	public void flow(Frame f, Circuit c, IGatewayAppSiteResource ctx) throws CircuitException {
		f.content().accept(new XwwwFormUrlencodedContentReciever() {

			@Override
			protected void done(Frame f) throws CircuitException {
				String user = f.parameter("user");//该user是账号也可能是统一用户
				String pwd = f.parameter("pwd");
				String json = auth.authenticate("auth.password", "netos.nettest", user, pwd, Integer.MAX_VALUE);
				Map<String, String> map = new Gson().fromJson(json, new TypeToken<HashMap<String, String>>() {
				}.getType());
				if (!"200".equals(map.get("status"))) {
					throw new CircuitException(map.get("status"), map.get("message"));
				}
				HttpFrame frame = (HttpFrame) f;
				String cjtoken=map.get("result");
				Map<String,Object> entry=token.parse(cjtoken);
				frame.session().attribute("uc.token", cjtoken);
				user=(String)entry.get("user");//这才是统一用户
				frame.session().attribute("uc.principals", user);

				ArrayList<Role> roles = (ArrayList<Role>) role.getRolesOnUser(user);
				frame.session().attribute("uc.roles", roles);
				CookieHelper.appendCookie(c, "cjtoken", map.get("result"));
			}
		});
	}

}
