<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>

<jsp:include page="common/include.jsp" />

<script type="text/javascript">
	jQuery(document).ready(function() {
 		$('#save').click(function(){			
			
			$.ajax({url:"${pageContext.request.contextPath}/subscriptions/saveJobSeekerSubscription.html",
				data:$('#subscriptionsId').serialize(),
				type:"POST",
				success: function(data) {					
					//parent.$.nmTop().close();
					location.reload();
				 },
			});
		}); 
		
		jQuery(".megamenu").megamenu();
		
	});
</script>

</head>

<body class="job_board">
	<form:form method="Post" action="../subscriptions/saveJobSeekerSubscription.html" id="subscriptionsId"
		commandName="jobSeekerSubscriptionForm">
		<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
			style="display: block">
			<div class="popupHeader">
				<h2>MODIFY SUBSCRIPTIONS</h2>
				<img src="../resources/images/Close.png" class="nyroModalClose"
					width="19" height="19" alt="">
			</div>
			<div class="popUpContainerWrapper">


				<div class="rowEvenSpacingMargin0">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="grid2">
						<tr>
							<td valign="top">
								<table>
									<tr class="borderTopNone">
										<th class="borderTopNone" width="46%" align="left" scope="col">
											Subscriptions</th>
									</tr>
									<c:forEach items="${jobSubscriptionsList}" var="subscriptions"
										varStatus="index">
										<tr>
											<td><form:checkbox path="currentsubs" 
													label="${subscriptions.optionName}"
													value="${subscriptions.optionId}"
													cssStyle="width:20px" /></td>
										</tr>
									</c:forEach>
								</table>
							</td>
							<%-- <td valign="top">
								<table>
									<tr class="borderTopNone">
										<th class="borderTopNone" width="46%" align="left" scope="col">Job
											Alerts</th>
									</tr>
									<c:forEach items="${jobAlertsList}" var="jobAlert"
										varStatus="index">
										<tr>
											<td><form:checkbox path="currentJobAlerts"
													label="${jobAlert.alertName}" value="${jobAlert.alertId}"
													cssStyle="width:20px" /></td>
										</tr>
									</c:forEach>
								</table>
							</td>
							<td valign="top">
								<table>
									<tr class="borderTopNone">
										<th class="borderTopNone" width="46%" align="left" scope="col">Magazines</th>
									</tr>
									<c:forEach items="${jobMagazinesList}" var="magazine"
										varStatus="index">
										<tr>
											<td><form:checkbox path="currentmagazines"
													label="${magazine.magazineName}"
													value="${magazine.magazineId}" cssStyle="width:20px" /></td>
										</tr>
									</c:forEach>
								</table>
							</td> --%>
						</tr>
					</table>
				</div>
				<div class="popUpButtonRow">
					
					<input type="button" id="save" class="orange" value="Save" />
					<%--<a href="" id="save" class="btn_sm orange">Save</a>  --%>
					<input type="button" value="Cancel" 
									class="nyroModalClose orange" name="Cancel" />
					
					<!-- <a href="" class="nyroModalClose btn_sm orange">Cancel</a> -->
				</div>


			</div>
			<div class="clearfix"></div>
		</div>
	</form:form>
</body>
</html>