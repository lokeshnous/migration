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
	<tr>
		<td class="content"><ul>
				<c:if test="${copyhtml}">
				Copied to local now you can view html data.
				</c:if>
				<c:if test="${viewhtml}">

					<div style="float: left; width: 40%;">
						<tr>
							<td class="Header2" bgcolor="#CCCCCC" height="25" width="50%">&nbsp;Career
								Tools and Resources</td>
						</tr>
						${careerstoolresource}
					</div>

					<div style="float: right; width: 60%;">
						<tr>
							<td class="Header2" bgcolor="#CCCCCC" height="25" width="50%">&nbsp;Healthcare
								news</td>
						</tr>
						${healthcarenew}
					</div>

				</c:if>

			</ul></td>
	</tr>


</body>
</html>