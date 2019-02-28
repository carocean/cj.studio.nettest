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
var IRequestConfigStub=Java.type('cj.studio.nettest.be.stub.IRequestConfigStub');
var Gson=Java.type('cj.ultimate.gson2.com.google.gson.Gson');
var TMethod=Java.type('cj.studio.nettest.be.args.TMethod');

exports.flow = function(f,c,ctx) {
	var ptStub=imports.head.services.rest.forRemote("rest://backend/nettest/").open(IRequestConfigStub.class);
	var netprotocol=f.parameter('netprotocol');
	var mid=f.parameter('mid');
	var creator=f.session().attribute('uc.principals');
	ptStub.saveAndUpdateRequestNetprotocol(mid,netprotocol,creator);
	c.content().writeBytes("{status:success}".getBytes());
}

