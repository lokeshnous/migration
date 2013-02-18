 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	jQuery(document).ready(function() {	
		$(".focusElement").last().focus();
		//Date picker
    	$(function() {
    		$( ".datepicker" ).datepicker();
    	});
    });
</script>

<div class="job_seeker_login leftFormHolderResumepage" id="cert${certPositionId}">
<p class="borderBottomDotted marginBottom15">&nbsp;</p>
	
	<div class="rowEvenNewSpacing">
		<span class="lableText3">Certification Name:</span>
		<form:input path="createResume.listCertForm[${certPositionId}].certificationName" class="job_seeker_password textBox350 focusElement" />
		<div class="floatRight margin0">
			<p class="floatLeft margin0">Delete this Certification section &nbsp;</p> 
			<p class="floatRight margin0"><img
				id="closeCheckOut"
				onclick="removeCertification('cert${certPositionId}',${certPositionId})"
				src="<%= request.getContextPath() %>/resources/images/Close.png"
				class="cursor" title="Delete" alt="Delete" /></p>
			
		</div>
	</div>
 	<div class="rowEvenNewSpacing">
		<span class="lableText3">Certifying Authority:</span>
		<form:input path="createResume.listCertForm[${certPositionId}].certifyingAuthority" class="job_seeker_password textBox350" />
	</div>

	<div class="rowEvenNewSpacing">
		<span class="lableText3"> Received:</span>
		<form:input path="createResume.listCertForm[${certPositionId}].dateOfReceipt" class="job_seeker_Resume datepicker" />
	</div>
	<div class="row MarginBottom10 ">
		<div class="lableText3 marginTop10">Summary:</div>
		<div class="input_grp5 ">
			<form:textarea path="createResume.listCertForm[${certPositionId}].summary" id="Certslimitedtextarea${certPositionId}"
				onKeyDown="limitText(this.form.Certslimitedtextarea${certPositionId},this.form.countdownCerts${certPositionId},1000);"
				onKeyUp="limitText(this.form.Certslimitedtextarea${certPositionId},this.form.countdownCerts${certPositionId},1000);"
				class="textareaBoxCResume" rows="3" cols="45" />
			<p class="magrin_top0 floatLeft"><input readonly type="text" class="input2000_width" name="countdownCerts${certPositionId}" size="3" value="1000">characters remaining.</p>
		</div>
	</div> 
</div>