<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Healthcare Jobs</title>

<!-- STYLESHEETS -->
<link href="../resources/css/JB.css" rel="stylesheet" type="text/css" />
<link href="../resources/css/jquery.megamenu.css" rel="stylesheet"
	type="text/css" />
<link href="../resources/css/SliderStyles.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" type="text/css" media="screen"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css">
</head>
<body>
	<!-- JAVASCRIPT FILES -->
	<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
	<script type="text/javascript"
		src="javascripts/jquery.cycle.all.min.js"></script>
	<script type="text/javascript" src="javascripts/slider.js"></script>
	<script type="text/javascript" src="javascripts/jquery.megamenu.js"></script>
	<script type="text/javascript">
		jQuery(document)
				.ready(
						function MM_jumpMenu(targ, selObj, restore) { //v3.0
							eval(targ
									+ ".location='"
									+ selObj.options[selObj.selectedIndex].value
									+ "'");
							if (restore)
								selObj.selectedIndex = 0;
						});
	</script>
</head>

<body class="job_board">

	<form:form method="get"
		action="../agency/saveEmployerDetails.html"
		commandName="empRegisterForm" enctype="multipart/form-data">
		<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
			style="display: block">
			<div class="popupHeader">
				<h2>Edit EMPLOYER</h2>
				<a href="#"><img src="../resources/images/Close.png" width="19" title="Close"
					height="19" alt="" onclick="parent.$.nmTop().close();"></a>
			</div>
			<form:hidden path="facilityId" id="facilityId"/>
			<div class="popUpContainerWrapper">
				<div class="rowEvenNewSpacing marginTop10 marginLeft15">
					<h3>EMPLOYER DETAILS</h3>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Employer Name:</span>
					<form:input path="firstName" class="job_seeker_email"
						id="emplyrNameAutoComplte" />
					<span class="required">(Required)</span>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Street Address:</span>
					<form:input path="street" id="street" class="job_seeker_email" />
					<span class="required">(Required)</span>
				</div>

				<div class="rowEvenNewSpacing">
					<span class="lableText3">City:</span>
					<form:input path="city" id="city" class="job_seeker_email" />
					<span class="required">(Required)</span>
				</div>
				<c:forEach items="${empRegisterForm.listProfAttribForms}"
					var="profAttrib" varStatus="status">
					<c:if test="${profAttrib.strLabelName =='State / Province'}">
						<div class="row">
							<span class="lableTextSelect marginTop13">State:</span>
							<form:select name="Country" id="Country"
								path="listProfAttribForms[${status.index}].strLabelValue"
								class="jb_input3 jb_input_width3">
								<form:option value="0" label="Select" />
								<form:options items="${profAttrib.dropdown}"
									itemValue="optionId" itemLabel="optionName" />
							</form:select>
							<span class="required marginTop8">(Required)</span>
						</div>
					</c:if>
					<c:if test="${profAttrib.strLabelName == 'Country'}">
						<div class="row">
							<span class="lableTextSelect marginTop13 ">Country:</span>
							<form:select
								path="listProfAttribForms[${status.index}].strLabelValue"
								name="Country" id="Country" class="jb_input3 jb_input_width3">
								<form:option value="0" label="Select" />
								<form:options items="${profAttrib.dropdown}"
									itemValue="optionId" itemLabel="optionName" />
							</form:select>
							<span class="required marginTop8">(Required)</span>
						</div>
					</c:if>
				</c:forEach>

				<!-- <div class="rowEvenNewSpacing">
					<span class="lableText3">State:</span> <input readonly type="text"
						name="state" id="state" class="job_seeker_email" /> <span
						class="required">(Required)</span>
				</div>

				<div class="rowEvenNewSpacing">
					<span class="lableText3">Country:</span> <input readonly
						type="text" id="country" name="country" class="job_seeker_email" /> <span
						class="required">(Required)</span>
				</div>
 -->
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Zip Code:</span>
					<form:input id="zipCode" path="zipCode" class="job_seeker_email" />
					<span class="required">(Required)</span>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Phone:</span>
					<form:input path="primaryPhone" id="primaryPhone"
						class="job_seeker_email" />
					<span class="required">(Required)</span>
				</div>
				<div class="rowEvenNewSpacing marginTop10 paddingBottom10">
					<span class="floatLeft marginTop10"><input type="submit"
						style="margin-top: -4px;" value="Save" class="btn_sm orange">
						<a href="" class="btn_sm orange"
						onclick="parent.$.nmTop().close();">Cancel</a></span>
				</div>
				<div class="clearfix"></div>

			</div>
			<div class="clearfix"></div>
		</div>
	</form:form>
</body>
</html>