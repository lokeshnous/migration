<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<body class="job_board">
	<form:form commandName="manageJobSeekerForm" id="manageJobSeeker">

		<div class="column1">
			<div class="section" id="div_manage_job_seeker">
				<h2>Folders</h2>
				<div class="refineResults">
					<span class="refineResultsItem plus">All Candidates</span>
					<div class="refineResultsSubContent"></div>

					<span class="refineResultsItem plus">My Folders</span>
					<div class="refineResultsSubContent" id="subContent">
						<a href="#"><img src="../resources/images/Addbutton.png"
							width="15" height="15" alt="Add" title="Add Folder"></a>
						<c:forEach items="${manageJobSeekerForm.admFolderDTOList}"
							var="folder" varStatus="folderStatus">
							<div class="buttonRow" >
								<a href="#" id="${folder.folderId}" title="${folder.folderName}-detail"
									class="folderdetail"> ${folder.folderName} </a>
								<div class="floatRight">
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