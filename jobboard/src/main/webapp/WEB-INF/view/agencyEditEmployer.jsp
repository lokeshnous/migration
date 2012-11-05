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
</head>

<body class="job_board">
	<form:form method="POST" id="editEmployer"
		action="../agency/saveEmployerDetails.html"
		commandName="empRegisterForm" enctype="multipart/form-data">
		<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
			style="display: block">
			<div class="popupHeader">
				<h2>View EMPLOYER</h2>
				<a href="#"><img src="../resources/images/Close.png" width="19"
					height="19" alt="" class="nyroModalClose"></a>
			</div>
			<form:hidden path="facilityId" id="facilityId"/>
			<div class="popUpContainerWrapper">
				<div class="rowEvenNewSpacing marginTop10 marginLeft15">
					<h3>EMPLOYER DETAILS</h3>
				</div>
				<div id="errmsg" class="validationMsg">
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Employer Name:</span>
					<form:input readonly="true" path="firstName" class="job_seeker_email"
						id="emplyrNameAutoComplte" />
					<span class="required">(Required)</span>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Street Address:</span>
					<form:input readonly="true" path="street" id="street" class="job_seeker_email" />
					<span class="required">(Required)</span>
				</div>

				<div class="rowEvenNewSpacing">
					<span class="lableText3">City:</span>
					<form:input readonly="true" path="city" id="city" class="job_seeker_email" />
					<span class="required">(Required)</span>
				</div>
				<c:forEach items="${empRegisterForm.listProfAttribForms}"
					var="profAttrib" varStatus="status">
					<c:if test="${profAttrib.strLabelName =='State / Province'}">
						<div class="row">
							<span class="lableTextSelect marginTop13">State:</span>
							<form:select disabled="true" name="state" id="state"
								path="state"
								class="jb_input3 jb_input_width3">
								<form:option value="0" label="Select" />
								<form:options items="${profAttrib.dropdown}"
									itemValue="optionId" itemLabel="optionName" />
							</form:select>
							<span class="required marginTop8">(Required)</span>
						</div>
					</c:if>
					<c:if test="${profAttrib.strLabelName == 'Country'}">
						<div class="row">
							<span class="lableTextSelect marginTop13 ">Country:</span>
							<form:select disabled="true"
								path="country"
								name="Country" id="Country" class="jb_input3 jb_input_width3">
								<form:option value="0" label="Select" />
								<form:options items="${profAttrib.dropdown}"
									itemValue="optionId" itemLabel="optionName"/>
							</form:select>
							<span class="required marginTop8">(Required)</span>
						</div>
					</c:if>
				</c:forEach>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Zip Code:</span>
					<form:input readonly="true" id="zipCode" path="zipCode" class="job_seeker_email" />
					<span class="required">(Required)</span>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Phone:</span>
					<form:input readonly="true" path="primaryPhone" id="primaryPhone"
						class="job_seeker_email" />
					<span class="required">(Required)</span>
				</div>
				<div class="rowEvenNewSpacing marginTop10 paddingBottom10">
					<span class="floatLeft marginTop10"><!-- <input type="button" id="save"
						style="margin-top: -4px;" value="Save" class="btn_sm orange"> -->
						<a class="nyroModal btn_sm orange" href="<%=request.getContextPath()%>/agency/getManageFacilityPopup.html">Cancel</a></span>
				</div>
				<div class="clearfix"></div>

			</div>
			<div class="clearfix"></div>
		</div>
	</form:form>
</body>
</html>