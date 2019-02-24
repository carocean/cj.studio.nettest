package cj.studio.nettest.be.stub;

import java.util.List;

import cj.studio.gateway.stub.annotation.CjStubInContentKey;
import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubReturn;
import cj.studio.gateway.stub.annotation.CjStubService;
import cj.studio.nettest.be.args.RunnerReport;
import cj.studio.nettest.be.args.RunnerStrategy;
import cj.studio.nettest.be.args.TFolder;
import cj.studio.nettest.be.args.TMethod;
import cj.studio.nettest.be.args.TService;

@CjStubService(bindService = "/projectTree.service", usage = "项目目录树")
public interface IProjectTreeStub {
	@CjStubMethod(command = "post", usage = "添加文件夹")
	@CjStubReturn(usage = "文件夹标识")
	String addFolder(@CjStubInContentKey(key = "folder", usage = "文件夹对象") TFolder folder);

	@CjStubMethod(command = "post", usage = "添加文件夹")
	void updateFolder(@CjStubInContentKey(key = "id", usage = "标识") String id,
			@CjStubInContentKey(key = "folder", usage = "文件夹对象") TFolder folder);

	@CjStubMethod(usage = "移除文件夹")
	void removeFolder(@CjStubInParameter(key = "folderCode", usage = "文件夹代码") String folderCode);

	@CjStubMethod(usage = "获取所有文件夹")
	@CjStubReturn(elementType = TFolder.class, usage = "文件夹集合")
	List<TFolder> getFolders();

	@CjStubMethod(usage = "获取所有文件夹")
	@CjStubReturn(elementType = TFolder.class, usage = "文件夹集合")
	List<TFolder> getMyFolders(@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(usage = "获取文件夹")
	@CjStubReturn(type = TFolder.class, usage = "文件夹")
	TFolder getFolder(@CjStubInParameter(key = "folderCode", usage = "文件夹代码") String folderCode);

	@CjStubMethod(usage = "获取文件夹")
	@CjStubReturn(type = TFolder.class, usage = "文件夹")
	TFolder getFolderById(@CjStubInParameter(key = "id", usage = "文件夹标识") String id);

	@CjStubMethod(usage = "获取文件夹内所有方法数")
	int getMethodCountOfFolder(@CjStubInParameter(key = "folderCode", usage = "文件夹代码") String folderCode);

	@CjStubMethod(command = "post", usage = "添加服务")
	@CjStubReturn(usage = "标识")
	String addService(@CjStubInContentKey(key = "service", usage = "服务对象") TService service);

	@CjStubMethod(command = "post", usage = "添加服务")
	void updateService(@CjStubInContentKey(key = "id", usage = "标识") String id,
			@CjStubInContentKey(key = "service", usage = "服务对象") TService service);

	@CjStubMethod(usage = "移除服务")
	void removeService(
			@CjStubInParameter(key = "serviceCode", usage = "服务代码，表示格式：folderCode.serviceCode") String serviceCode);

	@CjStubMethod(usage = "获取指定文件夹下所有服务")
	@CjStubReturn(elementType = TService.class, usage = "服务集合")
	List<TService> getServices(@CjStubInParameter(key = "folderCode", usage = "文件夹代码") String folderCode);

	@CjStubMethod(usage = "获取指定文件夹下所有服务")
	@CjStubReturn(elementType = TService.class, usage = "服务集合")
	List<TService> getMyServices(@CjStubInParameter(key = "folderCode", usage = "文件夹代码") String folderCode,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(usage = "获取服务")
	@CjStubReturn(type = TService.class, usage = "服务")
	TService getService(
			@CjStubInParameter(key = "serviceCode", usage = "服务代码，表示格式：folderCode.serviceCode") String serviceCode);

	@CjStubMethod(usage = "获取服务")
	@CjStubReturn(type = TService.class, usage = "服务")
	TService getServiceById(@CjStubInParameter(key = "id", usage = "标识") String id);

	@CjStubMethod(command = "post", usage = "添加服务方法")
	@CjStubReturn(usage = "标识")
	String addMethod(
			@CjStubInContentKey(key = "method", usage = "服务方法,表示格式：folderCode.serviceCode.methodCode") TMethod method);

	@CjStubMethod(command = "post", usage = "添加服务方法")
	void updateMethod(@CjStubInContentKey(key = "id", usage = "标识") String id,
			@CjStubInContentKey(key = "method", usage = "服务方法,表示格式：folderCode.serviceCode.methodCode") TMethod method);

	@CjStubMethod(usage = "移除服务方法")
	void removeMethod(
			@CjStubInParameter(key = "methodCode", usage = "服务方法的代码,表示格式：folderCode.serviceCode.methodCode") String methodCode);

	@CjStubMethod(usage = "获取指定服务下所有方法")
	@CjStubReturn(elementType = TMethod.class, usage = "方法集合")
	List<TMethod> getMethods(
			@CjStubInParameter(key = "serviceCode", usage = "服务代码，表示格式：folderCode.serviceCode") String serviceCode);

	@CjStubMethod(usage = "获取指定服务下所有方法")
	@CjStubReturn(elementType = TMethod.class, usage = "方法集合")
	List<TMethod> getMyMethods(
			@CjStubInParameter(key = "serviceCode", usage = "服务代码，表示格式：folderCode.serviceCode") String serviceCode,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(usage = "获取方法")
	@CjStubReturn(type = TMethod.class, usage = "方法")
	TMethod getMethod(
			@CjStubInParameter(key = "methodCode", usage = "服务方法的代码,表示格式：folderCode.serviceCode.methodCode") String methodCode);

	@CjStubMethod(usage = "获取方法")
	@CjStubReturn(type = TMethod.class, usage = "方法")
	TMethod getMethodById(@CjStubInParameter(key = "id", usage = "标识") String id);

	@CjStubMethod(usage = "获取运行策略")
	RunnerStrategy getRunnerStrategy(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(usage = "获取运行报告")
	RunnerReport getRunnerReport(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(command = "post", usage = "获取运行报告")
	void saveRunnerStrategy(@CjStubInContentKey(key = "strategy", usage = "运行策略") RunnerStrategy strategy);
}
