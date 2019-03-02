package cj.studio.nettest.be.service;

import java.util.List;

import cj.studio.nettest.be.args.RequestContentAny;
import cj.studio.nettest.be.args.RequestContentFormData;
import cj.studio.nettest.be.args.RequestContentType;
import cj.studio.nettest.be.args.RequestContentXwww;
import cj.studio.nettest.be.args.RequestHeader;
import cj.studio.nettest.be.args.RequestHeadline;
import cj.studio.nettest.be.args.RequestHost;
import cj.studio.nettest.be.args.RequestNetprotocol;
import cj.studio.nettest.be.args.RequestParameter;

public interface IRequestConfigService {
	void saveAndUpdateRequestNetprotocol(String mid, String netprotocol, String creator);

	RequestNetprotocol getMyRequestNetprotocol(String mid, String creator);

	void saveAndUpdateRequestHost(String mid, String host, String creator);

	RequestHost getMyRequestHost(String mid, String creator);

	void saveAndUpdateRequestHeadline(String mid, String cmd, String url, String protocol, String creator);

	RequestHeadline getMyRequestHeadline(String mid, String creator);

	void saveAndUpdateRequestContentType(String mid, String type, String creator);

	RequestContentType getMyRequestContentType(String mid, String creator);

	void saveAndUpdateRequestContentAny(String mid, String content, String creator);

	RequestContentAny getMyRequestContentAny(String mid, String creator);

	String saveRequestContentXwww(String mid, String key, String value, String desc, String creator);

	void updateRequestContentXwww(String mid, String id, String key, String value, String desc, String creator);

	void deleteRequestContentXwww(String id);

	RequestContentXwww findRequestContentXwww(String id);

	List<RequestContentXwww> getMyRequestContentXwwws(String mid, String creator);

	String saveRequestContentFormData(String mid, String key, String value, String desc, String creator);

	void updateRequestContentFormData(String mid, String id, String key, String value, String desc, String creator);

	void deleteRequestContentFormData(String id);

	RequestContentFormData findRequestContentFormData(String id);

	List<RequestContentFormData> getMyRequestContentFormDatas(String mid, String creator);

	String saveRequestParameter(String mid, String key, String value, String desc, String creator);

	void updateRequestParameter(String mid, String id, String key, String value, String desc, String creator);

	void deleteRequestParameter(String id);

	RequestParameter findRequestParameter(String id);

	List<RequestParameter> getMyRequestParameters(String mid, String creator);

	String saveRequestHeader(String mid, String key, String value, String desc, String creator);

	void updateRequestHeader(String mid, String id, String key, String value, String desc, String creator);

	void deleteRequestHeader(String id);

	RequestHeader findRequestHeader(String id);

	List<RequestHeader> getMyRequestHeaders(String mid, String creator);
}
