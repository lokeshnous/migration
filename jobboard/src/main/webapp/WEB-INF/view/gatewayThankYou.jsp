<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="common/include.jsp" />
<title>ADVANCE Heathcare Jobs</title>

<link href="../resources/css/Gateway.css" rel="stylesheet"
	type="text/css">

<!-- JAVASCRIPT FILES -->
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<!-- <script type="text/javascript"
	src="../resources/js/jquery.cycle.all.min.js"></script> -->
<script type="text/javascript" src="../resources/js/slider.js"></script>
<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery(".megamenu").megamenu();
	});
</script>
</head>

<body class="job_board">
	<div class="ad_page_top">
		${adPageTop}
	</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">
			<div class="main">
				<jsp:include page="../templates/templates_header.jsp"></jsp:include>
				<div class="gateway">
					<h2>Order Process</h2>
					<h3 class="gatewayBreadcrumbs">
						<span class="nextStep">Billing and Payment >> Confirm Order
							>></span> Order Completed >></span>
					</h3>
					<form:form action="../pgiController/callThankYouPage" method="POST"
						class="firstForm">
						<div class="FormErrorDisplayText">
							${errorMessage}
						</div>
						<c:if test="${empty errorMessage}">
							<div class="row">
								<p class="gateway_section_head">Thank you for your payment!</p>
								<p>Your order is now complete. Click the button below to continue.</p>
							</div>
						</c:if>
						
						<div class="buttonContainer">
							<c:if test="${statusCode == '400'}">
								<span class="floatLeft"><a href="<%=request.getContextPath()%>/pgiController/backToConfirmOrder.html" class="btn_sm orange">Return</a></span>
							</c:if>
							<c:if test="${statusCode == '200'}">
								<span class="floatLeft"><a href="<%=request.getContextPath()%>/employer/employerDashBoard.html" class="btn_sm orange">CONTINUE</a></span>
							</c:if>
						</div>
					</form:form>
				</div>
				<!-- gateway -->
				<div class="clearfix"></div>
				<div class="ad_wrapper">
					${adPageBottom }
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