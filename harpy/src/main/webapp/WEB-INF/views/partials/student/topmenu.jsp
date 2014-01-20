<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/css/partials/student/topmenu.css" var="topMenuCss" />
	<link rel="stylesheet" href="${topMenuCss}">
<spring:url value="/jquery/js/partials/student/topmenu.js" var="topMenuJs" />
	<script src="${topMenuJs}"></script>

<div id="sse1">
	<div id="sses1">
		<ul>
			<li><a href="/hag/student/home">Home</a></li>
			<li><a href="/hag/student/exam/home">Exam</a></li>
		</ul>
	</div>
</div>