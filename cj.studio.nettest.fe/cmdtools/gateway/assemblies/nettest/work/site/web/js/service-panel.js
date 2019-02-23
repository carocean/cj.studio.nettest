$(document).ready(function(){
	var panelTools=$(".panel-tools");
	
	panelTools.undelegate(' > .popup-ul.service > .popup-bar > .popup-bar-right-ul > li[addMethod]','click');
	panelTools.delegate('> .popup-ul.service > .popup-bar > .popup-bar-right-ul > li[addMethod]','click',function(){
		var table=$(this).parents('.popup-bar').siblings('.popup-tbar-region').find('.popup-rbar-table').first();
		var cli=table.find('.popup-rbar-row').first().clone();
		cli.removeAttr('id');
		cli.find('.popup-rbar-cell.code>input').val('');
		cli.find('.popup-rbar-cell.name>input').val('');
		table.append(cli);
	});
	
	panelTools.undelegate('> .popup-ul.service > .popup-tbar-region > .popup-rbar-table > .popup-rbar-row > .popup-rbar-cell.save','click');
	panelTools.delegate('> .popup-ul.service > .popup-tbar-region > .popup-rbar-table > .popup-rbar-row > .popup-rbar-cell.save','click',function(){
		var row=$(this).parent('.popup-rbar-row');
		var code=row.find('.popup-rbar-cell.code>input').val();
		var name=row.find('.popup-rbar-cell.name>input').val();
		var id=row.attr('id');
		var folder=panelTools.attr('folder');
		var service=panelTools.attr('service');
		var action=(typeof id=='undefined')?'new':'update';
		switch(action){
		case 'new':
			$.get('./views/method-add.service',{folder,service,code,name},function(data){
				var obj=$.parseJSON(data);
				row.attr('id',obj.id);
				row.find('.popup-rbar-cell>input').removeAttr('style');
				panelTools.trigger('refreshPTree');
			}).error(function(e){
				alert(e.responseText);
			});
			break;
		case 'update':
			$.get('./views/method-update.service',{id,folder,service,code,name},function(){
				row.find('.popup-rbar-cell>input').removeAttr('style');
				panelTools.trigger('refreshPTree');
			}).error(function(e){
				alert(e.responseText);
			});
			break;
		}
	});
	panelTools.undelegate('> .popup-ul.service > .popup-tbar-region > .popup-rbar-table > .popup-rbar-row > .popup-rbar-cell.delete','click');
	panelTools.delegate('> .popup-ul.service > .popup-tbar-region > .popup-rbar-table > .popup-rbar-row > .popup-rbar-cell.delete','click',function(){
		var row=$(this).parents('.popup-rbar-row');
		var crow=row.clone();
		crow.removeAttr('id');
		crow.removeAttr('style');
		crow.find('.popup-rbar-cell.code>input').val('');
		crow.find('.popup-rbar-cell.name>input').val('');
		var table=$(this).parents(' .popup-rbar-table');
		var id=row.attr('id');
		var folder=panelTools.attr('folder');
		var service=panelTools.attr('service');
		if(typeof id=='undefined'){
			row.remove();
			if(table.find('.popup-rbar-row').length==0){
				table.append(crow);
			}
			return;
		}
		$.get('./views/method-delete.service',{id,folder},function(){
			row.remove();
			if(table.find('.popup-rbar-row').length==0){
				table.append(crow);
			}
			panelTools.trigger('refreshPTree');
		}).error(function(e){
			alert(e.responseText);
		});
	});
	panelTools.undelegate('> .popup-ul.service > .popup-tbar-region > .popup-rbar-table > .popup-rbar-row > .popup-rbar-cell>input','change');
	panelTools.delegate('> .popup-ul.service > .popup-tbar-region > .popup-rbar-table > .popup-rbar-row > .popup-rbar-cell>input','change',function(){
		var row=$(this).parents('.popup-rbar-row');
		$(this).attr('style','border:1px solid red;');
	});
});