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

<script type="text/javascript">
	jQuery(document).ready(
			function() {
				$("#changePassword").displaypopup("#changePassword", "790",
						"370");
				$("#accountSettingpopUp").displaypopup("#accountSettingpopUp",
						"790", "360");
				jQuery(".megamenu").megamenu();
				$("#manageBrandingTemplatePopup").displaypopup(
						"#manageBrandingTemplatePopup", "775", "252");
				$("#postingInventory").displaypopup("#postingInventory", "790",
						"370");
				$("#purchaseJobPostings").displaypopup("#purchaseJobPostings",
						"790", "360");
				$("#accessPermissioPopUp").displaypopup(
						"#accessPermissioPopUp", "770", "360");
				$("#setAlertPopUp")
						.displaypopup("#setAlertPopUp", "770", "360");
				$("#viewAlertPopUp").displaypopup("#viewAlertPopUp", "770",
						"360");
				$("#manageFacilityPopUp").displaypopup("#manageFacilityPopUp", "770",
				"360"); 

			});
	window.onload = function() {
		loadMetricsDetails();
	};
	function loadMetricsDetails(){
		$.ajaxSetup({ cache: false });
		$.ajax({
			url : '../employer/metricsDetails.html',
			data : ({}),
			
			success : function(data) {
			$("#metricsDetails").html(data);
			},
			error : function(data) {
				alert('Unable to process');
			},
			complete : function(data) {
				// do nothing for now.
			}
		}
		);
	}
	function changeMetrics(){
		var selEmployerId = $("#selEmployer").val();
		$.ajax({url:"${pageContext.request.contextPath}/employer/viewEmployerMetrics.html?selEmployerId="+selEmployerId,
			data:$('#selEmployerId').serialize(),
			type:"GET",
			success: function(data) {			
		loadMetricsDetails();
			 },
				error : function(data) {
					alert('Unable to process');
				}
		});
	}
</script>
<script type="text/javascript" src="javascripts/expandCollapse.js"></script>
</head>

