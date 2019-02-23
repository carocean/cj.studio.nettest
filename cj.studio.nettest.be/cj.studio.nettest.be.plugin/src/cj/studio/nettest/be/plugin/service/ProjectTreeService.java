package cj.studio.nettest.be.plugin.service;

import java.util.ArrayList;
import java.util.List;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.IDocument;
import cj.lns.chip.sos.cube.framework.IQuery;
import cj.lns.chip.sos.cube.framework.TupleDocument;
import cj.studio.ecm.EcmException;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.nettest.be.args.TFolder;
import cj.studio.nettest.be.args.TMethod;
import cj.studio.nettest.be.args.TService;
import cj.studio.nettest.be.service.IProjectTreeService;

@CjService(name = "ptService")
public class ProjectTreeService implements IProjectTreeService {
	@CjServiceRef(refByName = "mongodb.netos.test")
	ICube test;

	@Override
	public String addFolder(TFolder folder) {
		folder.setId(null);
		if (getFolder(folder.getCode()) != null) {
			throw new EcmException("已存在文件夹：" + folder.getCode());
		}
		String id=test.saveDoc("folders", new TupleDocument<>(folder));
		folder.setId(id);
		return id;
	}

	@Override
	public void removeFolder(String folderCode) {
		TFolder folder = getFolder(folderCode);
		if (folder == null)
			return;
		test.deleteDocOne("folders", String.format("{'tuple.code':'%s'}", folderCode));
		List<TService> services = getServices(folderCode);
		for (TService s : services) {
			String full = folderCode + "." + s.getCode();
			removeService(full);
			List<TMethod> methods = getMethods(full);
			for (TMethod m : methods) {
				removeMethod(full + "." + m.getCode());
			}
		}
	}

	@Override
	public List<TFolder> getFolders() {
		String cjql = String.format("select {'tuple':'*'} from tuple folders %s where {}", TFolder.class.getName());
		IQuery<TFolder> q = test.createQuery(cjql);
		List<IDocument<TFolder>> docs = q.getResultList();
		List<TFolder> list = new ArrayList<>();
		for (IDocument<TFolder> doc : docs) {
			doc.tuple().setId(doc.docid());
			list.add(doc.tuple());
		}
		return list;
	}

	@Override
	public List<TFolder> getMyFolders(String creator) {
		String cjql = String.format("select {'tuple':'*'} from tuple folders %s where {'tuple.creator':'%s'}",
				TFolder.class.getName(), creator);
		IQuery<TFolder> q = test.createQuery(cjql);
		List<IDocument<TFolder>> docs = q.getResultList();
		List<TFolder> list = new ArrayList<>();
		for (IDocument<TFolder> doc : docs) {
			doc.tuple().setId(doc.docid());
			list.add(doc.tuple());
		}
		return list;
	}

	@Override
	public TFolder getFolder(String folderCode) {
		String cjql = String.format("select {'tuple':'*'} from tuple folders %s where {'tuple.code':'%s'}",
				TFolder.class.getName(), folderCode);
		IQuery<TFolder> q = test.createQuery(cjql);
		IDocument<TFolder> doc = q.getSingleResult();
		if (doc == null)
			return null;
		doc.tuple().setId(doc.docid());
		return doc.tuple();
	}

	@Override
	public TFolder getFolderById(String id) {
		String cjql = String.format("select {'tuple':'*'} from tuple folders %s where {'_id':ObjectId('%s')}",
				TFolder.class.getName(), id);
		IQuery<TFolder> q = test.createQuery(cjql);
		IDocument<TFolder> doc = q.getSingleResult();
		if (doc == null)
			return null;
		doc.tuple().setId(doc.docid());
		return doc.tuple();
	}

	@Override
	public int getMethodCountOfFolder(String folderCode) {
		List<TService> list = getServices(folderCode);
		int i = 0;
		for (TService service : list) {
			List<TMethod> methods = getMethods(String.format("%s.%s", service.getFolder(), service.getCode()));
			i += methods.size();
		}
		return i;
	}

	@Override
	public String addService(TService service) {
		service.setId(null);
		String fullName = String.format("%s.%s", service.getFolder(), service.getCode());
		if (getService(fullName) != null) {
			throw new EcmException("已存在服务：" + service.getCode());
		}
		String id=test.saveDoc("services", new TupleDocument<>(service));
		service.setId(id);
		return id;
	}

