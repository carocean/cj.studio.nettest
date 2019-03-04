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
var XwwwFormUrlencodedContentReciever=Java.type('cj.studio.gateway.socket.io.XwwwFormUrlencodedContentReciever');

exports.flow = function(f,c,ctx) {
	var ptStub=imports.head.services.rest.forRemote("rest://backend/nettest/").open(IRequestConfigStub.class);
	f.content().accept(new XwwwFormUrlencodedContentReciever(){
		done:function(f){
			var mid=f.parameter('mid');
			var host=f.parameter('host');
			var dest=f.parameter('dest');
			var creator=f.session().attribute('uc.principals');
			ptStub.saveAndUpdateRequestHost(mid,host,dest,creator);
			c.content().writeBytes("{status:success}".getBytes());
		}
	});
	
}

