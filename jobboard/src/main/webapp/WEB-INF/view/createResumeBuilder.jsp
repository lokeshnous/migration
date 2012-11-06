<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>

<!-- JAVASCRIPT FILES -->
<jsp:include page="common/include.jsp" />
<jsp:include page="common/include.jsp" />
<link href="../resources/css/JB.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript"
	src="/media/js/jquery.js"></script>		
 <link href="../resources/css/jquery-auto-ui.css" rel="stylesheet"
	type="text/css"> 
	<script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>
<link href="../resources/css/jquery.megamenu.css" rel="stylesheet"
	type="text/css" />
<link href="../resources/css/SliderStyles.css" rel="stylesheet"
	type="text/css">

	
 <link rel="stylesheet" type="text/css" media="screen"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css">

<script type="text/javascript">
function validateNumber(event) {
    var keyval = window.event ? event.keyCode : event.which;

    if (event.keyCode == 8 || event.keyCode == 9  || event.keyCode == 46
     || event.keyCode == 37 || event.keyCode == 39) {
        return true;
    }
    else if ( keyval < 48 || keyval > 57 ) {
        return false;
    }
    else return true;
};
</script>

<script type="text/javascript">

	//Limit text area characters
	function limitText(limitField, limitCount, limitNum) {
	/* 		alert(limitField.value.length+""+limitCount.value+""+limitNum); */
		if (limitField.value.length > limitNum) {
			limitField.value = limitField.value.substring(0, limitNum);
		} else {
			limitCount.value = limitNum - limitField.value.length;
		}
		
	}
	
	//Limit text area characters
	function resetEndDate(CBId) {

	}

	jQuery(document).ready(function() {	
				
		$("#phone").inputmask("mask", {"mask": "(999) 999-9999"}); 
		//Date picker
    	$(function() {
    		$( ".datepicker" ).datepicker();
    	});
    	
		//On click of check box as 'present' 
		//reseting end date is blank value
    	$('#workExpPresentCBId').click(function(){
    		if($("#workExpPresentCBId").val()){
    			$("#workExpEndDtId").val(null);    
    		}
    	});
    	
     	$('#graduatedCBId').click(function(){
    		if($("#graduatedCBId").val()){
    			$("#eduEndDateId").val(null);    
    		}
    	});
    	
    	$('#skillRefId').click(function() {
    	    var skillName = $('#skillId').val();

    	    if(skillName != ''){
    	    	if($('#textAreaId').val() != '') {
    	    		$("#textAreaId").append(',');    	    
    	    	}   	    
    	    	$("#textAreaId").append(skillName);
    	    	$("#skillId").val('');
    	    }
    	    //send to server and process response
    	});
    	
		var per =${createResume.totalProgress};
		var lprog;
		 lprog =$("#progressbar").progress({ width: 100, height: 10, prog:per});
		    	if(per<50)
		    	lprog.color("red");
		    	if(per>50 && per<80)
		    		lprog.color("blue");
		    	if(per>80)
		    		lprog.color("green");
		    	
		//Adding certifications
		$('#certAjaxCallIdButton').live('click', function() {
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/jobSeekerResume/addCertifications.html",
				success : function(data) {
					$('#listOfCertsId').append(data);
				},
			});
		});
		
		//Adding work experience
		$('#workExpAjaxCallIdButton').live('click', function() {
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/jobSeekerResume/addWorkExp.html",
				success : function(data) {
					$('#listOfWorkExpId').append(data);
				},
			});
		});
		
		//Adding Education
		$('#eduAjaxCallIdButton').live('click', function() {
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/jobSeekerResume/addEducationDetails.html",
				success : function(data) {
					$('#listOfEduId').append(data);
				},
			});
		});
		
		//Adding References
		$('#refAjaxCallIdButton').live('click', function() {
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/jobSeekerResume/addReferences.html",
				success : function(data) {
					$('#listOfRefId').append(data);
				},
			});
		});
		
		//Adding Phone Numbers
		$('#phNoAjaxCallIdButton').live('click', function() {
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/jobSeekerResume/addPhoneNos.html",
				success : function(data) {
					$('#listOfPhoneId').append(data);
				},
			});
		});
		
		//Adding Languages
		$('#langAjaxCallIdButton').live('click', function() {
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/jobSeekerResume/addLanguages.html",
				success : function(data) {
					$('#listOfLangId').append(data);
				},
			});
		});
		
		//Retreiving the percentage of resume builder completed from controller
		$("#saveResBuilderBtId").click(function(){
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/jobSeekerResume/getResumeProgress.html",
				success : function(data) {		
						val=data;
						if(val!=null){
							if (confirm("You have completed "+ val+ "% of information.\n Do you want to continue?")) 
							{
								$("#saveResBuilderHdBtId").click();
								
							} 
							
						}
						
				},
			});
		});
	//	$('[id^=zipCode]').keypress(validateNumber);
	});
	
	//jQuery(".megamenu").megamenu();
