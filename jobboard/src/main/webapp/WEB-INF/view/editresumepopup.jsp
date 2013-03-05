<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>


<script type="text/javascript">
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
		//validate the required fields
			var resumeName = $.trim($("#resumeName").val());
			var resumeId = $.trim($("#uploadResumeId").val());
			var jobTitle = $.trim($("#desiredJobTitle").val());
			var workAuth = $.trim($("#workAuthorizationUS option:selected").text());
	
			if (resumeName != null && resumeName != ""
				&& jobTitle != null	&& jobTitle != "" && workAuth != "Select"
				&& workAuth != null	&& workAuth != ""){
					$("#resumeErrorMsg").html("");
					//validate number of resumes
					//validate if resume name already exist in db
					$.ajax({url : "${pageContext.request.contextPath}/jobSeekerResume/validateCreateResumePopUp.html?resumeName="+ resumeName+"&resumeId="+resumeId,
						type: "GET",
						success : function(data) {
							if (data.maxResume != null) {
									$("#resumeErrorMsg").html("<span>"+ data.maxResume+ "</span>");
								} else if (data.duplicateResume != null) {
									$("#resumeErrorMsg").append("<span>"+ data.duplicateResume+ "</span>");
								} else {
									$("form").attr("action","${pageContext.request.contextPath}/jobSeekerResume/updateResumePopup.html");
									$("#editResumeForm").submit();
								}
							},
						error : function(response) {
							alert("Server Error : "+ response.status);
							},
						complete : function() {
							
						}
					});
			} else {
				$("#resumeErrorMsg").html("<span>Please enter the required fields.</span>");
			}
	 });
	 $("#searchCompanyName").click(function() {
			var IdData = new Array();
	    	var NameData = new Array();
	    	
			var empName = $("#searchComapnyName").val();
			//Remove the available list
			$('#availableList option').each(function(index, option) {
					  $(option).remove();
					  
			});
				if($.trim(empName).length<=0){
					$('#availableList').attr('size', 7);
		        	$('#selectedeList').attr('size', 7);
					
			}else{
				
			$.ajax({
		        type: "GET",
		        url: "${pageContext.request.contextPath}/agency/getFacilityNamesList.html?term="+empName,
		        dataType: "json",							        
		        contentType: "application/json; charset=utf-8",
		        success: function(data) {							        	
		        	if(data.EmpList.length>10){
		        		$('#availableList').attr('size', 10);
			        	$('#selectedeList').attr('size', 10);
		        	}else{	
			        	$('#availableList').attr('size', data.EmpList.length);
			        	$('#selectedeList').attr('size', data.EmpList.length);
		        	}
		        	
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
			}
			});
	});
</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>Edit Resume</h2>
			<img src="../resources/images/Close.png" width="19" height="19" title="Close" class="cursor"
				alt="" onclick="parent.$.nmTop().close();">
		</div>

		<div class="popUpContainerWrapper">
			<form:form method="post" action="updateResumePopup.html" id="editResumeForm" commandName="createResume" enctype="multipart/form-data" >
				<div id="resumeErrorMsg" class="FormErrorDisplayText">
					
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText4">Resume Name:</span>
					<form:hidden path="uploadResumeId" />
					<form:hidden path="resumeType" />
					<form:input type="text"	path="resumeName" class="job_seeker_password textBox2"/>
					<span class="required">(Required)</span>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText4">Desired job title:</span> 
					<form:input type="text" path="desiredJobTitle" class="job_seeker_password textBox2"/>
					<span class="required">(Required)</span>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText4">Desired Employment Type:</span> 
					
						<form:select  
							path="employmentTypes" id="employmentTypes" multiple="true" items="${employmentType}" 
							itemValue="optionValue" itemLabel="optionValue" size="7" style="width:150px;"/>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText4">U.S. Work Authorization:</span> 
					<form:select class="jb_input3 width350" name="select3" style="width: auto"
							path="workAuthorizationUS" items="${workAuthUS}" itemValue="optionValue" itemLabel="optionValue" />
					<span class="required">(Required)</span>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText4"> Willing to relocate:</span>
					
					<div class="redioButtonHolderWidth marginTop5">
						<form:radiobuttons path="willingToRelocate" items="${relocate}" itemValue="optionValue" itemLabel="optionValue" />
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
						<form:radiobuttons path="resumeVisibility" id="resVisibility" onclick="enableDisableCompanyPanel(this.id);" items="${resumeVisibility}" itemValue="visibilityId" itemLabel="visibilityName" />
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
					
					<a id="update" href="#"	class="btn_sm orange">Save and Continue</a> 
						<a href="<%=request.getContextPath()%>/jobSeekerResume/manageResume.html" class="nyroModal btn_sm orange">Cancel</a>
				</div>
				<div class="clearfix"></div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>