<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>selected master type id = ${m.examMasterTypeId}</p>
<c:url value="/admin/exam/browsed" var="browsed"/>
<form:form method="POST" commandName="m" action="${browsed}">
	<p>Master Types</p>
	<c:forEach var="masterType" items="${m.examMasterTypes}">
		<form:radiobutton path="examMasterTypeId" value="${masterType.examMasterTypeId}" onchange="this.form.submit()"/>
		${masterType.code }: ${masterType.name } <br />
	</c:forEach>
	
	<p>Sub Types</p>
	<c:forEach var="subType" items="${m.examSubTypes}">
		<form:radiobutton path="examSubTypeId" value="${subType.examSubTypeId}" onchange="this.form.submit()"/>
		${subType.code }: ${subType.name } <br />
	</c:forEach>
				
	<c:choose>
		<c:when test="${m.exams.size() > 0}">
			<p>Exams</p>
			<jsp:include page="/WEB-INF/views/partials/admin/m/m.exams.jsp"></jsp:include>
		</c:when>
	</c:choose>
</form:form>