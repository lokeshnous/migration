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
						}
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
							$(".AddNewBtn").attr('hidden','true');
							$("#div_manage_job_seeker img").each(function(event) {
								$(this).attr('hidden','true');
							});
							$("#"+folderId).replaceWith("<div class='addButtonRow' >" +
									" <input type ='text'size='16' maxlength='16' id='"+folderId+"' class='addButtonRow' value='"+folderName+"' onClick='resetValRename("+folderId+");' onBlur='Javascript: checkEventRename("+folderId+");'onKeydown='Javascript: if (event.keyCode==13) checkEventRename("+folderId+");'/> "
									+ "<div class='check floatRight' title='save' name='check'> </div> </div><br>");
							document.getElementById(folderId).select();
							document.getElementById(folderId).style.borderColor="red";
							document.getElementById(folderId).style.borderStyle="solid";
							
						}
						function sendResumeToFrd(resumeId,resumeName,context) {	
							var currentUrl = window.location.pathname;
							$.nmManual(context+'/employer/sendtofriend.html?id='+resumeId+'&resumeName='+resumeName+'&currentUrl='+currentUrl);
						}
						function moveToFolder(){
							var val = [];
							$(':checkbox:checked').each(function(i) {
								val[i] = $(this).val();
							});
							if (val != "") {
								$('#selectedRow').val(val);
								$('#moveToFolderPopup').attr("href","${pageContext.request.contextPath}/employer/moveToFolder.html?folderId=0&selectedVal="+val);
								$.nmManual($('#moveToFolderPopup').attr("href"),  {closeOnEscape: true, showCloseButton: true,resizable: true, sizes:{initW: 50, initH: 50, minW: 50, minH:50,  w: 50, h: 50}});
								
							} else {
								alert("Please select a resume!");
							}
						}
</script>

</head>
<body class="job_board">
	<form:form commandName="manageJobSeekerForm" id="manageJobSeeker">
		<form:hidden path="selectedRow" id="selectedRow" />
		<form:hidden path="rating" id="ratingId" />
		<form:hidden path="rating" id="ratingId" />
		<form:hidden path="folderId" id="folderId" />
		<div class="ad_page_top">${adPageTop }</div>
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
					
							<div id="contentDiv">
								<jsp:include page="manageJobSeekerContent.jsp"></jsp:include>
							</div>
							
							<!--Start:MidContant-->
							<div class="clearfix"></div>
							<!-- content_wrapper -->
							<div class="ad_wrapper">${adPageBottom }</div>
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