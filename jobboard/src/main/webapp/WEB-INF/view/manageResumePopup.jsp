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
	
		/* jQuery(".megamenu").megamenu(); */

		
		$("#tb_manage_resume img").click(function(event) {

			var action = $(this).attr("alt");
			var rowObj = $(this).parent().parent().parent();
			var resumeId = rowObj.attr("id");
			
			switch (action) {
			case "view":
					$("form").attr("action", getBaseURL()+"jobSeekerResume/viewResumeBuilder.html?resumeId="+resumeId);
					$("form").submit();
					break;
			case "download":
				alert("download");
				break;
			case "print":
				alert("print");
				break;
			case "delete":{
				if (confirm("Do You want to Delete?")) {
						$.ajax({url: getBaseURL()+"/jobSeekerResume/deleteResume.html?resumeId="+resumeId,
							type: "POST",
							success: function(data){ 
							    if(data.success != null){
							    	rowObj.remove();
							    	//alert(data.success);
							    }
							    if(data.failure != null){
							    	alert(data.failure);
							    }
							},
							error: function(response) {
								alert("Server Error : "+response.status);
							}
						});
					return true;
				 } else {
					return false;
				 }
   			   }
				break;
			}

		});
	});
</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>MANAGE MY RESUMES</h2>
			<img src="../resources/images/Close.png" width="19" class="nyroModalClose"
				height="19" alt="Close">
		</div>
		<div class="popUpContainerWrapper">
			<form:form method="POST" action="">
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
									<td>${resume.resumeName}</td>
									<td align="center"><label>
											${resume.resumeVisibility}
									</label></td>
									<td align="center">${resume.updateDt}</td>
									<td align="center"><a href="#"><img
											src="../resources/images/View.png" width="20" height="20"
											alt="view"></a>&nbsp;<a href='/jobboard/jobSeekerResume/editResume.html?resumeId=${resume.uploadResumeId}' class="nyroModal"><img 
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
					<span class="floatLeft marginTop10">
						<a class="nyroModal btn_sm orange" id="resumePopupId" href="/jobboard/jobSeekerResume/createResumePopUp.html?resumeType=createResume">New Resume</a>
						<a class="nyroModalClose btn_sm orange" href="#">Cancel</a>
					</span> 
					
					<span
						class="floatLeft marginTop10 marginLeft5"><em>*Only 1
							resume may be made Public at a time</em></span>
				</div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>
</body>
</html>