<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="common/include.jsp" />
<title>ADVANCE Heathcare Jobs</title>

<!-- JAVASCRIPT FILES -->
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript"
	src="../resources/js/jquery.cycle.all.min.js"></script>
<script type="text/javascript" src="../resources/js/slider.js"></script>
<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>
<script type="text/javascript">
function validateNumber(event) {
    var keyval = window.event ? event.keyCode : event.which;

    if (event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 46
     || event.keyCode == 37 || event.keyCode == 39) {
        return true;
    }
    else if ( keyval < 48 || keyval > 57 ) {
        return false;
    }
    else return true;
};
</script>
<script type="text/javascript">
		    jQuery(document).ready(function(){
		    	$('[id^=zipCode]').keypress(validateNumber);
		    jQuery(".megamenu").megamenu();
			$('.focus').focus();
		});
		</script>
		<script type="text/javascript">
		    function cancelProcess(){
		    	window.location.href = '${pageContext.request.contextPath}/healthcarejobs/advanceweb.html';
		    }		
		</script>
</head>

<body class="job_board">
	<div class="ad_page_top">
		<img src="../resources/images/ads/banner_ad_fpo.png" />
	</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">
			<div class="main">
			<jsp:include page="../templates/templates_header.jsp"></jsp:include>
				<div class="row">
					<!-- Step 1 -->

					<div id="jobSeekerRegister1"
						class="job_seeker_login leftFormHolder"
						style="display: block">
						<h2 class="sectionSubHeader">Step 2: Your Information</h2>
						<h3 class="marginLeft10"> Contact Information </h3>
						<c:if test="${not empty message}">
							<div class="rowEvenNewSpacing">
							<span class="lableText3"></span>
							<span class="FormErrorDisplayText">${message}<br /></span>
								
							</div>
						</c:if>
						<form:form method="Post" action="saveJobSeekerProfile.html" commandName="registerForm" enctype="multipart/form-data">

							<c:forEach items="${registerForm.listProfAttribForms}" var="profAttrib" varStatus="status">							
		
								<c:if test="${profAttrib.strLabelName == 'First Name'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">First Name:</span>
										<form:input path="listProfAttribForms[${status.index}].strLabelValue"
											class="job_seeker_password textBox350 focus" />
										<span class="required">(Required)</span>
									</div>
								</c:if>
		
								<c:if test="${profAttrib.strLabelName == 'Middle Name'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">Middle Name:</span>
										<form:input path="listProfAttribForms[${status.index}].strLabelValue"
											class="job_seeker_password textBox350" />
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Last Name'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">Last Name:</span>
		
										<form:input path="listProfAttribForms[${status.index}].strLabelValue"
											class="job_seeker_password textBox350" />
										<span class="required">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Street Address'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">Street Address:</span>
										<form:input path="listProfAttribForms[${status.index}].strLabelValue"
											class="job_seeker_password textBox350" />
										<span class="required">(Required)</span>
		
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Street Address1'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3"></span>
										<form:input path="listProfAttribForms[${status.index}].strLabelValue"
											class="job_seeker_password textBox350" />
										<span class="required"></span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Zip Code'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">Zip Code:</span>
		
											<form:input
											path="listProfAttribForms[${status.index}].strLabelValue"
											type="text" name="zipCode" id="zipCode"
											class="job_seeker_password textBox350" maxlength="5" />
											
										<span class="required">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'City'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">City:</span>
										<form:input path="listProfAttribForms[${status.index}].strLabelValue" class="job_seeker_password textBox350" />
										<span class="required">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'State / Province'}">
									<div class="row">
										<span class="lableTextSelect ">State /
											Province:</span>
												<form:select path="listProfAttribForms[${status.index}].strLabelValue" class="jb_input3 jb_input_width3">
													<form:option value="0" label="Select" />
													<form:options items="${profAttrib.dropdown}" itemValue="optionId"
														itemLabel="optionName" />
												</form:select>
										<span class="requiredTopmargin">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Country'}">
									<div class="row">
										<span class="lableTextSelect ">Country:</span>
												<form:select path="listProfAttribForms[${status.index}].strLabelValue" class="jb_input3 jb_input_width3">
													<form:option value="0" label="Select" />
													<form:options items="${profAttrib.dropdown}" itemValue="optionId"
														itemLabel="optionName" />
												</form:select>
										<span class="requiredTopmargin">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Phone Number'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">Phone Number:</span>
										<form:input path="listProfAttribForms[${status.index}].strLabelValue"
											class="job_seeker_password textBox350" />
											<div class="toolTip marginTop5 marginLeft5">
											<span class="classic">Valid format for Phone number is (xxx)xxx-xxxx</span>
										</div>
										<span class="required"></span>
									</div>
								</c:if>
								

								<c:if test="${profAttrib.strLabelName == 'My Industry'}">
									<div class="rowH3Holder">
										<h3 class="marginLeft10">Employment information</h3>
									</div>
									<div class="rowEvenNewSpacing">
										<span class="lableText3">My Industry:</span>
										<form:input path="listProfAttribForms[${status.index}].strLabelValue"
											class="job_seeker_password textBox350" />
										<div class="toolTip marginTop5 marginLeft5">
											<span class="classic">Enter the industry you serve.
												Example: Healthcare</span>
										</div>
										<span class="required">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'My Profession'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">My Profession:</span>
		
										<form:input path="listProfAttribForms[${status.index}].strLabelValue"
											class="job_seeker_password textBox350" />
										<div class="toolTip marginTop5 marginLeft5">
											<span class="classic">Enter the general field in which
												you work. Example: Respiratory Therapy</span>
										</div>
										<span class="required">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'My Specialty'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">My Specialty:</span>
										<form:input path="listProfAttribForms[${status.index}].strLabelValue"
											class="job_seeker_password textBox350" />
										<div class="toolTip marginTop5 marginLeft5">
											<span class="classic">Enter the area in which you
												specialize. Example: Neonatal/Pediatrics</span>
										</div>
										<span class="required">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'My Job Title'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">My Job Title:</span>
										<form:input path="listProfAttribForms[${status.index}].strLabelValue"
											class="job_seeker_password textBox350" />
										<div class="toolTip marginTop5 marginLeft5">
											<span class="classic">Enter your official job title.
												Example: Registered Respiratory Therapist</span>
										</div>
										<span class="required">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'My Resume'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">My Resume:</span>
		
										<!-- <div class="floatLeft"><input name="textfield4" type="file" id="textfield4" size="20" class="job_seeker_login_email fileType" /></div> -->
										<div>
											<form:input path="uploadResume" id="image" type="file"
												class="job_seeker_login_email fileType" />
										</div>
										<div class="required">&nbsp;&nbsp;&nbsp;&nbsp;</div>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Im seeking'}">
									<div class="row">
										<span class="lableTextSelect ">I'm seeking:</span>
											<form:select path="listProfAttribForms[${status.index}].strLabelValue" class="jb_input3 jb_input_width3">
												<form:option value="0" label="Select" />
												<form:options items="${profAttrib.dropdown}" itemValue="optionId"
													itemLabel="optionName" />
											</form:select>
									</div>
								</c:if>

								<c:if test="${profAttrib.strLabelName == 'Ethnicity'}">
									<div class="rowH3Holder">
										<h3 class="marginLeft10">Equal Opportunity / Affirmative Action</h3>
									</div>
									<div class="row">
										<span class="lableTextSelect ">Ethnicity:</span>
											<form:select path="listProfAttribForms[${status.index}].strLabelValue" class="jb_input3 jb_input_width3">
												<form:option value="0" label="Select" />
												<form:options items="${profAttrib.dropdown}" itemValue="optionId" itemLabel="optionName" />
											</form:select>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Gender'}">
									<div class="row">
										<span class="lableTextSelect">Gender:</span>
											<form:select path="listProfAttribForms[${status.index}].strLabelValue" class="jb_input3 jb_input_width3">
												<form:option value="0" label="Select" />
												<form:options items="${profAttrib.dropdown}" itemValue="optionId"
													itemLabel="optionName" />
											</form:select>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Veteran Status'}">
									<div class="row">
										<span class="lableTextSelect ">Veteran
											Status:</span>
											<form:select path="listProfAttribForms[${status.index}].strLabelValue" class="jb_input3 jb_input_width3">
												<form:option value="0" label="Select" />
												<form:options items="${profAttrib.dropdown}" itemValue="optionId"
													itemLabel="optionName" />
											</form:select>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Subscriptions'}">
									<div class="rowH3Holder">
										<h3 class="marginLeft10">Subscriptions</h3>
									</div>
									<div class="row paddingBottom10 marginLeft10">I would like
										the following sent to me so I can stay up to date with the
										latest healthcare news and information:</div>
		
									<div class="centerAlign ">
										<ul>
											<c:forEach items="${profAttrib.dropdown}" var="dropdown" varStatus="index">
												<li>
													<div>
														<form:checkbox path="listProfAttribForms[${status.index}].subs"
															label="${dropdown.optionName}"
															value="${dropdown.optionId}"
															cssStyle="width:20px" />
													</div>
												</li>
											</c:forEach>
										</ul>
									</div>
								</c:if>				
						</c:forEach>			
							<div
								class="popUpButtonRow">
								<!-- <a href="" class="btn_sm white">Back</a> -->
								<input type="submit" value="Back" class="orange"
									name="Back" /> <input type="submit" value="Finish"
									class="orange" name="Finish" />
									<c:if test="${registerForm.bReadOnly == false}"> 
										<input type="button" value="Cancel" onclick="cancelProcess();"
										class="orange" name="Cancel"/>
									</c:if>
									<%-- <a
									href="<%=request.getContextPath()%>/healthcarejobs/advanceweb.html"
									class="btn_sm orange cancelacount">Cancel</a> --%>
								<!-- <input type="submit" value="Cancel" class="orange" name="Cancel"/>  -->
							</div>
							<div class="clearfix"></div>
						</form:form>
						<div class="clearfix"></div>
					</div>
					<!-- Step 2 -->

					<div class="ad_col_right">
						<img src="../resources/images/ads/300x250ad1.png" /> <img
							src="../resources/images/ads/300x250ad2.png" />

						<div class="follow_us">
							<h2>Follow Us</h2>
						<p>Stay connected to the latest jobs.</p>
						<a href="${followuplinkfacebook}" target="_blank">
							<div class="social facebook_link">Facebook</div>
						</a> <a href="${followuplinktwitter}" target="_blank">
							<div class="social twitter_link">Twitter</div>
						</a> <a href="${followuplinkyoutube}" target="_blank">
							<div class="social youTube_link">YouTube</div>
						</a> <a href="${followuplinklinkedin}" target="_blank">
							<div class="last social linkedIn_link">LinkedIn</div>
						</a>
						</div>
						<br class="clearfix" />

					</div>
				</div>
				<div class="clearfix"></div>
				<div class="ad_wrapper">
					<img src="../resources/images/ads/banner_ad_fpo.png" />
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