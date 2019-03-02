package cj.studio.nettest.be.plugin.service;

import java.util.ArrayList;
import java.util.List;

import cj.lns.chip.sos.cube.framework.ICube;
import cj.lns.chip.sos.cube.framework.IDocument;
import cj.lns.chip.sos.cube.framework.IQuery;
import cj.lns.chip.sos.cube.framework.TupleDocument;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.nettest.be.args.RequestContentAny;
import cj.studio.nettest.be.args.RequestContentFormData;
import cj.studio.nettest.be.args.RequestContentType;
import cj.studio.nettest.be.args.RequestContentXwww;
import cj.studio.nettest.be.args.RequestHeader;
import cj.studio.nettest.be.args.RequestHeadline;
import cj.studio.nettest.be.args.RequestHost;
import cj.studio.nettest.be.args.RequestNetprotocol;
import cj.studio.nettest.be.args.RequestParameter;
import cj.studio.nettest.be.service.IRequestConfigService;
@CjService(name = "rcService")
public class RequestConfigService implements IRequestConfigService{
	@CjServiceRef(refByName = "mongodb.netos.test")
	ICube test;
	@Override
	public void saveAndUpdateRequestNetprotocol(String mid, String netprotocol, String creator) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple request.netprotocols %s where {'tuple.mid':'%s','tuple.creator':'%s'}",
				RequestNetprotocol.class.getName(), mid, creator);
		IQuery<RequestNetprotocol> q = test.createQuery(cjql);
		IDocument<RequestNetprotocol> doc = q.getSingleResult();
		if (doc == null) {
			// add
			RequestNetprotocol rnp = new RequestNetprotocol(netprotocol, mid, creator);
			test.saveDoc("request.netprotocols", new TupleDocument<>(rnp));
			return;
		}
		// update
		RequestNetprotocol newrnp = new RequestNetprotocol(netprotocol, mid, creator);
		test.updateDoc("request.netprotocols", doc.docid(), new TupleDocument<>(newrnp));
	}

	@Override
	public RequestNetprotocol getMyRequestNetprotocol(String mid, String creator) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple request.netprotocols %s where {'tuple.mid':'%s','tuple.creator':'%s'}",
				RequestNetprotocol.class.getName(), mid, creator);
		IQuery<RequestNetprotocol> q = test.createQuery(cjql);
		IDocument<RequestNetprotocol> doc = q.getSingleResult();
		if (doc == null)
			return null;
		return doc.tuple();
	}
	@Override
	public void saveAndUpdateRequestHost(String mid, String host, String creator) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple request.hosts %s where {'tuple.mid':'%s','tuple.creator':'%s'}",
				RequestHost.class.getName(), mid, creator);
		IQuery<RequestHost> q = test.createQuery(cjql);
		IDocument<RequestHost> doc = q.getSingleResult();
		if (doc == null) {
			// add
			RequestHost rnp = new RequestHost(host,mid, creator);
			test.saveDoc("request.hosts", new TupleDocument<>(rnp));
			return;
		}
		// update
		RequestHost newrnp = new RequestHost(host, mid, creator);
		test.updateDoc("request.hosts", doc.docid(), new TupleDocument<>(newrnp));
	}
	@Override
	public RequestHost getMyRequestHost(String mid, String creator) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple request.hosts %s where {'tuple.mid':'%s','tuple.creator':'%s'}",
				RequestHost.class.getName(), mid, creator);
		IQuery<RequestHost> q = test.createQuery(cjql);
		IDocument<RequestHost> doc = q.getSingleResult();
		if (doc == null)
			return null;
		return doc.tuple();
	}
	@Override
	public void saveAndUpdateRequestHeadline(String mid, String cmd, String url, String protocol, String creator) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple request.headlines %s where {'tuple.mid':'%s','tuple.creator':'%s'}",
				RequestHeadline.class.getName(), mid, creator);
		IQuery<RequestHeadline> q = test.createQuery(cjql);
		IDocument<RequestHeadline> doc = q.getSingleResult();
		if (doc == null) {
			// add
			RequestHeadline rnp = new RequestHeadline(mid, cmd, url, protocol, creator);
			test.saveDoc("request.headlines", new TupleDocument<>(rnp));
			return;
		}
		// update
		RequestHeadline newrnp = new RequestHeadline(mid, cmd, url, protocol, creator);
		test.updateDoc("request.headlines", doc.docid(), new TupleDocument<>(newrnp));

	}

	@Override
	public RequestHeadline getMyRequestHeadline(String mid, String creator) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple request.headlines %s where {'tuple.mid':'%s','tuple.creator':'%s'}",
				RequestHeadline.class.getName(), mid, creator);
		IQuery<RequestHeadline> q = test.createQuery(cjql);
		IDocument<RequestHeadline> doc = q.getSingleResult();
		if (doc == null)
			return null;
		return doc.tuple();
	}

	@Override
	public void saveAndUpdateRequestContentType(String mid, String type, String creator) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple request.content.types %s where {'tuple.mid':'%s','tuple.creator':'%s'}",
				RequestContentType.class.getName(), mid, creator);
		IQuery<RequestContentType> q = test.createQuery(cjql);
		IDocument<RequestContentType> doc = q.getSingleResult();
		if (doc == null) {
			// add
			RequestContentType rnp = new RequestContentType(mid, type, creator);
			test.saveDoc("request.content.types", new TupleDocument<>(rnp));
			return;
		}
		// update
		RequestContentType newrnp = new RequestContentType(mid, type, creator);
		test.updateDoc("request.content.types", doc.docid(), new TupleDocument<>(newrnp));

	}

	@Override
	public RequestContentType getMyRequestContentType(String mid, String creator) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple request.content.types %s where {'tuple.mid':'%s','tuple.creator':'%s'}",
				RequestContentType.class.getName(), mid, creator);
		IQuery<RequestContentType> q = test.createQuery(cjql);
		IDocument<RequestContentType> doc = q.getSingleResult();
		if (doc == null)
			return null;
		return doc.tuple();
	}

	@Override
	public void saveAndUpdateRequestContentAny(String mid, String content, String creator) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple request.content.anys %s where {'tuple.mid':'%s','tuple.creator':'%s'}",
				RequestContentAny.class.getName(), mid, creator);
		IQuery<RequestContentAny> q = test.createQuery(cjql);
		IDocument<RequestContentAny> doc = q.getSingleResult();
		if (doc == null) {
			// add
			RequestContentAny rnp = new RequestContentAny(mid, content, creator);
			test.saveDoc("request.content.anys", new TupleDocument<>(rnp));
			return;
		}
		// update
		RequestContentAny newrnp = new RequestContentAny(mid, content, creator);
		test.updateDoc("request.content.anys", doc.docid(), new TupleDocument<>(newrnp));
	}

	@Override
	public RequestContentAny getMyRequestContentAny(String mid, String creator) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple request.content.anys %s where {'tuple.mid':'%s','tuple.creator':'%s'}",
				RequestContentAny.class.getName(), mid, creator);
		IQuery<RequestContentAny> q = test.createQuery(cjql);
		IDocument<RequestContentAny> doc = q.getSingleResult();
		if (doc == null)
			return null;
		return doc.tuple();
	}

	@Override
	public String saveRequestContentXwww(String mid, String key, String value, String desc, String creator) {
		RequestContentXwww rnp = new RequestContentXwww(mid, null, key, value, desc, creator);
		return test.saveDoc("request.content.xwwws", new TupleDocument<>(rnp));
	}

	@Override
	public void updateRequestContentXwww(String mid, String id, String key, String value, String desc, String creator) {
		RequestContentXwww w = findRequestContentXwww(id);
		w.setCreator(creator);
		w.setDesc(desc);
		w.setId(null);
		w.setKey(key);
		w.setMid(mid);
		w.setValue(value);
		test.updateDoc("request.content.xwwws", id, new TupleDocument<>(w));
	}

	@Override
	public void deleteRequestContentXwww(String id) {
		test.deleteDoc("request.content.xwwws", id);
	}

	@Override
	public RequestContentXwww findRequestContentXwww(String id) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple request.content.xwwws %s where {'_id':ObjectId('%s')}",
				RequestContentXwww.class.getName(), id);
		IQuery<RequestContentXwww> q = test.createQuery(cjql);
		IDocument<RequestContentXwww> doc = q.getSingleResult();
		if (doc == null)
			return null;
		doc.tuple().setId(doc.docid());
		return doc.tuple();
	}

	@Override
	public List<RequestContentXwww> getMyRequestContentXwwws(String mid, String creator) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple request.content.xwwws %s where {'tuple.mid':'%s','tuple.creator':'%s'}",
				RequestContentXwww.class.getName(), mid, creator);
		IQuery<RequestContentXwww> q = test.createQuery(cjql);
		List<IDocument<RequestContentXwww>> docs = q.getResultList();
		List<RequestContentXwww> list = new ArrayList<>();
		for (IDocument<RequestContentXwww> doc : docs) {
			RequestContentXwww www = doc.tuple();
			www.setId(doc.docid());
			list.add(www);
		}
		return list;
	}

	@Override
	public String saveRequestContentFormData(String mid, String key,String value,String desc, String creator) {
		RequestContentFormData rnp = new RequestContentFormData(mid, null, key, value, desc, creator);
		return test.saveDoc("request.content.formdatas", new TupleDocument<>(rnp));
	}

	@Override
	public void updateRequestContentFormData(String mid, String id, String key,String value,String desc, String creator) {
		RequestContentFormData w = findRequestContentFormData(id);
		w.setCreator(creator);
		w.setDesc(desc);
		w.setId(null);
		w.setKey(key);
		w.setMid(mid);
		w.setValue(value);
		test.updateDoc("request.content.formdatas", id, new TupleDocument<>(w));
	}

	@Override
	public void deleteRequestContentFormData(String id) {
		test.deleteDoc("request.content.formdatas", id);
	}

	@Override
	public RequestContentFormData findRequestContentFormData(String id) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple request.content.formdatas %s where {'_id':ObjectId('%s')}",
				RequestContentFormData.class.getName(), id);
		IQuery<RequestContentFormData> q = test.createQuery(cjql);
		IDocument<RequestContentFormData> doc = q.getSingleResult();
		if (doc == null)
			return null;
		doc.tuple().setId(doc.docid());
		return doc.tuple();
	}

	@Override
	public List<RequestContentFormData> getMyRequestContentFormDatas(String mid, String creator) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple request.content.formdatas %s where {'tuple.mid':'%s','tuple.creator':'%s'}",
				RequestContentFormData.class.getName(), mid, creator);
		IQuery<RequestContentFormData> q = test.createQuery(cjql);
		List<IDocument<RequestContentFormData>> docs = q.getResultList();
		List<RequestContentFormData> list = new ArrayList<>();
		for (IDocument<RequestContentFormData> doc : docs) {
			RequestContentFormData www = doc.tuple();
			www.setId(doc.docid());
			list.add(www);
		}
		return list;
	}

	@Override
	public String saveRequestParameter(String mid, String key, String value,String desc, String creator) {
		RequestParameter rnp = new RequestParameter(mid, null, key, value, desc, creator);
		return test.saveDoc("request.parameters", new TupleDocument<>(rnp));
	}

	@Override
	public void updateRequestParameter(String mid, String id, String key, String value,String desc, String creator) {
		RequestParameter w = findRequestParameter(id);
		w.setCreator(creator);
		w.setDesc(desc);
		w.setId(null);
		w.setKey(key);
		w.setMid(mid);
		w.setValue(value);
		test.updateDoc("request.parameters", id, new TupleDocument<>(w));
	}

	@Override
	public void deleteRequestParameter(String id) {
		test.deleteDoc("request.parameters", id);
	}

	@Override
	public RequestParameter findRequestParameter(String id) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple request.parameters %s where {'_id':ObjectId('%s')}",
				RequestParameter.class.getName(), id);
		IQuery<RequestParameter> q = test.createQuery(cjql);
		IDocument<RequestParameter> doc = q.getSingleResult();
		if (doc == null)
			return null;
		doc.tuple().setId(doc.docid());
		return doc.tuple();
	}

	@Override
	public List<RequestParameter> getMyRequestParameters(String mid, String creator) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple request.parameters %s where {'tuple.mid':'%s','tuple.creator':'%s'}",
				RequestParameter.class.getName(), mid, creator);
		IQuery<RequestParameter> q = test.createQuery(cjql);
		List<IDocument<RequestParameter>> docs = q.getResultList();
		List<RequestParameter> list = new ArrayList<>();
		for (IDocument<RequestParameter> doc : docs) {
			RequestParameter www = doc.tuple();
			www.setId(doc.docid());
			list.add(www);
		}
		return list;
	}

	@Override
	public String saveRequestHeader(String mid, String key, String value,String desc, String creator) {
		RequestHeader rnp = new RequestHeader(mid, null, key, value, desc, creator);
		return test.saveDoc("request.headers", new TupleDocument<>(rnp));
	}

	@Override
	public void updateRequestHeader(String mid, String id, String key, String value,String desc, String creator) {
		RequestHeader w = findRequestHeader(id);
		w.setCreator(creator);
		w.setDesc(desc);
		w.setId(null);
		w.setKey(key);
		w.setMid(mid);
		w.setValue(value);
		test.updateDoc("request.headers", id, new TupleDocument<>(w));

	}

	@Override
	public void deleteRequestHeader(String id) {
		test.deleteDoc("request.headers", id);
	}

	@Override
	public RequestHeader findRequestHeader(String id) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple request.headers %s where {'_id':ObjectId('%s')}",
				RequestHeader.class.getName(), id);
		IQuery<RequestHeader> q = test.createQuery(cjql);
		IDocument<RequestHeader> doc = q.getSingleResult();
		if (doc == null)
			return null;
		doc.tuple().setId(doc.docid());
		return doc.tuple();
	}

	@Override
	public List<RequestHeader> getMyRequestHeaders(String mid, String creator) {
		String cjql = String.format(
				"select {'tuple':'*'} from tuple request.headers %s where {'tuple.mid':'%s','tuple.creator':'%s'}",
				RequestHeader.class.getName(), mid, creator);
		IQuery<RequestHeader> q = test.createQuery(cjql);
		List<IDocument<RequestHeader>> docs = q.getResultList();
		List<RequestHeader> list = new ArrayList<>();
		for (IDocument<RequestHeader> doc : docs) {
			RequestHeader www = doc.tuple();
			www.setId(doc.docid());
			list.add(www);
		}
		return list;
	}
}
