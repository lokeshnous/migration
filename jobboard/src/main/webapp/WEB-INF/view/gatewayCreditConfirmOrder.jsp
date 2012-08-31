<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>

<!-- STYLESHEETS -->
<link href="../resources/css/JB.css" rel="stylesheet" type="text/css" />
<link href="../resources/css/jquery.megamenu.css" rel="stylesheet"
	type="text/css" />
<link href="../resources/css/SliderStyles.css" rel="stylesheet"
	type="text/css">
<link href="../resources/css/Gateway.css" rel="stylesheet"
	type="text/css">
<!--[if IE]>
	<link href="stylesheets/ie.css" rel="stylesheet" type="text/css">
<![endif]-->

<!-- JAVASCRIPT FILES -->
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript"
	src="../resources/js/jquery.cycle.all.min.js"></script>
<script type="text/javascript" src="../resources/js/slider.js"></script>
<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery(".megamenu").megamenu();
	});

	/* $('#edit').click(function(){
		url = "../pgiController/paymentCreditBackMethod.html";
	    $('#firstname2').focus();
	}); */
</script>
<script type="text/javascript">
	function cancelProcess() {
		//window.location.href = '';
	}
	function backProcess() {
		window.location.href = '../pgiController/paymentCreditBackMethod.html';
	}
</script>
</head>

<body class="job_board">
	<div class="ad_page_top">
		<img src="images/ads/banner_ad_fpo.png" />
	</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">
			<div class="main">
				<jsp:include page="../templates/templates_header.jsp"></jsp:include>
				<div class="gateway">
					<h2>Order Process</h2>
					<h3 class="gatewayBreadcrumbs">
						Billing and Payment >> <span class="nextStep">Confirm Order
							>></span>
					</h3>
					<form:form action="../pgiController/callThankYouPage.html"
						method="POST" class="firstForm">
						<div class="row">
							<h3 class="gatewayBreadcrumbs main_section">Review Order</h3>
							<p class="form_notes review_order">Please review your order
								details and payment information. If you need to make any
								changes, you can click on the appropriate 'Edit' links below.
								This transaction will be final once you hit the 'Place Order'
								button, so please review carefully before proceeding.</p>

							<h3 class="gatewayBreadcrumbs main_section">Order Details</h3>

							<!-- cart details start-->
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

									<%
										int i = 0;
									%>
									<!-- cart  start-->
									<c:forEach items="${purchaseJobPostForm.jobPostingsCart}"
										var="cartItem" varStatus="status">
										<div class=" row DotBorderBottom marginTop5"></div>
										<div class="row">
											<table width="100%" border="0" cellpadding="0"
												cellspacing="0" class="marginTop5">
												<tr>
													<td width="32%" height="30px;" align="Left"><label
														for="radio" class="link_color2_selected">${cartItem.jobPostPlanName}</label></td>
													<td width="7%" align="Left"><span>$</span>${cartItem.jobPostPlanCretitAmt}</td>
													<td width="19%"></td>
												</tr>
												<c:forEach items="${cartItem.addOnForm}" var="addOn"
													varStatus="status">
													<tr>
														<td width="32%" height="30px;" align="Left"><div
																class="floatLeft">
																&nbsp;&nbsp;&nbsp; <label for="checkbox">${addOn.addOnName}</label>
																&nbsp;&nbsp;
															</div></td>
														<td width="7%" align="Left"><span>$</span>${addOn.addOnCreditAmt}</td>
														<td width="19%"></td>
													</tr>
												</c:forEach>
												<tr>
													<td width="32%" height="30px;" align="Left"><label
														for="radio" class="link_color2_selected">Package
															Subtotal</label></td>
													<td width="7%" align="Left"><span
														class="link_color2_selected">$</span>${cartItem.packageSubTotal}</td>
													<td width="19%"><input name="healthCareSubSplty2"
														type="text" class="jb_input75 marginTop0 mar"
														value="${cartItem.quantity}" /><a href="#"
														class="marginLeft20" id="<%=i++%>">Remove</a></td>
												</tr>
											</table>
										</div>
									</c:forEach>
									<!-- cart  end-->
									<div class="row">
										<div class="SolidBorderBottom"></div>
										<div class="row">
											<table width="100%" border="0" cellpadding="0"
												cellspacing="0">
												<tr cellpadding="0" cellspacing="0" border="0">
													<td width="32%" align="Left"><h3 class="TextColorA01">Grand
															Total:</h3></td>
													<td width="7%" align="Left"><h3 class="TextColorA01">
															<span>$</span>${purchaseJobPostForm.grandTotal}
														</h3></td>
													<td width="19%"><h3 class="TextColorA01">&nbsp;</h3></td>
												</tr>
												<tr>
												</tr>
											</table>
										</div>
									</div>
								</div>
								<!-- cart details end -->


								<p class="marginBottom15">&nbsp;</p>
								<h3 class="gatewayBreadcrumbs main_section">Payment
									Information</h3>
								<table class="gatewayTable indent10" width="540" border="0"
									cellspacing="0" cellpadding="0">
									<thead>
										<tr>
											<th align="left" scope="col">Bill To</th>
											<th align="center" scope="col">Payment Method</th>
											<th align="right" scope="col">Order Total</th>
										</tr>
									</thead>

									<tr>
										<td align="left"><span class="paymentLineHeight">
												${stAddr} <br> ${city} <br> ${state} <br>
												${country} <br> ${zipCode} <br> <span><a
													id="edit"
													href="../pgiController/paymentCreditBackMethod.html">Edit</a></span>
										</span></td>
										<td align="center" valign="top"><span
											class="paymentLineHeight"> ${cardType}, ending in
												${creditCardNo} <br> <span><a
													href="../pgiController/callPaymentMethod.html">Edit</a></span>
										</span></td>
										<td align="right" valign="top"><span
											class="paymentLineHeight">$796.00</span></td>
									</tr>
								</table>
							</div>
							<div class="buttonContainer indent10">
								<span class="floatLeft"> <input type="submit"
									class="orange" value="Place Order" /> <input type="button"
									value="Cancel" onclick="cancelProcess()" class="white"
									name="Cancel" /> <input type="button" value="Back"
									onclick="backProcess()" class="white" name="Back" /> <!-- <a href="" class="btn_sm white">Cancel</a> 
								<a href="../pgiController/paymentCreditBackMethod.html"
								class="btn_sm white">Back</a> -->
								</span>
							</div>
					</form:form>
				</div>
				<!-- gateway -->
				<div class="clearfix"></div>
				<div class="ad_wrapper">
					<img src="../resources/images/ads/banner_ad_fpo.png" />
				</div>
			</div>
			<!-- main -->

		</div>
		<!-- end main_wrapper_inside -->
	</div>
	<!-- end main_wrapper_outside -->

	<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
	<!-- footer_wrapper -->

</body>
</html>