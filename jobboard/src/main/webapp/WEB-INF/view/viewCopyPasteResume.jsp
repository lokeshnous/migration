<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />
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
				<form action="" method="get" commandName="createResume"
					id="viewresumeId">
					<div class="clearfix"></div>
					<!--Start:MidContant-->
					<div class="MidContent_Wrapper floatLeft">
						<div class="popupHeader Padding0  OrangeBG">
							<h2>VIEW YOUR RESUME</h2>
							<span class="floatRight marginRight10"><a
								href="<%=request.getContextPath()%>/jobSeeker/jobSeekerDashBoard.html"
								class="link_color3_emphasized FontSize12 FontWeight">Back to
									Dashboard</a></span>
						</div>


						<div class="clearfix"></div>
						<div class="MidContent_Wrapper FloatLeft marginBottom10">
							<div class="ResumeHeader">
								<%-- <h2 class="noTopBottomBorder "><c:out
										value="${createResume.resumeName}" /></h2> --%>
								<div class="clearfix"></div>
							</div>
						</div>
						<div class="rowEvenNewSpacing">
							<h2 class="noTopBottomBorder ">${createResume.resumeName}</h2>
							<br> <span>
								<h3 class="marginTop3">
									<c:out value="${createResume.desiredJobTitle}" />
								</h3>
							</span>


							<div class="clearfix"></div>
							<span> <!--  <p class="marginTop3">Available 08/01/2012</p> -->
							</span>
							<div class="IconsArea">
								<a href="${pageContext.request.contextPath}/employer/downloadResume.html?resumeId=${createResume.uploadResumeId}" title="Download"><div
										class="download"></div></a>&nbsp; <a
									href="${pageContext.request.contextPath}/employer/printResume.html?resumeId=${createResume.uploadResumeId}" title="Print" target="_blank"><div
										class="printOrange"></div></a>
							</div>


						</div>
						<!-- <div class="IconsArea">
								<a href="#"><div class="download"></div></a>&nbsp; <a href="#"><div class="printOrange"></div></a>
							</div> -->
					</div>
					<div class="rowEvenNewSpacing">
						<h2 class="noTopBottomBorder ">Resume:</h2>
						<br> </span> <br>
						<div class="clearfix"></div>
						<textarea readonly="readonly" cols="120" rows="50">${createResume.resumeText}</textarea>
						<!-- class="textareaBoxCResume Height255 marginTop5 " -->
					</div>
					<div class="IconsArea">
								<a href="${pageContext.request.contextPath}/employer/downloadResume.html?resumeId=${createResume.uploadResumeId}" title="Download"><div
										class="download"></div></a>&nbsp; <a
									href="${pageContext.request.contextPath}/employer/printResume.html?resumeId=${createResume.uploadResumeId}" title="Print" target="_blank"><div
										class="printOrange"></div></a>
					</div>
			</div>
			</form>
			<!--Start:MidContant-->
			<div class="clearfix"></div>
			<!-- content_wrapper -->
			<div class="ad_wrapper">${adPageBottom}</div>
			<!-- ad_wrapper -->

		</div>

		<!-- main -->

	</div>
	<!-- end main_wrapper_inside -->
	</div>
	<!-- end main_wrapper_outside -->
	<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
</body>
</html>