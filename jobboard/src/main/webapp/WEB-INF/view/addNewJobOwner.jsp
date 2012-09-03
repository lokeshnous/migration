<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>

<jsp:include page="common/include.jsp" />
<script type="text/javascript">
		    jQuery(document).ready(function(){
		    $("#addNewJobOwnerPopUp").displaypopup("#accessPermissioPopUp","770","360");
		    jQuery(".megamenu").megamenu();
		});
		</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>ADD NEW JOB OWNER</h2>
			 <img src="../resources/images/Close.png" width="19" height="19" class="nyroModalClose" alt="close">
		</div>

		<div class="popUpContainerWrapper">
			<form action="" method="">
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Job Owner Name:</span> <input type="text"
						name="EmailAddress" class="job_seeker_email width300" />
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Job Owner Email Address:</span> <input
						type="text" name="EmailAddress" class="job_seeker_email width300" />
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText4"> </span>
					<div class="floatLeft marginTop5 marginRight10">
						<label> <input name="RadioGroup10" type="radio"
							id="RadioGroup1_0" value="radio" checked> Full Access
						</label>
					</div>

					<div class="floatLeft marginTop5">
						<label> <input type="radio" name="RadioGroup10"
							value="radio" id="RadioGroup1_0"> Post / Edit Only
						</label>
					</div>



					<div class="rowEvenNewSpacing marginTop10 paddingBottom10">
						<span class="floatLeft marginTop10"><a href=""
							class="btn_sm orange">SAVE</a> <a href="" class="btn_sm orange">Cancel</a></span>
					</div>
					<div class="clearfix"></div>
			</form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>