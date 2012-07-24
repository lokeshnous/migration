<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Post New Job</title>
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

<form:form method="Get" action="savenewjob.html" 
commandName="employerJobPostForm" enctype="multipart/form-data">
	<div id="page-wrap">		
		<div id="tabs">		
 
    <div id="fragment-1" class="ui-tabs-panel"> 
    
	<fieldset style="width:800;">
	 	<legend class="Header2">Post Job</legend>
	<table border="0" width="100%">
 		<tr>
			<td>Customer Number:</td>
			<td><form:input path="customerNo" disabled="true" /></td>
			
		</tr> 

		<tr>
			<td>Company Name:</td>			
			<td><form:input path="companyName" disabled="true" /></td>
		</tr>

 		<tr>
			<td>Display Company Name:</td>
			<td><form:input path="disCompanyName" /></td>
			<td><FONT color="red"><form:errors path="disCompanyName" /></FONT></td>
		</tr>

		<tr>
			<td>Job ID:</td>
 			<td><form:input path="jobId" /></td> 
 			<td><FONT color="red"><form:errors path="jobId" /></FONT></td>
		</tr> 
		<tr>
			<td>Job Title:</td>
			<td><form:input path="jobTitle"/></td> 
 			<td><FONT color="red"><form:errors path="jobTitle" /></FONT></td>
		</tr>
	</table>
	</fieldset>
	</div>

	<div id="fragment-2" class="ui-tabs-panel">
	<table width="100%">
	 <tr>
	 	<td>
	 	<fieldset style="width:800;">
	 	<legend class="Header2">Application Method</legend>
	  <table>
			<tr>
			<td>Apply-to Email:</td>
			<td><form:input path="applyEmail"/></td> 
 			<td><FONT color="red"><form:errors path="applyEmail" /></FONT></td>
		</tr>
		<tr>
			<td>Applicant Tracking System (ATS):</td>
			<td><form:input path="applyUrl" /></td>
			<td><form:errors path="applyUrl" cssClass="error" /></td>
		</tr>
    	<tr>
			<td>Apply-to URL:</td>
			<td><form:input path="atsUrl" /></td>
			<td><FONT color="red"><form:errors path="atsUrl" cssClass="error" /></FONT></td>
		</tr>
		<tr>
			<td>Job City:</td>
			<td><form:input path="jobCity" /></td>
			<td><FONT color="red"><form:errors path="jobCity"  /></FONT></td>
		</tr>
		<tr>
			<td>Job State/Province:</td>
			
			<td><form:select path="jobState">
				<form:option value="0" label="Select" />
				<form:options items="${stateList}" itemValue="stateId" itemLabel="stateValue" />
				</form:select>
			</td>
			<td><FONT color="red"><form:errors path="jobState" /></FONT></td>
		</tr>
		<tr>
			<td>Job Country:</td>
			
			<td><form:select path="jobCountry">
				<form:option value="0" label="Select" />
				<form:option value="US" label="US" />
				</form:select>
			</td>
		<td><FONT color="red"><form:errors path="jobCountry"  /></FONT></td>
		</tr>
		<tr>
			<td>Job Zip Code:</td>
			<td><form:input path="jobZip" /></td>
			<td><FONT color="red"><form:errors path="jobZip" /></FONT></td>
		</tr>
		<tr>
		
			<td>Select Employment Type:</td>
			
			<td><form:select path="empTypeId">
				<form:option value="0" label="Select" />
				<form:options items="${empTypeList}" itemValue="employmentTypeId" itemLabel="employmentTypeValue" />
				</form:select>
			</td> 
			<td><FONT color="red"><form:errors path="empTypeId" /></FONT></td>
		</tr>
		<tr>
			<td>Required Skills:</td>
			<td><form:input path="reqSkills" /></td>
			<td><FONT color="red"><form:errors path="reqSkills" /></FONT></td>
		</tr>
		<tr>
			<td>Job Description:</td>
			<td><form:textarea path="jobDesc" rows="10" cols="60"/></td>
			<td><FONT color="red"><form:errors path="jobDesc" /></FONT></td>
		</tr> 
		<tr>
			<td>Tracking Pixel:</td>
			<td><form:input path="trackingPixel" /></td>
			<td><FONT color="red"><form:errors path="trackingPixel" /></FONT></td>
		</tr>
		
		<tr>
		
			<td>Select Job Posting Branding Template: </td>
			
			<td><form:select path="jpBrdTemp">
				<form:option value="0" label="Select" />
				</form:select>
			</td> 
			<td><FONT color="red"><form:errors path="jpBrdTemp" /></FONT></td>
		</tr>
		
		<tr>
		
			<td>Auto Renew:</td>
			
			<td><form:radiobutton path="autoRenew" value="Y" />Yes <form:radiobutton
					path="autoRenew" value="F" />No</td>
			<td><FONT color="red"><form:errors path="autoRenew" /></FONT></td>
		</tr>
		
		<tr>
		<td><input value="Post Job Now" type="submit" ></td><td><input value="Schedule Job" type="button"></td><td><input value="Save as Draft" type="button"></td>
		</tr>
     </table>
     </fieldset>
     </td>
    </tr>
    
    
 </table>
  </div>	
  </div>
  </div>
  

</form:form>
</body>
</html>

