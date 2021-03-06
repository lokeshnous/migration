<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Healthcare Jobs</title>
<link href="../resources/js/colorPicker.css" type='text/css'
	rel='stylesheet'>
<jsp:include page="common/include.jsp" />
<script src="../resources/js/jquery.validate.js"></script>

<script src="../resources/js/jquery.dataTables.nightly.js"></script>
<!-- <script src="../resources/js/searchResultsdatatable.js"></script> -->


<script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		$('#save').click(function() {
			$("#promoMediaErrMsg").text('');
			$("#logoErrMsg").text('');
			$("#errMsg").text('');
	        var hasError = true;
			 if($('#textfield4').val()!=''){
					var ext = $('#textfield4').val().split('.').pop().toLowerCase();
					if($.inArray(ext, ['mp4','wmv','gif']) == -1) {
					hasError = false;
		            $("#promoMediaErrMsg").text('Please select an appropriate video file');
				}
			}
			 if(parseInt(sizeInKB) > 10800){
				 $("#promoMediaErrMsg").text('File size should not exceed more than 10.8MB.');
				 hasError = false;
			 }
			if($('#textfield5').val()!=''){
					var ext = $('#textfield5').val().split('.').pop().toLowerCase();
					if($.inArray(ext, ['gif','png','jpg','tif']) == -1) {
		            $("#logoErrMsg").text('Please select an appropriate logo');
		            hasError = false;
				}
			} 
	        var emailReg = new RegExp("^[!#\\$%&''\\*\\+\\-/=\\?\\^_\\{\\|}~0-9a-zA-Z][!#\\$%&''\\*\\+\\-/=\\?\\^_\\{\\|}~0-9a-zA-Z\\.]+@[\\-a-zA-Z0-9]+(\\.?[\\-a-zA-Z0-9]+)+$");
	        var emailaddressVal = $("#companyEmaiAddress").val();
	      /*   if(emailaddressVal == '') {
	            hasError = false;
	            $("#errMsg").text('Please enter the E-Mail Address');
	        }*/
	         if(!emailReg.test(emailaddressVal) && emailaddressVal != "" ) {
	            $("#errMsg").text('Enter a valid email address.');
	            hasError = false;
	        } 
	         if($('#companyWebsiteErrMsg').text() != ''){
		        	hasError = false;
		        }
	        return hasError; 
	    });
		
		
		$('#colorPkr').colorPicker();
		var error = $('#FormErrorDisplayText').text();
		$('#FormErrorDisplayText').text("");
		if (error.length > 0) {
			alert(error);
			cancelProcess();
		}
		var sizeInKB = 0;
	$('#textfield4').bind('change', function() {
	  sizeInKB = Math.round(parseInt(this.files[0].size)/1024);
	});
	});
</script>

<script type="text/javascript"
	src="../resources/js/jquery.colorPicker.js"></script>
<script type="text/javascript">
	function cancelProcess() {
		window.location.href = '${pageContext.request.contextPath}/employer/employerDashBoard.html';
	}
	
	function appendURL() {
		var str = $("#companyWebsiteID").val();
		if (str.indexOf('www') == 0 && str.indexOf('.') == 3) {
			str = 'http://' + str;
			$("#companyWebsiteID").val(str);
			$("#companyWebsiteErrMsg").text('');
		} else {
			if ($("#companyWebsiteID").val() != '') {
				$("#companyWebsiteErrMsg").text(' Please enter the valid url');
			}else{
				$("#companyWebsiteErrMsg").text('');
			}
		}
	}
</script>

</head>

