<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="common/include.jsp" />
<title>ADVANCE Healthcare Jobs</title>
<!-- Common js files  -->
<script type="text/javascript" src="../resources/js/common/common.js"></script>
<script type="text/javascript">
jQuery(document).ready(function() {
		$(".folderdetail").click(
						function() {
							val = $(this).attr("id");
							//$(this).attr("style","font-weight:bold;");
							//$(this).attr("color", "#52a4dc");
						 //   document.getElementById(val).style.color="blue";
							$("#folderId").val(val);
							$("#folderName").val($(this).attr("title"));
							$.ajax({url : "${pageContext.request.contextPath}/employer/manageJobSeeker.html?folderId="
								+ val,
				    			data:$('#manageJobSeeker').serialize(),
								type: "POST",
								success : function(data) {
									if(data.failure!=null){
									}else{
										$("#contentDiv").html(data);
									}
								}					
									
								
							});
				});
				$(".refineResult").click(
						function() {
							$("#folderId").val(0);
							$.ajax({url : "${pageContext.request.contextPath}/employer/manageJobSeeker.html?folderId="
								+ 0,
				    			data:$('#manageJobSeeker').serialize(),
								type: "POST",
								success : function(data) {
									if(data.failure!=null){
									}else{
										$("#contentDiv").html(data);
									}
								}					
									
								
							});
				});
				$("#div_manage_job_seeker img")
				.click(
						function(event) {
							var action = $(this).attr("alt");
							var rowObj = $(this).parent().parent().parent();
							var folderNm = $(this).attr("id");
							//alert(folderNm);
							switch (action) {
							case "Add": {

								$(".AddNewBtn").replaceWith("<div class='buttonRow' >" +
										" <input type ='text' id='newFolder' class='addButtonRow' title='Add folder name and hit enter' value='New Folder' onClick='resetVal();' onblur='Javascript: checkevent();' onKeydown='Javascript: if (event.keyCode==13) checkevent();'/> "
										+"</div>");
								document.getElementById('newFolder').select();
								document.getElementById('newFolder').style.borderColor="red";
								document.getElementById('newFolder').style.borderStyle="solid";
							}
							break;
							case "remove": {
								if (confirm("Are you sure you want to delete?")) {
									 $.ajax({url: "${pageContext.request.contextPath}/employer/removeFolder.html?folderName="+folderNm,
										type: "POST" ,
										success : function(data) {
											if(data.failure!=null){
											}else{
												rowObj.remove();
											}
										}
									});
								}
								else{
									return false;
								}
							}
							break;
							default:
								$(".DotBorderBottom").replaceWith("<img src='../resources/images/Addbutton.png' align='center' id='addBtn' "+
									+"	width='15' height='15' alt='Add' title='Add New Folder'>");
							}

						});
				jQuery(".megamenu").megamenu();
					});
					</script>
</head>
<body class="job_board">
	<form:form commandName="manageJobSeekerForm" id="manageJobSeeker">
		<div class="column1">
			<div class="section" id="div_manage_job_seeker">
				<h2>Folders</h2>
				<div class="refineResults">
					<span class="refineResult" id="all">All Candidates</span>
					<div class="refineResultsSubContent"></div>
					<span class="refineResultsItem plus marginTop5" id="myFolder">My
						Folders</span>
					<div class="refineResultsSubContent" id="subContent">


						<c:forEach items="${manageJobSeekerForm.admFolderDTOList}"
							var="folder" varStatus="folderStatus">
							<div class="buttonRow">
								<span id="${folder.folderId}" title="${folder.folderName}"
									class="folderdetail"
									ondblclick="renameCall(${folder.folderId},'${folder.folderName}');" >
									${folder.folderName} </span>
								<div class="floatRight">
									<a href="#"><img src="../resources/images/Edit.png"
										width="15" height="15" alt="edit" title="Edit Folder"
										onclick="renameCall(${folder.folderId},'${folder.folderName}');"></a>
									<a href="#"><img src="../resources/images/CloseGray.jpg"
										id="${folder.folderName}" alt="remove" width="15" height="15">
									</a>
								</div>

							</div>
						</c:forEach>

						<div class="DotBorderBottom">&nbsp;</div>
						<div class="AddNewBtn">
						<img src="../resources/images/Addbutton.png" align="center"
							id="addBtn" width="15" height="15" alt="Add"
							title="Add New Folder"> 
						</div>
					</div>
				</div>
			</div>
		</div>

	</form:form>
</body>
</html>