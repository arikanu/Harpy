<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

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