	@Override
	public void removeService(String serviceCode) {
		if (!serviceCode.contains(".")) {
			throw new EcmException("参数serviceCode格式错误，应为:folderCode.serviceCode");
		}
		int pos = serviceCode.indexOf(".");
		String folderCode = serviceCode.substring(0, pos);
		serviceCode = serviceCode.substring(pos + 1, serviceCode.length());
		String full = folderCode + "." + serviceCode;
		TService service = getService(full);
		if (service == null)
			return;
		test.deleteDocOne("services",
				String.format("{'tuple.code':'%s','tuple.folder':'%s'}", serviceCode, folderCode));

		List<TMethod> methods = getMethods(full);
		for (TMethod m : methods) {
			removeMethod(full + "." + m.getCode());
		}
	}

	@Override
	public List<TService> getServices(String folderCode) {
		String cjql = String.format("select {'tuple':'*'} from tuple services %s where {'tuple.folder':'%s'}",
				TService.class.getName(), folderCode);
		IQuery<TService> q = test.createQuery(cjql);
		List<IDocument<TService>> docs = q.getResultList();
		List<TService> list = new ArrayList<>();
		for (IDocument<TService> doc : docs) {
			doc.tuple().setId(doc.docid());
			list.add(doc.tuple());
		}
		return list;
	}

