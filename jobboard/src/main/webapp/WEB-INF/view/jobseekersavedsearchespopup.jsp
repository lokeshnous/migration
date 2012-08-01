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
	jQuery(document).ready(function() {
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
				height="19" alt=""></a>
		</div>

		<div class="popUpContainerWrapper">
			<form:form action="" method="" commandName="saveSearchForm">
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
							<tr>
								<td><a
									href='<c:url value="/savedSearches/viewMySavedSearchRecord.html"><c:param name="id" value="${saveSearchdtoList.getUrl()}"/> </c:url>'
									rel="0" target="_blank" class="newWindow">${saveSearchdtoList.getSearchName()}</a></td>
								<td align="left">${saveSearchdtoList.getModifyDate()}</td>
								<td align="center"><form:select id="select"
										class="jb_input3 select100 marginTopBottom0" name="select"
										path="notify_me" items="${notifyMeList}" itemValue="optionId"
										itemLabel="optionName" /></td>
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
					<a href="">
						<h3>Create a new Saved Search</h3>
					</a> <em class="lineHeight16">You can create up to 5 Saved
						Searches</em>
				</div>
				<div class="row marginTop20 paddingBottom10">
					<a href="" class="btn_sm orange">Save</a> <a href=""
						onclick="parent.$.nmTop().close();" class="btn_sm orange">Cancel</a>
				</div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>