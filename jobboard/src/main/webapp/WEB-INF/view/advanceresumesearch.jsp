<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />

<script type="text/javascript" src="../resources/js/expandCollapse.js"></script>
<!-- JAVASCRIPT FILES -->
<script type="text/javascript" src="../resources/js/slider.js"></script>
<link href="../resources/css/jquery-ui.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript" src="/media/js/jquery.js"></script>
<script src="../resources/js/jquery.dataTables.nightly.js"></script>
<script src="../resources/js/searchResultsdatatable.js"></script>

<script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>

<script type="text/javascript">
	jQuery(document).ready(function() {
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
						<div class="headerLoginSectionColumns width205">
							<span class="boldText">Job Seeker:</span><br>
							<div class="PopUpToolTip">
								<a href="#">Why <strong>advance</strong>?
								</a> <span class="classic01">
									<p class="FontWeight marginBottom10">When you sign up,
										ADVANCE gives you:</p>
									<div class="FontWeight FontSize12 OrangeDot FontBlack">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Access to thousands of
										healthcare job opportunities</div>
									<div class="FontWeight FontSize12 OrangeDot FontBlack">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The best healthcare
										content you can get anywhere</div>
									<div class="FontWeight FontSize12 OrangeDot FontBlack">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hours of informative and
										entertaining multimedia</div>
									<div class="FontWeight FontSize12 OrangeDot FontBlack">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The latest news, articles,
										product reviews and much more!</div>
									<p class=" marginTop10">And it's all FREE!</p>
								</span>
							</div>
							<div class="floatleft">
								<span> <a href="">Login</a> | <a href="">Sign Up</a> |
								</span>
							</div>
						</div>
						<!-- loginHeader -->
						<div class="headerLoginSectionColumns">
							<span class="boldText">Employer:</span><br> <a href="">Login</a>
							| <a href="">Post Jobs</a>
						</div>
						<!-- loginHeader -->
						<div class="headerLoginSectionColumns">
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



				<div class="row">

					<div class="row">
						<form method="">

							<!--changed from an h1 to an h2-->
							<h2 class="noTopBottomBorder">Advanced Search</h2>
							<div class="job_AdvancedsearchResume">
								<div class="job_Advancedsearch_devider_border_none">
									<div class="input_grp1">
										<input name="keywords" type="text"
											class="jb_input1 InputActiveText" id="keywords"
											value="Enter Keywords" />
									</div>
									<div class="toolTip marginTop6 marginLeft20">
										<span class="classic">You can enter a job title,
											specialty, skill or any other keywords that describe the
											position you're trying to fill.</span>
									</div>
									<br />
									<div class="input_grp1 marginTop10">
										<input type="text" name="city_state" id="city_state"
											class="jb_input2" /><br />
										<div class="toolTipBefore">
											<label for="city_state">City and State or ZIP Code </label>
										</div>
										<div class="toolTip">
											<span class="classic">Narrow your search to
												job-seekers in a specific area by entering the location
												here. Then select a search radius in the following drop-down
												menu.</span>
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
									<div class="input_grp3New">
										<a href="" class="btn_sm orange jb_search_submit">Search</a>
									</div>
								</div>
							</div>

							<div class="job_AdvancedsearchResume">
								<div class=" rowEvenNewSpacing">
									<span class="lableText5 width165">Candidate's Desired
										Position:</span><input type="text" name="firstName" class="jb_input1" />
								</div>
								<div class="rowBox">
									<div class="floatLeft marginTop3">Resumes Posted:</div>
									<div class="floatLeft marginTop3">&nbsp;&nbsp;&nbsp;From:</div>
									<div class="floatLeft">
										<div class="floatLeft">
											<input type="text" name="firstName"
												class="EDTextBox FontSize15" />
										</div>
										<div class="calender">
											<a href="#"><img src="../resources/images/tranBg.png" width="14"
												height="14" alt="Datepick"></a>
										</div>
									</div>
									<div class="floatLeft marginTop3 marginLeft25">To:</div>
									<div class="floatLeft">
										<div class="floatLeft">
											<input type="text" name="firstName"
												class="EDTextBox FontSize15" />
										</div>
										<div class="calender">
											<a href="#"><img src="../resources/images/tranBg.png" width="14"
												height="14" alt="Datepick"></a>
										</div>
									</div>
								</div>
							</div>
							<div class="job_AdvancedsearchResume">
								<h3>Location</h3>

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
										<input type="radio" name="radio" id="ZipCode" value="ZipCode">
										<label for="ZipCode">By Zip Code:</label>
									</div>
									<div class="input_grp3">
										<select name="ZipCode" id="ZipCode" class="jb_input3">
											<option selected="">Within 10 miles of</option>
											<option selected="">Within 25 miles of</option>
											<option>Within 50 miles of</option>
											<option>Within 100 miles of</option>
										</select>
									</div>
									<div class="input_grp4 ">
										<input name="Exclude" type="text"
											class="jb_input3 InputActiveText" value="Enter Zip Code" />
									</div>


								</div>

								<div class="row">
									<div class="lableText2">
										<input type="radio" name="radio" id="Country" value="Country">
										<label for="country"> By Country:</label>
									</div>
									<div class="input_grp3">
										<select name="Country" class="jb_input3" id="Country">
											<option selected>NO PREFERENCE</option>
											<option value="Afghanistan">Afghanistan</option>
											<option value="Albania">Albania</option>
											<option value="Algeria">Algeria</option>
											<option value="Andorra">Andorra</option>
											<option value="Angola">Angola</option>
											<option value="Antigua and Barbuda">Antigua and
												Barbuda</option>
											<option value="Argentina">Argentina</option>
											<option value="Armenia">Armenia</option>
											<option value="Australia">Australia</option>
											<option value="Austria">Austria</option>
											<option value="Azerbaijan">Azerbaijan</option>
											<option value="Bahamas">Bahamas</option>
											<option value="Bahrain">Bahrain</option>
											<option value="Bangladesh">Bangladesh</option>
											<option value="Barbados">Barbados</option>
											<option value="Belarus">Belarus</option>
											<option value="Belgium">Belgium</option>
											<option value="Belize">Belize</option>
											<option value="Benin">Benin</option>
											<option value="Bermuda">Bermuda</option>
											<option value="Bhutan">Bhutan</option>
											<option value="Bolivia">Bolivia</option>
											<option value="Bosnia and Herzegovina">Bosnia and
												Herzegovina</option>
											<option value="Botswana">Botswana</option>
											<option value="Brazil">Brazil</option>
											<option value="Brunei">Brunei</option>
											<option value="Bulgaria">Bulgaria</option>
											<option value="Burkina Faso">Burkina Faso</option>
											<option value="Burma">Burma</option>
											<option value="Burundi">Burundi</option>
											<option value="Cambodia">Cambodia</option>
											<option value="Cameroon">Cameroon</option>
											<option value="Canada">Canada</option>
											<option value="Cape Verde">Cape Verde</option>
											<option value="Central African Republic">Central
												African Republic</option>
											<option value="Chad">Chad</option>
											<option value="Chile">Chile</option>
											<option value="China">China</option>
											<option value="Colombia">Colombia</option>
											<option value="Comoros">Comoros</option>
											<option value="Congo (Brazzaville)">Congo
												(Brazzaville)</option>
											<option value="Congo (Kinshasa)">Congo (Kinshasa)</option>
											<option value="Costa Rica">Costa Rica</option>
											<option value="Cote d'Ivoire (Ivory Coast)">Cote
												d'Ivoire (Ivory Coast)</option>
											<option value="Croatia">Croatia</option>
											<option value="Cuba">Cuba</option>
											<option value="Cyprus">Cyprus</option>
											<option value="Czech Republic">Czech Republic</option>
											<option value="Denmark">Denmark</option>
											<option value="Djibouti">Djibouti</option>
											<option value="Dominica">Dominica</option>
											<option value="Dominican Republic">Dominican
												Republic</option>
											<option value="Ecuador">Ecuador</option>
											<option value="Egypt">Egypt</option>
											<option value="El Salvador">El Salvador</option>
											<option value="Equatorial Guinea">Equatorial Guinea</option>
											<option value="Eritrea">Eritrea</option>
											<option value="Estonia">Estonia</option>
											<option value="Ethiopia">Ethiopia</option>
											<option value="Fiji">Fiji</option>
											<option value="Finland">Finland</option>
											<option value="France">France</option>
											<option value="Gabon">Gabon</option>
											<option value="Gambia">Gambia</option>
											<option value="Georgia">Georgia</option>
											<option value="Germany">Germany</option>
											<option value="Ghana">Ghana</option>
											<option value="Gibraltar">Gibraltar</option>
											<option value="Greece">Greece</option>
											<option value="Grenada">Grenada</option>
											<option value="Guatemala">Guatemala</option>
											<option value="Guinea">Guinea</option>
											<option value="Guinea-Bissau">Guinea-Bissau</option>
											<option value="Guyana">Guyana</option>
											<option value="Haiti">Haiti</option>
											<option value="Holy See">Holy See</option>
											<option value="Honduras">Honduras</option>
											<option value="Hungary">Hungary</option>
											<option value="Iceland">Iceland</option>
											<option value="India">India</option>
											<option value="Indonesia">Indonesia</option>
											<option value="Iran">Iran</option>
											<option value="Iraq">Iraq</option>
											<option value="Ireland">Ireland</option>
											<option value="Israel">Israel</option>
											<option value="Italy">Italy</option>
											<option value="Jamaica">Jamaica</option>
											<option value="Japan">Japan</option>
											<option value="Jordan">Jordan</option>
											<option value="Kazakhstan">Kazakhstan</option>
											<option value="Kenya">Kenya</option>
											<option value="Kiribati">Kiribati</option>
											<option value="Korea, North">Korea, North</option>
											<option value="Korea, South">Korea, South</option>
											<option value="Kuwait">Kuwait</option>
											<option value="Kyrgyzstan">Kyrgyzstan</option>
											<option value="Laos">Laos</option>
											<option value="Latvia">Latvia</option>
											<option value="Lebanon">Lebanon</option>
											<option value="Lesotho">Lesotho</option>
											<option value="Liberia">Liberia</option>
											<option value="Libya">Libya</option>
											<option value="Liechtenstein">Liechtenstein</option>
											<option value="Lithuania">Lithuania</option>
											<option value="Luxembourg">Luxembourg</option>
											<option value="Macedonia, The Former Yugoslav Republic of">Macedonia,
												The Former Yugoslav Republic of</option>
											<option value="Madagascar">Madagascar</option>
											<option value="Malawi">Malawi</option>
											<option value="Malaysia">Malaysia</option>
											<option value="Maldives">Maldives</option>
											<option value="Mali">Mali</option>
											<option value="Malta">Malta</option>
											<option value="Marshall Islands">Marshall Islands</option>
											<option value="Mauritania">Mauritania</option>
											<option value="Mauritius">Mauritius</option>
											<option value="Mexico">Mexico</option>
											<option value="Micronesia, Federated States of">Micronesia,
												Federated States of</option>
											<option value="Moldova">Moldova</option>
											<option value="Monaco">Monaco</option>
											<option value="Mongolia">Mongolia</option>
											<option value="Morocco">Morocco</option>
											<option value="Mozambique">Mozambique</option>
											<option value="Namibia">Namibia</option>
											<option value="Nauru">Nauru</option>
											<option value="Nepal">Nepal</option>
											<option value="Netherlands">Netherlands</option>
											<option value="New Zealand">New Zealand</option>
											<option value="Nicaragua">Nicaragua</option>
											<option value="Niger">Niger</option>
											<option value="Nigeria">Nigeria</option>
											<option value="Norway">Norway</option>
											<option value="Oman">Oman</option>
											<option value="Pakistan">Pakistan</option>
											<option value="Palau">Palau</option>
											<option value="Panama">Panama</option>
											<option value="Papua New Guinea">Papua New Guinea</option>
											<option value="Paraguay">Paraguay</option>
											<option value="Peru">Peru</option>
											<option value="Philippines">Philippines</option>
											<option value="Poland">Poland</option>
											<option value="Portugal">Portugal</option>
											<option value="Qatar">Qatar</option>
											<option value="Romania">Romania</option>
											<option value="Russia">Russia</option>
											<option value="Rwanda">Rwanda</option>
											<option value="Saint Kitts and Nevis">Saint Kitts
												and Nevis</option>
											<option value="Saint Lucia">Saint Lucia</option>
											<option value="Saint Vincent and the Grenadines">Saint
												Vincent and the Grenadines</option>
											<option value="Samoa">Samoa</option>
											<option value="San Marino">San Marino</option>
											<option value="Sao Tome and Principe">Sao Tome and
												Principe</option>
											<option value="Saudi Arabia">Saudi Arabia</option>
											<option value="Senegal">Senegal</option>
											<option value="Seychelles">Seychelles</option>
											<option value="Sierra Leone">Sierra Leone</option>
											<option value="Singapore">Singapore</option>
											<option value="Slovakia">Slovakia</option>
											<option value="Slovenia">Slovenia</option>
											<option value="Solomon Islands">Solomon Islands</option>
											<option value="Somalia">Somalia</option>
											<option value="South Africa">South Africa</option>
											<option value="Spain">Spain</option>
											<option value="Sri Lanka">Sri Lanka</option>
											<option value="Sudan">Sudan</option>
											<option value="Suriname">Suriname</option>
											<option value="Swaziland">Swaziland</option>
											<option value="Sweden">Sweden</option>
											<option value="Switzerland">Switzerland</option>
											<option value="Syria">Syria</option>
											<option value="Taiwan R.O.C.">Taiwan R.O.C.</option>
											<option value="Tajikistan">Tajikistan</option>
											<option value="Tanzania">Tanzania</option>
											<option value="Thailand">Thailand</option>
											<option value="Togo">Togo</option>
											<option value="Tonga">Tonga</option>
											<option value="Trinidad and Tobago">Trinidad and
												Tobago</option>
											<option value="Tunisia">Tunisia</option>
											<option value="Turkey">Turkey</option>
											<option value="Turkmenistan">Turkmenistan</option>
											<option value="Tuvalu">Tuvalu</option>
											<option value="Uganda">Uganda</option>
											<option value="Ukraine">Ukraine</option>
											<option value="United Arab Emirates">United Arab
												Emirates</option>
											<option value="United Kingdom">United Kingdom</option>
											<option selected="" value="United States">United
												States</option>
											<option value="Uruguay">Uruguay</option>
											<option value="Uzbekistan">Uzbekistan</option>
											<option value="Vanuatu">Vanuatu</option>
											<option value="Venezuela">Venezuela</option>
											<option value="Vietnam">Vietnam</option>
											<option value="Yemen">Yemen</option>
											<option value="Zambia">Zambia</option>
											<option value="Zimbabwe">Zimbabwe</option>
										</select>
									</div>
								</div>



							</div>

							<div class="job_AdvancedsearchResume">
								<h3>Current Career Level</h3>
								<div class="row">
									<select name="employmentType" class="jb_input3"
										id="employmentType">
										<option selected="selected">--- Select ---</option>
										<option value="Student (High School)">Student (High
											School)</option>
										<option value="Student (Undergraduate / Graduate)">Student
											(Undergraduate / Graduate)</option>
										<option value="Entry Level">Entry Level</option>
										<option value="Experienced">Experienced</option>
										<option value="Manager">Manager</option>
										<option value="Executive">Executive</option>
										<option value="Senior Executive">Senior Executive</option>
									</select>


								</div>

							</div>
							<div class="job_AdvancedsearchResume">
								<h3>United States Work Authorization</h3>
								<div class="row">
									<select name="employmentType" class="jb_input3 width350"
										id="employmentType">
										<option selected="selected">Select</option>
										<option selected="" value="--- Select ---">--- Select
											---</option>
										<option
											value="I am authorized to work in this country for any employer">I
											am authorized to work in this country for any employer</option>
										<option
											value="I am authorized to work in this country for my present employer only">I
											am authorized to work in this country for my present employer
											only</option>
										<option value="I require sponsorship to work in this country">I
											require sponsorship to work in this country</option>
									</select> </select>


								</div>

							</div>
							<div class="job_AdvancedsearchResume">
								<h3>Employment Details</h3>
								<div class="row">
									<div class="lableText2">Employment Type:</div>
									<div class="floatLeft">
										<select name="days" id="days" class="jb_input3">
											<option value="--- Select One ---">--- Select One
												---</option>
											<option value="Full-Time">Full-Time</option>
											<option value="Part-Time">Part-Time</option>
											<option value="Per Diem">Per Diem</option>
											<option value="Contract/Travel">Contract/Travel</option>
											<option value="Work From Home">Work From Home</option>
											<option value="Locum Tenens">Locum Tenens</option>
										</select>
									</div>
								</div>
								<div class="row">
									<div class="lableText2">Years of Experience:</div>
									<div class="floatLeft">
										<input type="text" name="firstName"
											class="jb_input4 FontSize15" />
									</div>
									<div class="lableText"></div>
								</div>
								<div class="row">
									<div class="lableText2">Willing to Relocate:</div>
									<div class="lableText">
										<input name="relevance" type="radio" value="">
									</div>
									<div class="lableText">
										<label for="relevance">Yes</label>
									</div>
									<div class="lableText">&nbsp;&nbsp;</div>
									<div class="lableText">
										<input name="relevance" type="radio" value="">
									</div>
									<div class="lableText">
										<label for="date">No</label>
									</div>
								</div>
							</div>
							<div class="job_AdvancedsearchResume">
								<h3>Other Details</h3>
								<div class="row">
									<div class="row">
										<div class="lableText1 marginTop10">Degrees:</div>
										<div class="input_grp5 ">
											<textarea id="Body Text:" class="textareaBoxCResume" rows="3"
												cols="45" name="Body Text:"></textarea>

										</div>
									</div>
									<div class="row ">
										<div class="lableText1 marginTop10">Certifications:</div>
										<div class="input_grp5 ">
											<textarea id="Body Text:" class="textareaBoxCResume" rows="3"
												cols="45" name="Body Text:"></textarea>

										</div>
									</div>
									<div class="row ">
										<div class="lableText1 marginTop10">Skills:</div>
										<div class="input_grp5 ">
											<textarea id="Body Text:" class="textareaBoxCResume" rows="3"
												cols="45" name="Body Text:"></textarea>

										</div>
									</div>
									<div class="row">
										<div class="lableText1 marginTop10">Languages:</div>
										<div class="floatLeft">
											<select name="days" id="days" class="jb_input3">
												<option value="--- Select One ---">--- Select One
													---</option>
												<option value="Chinese (Mandarin)">Chinese
													(Mandarin)</option>
												<option selected="" value="English">English</option>
												<option value="French">French</option>
												<option value="German">German</option>
												<option value="Italian">Italian</option>
												<option value="Korean">Korean</option>
												<option value="Portuguese">Portuguese</option>
												<option value="Russian">Russian</option>
												<option value="Spanish ">Spanish</option>
												<option value="Tagalog">Tagalog</option>
												<option value="Vietnamese">Vietnamese</option>
												<option value="Other">Other</option>
											</select>
										</div>
									</div>

								</div>

							</div>

							<div class="job_AdvancedsearchResume">
								<h3>Desired Salary</h3>
								<div class="row">
									<div class="lableText1 marginTop10">Minimum:</div>
									<div class="input_grp4 ">
										<input type="text" name="Exclude" class="jb_input3 FontSize15" />
									</div>
									<div class="lableText1 marginTop10">Maximum:</div>
									<div class="input_grp4 ">
										<input type="text" name="Exclude" class="jb_input3 FontSize15" />
									</div>
									<div class="floatLeft marginLeft10">
										<select name="days" id="days" class="jb_input3">
											<option value="--- USD ---">--- USD ---</option>
											<option>Peso</option>
											<option>Euro</option>
											<option>Yen</option>
											<option>CAD</option>

										</select>
									</div>
									<div class="floatLeft marginLeft10">
										<select name="days" id="days" class="jb_input3">
											<option value="--- per Year ---">--- Per Year ---</option>
											<option>Per Month</option>
											<option>Per Week</option>
											<option>Per Day</option>
											<option>Per Hour</option>
										</select>
									</div>

								</div>

							</div>
							<div class="job_AdvancedsearchResume marginBottom0">
								<h3>Search Results View Options</h3>
								<div class="row">
									<div class="lableText3 marginTop10">Sort By:</div>
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
									<div class="lableText3 marginTop10">Show Resumes Posted:
									</div>
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
									<div class="lableText3 marginTop10">Display:</div>
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
									<div class="lableText">&nbsp;resumes per page</div>
								</div>


							</div>
							<div class="job_AdvancedsearchResume">
								<div class=" paddingBottom10">
									<a href="" class="btn_sm orange jb_search_submit">Search
										Resumes</a> <a href="">Clear Selection</a>
								</div>
							</div>

							<div class="clearfix"></div>

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
				<div class="row">
					<div class="row paddingBottom05">
						<div class="floatLeft">
							<h1 class="FontSize24">200 resumes match your results</h1>
						</div>
					</div>


				</div>
				<div class="clearfix"></div>
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
											class="marginRight5"><label for="checkbox">Desired
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
										<td align="center">Yes</td>
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
										<td align="center">No</td>
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
										<td align="center">No</td>
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
										<td align="center">Yes</td>
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
										<td align="center">No</td>
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
										<td align="center">No</td>
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
										<td align="center">Yes</td>
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
										<td align="center">No</td>
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
										<td align="center">No</td>
										<td align="center">04/05/2012</td>
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
										<td align="center">Yes</td>
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
										<td align="center">No</td>
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
										<td align="center">No</td>
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
										<td align="center">Yes</td>
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
										<td align="center">No</td>
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
										<td align="center">No</td>
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
										<td align="center">Yes</td>
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
										<td align="center">No</td>
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
										<td align="center">No</td>
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
										<td align="center">Yes</td>
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
										<td align="center">No</td>
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



				<BR class="clearfix">
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
				<li><img src="../resources/images/email.png" class="foot_icon" /><a href="#">Sign
						Up for E-Newsletter</a></li>

				<li><img src="../resources/images/fbook_sm.png" class="foot_icon" /><a
					href="#">Connect on Facebook</a></li>

				<li><img src="../resources/images/L_In_sm.png" class="foot_icon" /><a
					href="#">Connect on LinkedIn</a></li>

				<li><img src="../resources/images/twitter_sm.png" class="foot_icon" /><a
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

</body>
</html>