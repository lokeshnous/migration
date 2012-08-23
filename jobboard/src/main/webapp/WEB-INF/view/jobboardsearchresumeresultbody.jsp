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
		<link href="stylesheets/JB.css" rel="stylesheet" type="text/css" />
		<link href="stylesheets/jquery.megamenu.css" rel="stylesheet" type="text/css" />
		<link href="stylesheets/SliderStyles.css" rel="stylesheet" type="text/css">
        
<!--[if IE]>
	<link href="stylesheets/ie.css" rel="stylesheet" type="text/css">
<![endif]-->


		<!-- JAVASCRIPT FILES -->
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
		<script type="text/javascript" src="javascripts/jquery.cycle.all.min.js"></script>
		<script type="text/javascript" src="javascripts/slider.js"></script>
		<script type="text/javascript" src="javascripts/jquery.megamenu.js"></script>
		<script type="text/javascript">
		    jQuery(document).ready(function(){
		    jQuery(".megamenu").megamenu();
		});
		</script>
        <script type="text/javascript" src="javascripts/expandCollapse.js"></script>
		</head>
		
			<body class="job_board">

			<div class=" mainTwo"><!-- header_wrapper -->
			
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
										<td align="center"><a href="#"><img
												src="../resources/images/View.png" width="20" height="20" alt=""></a>&nbsp;<img
											src="../resources/images/Download.png" width="20" height="20"
											alt="Download">&nbsp;<a href="#"><img
												src="../resources/images/Print2.png" width="20" height="20" alt="print"></a></td>
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
										<td align="center"><a href="#"><img
												src="../resources/images/View.png" width="20" height="20" alt=""></a>&nbsp;<img
											src="../resources/images/Download.png" width="20" height="20"
											alt="Download">&nbsp;<a href="#"><img
												src="../resources/images/Print2.png" width="20" height="20" alt="print"></a></td>
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
										<td align="center"><a href="#"><img
												src="../resources/images/View.png" width="20" height="20" alt=""></a>&nbsp;<img
											src="../resources/images/Download.png" width="20" height="20"
											alt="Download">&nbsp;<a href="#"><img
												src="../resources/images/Print2.png" width="20" height="20" alt="print"></a></td>
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
										<td align="center"><a href="#"><img
												src="../resources/images/View.png" width="20" height="20" alt=""></a>&nbsp;<img
											src="../resources/images/Download.png" width="20" height="20"
											alt="Download">&nbsp;<a href="#"><img
												src="../resources/images/Print2.png" width="20" height="20" alt="print"></a></td>
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
										<td align="center"><a href="#"><img
												src="../resources/images/View.png" width="20" height="20" alt=""></a>&nbsp;<img
											src="../resources/images/Download.png" width="20" height="20"
											alt="Download">&nbsp;<a href="#"><img
												src="../resources/images/Print2.png" width="20" height="20" alt="print"></a></td>
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
										<td align="center"><a href="#"><img
												src="../resources/images/View.png" width="20" height="20" alt=""></a>&nbsp;<img
											src="../resources/images/Download.png" width="20" height="20"
											alt="Download">&nbsp;<a href="#"><img
												src="../resources/images/Print2.png" width="20" height="20" alt="print"></a></td>
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
										<td align="center"><a href="#"><img
												src="../resources/images/View.png" width="20" height="20" alt=""></a>&nbsp;<img
											src="../resources/images/Download.png" width="20" height="20"
											alt="Download">&nbsp;<a href="#"><img
												src="../resources/images/Print2.png" width="20" height="20" alt="print"></a></td>
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
										<td align="center"><a href="#"><img
												src="../resources/images/View.png" width="20" height="20" alt=""></a>&nbsp;<img
											src="../resources/images/Download.png" width="20" height="20"
											alt="Download">&nbsp;<a href="#"><img
												src="../resources/images/Print2.png" width="20" height="20" alt="print"></a></td>
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
										<td align="center"><a href="#"><img
												src="../resources/images/View.png" width="20" height="20" alt=""></a>&nbsp;<img
											src="../resources/images/Download.png" width="20" height="20"
											alt="Download">&nbsp;<a href="#"><img
												src="../resources/images/Print2.png" width="20" height="20" alt="print"></a></td>
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
										<td align="center"><a href="#"><img
												src="../resources/images/View.png" width="20" height="20" alt=""></a>&nbsp;<img
											src="../resources/images/Download.png" width="20" height="20"
											alt="Download">&nbsp;<a href="#"><img
												src="../resources/images/Print2.png" width="20" height="20" alt="print"></a></td>
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
										<td align="center"><a href="#"><img
												src="../resources/images/View.png" width="20" height="20" alt=""></a>&nbsp;<img
											src="../resources/images/Download.png" width="20" height="20"
											alt="Download">&nbsp;<a href="#"><img
												src="../resources/images/Print2.png" width="20" height="20" alt="print"></a></td>
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
										<td align="center"><a href="#"><img
												src="../resources/images/View.png" width="20" height="20" alt=""></a>&nbsp;<img
											src="../resources/images/Download.png" width="20" height="20"
											alt="Download">&nbsp;<a href="#"><img
												src="../resources/images/Print2.png" width="20" height="20" alt="print"></a></td>
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
										<td align="center"><a href="#"><img
												src="../resources/images/View.png" width="20" height="20" alt=""></a>&nbsp;<img
											src="../resources/images/Download.png" width="20" height="20"
											alt="Download">&nbsp;<a href="#"><img
												src="../resources/images/Print2.png" width="20" height="20" alt="print"></a></td>
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
										<td align="center"><a href="#"><img
												src="../resources/images/View.png" width="20" height="20" alt=""></a>&nbsp;<img
											src="../resources/images/Download.png" width="20" height="20"
											alt="Download">&nbsp;<a href="#"><img
												src="../resources/images/Print2.png" width="20" height="20" alt="print"></a></td>
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
										<td align="center"><a href="#"><img
												src="../resources/images/View.png" width="20" height="20" alt=""></a>&nbsp;<img
											src="../resources/images/Download.png" width="20" height="20"
											alt="Download">&nbsp;<a href="#"><img
												src="../resources/images/Print2.png" width="20" height="20" alt="print"></a></td>
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
										<td align="center"><a href="#"><img
												src="../resources/images/View.png" width="20" height="20" alt=""></a>&nbsp;<img
											src="../resources/images/Download.png" width="20" height="20"
											alt="Download">&nbsp;<a href="#"><img
												src="../resources/images/Print2.png" width="20" height="20" alt="print"></a></td>
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
										<td align="center"><a href="#"><img
												src="../resources/images/View.png" width="20" height="20" alt=""></a>&nbsp;<img
											src="../resources/images/Download.png" width="20" height="20"
											alt="Download">&nbsp;<a href="#"><img
												src="../resources/images/Print2.png" width="20" height="20" alt="print"></a></td>
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
										<td align="center"><a href="#"><img
												src="../resources/images/View.png" width="20" height="20" alt=""></a>&nbsp;<img
											src="../resources/images/Download.png" width="20" height="20"
											alt="Download">&nbsp;<a href="#"><img
												src="../resources/images/Print2.png" width="20" height="20" alt="print"></a></td>
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
										<td align="center"><a href="#"><img
												src="../resources/images/View.png" width="20" height="20" alt=""></a>&nbsp;<img
											src="../resources/images/Download.png" width="20" height="20"
											alt="Download">&nbsp;<a href="#"><img
												src="../resources/images/Print2.png" width="20" height="20" alt="print"></a></td>
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
										<td align="center"><a href="#"><img
												src="../resources/images/View.png" width="20" height="20" alt=""></a>&nbsp;<img
											src="../resources/images/Download.png" width="20" height="20"
											alt="Download">&nbsp;<a href="#"><img
												src="../resources/images/Print2.png" width="20" height="20" alt="print"></a></td>
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
 </div>
    <!-- main --> 
    


</body>
</html>