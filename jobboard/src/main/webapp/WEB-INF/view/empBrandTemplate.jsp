<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Healthcare Jobs</title>
<jsp:include page="common/include.jsp" />
		<script type="text/javascript">

                jQuery(document).ready(

                function() {                    

                        $("#branding").displaypopup("#branding","770","360");

                });

                </script>

<link href="../resources/js/colorPicker.css" type='text/css'
	rel='stylesheet'>


<!--[if IE]>
	<link href="stylesheets/ie.css" rel="stylesheet" type="text/css">
	<![endif]-->

<script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>
<script type="text/javascript">
		    jQuery(document).ready(function(){
		    jQuery(".megamenu").megamenu();
		});
		</script>
<!-- <script type="text/javascript" src="javascripts/expandCollapse.js"></script> -->
<script type="text/javascript">
	jQuery(document).ready(function() {
		$('#colorPkr').colorPicker();
	});
</script>
<script type="text/javascript"
	src="../resources/js/jquery.colorPicker.js"></script>

</head>

<body class="job_board">
	<div class="ad_page_top">
		<img src="images/ads/banner_ad_fpo.png" />
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
							<span class="boldText">Welcome, (Employer Name)</span><br>
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

				<!-- ad_col_right -->
				<!-- content_wrapper -->
				<div class="popupHeader Padding0  OrangeBG marginBottom5">
					<h2>CREATE JOB POSTING TEMPLATE</h2>
					<span class="floatRight marginRight10"><a href="#"
						class="link_color3_emphasized FontSize12 FontWeight">Back to
							Dashboard</a></span>
				</div>
				<div class="row ">
					<form:form method="Post" action="saveEmpBrandTemp.html"	commandName="brandingTemplateForm" enctype="multipart/form-data">
						<div class="row marginTop15">
							<div class="lableTextCoverletter width150">Template Name:</div>
							<div class="input_grp5 ">
								<div class="floatLeft">
									<form:input path="templateName" id="templateName" class="jb_input2Coverletter FontSize15" />
								</div>
								<div>
									<span class="lableText3"></span> <FONT color="red"><form:errors
											path="templateName" /></FONT>
								</div>
							</div>
						</div>
						<div class="row marginTop15">
							<div class="lableTextCoverletter marginTop10 width150">
								Company Overview:</div>
							<div class="input_grp5 ">
								<form:textarea path="companyOverview" id="Body Text:"
									class="textareaBoxCResume Height256" rows="5" cols="45"
									name="Body Text:" />
							</div>
						</div>
						<div class="row marginTop15">
							<div class="lableTextCoverletter width150">Company Logo:</div>
							<div class="input_grp5 ">
								<div class="floatLeft">
									<form:input path="logoFileData" name="textfield4" type="file"
										id="textfield4" size="20"
										class="job_seeker_login_email fileType" />
								</div>
								<div class="toolTip marginTop6 marginLeft5">
									<span class="classic">Select the company logo you want
										to feature on this job posting template. The file size limit
										is XX KB at 72 dpi. Accepted file types include .jpg, .gif,
										.png and .tif.</span>
								</div>
								<div>
									<span class="lableText3"></span> <FONT color="red"><form:errors
											path="logoFileData" /></FONT>
								</div>
							</div>
						</div>


						<div class="row marginTop15">
							<div class="lableTextCoverletter width150">Primary Color:</div>
							<div class="input_grp5 ">
								<div class="floatLeft">
									<form:input id="color_id" path="color" type="text" readonly="true"
										class="jb_input2Coverletter InputActiveText" value="HEX #" />
								</div>
								
								<div class="required2">
									<!-- <img src="images/Color.png" width="16" height="16" alt="Color"> -->
									<div class="floatRight"><input id="colorPkr" name="color1" type="text"   /></div>
								</div>
								<div class="toolTip marginTop6 marginLeft5">
									<span class="classic">Select a color that complements
										your brand.</span>
								</div>
							</div>
						</div>
						<div class="row marginTop15">
							<div class="lableTextCoverletter width150">Main Image:</div>
							<div class="input_grp5 ">
								<div class="floatLeft">
									<form:input path="mainImageFileData" name="textfield4" type="file"
										id="textfield4" size="20"
										class="job_seeker_login_email fileType" />
								</div>
								<div class="toolTip marginTop6 marginLeft5">
									<span class="classic">This is the large image that will
										appear at the top of your job posting template. The file size
										limit is XX KB at 72 dpi. Accepted file types include .jpg,
										.gif, .png and .tif.</span>
								</div>
								<span class="lableText3"></span> <FONT color="red"><form:errors
											path="mainImageFileData" /></FONT>
								</div>
							</div>
						</div>



						<div class="row marginTop15 borderBottomDotted">
							<div class="width467">
								<h3 class="floatLeft marginRight10">Multimedia Section</h3>
								<p>(Gold or Platinum upgrade package required)</p>
							</div>

						</div>
						<div class="row marginTop15">
							<div class="lableTextCoverletter width150">Additional
								Images:</div>
							<div class="input_grp5 ">
								<div class="floatLeft">
									<input name="textfield4" type="file" id="textfield4" size="20"
										class="job_seeker_login_email fileType" />
								</div>
								<span class="required"><a href="#">Add Another Image</a></span>
								<div class="toolTip marginTop8 marginLeft5">
									<span class="classic">These images will appear as
										thumbnails in an interactive gallery prominently displayed on
										your job posting template. They will expand to full size upon
										being clicked. The file size limit is XX KB at 72 dpi.
										Accepted file types include .jpg, .gif, .png and .tif.</span>
								</div>
							</div>
						</div>
						<div class="row marginTop15">
							<div class="lableTextCoverletter width150">Videos:</div>
							<div class="input_grp5 ">
								<div class="floatLeft">
									<input name="textfield4" type="file" id="textfield4" size="20"
										class="job_seeker_login_email fileType" />
								</div>
								<span class="required"><a href="#">Add Another Video</a></span>
								<div class="toolTip marginTop8 marginLeft5">
									<span class="classic">These videos will appear as
										thumbnails in your interactive gallery. They will expand to
										full size and play upon being clicked. The file size limit is
										XX MB. Accepted file types include .mov and .mpg.</span>
								</div>
							</div>
						</div>
						<div class="row marginTop15">
							<div class="lableTextCoverletter marginTop10 width150">Testimonials:</div>
							<div class="input_grp5 AutoWidth">
								<div class="FloatLeft">
									<textarea id="Body Text:" class="textareaBoxCResume Height256"
										rows="5" cols="45" name="Body Text:" readonly="readonly"></textarea>
								</div>
								<span class="required"><a href="#">Add Another
										Testimonial</a></span>
								<div class="toolTip01 marginTop10 marginLeft5">
									<span class="classicA">If you have any testimonials that
										you would like to include, enter them here. They will appear
										as text in the interactive gallery.</span>
								</div>
							</div>
						</div>



						<div class="rowEvenNewSpacing marginTop20 paddingBottom10">
							<span class="floatLeft marginTop10">
							<%-- <a href="<%=request.getContextPath()%>/brandingTemplates/empBrandTemplatePreview.html" id="branding"
								class="btn_sm white" target="">Preview</a> --%> 
								<input type="submit" value="Preview" class="btn_sm white"  name="Preview" id="branding"/>
								<input type="submit" value="Save" class="btn_sm white"  name="Save"/>
								
								<!-- <a href="<%=request.getContextPath()%>/brandingTemplates/saveEmpBrandTemp.html"
								class="btn_sm white">Save</a> --> 
								<input type="button" value="Cancel" 
									class="btn_sm white" name="Cancel" />
								
								<!-- <a href="" class="btn_sm white">Cancel</a> --></span>
						</div>
						</form:form>
				</div>
				<div class="clearfix"></div>
				<div class="ad_wrapper">
					<img src="images/ads/banner_ad_fpo.png" />
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

</body>
</html>