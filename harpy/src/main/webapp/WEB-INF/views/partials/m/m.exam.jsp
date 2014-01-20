<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url value="/jquery/js/exam/m.exam.js" var="mExamJs" />
	<script src="${mExamJs}"></script>
<spring:url value="/css/exam/m.exam.css" var="mExamCss" />
	<link rel="stylesheet" href="${mExamCss}">

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
								<tr height="40px" valign="bottom">
									<td colspan="2">Correct Answer: <strong>${question.correctAnswer}</strong></td>
								</tr>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</c:forEach>
</c:forEach>