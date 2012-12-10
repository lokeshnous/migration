<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>

<jsp:include page="common/include.jsp" />
<script type="text/javascript">
	function closePopup() {
		parent.window.location.reload();
	}
	jQuery(document)
			.ready(
					function() {
						$("#sendButton")
								.click(
										function(event) {
											if (validate()) {
												$("#errmsg").html("<span>Processing...</span>");
												var file = $("#filePath").val();
												$("form").attr("action","${pageContext.request.contextPath}/anonymoususerjobapply/saveAnonymousUserJobapply.html");
												$("#applyJobForm").submit();
												alert("Job applied successfully!");	
											}
										});
					});
	function validate() {
		var userName = $("#userName").val();
		var userEmail = $("#userEmail").val();
		var file = $("#filePath").val();
		var x = userEmail.indexOf('@');
		var y = userEmail.lastIndexOf('.');
		var result = true;
		if (userName.length == 0) {
			$("#userNameError").text("Please enter the name");
			result = false;
		} else {
			$("#userNameError").text("");
		}
		if (x == -1 || y == -1 || (x + 2) >= y) {
			$("#userEmailError")
					.text("Please enter the correct Email address");
			result = false;
		} else {
			$("#userEmailError").text("");
		}
		if (!file.toLowerCase().match(/(\.doc|\.pdf|\.docx)$/)) {
			$("#filePathError").text(
					"Please choose the appropriate file format");
			result = false;
		} else {
			$("#filePathError").text("");
		}
		if (!result) {
			return false;
		} else {
			return true;
		}
	}
</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>GUEST USER FORM</h2>
			<a href=""><img src="../resources/images/Close.png" title="Close"
				onclick="closePopup();" width="19" height="19" alt=""></a>
		</div>
		<div id="errmsg" style="font: bold; color: red" align="left"></div>
		<div class="popUpContainerWrapper">
			<form:form id="applyJobForm" action="saveAnonymousUserJobapply.html" method="POST"
				commandName="jobApplicationForm" enctype="multipart/form-data">
				<div class="rowEvenNewSpacing">
					<h3>Send Resume</h3>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Name:</span>
					<form:input id="userName" path="userName" class="job_seeker_email" />
					<span class="required">(Required)</span>
				</div>
				<div class="errorRow">
					<div id="userNameError" style="color: red" align="left"></div>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Email Address:</span>
					<form:input id="userEmail" path="userEmail"
						class="job_seeker_email" />
					<span class="required">(Required)</span>
				</div>
				<div class="errorRow">
					<div style="color: red" align="left" id="userEmailError"></div>
				</div>

				<div class="rowEvenNewSpacing">
					<span class="lableText3">Upload Resume File:</span>
					<div class="floatLeft">
						<form:input path="fileContent" type="file" id="filePath" size="20"
							class="job_seeker_login_email fileType" />
					</div>
					<div class="toolTip marginTop8">
						<span class="classic">Select the resume you want to upload. Accepted file types are .doc, .docx and PDF only</span>
					</div>
					<span class="required">(Required)</span>
					
				</div>
				<div class="errorRow"> <div style="color: red" align="left" id="filePathError"></div></div>
					<div class="popUpButtonRow">

						<input type="button" class="btn_sm orange" id="sendButton"
							value="Send" /> 
							<input type="button" class="btn_sm orange" onclick="closePopup();" id="cancelButton"
							value="Cancel" />
							
					</div>
					<div class="clearfix"></div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>