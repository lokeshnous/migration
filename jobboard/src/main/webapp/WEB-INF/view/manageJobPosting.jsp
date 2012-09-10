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

<!-- JQUERY LIBRARY -->
<script src="../resources/js/jquery-1.7.1.js"></script>
<script src="../resources/js/jquery-1.7.1.min.js"></script>
<script src="../resources/jquery.nyroModal/js/popup.js"></script>
<script type="text/javascript"
	src="../resources/js/jquery.cycle.all.min.js"></script>
<script type="text/javascript" src="../resources/js/slider.js"></script>
<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>
<script type="text/javascript" src="../resources/js/jquery.tablesorter.js"></script> 

<!-- js files for modalpopup------------------------------------------------- -->
<script
	src="../resources/jquery.nyroModal/js/jquery.nyroModal.custom.js"></script>
<script
	src="../resources/jquery.nyroModal/js/jquery.nyroModal.custom.min.js"></script>
<link href="../resources/jquery.nyroModal/styles/nyroModal.css"
	rel="stylesheet" type="text/css">

<style type="text/css" media="screen">
@import
	url("${pageContext.request.contextPath}/resources/jquery.nyroModal/styles/nyroModal.css")
	;
</style>
<!-- -------------------------------------------------------------------------- -->
<script type="text/javascript">
	jQuery(document)
			.ready(
					function() {
						  $("#tb_manage_job").tablesorter(); 
						$('#deactivated').click(function() {
							var val = [];
							$(':checkbox:checked').each(function(i) {
								val[i] = $(this).val();
							});
							if (val != "") {
								$('#selectedRow').val(val);
								$("#deactivateHidden").click();
							} else {
								alert("Please select a job");
							}

						});
						$('#repost').click(function() {
							var val = [];
							$(':checkbox:checked').each(function(i) {
								val[i] = $(this).val();
							});
							if (val != "") {
								$('#selectedRow').val(val);
								$("#repostHidden").click();
							} else {
								alert("Please select a job");
							}
						});
						$('#delete')
								.click(
										function() {
											var val = [];
											$(':checkbox:checked').each(
													function(i) {
														val[i] = $(this).val();
													});
											if (val == "") {
												alert("Please select a job");
											}

											$('#selectedRow').val(val);
											if (val != ""
													&& confirm("Are you sure you want to delete?")) {
												$('#statusValue')
														.val(
																$(
																		'#statusValue')
																		.val())
												$("#deleteHidden").click();
											}

										});
						$('#repost_lower').click(function() {
							var val = [];
							$(':checkbox:checked').each(function(i) {
								val[i] = $(this).val();
							});
							if (val != "") {
								$('#selectedRow').val(val);
								$("#repostHidden").click();
							} else {
								alert("Please select a job");
							}
						});
						$('#delete_lower')
								.click(
										function() {
											var val = [];
											$(':checkbox:checked').each(
													function(i) {
														val[i] = $(this).val();
													});
											if (val == "") {
												alert("Please select a job");
											}

											$('#selectedRow').val(val);
											if (val != ""
													&& confirm("Are you sure you want to delete?")) {
												$('#statusValue')
														.val(
																$(
																		'#statusValue')
																		.val())
												$("#deleteHidden").click();
											}

										});
						$('#deactivated_lower').click(function() {
							var val = [];
							$(':checkbox:checked').each(function(i) {
								val[i] = $(this).val();
							});
							if (val != "") {
								$('#selectedRow').val(val);
								$("#deactivateHidden").click();
							} else {
								alert("Please select a job");
							}
						});
						$("#tb_manage_job img")
								.click(
										function(event) {
											var action = $(this).attr("alt");
											var val = 0;
											switch (action) {
											case "check": {
												val = $(this).attr("id");

												$("form")
														.attr(
																"action",
																"${pageContext.request.contextPath}/employer/updateJobs.html?jobId=${job.jobId}"
																		+ val);
												$("form")
														.attr("method", "POST");
												$("form").submit();

											}
												break;
											}

										});
						$('#statusValue').change(
								function() {
									val = $(this).val();
									$("form").attr(
											"action",
											"${pageContext.request.contextPath}/employer/manageJobPost.html?jobStatus="
													+ val);
									$("form").submit();
								});
						$('#noOfPage').change(
								function() {
									val = $(this).val();
									$('#noOfPageLower').val(val);
									$("form").attr(
											"action",
											"${pageContext.request.contextPath}/employer/manageJobPost.html?jobStatus="
													+ val);
									$("form").submit();
								});
						$('#noOfPageLower').change(
								function() {
									val = $(this).val();
									$('#noOfPage').val(val);
									$("form").attr(
											"action",
											"${pageContext.request.contextPath}/employer/manageJobPost.html?jobStatus="
													+ val);
									$("form").submit();
								});
					});
