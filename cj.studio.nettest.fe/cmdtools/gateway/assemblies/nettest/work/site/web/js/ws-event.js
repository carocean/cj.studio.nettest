$(document).ready(function(){
	var connLabel=$('.container > .workbench > .header > .topbar > .items>li[conn]');
	
	function getCookie(sName)
	{
		var aCookie = document.cookie.split("; ");
		for (var i=0; i < aCookie.length; i++)
		{
		var aCrumb = aCookie[i].split("=");
		if (sName == aCrumb[0])
		return unescape(aCrumb[1]);
		}
		return null;
	}
	
	function onmessage(frame){
		var content='';
		var responsePanel=$('.portlet > .response > .content');
		if(frame.isFrame){
//			console.log('...onmessage--是否侦:'+frame.isFrame+'; '+frame.heads.command+' '+frame.heads.url+' '+frame.heads.protocol);
			content=frame.content.replace(/\\\"/g,'"');
			responsePanel.html(content);
			return;
		}
		content=frame.content;
		responsePanel.html(content);
//		console.log('...onmessage--是否侦:'+frame.isFrame+' 内容：'+frame.content);
	}
	function onopen(e){
		connLabel.html('已连接');
		connLabel.attr('state','isopen');
		var cjtoken=getCookie('cjtoken');
		var frame={
				heads:{
					url:'/nettest/public/online.service',
					command:'get',
					protocol:'ws/1.0'
				},
				params:{
					token:cjtoken
				}
			};
		connLabel.ws.sendFrame(frame);//发送验证
	}
	function onclose(e){
		connLabel.html('未连接');
		connLabel.attr('state','isclose');
	}
	function onerror(e){
		alert('ws error:'+e);
	}
	var wsServiceuri=connLabel.attr('wsurl');
	var ws=$.ws.open(wsServiceuri,onmessage, onopen, onclose,onerror);
	connLabel.ws=ws;
	
});