<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />
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
			<h2>SAVE THIS SEARCH</h2>
			<img src="../resources/images/Close.png" width="19" class="nyroModalClose"
				height="19" alt="Close">
		</div>

		<div class="popUpContainerWrapper">
			<form action="" method="">
				<div class="MidContent_Wrapper">
					<span class="floatleft">This feature is only available for
						registered users.Click here to <a
						href="../commonLogin/login.html?page=jobSeeker" id="id">Signin
					</a>now.
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