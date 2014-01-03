<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
	<head>
		<title>Home</title>
	</head>
	
	<body>
		<h1>Student's Home Page</h1>
		<p>Welcome ${user.firstName} ${user.lastName}</p>
		<sec:authorize access="authenticated">
			<p>Authentication name = <sec:authentication property="name"/> </p> <br />
			<a href="<c:url value="/j_spring_security_logout" />" > LOGOUT</a>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_ADMN')">
			<br /><a href="/hag/admin/home">admin's home page</a>
		</sec:authorize>
	</body>
</html>