<%@ page import="com.advanceweb.afc.jb.common.util.MMJBCommonConstants" %>
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

		function isPositiveInt(number){
			var intRegex = /^\d+$/;
			if(!intRegex.test(number.toString())) {
				return true;
			}
			return false;
		}

		jQuery(document).ready(function() {
	$('#redeemWithHyperlink').hide();

						jQuery(".megamenu").megamenu();

						if ($('#promotionCode').val() != '15ADVoff'
								|| $('#discountAmt').val() > 0) {
							$('#redeemWithHyperlink').hide();
							$('#redeemWithOutHyperlink').show();
						} else {
							$('#redeemWithOutHyperlink').hide();
							$('#redeemWithHyperlink').show();
						}
						$('#promotionCode').on(
								'propertychange keyup input paste',
								function() {
									var redeemCode = $(this).val();
									if (redeemCode == '15ADVoff'
											&& $('#discountAmt').val() <= 0) {
										$('#redeemWithOutHyperlink').hide();
										$('#redeemWithHyperlink').show();

									} else {
										$('#redeemWithHyperlink').hide();
										$('#redeemWithOutHyperlink').show();

									}
								});

						$("#redeemLink")
								.click(
										function() {
											var redeemCode = $('#promotionCode')
													.val();
											$
													.ajax({
														url : "${pageContext.request.contextPath}/purchaseJobPosting/calculateDiscount.html",
														type : "POST",
														data : {
															"grandTotalAmt" : $(
																	'#grandTotal')
																	.val(),
															"promotionCode" : redeemCode
														},
														success : function(data) {
															if (null != data) {
																$(
																		"#showPurchaseJobPostCart")
																		.click();
															}
														},
														error : function(
																response) {
															alert("Server Error : "
																	+ response.status);
														}
													});

										});
						$("#purchaseJobPostingId :checkbox").change(
								function() {
									if ($(this).is(':checked')) {
										var planObj = $(this).parent().parent()
												.parent().parent().parent()
												.children(0).children(0)
												.children(0).children(0);
										if (!planObj.is(':checked')) {
											planObj.click();
										}
									}
								});

						$("#purchaseJobPostingId input[type='radio']")
								.change(
										function() {
											$(
													"#purchaseJobPostingId input[type='radio']")
													.each(
															function() {
																if (!$(this)
																		.is(
																				':checked')) {
																	var tableId = "#tb_jobPost_"
																			+ $(
																					this)
																					.parent()
																					.parent()
																					.attr(
																							"id");
																	$(
																			tableId
																					+ " input[type='checkbox']")
																			.each(
																					function() {
																						$(
																								this)
																								.attr(
																										"checked",
																										false);
																					});
																	$(this)
																			.parent()
																			.parent()
																			.find(
																					"td")
																			.eq(
																					2)
																			.children(
																					0)
																			.val(
																					"");
																	$(this)
																			.parent()
																			.parent()
																			.find(
																					"td")
																			.eq(
																					2)
																			.children(
																					0)
																			.attr(
																					"readonly",
																					true);
																} else {
																	$(this)
																			.parent()
																			.parent()
																			.find(
																					"td")
																			.eq(
																					2)
																			.children(
																					0)
																			.attr(
																					"readonly",
																					false);
																}
															});
										});

						$("#proceedToCheckout")
								.click(
										function() {
											if ($("#grandTotalId").text() == "$0.00") {
												//alert("Please select any one of the package to proceed to checkout!");
												alert("You need to add items to your shopping cart to proceed to checkout!");
												return false;
											} else {
												var redeemCode = $(
														'#promotionCode').val();
												$("#purchaseJobPostId")
														.attr(
																"action",
																"${pageContext.request.contextPath}/purchaseJobPosting/proceedToCheckOut.html?promotionCode="
																		+ redeemCode);
												$("#purchaseJobPostId").attr(
														"method", "GET");
												$("#purchaseJobPostId")
														.submit();

											}
											return true;
										});

						$("#jobPostingsCart a")
								.click(
										function() {
											if ($(this).html() == "Remove") {
												$
														.ajax({
															url : "${pageContext.request.contextPath}/purchaseJobPosting/removeJobPost.html",
															type : "POST",
															data : {
																"cartItemIndex" : parseInt($(
																		this)
																		.attr(
																				"id"))
															},
															success : function(
																	data) {
																if (null != data) {
																	$(
																			"#showPurchaseJobPostCart")
																			.click();
																}
															},
															error : function(
																	response) {
																alert("Server Error : "
																		+ response.status);
															}
														});
											}

										});

						$("#jobPostingsCart input[name='healthCareSubSplty2']")
								.change(
										function() {
											var quantity = $(this).val();
											if (isNaN(quantity)
													|| quantity <= 0
													|| isPositiveInt(quantity)) {
												alert("Please enter quantity in numerics( > 0)");
												return;
											}

											$
													.ajax({
														url : "${pageContext.request.contextPath}/purchaseJobPosting/updateQuantity.html",
														type : "POST",
														data : {
															"cartItemIndex" : parseInt($(
																	this)
																	.next()
																	.attr("id")),
															"quantity" : parseInt($(
																	this).val())
														},
														success : function(data) {
															if (null != data) {
																$(
																		"#showPurchaseJobPostCart")
																		.click();
															}
														},
														error : function(
																response) {
															alert("Server Error : "
																	+ response.status);
														}
													});
										});

						$("#addToCart")
								.click(
										function() {
											$(".AddToCartfloatRight").hide();
											var count = 0;
											$(
													"#purchaseJobPostingId input[type='radio']")
													.each(
															function() {
																if ($(this)
																		.is(
																				':checked')) {
																	count++;
																	var quantity = $(
																			this)
																			.parent()
																			.parent()
																			.find(
																					"td")
																			.eq(
																					2)
																			.children(
																					0)
																			.val();

																	if (isNaN(quantity)
																			|| quantity <= 0
																			|| isPositiveInt(quantity)) {
																		$(
																				".AddToCartfloatRight")
																				.show();
																		alert("Please enter quantity in numerics( > 0)");
																		return;
																	} else {
																		var planObj = $(
																				this)
																				.parent()
																				.parent();
																		var planId = planObj
																				.attr("id");
																		var tableId = "#tb_jobPost_"
																				+ planId;
																		var addOnStr = [];

																		$(
																				tableId
																						+ " :checkbox")
																				.each(
																						function() {
																							if ($(
																									this)
																									.is(
																											':checked')) {
																								addOnStr
																										.push({
																											"addOnId" : $(
																													this)
																													.parent()
																													.parent()
																													.parent()
																													.attr(
																															"id"),
																											"addOnName" : $(
																													this)
																													.parent()
																													.children(
																															1)
																													.text(),
																											"addOnDescription" : "",
																											"addOnCreditAmt" : $(
																													this)
																													.parent()
																													.parent()
																													.parent()
																													.find(
																															"td")
																													.eq(
																															1)
																													.attr(
																															"id"),
																										});
																							}
																						});

																		var jobPostObj = [];
																		jobPostObj
																				.push({
																					"jobPostPlanId" : planId,
																					"jobPostPlanName" : planObj
																							.find(
																									"td")
																							.eq(
																									0)
																							.children(
																									1)
																							.text(),
																					"jobPostPlanDescr" : "",
																					"jobPostPlanCretitAmt" : planObj
																							.find(
																									"td")
																							.eq(
																									1)
																							.attr(
																									"id"),
																					"addOnForm" : addOnStr,
																					"quantity" : parseInt(quantity),
																				});

																		$
																				.ajax({
																					url : "${pageContext.request.contextPath}/purchaseJobPosting/addToCart.html",
																					type : "POST",
																					data : {
																						"jobPostJson" : JSON
																								.stringify(jobPostObj[0])
																					},
																					beforeSend : function(
																							x) {
																						if (x
																								&& x.overrideMimeType) {
																							x
																									.overrideMimeType("application/j-son;charset=UTF-8");
																						}
																					},
																					success : function(
																							data) {
																						//	
																						if (null != data) {
																							$(
																									"#showPurchaseJobPostCart")
																									.click();
																						}
																					},
																					error : function(
																							response) {
																						$(
																								".AddToCartfloatRight")
																								.show();
																						alert("Server Error : "
																								+ response.status);
																					}
																				});
																	}
																}
															});
											if (count == 0) {
												$(".AddToCartfloatRight")
														.show();
												alert("Please select any one of the package to Add To Cart");
											}
										});
					});
