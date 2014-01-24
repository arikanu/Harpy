<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Sinavlar</h1>

<c:url value="/student/exam/browsed" var="browsed"/>
<form:form method="POST" commandName="m" action="${browsed}">
	<table>
		<c:forEach var="masterType" items="${m.examMasterTypes}">
			<tr><td>
				<form:radiobutton path="examMasterTypeId" value="${masterType.examMasterTypeId}" label="${masterType.name}" onchange="this.form.submit()"/>
			</td></tr>
			<c:choose>
				<c:when test="${m.examMasterTypeId == masterType.examMasterTypeId}">
					<tr><td style="padding-left: 50px;"><table>
						<c:forEach var="subType" items="${m.examSubTypes}">
							<tr><td>
								<form:radiobutton path="examSubTypeId" value="${subType.examSubTypeId}" label="${subType.name}" onchange="this.form.submit()"/>
							</td></tr>
						</c:forEach>
					</table></td></tr>
				</c:when>
			</c:choose>			
		</c:forEach>
	</table>	
</form:form>

<jsp:include page="/WEB-INF/views/student/m/exams/examsTable.jsp"></jsp:include>
