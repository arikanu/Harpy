<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<spring:url value="/jquery/js/exam/m.exam.js" var="mExamJs" />
	<script src="${mExamJs}"></script>
<spring:url value="/jquery/js/exam/m.exam.solve.js" var="mExamSolveJs" />
	<script src="${mExamSolveJs}"></script>
<spring:url value="/css/exam/m.exam.css" var="mExamCss" />
	<link rel="stylesheet" href="${mExamCss}">
<spring:url value="/css/exam/m.exam.solve.css" var="mExamSolveCss" />
	<link rel="stylesheet" href="${mExamSolveCss}">
<c:url value="/student/exam/solved" var="solved"/>


<jsp:include page="/WEB-INF/views/student/m/exam/solve/duration.jsp"/>

<form:form method="POST" commandName="m" action="${solved}" class="frmExamSolve" id="frmExamSolve">
	<form:hidden path="hiddenJson"/>
	
	<jsp:include page="/WEB-INF/views/student/m/exam/navigator.jsp"></jsp:include>
	
	<c:forEach var="test" items="${m.exam.tests}" varStatus="t">
		<c:forEach var="question" items="${test.questions}" varStatus="q">
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
								
								
								
								
								
								
								
								<%-- 
								
								<table>
									<c:choose>
										<c:when test="${sQuestion.viewType.equals('A_B_C_D_E')}">
											<c:forEach var="sChoice" items="${sQuestion.sChoices}" varStatus="c">
												<tr>
													<td>
														<form:checkbox
															path="sExam.sTests[${t.count-1}].sQuestions[${q.count-1}].sChoices[${c.count-1}].checked"
															label="${sChoice.choiceCode} ) ${sChoice.choiceHtml}"
															id="c_${sTest.testId}_${sQuestion.questionId}_${sChoice.choiceCode}"
															cssClass="choice"
														/>
													</td>
												</tr>
											</c:forEach>
										</c:when>
										<c:when test="${sQuestion.viewType.equals('ABCDE')}">
											<tr>
												<c:forEach var="sChoice" items="${sQuestion.sChoices}" varStatus="c">
													<td>
														<form:checkbox
															path="sExam.sTests[${t.count-1}].sQuestions[${q.count-1}].sChoices[${c.count-1}].checked"
															label="${sChoice.choiceCode} ) ${sChoice.choiceHtml}"
															id="c_${sTest.testId}_${sQuestion.questionId}_${sChoice.choiceCode}"
															cssClass="choice"
														/>
													</td>
												</c:forEach>
											</tr>
										</c:when>
										<c:when test="${sQuestion.viewType.equals('ABC_DE')}">
											<tr>
												<c:forEach var="sChoice" items="${sQuestion.sChoices}" varStatus="c">
													<c:choose>
														<c:when test="${c.count < 4}">
															<td>
																<form:checkbox
																	path="sExam.sTests[${t.count-1}].sQuestions[${q.count-1}].sChoices[${c.count-1}].checked"
																	label="${sChoice.choiceCode} ) ${sChoice.choiceHtml}"
																	id="c_${sTest.testId}_${sQuestion.questionId}_${sChoice.choiceCode}"
																	cssClass="choice"
																/>
															</td>
														</c:when>
													</c:choose>
												</c:forEach>
											</tr>
											<tr>
												<c:forEach var="sChoice" items="${sQuestion.sChoices}" varStatus="c">
													<c:choose>
														<c:when test="${c.count >= 4}">
															<td>
																<form:checkbox
																	path="sExam.sTests[${t.count-1}].sQuestions[${q.count-1}].sChoices[${c.count-1}].checked"
																	label="${sChoice.choiceCode} ) ${sChoice.choiceHtml}"
																	id="c_${sTest.testId}_${sQuestion.questionId}_${sChoice.choiceCode}"
																	cssClass="choice"
																/>
															</td>
														</c:when>
													</c:choose>
												</c:forEach>
											</tr>
										</c:when>
										<c:when test="${sQuestion.viewType.equals('AB_CDE')}">
											<tr>
												<c:forEach var="sChoice" items="${sQuestion.sChoices}" varStatus="c">
													<c:choose>
														<c:when test="${c.count < 3}">
															<td>
																<form:checkbox
																	path="sExam.sTests[${t.count-1}].sQuestions[${q.count-1}].sChoices[${c.count-1}].checked"
																	label="${sChoice.choiceCode} ) ${sChoice.choiceHtml}"
																	id="c_${sTest.testId}_${sQuestion.questionId}_${sChoice.choiceCode}"
																	cssClass="choice"
																/>
															</td>
														</c:when>
													</c:choose>
												</c:forEach>
											</tr>
											<tr>
												<c:forEach var="sChoice" items="${sQuestion.sChoices}" varStatus="c">
													<c:choose>
														<c:when test="${c.count >= 3}">
															<td>
																<form:checkbox
																	path="sExam.sTests[${t.count-1}].sQuestions[${q.count-1}].sChoices[${c.count-1}].checked"
																	label="${sChoice.choiceCode} ) ${sChoice.choiceHtml}"
																	id="c_${sTest.testId}_${sQuestion.questionId}_${sChoice.choiceCode}"
																	cssClass="choice"
																/>
															</td>
														</c:when>
													</c:choose>
												</c:forEach>
											</tr>
										</c:when>
										<c:when test="${sQuestion.viewType.equals('AB_CD_E')}">
											<tr>
												<c:forEach var="sChoice" items="${sQuestion.sChoices}" varStatus="c">
													<c:choose>
														<c:when test="${c.count < 3}">
															<td>
																<form:checkbox
																	path="sExam.sTests[${t.count-1}].sQuestions[${q.count-1}].sChoices[${c.count-1}].checked"
																	label="${sChoice.choiceCode} ) ${sChoice.choiceHtml}"
																	id="c_${sTest.testId}_${sQuestion.questionId}_${sChoice.choiceCode}"
																	cssClass="choice"
																/>
															</td>
														</c:when>
													</c:choose>
												</c:forEach>
											</tr>
											<tr>
												<c:forEach var="sChoice" items="${sQuestion.sChoices}" varStatus="c">
													<c:choose>
														<c:when test="${c.count >= 3 && c.count < 5}">
															<td>
																<form:checkbox
																	path="sExam.sTests[${t.count-1}].sQuestions[${q.count-1}].sChoices[${c.count-1}].checked"
																	label="${sChoice.choiceCode} ) ${sChoice.choiceHtml}"
																	id="c_${sTest.testId}_${sQuestion.questionId}_${sChoice.choiceCode}"
																	cssClass="choice"
																/>
															</td>
														</c:when>
													</c:choose>
												</c:forEach>
											</tr>
											<tr>
												<c:forEach var="sChoice" items="${sQuestion.sChoices}" varStatus="c">
													<c:choose>
														<c:when test="${c.count == 5}">
															<td>
																<form:checkbox
																	path="sExam.sTests[${t.count-1}].sQuestions[${q.count-1}].sChoices[${c.count-1}].checked"
																	label="${sChoice.choiceCode} ) ${sChoice.choiceHtml}"
																	id="c_${sTest.testId}_${sQuestion.questionId}_${sChoice.choiceCode}"
																	cssClass="choice"
																/>
															</td>
														</c:when>
													</c:choose>
												</c:forEach>
											</tr>
										</c:when>
										<c:when test="${sQuestion.viewType.equals('A_BC_DE')}">
											<tr>
												<c:forEach var="sChoice" items="${sQuestion.sChoices}" varStatus="c">
													<c:choose>
														<c:when test="${c.count < 2}">
															<td>
																<form:checkbox
																	path="sExam.sTests[${t.count-1}].sQuestions[${q.count-1}].sChoices[${c.count-1}].checked"
																	label="${sChoice.choiceCode} ) ${sChoice.choiceHtml}"
																	id="c_${sTest.testId}_${sQuestion.questionId}_${sChoice.choiceCode}"
																	cssClass="choice"
																/>
															</td>
														</c:when>
													</c:choose>
												</c:forEach>
											</tr>
											<tr>
												<c:forEach var="sChoice" items="${sQuestion.sChoices}" varStatus="c">
													<c:choose>
														<c:when test="${c.count >= 2 && c.count < 4}">
															<td>
																<form:checkbox
																	path="sExam.sTests[${t.count-1}].sQuestions[${q.count-1}].sChoices[${c.count-1}].checked"
																	label="${sChoice.choiceCode} ) ${sChoice.choiceHtml}"
																	id="c_${sTest.testId}_${sQuestion.questionId}_${sChoice.choiceCode}"
																	cssClass="choice"
																/>
															</td>
														</c:when>
													</c:choose>
												</c:forEach>
											</tr>
											<tr>
												<c:forEach var="sChoice" items="${sQuestion.sChoices}" varStatus="c">
													<c:choose>
														<c:when test="${c.count >= 4}">
															<td>
																<form:checkbox
																	path="sExam.sTests[${t.count-1}].sQuestions[${q.count-1}].sChoices[${c.count-1}].checked"
																	label="${sChoice.choiceCode} ) ${sChoice.choiceHtml}"
																	id="c_${sTest.testId}_${sQuestion.questionId}_${sChoice.choiceCode}"
																	cssClass="choice"
																/>
															</td>
														</c:when>
													</c:choose>
												</c:forEach>
											</tr>
										</c:when>
									</c:choose>
								</table>
								
								 --%>
								
								
								
								
								
								
								
								
								
								
								<table>
											<c:forEach var="choice" items="${question.choices}" varStatus="c">											
												<tr>
													<td>
														<form:radiobutton
															path="answers[${m.questionIndices[t.count-1][q.count-1]}]"
															label="${choice.choiceCode} ) ${choice.choiceHtml}"
															id="c_${test.testId}_${question.questionId}_${choice.choiceCode}"
															value="${c.count}"
															cssClass="choice"
															data-waschecked="false"
														/>
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
	<input type="submit" value="Sinavi Tamamla" class="btn1"/>
</form:form>