<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />
<script type="text/javascript">
	jQuery(document).ready(function() {
		function cancelProcess() {
			parent.$.nmTop().close();
		}	
		$(".purchaseJobPostings").displaypopup(".purchaseJobPostings","790", "360");
		jQuery(".megamenu").megamenu();
	});
</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>JOB POSTING INVENTORY</h2>
			<a href="#"><img title="close" src="../resources/images/Close.png" width="19"
				height="19" onclick="cancelProcess();" alt=""></a>
		</div>

		<div class="popUpContainerWrapper">
			<form:form method="GET"
				action="../inventory/employer/jobInventory.html"
				commandname="inventoryForm">

				<div class="rowEvenNewSpacing marginTop0">
					<div class="row FontSize18 boldText">Standard Job Posting</div>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="grid">
						<thead>
							<tr>
								<th width="41%" align="left" valign="top" scope="col">Type</th>
								<th width="13%" align="center" valign="top" scope="col">Duration</th>
								<th width="13%" align="center" valign="top" scope="col">Purchased</th>
								<th width="11%" align="center" valign="top" scope="col">Available</th>
								<th width="12%" align="center" valign="top" scope="col">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${jbPostList}" var="jbPostList">
								<tr class="Height30">
									<td align="left">${jbPostList.getAddon()}</td>
									<td align="center">${jbPostList.getDuration()}</td>
									<td align="center">${jbPostList.getQuantity()}</td>
									<td align="center">${jbPostList.getAvailableQty()}</td>
									<td align="center"><a
										href="<%=request.getContextPath()%>/employer/postNewJobs.html"><img
											title="add" src="../resources/images/Addbutton.png" width="20"
											height="20" alt="Add Button"></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="rowEvenNewSpacing marginTop20">
					<div class="row FontSize18 boldText">Job Posting Slot</div>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="grid">
						<thead>
							<tr>
								<th width="41%" align="left" valign="top" scope="col">Type</th>
								<th width="13%" align="center" valign="top" scope="col">Duration</th>
								<th width="13%" align="center" valign="top" scope="col">Purchased</th>
								<th width="11%" align="center" valign="top" scope="col">Available</th>
								<th width="12%" align="center" valign="top" scope="col">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${jbSlotList}" var="jbSlotList">
								<tr class="Height30">
									<td align="left">${jbSlotList.getAddon()}</td>
									<td align="center">${jbSlotList.getDuration()}</td>
									<td align="center">${jbSlotList.getQuantity()}</td>
									<td align="center">${jbSlotList.getAvailableQty()}</td>
									<td align="center"><a href="<%=request.getContextPath()%>/employer/postNewJobs.html"><img
											src="../resources/images/Addbutton.png" width="20"
											height="20" alt="Add Button"></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<input type="hidden" name="pageValue" value="inventoryPage" />
				<div class="row marginTop20 paddingBottom10">
					<a id="purchaseJobPostings" href="<%=request.getContextPath()%>/purchaseJobPosting/purchaseJobPostings.html?page=inventoryPage" class="purchaseJobPostings btn_sm orange">BUY MORE</a> 
					<a href="" onclick="cancelProcess();" class="btn_sm orange">Cancel</a>
				</div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>