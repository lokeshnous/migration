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
	
		 $("#resumeType").change(function() {
				var resumeType = $.trim($("#resumeType").val());
				switch(resumeType){
					case "ADVANCE Resume Builder":
						$("#resumeBuilder").click();
						break;
					case "Upload Existing Resume":
						$("#uploadResume").click();
						break;
					case "Copy and Paste":
						$("#copyPaste").click();
						break;
				}
		});
	
		$("#create").click(function() {
			//validate the required fields
			var resumeName = $.trim($("#resumeName").val());
			var jobTitle = $.trim($("#desiredJobTitle").val());
			var workAuth = $.trim($("#workAuthorizationUS option:selected").text());
	
			if (resumeName != null && resumeName != ""
				&& jobTitle != null	&& jobTitle != "" && workAuth != "Select"
				&& workAuth != null	&& workAuth != ""){
					$("#resumeErrorMsg").html("");
					//validate number of resumes
					//validate if resume name already exist in db
					$.ajax({url :"${pageContext.request.contextPath}/jobSeekerResume/validateCreateResumePopUp.html?resumeName="+ resumeName+"&resumeId=",
						  type: "GET",
						success : function(data) {
							if (data.maxResume != null) {
									$("#resumeErrorMsg").html("<span>"+ data.maxResume+ "</span>");
								} else if (data.duplicateResume != null) {
									$("#resumeErrorMsg").append("<span>"+ data.duplicateResume+ "</span>");
								} else {
									$("form").attr("action","${pageContext.request.contextPath}/jobSeekerResume/saveCreateResume.html");
									$("form").attr("method", "GET");
									$("#createResumeForm").submit();
								}
							},
						error : function(response) {
							alert("Server Error : "+ response.status);
							},
						complete : function() {
							
						}
					});
			} else {
				$("#resumeErrorMsg").html("<span>Please enter the required parameters.</span>");
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
			<img src="../resources/images/Close.png" class="nyroModalClose" title="Close"
				width="19" height="19" alt="">
		</div>

		<div class="popUpContainerWrapper">
			<form:form method="GET" action="saveCreateResume.html"
				commandName="createResume" id="createResumeForm"
				enctype="multipart/form-data">
				<div class="rowEvenNewSpacing">
					<div id="resumeErrorMsg" class="FormErrorDisplayText"></div>
				</div>
				<div class="rowEvenNewSpacing">
					<div class="splLableText">How would you
						like to create your resume?</div>
					<form:select class="jb_input3 jb_input_width3"
						path="resumeType" items="${resumeTypeList}"
						itemValue="optionValue" itemLabel="optionValue" />
					<span class="required">(Required)</span>

				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText4">Resume Name:</span>
					<!-- <input type="text" name="lastname" class="job_seeker_password textBox2" /><span class="required">(Required)</span> -->
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

					<form:select class="jb_input3 jb_input_width3"
						path="desiredEmploymentType" items="${employmentType}"
						itemValue="optionValue" itemLabel="optionValue" />
					<form:errors path="desiredEmploymentType" />

				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText4">U.S. Work Authorization:</span>

					<form:select class="jb_input3 width350"
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
				<div class="popUpButtonRow">
					<a id="create" href="#"
						class="btn_sm orange">Create</a> <a href="#"
						class="nyroModalClose btn_sm orange">Cancel</a>
				</div>
				<a id="resumeBuilder" href="<%=request.getContextPath()%>/jobSeekerResume/createResumePopUp.html?resumeType=ADVANCE Resume Builder" class="nyroModal"></a>
				<a id="uploadResume" href="<%=request.getContextPath()%>/jobSeekerResume/createResumePopUp.html?resumeType=Upload Existing Resume" class="nyroModal"></a>
				<a id="copyPaste" href="<%=request.getContextPath()%>/jobSeekerResume/createResumePopUp.html?resumeType=Copy and Paste" class="nyroModal"></a>
				<div class="clearfix"></div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>