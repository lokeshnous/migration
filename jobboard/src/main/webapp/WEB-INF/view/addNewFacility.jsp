<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>

<jsp:include page="common/include.jsp" />

<!-- JAVASCRIPT FILES -->
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="javascripts/jquery.cycle.all.min.js"></script>
<script type="text/javascript" src="javascripts/slider.js"></script>
<script type="text/javascript" src="javascripts/jquery.megamenu.js"></script>
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
		    	$("#cancelAddFacility").displaypopup("#cancelAddFacility","770","360");
		    	$("#saveNewFacility").click(function() {
	    			$("#facilityErrorMsg").html("<span> </span>");
		    		var facilityName = $.trim($("#facilityName").val());
					var facilityStreet = $.trim($("#facilityStreet").val());
					var facilityCity = $.trim($("#facilityCity").val());
					var facilityState = $.trim($("#facilityState").text());
					var zipCode = $.trim($("#zipcode").val());
					var facilityCountry = $.trim($("#facilityCountry").val());
					var phoneNumber = $.trim($("#phoneNumber").val());	
					
					 if (facilityName.length <= 0 || facilityStreet.length <= 0 || facilityCity.length <= 0
							 ){
						$("#facilityErrorMsg").html("<span> Please enter the required fields.</span>");
					
					}else{	 
						$.ajax({url : "${pageContext.request.contextPath}/facility/saveNewFacility.html",
			    			data:$('#addFacility').serialize(),
							type: "POST",
							success : function(data) {
								if(data.failure!=null){
									$("#facilityErrorMsg").html("<span>"+data.failure+"</span>");	
								}else{
									$("#facilityErrorMsg").html("<span>"+data.success+"</span>");	
								  $("#manageFacility").click();
								}
							}					
								
							
						});
					}				

					});
		    	$('[id^=zipCodeITId]').keypress(validateNumber);
		    jQuery(".megamenu").megamenu();
		});
		</script>
</head>

<body class="job_board">
	<form:form commandName="manageFacilityForm" id="addFacility">
		<form:hidden path="facilityId" />
		<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
			style="display: block">
			<div class="popupHeader">
			<c:choose>
			<c:when test="${manageFacilityForm.facilityId>0}"><h2>Update FACILITY</h2></c:when>
			<c:otherwise><h2>ADD FACILITY</h2></c:otherwise>
			</c:choose>
			 <img src="../resources/images/Close.png" title="Close" width="19" height="19" class="nyroModalClose" alt="close">
			</div>
			<div class="popUpContainerWrapper">

				<div class="rowEvenNewSpacing">
					<span class="lableText4"><h3 class=" color1">FACILITY
							DETAILS</h3></span>
				</div>

				<div class="popUpContainerWrapper">
					<div id="facilityErrorMsg" class="validationMsg"></div>
					<div class="rowEvenNewSpacing">
						<span class="lableText4">Facility Name:</span>
						<form:input path="facilityName"
							class="job_seeker_password textBox2" />
						<span class="required">(Required)</span>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText4">Street Address:</span>
						<form:input path="facilityStreet"
							class="job_seeker_password textBox2" />
						<span class="required">(Required)</span>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText4">City:</span>
						<form:input path="facilityCity"
							class="job_seeker_password textBox2" />
						<span class="required">(Required)</span>
					</div>

					<div class="rowEvenNewSpacing">
						<span class="lableText4">State:</span>
						<form:select path="facilityState"
							class="jb_input3 jb_input_width3" id="stateDpId">
							<form:option value="" label="Select" />
							<form:options items="${stateList}" itemValue="stateValue"
								itemLabel="stateValue" />
						</form:select>
						<span class="required">(Required)</span>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText4">Zip Code:</span>
						<form:input  path="zipCode" maxlength="5"
							class="job_seeker_password" id="zipCodeITId" />
						<span class="required">(Required)</span>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText4">Country:</span>
						<form:select path="facilityCountry"
							class="jb_input3 jb_input_width3" id="countryDpId">
							<form:option value="" label="Select" />
							<form:options items="${countryList}" itemValue="countryValue"
								itemLabel="countryValue" />
						</form:select>
						<span class="required">(Required)</span>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText4">Phone:</span>
						<form:input path="phoneNumber"
							class="job_seeker_password textBox2" />
						<span class="required">(Required)</span>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText4">Branding Template:</span>
						<form:select path="templateId" class="jb_input3 jb_input_width3">
							<form:option value="0" label="Select" />
							<form:options items="${templateList}" itemValue="optionId"
								itemLabel="optionName" />
						</form:select>
					</div>
					<div class="row marginTop20 paddingBottom10">
						<a href="#" id="saveNewFacility" class="btn_sm orange">Save</a> <a
							href="<%=request.getContextPath()%>/facility/updateFacilityDetail.html"
							id="cancelAddFacility" class="btn_sm orange">Cancel</a>
					</div>

					<a hidden="hidden" class="nyroModal"
						href="<%=request.getContextPath()%>/facility/updateFacilityDetail.html"
						id="manageFacility"></a>
					<div class="clearfix"></div>

				</div>
			</div>
			<div class="clearfix"></div>
	</form:form>
</body>
</html>