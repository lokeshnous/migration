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
			<form:form method="Post" action="saveJobSeekerProfile.html"
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
					<span class="lableText3">E-Mail Address:</span> <input type="text"
						name="healthCareSubSplty" class="job_seeker_password width350" /><span
						class="required">(Required)</span>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Phone Number:</span> <input type="text"
						name="healthCareSubSplty" class="job_seeker_password width350" />
				</div>

				<div class="row marginTop31">
					<h3>Employment Information</h3>
				</div>

				<div class="rowEvenNewSpacing marginTop13">
					<span class="lableText3">My Industry:</span> <input type="text"
						name="healthCareSubSplty" class="job_seeker_password width350" />
					<div class="toolTip marginTop6 marginLeft5">
						<span class="classic">Enter the industry you serve.
							Example: Healthcare</span>
					</div>
					<span class="required">(Required)</span>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">My Profession:</span> <input type="text"
						name="healthCareSubSplty" class="job_seeker_password width350" />
					<div class="toolTip marginTop6 marginLeft5">
						<span class="classic">Enter the general field in which you
							work. Example: Respiratory Therapy</span>
					</div>
					<span class="required">(Required)</span>
				</div>

				<div class="rowEvenNewSpacing">
					<span class="lableText3">My Specialty:</span> <input type="text"
						name="healthCareSubSplty" class="job_seeker_password width350" />
					<div class="toolTip marginTop6 marginLeft5">
						<span class="classic">Enter the area in which you
							specialize. Example: Neonatal/Pediatrics</span>
					</div>
					<span class="required">(Required)</span>
				</div>

				<div class="rowEvenNewSpacing">
					<span class="lableText3">My Job Title:</span> <input type="text"
						name="healthCareSubSplty" class="job_seeker_password width350" />
					<div class="toolTip marginTop6 marginLeft5">
						<span class="classic">Enter your official job title.
							Example: Registered Respiratory Therapist</span>
					</div>
					<span class="required">(Required)</span>
				</div>
				<div class="row">
					<span class="lableTextSelect marginTop13 ">I am seeking:</span> <select
						name="Country2" id="Country2" class="jb_input3 jb_input_width3">
						<option value="--- Select One ---" selected>--- Select
							One ---</option>
						<option value="Full-Time">Full-Time</option>
						<option value="Part-Time">Part-Time</option>
						<option value="Per Diem">Per Diem</option>
						<option value="Contract/Travel">Contract/Travel</option>
						<option value="Work From Home">Work From Home</option>
						<option value="Locum Tenens">Locum Tenens</option>
					</select>
				</div>

				<div class="row marginTop30">
					<h3>Equal Opportunity / Affirmative Action</h3>
				</div>

				<div class="row">
					<span class="lableTextSelect marginTop13 ">Ethnicity:</span> <select
						name="Country2" id="Country2" class="jb_input3 jb_input_width3">
						<option value="--- Select One ---">--- Select One ---</option>
						<option value="White (Not of Hispanic Origin)">White (Not
							of Hispanic Origin)</option>
						<option value="Black (Not of Hispanic Origin)">Black (Not
							of Hispanic Origin)</option>
						<option value="Asian or Pacific Islander">Asian or
							Pacific Islander</option>
						<option value="American Indian or Alaska Native">American
							Indian or Alaska Native</option>
						<option value="Hispanic / Latino">Hispanic / Latino</option>
					</select>
				</div>


				<div class="row">
					<span class="lableTextSelect marginTop13 ">Gender:</span> <select
						name="Country2" id="Country2" class="jb_input3 jb_input_width3">
						<option selected="" value="--- Select One ---">--- Select
							One ---</option>
						<option value="Male">Male</option>
						<option value="Female">Female</option>
						<option value="Other Gender Identity">Other Gender
							Identity</option>
					</select>
				</div>


				<div class="row">
					<span class="lableTextSelect marginTop13 ">Veteran Status:</span> <select
						name="Country2" id="Country2" class="jb_input3 jb_input_width3">
						<option value="--- Select One ---">--- Select One ---</option>
						<option value="Armed Forces Service Medal Veteran">Armed
							Forces Service Medal Veteran</option>
						<option value="Disabled Veteran">Disabled Veteran</option>
						<option value="Non-Veteran">Non-Veteran</option>
						<option value="One-Year Recently Separated Veteran">One-Year
							Recently Separated Veteran</option>
						<option value="Other Protected Veteran">Other Protected
							Veteran</option>
						<option value="Special Disabled Veteran">Special Disabled
							Veteran</option>
						<option value="Three-Year Recently Separated Veteran">Three-Year
							Recently Separated Veteran</option>
						<option value="Veteran">Veteran</option>
						<option value="Vietnam Era Veteran">Vietnam Era Veteran</option>
					</select>
				</div>
				<div class="rowEvenNewSpacing marginTop20 paddingBottom10">
					<span class="floatLeft marginTop10"><a href="/jobboard/jobseekerregistration/updateJobSeekerProfile.html"
						class="btn_sm orange">Save</a> <a href="#" class="btn_sm orange" onclick="parent.$.nmTop().close();">Cancel</a></span>
				</div>
				<div class="clearfix"></div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>