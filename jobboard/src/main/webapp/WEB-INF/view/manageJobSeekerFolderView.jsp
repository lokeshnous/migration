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
	$(".folderdetailList")
								.click(
										function() {
											var val = $(this).attr("id");

											$
													.ajax({
														url : "${pageContext.request.contextPath}/employer/moveToFolder.html?folderId="
																+ val
																+ "&selectedVal=${manageJobSeekerForm.selectedRow}",
														data : $(
																'#manageJobSeeker')
																.serialize(),
														type : "POST",
														success : function(data) {
															if (data.failure != null) {
															} else {
																//alert(data);
															}
														}

													});
										});
						jQuery(".megamenu").megamenu();
					});
</script>
<script type="text/javascript" src="../resources/js/expandCollapse.js"></script>

<script src="../resources/js/jquery.dataTables.nightly.js"></script>
<script src="../resources/js/searchResultsdatatable.js"></script>

<script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>

</head>
<body class="job_board">
	<form:form commandName="manageJobSeekerForm" id="manageJobSeeker">
		<form:hidden path="selectedRow" id="selectedRow" />
		<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
			style="display: block">
			<div class="popupHeader">
				<h2>Move To Folder</h2>
				<img src="../resources/images/Close.png" title="Close" width="19"
					height="19" class="nyroModalClose" alt="close">
			</div>

			<div class="popUpContainerWrapper">
				<div class="column1">
					<div class="section">
						<div class="refineResults">
							<span class="refineResultsItem plus">All Candidates</span>
							<div class="refineResultsSubContent"></div>

							<span class="refineResultsItem plus">My Folders</span>
							<div class="refineResultsSubContent">
								<c:forEach items="${manageJobSeekerForm.admFolderDTOList}"
									var="folder" varStatus="folderStatus">
									<div class="buttonRow">
										<a
											href="${pageContext.request.contextPath}/employer/moveToFolder.html?folderId=${folder.folderId}&selectedVal=${manageJobSeekerForm.selectedRow }"
											id="${folder.folderId}" title="folderdetailList"
											class="folderdetailList"> ${folder.folderName} </a>
										<div class="floatRight">
											<a href=""><img src="../resources/images/CloseGray.jpg"
												alt="close" width="15" height="15"> </a>
										</div>

									</div>
								</c:forEach>
							</div>




						</div>
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
		<%-- <div class="section">
									<h2>Folders</h2>

									<div class="refineResults">
										<span class="refineResultsItem plus">All Candidates</span>
										<div class="refineResultsSubContent"></div>

										<span class="refineResultsItem plus">My Folders</span>
										<div class="refineResultsSubContent">
										<c:forEach items="${manageJobSeekerForm.admFolderDTOList}" var="folder"
											varStatus="folderStatus">
												<div class="buttonRow" >
													<a href="#" id="${folder.folderId}" title="folderdetail" class="folderdetail"> ${folder.folderName} </a>
													<div class="floatRight">
														<a href=""><img
															src="../resources/images/CloseGray.jpg" alt="close"
															width="15" height="15"> </a>
													</div>

												</div>
											</c:forEach>
										</div>




									</div>


								</div> --%>
	</form:form>
</body>
</html>