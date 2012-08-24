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

							<table class="gatewayTable indent10" width="540" border="0"
								cellspacing="0" cellpadding="0">
								<thead>
									<tr>
										<th width="291" align="left" scope="col">Product Name</th>
										<th width="83" align="center" scope="col">Price</th>
										<th width="97" align="center" scope="col">Quantity</th>
										<th width="69" align="right" scope="col">Total</th>
									</tr>
								</thead>
								<tr>
									<td align="left"><span class="gateway_section_head">Standard
											Job Posting</span></td>
									<td align="center">&nbsp;</td>
									<td align="center">&nbsp;</td>
									<td align="right">&nbsp;</td>
								</tr>
								<tr>
									<td align="left">30-day Standard Job Posting</td>
									<td align="center">$399</td>
									<td align="center">2</td>
									<td align="right">$399</td>
								</tr>
								<tr>
									<td align="left">Job Posting Branding Template Upgrade</td>
									<td align="center">$199</td>
									<td align="center">2</td>
									<td align="right">$199</td>
								</tr>
								<tr>
									<td align="left">Universal Geography Upgrade</td>
									<td align="center">$99</td>
									<td align="center">2</td>
									<td align="right">$199</td>
								</tr>
								<tr>
									<td align="left">Premium Sponsored Job Posting Upgrade</td>
									<td align="center">&nbsp;</td>
									<td align="center">2</td>
									<td align="right">$199</td>
								</tr>
								<tr>
									<td align="left">&nbsp;</td>
									<td align="center">&nbsp;</td>
									<td align="center"><strong>Grand Total:</strong></td>
									<td align="right"><strong>$796.00</strong></td>
								</tr>
							</table>
							<p class="borderBottomDotted marginBottom15">&nbsp;</p>
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
									<td align="left">
										<span class="paymentLineHeight">
											${stAddr} <br> 
											${city} <br> 
											${state} <br>
											${country} <br> 
											${zipCode} <br> 
											<span><a id="edit" href="../pgiController/paymentCreditBackMethod.html">Edit</a></span>
										</span>
									</td>
									<td align="center" valign="top">
										<span class="paymentLineHeight">
											${cardType}, ending in ${creditCardNo} <br>
											<span><a href="../pgiController/callPaymentMethod.html">Edit</a></span>
										</span>
									</td>
									<td align="right" valign="top">
										<span class="paymentLineHeight">$796.00</span>
									</td>
								</tr>
							</table>
						</div>
						<div class="buttonContainer indent10">
							<span class="floatLeft"> <input type="submit"
								class="btn_sm orange" value="Place Order" /> 
								<a href="" class="btn_sm white">Cancel</a> 
								<a href="../pgiController/paymentCreditBackMethod.html"
								class="btn_sm white">Back</a>
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