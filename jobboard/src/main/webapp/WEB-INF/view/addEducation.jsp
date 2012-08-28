 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	jQuery(document).ready(function() {	
				
		//Date picker
    	$(function() {
    		$( ".datepicker" ).datepicker();
    	});
    });
</script>

<div class="job_seeker_login leftFormHolderResumepage">
<p class="borderBottomDotted marginBottom15">&nbsp;</p> 
	<div class="rowEvenNewSpacing">
		<span class="lableText3">Institution Name:</span>
		<form:input path="createResume.listEduForm[${eduPositionId}].instituteName"
			class="job_seeker_password textBox350" />
		<span class="required">(Required)</span>
	</div>
	<div class="row">
		<span class="lableTextSelect">Degree Level:</span>

		<form:select path="createResume.listEduForm[${eduPositionId}].degreeLvl"
			class="jb_input3 jb_input_width3">
			<form:option value="0" label="--- Degree Level ---" />
			<form:options items="${eduDegreeList}" itemValue="optionId"
				itemLabel="optionName" />
		</form:select>

		<span class="requiredTopmargin"> 
		<form:checkbox path="createResume.listEduForm[${eduPositionId}].bNotGraduatedYet" id="graduatedCBId"/>
		</span>

		<div class=" floatLeft marginLeft10 marginTop5">
			<p>I haven't graduated yet.</p>
		</div>
		<span class="requiredTopmargin">(Required)</span>
	</div>
	<div class="rowEvenNewSpacing">
		<span class="lableText3">Field of Study:</span>
		<form:input path="createResume.listEduForm[${eduPositionId}].fieldOfStudy"
			class="job_seeker_password textBox350" />
	</div>
	<div class="rowEvenNewSpacing">
		<span class="lableText3"> Start Date:</span>
		<form:input path="createResume.listEduForm[${eduPositionId}].startDate"
			class="job_seeker_Resume datepicker" />
<!-- 											<div class="calender">
												<a href="#"><img src="../resources/images/tranBg.png"
													width="14" height="14" alt="Datepick"></a>
											</div> -->
	</div>
	<div class="row marginTop10">
		<span class="lableTextSelect">End Date:</span>

		<form:input path="createResume.listEduForm[${eduPositionId}].endDate"
			class="job_seeker_Resume datepicker" />
<!-- 											<div class="calender">
												<a href="#"><img src="../resources/images/tranBg.png"
													width="14" height="14" alt="Datepick"></a>
											</div> -->
	</div>
	<div class="row MarginBottom10 ">

		<div class="lableText3 marginTop10">Degrees:</div>
		<div class="input_grp5 ">
			<form:textarea path="createResume.listEduForm[${eduPositionId}].degrees" id="eduDeglimitedtextarea${eduPositionId}"
				onKeyDown="limitText(this.form.eduDeglimitedtextarea${eduPositionId},this.form.countdowneduDeg${eduPositionId},2000);"
				onKeyUp="limitText(this.form.eduDeglimitedtextarea${eduPositionId},this.form.countdowneduDeg${eduPositionId},2000);"
				class="textareaBoxCResume" rows="3" cols="45" />
			<p class="magrin_top0"><input readonly type="text" class="input2000_width" name="countdowneduDeg${eduPositionId}" size="3" value="2000">characters remaining.<p>
		</div>
	</div>
	<div class="row MarginBottom10 ">

		<div class="lableText3 marginTop10">Certifications:</div>
		<div class="input_grp5 ">
			<form:textarea
				path="createResume.listEduForm[${eduPositionId}].certifications" id="eduCertlimitedtextarea${eduPositionId}"
				onKeyDown="limitText(this.form.eduCertlimitedtextarea${eduPositionId},this.form.countdowneduCert${eduPositionId},2000);"
				onKeyUp="limitText(this.form.eduCertlimitedtextarea${eduPositionId},this.form.countdowneduCert${eduPositionId},2000);"
				class="textareaBoxCResume" rows="3" cols="45" />
			<p class="magrin_top0"><input readonly type="text" class="input2000_width" name="countdowneduCert${eduPositionId}" size="3" value="2000">characters remaining.<p>
		</div>

	</div>
</div>
