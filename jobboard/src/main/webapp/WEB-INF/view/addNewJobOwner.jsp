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
		    	$("#accessPermissioPopUp1").displaypopup("#accessPermissioPopUp1","770","360");
		    	
		    	
		    	$("#saveNewOwner").click(function() {
		    		$.ajax({url : "${pageContext.request.contextPath}/employer/saveNewJobOwner.html",
		    			data:$('#addJobOwnerForm').serialize(),
						type: "POST",
						success : function(dataFound) {
							alert("hai");							  
							  $("#manageAccPerm").click();
						}					
							
						
					});
							/* 
								$("#addJobOwnerForm")
										.attr(
												"action",
												"${pageContext.request.contextPath}/employer/addNewJobOwner.html");
								$("#addJobOwnerForm")
										.attr("method", "POST");
								$("#addJobOwnerForm").submit();			 */				

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
			 <img src="../resources/images/Close.png" width="19" height="19" class="nyroModalClose" alt="close">
		</div>

		<div class="popUpContainerWrapper">
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Job Owner Name:</span> <form:input path="ownerName"
						name="EmailAddress" class="job_seeker_email width300" />
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Job Owner Email Address:</span> <form:input
						 path="ownerEmail" name="EmailAddress" class="job_seeker_email width300" />
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText4"> </span>
					<div class="floatLeft marginTop5 marginRight10">
						<label> <form:radiobutton name="RadioGroup10" value="5"
							id="RadioGroup1_0"  path="fullAccess" label="Full Access"/> 
						</label>
					</div>

					<div class="floatLeft marginTop5">
						<label> <form:radiobutton name="RadioGroup10" 
							id="RadioGroup1_0"  path="postEditAccess" value="6" label="Post / Edit Only"/> 
						</label>
					</div>
					</div> 

					<div class="rowEvenNewSpacing marginTop10 paddingBottom10">
						<span class="floatLeft marginTop10"><a href="" id="saveNewOwner"
							class="btn_sm orange">SAVE</a> <a href="<%=request.getContextPath()%>/employer/manageAccessPermission.html" id="accessPermissioPopUp1" class="btn_sm orange">Cancel</a></span>
							<a hidden="hidden" class="nyroModal" href="<%=request.getContextPath()%>/employer/manageAccessPermission.html" id="manageAccPerm"></a>
					</div>
					<div class="clearfix"></div>
			</div>
		<div class="clearfix"></div>
	</div>
</form:form>
</body>
</html>