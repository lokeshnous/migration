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
						 noOfPageValue=$("#noOfPage").val();
						 $("#noOfPageId").val(noOfPageValue);
						 $("#noOfPageLowerId").val(noOfPageValue);
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
								alert("Please select a job!");
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
								alert("Please select a job!");
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
												alert("Please select a job!");
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
								alert("Please select a job!");
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
												alert("Please select a job!");
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
								alert("Please select a job!");
							}
						});
						$("#tb_manage_job img")
								.click(
										function(event) {
											var rowObj = $(this).parent();
											var action =rowObj.attr("name");
											var val = 0;
											switch (action) {
											case "check": {
												val = rowObj.attr("id");
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
						$('#noOfPageId').change(
								function() {
									val = $(this).val();
									$('#noOfPageId').val(val);
									$('#noOfPageLowerId').val(val);
									$("form").attr(
											"action",
											"${pageContext.request.contextPath}/employer/manageJobPost.html?noOfPage="
													+ val);
									$("form").submit();
								});
						$('#noOfPageLowerId').change(
								function() {
									val = $(this).val();
									$('#noOfPageId').val(val);
									$('#noOfPageLowerId').val(val);
									$("form").attr(
											"action",
											"${pageContext.request.contextPath}/employer/manageJobPost.html?noOfPage="
													+ val);
									$("form").submit();
								});
						$('#avdJobIdTab').click( function(){
							
							$('#sortBy').val('a.jobNumber');
							val = $('#noOfPageId').val();
							$("form").attr(
									"action",
									"${pageContext.request.contextPath}/employer/manageJobPost.html?noOfPage="
											+ val + "&sort=true");
							$("form").submit();
						});
						$('#jobIdTab').click( function(){
							
							$('#sortBy').val('a.jobId');
							val = $('#noOfPageId').val();
							$("form").attr(
									"action",
									"${pageContext.request.contextPath}/employer/manageJobPost.html?noOfPage="
											+ val + "&sort=true");
							$("form").submit();
						});
							$('#jobTitleTab').click( function(){
							
							$('#sortBy').val('a.jobtitle');
							val = $('#noOfPageId').val();
							$("form").attr(
									"action",
									"${pageContext.request.contextPath}/employer/manageJobPost.html?noOfPage="
											+ val + "&sort=true");
							$("form").submit();
						});
							/* $('#jobLocationTab').click( function(){
								
								$('#sortBy').val('jobId');
								val = $('#noOfPageId').val();
								$("form").attr(
										"action",
										"${pageContext.request.contextPath}/employer/manageJobPost.html?noOfPage="
												+ val);
								$("form").submit();
							}); */
							$('#jobStatusTab').click( function(){
								
								$('#sortBy').val('a.jobStatus');
								val = $('#noOfPageId').val();
								$("form").attr(
										"action",
										"${pageContext.request.contextPath}/employer/manageJobPost.html?noOfPage="
												+ val + "&sort=true");
								$("form").submit();
							});
							$('#jobStartDateTab').click( function(){
								
								$('#sortBy').val('a.startDt');
								val = $('#noOfPageId').val();
								$("form").attr(
										"action",
										"${pageContext.request.contextPath}/employer/manageJobPost.html?noOfPage="
												+ val + "&sort=true");
								$("form").submit();
							});
							$('#jobEndDateTab').click( function(){
								
								$('#sortBy').val('a.endDt');
								val = $('#noOfPageId').val();
								$("form").attr(
										"action",
										"${pageContext.request.contextPath}/employer/manageJobPost.html?noOfPage="
												+ val + "&sort=true");
								$("form").submit();
							});
							$('#jobCompanyTab').click( function(){
								
								$('#sortBy').val('a.name');
								val = $('#noOfPageId').val();
								$("form").attr(
										"action",
										"${pageContext.request.contextPath}/employer/manageJobPost.html?noOfPage="
												+ val + "&sort=true");
								$("form").submit();
							});
						$('#jobViewTab').click( function(){
								
								$('#sortBy').val('a.jpJobStat.views');
								val = $('#noOfPageId').val();
								alert(val);
								$("form").attr(
										"action",
										"${pageContext.request.contextPath}/employer/manageJobPost.html?noOfPage="
												+ val + "&sort=true");
								$("form").submit();
							});
						$('#jobClicksTab').click( function(){
							
							$('#sortBy').val('a.jpJobStat.clicks');
							val = $('#noOfPageId').val();
							$("form").attr(
									"action",
									"${pageContext.request.contextPath}/employer/manageJobPost.html?noOfPage="
											+ val + "&sort=true");
							$("form").submit();
						});
					$('#jobAppliesTab').click( function(){
							
							$('#sortBy').val('a.jpJobStat.applies');
							val = $('#noOfPageId').val();
							$("form").attr(
									"action",
									"${pageContext.request.contextPath}/employer/manageJobPost.html?noOfPage="
											+ val + "&sort=true");
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
	<form:hidden path="noOfPage" />
	<form:hidden path="sortAsc" />
	<form:hidden path="sortBy" />
		<div class="ad_page_top">
			${adPageTop }
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
							<span>Results viewable:</span> <span class="Padding0">
											<select id="noOfPageId" 
												class="jb_input4 margin0">
												<option value="10">10</option>
												<option value="20">20</option>
												<option value="30">30</option>
												<option value="40">40</option>
												<option value="50">50</option>
											</select>
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
								<td><a href="<%=request.getContextPath()%>/employer/manageJobPost.html?page=${currentPage-10}&jobStatus=${statusValue}&next=${begin-10}&noOfPage=${jobPostForm.noOfPage}"> <img
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
													href="<%=request.getContextPath()%>/employer/manageJobPost.html?page=${i}&jobStatus=${jobPostForm.statusValue}&noOfPage=${jobPostForm.noOfPage}"
													>${i}</a>
											</c:if></span>

									</c:otherwise>
								</c:choose>
							</c:forEach>
							
								<span>
								<c:if test="${noOfPages gt 10 && noOfPages != currentPage}"><a
									href="<%=request.getContextPath()%>/employer/manageJobPost.html?page=${currentPage+10}&jobStatus=${jobPostForm.statusValue}&next=${begin+10}&noOfPage=${jobPostForm.noOfPage}">Next <img
										src="../resources/images/ArrowRight.png">
								</a></c:if></span>
							



						</div>
					</div>


					<!--button-->
					<div class="row">
						<span> <a href="#" id="repost" value="REPOST" name="REPOST"
							class="btn_sm white jb_search_submit">REPOST</a> <a href="#"
							id="deactivated" value="DEACTIVATED" name="DEACTIVATED"
							class="btn_sm white jb_search_submit">DEACTIVATE</a> <a href="#"
							id="delete" value="DELETE" name="DELETE"
							class="btn_sm white jb_search_submit">DELETE</a> <a
							href="<%=request.getContextPath()%>/employer/postNewJobs.html"
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
								<th width="5%" align="center" valign="middle" id="avdJobIdTab" class="FontSize11 cursor">Adv Job
										ID</th>
								<th width="4%" align="center" valign="middle" id="jobIdTab" class="FontSize11 cursor">Job
										ID</th>
								<th width="12%" align="center" valign="middle" id="jobTitleTab" class="FontSize11 cursor"><strong>Job
										Title</strong></th>
								<th width="10%" align="center" valign="middle" id="jobLocationTab" class="FontSize11 cursor"><strong>Location</strong></th>
								<th width="6%" align="center" valign="middle" id="jobStatusTab" class="FontSize11 cursor"><strong>Job<br />
										Status
								</strong></th>
								<th width="7%" align="center" valign="middle" id="jobStartDateTab" class="FontSize11 cursor"><strong>Start<br />
										Date
								</strong></th>
								<th width="5%" align="center" valign="middle" id="jobEndDateTab" class="FontSize11 cursor"><strong>End<br />
										Date
								</strong></th>								
								<th width="8%" align="center" valign="middle" id="jobCompanyTab" class="FontSize11 cursor"><strong>Company<br />
										Name
								</strong></th>
								<th width="3%" align="center" valign="middle" id="jobViewTab" class="FontSize11 cursor"><strong>Views</strong></th>
								<th width="4%" align="center" valign="middle" id="jobClicksTab" class="FontSize11 cursor"><strong>Clicks</strong></th>
								<th width="3%" align="center" valign="middle" id="jobAppliesTab" class="FontSize11 cursor"><strong>Applies</strong></th>
								<th width="7%" align="center" valign="middle" class="FontSize11 cursor"><strong>Auto<br />
										Renew
								</strong></th>
								<th width="11%" align="center" valign="middle" class="FontSize11 cursor"><strong>Job<br />
										Template
								</strong></th>
								<th width="5%" align="center" valign="middle" class="FontSize11 cursor"><strong>Override
								</strong></th>
								<th width="9%" align="center" valign="middle" class="FontSize11 cursor"><strong>Actions</strong></th>
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
										href="<%=request.getContextPath()%>/employer/editJob.html?jobId=${job.jobId}&jobStatus=${job.jobStatus}&readOnly=false">${job.jobId}</a></td>
									<td align="center" valign="middle"><a
										href="<%=request.getContextPath()%>/employer/editJob.html?jobId=${job.jobId}&jobStatus=${job.jobStatus}&readOnly=false">${job.jobNumber}</a></td>
									<td align="center" valign="middle"><a
										href="<%=request.getContextPath()%>/employer/editJob.html?jobId=${job.jobId}&jobStatus=${job.jobStatus}&readOnly=false">${job.jobTitle}</a></td>
									<td align="center" valign="middle">${job.location}</td>
									<td align="center" valign="middle">${job.jobStatus}</td>
									<td align="center" valign="middle">${job.startDt}</td>
									<td align="center" valign="middle">${job.endDt}</td>
									<td align="center" valign="middle">${job.companyName}</td>
									<td align="center" valign="middle">${job.views}</td>
									<td align="center" valign="middle">${job.clicks}</td>
									<td align="center" valign="middle">${job.applies}</td>
									<td align="center" valign="middle">
									<form:select
											path="jobPostDTOList[${status.index}].autoRenew"
											id="selectAutoRenew"
											class="jb_input3 select100 marginTopBottom0 FontSize10 width50"
											name="select1" disabled="true">
											<form:option label="No" value="No" />
											<form:option label="Yes" value="Yes" />
										</form:select>
									<td align="center" valign="middle"><form:select
											path="jobPostDTOList[${status.index}].brandTemplate"
											id="selectTemplate"
											class="jb_input3 select100 marginTopBottom0 width87 FontSize10"
											name="select" disabled="true">
											<form:option value="0" label="Select One" />
											<form:options items="${templateList}" itemLabel="optionName"
												itemValue="optionId" />
										</form:select></td>
										<c:if test="${job.bTemplateOverride eq true}">
										<td align="center" valign="middle">Yes</td>
										</c:if>
										<c:if test="${job.bTemplateOverride eq false}">
										<td align="center" valign="middle">No</td>
										</c:if>
									<td align="center" valign="middle"><div
											class="row width80 SearchIcons">
											<a title="edit"
												href="<%=request.getContextPath()%>/employer/editJob.html?jobId=${job.jobId}&jobStatus=${job.jobStatus}&readOnly=false"><div
													class="editFile"></div></a><a title="view"
												href="<%=request.getContextPath()%>/employer/editJob.html?jobId=${job.jobId}&readOnly=true"><div
													class="view"></div></a><%-- <a title="check" href="#"><div class="check" name="check"
													id="${job.jobId}"><img title="delete" src="../resources/images/tranBg.png" class="check"></div></a> --%>
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
							name="DEACTIVATED" class="btn_sm white jb_search_submit">DEACTIVATE</a>
							<a href="#" id="delete_lower" value="DELETE" name="DELETE"
							class="btn_sm white jb_search_submit">DELETE</a> <a
							href="<%=request.getContextPath()%>/employer/postNewJobs.html"
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
							<span>Results viewable:</span> <span class="Padding0">
											<select id="noOfPageLowerId" 
												class="jb_input4 margin0">
												<option value="10">10</option>
												<option value="20">20</option>
												<option value="30">30</option>
												<option value="40">40</option>
												<option value="50">50</option>
											</select>
										</span>
							<!--Added Class "marginTop5"-->
							<span>per page</span>
						</div>
						<div class="searchResultsNavigationColumn3">&nbsp;&nbsp;&nbsp;
						</div>
						<div class="searchResultsNavigationColumn2 floatRight">
							<!-- <span>Page: </span> -->
							
							<%--For displaying Previous link except for the 1st page --%>
							<c:if test="${currentPage != 1 && noOfPages gt 10}">
								<td><a href="<%=request.getContextPath()%>/employer/manageJobPost.html?page=${currentPage-10}&jobStatus=${statusValue}&next=${begin-10}"> <img
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
								<c:if test="${noOfPages gt 10 && noOfPages != currentPage}"><a
									href="<%=request.getContextPath()%>/employer/manageJobPost.html?page=${currentPage+10}&jobStatus=${jobPostForm.statusValue}&next=${begin+10}&noOfPage=${jobPostForm.noOfPage}">Next <img
										src="../resources/images/ArrowRight.png">
								</a></c:if></span>
						</div>
					</div>
				</div>

				<!--Start:MidContant-->
				<div class="clearfix"></div>
				<!-- content_wrapper -->
				<div class="ad_wrapper">
						${adPageBottom }
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