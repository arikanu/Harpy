<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



	<script>		
		$(document).ready(function(){
			$( "#tNav" ).accordion({
				animate: {
			        duration: 200
			    },
			    active: 1000
			});
			
			$(document).tooltip({
				content: function () {
					return $(this).prop('title');
				}
			});
			
			
			
			$(".q").hide();			
			
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
	</script>


<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

   


<div id="tNav">
	<c:forEach var="test" items="${m.exam.tests}">
		<h3>${test.name}</h3>
		<div>
			<table id="qNav_${test.testId}" class="qNav">
				<tbody>
					<tr>
						<c:forEach var="question" items="${test.questions}">
							<td class="qNavTd" id="qNavTd_${test.testId}_${question.questionId}">								
								<a title="${question.questionHtml}">${question.number}</a>								
							</td>
						</c:forEach>
					</tr>
				</tbody>
			</table>
		</div>		
	</c:forEach>
</div>

<c:forEach var="test" items="${m.exam.tests}">
	<c:forEach var="question" items="${test.questions}">
		<div id="q_${test.testId}_${question.questionId}" class="q">
			<table class="qTitle">
				<tr>
					<th id="qThPrev_${test.testId}_${question.questionId}" class="qThPrev"></th>
					<th>${test.name} - ${question.number}</th>
					<th id="qThNext_${test.testId}_${question.questionId}" class="qThNext"></th>
				</tr>
			</table>
			<table class="qBody">
				<tbody>
					<tr>
						<td>
							${question.questionHtml}
							<table>
								<c:forEach var="choice" items="${question.choices}">
									<tr>
										<td>
											${choice.choiceCode})
										</td>
										<td>
											${choice.choiceHtml}
										</td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</c:forEach>
</c:forEach>
