<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>
			${m.pageTitle}			
		</title>
		<spring:url value="/css/style.css" var="styleCss" />
			<link rel="stylesheet" href="${styleCss}">
		<spring:url value="/jquery/js/jquery-1.10.2.min.js" var="jqueryJs" />
			<script src="${jqueryJs}"></script>
		<spring:url value="/jquery/js/jquery-ui-1.10.3.custom.min.js" var="jqueryUiJs" />
			<script src="${jqueryUiJs}"></script>
	</head>
	
	<body>
		<table width="100%" height="100%">			
			<tr height="20px">
				<td colspan="3"">
					<tiles:insertAttribute name="top" ignore="true"/>
				</td>
			</tr>
			<tr valign="top">
				<td style="width: 10%;" valign="top" align="left">
					<tiles:insertAttribute name="left" ignore="true"/>						
				</td>
				<td style="width: 80%; padding: 0 20px 0 20px;" valign="top">
					<tiles:insertAttribute name="content" ignore="true"/>				
				</td>
				<td style="width: 10%;" valign="top" align="right">
					<tiles:insertAttribute name="right" ignore="true"/>
				</td>
			</tr>
			<tr height="20px">
				<td colspan="3">
					<tiles:insertAttribute name="bottom" ignore="true"/>
				</td>
			</tr>			
		</table>
	</body>
</html>