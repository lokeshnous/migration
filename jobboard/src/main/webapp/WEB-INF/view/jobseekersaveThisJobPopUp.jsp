<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="common/include.jsp" />
<title>ADVANCE Heathcare Jobs</title>

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
			parent.window.location.href = "+.html";
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
			<img src="../resources/images/Close.png" width="19" class="nyroModalClose" title="Close"
				height="19" alt="Close">
		</div>

		<div class="popUpContainerWrapper">
			<form action="" method="">
				<div class="MidContent_Wrapper">
					<span class="floatleft">You need to be
						logged in to save jobs. <a href="../commonLogin/login.html?page=jobSeeker" id="id">Click here to log
							in now.</a>
					</span>
				</div>

				<div class="popUpButtonRow">
					<a class="nyroModalClose btn_sm orange" href="#">Cancel</a>
				</div>
				<div class="clearfix"></div>
			</form>
		</div>
		<div class="clearfix"></div>
	</div>
</body>
</html>