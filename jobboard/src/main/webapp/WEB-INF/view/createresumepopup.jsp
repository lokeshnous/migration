<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>

<!-- ../resources/css -->
<link href="../resources/css/JB.css" rel="stylesheet" type="text/css" />
<link href="../resources/css/jquery.megamenu.css" rel="stylesheet"
	type="text/css" />
<link href="../resources/css/SliderStyles.css" rel="stylesheet"
	type="text/css">

<!-- JAVASCRIPT FILES -->
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript"
	src="../resources/js/jquery.cycle.all.min.js"></script>
<script type="text/javascript" src="../resources/js/slider.js"></script>
<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>

<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery(".megamenu").megamenu();
	});
	function MM_jumpMenu(targ, selObj, restore) { //v3.0
		eval(targ + ".location='" + selObj.options[selObj.selectedIndex].value
				+ "'");
		/* createResumePopUp.html?resumeType=
		createResumePopUp.html?resumeType=
		createResumePopUp.html?resumeType= */
		if (restore)
			selObj.selectedIndex = 0;
	}
</script>
</head>
<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>Create Or Upload My New Resume</h2>
			<img src="../resources/images/Close.png" width="19"
				onclick="parent.$.nmTop().close();" height="19" alt="">
		</div>

		<div class="popUpContainerWrapper">
			<form:form method="get"
				action="/jobboard/jobSeekerResume/createResumeBuilder.html"
				commandName="createResume" id="formtouse"
				enctype="multipart/form-data">
				
				<c:forEach items="${createResume.resumeProfileAttribForm}" 
					var="profAttrib" varStatus="status">
					<c:if test="${profAttrib.strAttribType == 'TextBox'}">
					<div class="rowEvenNewSpacing">
						<c:if test="${profAttrib.strLabelName == 'Resume Name'}">	
							<span class="lableText4"><c:out	value="${profAttrib.strLabelName}" />:</span>
							<form:input path="${profAttrib.strLabelValue}"
								class="job_seeker_password textBox2" />
							<span class="required">(Required)</span>
						</c:if>
						<c:if test="${profAttrib.strLabelName == 'Desired job title'}">	
							<span class="lableText4"><c:out	value="${profAttrib.strLabelName}" />:</span>
							<form:input path="${profAttrib.strLabelValue}"
								class="job_seeker_password textBox2" />
							<span class="required">(Required)</span>
						</c:if>		
					</div>
					</c:if>
					<c:if test="${profAttrib.strAttribType == 'Dropdown'}">						
						<div class="rowEvenNewSpacing">
							<c:if test="${profAttrib.strLabelName == 'How would you like to create your resume?'}">
								<span class="lableText3"><c:out
										value="${profAttrib.strLabelName}" />:</span>
								<form:select path="resumeProfileAttribForm[${status.index}].strLabelValue"
									class="jb_input3 jb_input_width3">
									<form:options items="${profAttrib.dropdown}"
										itemValue="optionId" itemLabel="optionName" />
								</form:select>
								<span class="required">(Required)</span>
							</c:if>
							<c:if test="${profAttrib.strLabelName == 'Desired Employment Type'}">
								<span class="lableText4"><c:out
										value="${profAttrib.strLabelName}" />:</span>
								<form:select path="resumeProfileAttribForm[${status.index}].strLabelValue"
									class="jb_input3 jb_input_width3 marginTop0">
									<form:option value="0" label="Select" />
									<form:options items="${profAttrib.dropdown}"
										itemValue="optionId" itemLabel="optionName" />
								</form:select>
							</c:if>
						  	<c:if test="${profAttrib.strLabelName == 'U.S. Work Authorization'}">
								<span class="lableText4"><c:out
										value="${profAttrib.strLabelName}" />:</span>
								<form:select path="resumeProfileAttribForm[${status.index}].strLabelValue"
									class="jb_input3 marginTop0 width350">
									<form:option value="0" label="Select" />
									<form:options items="${profAttrib.dropdown}"
										itemValue="optionId" itemLabel="optionName" />
								</form:select>
								<span class="required">(Required)</span>
							</c:if>
						</div>
					</c:if>
					
					<c:if test="${profAttrib.strAttribType == 'Radio'}">
						<div class="rowEvenNewSpacing ">
						<span class="lableText4">
						<c:out	value="${profAttrib.strLabelName}" />:</span>
							<div class="">
								<c:forEach items="${profAttrib.dropdown}" var="dropdown" varStatus="radioStatus">
									<form:radiobutton path="" Value="${dropdown.optionId}" label="${dropdown.optionName}"/>
								</c:forEach>
						</div>
						</div>
					</c:if>
					
					<c:if test="${profAttrib.strAttribType == 'CheckBox'}">
						<div class="row paddingBottom10 marginLeft10">I would like
							the following sent to me so I can stay up to date with the latest
							healthcare news and information:</div>

						<div class="centerAlign ">
							<ul>
								<c:forEach items="${profAttrib.dropdown}" var="dropdown"
									varStatus="index">
									<li>
										<div>
											<form:checkbox path="${profAttrib.strLabelValue}"
												label="${dropdown.optionId}" value="${dropdown.optionName}"
												cssStyle="width:20px" />
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>
					</c:if>
					
				</c:forEach>
				
				<div class="rowEvenNewSpacing marginTop10 paddingBottom10">
					<span class="floatLeft marginTop10"> <input type="submit"
						value="Create" class="btn_sm orange" /> <input type="button"
						class="btn_sm orange" value="Cancel"
						onclick="parent.$.nmTop().close();" /></span>
				</div>
				<div class="clearfix"></div>

			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>