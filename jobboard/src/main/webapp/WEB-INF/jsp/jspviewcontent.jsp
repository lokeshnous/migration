<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<br>
	<c:if test="${viewxml}">
		<tr>
			<td class="Header2" bgcolor="#CCCCCC" height="25" width="50%">&nbsp;Healthcare
				News</td>
		</tr>
		<tr>
			<td class="content"><ul>
					<c:forEach var="healthcarenew" items="${healthcarenew}">
						<li><a href="http://www.${healthcarenew.url}">${healthcarenew.content}</a></li>
					</c:forEach>
				</ul></td>
		</tr>
	</c:if>

	<tr>
		<td class="Header2" bgcolor="#CCCCCC" height="25" width="50%">&nbsp;Career
			Tools and Resources</td>
	</tr>
	<tr>
		<td class="content"><ul>
				<c:if test="${viewxml}">
					<c:forEach var="careerstoolresource" items="${careerstoolresource}">
						<li><a href="${careerstoolresource.url}">${careerstoolresource.name}</a>:
							${careerstoolresource.content}</li>
					</c:forEach>
				</c:if>
				<c:if test="${viewcareer}">
					<div style="float: left; width: 40%;">
					<c:forEach var="careerstoolresource" items="${careerstoolresource}">
						<li><a href="${careerstoolresource.url}">${careerstoolresource.name}</a>
						</li>
					</c:forEach>
					</div>
					<div style="float: right; width: 60%;">
					<li><span style="font: bold;">${careerstoolresourcename}</span></li>
					<li>${careerstoolresourcedescription}</li>
					</div>
				</c:if>

			</ul></td>
	</tr>


</body>
</html>