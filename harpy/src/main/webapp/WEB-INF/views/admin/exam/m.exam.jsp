<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url value="/jquery/js/jquery-1.10.2.min.js" var="jQueryMin" />
<script src="${jQueryMin}"></script>

<spring:url value="/jquery/js/exam/m.exam.js" var="examJs" />
<script src="${examJs}"></script>	
	
<c:choose>
	<c:when test="${m.exam != null}">
		<div class="tNav">
			<ul>
				<c:forEach var="test" items="${m.exam.tests}">
					<li class="notselected" id="tNavLi_${test.number}">
						${test.name}
					</li>					
				</c:forEach>
			</ul>
		</div>
		
		<div class="qNav">
			<ul>
				<c:forEach var="test" items="${m.exam.tests}">
					<c:forEach var="question" items="${test.questions}">
						<li class="hidden" id="qNavLi_${test.number}_${question.number}">
							${question.number}
						</li>
					</c:forEach>
				</c:forEach>
			</ul>
		</div>

		<div class="Questions">
			<c:forEach var="test" items="${m.exam.tests}">					
				<c:forEach var="question" items="${test.questions}">
					<div class="questionNS" id="q_${test.number}_${question.number}">					
						${question.questionHtml}
					</div>
				</c:forEach>
			</c:forEach>			
		</div>
	</c:when>
</c:choose>
