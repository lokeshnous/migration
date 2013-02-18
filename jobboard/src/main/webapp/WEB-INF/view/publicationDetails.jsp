<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="Post" id="subscriptionFormId"
							commandName="registerForm2" enctype="multipart/form-data">
<table  width="100%" border="0" cellspacing="0" cellpadding="0"
						class="grid2 Padding0" id="publicationId">
						<tr>
							<td valign="top">
								<table>
									<tr>
										<td valign="top"><div style="width:170px; margin:0 5px;"><%-- <form:checkbox path="printCheckbox"
												id="printCheckbox"
												onchange="modifyPrint('${listpublicationprint.size()}')" /> --%><!-- <label
											for="checkbox">Print-Magazine</label> -->
											<c:if test="${!listpublicationprint.isEmpty()}">
												<c:forEach items="${listpublicationprint}"
													var="subscriptionsprint" varStatus="status">

													<form:checkbox path="printSub"
														label="${subscriptionsprint.optionName}"
														value="${subscriptionsprint.optionId}"
														cssStyle="width:20px; text-align:left;" id="print${status.index}" />
													<br />	
													 												
										       </c:forEach>

											</c:if></div></td>

										<td  valign="top"><div style="width:170px; margin:0 5px;">
										<%-- <form:checkbox path="digCheckbox"
												id="digCheckboxSub"
												onchange="modifyDig('${listpublicationdigital.size()}')" /><label
											for="checkbox">Digital-Magazine</label> --%>
											<c:if test="${!listpublicationdigital.isEmpty()}">
												<c:forEach items="${listpublicationdigital}"
													var="subscriptionsprint" varStatus="status">

													<form:checkbox path="digSub"
														label="${subscriptionsprint.optionName}"
														value="${subscriptionsprint.optionId}"
														cssStyle="width:20px" id="dig${status.index}" />
													<br />														
										       </c:forEach>
											</c:if></div></td>

										<td valign="top"> <div style="width:170px; margin:0 5px;">
										<%-- <form:checkbox path="enewsCheckbox"
												id="enewsCheckboxSub"
												onchange="modifyNews('${listnewsletter.size()}')" /><label
											for="checkbox">E-newsletters</label> --%>
											<c:if test="${!listnewsletter.isEmpty()}">
												<c:forEach items="${listnewsletter}"
													var="subscriptionsprint" varStatus="status">

													<form:checkbox path="newsSub" 
														label="${subscriptionsprint.optionName}"
														value="${subscriptionsprint.optionId}"
														 id="news${status.index}" />
													<br />														
										       </c:forEach>
											</c:if>
											</div></td>

										<td  valign="top"><div style="width:170px; margin:0 5px;">
										<%-- <form:checkbox path="mailCheckbox"
												id="mailCheckboxSub" /> <label for="checkbox">E-mailer</label>--%>
												<c:if test="${!listEmailer.isEmpty()}">
												<c:forEach items="${listEmailer}"
													var="subscriptionsprint" varStatus="status">

													<form:checkbox path="emailSub" 
														label="${subscriptionsprint.optionName}"
														value="${subscriptionsprint.optionId}"
														 id="emailId" />
													<br />														
										       </c:forEach>
											</c:if>
											</div>
										</td>
										
									</tr>
								</table>
							</td>
							
						</tr>
					</table>
					</form:form>