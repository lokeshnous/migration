<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${metaTitle}</title>
<meta name="description" content="${metaDesc}" />
<link href="${canonicalUrl}" rel="canonical" />
<jsp:include page="common/include.jsp" />

<!-- <title>ADVANCE Heathcare Jobs</title> -->

<!-- JAVASCRIPT FILES -->
<script language="javascript" type="text/javascript" src="../resources/js/slider.js"></script>
<link href="../resources/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<!-- <script language="javascript" type="text/javascript" src="/media/js/jquery.js"></script> -->
<script language="javascript" type="text/javascript" src="../resources/js/jquery.dataTables.nightly.js"></script>
<script language="javascript" type="text/javascript" src="../resources/js/searchResultsdatatable.js"></script>
<script language="javascript" type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>

  <script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script> 
</head>

<body class="job_board">
<div class="ad_page_top">${adPageTop}</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">
			<div class="main">
				<jsp:include page="../templates/templates_header.jsp" />
				<div class="headerLoginSection">
					<div class="headerLoginSectionColumns">
						
						<div class="floatRight">
							<span class="floatLeft"> <a href="<%=request.getContextPath()%>/logout.html">${msg.commonLogOut}</a>
							</span>
						</div>
					</div>
					
				</div>
				<div>
				  <h1 style="color:red">Error occurred while processing your request. please try again.</h1>
				</div>
				<br/><br/><br/><br/><br/>
				
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