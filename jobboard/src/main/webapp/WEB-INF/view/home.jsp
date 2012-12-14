<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${metaTitle}</title>
<meta name="description" content="${metaDesc}" />
<link href="${canonicalUrl}" rel="canonical" />
<jsp:include page="common/include.jsp" />

<!-- <title>ADVANCE Heathcare Jobs</title> -->

<!-- JAVASCRIPT FILES -->
<script language="javascript" type="text/javascript" src="../resources/js/slider.js"></script>
<link href="../resources/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script language="javascript" type="text/javascript" src="/media/js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="../resources/js/jquery.dataTables.nightly.js"></script>
<script language="javascript" type="text/javascript" src="../resources/js/searchResultsdatatable.js"></script>
<script language="javascript" type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>

  <script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script> 
</head>

<body class="job_board">
<div class="ad_page_top">${adPageTop}</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">
			<div class="main">
				<jsp:include page="../templates/templates_header.jsp" />
				<jsp:include page="jobboardsearchresultsHeader.jsp" />
				<div class="searchContent" style="display: none;" id="tableContent">
					<jsp:include page="jobboardsearchresultsBody.jsp" />
					<br class="clearfix" />
				</div>
				<div class="otherContent ">
					<div class="ad_col_right">
						<div id="adPageRightMiddle"> ${adPageRightMiddle} </div>
						<div class="follow_us">
							<h2>Follow Us</h2>
							<p>Stay connected to the latest jobs.</p>
							<a href="${followuplinkfacebook}" target="_blank"><span class="social facebook_link">Facebook</span></a> 
							<a href="${followuplinktwitter}" target="_blank"><span class="social twitter_link">Twitter</span></a> 
							<a href="${followuplinkyoutube}" target="_blank"><span class="social youTube_link">YouTube</span></a>
							<a href="${followuplinklinkedin}" target="_blank"><span class="last social linkedIn_link">LinkedIn</span></a>
						</div>
						<br class="clearfix" />
					</div>
					<!-- ad_col_right -->
					<div class="content_columns">
						<div class="column1">
								<h2 class="more_link">
								<a href="">
									Career Tools</a><span><a href="">More</a></span>
								</h2>
							 ${careerstoolresource}
						</div>
						<!-- column1 -->

						<div class="column2">
								<h2 class="more_link">
								<a href="featuredemployers.html">
									Featured Employers</a><span><a href="featuredemployers.html">More</a></span>
								</h2>
							<div class="featured_emp_slider" id="slider1FramesId"></div>
							<!-- featured_emp_slider -->

								<h2 class="more_link Bgnone"><a href="" target="_blank">Healthcare News</a></h2>
							 ${healthcarenew}

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

				<div class="ad_wrapper">${adPageBottom}</div>


			</div>
			<!-- main -->
		</div>

		<!-- end main_wrapper_inside -->

		<!-- end main_wrapper_outside -->
		<jsp:include page="../templates/templates_footer.jsp" />
</body>
</html>