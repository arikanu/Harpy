<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url value="/css/exam/m.exams.css" var="mExamsCss" />
	<link rel="stylesheet" href="${mExamsCss}">

<c:choose>
	<c:when test="${m.exams != null}">
		<div class="exams">
			<table width="100%">
				<tbody>
					<tr>
						<td>Tarih</td>			
						<td>Test Sayisi</td>
						<td>Sure</td>
						<td>Soru Sayisi</td>
					</tr>
					<c:forEach var="exam" items="${m.exams}">
						<tr onclick="location.href='history/${exam.examId}'">
							<td width="25%">${exam.generateStrDate()}</td>				
							<td width="25%">${exam.getTests().size()}</td>
							<td width="25%">${exam.duration}</td>
							<td width="25%">${exam.nbOfQuestions}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:when>
</c:choose>