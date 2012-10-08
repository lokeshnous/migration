<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<title>ADVANCE Heathcare Jobs</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- STYLESHEETS -->
<link href="../resources/css/JB.css" rel="stylesheet" type="text/css" />
<link href="../resources/css/jquery.megamenu.css" rel="stylesheet"
	type="text/css" />
<link href="../resources/css/SliderStyles.css" rel="stylesheet"
	type="text/css">
<!-- <link rel="stylesheet" type="text/css" media="screen"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css">
 -->
 <link href="../resources/css/jquery-auto-ui.css" rel="stylesheet"
	type="text/css">

<script type="text/javascript">
function validateNumber(event) {
    var keyval = window.event ? event.keyCode : event.which;

    if (event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 46
     || event.keyCode == 37 || event.keyCode == 39) {
        return true;
    }
    else if ( keyval < 48 || keyval > 57 ) {
        return false;
    }
    else return true;
};
</script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		
		if($("#MyProfession :selected").text() == "Others"){
	         $("#otherProfession").show();
	    }else{
	    	$("#otherProfession").hide();
	    }
		 
		$('#MyProfession').change(function() {
			 if($("#MyProfession :selected").text() == "Others"){
		         $("#otherProfession").show();
		    }else{
		    	$("#otherProfession").hide();
		    }
		});


		
 		$('#save').click(function(){			
 			
			$.ajax({url:"${pageContext.request.contextPath}/jobseekerregistration/updateJobSeekerProfile.html",
				data:$('#editProfileSettingsId').serialize(),
				type:"POST",
				success: function(data) {
					if(data == ''){
						//alert("Data saved successfully !");
						parent.$.nmTop().close();
						window.location.reload();
					}else{
						var href = $('#BackToTopId').attr('href');
      					location.href = href;
						$("#errmsg").html(data);
					}
				 },
			});
		}); 
 		$('[id^=zipCode]').keypress(validateNumber);
 		
 		//Auto complete on selecting city
		$("#cityAutoPopulation").autocomplete({
			source: '${pageContext.request.contextPath}/employer/getCityList.html',
			width:500,
			select: function(event, ui) {
				$("#cityAutoPopulation").val(ui.item.value);				
				$.ajax({
				url: '${pageContext.request.contextPath}/employer/getState.html?city='+$("#cityAutoPopulation").val(),
				success : function(data) {
					$('#stateDpId').val(data);

					$.ajax({
					url: '${pageContext.request.contextPath}/employer/getPostalCode.html?city='+$("#cityAutoPopulation").val()+'&state='+$("#stateDpId").val(),
					success : function(data) {
						$('#zipCode').val(data);
					},
					});						
						$.ajax({
						url: '${pageContext.request.contextPath}/employer/getCountry.html?city='+$("#cityAutoPopulation").val()+'&state='+$("#stateDpId").val()+'&postalCode='+$("#zipCode").val(),
						success : function(country) {
							$('#countryDpId').val(country);
						},
					}); 						
				},
				});
			}
		}); 

		//Auto complete on selecting zipcode			
		$("#zipCode").autocomplete({
			source: '${pageContext.request.contextPath}/employer/getPostalCodeAutoPopulation.html',
			select: function(event, ui) {
				$("#zipCode").val(ui.item.value);	
				$('#cityAutoPopulation').val("");
				$('#stateDpId').val("");
				$.ajax({
					url: '${pageContext.request.contextPath}/employer/getLocations.html?zipCode='+$("#zipCode").val(),
					success : function(data) {
						$('#stateDpId').val(data.state);
						$('#countryDpId').val(data.country);
						$("#cityAutoPopulation").val(data.city);
					},error : function(data) {
						alert('Unable to process');
					},
					complete : function(data) {
					}
				});		
			}
		});	
 		
		$("#zipCode").change(function(){
			$('#cityAutoPopulation').val("");
			$('#stateDpId').val("");
			$('#countryDpId').val("");
		});
		
		$("#cityAutoPopulation").change(function(){
			$('#zipCode').val("");
			$('#stateDpId').val("");
			$('#countryDpId').val("");
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
			<img src="../resources/images/Close.png" width="19" height="19" title="Close"
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
								<form:input path="listProfAttribForms[${status.index}].strLabelValue" class="job_seeker_password textBox350 focus" />
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
								<form:input path="listProfAttribForms[${status.index}].strLabelValue" class="job_seeker_password textBox350" id="cityAutoPopulation"/>
								<span class="required">(Required)</span>
							</div>
						</c:if>
						<c:if test="${profAttrib.strLabelName == 'State / Province'}">
							<div class="rowEvenNewSpacing">
								<span class="lableText3">State:</span>
									<form:select path="listProfAttribForms[${status.index}].strLabelValue" class="jb_input3 jb_input_width3" id="stateDpId">
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
							
									<form:input
											path="listProfAttribForms[${status.index}].strLabelValue"
											type="text" name="zipCode" id="zipCode"
											class="job_seeker_password textBox350" maxlength="5" />
											
								<span class="required">(Required)</span>
							</div>
						</c:if>
						<c:if test="${profAttrib.strLabelName == 'Country'}">
							<div class="row">
								<span class="lableTextSelect">Country:</span>
									<form:select path="listProfAttribForms[${status.index}].strLabelValue" class="jb_input3 jb_input_width3" id="countryDpId">
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
								<form:input readonly="true" path="listProfAttribForms[${status.index}].strLabelValue" class="job_seeker_password textBox350" />
								<!-- <div class="toolTip01 marginTop5 marginLeft5">
									<span class="classic">Enter the industry you serve.
										Example: Healthcare</span>
								</div>
								<span class="required">(Required)</span> -->
							</div>
						</c:if>
						<c:if test="${profAttrib.strLabelName == 'My Profession'}">
							<div class="rowEvenNewSpacing">
								<span class="lableText3">My Profession:</span> 
								<form:select id="MyProfession" path="listProfAttribForms[${status.index}].strLabelValue" class="jb_input3 jb_input_width2">
										<form:option value="0" label="Select" />
										<form:options items="${profAttrib.dropdown}" itemValue="optionId"
											itemLabel="optionName" />
								</form:select>
								<form:input path="otherProfession" class="job_seeker_password textBox150"/>
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
						
				</c:forEach>
				<form:hidden path="emailId"/>
				<div class="popUpButtonRow">
					<!--<a href="<%=request.getContextPath()%>/jobseekerregistration/updateJobSeekerProfile.html"
						class="btn_sm orange">Save</a>  -->
					<input type="button" value="Save" class="orange" id="save"/>
					<input type="button" value="Cancel" onclick="cancelProcess()"
									class="orange" name="Cancel" />
					<a href="#jobSeekerRegister1" id="BackToTopId" style="display: none;">Back To Top</a>
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