<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="resources/js/jquery.js"></script>
<script src="resources/js/jquery.simplemodal.js"></script>
<script src="resources/js/basic.js"></script>
<!-- Page styles -->
<link type='text/css' href="resources/css/demo.css" rel='stylesheet'
	media='screen' />

<!-- Contact Form CSS files -->
<link type='text/css' href="resources/css/basic.css" rel='stylesheet'
	media='screen' />

<script type="text/javascript">

$(document).ready(function()
   {
	$("#resumetype").change(function()
	 {
	     if($("#resumetype :selected").text() == "Upload"){
	    	 $("#uploadResume").show();
	    	 
	     }else{
	    	 $("#uploadResume").hide();
	     }
	 });
});

$(function() {
	$("#uploadResume").hide();
});
</script>

<title>Profile Management</title>
</head>
<body>
	<div id='container'>
		<div align="right">
			<a href="/jobboard" style="text-align: left;">Back to Job list</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<div id='content'>
			<div id='basic-modal'>
				<a href='#' class='basic'>Create Resume</a>
			</div>

			<!-- modal content -->
			<div id="basic-modal-content">
				<div><h3 style="font: bold; color: white;">Create or Upload Resume</h3><br></div>
				<form:form method="post" action="copyPasteResume.html" commandName="createResume" id="formtouse" enctype="multipart/form-data">
				
				<div><strong style="font: bold;">What type of resume would you like to create?</strong><br>
				<form:select path="resumeType" id="resumetype">
                     <form:option value="NONE" label="--- Select ---"/>
                     <form:options items="${resumeTypeList}" />
                </form:select>
				
				</div>
				<div id="uploadResume"><strong style="font: bold;">Upload Resume</strong><br><form:input path="fileData" id="image" type="file" />
				</div>
				<div><strong style="font: bold;">Resume Name</strong><br><form:input path="resume_name"  /></div>
				<div><strong style="font: bold;">Desired job title</strong><br><form:input path="desired_job_title" /></div>
				<div><strong style="font: bold;">Desired Employment Type</strong><br>
				<form:select path="desired_employment_type">
                     <form:option value="NONE" label="--- Select ---"/>
                     <form:options items="${employmentTypeList}" />
                </form:select>
				
				</div>
				<div><strong style="font: bold;">Work authorization for US</strong><br>
				<form:select path="work_authorization_US">
                     <form:option value="NONE" label="--- Select ---"/>
                     <form:options items="${workauthUSList}" />
                </form:select>
				
				</div>
				<div><strong style="font: bold;">Willing to relocate?</strong><br>
				<form:radiobutton path="willing_to_relocate" value="Y" />Yes 
				<form:radiobutton path="willing_to_relocate" value="N" />No
				</div>
				<div><strong style="font: bold;">Resume Visibility [<a href="/jobboard" style="text-align: left;">Tips</a>]</strong><br>
				<form:radiobutton path="resume_visibility" value="Pu" />Public 
				<form:radiobutton path="resume_visibility" value="PrF" />Private
				
				</div>
				<div style="text-align: left;">
					<input type="submit" value="create" /><input type="button" value="cancel" />
				</div>
             </form:form>

			</div>

			<!-- preload the images -->
			<div style='display: none'>
				<img src='resources/img/basic/x.png' alt='' />
			</div>
		</div>
	</div>
</body>
</html>