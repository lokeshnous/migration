<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />
<script type="text/javascript">
	jQuery(document)
			.ready(
					function() {
						$("#tb_save_search img")
								.click(
										function(event) {

											var action = $(this).attr("alt");
											var rowObj = $(this).parent()
													.parent().parent();
											var saveSearchId = rowObj
													.attr("id");

											switch (action) {
											case "view":
												$("form")
														.attr(
																"action",
																getBaseURL()
																		+ "savedSearches/viewMySavedSearchRecord.html?saveSearchId="
																		+ saveSearchId);
												$("form").submit();
												break;
											case "delete": {
												$
														.ajax({
															url : getBaseURL()
																	+ "/savedSearches/deleteSavedSearch.html?saveSearchId="
																	+ saveSearchId,
															success : function(
																	data) {
																if (data.success != null) {
																	rowObj
																			.remove();
																	alert(data.success);
																}
																if (data.failure != null) {
																	alert(data.failure);
																}
															},
															error : function(
																	response) {
																alert("Server Error : "
																		+ response.status);
															},
															complete : function() {

															}
														});
											}
												break;
											}

										});
						jQuery(".megamenu").megamenu();
					});
</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>My Saved Searches</h2>
			<a href="#"><img src="../resources/images/Close.png" width="19"
				height="19" onclick="parent.$.nmTop().close();" alt=""></a>
		</div>

		<div class="popUpContainerWrapper">
			<form:form method="" commandName="saveSearchForm">
				<div class="row">
					<table id="tb_save_search" width="100%" border="0" cellspacing="0"
						cellpadding="0" class="grid">
						<tr>
							<th width="49%" align="left" scope="col">Saved Search Name</th>
							<th width="15%" align="center" scope="col">Updated</th>
							<th width="22%" align="center" scope="col">&nbsp;&nbsp;Notify
								Me</th>
							<th width="14%" align="center" scope="col">Actions</th>
						</tr>

						<c:forEach items="${saveSearchedJobsDTOList}"
							var="saveSearchdtoList">
							<tr id="${saveSearchdtoList.saveSearchID}">
								<td><a
									href='<c:url value="/savedSearches/viewMySavedSearchRecord.html"><c:param name="id" value="${saveSearchdtoList.getUrl()}"/> </c:url>'
									rel="0" target="_blank" class="newWindow">${saveSearchdtoList.getSearchName()}</a></td>
								<td align="center">${saveSearchdtoList.getModifyDate()}</td>
								<td align="center"><form:select
										class="jb_input3 select100 marginTopBottom0"
										path="emailFrequency" items="${notifyMeList}"
										itemValue="optionId" itemLabel="optionName" />
										
								</td>
								<td align="center"><a href="#"><img
										src="../resources/images/View.png" width="20" height="20"
										alt="view"></a>&nbsp;<a href='' class="nyroModal"><img
										src="../resources/images/Edit.png" width="20" height="20"
										alt="edit"></a>&nbsp;<a href="#"><img
										src="../resources/images/Delete.png" width="20" height="20"
										alt="delete"></a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="row marginTop20 paddingBottom10">
					<a href="/jobboard/jobsearchactivity/findJobPage.html">
						<h3>${msg.jsCreateNewSavedSearch}</h3>
					</a> <em class="lineHeight16">${msg.jsSavedSearchInfo}</em>
				</div>
			</form:form>
			<div class="row marginTop20 paddingBottom10">
				<a href="" class="btn_sm orange">Save</a> <a
					onclick="parent.$.nmTop().close();" class="btn_sm orange">Cancel</a>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>