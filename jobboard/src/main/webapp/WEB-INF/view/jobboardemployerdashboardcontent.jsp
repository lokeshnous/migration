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
				$("#changePassword").displaypopup("#changePassword", "780",
						"370");
				$("#accountSettingpopUp").displaypopup("#accountSettingpopUp",
						"770", "360");
				jQuery(".megamenu").megamenu();
				$("#manageBrandingTemplatePopup").displaypopup(
						"#manageBrandingTemplatePopup", "775", "252");
				$("#postingInventory").displaypopup("#postingInventory", "780",
						"370");
				$("#purchaseJobPostings").displaypopup("#purchaseJobPostings","770","360");		
			});
</script>
<script type="text/javascript" src="javascripts/expandCollapse.js"></script>
</head>

<body class="job_board">


		<div class="dashboardHeader">
			<h1 >[Employer Name] Dashboard</h1>
		</div>
		<div class="MidContent_Wrapper FloatLeft">
			<div class="dashboardColumns">
				<div class="dashboardPanal">
					<div class="dashboardPanalIconHolder">
						<img src="../resources/images/UserProfile.jpg" width="30"
							height="30" alt="User Profile">
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
						<div class="lableTextDashBoard">
							<p>
								<a href="">Manage Access Permissions</a>
							</p>
						</div>
						<div class="lableTextDashBoard">
							<p>
								<a
									href="<%=request.getContextPath()%>/empProfile/employerprofile.html">Manage
									Featured Employer Profile</a>
							</p>
						</div>
					</div>
				</div>
				<!---->
				<div class="dashboardPanal">
					<div class="dashboardPanalIconHolder">
						<img src="../resources/images/posting.png" width="30" height="30"
							alt="User Profile">
					</div>
					<div class="dashboardPanalcontent marginTop5">
						<h2 class="noTopBorder">Job Posting</h2>
						<div class="lableTextDashBoard">
							<p>
								<a id="purchaseJobPostings" href="<%=request.getContextPath()%>/purchaseJobPosting/purchaseJobPostings.html">Purchase Job Postings</a>
							</p>
						</div>
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
					<div class="dashboardPanalIconHolder">
						<img src="../resources/images/ManageApplicants.png" width="30"
							height="30" alt="User Profile">
					</div>
					<div class="dashboardPanalcontent marginTop5">
						<h2 class="noTopBorder">Manage Applicants</h2>
						<div class="lableTextDashBoard">
							<p>
								<a href="">Purchase Resume Search Packages</a>
							</p>
						</div>
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
					<div class="dashboardPanalIconHolder">
						<img src="../resources/images/Subscriptions.jpg" width="30"
							height="30" alt="Subscription">
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
				<div class="empDBPanalTableIcon">
					<div class="dashboardPanalIconHolder width8P">
						<img src="../resources/images/Activity.jpg" width="30" height="30"
							alt="Resume">
					</div>
					</div>
					<div class="empDBPanalTablecontent">
						<!--T-->
						<div class="rowEvenNewSpacing marginTop10">
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								class="grid marginTop3">
								<thead>
									<tr class="borderTopNone">
										<th width="46%" align="left" scope="col"><h2
												class="noTopBorder noTopBottomBorder">Metrics</h2></th>
										<th width="18%" align="center" scope="col"
											class="BorderLeftWhite"><div class="EDPrice">VIEWS</div></th>
										<th width="18%" align="center" scope="col"
											class="BorderLeftWhite"><div class="EDPriceA">CLICKS</div></th>
										<th width="18%" align="center" scope="col"
											class="BorderLeftWhite"><div class="EDPriceB">APPLIES</div></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${jbPostTotalList}" var="jobList">
										<tr class="gridB">
											<td><input name="radio2" type="radio" id="radio4"
												value="radio" class="marginLeft10 marginRight10"> <label
												for="radio2">${jobList.getMetricsName()}</label></td>
											<td align="center" valign="middle" class="BorderLeft TcolorA">${jobList.getViews()}</td>
											<td align="center" valign="middle" class="BorderLeft TcolorB">${jobList.getClicks()}</td>
											<td align="center" valign="middle" class="BorderLeft TcolorC">${jobList.getApplies()}</td>
										</tr>
									</c:forEach>
								</tbody>
								<!-- <tr class="gridB">
									<td><input name="radio2" type="radio" id="radio4"
										value="radio" class="marginLeft10 marginRight10"> <label
										for="radio2">Your Averages Per Job Posting</label></td>
									<td align="center" valign="middle" class="BorderLeft TcolorA">200</td>
									<td align="center" valign="middle" class="BorderLeft TcolorB">20</td>
									<td align="center" valign="middle" class="BorderLeft TcolorC">2</td>
								</tr>
								<tr class="gridB">
									<td class="TBorderNone"><input name="radio2" type="radio"
										id="radio4" value="radio" class="marginLeft10 marginRight10">
										<label for="radio2">Site-wide Averages Per Job Posting</label></td>
									<td align="center" valign="middle"
										class="BorderLeft TcolorA TBorderNone">300</td>
									<td align="center" valign="middle"
										class="BorderLeft TcolorB TBorderNone">30</td>
									<td align="center" valign="middle"
										class="BorderLeft TcolorC TBorderNone">3</td>
								</tr> -->
							</table>
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
					<div class=" rowBox Padding0">
					<div class="innerColumn marginRight10">
						<div class="dashboardPanal ">
							<div class="dashboardPanalIconHolder ">
								<img src="../resources/images/Alerts.png" width="30" height="30"
									alt="Subscription">
							</div>
							<div class="dashboardPanalcontentTwo">
							<div class="dashboardPanalcontentTwo marginTop5">
								<h2 class="noTopBorder">Alerts</h2>
								<div class="lableTextDashBoard ">
									<p>
										<a href="">View Alerts</a>
									</p>
								</div>
								<div class="lableTextDashBoard ">
									<p>
										<a href="">Set Alerts</a>
									</p>
								</div>
							</div>
							</div>
						</div>
						</div>
						<div class="innerColumn ">
						<div class="dashboardPanal ">
							<div class="dashboardPanalIconHolder">
								<img src="../resources/images/media.png" width="30" height="30"
									alt="Subscription">
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

</body>
</html>