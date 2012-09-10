<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="common/include.jsp" />
<title>ADVANCE Heathcare Jobs</title>

<!-- JAVASCRIPT FILES -->
<script type="text/javascript" src="../resources/js/slider.js"></script>
<link href="../resources/css/jquery-ui.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" language="javascript"
	src="/media/js/jquery.js"></script>
<script src="../resources/js/jquery.dataTables.nightly.js"></script>
<script src="../resources/js/searchResultsdatatable.js"></script>
<script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery(".megamenu").megamenu();

	});
</script>
<script type="text/javascript">
		    function cancelProcess(){
		    	window.location.href = '${pageContext.request.contextPath}/healthcarejobs/advanceweb.html';
		    }		
		</script>

</head>


<body class="job_board">
	<form:form method="post"
		action="../employerRegistration/saveEmployerProfile.html"
		commandName="empRegisterForm" enctype="multipart/form-data">
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
							class="job_seeker_login leftFormHolder" style="display: block">
							<h2 class="sectionSubHeader">To register as an employer,
								please fill out these fields.</h2>
							<c:if test="${not empty message}">
								<div class="validationMsg">
									${message}
								</div>
							</c:if>


							<c:forEach items="${empRegisterForm.listProfAttribForms}"
								var="profAttrib" varStatus="status">
								<c:if test="${profAttrib.strLabelName == 'First Name'}">
									<div class="row">
										<span class="lableText3">First Name:</span>
										<form:input
											path="listProfAttribForms[${status.index}].strLabelValue"
											class="job_seeker_password textBox350" />
										<span class="required">(Required)</span>
									</div>
								</c:if>

								<c:if test="${profAttrib.strLabelName == 'Middle Name'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">Middle Name:</span>
										<form:input
											path="listProfAttribForms[${status.index}].strLabelValue"
											type="text" name="lastname"
											class="job_seeker_password textBox350" />

									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Last Name'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">Last Name:</span>
										<form:input
											path="listProfAttribForms[${status.index}].strLabelValue"
											type="text" name="healthCareSubSplty2"
											class="job_seeker_password textBox350 
" />
										<span class="required">(Required)</span>
									</div>
								</c:if>
							</c:forEach>
							<div class="rowEvenNewSpacing">
								<span class="lableText3">Email Address:</span>
								<form:input path="emailId" type="text" name="mobileNo"
									class="job_seeker_password textBox350"  readonly="${empRegisterForm.bReadOnly}"/>
								<span class="required">(Required)</span>
							</div>
							<div class="row">
								<FONT class="validationMsgPadding" color="red"><form:errors
										path="emailId" /></FONT>
							</div>
							<div class="rowEvenNewSpacing">
								<span class="lableText3">Confirm Email Address:</span>
								<form:input path="confirmEmailId" type="text" name="JobTitle"
									class="job_seeker_password textBox350 " readonly="${empRegisterForm.bReadOnly}" />
								<span class="required">(Required)</span>
							</div>
							<div class="row">
								<FONT class="validationMsgPadding" color="red"><form:errors
										path="confirmEmailId" /></FONT>
							</div>
							<div class="rowEvenNewSpacing">
								<span class="lableText3">Password:</span>
								<form:password path="password" name="healthCareSubSplty"
									class="job_seeker_password textBox350" showPassword="true"  readonly="${empRegisterForm.bReadOnly}"/>
								<span class="required">(Required)</span>
								<div class="row marginTop5">
									<span class="lableText3"></span>(8-20 characters, including at
									least 1 number)
								</div>
							</div>
							<div class="row">
								<FONT class="validationMsgPadding" color="red"><form:errors
										path="password" /></FONT>
							</div>
							<div class="rowEvenNewSpacing">
								<span class="lableText3">Confirm Password:</span>
								<form:password path="confirmPassword" name="healthCareSubSplty"
									class="job_seeker_password textBox350 "  showPassword="true" readonly="${empRegisterForm.bReadOnly}"/>
								<span class="required">(Required)</span>
							</div>
							<div class="row">
								<FONT class="validationMsgPadding" color="red"><form:errors
										path="confirmPassword" /></FONT>
							</div>
							<c:forEach items="${empRegisterForm.listProfAttribForms}"
								var="profAttrib" varStatus="status">
								<c:if test="${profAttrib.strLabelName == 'Position Title'}">
									<div class="rowEvenNewSpacing marginTop0">
										<span class="lableText3">Position Title:</span>
										<form:input
											path="listProfAttribForms[${status.index}].strLabelValue"
											type="text" name="healthCareSubSplty"
											class="job_seeker_password textBox350 
