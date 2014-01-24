function getTestId(id) {
	var indx1 = id.indexOf("_");
	var indx2 = id.indexOf("_", indx1+1);
	var testId = id.substring(indx1+1, indx2);
	return testId;
}
function getQuestionId(id) {
	var indx1 = id.indexOf("_");
	var indx2 = id.indexOf("_", indx1+1);
	var indx3 = id.indexOf("_", indx2+1);
	var questionId = id.substring(indx2+1);
	if (indx3 > -1) {
		questionId = id.substring(indx2+1, indx3);
	}
	return questionId;
}

function navigateToQuestion(testId, questionId) {
	$(".q").hide();
	$("#q_" + testId + "_" + questionId).show();
}

function selectQNavTd(qNavTdId) {
	$(".qNavTdSelected").attr('class', 'qNavTd');
	$(".qNavTdSelectedSolved").attr('class', 'qNavTdSolved');
	if ($("#"+qNavTdId).attr('class') == "qNavTd") {
		$("#"+qNavTdId).attr('class', 'qNavTdSelected');
	} else if ($("#"+qNavTdId).attr('class') == "qNavTdSolved") {
		$("#"+qNavTdId).attr('class', 'qNavTdSelectedSolved');		
	}
}
function openNextTestDiv(testId, newTestId) {
	if (testId != newTestId) {
		var active = $( "#tNav" ).accordion( "option", "active" );
		var newActive = active + 1;
		$( "#tNav" ).accordion( "option", "active", newActive );
	}
}
function openPrevTestDiv(testId, newTestId) {
	if (testId != newTestId) {
		var active = $( "#tNav" ).accordion( "option", "active" );
		var newActive = active - 1;
		$( "#tNav" ).accordion( "option", "active", newActive );
	}
}


$(document).ready(function(){
	// accordion: to make animation faster & to initialize with none selected 
	$( "#tNav" ).accordion({ animate: {duration: 200}, active: 1000	});
	
	// tooltip: to enable tooltips display html
	$(document).tooltip({ content: function () { return $(this).prop('title'); } });
	
	// to hide all questions initially
	$(".q").hide();
	
	// click event of question navigation boxes
	$(".qNavTd").click(function(){
		var qNavTdId = $(this).attr('id');
		var testId = getTestId(qNavTdId);
		var questionId = getQuestionId(qNavTdId);
		navigateToQuestion(testId, questionId);
		selectQNavTd(qNavTdId);
	});
	
	// click event of next button
	$(".qThNext").click(function(){
		var qThId = $(this).attr('id');
		var testId = getTestId(qThId);
		var questionId = getQuestionId(qThId);
		var qId = "#q_" + testId + "_" + questionId;
		var newQId = "#" + $(qId).next().attr('id');
		if (newQId != "#undefined") {
			if ($(newQId).attr('class') == "q") {
				var newTestId = getTestId(newQId);
				var newQuestionId = getQuestionId(newQId);				
				navigateToQuestion(newTestId, newQuestionId);
				var qNavTdId = "qNavTd_" + newTestId + "_" + newQuestionId;
				selectQNavTd(qNavTdId);
				openNextTestDiv(testId, newTestId);
			}
		}
	});
	// click event of previous button
	$(".qThPrev").click(function(){
		var qThId = $(this).attr('id');
		var testId = getTestId(qThId);
		var questionId = getQuestionId(qThId);
		var qId = "#q_" + testId + "_" + questionId;
		var newQId = "#" + $(qId).prev().attr('id');
		if (newQId != "#undefined") {
			if ($(newQId).attr('class') == "q") {
				var newTestId = getTestId(newQId);
				var newQuestionId = getQuestionId(newQId);
				navigateToQuestion(newTestId, newQuestionId);
				var qNavTdId = "qNavTd_" + newTestId + "_" + newQuestionId;
				selectQNavTd(qNavTdId);
				openPrevTestDiv(testId, newTestId);
			}
		}
	});
});