<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${metaTitle}</title>
<meta name="robots" content="index, follow">
<%-- <c:if test="${empty staticContent}">
<meta name="robots" content="noindex, follow"> 
</c:if> --%>
<meta name="description" content="${metaDesc}"> 
<link href="${canonicalUrl}" rel="canonical" />
<jsp:include page="common/include.jsp" />
<script src="<%=request.getContextPath()%>/resources/js/FB.Share" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/in.js" type="text/javascript"></script>
<script  src="<%=request.getContextPath()%>/resources/js/widgets.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.dataTables.nightly.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/searchResultsdatatable.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-ui.min.js"></script>
</head>

<body class="job_board">
	<div class="ad_page_top">
		${adPageTop }
	</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">
			<div class="main">
				<jsp:include page="../templates/templates_header.jsp"></jsp:include>
				 <!-- <a href="../pgiController/callPaymentMethod.html" class="btn_sm white">Payment Order</a>  -->
				<jsp:include page="jobboardsearchresultsHeader.jsp"></jsp:include>
				<div class="searchContent" style="display: block;" id="tableContent">
					<jsp:include page="jobboardsearchresultsBody.jsp"></jsp:include>
					<br class="clearfix" />
					</div>
				<br class="clearfix" />
				<c:if test="${not empty staticContent}">
				<div class="Content_Description">
						${staticContent}
					</div>
					</c:if>
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
	
	
</body>
</html>