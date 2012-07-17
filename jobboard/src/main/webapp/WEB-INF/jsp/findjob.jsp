<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>JobSeekerRegistration</title>
	<link rel="stylesheet" href="../tabs.css" type="text/css" media="screen, projection"/>
	<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js" ></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.js" ></script>
	<script src="http://code.jquery.com/jquery-1.7.2.js"></script>
	<script src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
     
<style type="text/css">
.Header2 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-weight: bold;
	font-size: 12px;
}

fieldset {
    border-color: #EEEEEE;
    padding: 5px;
}

</style>
</head>
<body>

<form:form method="Post" action="findJobSearch.html" 
	commandName="jobSearchResultForm">
<form:input path="searchString" />
<input type="submit" value="Find Jobs" />
</form:form>
	

</body>
</html>