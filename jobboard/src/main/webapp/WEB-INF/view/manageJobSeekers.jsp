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
		 noOfPageValue=$("#noOfPage").val();
		 $("#noOfPageId").val(noOfPageValue);
		 $("#noOfPageLowerId").val(noOfPageValue);
		
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
				
				$('#compareSelected').click(function() {
					$("#errorMsg").html("");
					folderId=$("#folderId").val();
					var val = [];
					$(':checkbox:checked').each(function(i) {
						val[i] = $(this).val();
					});
					if(val != "" &&  val.length > 4){
						alert("Total selection of resume must be less than four");
					}
					if (val != "" && val.length > 1) {
						$('#selectedRow').val(val);
						 $("#manageJobSeeker").attr("action", "${pageContext.request.contextPath}/employer/compareResume.html?selectedVal="+val+"&folderId="+folderId);
						$("#manageJobSeeker").attr("method","POST");
						$("#manageJobSeeker").submit(); 
					} else {
						alert("Please select atleast two resume to continue");
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
				
				
				$('#noOfPageId').change(
						function() {
							val = $(this).val();
							$('#noOfPageLowerId').val(val);
							$("form").attr(
									"action",
									"${pageContext.request.contextPath}/employer/manageJobSeeker.html?folderId=-1&noOfPage="+val);
							$("form").submit();
						});
				$('#noOfPageLowerId').change(
						function() {
							val = $(this).val();
							$('#noOfPageId').val(val);
							$("form").attr(
									"action",
									"${pageContext.request.contextPath}/employer/manageJobSeeker.html?folderId=-1&noOfPage="+val);
							$("form").submit();
						});
						jQuery(".megamenu").megamenu();
					});
						function checkevent(){
						var folderName=	document.getElementById('newFolder').value;
							$.ajax({url: "${pageContext.request.contextPath}/employer/addFolder.html?folderName="+folderName,
								data:$('#manageJobSeeker').serialize(),
								type: "POST" ,
								success : function(data) {
									$("#folderId").val(9999);
										$("#folderDiv").html(data);									
								}	 
							});
						}/* 
						function saveNewFolder(){
							var folderName=	document.getElementById('newFolder').value;
								$.ajax({url: "${pageContext.request.contextPath}/employer/addFolder.html?folderName="+folderName,
									type: "POST"
								});
							} */
						function resetVal(){
							document.getElementById('newFolder').style.borderColor="gray";
							document.getElementById('newFolder').style.border="1px solid #e0e0e0";
							document.getElementById('newFolder').value="";
							
							}
						function resetValRename(val){
							document.getElementById(val).style.borderColor="gray";
							document.getElementById(val).style.border="1px solid #e0e0e0";
							document.getElementById(val).select();
							
							}
						function checkEventRename(val){
							var folderName=	document.getElementById(val).value;
							var folderId=val;
								$.ajax({url: "${pageContext.request.contextPath}/employer/renameFolder.html?folderName="+folderName+"&folderId=" + folderId,
									data:$('#manageJobSeeker').serialize(),
									type: "POST" ,
									success : function(data) {
										$("#folderDiv").html(data);
									}	 
								});
							}
						function onChangeAppStatus(eleObj) {
							var rowObj = $(eleObj).parent().parent();
							var resumeId =  $(rowObj).attr("id");
							val = eleObj.value;
							$.ajax({url : "${pageContext.request.contextPath}/employer/updateJobSeeker.html?appStatus="
								+ val+"&resumeId="+resumeId,
								data:$('#manageJobSeeker').serialize(),
								type: "POST"						
								
							});						
						}
						function onChangeRatining(eleObj) {
								folderId=$("#folderId").val();
							var rowObj = $(eleObj).parent().parent();
							var resumeId =  $(rowObj).attr("id");
							val = eleObj.id;
							$.ajax({url : "${pageContext.request.contextPath}/employer/updateRating.html?rating="
								+ val+"&resumeId="+resumeId+"&folderId="+folderId,
				    			data:$('#manageJobSeeker').serialize(),
								type: "POST",
								success: function(data){ 
									if(data.failure!=null){
									}else{
										$("#contentDiv").html(data);
									}
								}
								
							});
						}
						function renameCall(folderId,folderName){
							$("#addBtn").attr('hidden','true');
							$("#"+folderId).replaceWith("<div class='addButtonRow' >" +
									" <input type ='text' id='"+folderId+"' class='addButtonRow' value='"+folderName+"' onClick='resetValRename("+folderId+");' onBlur='Javascript: checkEventRename("+folderId+");'/> "
									+"</div>");
							document.getElementById(folderId).select();
							document.getElementById(folderId).style.borderColor="red";
							document.getElementById(folderId).style.borderStyle="solid";
							
						}
						function sendResumeToFrd(resumeId,resumeName,context) {	
							var currentUrl = window.location.pathname;
							$.nmManual(context+'/employer/sendtofriend.html?id='+resumeId+'&resumeName='+resumeName+'&currentUrl='+currentUrl);
						}
</script>
<script type="text/javascript" src="../resources/js/expandCollapse.js"></script>

<script src="../resources/js/jquery.dataTables.nightly.js"></script>
<script src="../resources/js/searchResultsdatatable.js"></script>

<script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>

</head>
<body class="job_board">
	<form:form commandName="manageJobSeekerForm" id="manageJobSeeker">
		<form:hidden path="selectedRow" id="selectedRow" />
		<form:hidden path="rating" id="ratingId"/>
		<form:hidden path="noOfPage" />
		<form:hidden path="rating" id="ratingId"/>
		<div class="ad_page_top">
			${adPageTop }
		</div>
		<div class="main_wrapper_outside">
			<div class="main_wrapper_inside">


				<div class="main">
					<jsp:include page="../templates/templates_header.jsp"></jsp:include>
					<div class="clearfix"></div>
					<!--nav-->
					<!--Start:MidContant-->
					<div class="MidContent_Wrapper floatLeft">
						<div class="popupHeader Padding0  OrangeBG marginBottom5">
							<h2>MANAGE JOB-SEEKERS</h2>
							<span class="floatRight marginRight10"><a
								href="<%=request.getContextPath()%>/employer/employerDashBoard.html"
								class="link_color3_emphasized FontSize12 FontWeight">Back to
									Dashboard</a></span></span>
						</div>
						
						<div class="clearfix"></div>
						<div class="content_columns_search_results">

							<div id="folderDiv">
								<jsp:include page="manageJobSeekerFolder.jsp"></jsp:include>
							</div>
							
							<!-- column1 -->
							<div class="column2">
								<div id="errorMsg" class="FormErrorDisplayText01 marginBottom5">
														<c:if test="${errorMsg != null}">
														<c:out value="${errorMsg}"></c:out>
														</c:if>
														
														</div>
								<div class="searchResultsNavigation width98P">
									<div class="searchResultsNavigationColumn1">

										<!--Added Class "marginTop5"-->
										<span>Results viewable:</span> <span class="Padding0">
											<select id="noOfPageId" 
												class="jb_input4 margin0">
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
												href="<%=request.getContextPath()%>/employer/manageJobSeeker.html?folderId=${manageJobSeekerForm.folderId}&page=${currentPage - 1}&next=${begin-10}">
													<img src="../resources/images/ArrowLeft.png">
													Previous
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
													href="<%=request.getContextPath()%>/employer/manageJobSeeker.html?folderId=${manageJobSeekerForm.folderId}&page=${i}
													">${i}</a>
														</c:if></span>

												</c:otherwise>
											</c:choose>
										</c:forEach>

										<span> <c:if
												test="${noOfPages gt 1 && noOfPages != currentPage}">
												<a
													href="<%=request.getContextPath()%>/employer/manageJobSeeker.html?folderId=-1&page=${currentPage - 1}&next=${begin-10}">Next
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
								<div id="contentDiv">
									<jsp:include page="manageJobSeekerContent.jsp"></jsp:include>
								</div>
								<div class="row marginTop15 paddingBottom5">
									<span><a href="#" class="btn_sm orange" id="compareSelectedLower">Compare
											Selected</a><a href="#" id="moveToFolderlower"
										class="btn_sm orange">Move To Folder</a></span>
								</div>
								<a href="" id="moveToFolderPopup" style="display:none;">Move</a>
								<div class="clearfix"></div>
								<div
									class="searchResultsNavigation width98P FloatLeft marginTop20">
									<div class="searchResultsNavigationColumn1">

										<!--Added Class "marginTop5"-->
										<span>Results viewable:</span> <span class="Padding0"><select
												id="noOfPageLowerId"
												class="jb_input4 margin0">
												
												<option value="10">10</option>
												<option value="20">20</option>
												<option value="30">30</option>
												<option value="40">40</option>
												<option value="50">50</option>
											<select> </span>
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
												href="<%=request.getContextPath()%>/employer/manageJobSeeker.html?folderId=-1&page=${currentPage - 1}&next=${begin-10}">
													<img src="../resources/images/ArrowLeft.png">
													Previous
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
													href="<%=request.getContextPath()%>/employer/manageJobSeeker.html?folderId=${manageJobSeekerForm.folderId}&page=${i}
													">${i}</a>
														</c:if></span>

												</c:otherwise>
											</c:choose>
										</c:forEach>

										<span> <c:if
												test="${noOfPages gt 1 && noOfPages != currentPage }">
												<a
													href="<%=request.getContextPath()%>/employer/manageJobSeeker.html?folderId=-1&page=${currentPage - 1}&next=${begin-10}">Next
													<img src="../resources/images/ArrowRight.png">
												</a>
											</c:if></span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--Start:MidContant-->
					<div class="clearfix"></div>
					<!-- content_wrapper -->
					<div class="ad_wrapper">
						${adPageBtm }
					</div>
					<!-- ad_wrapper -->

				</div>
				<!-- main -->

			</div>
			<!-- end main_wrapper_inside -->
		</div>
		<!-- end main_wrapper_outside -->
		<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
		<!-- footer_wrapper -->
	</form:form>
</body>
</html>