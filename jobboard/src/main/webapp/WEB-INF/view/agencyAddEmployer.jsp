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
<link rel="stylesheet" type="text/css" href="jquery.autocomplete.css" />
</head>
<body>
	<!-- JAVASCRIPT FILES -->
	<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
	<script type="text/javascript"
		src="javascripts/jquery.cycle.all.min.js"></script>
	<script type="text/javascript" src="javascripts/slider.js"></script>
	<script type="text/javascript" src="javascripts/jquery.megamenu.js"></script>
	
	<script type="text/javascript" src="jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="jquery.autocomplete.min.js"></script>
	<script type="text/javascript">
		jQuery(document)
				.ready(
						function() {						
							//Auto complete for employer name
							 $("#emplyrNameAutoComplte")
									.autocomplete(
											{
												source : '${pageContext.request.contextPath}/agency/getEmployerNamesList.html',
												select : function(event, ui) {
													$("#emplyrNameAutoComplte")
															.val(ui.item.value);
													$.ajax({
														url: '${pageContext.request.contextPath}/agency/getEmployerDetails.html?name='+$("#emplyrNameAutoComplte").val(),
														success : function(data) {															
															$('#city').val(data.city);
															$('#street').val(data.street);		
															$('#zipCode').val(data.postcode);
															$('#state').val(data.state);	
															$('#country').val(data.country);
															$('#phone').val(data.phone);
															$('#facilityId').val(data.facilityId);
														},
													});	
												},
											});
							jQuery(".megamenu").megamenu();
						});

		function MM_jumpMenu(targ, selObj, restore) { //v3.0
			eval(targ + ".location='"
					+ selObj.options[selObj.selectedIndex].value + "'");
			if (restore)
				selObj.selectedIndex = 0;
		}
	</script>
</head>

<body class="job_board">
	<form:form method="post"
		action="../agency/addEmployerDetails.html"
		commandName="empRegisterForm" enctype="multipart/form-data">
		<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
			style="display: block">
			<div class="popupHeader">
				<h2>ADD EMPLOYER</h2>
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
					<span class="lableText3">Street Address:</span> <input readonly
						type="text" name="street" id="street" class="job_seeker_email" /> <span
						class="required">(Required)</span>
				</div>

				<div class="rowEvenNewSpacing">
					<span class="lableText3">City:</span> <input readonly type="text"
						name="city" id="city" class="job_seeker_email" /> <span class="required">(Required)</span>
				</div>				

				<div class="rowEvenNewSpacing">
					<span class="lableText3">State:</span> <input readonly type="text"
						name="state" id="state" class="job_seeker_email" /> <span
						class="required">(Required)</span>
				</div>

				<div class="rowEvenNewSpacing">
					<span class="lableText3">Country:</span> <input readonly
						type="text" id="country" name="country" class="job_seeker_email" /> <span
						class="required">(Required)</span>
				</div>

				<div class="rowEvenNewSpacing">
					<span class="lableText3">Zip Code:</span> <input readonly
						type="text" id="zipCode" name="zipCode" class="job_seeker_email" /> <span
						class="required">(Required)</span>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Phone:</span> <input readonly type="text"
						name="primaryPhone" id="phone" class="job_seeker_email" /> <span
						class="required">(Required)</span>
				</div>
				<div class="rowEvenNewSpacing marginTop10 paddingBottom10">
					<span class="floatLeft marginTop10"><input type="submit"
						style="margin-top: -4px;" value="Save" class="btn_sm orange">
						<a href="" class="btn_sm orange">Cancel</a></span>
				</div> 
				<div class="clearfix"></div>

			</div>
			<div class="clearfix"></div>
		</div>
	</form:form>
</body>
</html>