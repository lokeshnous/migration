<%@ page isELIgnored="false"%>
<!DOCTYPE html>
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
	<link href="../resources/css/ie.css" rel="stylesheet" type="text/css">
<![endif]-->
<!-- js files for modalpopup------------------------------------------------- -->
<script src="../resources/js/jquery-1.7.1.js"></script>
<script src="../resources/js/jquery-1.7.1.min.js"></script>

<script
	src="../resources/jquery.nyroModal/js/jquery.nyroModal.custom.js"></script>

<script
	src="../resources/jquery.nyroModal/js/jquery.nyroModal.custom.min.js"></script>
<link href="../resources/jquery.nyroModal/styles/nyroModal.css"
	rel="stylesheet" type="text/css">

<style type="text/css" media="screen">
@import
	url("${pageContext.request.contextPath}/resources/jquery.nyroModal/styles/nyroModal.css")
	;
</style>
<!-- -------------------------------------------------------------------------- -->


<!-- JAVASCRIPT FILES -->
<script type="text/javascript" src="javascripts/jquery.cycle.all.min.js"></script>
<script type="text/javascript" src="javascripts/slider.js"></script>
<script type="text/javascript" src="javascripts/jquery.megamenu.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		$("#id").click(function() {
			parent.window.location.href = "navigateToLogin.html";
			parent.$.nmTop().close();

		});
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
			<h2>SAVE THIS JOB</h2>
			<a href=""><img
				src="../resources/images/Close.png" width="19" height="19"
				onclick="parent.$.nmTop().close();" alt="close"></a>
		</div>

		<div class="popUpContainerWrapper">
			<form action="" method="">
				<div class="rowEvenNewSpacing marginTop0">
					<span class="lableText3 width505 TextAlignL">You need to be
						logged in to save jobs. <a href="../loginFormForJobSeeker/login.html" id="id">Click here to log
							in now.</a>
					</span>
				</div>

				<div class="rowEvenNewSpacing marginTop10 paddingBottom10">
					<span class="floatLeft marginTop10"> <a
						href="" onclick="parent.$.nmTop().close();"
						class="btn_sm orange">Cancel</a></span>
				</div>
				<div class="clearfix"></div>
			</form>
		</div>
		<div class="clearfix"></div>
	</div>
</body>
</html>