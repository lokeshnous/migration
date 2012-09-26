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
		    jQuery(document).ready(function(){
		    	$("#addFacility").displaypopup("#addFacility","790","360");
		    	
		    	$("#tb_manage_facility img").click(function(event) {

		    		var action = $(this).attr("alt");
		    		var rowObj = $(this).parent().parent().parent();
		    		var facilityId = rowObj.attr("id");
		    		switch (action) {
		    		case "delete":{
		    			if (confirm("Are you sure you want to delete?")) {
		    					$.ajax({url: "${pageContext.request.contextPath}/facility/deleteFacility.html?facilityId="+facilityId,
		    						type: "POST",
		    						success : function(data) {
										if(data == ""){
											rowObj.remove();	
										}else{
											$("#facilityErrorMsg").html("<span>"+data.failure+"</span>");	
											
										}
									}	
		    					});
		    				return true;
		    			 } else {
		    				return false;
		    			 }
		    			   }
		    			break;
		    		}

		    	});
		    jQuery(".megamenu").megamenu();
		});
		</script>
</head>

<body class="job_board">
<form:form  commandName="manageFacilityForm" id="maangeFacility">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>Manage Facilities</h2>
			<a href="#"><img src="../resources/images/Close.png" width="19"
				height="19" alt=""></a>
		</div>
		<div id="facilityErrorMsg" class="FormErrorDisplayText"></div>
		<div class="popUpContainerWrapper">
			
				<div class="rowEvenNewSpacing marginTop0">
					<table id="tb_manage_facility" width="100%" border="0" cellspacing="0" cellpadding="0"
						class="grid">
						<tr class="borderTopNone">
							<th width="48%" align="left" scope="col">Employer Name</th>
							<th width="35%" align="left" scope="col">Branding Template
								Assigned</th>
							<th width="17%" align="center" scope="col">Actions</th>
						</tr>
						
						<c:forEach items="${manageFacilityForm.facilityDTOList}"
								var="facility" varStatus="status">
								<form:hidden path="facilityDTOList[${status.index}].facilityId" />
								<tr id="${facility.facilityId}">
									<td><a href="#">${facility.name}</a></td>
									<td>template</td>
									<td align="center"><a href="<%=request.getContextPath()%>/facility/editFacility.html?facilityId=${facility.facilityId}" class="nyroModal"><img
									src="../resources/images/Edit.png" width="20" height="20"
									alt=""></a>&nbsp;<a href="#">
									<img
									src="../resources/images/Delete.png" width="20" height="20"
									alt="delete"></a></td>
								</tr>
							</c:forEach>
						
						
					</table>
				</div>
				<div class="row marginTop20 paddingBottom10">
					<a href="<%=request.getContextPath()%>/facility/addFacility.html"
						id="addFacility" class="btn_sm orange">ADD FACILITY</a> <a
						href="<%=request.getContextPath()%>/employer/employerDashBoard.html"
						id="cancelUpdateFacility" class="btn_sm orange">Cancel</a></span>
				</div>
<a hidden="hidden" class="nyroModal" href="<%=request.getContextPath()%>/facility/updateFacilityDetail.html" id="updateFacility"></a>
		</div>
		<div class="clearfix"></div>
	</div>
	</form:form>
</body>
</html>