</script>

<script src="../resources/js/jquery.dataTables.nightly.js"></script>
<script src="../resources/js/searchResultsdatatable.js"></script>

<script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>
</head>
<body class="job_board">
	<form:form action="updateJobs.html" commandName="jobPostForm">
	<form:hidden path="beginVal"/>
		<div class="ad_page_top">
			<img src="../resources/images/ads/banner_ad_fpo.png" />
		</div>

		<div class="main_wrapper_outside">
			<div class="main_wrapper_inside">


				<div class="main">
				<jsp:include page="../templates/templates_header.jsp"></jsp:include>
					<div class="clearfix"></div>
					<!--nav-->
					<div class="popupHeader Padding0  OrangeBG marginBottom5">
						<h2>MANAGE / EDIT JOB POSTINGS</h2>
						<span class="floatRight marginRight10"><a
							href="<%=request.getContextPath()%>/employer/employerDashBoard.html"
							class="link_color3_emphasized FontSize12 FontWeight">Back to
								Dashboard</a></span>
					</div>
					<div class="clearfix"></div>
					<div class="manageJobPostingNavigation">
						<div class="searchResultsNavigationColumn1">

							<!--Added Class "marginTop5"-->
							<span>Results viewable:</span> <span class="Padding0"> <form:select
									path="noOfPage" name="results" class="jb_input4 margin0">
									<form:option value="20">20</form:option>
									<form:option value="30">30</form:option>
									<form:option value="40">40</form:option>
									<form:option value="50">50</form:option>
								</form:select>
							</span>
							<!--Added Class "marginTop5"-->
							<span>per page</span>
						</div>
						<div class="searchResultsNavigationColumn3">&nbsp;&nbsp;&nbsp;
						</div>
						<div class="searchResultsNavigationColumn2 floatRight">
							<!-- <span>Page:</span> -->
							<%--For displaying Previous link except for the 1st page --%>
							<c:if test="${currentPage != 1 && noOfPages gt 10}">
								<td><a href="<%=request.getContextPath()%>/employer/manageJobPost.html?page=${currentPage - 1}&jobStatus=${statusValue}&next=${begin-10}"> <img
										src="../resources/images/ArrowLeft.png"> Previous</a></td>
							</c:if>



							<c:forEach begin="${begin}" end="${noOfPages}" var="i">
								<c:choose>
									<c:when test="${currentPage eq i}">
										
											<span class="active">${i}</span>
										
									</c:when>
									<c:otherwise>
										<span class="active"> <c:if test="${i lt begin+10}">
												<a
													href="<%=request.getContextPath()%>/employer/manageJobPost.html?page=${i}&jobStatus=${jobPostForm.statusValue}">${i}</a>
											</c:if></span>

									</c:otherwise>
								</c:choose>
							</c:forEach>
							
								<span>
								<c:if test="${noOfPages gt 10}"><a
									href="<%=request.getContextPath()%>/employer/manageJobPost.html?page=${currentPage + 1}&jobStatus=${jobPostForm.statusValue}&next=${begin+10}">Next <img
										src="../resources/images/ArrowRight.png">
								</a></c:if></span>
							



						</div>
					</div>


					<!--button-->
					<div class="row">
						<span> <a href="#" id="repost" value="REPOST" name="REPOST"
							class="btn_sm white jb_search_submit">REPOST</a> <a href="#"
							id="deactivated" value="DEACTIVATED" name="DEACTIVATED"
							class="btn_sm white jb_search_submit">DEACTIVATED</a> <a href="#"
							id="delete" value="DELETE" name="DELETE"
							class="btn_sm white jb_search_submit">DELETE</a> <a
							href="/jobboard/employer/postNewJobs.html"
							class="btn_sm white jb_search_submit">POST NEW JOB</a>

							<div class="floatRight marginTop15">
								<span class=" FloatLeft marginTop5">View by Job Status</span>

								<form:select path="statusValue"
									class="jb_input3  margin0 width150 marginLeft5" name="select9">

									<form:option value="" label="--- Job Status ---" />
									<form:options items="${jobStatusList}" />

								</form:select>


							</div>
						</span>
					</div>
					<div class="clearfix"></div>
					<br>
					<c:if test="${not empty errorMessage}">
						<div id="errmsg" style="color: red" align="left">
							<c:out value="${errorMessage}"></c:out>
						</div>
					</c:if>
					<div class="row marginTop10 FontSize11">
						<form:hidden path="selectedRow" id="selectedRow" />
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="grid" id="tb_manage_job">
							<thead>
							<tr class="LightGrayBG Height35">
								<th width="2%" align="center" valign="middle" class="">&nbsp;</th>
								<th width="6%" align="center" valign="middle" class="FontSize11">Job
										ID</th>
								<th width="18%" align="center" valign="middle" class="FontSize11"><strong>Job
										Title</strong></th>
								<th width="11%" align="center" valign="middle" class="FontSize11"><strong>Location</strong></th>
								<th width="8%" align="center" valign="middle" class="FontSize11"><strong>Job<br />
										Status
								</strong></th>
								<th width="7%" align="center" valign="middle" class="FontSize11"><strong>Start<br />
										Date
								</strong></th>
								<th width="7%" align="center" valign="middle" class="FontSize11"><strong>End<br />
										Date
								</strong></th>
								<th width="5%" align="center" valign="middle" class="FontSize11"><strong>Views</strong></th>
								<th width="4%" align="center" valign="middle" class="FontSize11"><strong>Clicks</strong></th>
								<th width="5%" align="center" valign="middle" class="FontSize11"><strong>Applies</strong></th>
								<th width="7%" align="center" valign="middle" class="FontSize11"><strong>Auto<br />
										Renew
								</strong></th>
								<th width="11%" align="center" valign="middle" class="FontSize11"><strong>Job<br />
										Template
								</strong></th>
								<th width="9%" align="center" valign="middle" class="FontSize11"><strong>Actions</strong></th>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${jobPostForm.jobPostDTOList}" var="job"
								varStatus="status">
								<form:hidden path="jobPostDTOList[${status.index}].jobId" />
								<tr class="Height35">
									<td align="center" valign="middle"><input type="checkbox"
										name="checkbox" id=${job.jobId } value="${job.jobId}"></td>
									<td align="center" valign="middle"><a
										href="<%=request.getContextPath()%>/employer/editJob.html?jobId=${job.jobId}">${job.jobId}</a></td>
									<td align="center" valign="middle"><a
										href="<%=request.getContextPath()%>/employer/editJob.html?jobId=${job.jobId}">${job.jobTitle}</a></td>
									<td align="center" valign="middle">${job.location}</td>
									<td align="center" valign="middle">${job.jobStatus}</td>
									<td align="center" valign="middle">${job.startDt}</td>
									<td align="center" valign="middle">${job.endDt}</td>
									<td align="center" valign="middle">${job.views}</td>
									<td align="center" valign="middle">${job.clicks}</td>
									<td align="center" valign="middle">${job.applies}</td>
									<td align="center" valign="middle"><form:select
											path="jobPostDTOList[${status.index}].autoRenew"
											id="selectAutoRenew"
											class="jb_input3 select100 marginTopBottom0 FontSize10 width50"
											name="select1">
											<form:options items="${autoRenewList}" itemLabel="optionId"
												itemValue="optionName" />
										</form:select>
									<td align="center" valign="middle"><form:select
											path="jobPostDTOList[${status.index}].brandTemplate"
											id="selectTemplate"
											class="jb_input3 select100 marginTopBottom0 width87 FontSize10"
											name="select">
											<form:option value="0" label="Select One" />
											<form:options items="${templateList}" itemLabel="optionName"
												itemValue="optionId" />
										</form:select></td>
									<td align="center" valign="middle"><div
											class="row width80">
											<a
												href="<%=request.getContextPath()%>/employer/editJob.html?jobId=${job.jobId}"><div
													class="editFile"></div></a><a
												href="<%=request.getContextPath()%>/employer/editJob.html?jobId=${job.jobId}&readOnly=true"><div
													class="view"></div></a><a href="#"><div class="check"
													id="${job.jobId}"></div></a>
										</div></td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="row FloatLeft">
						<span><a href="#" id="repost_lower" value="REPOST"
							name="REPOST" class="btn_sm white jb_search_submit">REPOST</a> <a
							href="#" id="deactivated_lower" value="DEACTIVATED"
							name="DEACTIVATED" class="btn_sm white jb_search_submit">DEACTIVATED</a>
							<a href="#" id="delete_lower" value="DELETE" name="DELETE"
							class="btn_sm white jb_search_submit">DELETE</a> <a
							href="/jobboard/employer/postNewJobs.html"
							class="btn_sm white jb_search_submit">POST NEW JOB</a> <input
							type="submit" id="repostHidden" value="REPOST" name="REPOST"
							class="btn_sm white" style="visibility: hidden;" /><input
							type="submit" id="deactivateHidden" value="DEACTIVATED"
							name="DEACTIVATED" class="btn_sm white"
							style="visibility: hidden;" /> <input type="submit"
							id="deleteHidden" value="DELETE" name="DELETE"
							class="btn_sm white" style="visibility: hidden;" />
					</div>
					<div class="clearfix"></div>
					<div class="manageJobPostingNavigation ">
						<div class="searchResultsNavigationColumn1">

							<!--Added Class "marginTop5"-->
							<span>Results viewable:</span> <span class="Padding0"><form:select
									path="noOfPageLower" name="results" class="jb_input4 margin0">
									<form:option value="20">20</form:option>
									<form:option value="30">30</form:option>
									<form:option value="40">40</form:option>
									<form:option value="50">50</form:option>
								</form:select> </span>
							<!--Added Class "marginTop5"-->
							<span>per page</span>
						</div>
						<div class="searchResultsNavigationColumn3">&nbsp;&nbsp;&nbsp;
						</div>
						<div class="searchResultsNavigationColumn2 floatRight">
							<!-- <span>Page: </span> -->
							
							<%--For displaying Previous link except for the 1st page --%>
							<c:if test="${currentPage != 1 && noOfPages gt 10}">
								<td><a href="<%=request.getContextPath()%>/employer/manageJobPost.html?page=${currentPage - 1}&jobStatus=${statusValue}&next=${begin-10}"> <img
										src="../resources/images/ArrowLeft.png"> Previous</a></td>
							</c:if>



							<c:forEach begin="${begin}" end="${noOfPages}" var="i">
								<c:choose>
									<c:when test="${currentPage eq i}">
										
											<span class="active">${i}</span>
										
									</c:when>
									<c:otherwise>
										<span class="active"> <c:if test="${i lt begin+10}">
												<a
													href="<%=request.getContextPath()%>/employer/manageJobPost.html?page=${i}&jobStatus=${jobPostForm.statusValue}">${i}</a>
											</c:if></span>

									</c:otherwise>
								</c:choose>
							</c:forEach>
							
								<span>
								<c:if test="${noOfPages gt 10}"><a
									href="<%=request.getContextPath()%>/employer/manageJobPost.html?page=${currentPage + 1}&jobStatus=${jobPostForm.statusValue}&next=${begin+10}">Next <img
										src="../resources/images/ArrowRight.png">
								</a></c:if></span>
						</div>
					</div>
				</div>

				<!--Start:MidContant-->
				<div class="clearfix"></div>
				<!-- content_wrapper -->
				<div class="ad_wrapper">
					<img src="images/ads/banner_ad_fpo.png" />
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