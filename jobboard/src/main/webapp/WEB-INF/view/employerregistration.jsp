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
    <script type="text/javascript">


</script> 
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


<form:form method="Post" action="saveEmployerProfile.html" 
commandName="employerRegistrationForm" enctype="multipart/form-data">
    <div id="fragment-1"> 	
	<table border="0" width="50%">
	 	<tr>
			<td>First Name :</td>
			<td><form:input path="firstName" /></td>
			<td><FONT color="red">[Mandatory]</FONT></td>
			<td><FONT color="red"><form:errors path="firstName" cssClass="error" /></FONT></td>
		</tr>
		<tr>
			<td>Middle Name :</td>
			<td><form:input path="middleName" /></td>
			<td><form:errors path="middleName" cssClass="error" /></td>
		</tr>
    	<tr>
			<td>Last Name :</td>
			<td><form:input path="lastName" /></td>
			<td><FONT color="red">[Mandatory]</FONT></td>
			<td><FONT color="red"><form:errors path="lastName" cssClass="error" /></FONT></td>
		</tr>
	
 		<tr>
			<td>Email Address:</td>
			<td><form:input path="emailId" /></td>
			<td><FONT color="red">[Mandatory]</FONT></td>
			<td><FONT color="red"><form:errors path="emailId" /></FONT></td>
		</tr> 
		
		<tr>
			<td>Confirm Email Address:</td>
			<td><form:input path="confirmEmailId" /></td>
			<td><FONT color="red">[Mandatory]</FONT></td>
			<td><FONT color="red"><form:errors path="confirmEmailId" /></FONT></td>
		</tr> 
		
		<tr>
			<td>Position Title:</td>
			<td><form:input path="positionTitle" /></td>
			<td><FONT color="red">[Mandatory]</FONT></td>
			<td><FONT color="red"><form:errors path="positionTitle" /></FONT></td>
		</tr> 

		<tr>
			<td>Password:</td>			
			<td><form:password path="password" /></td>
			<td><FONT color="red">[Mandatory]</FONT></td>
			<td><FONT color="red"><form:errors path="password" /></FONT></td>
		</tr>

 		<tr>
			<td>Confirm Password:</td>
			<td><form:password path="confirmPassword" /></td>
			<td><FONT color="red">[Mandatory]</FONT></td>
			<td><FONT color="red"><form:errors path="confirmPassword" /></FONT></td>
		</tr>
		
		<tr>
			<td>Company :</td>
			<td><form:input path="company" /></td>
			<td><FONT color="red">[Mandatory]</FONT></td>
			<td><FONT color="red"><form:errors path="company" /></FONT></td>
		</tr> 

		<tr>
			<td>Phone :</td>
			<td><form:input path="phoneNo" /></td>
			<td><FONT color="red"><form:errors path="phoneNo" cssClass="error" /></FONT></td>
		</tr> 
		
		<tr>
			<td>Mobile No :</td>
			<td><form:input path="mobileNo" /></td>
			<td><FONT color="red"><form:errors path="mobileNo" cssClass="error" /></FONT></td>
		</tr> 
		
		<tr>
			<td>Street :</td>
			<td><form:input path="street" /></td>
			<td><FONT color="red">[Mandatory]</FONT></td>
			<td><FONT color="red"><form:errors path="street" cssClass="error" /></FONT></td>
		</tr>
		
		<tr>
			<td>City :</td>
			<td><form:input path="city" /></td>
			<td><FONT color="red">[Mandatory]</FONT></td>
			<td><FONT color="red"><form:errors path="city" cssClass="error" /></FONT></td>
		</tr>
		
		<tr>
			<td>State/Province :</td>
			<td><form:input path="state" /></td>
			<td><FONT color="red">[Mandatory]</FONT></td>
			<td><FONT color="red"><form:errors path="state" cssClass="error" /></FONT></td>
		</tr>
		
		<tr>
			<td>Country :</td>
			
			<td><form:select path="country">
				<form:option value="0" label="Select" />
				<form:options items="${countryList}" itemValue="countryId" itemLabel="countryValue" />
				</form:select>
			</td>
			<td><FONT color="red">[Mandatory]</FONT></td>
			<td><FONT color="red"><form:errors path="country" cssClass="error" /></FONT></td>
		</tr>
		
		<tr>
			<td>Zip Code :</td>
			<td><form:input path="postalCode" /></td>
			<td><FONT color="red">[Mandatory]</FONT></td>
			<td><FONT color="red"><form:errors path="postalCode" cssClass="error" /></FONT></td>
		</tr>
		
		<tr>
<%--			<td>Security Check:</td>
			<td><FONT color="red">[Mandatory]</FONT></td>
 			<td><form:password path="retypepassword" /></td>
			<td><FONT color="red"><form:errors path="retypepassword" /></FONT></td> --%>
		</tr>
		
		<tr>
			<input type="submit" value="save" />
		</tr>
		
	</table>
	</div>
	
</form:form>
</body>
</html>