<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />
<script type="text/javascript">
	function closePopup() {
		parent.window.location.reload();
	}
	$(document).keyup(function(event) {
		if (event.keyCode == 27) {
			parent.window.location.reload();
		}
	});
	jQuery(document).ready(function() {
		
		$("#tb_view_alerts img").click(function(event) {
			
			var action = $(this).attr("alt");
			var rowObj = $(this).parent().parent().parent();
			var alertId = rowObj.attr("id");
		
			switch (action) {
			case "delete":{
				var r=confirm("Are you sure you want to delete?");
				if(r==true){
					$.ajax({url: "${pageContext.request.contextPath}/alerts/employer/deleteAlert.html?alertId="+alertId,
							success: function(data){ 
							    if(data.success != null){							    	
							    	rowObj.remove();
							    }
							    if(data.failure != null){
							    	alert(data.failure);
							    }
							},
							error: function(response) {
								alert("Server Error : "+response.status);
							},
							complete: function() {
								
							}
						});
					}
					break;
			}
			}
		});	
		
		jQuery(".megamenu").megamenu();
	});
</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>VIEW ALERTS</h2>
			<a href="#"><img src="../resources/images/Close.png" width="19"
				height="19" onclick="closePopup();" alt=""></a>
		</div>

		<div class="popUpContainerWrapper">
			<form:form method="GET" action="" commandName="alertForm">
				<div class="rowEvenNewSpacing marginTop0">
					<table id="tb_view_alerts"  width="100%" border="0" cellspacing="0" cellpadding="0"
						class="grid">
						<thead>
							<tr class="borderTopNone">
								<th width="36%" align="left" scope="col">Alert Type</th>
								<th width="32%" align="left" scope="col">Job Owner</th>
								<th width="20%" align="left" scope="col">Date Set</th>
								<th width="12%" align="center" scope="col">Delete</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${alertList}" var="alertList">
								<tr id="${alertList.alertId}">
									<td align="left">${alertList.getAlertType()}</td>
									<td align="left">${alertList.getJobOwner()}</td>
									<td align="left">${alertList.getSetDate()}</td>
									<td align="center"><!-- <a href="#" alt="delete" class="delete marginLeft25"></a> -->
									<a href="#" title="delete"><img src="../resources/images/tranBg.png" width="20" height="20" alt="delete" class="delete marginLeft25"></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="row marginTop20 paddingBottom10">
					<input type="button" onclick="closePopup();" class="orange"
						value="Cancel" />
					<!-- <a href="" class="btn_sm orange">Cancel</a> -->
				</div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>