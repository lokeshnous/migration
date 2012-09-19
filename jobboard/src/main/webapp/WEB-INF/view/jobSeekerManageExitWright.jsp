<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Advance Health care job</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="common/include.jsp" />
<script type="text/javascript">
	jQuery(document).ready(function() {
		
		jQuery(".megamenu").megamenu();
	});
</script>
<script src="javascripts/expandCollapse.js" type="text/javascript"></script>
</head>
<body class="job_board">
	<div class="job_seeker_login popUpContainer" id="jobSeekerRegister1"
		style="display: block;">
		<div class="popupHeader">
			<h2>Manage My Cover Letters</h2>
			<a href="#"> <img width="19" height="19"
				src="<%=request.getContextPath()%>/resources/images/Close.png"
				class="nyroModalClose" alt="Close" /></a>
		</div>
		<div class="popUpContainerWrapper">
			<form:form action="" method="GET" commandname ="resCoverLetterForm">
				<div class="rowEvenNewSpacing marginTop0">
					<table width="100%" class="grid" border="0" cellSpacing="0"
						cellPadding="0">
						<thead>
							<tr class="borderTopNone">
								<th width="38%" align="left" scope="col">Cover Letter Name
								</th>
								<th width="23%" align="center" scope="col">Visibility*</th>
								<th width="18%" align="center" scope="col">Modified</th>
								<th width="21%" align="center" scope="col">Actions</th>
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${jobOwners}" var="job" varStatus="status">
								<tr>
									<td align="center" valign="middle">${job.name}</td>
									<td align="center" valign="middle">	
									<c:if test="${job.active=='1'}">Public</c:if>
									<c:if test="${job.active=='0'}">Private</c:if>														
									</td>
									<td align="center" valign="middle">${job.updateDt}</td>
									<td align="center">
										 <a href="<%=request.getContextPath()%>/jobSeekerCoverLetter/jobseekerViewCoverLetter.html?coverletterId=${job.coverletterId}&type=View" class="nyroModal"> <img width="20" height="20" alt="" class="view"/></a>
										 <a href="<%=request.getContextPath()%>/jobSeekerCoverLetter/jobseekerViewCoverLetter.html?coverletterId=${job.coverletterId}&type=Edit" class="nyroModal"> <img width="20" height="20" alt="" class="editFile"/></a> 
										 <a href="#"> <img width="20" height="20" alt="" class="download"/></a> 
										 <a href="#"> <img width="20" height="20" alt="" class="print"/></a> 
										 <a href="<%=request.getContextPath()%>/jobSeekerCoverLetter/jobseekerDeleteCoverLetter.html?coverletterId=${job.coverletterId}" class="nyroModal"> <img width="20" height="20" alt="" class="delete"/></a>
									 </td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="row marginTop5 paddingBottom10">
					<span class="floatLeft marginTop10">
					   <a class="btn_sm orange nyroModal" href="<%=request.getContextPath()%>/jobSeekerCoverLetter/createCoverLetter.html?resumeType=createCover">New Cover Letter </a> 
					   <a class="btn_sm orange" href=""> Cancel </a>
					</span> <span class="floatLeft marginTop10 marginLeft5"> </span>
				</div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>
</body>
</html>