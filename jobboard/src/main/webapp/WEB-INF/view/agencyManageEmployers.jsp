<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>

<!-- STYLESHEETS -->
<link href="../resources/css/JB.css" rel="stylesheet" type="text/css" />
<link href="../resources/css/jquery.megamenu.css" rel="stylesheet"
	type="text/css" />
<link href="../resources/css/SliderStyles.css" rel="stylesheet"
	type="text/css">
<link href="../resources/css/Gateway.css" rel="stylesheet"
	type="text/css">


<!-- JAVASCRIPT FILES -->
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="javascripts/jquery.cycle.all.min.js"></script>
<script type="text/javascript" src="javascripts/slider.js"></script>
<script type="text/javascript" src="javascripts/jquery.megamenu.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery(".megamenu").megamenu();
	});
	/* function cancelProcess() {
		parent.$.nmTop().close();
	} */
</script>


</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>Manage Employer</h2>
			<a href="#"><img src="../resources/images/Close.png" width="19"
				height="19" alt="" onclick="parent.$.nmTop().close();"></a>
		</div>
		<div class="popUpContainerWrapper">
			<form:form action="" method="">
				<div class="rowEvenNewSpacing marginTop0">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="grid">
						<tr class="borderTopNone">
							<th width="79%" align="left" scope="col">Employer Name</th>
							<th width="21%" align="center" scope="col">Actions</th>
						</tr>
						<c:forEach items="${assocEmplyrsNames}" var="assocEmplyrsName"
							varStatus="status">
							<tr>
								<td>${assocEmplyrsName.name}</td>
								<td align="center">
									<!-- 	<a
									href="<%=request.getContextPath()%>/agency/editEmployer.html?employerName=${assocEmplyrsName.facilityId}">-->
									<a
									href="<%=request.getContextPath()%>/agency/editEmployer.html?employerName=${assocEmplyrsName.name}">

										<img src="../resources/images/Edit.png" width="20" height="20"
										alt="">
								</a>&nbsp;<a
									href="<%=request.getContextPath()%>/agency/deleteAssocEmployer.html?facilityId=${assocEmplyrsName.facilityId}"><img
										src="../resources/images/Delete.png" width="20" height="20"
										alt=""></a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="row marginTop5 paddingBottom10">
					<span class="floatLeft marginTop10"> <a href=""
						class="btn_sm orange" onclick="parent.$.nmTop().close();">Cancel</a></span>
				</div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>
</body>
</html>