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
		 // $("#tb_manage_job_seeker").tablesorter(); 
		 
		 $("#myFolder").click(function(){
			 $("#all").toggleClass('refineResultsItem plus refineResultsItem minus');
			//attr('class', 'refineResultsItem plus');
		 });
		$(".folderdetail").click(
						function() {
							val = $(this).attr("id");
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
				$(".refineResultsItem").click(
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
				$('#compareSelectedLower').click(function() {
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
				
				$("#div_manage_job_seeker img")
								.click(
										function(event) {
											var action = $(this).attr("alt");
											var rowObj = $(this).parent().parent().parent();
											var folderNm = $(this).attr("id");
											//alert(folderNm);
											switch (action) {
											case "Add": {

												$("#addBtn").replaceWith("<div class='buttonRow' >" +
														" <input type ='text' id='newFolder' class='addButtonRow' value='New Folder' onClick='resetVal();' onKeydown='Javascript: if (event.keyCode==13) checkevent();'/> "
														+"<a href='#' class='white link_color1_emphasized' onClick='Javascript: checkevent();' id='newFolderLink' value='Add'>Add</a></div>");
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
											}

										});
						jQuery(".megamenu").megamenu();
					});
						function checkevent(){
						var folderName=	document.getElementById('newFolder').value;
							$.ajax({url: "${pageContext.request.contextPath}/employer/addFolder.html?folderName="+folderName,
								data:$('#manageJobSeeker').serialize(),
								type: "POST" ,
								success : function(data) {
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

							var rowObj = $(eleObj).parent().parent();
							var resumeId =  $(rowObj).attr("id");
							val = eleObj.id;
							$.ajax({url : "${pageContext.request.contextPath}/employer/updateRating.html?rating="
								+ val+"&resumeId="+resumeId,
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
									" <input type ='text' id='"+folderId+"' class='addButtonRow' value='"+folderName+"' onClick='resetValRename("+folderId+");' onKeydown='Javascript: if (event.keyCode==13) checkEventRename("+folderId+");'/> "
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
						<div id="errorMsg" class="validationMsg">
						<c:if test="${errorMsg != null}">
						${errorMsg}
						</c:if>
						
						</div>
						<div class="clearfix"></div>
						<div class="content_columns_search_results">

							<div id="folderDiv">
								<jsp:include page="manageJobSeekerFolder.jsp"></jsp:include>
							</div>
							
							<!-- column1 -->
							<div class="column2">

								<div class="searchResultsNavigation width98P">
									<div class="searchResultsNavigationColumn1">

										<!--Added Class "marginTop5"-->
										<span>Results viewable:</span> <span class="Padding0">
											<form:select path="noOfPage" name="noOfPage"
												class="jb_input4 margin0">
												<form:option value="20">20</form:option>
												<form:option value="30">30</form:option>
												<form:option value="40">40</form:option>
												<form:option value="50">50</form:option>
											</form:select>
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
												href="#<%-- <%=request.getContextPath()%>/employer/manageJobPost.html?page=${currentPage - 1}&next=${begin-10} --%>">
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
																href="#<%-- <%=request.getContextPath()%>/employer/manageJobPost.html?page=${i} --%>">${i}</a>
														</c:if></span>

												</c:otherwise>
											</c:choose>
										</c:forEach>

										<span> <c:if
												test="${noOfPages gt 1 && noOfPages != currentPage}">
												<a
													href="<%=request.getContextPath()%>/employer/manageJobPost.html?page=${currentPage + 1}&jobStatus=${jobPostForm.statusValue}&next=${begin+10}">Next
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
										<span>Results viewable:</span> <span class="Padding0"><form:select
												path="noOfPageLower" name="noOfPageLower"
												class="jb_input4 margin0">
												<form:option value="20">20</form:option>
												<form:option value="30">30</form:option>
												<form:option value="40">40</form:option>
												<form:option value="50">50</form:option>
											</form:select> </span>
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
												href="<%=request.getContextPath()%>/employer/manageJobPost.html?page=${currentPage - 1}&jobStatus=${statusValue}&next=${begin-10}">
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
																href="<%=request.getContextPath()%>/employer/manageJobPost.html?page=${i}&jobStatus=${jobPostForm.statusValue}">${i}</a>
														</c:if></span>

												</c:otherwise>
											</c:choose>
										</c:forEach>

										<span> <c:if
												test="${noOfPages gt 1 && noOfPages != currentPage }">
												<a
													href="<%=request.getContextPath()%>/employer/manageJobPost.html?page=${currentPage + 1}&jobStatus=${jobPostForm.statusValue}&next=${begin+10}">Next
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