<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List;"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="robots" content="noindex, follow"> 
<!-- <link rel="stylesheet" type="text/css" href="jquery.autocomplete.css" /> -->

<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.js"></script>
	<!-- <script type="text/javascript" src="jquery.autocomplete.min.js"></script> -->
<jsp:include page="common/include.jsp" />
<title>ADVANCE Heathcare Jobs</title>

<!-- STYLESHEETS -->
<!-- 
<link href="../resources/css/jquery.megamenu.css" rel="stylesheet"
	type="text/css" />
<link href="../resources/css/SliderStyles.css" rel="stylesheet"
	type="text/css">

JAVASCRIPT FILES
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript"
	src="../resources/js/jquery.cycle.all.min.js"></script>
<script type="text/javascript" src="../resources/js/slider.js"></script>
<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>
 -->
<script type="text/javascript">
		    jQuery(document).ready(function(){
		    jQuery(".megamenu").megamenu();
		    
		    $("#impersonation").displaypopup("#impersonation",
					"790", "360");
		    $("#editJobPosting").displaypopup("#editJobPosting",
					"790", "450");
		    $(".inventory").displaypopup(".inventory",
					"790", "360");
		    $(".manageFacilityGroup").displaypopup(".manageFacilityGroup",
					"790", "360");
		});
		</script>
<script type="text/javascript" src="javascripts/expandCollapse.js"></script>
</head>

<body class="job_board">
	<div class="ad_page_top">
		<img src="../resources/images/ads/banner_ad_fpo.png" />
	</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">
			<div class="main">
				<div class="header_wrapper">
					<a href="">
						<div class="logo"></div>
					</a>
					<div class="headerLoginSection">
						<div class="headerLoginSectionColumns paddingBottom10">
							<span class="boldText">&nbsp;&nbsp;&nbsp;Admin</span><br>

						</div>
						<!-- loginHeader -->
						<!-- loginHeader -->
						<!-- loginHeader -->
					</div>
					<!-- loginHeader -->
					<!-- loginHeader -->

				</div>
				<!-- header_wrapper -->

				<div id="nav">
					<ul class="megamenu">
						<li><a href="javascript:">Magazines</a>
							<div class="megamenuContainer">
								<div class="column">
									<a href="http://nursing.advanceweb.com/">
										<p>Nurses</p>
									</a> <a href="http://physical-therapy.advanceweb.com/">
										<p>Physical Therapy and Rehab Medicine</p>
									</a> <a href="http://occupational-therapy.advanceweb.com/">
										<p>Occupational Therapy Practitioners</p>
									</a> <a href="http://imaging-radiation-oncology.advanceweb.com/">
										<p>Imaging & Radiattion Oncology</p>
									</a> <a href="http://audiology.advanceweb.com/">
										<p>Hearing Practice Management</p>
									</a>
								</div>
								<div class="column">
									<a
										href="http://speech-language-pathology-audiology.advanceweb.com/">
										<p>Speech-Language Pathologists & Audiologists</p>
									</a> <a
										href="http://respiratory-care-sleep-medicine.advanceweb.com/">
										<p>Respiratory Care and Sleep Medicine</p>
									</a> <a href="http://laboratory-manager.advanceweb.com/">
										<p>Administrators of the Laboratory</p>
									</a> <a href="http://laboratorian.advanceweb.com/">
										<p>Medical Laboratory Professionals</p>
									</a> <a href="http://health-information.advanceweb.com/">
										<p>Health Information Professionals</p>
									</a>
								</div>
								<div class="column">
									<a href="http://long-term-care.advanceweb.com/">
										<p>Long-Term Care Management</p>
									</a> <a
										href="http://nurse-practitioners-and-physician-assistants.advanceweb.com/">
										<p>NPs & PAs</p>
									</a> <a href="http://healthcare-executive-insight.advanceweb.com/">
										<p>Executive Insight</p>
									</a>
								</div>
							</div></li>
						<li><a href="javascript:">Job Search</a>
							<div class="megamenuContainer">
								<div class="column">
									<a href="http://health-care-jobs.advanceweb.com/">
										<p>Quick Search</p>
									</a> <a
										href="http://health-care-jobs.advanceweb.com/ResumeBuilder/Default.aspx">
										<p>Resume Builder</p>
									</a> <a
										href="http://health-care-jobs.advanceweb.com/Salary/Default.aspx">
										<p>Salary Calculator</p>
									</a> <a
										href="http://health-care-jobs.advanceweb.com/AdvanceMessenger/Default.aspx">
										<p>
											<i>ADVANCE</i> Messenger
										</p>
									</a> <a
										href="http://health-care-jobs.advanceweb.com/careers/article.aspx?cc=251059">
										<p>Career Resource Center</p>
									</a> <a
										href="http://health-care-jobs.advanceweb.com/FeaturedFacilities/Default.aspx">
										<p>Featured Facilities</p>
									</a> <a href="http://health-care-jobs.advanceweb.com/Default.aspx">
										<p>Home</p>
									</a>
								</div>
							</div></li>
						<li><a href="javascript:">Education</a></li>
						<li><a href="javascript:">Events</a></li>
						<li><a href="javascript:">Community</a></li>
						<li><a href="javascript:">Healthcare Shop</a></li>
						<li><a href="javascript:">Custom Promotions</a></li>
					</ul>
				</div>
				<!--nav-->

				<!-- ad_col_right -->
				<!-- content_wrapper -->
				<div class="popupHeader Padding0  OrangeBG marginBottom5">
					<h2>Admin</h2>
				</div>
				<form:form method="POST">
					<div class="AdminLinkAlign"><a id="impersonation" class="boldText" href="<%=request.getContextPath()%>/admin/login.html">Impersonation</a></div>
					<div class="AdminLinkAlign"><a id="editJobPosting" class="boldText" href="<%=request.getContextPath()%>/admin/editJobPosting.html">Manage/Edit Job Posting Expire Date</a></div>
					<div class="AdminLinkAlign"><a id="inventory" class="inventory boldText" href="<%=request.getContextPath()%>/admin/employer1/jobInventorypopup.html">Manage/Edit Job Posting Inventory</a></div>
					<div class="AdminLinkAlign"><a id="manageFacilityGroupId" class="inventory boldText" href="<%=request.getContextPath()%>/admin/employer1/manageFacilityGroup.html">Manage/Edit Facility Group </a></div>
				</form:form>
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
	<!-- footer_wrapper -->

</body>
</html>