</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>PURCHASE JOB POSTINGS</h2>
			<img id="closeCheckOut" src="../resources/images/Close.png" width="19" height="19" title="Close"
				alt="" class="nyroModalClose cursor">
		</div>
		<div class="popUpContainerWrapper">
		<form:form method="post" action="html" commandName="purchaseJobPostForm" id="purchaseJobPostId">
		<form:hidden path="total"/>
		<form:hidden path="discountAmt"/>
		<form:hidden path="grandTotal"/>
			 <c:if test="${empty purchaseJobPostForm.jobPostingsForm}">
						<tr><td>
							<br><p align="left" class="FormErrorDisplayText">No Job Postings Found</p><br><br><br><br><br>
							<a href="#" class="nyroModalClose btn_sm orange cursor">Cancel</a>
						</td></tr>
			</c:if>
			 <c:if test="${not empty purchaseJobPostForm.jobPostingsForm}">
				<div class="rowEvenNewSpacing marginTop5 marginTop0">
					<div class=" row DotBorderBottom">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="marginBottom3">
							<tr cellpadding="0" cellspacing="0" border="0">
								<td width="32%" align="Left">&nbsp;</td>
								<td width="7%" align="Left"><h3 class="TextColorA01">Price</h3></td>
								<td width="19%"><h3 class="TextColorA01">Quantity</h3></td>
							</tr>
							<tr>
							</tr>
						</table>
					</div>
					<div id="purchaseJobPostingId" class="row">
					 <c:forEach items="${purchaseJobPostForm.jobPostingsForm}" var="jobPosting" varStatus="status">	
						<table id="tb_jobPost_${jobPosting.jobPostPlanId}" width="100%" border="0" cellpadding="0" cellspacing="0"
							class="marginTop5">
							<thead> <tr id="${jobPosting.jobPostPlanId}">
								<td width="32%" height="30px;" align="Left"><input
									name="radio" type="radio" id="radio1" value="radio"
									class="marginRight5"> <label for="radio"
									class="link_color2_selected">${jobPosting.jobPostPlanName}</label></td>
								<td width="7%" align="Left" id="${jobPosting.jobPostPlanCretitAmt}"><span
									class="link_color2_selected"><span>$</span><span>${jobPosting.jobPostPlanCretitAmt}</span></span></td>
								<td width="19%"><input type="text"
									name="healthCareSubSplty2" readonly="readonly" class="jb_input4 marginTop0" maxlength="3"/></td>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${jobPosting.addOnForm}" var="addOn" varStatus="status">
								<c:if test="${addOn.addOnName != 'Basic'}">
									<tr id="${addOn.addOnId}">
										<td width="32%" height="30px;" align="Left"><div
												class="floatLeft">
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input name="" type="checkbox"
													value="" class="marginRight5"> <label for="checkbox">${addOn.addOnName}</label> &nbsp;&nbsp;
											</div>
											<div class="toolTip">
												<span class="classic">${addOn.addOnDescription}</span>
											</div></td>
										<td width="7%" align="Left" id="${addOn.addOnCreditAmt}"><span>$</span><span>${addOn.addOnCreditAmt}</span></td>
										<td width="19%"></td>
									</tr>
								</c:if>
							</c:forEach>
							</tbody>
						</table>
						</c:forEach>
					</div>
					<div class=" row DotBorderBottom"></div>
				</div>
				<div class="row marginTop20 paddingBottom10">
					<div class="AddToCartfloatRight">
						<a id="addToCart" href="#" class="btn_sm orange cursor">Add To Cart</a>
					</div>
				</div>
				
				<div id="jobPostingsCart">
					
					<div class="rowEvenNewSpacing marginTop20">
					<div class=" row">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="marginBottom3">
							<tr cellpadding="0" cellspacing="0" border="0">
								<td width="32%" align="Left"><h3 class="TextColorA01">&nbsp;My
										Shopping Cart</h3></td>
								<td width="7%" align="Left"><h3 class="TextColorA01">Price</h3></td>
								<td width="19%"><h3 class="TextColorA01">Quantity</h3></td>
							</tr>
							<tr>
							</tr>
						</table>
					</div>
					<c:if test="${empty purchaseJobPostForm.jobPostingsCart}">
						<div class=" row DotBorderBottom marginTop5"></div>
						<br><br><p align="left" class="FormErrorDisplayText">Please select the packages</p><br><br><br>
					</c:if>
					
					<% int i= 0; %>
					<!-- cart  start-->
					<c:forEach items="${purchaseJobPostForm.jobPostingsCart}" var="cartItem" varStatus="status">
					 <div class=" row DotBorderBottom marginTop5"></div>
						<div class="row">
						<table width="100%" border="0" cellpadding="0" cellspacing="0" 
							class="marginTop5">
							<tr>
								<td width="32%" height="30px;" align="Left"><label
									for="radio" class="link_color2_selected">${cartItem.jobPostPlanName}</label></td>
								<td width="7%" align="Left"><span>$</span>${cartItem.jobPostPlanCretitAmt}</td>
								<td width="19%"></td>
							</tr>
							<c:forEach items="${cartItem.addOnForm}" var="addOn" varStatus="status">
							<tr>
								<td width="32%" height="30px;" align="Left"><div
										class="floatLeft">
										&nbsp;&nbsp;&nbsp; <label for="checkbox">${addOn.addOnName}</label> &nbsp;&nbsp;
									</div></td>
								<td width="7%" align="Left"><span>$</span>${addOn.addOnCreditAmt}</td>
								<td width="19%"></td>
							</tr>
							</c:forEach>
							<tr>
								<td width="32%" height="30px;" align="Left"><label
									for="radio" class="link_color2_selected">Package
										Subtotal</label></td>
								<td width="7%" align="Left"><h3 class="TextColorA01"><span
									class="link_color2_selected">$</span>${cartItem.packageSubTotal}</h3></td>
								<td width="19%"><input name="healthCareSubSplty2" maxlength="3"
									type="text" class="jb_input4 marginTop0 mar" value="${cartItem.quantity}" /><a
									href="#" class="marginLeft20" id="<%=i++%>" >Remove</a></td>
							</tr>
						</table>
					</div>
					</c:forEach>
					<!-- cart  end-->
					<div class="row">
					<c:if test="${purchaseJobPostForm.discountAmt > 0}">
						<div class="row" id="discountOnReDeem" >
						<div class="SolidBorderBottom"></div>
							<table width="100%" border="0" cellpadding="5" cellspacing="5">
								<tr cellpadding="0" cellspacing="0" border="0">
									<td width="32%" align="Left"><h3 class="TextColorA01">
											Total:</h3></td>
									<td width="7%" align="Left"><h3 class="TextColorA01" id="totalId"><span>$</span>${purchaseJobPostForm.total}0</h3></td>
									<td width="19%"><h3 class="TextColorA01">&nbsp;</h3></td>
								</tr>
								<tr>
								<td width="32%" align="Left"><h3 class="TextColorA01">Discount:</h3></td>
									<td width="7%" align="Left"><h3 class="TextColorA01" id="discountAmtlId"><span>-$</span>${purchaseJobPostForm.discountAmt}0</h3></td>
									<td width="19%"><h3 class="TextColorA01">&nbsp;</h3></td>
								</tr>
							</table>
						</div>
						</c:if>
					</div>
					<div class="row">
						<div class="SolidBorderBottom"></div>
						<div class="row">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr cellpadding="0" cellspacing="0" border="0">
									<td width="32%" align="Left"><h3 class="TextColorA01">Grand
											Total:</h3></td>
									<td width="7%" align="Left"><h3 class="TextColorA01" id="grandTotalId"><span>$</span>${purchaseJobPostForm.grandTotal}0</h3></td>
									<td width="19%"><h3 class="TextColorA01">&nbsp;</h3></td>
								</tr>
								<tr>
								</tr>
							</table>
						</div>
					</div>
				</div>


						<div class="row marginTop20">
							<div class="floatLeft marginBottom5">Promotion Code:</div>
							<br>


							<div class=" row floatLeft marginBottom5">
								<form:input  path="promotionCode" class="job_seeker_email" />

								
									<div id="redeemWithOutHyperlink">
									<span class="marginLeft20">Redeem</span>
									</div>

									<div id="redeemWithHyperlink">
										<a href="#" class="marginLeft20" id="redeemLink">Redeem</a>
										</div>
							</div>
						</div>
						<div class="row marginTop20 paddingBottom10">
					<span class="floatLeft marginTop10"><a href="#" id="proceedToCheckout"
						class="btn_sm orange cursor">Proceed to Checkout</a> 		
					<c:choose>
						<c:when test="${pageName == 'postJobPage'}">
								<a href="<%=request.getContextPath()%>/inventory/employer/jobInventory.html" class="nyroModal btn_sm orange cursor">Cancel</a>
						</c:when>
						<c:otherwise>
								<a href="#" class="nyroModalClose btn_sm orange cursor">Cancel</a>
						</c:otherwise>
					</c:choose>
						</span> <span class="floatLeft marginTop10 marginLeft5"></span>
				</div>
				
				</div>
				<a id="showPurchaseJobPostCart" class="nyroModal" href="<%=request.getContextPath()%>/purchaseJobPosting/showPurchaseJobPostCart.html"></a>
				</c:if>
			</form:form>
		</div>
		<div class="clearfix"></div>		
	</div>
</body>
</html>