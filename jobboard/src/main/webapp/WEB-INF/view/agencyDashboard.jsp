<%@page import="com.advanceweb.afc.jb.common.util.MMJBCommonConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />
<script type="text/javascript" src="../resources/js/highcharts.js"></script>
<script type="text/javascript" src="../resources/js/funnel.src.js"></script>
<script type="text/javascript" src="../resources/js/exporting.src.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css">
<!-- JAVASCRIPT FILES -->
		<script type="text/javascript" src="../resources/js/jquery.cycle.all.min.js"></script>
		<script type="text/javascript" src="../resources/js/slider.js"></script>
		<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.js"></script>
<script type="text/javascript">
	jQuery(document).ready(
			function() {
				$("#changePassword").displaypopup("#changePassword", "790",
						"370");
				$("#accountSettingpopUp").displaypopup("#accountSettingpopUp",
						"790", "360");
				$("#createEmployerpopup").displaypopup("#createEmployerpopup",
						"790", "350");
				$("#manageEmployers").displaypopup("#manageEmployers", "750",
						"350");
				$(".employerMetrics").displaypopup(".employerMetrics", "750",
						"350");
				$("#accessPermissioPopUp").displaypopup(
						"#accessPermissioPopUp", "770", "360");
				$("#modifySubs").displaypopup("#modifySubs", "770", "360");
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
				<div class="clearfix"></div>
				<!--Start:MidContant-->
				<div class="MidContent_Wrapper floatLeft">
					<div class="dashboardHeader">
						<h1>
							[<%=(String) session.getAttribute(MMJBCommonConstants.COMPANY_EMP)%>] Dashboard
						</h1>
					</div>
					<div class="MidContent_Wrapper FloatLeft">
						<div class="dashboardcolumn1">
							<div class="dashboardPanal">
								<div class="dashboardPanalIconHolder profile">
									<img src="../resources/images/tranBg.png" width="30"
										height="30" alt="User Profile">
								</div>

								<div class="dashboardPanalAGCcontent ">
									<h2 class="noTopBorder">Profile Management</h2>
									<div class="lableTextDashBoard">
										<p>
											<a
												href="<%=request.getContextPath()%>/jobseekerregistration/jobSeekerChangePassword.html"
												id="changePassword">${msg.jsChangePwd}</a>
										</p>
									</div>
									<div class="lableTextDashBoard">
										<p>
											<a
												href="<%=request.getContextPath()%>/agency/viewEmpAccountProfile.html"
												id="accountSettingpopUp">Account Settings</a>
										</p>
									</div>
									<security:authorize
										access="!hasRole('ROLE_FACILITY_FULL_ACCESS') and !hasRole('ROLE_FACILITY_POST_EDIT') ">
										<input type="hidden" name="pageValue" value="agePermPage" />
										<div class="lableTextDashBoard">
											<p>
												<a id="accessPermissioPopUp"
													href="<%=request.getContextPath()%>/employer/manageAccessPermission.html?page=agePermPage">Manage
													Access Permissions</a>

											</p>
										</div>
									</security:authorize>
								</div>
							</div>
							<!---->
							<div class="dashboardPanal">
								<div class="dashboardPanalIconHolder subscriptions">
									<img src="../resources/images/tranBg.png" width="30"
										height="30" alt="Subscription">
								</div>

								<div class="dashboardPanalAGCcontent ">
									<h2 class="noTopBorder">Current Subscriptions</h2>
									<div>
										<c:forEach items="${currentSubs}" var="subscription"
											varStatus="index">
											<tr>
												<td><c:out value="${subscription.optionName}" /></td>
											</tr>
											<br />
										</c:forEach>
									</div>
									<div class="lableTextDashBoard">
										<p>
											<a id="modifySubs"
												href="<%=request.getContextPath()%>/subscriptions/modifyFacilitySubscriptions.html">Modify
												Subscriptions</a>
										</p>
									</div>
								</div>
							</div>
							<!---->
							<div class="dashboardPanal">
								<div class="dashboardPanalIconHolder Solutions">
									<img src="../resources/images/tranBg.png" width="30"
										height="30" alt="Subscription">
								</div>

								<div class="dashboardPanalAGCcontent ">
									<h2 class="noTopBorder">Media</h2>


									<div class="lableTextDashBoard">
										<p>
											<a target="_blank" href="${msg.agencyMediaKitURL}"><em>ADVANCE</em>
												Recruitment Solutions Media Kit</a>
										</p>
									</div>
								</div>
							</div>
						</div>
						<!--Right-->
						<div class="dashboardcolumn2">
							<div class="dashboardPanal">
								<div class="dashboardPanalIconHolder resume">
									<img src="../resources/images/tranBg.png" width="30"
										height="30" alt="Resume">
								</div>

								<div class="dashboardPanalAGCcontent">
									<h2 class="noTopBorder">Employers</h2>

									<c:forEach items="${emplyrsByState}" var="assocEmplyrsName"
										varStatus="status">
										<div class="lableTextDashBoard">
											<h3 class="TextColor01">${assocEmplyrsName.key}</h3>
										</div>
										<div class="lableTextDashBoard">
											<c:forEach items="${assocEmplyrsName.value}"
												var="emplyrsName" varStatus="emplyrsStatus">
												<p>
													<a class="employerMetrics"
														href="<%=request.getContextPath()%>/agency/showFacilityMetrics.html?facilityId=${emplyrsName.facilityId}">${emplyrsName.name}</a>
												</p>
											</c:forEach>
										</div>
										<br>
										<br>
										<br>
									</c:forEach>
									<div class="rowEvenTB10Spacing"></div>
									<div class="rowEvenTB10Spacing"></div>
									<div class="row">
										<a
											href="<%=request.getContextPath()%>/agency/getAddFacilityPopup.html"
											id="createEmployerpopup" class="btn_sm orange cursor">ADD
											EMPLOYER</a><a
											href="<%=request.getContextPath()%>/agency/getManageFacilityPopup.html"
											id="manageEmployers" class="btn_sm orange cursor">MANAGE
											EMPLOYERS</a>
									</div>
								</div>

							</div>
							<!---->

							<!---->

						</div>
					</div>

					<div class="clearfix"></div>

					<!---->

					<div class="clearfix"></div>
					<!---->

					<!---->

					<!---->

					<!---->

					<!---->

					<!---->

					<!---->

					<!---->

					<!---->

					<!---->

					<!---->
				</div>

				<!--Start:MidContant-->
				<div class="clearfix"></div>
				<!-- content_wrapper -->
				<div class="ad_wrapper">
					${adPageBottom}
				</div>
				<!-- ad_wrapper -->

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