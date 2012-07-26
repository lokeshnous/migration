<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<!-- JAVASCRIPT FILES -->
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="javascripts/jquery.cycle.all.min.js"></script>
<script type="text/javascript" src="javascripts/slider.js"></script>
<script type="text/javascript" src="javascripts/jquery.megamenu.js"></script>

<!-- js files for modalpopup------------------------------------------------- -->
<script src="../resources/js/jquery-1.7.1.js"></script>
<script src="../resources/js/jquery-1.7.1.min.js"></script>

		<script src="../resources/nyroModal/js/jquery.nyroModal.custom.js"></script>
        <script src="../resources/nyroModal/js/jquery.nyroModal.custom.min.js"></script>
 	    <link href="../resources/nyroModal/styles/nyroModal.css" rel="stylesheet" type="text/css">

        <style type="text/css" media="screen">
           @import url("${pageContext.request.contextPath}/resources/jquery.nyroModal/styles/nyroModal.css");
        </style>
<!-- -------------------------------------------------------------------------- -->



<script type="text/javascript">
	jQuery(document).ready(function() {
	
		/* jQuery(".megamenu").megamenu(); */

		$("#tb_manage_resume img").click(function(event) {

			var action = $(this).attr("alt");
			var resumeId = $(this).parent().parent().parent().attr("id");

			switch (action) {
			case "view":
				alert("view");
				break;
			case "edit":
				$.ajax({url: getBaseURL()+"/jobSeekerResume/editResume.html?resumeId="+resumeId,
					success: function() {
					  },
					error: function() {
					},
					complete: function() {
					}
				});
				break;
			case "download":
				alert("download");
				break;
			case "print":
				alert("print");
				break;
			case "delete": {
				$.ajax({url: getBaseURL()+"/jobSeekerResume/deleteResume.html?resumeId="+resumeId,
					success: function() {
						  $(this).parent().parent().parent().remove();
					  },
					error: function() {
					},
					complete: function() {
						 $(this).parent().parent().parent().remove();
					}
				});
				$(this).parent().parent().parent().remove();
			}
				break;

			}

		});
		
		function getBaseURL() {
			var url = location.href;  // entire url including querystring - also: window.location.href;
			var baseURL = url.substring(0, url.indexOf('/', 14));


			if (baseURL.indexOf('http://localhost') != -1) {
			    // Base Url for localhost
			    var url = location.href;  // window.location.href;
			    var pathname = location.pathname;  // window.location.pathname;
			    var index1 = url.indexOf(pathname);
			    var index2 = url.indexOf("/", index1 + 1);
			    var baseLocalUrl = url.substr(0, index2);

			    return baseLocalUrl + "/";
			}
			else {
			    // Root Url for domain name
			    return baseURL + "/";
			}
		}

	});
</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>MANAGE MY RESUMES</h2>
			<a href="#" class="nyroModalClose nyroModalCloseButton nmReposition" title="close"><img src="../resources/images/Close.png" width="19"
				height="19" alt=""></a>
		</div>
		<div class="popUpContainerWrapper">
			<form action="" method="">
				<div class="rowEvenNewSpacing marginTop0">
					<table id="tb_manage_resume" width="100%" border="0"
						cellspacing="0" cellpadding="0" class="grid">
						<thead>
							<tr class="borderTopNone">
								<th width="36%" align="left" scope="col">Resume Name</th>
								<th width="25%" align="center" scope="col">Visibility*</th>
								<th width="17%" align="center" scope="col">Modified</th>
								<th width="22%" align="center" scope="col">Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${resumeList}" var="resume" varStatus="status">
								<tr id="${resume.uploadResumeId}">
									<td>${resume.resume_name}</td>
									<td align="center"><label>
											${resume.resume_visibility}
									</label></td>
									<td align="center">${resume.updateDt}</td>
									<td align="center"><a href="#"><img
											src="../resources/images/View.png" width="20" height="20"
											alt="view"></a>&nbsp;<a href="#"><img
											src="../resources/images/Edit.png" width="20" height="20"
											alt="edit"></a>&nbsp;<a href="#"><img
											src="../resources/images/Download.png" width="20" height="20"
											alt="download"></a>&nbsp;<a href="#"><img
											src="../resources/images/Print2.png" width="20" height="20"
											alt="print"></a>&nbsp;<a href="#"><img
											src="../resources/images/Delete.png" width="20" height="20"
											alt="delete"></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="row marginTop5 paddingBottom10">
					<span class="floatLeft marginTop10"><a href=""
						class="btn_sm orange">New Resume</a> <a href=""
						class="btn_sm orange">Cancel</a></span> <span
						class="floatLeft marginTop10 marginLeft5"><em>*Only 1
							resume may be made Public at a time</em></span>
				</div>
			</form>
		</div>
		<div class="clearfix"></div>
	</div>
</body>
</html>