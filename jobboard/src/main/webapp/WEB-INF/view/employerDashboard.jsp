<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="common/include.jsp" />
<title>ADVANCE Heathcare Jobs</title>
<!-- JAVASCRIPT FILES -->
<script type="text/javascript" src="../resources/js/slider.js"></script>
<link href="../resources/css/jquery-ui.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" language="javascript"
	src="/media/js/jquery.js"></script>
<script src="../resources/js/jquery.dataTables.nightly.js"></script>
<script src="../resources/js/resumesearchresult.js"></script>

<script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery(".megamenu").megamenu();

	});
</script>

</head>

<body class="job_board">
	<div class="ad_page_top">
		${adPageTop}
	</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">
			<div class="main">
				<jsp:include page="../templates/templates_header.jsp"></jsp:include>

				<div class="clearfix"></div>
				<!--Start:MidContant-->
				<div class="row">
						<!-- Include the resume header page -->
						<jsp:include page="jobboardsearchresumeheader.jsp"></jsp:include>
						<div class="searchContent" style="display: none;" id="resumeTableContent">
							<jsp:include page="jobboardsearchresumeresultbody.jsp"></jsp:include>
						</div>
						<!-- search_info_box2 -->
						<!-- browse_bar -->
				</div>

				<!-- Add include for employee dashboard content -->

				<div class="mainTwo otherContent" style="display: block">
					<jsp:include page="jobboardemployerdashboardcontent.jsp"></jsp:include>
				</div>


				<div class="clearfix"></div>





				<!--Start:MidContant-->
				<div class="clearfix"></div>
				<!-- content_wrapper -->
				<div class="ad_wrapper">
					${adPageBtm}
				</div>
				<!-- ad_wrapper -->
			</div>
		</div>
		<!-- main -->

	</div>
	<!-- end main_wrapper_inside -->

	<!-- end main_wrapper_outside -->

	<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
	<!-- footer_wrapper -->

</body>
</html>