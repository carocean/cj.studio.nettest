package cj.studio.nettest.be.stub;

import java.util.List;

import cj.studio.gateway.stub.annotation.CjStubInContentKey;
import cj.studio.gateway.stub.annotation.CjStubInParameter;
import cj.studio.gateway.stub.annotation.CjStubMethod;
import cj.studio.gateway.stub.annotation.CjStubReturn;
import cj.studio.gateway.stub.annotation.CjStubService;
import cj.studio.nettest.be.args.RequestContentAny;
import cj.studio.nettest.be.args.RequestContentFormData;
import cj.studio.nettest.be.args.RequestContentType;
import cj.studio.nettest.be.args.RequestContentXwww;
import cj.studio.nettest.be.args.RequestHeader;
import cj.studio.nettest.be.args.RequestHeadline;
import cj.studio.nettest.be.args.RequestHost;
import cj.studio.nettest.be.args.RequestNetprotocol;
import cj.studio.nettest.be.args.RequestParameter;

@CjStubService(bindService = "/request-config.service", usage = "请求配置服务")
public interface IRequestConfigStub {
	@CjStubMethod(command = "get", usage = "更新请求的协议")
	void saveAndUpdateRequestNetprotocol(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "netprotocol", usage = "协议，如：httpOnBrower|wsOnBrowser|tcpOnServer|udtOnServer") String netprotocol,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(command = "get", usage = "获取请求的协议")
	@CjStubReturn(type = RequestNetprotocol.class, usage = "请求协议")
	RequestNetprotocol getMyRequestNetprotocol(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(command = "get", usage = "更新请求的主机地址")
	void saveAndUpdateRequestHost(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "host", usage = "主机地址") String host,
			@CjStubInParameter(key = "dest", usage = "目标名") String dest,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(command = "get", usage = "获取请求的主机地址")
	@CjStubReturn(type = RequestHost.class, usage = "请求主机")
	RequestHost getMyRequestHost(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);
	
	@CjStubMethod(command = "post", usage = "更新请求头")
	void saveAndUpdateRequestHeadline(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "cmd", usage = "命令") String cmd,
			@CjStubInParameter(key = "url", usage = "请求地址") String url,
			@CjStubInParameter(key = "protocol", usage = "协议，支持自定义协议") String protocol,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(command = "get", usage = "获取请求头")
	@CjStubReturn(type = RequestHeadline.class, usage = "请求头")
	RequestHeadline getMyRequestHeadline(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(command = "get", usage = "内容类型")
	void saveAndUpdateRequestContentType(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "type", usage = "内容类型") String type,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(command = "get", usage = "获取内容类型")
	@CjStubReturn(type = RequestContentType.class, usage = "内容类型")
	RequestContentType getMyRequestContentType(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(command = "post", usage = "获取内容类型")
	void saveAndUpdateRequestContentAny(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInContentKey(key = "content", usage = "内容") String content,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(command = "get", usage = "获取任意格式内容")
	RequestContentAny getMyRequestContentAny(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(command = "post", usage = "保存内容键值")
	String saveRequestContentXwww(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "key", usage = "键") String key,
			@CjStubInContentKey(key = "value", usage = "值") String value,
			@CjStubInContentKey(key = "desc", usage = "说明") String desc,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(command = "post", usage = "更新内容键值")
	void updateRequestContentXwww(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "id", usage = "标识") String id,
			@CjStubInParameter(key = "key", usage = "键") String key,
			@CjStubInContentKey(key = "value", usage = "值") String value,
			@CjStubInContentKey(key = "desc", usage = "说明") String desc,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(usage = "删除内容键值")
	void deleteRequestContentXwww(@CjStubInParameter(key = "id", usage = "标识") String id);

	@CjStubMethod(usage = "查找内容键值")
	RequestContentXwww findRequestContentXwww(@CjStubInParameter(key = "id", usage = "标识") String id);

	@CjStubMethod(usage = "获取我的内容键值")
	@CjStubReturn(elementType = RequestContentXwww.class, usage = "内容键")
	List<RequestContentXwww> getMyRequestContentXwwws(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(command = "post", usage = "保存内容键值")
	String saveRequestContentFormData(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "key", usage = "键") String key,
			@CjStubInContentKey(key = "value", usage = "值") String value,
			@CjStubInContentKey(key = "desc", usage = "说明") String desc,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(command = "post", usage = "更新内容键值")
	void updateRequestContentFormData(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "id", usage = "标识") String id,
			@CjStubInParameter(key = "key", usage = "键") String key,
			@CjStubInContentKey(key = "value", usage = "值") String value,
			@CjStubInContentKey(key = "desc", usage = "说明") String desc,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(usage = "删除内容键值")
	void deleteRequestContentFormData(@CjStubInParameter(key = "id", usage = "标识") String id);

	@CjStubMethod(usage = "查找内容键值")
	RequestContentFormData findRequestContentFormData(@CjStubInParameter(key = "id", usage = "标识") String id);

	@CjStubMethod(usage = "获取我的内容键值")
	@CjStubReturn(elementType = RequestContentFormData.class, usage = "内容键")
	List<RequestContentFormData> getMyRequestContentFormDatas(
			@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(command = "post", usage = "保存请求参数")
	String saveRequestParameter(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "key", usage = "键") String key,
			@CjStubInContentKey(key = "value", usage = "值") String value,
			@CjStubInContentKey(key = "desc", usage = "说明") String desc,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(command = "post", usage = "更新请求参数")
	void updateRequestParameter(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "id", usage = "标识") String id,
			@CjStubInParameter(key = "key", usage = "键") String key,
			@CjStubInContentKey(key = "value", usage = "值") String value,
			@CjStubInContentKey(key = "desc", usage = "说明") String desc,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(usage = "删除请求参数")
	void deleteRequestParameter(@CjStubInParameter(key = "id", usage = "标识") String id);

	@CjStubMethod(usage = "查找请求参数")
	RequestParameter findRequestParameter(@CjStubInParameter(key = "id", usage = "标识") String id);

	@CjStubMethod(usage = "获取我的请求参数")
	@CjStubReturn(elementType = RequestParameter.class, usage = "请求参数")
	List<RequestParameter> getMyRequestParameters(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(command = "post", usage = "保存请求头")
	String saveRequestHeader(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "key", usage = "键") String key,
			@CjStubInContentKey(key = "value", usage = "值") String value,
			@CjStubInContentKey(key = "desc", usage = "说明") String desc,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(command = "post", usage = "更新请求头")
	void updateRequestHeader(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "id", usage = "标识") String id,
			@CjStubInParameter(key = "key", usage = "键") String key,
			@CjStubInContentKey(key = "value", usage = "值") String value,
			@CjStubInContentKey(key = "desc", usage = "说明") String desc,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);

	@CjStubMethod(usage = "删除请求头")
	void deleteRequestHeader(@CjStubInParameter(key = "id", usage = "标识") String id);

	@CjStubMethod(usage = "查找请求头")
	RequestHeader findRequestHeader(@CjStubInParameter(key = "id", usage = "标识") String id);

	@CjStubMethod(usage = "获取我的请求头")
	@CjStubReturn(elementType = RequestHeader.class, usage = "请求头")
	List<RequestHeader> getMyRequestHeaders(@CjStubInParameter(key = "mid", usage = "方法标识") String mid,
			@CjStubInParameter(key = "creator", usage = "创建者") String creator);
}