" />
										<span class="required">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Company'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">Company:</span>
										<form:input
											path="listProfAttribForms[${status.index}].strLabelValue"
											type="text" name="healthCareSubSplty"
											class="job_seeker_password textBox350 
" />
										<span class="required">(Required)</span>
									</div>

								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Street Address'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">Street Address:</span>
										<form:input
											path="listProfAttribForms[${status.index}].strLabelValue"
											type="text" name="healthCareSubSplty"
											class="job_seeker_password textBox350 
" />
										<span class="required">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'City'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">City / Town:</span>
										<form:input
											path="listProfAttribForms[${status.index}].strLabelValue"
											type="text" name="healthCareSubSplty"
											class="job_seeker_password textBox350 
" />
										<span class="required">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'State / Province'}">
									<div class="row">
										<span class="lableTextSelect marginTop13 ">State /
											Province:</span>
										<form:select name="Country" id="Country"
											path="listProfAttribForms[${status.index}].strLabelValue"
											class="jb_input3 jb_input_width3">
											<form:option value="0" label="Select" />
											<form:options items="${profAttrib.dropdown}"
												itemValue="optionId" itemLabel="optionName" />
										</form:select>
										<span class="required marginTop8">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Zip Code'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">Zip:</span>
										<form:input
											path="listProfAttribForms[${status.index}].strLabelValue"
											type="text" name="healthCareSubSplty"
											class="job_seeker_password textBox350 
" maxlength="10" />
										<span class="required">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Country'}">
									<div class="row">
										<span class="lableTextSelect marginTop13 ">Country:</span>
										<form:select
											path="listProfAttribForms[${status.index}].strLabelValue"
											name="Country" id="Country" class="jb_input3 jb_input_width3">
											<form:option value="0" label="Select" />
											<form:options items="${profAttrib.dropdown}"
												itemValue="optionId" itemLabel="optionName" />
										</form:select>
										<span class="required marginTop8">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Primary Phone'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">Primary Phone:</span>
										<form:input
											path="listProfAttribForms[${status.index}].strLabelValue"
											type="text" name="healthCareSubSplty"
											class="job_seeker_password textBox350 
" />
										<span class="required">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Secondary Phone'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">Secondary Phone:</span>
										<form:input
											path="listProfAttribForms[${status.index}].strLabelValue"
											type="text" name="healthCareSubSplty"
											class="job_seeker_password textBox350 
" />
									</div>
								</c:if>
							</c:forEach>

							<%-- <div class="rowEvenNewSpacing"></div>
							<div class="row">
								<img src="images/SecureText.jpg" width="294" height="48" alt="">
							</div>
							<div class="rowEvenNewSpacing marginTop30">
								<span class="lableText3">Type the two words:</span>
								<form:input path="" type="text" name="healthCareSubSplty"
									class="job_seeker_password textBox350 
" />
								<span class="required">(Required)</span>
							</div>
 --%>
							<div class="clearfix"></div>

							<div class="clearfix"></div>
						</div>
						<div class="rowEvenNewSpacing marginTop25 paddingBottom30">
							<span> <!-- <a
							id="save" href="#" class="btn_sm orange">Save &amp; Continue</a> -->
								<input type="submit"
								value="Save & Continue" class="orange"> 
								 <c:if test="${empRegisterForm.bReadOnly == false}">
									<input type="button" value="Cancel" onclick="cancelProcess()" class="orange" name="Cancel" />
								</c:if> 
								<%-- <a href="<%=request.getContextPath()%>/healthcarejobs/advanceweb.html" class="btn_sm orange">Cancel</a> --%></span>
							<!-- <span class="floatLeft marginTop10">I'll set up my profile
								later. <a href="#">Continue</a> to the site now.
							</span> -->
						</div>
						<!-- Step 2 -->
							<%-- <input type="submit" style="margin-top: -4px;" value="Save & Continue" class="orange">
							<input type="button" value="Cancel" onclick="cancelProcess()"
									class="orange" name="Cancel" />
							
							<a href="<%=request.getContextPath()%>/healthcarejobs/advanceweb.html" class="btn_sm orange">Cancel</a></span> <span
							class="floatLeft marginTop10">I'll set up my profile
							later. <a href="#">Continue</a> to the site now. --%>
						</span>
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

		<!-- footer_wrapper -->
		<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
	</form:form>
</body>
</html>