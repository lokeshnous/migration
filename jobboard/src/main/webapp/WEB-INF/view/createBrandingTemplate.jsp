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
                	$('#templateName').focus();
	                	var existingLength = $('#companyOverview').val().length;
						$('#counterTextID').val(1000 - existingLength);

                        $("#brandingpreview").displaypopup("#brandingpreview","770","360");
     
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
                      
                		//Adding Videos
                		$('#videoAjaxCallId').live('click', function() {
                			$.ajax({
                				type : "POST",
                				url : "${pageContext.request.contextPath}/brandingTemplates/addVideos.html",
                				success : function(data) {
                					$('#videosSectionDivId').append(data);
                				},
                			});
                		});
                      
                		//Adding Additional Images
                		$('#imageAjaxCallId').live('click', function() {
                			$.ajax({
                				type : "POST",
                				url : "${pageContext.request.contextPath}/brandingTemplates/addImages.html",
                				success : function(data) {
                					$('#imagesSectionDivId').append(data);
                				},
                			});
                		});
                });
                
                
                
             // function start
             
           


			function preview() {          	 
            	 

				$.ajax({
					url : '../brandingTemplates/preview.html',
					data : ({
						userID : "userID"
					}),
					success : function(data) {
						$.each(data, function(key, val) {
							if (key == "NavigationPath") {
								
								$.nmManual(val + '.html');

							}
						});
					},
					error : function(data) {
					},
					complete : function(data) {
					}
				});
					//	$.nmManual(val + '.html');
			}

			// function end
		</script>

<link href="../resources/js/colorPicker.css" type='text/css'
	rel='stylesheet'>


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

<script type="text/javascript">
	function limitText(limitField, limitCount, limitNum) {

		if (limitField.value.length > limitNum) {
			limitField.value = limitField.value.substring(0, limitNum);
		} else {
			limitCount.value = limitNum - limitField.value.length;
		}

	}
</script>

</head>

