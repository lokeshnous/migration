<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>ADVANCE Heathcare Jobs</title>
		
		
		<script type="text/javascript">
		$("#jobApply").click(function() {
			var valueSelected=false;
			var cletterSelected=false;
			var cletterId;
			
			$("#selectCletterId input[type='radio']").each(function(){
				if($(this).is(':checked')){
					cletterSelected=true;
					cletterId=$(this).closest('tr').attr("id");
				}
			});
			
			$("#selectResumeId input[type='radio']").each(function(){
				if($(this).is(':checked')){
					$("#jobApply").attr('disabled','true');
					$('#actionInfo').html("Processing...");
					valueSelected=true;
					var resumeId = $(this).closest('tr').attr("id");
					var jobId = $(this).closest('tr').attr("class");
					var userId = $(this).closest('tr').attr("dir");
					var position=$(this).closest('tr').attr("char");
					if(!cletterSelected){
						cletterId='none';
					}
		$.ajax({
				url :  $("#contextPath").val()+'/search/applyJob.html?id='+jobId+'&currentUrl=null&resumeId='+resumeId+'&userId='+userId+'&cletterId='+cletterId,
			success : function(data) {
					$.each(data, function(key, val) {
						if (key == "AjaxMSG") {
							if(position == "top"){
								$('#topjobActionInfo'+jobId).html(val);
								$('#topjobActionInfo').html(val);
								$('#bottomjobActionInfo').html('');
							}
							if(position == "bottom"){
								$('#topjobActionInfo'+jobId).html('');
								$('#topjobActionInfo').html('');
								$('#bottomjobActionInfo').html(val);
							}
							
							parent.$.nmTop().close();
						}
					});
				},
				error : function(data) {
				},
				complete : function(data) {
				}
				
			});
				}
				
			});
			if(!valueSelected){
				$('#actionInfo').html("Please select a resume to apply the job");
				$("#jobApply").removeAttr("disabled");
			}
		});
	
</script>


		</head>
		<body class="job_board">
               
        <div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
		<h2>Apply To Job</h2> 
		
			<img id="closeCheckOut" src="<%= request.getContextPath() %>/resources/images/Close.png" class="nyroModalClose cursor" title="Close" alt="Close"/>
		</div>
		<div class="popupHeader marginTop10">
		<h2>SELECT RESUME</h2>
		</div>
		<div class="popUpContainerWrapper">
			<form:form method="POST" action="" id="selectResume">
				<div class="rowEvenSpacingMargin0">
				<div id="selectResumeErrorMsg" class="FormErrorDisplayText">
					
				</div>
				<div class="FormErrorDisplayText" id="actionInfo" ></div>
				<div id="selectResumeId" class="row">
					<table id="tb_select_resume" width="100%" border="0"
						cellspacing="0" cellpadding="0" class="grid">
						<thead>
							<tr class="borderTopNone">
							<!-- <th width="20%" align="center" scope="col"></th> -->
								<th width="36%" align="left" scope="col">Resume Name</th>
								<th width="25%" align="center" scope="col">Visibility*</th>
								<!-- <th width="18%" align="center" scope="col">Modified</th> -->
							</tr>
						</thead>
						<tbody>
						
							<c:forEach items="${resumeList}" var="resume" varStatus="status">
								<tr id="${resume.uploadResumeId}" dir="${userId}" class="${jobId}" char="${position}">
								<td align="Left"><input
									name="radio" type="radio" id="radio1" value="radio"
									class="marginRight5"> <label for="radio"
									class="link_color2_selected">${resume.resumeName}</label></td>
									<%-- <td>${resume.resumeName}</td> --%>
									<td align="center"><label>
											${resume.resumeVisibility}
									</label></td>
									<%-- <td align="center">${resume.updateDt}</td> --%>
								</tr>
							</c:forEach>
							
						</tbody>
					</table>
					</div>
					
				</div>
			</form:form>
			<form action="">
					<div id="selectCletterId" class="row">
					<c:if test="${not empty coverLetterList}">
					<div class="popupHeader marginTop20">
			<h2>SELECT Cover Letter</h2>
		</div>
					
					
					<table id="tb_select_cletter" width="100%" border="0"
						cellspacing="0" cellpadding="0" class="grid">
						<thead>
							<tr class="borderTopNone">
							<!-- <th width="20%" align="center" scope="col"></th> -->
								<th width="36%" align="left" scope="col">Cover Letter Name</th>
							</tr>
						</thead>
						<tbody>
						
							<c:forEach items="${coverLetterList}" var="letter" varStatus="status">
								<tr id="${letter.coverletterId}" dir="${userId}" class="${jobId}" char="${position}">
								<td align="Left"><input
									name="radio" type="radio" id="radio1" value="radio"
									class="marginRight5"> <label for="radio"
									class="link_color2_selected">${letter.name}</label></td>
								</tr>
							</c:forEach>
							
						</tbody>
					</table>
					</c:if>
					</div>
					</form>
					<input type="button" value="Apply" id="jobApply" class="orange cursor marginTop20"/>
		</div>
		<div class="clearfix"></div>
	</div>
</body>
</html>