<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>
<!-- Common js files  -->
<script type="text/javascript" src="../resources/js/common/common.js"></script>
<script type="text/javascript">
tinyMCE.init({
	width : "550",
	height: "300",
	mode : "textareas",
	theme : "advanced",
	theme_advanced_buttons1 : "mybutton,bold,italic,underline,strikethrough,separator,|,justifyleft,justifycenter,justifyright, justifyfull,|,bullist,numlist,|,undo,redo,link,unlink",
	theme_advanced_buttons2 :"",
	theme_advanced_buttons3 :"",
	theme_advanced_toolbar_location : "top",
	theme_advanced_toolbar_align : "left",
	/* max_chars : 5000,
	max_chars_indicator : "characterCounter", */
	/*plugins : 'inlinepopups',*/
	plugins : "autolink,advlink",
	ontent_css : "css/mycontent.css"
});

	jQuery(document).ready(function() {
		jQuery(".megamenu").megamenu();
		var resVisibility = <%= request.getAttribute("resVisibility")%>
		if (resVisibility>0){
			$("#managePrivacy").show();
		}else{
			$("#managePrivacy").hide();
		}
		 
		 $("#update").click(function(){
			 selectAllElementsInDualList();
			 $(".popUpButtonRow").hide();
			//validate the required fields
				var resumeName = $.trim($("#resumeName").val());
				var resumeId = $.trim($("#uploadResumeId").val());
				var jobTitle = $.trim($("#desiredJobTitle").val());
				//var resumeText = $.trim($("#resumeText").val());
				var resumeText = tinyMCE.get('resumeText').getContent();
				$("#description").val(resumeText);
				var workAuth = $.trim($("#workAuthorizationUS option:selected").text());
		
				if (resumeName != null && resumeName != ""
					&& jobTitle != null	&& jobTitle != "" && workAuth != "Select"
					&& workAuth != null	&& workAuth != "" && resumeText != ""){
						$("#resumeErrorMsg").html("");
						//validate number of resumes
						//validate if resume name already exist in db
						$.ajax({url : "${pageContext.request.contextPath}/jobSeekerResume/validateCreateResumePopUp.html?resumeName="+ resumeName+"&resumeId="+resumeId,
							type: "GET",
							success : function(data) {
								if (data.maxResume != null) {
									$(".popUpButtonRow").show();
										$("#resumeErrorMsg").html("<span>"+ data.maxResume+ "</span>");
									} else if (data.duplicateResume != null) {
										$(".popUpButtonRow").show();
										$("#resumeErrorMsg").append("<span>"+ data.duplicateResume+ "</span>");
									} else {
										/* 
										$("form").attr("action","${pageContext.request.contextPath}/jobSeekerResume/updateCopyPasteResume.html");
										$("#copyPastResumeForm").submit(); */
										$.ajax({url : "${pageContext.request.contextPath}/jobSeekerResume/updateCopyPasteResume.html",
											data : $("#copyPastResumeForm").serialize(),
											type: "POST",
											success : function(data) {
												alert('Resume updated successfully');
												parent.$.nmTop().close();
												window.location.reload();
												},
											error : function(response) {
												$("#resumeErrorMsg").html("<span>"+ "Please reduce the size of Paste Resume text and try again."+ "</span>");
												var href = $('#topLocation').attr('href');
												location.href = href;
												$(".popUpButtonRow").show();
												//alert("Server Error : "+ response.status);
												//parent.$.nmTop().close();
												//window.location.reload();
												},
											complete : function() {
												
											}
										});
												
									}
								},
							error : function(response) {
								$(".popUpButtonRow").show();
								alert("Server Error : "+ response.status);
								},
							complete : function() {
								
							}
						});
				} else {
					$(".popUpButtonRow").show();
					$("#resumeErrorMsg").html("<span>Please enter the required fields.</span>");
				}
			 });
		
	});	 
	
	function refreshCall(){
		location.reload();
	}
	
	$('#Cancel').click(function(){	
		location.reload();
		parent.$.nmTop().close();
	});
</script>
</head>
<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>Edit Resume</h2>
			<img src="../resources/images/Close.png" title="Close" class="cursor"
				width="19" height="19" alt="Close" onclick="refreshCall();">
		</div>

		<div class="popUpContainerWrapper">
			<form:form method="post" action="updateCopyPasteResume.html" commandName="createResume" id="copyPastResumeForm" enctype="multipart/form-data">
				<div id="resumeErrorMsg" class="FormErrorDisplayText">
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
						<form:radiobuttons path="resumeVisibility" onclick="enableDisableCompanyPanel(this.id);"
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
					<div class="floatLeft">
					<%-- <form:textarea path="resumeText"
						class="textareaBoxCResume Height255 marginTop5 "
						cols="45" rows="3"></form:textarea> --%>

					<form:hidden path="description" id="description" />
					<form:textarea path="resumeText" name="resumeText"
						resize="none" rows="5" cols="20" /></div>

					<div class="toolTipBefore">
						<span class="required">(Required)</span>
					</div>
				</div>
				<div id="managePrivacy">
					<div class="rowEvenNewSpacing" id="searchCompany">
						<span class="lableText4">Company to block:</span>
						<form:input path="searchComapnyName"
							class="job_seeker_password textBox150" />
						<a id="searchCompanyName" href="#" class="btn_sm orange" onclick="searchByName('${pageContext.request.contextPath}/agency/getFacilityNamesList.html?term=');">Search</a>

					</div>

					<div class="rowEvenNewSpacing" id="selectCompany">
						<span class="lableText4">&nbsp;</span>
						<table style="border-style: none; cellspacing: 0; border: none;">
							<tr>
								<td colspan="3"
									style="border: none; font-weight: bold; bottom: -8">Company
									List</td>
								<td style="border: none; font-weight: bold; bottom: -8">
									Blocked Company</td>
							</tr>
							<tr>
								<td><form:select path="availableList" id="availableList"
										multiple="true" size="7" style="width:250px;">
										
									</form:select></td>
								<td width="3%" />
								<td width="7%" style="border: none">
									<button type="button" onclick="addAll();" style="">&gt;&gt;</button>
									<br>
									<button type="button" onclick="addAttribute();" style="">&gt;</button>
									<br>
									<button type="button" onclick="deleteAttribute();" style="">&lt;</button>
									<br>
									<button type="button" onclick="delAll();" style="">&lt;&lt;</button>
								</td>

								<td width="45%" style="border: none"><form:select
										path="selectedList" id="selectedeList" multiple="true" items="${blockedCompanies}" itemValue="optionId" itemLabel="optionName"
										size="7" style="width:250px;">
									</form:select></td>
						</table>
					</div>
				</div>
				<div class="popUpButtonRow">
					<a id="update" href="#"
						class="btn_sm orange">Save</a> <a id="Cancel" href="<%=request.getContextPath()%>/jobSeekerResume/manageResume.html" class="nyroModal btn_sm orange">Cancel</a>
				</div>
				<div class="clearfix"></div>
			</form:form>
		</div>
		<div class="clearfix"></div>
		<a id="topLocation" href="#jobSeekerRegister1"></a>
	</div>

</body>
</html>