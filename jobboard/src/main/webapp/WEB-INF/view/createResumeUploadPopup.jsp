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
<!-- Common js files  -->
<script type="text/javascript" src="../resources/js/common/common.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".megamenu").megamenu();
		$("#managePrivacy").hide();
		var virusStatus=$('#virusFound').val();
		if(virusStatus=='true'){
			$("#resumeErrorMsg").html("The resume you tried to upload contains a virus. We attempted to remove the virus from your file, but we were unsuccessful. Please upload another file.");
		}
		var sizeInKB = 0;
		
		$("#resumeType").change(function() {
			var resumeType = $.trim($("#resumeType").val());
			switch (resumeType) {
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
		
		$('#fileData').bind('change', function() {
			  sizeInKB = Math.round(parseInt(this.files[0].size)/1024);
			  if(parseInt(sizeInKB) > 750){
				  alert("File size should not exceed more than 750KB.\nPlease try again.");
			  }
		});
		
		$("#create").click(function() {
			        selectAllElementsInDualList();
					//validate the required fields
					var resumeName = $.trim($("#resumeName").val());
					var jobTitle = $.trim($("#desiredJobTitle").val());
					var workAuth = $.trim($("#workAuthorizationUS option:selected").text());
					var chooseFile = $.trim($("#fileData").val());
					
					if (resumeName != null	&& resumeName != "" && 
							jobTitle != null	&& jobTitle != ""
							&& workAuth != "Select"	&& workAuth != null
							&& workAuth != "" && parseInt(sizeInKB) < 750 && chooseFile != null && chooseFile != "") {
						if(validateResume(chooseFile)){
							$("#resumeErrorMsg").html("");
							
							//validate number of resumes
							//validate if resume name already exist in db
							$.ajax({
										url : "${pageContext.request.contextPath}/jobSeekerResume/validateCreateResumePopUp.html?resumeName="
												+ resumeName
												+ "&resumeId=",
										type : "GET",
										success : function(data) {
											if (data.maxResume != null) {
												$("#resumeErrorMsg").html("<span>"+ data.maxResume+ "</span>");
											} else if (data.duplicateResume != null) {
												$("#resumeErrorMsg").append("<span>"+ data.duplicateResume+ "</span>");
											} else {
											 	$("form").attr("action","${pageContext.request.contextPath}/jobSeekerResume/createResumeUpload.html");
												$("#resumeUploadForm").submit();
											}
										},
										error : function(response) {
											alert("Server Error : "+ response.status);
										},
										complete : function() {
	
										}
							});
						}	
					}else if(parseInt(sizeInKB) > 750){
						$("#resumeErrorMsg").html("<span>File size should not exceed more than 750KB. Please try again.</span>");
					} else {
						$("#resumeErrorMsg")
								.html(
										"<span>Please enter the required fields.</span>");
					}
				});
		
		function validateResume(fileName)
		  {
			 if(fileName!=''){
				  var filename = fileName.toLowerCase();
				  if (!filename.match(/(\.doc|\.pdf|\.docx)$/)){
					  alert("Please upload resume with Doc,Docx or Pdf format only!");
					  return false;
				    }
				 }
			 return true;
		 }
		$("#searchCompanyName").click(function() {
			var IdData = new Array();
	    	var NameData = new Array();
	    	
			var empName = $("#searchComapnyName").val();
			$.ajax({
		        type: "GET",
		        url: "${pageContext.request.contextPath}/agency/getFacilityNamesList.html?term="+empName,
		        dataType: "json",							        
		        contentType: "application/json; charset=utf-8",
		        success: function(data) {							        	
		        								        	
		        	for (var x = 0; x < data.EmpList.length; x++) {
		        		
		               IdData.push(data.EmpList[x].ID);
		               NameData.push(data.EmpList[x].NAME);
		               //appends options 
		               var availableList = document.getElementById("availableList");
		               var exists = false; 
		               $('#availableList option').each(function(){
		            	   
		                 if ((this.text).toLowerCase() == (data.EmpList[x].NAME).toLowerCase()) {
		                	 exists=true;
		                 }
		               });
		               if(!exists){
		            	   availableList.options[availableList.options.length]=new Option(data.EmpList[x].NAME,data.EmpList[x].ID,false,false);
		               }
		               	               
		            }
		        	        					
		        },
		        error: function(XMLHttpRequest, textStatus, errorThrown) {
		           alert(textStatus);
		        }
		    });
			});
			
	
});
						
</script>
</head>
<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>Create Or Upload My New Resume</h2>
			<img src="<%= request.getContextPath() %>/resources/images/Close.png" class="nyroModalClose cursor" title="Close"
				width="19" height="19" alt="">
		</div>

		<div class="popUpContainerWrapper">
			<form:form method="post" action="createResumeUpload.html"
				commandName="createResume" id="resumeUploadForm"
				enctype="multipart/form-data">
				<form:hidden path="virusFound"/>
				<div class="rowEvenNewSpacing">
					<div id="resumeErrorMsg" class="FormErrorDisplayText"></div>
					<div class="clearfix"></div>
				</div>
				<div class="rowEvenNewSpacing">
					<div class="splLableText">How would you
						like to create your resume</div>
					<form:select class="jb_input3 jb_input_width3" path="resumeType"
						items="${resumeTypeList}" itemValue="optionValue"
						itemLabel="optionValue" />
					<span class="required">(Required)</span>

				</div>
				<!-- Choose file section -->
				<div class="rowEvenNewSpacing">
					<span class="lableText4">Upload Resume:</span>
					<form:input path="fileData" class="floatLeft" type="file" />
					<div class="toolTip marginTop8">
						<span class="classic">Select the resume you want to upload. Accepted file types are .doc, .docx and PDF only</span>
					</div>
					<span class="required paddingTop0 marginTop5">(Required)</span>
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

					<form:select class="jb_input3 width350" style="width: auto"
						path="workAuthorizationUS" items="${workAuthUS}"
						itemValue="optionValue" itemLabel="optionValue" />
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
					<div id="managePrivacy">
					<div class="rowEvenNewSpacing" id="searchCompany">
						<span class="lableText4">Company to block:</span>
						<form:input path="searchComapnyName"
							class="job_seeker_password textBox150" />
						<a id="searchCompanyName" href="#" class="btn_sm orange">Search</a>

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
										multiple="true" size="5" style="width:150px;">
										
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
										path="selectedList" id="selectedeList" multiple="true"
										size="5" style="width:150px;">
									</form:select></td>
						</table>
					</div>
				</div>
				<div class="popUpButtonRow">
					<a id="create" href="#" class="btn_sm orange">Create</a> 
					<a id="cancelButton" href="#" class="nyroModalClose btn_sm orange">Cancel</a>
				</div>
				<a id="resumeBuilder"
					href="<%=request.getContextPath()%>/jobSeekerResume/createResumePopUp.html?resumeType=ADVANCE Resume Builder"
					class="nyroModal"></a>
				<a id="uploadResume"
					href="<%=request.getContextPath()%>/jobSeekerResume/createResumePopUp.html?resumeType=Upload Existing Resume"
					class="nyroModal"></a>
				<a id="copyPaste"
					href="<%=request.getContextPath()%>/jobSeekerResume/createResumePopUp.html?resumeType=Copy and Paste"
					class="nyroModal"></a>
				<div class="clearfix"></div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>