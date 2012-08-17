<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>
<link href="../resources/js/colorPicker.css" type='text/css'
	rel='stylesheet'>
<jsp:include page="common/include.jsp" />
<script type="text/javascript">
	jQuery(document).ready(function() {
		$('#colorPkr').colorPicker();
	});
</script>

<script type="text/javascript"
	src="../resources/js/jquery.colorPicker.js"></script>
</head>

<body class="job_board">
	<form:form method="Post" action="saveemployerprofile.html"
		commandName="employerProfileManagementForm"
		enctype="multipart/form-data">
		<div class="ad_page_top">
			<img src="../resources/images/ads/banner_ad_fpo.png" />
		</div>
		
		<div class="main_wrapper_outside">
			<div class="main_wrapper_inside">


				<div class="main">
					<jsp:include page="../templates/templates_header.jsp"></jsp:include>
					<div class="clearfix"></div>
					<!--nav-->

					<!-- ad_col_right -->
					<!-- content_wrapper -->
					<div class="popupHeader Padding0  OrangeBG marginBottom5">
						<h2>MANAGE FEATURED EMPLOYER PROFILE</h2>
						<span class="floatRight marginRight10"><a href="#"
							class="link_color3_emphasized FontSize12 FontWeight">Back to
								Dashboard</a></span>
					</div>
					<div class="row ">
						<div class="row marginTop15">
							<div class="lableTextCoverletter width150">Company Name:</div>
							<div class="input_grp5 ">
								<div class="floatLeft">
									<form:input path="companyName" name="Exclude"
										class="jb_input2Coverletter width300" />
								</div>
							</div>
						</div>

						<div class="row marginTop15">
							<div class="lableTextCoverletter marginTop10 width150">
								Company Overview:</div>
							<div class="input_grp5 ">
								<form:textarea path="companyOverview" id="Body Text:"
									class="textareaBoxCResume Height256" rows="5" cols="45"
									name="Body Text:" />

							</div>




						</div>
						<div class="row marginTop15">
							<div class="lableTextCoverletter width150">Company Website:</div>
							<div class="input_grp5 ">

								<div class="floatLeft">
									<form:input path="companyWebsite" name="Exclude"
										class="jb_input2Coverletter width300" />
								</div>
							</div>
						</div>
						<div class="row marginTop15">
							<div class="lableTextCoverletter width150">Company Email
								Address:</div>
							<div class="input_grp5 ">
								<div class="floatLeft">
									<form:input path="companyEmail" name="Exclude"
										class="jb_input2Coverletter width300" />

								</div>
							</div>
						</div>
						<div class="floatLeft marginTop5">
										<span class="lableText3"> Primary Color:</span>
							         	 <form:input id="color_id" path="primaryColor" readonly="true" class="jb_input2Coverletter InputActiveText" value="HEX #"/>
							        <div class="floatRight"><input id="colorPkr" name="color1" type="text"  value="#333399" /></div></div>
							         <div class="toolTip marginTop6 marginleft5"><span class="classic">Select a color that complements your brand.</span></div>
							         	
									  </div>
						<div class="row marginTop15">
							<div class="lableTextCoverletter marginTop10 width150">
								Company News:</div>
							<div class="input_grp5">
								<div class="FloatLeft">
									<form:textarea path="companyNews" id="Body Text"
										class="textareaBoxCResume Height256" rows="5" cols="45"
										name="Body Text:" />
								</div>
								<div class="toolTip marginTop10 marginLeft5">
									<span class="classic">Describe what's going on at your
										company here. Attract job-seekers by mentioning awards you've
										recently won, expansions currently in the works or upcoming
										events you have planned.</span>
								</div>

							</div>
						</div>
						<div class="row marginTop15">
							<div class="lableTextCoverletter width150">Promotional
								Media:</div>
							<div class="input_grp5 ">
								<div class="floatLeft">

									<form:input path="positionalMedia" name="textfield4"
										type="file" id="textfield4" size="20"
										class="job_seeker_login_email fileType" />
								</div>
								<div class="toolTip marginTop6 marginLeft5">
									<span class="classic">Upload a video, slideshow or
										animated .gif that shines the spotlight on your facility or
										health system.</span>
								</div>
							</div>
						</div>
						<div class="row marginTop15">
							<div class="lableTextCoverletter width150">Logo:</div>
							<div class="input_grp5 ">
								<div class="floatLeft">
									<form:input path="logoUrl" name="textfield4" type="file"
										id="textfield4" size="20"
										class="job_seeker_login_email fileType" />
								</div>
							</div>
						</div>
						<div class="row paddingBottom10">
							<div class="rowEvenNewSpacing marginTop20 paddingBottom10">
								<span class="floatLeft marginTop10"> <input
						type="submit" value="Save" name="Save" class="btn_sm orange" />
						<a href="#"
						class="btn_sm orange">Cancel</a></span>
							</div>

						</div>


					</div>

					<div class="clearfix"></div>
					<div class="ad_wrapper">
						<img src="../resources/images/ads/banner_ad_fpo.png"  />
					</div>

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