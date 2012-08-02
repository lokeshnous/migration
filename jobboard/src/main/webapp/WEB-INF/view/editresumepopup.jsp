<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>

<!-- STYLESHEETS -->
<link href="../resources/css/JB.css" rel="stylesheet" type="text/css" />
<link href="../resources/css/jquery.megamenu.css" rel="stylesheet" type="text/css" />
<link href="../resources/css/SliderStyles.css" rel="stylesheet"	type="text/css">

<!--[if IE]>
	<link href="stylesheets/ie.css" rel="stylesheet" type="text/css">
<![endif]-->


<!-- JAVASCRIPT FILES -->
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="javascripts/jquery.cycle.all.min.js"></script>
<script type="text/javascript" src="javascripts/slider.js"></script>
<script type="text/javascript" src="javascripts/jquery.megamenu.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery(".megamenu").megamenu();
		
	 $("#update").click(function(){
		
		var resumeName = $.trim($("#resume_name").val());
		var jobTitle = $.trim($("#desired_job_title").val());
		var workAuth = $.trim($("#work_authorization_US option:selected").text());
		debugger; 
		if(resumeName != null && resumeName !="" && jobTitle != null && jobTitle != "" && workAuth !="Select" && workAuth != null && workAuth != ""){
			$("#errorMsg").html("");
			$("form").attr("action", getBaseURL()+"jobSeekerResume/updateResumePopup.html");
			$("form").submit();
		} 
		else{
			 $("#errorMsg").html("<span style='color:red'>Please enter the required parameters.</span>");
		}
	 });
	 
	});
	function MM_jumpMenu(targ, selObj, restore) { //v3.0
		eval(targ + ".location='" + selObj.options[selObj.selectedIndex].value
				+ "'");
		if (restore)
			selObj.selectedIndex = 0;
	}
</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>Create Or Upload My New Resume</h2>
			<img src="../resources/images/Close.png" width="19" height="19"
				alt="" onclick="parent.$.nmTop().close();">
		</div>

		<div class="popUpContainerWrapper">
			<form:form method="get" action="updateResumePopup.html" id="editResumeForm" commandName="createResume" enctype="multipart/form-data" >
				<div id="errorMsg">
					
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText4">Resume Name:</span>
					<form:input type="hidden" path="uploadResumeId" />
					<form:input type="hidden" path="resumeType" />
					<form:input type="text"	path="resume_name" class="job_seeker_password textBox2"/>
					<span class="required">(Required)</span>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText4">Desired job title:</span> 
					<form:input type="text" path="desired_job_title" class="job_seeker_password textBox2"/>
					<span class="required">(Required)</span>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText4">Desired Employment Type:</span> 
					
						<form:select class="jb_input3 jb_input_width3 marginTop0" name="select9" 
							path="desired_employment_type" items="${employmentType}" 
							itemValue="optionId" itemLabel="optionName" />
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText4">U.S. Work Authorization:</span> 
					<form:select class="jb_input3 marginTop0 width350" name="select3" style="width: auto"
							path="work_authorization_US" items="${workAuthUS}" itemValue="optionId" itemLabel="optionName" />
					<span class="required">(Required)</span>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText4"> Willing to relocate:</span>
					<!-- <div class="redioButtonHolderWidth marginTop5">
						<label> <input name="RadioGroup10" type="radio"
							id="RadioGroup1_0" value="radio" checked> Yes
						</label>
					</div>

					<div class="redioButtonHolderWidth marginTop5">
						<label> <input type="radio" name="RadioGroup10"
							value="radio" id="RadioGroup1_0"> No
						</label>
					</div> -->
					<div class="redioButtonHolderWidth marginTop5">
						<form:radiobuttons path="willing_to_relocate" items="${relocate}" itemValue="optionId" itemLabel="optionName" />
					</div>
					<div class="toolTip marginTop8">
						<span class="classic">Select 'Yes' to let potential
							employers know you're willing to move to a new location for the
							right job opportunity.</span>
					</div>
					<div class="toolTipBefore">
						<span class="required">(Required)</span>
					</div>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText4">Resume Visibility:</span>
					<!-- <div class="redioButtonHolderWidth marginTop5">
						<label> <input name="RadioGroup1" type="radio"
							id="RadioGroup1_2" value="radio" checked> Public
						</label>
					</div>
					<div class="redioButtonHolderWidth marginTop5">
						<label> <input type="radio" name="RadioGroup1"
							value="radio" id="RadioGroup1_2"> Private
						</label>
					</div> -->
					<div class="redioButtonHolderWidth marginTop5">
						<form:radiobuttons path="resume_visibility" items="${resumeVisibility}" itemValue="optionId" itemLabel="optionName" />
					</div>
					<div class="toolTip marginTop8">
						<span class="classic">You can only have one resume visible
							to employers at a time, so select 'Private' if you already have a
							public resume saved to your profile. Otherwise, you may select
							'Public' and employers will be able to view your resume
							immediately.</span>
					</div>
					<div class="toolTipBefore">
						<span class="required">(Required)</span>
					</div>
				</div>
				<div class="rowEvenNewSpacing marginTop10 paddingBottom10">
					<span class="floatLeft marginTop10">
					<a id="update" href="#"	class="btn_sm orange">Update</a> 
						<a href="/jobboard/jobSeekerResume/manageResume.html" class="nyroModal btn_sm orange">Cancel</a></span>
				</div>
				<div class="clearfix"></div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>