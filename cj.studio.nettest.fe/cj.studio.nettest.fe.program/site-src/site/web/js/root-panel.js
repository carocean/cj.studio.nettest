$(document).ready(function(){
	var panelTools=$(".panel-tools");
	
	panelTools.undelegate(' > .popup-ul > .popup-bar > .popup-bar-right-ul > li[addFolder]','click');
	panelTools.delegate('> .popup-ul > .popup-bar > .popup-bar-right-ul > li[addFolder]','click',function(){
		var table=$(this).parents('.popup-bar').siblings('.popup-tbar-region').find('.popup-rbar-table').first();
		var cli=table.find('.popup-rbar-row').first().clone();
		cli.removeAttr('id');
		cli.find('.popup-rbar-cell.code>input').val('');
		cli.find('.popup-rbar-cell.name>input').val('');
		table.append(cli);
	});
	panelTools.undelegate(' > .popup-ul > .popup-bar > .popup-bar-right-ul > li[retrieve] input','keyup');
	panelTools.delegate('> .popup-ul > .popup-bar > .popup-bar-right-ul > li[retrieve] input','keyup',function(e){
		if(e.keyCode!=13){
			return;
		}
		var user=$(this).val();
		$.get('./views/retrieve-config.service',{user:user},function(data){
			window.location.href='./';
		}).error(function(e){
			alert(e.responseText);
		});
	});
	panelTools.undelegate('> .popup-ul.root > .popup-tbar-region > .popup-rbar-table > .popup-rbar-row > .popup-rbar-cell.save','click');
	panelTools.delegate('> .popup-ul.root > .popup-tbar-region > .popup-rbar-table > .popup-rbar-row > .popup-rbar-cell.save','click',function(){
		var row=$(this).parent('.popup-rbar-row');
		var code=row.find('.popup-rbar-cell.code>input').val();
		var name=row.find('.popup-rbar-cell.name>input').val();
		var id=row.attr('id');
		var action=(typeof id=='undefined')?'new':'update';
		switch(action){
		case 'new':
			$.get('./views/folder-add.service',{code,name},function(data){
				var obj=$.parseJSON(data);
				row.attr('id',obj.id);
				row.find('.popup-rbar-cell>input').removeAttr('style');
				panelTools.trigger('refreshPTree');
			}).error(function(e){
				alert(e.responseText);
			});
			break;
		case 'update':
			$.get('./views/folder-update.service',{id,code,name},function(){
				row.find('.popup-rbar-cell>input').removeAttr('style');
				panelTools.trigger('refreshPTree');
			}).error(function(e){
				alert(e.responseText);
			});
			break;
		}
	});
	panelTools.undelegate('> .popup-ul.root > .popup-tbar-region > .popup-rbar-table > .popup-rbar-row > .popup-rbar-cell.delete','click');
	panelTools.delegate('> .popup-ul.root > .popup-tbar-region > .popup-rbar-table > .popup-rbar-row > .popup-rbar-cell.delete','click',function(){
		var row=$(this).parents('.popup-rbar-row');
		var crow=row.clone();
		crow.removeAttr('id');
		crow.removeAttr('style');
		crow.find('.popup-rbar-cell.code>input').val('');
		crow.find('.popup-rbar-cell.name>input').val('');
		var table=$(this).parents(' .popup-rbar-table');
		var id=row.attr('id');
		if(typeof id=='undefined'){
			row.remove();
			if(table.find('.popup-rbar-row').length==0){
				table.append(crow);
			}
			return;
		}
		$.get('./views/folder-delete.service',{id},function(){
			row.remove();
			if(table.find('.popup-rbar-row').length==0){
				table.append(crow);
			}
			panelTools.trigger('refreshPTree');
		}).error(function(e){
			alert(e.responseText);
		});
	});
	panelTools.undelegate('> .popup-ul.root > .popup-tbar-region > .popup-rbar-table > .popup-rbar-row > .popup-rbar-cell>input','change');
	panelTools.delegate('> .popup-ul.root > .popup-tbar-region > .popup-rbar-table > .popup-rbar-row > .popup-rbar-cell>input','change',function(){
		var row=$(this).parents('.popup-rbar-row');
		$(this).attr('style','border:1px solid red;');
		var code=row.find('.popup-rbar-cell.code>input').val();
		if(typeof code!='undefined'&&code!=''){
			if(code.indexOf('.')>-1){
				alert('名称不能含有.号');
				row.find('.popup-rbar-cell.code>input').val('');
			}
		}
	});
});