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
		$('#mailCheckbox').prop('checked', true);
		$('#save').click(function(){			
			var printCheckbox = $('#printCheckbox').is(':checked');
			var digCheckbox = $('#digCheckbox').is(':checked');
			var enewsCheckbox = $('#enewsCheckbox').is(':checked');
			var mailCheckbox = $('#mailCheckbox').is(':checked');
			$.ajax({url:"${pageContext.request.contextPath}/subscriptions/saveJobSeekerSubscription.html?printCheckbox="+printCheckbox+
					"&digCheckbox="+digCheckbox+"&enewsCheckbox="+enewsCheckbox+"&mailCheckbox="+mailCheckbox,
				data:$('#subscriptionsId').serialize(),
				type:"POST",
				success: function(data) {					
					//parent.$.nmTop().close();
					alert("Data saved successfully!");
					location.reload();
				 },
			});
		}); 
		
		/* var printEnable = '${printSubscription}';
		var digEnable = '${digSubscription}';
		var newEnable = '${enewsSubscription}';
		var emailEnable = '${emailSubscription}';
		enableSub(printEnable,digEnable,newEnable,emailEnable);
		
		modifyPrint('${listpublicationprint.size()}');
		modifyDig('${listpublicationdigital.size()}');
		modifyNews('${listnewsletter.size()}'); */
		jQuery(".megamenu").megamenu();
	});
	
	function enableSub(printEnable,digEnable,newEnable,emailEnable){
		if (printEnable == 'false') {
			document.getElementById('printCheckbox').disabled = true;
		} 
		if(digEnable =='false'){
			document.getElementById('digCheckbox').disabled = true;
		}
		if(newEnable == 'false'){
			document.getElementById('enewsCheckbox').disabled = true;
		}
		if(emailEnable == 'false'){
			document.getElementById('mailCheckbox').disabled = true;
		}
	}

	function modifyPrint(size) {
		var printCheckbox = $('#printCheckbox').is(':checked');
		if (!printCheckbox) {
			for ( var i = 0; i < size; i++) {
				document.getElementById('print' + i).disabled = true;
			}
		} else {
			for ( var i = 0; i < size; i++) {
				document.getElementById('print' + i).disabled = false;
			}
		}
	}

	function modifyDig(size) {
		var digCheckbox = $('#digCheckbox').is(':checked');
		if (!digCheckbox) {
			for ( var i = 0; i < size; i++) {
				document.getElementById('dig' + i).disabled = true;
			}
		} else {
			for ( var i = 0; i < size; i++) {
				document.getElementById('dig' + i).disabled = false;
			}
		}
	}

	function modifyNews(size) {
		var enewsCheckbox = $('#enewsCheckbox').is(':checked');
		if (!enewsCheckbox) {
			for ( var i = 0; i < size; i++) {
				document.getElementById('news' + i).disabled = true;
			}
		} else {
			for ( var i = 0; i < size; i++) {
				document.getElementById('news' + i).disabled = false;
			}
		}
	}
</script>

</head>

