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

	function copyAccToBillingAddr(obj) {
		var isSelected = obj.value;
		//alert(isSelected);
		if (isSelected) {
			$("#firstname2").val($("#firstName").val());
			$("#lastname2").val($("#lastname").val());
			$("#streetAddress_billing1").val($("#streetAddress").val());
			$("#cityTown2").val($("#cityTown").val());
			$("#State2").val($("#state").val());
			$("#Country2").val($("#country").val());
			$("#zip2").val($("#zip").val());
		}
	}
</script>
<script type="text/javascript">
		    function cancelProcess(){
		    	//window.location.href = '';
		    }		
		    function backProcess(){
		    	window.location.href = '../pgiController/callPaymentMethod.html';
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
					<form:form action="../pgiController/callInvoiceConfirmOrder.html"
						method="POST" commandName="paymentMethodForm">

						<div class="row">
							<h3 class="gatewayBreadcrumbs main_section">Account
								Information</h3>
							<p class="form_notes">
								Please fill in all of the information below. <span>(All
									items are required to complete your order.)</span>
							</p>
						</div>


						<div class="row">
							<span class="lableText3">First Name:</span>
							<form:input type="text" path="accountAddressForm.firstName"
								id="firstName" class="job_seeker_password textBox350" />
							<form:errors path="accountAddressForm.firstName"></form:errors>
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">Middle Name:</span>
							<form:input type="text" path="accountAddressForm.middleName"
								name="middlename" id="middlename"
								class="job_seeker_password textBox350" />
							<span class="required">(Optional)</span>
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">Last Name:</span>
							<form:input type="text" path="accountAddressForm.lastName"
								name="lastname" id="lastname"
								class="job_seeker_password textBox350 " />
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">Email Address:</span>
							<form:input path="accountAddressForm.email" type="text"
								name="email" id="email" class="job_seeker_password textBox350 " />
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">Company</span>
							<form:input path="accountAddressForm.company" type="company"
								name="company" id="company"
								class="job_seeker_password textBox350 " />
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">Street Address:</span>
							<form:input path="accountAddressForm.streetAddress" type="text"
								name="streetAddress" id="streetAddress"
								class="job_seeker_password textBox350 " />
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">City / Town:</span>
							<form:input path="accountAddressForm.cityOrTown" type="text"
								name="cityTown" id="cityTown"
								class="job_seeker_password textBox350 " />
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">State / Province:</span>

							<form:input path="accountAddressForm.state" type="text"
								name="state" id="state"
								class="job_seeker_password textBox350 " />
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">Country:</span>
							<form:input path="accountAddressForm.country" type="text"
								name="country" id="country"
								class="job_seeker_password textBox350 " />
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">ZIP Code:</span>
							<form:input path="accountAddressForm.zipCode" type="text"
								name="zip" id="zip" class="job_seeker_password textBox350 " />
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">Phone:</span>
							<form:input path="accountAddressForm.phone" type="text"
								name="phone" class="job_seeker_password textBox350 " />
						</div>

						<p class="borderBottomDotted marginBottom15">&nbsp;</p>
				<!-- Billing Address -->
						<h3 class="gatewayBreadcrumbs main_section">Billing Address</h3>
						<p class="gateway_section_head form_notes">Accounts Payable
							Contact</p>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">First Name:</span>
							<form:input path="billingAddressForm.fnameForBillingAddr"
								type="text" name="firstname2" id="firstname2"
								class="job_seeker_password textBox350 " />
							<span class="required">(Required)</span>
						</div>

						<div>
							<font color="red" style="padding-left: 185px"><form:errors
									path="billingAddressForm.fnameForBillingAddr" /> </font>
						</div>
						<div class="rowEvenSpacing">
							<span class="lableText3">Last Name:</span>
							<form:input path="billingAddressForm.lnameForBillingAddr"
								type="text" name="lastname2" id="lastname2"
								class="job_seeker_password textBox350 " />
							<span class="required">(Required)</span>
						</div>
						<div>
							<font color="red" style="padding-left: 185px"><form:errors
									path="billingAddressForm.lnameForBillingAddr" /> </font>
						</div>

						<div class="rowEvenNewSpacing reuse_address">
							<form:checkbox onchange="copyAccToBillingAddr(this)"
								value="false" path="billingAddressForm.useMyAccountAddr"
								name="useAcctAddress" />
							<span>Use my account address</span>
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">Street Address:</span>
							<form:input path="billingAddressForm.streetForBillingAddr"
								type="text" name="streetAddress_billing1"
								id="streetAddress_billing1"
								class="job_seeker_password textBox350 " />
							<span class="required">(Required)</span>
						</div>
						<div>
							<font color="red" style="padding-left: 185px"><form:errors
									path="billingAddressForm.streetForBillingAddr" /> </font>
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">City/Town:</span>
							<form:input path="billingAddressForm.cityOrTownForBillingAddr"
								type="text" name="cityTown2"
								id="cityTown2"
								class="job_seeker_password textBox350 " />
							<span class="required">(Required)</span>
						</div>
						<div>
							<font color="red" style="padding-left: 185px"><form:errors
									path="billingAddressForm.cityOrTownForBillingAddr" /> </font>
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">State / Province:</span>
							<form:select path="billingAddressForm.stateBillingAddress"
								name="State2" id="State2" class="jb_input3 jb_input_width3">
								<form:option value="0" label="Select" />
								<form:options items="${stateList}" itemValue="stateValue"
									itemLabel="stateValue" />
							</form:select>
							<span class="required">(Required)</span>
						</div>
						<div>
							<font color="red" style="padding-left: 185px"><form:errors
									path="billingAddressForm.stateBillingAddress" /></font>
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">Country:</span>
							<form:select name="Country2"
								path="billingAddressForm.countryForBillingAddr" id="Country2"
								class="jb_input3 jb_input_width3">
								<form:option value="0" label="Select" />
								<form:options items="${countryList}" itemValue="countryValue"
									itemLabel="countryValue" />
							</form:select>
							<span class="required">(Required)</span>
						</div>
						<div>
							<font color="red" style="padding-left: 185px"><form:errors
									path="billingAddressForm.countryForBillingAddr" /> </font>
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">ZIP Code:</span>
							<form:input path="billingAddressForm.zipCodeForBillingAddr"
								type="text" name="zip2" id="zip2"
								class="job_seeker_password textBox350 " />
							<span class="required">(Required)</span>
						</div>
						<div>
							<font color="red" style="padding-left: 185px"><form:errors
									path="billingAddressForm.zipCodeForBillingAddr" /> </font>
						</div>
						<form:hidden path="billingAddressForm.facilityContactId" />

						<p class="borderBottomDotted marginBottom15">&nbsp;</p>

						<h3 class="gatewayBreadcrumbs main_section">Invoice
							Information</h3>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">Purchase Order Number:</span>
							<form:input path="invoiceForm.purchaseOrderNo" type="text"
								name="PO_number" id="PO_number"
								class="job_seeker_password textBox350 " />
							<span class="required">(Required)</span>
						</div>
						<div>
							<font color="red" style="padding-left: 185px"> <form:errors
									path="invoiceForm.purchaseOrderNo" />
							</font>
						</div>

						<div class="clearfix"></div>

						<div class="buttonContainer">
							<span class="floatLeft"><input type="submit"
								class="orange" value="Continue to Next Step" /> 
								<input type="button" value="Cancel" onclick="cancelProcess()"
									class="white" name="Cancel" />
								<input type="button" value="Back" onclick="backProcess()"
									class="white" name="Back" />
								<!-- <a
								href="" class="btn_sm white">Cancel</a> <a
								href="../pgiController/callPaymentMethod.html"
								class="btn_sm white">Back</a> --> </span>
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

</body>
</html>