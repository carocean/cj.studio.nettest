package cj.studio.nettest.be.args;

import cj.studio.ecm.net.CircuitException;
import cj.studio.ecm.net.Frame;

public class RequestFrame {
	String frameText;
	String socketType;// 套节字类型，在界面上选择的
	String requestor;// 请求者
	String contentType;
	String host;
	public RequestFrame() {
		// TODO Auto-generated constructor stub
	}

	public RequestFrame(Frame frame,String host, String socketType, String contentType, String requestor) {
		super();
		try {
			this.frameText = frame.toJson();
		} catch (CircuitException e) {
			e.printStackTrace();
		}
		this.socketType = socketType;
		this.requestor = requestor;
		this.contentType = contentType;
		this.host=host;
	}
public String getHost() {
	return host;
}
public void setHost(String host) {
	this.host = host;
}
	public String getFrameText() {
		return frameText;
	}

	public void setFrameText(String frameText) {
		this.frameText = frameText;
	}

	public String getSocketType() {
		return socketType;
	}

	public void setSocketType(String socketType) {
		this.socketType = socketType;
	}

	public String getRequestor() {
		return requestor;
	}

	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
