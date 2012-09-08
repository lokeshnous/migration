<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>


<!-- ../resources/css -->
<link href="../resources/css/JB.css" rel="stylesheet" type="text/css" />
<link href="../resources/css/jquery.megamenu.css" rel="stylesheet"
	type="text/css" />
<link href="../resources/css/SliderStyles.css" rel="stylesheet"
	type="text/css">

<!-- JAVASCRIPT FILES -->
<script type="text/javascript" src="../resources/js/slider.js"></script>
<link href="../resources/css/jquery.dataTables.css" rel="stylesheet"
	type="text/css">
<link href="../resources/css/jquery-ui.css" rel="stylesheet"
	type="text/css">
<link href="../resources/css/jobsearchResults.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" language="javascript"
	src="/media/js/jquery.js"></script>
<script src="../resources/js/jquery.dataTables.nightly.js"></script>
<script src="../resources/js/searchResultsdatatable.js"></script>
<script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>

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
			<h2>Impersonation</h2>
			<img id="closeCheckOut" src="../resources/images/Close.png"
				width="19" height="19" alt="" class="nyroModalClose">
		</div>
		<div class="popUpContainerWrapper">
			<div class="popUpContainerWrapper">
				<form:form method="get" action="../admin/authenticate.html"
					commandName="adminLoginForm">
					<div class="row ">
						<div class="row marginTop15">
							<div class="lableTextCoverletter width285">Email Address of
								Employer / Agency / JobSeeker :</div>
							<div class="input_grp5 width400 ">
								<div class="floatLeft width400">
									<form:input path="jobSeekerOrEmpOrAgeEmail" name="Exclude"
										class="jb_input2Coverletter FontSize15" />
								</div>
							</div>
						</div>
						<div class="row">
							<FONT class="validationMsgPadding" color="red"><form:errors
									path="jobSeekerOrEmpOrAgeEmail" /></FONT>
						</div>
						<div class="row marginTop15">
							<div class="lableTextCoverletter width285">User Email
								Address :</div>
							<div class="input_grp5 width400 ">
								<div class="floatLeft width400">
									<input type="text" name="Exclude"
										class="jb_input2Coverletter FontSize15" />
								</div>
							</div>
						</div>
						<div class="row marginTop15">
							<div class="lableTextCoverletter width285">Password :</div>
							<div class="input_grp5 width400 ">
								<div class="floatLeft width400">
									<input type="password" name="Exclude"
										class="jb_input2Coverletter FontSize15" />
								</div>
							</div>
						</div>
						<div
							class="rowEvenNewSpacing marginTop20 paddingBottom10 marginBottom25">
							<span class="floatLeft marginTop10 marginLeft305"><input
								type="submit" value="Impersonate" class="btn_sm orange"><a
								href="" class="btn_sm orange">Cancel</a></span>
						</div>
					</div>
				</form:form>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</body>
</html>