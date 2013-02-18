<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Advance Health care job</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	</head>
	<body class="job_board">
	<c:choose>
	<c:when test="${status}">
		SiteMap File is generated successfully in server!.
	</c:when>
	<c:otherwise>
		SiteMap File is not generated!.
	</c:otherwise>
	</c:choose>
    </body>
</html>