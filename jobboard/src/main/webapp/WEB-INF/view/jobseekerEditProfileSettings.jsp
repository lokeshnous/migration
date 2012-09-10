<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />
<script type="text/javascript">
	jQuery(document).ready(function() {
		
 		$('#save').click(function(){			
 			
			$.ajax({url:"${pageContext.request.contextPath}/jobseekerregistration/updateJobSeekerProfile.html",
				data:$('#editProfileSettingsId').serialize(),
				type:"POST",
				success: function(data) {
					if(data == ''){
						alert("Data saved successfully !");
						parent.$.nmTop().close();
						window.location.reload();
					}else{
						$("#errmsg").html(data);
					}
				 },
			});
		}); 
		
		jQuery(".megamenu").megamenu();
	});
</script>
<script type="text/javascript">
		    function cancelProcess(){
		    	parent.$.nmTop().close();
		    }		
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
			<div id="errmsg" class="validationMsg">
				</div>
			<form:form action="../jobseekerregistration/updateJobSeekerProfile.html" method="POST" commandName="registerForm"  id="editProfileSettingsId">
					<c:forEach items="${registerForm.listProfAttribForms}" var="profAttrib" varStatus="status">
						<c:if test="${profAttrib.strLabelName == 'First Name'}">
							<div class="rowEvenNewSpacing">
								<span class="lableText3">First Name:</span>
								<form:input path="listProfAttribForms[${status.index}].strLabelValue" class="job_seeker_password textBox350" />
								<span class="required">(Required)</span>
							</div>
						</c:if>
						<c:if test="${profAttrib.strLabelName == 'Middle Name'}">		
							<div class="rowEvenNewSpacing">
								<span class="lableText3">Middle Name:</span>
								<form:input path="listProfAttribForms[${status.index}].strLabelValue"
									class="job_seeker_password textBox350" />
							</div>
						</c:if>
						<c:if test="${profAttrib.strLabelName == 'Last Name'}">	
							<div class="rowEvenNewSpacing">
								<span class="lableText3">Last Name:</span>
								<form:input path="listProfAttribForms[${status.index}].strLabelValue" class="job_seeker_password textBox350" />
								<span class="required">(Required)</span>
							</div>
						</c:if>
						<c:if test="${profAttrib.strLabelName == 'Street Address'}">
							<div class="rowEvenNewSpacing">
								<span class="lableText3">Street Address:</span>
								<form:input path="listProfAttribForms[${status.index}].strLabelValue"
									class="job_seeker_password textBox350" />
								<span class="required">(Required)</span>
			
							</div>
						</c:if>
						<c:if test="${profAttrib.strLabelName == 'Street Address1'}">
							<div class="rowEvenNewSpacing">
								<span class="lableText3"></span>
								<form:input path="listProfAttribForms[${status.index}].strLabelValue"
									class="job_seeker_password textBox350" />
								<span class="required"></span>
							</div>
						</c:if>
						<c:if test="${profAttrib.strLabelName == 'City'}">
							<div class="rowEvenNewSpacing">
								<span class="lableText3">City:</span>
								<form:input path="listProfAttribForms[${status.index}].strLabelValue" class="job_seeker_password textBox350" />
								<span class="required">(Required)</span>
							</div>
						</c:if>
						<c:if test="${profAttrib.strLabelName == 'State / Province'}">
							<div class="rowEvenNewSpacing">
								<span class="lableText3">State:</span>
									<form:select path="listProfAttribForms[${status.index}].strLabelValue" class="jb_input3 jb_input_width3">
										<form:option value="0" label="Select" />
										<form:options items="${profAttrib.dropdown}" itemValue="optionId"
											itemLabel="optionName" />
									</form:select>			
								<span class="required">(Required)</span>
							</div>
						</c:if>
						<c:if test="${profAttrib.strLabelName == 'Zip Code'}">
							<div class="rowEvenNewSpacing">
								<span class="lableText3">ZIP Code:</span>
								<form:input path="listProfAttribForms[${status.index}].strLabelValue"
									class="job_seeker_password textBox350" />
								<span class="required">(Required)</span>
							</div>
						</c:if>
						<c:if test="${profAttrib.strLabelName == 'Country'}">
							<div class="row">
								<span class="lableTextSelect">Country:</span>
									<form:select path="listProfAttribForms[${status.index}].strLabelValue" class="jb_input3 jb_input_width3">
										<form:option value="0" label="Select" />
										<form:options items="${profAttrib.dropdown}" itemValue="optionId"
											itemLabel="optionName" />
									</form:select>
								<span class="requiredTopmargin">(Required)</span>
							</div>
						</c:if>
						<c:if test="${profAttrib.strLabelName == 'E-Mail Address'}">							
							<div class="rowEvenNewSpacing">
								<span class="lableText3">E-Mail Address:</span>
								<form:input path="listProfAttribForms[${status.index}].strLabelValue" class="job_seeker_password textBox350" readonly="true"/>
								<span class="required">(Required)</span>
							</div>
						</c:if>
						<c:if test="${profAttrib.strLabelName == 'Phone Number'}">
							<div class="rowEvenNewSpacing">
								<span class="lableText3">Phone Number:</span> 
								<form:input path="listProfAttribForms[${status.index}].strLabelValue" class="job_seeker_password textBox350" />
							</div>
						</c:if>
						<c:if test="${profAttrib.strLabelName == 'My Industry'}">
							<div class="rowH3Holder">
								<h3>Employment Information</h3>
							</div>		
							<div class="rowEvenNewSpacing">
								<span class="lableText3">My Industry:</span> 
								<form:input path="listProfAttribForms[${status.index}].strLabelValue" class="job_seeker_password textBox350" />
								<div class="toolTip01 marginTop5 marginLeft5">
									<span class="classic">Enter the industry you serve.
										Example: Healthcare</span>
								</div>
								<span class="required">(Required)</span>
							</div>
						</c:if>
						<c:if test="${profAttrib.strLabelName == 'My Profession'}">
							<div class="rowEvenNewSpacing">
								<span class="lableText3">My Profession:</span> 
								<form:input path="listProfAttribForms[${status.index}].strLabelValue" class="job_seeker_password textBox350" />
								<div class="toolTip01 marginTop5 marginLeft5">
									<span class="classic">Enter the general field in which you
										work. Example: Respiratory Therapy</span>
								</div>
								<span class="required">(Required)</span>
							</div>
						</c:if>
						<c:if test="${profAttrib.strLabelName == 'My Specialty'}">
							<div class="rowEvenNewSpacing">
								<span class="lableText3">My Specialty:</span> 
								<form:input path="listProfAttribForms[${status.index}].strLabelValue" class="job_seeker_password textBox350" />
								<div class="toolTip01 marginTop5 marginLeft5">
									<span class="classic">Enter the area in which you
										specialize. Example: Neonatal/Pediatrics</span>
								</div>
								<span class="required">(Required)</span>
							</div>
						</c:if>
						<c:if test="${profAttrib.strLabelName == 'My Job Title'}">
							<div class="rowEvenNewSpacing">
								<span class="lableText3">My Job Title:</span> 
								<form:input path="listProfAttribForms[${status.index}].strLabelValue" class="job_seeker_password textBox350" />
								<div class="toolTip01 marginTop5 marginLeft5">
									<span class="classic">Enter your official job title.
										Example: Registered Respiratory Therapist</span>
								</div>
								<span class="required">(Required)</span>
							</div>
						</c:if>
						<c:if test="${profAttrib.strLabelName == 'Im seeking'}">
							<div class="row">
								<span class="lableTextSelect ">I am seeking:</span>
									<form:select path="listProfAttribForms[${status.index}].strLabelValue" class="jb_input3 jb_input_width3">
										<form:option value="0" label="Select" />
										<form:options items="${profAttrib.dropdown}" itemValue="optionId"
											itemLabel="optionName" />
									</form:select>
							</div>
						</c:if>
						<c:if test="${profAttrib.strLabelName == 'Ethnicity'}">
							<div class="rowH3Holder">
								<h3>Equal Opportunity / Affirmative Action</h3>
							</div>
			
							<div class="row">
								<span class="lableTextSelect ">Ethnicity:</span> 
									<form:select path="listProfAttribForms[${status.index}].strLabelValue" class="jb_input3 jb_input_width3">
										<form:option value="0" label="Select" />
										<form:options items="${profAttrib.dropdown}" itemValue="optionId"
											itemLabel="optionName" />
									</form:select>
							</div>
						</c:if>
						<c:if test="${profAttrib.strLabelName == 'Gender'}">
							<div class="row">
								<span class="lableTextSelect ">Gender:</span> 
									<form:select path="listProfAttribForms[${status.index}].strLabelValue" class="jb_input3 jb_input_width3">
										<form:option value="0" label="Select" />
										<form:options items="${profAttrib.dropdown}" itemValue="optionId"
											itemLabel="optionName" />
									</form:select>
							</div>
						</c:if>
						<c:if test="${profAttrib.strLabelName == 'Veteran Status'}">
							<div class="row">
								<span class="lableTextSelect ">Veteran Status:</span> 
									<form:select path="listProfAttribForms[${status.index}].strLabelValue" class="jb_input3 jb_input_width3">
										<form:option value="0" label="Select" />
										<form:options items="${profAttrib.dropdown}" itemValue="optionId"
											itemLabel="optionName" />
									</form:select>
							</div>
						</c:if>
				</c:forEach>
				<form:hidden path="emailId"/>
				<div class="popUpButtonRow">
					<!--<a href="<%=request.getContextPath()%>/jobseekerregistration/updateJobSeekerProfile.html"
						class="btn_sm orange">Save</a>  -->
					<input type="button" value="Save" class="orange" id="save"/>
					<input type="button" value="Cancel" onclick="cancelProcess()"
									class="orange" name="Cancel" />

					
					<!-- <a href="#" class="btn_sm orange"
						onclick="parent.$.nmTop().close();">Cancel</a> -->
				</div>
				<div class="clearfix"></div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>