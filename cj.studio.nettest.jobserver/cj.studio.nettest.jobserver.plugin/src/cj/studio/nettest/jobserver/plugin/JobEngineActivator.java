package cj.studio.nettest.jobserver.plugin;

import cj.studio.ecm.IEntryPointActivator;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.context.IElement;
import cj.studio.nettest.jobserver.plugin.jobcore.IJobEngine;
import cj.ultimate.IClosable;

public class JobEngineActivator implements IEntryPointActivator {

	@Override
	public void activate(IServiceSite site, IElement e) {
		IJobEngine engine=(IJobEngine)site.getService("jobEngine");
		engine.run();
	}

	@Override
	public void inactivate(IServiceSite site) {
		IClosable engine=(IClosable)site.getService("jobEngine");
		engine.close();
	}

}
