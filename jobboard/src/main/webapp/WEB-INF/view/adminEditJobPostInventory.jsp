<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<h2>MANAGE/EDIT JOB POSTING INVENTORY</h2>
			<img id="closeCheckOut" src="../resources/images/Close.png"
				width="19" height="19" alt="" class="nyroModalClose">
		</div>
		<div class="popUpContainerWrapper">
			<form:form method="post" >
				<b>Employer List:</b>
				<input type="text" />
				<font color="red">OR</font>
				<b>Net Suite ID Number</b>
				<input type="text" />
				<input type="button" value="FIND" />
				<div class="rowEvenNewSpacing marginTop0">
					<div class="row FontSize18 boldText">Standard Job Posting</div>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="grid">
						<tr>
							<th width="41%" align="left" valign="top" scope="col">Type</th>
							<th width="13%" align="center" valign="top" scope="col">Duration</th>
							<th width="13%" align="center" valign="top" scope="col">Purchased</th>
							<th width="11%" align="center" valign="top" scope="col">Available</th>
						</tr>
						<tr class="Height30">
							<td align="left">Job Template</td>
							<td align="center">30 days</td>
							<td align="center">3</td>
							<td align="center">1</td>
						</tr>
						<tr class="Height30">
							<td align="left">Universal Geography</td>
							<td align="center"></td>
							<td align="center">3</td>
							<td align="center">1</td>
						</tr>
						<tr class="Height30">
							<td align="left">Premium Sponsored</td>
							<td align="center"></td>
							<td align="center">3</td>
							<td align="center">1</td>
						</tr>
						<tr class="Height30">
							<td align="left">Job Template & Universal Geography</td>
							<td align="center"></td>
							<td align="center">3</td>
							<td align="center">1</td>
						</tr>
						<tr class="Height30">
							<td align="left">Universal Geography & Premium Sponsored</td>
							<td align="center"></td>
							<td align="center">3</td>
							<td align="center">1</td>
						</tr>
						<tr class="Height30">
							<td align="left">Job Template & Premium Sponsored</td>
							<td align="center"></td>
							<td align="center">3</td>
							<td align="center">1</td>
						</tr>
						<tr class="Height30">
							<td align="left">Job Template, Universal Geography <br>
								& Premium Sponsored
							</td>
							<td align="center"></td>
							<td align="center">3</td>
							<td align="center">1</td>
						</tr>
					</table>
				</div>
				<div class="rowEvenNewSpacing marginTop20">
					<div class="row FontSize18 boldText">Job Posting Slot</div>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="grid">
						<tr>
							<th width="41%" align="left" valign="top" scope="col">Type</th>
							<th width="13%" align="center" valign="top" scope="col">Duration</th>
							<th width="13%" align="center" valign="top" scope="col">Purchased</th>
							<th width="11%" align="center" valign="top" scope="col">Available</th>
						</tr>
						<tr class="Height30">
							<td align="left">Job Template, Universal Geography & Premium
								Sponsored</td>
							<td align="center"></td>
							<td align="center">3</td>
							<td align="center">1</td>
						</tr>
						<tr class="Height30">
							<td align="left">Universal Geography</td>
							<td align="center"></td>
							<td align="center">3</td>
							<td align="center">1</td>
						</tr>
						<tr class="Height30">
							<td align="left">Premium Sponsored</td>
							<td align="center"></td>
							<td align="center">3</td>
							<td align="center">1</td>
						</tr>
						<tr class="Height30">
							<td align="left">Job Template & Universal Geography</td>
							<td align="center"></td>
							<td align="center">3</td>
							<td align="center">1</td>
						</tr>
						<tr class="Height30">
							<td align="left">Universal Geography & Premium Sponsored</td>
							<td align="center"></td>
							<td align="center">3</td>
							<td align="center">3</td>
						</tr>
						<tr class="Height30">
							<td align="left">Job Template & Premium Sponsored</td>
							<td align="center"></td>
							<td align="center">3</td>
							<td align="center">1</td>
						</tr>
						<tr class="Height30">
							<td align="left">Job Template, Universal Geography & Premium
								Sponsored</td>
							<td align="center"></td>
							<td align="center">3</td>
							<td align="center">1</td>
						</tr>
					</table>
				</div>
				<div class="row marginTop20 paddingBottom10">
					<a href="" class="btn_sm orange">SAVE</a><a href=""
						class="btn_sm orange">CANCEL</a>
				</div>

			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>
</body>
</html>