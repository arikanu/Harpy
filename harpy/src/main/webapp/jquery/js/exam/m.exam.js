$(document).ready(function(){
	// accordion: to make animation faster & to initialize with none selected 
	$( "#tNav" ).accordion({
		animate: {
	        duration: 200
	    },
	    active: 1000
	});
	// tooltip: to enable tooltips display html
	$(document).tooltip({
		content: function () {
			return $(this).prop('title');
		}
	});	
	// to hide all questions initially
	$(".q").hide();
	// click event of question boxes
	$(".qNavTd").click(function(){
		if ($(this).attr('class') == 'qNavTd') {
			var qNavTdId = $(this).attr('id');
			var indx1 = qNavTdId.indexOf("_");
			var indx2 = qNavTdId.indexOf("_", indx1+1);
			var testId = qNavTdId.substring(indx1+1, indx2);
			var questionId = qNavTdId.substring(indx2+1);
			var qId = "#q_" + testId + "_" + questionId;
			$(".q").hide();
			$(qId).show();
			$(".qNavTdSelected").attr('class', 'qNavTd');
			$(this).attr('class', 'qNavTdSelected');
		}
	});
	// click event of next button
	$(".qThNext").click(function(){
		var qThId = $(this).attr('id');
		var indx1 = qThId.indexOf("_");
		var indx2 = qThId.indexOf("_", indx1+1);
		var testId = qThId.substring(indx1+1, indx2);
		var questionId = qThId.substring(indx2+1);
		var qId = "#q_" + testId + "_" + questionId;
		var newQId = "#" + $(qId).next().attr('id');
		if (newQId != "#undefined") {
			if ($(newQId).attr('class') == "q") {
				var newIndx1 = newQId.indexOf("_");
				var newIndx2 = newQId.indexOf("_", newIndx1+1);
				var newTestId = newQId.substring(newIndx1+1, newIndx2);
				var newQuestionId = newQId.substring(newIndx2+1);
				var qNavTdId = "#qNavTd_" + newTestId + "_" + newQuestionId;
				$(".q").hide();
				$(newQId).show();					
				$(".qNavTdSelected").attr('class', 'qNavTd');
				$(qNavTdId).attr('class', 'qNavTdSelected');
				if (testId != newTestId) {
					var active = $( "#tNav" ).accordion( "option", "active" );
					var newActive = active + 1;
					$( "#tNav" ).accordion( "option", "active", newActive );
				}
			}
		}
	});
	// click event of previous button
	$(".qThPrev").click(function(){
		var qThId = $(this).attr('id');
		var indx1 = qThId.indexOf("_");
		var indx2 = qThId.indexOf("_", indx1+1);
		var testId = qThId.substring(indx1+1, indx2);
		var questionId = qThId.substring(indx2+1);
		var qId = "#q_" + testId + "_" + questionId;
		var newQId = "#" + $(qId).prev().attr('id');
		if (newQId != "#undefined") {
			if ($(newQId).attr('class') == "q") {
				var newIndx1 = newQId.indexOf("_");
				var newIndx2 = newQId.indexOf("_", newIndx1+1);
				var newTestId = newQId.substring(newIndx1+1, newIndx2);
				var newQuestionId = newQId.substring(newIndx2+1);
				var qNavTdId = "#qNavTd_" + newTestId + "_" + newQuestionId;
				$(".q").hide();
				$(newQId).show();					
				$(".qNavTdSelected").attr('class', 'qNavTd');
				$(qNavTdId).attr('class', 'qNavTdSelected');
				if (testId != newTestId) {
					var active = $( "#tNav" ).accordion( "option", "active" );
					var newActive = active - 1;
					$( "#tNav" ).accordion( "option", "active", newActive );
				}
			}
		}
	});
});