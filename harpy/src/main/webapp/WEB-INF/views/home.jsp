<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<title>Home</title>
		<spring:url value="/css/style.css" var="styleCss" />
			<link rel="stylesheet" href="${styleCss}">
		<spring:url value="/jquery/js/jquery-1.10.2.min.js" var="jqueryJs" />
			<script src="${jqueryJs}"></script>
		<spring:url value="/jquery/js/jquery-ui-1.10.3.custom.min.js" var="jqueryUiJs" />
			<script src="${jqueryUiJs}"></script>
	</head>
	<body>
		<h1>Login Page</h1>
		<form:form action="j_spring_security_check" method="post" commandName="m">
			<table><tbody>
				<tr>
					<td><label for="j_username">Email Address:</label></td>
					<td><form:input path="j_username"/>
				</tr>
				<tr>
					<td><label for="j_password">Password:</label></td>
					<td><form:password path="j_password"/>
				</tr>
				<tr>
					<td colspan="2"><input id="submit" name="submit" type="submit" value="login" /></td>
				</tr>
			</tbody></table>			
		</form:form>
	</body>
</html>