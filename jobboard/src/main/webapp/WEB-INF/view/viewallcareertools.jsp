<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Advance Health care job</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<jsp:include page="common/include.jsp" />
<link href="../resources/css/jquery-ui.css" rel="stylesheet"
	type="text/css">
	</head>
	<body class="job_board">
<div class="ad_page_top">${adPageTop}</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">
			<div class="main">
				<jsp:include page="../templates/templates_header.jsp" />
				<h2 class="more_link">
			Career Tools
		</h2> 
				<div class="column1">
		${careerstoolresource}
	</div>
				<br class="clearfix" />
				
				<div class="ad_wrapper">${adPageBottom}</div>


			</div>
			<!-- main -->
		</div>

		<!-- end main_wrapper_inside -->

		<!-- end main_wrapper_outside -->
		<jsp:include page="../templates/templates_footer.jsp" />
</body>
</html>