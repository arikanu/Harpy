<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:url value="/admin/exam/uploaded" var="uploaded"/>
<c:url value="/admin/exam/uploadedDb" var="uploadedDb"/>


<p>${m.uploadedMessage}</p>
<form:form method="POST" commandName="m" action="${uploaded}" enctype="multipart/form-data">
	<p>
		Browse Exam-Json File: 
		<form:input type="file" path="jsonFile" onchange="this.form.submit()" />
	</p>
</form:form>


<form:form method="POST" commandName="m" action="${uploadedDb}" enctype="multipart/form-data">
	<form:hidden path="hiddenJson"/>
	<c:choose>
		<c:when test="${m.exam != null}">
			<p>An exam with the same key exists in DB: <strong>${m.existsInDb}</strong></p>
			<input type="submit" value="Upload Exam" name="upload" />
			
			<jsp:include page="/WEB-INF/views/partials/m/m.exam.meta.jsp"></jsp:include>
			<jsp:include page="/WEB-INF/views/partials/m/m.exam.jsp"></jsp:include>
			
		</c:when>
	</c:choose>
</form:form>



<%-- <form:form method="POST" commandName="m" action="${uploaded}" enctype="multipart/form-data"> --%>
	
	
<%-- 	<p>Browse Exam-Json File: <form:input type="file" path="jsonFile" onchange="this.form.submit()" /></p>			 --%>
<%-- 	<c:choose> --%>
<%-- 		<c:when test="${m.exam != null}"> --%>
<%-- 			<p>An exam with the same key exists in DB: <strong>${m.existsInDb}</strong></p> --%>
<!-- 			<input type="submit" value="Upload Examoooo" name="upload" /> -->
			
<%-- 			<jsp:include page="/WEB-INF/views/partials/m/m.exam.meta.jsp"></jsp:include> --%>
<%-- 			<jsp:include page="/WEB-INF/views/partials/m/m.exam.jsp"></jsp:include> --%>
			
<%-- 		</c:when> --%>
<%-- 	</c:choose> --%>
<%-- </form:form> --%>


