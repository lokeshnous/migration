<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List;"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="common/include.jsp" />
<link href="../resources/css/Gateway.css" rel="stylesheet"
	type="text/css">
<title>ADVANCE Heathcare Jobs</title>
<script type="text/javascript">
	jQuery(document).ready(function() {
		
		jQuery(".megamenu").megamenu();
		
		$('[id^=card_number]').keypress(validateNumber);
		$('[id^=security_code]').keypress(validateNumber);
		
		$("#continueToNext").click(function(){
			$("#billingForm").attr("action","${pageContext.request.contextPath}/pgiController/confirmOrder.html");
			$("#billingForm").submit();
		});
		
		$('#firstname2').focus();
		$('[id^=zip]').keypress(validateNumber);
	});
	
	function copyAccToBillingAddr(obj) {
		var isSelected = obj.value;
		
		if (isSelected) {
			$("#firstname2").val($("#firstName").val());
			$("#lastname2").val($("#lastname").val());
			$("#streetAddress_billing1").val($("#streetAddress").val());
			$("#cityTown2").val($("#cityTown").val());
			$("#State2").val($("#state").val());
			$("#Country2").val($("#country").val());
			$("#zip2").val($("#zip").val());
			$("#phone2").val($("#phone").val());
		}
	}
	
	function validateNumber(event) {
	    var keyval = window.event ? event.keyCode : event.which;

	    if (event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 46
	     || event.keyCode == 37 || event.keyCode == 39) {
	        return true;
	    }
	    else if ( keyval < 48 || keyval > 57 ) {
	        return false;
	    }
	    else return true;
	};
</script>
</head>

