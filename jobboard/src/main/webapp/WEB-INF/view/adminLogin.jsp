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
<link rel='shortcut icon' href='<%=request.getContextPath()%>/resources/images/favicon.ico' type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="robots" content="noindex, follow"> 
<!-- <link rel="stylesheet" type="text/css" href="jquery.autocomplete.css" /> -->


	<!-- <script type="text/javascript" src="jquery.autocomplete.min.js"></script> -->
<jsp:include page="common/include.jsp" />
<link rel="stylesheet" type="text/css" media="screen" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.js"></script>
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
		    $("#manageLogLevel").displaypopup("#manageLogLevel",
					"790", "360");
		});
		</script>
<!-- <script type="text/javascript" src="javascripts/expandCollapse.js"></script> -->
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
						<div class="headerLoginSectionColumns">
						<span class="boldText">${msg.jsWelcomeMsg}<%=(String) session.getAttribute("userName")%>${msg.commonExclamationMark}

						</span><br />
						<div class="floatRight">
							<span class="floatLeft"> <a href="<%=request.getContextPath()%>/admin/logout.html">${msg.commonLogOut}</a>

								${msg.commonVerticalBar} <c:choose>
									<c:when test="${jobSeekerDashBoardForm != null}">
										<a href="<%=request.getContextPath()%>/admin/adminMenu.html">${msg.commonBackHome}</a>
									</c:when>
									<c:otherwise>
										<a href="<%=request.getContextPath()%>/admin/adminMenu.html">Admin Home</a>
									</c:otherwise>
								</c:choose>
							</span>
						</div>
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
									<li><a href="http://nursing.advanceweb.com/" target="_blank">Nurses</a></li>
									<li><a href="http://physical-therapy.advanceweb.com/" target="_blank">Physical Therapy and Rehab Medicine</a></li>
									<li><a href="http://occupational-therapy.advanceweb.com/" target="_blank">Occupational
											Therapy Practitioners</a></li>
									<li><a
										href="http://imaging-radiation-oncology.advanceweb.com/" target="_blank">Imaging
											& Radiattion Oncology</a></li>
									<li><a href="http://audiology.advanceweb.com/" target="_blank">Hearing
											Practice Management</a></li>
								</div>
								<!-- END css_column -->

								<div class="css_column">
									<li><a
										href="http://speech-language-pathology-audiology.advanceweb.com/" target="_blank">Speech-Language
											Pathologists & Audiologists</a></li>
									<li><a
										href="http://respiratory-care-sleep-medicine.advanceweb.com/" target="_blank">Respiratory
											Care and Sleep Medicine</a></li>
									<li><a href="http://laboratory-manager.advanceweb.com/" target="_blank">Administrators
											of the Laboratory</a></li>
									<li><a href="http://laboratorian.advanceweb.com/" target="_blank">Medical
											Laboratory Professionals</a></li>
									<li><a href="http://health-information.advanceweb.com/" target="_blank">Health
											Information Professionals</a></li>
								</div>
								<!-- END css_column -->

								<div class="css_column">
									<li><a href="http://long-term-care.advanceweb.com/" target="_blank">Long-Term
											Care Management</a></li>
									<li><a
										href="http://nurse-practitioners-and-physician-assistants.advanceweb.com/" target="_blank">NPs
											& PAs</a></li>
									<li><a
										href="http://healthcare-executive-insight.advanceweb.com/" target="_blank">Executive
											Insight</a></li>
								</div>
								<!-- END css_column -->
							</ul>
							<!-- END css_subContainer --></li>
						<!-- END css_main_menu_item -->

						<li class="css_main_menu_item"><a href="#">Job Search</a>
							<ul class="css_subContainer">
								<div class="css_column">
									<li><a href="http://www.advancehealthcarejobs.com" target="_blank">Quick
											Search</a></li>
									<li><a
										href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcare/showCareers.html?careerType=resumeBuilder" target="_blank">Resume
											Builder</a></li>
								
									<li><a
										href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcare/showCareers.html?careerType=messanger" target="_blank"><em>ADVANCE</em>
											Messenger</a></li>
									<li><a
										href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcare/showCareers.html?careerType=career" target="_blank">Career
											Resource Center</a></li>
									<li><a
										href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"")%>/healthcare/featuredemployers.html" target="_blank">Featured Employers</a></li>
									<li><a
										href="http://www.advancehealthcarejobs.com" target="_blank">Home</a></li>
								</div>
								<!-- END css_column -->
							</ul>
							<!-- END css_subContainer --></li>
						<!-- END css_main_menu_item -->

						<li class="css_main_menu_item"><a
							href="http://www.advanceweb.com/Advertise/CE2.aspx" target="_blank">Education</a></li>
						<li class="css_main_menu_item"><a
							href="http://events.advanceweb.com/Attendee/Default.aspx" target="_blank">Events</a></li>
						<li class="css_main_menu_item"><a
							href="http://community.advanceweb.com" target="_blank">Community</a></li>
						<li class="css_main_menu_item"><a
							href="http://shop.advanceweb.com" target="_blank">Healthcare Shop</a></li>
						<li class="css_main_menu_item"><a
							href="http://promotions.advanceweb.com" target="_blank">Custom Promotions</a></li>
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
					<div class="AdminLinkAlign"><a class="boldText" href="<%=request.getContextPath()%>/admin/title.html">Edit Job Titles SEO Info</a></div>
					<div class="AdminLinkAlign"><a id="manageLogLevel" class="boldText"  href="<%=request.getContextPath()%>/admin/manageLogLevel.html">Manage Logger level</a></div>
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