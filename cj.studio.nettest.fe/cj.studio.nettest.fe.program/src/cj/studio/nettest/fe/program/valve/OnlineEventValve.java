package cj.studio.nettest.fe.program.valve;

import cj.studio.ecm.Scope;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.gateway.socket.pipeline.IAnnotationInputValve;
import cj.studio.gateway.socket.pipeline.IIPipeline;
import cj.studio.nettest.fe.program.IOnlineTable;

@CjService(name = "OnlineEventValve", scope = Scope.multiton)
public class OnlineEventValve implements IAnnotationInputValve {
	@CjServiceRef(refByName="online")
	IOnlineTable table;
	@Override
	public void onActive(String inputName, IIPipeline pipeline) throws CircuitException {
		pipeline.nextOnActive(inputName, this);
	}

	@Override
	public void flow(Object request, Object response, IIPipeline pipeline) throws CircuitException {
		pipeline.nextFlow(request, response, this);
	}

	@Override
	public void onInactive(String inputName, IIPipeline pipeline) throws CircuitException {
		table.offPipeline(inputName);
		pipeline.nextOnInactive(inputName, this);
	}
	@Override
	public int getSort() {
		return 3;
	}

}
