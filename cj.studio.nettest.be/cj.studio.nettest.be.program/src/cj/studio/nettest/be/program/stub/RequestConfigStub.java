package cj.studio.nettest.be.program.stub;

import java.util.List;

import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.gateway.stub.GatewayAppSiteRestStub;
import cj.studio.nettest.be.args.RequestContentAny;
import cj.studio.nettest.be.args.RequestContentFormData;
import cj.studio.nettest.be.args.RequestContentType;
import cj.studio.nettest.be.args.RequestContentXwww;
import cj.studio.nettest.be.args.RequestHeader;
import cj.studio.nettest.be.args.RequestHeadline;
import cj.studio.nettest.be.args.RequestHost;
import cj.studio.nettest.be.args.RequestNetprotocol;
import cj.studio.nettest.be.args.RequestParameter;
import cj.studio.nettest.be.args.SimpleReport;
import cj.studio.nettest.be.service.IRequestConfigService;
import cj.studio.nettest.be.stub.IRequestConfigStub;

@CjService(name = "/request-config.service")
public class RequestConfigStub extends GatewayAppSiteRestStub implements IRequestConfigStub {
	@CjServiceRef(refByName = "nettestplugin.rcService")
	IRequestConfigService rcService;

	@Override
	public void saveAndUpdateRequestResponse(SimpleReport report, String creator) {
		rcService.saveAndUpdateRequestResponse(report, creator);
	}
	@Override
	public SimpleReport getMyRequestResponse(String mid, String creator) {
		return rcService.getMyRequestResponse(mid, creator);
	}
	@Override
	public void saveAndUpdateRequestNetprotocol(String mid, String netprotocol, String creator) {
		rcService.saveAndUpdateRequestNetprotocol(mid, netprotocol, creator);
	}

	@Override
	public RequestNetprotocol getMyRequestNetprotocol(String mid, String creator) {
		return rcService.getMyRequestNetprotocol(mid, creator);
	}
	@Override
	public void saveAndUpdateRequestHost(String mid, String host,String dest, String creator) {
		rcService.saveAndUpdateRequestHost(mid, host,dest, creator);
	}
	@Override
	public RequestHost getMyRequestHost(String mid, String creator) {
		return rcService.getMyRequestHost(mid, creator);
	}
	@Override
	public RequestHeadline getMyRequestHeadline(String mid, String creator) {
		return rcService.getMyRequestHeadline(mid, creator);
	}

	@Override
	public void saveAndUpdateRequestHeadline(String mid, String cmd, String url, String protocol, String creator) {
		rcService.saveAndUpdateRequestHeadline(mid, cmd, url, protocol, creator);
	}

	@Override
	public void saveAndUpdateRequestContentType(String mid, String type, String creator) {
		rcService.saveAndUpdateRequestContentType(mid, type, creator);
	}

	@Override
	public RequestContentType getMyRequestContentType(String mid, String creator) {
		return rcService.getMyRequestContentType(mid, creator);
	}

	@Override
	public void saveAndUpdateRequestContentAny(String mid, String content, String creator) {
		rcService.saveAndUpdateRequestContentAny(mid, content, creator);
	}

	@Override
	public RequestContentAny getMyRequestContentAny(String mid, String creator) {
		return rcService.getMyRequestContentAny(mid, creator);
	}

	@Override
	public String saveRequestContentXwww(String mid, String key, String value, String desc, String creator) {
		return rcService.saveRequestContentXwww(mid, key, value, desc, creator);
	}

	@Override
	public void updateRequestContentXwww(String mid, String id, String key, String value, String desc, String creator) {
		rcService.updateRequestContentXwww(mid, id, key, value, desc, creator);
	}

	@Override
	public void deleteRequestContentXwww(String id) {
		rcService.deleteRequestContentXwww(id);
	}

	@Override
	public RequestContentXwww findRequestContentXwww(String id) {
		return rcService.findRequestContentXwww(id);
	}

	@Override
	public List<RequestContentXwww> getMyRequestContentXwwws(String mid, String creator) {
		return rcService.getMyRequestContentXwwws(mid, creator);
	}

	@Override
	public String saveRequestContentFormData(String mid, String key, String value, String desc, String creator) {
		return rcService.saveRequestContentFormData(mid, key, value, desc, creator);
	}

	@Override
	public void updateRequestContentFormData(String mid, String id, String key, String value, String desc,
			String creator) {
		rcService.updateRequestContentFormData(mid, id, key, value, desc, creator);
	}

	@Override
	public void deleteRequestContentFormData(String id) {
		rcService.deleteRequestContentFormData(id);
	}

	@Override
	public RequestContentFormData findRequestContentFormData(String id) {
		return rcService.findRequestContentFormData(id);
	}

	@Override
	public List<RequestContentFormData> getMyRequestContentFormDatas(String mid, String creator) {
		return rcService.getMyRequestContentFormDatas(mid, creator);
	}

	@Override
	public String saveRequestParameter(String mid, String key, String value, String desc, String creator) {
		return rcService.saveRequestParameter(mid, key, value, desc, creator);
	}

	@Override
	public void updateRequestParameter(String mid, String id, String key, String value, String desc, String creator) {
		rcService.updateRequestParameter(mid, id, key, value, desc, creator);
	}

	@Override
	public void deleteRequestParameter(String id) {
		rcService.deleteRequestParameter(id);
	}

	@Override
	public RequestParameter findRequestParameter(String id) {
		return rcService.findRequestParameter(id);
	}

	@Override
	public List<RequestParameter> getMyRequestParameters(String mid, String creator) {
		return rcService.getMyRequestParameters(mid, creator);
	}

	@Override
	public String saveRequestHeader(String mid, String key, String value, String desc, String creator) {
		return rcService.saveRequestHeader(mid, key, value, desc, creator);
	}

	@Override
	public void updateRequestHeader(String mid, String id, String key, String value, String desc, String creator) {
		rcService.updateRequestHeader(mid, id, key, value, desc, creator);
	}

	@Override
	public void deleteRequestHeader(String id) {
		rcService.deleteRequestHeader(id);
	}

	@Override
	public RequestHeader findRequestHeader(String id) {
		return rcService.findRequestHeader(id);
	}

	@Override
	public List<RequestHeader> getMyRequestHeaders(String mid, String creator) {
		return rcService.getMyRequestHeaders(mid, creator);
	}

}
