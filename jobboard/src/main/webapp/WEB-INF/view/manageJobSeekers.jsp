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
		$("#moveToFolderPopup").displaypopup("#moveToFolderPopup","20","20");
		
		$('#appStatus').change(
				function() {
					val = $(this).val();
					$.ajax({url : "${pageContext.request.contextPath}/employer/updateJobSeeker.html?appStatus="
						+ val,
		    			data:$('#manageJobSeeker').serialize(),
						type: "POST",
						success : function(data) {
							if(data.failure!=null){
								//alert("Fail");
								//$("#jobOwnerErrorMsg").html("<span>"+data.failure+"</span>");	
							}else{
								//$("#jobOwnerErrorMsg").html("<span>"+data.success+"</span>");
								//alert("success");
							 // $("#manageAccPerm").click();
							}
						}					
							
						
					});
										
				});
		$(".folderdetail").click(
						function() {
							val = $(this).attr("id");
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
				$('#moveToFolder').click(function() {
					var val = [];
					$(':checkbox:checked').each(function(i) {
						val[i] = $(this).val();
					});
					if (val != "") {
						$('#selectedRow').val(val);
						$('#moveToFolderPopup').attr("href","../employer/moveToFolder.html?folderId=0&selectedVal="+val);
						moveToFolderPopup.click();
					} else {
						alert("Please select a resume");
					}

				});
				$('#moveToFolderlower').click(function() {
					var val = [];
					$(':checkbox:checked').each(function(i) {
						val[i] = $(this).val();
					});
					if (val != "") {
						$('#selectedRow').val(val);
						$('#moveToFolderPopup').attr("href","../employer/moveToFolder.html?folderId=0&selectedVal="+val);
						moveToFolderPopup.click();
					} else {
						alert("Please select a resume");
					}

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
	<form:hidden path="folderId"/>
		<div class="ad_page_top">
			<img src="../resources/images/ads/banner_ad_fpo.png" />
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
							<div class="column1">
							<%--<div id="folderDiv">								
								 <jsp:include page="manageJobSeekerFolderView.jsp"></jsp:include> 
								</div>--%>

								 <div class="section">
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


								</div>


							</div>
							<!-- column1 -->
							<div class="column2">

								<div class="searchResultsNavigation width98P">
									<div class="searchResultsNavigationColumn1">

										<!--Added Class "marginTop5"-->
										<span class="marginTop5">Results viewable:</span> <span>
											<select name="results" class="jb_input4">
												<option value="20">20</option>
												<option value="30">30</option>
												<option value="40">40</option>
												<option value="50">50</option>
												<option value="All">All</option>
										</select>
										</span>
										<!--Added Class "marginTop5"-->
										<span class="marginTop5">per page</span>
									</div>
									<div class="searchResultsNavigationColumn3">&nbsp;&nbsp;&nbsp;
									</div>
									<div class="searchResultsNavigationColumn2 floatRight">
										<span>Page:</span> <span class="active">1</span> <span><a
											href="">2</a></span> <span><a href="">3</a></span> <span><a
											href="">4</a></span> <span><a href="">5</a></span> <span><a
											href="">6</a></span> <span><a href="">7</a></span> <span><a
											href="">8</a></span> <span><a href="">9</a></span> <span><a
											href="">Next<img src="../resources/images/ArrowRight.png"></a></span>
									</div>
								</div>
								<!--button-->
								<div class="row marginTop10 paddingBottom5">
									<span><a href="#" class="btn_sm orange">Compare
											Selected</a><a href="#" id="moveToFolder" class="btn_sm orange">Move To Folder</a></span>											
								</div>
								
								<div class="clearfix"></div>
								<div id="contentDiv">								
								<jsp:include page="manageJobSeekerContent.jsp"></jsp:include>
								</div>
								<div class="row marginTop15 paddingBottom5">
									<span><a href="#" class="btn_sm orange">Compare
											Selected</a><a href="#" id="moveToFolderlower" class="btn_sm orange">Move To Folder</a></span>
								</div>
								<a
									href=""
									id="moveToFolderPopup"  />
								<div class="clearfix"></div>
								<div
									class="searchResultsNavigation width98P FloatLeft marginTop20">
									<div class="searchResultsNavigationColumn1">

										<!--Added Class "marginTop5"-->
										<span class="marginTop5">Results viewable:</span> <span>
											<select name="results" class="jb_input4">
												<option value="20">20</option>
												<option value="30">30</option>
												<option value="40">40</option>
												<option value="50">50</option>
												<option value="All">All</option>
										</select>
										</span>
										<!--Added Class "marginTop5"-->
										<span class="marginTop5">per page</span>
									</div>
									<div class="searchResultsNavigationColumn3">&nbsp;&nbsp;&nbsp;
									</div>
									<div class="searchResultsNavigationColumn2 floatRight">
										<span>Page:</span> <span class="active">1</span> <span><a
											href="">2</a></span> <span><a href="">3</a></span> <span><a
											href="">4</a></span> <span><a href="">5</a></span> <span><a
											href="">6</a></span> <span><a href="">7</a></span> <span><a
											href="">8</a></span> <span><a href="">9</a></span> <span><a
											href="">Next<img src="../resources/images/ArrowRight.png"></a></span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--Start:MidContant-->
					<div class="clearfix"></div>
					<!-- content_wrapper -->
					<div class="ad_wrapper">
						<img src="../resources/images/ads/banner_ad_fpo.png" />
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