	@Override
	public List<TService> getMyServices(String folderCode, String creator) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple services %s where {'tuple.folder':'%s','tuple.creator':'%s'}",
				TService.class.getName(), folderCode, creator);
		IQuery<TService> q = test.createQuery(cjql);
		List<IDocument<TService>> docs = q.getResultList();
		List<TService> list = new ArrayList<>();
		for (IDocument<TService> doc : docs) {
			doc.tuple().setId(doc.docid());
			list.add(doc.tuple());
		}
		return list;
	}

	@Override
	public TService getService(String serviceCode) {
		if (!serviceCode.contains(".")) {
			throw new EcmException("参数serviceCode格式错误，应为:folderCode.serviceCode");
		}
		int pos = serviceCode.indexOf(".");
		String folderCode = serviceCode.substring(0, pos);
		serviceCode = serviceCode.substring(pos + 1, serviceCode.length());
		String cjql = String.format(
				"select {'tuple':'*'} from tuple services %s where {'tuple.code':'%s','tuple.folder':'%s'}",
				TService.class.getName(), serviceCode, folderCode);
		IQuery<TService> q = test.createQuery(cjql);
		IDocument<TService> doc = q.getSingleResult();
		if (doc == null)
			return null;
		doc.tuple().setId(doc.docid());
		return doc.tuple();
	}

	@Override
	public TService getServiceById(String id) {
		String cjql = String.format("select {'tuple':'*'} from tuple services %s where {'_id':ObjectId('%s')}",
				TService.class.getName(), id);
		IQuery<TService> q = test.createQuery(cjql);
		IDocument<TService> doc = q.getSingleResult();
		if (doc == null)
			return null;
		doc.tuple().setId(doc.docid());
		return doc.tuple();
	}

	@Override
	public String addMethod(TMethod method) {
		method.setId(null);
		String fullName = String.format("%s.%s.%s", method.getFolder(), method.getService(), method.getCode());
		if (getMethod(fullName) != null) {
			throw new EcmException("已存在方法：" + method.getCode());
		}
		String id=test.saveDoc("methods", new TupleDocument<>(method));
		method.setId(id);
		return id;
	}

	@Override
	public void removeMethod(String methodCode) {
		if (!methodCode.contains(".")) {
			throw new EcmException("参数methodCode格式错误，应为:folderCode.serviceCode.methodCode");
		}
		int pos = methodCode.indexOf(".");
		String folderCode = methodCode.substring(0, pos);
		String remaining = methodCode.substring(pos + 1, methodCode.length());
		if (!remaining.contains(".")) {
			throw new EcmException("参数methodCode格式错误，应为:folderCode.serviceCode.methodCode");
		}
		pos = remaining.indexOf(".");
		String serviceCode = remaining.substring(0, pos);
		methodCode = remaining.substring(pos + 1, remaining.length());
		test.deleteDocOne("methods", String.format("{'tuple.code':'%s','tuple.service':'%s','tuple.folder':'%s'}",
				methodCode, serviceCode, folderCode));
	}

	@Override
	public List<TMethod> getMethods(String serviceCode) {
		if (!serviceCode.contains(".")) {
			throw new EcmException("参数methodCode格式错误，应为:folderCode.serviceCode");
		}
		int pos = serviceCode.indexOf(".");
		String folderCode = serviceCode.substring(0, pos);
		serviceCode = serviceCode.substring(pos + 1, serviceCode.length());

		String cjql = String.format(
				"select {'tuple':'*'} from tuple methods %s where {'tuple.folder':'%s','tuple.service':'%s'}",
				TMethod.class.getName(), folderCode, serviceCode);
		IQuery<TMethod> q = test.createQuery(cjql);
		List<IDocument<TMethod>> docs = q.getResultList();
		List<TMethod> list = new ArrayList<>();
		for (IDocument<TMethod> doc : docs) {
			doc.tuple().setId(doc.docid());
			list.add(doc.tuple());
		}
		return list;
	}

	@Override
	public List<TMethod> getMyMethods(String serviceCode, String creator) {
		if (!serviceCode.contains(".")) {
			throw new EcmException("参数methodCode格式错误，应为:folderCode.serviceCode");
		}
		int pos = serviceCode.indexOf(".");
		String folderCode = serviceCode.substring(0, pos);
		serviceCode = serviceCode.substring(pos + 1, serviceCode.length());

		String cjql = String.format(
				"select {'tuple':'*'} from tuple methods %s where {'tuple.folder':'%s','tuple.service':'%s','tuple.creator':'%s'}",
				TMethod.class.getName(), folderCode, serviceCode, creator);
		IQuery<TMethod> q = test.createQuery(cjql);
		List<IDocument<TMethod>> docs = q.getResultList();
		List<TMethod> list = new ArrayList<>();
		for (IDocument<TMethod> doc : docs) {
			doc.tuple().setId(doc.docid());
			list.add(doc.tuple());
		}
		return list;
	}

	@Override
	public TMethod getMethod(String methodCode) {
		if (!methodCode.contains(".")) {
			throw new EcmException("参数methodCode格式错误，应为:folderCode.serviceCode.methodCode");
		}
		int pos = methodCode.indexOf(".");
		String folderCode = methodCode.substring(0, pos);
		String remaining = methodCode.substring(pos + 1, methodCode.length());
		if (!remaining.contains(".")) {
			throw new EcmException("参数methodCode格式错误，应为:folderCode.serviceCode.methodCode");
		}
		pos = remaining.indexOf(".");
		String serviceCode = remaining.substring(0, pos);
		methodCode = remaining.substring(pos + 1, remaining.length());

		String cjql = String.format(
				"select {'tuple':'*'} from tuple methods %s where {'tuple.code':'%s','tuple.service':'%s','tuple.folder':'%s'}",
				TMethod.class.getName(), methodCode, serviceCode, folderCode);
		IQuery<TMethod> q = test.createQuery(cjql);
		IDocument<TMethod> doc = q.getSingleResult();
		if (doc == null)
			return null;
		doc.tuple().setId(doc.docid());
		return doc.tuple();
	}

	@Override
	public TMethod getMethodById(String id) {
		String cjql = String.format("select {'tuple':'*'} from tuple methods %s where {'_id':ObjectId('%s')}",
				TMethod.class.getName(), id);
		IQuery<TMethod> q = test.createQuery(cjql);
		IDocument<TMethod> doc = q.getSingleResult();
		if (doc == null)
			return null;
		doc.tuple().setId(doc.docid());
		return doc.tuple();
	}

	@Override
	public void updateFolder(String id, TFolder folder) {
		folder.setId(null);
		test.updateDoc("folders", id, new TupleDocument<>(folder));
	}

	@Override
	public void updateService(String id, TService service) {
		service.setId(null);
		test.updateDoc("services", id, new TupleDocument<>(service));
	}

	@Override
	public void updateMethod(String id, TMethod method) {
		method.setId(null);
		test.updateDoc("methods", id, new TupleDocument<>(method));
	}

}
