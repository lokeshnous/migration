<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.advanceweb.afc.jb.common.util.MMJBCommonConstants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<jsp:include page="common/include.jsp" />
<title>ADVANCE Heathcare Jobs</title>

<!-- JAVASCRIPT FILES -->
<script type="text/javascript" src="../resources/js/slider.js"></script>
<!-- commenting this because its creating problem in auto complete drop down of edit profile settings screen 
<link href="../resources/css/jquery-ui.css" rel="stylesheet" type="text/css"> -->
<script language="javascript" type="text/javascript" src="../resources/js/jquery.dataTables.nightly.js"></script>
<script language="javascript" type="text/javascript" src="../resources/js/searchResultsdatatable.js"></script>

<script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="../resources/js/jquery-ui.js"></script>
<!-- <link rel="stylesheet" type="text/css"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" /> -->
	<script type="text/javascript">
jQuery(document).ready(
		function() {
			var uploadStatus = <%= request.getAttribute("uploadStatus")%>
			if(uploadStatus){
				alert("Resume uploaded successfully")
			}
			
			//jQuery(".megamenu").megamenu();
			$("#viewappliedjob").displaypopup("#viewappliedjob", "790", "350");
			$("#viewsavedjob").displaypopup("#viewsavedjob", "790", "350");

			$("#changePassword").displaypopup("#changePassword", "790",
					"370");
			$("#manageResumePopup").displaypopup("#manageResumePopup",
					"790", "350");
			$("#createResumePopup").displaypopup("#createResumePopup",
					"790", "350");
			$("#editProfileId")
					.displaypopup("#editProfileId", "790", "252");
			$("#modifySubs").displaypopup("#modifySubs", "790", "252");
			$("#viewMySavedSearchesId").displaypopup(
					"#viewMySavedSearchesId", "790", "252");
			$("#createCoverLett").displaypopup(
					"#createCoverLett", "400", "350");
			$("#manageCoverLett").displaypopup(
					"#manageCoverLett", "400", "350");
			
			$("#profileViewCount").displaypopup(
					"#profileViewCount", "400", "350");
			$("#retainThisJobId").displaypopup(
					"#retainThisJobId", "790", "252");
			var status = <%= session.getAttribute(MMJBCommonConstants.RETAIN_SEARCH)%>
			if(status)
			{
				$("#retainThisJobId").click();
			}
		});
