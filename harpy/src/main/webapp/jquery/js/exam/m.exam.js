$(document).ready(function(){
	
	alert('ugur');
	
	$("div.tNav ul li").first().attr('class', 'selected');
	var tNb = $("div.tNav ul li.selected").attr('id');
	tNb = tNb.substring(7);
	$("div.qNav ul li").each(function(){
		var qNavLi = $(this).attr('id');
		var qTNb = qNavLi.substring(7, qNavLi.indexOf("_", 7));		
		if (qTNb == tNb) {
			var qNb = qNavLi.substring(qNavLi.indexOf("_", 7)+1);
			if (qNb == "1") {
				$(this).attr('class', 'displayedS');
			} else {
				$(this).attr('class', 'displayedNS');
			}
		} else {
			$(this).attr('class', 'hidden');
		}
	});
	
	
	
	$("div.tNav ul li").click(function(){	
		var selTNb = $("div.tNav ul li.selected").attr('id');
		selTNb = selTNb.substring(7);		
		var curTNb = $(this).attr('id');
		curTNb = curTNb.substring(7);
		
		if (selTNb != curTNb) {
			var selTId = "tNavLi_" + selTNb;
			var curTId = "tNavLi_" + curTNb;
			$("#" + selTId).attr('class', 'notselected');
			$("#" + curTId).attr('class', 'selected');
			
			
			
			$("div.qNav ul li").each(function(){
				var qNavLi = $(this).attr('id');
				var qTNb = qNavLi.substring(7, qNavLi.indexOf("_", 7));
				if (qTNb == curTNb) {
					var qNb = qNavLi.substring(qNavLi.indexOf("_", 7)+1);
					if (qNb == "1") {
						//$(this).removeClass('displayedNS');
						//$(this).removeClass('hidden');
						$(this).addClass('displayedS');
						//$(this).attr('class', 'displayedS');					
					} else {
						//$(this).removeClass('displayedS');
						//$(this).removeClass('hidden');
						$(this).addClass('displayedNS');
						//$(this).attr('class', 'displayedNS');
					}
				} else {
					$(this).attr('class', 'hidden');
				}
			});			
		}		
	});
	
	
	
	$(".displayedNS").click(function(){

		alert('uuuur');
		
		
	});
});