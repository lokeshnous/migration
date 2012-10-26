<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>JobSeekerRegistration</title>
<meta name="robots" content="noindex, follow"> 
	<link rel="stylesheet" href="../tabs.css" type="text/css" media="screen, projection"/>
	<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js" ></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.js" ></script>
	<script src="http://code.jquery.com/jquery-1.7.2.js"></script>
	<script src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
    <script type="text/javascript">
		$(function() {
			var $tabs = $('#tabs').tabs();	
			$(".ui-tabs-panel").each(function(i){	
			  var totalSize = $(".ui-tabs-panel").size() - 1;	
			  if (i != totalSize) {
			      next = i + 2;
		   		  $(this).append("<a href='#' class='next-tab mover' rel='" + next + "'>Next &#187;</a>");
			  }	  
			  if (i != 0) {
			      prev = i;
		   		  $(this).append("<a href='#' class='prev-tab mover' rel='" + prev + "'>&#171; Prev </a>");
			  }   		
			});	
			$('.next-tab, .prev-tab').click(function() { 
		           $tabs.tabs('select', $(this).attr("rel"));
		           return false;
		       });       
		});
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

<!-- <c:url var="viewProfileSettings" value="/viewJobSeekerProfile.html" /> -->
<a href="viewJobSeekerProfile.html">View Profile Settings</a>
<%-- <form:form method="Post" action="editJobSeekerProfile.html" 
commandName="jsProfileSettingsForm">

</form:form> --%>
<form:form method="Post" action="saveJobSeekerProfile.html" 
commandName="jobSeekerRegistrationForm" enctype="multipart/form-data">
	<div id="page-wrap">		
		<div id="tabs">		
    		<ul>
        		<li><a href="#fragment-1">Account Information</a></li>
        		<li><a href="#fragment-2">Your Information</a></li>
  			</ul>



    <div id="fragment-1" class="ui-tabs-panel"> 
	
	<table border="0" width="100%">
 		<tr>
			<td>Email Address:</td>
			<td><form:input path="emailId" /></td>
			<td><FONT color="red">[Mandatory]</FONT></td>
			<td><FONT color="red"><form:errors path="emailId" /></FONT></td>
		</tr> 

		<tr>
			<td>Password:</td>			
			<td><form:password path="password" /></td>
			<td><FONT color="red">[Mandatory]</FONT></td>
			<td><FONT color="red"><form:errors path="password" /></FONT></td>
		</tr>

 		<tr>
			<td>Re-type Password:</td>
			<td><form:password path="retypepassword" /></td>
			<td><FONT color="red">[Mandatory]</FONT></td>
			<td><FONT color="red"><form:errors path="retypepassword" /></FONT></td>
		</tr>

		<tr>
			<td>Resume Upload:</td>
 			<td><form:input path="fileData" id="image" type="file" /></td> 

		</tr> 
		<tr>
			<td>Security Check:</td>
			<td><FONT color="red">[Mandatory]</FONT></td>
<%-- 			<td><form:password path="retypepassword" /></td>
			<td><FONT color="red"><form:errors path="retypepassword" /></FONT></td> --%>
		</tr>
	</table>
	</div>

	<div id="fragment-2" class="ui-tabs-panel">
	<table width="100%">
	 <tr>
	 	<td>
	 	<fieldset style="width:800;">
	 	<legend class="Header2">Contact Information</legend>
	  <table>
<%-- 	  	 <tr>
			<td>First Name :</td>
			<td><form:input path="contactForm.firstName" /></td>
			<td><FONT color="red">[Mandatory]</FONT></td>
			<td><FONT color="red"><form:errors path="contactForm.firstName" cssClass="error" /></FONT></td>
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
			<td>Address :</td>
			<td><form:input path="addressLine1" /></td>
			<td><FONT color="red">[Mandatory]</FONT></td>
			<td><FONT color="red"><form:errors path="addressLine1" cssClass="error" /></FONT></td>
		</tr>
		<tr>
			<td/>
			<td><form:input path="addressLine2" /></td>
			<td><FONT color="red">[Mandatory]</FONT></td>
			<td><FONT color="red"><form:errors path="addressLine2" cssClass="error" /></FONT></td>
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
			<td>Postal Code :</td>
			<td><form:input path="postalCode" /></td>
			<td><FONT color="red">[Mandatory]</FONT></td>
			<td><FONT color="red"><form:errors path="postalCode" cssClass="error" /></FONT></td>
		</tr>
		<tr>
			<td>Phone :</td>
			<td><form:input path="phone" /></td>
			<td><FONT color="red"><form:errors path="phone" cssClass="error" /></FONT></td>
		</tr>  --%>
     </table>
     </fieldset>
     </td>
    </tr>
    	 <tr>
	 	<td>
	 	<fieldset>
	 	<legend class="Header2">Employment Information</legend>
          
      <table>
		<tr>
			<td>I'm Currently :</td>
			<td><form:input path="imCurrentlyIn" /></td>
			<td><form:errors path="imCurrentlyIn" cssClass="error" /></td>
		</tr>
     </table> 
     </fieldset>
     </td>
     </tr>
       <tr>
	 	<td>
		 <fieldset>
		 	  <legend class="Header2">Equal Opportunity / Affirmative Action</legend>
		      <table border="1"  height="30px">
				<tr>
					<td>Ethenticity :</td>
					<td><form:input path="ethenticity" /></td>
					<td><form:errors path="ethenticity" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Gender :</td>
					<td><form:input path="gender" /></td>
					<td><form:errors path="gender" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Veteran Status :</td>
					<td><form:input path="veteranStatus" /></td>
					<td><form:errors path="veteranStatus" cssClass="error" /></td>
				</tr>		
		     </table> 
	     </fieldset>
     </td>
	</tr>
	<tr>
	 	<td>
		 <fieldset>
		 	  <legend class="Header2">Subscriptions</legend>
		      <table border="1"  height="30px">
<%-- 				<tr>
					<td>Ethenticity :</td>
					<td><form:input path="ethenticity" /></td>
					<td><form:errors path="ethenticity" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Gender :</td>
					<td><form:input path="gender" /></td>
					<td><form:errors path="gender" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Veteran Status :</td>
					<td><form:input path="veteranStatus" /></td>
					<td><form:errors path="veteranStatus" cssClass="error" /></td>
				</tr> --%>		
		     </table> 
	     </fieldset>
     </td>
	</tr>
	<tr>
	 	<td>
		 <fieldset>
		 	  <legend class="Header2">Job Alerts</legend>
		      <table border="1"  height="30px">
<%-- 				<tr>
					<td>Ethenticity :</td>
					<td><form:input path="ethenticity" /></td>
					<td><form:errors path="ethenticity" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Gender :</td>
					<td><form:input path="gender" /></td>
					<td><form:errors path="gender" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Veteran Status :</td>
					<td><form:input path="veteranStatus" /></td>
					<td><form:errors path="veteranStatus" cssClass="error" /></td>
				</tr>	 --%>	
		     </table> 
	     </fieldset>
     </td>
	</tr>
     <table>
          	<tr>
			<td>
				<input type="submit" value="Register" />
			</td>
		</tr>
     </table>
       </table>	
  </div>	

</form:form>
</body>
</html>