<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:choose>
	<c:when test="${m.exam != null}">
		<div>
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
					<tr>
						<td>${m.exam.examId}</td>
						<td>${m.exam.key}</td>
						<td>${m.exam.getDate()}</td>
						<td>${m.exam.getTests().size()}</td>
						<td>${m.exam.duration}</td>
						<td>${m.exam.nbOfQuestions}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</c:when>
</c:choose>