<body class="job_board">
	<div class="ad_page_top">
		<img src="../resources/images/ads/banner_ad_fpo.png" />
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
					<form:form action="../pgiController/callCreditConfirmOrder.html" id="billingForm"
						method="POST" commandName="paymentGatewayForm">

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
							<form:input path="accountAddressForm.firstName" type="text"
								name="firstName" readonly="true" id="firstName"
								class="job_seeker_password textBox350" />
						</div>
						<div class="rowEvenNewSpacing">
							<span class="lableText3">Middle Name:</span>
							<form:input path="accountAddressForm.middleName" type="text"
								name="middlename" readonly="true" id="middlename"
								class="job_seeker_password textBox350" />
							<!-- <span class="required">(Optional)</span> -->
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">Last Name:</span>
							<form:input path="accountAddressForm.lastName" type="text"
								name="lastname" readonly="true" id="lastname"
								class="job_seeker_password textBox350" />
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">Email Address:</span>
							<form:input path="accountAddressForm.email" type="text"
								name="email" id="email" readonly="true"
								class="job_seeker_password textBox350 " />
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">Company</span>
							<form:input path="accountAddressForm.company" type="text"
								name="company" readonly="true" id="company"
								class="job_seeker_password textBox350 " />
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">Street Address:</span>
							<form:input path="accountAddressForm.streetAddress" type="text"
								name="streetAddress" readonly="true" id="streetAddress"
								class="job_seeker_password textBox350 " />
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">City / Town:</span>
							<form:input path="accountAddressForm.cityOrTown" type="text"
								name="cityTown" readonly="true" id="cityTown"
								class="job_seeker_password textBox350 " />
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">State / Province:</span>
							<form:input path="accountAddressForm.state" type="text"
								name="cityTown" readonly="true" id="state"
								class="job_seeker_password textBox350 " />
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">Country:</span>
							<form:input path="accountAddressForm.country" type="text"
								name="cityTown" readonly="true" id="country"
								class="job_seeker_password textBox350 " />
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">ZIP Code:</span>
							
							<form:input path="accountAddressForm.zipCode" type="text"
								name="zip" id="zip" readonly="true"
								class="job_seeker_password textBox350 " />
								
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">Phone:</span>
							<form:input path="accountAddressForm.phone" type="text"
								name="phone" readonly="true" id="phone"
								class="job_seeker_password textBox350 " />
						</div>
						<div class="clearfix"></div>
						<p class="borderBottomDotted marginBottom15">&nbsp;</p>
						<div class="clearfix"></div>
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
						<div class="validationMsg ">
							<form:errors path="billingAddressForm.fnameForBillingAddr" /> 
							<%-- <font color="red" style="padding-left: 185px"><form:errors
									path="billingAddressForm.fnameForBillingAddr" /> </font> --%>
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">Last Name:</span>
							<form:input path="billingAddressForm.lnameForBillingAddr"
								type="text" name="lastname2" id="lastname2"
								class="job_seeker_password textBox350 " />
							<span class="required">(Required)</span>
						</div>
						<div class="validationMsg ">
							
							<form:errors path="billingAddressForm.lnameForBillingAddr" /> 
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
						<div class="validationMsg ">
							
							<form:errors path="billingAddressForm.streetForBillingAddr" /> 
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">City/Town:</span>
							<form:input path="billingAddressForm.cityOrTownForBillingAddr"
								type="text" name="streetAddress_billing2" id="cityTown2"
								class="job_seeker_password textBox350 " />
							<span class="required">(Required)</span>
						</div>
						<div class="validationMsg ">
							<form:errors path="billingAddressForm.cityOrTownForBillingAddr" />
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
						<div class="validationMsg ">
							<form:errors path="billingAddressForm.stateBillingAddress" />
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
						<div class="validationMsg ">
							<form:errors path="billingAddressForm.countryForBillingAddr" />
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">ZIP Code:</span>
							<form:input path="billingAddressForm.zipCodeForBillingAddr"
								type="text" name="zip2" id="zip2"
								class="job_seeker_password textBox350 " />
							<span class="required">(Required)</span>
						</div>
						
						<div class="validationMsg ">
							<form:errors path="billingAddressForm.zipCodeForBillingAddr" />
						</div>
						<div class="rowEvenNewSpacing">
							<span class="lableText3">Phone:</span>
							<form:input path="billingAddressForm.phone" type="text" id="phone2"
								class="job_seeker_password textBox350 " />
						</div>
						<div class="clearfix"></div>
						<form:hidden path="billingAddressForm.facilityContactId" />
						<div class"clearfix"></div>
						<p class="borderBottomDotted marginBottom15">&nbsp;</p>
						
						<c:if test="${paymentGatewayForm.paymentMethod == 'ccp'}">
							<!-- credit payment info -->
						<h3 class="gatewayBreadcrumbs main_section">Payment
							Information</h3>
						<div class="rowEvenNewSpacing">
							<span class="lableText3 twoLineFormLabel">Name:<br /> <span
								class="tinyText">(as it appears on card)</span></span>
							<form:input path="creditCardInfoForm.name" type="text"
								name="Name_on_card" id="Name_on_card"
								class="job_seeker_password textBox350 " />

							<span class="required">(Required)</span>
						</div>
						<div class="validationMsg ">
							<form:errors path="creditCardInfoForm.name" /> 
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">Card Type:</span>
							<form:select path="creditCardInfoForm.cardType" name="card_type"
								id="card_type" class="jb_input3 jb_input_width3">
								<form:option value="" selected="selected">-- Select Card Type --</form:option>
								<form:option value="3">Discover</form:option>
								<form:option value="4">Master Card</form:option>
								<form:option value="5">Visa</form:option>
								<form:option value="6">American Express</form:option>
							</form:select>
							<span class="required">(Required)</span>
						</div>
						<div class="validationMsg ">
							<form:errors path="creditCardInfoForm.cardType" />
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">Card Number:</span>
							<form:input path="creditCardInfoForm.creditCardNo" type="text"
								maxlength="16" name="card_number" id="card_number"
								class="job_seeker_password textBox350 " />
							<span class="required">(Required)</span>
						</div>
						<div class="validationMsg">
							<form:errors path="creditCardInfoForm.creditCardNo" />
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">Expiration Date:</span>
							<form:select path="creditCardInfoForm.expMonth" name="expiration"
								id="expiration" class="jb_input3 jb_input_width3">
								<form:option value="" selected="selected">-- Select Month --</form:option>
								<form:option value="1">January</form:option>
								<form:option value="2">February</form:option>
								<form:option value="3">March</form:option>
								<form:option value="4">April</form:option>
								<form:option value="5">May</form:option>
								<form:option value="6">June</form:option>
								<form:option value="7">July</form:option>
								<form:option value="8">August</form:option>
								<form:option value="9">September</form:option>
								<form:option value="10">October</form:option>
								<form:option value="11">November</form:option>
								<form:option value="12">December</form:option>
							</form:select>

							<%
								List<Integer> yearsList = new ArrayList<Integer>();
									int currentYear = Calendar.getInstance().get(Calendar.YEAR);
									for (int i = 0; i < 10; i++) {
										yearsList.add(currentYear + i);

									}
							%>


							<form:select name="year" id="year"
								path="creditCardInfoForm.expYear"
								class="jb_input3 jb_input_width3 add_L_margin">
								<form:option value="" selected="selected">-- Select Year --</form:option>yearsList
								<form:option value="2012">2012</form:option>
								<form:option value="2013">2013</form:option>
								<form:option value="2014">2014</form:option>
								<form:option value="2015">2015</form:option>
								<form:option value="2016">2016</form:option>
								<form:option value="2017">2017</form:option>
								<form:option value="2018">2018</form:option>
								<form:option value="2019">2019</form:option>
								<form:option value="2020">2020</form:option>
								<form:option value="2021">2021</form:option>
								<form:option value="2022">2022</form:option>
								<form:option value="2023">2023</form:option>
								<form:option value="2024">2024</form:option>
								<form:option value="2025">2025</form:option>

								<%-- <form:option value="0" label="Select" />
								<form:options items="${yearsList}" itemValue="yearsValue"
									itemLabel="yearsValue1" />  --%>
							</form:select>
							<span class="required">(Required)</span>
						</div>
						<div class="validationMsg">
							<form:errors path="creditCardInfoForm.expMonth" /> 
							<form:errors path="creditCardInfoForm.expYear" /> 
						</div>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">Security Code:</span>
							<form:password path="creditCardInfoForm.securiyCode"
								name="security_code" id="security_code" 
								class="job_seeker_password tinyTextBox " />
							<div class="toolTip colorPkrAreaToolTip"><span class="classic">For Visa, MasterCard, and Discover this number is the last 3 digits on back of your card on the signature strip. For American Express this number is the 4 digits above card number at the right on the front of your card.</span></div>	
							<!-- <span class="floatLeft marginTop6"><img
								src="../resources/images/Tips_blue_Icon.png" /></span>  -->
								
								<span
								class="required">(Required)</span>
						</div>
						<div class="validationMsg">
							<form:errors path="creditCardInfoForm.securiyCode" />
						</div>
						
						<!-- Credit info end -->
						</c:if>
						
						<c:if test="${paymentGatewayForm.paymentMethod == 'inv'}"> 
							<h3 class="gatewayBreadcrumbs main_section">Invoice
							Information</h3>

						<div class="rowEvenNewSpacing">
							<span class="lableText3">Purchase Order Number:</span>
							<form:input path="invoiceForm.purchaseOrderNo" type="text"
								name="PO_number" id="PO_number"
								class="job_seeker_password textBox350 " />
							<span class="required">(Required)</span>
						</div>
						<div class="validationMsg">
							<form:errors path="invoiceForm.purchaseOrderNo" />
						</div>
						</c:if>
						
						<div class="clearfix"></div>
						<div class="buttonContainer">
							<span class="floatLeft">
								<a id="continueToNext" href="#" class="btn_sm orange">Continue to Next Step</a>
								<a href="<%=request.getContextPath()%>/pgiController/cancelPayment.html" class="btn_sm orange">Cancel</a>
								<a href="<%=request.getContextPath()%>/pgiController/callPaymentMethod.html" class="btn_sm orange">Back</a>	
						</div>

					</form:form>
				</div>
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