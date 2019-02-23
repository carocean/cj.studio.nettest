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

exports.flow = function(f,c,ctx) {
	var session=f.session();
	session.removeAttribute('uc.token');
	session.removeAttribute('uc.principals');
	session.removeAttribute('uc.roles');
}
