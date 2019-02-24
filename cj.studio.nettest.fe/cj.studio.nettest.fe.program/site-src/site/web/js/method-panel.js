$(document).ready(function(){
	var panelTools=$(".panel-tools");
	
	panelTools.undelegate(' > .popup-ul.method > .popup-ibar-region > .popup-ibar-table > .popup-ibar-row > .popup-ibar-cell input[alwaysloop]','click');
	panelTools.delegate(' > .popup-ul.method > .popup-ibar-region > .popup-ibar-table > .popup-ibar-row > .popup-ibar-cell input[alwaysloop]','click',function(){
		var loopcount=$(this).parents('.popup-ibar-row').find('input[loopcount]');
		if(loopcount.attr('disabled')=='disabled'){
			loopcount.removeAttr('disabled');
		}else{
			loopcount.attr('disabled','disabled');
		}
	});
	panelTools.undelegate(' > .popup-ul.method > .popup-bar > .popup-bar-right-ul > li[save]','click');
	panelTools.delegate('> .popup-ul.method > .popup-bar > .popup-bar-right-ul > li[save]','click',function(){
		var id=panelTools.attr('id');
		var methodid=panelTools.attr('mid');
		var folder=panelTools.attr('folder');
		var service=panelTools.attr('service');
		var method=panelTools.attr('method');
		var cells=panelTools.find('> .popup-ul > .popup-ibar-region > .popup-ibar-table > .popup-ibar-row > .popup-ibar-cell');
		var threadcount=cells.find('input[type="text"][threadcount]').val();
		var intervals=cells.find('input[type="text"][intervals]').val();
		var alwaysloop=cells.find('input[type="checkbox"][alwaysloop]').prop('checked');
		var loopcount=cells.find('input[type="text"][loopcount]').val();
		$.post('./views/runner-save.service',{id:id,mid:methodid,threadcount:threadcount,intervals:intervals,alwaysloop:alwaysloop,loopcount:loopcount},function(){
			panelTools.find('.popup-bar-left>label').html('');
		}).error(function(e){
			alert(e.responseText);
		})
		
	});
	
	panelTools.undelegate(' > .popup-ul.method > .popup-bar > .popup-bar-right-ul > li[run]','click');
	panelTools.delegate('> .popup-ul.method > .popup-bar > .popup-bar-right-ul > li[run]','click',function(){
		alert('');
	});
	
	panelTools.undelegate(' > .popup-ul.method > .popup-bar > .popup-bar-right-ul > li[stop]','click');
	panelTools.delegate('> .popup-ul.method > .popup-bar > .popup-bar-right-ul > li[stop]','click',function(){
		alert('');
	});
	
	panelTools.undelegate('>.popup-ul > .popup-ibar-region > .popup-ibar-table > .popup-ibar-row > .popup-ibar-cell input[type="text"]','change');
	panelTools.delegate('>.popup-ul > .popup-ibar-region > .popup-ibar-table > .popup-ibar-row > .popup-ibar-cell input[type="text"]','change',function(){
		panelTools.find('.popup-bar-left>label').html('*');
	});
	panelTools.undelegate('>.popup-ul > .popup-ibar-region > .popup-ibar-table > .popup-ibar-row > .popup-ibar-cell input[type="checkbox"]','change');
	panelTools.delegate('>.popup-ul > .popup-ibar-region > .popup-ibar-table > .popup-ibar-row > .popup-ibar-cell input[type="checkbox"]','change',function(){
		panelTools.find('.popup-bar-left>label').html('*');
	});
});