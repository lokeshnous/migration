<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Healthcare Jobs</title>
<jsp:include page="common/include.jsp" />


<script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery(".megamenu").megamenu();
	});
</script>

</head>

<body class="job_board">
	<div class="ad_page_top">${adPageTop}</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">
			<div class="main">
				<jsp:include page="../templates/templates_header.jsp"></jsp:include>


				<div class="popupHeader Padding0  OrangeBG marginBottom5">
					<h2>NEWS FROM THIS EMPLOYER</h2>
					
				</div>
				<div class="row ">
					<c:forEach items="${newsDTOList}" var="newsDTO">
						<div class="BlueBoxCont">
							<a href="${newsDTO.link}" target="_blank"
								class="TextColorA02Link">
								<h3 class="TextColor01 FontSize12">${newsDTO.title}</h3>
							</a>
						</div>
					</c:forEach>

				</div>

			</div>

			<div class="clearfix"></div>
			<div class="ad_wrapper">${adPageBottom}</div>

		</div>

		<!-- main -->

	</div>
	<!-- end main_wrapper_inside -->


	<!-- end main_wrapper_outside -->
	<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
	<!-- footer_wrapper -->

</body>
</html>