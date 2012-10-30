<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<body class="job_board">
	<form:form commandName="manageJobSeekerForm" id="manageJobSeeker">
		<%-- <form:hidden path="folderId" />
		<form:hidden path="folderName" />
		<c:out value="${manageJobSeekerForm.folderId}"></c:out> --%>
		<div class="column1">
			<div class="section" id="div_manage_job_seeker">
				<h2>Folders</h2>
				<div class="refineResults">
					<span class="refineResultsItem minus" id="all">All Candidates</span>
					<div class="refineResultsSubContent"></div>
					<span class="refineResultsItem plus" id="myFolder">My Folders</span>
					<div class="refineResultsSubContent" id="subContent">
						<div class="floatRight">
						<a href="#"><img src="../resources/images/Addbutton.png" id="addBtn" 
							width="15" height="15" alt="Add" title="Add Folder"></a>
							</div>
							
						<c:forEach items="${manageJobSeekerForm.admFolderDTOList}"
							var="folder" varStatus="folderStatus">
							<div class="buttonRow" >
								<span id="${folder.folderId}" title="${folder.folderName}"
									class="folderdetail" ondblclick="renameCall(${folder.folderId},'${folder.folderName}');"> ${folder.folderName} </span>
								<div class="floatRight">
								<a href="#"><img src="../resources/images/Edit.png" 
										width="15" height="15" alt="edit" title="Edit Folder" onclick="renameCall(${folder.folderId},'${folder.folderName}');"></a>
									<a href="#"><img src="../resources/images/CloseGray.jpg" id="${folder.folderName}"
										alt="remove" width="15" height="15"> </a>
								</div>

							</div>
						</c:forEach>
						
					</div>




				</div>
			</div>
		</div>

	</form:form>
</body>
</html>