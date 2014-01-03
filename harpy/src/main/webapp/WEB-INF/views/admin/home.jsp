<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
	<head>
		<title>Home</title>		
		<spring:url value="/css/style.css" var="styleCss" />
		<link rel="stylesheet" href="${styleCss}">
		
		
		
	<script>
	function showonlyone(thechosenone) {
	    var newboxes = document.getElementsByTagName("div");
	        for(var x=0; x<newboxes.length; x++) {
	            name = newboxes[x].getAttribute("class");
	            if (name == 'newboxes') {
	                if (newboxes[x].id == thechosenone) {
	                newboxes[x].style.display = 'block';
	            	}
		            else {
	                	newboxes[x].style.display = 'none';
		            }
	        	}
	    	}
	}
	function showQuestion(chosenQuestionId) {
		var questionDivs = document.getElementsByTagName("div");
			for(var x=0; x<questionDivs.length; x++) {
				className = questionDivs[x].getAttribute("class");
				if (className == "questionDiv") {
					if (questionDivs[x].id == chosenQuestionId) {
						questionDivs[x].style.display = 'block';
					}
					else {
						questionDivs[x].style.display = 'none';
					}
				}
			}
	}
	</script>		
		
		
		
		
	</head>
	<header>
		<jsp:include page="/WEB-INF/views/admin/header.jsp"></jsp:include>
	</header>
	<body>
		<h1>Admin's Home Page</h1>
		<p>Welcome ${user.firstName} ${user.lastName}</p>
		<sec:authorize access="authenticated">
			Authentication name = <sec:authentication property="name"/> <br />
			<a href="/hag/admin/exam/home">exam page</a> <br />
			<a href="<c:url value="/j_spring_security_logout" />" > LOGOUT</a>
		</sec:authorize>		
		<br/>
		<sec:authorize access="hasRole('ROLE_STDNT')">
			<br /><a href="/hag/student/home">student's home page</a>
		</sec:authorize>
		
		<p>BURASI YENI</p>
		<table>
		   <tr>
		      <td>
		         <div style="border: 1px solid blue; background-color: #99CCFF; padding: 5px;">
		            <a id="myHeader1" href="javascript:showonlyone('newboxes1');" >collapse</a>
		         </div>
		         <div class="newboxes" id="newboxes1" style="border: 1px solid black; background-color: #CCCCCC; display: block;padding: 5px;">Div #1</div>
		      </td>
		      <td>
		         <div style="border: 1px solid blue; background-color: #99CCFF; padding: 5px;">
		            <a id="myHeader2" href="javascript:showonlyone('newboxes2');" >collapse</a>
		         </div>
		         <div class="newboxes" id="newboxes2" style="border: 1px solid black; background-color: #CCCCCC; display: none;padding: 5px;">Div #2</div>
		      </td>
		      <td>
		         <div style="border: 1px solid blue; background-color: #99CCFF; padding: 5px;">
		            <a id="myHeader3" href="javascript:showonlyone('newboxes3');" >collapse</a>
		         </div>
		         <div class="newboxes" id="newboxes3" style="border: 1px solid black; background-color: #CCCCCC; display: none;padding: 5px;">Div #3</div>
		      </td>
		   </tr>
		</table>
		
		
		<div><a href="javascript:showQuestion('q1');">Q1</a></div>
		<div><a href="javascript:showQuestion('q2');">Q2</a></div>
		<div><a href="javascript:showQuestion('q3');">Q3</a></div>
		<div><a href="javascript:showQuestion('q4');">Q4</a></div>
		<div class="questionDiv" id="q1" style="border: 1px solid black; background-color: #CCCCCC; display: block;padding: 5px;">bu soru 1</div>
		<div class="questionDiv" id="q2" style="border: 1px solid black; background-color: #CCCCCC; display: none;padding: 5px;">bu soru 2</div>
		<div class="questionDiv" id="q3" style="border: 1px solid black; background-color: #CCCCCC; display: none;padding: 5px;">bu soru 3</div>
		<div class="questionDiv" id="q4" style="border: 1px solid black; background-color: #CCCCCC; display: none;padding: 5px;">bu soru 4</div>
		
		
	</body>
	<footer>
		<jsp:include page="/WEB-INF/views/admin/footer.jsp"></jsp:include>
	</footer>
</html>