</script>
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
				<div class="searchContent" style="display: none;" id="tableContent">
					<jsp:include page="jobboardsearchresultsBody.jsp"></jsp:include>
					<br class="clearfix" />
				</div>
				<div class="otherContent">
				<div class="ad_col_right">
				<div id="adPageRightMiddle"> ${adPageRightMiddle} </div>
					<div class="follow_us">
						<h2>Follow Us</h2>
						<p>Stay connected to the latest jobs.</p>
						<a href="${followuplinkfacebook}" target="_blank"><span class="social facebook_link">Facebook</span></a>
						<a href="${followuplinktwitter}" target="_blank"><span class="social twitter_link">Twitter</span></a>
						<a href="${followuplinkyoutube}" target="_blank"><span class="social youTube_link">YouTube</span></a>
						<a href="${followuplinklinkedin}" target="_blank"><span class="last social linkedIn_link">LinkedIn</span></a>
					</div>
					<br class="clearfix" />
				</div>
				<!-- ad_col_right -->
				<div class="content_columns">
				<form:form commandName="jobSeekerDashBoardForm" id="jsdashboard">
							<div class="dashboardHeader">
								<div class="advanceDashBoardHeader"><img src="../resources/images/myadvance.png" width="288"
									height="23" alt="My advance  dashboard"></div>
							</div>
							<div class="dashboardcolumn1">
								<div class="dashboardPanal">
									<div class="profile">
										<img src="../resources/images/tranBg.png" width="30"
											height="30" alt="User Profile">
									</div>


									<div class="dashboardPanalcontent">
										<h2 class="noTopBorder">${msg.jsUserProfile}</h2>
										<div class="lableTextDashBoard">
											<p>
												<a
													href="<%=request.getContextPath()%>/jobseekerregistration/viewJobSeekerProfile.html"
													id="editProfileId">${msg.jsEditProfileSettings}</a>
											</p>
										</div>
										<div class="lableTextDashBoard" id="jsChangePassword">
											<p>
												<a
													href="<%=request.getContextPath()%>/jobseekerregistration/jobSeekerChangePassword.html"
													id="changePassword">${msg.jsChangePwd}</a>
											</p>

										</div>

									</div>
								</div>
								<div class="dashboardPanal">
									<div class="search">
										<img src="../resources/images/tranBg.png" width="30"
											height="30" alt="Search">
									</div>

									<div class="dashboardPanalcontent">
										<h2 class="noTopBorder">${msg.jsSavedSearches}</h2>
										<div class="lableTextDashBoard">
											<p>
												<a href="<%=request.getContextPath()%>/savedSearches/viewMySavedSearches.html"
													id="viewMySavedSearchesId">${msg.jsViewSavedSearches}&nbsp;${msg.commonOpenBrace}<c:out
														value="${jobSeekerDashBoardForm.savedSearchCount}" />${msg.commonCloseBrace}
												</a>
											</p>
										</div>
										<div class="lableTextDashBoard">
											<p>
												<a href="../jobsearch/findJobPage.html">${msg.jsCreateNewSavedSearch}</a>
											</p>
										</div>
										<div class="lableTextDashBoard">
											<p>${msg.jsSavedSearchInfo}</p>
										</div>


									</div>
								</div>
								<div class="dashboardPanal">
									<div class="activity">
										<img src="../resources/images/tranBg.png" width="30"
											height="30" alt="Activity">
									</div>

									<div class="dashboardPanalcontent">
										<h2 class="noTopBorder">${msg.jsActivity}</h2>
										<div class="lableTextDashBoard">
											<p>
												<a href="<%=request.getContextPath()%>/jobSeekerJobDetail/viewSavedJob.html"
													id="viewsavedjob">${msg.jsViewSavedjobs}&nbsp;${msg.commonOpenBrace}<c:out
														value="${jobSeekerDashBoardForm.savedJobsCount}" />${msg.commonCloseBrace}
												</a>
											</p>
										</div>

										<div class="lableTextDashBoard">
											<p>
												<a href="<%=request.getContextPath()%>/jobSeekerJobDetail/viewAppliedJob.html"
													id="viewappliedjob">${msg.jsViewJobsApplied}&nbsp;${msg.commonOpenBrace}<c:out
														value="${jobSeekerDashBoardForm.appliedJobsCount}" />${msg.commonCloseBrace}
												</a>
											</p>
										</div>
										<div class="lableTextDashBoard">
											<p>
												 <a href="<%=request.getContextPath()%>/jobSeekerJobDetail/viewResumeCount.html"
													id="profileViewCount">${msg.jsEmployersViewedProfile} </a> 
											</p>
										</div>

									</div>
								</div>


							</div>

							<div class="dashboardcolumn2">
								<div class="dashboardPanal">
									<div class="resume">
										<img src="../resources/images/tranBg.png" width="30"
											height="30" alt="Resume">
									</div>

									<div class="dashboardPanalcontent">
										<h2 class="noTopBorder">${msg.jsResumeCoverLetters}</h2>
										<div class="lableTextDashBoard">
											<p>
												<span class="link_color2_selected">${msg.jsResumes}</span>
											</p>

										</div>
										<div class="lableTextDashBoard">
											<p>
												<a href="<%=request.getContextPath()%>/jobSeekerResume/manageResume.html"
													id="manageResumePopup">${msg.jsManageExistingResumes}</a>
											</p>
										</div>
										<div class="lableTextDashBoard">
											<p>
												<a
													href="<%=request.getContextPath()%>/jobSeekerResume/createResumePopUp.html?resumeType=createResume"
													id="createResumePopup">${msg.jsCreateNewResume}</a>
											</p>
										</div>
										<div class="lableTextBLineDashBoard">

											<p>${msg.jsResumeInfo}</p>
										</div>
										<div class="lableTextDashBoard">
											<p>
												<span class="link_color2_selected">${msg.jsCoverLetters}</span>
											</p>
										</div>
										<div class="lableTextDashBoard">
											<p>
												<a href="<%=request.getContextPath()%>/jobSeekerCoverLetter/manageExistProfile.html?resumeType=manageCover" id="manageCoverLett">${msg.jsManageExistingCoverLetters}</a> 
												
											</p>
											
										</div>
										<div class="lableTextDashBoard">
											<p>
											<a href="<%=request.getContextPath()%>/jobSeekerCoverLetter/createCoverLetter.html?resumeType=createCover" id="createCoverLett">${msg.jsCreateNewCoverLetter} </a> 											
											</p>
										</div>
										<div class="lableTextDashBoard">
											<p>${msg.jsCoverLetterInfo}</p>
										</div>

									</div>
								</div>
								<div class="dashboardPanal">

									<div class="subscriptions">
										<img src="../resources/images/tranBg.png" width="30"
											height="30" alt="Subscription">
									</div>

									<div class="dashboardPanalcontent">
										<h2 class="noTopBorder">My Subscriptions</h2>
										<div class="lableTextDashBoard">
											<p>
												<a id="modifySubs"
													href="<%=request.getContextPath()%>/subscriptions/modifySubscription.html">${msg.jsModifySubscriptions}</a>
											</p>
										</div>
										<div class="lableTextDashBoard">
											<div class="rowEvenSpacingMargin0">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0" class="grid2">
													<tr>
														<td valign="top">
															<table>
																<tr class="borderTopNone">
																	<th class="borderTopNone" width="46%" align="left"
																		scope="col">Current Subscriptions</th>
																</tr>
																<c:forEach items="${currentSubs}"
																	var="subscription" varStatus="index">
																	<tr>
																		<td><c:out
																				value="${subscription.optionName}" /></td>
																	</tr>
																</c:forEach>
															</table>
														</td>
														<%-- <td valign="top">
															<table>
																<tr class="borderTopNone">
																	<th class="borderTopNone" width="46%" align="left"
																		scope="col">Job Alerts</th>
																</tr>
																<c:forEach items="${jobAlertsList}" var="jobAlert"
																	varStatus="index">
																	<tr>
																		<td><c:out value="${jobAlert.alertName}" /></td>
																	</tr>
																</c:forEach>
															</table>
														</td>
														<td valign="top">
															<table>
																<tr class="borderTopNone">
																	<th class="borderTopNone" width="46%" align="left"
																		scope="col">Magazines</th>
																</tr>
																<c:forEach items="${jobMagazinesList}" var="magazine"
																	varStatus="index">
																	<tr>
																		<td><c:out value="${magazine.magazineName}" /></td>
																	</tr>
																</c:forEach>
															</table>
														</td> --%>
													</tr>
												</table>
											</div>
											<c:if test="${empty currentSubs}">
												<div class="lableTextDashBoard">
													<p>
														<span class="link_color2_selected">You currently have no active subscriptions.</span>
														Sign up now to get job alerts, healthcare news and much
														more delivered to you automatically!
														</p>
													</div>
											</c:if>
										</div>
									</div>

								</div>



							</div>



			<a class="nyroModal" href="<%=request.getContextPath()%>/savedSearches/saveSearchPopup.html" id="retainThisJobId"></a>

			</form:form>
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