<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/css/partials/student/footer.css" var="footerCss" />
	<link rel="stylesheet" href="${footerCss}">
<spring:url value="/jquery/js/partials/student/footer.js" var="footerJs" />
	<script src="${footerJs}"></script>

<div id="footer">
	<div id="footerDiv">
		<ul>
			<li>
				<a href="<c:url value="/j_spring_security_logout" />">LOGOUT</a>
			</li>
		</ul>
	</div>
</div>