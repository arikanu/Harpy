<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<a href="/hag/admin/exam/readexam">Upload Json Exam</a> <br /> <br />
<a href="<c:url value="/j_spring_security_logout" />" > LOGOUT</a> <br />
<sec:authorize access="hasRole('ROLE_STUDENT')">
	<br /><a href="/hag/student/home">student's home page</a>
</sec:authorize>
<br /><a href="/hag/admin/home">admin's home page</a>