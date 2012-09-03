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
<!-- <script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css">
 -->
		<script type="text/javascript">

                jQuery(document).ready(

                function() {                    

                        $("#branding").displaypopup("#branding","770","360");
                        
                      //Adding Testimonies
                		$('#testimonyAjaxCallId').live('click', function() {
                			$.ajax({
                				type : "POST",
                				url : "${pageContext.request.contextPath}/brandingTemplates/addTestimonies.html",
                				success : function(data) {
                					$('#testimonialsSectionDivId').append(data);
                				},
                			});
                		});

                });
                
             // function start
                function createBrandingTemplate() {
            		$.ajax(
            			
            						$.nmManual(val + '.html')

            				
            			
            		);
            	}
                
                // function end

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
				<jsp:include page="../templates/templates_header.jsp"></jsp:include>
				<div class="popupHeader Padding0  OrangeBG marginBottom5">
					<h2>CREATE JOB POSTING TEMPLATE</h2>
					<span class="floatRight marginRight10"><a href="#"
						class="link_color3_emphasized FontSize12 FontWeight">Back to
							Dashboard</a></span>
				</div>
				<form:form method="Post" action="updateBrandingTemplate.html" commandName="brandingTemplateForm" enctype="multipart/form-data">
				<form:hidden path="templateId"/>
				<form:hidden path="facilityId"/>
				<div class="row ">
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
									<form:input path="" name="textfield4" type="file" id="textfield4" size="20"
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
								<%-- <span class="lableText3"></span> <FONT color="red"><form:errors
											path="addImageFileData" /></FONT> --%>
							</div>
						</div>
						<div class="row marginTop15">
							<div class="lableTextCoverletter width150">Videos:</div>
							<div class="input_grp5 ">
								<div class="floatLeft">
									<form:input path="" name="textfield4" type="file" id="textfield4" size="20"
										class="job_seeker_login_email fileType" />
								</div>
								<span class="required"><a href="#">Add Another Video</a></span>
								<div class="toolTip marginTop8 marginLeft5">
									<span class="classic">These videos will appear as
										thumbnails in your interactive gallery. They will expand to
										full size and play upon being clicked. The file size limit is
										XX MB. Accepted file types include .mov and .mpg.</span>
								</div>
								<%-- <span class="lableText3"></span> <FONT color="red"><form:errors
											path="videoFileData" /></FONT> --%>
							</div>
						</div>
						<div class="row marginTop15">
							<div class="lableTextCoverletter marginTop10 width150">Testimonials:</div>
							<div class="input_grp5 AutoWidth">
								<div class="FloatLeft">
									<form:textarea path="" id="Body Text:" class="textareaBoxCResume Height256"
										rows="5" cols="45" name="Body Text:" />
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
								
								<!-- <a href="" class="btn_sm">Cancel</a> -->
								<!-- <a class="nyroModalClose btn_sm white" href="#">Cancel</a> -->
								<a href="<%=request.getContextPath()%>/brandingTemplates/cancelBrandTemp.html" class="btn_sm white" id="branding">Cancel</a>
								</span>
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
<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
</body>
</html>