$(document).ready(function(){
	var connector=$('.portlet .req-url label');
	connector.attr('state','isclose');
	function onmessage(frame){
		if(frame.isFrame){
			console.log('...onmessage--是否侦:'+frame.isFrame+'; '+frame.heads.command+' '+frame.heads.url+' '+frame.heads.protocol);
			return;
		}
		console.log('...onmessage--是否侦:'+frame.isFrame+' 内容：'+frame.content);
	}
	function onopen(e){
		connector.html('已连接');
		connector.attr('state','isopen');
	}
	function onclose(e){
		connector.html('未连接');
		connector.attr('state','isclose');
	}
	function onerror(e){
		alert('ws error:'+e);
	}
	
	connector.on('click',function(){
		var host=$(this).siblings('input').val();
		if(connector.attr('state')=='isopen'){
			connector.ws.close();
		}else{
			var ws=$.ws.open(host,onmessage, onopen, onclose,onerror);
			connector.ws=ws;
		}
	})
	var sender=$('.portlet .headline > ul > li.send');
	sender.on('click',function(){
		var mid=$('.container > .workbench > .desktop > .column .layout-main .column-context > .main-column-lets > .portlet[position]').attr('mid');
		
		var netptCheckedValue=$('.portlet .net-protocols > ul > li input:radio:checked').attr('id');
		console.log('....'+netptCheckedValue);
		if('wsOnBrowser'!=netptCheckedValue){
			//服务器端是异步回路，作业处理完之后通过全局的ws将响应推送回来显示
			$.get('./views/onserver-job-add.service',{mid:mid},function(data){
				
			}).error(function(e){
				alert(e.responseText);
			})
			return;
		}
		//以下是处理wsOnBrowser
		if(typeof connector.ws=='undefined'||connector.attr('state')=='isclose'){
			alert('请先点击连接按钮');
			return;
		}
		$.get('./views/request-frame-get.service',{mid:mid},function(data){
			var obj=$.parseJSON(data);
			obj.frame=$.parseJSON(obj.frameText);
			if((typeof obj.frame.content!='undefined')&&obj.frame.content.indexOf('\"')>-1){
				obj.frame.contentText=obj.frame.content;
				obj.frame.content=$.parseJSON(obj.frame.content.replace(/\\\"/g,'"'));
			}
			delete obj.frameText;
			
			var url=obj.frame.headers.url;

			//			connector.ws.sendText('command=put\r\nurl='+url+'\r\nprotocol=ws/1.0\r\n\r\nname=cj\r\n\r\n我是中国人')
			
			var frame={heads:{},params:{}};
			
			for(var head in obj.frame.headers){
				frame.heads[head]=obj.frame.headers[head];
			}
			for(var p in obj.frame.parameters){
				frame.params[p]=obj.frame.parameters[p];
			}
			frame.content=obj.frame.contentText;
			for(var i=0;i<1;i++){
				connector.ws.sendFrame(frame);
			}
		}).error(function(e){
			alert(e.responseText);
		});
	})
});