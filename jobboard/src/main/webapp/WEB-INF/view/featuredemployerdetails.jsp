<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>

<!-- JAVASCRIPT FILES -->
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript"
	src="../resources/js/jquery.cycle.all.min.js"></script>
<script type="text/javascript" src="../resources/js/slider.js"></script>
<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>
<script type="text/javascript" src="http://jqueryjs.googlecode.com/files/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="http://github.com/ahmednuaman/vid/raw/master/jquery.vid.js"></script>
<jsp:include page="common/include.jsp" />
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery(".megamenu").megamenu();
	});
</script>
        <!-- <script type="text/javascript">
            $(document).ready( function()
            {
                var api = $( '#flash' ).vid({
                    type    : 'wmv',
                    videoId : 'R7yfISlGLNU'
                });
            });
        </script> -->
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
				<div class="row">
					<div class="sectionHeaderResume">
						<h2 class="noBorder floatLeft">Featured Employer Details</h2>
						<span class="floatRight marginRight10 marginTop5"><a
							href="featuredemployers.html"
							class="link_color2_emphasized FontSize12 ">View All Featured
								Employers</a></span>
					</div>
					<div class="featuredEmployerLeft" style="background: ${employerProfileManagementForm.primaryColor}">
						<div class="featuredEmployerVideo">
							&nbsp;
							<!-- <iframe runat="server" id="ifrmMultimediaPlayer" marginheight="0"
								marginwidth="0" src="" frameborder="0" height="180"
								scrolling="no" width="255"></iframe> -->
								
							
		<!-- <video width="320" height="240" controls="controls">
		  <source src="movie.mp4" type="video/mp4" />
		   <source src="C://Wildlife.wmv" type="video/wmv" />
		  <source src="movie.ogg" type="video/ogg" />
		  Your browser does not support the video tag.
		</video> -->
		
		        <div id="flash" style="width: 320px; height: 240px;"></div>
							<iframe runat="server" id="ifrmMultimediaPlayer" marginheight="0" marginwidth="0" src="" frameborder="0"  height="180" scrolling="no" width="255"></iframe><br>

						</div>
						<div class="featuredEmployerLinks">
							<div class="row marginBottom10">
								<span class="labelHolder1">Website:</span> <a
									href="http://${employerProfileManagementForm.companyWebsite }"
									target="_blank">
									${employerProfileManagementForm.companyWebsite } </a>
							</div>
							<span class="labelHolder1">E-Mail:</span> <a
								href="mailto:${employerProfileManagementForm.companyEmail }">
								${employerProfileManagementForm.companyEmail } </a>
						</div>
						<a href="#"><div class="featuredEmployerViewAllJobLink">
								View all job postings from this Employer</div></a>
					</div>

					<div class="featuredEmployerRight">
						<div class="row marginBottom10 marginTop10">
							<img src="<%=request.getContextPath()%>/healthcarejobs/viewImage.html?id=${employerProfileManagementForm.logoPath}"
								alt="${employerProfileManagementForm.companyName}">
						</div>
						<div class="row borderTopRed">
							<p class="marginTop15">
								<span class="featuredEmployerSectionHeader">Company
									Overview</span><br>
								${employerProfileManagementForm.companyOverview }
							</p>

							<p class="marginTop15">
								<span class="featuredEmployerSectionHeader">Company News</span><br>
								${employerProfileManagementForm.companyNews }
							</p>
						</div>
					</div>


				</div>

				<div class="clearfix"></div>

				<div class="ad_wrapper">
					<img src="../resources/images/ads/banner_ad_fpo.png" />
				</div>
			</div>
			<!-- main -->

		</div>
		<!-- end main_wrapper_inside -->
	</div>
	<!-- end main_wrapper_outside -->
	<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
</body>
</html>