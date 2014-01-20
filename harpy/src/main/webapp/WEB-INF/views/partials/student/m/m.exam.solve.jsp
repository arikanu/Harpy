<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<spring:url value="/jquery/js/exam/m.exam.js" var="mExamJs" />
	<script src="${mExamJs}"></script>
<spring:url value="/css/exam/m.exam.css" var="mExamCss" />
	<link rel="stylesheet" href="${mExamCss}">


<div id="tNav">
	<c:forEach var="sTest" items="${m.sExam.sTests}">
		<h3>${sTest.name}</h3>
		<div>
			<table id="qNav_${sTest.testId}" class="qNav">
				<tbody>
					<tr>
						<c:forEach var="sQuestion" items="${sTest.sQuestions}">
							<td class="qNavTd" id="qNavTd_${sTest.testId}_${sQuestion.questionId}">								
								<a title="${sQuestion.questionHtml}">${sQuestion.number}</a>								
							</td>
						</c:forEach>
					</tr>
				</tbody>
			</table>
		</div>		
	</c:forEach>
</div>

<c:forEach var="sTest" items="${m.sExam.sTests}">
	<c:forEach var="sQuestion" items="${sTest.sQuestions}">
		<div id="q_${sTest.testId}_${sQuestion.questionId}" class="q">
			<table class="qTitle">
				<tr>
					<th id="qThPrev_${sTest.testId}_${sQuestion.questionId}" class="qThPrev"></th>
					<th>${sTest.name} - ${sQuestion.number}</th>
					<th id="qThNext_${sTest.testId}_${sQuestion.questionId}" class="qThNext"></th>
				</tr>
			</table>
			<table class="qBody">
				<tbody>
					<tr>
						<td>
							${sQuestion.questionHtml}
							<table>
								<c:forEach var="sChoice" items="${sQuestion.sChoices}">
									<tr>
										<td>
											${sChoice.choiceCode})
										</td>
										<td>
											${sChoice.choiceHtml}
										</td>
									</tr>
								</c:forEach>
								<tr height="40px" valign="bottom">
									<td colspan="2">Correct Answer: <strong>${sQuestion.correctAnswer}</strong></td>
								</tr>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</c:forEach>
</c:forEach>
