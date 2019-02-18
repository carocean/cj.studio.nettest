$(document).ready(function(){
	var mask=$('.container > .workbench > .desktop > .mask');
	mask.on('click',function(){
		var onMasks=$('.container > .workbench > .desktop > .column .layout-main .column-context .on-mask');
		onMasks.hide();
		$(this).hide();
	})
	var folders=$('.container>.workbench>.desktop>.column  .column-left>.proj-region>.pr-tree>.pr-folders>.pr-folder,.container > .workbench > .desktop > .column .column-left > .proj-region > .pr-tree > .pr-folders > .pr-folder > .pr-objs > .pr-obj,.container > .workbench > .desktop > .column .column-left > .proj-region > .pr-tree > .pr-folders > .pr-folder > .pr-objs > .pr-obj > .pr-methods > .pr-method');
	folders.on('click',function(e){
		e.stopPropagation();
		e.preventDefault();
		$(this).parents('.container>.workbench>.desktop>.column  .column-left>.proj-region>.pr-tree>.pr-folders').find('.selected').removeClass('selected');
		$(this).addClass('selected');
	});
	var pannelTools=$('.container > .workbench > .desktop > .column .layout-main .column-context > .panel-tools');
	pannelTools.on('click',function(){
		alert('');
	})
	var columnContext=$('.container > .workbench > .desktop > .column .layout-main .column-context');
	var popupArrow=$('.container>.workbench>.desktop>.column  .column-left>.proj-region>.pr-tree>.pr-folders>.pr-folder .popup-arrow');
	popupArrow.on('click',function(e){
		e.stopPropagation();
		e.preventDefault();
//		var offset=$(this).offset();
//		var columnOffset=columnContext.offset();
//		var top=(offset.top-columnOffset.top);
//		pannelTools.attr('style','top:'+top+'px;');
		if(!mask.is('on')){
			mask.show();
		}
		if(!pannelTools.is('on')){
			pannelTools.show();
		}
	});
	var folderMain=$('.container>.workbench>.desktop>.column  .column-left>.proj-region>.pr-tree>.pr-folders>.pr-folder>.folder-main');
	folderMain.on('click',function(){
		var arrow=$(this).find('.folder-arrow');
		var isdown=arrow.attr('isdown');
		var objPad=$(this).siblings('.pr-objs');
		if(isdown=='false'){//换成向下
			arrow.attr('src','img/arrow-down.svg');
			objPad.show();
			arrow.attr('isdown','true');
		}else{
			arrow.attr('src','img/arrow.svg');
			objPad.hide();
			arrow.attr('isdown','false');
		}
	});
	var objMain=$('.container > .workbench > .desktop > .column .column-left > .proj-region > .pr-tree > .pr-folders > .pr-folder > .pr-objs > .pr-obj>.obj-main');
	objMain.on('click',function(){
		var arrow=$(this).find('.obj-arrow');
		var isdown=arrow.attr('isdown');
		var methodPad=$(this).siblings('.pr-methods');
		if(isdown=='false'){//换成向下
			arrow.attr('src','img/arrow-down.svg');
			methodPad.show();
			arrow.attr('isdown','true');
		}else{
			arrow.attr('src','img/arrow.svg');
			methodPad.hide();
			arrow.attr('isdown','false');
		}
	})
});