</script>
<script type="text/javascript">
		    function cancelProcess(){
		    	window.location.href = '${pageContext.request.contextPath}/jobSeeker/jobSeekerDashBoard.html';
		    }		
		</script>

<!-- <script type="text/javascript" src="../resources/js/expandCollapse.js"></script> -->
<script type="text/javascript" src="../resources/js/lprogress.min.js"></script>
<script type="text/javascript" src="../resources/js/lprogress.js"></script>
</head>

<body class="job_board">
	<form:form method="Post" action="saveResumeBuilder.html"
		commandName="createResume" id="createResumeBuilderId">
		<div class="ad_page_top">
			${adPageTop}
		</div>
		<div class="main_wrapper_outside">
			<div class="main_wrapper_inside">

				<div class="main">
					<jsp:include page="../templates/templates_header.jsp"></jsp:include>
					<div class="clearfix"></div>

					<!--Start:MidContant-->
					<div class="MidContent_Wrapper">
						<div class="popupHeader Padding0  OrangeBG">
							<h2>CREATE YOUR RESUME</h2>
							<span class="floatRight marginRight10"><a
								href="<%=request.getContextPath()%>/jobSeeker/jobSeekerDashBoard.html"
								class="link_color3_emphasized FontSize12 FontWeight">Back to
									Dashboard</a></span>
						</div>
						<div class="clearfix"></div>
						
 						<div class="row">
							<span>
								<h2 class="marginLeft10 noTopBottomBorder floatLeft color1">Resume Name:</h2>&#160;
								<span class="DisplayResumeName"><c:out value="${createResume.resumeName}"/></span>

								 <div class="progressBarContainer"><span class="floatLeft"><div id="progressbar"></div></span>
								 <h3 class="floatLeft marginLeft5"><c:out value="${createResume.totalProgress}"/>% Complete</h3>
								 <%-- <h3 class="floatLeft marginLeft5"><c:out value="${createResume.resumeName}"/></h3> --%>
           </div>
           </span> </div> 
							
						
						
						<c:if test="${not empty errorMessage}">
						    	<div id="errmsg" style="color: red" align="left" >
					    		<c:out value="${errorMessage}"></c:out>
							</div>
						</c:if>
				
						<div class="clearfix"></div>
					</div>
					<!---->

					<!--Test-->
					<div class="searchResultsListing">
						<div class="searchResultsItem MarginBottom10">
							<ul class="searchResultsJobInfo closed">
								<li class="searchResultsColumn1">
									<div class="sectionHeaderCreateResume">
										<div class="floatLeft">

											<h2 class="noBorder">Contact
												Info</h2>
										</div>
										<div class=" accord-open">&nbsp;</div>
									</div>
								</li>
							</ul>
							<div class="searchResultsSubContent">
								<div class="job_seeker_login leftFormHolderResumepage">

									<div class="rowEvenNewSpacing">
										<span class="lableText3">First Name:</span>
										<form:input path="contactInfoForm.firstName"
											class="job_seeker_password textBox350" />
										<span class="required">(Required)</span>
									</div>
									<div>
										<span class="lableText3"></span> <FONT color="red"><form:errors
												path="contactInfoForm.firstName" /></FONT>
									</div>

									<div class="rowEvenNewSpacing">
										<span class="lableText3">Middle Name:</span>
										<form:input path="contactInfoForm.middleName"
											class="job_seeker_password textBox350" />
									</div>

									<div class="rowEvenNewSpacing">
										<span class="lableText3">Last Name:</span>
										<form:input path="contactInfoForm.lastName"
											class="job_seeker_password textBox350" />
										<span class="required">(Required)</span>
									</div>
									<div>
										<span class="lableText3"></span> <FONT color="red"><form:errors
												path="contactInfoForm.lastName" /></FONT>
									</div>
									<div class="rowEvenNewSpacing">
										<span class="lableText3">Street Address:</span>
										<form:input path="contactInfoForm.addressLine1"
											class="job_seeker_password textBox350" />
										<span class="required">(Required)</span>
									</div>
									<div>
										<span class="lableText3"></span> <FONT color="red"><form:errors
												path="contactInfoForm.addressLine1" /></FONT>
									</div>
									<div class="rowEvenNewSpacing">
										<span class="lableText3"></span>
										<form:input path="contactInfoForm.addressLine2"
											class="job_seeker_password textBox350" />
										<span class="required"></span>
									</div>
									<div class="rowEvenNewSpacing">
										<span class="lableText3">City:</span>
										<form:input path="contactInfoForm.city"
											class="job_seeker_password textBox350" />
										<span class="required">(Required)</span>
									</div>
									<div>
										<span class="lableText3"></span> <FONT color="red"><form:errors
												path="contactInfoForm.city" /></FONT>
									</div>
									<div class="row">
										<span class="lableTextSelect ">State / Province:</span>
										<form:select path="contactInfoForm.state"
											class="jb_input3 jb_input_width3">
											<form:option value="0" label="Select" />
											<form:options items="${stateList}" itemValue="stateValue"
												itemLabel="stateValue" />
										</form:select>
										<span class="requiredTopmargin">(Required)</span>
									</div>
									<div>
										<span class="lableText3"></span> <FONT color="red"><form:errors
												path="contactInfoForm.state" /></FONT>
									</div>
									<div class="rowEvenNewSpacing">
										<span class="lableText3">Zip Code:</span>
										
										<form:input path="contactInfoForm.postalCode"
											type="text" name="zipCode" id="zipCode"
											class="job_seeker_password textBox350" maxlength="5" />
																						
										<span class="required">(Required)</span>
									</div>
									<div>
										<span class="lableText3"></span> <FONT color="red"><form:errors
												path="contactInfoForm.postalCode" /></FONT>
									</div>
									<div class="row">
										<span class="lableTextSelect ">Country:</span>
										<form:select path="contactInfoForm.country"
											class="jb_input3 jb_input_width3">
											<form:option value="0" label="Select" />
											<form:options items="${countryList}" itemValue="countryValue"
												itemLabel="countryValue" />
										</form:select>
										<span class="requiredTopmargin">(Required)</span>
									</div>
									<div>
										<span class="lableText3"></span> <FONT color="red"><form:errors
												path="contactInfoForm.country" /></FONT>
									</div>

									<div class="rowEvenTB10Spacing">