<body class="job_board">
	<form:form method="Post"
		action="../subscriptions/saveJobSeekerSubscription.html"
		id="subscriptionsId" commandName="jobSeekerSubscriptionForm">
		<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
			style="display: block">
			<div class="popupHeader">
				<h2>MODIFY SUBSCRIPTIONS</h2>
				<img src="../resources/images/Close.png" class="nyroModalClose cursor"
					title="Close" width="19" height="19" alt="">
			</div>
			<div class="popUpContainerWrapper">


				<div class="rowEvenSpacingMargin0">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="grid2">
						<tr>
							<td valign="top">
								<table>
									<tr class="borderTopNone">
										<div class="row paddingBottom10 marginLeft10">I would
										like the following sent to me so I can stay up to date with
										the latest healthcare news and information. The following
										subscriptions are always a free service.</div>
									</tr>
									<tr>
										<td valign="top" ><form:hidden path="printCheckbox"
												id="printCheckbox" /><label
												<%-- onchange="modifyPrint('${listpublicationprint.size()}')" /><label --%>
											for="checkbox"><strong>Print-Magazine</strong></label>&nbsp;&nbsp;<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<c:if test="${!listpublicationprint.isEmpty()}">
												<c:forEach items="${listpublicationprint}"
													var="subscriptionsprint" varStatus="status">
												<div style=" width:170px; padding-left:10px;">
													<form:checkbox path="printSub"
														label="${subscriptionsprint.optionName}"
														value="${subscriptionsprint.optionId}"
														cssStyle="width:20px" id="print${status.index}" />
												</div>			
													 												
										       </c:forEach>

											</c:if></td>

										<td valign="top" ><form:hidden path="digCheckbox"
												id="digCheckbox" /><label
												<%-- onchange="modifyDig('${listpublicationdigital.size()}')" /><label --%>
											for="checkbox"><strong>Digital-Magazine</strong></label>&nbsp;&nbsp;<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<c:if test="${!listpublicationdigital.isEmpty()}">
												<c:forEach items="${listpublicationdigital}"
													var="subscriptionsprint" varStatus="status">
													<div style=" width:170px; padding-left:10px;">
													<form:checkbox path="digSub"
														label="${subscriptionsprint.optionName}"
														value="${subscriptionsprint.optionId}"
														id="dig${status.index}" />
													</div>												
										       </c:forEach>
											</c:if></td>

										<td valign="top" >
										<form:hidden path="enewsCheckbox"
												id="enewsCheckbox" /><label
												<%-- onchange="modifyNews('${listnewsletter.size()}')" /><label --%>
											for="checkbox"><strong>E-newsletters</strong></label>&nbsp;&nbsp;<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<c:if test="${!listnewsletter.isEmpty()}">
											 
												<c:forEach items="${listnewsletter}"
													var="subscriptionsprint" varStatus="status">
													<div style=" width:170px; padding-left:10px;">
													<form:checkbox path="newsSub"
														label="${subscriptionsprint.optionName}"
														value="${subscriptionsprint.optionId}"
														id="news${status.index}" />
													
													</div>													
										       </c:forEach>
											</c:if></td>

										<td valign="top" width="120"><form:hidden class="floatLeft" path="mailCheckbox"
												id="mailCheckbox" /><label class="floatLeft" for="checkbox"><strong>E-mails</strong></label>
												<div class="toolTip01 marginLeft5">
											<span class="classic">Select this option if you want us to send you emails regarding featured career opportunities</span>
										</div>&nbsp;&nbsp;<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<c:if test="${!listEmailer.isEmpty()}">
											 
												<c:forEach items="${listEmailer}"
													var="subscriptionsprint" varStatus="status">
													<div style=" width:120px; padding-left:10px;">
													<form:checkbox path="emailSub"
														label="${subscriptionsprint.optionName}"
														value="${subscriptionsprint.optionId}"
														id="news${status.index}" />												
													</div>													
										       </c:forEach>
											</c:if>
												
										</td>
										<%-- <td valign="top">

											<ul>
												<c:forEach items="${jobSubscriptionsList}"
													var="subscriptions" begin="1"
													end="${jobSubscriptionsList.size()}" step="4">

													<form:checkbox path="currentsubs"
														label="${subscriptions.optionName}"
														value="${subscriptions.optionId}" cssStyle="width:20px" />
													<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							 
							  						<c:if test="${!listpublicationdigital.isEmpty()}">

														<c:forEach items="${listpublicationdigital}"
															var="subscriptionsdigital" varStatus="index">

															<form:checkbox path="currentsubscheck"
																label="${subscriptionsdigital.optionName}"
																value="${subscriptionsdigital.optionId}"
																cssStyle="width:20px" />
															<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

														</c:forEach>

													</c:if>
												</c:forEach>
											</ul>


										</td> --%>
										<%-- <td valign="top">
											<ul>
												<c:forEach items="${jobSubscriptionsList}"
													var="subscriptions" begin="2"
													end="${jobSubscriptionsList.size()}" step="4">

													<form:checkbox path="currentsubs"
														label="${subscriptions.optionName}"
														value="${subscriptions.optionId}" cssStyle="width:20px" />
													<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													
                                    				 <c:if
														test="${!listnewsletter.isEmpty()}">
														<c:forEach items="${listnewsletter}"
															var="subscriptionsletter" varStatus="index">

															<form:checkbox path="currentsubscheck"
																label="${subscriptionsletter.optionName}"
																value="${subscriptionsletter.optionId}"
																cssStyle="width:20px" />
															<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														</c:forEach>
													</c:if>
												</c:forEach>
											</ul>
										</td>
										<td valign="top"><c:forEach
												items="${jobSubscriptionsList}" var="subscriptions"
												begin="3" end="${jobSubscriptionsList.size()}" step="4">
												<form:checkbox path="currentsubs"
													label="${subscriptions.optionName}"
													value="${subscriptions.optionId}" cssStyle="width:20px" />
												<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       											</c:forEach></td> --%>
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
				<div class="popUpButtonRow">

					<input type="button" id="save" class="orange cursor" value="Save" />
					<%--<a href="" id="save" class="btn_sm orange">Save</a>  --%>
					<input type="button" value="Cancel"
						class="nyroModalClose orange cursor" name="Cancel" />

					<!-- <a href="" class="nyroModalClose btn_sm orange">Cancel</a> -->
				</div>

			</div>
			<div class="clearfix"></div>
		</div>
	</form:form>
</body>
</html>