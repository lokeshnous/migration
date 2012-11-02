<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="common/include.jsp" />
<title>ADVANCE Healthcare Jobs</title>
<!-- Common js files  -->
<script type="text/javascript" src="../resources/js/common/common.js"></script>

<script type="text/javascript">
	jQuery(document)
			.ready(
					function() {
						$("#tb_compare_job_seeker img")
								.click(
										function(event) {
											var action = $(this).attr("alt");
											var rowObj = $(this).parent()
													.parent().parent();
											var resumeId = $(rowObj).attr("id");
											switch (action) {
											case "delete": {
												if ($("#totalRecordForCompId")
														.val() > 2) {
													if (confirm("Are you sure you want to delete?")) {

														$("#manageJobSeeker")
																.attr(
																		"action",
																		"${pageContext.request.contextPath}/employer/removeCompareResume.html?resumeId="
																				+ resumeId);
														$("#manageJobSeeker")
																.attr("method",
																		"POST");
														$("#manageJobSeeker")
																.submit();
														return true;
													} else {
														return false;
													}
												} else {
													alert("Can not delete the job seeker as for comparision we need atleast two job seeker details");
												}
											}

											}

										});
						$('#moveToFolder').live("click", function() {
							var rowObj = $(this).parent().parent();
							var resumeId = $(rowObj).attr("id");
							var val = [];
								val[0] = resumeId;
							if (val != "") {						
								$('#selectedRow').val(val);
								$('#moveToFolderPopup').attr("href","${pageContext.request.contextPath}/employer/moveToFolder.html?folderId=0&selectedVal="+val);	
								$.nmManual($('#moveToFolderPopup').attr("href"));
							} 
						});
						jQuery(".megamenu").megamenu();
					});
	function sendResumeToFrd(resumeId,resumeName,context) {	
		var currentUrl = window.location.pathname;
		$.nmManual(context+'/employer/sendtofriend.html?id='+resumeId+'&resumeName='+resumeName+'&currentUrl='+currentUrl);
	}
</script>
<script type="text/javascript" src="../resources/js/expandCollapse.js"></script>
</head>

