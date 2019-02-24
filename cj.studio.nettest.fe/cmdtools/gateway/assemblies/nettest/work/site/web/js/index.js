$(document).ready(function(){
	var mask=$('.container > .workbench > .desktop > .mask');
	mask.on('click',function(){
		var onMasks=$('.container > .workbench > .desktop > .column .layout-main .column-context .on-mask');
		onMasks.hide();
		$(this).hide();
	})
	
	var pannelTools=$('.container > .workbench > .desktop > .column .layout-main .column-context > .panel-tools');
	var addFolder=$('.container > .workbench > .desktop > .column .column-left > .proj-region > .pr-toolbar li[addFolder]');
	addFolder.on('click',function(){
		if(!pannelTools.is('on')){
			if(!mask.is('on')){
				mask.show();
			}
			var template=$(this).parents('ul').siblings('.popup-template');
			$.get('./views/folder-editor.service',{},function(data){
				var ul=template.find('.popup-rbar-table').first();
				var cli=ul.find('>li').first().clone();
				var folders=$.parseJSON(data);
				if(folders.length==0){
					ul.empty();
					ul.append(cli.clone());
					var html=template.html();
					pannelTools.html(html);
					pannelTools.show();
					return;
				}
				ul.empty();
				for(var i=0;i<folders.length;i++){
					var folder=folders[i];
					var li=cli.clone();
					li.attr('id',folder.id);
					li.find('.popup-rbar-cell.code>input').attr('value',folder.code);
					li.find('.popup-rbar-cell.name>input').attr('value',folder.name==null?'':folder.name);
					ul.append(li);
				}
				var html=template.html();
				pannelTools.html(html);
				pannelTools.show();
			});
		}
	});
	var columnContext=$('.container > .workbench > .desktop > .column .layout-main .column-context');
	var prRegion=$('.container>.workbench>.desktop>.column  .column-left>.proj-region');
	prRegion.undelegate('>.pr-tree>.pr-folders>.pr-folder .popup-arrow','click');
	prRegion.delegate('>.pr-tree>.pr-folders>.pr-folder .popup-arrow','click',function(e){
		e.stopPropagation();
		e.preventDefault();
		if(!mask.is('on')){
			mask.show();
		}
		if(!pannelTools.is('on')){
			var arrow=$(this).attr('arrow');
			switch(arrow){
			case 'folder':
				var template=$(this).parents('.pr-tree').find('>.popup-template');
				var folder=$(this).parents('.pr-folder').attr('code');
				pannelTools.attr('folder',folder);
				var titlebar=template.find('.popup-bar-left>span');
				titlebar.html(folder);
				$(this).siblings('.folder-main').trigger('click');
				$.get('./views/service-get.service',{folder:folder},function(data){
					var ul=template.find('.popup-rbar-table').first();
					var cli=ul.find('>li').first().clone();
					var services=$.parseJSON(data);
					if(services.length==0){
						ul.empty();
						ul.append(cli.clone());
						var html=template.html();
						pannelTools.html(html);
						pannelTools.show();
						return;
					}
					ul.empty();
					for(var i=0;i<services.length;i++){
						var service=services[i];
						var li=cli.clone();
						li.attr('id',service.id);
						li.find('.popup-rbar-cell.code>input').attr('value',service.code);
						li.find('.popup-rbar-cell.name>input').attr('value',service.name==null?'':service.name);
						ul.append(li);
					}
					var html=template.html();
					pannelTools.html(html);
					pannelTools.show();
				});
				break;
			case 'obj':
				var template=$(this).parents('.pr-folder').find('>.popup-template');
				var obj=$(this).parents('.pr-obj').attr('code');
				var folder=$(this).parents('.pr-obj').attr('folder');
				pannelTools.attr('folder',folder);
				pannelTools.attr('service',obj);
				var titlebar=template.find('.popup-bar-left>span');
				titlebar.html(obj);
				$(this).siblings('.obj-main').trigger('click');
				$.get('./views/method-get.service',{folder:folder,service:obj},function(data){
					var ul=template.find('.popup-rbar-table').first();
					var cli=ul.find('>li').first().clone();
					var services=$.parseJSON(data);
					if(services.length==0){
						ul.empty();
						ul.append(cli.clone());
						var html=template.html();
						pannelTools.html(html);
						pannelTools.show();
						return;
					}
					ul.empty();
					for(var i=0;i<services.length;i++){
						var service=services[i];
						var li=cli.clone();
						li.attr('id',service.id);
						li.find('.popup-rbar-cell.code>input').attr('value',service.code);
						li.find('.popup-rbar-cell.name>input').attr('value',service.name==null?'':service.name);
						ul.append(li);
					}
					var html=template.html();
					pannelTools.html(html);
					pannelTools.show();
				});
				break;
			case 'method':
				var template=$(this).parents('.pr-obj').find('>.popup-template');
				var method=$(this).parents('.pr-method').attr('code');
				var folder=$(this).parents('.pr-method').attr('folder');
				var service=$(this).parents('.pr-method').attr('service');
				var mid=$(this).parents('.pr-method').attr('id');
				pannelTools.attr('folder',folder);
				pannelTools.attr('service',service);
				pannelTools.attr('method',method);
				pannelTools.attr('mid',mid);
				$(this).siblings('.method-main').trigger('click');
				var titlebar=template.find('.popup-tbar .popup-bar-left>span');
				titlebar.html(method);
				$.get('./views/runner-get.service',{mid},function(data){
					var obj=$.parseJSON(data);
					pannelTools.html(template.html());
					if(obj.strategy){
						var table= pannelTools.find('.popup-ul.method > .popup-ibar-region > .popup-ibar-table');
						table.find('input[threadcount]').val(obj.strategy.threadcount);
						table.find('input[intervals]').val(obj.strategy.intervals);
						table.find('input[loopcount]').val(obj.strategy.loopcount);
						if(obj.strategy.alwaysloop){
							table.find('input[alwaysloop]').prop('checked',true);
							table.find('input[loopcount]').attr('disabled','disabled');
						}
					}
					pannelTools.show();
				}).error(function(e){
					alert(e.responseText);
				})
				break;
			}
			
		}
	});
	prRegion.undelegate('>.pr-tree>.pr-folders>.pr-folder>.folder-main','click');
	prRegion.delegate('>.pr-tree>.pr-folders>.pr-folder>.folder-main','click',function(){
		var arrow=$(this).find('.folder-arrow');
		var isdown=arrow.attr('isdown');
		var objPad=$(this).siblings('.pr-objs');
		if(isdown=='false'){// 换成向下
			arrow.attr('src','img/arrow-down.svg');
			objPad.show();
			arrow.attr('isdown','true');
		}else{
			arrow.attr('src','img/arrow.svg');
			objPad.hide();
			arrow.attr('isdown','false');
		}
		prRegion.find('.selected').removeClass('selected');
		$(this).addClass('selected');
	});
	prRegion.undelegate('>.pr-tree > .pr-folders > .pr-folder > .pr-objs > .pr-obj>.obj-main','click');
	prRegion.delegate('>.pr-tree > .pr-folders > .pr-folder > .pr-objs > .pr-obj>.obj-main','click',function(){
		var arrow=$(this).find('.obj-arrow');
		var isdown=arrow.attr('isdown');
		var methodPad=$(this).siblings('.pr-methods');
		if(isdown=='false'){// 换成向下
			arrow.attr('src','img/arrow-down.svg');
			methodPad.show();
			arrow.attr('isdown','true');
		}else{
			arrow.attr('src','img/arrow.svg');
			methodPad.hide();
			arrow.attr('isdown','false');
		}
		prRegion.find('.selected').removeClass('selected');
		$(this).addClass('selected');
	});
	prRegion.undelegate('>.pr-tree > .pr-folders > .pr-folder > .pr-objs > .pr-obj > .pr-methods > .pr-method > .method-main','click');
	prRegion.delegate('>.pr-tree > .pr-folders > .pr-folder > .pr-objs > .pr-obj > .pr-methods > .pr-method > .method-main','click',function(){
		prRegion.find('.selected').removeClass('selected');
		$(this).addClass('selected');
		var pos=$('.container > .workbench > .desktop > .column .layout-main .column-context > .main-column-lets > .portlet[position]');
		var li=$(this).parents('li.pr-method');
		pos.find('span[folder]').html(li.attr('folder'));
		pos.find('span[service]').html(li.attr('service'));
		pos.find('span[method]').html(li.attr('code'));
		pos.attr('mid',li.attr('id'));
	});
	var tabPanels=$('.portlet .settings>.tab-panels');
	var tabs=$('.portlet .settings > .tabs');
	tabs.children('.tab').on('click',function(){
		tabPanels.find('>.tab-panel').hide();
		tabs.find('.selected').removeClass('selected');
		$(this).addClass('selected');
		var kind=$(this).attr('kind');
		switch(kind){
		case 'parameter':
			tabPanels.find('>.parameter').show();
			break;
		case 'header':
			tabPanels.find('>.header').show();
			break;
		case 'content':
			tabPanels.find('>.content').show();
			break;
		}
	});
	var cntTabPanels=$('.portlet .settings > .tab-panels > .tab-panel.content>.tab-panels');
	var cntRadios=$('.portlet .settings > .tab-panels > .tab-panel.content .radios input');
	var selectedRadio=$('.portlet .settings > .tab-panels > .tab-panel.content .radios input:radio:checked');
	var onSeletedRaio=function(the){
		cntTabPanels.find('.tab-panel').hide();
		var kind=the.siblings('label').html();
		switch(kind){
		case 'None':
			cntTabPanels.find('>.None').show();
			break;
		case 'Any':
			cntTabPanels.find('>.Any').show();
			break;
		case 'x-www-form-urlencode':
			cntTabPanels.find('>.x-www-form-urlencode').show();
			break;
		case 'Form-Data':
			cntTabPanels.find('>.Form-Data').show();
			break;
		}
	}
	onSeletedRaio(selectedRadio);
	cntRadios.on('change',function(){
		onSeletedRaio($(this));
	});
	
	tabPanels.undelegate('.tab-table-row','mouseenter');
	tabPanels.delegate('.tab-table-row','mouseenter',function(){
		$(this).find('.op').attr('style','display:table-cell;');
	})
	tabPanels.undelegate('.tab-table-row','mouseleave');
	tabPanels.delegate('.tab-table-row','mouseleave',function(){
		$(this).find('.op').hide();
	})
	cntTabPanels.undelegate('.tab-table-row','mouseenter');
	cntTabPanels.delegate('.tab-table-row','mouseenter',function(){
		$(this).find('.op').attr('style','display:table-cell;');
	})
	cntTabPanels.undelegate('.tab-table-row','mouseleave');
	cntTabPanels.delegate('.tab-table-row','mouseleave',function(){
		$(this).find('.op').hide();
	});
	
	var logoutE=$('.container > .workbench > .header > .topbar > .items > li[logout]');
	logoutE.on('click',function(){
		$.get('./public/logout.service',{},function(){
			window.location.href='./';
		});
	});
	var panelTools=$('.panel-tools');
	panelTools.on('refreshPTree',function refreshPTree(){
		$.get('./',{onlyPrintPt:true},function(html){
			$('.pr-tree').html(html);
		}).error(function(e){
			alert(e.responseText);
		})});

});