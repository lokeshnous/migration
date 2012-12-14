 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">

	//Limit text area characters
/* 	function limitText(limitField, limitCount, limitNum) {
	 		alert(limitField.value.length+""+limitCount.value+""+limitNum); 
		if (limitField.value.length > limitNum) {
			limitField.value = limitField.value.substring(0, limitNum);
		} else {
			limitCount.value = limitNum - limitField.value.length;
		}
	} */


	jQuery(document).ready(function() {	
		$(".focusElement").last().focus();
		//Date picker
    	$(function() {
    		$( ".datepicker" ).datepicker();
    	});
    });
</script>
	
<div class="job_seeker_login leftFormHolderResumepage">
    <p class="borderBottomDotted marginBottom15">&nbsp;</p> 
	<div class="rowEvenNewSpacing">
		<span class="lableText3">Job Title:</span>
		<form:input path="createResume.listWorkExpForm[${workExpPositionId}].jobTitle"
			class="job_seeker_Resume focusElement" />
		<span class="required">(Required)</span>
	</div>
	<div class="rowEvenNewSpacing">
		<span class="lableText3">Company Name:</span>

		<form:input
			path="createResume.listWorkExpForm[${workExpPositionId}].employerName"
			class="job_seeker_Resume" />
		<span class="required">(Required)</span>
	</div>
	<div class="class="row"">
		<span class="lableTextSelect ">Employment Type:</span>
		<form:select
			path="createResume.listWorkExpForm[${workExpPositionId}].employmentType"
			class="jb_input3 jb_input_width3">
			<form:option value="0" label="--- Employment Type ---" />
			<form:options items="${empTypeList}" itemValue="optionName"
				itemLabel="optionName" />
		</form:select>
		<span class="requiredTopmargin">(Required)</span>
	</div>
	<div class="rowEvenNewSpacing">
		<span class="lableText3"> Start Date:</span>
		<form:input path="createResume.listWorkExpForm[${workExpPositionId}].startDate"
			class="job_seeker_Resume datepicker" />
		<div class="calender">
			<a href="#"><img src="../resources/images/tranBg.png"
				width="14" height="14" alt="Datepick"></a>
		</div>
		<span class="required">(Required)</span>
	</div>
	<div class="row marginTop10">
		<span class="lableTextSelect">End Date:</span>
		<form:input path="createResume.listWorkExpForm[${workExpPositionId}].endDate"
			class="job_seeker_Resume datepicker"/>
		<div class="calender">
			<a href="#"><img src="../resources/images/tranBg.png"
				width="14" height="14" alt="Datepick"></a>
		</div>
		<span class="required"> 
		<form:checkbox path="createResume.listWorkExpForm[${workExpPositionId}].bPresent" id="workExpPresentCBId"/>
		</span>
		<div class="floatLeft marginLeft10 marginTop8">present</div>

		<span class="required">(Required)</span>
	</div>
	<div class="rowEvenNewSpacing">
		<span class="lableText3">Years at Position:</span>
		<form:input
			path="createResume.listWorkExpForm[${workExpPositionId}].yrsAtPostion"
			class="job_seeker_Resume" />
		<span class="required">(Required)</span>
	</div>
	<div class="row">
		<span class="lableTextSelect">Career Level:</span>

		<form:select
			path="createResume.listWorkExpForm[${workExpPositionId}].currentCareerLvl"
			class="jb_input3 jb_input_width3">
			<form:option value="0" label="--- Career Level ---" />
			<form:options items="${careerLvlList}"
				itemValue="optionName" itemLabel="optionName" />
		</form:select>

		<span class="requiredTopmargin"> 
		<form:checkbox path="createResume.listWorkExpForm[${workExpPositionId}].bCurrentCareerLevel"/> 
		</span>

		<div class=" floatLeft marginLeft10 marginTop5">
			<p>This is my current career level</p>
		</div>
		<span class="requiredTopmargin">(Required)</span>
	</div>
	<div class="row">
		<span class="lableTextSelect">Annual Salary:</span>

		<form:select
			path="createResume.listWorkExpForm[${workExpPositionId}].annualSalary"
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
		<form:input path="createResume.listWorkExpForm[${workExpPositionId}].hrlyPayRate"
			class="job_seeker_Resume" />
		<span class="required "></span>
	</div>
	<div class="row MarginBottom10 ">
		<div class="lableText3 marginTop10">Summary / Job
			Description:</div>
		<div class="input_grp5 ">
			<form:textarea id="workExplimitedtextarea${workExpPositionId}"
				path="createResume.listWorkExpForm[${workExpPositionId}].description"
				onKeyDown="limitText(this.form.workExplimitedtextarea${workExpPositionId},this.form.countdownworkexp${workExpPositionId},2000);"
				onKeyUp="limitText(this.form.workExplimitedtextarea${workExpPositionId},this.form.countdownworkexp${workExpPositionId},2000);"
				class="textareaBoxCResume" rows="3" cols="45" />
			<p class="magrin_top0"><input readonly type="text" class="input2000_width" name="countdownworkexp${workExpPositionId}" size="3" value="2000" >characters remaining.<p>
		</div>
	</div>
</div>
