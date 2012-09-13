<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Healthcare Jobs</title>
<link href="../resources/js/colorPicker.css" type='text/css'
	rel='stylesheet'>
<jsp:include page="common/include.jsp" />

<script src="../resources/js/jquery.dataTables.nightly.js"></script>
<script src="../resources/js/searchResultsdatatable.js"></script>

<script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		$('#colorPkr').colorPicker();
		var error = $('#FormErrorDisplayText').text();
		if (error.length > 0) {
			alert(error);
			cancelProcess();
		}
	});
</script>

<script type="text/javascript"
	src="../resources/js/jquery.colorPicker.js"></script>
<script type="text/javascript">
	function cancelProcess() {
		window.location.href = '${pageContext.request.contextPath}/employer/employerDashBoard.html';
	}
</script>

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
					<div id="FormErrorDisplayText" class="FormErrorDisplayText"
						align="center">${error}</div>
					<!-- ad_col_right -->
					<!-- content_wrapper -->
					<div class="popupHeader Padding0  OrangeBG marginBottom5">
						<h2>MANAGE FEATURED EMPLOYER PROFILE</h2>
						<span class="floatRight marginRight10"><a
							href="<%=request.getContextPath()%>/employer/employerDashBoard.html"
							class="link_color3_emphasized FontSize12 FontWeight">Back to
								Dashboard</a></span>
					</div>
					<div class="row ">
						<div class="row marginTop15">
							<div class="lableTextCoverletterSize">Company Name:</div>
							<div class="input_grp5 ">
								<div class="floatLeft">
									<form:input path="companyName" name="Exclude"
										class="jb_input2Coverletter width300" />
								</div>
							</div>
						</div>

						<div class="row marginTop15">
							<div class="lableTextCoverletterSize marginTop10">Company
								Overview:</div>
							<div class="input_grp5 ">
								<form:textarea path="companyOverview" id="Body Text:"
									class="textareaBoxCResume Height256" rows="5" cols="45"
									name="Body Text:" />

							</div>

						</div>
						<div class="row marginTop15">
							<div class="lableTextCoverletterSize">Company Website:</div>
							<div class="input_grp5 ">

								<div class="floatLeft">
									<form:input path="companyWebsite" name="Exclude"
										class="jb_input2Coverletter width300" />
								</div>
							</div>


						</div>
						<div class="row marginTop15">
							<div class="lableTextCoverletterSize">Company Email
								Address:</div>
							<div class="input_grp5 ">
								<div class="floatLeft">
									<form:input path="companyEmail" name="Exclude"
										class="jb_input2Coverletter width300" />

								</div>
								<div class="FormErrorDisplayText">
									<form:errors path="companyEmail" />
								</div>
							</div>

						</div>
						<div class="row marginTop15">
							<span class="lableTextCoverletterSize"> Primary Color:</span>
							<form:input id="color_id" path="primaryColor" readonly="true"
								class="jb_input2Coverletter InputActiveText" />
							<span>
								<div class="colorPkrArea">
									<input id="colorPkr" name="color1" type="text" />
								</div>
								<div class="toolTip colorPkrAreaToolTip">
									<span class="classic">Select a color that complements
										your brand. </span>

								</div>
							</span>
						</div>

					</div>
					<div class="row marginTop15">
						<div class="lableTextCoverletterSize marginTop10">Company
							News:</div>
						<div class="input_grp5">
							<div class="FloatLeft">
								<form:textarea path="companyNews" id="Body Text"
									class="textareaBoxCResume Height256" rows="5" cols="45"
									name="Body Text:" />
							</div>
							<div class="toolTip01 colorPkrAreaToolTip">
								<span class="classic">Describe what's going on at your
									company here. Attract job-seekers by mentioning awards you've
									recently won, expansions currently in the works or upcoming
									events you have planned.</span>
							</div>

						</div>
					</div>
					<div class="row marginTop15">
						<div class="lableTextCoverletterSize">Promotional Media:</div>
						<div class="input_grp5 ">
							<div class="floatLeft">

								<form:input path="positionalMedia" name="textfield4" type="file"
									id="textfield4" size="20"
									class="job_seeker_login_email fileType" />

							</div>
							<div class="toolTip colorPkrAreaToolTip">
								<span class="classic">Upload a video, slideshow or
									animated .gif that shines the spotlight on your facility or
									health system.</span>
							</div>
							<div class="FormErrorDisplayText">
								<form:errors path="positionalMedia" />
							</div>
						</div>
					</div>
					<div class="row marginTop15">
						<div class="lableTextCoverletterSize">Logo:</div>
						<div class="input_grp5 ">
							<div class="floatLeft">
								<form:input path="logoUrl" name="textfield4" type="file"
									id="textfield4" size="20"
									class="job_seeker_login_email fileType" />
							</div>
							<div class="FormErrorDisplayText">
								<form:errors path="logoUrl" />
							</div>
						</div>
					</div>
					<div class="row paddingBottom10">
						<div class="rowEvenNewSpacing marginTop20 paddingBottom10">
							<span class="floatLeft marginTop10"> <input type="submit"
								value="Save" name="Save" class="orange" /> <input type="button"
								value="Cancel" onclick="cancelProcess()" class="orange"
								name="Cancel" /> <%-- <a href="<%=request.getContextPath()%>/employer/employerDashBoard.html"
						class="btn_sm orange">Cancel</a> --%></span>
						</div>

					</div>


				</div>

				<div class="clearfix"></div>
				<div class="ad_wrapper">
					<img src="../resources/images/ads/banner_ad_fpo.png" />
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