<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery(".megamenu").megamenu();
	});
</script>
<script type="text/javascript" src="../resources/js/expandCollapse.js"></script>
<!-- JAVASCRIPT FILES -->
<script type="text/javascript" src="../resources/js/slider.js"></script>
<link href="../resources/css/jquery-ui.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript" src="/media/js/jquery.js"></script>
<script src="../resources/js/jquery.dataTables.nightly.js"></script>
<script src="../resources/js/searchResultsdatatable.js"></script>

<script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>
</head>

<body class="job_board">
	<div class="ad_page_top">
		<img src="../resources/images/ads/banner_ad_fpo.png" />
	</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">


			<div class="main">
				<jsp:include page="../templates/templates_header.jsp"></jsp:include>
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

					<div class="job_Advancedsearch_main">
						<form method="">

							<!--changed from an h1 to an h2-->
							<h2 class="noTopBottomBorder">Advanced Search</h2>
						<form:form method="" action="" commandName="jobSearchResultForm">
							<div class="job_Advancedsearch_devider">
								<div class="input_grp1">
								<form:input path="keywords" maxlength="60" id="keywords" cssClass="jb_input1" />
									<!-- <input type="text" name="keywords" id="keywords"
										class="jb_input1" /><br /> -->
									<div class="toolTipBefore">
										<label for="keywords">Job Title, Keywords, Job ID,
											Company Name </label>
									</div>
									<div class="toolTip">
										<span class="classic"><p>Type in your search
												criteria here. Include any group of terms related to your
												desired position.</p></span>
									</div>
								</div>
								<br />
								<div class="input_grp1 marginTop10">
								<form:input path="cityState" id="cityState" cssClass="jb_input2" />
									<!-- <input type="text" name="cityState" id="cityState"
										class="jb_input2" /> --><br />
									<div class="toolTipBefore">
										<label for="cityState">City and State or ZIP Code </label>
									</div>
									<div class="toolTip">
										<span class="classic"><p>Enter the city and state
												or zip code of the location you want to search. Then select
												a radius to expand your search up to 100 miles from your
												starting point.</p></span>
									</div>
								</div>

								<div class="input_grp2 marginTop10">
									<form:select path="radius" id="radius" cssClass="jb_input3"	onchange="validateRadius();">
									<form:option label="--" value="0" />
									<!-- USE <form:options/> while dynamically populating the values  -->
									<form:option label="5 Miles" value="5" />
									<form:option label="10 Miles" value="10" />
									<form:option label="25 Miles" value="25" />
									<form:option label="50 Miles" value="50" />
									<form:option label="100 Miles" value="100" />
									</form:select>
									<label for="radius">Radius</label>
								</div>
								<div class="input_grp3New">
								<div class="FormErrorDisplayText" id="findSearchInfo"></div>
								<input type="button" id="submitval" value="Find Jobs" class="orange jb_search_submit" />
									<!-- <a href="" class="btn_sm orange jb_search_submit">Find Jobs</a> -->
								</div>
							</div>

							<div class="job_Advancedsearch_devider">
								<h3>Ads Selection</h3>


								<!--these div classes were switched from lableText to radioLableText-->
								<div class="floatLeft">
									<div class="radioLableText">
										<input name="chkbNationalAds" type="checkbox" value="">
									</div>
									<div class="radioLableText">
										<label for="chkbNationalAds">Include National Ads</label>&nbsp;&nbsp;&nbsp;
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="floatLeft">
									<div class="radioLableText">
										<input name="chkbInternationalAds" type="checkbox" value="">
									</div>
									<div class="radioLableText">
										<label for="chkbNationalAds">Include International Ads</label>
									</div>
								</div>
							</div>

							<div class="job_Advancedsearch_devider">
								<h3>Exclude</h3>
								<p>
									Exclude items from your search by entering one keyword per text
									box. Click 'Save and Add Another' to open additional text
									boxes. If you select 'Entire Job Detail' from the drop-down
									menu, jobs containing the keyword(s) you entered anywhere in
									the description will be excluded.

									<!--removed 2 br tags-->


								</p>

								<div class="row">
									<div class="lableText">Exclude from:</div>
									<div class="input_grp3">
										<select name="exclude" id="exclude" class="jb_input3">
											<option>Job Title</option>
											<option>Company Name</option>
											<option>Entire Job Detail</option>
										</select>
									</div>
									<div class="input_grpNew marginTop10 ">
										<input type="text" name="Exclude" class="jb_input2" />
									</div>
									<div class="clearfix"></div>
									<div class="">
										<a href="#">Save and Add Another</a>
									</div>
								</div>

							</div>

							<div class="job_Advancedsearch_devider">
								<h3>Skills</h3>


								<div class="row  marginTop5">


									<div class="floatLeft">
										<input type="text" name="Skills" id="Skills" class="jb_input2" />
									</div>
									<div class="toolTip marginLeft5 marginTop5">
										<span class="classic"><p>Include any special skills
												you might have to find jobs that match your specific
												qualifications.</p></span>
									</div>


								</div>

							</div>

							<div class="job_Advancedsearch_devider">
								<h3>Location</h3>


								<div class="row">
									<div class="lableText2">
										<input type="radio" name="radio" id="ZipCode" value="ZipCode">
										<label for="ZipCode">By Zip Code:</label>
									</div>
									<div class="input_grp3">
										<select name="ZipCode" id="ZipCode" class="jb_input3">
											<option selected="">Within 5 miles of</option>
											<option>Within 10 miles of</option>
											<option>Within 25 miles of</option>
											<option>Within 50 miles of</option>
											<option>Within 100 miles of</option>
										</select>
									</div>
									<div class="input_grp4 ">
										<input type="text" name="Exclude" class="jb_input3" />
									</div>


								</div>
								<div class="row">
									<div class="lableText2">
										<input type="radio" name="radio" id="StateProvince"
											value="StateProvince"> <label for="StateProvince">By
											State / Province:</label>
									</div>
									<div class="input_grp3">
										<select name="StateProvince" class="jb_input3"
											id="StateProvince">
											<option selected>NO PREFERENCE</option>
											<option>Alabama</option>
											<option>Alaska</option>
											<option>Arizona</option>
											<option>Arkansas</option>
											<option>California</option>
											<option>Colorado</option>
											<option>Connecticut</option>
											<option>Delaware</option>
											<option>District of Columbia</option>
											<option>Florida</option>
											<option>Georgia</option>
											<option>Hawaii</option>
											<option>Idaho</option>
											<option>Illinois</option>
											<option>Indiana</option>
											<option>Iowa</option>
											<option>Kansas</option>
											<option>Kentucky</option>
											<option>Louisiana</option>
											<option>Maine</option>
											<option>Maryland</option>
											<option>Massachusetts</option>
											<option>Michigan</option>
											<option>Minnesota</option>
											<option>Mississippi</option>
											<option>Missouri</option>
											<option>Montana</option>
											<option>Nebraska</option>
											<option>Nevada</option>
											<option>New Hampshire</option>
											<option>New Jersey</option>
											<option>New Mexico</option>
											<option>New York</option>
											<option>North Carolina</option>
											<option>North Dakota</option>
											<option>Ohio</option>
											<option>Oklahoma</option>
											<option>Oregon</option>
											<option>Pennsylvania</option>
											<option>Rhode Island</option>
											<option>South Carolina</option>
											<option>South Dakota</option>
											<option>Tennessee</option>
											<option>Texas</option>
											<option>Utah</option>
											<option>Vermont</option>
											<option>Virginia</option>
											<option>Washington</option>
											<option>West Virginia</option>
											<option>Wisconsin</option>
											<option>Wyoming</option>
										</select>
									</div>

								</div>
								<div class="row">
									<div class="lableText2">
										<input type="radio" name="radio" id="Country" value="Country">
										<label for="country"> By Country</label>
									</div>
									<div class="input_grp3">
										<select name="Country" class="jb_input3" id="Country">
											<option selected>NO PREFERENCE</option>
											<option>United States</option>
										</select>
									</div>
								</div>
								<div class="row">
									<div class="lableText2">
										<input type="radio" name="radio" id="metroArea"
											value="metroArea"> <label for="ZipCode">By
											Metro Area:</label>
									</div>
									<div class="input_grp3">
										<select name="exclude" id="metroArea" class="jb_input3">
											<option selected>NO PREFERENCE</option>
											<option>Atlanta, GA</option>
											<option>Austin, TX</option>
											<option>Baltimore, MD</option>
											<option>Birmingham, AL</option>
											<option>Boston, MA</option>
											<option>Buffalo, NY</option>
											<option>Calgary, AB</option>
											<option>Charlotte, NC</option>
											<option>Chicago, IL</option>
											<option>Cincinnati, OH</option>
											<option>Cleveland, OH</option>
											<option>Columbus, OH</option>
											<option>Dallas, TX</option>
											<option>Denver, CO</option>
											<option>Des Moines, IA</option>
											<option>Detroit, MI</option>
											<option>Hartford, CT</option>
											<option>Houston, TX</option>
											<option>Indianapolis, IN</option>
											<option>Jacksonville, FL</option>
											<option>Kansas City, MO</option>
											<option>Las Vegas, NV</option>
											<option>Los Angeles, CA</option>
											<option>Louisville, KY</option>
											<option>Memphis, TN</option>
											<option>Miami, FL</option>
											<option>Milwaukee, WI</option>
											<option>Minneapolis, MN</option>
											<option>Montreal, QC</option>
											<option>Nashville, TN</option>
											<option>New Orleans, LA</option>
											<option>New York, NY</option>
											<option>Oklahoma City, OK</option>
											<option>Orlando, FL</option>
											<option>Philadelphia, PA</option>
											<option>Phoenix, AZ</option>
											<option>Pittsburgh, PA</option>
											<option>Portland, ME</option>
											<option>Portland, OR</option>
											<option>Providence, RI</option>
											<option>Raleigh, NC</option>
											<option>Richmond, VA</option>
											<option>Rochester, NY</option>
											<option>Sacramento, CA</option>
											<option>Salt Lake City, UT</option>
											<option>San Antonio, TX</option>
											<option>San Bernardino, CA</option>
											<option>San Diego, CA</option>
											<option>San Francisco, CA</option>
											<option>Seattle, WA</option>
											<option>Silicon Valley</option>
											<option>St Louis, MO</option>
											<option>Tampa, FL</option>
											<option>Toronto, ON</option>
											<option>Vancouver, BC</option>
											<option>Virginia Beach, VA</option>
											<option>Washington, DC</option>
										</select>
									</div>
								</div>


							</div>

							<div class="job_Advancedsearch_devider">
								<h3>Employment Type</h3>
								<div class="row">
									<select name="employmentType" class="jb_input3"
										id="employmentType">
										<option selected="selected">Select</option>
										<option>Full Time</option>
										<option>Part Time</option>
										<option>Per Diem</option>
										<option>Contract/Travel</option>
										<option>Work From Home</option>
										<option>Locum Tenens</option>
									</select>


								</div>

							</div>
							<div class="job_Advancedsearch_devider">
								<h3>Search Results View Options</h3>
								<div class="row">
									<div class="lableText2">Sorted by:</div>
									<div class="lableText">
										<input name="relevance" type="radio" value="">
									</div>
									<div class="lableText">
										<label for="relevance">Relevance</label>
									</div>
									<div class="lableText">&nbsp;&nbsp;</div>
									<div class="lableText">
										<input name="date" type="radio" value="">
									</div>
									<div class="lableText">
										<label for="date">Date</label>
									</div>
								</div>
								<div class="row">
									<div class="lableText2">Job Posted:</div>
									<div class="lableText">Within</div>
									<div class="floatLeft">
										<select name="days" id="days" class="jb_input4">
											<option>30</option>
											<option>21</option>
											<option>14</option>
											<option>10</option>
											<option>7</option>
											<option>2</option>
											<option>1</option>
										</select>
									</div>
									<div class="lableText">&nbsp;Days</div>
								</div>
								<div class="row">
									<div class="lableText2">Results Displayed:</div>
									<div class="floatLeft">
										<select name="days" id="days" class="jb_input4">
											<option>30</option>
											<option>10</option>
											<option>20</option>
											<option>30</option>
											<option>40</option>
											<option>50</option>
										</select>
									</div>
									<div class="lableText">&nbsp;per page</div>
								</div>


							</div>
							<input type="button" id="submitvalAdv" value="Find Jobs" class="orange jb_search_submit" />
					</form:form>
							<!-- <a href="" class="btn_sm orange jb_search_submit">Find Jobs</a>  -->
							<a href="">Clear Selection</a>



							<!-- search_form -->

							<!--<div class="search_info_box1">
								<p class="search_message">JOIN THE <span>ADVANCE</span> NETWORK</p>
									<ul>
									<li>Apply to jobs faster</li>
									<li>Post a resume to be found by registered employers</li>
									<li>Create a Job Alert and more for free</li>
									</ul>

								<a href="">Create an Account</a>
								
							</div> search_info_box1 -->

							<div class="search_info_box2">
								<ul>
									<li><span class="uppercase bold">My Recent
											Searches:</span><br /> <a href="">Clear All</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a
										href="">See All</a></li>

									<li>May 16, 2012, 7:00 am<br />Search by: <span class="jb"><a
											href="">Physical Therapist</a></span></li>
									<li>May 17, 2012, 8:00 am<br />Search by: <span class="jb"><a
											href="">Pediatric Nurse</a></span></li>
									<li class="last">May 18, 2012, 9:00 am<br />Search by: <span
										class="jb"><a href="">ER Nurse</a></span></li>
								</ul>

							</div>
							<!-- search_info_box2 -->
							<!-- browse_bar -->

						</form>
					</div>
					<!-- job_search_main -->
					<!-- content_columns -->
					<div class="clearfix"></div>
				</div>
				<!-- content_wrapper -->

				<div class="content_columns_search_results"  id="tableContent">
					<jsp:include page="jobboardsearchresultsBody.jsp"></jsp:include>
					<BR class="clearfix">
				</div>
			</div>
			<!-- main -->

		</div>
		<!-- end main_wrapper_inside -->
	</div>
	<!-- end main_wrapper_outside -->
	<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
</body>
</html>