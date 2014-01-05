<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<title>Read Exam</title>
		<spring:url value="/css/style.css" var="styleCss" />
		<link rel="stylesheet" href="${styleCss}">
	</head>
	<header>
		<jsp:include page="/WEB-INF/views/admin/header.jsp"></jsp:include>
	</header>
	
	<body>
		<p>${m.uploadedMessage}</p>
		<form:form method="POST" commandName="m" action="uploaded" enctype="multipart/form-data">
			<form:hidden path="examJson"/>
			<form:hidden path="examSubTypeCode"/>
			<p>Browse Exam-Json File: <form:input type="file" path="jsonFile" onchange="this.form.submit()" /></p>			
			<c:choose>
				<c:when test="${m.exam != null}">
					<p>An exam with the same key exists in DB: <strong>${m.existsInDb}</strong></p>
					<input type="submit" value="Upload Exam" name="upload" />
				</c:when>
			</c:choose>
			<jsp:include page="/WEB-INF/views/admin/exam/m.exam.meta.jsp"></jsp:include>
			<jsp:include page="/WEB-INF/views/admin/exam/m.exam.jsp"></jsp:include>			
		</form:form>
	</body>
	
	<footer>
		<jsp:include page="/WEB-INF/views/admin/footer.jsp"></jsp:include>
	</footer>
</html>