<body class="job_board">
	<form:form commandName="manageJobSeekerForm" id="manageJobSeeker">
		<form:hidden path="selectedRow" id="selectedRow" />
		<form:hidden path="totalRecordForComp" id="totalRecordForCompId" />
		<form:hidden path="folderId" id="folderId" />
		<div class="ad_page_top">
			<img src="../resources/images/ads/banner_ad_fpo.png" />
		</div>
		<div class="main_wrapper_outside">
			<div class="main_wrapper_inside">


				<div class="main">
					<jsp:include page="../templates/templates_header.jsp"></jsp:include>
					<div class="clearfix"></div>
					<!--nav-->

					<!-- ad_col_right -->
					<!-- content_wrapper -->
					<div class="popupHeader Padding0  OrangeBG marginBottom5">
						<h2>COMPARE JOB-SEEKERS</h2>
						<span class="floatRight marginRight10"><a href="<%=request.getContextPath()%>/employer/manageJobSeeker.html?folderId=-1"
							class="link_color3_emphasized FontSize12 FontWeight">Back to
								Manage Job-Seeker</a></span>
					</div>
					
					<div class="row marginTop20 marginBottom15">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="grid marginTop3" id="tb_compare_job_seeker">
							<tr class="borderTopNone Com">
								<td width="20%" height="45" align="left" valign="bottom"><h2
										class="noTopBorder noTopBottomBorder">Details</h2></td>
								<c:forEach items="${manageJobSeekerForm.resumeDTOList}"
									var="jobSeeker" varStatus="status">
									<td width="20%" height="55" align="center"
										class="BorderLeftWhite" id="${jobSeeker.uploadResumeId}"><div
											class="EDPrice bold FontSize14" style="height:45px;">
											${jobSeeker.contactInfoDTO.firstName} &nbsp;
											${jobSeeker.contactInfoDTO.lastName}<br /> <a
												href="${pageContext.request.contextPath}/employer/viewResume.html?resumeId=${jobSeeker.uploadResumeId}"><img
												src="../resources/images/View.png" width="20" height="20"
												alt="view"></a>&nbsp;<a href="#" id="moveToFolder"><img
												src="../resources/images/Folder.png" width="20" height="20"
												alt="folder"></a>&nbsp;<a href="${pageContext.request.contextPath}/employer/printResume.html?resumeId=${jobSeeker.uploadResumeId }"><img
												src="../resources/images/Print2.png" width="20" height="20"
												alt="print"></a>&nbsp;<a onclick="sendResumeToFrd(${jobSeeker.uploadResumeId}, '${jobSeeker.resumeName}','<%= request.getContextPath() %>')"><img
												src="../resources/images/EmailOrange.png" width="20"
												height="20" alt="email"></a>&nbsp;<a href="#"><img
												src="../resources/images/Delete.png" width="20" height="20"
												alt="delete">&nbsp;</a>
										</div></td>
								</c:forEach>

							</tr>
							<tr class="gridB Height30">
								<td align="left" valign="middle">Desired Job Title</td>
								<c:forEach items="${manageJobSeekerForm.resumeDTOList}"
									var="jobSeeker" varStatus="status">
									<td align="center" valign="middle" class="BorderLeft">${jobSeeker.desiredJobTitle}</td>
								</c:forEach>
							</tr>
							<tr class="gridB Height30">
								<td align="left" valign="middle">Current Job Title</td>
								<c:forEach items="${manageJobSeekerForm.resumeDTOList}"
									var="jobSeeker" varStatus="status">
									<td align="center" valign="middle" class="BorderLeft">${jobSeeker.listWorkExpDTO[0].jobTitle}</td>
								</c:forEach>
							</tr>
							<tr class="gridB Height30">
								<td align="left" valign="middle">Years in Position</td>
								<c:forEach items="${manageJobSeekerForm.resumeDTOList}"
									var="jobSeeker" varStatus="status">
									<td align="center" valign="middle" class="BorderLeft">
										${jobSeeker.listWorkExpDTO[0].yrsAtPostion} <c:if
											test="${not empty  jobSeeker.listWorkExpDTO[0].yrsAtPostion}">years</c:if>
									</td>
								</c:forEach>
							</tr>
							<tr class="gridB Height30">
								<td align="left" valign="middle">Current Career Level</td>
								<c:forEach items="${manageJobSeekerForm.resumeDTOList}"
									var="jobSeeker" varStatus="status">
									<td align="center" valign="middle" class="BorderLeft">${jobSeeker.listWorkExpDTO[0].currentCareerLvl}</td>
								</c:forEach>
							</tr>
							<tr class="gridB Height30">
								<td align="left" valign="middle">Field of Study</td>
								<c:forEach items="${manageJobSeekerForm.resumeDTOList}"
									var="jobSeeker" varStatus="status">
									<td align="center" valign="middle" class="BorderLeft">${jobSeeker.listEduDTO[0].fieldOfStudy}</td>
								</c:forEach>
							</tr>
							<tr class="gridB Height30">
								<td align="left" valign="middle">Degree Level</td>
								<c:forEach items="${manageJobSeekerForm.resumeDTOList}"
									var="jobSeeker" varStatus="status">
									<td align="center" valign="middle" class="BorderLeft">${jobSeeker.listEduDTO[0].degreeLvl}</td>
								</c:forEach>
							</tr>
							<tr class="gridB Height30">
								<td align="left" valign="middle">Desired Employment Type</td>
								<c:forEach items="${manageJobSeekerForm.resumeDTOList}"
									var="jobSeeker" varStatus="status">
									<td align="center" valign="middle" class="BorderLeft">${jobSeeker.desiredEmploymentType}</td>
								</c:forEach>
								</td>
							</tr>
							<tr class="gridB Height30">
								<td align="left" valign="middle">Work Authorization</td>
								<c:forEach items="${manageJobSeekerForm.resumeDTOList}"
									var="jobSeeker" varStatus="status">
									<td align="center" valign="middle" class="BorderLeft">${jobSeeker.workAuthorizationUS}</td>
								</c:forEach>
							</tr>
							<tr class="gridB Height30">
								<td align="left" valign="middle">Willing to Relocate?</td>
								<c:forEach items="${manageJobSeekerForm.resumeDTOList}"
									var="jobSeeker" varStatus="status">
									<td align="center" valign="middle" class="BorderLeft">${jobSeeker.willingToRelocate}</td>
								</c:forEach>
							</tr>
						</table>
					</div>
					<a href="" id="moveToFolderPopup" style="display:none;">Move</a>
					<!--Start:MidContant-->
					<div class="clearfix"></div>
					<!-- content_wrapper -->
					<div class="ad_wrapper">
						<img src="../resources/images/ads/banner_ad_fpo.png" />
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
	</form:form>
</body>
</html>