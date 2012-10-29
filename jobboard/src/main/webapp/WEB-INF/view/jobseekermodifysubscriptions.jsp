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
				<img src="../resources/images/Close.png" class="nyroModalClose" title="Close"
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
                                          <th align="left" scope="col">Subscriptions</th>
                                    </tr><tr><td  valign="top">

							
									  <ul>
										<c:forEach items="${jobSubscriptionsList}" var="subscriptions"  begin="0" end="${jobSubscriptionsList.size()}" step="4">
											
												<form:checkbox path="currentsubs"
														label="${subscriptions.optionName}"
														value="${subscriptions.optionId}" cssStyle="width:20px"/>
														<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														
													 <c:if test="${!listpublicationprint.isEmpty()}">	
														<c:forEach items="${listpublicationprint}" var="subscriptionsprint" varStatus="index">
														
															<form:checkbox path="currentsubscheck"
													label="${subscriptionsprint.optionName}" value="${subscriptionsprint.optionId}"
													cssStyle="width:20px" /><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														
														
														
										  </c:forEach>
                                                </c:if>
                                          </c:forEach>
                                    </ul>&nbsp;&nbsp;
								
							 </td><td valign="top">
							 
							  <ul>
							 <c:forEach items="${jobSubscriptionsList}" var="subscriptions"  begin="1" end="${jobSubscriptionsList.size()}" step="4">
							 
							 <form:checkbox path="currentsubs"
														label="${subscriptions.optionName}"
														value="${subscriptions.optionId}" cssStyle="width:20px"/>
														<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							 
							  <c:if test="${!listpublicationdigital.isEmpty()}">
							  
							  <c:forEach items="${listpublicationdigital}"
											var="subscriptionsdigital" varStatus="index">
											
												<form:checkbox path="currentsubscheck"
													label="${subscriptionsdigital.optionName}" value="${subscriptionsdigital.optionId}"
													cssStyle="width:20px" /><br>

										</c:forEach>
										
										   </c:if>
                                          </c:forEach>
                                    </ul>
                                    
                                    
                                     </td><td valign="top">
                                      <ul>
                                 <c:forEach items="${jobSubscriptionsList}" var="subscriptions"  begin="2" end="${jobSubscriptionsList.size()}" step="4">
							 
							 <form:checkbox path="currentsubs"
														label="${subscriptions.optionName}"
														value="${subscriptions.optionId}" cssStyle="width:20px"/>
														<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                     <c:if test="${!listnewsletter.isEmpty()}">
                                       
                                      <c:forEach items="${listnewsletter}"
											var="subscriptionsletter" varStatus="index">
											
												<form:checkbox path="currentsubscheck"
													label="${subscriptionsletter.optionName}" value="${subscriptionsletter.optionId}"
													cssStyle="width:20px" /><br>

										</c:forEach>
                                      
                                        </c:if> 
                          </c:forEach>
                                      
                                     
                             </ul>
                                    
								 </td><td valign="top">
							  <c:forEach items="${jobSubscriptionsList}" var="subscriptions"  begin="3" end="${jobSubscriptionsList.size()}" step="4">
							   <form:checkbox path="currentsubs"
														label="${subscriptions.optionName}"
														value="${subscriptions.optionId}" cssStyle="width:20px"/>
														<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                      
							  
							    </c:forEach>
							  
							  
							   </td>
                                </tr>
							  
						
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


			</div>
			<div class="clearfix"></div>
		</div>
	</form:form>
</body>
</html>