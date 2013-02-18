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
	jQuery(document).ready(function() {
		jQuery(".megamenu").megamenu();
		 var sizeInKB = 0;
		 $("#downloadResume").click(function() {
		 	var resumeId = $("#uploadResumeId").val();
			$("form").attr("action", "${pageContext.request.contextPath}/jobSeekerResume/downloadResume.html?resumeId="+resumeId);
			$("form").attr("method","GET");
			$("form").submit();
		 });
		
		 $('#fileData').bind('change', function() {
			 $("#resumeErrorMsg").html("");	
			  sizeInKB = Math.round(parseInt(this.files[0].size)/1024);
			  if(parseInt(sizeInKB) > 750){
				  alert("File size should not exceed more than 750KB. Please try again.");
			  }
		});
		 
		 $("#update").click(function() {
			 selectAllElementsInDualList();
				//validate the required fields
				var resumeName = $.trim($("#resumeName").val());
				var resumeId = $.trim($("#uploadResumeId").val());
				var jobTitle = $.trim($("#desiredJobTitle").val());
				var workAuth = $.trim($("#workAuthorizationUS option:selected").text());
				
				if (resumeName != null && resumeName != ""
					&& jobTitle != null	&& jobTitle != "" && workAuth != "Select"
					&& workAuth != null	&& workAuth != "" && parseInt(sizeInKB) < 750){
					
					if(parseInt(sizeInKB) == 0){
						$("#resumeErrorMsg").html("");
						updateResume(resumeName, resumeId);	
					}
					if(parseInt(sizeInKB) > 0 && validateResume($.trim($("#fileData").val()))){
						$("#resumeErrorMsg").html("");
						updateResume(resumeName, resumeId);						
					}		
				}  
				else if(parseInt(sizeInKB) > 750){
					$("#resumeErrorMsg").html("<span style='color:red'>File size should not exceed more than 750KB. Please try again.</span>");
				}
				else {
					$("#resumeErrorMsg").html("<span style='color:red'>Please enter the required fields.</span>");
				}
			});
		 
		 function updateResume(resumeName, resumeId){
			 selectAllElementsInDualList();
			//validate number of resumes
			//validate if resume name already exist in db
				$.ajax({url :"${pageContext.request.contextPath}/jobSeekerResume/validateCreateResumePopUp.html?resumeName="+ resumeName+"&resumeId="+resumeId,
					type: "GET",
					success : function(data) {
						if (data.maxResume != null) {
								$("#resumeErrorMsg").html("<span style='color:red'>"+ data.maxResume+ "</span>");
							} else if (data.duplicateResume != null) {
								$("#resumeErrorMsg").append("<br/><span style='color:red'>"+ data.duplicateResume+ "</span>");
							} else {
								$("#uploadResumeForm").attr("action","${pageContext.request.contextPath}/jobSeekerResume/updateResumeUpload.html");
								$("#uploadResumeForm").attr("method","POST");
								$("#uploadResumeForm").submit();
							}
						},
					error : function(response) {
						alert("Server Error : "+ response.status);
						},
					complete : function() {
						
					}
				});
		 }
		 
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
			<h2>Edit Resume</h2>
			<img src="../resources/images/Close.png" onclick="parent.$.nmTop().close();" title="Close" class="cursor"
				width="19" height="19" alt="">
		</div>

		<div class="popUpContainerWrapper">
			<form:form method="post" action="updateResumeUpload.html" commandName="createResume" id="uploadResumeForm" enctype="multipart/form-data">
				<div id="resumeErrorMsg"></div>
				
				<form:input type="hidden" path="uploadResumeId" />
				<form:input type="hidden" path="resumeType" />
				<div class="rowEvenNewSpacing">
					<span>You uploaded <a href="#" id="downloadResume">${createResume.filename}</a> as your resume, you can upload an updated resume</span>
            	</div> 
				<!-- Choose file section -->
				<div class="rowEvenNewSpacing">
					<span class="lableText4">Upload Resume:</span>
					<form:input path="fileData" type="file" />
                	<!-- <span class="floatLeft marginTop5"><a href="" class="btn_sm orange">Choose File</a></span><span class="required paddingTop0 marginTop5 TextColorA05">No File Chosen</span><span class="required paddingTop0 marginTop4">(Required)</span> -->
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
										path="selectedList" id="selectedeList" multiple="true" items="${blockedCompanies}" itemValue="optionId" itemLabel="optionName"
										size="5" style="width:150px;">
									</form:select></td>
						</table>
					</div>
				</div>
				<div class="popUpButtonRow">
					 <a id="update" href="#"
						class="btn_sm orange">Save</a> <a href="<%=request.getContextPath()%>/jobSeekerResume/manageResume.html" class="nyroModal btn_sm orange">Cancel</a>
				</div>
				<div class="clearfix"></div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>