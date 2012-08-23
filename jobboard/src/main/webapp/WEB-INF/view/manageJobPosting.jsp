<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Healthcare Jobs</title>
		<!-- STYLESHEETS -->
<link href="../resources/css/JB.css" rel="stylesheet" type="text/css" />
<link href="../resources/css/jquery.megamenu.css" rel="stylesheet"	type="text/css" />
<link href="../resources/css/SliderStyles.css" rel="stylesheet"	type="text/css">
<link rel="stylesheet" href="css/displaytag.css" type="text/css">
<!-- Common js files  -->
<script type="text/javascript" src="../resources/js/common/common.js"></script>

<!-- JQUERY LIBRARY -->
<script src="../resources/js/jquery-1.7.1.js"></script>
<script src="../resources/js/jquery-1.7.1.min.js"></script>
<script src="../resources/jquery.nyroModal/js/popup.js"></script>
<script type="text/javascript" src="../resources/js/jquery.cycle.all.min.js"></script>
<script type="text/javascript" src="../resources/js/slider.js"></script>
<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>

<!-- js files for modalpopup------------------------------------------------- -->
<script	src="../resources/jquery.nyroModal/js/jquery.nyroModal.custom.js"></script>
<script	src="../resources/jquery.nyroModal/js/jquery.nyroModal.custom.min.js"></script>
<link href="../resources/jquery.nyroModal/styles/nyroModal.css"	rel="stylesheet" type="text/css">

<style type="text/css" media="screen">
@import
	url("/jobboard/resources/jquery.nyroModal/styles/nyroModal.css");
</style>
<!-- -------------------------------------------------------------------------- -->


<script src="../resources/js/jquery.dataTables.nightly.js"></script>
<script src="../resources/js/searchResultsdatatable.js"></script>

