/*
 * 2016.0829
 * 作者：赵向彬
 * 说明：services是声明本jss要引用的服务
 * <![jss:{
		scope:'runtime',
		extends:'cj.studio.gateway.socket.app.IGatewayAppSiteWayWebView',
		isStronglyJss:true,
		filter:{
	 	} 	
	},
	object:{
	 		name:"test..."
	},
 	services:{
 		rest:'$.rest'
 	}
 ]>
 <![desc:{
	ttt:'2323',
	obj:{
		name:'09skdkdk'
		}
* }]>
 */

var String = Java.type('java.lang.String');
var StringUtil = Java.type('cj.ultimate.util.StringUtil');
var StringBuffer = Java.type('java.lang.StringBuffer');
var CircuitException=Java.type('cj.studio.ecm.net.CircuitException');
var IRequestFrameStub=Java.type('cj.studio.nettest.be.stub.IRequestFrameStub');
var Gson=Java.type('cj.ultimate.gson2.com.google.gson.Gson');

exports.flow = function(f,c,ctx) {
	var mid=f.parameter('mid');
	var rfStub=imports.head.services.rest.forRemote("rest://backend/nettest/").open(IRequestFrameStub.class);
	var creator=f.session().attribute('uc.principals');
	var req=rfStub.getMyRequestFrame(mid,creator);
	var json=new Gson().toJson(req);
	c.content().writeBytes(json.getBytes());
}

