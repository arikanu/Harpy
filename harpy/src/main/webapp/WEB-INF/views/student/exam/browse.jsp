<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Sinavlar</h1>

<form:form method="POST" commandName="m" action="browsePost">
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
				
	<c:choose>
		<c:when test="${m.exams.size() > 0}">
			<br/>
			<div style="width: 100%;">
				<jsp:include page="/WEB-INF/views/partials/student/m/m.exams.jsp"></jsp:include>
			</div>
		</c:when>
	</c:choose>
</form:form>
