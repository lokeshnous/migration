<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>

<!-- STYLESHEETS -->
<link href="../resources/css/JB.css" rel="stylesheet" type="text/css" />
<link href="../resources/css/jquery.megamenu.css" rel="stylesheet"
	type="text/css" />
<link href="../resources/css/SliderStyles.css" rel="stylesheet"
	type="text/css">

<!--[if IE]>
	<link href="stylesheets/ie.css" rel="stylesheet" type="text/css">
<![endif]-->


<!-- JAVASCRIPT FILES -->
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript"
	src="../resources/js/jquery.cycle.all.min.js"></script>
<script type="text/javascript" src="../resources/js/slider.js"></script>
<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery(".megamenu").megamenu();
	});
</script>
<script type="text/javascript" src="../resources/js/expandCollapse.js"></script>
</head>

<body class="job_board">
	<div class="ad_page_top">
		<img src="../resources/images/ads/banner_ad_fpo.png" />
	</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">
			<div class="main">
				<div class="row">
					<div class="header_wrapper">

						<a href="">
							<div class="logo"></div>
						</a>
						<div class="headerLoginSection">
							<div class="headerLoginSectionColumns">
								<span class="boldText">Welcome, (Employer Name)!</span><br>
								<div class="floatRight">
									<span class="floatLeft"> <a href="">Log Out</a> | <a
										href="">My Dashboard</a></span>
								</div>
							</div>
							<!-- loginHeader -->
							<!-- loginHeader -->

							<!-- loginHeader -->
						</div>
						<!-- loginHeader -->
						<!-- loginHeader -->

					</div>
				</div>
				<!-- header_wrapper -->

				<div class="row">
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
				</div>
				<!--nav-->
				<div class="row">
					<div class="job_search_Resume">
						<form method="">
							<div class="row ">
								<div class="row marginTop10">
									<span class="FontSize18">Search Resumes</span>
								</div>
								<div class="row marginTop10 marginBottom10">
									<div class=" floatLeft  width255">
										<input type="text" name="keywords" id="keywords"
											class="jb_input2" />
										<div class="floatLeft">
											<div class="toolTipBefore">
												<label for="keywords">Keywords </label>
											</div>
											<div class="toolTip">
												<span class="classic"><p>You can enter a job
														title, specialty, skill or any other keywords that
														describe the position you're trying to fill. Then select
														an option from the drop-down menu to match your exact
														phrase or any of the individual words you entered.</p></span>
											</div>
										</div>
									</div>
									<div class="floatLeft marginLeft10">

										<select name="phrase" id="phrase" class="jb_input3 marginTop0">
											<option>Match exact phrase</option>
											<option>Match any word</option>
										</select>
									</div>
									<div class="FloatLeft marginLeft10 width255">

										<input type="text" name="city_state" id="city_state"
											class="jb_input2" />

										<div class="toolTipBefore">
											<label for="city_state">City and State or ZIP Code </label>
										</div>
										<div class="toolTip">
											<span class="classic"><p>Narrow your search to
													job-seekers in a specific area by entering the location
													here. Then select a search radius in the following
													drop-down menu.</p></span>
										</div>
									</div>
									<div class="input_grp2 marginLeft10">

										<div class="floatLeft">
											<select name="radius" id="radius" class="jb_input3">
												<option selected="" value="--">--</option>
												<option value="5 Miles">5 Miles</option>
												<option value="10 Miles">10 Miles</option>
												<option value="25 Miles">25 Miles</option>
												<option value="50 Miles">50 Miles</option>
												<option value="100 Miles">100 Miles</option>
											</select>
										</div>
										<div class="toolTipBefore">
											<label for="radius">Radius</label>
										</div>
									</div>
									<div class="floatRight marginTop6 marginLeft10">
										<a href="#" class="btn_sm orange margin0">Search</a><br>
										<div class="floatLeft marginTop13">
											<a href="">Advanced Search</a>
										</div>
									</div>
								</div>
							</div>

							<!-- search_info_box2 -->
							<!-- browse_bar -->

						</form>
						<div class="clearfix"></div>
					</div>
				</div>
				<!-- ad_col_right -->

				<div class="row">
					<div class="row marginTop5 paddingBottom05">
						<div class="floatLeft">
							<h1 class="FontSize24">200 resumes match your results</h1>
						</div>
					</div>

				<div class="content_columns_search_results">
					<div class="column1">


						<div class="section">
							<h2>Current Search</h2>
							<div class="buttonRow">
								Nurse
								<div class="floatRight">
									<a href=""><img src="../resources/images/CloseGray.jpg" alt="close"
										width="15" height="15"> </a>
								</div>
							</div>
							<div class="buttonRow">
								19406
								<div class="floatRight">
									<a href=""><img src="../resources/images/CloseGray.jpg" alt="close">
									</a>
								</div>
							</div>
							<div class="buttonRow">
								25 miles
								<div class="floatRight">
									<a href=""><img src="../resources/images/CloseGray.jpg" alt="close"
										width="15" height="15"> </a>
								</div>
							</div>
							<div class="section">
								<div class="SaveSearchButton">
									<a href="" class="btn_sm orange">Save This Search</a>
								</div>
							</div>
						</div>
						<div class="section">
							<h2>Refine Results</h2>

							<div class="refineResults">
								<span class="refineResultsItem plus">Posted Date</span>
								<div class="refineResultsSubContent">
									<ul>
										<li><a href="">Today (1)</a></li>
										<li><a href="">Yesterday (8)</a></li>
										<li><a href="">Last 3 days (10)</a></li>
										<li><a href="">Last 7 days (16)</a></li>
										<li><a href="">Last 14 days (23)</a></li>
										<li><a href="">Last 30 days (40)</a></li>
										<li><a href="">All Jobs (2000)</a></li>
									</ul>
								</div>

								<span class="refineResultsItem plus">Radius</span>
								<div class="refineResultsSubContent">
									<ul>
										<li><a href="">5 miles</a></li>
										<li><a href="">10 miles</a></li>
										<li><a href="">25 miles</a></li>
										<li><a href="">50 miles</a></li>
										<li><a href="">100 miles</a></li>
									</ul>
								</div>

								<span class="refineResultsItem plus">State</span>
								<div class="refineResultsSubContent">
									<ul>
										<li><a href="">Virginia (5)</a></li>
										<li><a href="">Maryland (2)</a></li>
										<li><a href="">New York (3)</a></li>
									</ul>
								</div>

								<span class="refineResultsItem plus">City</span>
								<div class="refineResultsSubContent">
									<ul>
										<li><a href="">Alexandria (5)</a></li>
										<li><a href="">Baltimore (2)</a></li>
										<li><a href="">Manhattan (3)</a></li>
									</ul>
								</div>
							</div>


						</div>


					</div>
					<!-- column1 -->

					<div class="column2">

						<div class="searchResults">

							<div class="searchResultsNavigation">

								<div class="searchResultsNavigationColumn1">
									<span class="marginTop5">Results viewable:</span> <span><select
										name="results" class="jb_input4">
											<option value="20">20</option>
											<option value="30">30</option>
											<option value="40">40</option>
											<option value="50">50</option>
											<option value="All">All</option>
									</select></span> <span class="marginTop5">per page</span>
								</div>



								<div class="searchResultsNavigationColumn3">&nbsp;&nbsp;&nbsp;
								</div>
								<div class="searchResultsNavigationColumn2">
									<span>Page:</span> <span class="active">1</span> <span><a
										href="">2</a></span> <span><a href="">3</a></span> <span><a
										href="">4</a></span> <span><a href="">5</a></span> <span><a
										href="">6</a></span> <span><a href="">7</a></span> <span><a
										href="">8</a></span> <span><a href="">9</a></span> <span><a
										href="">Next<img src="../resources/images/ArrowRight.png"></a></span>
								</div>
							</div>
							<div class=" floatLeft marginBottom15 marginTop10">
								<a href="" class="btn_sm orange">Move Checked Candidates To
									Folder </a>
							</div>



							<div class="row marginTop0">
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									class="grid4">
									<tr class=" borderBottomNone LightGrayBG ">
										<th width="1%" align="left" valign="top" scope="col">&nbsp;</th>
										<th width="22%" align="left" valign="top" scope="col"><input
											type="checkbox" name="checkbox" id="checkbox"
											class="marginRight5"> <label for="checkbox">Desired
												Position</label></th>
										<th width="15%" align="left" valign="top" scope="col">Applicant
											Name</th>
										<th width="13%" align="left" valign="top" scope="col">Location</th>
										<th width="6%" align="center" valign="top" scope="col">Exp.</th>
										<th width="15%" align="center" valign="top" scope="col">Employment
											Type</th>
										<th width="8%" align="center" valign="top" scope="col">Relocate</th>
										<th width="9%" align="center" valign="top" scope="col">Posted</th>
										<th width="11%" align="center" valign="top" scope="col">Actions</th>
									</tr>

									<tr>
										<td align="left" valign="bottom">&nbsp;</td>
										<td align="left" valign="bottom"><input type="checkbox"
											name="checkbox2" id="checkbox2" class="marginRight5">
											<label for="checkbox2"><a href="#">Per Diem
													Surgical Care</a></label></td>
										<td align="left" valign="bottom">Amy Mather</td>
										<td align="left" valign="bottom">Philadelphia, PA</td>
										<td align="center">6 yrs.</td>
										<td align="center">Full-Time</td>
										<td>Yes</td>
										<td>06/27/2012</td>
										<td align="center"><a href="#"><div class="view"></div></a><a href="#"><div class="download"></div></a><a href="#"><div class="printOrange"></div></a></td>
									</tr>
									<tr>
										<td align="left" valign="bottom">&nbsp;</td>
										<td align="left" valign="bottom"><input type="checkbox"
											name="checkbox2" id="checkbox2" class="marginRight5">
											<label for="checkbox2"><a href="#"> Nursing Shift
													Manager</a></label></td>
										<td align="left" valign="bottom">Ashley Relova</td>
										<td align="left" valign="bottom">New York, NY</td>
										<td align="center">12 yrs.</td>
										<td align="center">Part-Time</td>
										<td>No</td>
										<td>05/12/2011</td>
										<td align="center"><a href="#"><div class="view"></div></a><a href="#"><div class="download"></div></a><a href="#"><div class="printOrange"></div></a></td>
									</tr>
									<tr>
										<td align="left" valign="bottom">&nbsp;</td>
										<td align="left" valign="bottom"><input type="checkbox"
											name="checkbox2" id="checkbox2" class="marginRight5">
											<label for="checkbox2"><a href="javascript:void(0)">Per
													Diem Nurse</a><a href="#"></a></label></td>
										<td align="left" valign="bottom">Becky McCafferty</td>
										<td align="left" valign="bottom">Sacramento, CA</td>
										<td align="center">7 yrs.</td>
										<td align="center">Per Diem</td>
										<td>No</td>
										<td>04/05/2012</td>
										<td align="center"><a href="#"><div class="view"></div></a><a href="#"><div class="download"></div></a><a href="#"><div class="printOrange"></div></a></td>
									</tr>
									<tr>
										<td align="left" valign="bottom">&nbsp;</td>
										<td align="left" valign="bottom"><input type="checkbox"
											name="checkbox2" id="checkbox2" class="marginRight5">
											<label for="checkbox2"><a href="#">Per Diem
													Surgical Care</a></label></td>
										<td align="left" valign="bottom">Amy Mather</td>
										<td align="left" valign="bottom">Philadelphia, PA</td>
										<td align="center">6 yrs.</td>
										<td align="center">Full-Time</td>
										<td>Yes</td>
										<td>06/27/2012</td>
										<td align="center"><a href="#"><div class="view"></div></a><a href="#"><div class="download"></div></a><a href="#"><div class="printOrange"></div></a></td>
									</tr>
									<tr>
										<td align="left" valign="bottom">&nbsp;</td>
										<td align="left" valign="bottom"><input type="checkbox"
											name="checkbox2" id="checkbox2" class="marginRight5">
											<label for="checkbox2"><a href="#"> Nursing Shift
													Manager</a></label></td>
										<td align="left" valign="bottom">Ashley Relova</td>
										<td align="left" valign="bottom">New York, NY</td>
										<td align="center">12 yrs.</td>
										<td align="center">Part-Time</td>
										<td>No</td>
										<td>05/12/2011</td>
										<td align="center"><a href="#"><div class="view"></div></a><a href="#"><div class="download"></div></a><a href="#"><div class="printOrange"></div></a></td>
									</tr>
									<tr>
										<td align="left" valign="bottom">&nbsp;</td>
										<td align="left" valign="bottom"><input type="checkbox"
											name="checkbox2" id="checkbox2" class="marginRight5">
											<label for="checkbox2"><a href="javascript:void(0)">Per
													Diem Nurse</a><a href="#"></a></label></td>
										<td align="left" valign="bottom">Becky McCafferty</td>
										<td align="left" valign="bottom">Sacramento, CA</td>
										<td align="center">7 yrs.</td>
										<td align="center">Per Diem</td>
										<td>No</td>
										<td>04/05/2012</td>
										<td align="center"><a href="#"><div class="view"></div></a><a href="#"><div class="download"></div></a><a href="#"><div class="printOrange"></div></a></td>
									</tr>
									<tr>
										<td align="left" valign="bottom">&nbsp;</td>
										<td align="left" valign="bottom"><input type="checkbox"
											name="checkbox2" id="checkbox2" class="marginRight5">
											<label for="checkbox2"><a href="#">Per Diem
													Surgical Care</a></label></td>
										<td align="left" valign="bottom">Amy Mather</td>
										<td align="left" valign="bottom">Philadelphia, PA</td>
										<td align="center">6 yrs.</td>
										<td align="center">Full-Time</td>
										<td>Yes</td>
										<td>06/27/2012</td>
										<td align="center"><a href="#"><div class="view"></div></a><a href="#"><div class="download"></div></a><a href="#"><div class="printOrange"></div></a></td>
									</tr>
									<tr>
										<td align="left" valign="bottom">&nbsp;</td>
										<td align="left" valign="bottom"><input type="checkbox"
											name="checkbox2" id="checkbox2" class="marginRight5">
											<label for="checkbox2"><a href="#"> Nursing Shift
													Manager</a></label></td>
										<td align="left" valign="bottom">Ashley Relova</td>
										<td align="left" valign="bottom">New York, NY</td>
										<td align="center">12 yrs.</td>
										<td align="center">Part-Time</td>
										<td>No</td>
										<td>05/12/2011</td>
										<td align="center"><a href="#"><div class="view"></div></a><a href="#"><div class="download"></div></a><a href="#"><div class="printOrange"></div></a></td>
									</tr>
									<tr>
										<td align="left" valign="bottom">&nbsp;</td>
										<td align="left" valign="bottom"><input type="checkbox"
											name="checkbox2" id="checkbox2" class="marginRight5">
											<label for="checkbox2"><a href="javascript:void(0)">Per
													Diem Nurse</a><a href="#"></a></label></td>
										<td align="left" valign="bottom">Becky McCafferty</td>
										<td align="left" valign="bottom">Sacramento, CA</td>
										<td align="center">7 yrs.</td>
										<td align="center">Per Diem</td>
										<td>No</td>
										<td>04/05/2012</td>
										<td align="center"><a href="#"><div class="view"></div></a><a href="#"><div class="download"></div></a><a href="#"><div class="printOrange"></div></a></td>
									</tr>
									<tr>
										<td align="left" valign="bottom">&nbsp;</td>
										<td align="left" valign="bottom"><input type="checkbox"
											name="checkbox2" id="checkbox2" class="marginRight5">
											<label for="checkbox2"><a href="#">Per Diem
													Surgical Care</a></label></td>
										<td align="left" valign="bottom">Amy Mather</td>
										<td align="left" valign="bottom">Philadelphia, PA</td>
										<td align="center">6 yrs.</td>
										<td align="center">Full-Time</td>
										<td>Yes</td>
										<td>06/27/2012</td>
										<td align="center"><a href="#"><div class="view"></div></a><a href="#"><div class="download"></div></a><a href="#"><div class="printOrange"></div></a></td>
									</tr>
									<tr>
										<td align="left" valign="bottom">&nbsp;</td>
										<td align="left" valign="bottom"><input type="checkbox"
											name="checkbox2" id="checkbox2" class="marginRight5">
											<label for="checkbox2"><a href="#"> Nursing Shift
													Manager</a></label></td>
										<td align="left" valign="bottom">Ashley Relova</td>
										<td align="left" valign="bottom">New York, NY</td>
										<td align="center">12 yrs.</td>
										<td align="center">Part-Time</td>
										<td>No</td>
										<td>05/12/2011</td>
										<td align="center"><a href="#"><div class="view"></div></a><a href="#"><div class="download"></div></a><a href="#"><div class="printOrange"></div></a></td>
									</tr>
									<tr>
										<td align="left" valign="bottom">&nbsp;</td>
										<td align="left" valign="bottom"><input type="checkbox"
											name="checkbox2" id="checkbox2" class="marginRight5">
											<label for="checkbox2"><a href="javascript:void(0)">Per
													Diem Nurse</a><a href="#"></a></label></td>
										<td align="left" valign="bottom">Becky McCafferty</td>
										<td align="left" valign="bottom">Sacramento, CA</td>
										<td align="center">7 yrs.</td>
										<td align="center">Per Diem</td>
										<td>No</td>
										<td>04/05/2012</td>
										<td align="center"><a href="#"><div class="view"></div></a><a href="#"><div class="download"></div></a><a href="#"><div class="printOrange"></div></a></td>
									</tr>
									<tr>
										<td align="left" valign="bottom">&nbsp;</td>
										<td align="left" valign="bottom"><input type="checkbox"
											name="checkbox2" id="checkbox2" class="marginRight5">
											<label for="checkbox2"><a href="#">Per Diem
													Surgical Care</a></label></td>
										<td align="left" valign="bottom">Amy Mather</td>
										<td align="left" valign="bottom">Philadelphia, PA</td>
										<td align="center">6 yrs.</td>
										<td align="center">Full-Time</td>
										<td>Yes</td>
										<td>06/27/2012</td>
										<td align="center"><a href="#"><div class="view"></div></a><a href="#"><div class="download"></div></a><a href="#"><div class="printOrange"></div></a></td>
									</tr>
									<tr>
										<td align="left" valign="bottom">&nbsp;</td>
										<td align="left" valign="bottom"><input type="checkbox"
											name="checkbox2" id="checkbox2" class="marginRight5">
											<label for="checkbox2"><a href="#"> Nursing Shift
													Manager</a></label></td>
										<td align="left" valign="bottom">Ashley Relova</td>
										<td align="left" valign="bottom">New York, NY</td>
										<td align="center">12 yrs.</td>
										<td align="center">Part-Time</td>
										<td>No</td>
										<td>05/12/2011</td>
										<td align="center"><a href="#"><div class="view"></div></a><a href="#"><div class="download"></div></a><a href="#"><div class="printOrange"></div></a></td>
									</tr>
									<tr>
										<td align="left" valign="bottom">&nbsp;</td>
										<td align="left" valign="bottom"><input type="checkbox"
											name="checkbox2" id="checkbox2" class="marginRight5">
											<label for="checkbox2"><a href="javascript:void(0)">Per
													Diem Nurse</a><a href="#"></a></label></td>
										<td align="left" valign="bottom">Becky McCafferty</td>
										<td align="left" valign="bottom">Sacramento, CA</td>
										<td align="center">7 yrs.</td>
										<td align="center">Per Diem</td>
										<td>No</td>
										<td>04/05/2012</td>
										<td align="center"><a href="#"><div class="view"></div></a><a href="#"><div class="download"></div></a><a href="#"><div class="printOrange"></div></a></td>
									</tr>
									<tr>
										<td align="left" valign="bottom">&nbsp;</td>
										<td align="left" valign="bottom"><input type="checkbox"
											name="checkbox2" id="checkbox2" class="marginRight5">
											<label for="checkbox2"><a href="#">Per Diem
													Surgical Care</a></label></td>
										<td align="left" valign="bottom">Amy Mather</td>
										<td align="left" valign="bottom">Philadelphia, PA</td>
										<td align="center">6 yrs.</td>
										<td align="center">Full-Time</td>
										<td>Yes</td>
										<td>06/27/2012</td>
										<td align="center"><a href="#"><div class="view"></div></a><a href="#"><div class="download"></div></a><a href="#"><div class="printOrange"></div></a></td>
									</tr>
									<tr>
										<td align="left" valign="bottom">&nbsp;</td>
										<td align="left" valign="bottom"><input type="checkbox"
											name="checkbox2" id="checkbox2" class="marginRight5">
											<label for="checkbox2"><a href="#"> Nursing Shift
													Manager</a></label></td>
										<td align="left" valign="bottom">Ashley Relova</td>
										<td align="left" valign="bottom">New York, NY</td>
										<td align="center">12 yrs.</td>
										<td align="center">Part-Time</td>
										<td>No</td>
										<td>05/12/2011</td>
										<td align="center"><a href="#"><div class="view"></div></a><a href="#"><div class="download"></div></a><a href="#"><div class="printOrange"></div></a></td>
									</tr>
									<tr>
										<td align="left" valign="bottom">&nbsp;</td>
										<td align="left" valign="bottom"><input type="checkbox"
											name="checkbox2" id="checkbox2" class="marginRight5">
											<label for="checkbox2"><a href="javascript:void(0)">Per
													Diem Nurse</a><a href="#"></a></label></td>
										<td align="left" valign="bottom">Becky McCafferty</td>
										<td align="left" valign="bottom">Sacramento, CA</td>
										<td align="center">7 yrs.</td>
										<td align="center">Per Diem</td>
										<td>No</td>
										<td>04/05/2012</td>
										<td align="center"><a href="#"><div class="view"></div></a><a href="#"><div class="download"></div></a><a href="#"><div class="printOrange"></div></a></td>
									</tr>
									<tr>
										<td align="left" valign="bottom">&nbsp;</td>
										<td align="left" valign="bottom"><input type="checkbox"
											name="checkbox2" id="checkbox2" class="marginRight5">
											<label for="checkbox2"><a href="#">Per Diem
													Surgical Care</a></label></td>
										<td align="left" valign="bottom">Amy Mather</td>
										<td align="left" valign="bottom">Philadelphia, PA</td>
										<td align="center">6 yrs.</td>
										<td align="center">Full-Time</td>
										<td>Yes</td>
										<td>06/27/2012</td>
										<td align="center"><a href="#"><div class="view"></div></a><a href="#"><div class="download"></div></a><a href="#"><div class="printOrange"></div></a></td>
									</tr>
									<tr>
										<td align="left" valign="bottom">&nbsp;</td>
										<td align="left" valign="bottom"><input type="checkbox"
											name="checkbox2" id="checkbox2" class="marginRight5">
											<label for="checkbox2"><a href="#"> Nursing Shift
													Manager</a></label></td>
										<td align="left" valign="bottom">Ashley Relova</td>
										<td align="left" valign="bottom">New York, NY</td>
										<td align="center">12 yrs.</td>
										<td align="center">Part-Time</td>
										<td>No</td>
										<td>05/12/2011</td>
										<td align="center"><a href="#"><div class="view"></div></a><a href="#"><div class="download"></div></a><a href="#"><div class="printOrange"></div></a></td>
									</tr>
								</table>
							</div>
							<div class="searchResultsHeader"></div>

							<div
								class="searchResultsNavigation searchResultsNavigationBottom marginTop0">

								<div class="searchResultsNavigationColumn1">
									<span class="marginTop5">Results viewable:</span> <span><select
										name="results" class="jb_input4">
											<option value="20">20</option>
											<option value="30">30</option>
											<option value="40">40</option>
											<option value="50">50</option>
											<option value="All">All</option>
									</select></span> <span class="marginTop5">per page</span>
								</div>



								<div class="searchResultsNavigationColumn3">&nbsp;&nbsp;&nbsp;
								</div>
								<div class="searchResultsNavigationColumn2">
									<span>Page:</span> <span class="active">1</span> <span><a
										href="">2</a></span> <span><a href="">3</a></span> <span><a
										href="">4</a></span> <span><a href="">5</a></span> <span><a
										href="">6</a></span> <span><a href="">7</a></span> <span><a
										href="">8</a></span> <span><a href="">9</a></span> <span><a
										href="">Next<img src="../resources/images/ArrowRight.png"></a></span>
								</div>
							</div>
							<div class=" floatLeft marginBottom15 marginTop5">
								<a href="" class="btn_sm orange">Move Checked Candidates To
									Folder </a>
							</div>

						</div>





					</div>
					<!-- column2-->



					<BR class="clearfix">
				</div>
				

				</div>

				<div class="clearfix">
				
				
				</div>
				
				<div class="ad_wrapper">
					<img src="../resources/images/ads/banner_ad_fpo.png" />
				</div>
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
				<li><div class="email"></div><a href="#">Sign Up for Enewsletter</a></li>
				<li><div class="fbook"></div><a href="#">Connect on Facebook</a></li>
				<li><div class="linkedIn"></div><a href="#">Connect on LinkedIn</a></li>
				<li><div class="twitter"></div><a href="#">Connect on Twitter</a></li>
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