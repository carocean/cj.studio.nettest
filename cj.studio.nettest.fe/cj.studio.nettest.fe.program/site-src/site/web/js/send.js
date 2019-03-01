$(document).ready(function(){
	var sender=$('.portlet .headline > ul > li.send');
	sender.on('click',function(){
		var mid=$('.container > .workbench > .desktop > .column .layout-main .column-context > .main-column-lets > .portlet[position]').attr('mid');
		$.get('./views/request-frame-get.service',{mid:mid},function(data){
			console.log(data);
		}).error(function(e){
			alert(e.responseText);
		});
	})
});