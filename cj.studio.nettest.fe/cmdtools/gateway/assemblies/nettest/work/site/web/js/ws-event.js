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
		var resul=$('.portlet > .res-bar ul');
		if(frame.isFrame){
			//console.log('...onmessage--是否侦:'+frame.isFrame+'; '+frame.heads.command+' '+frame.heads.url+' '+frame.heads.protocol);
			content=frame.content;//.replace(/\"/g,'"');
			var report=$.parseJSON(content);
			if(frame.heads.url.indexOf('/nettest/test-report.service')>-1){
				resul.find('li[state] span').html(report.state);
				resul.find('li[message] span').html(report.message);
				resul.find('li[taketime] span').html(report.takeTime);
				var time=new Date(report.ctime).Format('yyyy-MM-dd hh:mm:ss');
				resul.find('li[ctime] span').html(time);
				resul.show();
				responsePanel.html(report.response);
				return;
			}
//			console.log(report);
			var reportPanel=$('.panel-tools > .popup-ul > .popup-rbar-region.test-report .popup-rbar-table .popup-rbar-row.data');
			if(typeof report.simCount=='undefined'){
				return;
			}
			reportPanel.find('.popup-rbar-cell[simcount] label').html(report.simCount+'');
			var avg=(report.takeTimes/report.simCount);
			avg=Math.floor(avg * 100) / 100;
			reportPanel.find('.popup-rbar-cell[avg] label').html(avg+'');
			reportPanel.find('.popup-rbar-cell[min] label').html(report.min+'');
			reportPanel.find('.popup-rbar-cell[max] label').html(report.max+'');
			var passcnt=parseInt(report.passCount);
			var simcnt=parseInt(report.simCount);
			var errrate=(simcnt-passcnt)*1.0/simcnt;
			errrate=errrate*100;
			var passrate=(passcnt)*1.0/simcnt;
			passrate=passrate*100;
			reportPanel.find('.popup-rbar-cell[errrate] label').html(Math.floor(errrate * 100) / 100+'');
			reportPanel.find('.popup-rbar-cell[passrate] label').html(Math.floor(passrate * 100) / 100+'');
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