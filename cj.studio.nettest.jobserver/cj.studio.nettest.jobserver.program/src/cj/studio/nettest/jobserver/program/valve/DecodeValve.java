package cj.studio.nettest.jobserver.program.valve;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import cj.studio.ecm.Scope;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.net.CircuitException;
import cj.studio.ecm.net.Frame;
import cj.studio.gateway.socket.pipeline.IAnnotationInputValve;
import cj.studio.gateway.socket.pipeline.IIPipeline;

@CjService(name = "decodeValve", scope = Scope.multiton)
public class DecodeValve implements IAnnotationInputValve {

	@Override
	public void onActive(String inputName, IIPipeline pipeline) throws CircuitException {
		pipeline.nextOnActive(inputName, this);
	}

	@Override
	public void flow(Object request, Object response, IIPipeline pipeline) throws CircuitException {
		Frame f=(Frame)request;
		String url=f.url();
		try {
			String deurl=URLDecoder.decode(url, "utf-8");
			f.url(deurl);
		} catch (UnsupportedEncodingException e) {
		}
		pipeline.nextFlow(request, response, this);
	}

	@Override
	public void onInactive(String inputName, IIPipeline pipeline) throws CircuitException {
		pipeline.nextOnInactive(inputName, this);
	}

	@Override
	public int getSort() {
		return 1;
	}

}
