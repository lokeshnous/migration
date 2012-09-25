<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />
<!-- <script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script> -->
	<script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>
<!-- <script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.js"></script> -->
	<script type="text/javascript" src="../resources/js/jquery-ui.js"></script>
<script type="text/javascript">
	jQuery(document).ready(
			function() {
				 $("#changePassword").displaypopup("#changePassword", "780",
						"370");
				$("#accountSettingpopUp").displaypopup("#accountSettingpopUp",
						"770", "360");
				$("#createEmployerpopup").displaypopup("#createEmployerpopup",
						"750", "350");
				$("#manageEmployers").displaypopup("#manageEmployers", "750",
						"350");
				$(".employerMetrics").displaypopup(".employerMetrics", "750",
						"350");
				$("#accessPermissioPopUp").displaypopup(
						"#accessPermissioPopUp", "770", "360");
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
				<div class="header_wrapper">
					<a href="">
						<div class="logo"></div>
					</a>
					<div class="headerLoginSection">
						<div class="headerLoginSectionColumns">
							<span class="boldText">${msg.jsWelcomeMsg}<%=(String) session.getAttribute("userName")%>
								${msg.commonExclamationMark}
							</span><br>
							<div class="floatRight">
								<span class="floatLeft"> <a
									href="<%=request.getContextPath()%>/logout.html">Log Out</a> |
									<a href="../healthcarejobs/advanceweb.html">Home</a></span>
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
				<div class="clearfix"></div>
				<!--Start:MidContant-->
				<div class="MidContent_Wrapper floatLeft">
					<div class="dashboardHeader">
						<h1>
							[<%=(String) session.getAttribute("userName")%>]Dashboard
						</h1>
					</div>
					<div class="MidContent_Wrapper FloatLeft">
						<div class="dashboardcolumn1">
							<div class="dashboardPanal">
								<div class="dashboardPanalIconHolder profile">
									<img src="../resources/images/tranBg.png" width="30"
										height="30" alt="User Profile">
								</div>

								<div class="dashboardPanalAGCcontent marginTop5">
									<h2 class="noTopBorder">Profile Management</h2>
									<div class="lableTextDashBoard">
										<p>
											<a
												href="<%=request.getContextPath()%>/jobseekerregistration/jobSeekerChangePassword.html"
												id="changePassword">${msg.jsChangePwd}</a>
										</p>
									</div>
									<div class="lableTextDashBoard">
										<p>
											<a
												href="<%=request.getContextPath()%>/agency/viewEmpAccountProfile.html"
												id="accountSettingpopUp">Account Settings</a>
										</p>
									</div>
									<div class="lableTextDashBoard">
										<p>
											<a id="accessPermissioPopUp"
												href="<%=request.getContextPath()%>/employer/manageAccessPermission.html">Manage
												Access Permissions</a>

										</p>
									</div>
								</div>
							</div>
							<!---->
							<div class="dashboardPanal">
								<div class="dashboardPanalIconHolder subscriptions">
									<img src="../resources/images/tranBg.png" width="30"
										height="30" alt="Subscription">
								</div>

								<div class="dashboardPanalAGCcontent marginTop5">
									<h2 class="noTopBorder">Current Subscriptions</h2>
									<div class="lableTextDashBoard">
										<p>E-newsletters</p>
									</div>
									<div class="lableTextDashBoard">
										<p>E-mailer</p>
									</div>
									<div class="lableTextDashBoard">
										<p>
											<a href="">Modify Subscriptions</a>
										</p>
									</div>
								</div>
							</div>
							<!---->
							<div class="dashboardPanal">
								<div class="dashboardPanalIconHolder Solutions">
									<img src="../resources/images/tranBg.png" width="30"
										height="30" alt="Subscription">
								</div>

								<div class="dashboardPanalAGCcontent marginTop5">
									<h2 class="noTopBorder">Media</h2>


									<div class="lableTextDashBoard">
										<p>
											<a href=""><em>ADVANCE</em> Recruitment Solutions Media
												Kit</a>
										</p>
									</div>
								</div>
							</div>
						</div>
						<!--Right-->
						<div class="dashboardcolumn2">
							<div class="dashboardPanal">
								<div class="dashboardPanalIconHolder resume">
									<img src="../resources/images/tranBg.png" width="30"
										height="30" alt="Resume">
								</div>

								<div class="dashboardPanalAGCcontent marginTop5 FloatLeft">
									<h2 class="noTopBorder">Employers</h2>
									
									<c:forEach items="${emplyrsByState}" var="assocEmplyrsName"
							varStatus="status">
									<div class="lableTextDashBoard">
										<h3 class="TextColor01">${assocEmplyrsName.key}</h3>
									</div>
									<div class="lableTextDashBoard">
									<c:forEach items="${assocEmplyrsName.value}" var="emplyrsName"
							          varStatus="emplyrsStatus">
										<p>
											<a class="employerMetrics" href="<%=request.getContextPath()%>/agency/showEmployerMetrics.html?facilityId=${emplyrsName.facilityId}">${emplyrsName.name}</a> 
										</p>
										 </c:forEach>
									</div>
									<br><br><br>
									</c:forEach> 
									<div class="rowEvenTB10Spacing"></div>
									<div class="rowEvenTB10Spacing"></div>
									<div class="row">
										<a
											href="<%=request.getContextPath()%>/agency/addEmployer.html"
											id="createEmployerpopup" class="btn_sm orange">ADD
											EMPLOYER</a><a
											href="<%=request.getContextPath()%>/agency/manageEmployers.html"
											id="manageEmployers" class="btn_sm orange">MANAGE
											EMPLOYERS</a>
									</div>
								</div>

							</div>
							<!---->

							<!---->

						</div>
					</div>

					<div class="clearfix"></div>

					<!---->

					<div class="clearfix"></div>
					<!---->

					<!---->

					<!---->

					<!---->

					<!---->

					<!---->

					<!---->

					<!---->

					<!---->

					<!---->

					<!---->
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