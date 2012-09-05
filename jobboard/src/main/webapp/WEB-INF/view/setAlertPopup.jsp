<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />
<script type="text/javascript">
	function closePopup() {
		parent.window.location.reload();
	}
	$(document).keyup(function(event) {
		if (event.keyCode == 27) {
			parent.window.location.reload();
		}
	});
	jQuery(document).ready(function() {
		jQuery(".megamenu").megamenu();
	});
</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader marginBottom0">
			<h2>SET ALERTS</h2>
			<a href="#"><img src="../resources/images/Close.png" width="19"
				height="19" onclick="closePopup();" alt=""></a>
		</div>
		<div class="popUpContainerWrapper">
			<form action="" method="">
				<div class="rowEvenNewSpacing">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="grid2">

						<tr>
							<td><input type="checkbox" name="checkbox" id="checkbox">
								&nbsp;&nbsp;<label for="checkbox">Unused job posting
									expires soon</label></td>
							<td><input type="checkbox" name="checkbox" id="checkbox">
								&nbsp;&nbsp;<label for="checkbox">New job posting
									credits are available</label></td>
						</tr>
						<tr>
							<td><input type="checkbox" name="checkbox" id="checkbox">
								&nbsp;&nbsp;<label for="checkbox">Active job posting
									expires soon</label></td>
							<td><input type="checkbox" name="checkbox" id="checkbox">
								&nbsp;&nbsp;<label for="checkbox">Order has been
									assigned</label></td>
						</tr>
						<tr>
							<td><input type="checkbox" name="checkbox" id="checkbox">
								&nbsp;&nbsp;<label for="checkbox">Job posting has
									expired</label></td>
							<td><input type="checkbox" name="checkbox" id="checkbox">
								&nbsp;&nbsp;<label for="checkbox">Order receipt is
									available</label></td>
						</tr>
						<tr>
							<td><input type="checkbox" name="checkbox" id="checkbox">
								&nbsp;&nbsp;<label for="checkbox">Job posting failed to
									auto renew</label></td>
							<td><input type="checkbox" name="checkbox" id="checkbox">
								&nbsp;&nbsp;<label for="checkbox">Sales contact has been
									requested </label></td>
						</tr>
						<tr>
							<td><input type="checkbox" name="checkbox" id="checkbox">
								&nbsp;&nbsp;<label for="checkbox">No job postings are
									active</label></td>
							<td><input type="checkbox" name="checkbox" id="checkbox">
								&nbsp;&nbsp;<label for="checkbox">Administrator changes
									have been made</label></td>
						</tr>



					</table>
					<div
						class="rowEvenNewSpacing borderBottomDotted paddingBottom10 marginTop0"></div>
					<div class="clearfix"></div>
					<span><h3 class="TextColor01 marginTop15">Select who
							you would like to receive the alert(s) checked above.</h3></span>
					<div class="rowEvenNewSpacing">
						<span class=" FloatLeft marginTop3">Job Owner: </span> <select
							id="select14" class="jb_input3  marginTop0 width150 marginLeft5"
							name="select9">
							<option selected="selected">--- Job Owner ---</option>
							<option>Daniel Stuart</option>
							<option>Kim Noble</option>
							<option>Jane Wharton</option>
							<option>Mark Murphy</option>
							<option>Michael Connor</option>
						</select> <span class="required paddingTop4"><a href="#">Add New
								Job Owner</a></span>
					</div>
				</div>
				<div class="row marginTop20 paddingBottom10">
					<span class="floatLeft marginTop10"> <input type="button"
						id="saveData" class="btn_sm orange value=" Save" /> <input
						type="button" onclick="closePopup();" class="btn_sm orange value=" Cancel" />
						<!-- <a href="" class="btn_sm orange">Cancel</a> --></span>
				</div>
			</form>
		</div>
		<div class="clearfix"></div>
	</div>
</body>
</html>