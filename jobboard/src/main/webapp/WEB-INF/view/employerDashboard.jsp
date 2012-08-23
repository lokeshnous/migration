<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="common/include.jsp" />
<title>ADVANCE Heathcare Jobs</title>

<script type="text/javascript">
	jQuery(document).ready(function() {
		$("#changePassword").displaypopup("#changePassword", "780", "370");
		 $("#accountSettingpopUp").displaypopup("#accountSettingpopUp","770","360");
		jQuery(".megamenu").megamenu();
	});
</script>

</head>

<body class="job_board">
	<div class="ad_page_top">
		<img src="../resources/images/ads/banner_ad_fpo.png" />
	</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">
			<div class="main">
			<jsp:include page="../templates/templates_header.jsp"></jsp:include>
				
				<div class="clearfix"></div>
				<!--Start:MidContant-->
				<div class="MidContent_Wrapper floatLeft">
					<div class="job_search_Resume">
						<form method="">
							<div class="row ">

								<!-- Include the resume header page -->

								<jsp:include page="jobboardsearchresumeheader.jsp"></jsp:include>

								<div class="searchContent" style="display: none;">
									<jsp:include page="jobboardsearchresumeresultbody.jsp"></jsp:include>
								</div>

							</div>

							<!-- search_info_box2 -->
							<!-- browse_bar -->

						</form>
						<div class="clearfix"></div>
					</div>
					
					<!-- Add include for employee dashboard content -->
					
					<div class="otherContent" style="display: block">
									<jsp:include page="jobboardemployerdashboardcontent.jsp"></jsp:include>
					</div>
					
				</div>
				<div class="clearfix"></div>

				<div class="clearfix"></div>
				
			</div>

			<!--Start:MidContant-->
			<div class="clearfix"></div>
			<!-- content_wrapper -->
			<div class="ad_wrapper">
				<img src="../resources/images/ads/banner_ad_fpo.png" />
			</div>
			<!-- ad_wrapper -->

		</div>
		<!-- main -->

	</div>
	<!-- end main_wrapper_inside -->
	</div>
	<!-- end main_wrapper_outside -->
	
	<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
	<!-- footer_wrapper -->

</body>
</html>