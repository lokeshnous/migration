<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Advance Health care job</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<jsp:include page="common/include.jsp" />
<script type="text/javascript">
	jQuery(document).ready(function() {
		
 		$('#save').click(function(){			
 			
			$.ajax({url:"${pageContext.request.contextPath}/employerRegistration/employeeAccountSetting.html",
				data:$('#editAccountSettingData').serialize(),
				type:"POST",
				success: function(data) {
					if(data == ''){
						parent.$.nmTop().close();
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
	jQuery(document).ready(function() {
		
 		$('#save').click(function(){			
 			
			$.ajax({url:"${pageContext.request.contextPath}/employerRegistration/employeeAccountSetting.html",
				data:$('#editBillingSettingData').serialize(),
				type:"POST",
				success: function(data) {
					if(data == ''){
						parent.$.nmTop().close();
					}else{
						$("#errmsg").html(data);
					}
				 },
			});
		}); 
		
		jQuery(".megamenu").megamenu();
	});
</script>	
	</head>
	<body class="job_board">
		<div class="job_seeker_login popUpContainer" id="jobSeekerRegister1" style="display: block;">
			<div class="popupHeader">
				<h2>
					ACCOUNT SETTINGS
				</h2>
				<a href="#">
				<img width="19" height="19" alt="" src="../resources/images/Close.png" class="nyroModalClose" alt="Close">
				</a>
			</div>
			<div class="popUpContainerWrapper">
				<form:form action="../employerRegistration/employeeAccountSetting.html" method="get" commandName="employeeAccountForm" id="editAccountSettingData" enctype="multipart/form-data">
					<div class="EvenNewSpacing marginLeft20">
						<h3>Account Profile</h3>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							Contact Name:
						</span>
						<form:input path="firstName" name="firstName" class="job_seeker_email" type="text"/>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							Company:
						</span>
						<form:input path="company" name="company" class="job_seeker_password" type="text"/>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							Street Address:
						</span>
						<form:input path="streetAddress" name="streetAddress" class="job_seeker_password" type="text"/>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							City:
						</span>
						<form:input path="cityOrTown" name="cityOrTown" class="job_seeker_password" type="text"/>
					</div>
					<div class="row">
						<span class="lableTextSelect marginTop13 ">
							State:
						</span>
						<form:select path="state" name="state" class="jb_input3 jb_input_width3" id="state">
						<form:options items="${stateList}" itemValue="stateValue" itemLabel="stateValue" />
						</form:select>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							ZIP Code:
						</span>
						<form:input path="zipCode" name="zipCode" class="job_seeker_password" type="text"/>
					</div>
					<div class="row">
						<span class="lableTextSelect marginTop13 ">
							Country:
						</span>
						<form:select path="country" name="country" class="jb_input3 jb_input_width3" id="Country">
						<form:options items="${countryList}" itemValue="countryValue" itemLabel="countryValue" />
						</form:select>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							E-Mail:
						</span>
						<form:input path="email" name="email" class="job_seeker_password" type="text"/>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							Phone:
						</span>
						<form:input path="phone" name="phone" class="job_seeker_password" type="text"/>
					</div>
					<div class="rowEvenNewSpacing marginTop20 paddingBottom10">
						<span class="floatLeft marginTop10">
							<!-- <a class="btn_sm orange" href="../employerRegistration/employeeAccountSetting.html">
								Edit
							</a> -->
							<input class="btn_sm orange" type="submit" value="Edit">
							<a class="btn_sm orange" href="">
								Cancel
							</a>
						</span>
					</div>
					</form:form>
					<div class="borderBottomDotted row">
					</div>
					<form:form action="../employerRegistration/employeeBillingSetting.html" method="get" commandName="employeeBillingForm" id="editBillingSettingData" enctype="multipart/form-data">
					<div class="row marginLeft20 marginTop25">
						<h3>Billing Contact</h3>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							Contact Name:
						</span>
						<form:input path="firstName" name="firstName" class="job_seeker_email" type="text"/>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							Company:
						</span>
						<form:input path="company" name="company" class="job_seeker_password" type="text"/>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							Street Address:
						</span>
						<form:input path="streetAddress" name="streetAddress" class="job_seeker_password" type="text"/>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							City:
						</span>
						<form:input path="cityOrTown" name="cityOrTown" class="job_seeker_password" type="text"/>
					</div>
					<div class="row">
						<span class="lableTextSelect marginTop13 ">
							State:
						</span>
						<form:select path="state" name="state" class="jb_input3 jb_input_width3" id="state">
						<form:options items="${stateList}" itemValue="stateValue" itemLabel="stateValue" />
						</form:select>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							ZIP Code:
						</span>
						<form:input path="zipCode" name="zipCode" class="job_seeker_password" type="text"/>
					</div>
					<div class="row">
						<span class="lableTextSelect marginTop13 ">
							Country:
						</span>
						<form:select name="Country" path="" class="jb_input3 jb_input_width3" id="Country">
						<form:options items="${countryList}" itemValue="countryValue" itemLabel="countryValue" />
						</form:select>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							E-Mail:
						</span>
						<form:input path="email" name="email" class="job_seeker_password" type="text"/>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							Phone:
						</span>
						<form:input path="phone" name="phone" class="job_seeker_password" type="text"/>
					</div>
					<div class="rowEvenNewSpacing marginTop20 paddingBottom10">
						<span class="floatLeft marginTop10">
							<!-- <a class="btn_sm orange" href="">
								Edit
							</a> -->
							<input class="btn_sm orange" type="submit" value="Edit">
							<a class="btn_sm orange" href="">
								Cancel
							</a>
						</span>
					</div>
					<div class="clearfix">
					</div>
				</form:form>
			</div>
			<div class="clearfix">
			</div>
		</div>
    </body>
</html>