<!-- 										<span class="lableText3">Phone Number:</span> -->
										<div class="floatLeft marginRight10"></div>
										<span class="floatLeft marginRight10">

									<div class="row">
									<div id="listOfPhoneId">
										<c:forEach items="${createResume.listPhoneDtlForm}" var="phoneDtl" varStatus="status">
											<div class="rowEvenNewSpacing MarginBottom10">
											<c:if test="${status.count == 1}">   
									            <span class="lableText3">Phone Number:</span> 
									         </c:if>   
											<c:if test="${status.count != 1}">   
									            <span class="lableText3"></span> 
									         </c:if>  									         
											<div class="floatLeft marginRight10"></div>
											<span class="floatLeft marginRight10"></span>
												<form:select path="listPhoneDtlForm[${status.index}].phoneType" id="exclude" class="jb_input75">
													<form:options items="${phoneTypeList}" itemValue="optionId" itemLabel="optionName" />
												</form:select>
												<form:input id="phone" path="listPhoneDtlForm[${status.index}].phoneNumber" class="job_seeker_password" />
												<span class="required ">(Required)</span>
											</div>
										</c:forEach>
										</span>
									</div>

									<div id="phNoAjaxCallId">
										<p>
											<a href="#" class="link_color1_emphasized" id="phNoAjaxCallIdButton">Save and add another phone number</a> 
										</p>									
									</div>
									</div>
									<form:hidden path="uploadResumeId" />
									<form:hidden path="builderResumeId" />
								</div>
							</div>
						</div>
						<div class="searchResultsItem">
							<ul class="searchResultsJobInfo closed">

								<li class="searchResultsColumn1">
									<div class="sectionHeaderCreateResume">
										<div class="floatLeft">
											<h2 class="noBorder">Objective</h2>
										</div>
										<div class=" accord-open">&nbsp;</div>
									</div>
								</li>

							</ul>
							<div class="searchResultsSubContent">
								<div class="job_seeker_login leftFormHolderResumepage">
									<div class="row MarginBottom10 ">
										<div class="lableText3 marginTop10">Your Career
											Objective:</div>
										<div class="input_grp5 ">
											<form:textarea path="objective" class="textareaBoxCResume" id="objlimitedtextarea"
												onKeyDown="limitText(this.form.objlimitedtextarea,this.form.countdownId1,2000);"
												onKeyUp="limitText(this.form.objlimitedtextarea,this.form.countdownId1,2000);"
												rows="5" cols="45" />
												<p class="magrin_top0" ><input readonly type="text" class="input2000_width" name="countdownId1" size="3" value="2000" id="countdown">characters remaining.<p>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="searchResultsItem">
							<ul class="searchResultsJobInfo closed">

								<li class="searchResultsColumn1">
									<div class="sectionHeaderCreateResume">
										<div class="floatLeft">
											<h2 class="noBorder">Work
												Experience</h2>
										</div>
										<div class=" accord-open">&nbsp;</div>
									</div>
								</li>

							</ul>
							<div class="searchResultsSubContent">
							<div id="listOfWorkExpId">
								<c:forEach items="${createResume.listWorkExpForm}" var="workExp"
									varStatus="status">
									 <c:if test="${status.count != 1}">   
							            <p class="borderBottomDotted marginBottom15">&nbsp;</p> 
							         </c:if>   
									<div class="job_seeker_login leftFormHolderResumepage">
										<div class="rowEvenNewSpacing">
											<span class="lableText3">Job Title:</span>
											<form:input path="listWorkExpForm[${status.index}].jobTitle"
												class="job_seeker_Resume" />
											<span class="required">(Required)</span>
										</div>
										<div class="rowEvenNewSpacing">
											<span class="lableText3">Company Name:</span>

											<form:input
												path="listWorkExpForm[${status.index}].employerName"
												class="job_seeker_Resume" />
											<span class="required">(Required)</span>
										</div>
										<div class="class="row"">
											<span class="lableTextSelect ">Employment Type:</span>
											<form:select
												path="listWorkExpForm[${status.index}].employmentType"
												class="jb_input3 jb_input_width3">
												<form:option value="0" label="--- Employment Type ---" />
												<form:options items="${empTypeList}" itemValue="optionName"
													itemLabel="optionName" />
											</form:select>
											<span class="requiredTopmargin">(Required)</span>
										</div>
										<div class="rowEvenNewSpacing">
											<span class="lableText3"> Start Date:</span>
											<form:input path="listWorkExpForm[${status.index}].startDate"
												class="job_seeker_Resume datepicker" />
											<div class="calender">
												<a href="#"><img src="../resources/images/tranBg.png"
													width="14" height="14" alt="Datepick"></a>
											</div>
											<span class="required">(Required)</span>
										</div>
										<div class="row marginTop10">
											<span class="lableTextSelect">End Date:</span>
											<form:input path="listWorkExpForm[${status.index}].endDate"
												class="job_seeker_Resume datepicker" />
											<div class="calender">
												<a href="#"><img src="../resources/images/tranBg.png"
													width="14" height="14" alt="Datepick"></a>
											</div>
											<span class="required"> 
											<form:checkbox path="listWorkExpForm[${status.index}].bPresent" onclick="resetEndDate(this.id);"/>
											</span>
											<div class="floatLeft marginLeft10 marginTop8">present</div>

											<span class="required">(Required)</span>
										</div>
										<div class="rowEvenNewSpacing">
											<span class="lableText3">Years at Position:</span>
											<form:input
												path="listWorkExpForm[${status.index}].yrsAtPostion"
												class="job_seeker_Resume" />
											<span class="required">(Required)</span>
										</div>
										<div class="row">
											<span class="lableTextSelect">Career Level:</span>

											<form:select
												path="listWorkExpForm[${status.index}].currentCareerLvl"
												class="jb_input3 jb_input_width3">
												<form:option value="0" label="--- Career Level ---" />
												<form:options items="${careerLvlList}"
													itemValue="optionName" itemLabel="optionName" />
											</form:select>

											<span class="requiredTopmargin"> 
											<form:checkbox path="listWorkExpForm[${status.index}].bCurrentCareerLevel"/> 
											</span>

											<div class=" floatLeft marginLeft10 marginTop5">
												<p>This is my current career level</p>
											</div>
											<span class="requiredTopmargin">(Required)</span>
										</div>
										<div class="row">
											<span class="lableTextSelect">Annual Salary:</span>

											<form:select
												path="listWorkExpForm[${status.index}].annualSalary"
												class="jb_input3 jb_input_width3">
												<form:option value="0" label="--- Annual Salary ---" />
												<form:options items="${annualSalarylList}"
													itemValue="optionName" itemLabel="optionName" />
											</form:select>

											<div class="toolTip marginTop15 marginLeft10">
												<span class="classic">Select your annual salary from
													this drop-down menu or enter your hourly pay rate in the
													following text box.</span>
											</div>
											<span class="requiredTopmargin"></span>
										</div>
										<div class="rowEvenTB10Spacing">
											<span class="lableText3"> Hourly Pay Rate:</span>
											<form:input path="listWorkExpForm[${status.index}].hrlyPayRate"
												class="job_seeker_Resume" />
											<span class="required "></span>
										</div>
										<div class="row MarginBottom10 ">
											<div class="lableText3">Summary / Job
												Description:</div>
											<div class="input_grp5 ">
												<form:textarea id="workExplimitedtextarea${status.index}"
													onKeyDown="limitText(this.form.workExplimitedtextarea${status.index},this.form.countdownworkexp${status.index},2000);"
													onKeyUp="limitText(this.form.workExplimitedtextarea${status.index},this.form.countdownworkexp${status.index},2000);"
													path="listWorkExpForm[${status.index}].description"
													class="textareaBoxCResume magrin_top0" rows="3" cols="45" />
												<p class="magrin_top0"><input readonly type="text" class="input2000_width" name="countdownworkexp${status.index}" size="3" value="2000" >characters remaining.<p>
											</div>
										</div>
									</div>
								</c:forEach>
								</div>
								<div id="workExpAjaxCallId">
									<p>
										<a href="#" class="link_color1_emphasized" id="workExpAjaxCallIdButton">Save and add another work experience</a> 
									</p>									
								</div>
							</div>
						</div>
						<div class="searchResultsItem">
							<ul class="searchResultsJobInfo closed">

								<li class="searchResultsColumn1">
									<div class="sectionHeaderCreateResume">
										<div class="floatLeft">
											<h2 class="noBorder">Education</h2>
										</div>
										<div class="accord-open">&nbsp;</div>
									</div>
								</li>

							</ul>
							<div class="searchResultsSubContent">
							<div id="listOfEduId">
								<c:forEach items="${createResume.listEduForm}" var="education" varStatus="status">
									 <c:if test="${status.count != 1}">   
							            <p class="borderBottomDotted marginBottom15">&nbsp;</p> 
							         </c:if>   
									<div class="job_seeker_login leftFormHolderResumepage">
										<div class="rowEvenNewSpacing">
											<span class="lableText3">Institution Name:</span>
											<form:input path="listEduForm[${status.index}].instituteName"
												class="job_seeker_password textBox350" />
											<span class="required">(Required)</span>
										</div>
										<div class="row">
											<span class="lableTextSelect">Degree Level:</span>

											<form:select path="listEduForm[${status.index}].degreeLvl"
												class="jb_input3 jb_input_width3">
												<form:option value="0" label="--- Degree Level ---" />
												<form:options items="${eduDegreeList}" itemValue="optionId"
													itemLabel="optionName" />
											</form:select>

											<span class="requiredTopmargin"> 
											<form:checkbox path="listEduForm[${status.index}].bNotGraduatedYet" id="graduatedCBId"/>
											</span>

											<div class=" floatLeft marginLeft10 marginTop5">
												<p>I haven't graduated yet.</p>
											</div>
											<span class="requiredTopmargin">(Required)</span>
										</div>
										<div class="rowEvenNewSpacing">
											<span class="lableText3">Field of Study:</span>
											<form:input path="listEduForm[${status.index}].fieldOfStudy"
												class="job_seeker_password textBox350" />
										</div>
										<div class="rowEvenNewSpacing">
											<span class="lableText3"> Start Date:</span>
											<form:input path="listEduForm[${status.index}].startDate"
												class="job_seeker_Resume datepicker" />
