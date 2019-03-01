package cj.studio.nettest.be.plugin.service;

import java.util.List;

import cj.studio.ecm.EcmException;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.ecm.net.Frame;
import cj.studio.ecm.net.IInputChannel;
import cj.studio.ecm.net.io.MemoryContentReciever;
import cj.studio.ecm.net.io.MemoryInputChannel;
import cj.studio.nettest.be.args.RequestContentAny;
import cj.studio.nettest.be.args.RequestContentFormData;
import cj.studio.nettest.be.args.RequestContentType;
import cj.studio.nettest.be.args.RequestContentXwww;
import cj.studio.nettest.be.args.RequestFrame;
import cj.studio.nettest.be.args.RequestHeader;
import cj.studio.nettest.be.args.RequestHeadline;
import cj.studio.nettest.be.args.RequestNetprotocol;
import cj.studio.nettest.be.args.RequestParameter;
import cj.studio.nettest.be.service.IRequestConfigService;
import cj.studio.nettest.be.service.IRequestFrameService;
import cj.ultimate.gson2.com.google.gson.Gson;
import cj.ultimate.util.StringUtil;

@CjService(name = "rfService")
public class RequestFrameService implements IRequestFrameService {
	@CjServiceRef(refByName = "rcService")
	IRequestConfigService config;

	@Override
	public RequestFrame getMyRequestFrame(String mid, String creator) {
		RequestHeadline line = config.getMyRequestHeadline(mid, creator);
		if (line == null) {
			throw new EcmException("没有请求头");
		}
		IInputChannel in = new MemoryInputChannel();
		Frame f = new Frame(in, String.format("%s %s %s", line.getCmd(), line.getUrl(), line.getProtocol()));
		MemoryContentReciever reciever=new MemoryContentReciever();
		try {
			f.content().accept(reciever);
		} catch (CircuitException e1) {
			e1.printStackTrace();
		}
		List<RequestHeader> headers = config.getMyRequestHeaders(mid, creator);
		for (RequestHeader header : headers) {
			f.head(header.getKey(), header.getValue());
		}
		List<RequestParameter> params = config.getMyRequestParameters(mid, creator);
		for (RequestParameter param : params) {
			f.parameter(param.getKey(), param.getValue());
		}
		RequestContentType type = config.getMyRequestContentType(mid, creator);
		if (type != null) {
			switch (type.getType()) {
			case "none":
				break;
			case "any":
				RequestContentAny any = config.getMyRequestContentAny(mid, creator);
				String cnt = any.getContent();
				if (StringUtil.isEmpty(cnt)) {
					byte[] b = cnt.getBytes();
					try {
						in.done(b, 0, b.length);
					} catch (CircuitException e) {
						e.printStackTrace();
					}
				}
				break;
			case "xwfu":
				List<RequestContentXwww> xwwws = config.getMyRequestContentXwwws(mid, creator);
				byte[] b = new Gson().toJson(xwwws).getBytes();
				try {
					in.done(b, 0, b.length);
				} catch (CircuitException e) {
					e.printStackTrace();
				}
				break;
			case "formdata":
				List<RequestContentFormData> formdatas = config.getMyRequestContentFormDatas(mid, creator);
				b = new Gson().toJson(formdatas).getBytes();
				try {
					in.done(b, 0, b.length);
				} catch (CircuitException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		RequestNetprotocol rnpt = config.getMyRequestNetprotocol(mid, creator);
		String socketType = rnpt.getProtocol();
		if (StringUtil.isEmpty(socketType)) {
			throw new EcmException("没明确指明网络协议");
		}
		RequestFrame rf = new RequestFrame(f, socketType, type.getType(), creator);
		return rf;
	}

}
