<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>

<!-- ../resources/css -->
<link href="../resources/css/JB.css" rel="stylesheet" type="text/css" />
<link href="../resources/css/jquery.megamenu.css" rel="stylesheet"
	type="text/css" />
<link href="../resources/css/SliderStyles.css" rel="stylesheet"
	type="text/css">

<!--[if IE]>
	<link href="../resources/css/ie.css" rel="stylesheet" type="text/css">
<![endif]-->


<!-- JAVASCRIPT FILES -->
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript"
	src="../resources/js/jquery.cycle.all.min.js"></script>
<script type="text/javascript" src="../resources/js/slider.js"></script>
<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>
<script type="text/javascript" src="../resources/js/jobseeker.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery(".megamenu").megamenu();
	});
</script>



</head>

<body class="job_board">
	<form:form commandName="jobSeekerDashBoardForm" id="jsdashboard">
		<div class="ad_page_top">
			<img src="../resources/images/ads/banner_ad_fpo.png" />
		</div>
		<div class="main_wrapper_outside">
			<div class="main_wrapper_inside">


				<div class="main">

					<div class="header_wrapper">


						<div class="logo">
							<a href=""><img src="../resources/images/tranBg.png"
								width="397" height="74" alt="ToolTip"></a>
						</div>

						<div class="headerLoginSection">
							<div class="headerLoginSectionColumns">
								<span class="boldText">Welcome, (Job Seeker Name)!</span><br>
								<div class="floatRight">
									<span class="floatLeft"> <a href="">Log Out</a> | <a
										href="">Back to Home</a></span>
								</div>
							</div>
							<!-- loginHeader -->

							<div class="headerLoginSectionColumns" style="display: none">
								<span class="boldText">Employer:</span><br> <a href="">Login</a>
								| <a href="">Post Jobs</a>
							</div>
							<!-- loginHeader -->
							<div class="headerLoginSectionColumns" style="display: none">
								<span class="boldText">Ad Agency:</span><br> <a href="">Login</a>
								| <a href="">Post Jobs</a>
							</div>
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
										<a href="http://nursing.advanceweb.com/"><p>Nurses</p></a> <a
											href="http://physical-therapy.advanceweb.com/"><p>Physical
												Therapy and Rehab Medicine</p></a> <a
											href="http://occupational-therapy.advanceweb.com/"><p>Occupational
												Therapy Practitioners</p></a> <a
											href="http://imaging-radiation-oncology.advanceweb.com/"><p>Imaging
												& Radiattion Oncology</p></a> <a
											href="http://audiology.advanceweb.com/"><p>Hearing
												Practice Management</p></a>
									</div>
									<div class="column">
										<a
											href="http://speech-language-pathology-audiology.advanceweb.com/"><p>Speech-Language
												Pathologists & Audiologists</p></a> <a
											href="http://respiratory-care-sleep-medicine.advanceweb.com/"><p>Respiratory
												Care and Sleep Medicine</p></a> <a
											href="http://laboratory-manager.advanceweb.com/"><p>Administrators
												of the Laboratory</p></a> <a
											href="http://laboratorian.advanceweb.com/"><p>Medical
												Laboratory Professionals</p></a> <a
											href="http://health-information.advanceweb.com/"><p>Health
												Information Professionals</p></a>
									</div>
									<div class="column">
										<a href="http://long-term-care.advanceweb.com/"><p>Long-Term
												Care Management</p></a> <a
											href="http://nurse-practitioners-and-physician-assistants.advanceweb.com/"><p>NPs
												& PAs</p></a> <a
											href="http://healthcare-executive-insight.advanceweb.com/"><p>Executive
												Insight</p></a>
									</div>
								</div></li>
							<li><a href="javascript:">Job Search</a>

								<div class="megamenuContainer">
									<div class="column">
										<a href="http://health-care-jobs.advanceweb.com/"><p>Quick
												Search</p></a> <a
											href="http://health-care-jobs.advanceweb.com/ResumeBuilder/Default.aspx"><p>Resume
												Builder</p></a> <a
											href="http://health-care-jobs.advanceweb.com/Salary/Default.aspx"><p>Salary
												Calculator</p></a> <a
											href="http://health-care-jobs.advanceweb.com/AdvanceMessenger/Default.aspx"><p>
												<i>ADVANCE</i> Messenger
											</p></a> <a
											href="http://health-care-jobs.advanceweb.com/careers/article.aspx?cc=251059"><p>Career
												Resource Center</p></a> <a
											href="http://health-care-jobs.advanceweb.com/FeaturedFacilities/Default.aspx"><p>Featured
												Facilities</p></a> <a
											href="http://health-care-jobs.advanceweb.com/Default.aspx"><p>Home</p></a>
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

					<div class="ad_col_right">
						<img src="../resources/images/ads/300x250ad1.png" /> <img
							src="../resources/images/ads/300x250ad2.png" />

						<div class="follow_us">
							<h2>Follow Us</h2>
							<p>Stay connected to the latest jobs.</p>

							<a href=""><div class="social facebook_link">Facebook</div></a> <a
								href=""><div class="social twitter_link">Twitter</div></a> <a
								href=""><div class="social youTube_link">YouTube</div></a> <a
								href=""><div class="last social linkedIn_link">LinkedIn</div></a>
						</div>
						<br class="clearfix" />

					</div>
					<!-- ad_col_right -->

					<div class="content_wrapper">

						<div class="job_search_main job_search_main_height marginBottom20">
							<form method="">
								<div class="search_form">
									<h1 class="marginBottom5">
										Search <span>60,262</span> Healthcare Jobs
									</h1>

									<input type="text" name="keywords" id="keywords"
										class="jb_input1" /><br />
									<div class="toolTipBefore">
										<label for="keywords">Job Title, Keywords, Job ID,
											Company Name </label>
									</div>
									<div class="toolTip">
										<span class="classic"><p>Type in your search
												criteria here. Include any group of terms related to your
												desired position. Click on 'Advanced Search' below for more
												options.</p></span>
									</div>
									<br />

									<div class="input_grp1 marginTop10">
										<input type="text" name="city_state" id="city_state"
											class="jb_input2" /> <br />
										<div class="toolTipBefore">
											<label for="city_state">City and State or ZIP Code </label>
										</div>
										<div class="toolTip">
											<span class="classic"><p>Enter the city and state
													or zip code of the location you want to search. Then select
													a radius to expand your search up to 100 miles from your
													starting point.</p></span>
										</div>

									</div>

									<div class="input_grp2 marginTop10">
										<select name="radius" id="radius" class="jb_input3">
											<option>--</option>
											<option>5</option>
											<option>10</option>
											<option>25</option>

											<option>50</option>
											<option>100</option>
										</select> <label for="radius">Radius</label>
									</div>
									<div class="clearfix"></div>
									<a href="#" class="btn_sm orange jb_search_submit">Find
										Jobs</a><a href="">Advanced Search</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a
										href="">Post Your Resume</a></a>

								</div>
								<!-- search_form -->

								<div class="search_info_box1">
									<div class="rowPadding borderBottomDotted">
										<span class="rowEvenSpacing capsText">MY RECENT
											SEARCHES:</span><br> <a href="#">Clear All</a> | <a href="#">See
											All</a>
									</div>

									<div class="rowPadding borderBottom">
										May 16, 2012, 7:00am<br> Search by: <a href="#">Physical
											Therapist / Philadelphia, PA</a>
									</div>
									<div class="rowPadding borderBottom">
										May 16, 2012, 7:00am<br> Search by: <a href="#">Physical
											Therapist / Philadelphia, PA</a>

									</div>
									<div class="rowPadding">
										May 16, 2012, 7:00am<br> Search by: <a href="#">Physical
											Therapist / Philadelphia, PA</a>
									</div>


								</div>
								<!-- search_info_box1 -->

								<div class="search_info_box2">

									<ul>
										<li><span class="uppercase bold">My Recent
												Searches:</span><br /> <a href="">Clear All</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a
											href="">See All</a></li>

										<li>May 16, 2012, 7:00 am<br />Search by: <span
											class="jb"><a href="">Physical Therapist</a></span></li>
										<li>May 17, 2012, 8:00 am<br />Search by: <span
											class="jb"><a href="">Pediatric Nurse</a></span></li>

										<li class="last">May 18, 2012, 9:00 am<br />Search by: <span
											class="jb"><a href="">ER Nurse</a></span></li>
									</ul>

								</div>
								<!-- search_info_box2 -->

								<div class="browse_bar bold">
									<span>BROWSE JOBS:</span>&nbsp;&nbsp;<a href="">By Job
										Title</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">By Employer</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a
										href="">By Location</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">By
										Employment Type</a>

								</div>
								<!-- browse_bar -->

                      
                      <div class="dashboardPanalcontent"><h2 class="noTopBorder"> My User Profile</h2> 
                       <div class="lableTextDashBoard">
                      <p><a href="">Edit Profile Settings</a> </p>
                      </div>
                      <div class="lableTextDashBoard" id="jsChangePassword">
                      <p><a href="" onclick="popupJsChangePassword();">Change Password</a> </p>
							</form>
						</div>
						<!-- job_search_main -->
						<div class="content_columns">
							<div class="dashboardHeader">
								<img src="../resources/images/myadvance.png" width="288"
									height="23" alt="My advance  dashboard">
							</div>
							<div class="dashboardcolumn1">
								<div class="dashboardPanal">
									<div class="dashboardPanalIconHolder">
										<img src="../resources/images/UserProfile.jpg" width="30"
											height="30" alt="User Profile">
									</div>

									<div class="dashboardPanalcontent">
										<h2 class="noTopBorder">My User Profile</h2>
										<div class="lableTextDashBoard">
											<p>
												<a href="">Edit Profile Settings</a>
											</p>
										</div>
										<div class="lableTextDashBoard" id="jsChangePassword">
											<p>
												<a href="jobseekerregistration/jobSeekerChangePassword.html"
													onclick="popupJsChangePassword();">Change Password</a>
											</p>

										</div>

									</div>
								</div>
								<div class="dashboardPanal">
									<div class="dashboardPanalIconHolder">
										<img src="../resources/images/SearchAgents.jpg" width="30"
											height="30" alt="Search">
									</div>

									<div class="dashboardPanalcontent">
										<h2 class="noTopBorder">My Saved Searches</h2>
										<div class="lableTextDashBoard">
											<p>
												<a href="">View My Saved Searches (5)</a>
											</p>

										</div>
										<div class="lableTextDashBoard">
											<p>
												<a href="">Create a New Saved Search</a>
											</p>
										</div>
										<div class="lableTextDashBoard">
											<p>You can create up to 5 Saved Searches</p>
										</div>


									</div>
								</div>
								<div class="dashboardPanal">
									<div class="dashboardPanalIconHolder">
										<img src="../resources/images/Activity.jpg" width="30"
											height="30" alt="Activity">
									</div>


                      <div class="dashboardPanalIconHolder"><img src="../resources/images/Subscriptions.jpg" width="30" height="30" alt="Subscription"></div>
                      
                      <div class="dashboardPanalcontent"><h2 class="noTopBorder"> My Subscriptions</h2> 
                       <div class="lableTextDashBoard">
                      <p><a href="">Modify Subscriptions</a> </p>
                      </div>                   
                      <div class="rowEvenNewSpacing marginTop0">
		              	 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="grid2">
								<tr>
									<td valign="top">						
						     			<table>
						     				<tr  class="borderTopNone">
							     				<th class="borderTopNone" width="46%" align="left" scope="col">
							    					Subscriptions
							     				</th>
						     				</tr>
		  									<c:forEach items="${jobSubscriptionsList}" var="subscription" varStatus="index">
												<tr>	
												<td> <c:out value="${subscription.subscriptionName}"/></td>													
												</tr>
											</c:forEach>						
						     			</table>
						     		</td>
 						     		<td valign="top">
										<table> 
											<tr  class="borderTopNone">
												<th class="borderTopNone" width="46%" align="left" scope="col">Job Alerts</th>
											</tr>			 			
		 									<c:forEach items="${jobAlertsList}" var="jobAlert" varStatus="index">
												<tr>	
													<td> <c:out value="${jobAlert.alertName}"/></td>														
												</tr>
											</c:forEach>	 				
									 	</table>
									</td>
									<td valign="top">
								 		<table> 
								 			<tr  class="borderTopNone">
												<th class="borderTopNone" width="46%" align="left" scope="col">Magazines</th>
											</tr>	 			
											<c:forEach items="${jobMagazinesList}" var="magazine" varStatus="index">
												<tr>	
													<td> <c:out value="${magazine.magazineName}"/></td>	
												</tr>
											</c:forEach> 						
									 	</table>
								 	</td> 
								</tr> 	
		              	 </table>
                     </div> 
                      
                     <c:if test="${empty jobSubscriptionsList && jobAlertsList && jobMagazinesList }">
                      <div class="lableTextDashBoard">
                      <p><span class="link_color2_selected">You currently have no active subscriptions.</span>

									<div class="dashboardPanalcontent">
										<h2 class="noTopBorder">My Activity</h2>
										<div class="lableTextDashBoard">
											<p>
												<a href="">View My Saved Jobs (1)</a>
											</p>
										</div>
						Sign up now to get job alerts, healthcare news and much more delivered to you automatically!</p>
                      </div>
                     </c:if> 
                      </div>                     
                      </div>
                    
                    </div>


										<div class="lableTextDashBoard">
											<p>
												<a href="">View Jobs I've Applied To (1)</a>
											</p>
										</div>
										<div class="lableTextDashBoard">
											<p>
												<a href="">Employers Who Viewed My Profile</a>
											</p>
										</div>

									</div>
								</div>


							</div>

							<div class="dashboardcolumn2">
								<div class="dashboardPanal">
									<div class="dashboardPanalIconHolder">
										<img src="../resources/images/Resume.jpg" width="30"
											height="30" alt="Resume">
									</div>

									<div class="dashboardPanalcontent">
										<h2 class="noTopBorder">My Resumes & Cover Letters</h2>
										<div class="lableTextDashBoard">
											<p>
												<span class="link_color2_selected">My Resumes</span>
											</p>

										</div>
										<div class="lableTextDashBoard">
											<p>
												<a href="">Manage Existing Resumes</a>
											</p>
										</div>
										<div class="lableTextDashBoard">
											<p>
												<a href="">Create New Resume</a>
											</p>
										</div>
										<div class="lableTextBLineDashBoard">

											<p>You can have up to 5 unique resumes</p>
										</div>
										<div class="lableTextDashBoard">
											<p>
												<span class="link_color2_selected">My Cover Letters</span>
											</p>
										</div>
										<div class="lableTextDashBoard">
											<p>
												<a href="">Manage Existing Cover Letters</a>
											</p>

										</div>
										<div class="lableTextDashBoard">
											<p>
												<a href="">Create New Cover Letter </a>
											</p>
										</div>
										<div class="lableTextDashBoard">
											<p>You can have up to 5 unique cover letters</p>
										</div>

									</div>
								</div>
								<div class="dashboardPanal">

									<div class="dashboardPanalIconHolder">
										<img src="../resources/images/Subscriptions.jpg" width="30"
											height="30" alt="Subscription">
									</div>

									<div class="dashboardPanalcontent">
										<h2 class="noTopBorder">My Subscriptions</h2>
										<div class="lableTextDashBoard">
											<p>
												<a href="">Modify Subscriptions</a>
											</p>
										</div>
										<div class="lableTextDashBoard">
											<p>
												<span class="link_color2_selected">You currently have
													no active subscriptions.</span> Sign up now to get job alerts,
												healthcare news and much more delivered to you
												automatically!
											</p>
										</div>

									</div>
								</div>

							</div>



						</div>




					</div>
					<!-- content_wrapper -->
					<div class=" clearfix"></div>

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
					<li><a href="#">Imaging & Radiation Oncology</a></li>
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
						href="#">Sign Up for E-Newsletter</a></li>

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
			<p class="copyright">&copy; 2012 Merion Matters 2900 Horizon
				Drive King of Prussia PA 19406 800-355-5627</p>

		</div>
		<!-- footer_wrapper -->
	</form:form>
</body>
</html>