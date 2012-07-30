<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery(".megamenu").megamenu();
	});
</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>Edit Profile Settings</h2>
			<img src="../resources/images/Close.png" width="19" height="19"
				alt="" onclick="parent.$.nmTop().close();">
		</div>

		<div class="popUpContainerWrapper">
			<form:form action="/jobboard/jobseekerregistration/updateJobSeekerProfile.html" method="POST"
				commandName="registerForm" enctype="multipart/form-data">
				<div class="rowEvenNewSpacing">
					<span class="lableText3">First Name:</span>
					<form:input path="firstName" class="job_seeker_password textBox350" />
					<span class="required">(Required)</span>
				</div>
				<div>
					<span class="lableText3"></span> <FONT color="red"><form:errors
							path="firstName" /></FONT>
				</div>

				<div class="rowEvenNewSpacing">
					<span class="lableText3">Middle Name:</span>
					<form:input path="middleName"
						class="job_seeker_password textBox350" />
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Last Name:</span>
					<form:input path="lastName" class="job_seeker_password textBox350" />
					<span class="required">(Required)</span>
				</div>
				<div>
					<span class="lableText3"></span> <FONT color="red"><form:errors
							path="lastName" /></FONT>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Street Address:</span>
					<form:input path="addressLine1"
						class="job_seeker_password textBox350" />
					<span class="required">(Required)</span>

				</div>
				<div>
					<span class="lableText3"></span> <FONT color="red"><form:errors
							path="addressLine1" /></FONT>
				</div>

				<div class="rowEvenNewSpacing">
					<span class="lableText3">City:</span>
					<form:input path="city" class="job_seeker_password textBox350" />
					<span class="required">(Required)</span>
				</div>
				<div>
					<span class="lableText3"></span> <FONT color="red"><form:errors
							path="city" /></FONT>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">State:</span>
					<form:select path="state" class="jb_input3 jb_input_width3">
						<form:option value="0" label="Select" />
						<form:options items="${stateList}" itemValue="stateId"
							itemLabel="stateValue" />
					</form:select>

					<span class="required marginTop8">(Required)</span>
				</div>
				<div>
					<span class="lableText3"></span> <FONT color="red"><form:errors
							path="state" /></FONT>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">ZIP Code:</span>
					<form:input path="postalCode"
						class="job_seeker_password textBox350" />
					<span class="required">(Required)</span>
				</div>
				<div>
					<span class="lableText3"></span> <FONT color="red"><form:errors
							path="postalCode" /></FONT>
				</div>
				<div class="row">
					<span class="lableTextSelect marginTop13 ">Country:</span>
					<form:select path="country" class="jb_input3 jb_input_width3">
						<form:option value="0" label="Select" />
						<form:options items="${countryList}" itemValue="countryId"
							itemLabel="countryValue" />
					</form:select>
					<span class="required marginTop8">(Required)</span>
				</div>
				<div>
					<span class="lableText3"></span> <FONT color="red"><form:errors
							path="country" /></FONT>
				</div>

				<div class="rowEvenNewSpacing">
					<span class="lableText3">E-Mail Address:</span>
					<form:input path="emailId" class="job_seeker_password textBox350" />
					<span class="required">(Required)</span>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Phone Number:</span> 
					<form:input path="phoneNo" class="job_seeker_password textBox350" />
				</div>

				<div class="row marginTop31">
					<h3>Employment Information</h3>
				</div>

				<div class="rowEvenNewSpacing marginTop13">
					<span class="lableText3">My Industry:</span> 
					<form:input path="myIndustry" class="job_seeker_password textBox350" />
					<div class="toolTip marginTop6 marginLeft5">
						<span class="classic">Enter the industry you serve.
							Example: Healthcare</span>
					</div>
					<span class="required">(Required)</span>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">My Profession:</span> 
					<form:input path="myProfession" class="job_seeker_password textBox350" />
					<div class="toolTip marginTop6 marginLeft5">
						<span class="classic">Enter the general field in which you
							work. Example: Respiratory Therapy</span>
					</div>
					<span class="required">(Required)</span>
				</div>

				<div class="rowEvenNewSpacing">
					<span class="lableText3">My Specialty:</span> 
					<form:input path="mySpeciality" class="job_seeker_password textBox350" />
					<div class="toolTip marginTop6 marginLeft5">
						<span class="classic">Enter the area in which you
							specialize. Example: Neonatal/Pediatrics</span>
					</div>
					<span class="required">(Required)</span>
				</div>

				<div class="rowEvenNewSpacing">
					<span class="lableText3">My Job Title:</span> <form:input path="myJobTitle" class="job_seeker_password textBox350" />
					<div class="toolTip marginTop6 marginLeft5">
						<span class="classic">Enter your official job title.
							Example: Registered Respiratory Therapist</span>
					</div>
					<span class="required">(Required)</span>
				</div>
				<div class="row">
					<span class="lableTextSelect marginTop13 ">I am seeking:</span>
					<form:select path="employmentType" class="jb_input3 jb_input_width3">
						<form:option value="0" label="Select" />
						<form:options items="${empTyepList}" itemValue="optionId" itemLabel="optionName" />
					</form:select>
				</div>

				<div class="row marginTop30">
					<h3>Equal Opportunity / Affirmative Action</h3>
				</div>

				<div class="row">
					<span class="lableTextSelect marginTop13 ">Ethnicity:</span> 
				<form:select path="ethenticity" class="jb_input3 jb_input_width3">
					<form:option value="0" label="Select" />
					<form:options items="${ethnicityList}" itemValue="ethencityId" itemLabel="ethencityValue" />
				</form:select>
				</div>


				<div class="row">
					<span class="lableTextSelect marginTop13 ">Gender:</span> 
				<form:select path="gender" class="jb_input3 jb_input_width3">
					<form:option value="0" label="Select" />
					<form:options items="${genderList}" itemValue="genderId" itemLabel="genderName" />
				</form:select>
				</div>


				<div class="row">
					<span class="lableTextSelect marginTop13 ">Veteran Status:</span> 
		 		<form:select path="veteranStatus" class="jb_input3 jb_input_width3">
					<form:option value="0" label="Select" />
					<form:options items="${veteranStatusList}" itemValue="statusId" itemLabel="statusValue" />
				</form:select>
				</div>
				<div class="rowEvenNewSpacing marginTop20 paddingBottom10">
					<span class="floatLeft marginTop10">
					
<!-- 					<a href="/jobboard/jobseekerregistration/updateJobSeekerProfile.html"
						class="btn_sm orange">Save</a>  -->
						<input type="submit" value="Save" class="btn_sm orange"/>
						<a href="#" class="btn_sm orange" onclick="parent.$.nmTop().close();">Cancel</a></span>
				</div>
				<div class="clearfix"></div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>