<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="common/include.jsp" />
<title>ADVANCE Heathcare Jobs</title>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>APPLY JOB</h2>
			<img src="<%= request.getContextPath() %>/resources/images/Close.png" width="19" class="nyroModalClose" title="Close"
				height="19" alt="Close">
		</div>

		<div class="popUpContainerWrapper">
			<form action="" method="">
				<div class="MidContent_Wrapper">
					<span class="floatleft">You need to be
						logged in to Apply jobs. <a href="<%= request.getContextPath() %>/commonLogin/login.html?page=jobSeeker&isRedirect=true" id="id">Click here to log
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