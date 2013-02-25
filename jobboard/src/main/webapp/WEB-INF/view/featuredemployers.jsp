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

<!-- JAVASCRIPT FILES -->
<script type="text/javascript" src="../resources/js/slider.js"></script>
<link href="../resources/css/jquery-ui.css" rel="stylesheet"
	type="text/css">
<!-- <script type="text/javascript" language="javascript"
	src="/media/js/jquery.js"></script> -->
<script src="../resources/js/jquery.dataTables.nightly.js"></script>
<script src="../resources/js/searchResultsdatatable.js"></script>

<script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>
<!-- <link rel="stylesheet" type="text/css"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" /> -->

</head>

<body class="job_board">
	<div class="ad_page_top">
		${adPageTop }
	</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">
			<div class="main">
				<jsp:include page="../templates/templates_header.jsp"></jsp:include>
				<jsp:include page="jobboardsearchresultsHeader.jsp"></jsp:include>
				<div class="searchContent" style="display: none;"  id="tableContent">
					<jsp:include page="jobboardsearchresultsBody.jsp"></jsp:include>
					<br class="clearfix" />
				</div>
				<div class="otherContent marginTop5">
				<div class="row ">
					<div class="sectionHeader marginTop15">
						<h2>Featured Employers</h2>
					</div>
					<ul class="employersList">
					
						<c:forEach var="companyProfileDTO" items="${companyProfileDTOList}" >
						<li><a href="featuredemployerdetails.html?id=${companyProfileDTO.facilityid }"><img src="<%=request.getContextPath()%>/healthcare/viewImage.html?id=${companyProfileDTO.logoPath}" alt="${companyProfileDTO.companyName }"></a></li>
						</c:forEach>
					</ul>
					
				</div>
				</div>
				<br class="clearfix" />
				
				<div class="ad_wrapper">
					${adPageBottom}
				</div>
				
				
			</div>
			<!-- main -->
			</div>

		<!-- end main_wrapper_inside -->

	<!-- end main_wrapper_outside -->
	<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
</body>
</html>