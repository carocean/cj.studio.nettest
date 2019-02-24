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
var Boolean = Java.type('java.lang.Boolean');
var Integer = Java.type('java.lang.Integer');
var Long = Java.type('java.lang.Long');
var HashMap = Java.type('java.util.HashMap');
var StringUtil = Java.type('cj.ultimate.util.StringUtil');
var StringBuffer = Java.type('java.lang.StringBuffer');
var CircuitException=Java.type('cj.studio.ecm.net.CircuitException');
var IProjectTreeStub=Java.type('cj.studio.nettest.be.stub.IProjectTreeStub');
var Gson=Java.type('cj.ultimate.gson2.com.google.gson.Gson');
var RunnerStrategy=Java.type('cj.studio.nettest.be.args.RunnerStrategy');
var XwwwFormUrlencodedContentReciever=Java.type('cj.studio.gateway.socket.io.XwwwFormUrlencodedContentReciever');

exports.flow = function(f,c,ctx) {
	var ptStub=imports.head.services.rest.forRemote("rest://backend/nettest/").open(IProjectTreeStub.class);
	f.content().accept(new XwwwFormUrlencodedContentReciever(){
		done:function(f){
			var threadcount=f.parameter('threadcount');
			var intervals=f.parameter('intervals');
			var alwaysloop=f.parameter('alwaysloop');
			var loopcount=f.parameter('loopcount');
			var mid=f.parameter('mid');
			var id=f.parameter('id');
			var strategy=new RunnerStrategy();
			strategy.id=id;//runnerId
			strategy.mid=mid;//methodId
			strategy.loopcount=Integer.valueOf(loopcount==null?'0':loopcount);
			strategy.alwaysloop=Boolean.valueOf(alwaysloop==null?'false':alwaysloop);
			strategy.intervals=Long.valueOf(intervals==null?'0':intervals);
			strategy.threadcount=Integer.valueOf(threadcount==null?'0':threadcount);
			strategy.creator=f.session().attribute('uc.principals');
			var sid=ptStub.saveRunnerStrategy(strategy);
			var map=new HashMap();
			if(sid!=null){
				map.put('id',sid);
				map.put('isNew',true);
			}else{
				map.put('id',id);
				map.put('isNew',false);
			}
			c.content().writeBytes(new Gson().toJson(map).getBytes());
		}
	});
}

