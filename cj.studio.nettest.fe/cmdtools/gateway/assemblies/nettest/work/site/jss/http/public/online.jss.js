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
 		online:'online',
 		selector:'$.output.selector',
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
var StringBuffer = Java.type('java.lang.StringBuffer');
var CircuitException=Java.type('cj.studio.ecm.net.CircuitException');
var SocketContants=Java.type('cj.studio.gateway.socket.util.SocketContants');
var ITokenStub=Java.type('cj.studio.backend.uc.stub.ITokenStub');

exports.flow = function(f,c,ctx) {
	var token=f.parameter('token');
	var selector=imports.head.services.selector;
	var pipeline_name=f.head(SocketContants.__frame_fromPipelineName);
	var out=selector.select(pipeline_name);
	if(token==null){//认证不通过，则关闭客端的ws通道，关闭方法是使用ioutputselector.selector(pipeline_name).closePipeline();
		out.closePipeline();
	}
	var tsStub=imports.head.services.rest.forRemote("rest://backend/uc/").open(ITokenStub.class);
	var map={};
	try{
		map=tsStub.parse(token);
	}catch(e){
		out.closePipeline();
		print(e);
		return;
	}
	var user=map.user;
	var online=imports.head.services.online;
	online.on(user,pipeline_name);
}
