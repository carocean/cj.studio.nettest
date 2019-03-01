$(document).ready(function(){
	var sender=$('.portlet .headline > ul > li.send');
	sender.on('click',function(){
		var mid=$('.container > .workbench > .desktop > .column .layout-main .column-context > .main-column-lets > .portlet[position]').attr('mid');
		$.get('./views/request-frame-get.service',{mid:mid},function(data){
			var obj=$.parseJSON(data);
			obj.frame=$.parseJSON(obj.frameText);
			obj.frame.content=$.parseJSON(obj.frame.content.replace(/'/g,'"'));
			delete obj.frameText;
			console.log(obj);
		}).error(function(e){
			alert(e.responseText);
		});
	})
});