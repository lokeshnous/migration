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
		$('#save').click(function(){			
			$.ajax({url:"${pageContext.request.contextPath}/alerts/employer/saveAlerts.html",
				data:$('#alertId').serialize(),
				type:"GET",
				success: function(data) {			
						alert("The alerts saved Successfully.");
						parent.$.nmTop().close();
				 },
			});
		}); 
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
			<form:form method="GET" action="../alerts/employer/saveAlerts.html" id="alertId" commandName="alertForm">
				<div class="rowEvenNewSpacing">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="grid2">
						<c:forEach items="${alertList}" var="alertList" varStatus="index">
							<c:if test="${index.count % 2 != 0 }">
								<tr>
							</c:if>
							<td><form:checkbox path="selectedAlerts"
									label="${alertList.optionName}" value="${alertList.optionId}"
									cssStyle="width:20px" /></td>
							<c:if test="${index.count % 2 == 0 }">
								</tr>
							</c:if>
						</c:forEach>
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
							<option selected="selected" id="ownerId">--- Job Owner ---</option>
							<option>Daniel Stuart</option>
							<option>Kim Noble</option>
							<option>Jane Wharton</option>
							<option>Mark Murphy</option>
							<option>Michael Connor</option>
						</select> <span class="required paddingTop4"><a href="#">Add New
								Job Owner</a></span>
					</div>
				</div>
				<div class="popUpButtonRow">
					<input type="button" id="save" value="Save" class="orange" />
					<!-- <a href="" class="btn_sm orange">Save</a> -->
					<input type="button" value="Cancel" onclick="closePopup()"
						class="orange" name="Cancel" />
				</div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>
</body>
</html>