<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />
<script type="text/javascript">
	jQuery(document).ready(function() {
		
		 $('#save').click(function(){
			$.ajax({url:"${pageContext.request.contextPath}/subscriptions/saveFacilitySubscription.html",
				data:$('#subscriptionsId').serialize(),
				type:"GET",
				success: function(data) {			
					location.reload();
				 },
				 error : function (response) {
					 alert("Unable to process");
				 }
			});
			
	     }); 
		jQuery(".megamenu").megamenu();
	});
</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>MODIFY SUBSCRIPTIONS</h2>
			<img src="../resources/images/Close.png" class="nyroModalClose"
				title="Close" width="19" height="19" alt="">
		</div>
		<div class="popUpContainerWrapper">
			<form:form action="../subscriptions/modifyFacilitySubscriptions.html"
				method="GET" id="subscriptionsId" commandName="facilitySubsForm">
				<div class="rowEvenNewSpacing marginTop0">
					<table>
						<tr class="borderTopNone">
							<th align="left" scope="col">Subscriptions</th>
						</tr><tr><td  valign="top">
						<ul>
							<c:forEach items="${facilitySubList}" var="subscriptions"
								begin="0" end="${facilitySubList.size()}" step="3">
								<form:checkbox path="currentsubs"
										label="${subscriptions.optionName}"
										value="${subscriptions.optionId}" cssStyle="width:20px" />
										<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<c:if test="${!digitalSubList.isEmpty()}">
										<c:forEach items="${digitalSubList}" var="digital"
											varStatus="index">
											<form:checkbox path="facsub"
													label="${digital.optionName}" value="${digital.optionId}"
													cssStyle="width:20px" />
													<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</c:forEach>
								</c:if>
							</c:forEach>
						</ul>&nbsp;&nbsp;
						</td><td valign="top">
						<ul>
							<c:forEach items="${facilitySubList}" var="subscriptions"
								begin="1" end="${facilitySubList.size()}" step="3">
								<form:checkbox path="currentsubs"
										label="${subscriptions.optionName}"
										value="${subscriptions.optionId}" cssStyle="width:20px" />
										<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<c:if test="${!enewSubList.isEmpty()}">
										<c:forEach items="${enewSubList}" var="enews"
											varStatus="index">
											<form:checkbox path="facsub"
													label="${enews.optionName}" value="${enews.optionId}"
													cssStyle="width:20px" />
													<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</c:forEach>
								</c:if>
							</c:forEach>
						</ul>
						</td><td valign="top">
						<ul>
							<c:forEach items="${facilitySubList}" var="subscriptions"
								begin="2" end="${facilitySubList.size()}" step="3">
								<form:checkbox path="currentsubs"
										label="${subscriptions.optionName}"
										value="${subscriptions.optionId}" cssStyle="width:20px" />
										<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</c:forEach>
						</ul>
						</td>
						</tr>
					</table>
				</div>
				<div class="row marginTop10 paddingBottom10">
					<span class="floatLeft marginTop10"> 
						<input type="button" id="save" class="orange cursor" value="Save"/> 
						<input type="button" value="Cancel" class="nyroModalClose orange cursor" name="Cancel" /> 
					</span>
				</div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>
</body>
</html>