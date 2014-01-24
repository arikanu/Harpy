var progress = 0;
var timer = setInterval(updateProgressbar, 60000);
var durationInMs = 0;

function updateProgressbar(){
    $(".examDuration").progressbar({
        value: ++progress
    });
    if(progress == durationInMs) {
        clearInterval(timer);
        alert('sinav bitti...');
        $("#frmExamSolve").submit();
    }
}
$(function () {
    $(".examDuration").progressbar({
        value: progress
    });
});

$(document).ready(function(){
	//set duration in miliseconds
	durationInMs = $(".examDuration").attr('id') - 115;// - 7195000;

	// to check & uncheck choices
	$(".choice").click(function(){
		var choiceId = $(this).attr('id');
		var testId = getTestId(choiceId);
		var questionId = getQuestionId(choiceId);
		var qNavTdId = "#qNavTd_" + testId + "_" + questionId;
		
		var $radio = $(this);
		if ($radio.data('waschecked') == true) {
			$radio.prop('checked', false);
			$radio.data('waschecked', false);
			$(qNavTdId).attr('class', 'qNavTdSelected');
		} else {
			$radio.prop('checked', true);
			$radio.data('waschecked', true);
			$(qNavTdId).attr('class', 'qNavTdSelectedSolved');
		}
		
		$radio.siblings(".choice").data('waschecked', false);
	});
	
	// progressbar for exam-duration
	 $( ".examDuration" ).progressbar({
		 value: 0,
		 max: durationInMs
	 });	 
	
});