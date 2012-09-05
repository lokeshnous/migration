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
</script>
<script type="text/javascript">
		    function cancelProcess(){
		    	//window.location.href = '';
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

					<form:form action="paymentMethod.html"
						enctype="multipart/form-data" method="POST" class="firstForm"
						commandName="paymentGatewayForm">
						<div class="row">
							<p class="gateway_section_head">Payment Method</p>
							<p>
								<form:radiobutton path="paymentMethod" value="creditCard"
									checked="true" />
								Credit Card
							</p>
							<br />
							<p>
								<form:radiobutton path="paymentMethod" value="invoice" />
								Invoice
							</p>
						</div>
						<form:errors path="paymentMethod" />
						<div class="buttonContainer">
							<span class="floatLeft"><input type="submit"
								class="orange" value="Continue to Next Step" />
								<input type="button" value="Cancel" onclick="cancelProcess()"
									class="orange" name="Cancel" />
								
								<!--  <a
								href="" class="btn_sm orange">Cancel</a> --></span>
						</div>

					</form:form>

				</div>
				<!-- gateway -->
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