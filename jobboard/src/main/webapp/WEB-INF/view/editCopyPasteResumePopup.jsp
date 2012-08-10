<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>

<jsp:include page="common/include.jsp" />

<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery(".megamenu").megamenu();
		
		 
		 $("#update").click(function(){
				
			//validate the required fields
				var resumeName = $.trim($("#resumeName").val());
				var resumeId = $.trim($("#uploadResumeId").val());
				var jobTitle = $.trim($("#desiredJobTitle").val());
				var workAuth = $.trim($("#workAuthorizationUS option:selected").text());
		
				if (resumeName != null && resumeName != ""
					&& jobTitle != null	&& jobTitle != "" && workAuth != "Select"
					&& workAuth != null	&& workAuth != ""){
						$("#errorMsg").html("");
						//validate number of resumes
						//validate if resume name already exist in db
						$.ajax({url : getBaseURL()+ "/jobSeekerResume/validateCreateResumePopUp.html?resumeName="+ resumeName+"&resumeId="+resumeId,
							type: "GET",
							success : function(data) {
								if (data.maxResume != null) {
										$("#errorMsg").html("<span style='color:red'>"+ data.maxResume+ "</span>");
									} else if (data.duplicateResume != null) {
										$("#errorMsg").append("<br/><span style='color:red'>"+ data.duplicateResume+ "</span>");
									} else {
										$("form").attr("action",getBaseURL()+ "jobSeekerResume/updateCopyPasteResume.html");
										$("form").submit();
									}
								},
							error : function(response) {
								alert("Server Error : "+ response.status);
								},
							complete : function() {
								
							}
						});
				} else {
					$("#errorMsg").html("<span style='color:red'>Please enter the required parameters.</span>");
				}
			 });
	});	 
</script>
</head>
<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>Create Or Upload My New Resume</h2>
			<img src="../resources/images/Close.png"
				width="19" height="19" alt="Close" onclick="parent.$.nmTop().close();">
		</div>

		<div class="popUpContainerWrapper">
			<form:form method="post" action="updateCopyPasteResume.html" commandName="createResume" id="copyPastResume" enctype="multipart/form-data">
				<div id="errorMsg">
				</div>
				<div class="rowEvenNewSpacing">
					<form:input type="hidden" path="uploadResumeId" />
					<form:input type="hidden" path="resumeType" />
					<span class="lableText4">Resume Name:</span>
					<form:input path="resumeName" class="job_seeker_password textBox2" />
					<span class="required">(Required)</span>
					<form:errors path="resumeName" />
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText4">Desired job title:</span>
					<!-- <input type="text" name="lastname2" class="job_seeker_password textBox2" /><span class="required">(Required)</span> -->
					<form:input path="desiredJobTitle"
						class="job_seeker_password textBox2" />
					<span class="required">(Required)</span>
					<form:errors path="desiredJobTitle" />

				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText4">Desired Employment Type:</span>

					<form:select class="jb_input3 jb_input_width3 marginTop0"
						path="desiredEmploymentType" items="${employmentType}"
						itemValue="optionValue" itemLabel="optionValue" />
					<form:errors path="desiredEmploymentType" />

				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText4">U.S. Work Authorization:</span>

					<form:select class="jb_input3 marginTop0 width350"
						style="width: auto" path="workAuthorizationUS"
						items="${workAuthUS}" itemValue="optionValue"
						itemLabel="optionValue" />
					<form:errors path="workAuthorizationUS" />
					<span class="required">(Required)</span>

				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText4"> Willing to relocate:</span>
					<div class="redioButtonHolderWidth marginTop5">
						<form:radiobuttons path="willingToRelocate" items="${relocate}"
							itemValue="optionValue" itemLabel="optionValue" />
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
					<div class="redioButtonHolderWidth marginTop5">
						<form:radiobuttons path="resumeVisibility"
							items="${resumeVisibility}" itemValue="visibilityId"
							itemLabel="visibilityName" />
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
				<div class="rowEvenNewSpacing">
					<span class="lableText4 TextAlignL">Paste Resume:</span>
					<div class="clearfix"></div>
					<form:textarea path="resumeText"
						class="textareaBoxCResume width615 Height255 marginTop5 "
						cols="45" rows="3"></form:textarea>
				</div>
				<div class="rowEvenNewSpacing marginTop10 paddingBottom10">
					<span class="floatLeft marginTop10"> <a id="update" href="#"
						class="btn_sm orange">Update</a> <a href="/jobboard/jobSeekerResume/manageResume.html" class="nyroModal btn_sm orange">Cancel</a></span>
				</div>
				<div class="clearfix"></div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>