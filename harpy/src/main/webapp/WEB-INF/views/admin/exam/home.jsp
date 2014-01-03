<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<title>Exam Page</title>
		<spring:url value="/css/style.css" var="styleCss" />
		<link rel="stylesheet" href="${styleCss}">
	</head>
	
	<header>
		<jsp:include page="/WEB-INF/views/admin/header.jsp"></jsp:include>
	</header>
	
	<body>
		
		<h1>Admin's Exam Page</h1>
		<a href="/hag/admin/exam/readexam">Upload Json Exam</a> <br /> <br />
		<a href="<c:url value="/j_spring_security_logout" />" > LOGOUT</a> <br />
		<sec:authorize access="hasRole('ROLE_STUDENT')">
			<br /><a href="/hag/student/home">student's home page</a>
		</sec:authorize>
		<br /><a href="/hag/admin/home">admin's home page</a>
		
		
		
		
	</body>
	<footer>
		<jsp:include page="/WEB-INF/views/admin/footer.jsp"></jsp:include>
	</footer>
</html>