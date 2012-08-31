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
		$("#newResumeId").click(function(event){
			$.ajax({url : "${pageContext.request.contextPath}/jobSeekerResume/validateCreateResumePopUp.html?resumeName=&resumeId=",
				  type: "GET",
				success : function(data) {
					if (data.maxResume != null) {
							$("#manageResumeErrorMsg").html("<span>"+ data.maxResume+ "</span>");
						} else {
							$("#createResumeId").click();
						}
					},
				error : function(response) {
					alert("Server Error : "+ response.status);
					},
				complete : function() {
					
				}
			});
			
     	});
		
		$("#tb_manage_resume img").click(function(event) {

			var action = $(this).attr("alt");
			var rowObj = $(this).parent().parent().parent();
			var resumeId = rowObj.attr("id");
			
			switch (action) {
			case "view":
					$("form").attr("action", "${pageContext.request.contextPath}/jobSeekerResume/viewResumeBuilder.html?resumeId="+resumeId);
					$("form").submit();
					break;
			case "download":
				alert("download");
				break;
			case "print":
				alert("print");
				break;
			case "delete":{
				if (confirm("Are you sure you want to delete?")) {
						$.ajax({url: "${pageContext.request.contextPath}/jobSeekerResume/deleteResume.html?resumeId="+resumeId,
							type: "POST",
							success: function(data){ 
							    if(data.success != null){
							    	rowObj.remove();
							    	$("#manageResumeErrorMsg").html("");
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
				<div class="rowEvenSpacingMargin0">
				<div id="manageResumeErrorMsg" class="FormErrorDisplayText">
					
				</div>
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
											alt="view"></a>&nbsp;<a href='<%=request.getContextPath()%>/jobSeekerResume/editResume.html?resumeId=${resume.uploadResumeId}' class="nyroModal"><img 
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
				<div class="popUpButtonRow">
					<span class="floatLeft ">
						<a class="btn_sm orange" href="#" id="newResumeId">New Resume</a>
						<a class="nyroModal" id="createResumeId" href="<%=request.getContextPath()%>/jobSeekerResume/createResumePopUp.html?resumeType=createResume"></a>
						<a class="nyroModalClose btn_sm orange" href="#">Cancel</a>	</span>				
					<span
						class="floatLeft"><em>*Only 1
							resume may be made Public at a time</em></span>
				</div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>
</body>
</html>