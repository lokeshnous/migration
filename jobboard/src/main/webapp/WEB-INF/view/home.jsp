<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${metaTitle}</title>
<meta name="description" content="${metaDesc}"> 
<link href="${canonicalUrl}" rel="canonical" />
<jsp:include page="common/include.jsp" />

<!-- <title>ADVANCE Heathcare Jobs</title> -->


<!-- JAVASCRIPT FILES -->
<script type="text/javascript" src="../resources/js/slider.js"></script>
<link href="../resources/css/jquery-ui.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" language="javascript"
	src="/media/js/jquery.js"></script>
<script src="../resources/js/jquery.dataTables.nightly.js"></script>
<script src="../resources/js/searchResultsdatatable.js"></script>

<script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>
</head>

<body class="job_board">
	<div class="ad_page_top">
		${adPageTop}
	</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">
			<div class="main">
				<jsp:include page="../templates/templates_header.jsp"></jsp:include>
				<jsp:include page="jobboardsearchresultsHeader.jsp"></jsp:include>
				<div class="searchContent" style="display: none;" id="tableContent">
					<jsp:include page="jobboardsearchresultsBody.jsp"></jsp:include>
					<br class="clearfix" />
					</div>
				<div class="otherContent ">
				<div class="ad_col_right">
						 ${adPageRightMiddle}
					<div class="follow_us">
						<h2>Follow Us</h2> 
						<p>Stay connected to the latest jobs.</p>
						<a href="${followuplinkfacebook}" target="_blank">
							<div class="social facebook_link">Facebook</div>
						</a> <a href="${followuplinktwitter}" target="_blank">
							<div class="social twitter_link">Twitter</div>
						</a> <a href="${followuplinkyoutube}" target="_blank">
							<div class="social youTube_link">YouTube</div>
						</a> <a href="${followuplinklinkedin}" target="_blank">
							<div class="last social linkedIn_link">LinkedIn</div>
						</a>
					</div>
					<br class="clearfix" />
				</div>
				<!-- ad_col_right -->
				<div class="content_columns">
					<div class="column1">
						<a href="">
							<h2 class="more_link">
								Career Tools<span>More</span>
							</h2> 
						</a> ${careerstoolresource}
					</div>
					<!-- column1 -->

					<div class="column2">
						<a href="featuredemployers.html">
							<h2 class="more_link">
								Featured Employers<span>More</span>
							</h2>
						</a>
						<div class="featured_emp_slider" id="slider1FramesId"></div> 
						<!-- featured_emp_slider -->

						<a href="" target="_blank">
							<h2 class="more_link Bgnone">Healthcare News</h2>
						</a> ${healthcarenew}

					</div>
					<!-- column2-->
					<!--<a href="<%=request.getContextPath()%>/employer/postNewJobs.html">Post New Job</a>-->
				</div>
					<div class="SEO_text">
						<!-- added November 8, 2012 -->
							${staticContent}
					</div>
				</div>
				<br class="clearfix" />
				
				<div class="ad_wrapper">
					${adPageBtm}
				</div>
			
				
			</div>
			<!-- main -->
			</div>

		<!-- end main_wrapper_inside -->

	<!-- end main_wrapper_outside -->
	<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
</body>
</html>