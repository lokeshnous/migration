<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>

<script type="text/javascript">
jQuery(document).ready(function() {
	noOfPageValue=$("#noOfPage").val();
	 $("#noOfPageId").val(noOfPageValue);
	 $("#noOfPageLowerId").val(noOfPageValue);
	var folderIdVal=$("#folderId").val();
	if(folderIdVal==0){
		folderIdVal=-1;
	}
	 $('#noOfPageId').change(
				function() {
					val = $(this).val();
					$('#noOfPageLowerId').val(val);
					$("form").attr(
							"action",
							"${pageContext.request.contextPath}/employer/manageJobSeeker.html?folderId="+folderIdVal+"&compare=true&noOfPage="+val);
					$("form").submit();
				});
		$('#noOfPageLowerId').change(
				function() {
					val = $(this).val();
					$('#noOfPageId').val(val);
					$("form").attr(
							"action",
							"${pageContext.request.contextPath}/employer/manageJobSeeker.html?folderId="+folderIdVal+"&compare=true&noOfPage="+val);
					$("form").submit();
				});

		$('#compareSelected').click(function() {
			$("#errorMsg").html("");
			folderId=$("#folderId").val();
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val[i] = $(this).val();
			});
			if(val != "" &&  val.length > 4){
				alert("Total selection of resume must be less than four");
			}else{
			if (val != "" && val.length > 1) {
				$('#selectedRow').val(val);
				 $("#manageJobSeeker").attr("action", "${pageContext.request.contextPath}/employer/compareResume.html?selectedVal="+val+"&folderId="+folderId);
				$("#manageJobSeeker").attr("method","POST");
				$("#manageJobSeeker").submit(); 
			} else {
				alert("Please select atleast two resume to continue");
			}
			}
		});
		$('#compareSelectedLower').click(function() {
			$("#errorMsg").html("");
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val[i] = $(this).val();
			});
			if (val != "" && val.length > 1) {
				$('#selectedRow').val(val);
				$("#manageJobSeeker").attr("action", "${pageContext.request.contextPath}/employer/compareResume.html?selectedVal="+val);
				$("#manageJobSeeker").attr("method","POST");
				$("#manageJobSeeker").submit();
			} else {
				alert("Please select more than one resume");
			}

		});

		$('#moveToFolder').live("click", function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {						
				val[i] = $(this).val();
			});
			if (val != "") {						
				$('#selectedRow').val(val);
				$('#moveToFolderPopup').attr("href","${pageContext.request.contextPath}/employer/moveToFolder.html?folderId=0&selectedVal="+val);	
				$.nmManual($('#moveToFolderPopup').attr("href"));
			} else {
				alert("Please select a resume!");
			}

		});
		$('#moveToFolderlower').click(function() {
			var val = [];
			$(':checkbox:checked').each(function(i) {
				val[i] = $(this).val();
			});
			if (val != "") {
				$('#selectedRow').val(val);
				$('#moveToFolderPopup').attr("href","${pageContext.request.contextPath}/employer/moveToFolder.html?folderId=0&selectedVal="+val);
				$.nmManual($('#moveToFolderPopup').attr("href"));
			} else {
				alert("Please select a resume!");
			}

		});
		
		
	$("#tb_manage_job_seeker img")
	.click(
			function(event) {
				var action = $(this).attr("alt");
				var rowObj = $(this).parent().parent().parent();
				var resumeId =  $(rowObj).attr("id");
				switch (action) {
				case "delete": {

					if (confirm("Are you sure you want to delete?")) {
							$.ajax({url: "${pageContext.request.contextPath}/employer/deleteJobSeekerDetails.html?resumeId="+resumeId,
								type: "POST",
								success: function(data){ 
								    if(data.success != null){
								    	rowObj.remove();
								    }
								    if(data.failure != null){
								    	alert(data.failed);
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
				case "download":{
					$("#manageResumeForm").attr("action", "${pageContext.request.contextPath}/jobSeekerResume/downloadResume.html?resumeId="+resumeId);
					$("#manageResumeForm").attr("method","POST");
					$("#manageResumeForm").attr("target","_new"); 
					$("#manageResumeForm").submit();
				}
					break;
				
				}

			});

	jQuery(".megamenu").megamenu();
});
</script>
</head>
<body class="job_board">
	<form:form commandName="manageJobSeekerForm" id="manageJobSeeker">
		<form:hidden path="folderId" />
		<form:hidden path="folderName" />
		<form:hidden path="noOfPage" />
		<div class="column2">
			<div id="errorMsg" class="FormErrorDisplayText01 marginBottom5">
				<c:if test="${errorMsg != null}">
					<c:out value="${errorMsg}"></c:out>
				</c:if>

			</div>
			<div class="searchResultsNavigation width98P">
				<div class="searchResultsNavigationColumn1">

					<!--Added Class "marginTop5"-->
					<span>Results viewable:</span> <span class="Padding0"> <select
						id="noOfPageId" class="jb_input4 margin0">
							<option value="10">10</option>
							<option value="20">20</option>
							<option value="30">30</option>
							<option value="40">40</option>
							<option value="50">50</option>
					</select>
					</span>
					<!--Added Class "marginTop5"-->
					<span class="marginTop5">per page</span>
				</div>
				<div class="searchResultsNavigationColumn3">&nbsp;&nbsp;&nbsp;
				</div>
				<div class="searchResultsNavigationColumn2 floatRight">
					<!-- <span>Page:</span> -->
					<%--For displaying Previous link except for the 1st page --%>
					<c:if test="${currentPage != 1 && noOfPages gt 1}">
						<td><a
							href="<%=request.getContextPath()%>/employer/manageJobSeeker.html?folderId=${manageJobSeekerForm.folderId}&compare=true&page=${currentPage - 1}&next=${begin-1}">
								<img src="../resources/images/ArrowLeft.png"> Previous
						</a></td>
					</c:if>



					<c:forEach begin="${begin}" end="${noOfPages}" var="i">
						<c:choose>
							<c:when test="${currentPage eq i}">

								<span class="active">${i}</span>

							</c:when>
							<c:otherwise>
								<span class="active"> <c:if test="${i lt begin+10}">
										<a
											href="<%=request.getContextPath()%>/employer/manageJobSeeker.html?folderId=${manageJobSeekerForm.folderId}&compare=true&page=${i}&noOfPage=${manageJobSeekerForm.noOfPage}
													">${i}</a>
									</c:if></span>

							</c:otherwise>
						</c:choose>
					</c:forEach>

					<span> <c:if
							test="${noOfPages gt 1 && noOfPages != currentPage}">
							<a
								href="<%=request.getContextPath()%>/employer/manageJobSeeker.html?folderId=${manageJobSeekerForm.folderId}&compare=true&page=${currentPage+1}&next=${begin+1}&noOfPage=${manageJobSeekerForm.noOfPage}">Next
								<img src="../resources/images/ArrowRight.png">
							</a>
						</c:if></span>




				</div>
			</div>
			<!--button-->
			<div class="row marginTop10 paddingBottom5">
				<span><a href="#" class="btn_sm orange" id="compareSelected">Compare
						Selected</a><a href="#" id="moveToFolder" class="btn_sm orange">Move
						To Folder</a></span>
			</div>
			<div class="clearfix"></div>
			<div class="row marginTop10 FontSize11">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="grid" id="tb_manage_job_seeker">
					<tr class="LightGrayBG Height35">
						<td width="6%" align="center" valign="middle" class="">&nbsp;</td>
						<td width="29%" align="left" valign="middle"><strong>Name</strong></td>
						<td width="14%" align="center" valign="middle"><strong>Rating</strong></td>
						<td width="22%" align="center" valign="middle"><strong>Applicant
								Status</strong></td>
						<td width="11%" align="center" valign="middle"><strong>Saved</strong></td>
						<td width="18%" align="center" valign="middle"><strong>Actions</strong></td>
					</tr>
					<c:forEach items="${manageJobSeekerForm.manageJobSeekerDTOList}"
						var="resume" varStatus="status">

						<form:hidden
							path="manageJobSeekerDTOList[${status.index}].folderResumeId" />
						<form:hidden
							path="manageJobSeekerDTOList[${status.index}].resumeId"
							id="resumeId" />
						<tr class="Height35" id=${resume.folderResumeId}>
							<td align="center" valign="middle"><input type="checkbox"
								name="checkbox" id=${resume.folderResumeId
								}
							value="${resume.folderResumeId}"></td>
							<td align="left" valign="middle"><a
								href="${pageContext.request.contextPath}/employer/viewResume.html?resumeId=${resume.resumeId }">${resume.resumeName}</a></td>

							<td align="center" valign="middle"><c:forEach var="i"
									begin="1" end="${resume.rating}">
									<c:if test="${i>1}">
										</span>
									</c:if>
									<span class="starOn" id="${i}"
										onclick="onChangeRatining(this);" />
								</c:forEach> <c:forEach var="j" begin="${resume.rating+1}"
									end="${resume.rating + (5-resume.rating)}">
									<c:if test="${j>resume.rating}">
										</span>
									</c:if>
									<span class="starOff" id="${j}"
										onclick="onChangeRatining(this);" />

								</c:forEach></td>
							<td align="center" valign="middle"><form:select
									onchange="onChangeAppStatus(this);"
									path="manageJobSeekerDTOList[${status.index}].applicationStatus"
									class="jb_input3 marginLeft10 marginTopBottom0" name="select"
									items="${appStatusList}" itemLabel="optionName"
									itemValue="optionId">
								</form:select></td>
							<td align="center" valign="middle">${resume.savedDate}</td>
							<td align="center" valign="middle"><a
								href="${pageContext.request.contextPath}/employer/viewResume.html?resumeId=${resume.resumeId }"><img
									src="../resources/images/View.png" title="View resume"
									width="20" height="20" alt="view"></a>&nbsp;<a
								href="${pageContext.request.contextPath}/employer/downloadResume.html?resumeId=${resume.resumeId }"><img
									src="../resources/images/Download.png" width="20" height="20"
									alt="download" title="Download resume"></a>&nbsp;<a
								href="${pageContext.request.contextPath}/employer/printResume.html?resumeId=${resume.resumeId }"><img
									src="../resources/images/Print2.png" title="Print Resume"
									width="20" height="20" alt="print"></a>&nbsp;<a
								onclick="sendResumeToFrd(${resume.resumeId}, '${resume.resumeName}','<%= request.getContextPath() %>')"><img
									src="../resources/images/EmailOrange.png"
									title="Forward resume" width="20" height="20" alt="email"></a>&nbsp;<a
								href="#"><img src="../resources/images/Delete.png"
									title="Delete Resume" width="20" height="20" alt="delete"></a></td>
						</tr>
					</c:forEach>

				</table>
			</div>
			<div class="row marginTop15 paddingBottom5">
				<span><a href="#" class="btn_sm orange"
					id="compareSelectedLower">Compare Selected</a><a href="#"
					id="moveToFolderlower" class="btn_sm orange">Move To Folder</a></span>
			</div>
			<a href="" id="moveToFolderPopup" style="display: none;">Move</a>
			<div class="clearfix"></div>
			<div class="searchResultsNavigation width98P FloatLeft marginTop20">
				<div class="searchResultsNavigationColumn1">

					<!--Added Class "marginTop5"-->
					<span>Results viewable:</span> <span class="Padding0"><select
						id="noOfPageLowerId" class="jb_input4 margin0">

							<option value="10">10</option>
							<option value="20">20</option>
							<option value="30">30</option>
							<option value="40">40</option>
							<option value="50">50</option>
							<select></span>
					<!--Added Class "marginTop5"-->
					<span class="marginTop5">per page</span>
				</div>
				<div class="searchResultsNavigationColumn3">&nbsp;&nbsp;&nbsp;
				</div>
				<div class="searchResultsNavigationColumn2 floatRight">
					<!-- <span>Page: </span> -->

					<%--For displaying Previous link except for the 1st page --%>
					<c:if test="${currentPage != 1 && noOfPages gt 1}">
						<td><a
							href="<%=request.getContextPath()%>/employer/manageJobSeeker.html?folderId=${manageJobSeekerForm.folderId}&compare=true&page=${currentPage - 1}&next=${begin-1}&noOfPage=${manageJobSeekerForm.noOfPage}">
								<img src="../resources/images/ArrowLeft.png"> Previous
						</a></td>
					</c:if>



					<c:forEach begin="${begin}" end="${noOfPages}" var="i">
						<c:choose>
							<c:when test="${currentPage eq i}">

								<span class="active">${i}</span>

							</c:when>
							<c:otherwise>
								<span class="active"> <c:if test="${i lt begin+10}">
										<a
											href="<%=request.getContextPath()%>/employer/manageJobSeeker.html?folderId=${manageJobSeekerForm.folderId}&compare=true&page=${i}&noOfPage=${manageJobSeekerForm.noOfPage}
													">${i}</a>
									</c:if></span>

							</c:otherwise>
						</c:choose>
					</c:forEach>

					<span> <c:if
							test="${noOfPages gt 1 && noOfPages != currentPage }">
							<a
								href="<%=request.getContextPath()%>/employer/manageJobSeeker.html?folderId=${manageJobSeekerForm.folderId}&compare=true&page=${currentPage+1}&next=${begin+1}&noOfPage=${manageJobSeekerForm.noOfPage}">Next
								<img src="../resources/images/ArrowRight.png">
							</a>
						</c:if></span>
				</div>
			</div>
		</div>
		</div>
		</div>
	</form:form>
</body>
</html>