<body class="job_board">

	<form:form commandName="employerDashBoardForm" id="empDashBoard">
		<div class="EmployerDashboardHeader">
			<h1><%=(String) session.getAttribute("userName")%>
				Dashboard
			</h1>
		</div>
		<div class="MidContent_Wrapper FloatLeft">
			<div class="dashboardColumns">
				<div class="dashboardPanal">
					<div class="profile">
						<img src="../resources/images/tranBg.png" width="30" height="30"
							alt="User Profile">
					</div>

					<div class="dashboardPanalcontent marginTop5">
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
									href="<%=request.getContextPath()%>/employerRegistration/viewEmpAccountProfile.html"
									id="accountSettingpopUp">Account Settings</a>
							</p>
						</div>
						<%-- <c:if
							test="${enableAccess == 'true' && enablePostEditAccess == 'true'}"> --%>
							<security:authorize access="!hasRole('ROLE_FACILITY_FULL_ACCESS') and !hasRole('ROLE_FACILITY_POST_EDIT') ">
							<div class="lableTextDashBoard">
								<p>
									<a id="accessPermissioPopUp"
										href="<%=request.getContextPath()%>/employer/manageAccessPermission.html">Manage
										Access Permissions</a>
								</p>
							</div>
							</security:authorize>
						<%-- </c:if> --%>
						<div class="lableTextDashBoard">
							<p>
								<a
									href="<%=request.getContextPath()%>/empProfile/employerprofile.html">Manage
									Featured Employer Profile</a>
							</p>
						</div>
						<security:authorize access="hasRole('ROLE_FACILITY_GROUP')">
						<div class="lableTextDashBoard">
							<p>
								<a id="manageFacilityPopUp"
									href="<%=request.getContextPath()%>/facility/updateFacilityDetail.html">Manage Facility List</a>
							</p>
						</div>
						</security:authorize>
						<div class="FormErrorDisplayText">
							${error}<br /> <br />
						</div>
					</div>
				</div>
				<!---->
				<div class="dashboardPanal">
					<div class="jobPosting">
						<img src="../resources/images/tranBg.png" width="30" height="30"
							alt="Job Posting">
					</div>
					<div class="dashboardPanalcontent marginTop5">
						<h2 class="noTopBorder">Job Posting</h2>
						<%-- <c:if test="${ enablePostEditAccess eq 'true'}"> --%>
						<security:authorize access="!hasRole('ROLE_FACILITY_POST_EDIT')">
							<div class="lableTextDashBoard">
								<p>
									<a id="purchaseJobPostings"
										href="<%=request.getContextPath()%>/purchaseJobPosting/purchaseJobPostings.html">Purchase
										Job Postings</a>
								</p>
							</div>
							</security:authorize>
						<%-- </c:if> --%>
						<div class="lableTextDashBoard">
							<p>
								<a
									href="<%=request.getContextPath()%>/employer/postNewJobs.html">Post
									New Job</a>
							</p>
						</div>
						<div class="lableTextDashBoard">
							<p>
								<a
									href="<%=request.getContextPath()%>/employer/manageJobPost.html">Manage
									/ Edit Job Postings</a>
							</p>
						</div>
						<div class="lableTextDashBoard">
							<p>


								<%-- 								<a href="<%=request.getContextPath()%>/brandingTemplates/manageBrandingTemplate.html" id="branding">Manage Job Posting Branding Templates</a> --%>

								<a
									href="<%=request.getContextPath()%>/brandingTemplates/employer/manageBrandingTemplate.html"
									id="manageBrandingTemplatePopup">Manage Job Posting
									Branding Templates</a>


							</p>
						</div>
						<div class="lableTextDashBoard">
							<p>
								<a
									href="<%=request.getContextPath()%>/inventory/employer/jobInventory.html"
									id="postingInventory">Job Posting Inventory</a>
							</p>
						</div>
					</div>
				</div>
				<!---->
				<div class="dashboardPanal">
					<div class="Applicants">
						<img src="../resources/images/tranBg.png" width="30" height="30"
							alt="User Profile">
					</div>
					<div class="dashboardPanalcontent marginTop5">
						<h2 class="noTopBorder">Manage Applicants</h2>
						<security:authorize access="!hasRole('ROLE_FACILITY_POST_EDIT')">
					<%-- 	<c:if test="${enablePostEditAccess eq 'true'}"> --%>
							<div class="lableTextDashBoard">
								<p>
									<a href="">Purchase Resume Search Packages</a>
								</p>
							</div>
							</security:authorize>
						<%-- </c:if> --%>
						<div class="lableTextDashBoard">
							<p>
								<a href="">Manage Job-Seekers</a>
							</p>
						</div>
						<div class="lableTextDashBoard">
							<p>
								<a href="">My Saved Resume Searches</a>
							</p>
						</div>
					</div>
				</div>
				<!---->
				<div class="dashboardPanal">
					<div class="subscriptions">
						<img src="../resources/images/tranBg.png" width="30" height="30"
							alt="Subscription">
					</div>
					<div class="dashboardPanalcontent marginTop5">
						<h2 class="noTopBorder">Current Subscriptions</h2>
						<div class="lableTextDashBoard">
							<p>E-newsletters</p>
						</div>
						<div class="lableTextDashBoard">
							<p>E-mailer</p>
						</div>
						<div class="lableTextDashBoard">
							<p>
								<a href="">Modify Subscriptions</a>
							</p>
						</div>
					</div>
				</div>
				<!---->

			</div>
			<!--Right-->
			<div class="dBEmpRightColumns BorderLeft">
				<div class="dashboardPanal">
					<div>
						<div class="activity">
							<img src="../resources/images/tranBg.png" width="30" height="30">
						</div>
						<div class="empDBPanalTablecontent">
							<!--T-->
							<div class="rowEvenNewSpacing marginTop10">
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									class="grid marginTop3">
									<thead>
										<tr class="borderTopNone">
											<th width="46%" align="left" scope="col"><h2
													class="HeadTopBottomBorder">Metrics</h2> 
												<form:select
													class="jb_input3" path="selEmployer" items="${downDTOs}"
													itemValue="optionId" itemLabel="optionName" onchange="changeMetrics();">
												</form:select>
											</th>
											<th width="18%" align="center" scope="col"
												class="BorderLeftWhite"><div class="EDPrice">VIEWS</div></th>
											<th width="18%" align="center" scope="col"
												class="BorderLeftWhite"><div class="EDPriceA">CLICKS</div></th>
											<th width="18%" align="center" scope="col"
												class="BorderLeftWhite"><div class="EDPriceB">APPLIES</div></th>
										</tr>
									</thead>
									</table>
									<br/>
									<div id="metricsDetails" ></div>								
							</div>
							<!--T-->
							<div class="rowBox EDPricec">
								<div class="floatLeft marginTop3">
									<strong>&nbsp;&nbsp;&nbsp;Date range</strong>
								</div>
								<div class="floatLeft marginTop3">&nbsp;&nbsp;&nbsp;From:</div>
								<div class="floatLeft">
									<div class="floatLeft">
										<input type="text" name="firstName" class="EDTextBox" />
									</div>
									<div class="calender">
										<a href="#"><img src="../resources/images/tranBg.png"
											width="14" height="14" alt="Datepick"></a>
									</div>
								</div>
								<div class="floatLeft marginTop3 marginLeft25">To:</div>
								<div class="floatLeft">
									<div class="floatLeft">
										<input type="text" name="firstName" class="EDTextBox" />
									</div>
									<div class="calender">
										<a href="#"><img src="../resources/images/tranBg.png"
											width="14" height="14" alt="Datepick"></a>
									</div>
								</div>
								<div class="EDBox01 marginLeft25">
									<strong>SHOW</strong>
								</div>
								<div class="floatLeft marginTop5 marginLeft15">
									<a href="#">Export</a>
								</div>
							</div>
							<!--T-->
							<div class="rowBox">
								<div class="rowBox Padding0 AutoWidth AutoHeight">
									<div class="EDBoxMinW">
										<div class="EDBox02">
											<div class="row borderBottomDotted Height25">
												<p class="floatLeft">Available Job Postings</p>
												<p class="floatRight TextAlignR">3</p>
											</div>
											<div class="row marginTop10">
												<p class="floatLeft">Active Job Postings</p>
												<p class="floatRight TextAlignR">3</p>
											</div>
										</div>
									</div>
									<div class=" clearfix"></div>
									<span class="FloatLeft"><a href="">View Individual
											Job Posting Stats</a></span>
								</div>
								<div class="rowBox marginLeft25 Padding0 AutoWidth AutoHeight">
									<img src="../resources/images/EmpDimg.png" width="250"
										height="208" alt="img">
								</div>
							</div>

						</div>
					</div>
					<div class=" rowBox Padding0">
						<div class="innerColumn marginRight10">
							<div class="dashboardPanal ">
								<div class="Alerts">
									<img src="../resources/images/tranBg.png" width="30"
										height="30" alt="Subscription">
								</div>
								<div class="dashboardPanalcontentTwo">
									<div class="dashboardPanalcontentTwo marginTop5">
										<h2 class="noTopBorder">Alerts</h2>
										<div class="lableTextDashBoard ">
											<p>
												<a
													href="<%=request.getContextPath()%>/alerts/employer/viewAlerts.html"
													id="viewAlertPopUp">View Alerts</a>
											</p>
										</div>
										<div class="lableTextDashBoard ">
											<p>
												<a
													href="<%=request.getContextPath()%>/alerts/employer/setAlerts.html"
													id="setAlertPopUp">Set Alerts</a>
											</p>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="innerColumn ">
							<div class="dashboardPanal ">
								<div class="Solutions">
									<img src="../resources/images/tranBg.png" width="30"
										height="30" alt="Subscription">
								</div>
								<div class="dashboardPanalcontentTwo marginTop5">
									<h2 class="noTopBorder">Solutions</h2>
									<div class="lableTextDashBoard">
										<p>
											<a href=""><em>ADVANCE </em>Recruitment Solutions Media
												Kit</a>
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!---->

			<!---->

		</div>
	</form:form>
</body>
</html>