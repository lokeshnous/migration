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
/* $("#createEmployerpopup").displaypopup("#createEmployerpopup",
		"750", "350"); */
jQuery(document).ready(function() {
	
	$("#tb_manage_employer img").click(function(event) {

		var action = $(this).attr("alt");
		var rowObj = $(this).parent().parent().parent();
		var facilityId = rowObj.attr("id");
		switch (action) {
		case "delete":{
			if (confirm("Are you sure you want to delete?")) {
					$.ajax({url: "${pageContext.request.contextPath}/agency/deleteAssocFacility.html?facilityId="+facilityId,
						type: "POST",
						success: function(data){ 
						    if(data.success != null){
						    	rowObj.remove();
						    }
						    if(data.failure != null){
						    	alert(data.failed);
						    }
						},
						error: function(response) {
							alert("Server Error : "+response.status);
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
	
	
});
		function closePopup() {
			parent.window.location.reload();
		}
</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>Manage Employer</h2>
			<a href="#"><img src="../resources/images/Close.png" width="19"
				height="19" alt="" onclick="closePopup();"></a>
		</div>
		<div class="popUpContainerWrapper">
			<form:form action="" method="">
				<div class="rowEvenNewSpacing marginTop0">
					<table id="tb_manage_employer" width="100%" border="0" cellspacing="0" cellpadding="0"
						class="grid">
						<tr class="borderTopNone">
							<th width="79%" align="left" scope="col">Employer Name</th>
							<th width="21%" align="left" scope="col">Actions</th>
						</tr>
						<c:forEach items="${assocEmplyrsNames}" var="assocEmplyrsName"
							varStatus="status">
							<tr id="${assocEmplyrsName.facilityId}">
								<td>${assocEmplyrsName.name}</td>
								<td >
									<a  
									href="<%=request.getContextPath()%>/agency/viewFacilityDetails.html?facilityId=${assocEmplyrsName.facilityId}" class="nyroModal view">
									<img src="../resources/images/tranBg.png" width="20" height="20"
									alt="">
								</a>&nbsp;
								    <a href="#"><img src="../resources/images/Delete.png" width="20" height="20" alt="delete"></a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="row marginTop5 paddingBottom10">
					<span class="floatLeft marginTop10"> <a href="#" onclick="closePopup();"
						class="btn_sm orange">Cancel</a></span>
				</div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>
</body>
</html>