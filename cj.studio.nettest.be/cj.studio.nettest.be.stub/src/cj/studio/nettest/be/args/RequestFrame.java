package cj.studio.nettest.be.args;

import cj.studio.ecm.net.CircuitException;
import cj.studio.ecm.net.Frame;

public class RequestFrame {
	String frameRaw;
	String socketType;// 套节字类型，在界面上选择的
	String requestor;// 请求者
	String contentType;
	String host;
	String dest;
	String mid;
	public RequestFrame() {
		// TODO Auto-generated constructor stub
	}

	public RequestFrame(Frame frame,String mid, String host, String dest, String socketType, String contentType,
			String requestor) {
		super();
		try {
			this.frameRaw = new String(frame.toBytes());
		} catch (CircuitException e) {
			e.printStackTrace();
		}
		this.socketType = socketType;
		this.requestor = requestor;
		this.contentType = contentType;
		this.host = host;
		this.dest = dest;
		this.mid=mid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getFrameRaw() {
		return frameRaw;
	}
	public void setFrameRaw(String frameRaw) {
		this.frameRaw = frameRaw;
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
