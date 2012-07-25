<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>

<!-- STYLESHEETS -->
<link href="../resources/css/JB.css" rel="stylesheet" type="text/css" />
<link href="../resources/css/jquery.megamenu.css" rel="stylesheet"
	type="text/css" />
<link href="../resources/css/SliderStyles.css" rel="stylesheet"
	type="text/css">

<!--[if IE]>
	<link href="stylesheets/ie.css" rel="stylesheet" type="text/css">
<![endif]-->


<!-- JAVASCRIPT FILES -->
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript"
	src="../resources/js/jquery.cycle.all.min.js"></script>
<script type="text/javascript" src="../resources/js/slider.js"></script>
<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>

<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery(".megamenu").megamenu();
	});
	function MM_jumpMenu(targ, selObj, restore) { //v3.0
		eval(targ + ".location='" + selObj.options[selObj.selectedIndex].value
				+ "'");
		if (restore)
			selObj.selectedIndex = 0;
	}
</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>FORGOT YOUR PASSWORD?</h2>
			<a href="#"><img src="images/Close.png" width="19" height="19"
				alt=""></a>
		</div>

		<div class="popUpContainerWrapper">
			<form:form method="Post" action="jobSeekerForgotYourPasswordPagePopUp.html" commandName="loginForm">
				<div class="rowEvenNewSpacing borderBottomDotted paddingBottom10 marginTop0">
					<p>Enter the email address you use for this account and click
						the 'SEND' button. We'll email you a security code that will allow
						you to reset your password.</p>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Email Address:</span>
					<form:input type="text" path="emailAddress"
						class="job_seeker_email" />
					<form:errors path="emailAddress" />
					<span class="required">(Required)</span>
				</div>
				<div class="rowEvenNewSpacing marginTop10 paddingBottom10">
					<span class="floatLeft marginTop10"> 
					     <input type="submit" class="btn_sm orange" value="Send"/>
					     <input type="" class="btn_sm orange" value="Cancel"/>
					</span>
				</div>
				<div class="clearfix"></div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>