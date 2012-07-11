<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
.Header3 {
    font-family: Verdana,Arial,Helvetica,sans-serif;
    font-size: 11px;
    font-weight: bold;
}
fieldset {
    border-color: #EEEEEE;
    padding: 5px;
}

</style>
</head>
<body>

<form:form method="Post" action="saveResumeBuilder.html" 
commandName="createResumeForm" enctype="multipart/form-data">
		
	<table border="0" width="100%">
 		<tr class="Header3">
			<td>Contact Info</td>
		</tr>
		
		<tr>
			<td>
				<table>
					<tr>
						<td>First Name :</td>
						<td><form:input path="contactInfoForm.firstName" /></td>
						<td><FONT color="red">[Mandatory]</FONT></td>
						<td><FONT color="red"><form:errors path="contactInfoForm.firstName" cssClass="error" /></FONT></td>						
					</tr>
					<tr>
						<td>Last Name :</td>
						<td><form:input path="contactInfoForm.lastName" /></td>
						<td><FONT color="red">[Mandatory]</FONT></td>
						<td><FONT color="red"><form:errors path="contactInfoForm.lastName" cssClass="error" /></FONT></td>
					</tr>
					<tr>
						<td>Address :</td>
						<td><form:input path="contactInfoForm.addressLine1" /></td>
						<td><FONT color="red">[Mandatory]</FONT></td>
						<td><FONT color="red"><form:errors path="contactInfoForm.addressLine1" cssClass="error" /></FONT></td>
					</tr>
					<tr>
						<td/>
						<td><form:input path="contactInfoForm.addressLine2" /></td>
						<td><FONT color="red">[Mandatory]</FONT></td>
						<td><FONT color="red"><form:errors path="contactInfoForm.addressLine2" cssClass="error" /></FONT></td>
					</tr>
					<tr>
						<td>Country :</td>
						<td><form:select path="contactInfoForm.country">
							<form:option value="0" label="Select" />
							<form:options items="${countryList}" itemValue="countryId" itemLabel="countryValue" />
							</form:select>
						</td>
					</tr>
					<tr>
						<td>City :</td>
						<td><form:input path="contactInfoForm.city" /></td>
						<td><FONT color="red">[Mandatory]</FONT></td>
						<td><FONT color="red"><form:errors path="contactInfoForm.city" cssClass="error" /></FONT></td>
					</tr>
					<tr>
						<td>State :</td>
						<td><form:input path="contactInfoForm.state" /></td>
						<td><FONT color="red">[Mandatory]</FONT></td>
						<td><FONT color="red"><form:errors path="contactInfoForm.state" cssClass="error" /></FONT></td>
					</tr>
					<tr>
						<td>Postal Code :</td>
						<td><form:input path="contactInfoForm.postalCode" /></td>
						<td><FONT color="red">[Mandatory]</FONT></td>
						<td><FONT color="red"><form:errors path="contactInfoForm.postalCode" cssClass="error" /></FONT></td>
					</tr>
<%-- 					<tr>
						<td>Phone :</td>
						<td><form:input path="contactForm.postalCode" /></td>
						<td><FONT color="red">[Mandatory]</FONT></td>
						<td><FONT color="red"><form:errors path="contactForm.postalCode" cssClass="error" /></FONT></td>
					</tr> --%>
				</table>							
			</td>
		</tr>
		<tr class="Header3">
			<td>Objective</td>
		</tr>
		<tr>
			<td>
				<table>	
					<tr>
						<td>Enter Objective :</td>
						<td><form:textarea path="objective" cols="15" rows="5"/></td>
						<td><FONT color="red">[Mandatory]</FONT></td>
						<td><FONT color="red"><form:errors path="objective" cssClass="error" /></FONT></td>
					</tr>
				</table>
			</td>
		</tr>	
		<tr class="Header3">
			<td>Certification</td>
		</tr>
		<tr>
			<td>
				<c:forEach items="${createResumeForm.listCertForm}" var="certForm" varStatus="status">
					<table>
						<td align="center" style="visibility:hidden">${status.count}</td>
						<tr>
							<td>Certification Name :</td>
							<td><input name="listCertForm[${status.index}].certificationName" value="${certForm.certificationName}"/></td>
							<td><FONT color="red">[Mandatory]</FONT></td>

						</tr>
 						<tr>
							<td>Institute Name :</td>
							<td><input name="listCertForm[${status.index}].instituteName" value="${certForm.instituteName}"/></td>
						</tr>
						<tr >
							<td>Summary:</td>
							<td><input   name="listCertForm[${status.index}].summary" value="${certForm.summary}" /></td>
						</tr> 
						<br/>
						<br/>
						<br/>
						<tr>
							<td/>
							<td>2000 characters remaining</td>
						</tr>
					</table>
				</c:forEach>							
			</td>
		</tr>		
		<tr>
			<td>
				<input type="submit" value="Save" />
			</td>
		</tr>
		
	</table>
</form:form>
</body>
</html>