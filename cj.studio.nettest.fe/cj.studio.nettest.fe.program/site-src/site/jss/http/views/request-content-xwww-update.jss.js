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
	var rcStub=imports.head.services.rest.forRemote("rest://backend/nettest/").open(IRequestConfigStub.class);
	f.content().accept(new XwwwFormUrlencodedContentReciever(){
		done:function(f){
			var mid=f.parameter('mid');
			var id=f.parameter('id');
			var key=f.parameter('key');
			var value=f.parameter('value');
			var desc=f.parameter('desc');
			var creator=f.session().attribute('uc.principals');
			rcStub.updateRequestContentXwww(mid,id,key,value,desc,creator);
			c.content().writeBytes(String.format('{"status":"success","id":"%s"}',id).getBytes());
		}
	});
	
}

