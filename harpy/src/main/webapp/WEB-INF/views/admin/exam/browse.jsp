<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Search Exam</title>
		<spring:url value="/css/style.css" var="styleCss" />
		<link rel="stylesheet" href="${styleCss}">
	</head>
	<header>
		<jsp:include page="/WEB-INF/views/admin/header.jsp"></jsp:include>
	</header>
	<body>
		selected master type id = ${m.examMasterTypeId}
		<form:form method="POST" commandName="m" action="browsePost">
			<p>Master Types</p>
			<c:forEach var="masterType" items="${m.examMasterTypes}">
				<form:radiobutton path="examMasterTypeId" value="${masterType.examMasterTypeId}" onchange="this.form.submit()"/>
				${masterType.code }: ${masterType.name } <br />
			</c:forEach>
			
			<p>Sub Types</p>
			size = ${m.examSubTypes.size() } <br />
			<c:forEach var="subType" items="${m.examSubTypes}">
				<form:radiobutton path="examSubTypeId" value="${subType.examSubTypeId}" onchange="this.form.submit()"/>				
				${subType.code }: ${subType.name } <br />
			</c:forEach>
			
			<p>Exams</p>
			size = ${m.exams.size() } <br />
			<jsp:include page="/WEB-INF/views/admin/exam/m.exams.jsp"></jsp:include>
			
			
		</form:form>
	</body>
	<footer>
		<jsp:include page="/WEB-INF/views/admin/footer.jsp"></jsp:include>
	</footer>
</html>