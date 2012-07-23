<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Welcome Registration Successfully.</h3>
<table>
	<tr>
		<td>User Name :</td>
		<td><core:out value="${registration.userName}" /></td>
	</tr>
	<tr>
		<td>Password :</td>
		<td><core:out value="${registration.password}" /></td>
	</tr>
		<tr>
		<td>Confirm Password :</td>
		<td><core:out value="${registration.confirmPassword}" /></td>
	</tr>
	<tr>
		<td>Email :</td>
		<td><core:out value="${registration.email}" /></td>
	</tr>
		<tr>
		<td>First Name :</td>
		<td><core:out value="${registration.firstname}" /></td>
	</tr>
	<tr>
		<td>Last Name :</td>
		<td><core:out value="${registration.lastname}" /></td>
	</tr>
		<tr>
		<td>Address :</td>
		<td><core:out value="${registration.address}" /></td>
	</tr>
	<tr>
		<td>Postlcode :</td>
		<td><core:out value="${registration.postalcode}" /></td>
	</tr>
	<tr>
		<td>Phone No:</td>
		<td><core:out value="${registration.phone}" /></td>
	</tr>
</table>

</body>
</html>