<!-- 											<div class="calender">
												<a href="#"><img src="../resources/images/tranBg.png"
													width="14" height="14" alt="Datepick"></a>
											</div> -->
										</div>
										<div class="row marginTop10">
											<span class="lableTextSelect">End Date:</span>

											<form:input path="listEduForm[${status.index}].endDate"
												class="job_seeker_Resume datepicker" />
<!-- 											<div class="calender">
												<a href="#"><img src="../resources/images/tranBg.png"
													width="14" height="14" alt="Datepick"></a>
											</div> -->
										</div>
										<div class="row MarginBottom10 ">

											<div class="lableText3 marginTop10">Degrees:</div>
											<div class="input_grp5 ">
												<form:textarea path="listEduForm[${status.index}].degrees" id="eduDeglimitedtextarea${status.index}"
													onKeyDown="limitText(this.form.eduDeglimitedtextarea${status.index},this.form.countdowneduDeg${status.index},2000);"
													onKeyUp="limitText(this.form.eduDeglimitedtextarea${status.index},this.form.countdowneduDeg${status.index},2000);"
													class="textareaBoxCResume" rows="3" cols="45" />
												<p class="magrin_top0"><input readonly type="text" class="input2000_width" name="countdowneduDeg${status.index}" size="3" value="2000">characters remaining.<p>
											</div>
										</div>
										<div class="row MarginBottom10 ">

											<div class="lableText3 marginTop10">Certifications:</div>
											<div class="input_grp5">
												<form:textarea
													path="listEduForm[${status.index}].certifications" id="eduCertlimitedtextarea${status.index}"
													onKeyDown="limitText(this.form.eduCertlimitedtextarea${status.index},this.form.countdowneduCert${status.index},2000);"
													onKeyUp="limitText(this.form.eduCertlimitedtextarea${status.index},this.form.countdowneduCert${status.index},2000);"
													class="textareaBoxCResume magrin_top0" rows="3" cols="45" />
												<p class="magrin_top0"><input readonly type="text" class="input2000_width" name="countdowneduCert${status.index}" size="3" value="2000">characters remaining.<p>
											</div>

										</div>
									</div>
								</c:forEach>
								</div>
								<div id="eduAjaxCallId">
									<p>
										<a href="#" class="link_color1_emphasized" id="eduAjaxCallIdButton">Save and add another institution</a> 
									</p>									
								</div>
							</div>
						</div>
						<div class="searchResultsItem">
							<ul class="searchResultsJobInfo closed">
								<li class="searchResultsColumn1">
									<div class="sectionHeaderCreateResume">
										<div class="floatLeft">

											<h2 class="noBorder">Certification</h2>
										</div>
										<div class="accord-open">&nbsp;</div>
									</div>
								</li>
							</ul>
							<div class="searchResultsSubContent" >
								<div id="listOfCertsId">
								<c:forEach items="${createResume.listCertForm}" var="certification" varStatus="status">
									 <c:if test="${status.count != 1}">   
							            <p class="borderBottomDotted marginBottom15">&nbsp;</p> 
							         </c:if>   								
									<div class="job_seeker_login leftFormHolderResumepage"
										id="addCertDivId">
										<div class="rowEvenNewSpacing">
											<span class="lableText3">Certification Name:</span>
											<form:input
												path="listCertForm[${status.index}].certificationName"
												class="job_seeker_password textBox350" />
											<span class="required">(Required)</span>
										</div>
										<div class="rowEvenNewSpacing">
											<span class="lableText3">Certifying Authority:</span>
											<form:input
												path="listCertForm[${status.index}].certifyingAuthority"
												class="job_seeker_password textBox350" />
										</div>

										<div class="rowEvenNewSpacing">
											<span class="lableText3"> Received:</span>
											<form:input
												path="listCertForm[${status.index}].dateOfReceipt"
												class="job_seeker_Resume datepicker" />
										</div>
										<div class="row MarginBottom10 ">
											<div class="lableText3 marginTop10">Summary:</div>
											<div class="input_grp5 ">

												<form:textarea path="listCertForm[${status.index}].summary" id="Certslimitedtextarea${status.index}"
													onKeyDown="limitText(this.form.Certslimitedtextarea${status.index},this.form.countdownCerts${status.index},2000);"
													onKeyUp="limitText(this.form.Certslimitedtextarea${status.index},this.form.countdownCerts${status.index},2000);"
													class="textareaBoxCResume" rows="3" cols="45" />
												<p class="magrin_top0"><input readonly type="text" class="input2000_width" name="countdownCerts${status.index}" size="3" value="2000">characters remaining.<p>
											</div>
										</div>
									</div>
								</c:forEach>
								</div>
								<div id="certAjaxCallId">
