package cj.studio.nettest.be.service;

import java.util.List;

import cj.studio.nettest.be.args.RunnerReport;
import cj.studio.nettest.be.args.RunnerStrategy;
import cj.studio.nettest.be.args.TFolder;
import cj.studio.nettest.be.args.TMethod;
import cj.studio.nettest.be.args.TService;

public interface IProjectTreeService {
	String addFolder(TFolder folder);

	void updateFolder(String id, TFolder folder);

	void removeFolder(String folderCode);

	List<TFolder> getFolders();

	TFolder getFolder(String folderCode);

	int getMethodCountOfFolder(String folderCode);

	String addService(TService service);

	void updateService(String id, TService service);

	void removeService(String serviceCode);

	List<TService> getServices(String folderCode);

	TService getService(String serviceCode);

	String addMethod(TMethod method);

	void updateMethod(String id, TMethod method);

	void removeMethod(String methodCode);

	List<TMethod> getMethods(String serviceCode);

	TMethod getMethod(String methodCode);

	public List<TFolder> getMyFolders(String creator);

	public TFolder getFolderById(String id);

	public List<TService> getMyServices(String folderCode, String creator);

	public TService getServiceById(String id);

	public List<TMethod> getMyMethods(String serviceCode, String creator);

	public TMethod getMethodById(String id);

	RunnerStrategy getRunnerStrategy(String mid, String creator);

	RunnerReport getRunnerReport(String mid, String creator);

	void saveRunnerStrategy(RunnerStrategy strategy);

	

}
