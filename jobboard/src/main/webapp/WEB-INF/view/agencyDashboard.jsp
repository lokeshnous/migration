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
<script type="text/javascript">
	jQuery(document).ready(function() {
		$("#changePassword").displaypopup("#changePassword", "780", "370");
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
									href="<%=request.getContextPath()%>/logout">Log Out</a> | <a
									href="../healthcarejobs/advanceweb.html">Home</a></span>
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
						<h1 class=" FontSize20">[Ad Agency Name] Dashboard</h1>
					</div>
					<div class="MidContent_Wrapper FloatLeft">
						<div class="dashboardcolumn1">
							<div class="dashboardPanal">
								<div class="dashboardPanalIconHolder width10P">
									<img src="../resources/images/UserProfile.jpg" width="30"
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
											<a href="">Account Settings</a>
										</p>
									</div>
									<div class="lableTextDashBoard">
										<p>
											<a href="">Manage Access Permissions</a>
										</p>
									</div>
								</div>
							</div>
							<!---->
							<div class="dashboardPanal">
								<div class="dashboardPanalIconHolder width10P">
									<img src="../resources/images/Subscriptions.jpg" width="30"
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
								<div class="dashboardPanalIconHolder width10P">
									<img src="../resources/images/media.png" width="30" height="30"
										alt="Subscription">
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
								<div class="dashboardPanalIconHolder width10P">
									<img src="../resources/images/Resume.jpg" width="30"
										height="30" alt="Resume">
								</div>

								<div class="dashboardPanalAGCcontent marginTop5 FloatLeft">
									<h2 class="noTopBorder">Employers</h2>
									<div class="lableTextDashBoard">
										<h3 class="TextColor01">Colorado</h3>
									</div>
									<div class="lableTextDashBoard">
										<p>
											<a href="">Vail Valley Medical Center</a>
										</p>
									</div>

									<div class="lableTextDashBoard marginTop20">
										<h3 class="TextColor01">Florida</h3>
									</div>
									<div class="lableTextDashBoard">
										<p>
											<a href="">BayCare Health System</a>
										</p>
									</div>
									<div class="lableTextDashBoard">
										<p>
											<a href="">HCA West Florida Division</a>
										</p>
									</div>
									<div class="lableTextDashBoard marginTop20">
										<h3 class="TextColor01">Maryland</h3>
									</div>
									<div class="lableTextDashBoard">
										<p>
											<a href="">Greater Baltimore Medical Center</a>
										</p>
									</div>
									<div class="lableTextDashBoard">
										<p>
											<a href="">Howard County General Hospital</a>
										</p>
									</div>
									<div class="rowEvenTB10Spacing">
									</div>
									<div class="rowEvenTB10Spacing">
									</div>
									<div class="rowEvenNewSpacing">
										<a href="#" class="btn_sm orange">ADD EMPLOYER</a><a href="#"
											class="btn_sm orange">MANAGE EMPLOYERS</a>
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
	<div class="footer_wrapper">
		<div class="container1">
			<h4>Professions:</h4>
			<ul>
				<li><a href="#">Nursing</a></li>
				<li><a href="#">Imaging & Radiation</a></li>
				<li><a href="#">Oncology</a></li>
				<li><a href="#">Physical Therapy and Rehab Medicine</a></li>
				<li><a href="#">Occupational Therapy</a></li>
				<li><a href="#">Speech-Language Pathology</a></li>
				<li><a href="#">Audiology</a></li>
				<li><a href="#">Hearing Practice Management</a></li>
				<li><a href="#">Long-Term Care Managament</a></li>
				<li><a href="#">Respiratory Care</a></li>
				<li><a href="#">Sleep Medicine</a></li>
				<li><a href="#">Labortory</a></li>
				<li><a href="#">Health Information</a></li>
				<li><a href="#">Nurse Practitioners</a></li>
				<li><a href="#">Physician Assistants</a></li>
				<li><a href="#">Healthcare Executives</a></li>
			</ul>
		</div>
		<!-- end container1 -->

		<div class="container2">
			<h4>Content:</h4>
			<ul>
				<li><a href="#">News</a></li>
				<li><a href="#">Features</a></li>
				<li><a href="#">Multimedia</a></li>
				<li><a href="#">Buyers Guide</a></li>
				<li><a href="#">Community</a></li>
				<li><a href="#">Downloads</a></li>
			</ul>
		</div>
		<!-- end container2 -->

		<div class="container3">
			<h4>Services:</h4>
			<ul>
				<li><a href="#">ADVANCE Healthcare Jobs</a></li>
				<li><a href="#">Subscribe</a></li>
				<li><img src="../resources/images/email.png" class="foot_icon" /><a
					href="#">Sign Up for Enewsletter</a></li>
				<li><img src="../resources/images/fbook_sm.png"
					class="foot_icon" /><a href="#">Connect on Facebook</a></li>
				<li><img src="../resources/images/L_In_sm.png"
					class="foot_icon" /><a href="#">Connect on LinkedIn</a></li>
				<li><img src="../resources/images/twitter_sm.png"
					class="foot_icon" /><a href="#">Connect on Twitter</a></li>
				<li><a href="#">Download the Mobile App</a></li>
				<li><a href="#">Order Promotional Items</a></li>
			</ul>
		</div>
		<!-- end container3 -->

		<div class="container4">
			<h4>More from ADVANCE:</h4>
			<ul>
				<li><a href="#">ADVANCE Heathcare Shop</a></li>
				<li><a href="#">ADVANCE Custom Promotions</a></li>
				<li><a href="#">ADVANCE Heathcare Jobs</a></li>
				<li><a href="#">ADVANCE Job Fairs</a></li>
				<li><a href="#">ADVANCE Continuing Ediucation</a></li>
				<li><a href="#">ADVANCE Custom Publishing</a></li>
			</ul>
		</div>
		<!-- end container4 -->

		<div class="container5">
			<h4>Corporate:</h4>
			<ul>
				<li><a href="#">About Us</a></li>
				<li><a href="#">Contact Us</a></li>
				<li><a href="#">Advertise with Us</a></li>
				<li><a href="#">Work for Us</a></li>
				<li><a href="#">Privacy Policy</a></li>
				<li><a href="#">Term of Service</a></li>
				<li><a href="#">Help</a></li>
			</ul>
		</div>
		<!-- end container5 -->

		<br class="clearfix" />
		<p class="copyright">&copy; 2012 Merion Matters 2900 Horizon Drive
			King of Prussia PA 19406 800-355-5627</p>
	</div>
	<!-- footer_wrapper -->

</body>
</html>