<!-- 									<input type="button" value="Save and add another certification"
										class="orange" id="certAjaxCallIdButton" /> -->
									 <p><a href="#" class="link_color1_emphasized" id="certAjaxCallIdButton" >Save and add another certification</a></p> 
								</div>
							</div>

						</div>
						<div class="searchResultsItem">
							<ul class="searchResultsJobInfo closed">
								<li class="searchResultsColumn1">
									<div class="sectionHeaderCreateResume">
										<div class="floatLeft">
											<h2 class="noBorder">Skills</h2>
										</div>

										<div class="accord-open">&nbsp;</div>
									</div>
								</li>
							</ul>
							<div class="searchResultsSubContent">
								<div class="job_seeker_login leftFormHolderResumepage">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">Skill:</span> 
										<input type="text" name="skill" class="job_seeker_password textBox350" id="skillId"/> 
										<span class="required"><a href="#" class="btn_sm orange" id="skillRefId">Add</a></span>
										<div class="toolTip marginTop8 marginLeft5">
											<span class="classic">Show potential employers what
												your strengths are by entering up to 50 special skills in
												this section. Use brief keywords and phrases like "Triage"
												and "Emergency Care" so your list of skills can be browsed
												with ease.</span>
										</div>
									</div>
									<div class="row MarginBottom10 ">
										<div class="lableText3 marginTop10"></div>
										<div class="input_grp5 ">
											<form:textarea path="skills" class="textareaBoxCResume"
												rows="3" cols="45" id="textAreaId"/>

										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="searchResultsItem">
							<ul class="searchResultsJobInfo closed">
								<li class="searchResultsColumn1">
									<div class="sectionHeaderCreateResume">

										<div class="floatLeft">
											<h2 class="noBorder">Languages</h2>
										</div>
										<div class="accord-open">&nbsp;</div>
									</div>
								</li>
							</ul>
							<div class="searchResultsSubContent">
								<div id="listOfLangId">
								<c:forEach items="${createResume.listLangForm}" var="languageObj"
									varStatus="status">
									 <c:if test="${status.count != 1}">   
							            <p class="borderBottomDotted marginBottom15">&nbsp;</p> 
							         </c:if>   									
									<div class="job_seeker_login leftFormHolderResumepage">
										<div class="row">
											<span class="lableTextSelect">Language:</span>
											<form:select path="listLangForm[${status.index}].language"
												class="jb_input3 jb_input_width3">
												<form:options items="${languagelList}"
													itemValue="optionName" itemLabel="optionName" />
											</form:select>
										</div>
										<div class="row">
											<span class="lableTextSelect ">Proficiency Level:</span>

											<form:select path="listLangForm[${status.index}].expLvl"
												class="jb_input3 jb_input_width3">
												<form:option value="0" label="--- Proficiency Level ---" />
												<form:options items="${langProficiencylList}"
													itemValue="optionName" itemLabel="optionName" />
											</form:select>
										</div>
										<div class="row MarginBottom10">
											<span class="lableTextSelect"></span>
										</div>
									</div>
								</c:forEach>
								</div>
								<div id="langAjaxCallId">
									<p>
										<a href="#" class="link_color1_emphasized" id="langAjaxCallIdButton">Save and add another language</a> 
									</p>									
								</div>
							</div>
						</div>
						<div class="searchResultsItem">
							<ul class="searchResultsJobInfo closed">
								<li class="searchResultsColumn1">
									<div class="sectionHeaderCreateResume">
										<div class="floatLeft">
											<h2 class="noBorder">Awards</h2>

										</div>
										<div class="accord-open">&nbsp;</div>
									</div>
								</li>
							</ul>
							<div class="searchResultsSubContent">
								<div class="job_seeker_login leftFormHolderResumepage">
									<div class="row MarginBottom10 ">
										<div class="lableText3 marginTop10">
											If you've received any<br> professional awards,<br>
											please list them here<br> along with the years in<br>
											which you earned them:
										</div>
										<div class="input_grp5 ">
											<form:textarea path="awards" class="textareaBoxCResume" id="awardsTAId"
													onKeyDown="limitText(this.form.awardsTAId,this.form.countdownIdAwards,2000);"
													onKeyUp="limitText(this.form.awardsTAId,this.form.countdownIdAwards,2000);"
												rows="3" cols="45" />
											<p class="magrin_top0"><input readonly type="text" class="input2000_width" name="countdownIdAwards" size="3" value="2000">characters remaining.<p>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="searchResultsItem">
							<ul class="searchResultsJobInfo closed">

								<li class="searchResultsColumn1">
									<div class="sectionHeaderCreateResume">
										<div class="floatLeft">
											<h2 class="noBorder">Memberships</h2>
										</div>
										<div class="accord-open">&nbsp;</div>
									</div>
								</li>

							</ul>
							<div class="searchResultsSubContent">
								<div class="job_seeker_login leftFormHolderResumepage">
									<div class="row MarginBottom10 ">
										<div class="lableText3 marginTop10">If you're a member
											of any professionals associations, please list them here:</div>
										<div class="input_grp5 ">
											<form:textarea path="memberships" class="textareaBoxCResume" id="membershipsTAId"
													onKeyDown="limitText(this.form.membershipsTAId,this.form.countdownMemShip,2000);"
													onKeyUp="limitText(this.form.membershipsTAId,this.form.countdownMemShip,2000);"
												rows="3" cols="45" />
											<p  class="magrin_top0"><input readonly type="text" class="input2000_width" name="countdownMemShip" size="3" value="2000">characters remaining.<p>

										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="searchResultsItem">
							<ul class="searchResultsJobInfo closed">
								<li class="searchResultsColumn1">
									<div class="sectionHeaderCreateResume">

										<div class="floatLeft">
											<h2 class="noBorder">Other</h2>
										</div>
										<div class="accord-open">&nbsp;</div>
									</div>
								</li>
							</ul>
							<div class="searchResultsSubContent">

								<div class="job_seeker_login leftFormHolderResumepage">
									<div class="row MarginBottom10 ">
										<div class="lableText3 marginTop10">
											If you have more<br> information that you<br> would
											like to include,<br> please enter it here:
										</div>

										<div class="input_grp5 ">
											<form:textarea path="otherDetails" id="otherInterestsTAId" class="textareaBoxCResume"
													onKeyDown="limitText(this.form.otherInterestsTAId,this.form.countdownOtherInt,2000);"
													onKeyUp="limitText(this.form.otherInterestsTAId,this.form.countdownOtherInt,2000);" 
													rows="3" cols="45" />
											<p class="magrin_top0"><input readonly type="text" class="input2000_width" name="countdownOtherInt" size="3" value="2000">characters remaining.<p>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="searchResultsItem MarginBottom10">
							<ul class="searchResultsJobInfo closed">
								<li class="searchResultsColumn1">
									<div class="sectionHeaderCreateResume">
										<div class="floatLeft">
											<h2 class="noBorder">References</h2>
										</div>
										<div class="accord-open">&nbsp;</div>

									</div>
								</li>
							</ul>
							<div class="searchResultsSubContent">
							<div id="listOfRefId">
								<c:forEach items="${createResume.listRefForm}" var="reference" varStatus="status">
									 <c:if test="${status.count != 1}">   
							            <p class="borderBottomDotted marginBottom15">&nbsp;</p> 
							         </c:if>   								
									<div class="job_seeker_login leftFormHolderResumepage">
										<div class="rowEvenNewSpacing">
											<span class="lableText3">Name:</span>
											<form:input path="listRefForm[${status.index}].name"
												class="job_seeker_password textBox350" />
										</div>

										<div class="rowEvenNewSpacing">
											<span class="lableText3">Job Title:</span>
											<form:input path="listRefForm[${status.index}].jobTitle"
												class="job_seeker_password textBox350" />
										</div>
										<div class="rowEvenNewSpacing">
											<span class="lableText3">Company Name:</span>
											<form:input path="listRefForm[${status.index}].companyName"
												class="job_seeker_password textBox350" />
										</div>
										<div class="rowEvenNewSpacing">
											<span class="lableText3">Phone Number:</span>

											<form:input path="listRefForm[${status.index}].phoneNo"
												class="job_seeker_password textBox350" />
										</div>
										<div class="rowEvenNewSpacing">
											<span class="lableText3">Email Address:</span>
											<form:input path="listRefForm[${status.index}].email"
												class="job_seeker_password textBox350" />
											<span class="required"></span>
										</div>
										<div class="rowEvenNewSpacing">
											<span class="lign_height padding_top">Reference Type:</span><span
												class="floteleft"> 
												<form:radiobutton path="listRefForm[${status.index}].referenceType" value="Professional" /> 
												<label>Professional</label>
											</span>&nbsp;&nbsp;&nbsp;<span class="floteleft"> <form:radiobutton
													path="listRefForm[${status.index}].referenceType" value="Personal" /> <label>Personal</label>
											</span>
										</div>
										<div class="rowEvenTB10Spacing magrin_top0">
											<span class="lableText3"> </span> 
										</div>
									</div>
								</c:forEach>
								</div>
								<div id="refAjaxCallId">
									<p>
										<a href="#" class="link_color1_emphasized" id="refAjaxCallIdButton">Save and add another reference</a> 
									</p>									
								</div>
							</div>
						</div>
					</div>
					<div class="clearfix"></div>
					<br /> <span class="marginBottom10 FloatLeft"> 
					<input type="button" value="Save" name="Save" class="orange"  id="saveResBuilderBtId"/>
					<input type="submit" value="Preview" name="Preview" class="orange" />
					<input type="button" value="Cancel" class="orange" name="Cancel" onclick="cancelProcess()" />
					<%-- <a href="<%=request.getContextPath()%>jobSeeker/jobSeekerDashBoard.html" class="orange cancelacount">Cancel</a> --%>
					<input type="submit" value="Save" name="Save" class="orange"  id="saveResBuilderHdBtId" style="visibility: hidden;"/>  
					</span>
				</div>

				

				<div class="clearfix"></div>
				<!-- content_wrapper -->
				<div class="ad_wrapper">
					${adPageBottom}
				</div>
				<!-- ad_wrapper -->
	</div><!--Start:MidContant-->
			</div>
			<!-- main -->

		</div>
		<!-- end main_wrapper_inside -->
		</div>

		<!-- end main_wrapper_outside -->
		<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
	</form:form>
</body>
</html>