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
		
		$("#tb_cover_letter img").click(function(event) {
			
			var action = $(this).attr("class");
			var rowObj = $(this).parent().parent().parent();
			var saveSearchId = rowObj.attr("id");
			
			//var saveSearchedUrl = rowObj.attr("href");
			//var searchName = $("#searchName").text();
			
			//alert(action);
			switch (action) {
			 case "edit":		
				
			   break; 
			case "delete":{
				var r=confirm("Are you sure you want to delete?");
				if(r==true){
					$.ajax({url: "${pageContext.request.contextPath}/jobSeekerCoverLetter/deleteManageExistCoverLetter.html?coverletterId="+saveSearchId,
							success: function(data){ 
							    if(data.success != null){
							    	rowObj.remove();
							    }
							    if(data.failure != null){
							    	alert(data.failure);
							    }
							},
							error: function(response) {
								alert("Server Error : "+response.status);
							},
							complete: function() {
								
							}
						});
					}
					break;
			}
			}
		});	
		
		
		
		jQuery(".megamenu").megamenu();
	});
	

function openwin(where) {
		window.open(where,"TempWindow","width=300,height=210,resizable=yes,status=yes");
		} 

 function printPopup(id){
	 var coverletterId = id.replace("print","");
	 //$.nmManual('../jobSeekerCoverLetter/jobseekerPrintCoverLetter.html?coverletterId='+coverletterId+'&type=Print');
	document.location.href =('../jobSeekerCoverLetter/jobseekerPrintCoverLetter.html?coverletterId='+coverletterId+'&type=Print');
 } 
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
					<table id="tb_cover_letter" width="100%" class="grid" border="0" cellSpacing="0"
						cellPadding="0">
						<thead>
							<tr class="borderTopNone">
								<th width="38%" align="center" scope="col">Cover Letter Name</th>
								<th width="23%" align="center" scope="col">Visibility*</th>
								<th width="18%" align="center" scope="col">Modified</th>
								<th width="21%" align="center" scope="col">Actions</th>
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${jobOwners}" var="job" varStatus="status">
								<tr id="${job.coverletterId}">
									<td align="center" valign="middle">${job.name}</td>
									<td align="center" valign="middle">	
									<c:if test="${job.active=='1'}">Public</c:if>
									<c:if test="${job.active=='0'}">Private</c:if>														
									</td>
									<td align="center" valign="middle">${job.updateDt}</td>
									<td align="center">
										 <a href="<%=request.getContextPath()%>/jobSeekerCoverLetter/jobseekerViewCoverLetter.html?coverletterId=${job.coverletterId}&type=View" class="nyroModal" title="View"> <img width="20" height="20" alt="" class="view"/></a>
										 <a href="<%=request.getContextPath()%>/jobSeekerCoverLetter/jobseekerViewCoverLetter.html?coverletterId=${job.coverletterId}&type=Edit" class="nyroModal" title="Edit"> <img width="20" height="20" alt="" class="editFile"/></a> 
										 <a href="<%=request.getContextPath()%>/jobSeekerCoverLetter/jobseekerDownloadCoverLetter.html?coverletterId=${job.coverletterId}"> <img width="20" height="20" alt="" class="download" /></a>
										 <%-- <a href="<%=request.getContextPath()%>/jobSeekerCoverLetter/jobseekerPrintCoverLetter.html?coverletterId=${job.coverletterId}&type=Print" class="print" class="btnPrint"> <img width="20" height="20" alt="" class="print"/></a> --%>								
										 <a title="Print" id="print${job.coverletterId}" onclick="printPopup(this.id)"><img width="20" height="20" alt="" class="print"/></a> 
										 <a href="#" title="Delete"> <img width="20" height="20" alt="" class="delete"/></a>
										 
									 </td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="row marginTop5 paddingBottom10">
					<span class="floatLeft marginTop10">
					   <a class="btn_sm orange nyroModal" href="<%=request.getContextPath()%>/jobSeekerCoverLetter/createCoverLetter.html?resumeType=createCover" title="New Cover Letter">New Cover Letter </a> 
					   <a class="btn_sm orange" href="" title="Cancel"> Cancel </a>
					</span> <span class="floatLeft marginTop10 marginLeft5"> </span>
				</div>
				<!-- <iframe id="printPage" name="printPage" src="../jobSeekerCoverLetter/jobseekerPrintCoverLetter.html" style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; border: 0px none; z-index: -1;"></iframe> -->
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>
</body>
</html>