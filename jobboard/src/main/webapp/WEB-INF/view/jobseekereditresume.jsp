<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript" src="javascripts/expandCollapse.js"></script>
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

						<!-- loginHeader -->

						<!-- loginHeader -->
						<div class="headerLoginSectionColumns">
							<span class="boldText">Welcome, (Job Seeker Name)!</span><br>
							<div class="floatRight">
								<span class="floatLeft"> <a href="">Log Out</a> | <a
									href="">My Dashboard</a></span>
							</div>
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
					<div class="popupHeader Padding0  OrangeBG">
						<h2>EDIT YOUR RESUME</h2>
						<span class="floatRight marginRight10"><a href="#"
							class="link_color3_emphasized FontSize12 FontWeight">Back to
								Dashboard</a></span>
					</div>
					<div class="clearfix"></div>
					<div class="MidContent_Wrapper FloatLeft ">
						<span>
							<h1
								class="marginTop3 marginLeft10 FloatLeft FontSize18 TextColor03">Resume
								Name:&nbsp;</h1>
							<h1 class="FontSize18 marginTop3">Registered Nurse Resume</h1>

						</span>
					</div>
					<div class="clearfix"></div>
				</div>
				<!---->
				<div class="clearfix"></div>
				<div class="searchResultsListing"></div>

				<!--Test-->
				<div class="searchResultsListing">
				
					<div class="searchResultsItem MarginBottom10">
						<ul class="searchResultsJobInfo closed">
							<li class="searchResultsColumn1">
								<div class="sectionHeader Padding0 Height28 LightGrayBG">
									<div class="floatLeft">
										<h2 class="width305 marginTop6 UpperCaseNone TextColor01">Contact
											Info</h2>
									</div>
									<div class="ESsection accord-open">
										<h2 class="UpperCaseNone FontSize12 marginTop1">
											<a href="#" class="edit">Edit this section</a>
										</h2>
									</div>
								</div>
							</li>

						</ul>
						<form:form method="get" action="saveResumeBuilder.html" commandName="createResume" modelAttribute="resumeform" id="editResumeForm" enctype="multipart/form-data" >
						<div class="searchResultsSubContent">

							<div class="job_seeker_login leftFormHolderResumepage marginTop0">
								<div class="rowEvenNewSpacing">
									<span class="lableText3">First Name:</span> <input type="text"
										name="mobileNo" class="job_seeker_password textBox350" /> <span
										class="required">(Required)</span>
								</div>
								<div class="rowEvenNewSpacing">
									<span class="lableText3">Middle Name:</span> <input type="text"
										name="JobTitle" class="job_seeker_password textBox350" />
								</div>
								<div class="rowEvenNewSpacing">
									<span class="lableText3">Last Name:</span> <input type="text"
										name="healthCareSubSplty"
										class="job_seeker_password textBox350" /> <span
										class="required">(Required)</span>
								</div>
								<div class="rowEvenNewSpacing">
									<span class="lableText3">Street Address:</span> <input
										type="text" name="healthCareSubSplty"
										class="job_seeker_password textBox350" /> <span
										class="required">(Required)</span>
								</div>
								<div class="rowEvenNewSpacing">
									<span class="lableText3"></span> <input type="text"
										name="healthCareSubSplty"
										class="job_seeker_password textBox350" /> <span
										class="required"></span>
								</div>

								<div class="rowEvenNewSpacing">
									<span class="lableText3">City:</span> <input type="text"
										name="healthCareSubSplty"
										class="job_seeker_password textBox350" /> <span
										class="required">(Required)</span>
								</div>
								<div class="row">
									<span class="lableTextSelect marginTop13 ">State /
										Province:</span> <select name="Country" id="Country"
										class="jb_input3 jb_input_width3">
										<option value="--- Select State ---">--- Select State
											---</option>
										<option value="Alabama">Alabama</option>
										<option value="Alaska">Alaska</option>
										<option value="American Samoa">American Samoa</option>
										<option value="Arizona">Arizona</option>
										<option value="Arkansas">Arkansas</option>
										<option value="California">California</option>
										<option value="Colorado">Colorado</option>
										<option value="Connecticut">Connecticut</option>
										<option value="Delaware">Delaware</option>
										<option value="District of Columbia">District of
											Columbia</option>
										<option value="Federated States of Micronesia">Federated
											States of Micronesia</option>
										<option value="Florida">Florida</option>
										<option value="Georgia">Georgia</option>
										<option value="Guam">Guam</option>
										<option value="Hawaii">Hawaii</option>
										<option value="Idaho">Idaho</option>
										<option value="Illinois">Illinois</option>
										<option value="Indiana">Indiana</option>
										<option value="Iowa">Iowa</option>
										<option value="Kansas">Kansas</option>
										<option value="Kentucky">Kentucky</option>
										<option value="Louisiana">Louisiana</option>
										<option value="Maine">Maine</option>
										<option value="Marshall Islands">Marshall Islands</option>
										<option value="Maryland">Maryland</option>
										<option value="Massachusetts">Massachusetts</option>
										<option value="Michigan">Michigan</option>
										<option value="Minnesota">Minnesota</option>
										<option value="Mississippi">Mississippi</option>
										<option value="Missouri">Missouri</option>
										<option value="Montana">Montana</option>
										<option value="Nebraska">Nebraska</option>
										<option value="Nevada">Nevada</option>
										<option value="New Hampshire">New Hampshire</option>
										<option value="New Jersey">New Jersey</option>
										<option value="New Mexico">New Mexico</option>
										<option value="New York">New York</option>
										<option value="North Carolina">North Carolina</option>
										<option value="North Dakota">North Dakota</option>
										<option value="Northern Mariana Islands">Northern
											Mariana Islands</option>
										<option value="Ohio">Ohio</option>
										<option value="Oklahoma">Oklahoma</option>
										<option value="Oregon">Oregon</option>
										<option value="Palau">Palau</option>
										<option value="Pennsylvania">Pennsylvania</option>
										<option value="Puerto Rico">Puerto Rico</option>
										<option value="Rhode Island">Rhode Island</option>
										<option value="South Carolina">South Carolina</option>
										<option value="South Dakota">South Dakota</option>
										<option value="Tennessee">Tennessee</option>
										<option value="Texas">Texas</option>
										<option value="Utah">Utah</option>
										<option value="Vermont">Vermont</option>
										<option value="Virgin Islands">Virgin Islands</option>
										<option value="Virginia">Virginia</option>
										<option value="Washington">Washington</option>
										<option value="West Virginia">West Virginia</option>
										<option value="Wisconsin">Wisconsin</option>
										<option value="Wyoming">Wyoming</option>

									</select> <span class="required marginTop8">(Required)</span>
								</div>
								<div class="rowEvenNewSpacing">
									<span class="lableText3">Zip Code:</span> <input type="text"
										name="healthCareSubSplty"
										class="job_seeker_password textBox350" /> <span
										class="required">(Required)</span>
								</div>
								<div class="row">
									<span class="lableTextSelect marginTop13 ">Country:</span> <select
										name="Country" id="Country" class="jb_input3 jb_input_width3">
										<option value="--- Select Country ---">--- Select
											Country ---</option>
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
										<option value="Dominican Republic">Dominican Republic</option>
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
										<option value="Saint Kitts and Nevis">Saint Kitts and
											Nevis</option>
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
									</select> <span class="required marginTop8">(Required)</span>
								</div>

								<div class="rowEvenNewSpacing MarginBottom10">
									<span class="lableText3">Phone Number:</span>
									<div class="floatLeft marginRight10"></div>
									<span class="floatLeft marginRight10"> <select
										name="exclude" id="exclude" class="jb_input75 marginTop0">
											<option value="Home">Home</option>
											<option selected="" value="Work">Work</option>
											<option value="Mobile">Mobile</option>
											<option value="Other">Other</option>
									</select>
									</span> <input type="text" name="healthCareSubSplty"
										class="job_seeker_password" /> <span class="required ">(Required)</span>
									<span class="required "><a href=""
										class="link_color1_emphasized">Save and add another phone
											number</a></span>
								</div>
							</div>
						</div>
						<input type="submit" value="Submit" />
					</form:form>
					
					</div>
					

					<div class="searchResultsItem">
						<ul class="searchResultsJobInfo closed">
							<li class="searchResultsColumn1">
								<div class="sectionHeader Padding0 Height28 LightGrayBG">
									<div class="floatLeft">
										<h2 class="width305 marginTop6 UpperCaseNone TextColor01">Objective</h2>
									</div>
									<div class="ESsection accord-open">
										<h2 class="UpperCaseNone FontSize12 marginTop1">
											<a href="#" class="edit">Edit this section</a>
										</h2>
									</div>
								</div>
							</li>

						</ul>
						<div class="searchResultsSubContent">

							<div class="job_seeker_login leftFormHolderResumepage marginTop0">
								<div class="row MarginBottom10 ">
									<div class="lableText3 marginTop10">Your Career
										Objective:</div>
									<div class="input_grp5 ">
										<textarea id="Body Text:" class="textareaBoxCResume" rows="5"
											cols="45" name="Body Text:"></textarea>
										<p>2000 characters remaining</p>
									</div>




								</div>
							</div>





						</div>
					</div>

					<div class="searchResultsItem">
						<ul class="searchResultsJobInfo closed">
							<li class="searchResultsColumn1">
								<div class="sectionHeader Padding0 Height28 LightGrayBG">
									<div class="floatLeft">
										<h2 class="width305 marginTop6 UpperCaseNone TextColor01">Work
											Experience</h2>
									</div>
									<div class="ESsection accord-open">
										<h2 class="UpperCaseNone FontSize12 marginTop1">
											<a href="#" class="edit">Edit this section</a>
										</h2>
									</div>
								</div>
							</li>

						</ul>
						<div class="searchResultsSubContent">

							<div class="job_seeker_login leftFormHolderResumepage marginTop0">
								<div class="rowEvenNewSpacing">
									<span class="lableText3">Job Title:</span> <input type="text"
										name="mobileNo" class="job_seeker_password textBox350" /> <span
										class="required">(Required)</span>
								</div>
								<div class="rowEvenNewSpacing">
									<span class="lableText3">Company Name:</span> <input
										type="text" name="JobTitle"
										class="job_seeker_password textBox350" /> <span
										class="required">(Required)</span>
								</div>
								<div class="class="row"">
									<span class="lableTextSelect marginTop13 ">Employment
										Type:</span> <select name="Country" id="Country"
										class="jb_input3 jb_input_width3">
										<option value="--- Select One ---">--- Employment
											Type ---</option>
										<option value="Full-Time">Full-Time</option>
										<option value="Part-Time">Part-Time</option>
										<option value="Per Diem">Per Diem</option>
										<option value="Contract/Travel">Contract/Travel</option>
										<option value="Work From Home">Work From Home</option>
										<option value="Locum Tenens">Locum Tenens</option>
									</select> <span class="required marginTop8">(Required)</span>

								</div>
								<div class="rowEvenNewSpacing">
									<span class="lableText3"> Start Date:</span> <input type="text"
										name="healthCareSubSplty" class="job_seeker_Resume" />
									<div class="calender">
										<a href="#"><img src="../resources/images/tranBg.png"
											width="14" height="14" alt="Datepick"></a>
									</div>
									<span class="required">(Required)</span>
								</div>
								<div class="row marginTop10">
									<span class="lableTextSelect marginTop10">End Date:</span> <input
										type="text" name="healthCareSubSplty"
										class="job_seeker_Resume" />
									<div class="calender">
										<a href="#"><img src="../resources/images/tranBg.png"
											width="14" height="14" alt="Datepick"></a>
									</div>
									<span class="required"><input name="" type="checkbox"
										value=""> </span>
									<div class="floatLeft marginLeft10 marginTop8">present</div>
									<span class="required">(Required)</span>
								</div>

								<div class="rowEvenNewSpacing">
									<span class="lableText3">Years at Position:</span> <input
										type="text" name="healthCareSubSplty"
										class="job_seeker_Resume" /> <span class="required">(Required)</span>
								</div>
								<div class="row">
									<span class="lableTextSelect marginTop13 ">Career Level:</span>
									<select name="Country" id="Country"
										class="jb_input3 jb_input_width3">
										<option value="--- Select One ---">--- Career Level
											---</option>
										<option value="Student (High School)">Student (High
											School)</option>
										<option value="Student (Undergraduate / Graduate)">Student
											(Undergraduate / Graduate)</option>
										<option value="Entry Level">Entry Level</option>
										<option value="Experienced">Experienced</option>
										<option value="Manager">Manager</option>
										<option value="Executive">Executive</option>
										<option value="Senior Executive">Senior Executive</option>
									</select> <span class="required marginTop8"><input name=""
										type="checkbox" value=""> </span>
									<div class=" floatLeft marginLeft10 marginTop5">
										<p>This is my current career level</p>
									</div>
									<span class="required marginTop8">(Required)</span>
								</div>
								<div class="row">
									<span class="lableTextSelect marginTop13 ">Annual
										Salary:</span> <select name="Country" id="Country"
										class="jb_input75 width200">
										<option>--- Annual Salary ---</option>
										<option value="$0 to $20,000">$0 to $20,000</option>
										<option value="$20,000 to $40,000">$20,000 to $40,000</option>
										<option value="$40,000 to $60,000">$40,000 to $60,000</option>
										<option value="$60,000 to $80,000">$60,000 to $80,000</option>
										<option value="$80,000 to $100,000">$80,000 to
											$100,000</option>
										<option value="$100,000+">$100,000+</option>
									</select>
									<div class="toolTip marginTop15 marginLeft10">
										<span class="classic">Select your annual salary from
											this drop-down menu or enter your hourly pay rate in the
											following text box.</span>
									</div>
									<span class="required marginTop8"></span>
								</div>

								<div class="rowEvenNewSpacing MarginBottom10">
									<span class="lableText3"> Hourly Pay Rate:</span> <input
										type="text" name="healthCareSubSplty"
										class="job_seeker_Resume" /> <span class="required "></span>
								</div>

								<div class="row MarginBottom10 ">
									<div class="lableText3 marginTop10">Summary/Job
										Description:</div>
									<div class="input_grp5 ">
										<textarea id="Body Text:" class="textareaBoxCResume" rows="3"
											cols="45" name="Body Text:"></textarea>
										<p>2000 characters remaining</p>
										<p>
											<a href="" class="link_color1_emphasized">Save and add
												another work experience</a>
										</p>
									</div>




								</div>
							</div>





						</div>
					</div>

					<div class="searchResultsItem">
						<ul class="searchResultsJobInfo closed">
							<li class="searchResultsColumn1">
								<div class="sectionHeader Padding0 Height28 LightGrayBG">
									<div class="floatLeft">
										<h2 class="width305 marginTop6 UpperCaseNone TextColor01">Education</h2>
									</div>
									<div class="ESsection accord-open">
										<h2 class="UpperCaseNone FontSize12 marginTop1">
											<a href="#" class="edit">Edit this section</a>
										</h2>
									</div>
								</div>
							</li>

						</ul>
						<div class="searchResultsSubContent">

							<div class="job_seeker_login leftFormHolderResumepage marginTop0">
								<div class="rowEvenNewSpacing">
									<span class="lableText3">Institution Name:</span> <input
										type="text" name="mobileNo"
										class="job_seeker_password textBox350" /> <span
										class="required">(Required)</span>
								</div>
								<div class="row">
									<span class="lableTextSelect marginTop13 ">Degree Level:</span>
									<select name="Country" id="Country"
										class="jb_input3 jb_input_width3">
										<option value="--- Select One ---">--- Degree Level
											---</option>
										<option value="High School Diploma or GED">High
											School Diploma or GED</option>
										<option value="Some college, no degree">Some college,
											no degree</option>
										<option value="Associate's Degree">Associate's Degree</option>
										<option value="Bachelor's Degree">Bachelor's Degree</option>
										<option value="Some Graduate, no degree">Some
											Graduate, no degree</option>
										<option value="Master's Degree">Master's Degree</option>
										<option value="Doctorate">Doctorate</option>
									</select> <span class="required marginTop8">(Required)</span>
								</div>

								<div class="rowEvenNewSpacing">
									<span class="lableText3">Field of Study:</span> <input
										type="text" name="JobTitle"
										class="job_seeker_password textBox350" />
								</div>

								<div class="rowEvenNewSpacing">
									<span class="lableText3"> Start Date:</span> <input type="text"
										name="healthCareSubSplty" class="job_seeker_Resume" />
									<div class="calender">
										<a href="#"><img src="../resources/images/tranBg.png"
											width="14" height="14" alt="Datepick"></a>
									</div>

								</div>
								<div class="row marginTop10">
									<span class="lableTextSelect marginTop10">End Date:</span> <input
										type="text" name="healthCareSubSplty2"
										class="job_seeker_Resume" />
									<div class="calender">
										<a href="#"><img src="../resources/images/tranBg.png"
											width="14" height="14" alt="Datepick"></a>
									</div>
									<span class="required"><input name="" type="checkbox"
										value=""> </span>
									<div class=" floatLeft marginLeft10 marginTop8">I haven't
										graduated yet.</div>

								</div>
								<div class="row MarginBottom10 ">
									<div class="lableText3 marginTop10">Degrees:</div>
									<div class="input_grp5 ">
										<textarea id="Body Text:" class="textareaBoxCResume" rows="3"
											cols="45" name="Body Text:"></textarea>
										<p>2000 characters remaining</p>

									</div>




								</div>

								<div class="row MarginBottom10 ">
									<div class="lableText3 marginTop10">Certifications:</div>
									<div class="input_grp5 ">
										<textarea id="Body Text:" class="textareaBoxCResume" rows="3"
											cols="45" name="Body Text:"></textarea>
										<p>2000 characters remaining</p>
										<p>
											<a href="" class="link_color1_emphasized">Save and add
												another institution</a>
										</p>
									</div>




								</div>
							</div>





						</div>
					</div>

					<div class="searchResultsItem">
						<ul class="searchResultsJobInfo closed">
							<li class="searchResultsColumn1">
								<div class="sectionHeader Padding0 Height28 LightGrayBG">
									<div class="floatLeft">
										<h2 class="width305 marginTop6 UpperCaseNone TextColor01">Certification</h2>
									</div>
									<div class="ESsection accord-open">
										<h2 class="UpperCaseNone FontSize12 marginTop1">
											<a href="#" class="edit">Edit this section</a>
										</h2>
									</div>

								</div>
							</li>

						</ul>
						<div class="searchResultsSubContent">

							<div class="job_seeker_login leftFormHolderResumepage marginTop0">
								<div class="rowEvenNewSpacing">
									<span class="lableText3">Certification Name:</span> <input
										type="text" name="mobileNo"
										class="job_seeker_password textBox350" /> <span
										class="required">(Required)</span>
								</div>
								<div class="rowEvenNewSpacing">
									<span class="lableText3">Certifying Authority:</span> <input
										type="text" name="JobTitle"
										class="job_seeker_password textBox350" />
								</div>

								<div class="rowEvenNewSpacing">
									<span class="lableText3"> Received:</span> <input type="text"
										name="healthCareSubSplty" class="job_seeker_Resume" />

								</div>
								<div class="row MarginBottom10 ">
									<div class="lableText3 marginTop10">Summary:</div>
									<div class="input_grp5 ">
										<textarea id="Body Text:" class="textareaBoxCResume" rows="3"
											cols="45" name="Body Text:"></textarea>
										<p>2000 characters remaining</p>
										<p>
											<a href="" class="link_color1_emphasized">Save and add
												another certification</a>
										</p>
									</div>




								</div>
							</div>





						</div>
					</div>

					<div class="searchResultsItem">
						<ul class="searchResultsJobInfo closed">
							<li class="searchResultsColumn1">
								<div class="sectionHeader Padding0 Height28 LightGrayBG">
									<div class="floatLeft">
										<h2 class="width305 marginTop6 UpperCaseNone TextColor01">Skills</h2>
									</div>
									<div class="ESsection accord-open">
										<h2 class="UpperCaseNone FontSize12 marginTop1">
											<a href="#" class="edit">Edit this section</a>
										</h2>
									</div>
								</div>
							</li>

						</ul>
						<div class="searchResultsSubContent">

							<div class="job_seeker_login leftFormHolderResumepage marginTop0">
								<div class="rowEvenNewSpacing">
									<span class="lableText3">Skill:</span> <input type="text"
										name="mobileNo" class="job_seeker_password textBox350" /> <span
										class="required"><a href="" class="btn_sm orange">Add</a></span>
									<div class="toolTip marginTop8 marginLeft5">
										<span class="classic">Show potential employers what
											your strengths are by entering up to 50 special skills in
											this section. Use brief keywords and phrases like "Triage"
											and "Emergency Care" so your list of skills can be browsed
											with ease.</span>
									</div>
								</div>
								<div class="row MarginBottom10 ">
									<div class="lableText3 marginTop10"></div>
									<div class="input_grp5 ">
										<textarea id="Body Text:" class="textareaBoxCResume" rows="3"
											cols="45" name="Body Text:"></textarea>

									</div>




								</div>
							</div>





						</div>
					</div>

					<div class="searchResultsItem">
						<ul class="searchResultsJobInfo closed">
							<li class="searchResultsColumn1">
								<div class="sectionHeader Padding0 Height28 LightGrayBG">
									<div class="floatLeft">
										<h2 class="width305 marginTop6 UpperCaseNone TextColor01">Languages</h2>
									</div>
									<div class="ESsection accord-open">
										<h2 class="UpperCaseNone FontSize12 marginTop1">
											<a href="#" class="edit">Edit this section</a>
										</h2>
									</div>
								</div>
							</li>

						</ul>
						<div class="searchResultsSubContent">

							<div class="job_seeker_login leftFormHolderResumepage marginTop0">
								<div class="row">
									<span class="lableText3 marginTop13 ">Language:</span> <select
										name="Country" id="Country" class="jb_input3 jb_input_width3">
										<option value="--- Select One ---">--- Language ---</option>
										<option value="Chinese (Mandarin)">Chinese (Mandarin)</option>
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
								<div class="row">
									<span class="lableText3 marginTop13 ">Proficiency Level:</span>
									<select name="Country" id="Country"
										class="jb_input3 jb_input_width3">
										<option value="--- Select One ---">--- Proficiency
											Level ---</option>
										<option value="Elementary proficiency">Elementary
											proficiency</option>
										<option value="Limited working proficiency">Limited
											working proficiency</option>
										<option value="Professional working proficiency">Professional
											working proficiency</option>
										<option value="Full professional proficiency">Full
											professional proficiency</option>
										<option value="Native or bilingual proficiency">Native
											or bilingual proficiency</option>
									</select>

								</div>
								<div class="row">
									<span class="lableText3 marginTop13 "></span>
									<p>
										<a href="" class="link_color1_emphasized">Save and add
											another language</a>
									</p>
								</div>

							</div>
						</div>
					</div>

					<div class="searchResultsItem">
						<ul class="searchResultsJobInfo closed">
							<li class="searchResultsColumn1">
								<div class="sectionHeader Padding0 Height28 LightGrayBG">
									<div class="floatLeft">
										<h2 class="width305 marginTop6 UpperCaseNone TextColor01">Awards</h2>
									</div>
									<div class="ESsection accord-open">
										<h2 class="UpperCaseNone FontSize12 marginTop1">
											<a href="#" class="edit">Edit this section</a>
										</h2>
									</div>
								</div>
							</li>

						</ul>
						<div class="searchResultsSubContent">

							<div class="job_seeker_login leftFormHolderResumepage marginTop0">
								<div class="row MarginBottom10 ">
									<div class="lableText3 marginTop10">
										If you've received any<br> professional awards,<br>
										please list them here<br> along with the years in<br>
										which you earned them:
									</div>
									<div class="input_grp5 ">

										<textarea id="Body Text:" class="textareaBoxCResume" rows="3"
											cols="45" name="Body Text:"></textarea>
										<p>2000 characters remaining</p>
									</div>




								</div>
							</div>





						</div>
					</div>
					<div class="searchResultsItem">
						<ul class="searchResultsJobInfo closed">
							<li class="searchResultsColumn1">
								<div class="sectionHeader Padding0 Height28 LightGrayBG">
									<div class="floatLeft">
										<h2 class="width305 marginTop6 UpperCaseNone TextColor01">Memberships</h2>
									</div>
									<div class="ESsection accord-open">
										<h2 class="UpperCaseNone FontSize12 marginTop1">
											<a href="#" class="edit">Edit this section</a>
										</h2>
									</div>
								</div>
							</li>

						</ul>
						<div class="searchResultsSubContent">

							<div class="job_seeker_login leftFormHolderResumepage marginTop0">
								<div class="row MarginBottom10 ">
									<div class="lableText3 marginTop10">If you're a member of
										any professionals associations, please list them here:</div>
									<div class="input_grp5 ">
										<textarea id="Body Text:" class="textareaBoxCResume" rows="3"
											cols="45" name="Body Text:"></textarea>
										<p>2000 characters remaining</p>

									</div>




								</div>
							</div>





						</div>
					</div>
					<div class="searchResultsItem">
						<ul class="searchResultsJobInfo closed">
							<li class="searchResultsColumn1">
								<div class="sectionHeader Padding0 Height28 LightGrayBG">
									<div class="floatLeft">
										<h2 class="width305 marginTop6 UpperCaseNone TextColor01">Other</h2>
									</div>
									<div class="ESsection accord-open">
										<h2 class="UpperCaseNone FontSize12 marginTop1">
											<a href="#" class="edit">Edit this section</a>
										</h2>
									</div>
								</div>
							</li>

						</ul>
						<div class="searchResultsSubContent">

							<div class="job_seeker_login leftFormHolderResumepage marginTop0">
								<div class="row MarginBottom10 ">
									<div class="lableText3 marginTop10">
										If you have more<br> information that you<br> would
										like to include,<br> please enter it here:
									</div>
									<div class="input_grp5 ">
										<textarea id="Body Text:" class="textareaBoxCResume" rows="3"
											cols="45" name="Body Text:"></textarea>
										<p>2000 characters remaining</p>
									</div>




								</div>
							</div>





						</div>
					</div>
					<div class="searchResultsItem MarginBottom10">
						<ul class="searchResultsJobInfo closed">
							<li class="searchResultsColumn1">
								<div class="sectionHeader Padding0 Height28 LightGrayBG">
									<div class="floatLeft">
										<h2 class="width305 marginTop6 UpperCaseNone TextColor01">References</h2>
									</div>
									<div class="ESsection accord-open">
										<h2 class="UpperCaseNone FontSize12 marginTop1">
											<a href="#" class="edit">Edit this section</a>
										</h2>
									</div>
								</div>
							</li>

						</ul>
						<div class="searchResultsSubContent">

							<div class="job_seeker_login leftFormHolderResumepage marginTop0">
								<div class="rowEvenNewSpacing">
									<span class="lableText3">Name:</span> <input type="text"
										name="mobileNo" class="job_seeker_password textBox350" />

								</div>
								<div class="rowEvenNewSpacing">
									<span class="lableText3">Job Title:</span> <input type="text"
										name="JobTitle" class="job_seeker_password textBox350" />
								</div>
								<div class="rowEvenNewSpacing">
									<span class="lableText3">Company Name:</span> <input
										type="text" name="healthCareSubSplty"
										class="job_seeker_password textBox350" />

								</div>
								<div class="rowEvenNewSpacing">
									<span class="lableText3">Phone Number:</span> <input
										type="text" name="healthCareSubSplty"
										class="job_seeker_password textBox350" />

								</div>
								<div class="rowEvenNewSpacing">
									<span class="lableText3">Email Address:</span> <input
										type="text" name="healthCareSubSplty"
										class="job_seeker_password textBox350" /> <span
										class="required"></span>
								</div>

								<div class="rowEvenNewSpacing">
									<span class="lableText3">Reference Type:</span><span
										class="required"><input name="Professional"
										type="radio" value=""> <label class="greyLabel">Professional</label></span>&nbsp;&nbsp;<span
										class="required"> <input name="Professional"
										type="radio" value="" checked> <label
										class="greyLabel">Personal</label></span>
								</div>
								<div class="rowEvenNewSpacing MarginBottom10">
									<span class="lableText3"></span> <a href=""
										class="link_color1_emphasized">Save and add another
										reference</a>
								</div>
							</div>





						</div>
					</div>

				</div>
				<div class="clearfix"></div>
				<br /> <span class="marginBottom10 FloatLeft"><a href="#"
					class="btn_sm orange">Save</a><a href="#" class="btn_sm orange">PREVIEW</a><a
					href="#" class="btn_sm orange">Cancel</a></span>
			</div>

			<!--Start:MidContant-->
			<div class="clearfix"></div>
			<!-- content_wrapper -->
			<div class="ad_wrapper">
				<span class="input_grp5 "> </span><img
					src="../resources/images/ads/banner_ad_fpo.png" />
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