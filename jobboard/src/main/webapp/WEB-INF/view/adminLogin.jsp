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
					"300", "100");
		    $(".manageFacilityGroup").displaypopup(".manageFacilityGroup",
					"790", "360");
		});
		</script>
<script type="text/javascript" src="javascripts/expandCollapse.js"></script>
</head>

<body class="job_board">
	<div class="ad_page_top">
		${adPageTop}
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

				<div class="css_nav">
					<ul class="css_main_menu">
						<li class="css_main_menu_item"><a href="#">Magazines</a>
							<ul class="css_subContainer">
								<div class="css_column">
									<li><a href="http://nursing.advanceweb.com/">Nurses</a></li>
									<li><a href="http://physical-therapy.advanceweb.com/">Physical
											Therapy and Rehab Medicine</a></li>
									<li><a href="http://occupational-therapy.advanceweb.com/">Occupational
											Therapy Practitioners</a></li>
									<li><a
										href="http://imaging-radiation-oncology.advanceweb.com/">Imaging
											& Radiattion Oncology</a></li>
									<li><a href="http://audiology.advanceweb.com/">Hearing
											Practice Management</a></li>
								</div>
								<!-- END css_column -->

								<div class="css_column">
									<li><a
										href="http://speech-language-pathology-audiology.advanceweb.com/">Speech-Language
											Pathologists & Audiologists</a></li>
									<li><a
										href="http://respiratory-care-sleep-medicine.advanceweb.com/">Respiratory
											Care and Sleep Medicine</a></li>
									<li><a href="http://laboratory-manager.advanceweb.com/">Administrators
											of the Laboratory</a></li>
									<li><a href="http://laboratorian.advanceweb.com/">Medical
											Laboratory Professionals</a></li>
									<li><a href="http://health-information.advanceweb.com/">Health
											Information Professionals</a></li>
								</div>
								<!-- END css_column -->

								<div class="css_column">
									<li><a href="http://long-term-care.advanceweb.com/">Long-Term
											Care Management</a></li>
									<li><a
										href="http://nurse-practitioners-and-physician-assistants.advanceweb.com/">NPs
											& PAs</a></li>
									<li><a
										href="http://healthcare-executive-insight.advanceweb.com/">Executive
											Insight</a></li>
								</div>
								<!-- END css_column -->
							</ul>
							<!-- END css_subContainer --></li>
						<!-- END css_main_menu_item -->

						<li class="css_main_menu_item"><a href="#">Job Search</a>
							<ul class="css_subContainer">
								<div class="css_column">
									<li><a href="http://health-care-jobs.advanceweb.com/">Quick
											Search</a></li>
									<li><a
										href="http://health-care-jobs.advanceweb.com/ResumeBuilder/Default.aspx">Resume
											Builder</a></li>
									<li><a
										href="http://health-care-jobs.advanceweb.com/Salary/Default.aspx">Salary
											Calculator</a></li>
									<li><a
										href="http://health-care-jobs.advanceweb.com/AdvanceMessenger/Default.aspx"><em>ADVANCE</em>
											Messenger</a></li>
									<li><a
										href="http://health-care-jobs.advanceweb.com/careers/article.aspx?cc=251059">Career
											Resource Center</a></li>
									<li><a
										href="http://health-care-jobs.advanceweb.com/FeaturedFacilities/Default.aspx">Featured
											Facilities</a></li>
									<li><a
										href="http://health-care-jobs.advanceweb.com/Default.aspx">Home</a></li>
								</div>
								<!-- END css_column -->
							</ul>
							<!-- END css_subContainer --></li>
						<!-- END css_main_menu_item -->

						<li class="css_main_menu_item"><a
							href="http://www.advanceweb.com/Advertise/CE2.aspx">Education</a></li>
						<li class="css_main_menu_item"><a
							href="http://events.advanceweb.com/Attendee/Default.aspx">Events</a></li>
						<li class="css_main_menu_item"><a
							href="http://community.advanceweb.com/bloggroups/2/Home.aspx">Community</a></li>
						<li class="css_main_menu_item"><a
							href="http://shop.advanceweb.com">Healthcare Shop</a></li>
						<li class="css_main_menu_item"><a
							href="http://promotions.advanceweb.com">Custom Promotions</a></li>
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
					${adPageBottom}
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