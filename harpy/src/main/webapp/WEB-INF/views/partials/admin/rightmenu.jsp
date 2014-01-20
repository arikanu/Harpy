<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url value="/jquery/js/partials/admin/rightmenu.js" var="rightmenuJs" />
	<script src="${rightmenuJs}"></script>
<spring:url value="/css/partials/admin/rightmenu.css" var="rightmenuCss" />
	<link rel="stylesheet" href="${rightmenuCss}">

<div id="rightmenu">	
	<h3>Home</h3>
	<div>
		<ul>
			<li>
				<a href="/hag/admin/home">Home</a><br/>
			</li>
		</ul>
	</div>
	<h3>Exam</h3>
	<div>
		<ul>
			<li>
				<a href="/hag/admin/exam/home">Home</a><br/>
			</li>
		</ul>
		<ul>
			<li>
				<a href="/hag/admin/exam/upload">Upload</a><br/>
			</li>
		</ul>
		<ul>
			<li>
				<a href="/hag/admin/exam/browse">Browse</a><br/>
			</li>
		</ul>
	</div>
</div>