<body class="job_board">
	<div class="ad_page_top">
		${adPageTop}
	</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">
			<div class="main">
				<jsp:include page="../templates/templates_header.jsp"></jsp:include>
				

				<div class="popupHeader Padding0  OrangeBG marginBottom5">
					<h2>CREATE JOB POSTING TEMPLATE</h2>
					<span class="floatRight marginRight10"><a href="<%=request.getContextPath()%>/employer/employerDashBoard.html"
						class="link_color3_emphasized FontSize12 FontWeight">Back to
							Dashboard</a></span>
				</div>
				<div class="row ">
					<form:form method="Post" action="createBrandingTemplate.html"	commandName="brandingTemplateForm" enctype="multipart/form-data">
						<div class="row marginTop15">
							<div class="lableTextCoverletter width150">Template Name:</div>
							<div class="input_grp5 ">
								<div class="floatLeft">
									<form:input path="templateName" id="templateName" class="jb_input2Coverletter FontSize15" />
								</div>
								<span class="required">(Required)</span>
								<div class="clearfix"></div>
								<div class="FormErrorDisplayText">
									<form:errors path="templateName" />
								</div>
							</div>
						</div>
						<div class="row marginTop15">
							<div class="lableTextCoverletter marginTop10 width150">
								Company Overview:</div>
							<div class="input_grp5 ">
								<form:textarea path="companyOverview" id="companyOverview"
									class="textareaBoxCResume Height256" rows="5" cols="45"
									name="Body Text:" onKeyDown="limitText(this.form.companyOverview,this.form.countdownCoverLetter,1000);"
									onKeyUp="limitText(this.form.companyOverview,this.form.countdownCoverLetter,1000);"/>
							</div>

								<span class="charactersRemaining"> <input readonly
									type="text" class="input2000_width" name="countdownCoverLetter"
									size="3" value="1000" id="counterTextID" tabindex="-1">characters
									remaining.
								</span>
						</div>
						<div class="row marginTop15">
							<div class="lableTextCoverletter width150">Company Logo:</div>
							<div class="input_grp5 ">
								<div class="floatLeft">
									<form:input path="logoFileData" name="textfield4" type="file"
										id="textfield4" size="20"
										class="job_seeker_login_email fileType cursor"  />
										
								</div>
								<div class="toolTip colorPkrAreaToolTip">
									<span class="classic">Select the company logo you want
										to feature on this job posting template. The file size limit
										is ${brandingTemplateForm.imageSizeLimit} KB at 72 dpi. Accepted file types include .jpg, .gif,
										.png and .tif.</span>
								</div>
								<span class="required">(Required)</span>
								<label class="MultimediaLabel">
									<c:if test="${brandingTemplateForm.chosenLogo != null}">
										You uploaded <Strong>${brandingTemplateForm.chosenLogo}</Strong> as your Logo, you can upload a different Logo.
									</c:if>
								</label>
								
								<div class="clearfix"></div>
								<div class="FormErrorDisplayText">
									<form:errors path="logoFileData" />
								</div>
							</div>
						</div>


						<div class="row marginTop15">
							<div class="lableTextCoverletter width150">Primary Color:</div>
							<div class="input_grp5 ">
								<div class="floatLeft">
									<form:input id="color_id" path="color" type="text" readonly="true"
										class="jb_input2Coverletter InputActiveText" />
								</div>
								
								<!-- value="HEX #" --> 
								
								<div class="colorPkrArea">
									<!-- <img src="images/Color.png" width="16" height="16" alt="Color"> -->
									<div class="floatRight"><input id="colorPkr" name="color1" type="text"   /></div>
								</div>
								<div class="toolTip colorPkrAreaToolTip">
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
										class="job_seeker_login_email fileType cursor" />
										
								</div>
								<div class="toolTip colorPkrAreaToolTip">
									<span class="classic">This is the large image that will
										appear at the top of your job posting template. The file size
										limit is ${brandingTemplateForm.imageSizeLimit} KB at 72 dpi. Accepted file types include .jpg,
										.gif, .png and .tif.</span>
								</div>
								<span class="required">(Required)</span>
								<label class="MultimediaLabel">
									<c:if test="${brandingTemplateForm.chosenMainImage != null}">
										You uploaded <Strong>${brandingTemplateForm.chosenMainImage}</Strong> as your Main Image, you can upload a different Main Image.
									</c:if>
								</label>
								<div class="clearfix"></div>
								<div class="FormErrorDisplayText">
									<form:errors path="mainImageFileData" />
								</div>
							</div>
						
						
				<c:if test="${!brandingTemplateForm.getIsSilverCustomer()}">
				<div id="multimediaSectionDivId">

						<div class="row marginTop15 borderBottomDotted">
							<div class="width467">
								<h3 class="floatLeft marginRight10">Multimedia Section</h3>
								<p>(Gold or Platinum upgrade package required)</p>
							</div>

						</div>
						
						<div class="row">
							<div id="testimonialsSectionDivId">
								<c:forEach items="${brandingTemplateForm.listTestimony}" var="testimonies" varStatus="status">
							
									<!-- <div class="rowEvenNewSpacing MarginBottom10"> -->
									<div class="rowEvenNewSpacing">
									<c:if test="${status.count == 1}">   
							            <span class="lableTextCoverletter marginTop10 width150">Testimonials:</span> 
							         </c:if>   
									<c:if test="${status.count != 1}">   
							            <span class="lableTextCoverletter marginTop10 width150"></span> 
							         </c:if>  									         
									<!-- <div class="floatLeft marginRight10"></div> -->
									<span class="floatLeft marginRight10">
									 
										<form:textarea path="listTestimony[${status.index}].testimony" class="textareaBoxCResumeTemplate" rows="5" cols="30" />
									</span>	
									</div>
								</c:forEach>
							</div>								
							
							<div id="testimony">
								<span class="required" ><a href="#nogo" id="testimonyAjaxCallId">Add Another Testimonial</a></span>
							</div>	
							
							<div class="toolTip colorPkrAreaToolTip">
								<span class="classicA">If you have any testimonials that
									you would like to include, enter them here. They will appear
									as text in the interactive gallery.</span>
							</div>
						</div>
							
						<div class="row">
							<div id="imagesSectionDivId">
								<c:forEach items="${brandingTemplateForm.listAddImages}" var="images" varStatus="status">
							
									<!-- <div class="rowEvenNewSpacing MarginBottom10"> -->
									<div class="rowEvenNewSpacing">
									<c:if test="${status.count == 1}">   
							            <span class="lableTextCoverletter">Additional Images:</span> 
							         </c:if>   
									<c:if test="${status.count != 1}">   
							            <span class="lableTextCoverletter"></span> 
							         </c:if>  									         
									<!-- <div class="floatLeft marginRight10"></div> -->
									<span class="floatLeft marginRight10">
									 
										<form:input path="listAddImages[${status.index}].addImageFileData" name="textfield4" type="file" id="textfield4" class="job_seeker_login_email fileType cursor" size="20" />
									</span>	
									<label class="MultimediaLabel">
									<c:if test="${brandingTemplateForm.listAddImages[status.index].chosenAddImage != null and errorMessage==null}">
										You uploaded <Strong>${brandingTemplateForm.listAddImages[status.index].chosenAddImage}</Strong> as your Additional Image, you can upload a different Additional Image.
									</c:if>
									</label>
									</div>
								</c:forEach>
							</div>	
								
							<div id="image">
								<span class="required"><a href="#nogo" id="imageAjaxCallId">Add Another Image</a></span>
							</div>	
								
							<div class="toolTip colorPkrAreaToolTip">
								<span class="classic">These images will appear as
										thumbnails in an interactive gallery prominently displayed on
										your job posting template. They will expand to full size upon
										being clicked. The file size limit is ${brandingTemplateForm.imageSizeLimit} KB at 72 dpi.
										Accepted file types include .jpg, .gif, .png and .tif.</span>
							</div>
						</div>
					
						<div class="row">
							<div id="videosSectionDivId">
								<c:forEach items="${brandingTemplateForm.listVideos}" var="videos" varStatus="status">
							
									<!-- <div class="rowEvenNewSpacing MarginBottom10"> -->
									<div class="rowEvenNewSpacing">
									<c:if test="${status.count == 1}">   
							            <span class="lableTextCoverletter width150">Videos:</span> 
							         </c:if>   
									<c:if test="${status.count != 1}">   
							            <span class="lableTextCoverletter width150"></span> 
							         </c:if>  									         
									<!-- <div class="floatLeft marginRight10"></div> -->
									<span class="floatLeft marginRight10">
									 
										<form:input path="listVideos[${status.index}].videoFileData" name="textfield4" type="file" id="textfield4" class="job_seeker_login_email fileType cursor" size="20" />
									</span>
									<label class="MultimediaLabel">
									<c:if test="${brandingTemplateForm.listVideos[status.index].chosenVideo != null and errorMessage==null}">
										You uploaded <Strong>${brandingTemplateForm.listVideos[status.index].chosenVideo}</Strong> as your Video, you can upload a different Video.
									</c:if>
									</label>	
									</div>
								</c:forEach>
							</div>	
								
							<div id="video">
								<span class="required"><a href="#nogo" id="videoAjaxCallId">Add Another	Video</a></span>
							</div>	
								
							<div class="toolTip colorPkrAreaToolTip">
								<span class="classic">These videos will appear as
									thumbnails in your interactive gallery. They will expand to
									full size and play upon being clicked. The file size limit is
									${brandingTemplateForm.videoSizeLimit} MB. Accepted file types include .wmv and .mp4.</span>
							</div>
						</div>
						
						<div class="row">
						<c:if test="${not empty errorMessage}">
				    	<div id="errmsg" style="color: red" align="left" >
			    		<c:out value="${errorMessage}"></c:out>
						</div>
						</c:if>
						</div>
				
					</div>		
					</c:if>		
							<div class="rowEvenNewSpacing marginTop20 paddingBottom10">
							<span class="floatLeft marginTop10">
							<%-- <a href="<%=request.getContextPath()%>/brandingTemplates/preview.html" id="brandingpreview"
								class="btn_sm white" target="">Preview</a>  --%>
								
								<!--  <input type="button" id="preview" value="Preview" class="white" onclick="preview();" name="Preview"/> --> 
								
								<!-- working -->
								<!-- <a onclick="preview()" id="preview" class="white" style=" cursor: default;">Preview</a> -->
								
								
								<input type="submit" id="brandingpreview" value="Preview" class="white cursor"  name="Preview"/>
								<input type="submit" value="Save" class="white cursor"  name="Save"/>
								<!-- <input type="submit" value="Cancel" class="btn_sm white"  name="Cancel"/> -->
								 <%-- <a href="<%=request.getContextPath()%>/brandingTemplates/createBrandingTemplate.html"
								class="btn_sm white">Save</a> --%> 
								
								<%-- <a href="<%=request.getContextPath()%>/brandingTemplates/cancelBrandTemp.html" class="btn_sm white">Cancel</a> --%>
								<input type="submit" value="Cancel" class="white cursor" name="Cancel"/>
								</span>
						</div>
						</form:form>
						</div>



						
						
				</div>
				
				
				
				<div class="clearfix"></div>
				<div class="ad_wrapper">
					${adPageBottom}
				</div>

			</div>


			<!-- main -->

		</div>
		<!-- end main_wrapper_inside -->


	<!-- end main_wrapper_outside -->
	<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
	<!-- footer_wrapper -->

</body>
</html>