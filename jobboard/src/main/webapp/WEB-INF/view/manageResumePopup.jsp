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

		$("#resumePopupId").click(function (event){
             parent.$.nmTop().close();
             $("#createResumePopupId").click();
        });

		$("#createResumePopupId").displaypopup("#createResumePopupId","775","252");
		
		$("#editResumePopupId").displaypopup("#editResumePopupId","775","252");

		$("#tb_manage_resume img").click(function(event) {

			var action = $(this).attr("alt");
			var resumeId = $(this).parent().parent().parent().attr("id");
			
			switch (action) {
			case "view":
				alert("view");
				break;
			case "edit":
				/* $("#editResumePopupId").attr("href",getBaseURL()+"/jobSeekerResume/editResume.html?resumeId="+resumeId);
				alert($("#editResumePopupId").attr("href")); */
				parent.$.nmTop().close();
				$("#editResumePopupId").click();
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
				alert("Deleted Successfully");
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
			<img src="../resources/images/Close.png" width="19"
				height="19" alt="Close" onclick="parent.$.nmTop().close();">
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
					<span class="floatLeft marginTop10">
					<!-- <input type="button" id="resumePopupId" class="btn_sm orange" value="New Resume" />  -->
					<a class="btn_sm orange" id="resumePopupId" href="#">New Resume</a>
					<input type="button" class="btn_sm orange" value="Cancel" onclick="parent.$.nmTop().close();" /></span> 
					<a style="visibility: hidden;" class="btn_sm orange" id="createResumePopupId" href="/jobboard/jobSeekerResume/createResumePopUp.html?resumeType=createResume"></a>
					<a style="visibility: hidden;" class="btn_sm orange" id="editResumePopupId" href="/jobboard/jobSeekerResume/editResume.html?resumeId=408"></a>
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