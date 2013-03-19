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
<script type="text/javascript">
		    jQuery(document).ready(function(){
		    	$.nmFilters({
		    	    custom: {
		    	        afterShowCont: function(nm) {
		    	        	$('#ownerName').focus();
		    	        }
		    	    }
		    	});

		    	$("#accessPermissioPopUp1").displaypopup("#accessPermissioPopUp1","770","360");
		    	$("#saveNewOwner").click(function() {
		    			$("#jobOwnerErrorMsg").html("<span> </span>");
			    		var ownerName = $.trim($("#ownerName").val());
						var ownerEmail = $.trim($("#ownerEmail").val());
						var email_regex = new RegExp("^[!#\\$%&''\\*\\+\\-/=\\?\\^_\\{\\|}~0-9a-zA-Z][!#\\$%&''\\*\\+\\-/=\\?\\^_\\{\\|}~0-9a-zA-Z\\.]+@[\\-a-zA-Z0-9]+(\\.?[\\-a-zA-Z0-9]+)+$");           
						
						if (ownerName.length <= 0
							&& ownerEmail .length <= 0){
							$("#jobOwnerErrorMsg").html("<span>Please enter the required fields.</span>");
						
						}else if($('#ownerName').val().split(' ').length < 2) {		
							$("#jobOwnerErrorMsg").html("<span>Please enter the person's first name and last name separated by a space.</span>");
							
						}else if($('#ownerName').val().split(' ')[1] == "") {		
							$("#jobOwnerErrorMsg").html("<span>Please enter the person's first name and last name separated by a space.</span>");
							
						}else if(!email_regex.test(ownerEmail)){
							$("#jobOwnerErrorMsg").html("<span>Please enter the correct email address.</span>");	
						
						}else {		
							$("#jobOwnerErrorMsg").html("<span>Processing...</span>");
							$.ajax({url : "${pageContext.request.contextPath}/employer/saveNewJobOwner.html",
				    			data:$('#addJobOwnerForm').serialize(),
								type: "POST",
								success : function(data) {
									if(data.failure!=null){
										$("#jobOwnerErrorMsg").html("<span>"+data.failure+"</span>");	
									}else{
										$("#jobOwnerErrorMsg").html("<span>"+data.success+"</span>");
										alert("New job owner added successfully!")
									  	$("#manageAccPerm").click();
									}
								}					
									
								
							});
						}				

						});
		    jQuery(".megamenu").megamenu();
		});
		</script>
</head>

<body class="job_board">
<form:form  commandName="manageAccessPermissionForm" id="addJobOwnerForm">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>ADD NEW JOB OWNER</h2>
			 <img src="../resources/images/Close.png" title="Close" width="19" height="19" class="nyroModalClose cursor" alt="close">
		</div>

		<div class="popUpContainerWrapper">
		<span class="lableText3"></span>
		<div id="jobOwnerErrorMsg" class="FormErrorDisplayText paddingleft0" ></div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Job Owner Name:</span> <form:input path="ownerName"
						name="EmailAddress" class="job_seeker_email width300" />
					<div class="toolTip colorPkrAreaToolTip">
						<span class="classic">Enter the person's first name and last name separated by a space.</span>
					</div>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Job Owner Email Address:</span> 
					<form:input path="ownerEmail" name="EmailAddress" class="job_seeker_email width300" />
				    <form:errors path="ownerEmail" />
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText4"> </span>
					<span>
						<div class="required"><label ><input type="radio" checked="checked" value="5" name="fullAccess" id="fullAccess1"><label class="greyLabel" for="fullAccess1">Full Access</label> </label></div>
						<div class="required"><label><input type="radio" value="6" name="fullAccess" id="fullAccess1"><label class="greyLabel" for="fullAccess1">Post / Edit Only</label> </label></div>
					</span>
					<%-- <div class="floatLeft marginTop5">
						<label> <form:radiobutton name="RadioGroup10" path="fullAccess" value="6" label="Post / Edit Only"/> </label>
					</div> --%>
					</div> 

					<div class="rowEvenNewSpacing marginTop10 paddingBottom10">
						<span class="floatLeft marginTop10">
							<a href="#" id="saveNewOwner" class="btn_sm orange cursor">SAVE</a> 
							<c:choose>
									<c:when test="${manageAccessPermissionForm.setAlertPage != null}">
										<a href="<%=request.getContextPath()%>/alerts/employer/setAlerts.html" id="accessPermissioPopUp1" class="btn_sm orange">Cancel</a>
									</c:when>
									<c:otherwise>
										<a href="<%=request.getContextPath()%>/employer/manageAccessPermission.html" id="accessPermissioPopUp1" class="btn_sm orange">Cancel</a>
									</c:otherwise>
							</c:choose>
						</span>
							<a hidden="hidden" class="nyroModal" href="<%=request.getContextPath()%>/employer/manageAccessPermission.html" id="manageAccPerm"></a>
					</div>
					<div class="clearfix"></div>
			</div>
		<div class="clearfix"></div>
	</div>
</form:form>
</body>
</html>