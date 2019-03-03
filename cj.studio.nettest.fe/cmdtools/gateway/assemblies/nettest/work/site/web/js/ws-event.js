$(document).ready(function(){
	var connLabel=$('.container > .workbench > .header > .topbar > .items>li[conn]');
	
	function onmessage(frame){
		if(frame.isFrame){
			console.log('...onmessage--是否侦:'+frame.isFrame+'; '+frame.heads.command+' '+frame.heads.url+' '+frame.heads.protocol);
			return;
		}
		console.log('...onmessage--是否侦:'+frame.isFrame+' 内容：'+frame.content);
	}
	function onopen(e){
		connLabel.html('已连接');
		connLabel.attr('state','isopen');
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