<body class="job_board">
	<form:form method="Post" action="saveemployerprofile.html"
		commandName="employerProfileManagementForm" id="myForm"
		enctype="multipart/form-data">
		<div class="ad_page_top">
			${adPageTop }
		</div>

		<div class="main_wrapper_outside">
			<div class="main_wrapper_inside">


				<div class="main">
					<jsp:include page="../templates/templates_header.jsp"></jsp:include>
					<div class="clearfix"></div>
					<!--nav-->
					<div id="FormErrorDisplayText" class="FormErrorDisplayText"
						align="center">${error}</div>
					<!-- ad_col_right -->
					<!-- content_wrapper -->
					<div class="popupHeader Padding0  OrangeBG marginBottom5">
						<h2>MANAGE FEATURED EMPLOYER PROFILE</h2>
						<span class="floatRight marginRight10"><a
							href="<%=request.getContextPath()%>/employer/employerDashBoard.html"
							class="link_color3_emphasized FontSize12 FontWeight">Back to
								Dashboard</a></span>
					</div>
					<div class="row ">
						<div class="row marginTop15">
							<div class="lableTextCoverletterSize">Company Name:</div>
							<div class="input_grp5 ">
								<div class="floatLeft">
									<form:input path="companyName" name="Exclude"
										class="jb_input2Coverletter width300" />
								</div>
							</div>
						</div>

						<div class="row marginTop15">
							<div class="lableTextCoverletterSize marginTop10">Company
								Overview:</div>
							<div class="input_grp5 ">
								<form:textarea path="companyOverview" id="Body Text:"
									class="textareaBoxCResume Height256" rows="5" cols="45"
									name="Body Text:" />

							</div>

						</div>
						<div class="row marginTop15">
							<div class="lableTextCoverletterSize">Company Website:</div>
							<div class="input_grp5 ">

								<div class="floatLeft">
									<form:input path="companyWebsite" name="Exclude"
										class="jb_input2Coverletter width300" id="companyWebsiteID" onblur="appendURL()" />
								</div>
								<div id="companyWebsiteErrMsg" class="FormErrorDisplayText paddingleft0" ></div>
							</div>


						</div>
						<div class="row marginTop15">
							<div class="lableTextCoverletterSize">Company Email
								Address:</div>
							<div class="input_grp5 ">
								<div class="floatLeft">
									<form:input path="companyEmail" name="Exclude" id="companyEmaiAddress"
										class="jb_input2Coverletter width300" />

								</div>
								<!-- <span class="required">(Required)</span> -->
								<div class="FormErrorDisplayText">
									<span id="errMsg" ></span>
								</div>
							</div>

						</div>
						<div class="row marginTop15">
							<span class="lableTextCoverletterSize"> Primary Color:</span>
							<form:input id="color_id" path="primaryColor" readonly="true"
								class="jb_input2Coverletter InputActiveText" />
							<span>
								<div class="colorPkrArea">
									<input id="colorPkr" name="color1" type="text" />
								</div>
								<div class="toolTip colorPkrAreaToolTip">
									<span class="classic">Select a color that complements
										your brand. </span>

								</div>
							</span>
						</div>

					</div>
					<div class="row marginTop15">
						<div class="lableTextCoverletterSize marginTop10">Company
							News:</div>
						<div class="input_grp5">
							<div class="FloatLeft">
								<form:textarea path="companyNews" id="Body Text"
									class="textareaBoxCResume Height256" rows="5" cols="45"
									name="Body Text:" />
							</div>
							<div class="toolTip01 colorPkrAreaToolTip">
								<span class="classic">Describe what's going on at your
									company here. Attract job-seekers by mentioning awards you've
									recently won, expansions currently in the works or upcoming
									events you have planned.</span>
							</div>

						</div>
					</div>
					<div class="row marginTop15">
						<div class="lableTextCoverletterSize">Promotional Media:</div>
						<div class="input_grp5 ">
							<div class="floatLeft">

								<form:input path="positionalMedia" name="textfield4" type="file"
									id="textfield4" size="20"
									class="job_seeker_login_email fileType" />

							</div>
							<div class="toolTip colorPkrAreaToolTip">&nbsp;
								<span class="classic">Upload a video, slideshow or
									animated .gif that shines the spotlight on your facility or
									health system.</span>
							</div>
							<div class="FormErrorDisplayText">
								<span id="promoMediaErrMsg"></span>
							</div>
							<label class="MultimediaLabel">
							<c:if test="${mediaName != null}">
								You uploaded <Strong>${mediaName}</Strong> as your Video, you can upload a different Video.
							</c:if>
							</label>
						</div>
					</div>
					<div class="row marginTop15">
						<div class="lableTextCoverletterSize">Logo:</div>
						<div class="input_grp5 ">
							<div class="floatLeft">
								<form:input path="logoUrl" name="textfield5" type="file"
									id="textfield5" size="20" 
									class="job_seeker_login_email fileType" />
							</div>
							<div class="toolTip colorPkrAreaToolTip">&nbsp;
								<span class="classic">Upload a Logo. Accepted file types include .gif , .png, .jpg , .tif
									</span>
							</div>
							<div class="colorPkrArea">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
							</div>
							<div class="FormErrorDisplayText">
								<span id="logoErrMsg"></span>
							</div>
							<label class="MultimediaLabel">
							<c:if test="${logoName != null}">
								You uploaded <Strong>${logoName}</Strong> as your Logo, you can upload a different Logo.
							</c:if>
							</label>
						</div>
					</div>
					<div class="row paddingBottom10">
						<div class="rowEvenNewSpacing marginTop20 paddingBottom10">
							<span class="floatLeft marginTop10"> <input type="submit" id="save"
								value="Save" name="Save" class="orange cursor" /> <input type="button"
								value="Cancel" onclick="cancelProcess()" class="orange cursor"
								name="Cancel" /> <%-- <a href="<%=request.getContextPath()%>/employer/employerDashBoard.html"
						class="btn_sm orange">Cancel</a> --%></span>
						</div>

					</div>


				</div>

				<div class="clearfix"></div>
				<div class="ad_wrapper">
					${adPageBottom }
				</div>

			</div>
			<!-- main -->

		</div>
		<!-- end main_wrapper_inside -->
		</div>
		<!-- end main_wrapper_outside -->
		<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
	</form:form>
</body>
</html>