<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<script>		
		function showTest(testName) {
			var allDivs = document.getElementsByTagName("div");
				for(var x = 0; x < allDivs.length; x++) {
					className = allDivs[x].getAttribute("class");
					if (className == "questionList") {
						if (allDivs[x].id == testName) {
							allDivs[x].style.display = 'block';
						}
						else {
							allDivs[x].style.display = 'none';
						}
					}
				}
		}
		function showQuestion(questionId) {
			var allDivs = document.getElementsByTagName("div");
			for(var x = 0; x < allDivs.length; x++) {
				className = allDivs[x].getAttribute("class");
				if (className == "question") {
					if (allDivs[x].id == questionId) {
						allDivs[x].style.display = 'block';
					}
					else {
						allDivs[x].style.display = 'none';
					}
				}
			}
		}
		</script>
	</head>

	<body>
		<c:choose>
			<c:when test="${m.exam != null}">
				<div class="testList">						
					<c:forEach var="test" items="${m.exam.tests}">
						<a href="javascript:showTest('${test.name}');">${test.name}</a>
					</c:forEach>
				</div>
				<c:forEach var="test" items="${m.exam.tests}">
					<c:choose>
						<c:when test="${test.name == m.exam.getTests().get(0).getName()}">
							<div class="questionList" id="${test.name}" style="display: block;">
								<c:forEach var="question" items="${test.questions}">
									<a href="javascript:showQuestion('${test.name}_${question.number}');">${question.number}</a>
								</c:forEach>
							</div>
						</c:when>
						<c:otherwise>
							<div class="questionList" id="${test.name}" style="display: none;">
								<c:forEach var="question" items="${test.questions}">
									<a href="javascript:showQuestion('${test.name}_${question.number}');">${question.number}</a>
								</c:forEach>
							</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:forEach var="test" items="${m.exam.tests}">
					<c:forEach var="question" items="${test.questions}">
						<c:choose>
							<c:when test="${test.name == m.exam.getTests().get(0).getName()}">
								<c:choose>
									<c:when test="${question.number == m.exam.getTests().get(0).getQuestions().get(0).getNumber()}">
										<div class="question" id="${test.name}_${question.number}" style="display: block;">
											<p>${question.questionHtml}</p>
										</div>
									</c:when>
									<c:otherwise>
										<div class="question" id="${test.name}_${question.number}" style="display: none;">
											<p>${question.questionHtml}</p>
										</div>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<div class="question" id="${test.name}_${question.number}" style="display: none;">
									<p>${question.questionHtml}</p>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:forEach>
			</c:when>
		</c:choose>
	</body>
</html>