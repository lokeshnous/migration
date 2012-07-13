<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript">
	function go(url) {
		window.location = url;
	}

	function deleteContact(url) {
		var isOK = confirm("Are you sure to delete?");
		if (isOK) {
			go(url);
		}
	}
</script>



</head>
<body>
	<h3>Manage Featured Employer Profile</h3>


	<input value="Save"
		onclick="javascript:go('saveemployerprofile.html');" type="button">

	<input value="Cancel"
		onclick="javascript:cancel('cancelemployerprofile.html');"
		type="button">


</body>
</html>