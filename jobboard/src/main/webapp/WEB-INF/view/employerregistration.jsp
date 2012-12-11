<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page language="java" import="java.util.*" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.advanceweb.afc.jb.common.util.MMJBCommonConstants"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="robots" content="noindex, follow"> 
<title>ADVANCE Heathcare Jobs</title>
<!-- JAVASCRIPT FILES -->
<jsp:include page="common/include.jsp" />
<script type="text/javascript" src="../resources/js/slider.js"></script>
<link href="../resources/css/jquery-ui.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" language="javascript"
	src="/media/js/jquery.js"></script>
	
	<script src="../resources/js/recaptcha_ajax.js"></script>
<script src="../resources/js/jquery.dataTables.nightly.js"></script>
<script src="../resources/js/searchResultsdatatable.js"></script>
<script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>
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
	jQuery(document).ready(function() {
		//$('[id^=zipCode]').keypress(validateNumber);
		$("#primaryPhoneId").inputmask("mask", {"mask": "(999) 999-9999"}); 
		$("#secondaryPhoneId").inputmask("mask", {"mask": "(999) 999-9999"}); 
		jQuery(".megamenu").megamenu();
		$('.focus').focus();
		//wrote to clearing the fields the city, zipcode, country fields whiel changing the state
		$("#stateDpId").change( function(){
				$('#cityAutoPopulation').val('');
				$('#zipCode').val('');
				$('#countryDpId').val('');
			});
		$("#zipCode").change(function(){
			$('#cityAutoPopulation').val("");
			$('#stateDpId').val("");
			$('#countryDpId').val("");
		});
		
		$("#cityAutoPopulation").change(function(){
			$('#zipCode').val("");
			$('#stateDpId').val("");
			$('#countryDpId').val("");
		});
		$("#countryDpId").change(function(){
			$('#zipCode').val("");
			$('#stateDpId').val("");
			$('#cityAutoPopulation').val("");
		});
		//Auto complete on selecting city
		$("#cityAutoPopulation").autocomplete({
			source: '${pageContext.request.contextPath}/employer/getCityList.html',
			width:500,
			select: function(event, ui) {
				$("#cityAutoPopulation").val(ui.item.value);				
				$.ajax({
				url: '${pageContext.request.contextPath}/employer/getState.html?city='+$("#cityAutoPopulation").val(),
				success : function(data) {
					$('#stateDpId').val(data);

					$.ajax({
					url: '${pageContext.request.contextPath}/employer/getPostalCode.html?city='+$("#cityAutoPopulation").val()+'&state='+$("#stateDpId").val(),
					success : function(data) {
						$('#zipCode').val(data);
					},
					});						
						$.ajax({
						url: '${pageContext.request.contextPath}/employer/getCountry.html?city='+$("#cityAutoPopulation").val()+'&state='+$("#stateDpId").val()+'&postalCode='+$("#zipCode").val(),
						success : function(country) {
							$('#countryDpId').val(country);
						},
					}); 						
				},
				});
			},
		}); 

		//Auto complete on selecting zipcode			
		$("#zipCode").autocomplete({
			source: '${pageContext.request.contextPath}/employer/getPostalCodeAutoPopulation.html',
			select: function(event, ui) {
				$("#zipCode").val(ui.item.value);	
				$('#cityAutoPopulation').val("");
				$('#stateDpId').val("");
				$.ajax({
					url: '${pageContext.request.contextPath}/employer/getLocations.html?zipCode='+$("#zipCode").val(),
					success : function(data) {
						$('#stateDpId').val(data.state);
						$('#countryDpId').val(data.country);
						$("#cityAutoPopulation").val(data.city);
					},error : function(data) {
					},
					complete : function(data) {
					}
				});		
			}
		});	
		
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
			${adPageTop }
		</div>
		<form:hidden path="socialSignUp"/>
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
							<div>
								<span class="lableText3"></span> <FONT color="red"> <c:if
										test="${not empty socialSignUpMsg}">
										<div id="errmsg" style="color: red" align="left">
											<c:out value="${socialSignUpMsg}"></c:out>
										</div>
									</c:if>
								</FONT>
							</div>
							<c:forEach items="${empRegisterForm.listProfAttribForms}"
								var="profAttrib" varStatus="status">
								<c:if test="${profAttrib.strLabelName == 'First Name'}">
									<div class="row">
										<span class="lableText3">First Name:</span>
										<form:input
											path="listProfAttribForms[${status.index}].strLabelValue"
											class="job_seeker_password textBox350 focus" />
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
                                        <div class="rowEvenNewSpacing">
										<span class="lableText3"></span>
										<div class="floatLeft">
										Health System:&nbsp;&nbsp; </div> <div class="floatLeft"> <form:checkbox path="helthSystem" />
										</div>
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
											type="text" name="healthCareSubSplty" id="cityAutoPopulation"
											class="job_seeker_password textBox350 
" />
										<span class="required">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'State / Province'}">
									<div class="row">
										<span class="lableTextSelect marginTop13 ">State /
											Province:</span>
										<form:select name="stateDpId" id="stateDpId"
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
											type="text" name="zipCode" id="zipCode"
											class="job_seeker_password textBox350" maxlength="5" />
										<span class="required">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Country'}">
									<div class="row">
										<span class="lableTextSelect marginTop13 ">Country:</span>
										<form:select
											path="listProfAttribForms[${status.index}].strLabelValue"
											name="countryDpId" id="countryDpId" class="jb_input3 jb_input_width3">
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
										<form:input id="primaryPhoneId"
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
										<form:input id="secondaryPhoneId"
											path="listProfAttribForms[${status.index}].strLabelValue"
											type="text" name="healthCareSubSplty"
											class="job_seeker_password textBox350 
" />
									</div>
								</c:if>
							</c:forEach>
							<div class="row marginTop15">
							<div class="width526"> 
								<%
										String pubKey = MMJBCommonConstants.PUBLIC_KEY;
										String privKey = MMJBCommonConstants.PRIVATE_KEY;
										/* ReCaptcha c = ReCaptchaFactory.newReCaptcha("ADD-YOUR-PUBLIC-KEY-HERE", "ADD-YOUR-PRIVATE-KEY-HERE", false); */
										ReCaptcha c = ReCaptchaFactory.newReCaptcha(pubKey, privKey,
												false);
										out.print(c.createRecaptchaHtml(null, null));
								%>
								</div>
								<span class="required">(Required)</span>
							</div>
								<div class="row">
								<span class="lableText3"></span> <FONT color="red"> <c:if
										test="${not empty errorMessage}">
										<div id="errmsg" style="color: red" align="left">
											<c:out value="${errorMessage}"></c:out>
										</div>
									</c:if>
								</FONT>
								</div>
							<div>
								<c:out value=""></c:out>
							</div>
							

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
						<div class="rowEvenNewSpacing marginTop25 marginBottom10">
							<span> <!-- <a
							id="save" href="#" class="btn_sm orange">Save &amp; Continue</a> -->
								<input type="submit"
								value="Save & Continue" class="orange cursor"> 
								 <c:if test="${empRegisterForm.bReadOnly == false}">
									<input type="button" value="Cancel" onclick="cancelProcess()" class="orange cursor" name="Cancel" />
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

		<!-- footer_wrapper -->
		<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
	</form:form>
</body>
</html>