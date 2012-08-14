<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="common/include.jsp" />
<title>ADVANCE Heathcare Jobs</title>

<!-- JAVASCRIPT FILES -->
<script type="text/javascript" src="../resources/js/slider.js"></script>
<link href="../resources/css/jquery.dataTables.css" rel="stylesheet"
	type="text/css">
<link href="../resources/css/jquery-ui.css" rel="stylesheet"
	type="text/css">
<link href="../resources/css/jobsearchResults.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" language="javascript"
	src="/media/js/jquery.js"></script>
<script src="../resources/js/jquery.dataTables.nightly.js"></script>
<script src="../resources/js/searchResultsdatatable.js"></script>

<script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>
<!-- <link rel="stylesheet" type="text/css"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" /> -->

</head>

<body class="job_board">
	<div class="ad_page_top">
		<img src="../resources/images/ads/banner_ad_fpo.png" />
	</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">
			<div class="main">
				<jsp:include page="../templates/templates_header.jsp"></jsp:include>
				<jsp:include page="jobboardsearchresultsHeader.jsp"></jsp:include>
				<div class="content_columns row">
				<div class="searchContent" style="display: block">
					<jsp:include page="jobboardsearchresultsBody.jsp"></jsp:include>
				</div>
					
				
				<%--<div class="otherContent">
				 <div class="ad_col_right" style="display: none;">
					<img class="marginTop10"
						src="../resources/images/ads/300x250ad2.png" />
					<div class="follow_us">
						<h2>Follow Us</h2>
						<p>Stay connected to the latest jobs.</p>
						<a href="${follouplinkfacebook}" target="_blank">
							<div class="social facebook_link">Facebook</div>
						</a> <a href="${follouplinktwitter}" target="_blank">
							<div class="social twitter_link">Twitter</div>
						</a> <a href="${follouplinkyoutube}" target="_blank">
							<div class="social youTube_link">YouTube</div>
						</a> <a href="${follouplinklinkedin}" target="_blank">
							<div class="last social linkedIn_link">LinkedIn</div>
						</a>
					</div>
					<br class="clearfix" />
				</div> 
				<!-- ad_col_right -->
				<%-- <div class="content_columns">
					<jsp:include page="jobboardsearchresultsBody.jsp"></jsp:include>
				</div> 
				</div>--%>
				<br class="clearfix" />
				
				<div class="ad_wrapper">
					<img src="../resources/images/ads/banner_ad_fpo.png" />
				</div>
				</div>
				
			</div>
			<!-- main -->
			</div>

		<!-- end main_wrapper_inside -->

	<!-- end main_wrapper_outside -->
	<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
</body>
</html>