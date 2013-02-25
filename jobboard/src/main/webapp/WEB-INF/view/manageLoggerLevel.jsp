<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />
<link href="../resources/css/JB.css" rel="stylesheet" type="text/css" />
<link href="../resources/css/jquery.megamenu.css" rel="stylesheet"
	type="text/css" />
<link href="../resources/css/SliderStyles.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" type="text/css" media="screen"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="jquery.autocomplete.css" />
<script type="text/javascript">
	$("#SetLogLevel").keypress(function(event) {
		if (event.which == 13) {
			return false;
		}
	});
	jQuery(document)
			.ready(
					function() {
						$("#SetLogLevel")
								.click(
										function(event) {
											var logLevel = $
													.trim($("#logLevel").val().toUpperCase());
											$("#ErrorMsg").text("");
											if (logLevel == '') {
												$("#ErrorMsg")
														.text(
																"Please enter a Log level!");
												return false;
											}
											if (logLevel.match(/^\d+$/)) {
												$("#ErrorMsg")
														.text(
																"Please enter an valid Log level!");
												return false;
											}
											if (logLevel != 'INFO'
													&& logLevel != 'DEBUG'
													&& logLevel != 'TRACE'
													) {
												$("#ErrorMsg")
														.text(
																"Please enter either INFO, DEBUG or TRACE as Log level!");
												return false;
											}
											$
													.ajax({
														url : "${pageContext.request.contextPath}/admin/changeLogLevel.html?logLevel="
																+ logLevel,
														success : function(data) {
															if (data.status == 'error') {
																$("#ErrorMsg")
																		.text(
																				"An error occurred while changing log level. Please try later.");
															}
															if (data.status == '') {
																$("#ErrorMsg")
																		.text(
																				"Log level applied successfully");
															}
														},
														error : function(
																response) {
															alert("Server Error : "
																	+ response.status);
														},
														complete : function() {

														}
													});
										});

						jQuery(".megamenu").megamenu();
						$.nmFilters({
							custom : {
								afterShowCont : function(nm) {
									$('#logLevel').focus();
								}
							}
						});

						function closePopup() {
							parent.window.location.reload();
						}

					});
</script>

</head>
<body class="job_board">
	<form:form method="GET" id="formid" name="formid">
		<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
			style="display: block">
			<div class="popupHeader">
				<h2>MANAGE LOGGER LEVEL</h2>
				<img id="closeCheckOut"
					src="<%=request.getContextPath()%>/resources/images/Close.png"
					class="nyroModalClose cursor" title="Close" alt="Close" />
			</div>
			<div class="row">
				<span class="lableText3"></span><span id="ErrorMsg"
					class="FormErrorDisplayText01"> </span>
			</div>
			<div class="popUpContainerWrapper">

				<div class="rowEvenNewSpacing">
					<span class="lableText3"> Logger level </span> <input
						name="logLevel" id="logLevel" class="job_seeker_email focus"
						type="text" />&nbsp;&nbsp;&nbsp; <input type="button" value="Set"
						name="SetLogLevel" id="SetLogLevel" class="btn_sm orange cursor" />
					<!-- <div class="toolTip">
						<span class="classic">Available Log levels are INFO, DEBUG
							and TRACE</span>
					</div> -->
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</form:form>
</body>

</html>