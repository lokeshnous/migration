 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="job_seeker_login leftFormHolderResumepage">
<p class="borderBottomDotted marginBottom15">&nbsp;</p> 
	<div class="rowEvenNewSpacing">
		<span class="lableText3">Name:</span>
		<form:input path="createResume.listRefForm[${refPositionId}].name"
			class="job_seeker_password textBox350" />
	</div>

	<div class="rowEvenNewSpacing">
		<span class="lableText3">Job Title:</span>
		<form:input path="createResume.listRefForm[${refPositionId}].jobTitle"
			class="job_seeker_password textBox350" />
	</div>
	<div class="rowEvenNewSpacing">
		<span class="lableText3">Company Name:</span>
		<form:input path="createResume.listRefForm[${refPositionId}].companyName"
			class="job_seeker_password textBox350" />
	</div>
	<div class="rowEvenNewSpacing">
		<span class="lableText3">Phone Number:</span>

		<form:input path="createResume.listRefForm[${refPositionId}].phoneNo"
			class="job_seeker_password textBox350" />
	</div>
	<div class="rowEvenNewSpacing">
		<span class="lableText3">Email Address:</span>
		<form:input path="createResume.listRefForm[${refPositionId}].email"
			class="job_seeker_password textBox350" />
		<span class="required"></span>
	</div>
	<div class="rowEvenNewSpacing">
		<span class="lableText3">Reference Type:</span><span
			class="floteleft"> 
			<form:radiobutton path="createResume.listRefForm[${refPositionId}].referenceType" value="Professional" /> 
			<label>Professional</label>
		</span>&nbsp;&nbsp;&nbsp;<span class="floteleft"> <form:radiobutton
				path="createResume.listRefForm[${refPositionId}].referenceType" value="Personal" /> <label>Personal</label>
		</span>
	</div>
	<div class="rowEvenNewSpacing MarginBottom10">
		<span class="lableText3"></span> 
	</div>
</div>