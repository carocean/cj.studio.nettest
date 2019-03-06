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
var HashMap = Java.type('java.util.HashMap');
var String = Java.type('java.lang.String');
var StringUtil = Java.type('cj.ultimate.util.StringUtil');
var StringBuffer = Java.type('java.lang.StringBuffer');
var CircuitException=Java.type('cj.studio.ecm.net.CircuitException');
var IRequestConfigStub=Java.type('cj.studio.nettest.be.stub.IRequestConfigStub');
var Gson=Java.type('cj.ultimate.gson2.com.google.gson.Gson');
var IContentReciever=Java.type('cj.studio.ecm.net.IContentReciever');
var ByteBuf=Java.type('io.netty.buffer.ByteBuf');
var Unpooled=Java.type('io.netty.buffer.Unpooled');
var JavaUtil=Java.type('cj.ultimate.util.JavaUtil');

exports.flow = function(f,c,ctx) {
	var rcStub=imports.head.services.rest.forRemote("rest://backend/nettest/").open(IRequestConfigStub.class);
	f.content().accept(new IContentReciever(){
		begin:function(frame){
			this.frame=frame;
			this.buf=Unpooled.buffer(8192);
		},
		recieve:function(b,pos,length){
			this.buf.writeBytes(b, pos, length);
		},
		done:function(b,pos,length){
			var buf=this.buf;
			buf.writeBytes(b, pos, length);
			if(buf.refCnt()<1)return new Array();
			var all = JavaUtil.createByteArray(buf.readableBytes());
			buf.readBytes(all, 0, all.length);
			if(buf.refCnt()>0) {
				buf.release();
			}
			var json=new String(all);
			var map=new Gson().fromJson(json,HashMap.class);
			var mid=map.get('mid');
			var content=map.get('content');
			var creator=f.session().attribute('uc.principals');
			rcStub.saveAndUpdateRequestContentAny(mid,content,creator);
		}
	});
}

