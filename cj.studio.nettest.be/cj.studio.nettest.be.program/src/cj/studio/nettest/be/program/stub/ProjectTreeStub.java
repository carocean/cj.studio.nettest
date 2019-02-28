package cj.studio.nettest.be.program.stub;

import java.util.List;

import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;
import cj.studio.nettest.be.args.RunnerReport;
import cj.studio.nettest.be.args.RunnerStrategy;
import cj.studio.nettest.be.args.TFolder;
import cj.studio.nettest.be.args.TMethod;
import cj.studio.nettest.be.args.TService;
import cj.studio.nettest.be.service.IProjectTreeService;
import cj.studio.nettest.be.stub.IProjectTreeStub;

@CjService(name = "/projectTree.service")
public class ProjectTreeStub extends GatewayAppSiteRestStub implements IProjectTreeStub {
	@CjServiceRef(refByName = "nettestplugin.ptService")
	IProjectTreeService ptService;

	@Override
	public String addFolder(TFolder folder) {
		folder.setCtime(System.currentTimeMillis());
		return ptService.addFolder(folder);
	}

	@Override
	public void removeFolder(String folderCode) {
		ptService.removeFolder(folderCode);
	}

	@Override
	public List<TFolder> getFolders() {
		return ptService.getFolders();
	}

	@Override
	public TFolder getFolder(String folderCode) {
		return ptService.getFolder(folderCode);
	}

	@Override
	public int getMethodCountOfFolder(String folderCode) {
		return ptService.getMethodCountOfFolder(folderCode);
	}

	@Override
	public String addService(TService service) {
		service.setCtime(System.currentTimeMillis());
		return ptService.addService(service);
	}

	@Override
	public void removeService(String serviceCode) {
		ptService.removeService(serviceCode);
	}

	@Override
	public List<TService> getServices(String folderCode) {
		return ptService.getServices(folderCode);
	}

	@Override
	public TService getService(String serviceCode) {
		return ptService.getService(serviceCode);
	}

	@Override
	public String addMethod(TMethod method) {
		method.setCtime(System.currentTimeMillis());
		return ptService.addMethod(method);
	}

	@Override
	public void removeMethod(String methodCode) {
		ptService.removeMethod(methodCode);
	}

	@Override
	public List<TMethod> getMethods(String serviceCode) {
		return ptService.getMethods(serviceCode);
	}

	@Override
	public TMethod getMethod(String methodCode) {
		return ptService.getMethod(methodCode);
	}

	@Override
	public void updateFolder(String id, TFolder folder) {
		ptService.updateFolder(id, folder);
	}

	@Override
	public void updateMethod(String id, TMethod method) {
		ptService.updateMethod(id, method);
	}

	@Override
	public void updateService(String id, TService service) {
		ptService.updateService(id, service);
	}

	@Override
	public List<TFolder> getMyFolders(String creator) {
		return ptService.getMyFolders(creator);
	}

	@Override
	public TFolder getFolderById(String id) {
		return ptService.getFolderById(id);
	}

	@Override
	public List<TService> getMyServices(String folderCode, String creator) {
		return ptService.getMyServices(folderCode, creator);
	}

	@Override
	public TService getServiceById(String id) {
		return ptService.getServiceById(id);
	}

	@Override
	public List<TMethod> getMyMethods(String serviceCode, String creator) {
		return ptService.getMyMethods(serviceCode, creator);
	}

	@Override
	public TMethod getMethodById(String id) {
		return ptService.getMethodById(id);
	}

	@Override
	public RunnerStrategy getRunnerStrategy(String mid, String creator) {
		return ptService.getRunnerStrategy(mid, creator);
	}

	@Override
	public RunnerReport getRunnerReport(String mid, String creator) {
		return ptService.getRunnerReport(mid, creator);
	}

	@Override
	public void saveRunnerStrategy(RunnerStrategy strategy) {
		ptService.saveRunnerStrategy(strategy);
	}

}
