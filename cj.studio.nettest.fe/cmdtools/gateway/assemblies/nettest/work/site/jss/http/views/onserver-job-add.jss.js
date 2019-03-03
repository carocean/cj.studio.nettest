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
var IJobServerStub=Java.type('cj.studio.nettest.jobserver.stub.IJobServerStub');
var JobSender=Java.type('cj.studio.nettest.jobserver.args.JobSender');
var Gson=Java.type('cj.ultimate.gson2.com.google.gson.Gson');

exports.flow = function(f,c,ctx) {
	var mid=f.parameter('mid');
	var jsStub=imports.head.services.rest.forRemote("rest://jobserver/nettest/").open(IJobServerStub.class,true);
	var creator=f.session().attribute('uc.principals');
	var sender=new JobSender(creator,'');
	jsStub.addJob(mid,sender);
}

