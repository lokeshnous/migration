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
function validateNumber(event) {
    var keyval = window.event ? event.keyCode : event.which;

    if (event.keyCode == 8 || event.keyCode == 46
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
  $('#btn-submit').click(function() { 
 
    $(".error").hide();
    var hasError = false;
    var emailReg=/^[a-zA-Z0-9_\+-]+(\.[a-zA-Z0-9_\+-]+)*@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.([a-zA-Z]{2,4})$/;
    //var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    var emailblockReg =
     /^([\w-\.]+@(?!gmail.com)(?!yahoo.com)(?!hotmail.com)([\w-]+\.)+[\w-]{2,4})?$/;
   
    var emailaddressVal = $("#email").val();
   
    if(emailaddressVal == '') {
      $("#email").after('<div class="rowEvenNewSpacing"><span class="lableText3"></span><span class="error" STYLE="color: red; font-size: 10pt">Please enter the correct E-Mail address</span></div>');
      hasError = true;
    }
 
    else if(!emailReg.test(emailaddressVal)) {
      $("#email").after('<div class="rowEvenNewSpacing"><span class="lableText3"></span><span class="error" STYLE="color: red; font-size: 10pt">Please enter the correct E-Mail address</span></div>');
      hasError = true;
    }
 
   /*  else if(!emailblockReg.test(emailaddressVal)) {
      $("#email").after('<span class="error" STYLE="color: red; font-size: 10pt">No yahoo, gmail or hotmail emails.</span>');
      hasError = true
    } */
    var filter =/^((\(\d{3}\) ?)|(\d{3}-))?\d{3}-\d{4}/; 
    var phone=$("#phone").val();
    if(phone==''){
    	 $("#phone").after('<div class="rowEvenNewSpacing"><span class="lableText3"></span><span class="error" STYLE="color: red; font-size: 10pt">Please enter the correct Phone Number (XXX-XXX-XXXX)</span></div>');
         hasError = true;
    }else if(!filter.test(phone)){
    	 $("#phone").after('<div class="rowEvenNewSpacing"><span class="lableText3"></span><span class="error" STYLE="color: red; font-size: 10pt">Please enter the correct Phone Number (XXX-XXX-XXXX)</span></div>');
         hasError = true;
    }
 
    if(hasError == true) { return false; }
 
    });	
  </script>
  <script type="text/javascript">
  $('#btn-submit2').click(function() { 
 
    $(".error").hide();
    var hasError = false;
    var emailReg=/^[a-zA-Z0-9_\+-]+(\.[a-zA-Z0-9_\+-]+)*@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.([a-zA-Z]{2,4})$/;
   // var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    var emailblockReg =
     /^([\w-\.]+@(?!gmail.com)(?!yahoo.com)(?!hotmail.com)([\w-]+\.)+[\w-]{2,4})?$/;
 
    var emailaddressVal = $("#email2").val();
    if(emailaddressVal == '') {
      $("#email2").after('<div class="rowEvenNewSpacing"><span class="lableText3"></span><span class="error" STYLE="color: red; font-size: 10pt">Please enter the correct E-Mail address</span></div>');
      hasError = true;
    }
 
    else if(!emailReg.test(emailaddressVal)) {
      $("#email2").after('<div class="rowEvenNewSpacing"><span class="lableText3"></span><span class="error" STYLE="color: red; font-size: 10pt">Please enter the correct E-Mail address</span></div>');
      hasError = true;
    }
 
    /* else if(!emailblockReg.test(emailaddressVal)) {
      $("#email2").after('<span class="error" STYLE="color: red; font-size: 10pt">Please enter the correct E-Mail Address</span>');
      hasError = true
    } */
    
    var filter =/^((\(\d{3}\) ?)|(\d{3}-))?\d{3}-\d{4}/; 
    var phone=$("#phone2").val();
    if(phone==''){
    	 $("#phone2").after('<div class="rowEvenNewSpacing"><span class="lableText3"></span><span class="error" STYLE="color: red; font-size: 10pt">Please enter the correct Phone Number (XXX-XXX-XXXX)</span></div>');
         hasError = true;
    }else if(!filter.test(phone)){
    	 $("#phone2").after('<div class="rowEvenNewSpacing"><span class="lableText3"></span><span class="error" STYLE="color: red; font-size: 10pt">Please enter the correct Phone Number (XXX-XXX-XXXX)</span></div>');
         hasError = true;
    }
 
    if(hasError == true) { return false; }
 
    });	
  </script>
  

<script type="text/javascript">
function copyAccToBillingAddr(obj) {            	
            	var isSelected = obj.value;          	
           	 var firstNameVal = $("#firstName2").val();
           	 var lastNameVal = $("#lastName2").val();
           	 var companyVal = $("#company2").val();
           	 var streetAddressVal = $("#streetAddress").val();
           	 var cityOrTownVal = $("#cityOrTown").val();
           	 var zipCodeVal = $("#zipCode2").val();
           	 var emailVal = $("#email2").val();
           	 var phoneVal = $("#phone2").val();
           	 
           	if (isSelected) {
           		$("#firstName2").val($("#firstName").val());
           		$("#lastName2").val($("#lastName").val());
           		$("#company2").val($("#company").val());
           		$("#streetAddress2").val($("#streetAddress").val());
           		$("#cityOrTown2").val($("#cityOrTown").val());
           		$("#state2").val($("#state").val());
           		$("#country2").val($("#country").val());
           		$("#zipCode2").val($("#zipCode").val());
           		$("#email2").val($("#email").val());
           		$("#phone2").val($("#phone").val());
           	}
           	if(!isSelected)
           		{
           		$("#firstName2").val(firstNameVal);
           		$("#lastName2").val(lastNameVal);
           		$("#company2").val(companyVal);
           		$("#streetAddress2").val(streetAddressVal);
           		$("#cityOrTown2").val(cityOrTownVal);
           		$("#zipCode2").val(zipCodeVal);
           		$("#email2").val(emailVal);
           		$("#phone2").val(phoneVal);
           		}
            	
    } 
   
	
	 
</script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		
 		$('#Save').click(function(){			
 			
			$.ajax({url:"${sessionScope.contextPath}/employerRegistration/employeeAccountSetting.html",
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
 		
 		$('[id^=zipCode]').keypress(validateNumber);
 		$('[id^=zipCode2]').keypress(validateNumber); 		
		jQuery(".megamenu").megamenu();
	});
</script>

<script type="text/javascript">
	 jQuery(document).ready(function() {
		
 		$('#Save').click(function(){			
 			
			$.ajax({url:"${sessionScope.contextPath}/employerRegistration/employeeBillingSetting.html",
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
		
		$('[id^=zipCode]').keypress(validateNumber);
		$('[id^=zipCode2]').keypress(validateNumber);

	}); 
</script>
		<script type="text/javascript">
		$('#Cancel').click(function(){		
			parent.$.nmTop().close();		
		});
		$('#CancelData').click(function(){		
			parent.$.nmTop().close();		
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
				<img width="19" height="19" alt="" src="<%= request.getContextPath() %>/resources/images/Close.png" class="nyroModalClose" alt="Close">
				</a>
			</div>
			<div class="popUpContainerWrapper">
				<form:form action="../employerRegistration/employeeAccountSetting.html" method="get" commandName="employeeAccountForm" id="editAccountSettingData" name="editAccountSettingData" enctype="multipart/form-data">
					<div class="EvenNewSpacing marginLeft20">
						<h3>Account Profile</h3>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							First Name:
						</span>
						<form:input path="firstName" id="firstName" name="firstName" class="job_seeker_email" type="text"/>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							Last Name:
						</span>
						<form:input path="lastName" id="lastName" name="lastName" class="job_seeker_email" type="text"/>
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
						<form:option value="0" label="Select" />
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
						<form:select path="country" name="country" class="jb_input3 jb_input_width3" id="country">
						<form:option value="0" label="Select" />
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
						<form:input path="phone" id="phone" name="phone" class="job_seeker_password" type="text"/>
					</div>
					<div class="rowEvenNewSpacing marginTop20 paddingBottom10">
						<span class="floatLeft marginTop10">
							<input type="submit" value="Save" name="Save" id="btn-submit" class="btn_sm orange" />
							<!-- <input class="btn_sm orange" type="submit" id="btn-submit" value="Edit"> -->
							<input type="button" name="Cancel" id="Cancel" value="Cancel" class="btn_sm orange" />
								
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
						<span class="lableText3"> Use my account address </span>
						<form:checkbox onchange="copyAccToBillingAddr(this)" path="" value="false" name="useAcctAddress" id="useAcctAddress"/>
					</div>
				
				<c:if test="${count == '1'}">
					<div class="rowEvenNewSpacing">
						<span class="lableText3"> First Name: </span>
						<form:input path="billingAddressForm.fnameForBillingAddr" id="firstName2" name="firstName2"
							class="job_seeker_email" type="text" />
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							Last Name:
						</span>
						<form:input path="billingAddressForm.lnameForBillingAddr" id="lastName2" name="lastName2" class="job_seeker_email" type="text"/>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3"> Company: </span>
						<form:input path="company" name="company2" id="company2"
							class="job_seeker_password" type="text" />
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3"> Street Address: </span>
						<form:input path="billingAddressForm.streetForBillingAddr" name="streetAddress2" id="streetAddress2"
							class="job_seeker_password" type="text" />
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3"> City: </span>
						<form:input path="billingAddressForm.cityOrTownForBillingAddr" name="cityOrTown2" id="cityOrTown2"
							class="job_seeker_password" type="text" />
					</div>
					<div class="row">
						<span class="lableTextSelect marginTop13 "> State: </span>
						<form:select path="billingAddressForm.stateBillingAddress" name="state2"
							class="jb_input3 jb_input_width3" id="state2">
							<form:option value="0" label="Select" />
							<form:options items="${stateList}" itemValue="stateValue"
								itemLabel="stateValue" />
						</form:select>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3"> ZIP Code: </span>
						<form:input path="billingAddressForm.zipCodeForBillingAddr" name="zipCode2" id="zipCode2"
							class="job_seeker_password" type="text" />
							
					</div>
					<div class="row">
						<span class="lableTextSelect marginTop13 "> Country: </span>
						<form:select path="billingAddressForm.countryForBillingAddr"
							class="jb_input3 jb_input_width3" id="country2" name="country2">
							<form:option value="0" label="Select" />
							<form:options items="${countryList}" itemValue="countryValue"
								itemLabel="countryValue" />
						</form:select>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3"> E-Mail: </span>
						<form:input path="email" name="email2" id="email2" class="job_seeker_password"
							type="text"/>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3"> Phone: </span>
						<form:input path="phone" name="phone2" id="phone2" class="job_seeker_password"
							type="text"/>
					</div>
					<div class="rowEvenNewSpacing marginTop20 paddingBottom10">
						<span class="floatLeft marginTop10"> <!-- <a class="btn_sm orange" href="">
								Edit
							</a> -->
							 <!-- <input class="btn_sm orange" type="submit"
							id="btn-submit2" value="Edit">  -->
							<input type="submit" value="Save" name="Save" id="btn-submit2" class="btn_sm orange" />
							<input type="button" name="CancelData"  id="CancelData" value="Cancel" class="btn_sm orange" />
						</span>
					</div>
					<div class="clearfix"></div>
				</c:if>
			</form:form>
			</div>
			<div class="clearfix">
			</div>
		</div>
    </body>
</html>