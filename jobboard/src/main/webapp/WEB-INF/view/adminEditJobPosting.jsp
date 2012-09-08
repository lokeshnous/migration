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
			<h2>MANAGE/EDIT JOB POSTING</h2>
			<img id="closeCheckOut" src="../resources/images/Close.png" width="19" height="19"
				alt="" class="nyroModalClose">
		</div>
		<div class="popUpContainerWrapper">
			<form:form method="post">
			</form:form>
		</div>
		<div class="clearfix"></div>		
	</div>
</body>
</html>