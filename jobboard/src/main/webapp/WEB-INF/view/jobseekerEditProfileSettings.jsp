<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />
<script type="text/javascript">
	jQuery(document).ready(function() {
		
 		$('#save').click(function(){			
 			
			$.ajax({url:"/jobboard/jobseekerregistration/updateJobSeekerProfile.html",
				data:$('#editProfileSettingsId').serialize(),
				type:"POST",
				success: function(data) {
					parent.$.nmTop().close();
				 },
			});
		}); 
		
		jQuery(".megamenu").megamenu();
	});
</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>Edit Profile Settings</h2>
			<img src="../resources/images/Close.png" width="19" height="19"
				alt="" onclick="parent.$.nmTop().close();">
		</div>

		<div class="popUpContainerWrapper">
			<form:form action="/jobboard/jobseekerregistration/updateJobSeekerProfile.html" method="POST"
				commandName="registerForm" enctype="multipart/form-data" id="editProfileSettingsId">
				<c:forEach items="${registerForm.listProfAttribForms}" var="profAttrib" varStatus="status">			
					<c:if test="${profAttrib.strLabelName != 'Subscriptions'}">				
 								<c:if test="${profAttrib.strSectionName == 'Contact Information1'}">
		 							<div class="row marginTop20 paddingBottom10">
										<h3 class="marginLeft10">Employment information</h3>
									</div>
 								</c:if> 
 								<c:if test="${profAttrib.strSectionName == 'Employment information1'}">
 									<div class="row marginTop20 paddingBottom10">
 										<h3 class="marginLeft10">Employment information</h3>
 									</div>
 								</c:if> 
 								<c:if test="${profAttrib.strSectionName == 'Equal Opportunity1'}">
 									<div class="row marginTop20 paddingBottom10">
 										<h3 class="marginLeft10">Equal Opportunity / Affirmative Action</h3>
 									</div>
 								</c:if> 
 								<c:if test="${profAttrib.strSectionName == 'Subscriptions1'}">
 									<div class="row marginTop20 paddingBottom10">
 										<h3 class="marginLeft10">Subscriptions</h3>
 									</div>
 								</c:if>  								
									<c:if test="${profAttrib.strAttribType == 'TextBox'}">
										<div class="rowEvenSpacing">
											<c:if test="${profAttrib.strLabelName != 'Street Address1'}" >
												<span class="lableText3"><c:out value="${profAttrib.strLabelName}" />:</span>
											</c:if>
											<c:if test="${profAttrib.strLabelName == 'Street Address1'}" >
												<span class="lableText3"></span>
											</c:if>
											 <form:input  path="listProfAttribForms[${status.index}].strLabelValue" class="job_seeker_password textBox350"/>
											 <c:if test="${not empty profAttrib.strToolTip}">
												 <div class="toolTip marginTop6 marginLeft5">
														<span class="classic"><c:out value="${profAttrib.strToolTip}" /></span>
												</div>
											</c:if>
											<span class="required">(Required)</span>
										</div>
									</c:if>
									
									<c:if test="${profAttrib.strAttribType == 'Dropdown'}">
										<div class="row">
											<span class="lableText3"><c:out value="${profAttrib.strLabelName}" />:</span>
											<form:select path="listProfAttribForms[${status.index}].strLabelValue" class="jb_input3 jb_input_width3">
												<form:option value="0" label="Select" />
												<form:options items="${profAttrib.dropdown}" itemValue="optionId"
													itemLabel="optionName" />
											</form:select>
											<span class="required">(Required)</span>
										</div>
									</c:if>
									<c:if test="${profAttrib.strAttribType == 'CheckBox'}">
										<div class="row paddingBottom10 marginLeft10">I would like
											the following sent to me so I can stay up to date with the
											latest healthcare news and information:</div>
			
										<div class="centerAlign ">
											<ul>		
												<c:forEach items="${profAttrib.dropdown}" var="dropdown" varStatus="index">
													<li>
														<div>
															<form:checkbox path="listProfAttribForms[${status.index}].strLabelValue"
																label="${dropdown.optionName}"
																value="${dropdown.optionId}"
																cssStyle="width:20px" />
														</div>
													</li>
												</c:forEach>
											</ul>
										</div>	
									</c:if>	
					</c:if> 
				</c:forEach>
				<div class="rowEvenNewSpacing marginTop20 paddingBottom10">
					<span class="floatLeft marginTop10">
					<input type="button" value="Save" class="btn_sm orange" id="save"/>
					<a href="#" class="btn_sm orange" onclick="parent.$.nmTop().close();">Cancel</a></span>
				</div>
				<div class="clearfix"></div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>