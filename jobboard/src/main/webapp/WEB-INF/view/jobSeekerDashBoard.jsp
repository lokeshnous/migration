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
<link href="../resources/css/jquery.dataTables.css" rel="stylesheet"
	type="text/css">
<link href="../resources/css/jquery-ui.css" rel="stylesheet"
	type="text/css">
<link href="../resources/css/jobsearchResults.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" language="javascript"
	src="/media/js/jquery.js"></script>
<script src="../resources/js/jquery.dataTables.nightly.js"></script>
<script src="../resources/js/searchResultsdatatable.js"></script>

<script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>
<!-- <link rel="stylesheet" type="text/css"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" /> -->
	<script type="text/javascript">
jQuery(document).ready(
		function() {
			val=<%= request.getParameter("totalProgress") %>;
			if(val!=null){
			alert("You have completed "+ val+ "% of information.");	
			}
			//jQuery(".megamenu").megamenu();
			$("#viewappliedjob").displaypopup("#viewappliedjob", "775",
					"355");
			$("#viewsavedjob").displaypopup("#viewsavedjob", "780", "370");

			$("#changePassword").displaypopup("#changePassword", "780",
					"370");
			$("#manageResumePopup").displaypopup("#manageResumePopup",
					"775", "252");
			$("#createResumePopup").displaypopup("#createResumePopup",
					"775", "252");
			$("#editProfileId")
					.displaypopup("#editProfileId", "790", "252");
			$("#modifySubs").displaypopup("#modifySubs", "775", "252");
			$("#viewMySavedSearchesId").displaypopup(
					"#viewMySavedSearchesId", "775", "252");
		});
</script>
</head>

<body class="job_board">
	<div class="ad_page_top">
		<img src="../resources/images/ads/banner_ad_fpo.png" />
	</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">
			<div class="main">
				<jsp:include page="../templates/templates_header.jsp"></jsp:include>
				<jsp:include page="jobboardsearchresultsHeader.jsp"></jsp:include>
				<div class="searchContent" style="display: none;">
					<jsp:include page="jobboardsearchresultsBody.jsp"></jsp:include>
					<br class="clearfix" />
				
				<div class="otherContent">
				<div class="ad_col_right">
					<img class="marginTop10"
						src="../resources/images/ads/300x250ad2.png" />
					<div class="follow_us">
						<h2>Follow Us</h2>
						<p>Stay connected to the latest jobs.</p>
						<a href="${followuplinkfacebook}" target="_blank">
							<div class="social facebook_link">Facebook</div>
						</a> <a href="${followuplinktwitter}" target="_blank">
							<div class="social twitter_link">Twitter</div>
						</a> <a href="${followuplinkyoutube}" target="_blank">
							<div class="social youTube_link">YouTube</div>
						</a> <a href="${followuplinklinkedin}" target="_blank">
							<div class="last social linkedIn_link">LinkedIn</div>
						</a>
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
													target="_blank" id="viewsavedjob">${msg.jsViewSavedjobs}&nbsp;${msg.commonOpenBrace}<c:out
														value="${jobSeekerDashBoardForm.savedJobsCount}" />${msg.commonCloseBrace}
												</a>
											</p>
										</div>

										<div class="lableTextDashBoard">
											<p>
												<a href="<%=request.getContextPath()%>/jobSeekerJobDetail/viewAppliedJob.html"
													target="_blank" id="viewappliedjob">${msg.jsViewJobsApplied}&nbsp;${msg.commonOpenBrace}<c:out
														value="${jobSeekerDashBoardForm.appliedJobsCount}" />${msg.commonCloseBrace}
												</a>
											</p>
										</div>
										<div class="lableTextDashBoard">
											<p>
												<a href="">${msg.jsEmployersViewedProfile}</a>
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
												<a href="">${msg.jsManageExistingCoverLetters}</a>
											</p>

										</div>
										<div class="lableTextDashBoard">
											<p>
												<a href="">${msg.jsCreateNewCoverLetter}</a>
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




			</form:form>
				</div>
				</div>
				<br class="clearfix" />
				
				<div class="ad_wrapper">
					<img src="../resources/images/ads/banner_ad_fpo.png" />
				</div>
				</div>
				
			</div>
			<!-- main -->
			</div>

		<!-- end main_wrapper_inside -->

	<!-- end main_wrapper_outside -->
	<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
</body>
</html>