<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
	<c:when test="${m.exams != null}">
		<table>
			<thead>
				<tr>
					<th>Id</th>
					<th>Key</th>
					<th>Date</th>			
					<th>Nb. of Tests</th>
					<th>Duration</th>
					<th>Nb. of Questions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="exam" items="${m.exams}">
					<tr>
						<td>${exam.examId}</td>
						<td>${exam.key}</td>
						<td>${exam.getStrDate()}</td>				
						<td>${exam.getTests().size()}</td>
						<td>${exam.duration}</td>
						<td>${exam.nbOfQuestions}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>	
	</c:when>
</c:choose>