<script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>
</head>
<body class="job_board">
<form:form action="manageJobPost.html" commandName="jobPostForm">
	<div class="ad_page_top">
		<img src="../resources/images/ads/banner_ad_fpo.png" />
	</div>

	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">


			<div class="main">
				<div class="header_wrapper"> <a href="">
                <div class="logo"></div>
                </a>
        <div class="headerLoginSection">
                  <div class="headerLoginSectionColumns"> 
                  	<span class="boldText">${msg.jsWelcomeMsg}<%=(String) session.getAttribute("userName")%>
							${msg.commonExclamationMark}
                  	</span><br>
            <div class="floatRight"> <span class="floatLeft"> <a href="../jobboard/logout">Log Out</a> | <a href="../healthcarejobs/advanceweb.html">Home</a></span></div>
          </div>
                  <!-- loginHeader --><!-- loginHeader --> 
                  
                  <!-- loginHeader --> 
                </div>
        <!-- loginHeader --> 
        <!-- loginHeader --> 
        
      </div>




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
						</a> <a href="http://respiratory-care-sleep-medicine.advanceweb.com/">
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
							<p>NPs &amp; PAs</p>
						</a> <a href="http://healthcare-executive-insight.advanceweb.com/">
							<p>Executive Insight</p>
						</a>
					</div>
				</div></li>
			<li><a href="../healthcarejobs/advanceweb.html">Job Search</a>
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
				<!--nav-->
				<div class="popupHeader Padding0  OrangeBG marginBottom5">
					<h2>MANAGE / EDIT JOB POSTINGS</h2>
					<span class="floatRight marginRight10"><a href="/jobboard/employer/employerDashBoard.html"
						class="link_color3_emphasized FontSize12 FontWeight">Back to
							Dashboard</a></span>
				</div>
				<div class="clearfix"></div>
				<div class="searchResultsNavigation width98P">
					<div class="searchResultsNavigationColumn1">

						<!--Added Class "marginTop5"-->
						<span class="marginTop5">Results viewable:</span> <span> <select
							name="results" class="jb_input4">
								<option value="20">20</option>
								<option value="30">30</option>
								<option value="40">40</option>
								<option value="50">50</option>
								<option value="All">All</option>
						</select>
						</span>
						<!--Added Class "marginTop5"-->
						<span class="marginTop5">per page</span>
					</div>
					<div class="searchResultsNavigationColumn3">&nbsp;&nbsp;&nbsp;
					</div>
					<div class="searchResultsNavigationColumn2 floatRight">
						<span>Page:</span> <span class="active">1</span> <span><a
							href="">2</a></span> <span><a href="">3</a></span> <span><a
							href="">4</a></span> <span><a href="">5</a></span> <span><a
							href="">6</a></span> <span><a href="">7</a></span> <span><a
							href="">8</a></span> <span><a href="">9</a></span> <span><a
							href="">Next<img src="images/ArrowRight.png"></a></span>
					</div>
				</div>
				<!--button-->
				<div class="row">
					<span><a href="#" class="btn_sm white jb_search_submit">REPOST</a><a
						href="#" class="btn_sm white jb_search_submit">DEACTIVATED</a><a
						href="#" class="btn_sm white jb_search_submit">DELETE</a><a
						href="#" class="btn_sm white jb_search_submit">POST NEW JOB</a>
						<div class="floatRight marginTop15">
							<span class=" FloatLeft marginTop3">View by Job Status</span> <select
								id="select14" class="jb_input3  marginTop0 width150 marginLeft5"
								name="select9">
								<option selected="selected">--- Job Status ---</option>
								<option>Active</option>
								<option>Scheduled</option>
								<option>Draft</option>
								<option>Expired</option>
								<option>Inactive</option>
							</select>
						</div> </span>
				</div>
					<div class="clearfix"></div>
					<div class="row marginTop10 FontSize11">
						<%-- <display:table name="${jobList}" sort="external" id="row"  
						 pagesize="10" cellspacing="5"	size="10" cellpadding="10"
								requestURI="" defaultsort="1" style="border:2 solid #236FBD;" class="grid" >

							<display:setProperty name="paging.banner.placement"
								value="bottom" />
							<display:setProperty name="paging.banner.no_items_found" value="" />
							<display:setProperty name="paging.banner.onepage" value="" />
							<display:setProperty name="paging.banner.one_item_found" value="" />
							<display:setProperty name="paging.banner.all_items_found"
								value="" />
							<display:setProperty name="paging.banner.some_items_found"
								value="" />
							<display:setProperty name="paging.banner.page.separator"
								value=" | " />
							<display:setProperty name="paging.banner.group_size" value="10" />
							<display:setProperty name="paging.banner.first"
								value='<center><span class="pagelinks">{0}</span></center>' />
							<display:caption>&nbsp;</display:caption>
							<display:column class="Height35" ><input type="checkbox"
										name="checkbox" id="checkbox"></display:column>
							<display:column property="jobId" sortName="jobId" title="Job ID"
								sortable="true" class="Height35"/>
								
							<display:column property="jobTitle" sortName="jobTitle"
								title="Job Title" sortable="true" class="Height35" />


						</display:table> --%>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="grid">
							<tr class="LightGrayBG Height35">
								<td width="2%" align="center" valign="middle" class="">&nbsp;</td>
								<td width="6%" align="center" valign="middle"><strong>Job
										ID</strong></td>
								<td width="22%" align="center" valign="middle"><strong>Job
										Title</strong></td>
								<td width="11%" align="center" valign="middle"><strong>Location</strong></td>
								<td width="6%" align="center" valign="middle"><strong>Job<br />
										Status
								</strong></td>
								<td width="7%" align="center" valign="middle"><strong>Start<br />
										Date
								</strong></td>
								<td width="7%" align="center" valign="middle"><strong>End<br />
										Date
								</strong></td>
								<td width="5%" align="center" valign="middle"><strong>Views</strong></td>
								<td width="4%" align="center" valign="middle"><strong>Clicks</strong></td>
								<td width="5%" align="center" valign="middle"><strong>Applies</strong></td>
								<td width="6%" align="center" valign="middle"><strong>Auto<br />
										Renew
								</strong></td>
								<td width="10%" align="center" valign="middle"><strong>Job<br />
										Template
								</strong></td>
								<td width="9%" align="center" valign="middle"><strong>Actions</strong></td>
							</tr>
							<c:forEach items="${jobList}" var="job" varStatus="status">
								<tr class="Height35">
									<td align="center" valign="middle"><input type="checkbox"
										name="checkbox" id="checkbox"></td>
									<td align="center" valign="middle"><a href="/jobboard/employer/editJob.html?jobId=${job.jobId}">${job.jobId}</a></td>
									<td align="left" valign="middle"><a href="/jobboard/employer/editJob.html?jobId=${job.jobId}">${job.jobTitle}</a></td>
									<td align="center" valign="middle">Wissahickon, MO</td>
									<td align="center" valign="middle">${job.jobStatus}</td>
									<td align="center" valign="middle">${job.startDt}</td>
									<td align="center" valign="middle">${job.endDt}</td>
									<td align="center" valign="middle">10</td>
									<td align="center" valign="middle">7</td>
									<td align="center" valign="middle">3</td>
									<td align="center" valign="middle"><select id="select"
										class="jb_input3 select100 marginTopBottom0 FontSize10 width50"
										name="select">
											<option selected="">Yes</option>
											<option>No</option>
									</select></td>
									<td align="center" valign="middle"><select id="select"
										class="jb_input3 select100 marginTopBottom0 width87 FontSize10"
										name="select">
											<option selected="" value="Public">Select One</option>
									</select></td>
									<td align="center" valign="middle"><div
											class="row width80">
											<a href="/jobboard/employer/editJob.html?jobId=${job.jobId}"><img src="../resources/images/Edit.png"
												width="20" height="20" alt=""></a>&nbsp;<a href="#"><img
												src="../resources/images/View.png" width="20" height="20"
												alt=""></a>&nbsp;<a href="#"><img
												src="images/check.png" width="20" height="20" alt=""></a>
										</div></td>
								</tr>
							</c:forEach>

						</table>
					</div>
					<div class="row FloatLeft">
					<span><a href="#" class="btn_sm white jb_search_submit">REPOST</a><a
						href="#" class="btn_sm white jb_search_submit">DEACTIVATED</a><a
						href="#" class="btn_sm white jb_search_submit">DELETE</a><a
						href="#" class="btn_sm white jb_search_submit">POST NEW JOB</a> </span>
				</div>
				<div class="clearfix"></div>
				<div class="searchResultsNavigation width98P FloatLeft marginTop20">
					<div class="searchResultsNavigationColumn1">

						<!--Added Class "marginTop5"-->
						<span class="marginTop5">Results viewable:</span> <span> <select
							name="results" class="jb_input4">
								<option value="20">20</option>
								<option value="30">30</option>
								<option value="40">40</option>
								<option value="50">50</option>
								<option value="All">All</option>
						</select>
						</span>
						<!--Added Class "marginTop5"-->
						<span class="marginTop5">per page</span>
					</div>
					<div class="searchResultsNavigationColumn3">&nbsp;&nbsp;&nbsp;
					</div>
					<div class="searchResultsNavigationColumn2 floatRight">
						<span>Page:</span> <span class="active">1</span> <span><a
							href="">2</a></span> <span><a href="">3</a></span> <span><a
							href="">4</a></span> <span><a href="">5</a></span> <span><a
							href="">6</a></span> <span><a href="">7</a></span> <span><a
							href="">8</a></span> <span><a href="">9</a></span> <span><a
							href="">Next<img src="images/ArrowRight.png"></a></span>
					</div>
				</div>
			</div>

			<!--Start:MidContant-->
			<div class="clearfix"></div>
			<!-- content_wrapper -->
			<div class="ad_wrapper">
				<img src="images/ads/banner_ad_fpo.png" />
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
				<li><img src="images/email.png" class="foot_icon" /><a href="#">Sign
						Up for Enewsletter</a></li>
				<li><img src="images/fbook_sm.png" class="foot_icon" /><a
					href="#">Connect on Facebook</a></li>
				<li><img src="images/L_In_sm.png" class="foot_icon" /><a
					href="#">Connect on LinkedIn</a></li>
				<li><img src="images/twitter_sm.png" class="foot_icon" /><a
					href="#">Connect on Twitter</a></li>
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
</form:form>
</body>
</html>