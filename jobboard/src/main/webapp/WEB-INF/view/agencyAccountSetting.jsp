<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Advance Health care job</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<jsp:include page="common/include.jsp" />
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
function copyAccToBillingAddr() {     
	var isSelected = $('#useAcctAddress').is(':checked');
            	/* var isSelected = obj.value;          	
           	 var firstNameVal = $("#firstName2").val();
           	 var lastNameVal = $("#lastName2").val();
           	 var companyVal = $("#company2").val();
           	 var streetAddressVal = $("#streetAddress").val();
           	 var cityOrTownVal = $("#cityOrTown").val();
           	 var zipCodeVal = $("#zipCode2").val();
           	 var emailVal = $("#email2").val();
           	 var phoneVal = $("#phone2").val(); */
           	 
           	if (isSelected) {
           		$("#firstName2").val($("#firstName").val());
           		$("#lastName2").val($("#lastName").val());
           		$("#company2").val($("#company").val());
           		$("#streetAddress2").val($("#streetAddress").val());
           		$("#cityOrTown2").val($("#cityOrTown").val());
           		$("#state2").val($("#state").val());
           		$("#country2").val($("#country").val());
           		$("#zipCode2").val($("#zipCode").val());
           		$("#email2").val($("#emailId").val());
           		$("#phone2").val($("#phone").val());
           	}else{
           		$("#firstName2").val("");
           		$("#lastName2").val("");
           		$("#company2").val("");
           		$("#streetAddress2").val("");
           		$("#cityOrTown2").val("");
           		$("#state2").val("");
           		$("#zipCode2").val("");
           		$("#email2").val("");
           		$("#phone2").val("");
           	}
           	/* if(!isSelected)
           		{
           		$("#firstName2").val(firstNameVal);
           		$("#lastName2").val(lastNameVal);
           		$("#company2").val(companyVal);
           		$("#streetAddress2").val(streetAddressVal);
           		$("#cityOrTown2").val(cityOrTownVal);
           		$("#zipCode2").val(zipCodeVal);
           		$("#email2").val(emailVal);
           		$("#phone2").val(phoneVal);
           		} */
            	
    } 
   
	
	 
</script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		$("#phone").inputmask("mask", {"mask": "(999) 999-9999"}); 
		$("#phone2").inputmask("mask", {"mask": "(999) 999-9999"}); 
		$.nmFilters({
    	    custom: {
    	        afterShowCont: function(nm) {
    	        	$('#firstName').focus();
    	        }
    	    }
    	});
 		$('#btn-submit').click(function(){
 			var href = $('#BackToTopId').attr('href');
			location.href = href;
 			$("#errmsg").html("Processing...");
			$.ajax({url:"${pageContext.request.contextPath}/agency/agencyAccountSetting.html",
				data:$('#editAccountSettingData').serialize(),
				type:"POST",
				success: function(data) {
					if(data == ''){
						alert("Data saved successfully!");	
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
 		
 		//$('[id^=zipCode]').keypress(validateNumber);
 		//$('[id^=zipCode2]').keypress(validateNumber); 		
		jQuery(".megamenu").megamenu();
		if ($("#adminLogin").val() == 'true') {
			$("#emailId").attr('readonly', false);
		}
		/* $("#cityOrTown").autocomplete({
			source: '${pageContext.request.contextPath}/employer/getCityList.html',
			width:500,
			select: function(event, ui) {
				$("#cityOrTown").val(ui.item.value);				
				$.ajax({
				url: '${pageContext.request.contextPath}/employer/getState.html?city='+$("#cityOrTown").val(),
				success : function(data) {
					$('#state').val(data);

					$.ajax({
					url: '${pageContext.request.contextPath}/employer/getPostalCode.html?city='+$("#cityOrTown").val()+'&state='+$("#state").val(),
					success : function(data) {
						$('#zipCode').val(data);
					},
					});						
						$.ajax({
						url: '${pageContext.request.contextPath}/employer/getCountry.html?city='+$("#cityOrTown").val()+'&state='+$("#state").val()+'&postalCode='+$("#zipCode").val(),
						success : function(country) {
							//alert(country);
							$('#country').val(country);
							var modCity = $("#cityOrTown").val();
							modCity = modCity.substring(0,modCity.lastIndexOf(", "));
							$("#cityOrTown").val(modCity);
							if (country == "USA" || country == "US") {           		
			            		 var checks = ["2","3","4"];
			            		 $(":checkbox").val(checks).filter(":checked").attr("disabled",true);
			            		 $(":checkbox").val(checks).filter(":checked").attr("checked",false); 
			            		 $("#waitmsg").hide();
			            	}else{
			            		var checks = ["1","2","3","4"];
			           		    $(":checkbox").val(checks).filter(":checked").attr("disabled",false);  
			           		    $(":checkbox").val(checks).filter(":checked").attr("checked",false); 
			            	} 
						},
					}); 
				},error : function(data) {
				},
				complete : function(data) {
				}
				});
			}
		}); 
		
		//Auto complete on selecting zipcode			
		$("#zipCode").autocomplete({
			source: '${pageContext.request.contextPath}/employer/getPostalCodeAutoPopulation.html',
			select: function(event, ui) {
				$("#zipCode").val(ui.item.value);	
				$('#cityOrTown').val("");
				$('#state').val("");
				$.ajax({
					url: '${pageContext.request.contextPath}/employer/getLocations.html?zipCode='+$("#zipCode").val(),
					success : function(data) {
						$('#state').val(data.state);
						$('#country').val(data.country);
						$("#cityOrTown").val(data.city);
					},error : function(data) {
					},
					complete : function(data) {
					}
				});		
			}
		});	 */
			
		//setting value by default as USA
		$('#country2').val("USA");
		
	/* $("#zipCode").change(function(){
		$('#cityOrTown').val("");
		$('#state').val("");
		$('#country').val("");
	});

	$("#cityOrTown").change(function(){
		$('#zipCode').val("");
		$('#state').val("");
		$('#country').val("");
	}); 
	$("#cityOrTown2").autocomplete({
		source: '${pageContext.request.contextPath}/employer/getCityList.html',
		width:500,
		select: function(event, ui) {
			$("#cityOrTown2").val(ui.item.value);				
			$.ajax({
			url: '${pageContext.request.contextPath}/employer/getState.html?city='+$("#cityOrTown2").val(),
			success : function(data) {
				$('#state2').val(data);

				$.ajax({
				url: '${pageContext.request.contextPath}/employer/getPostalCode.html?city='+$("#cityOrTown2").val()+'&state='+$("#state2").val(),
				success : function(data) {
					$('#zipCode2').val(data);
				},
				});						
					$.ajax({
					url: '${pageContext.request.contextPath}/employer/getCountry.html?city='+$("#cityOrTown2").val()+'&state='+$("#state2").val()+'&postalCode='+$("#zipCode2").val(),
					success : function(country) {
						//alert(country);
						$('#country2').val(country);
						var modCity = $("#cityOrTown2").val();
						modCity = modCity.substring(0,modCity.lastIndexOf(", "));
						$("#cityOrTown2").val(modCity);
						if (country == "USA" || country == "US") {           		
		            		 var checks = ["2","3","4"];
		            		 $(":checkbox").val(checks).filter(":checked").attr("disabled",true);
		            		 $(":checkbox").val(checks).filter(":checked").attr("checked",false); 
		            		 $("#waitmsg").hide();
		            	}else{
		            		var checks = ["1","2","3","4"];
		           		    $(":checkbox").val(checks).filter(":checked").attr("disabled",false);  
		           		    $(":checkbox").val(checks).filter(":checked").attr("checked",false); 
		            	} 
					},
				}); 
			},error : function(data) {
			},
			complete : function(data) {
			}
			});
		}
	});  
	
	//Auto complete on selecting zipcode			
	$("#zipCode2").autocomplete({
		source: '${pageContext.request.contextPath}/employer/getPostalCodeAutoPopulation.html',
		select: function(event, ui) {
			$("#zipCode2").val(ui.item.value);	
			$('#cityOrTown2').val("");
			$('#state2').val("");
			$.ajax({
				url: '${pageContext.request.contextPath}/employer/getLocations.html?zipCode='+$("#zipCode2").val(),
				success : function(data) {
					$('#state2').val(data.state);
					$('#country2').val(data.country);
					$("#cityOrTown2").val(data.city);
				},error : function(data) {
				},
				complete : function(data) {
				}
			});		
		}
	});	
		
$("#zipCode2").change(function(){
	$('#cityOrTown2').val("");
	$('#state2').val("");
	$('#country2').val("");
});

$("#cityOrTown2").change(function(){
	$('#zipCode2').val("");
	$('#state2').val("");
	$('#country2').val("");
});
		*/
	
	});
</script>

<script type="text/javascript">
	 jQuery(document).ready(function() {
 		$('#btn-submit2').click(function(){	
 			var href = $('#BackToMdlId').attr('href');
			location.href = href;
		 	$("#errmsgData").html("Processing...");
			$.ajax({url:"${pageContext.request.contextPath}/agency/agencyBillingSetting.html",
				data:$('#editBillingSettingData').serialize(),
				type:"POST",
				success: function(data) {
					if(data == ''){
						alert("Data saved successfully!");	
						parent.$.nmTop().close();
					}else{
						var href = $('#BackToMdlId').attr('href');
      					location.href = href;
						$("#errmsgData").html(data);
					}
				 },
			});
		}); 
 		 		
		jQuery(".megamenu").megamenu();
		
		//$('[id^=zipCode]').keypress(validateNumber);
	//	$('[id^=zipCode2]').keypress(validateNumber);

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
				<img width="19" height="19" alt="" src="<%= request.getContextPath() %>/resources/images/Close.png" class="nyroModalClose cursor" title="Close" alt="Close">
				</a>
			</div>
			<div class="popUpContainerWrapper">
				<form:form action="../agency/agencyAccountSetting.html" method="POST" commandName="employeeAccountForm" id="editAccountSettingData" name="editAccountSettingData" enctype="multipart/form-data">
					<div class="EvenNewSpacing marginLeft20">
						<h3>Account Profile</h3>
					</div>
					<div class="rowEvenNewSpacing">
					<span class="lableText3"> </span>
					<div id="errmsg" class="FormErrorDisplayText" style="padding-left:0px;"></div>
					</div>
					<form:hidden path="adminLogin"/>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							First Name:
						</span>
						<form:input path="firstName" id="firstName" name="firstName" disabled="${employeeAccountForm.readOnly }" class="job_seeker_email" type="text"/>
						<span class="required">(Required)</span>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							Last Name:
						</span>
						<form:input path="lastName" id="lastName" name="lastName" disabled="${employeeAccountForm.readOnly }" class="job_seeker_email" type="text"/>
						<span class="required">(Required)</span>
					</div>
					<div class="rowEvenNewSpacing ">
						<span class="lableText3">
							Company:
						</span>
						
						<c:if test="${!employeeAccountForm.adminLogin && !employeeAccountForm.readOnly}">
						<form:input path="company" name="company" readonly="true" class="job_seeker_password disabled-input" type="text"/>
						</c:if>
						<c:if test="${employeeAccountForm.readOnly }">
						<form:input path="company" name="company" disabled="${employeeAccountForm.readOnly }" class="job_seeker_password" type="text"/>
						</c:if>
						<c:if test="${employeeAccountForm.adminLogin }">
						<form:input path="company" name="company" readonly="true" class="job_seeker_password disabled-input" type="text"/>
						</c:if>
						
						<%-- <form:input path="company" name="company" readonly="true" disabled="${employeeAccountForm.readOnly }" class="job_seeker_password" type="text"/> --%>
						<span class="required">(Required)</span>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							Street Address:
						</span>
						<form:input path="streetAddress" name="streetAddress" disabled="${employeeAccountForm.readOnly }" class="job_seeker_password" type="text"/>
						<span class="required">(Required)</span>	
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							City:
						</span>
						<form:input path="cityOrTown" name="cityOrTown" disabled="${employeeAccountForm.readOnly }" class="job_seeker_password" type="text"/>
						<span class="required">(Required)</span>
					</div>
					
					<div class="row">
						<span class="lableTextSelect marginTop13 ">
							State:
						</span>
						<form:select path="state" name="state" disabled="${employeeAccountForm.readOnly }" class="jb_input3 jb_input_width3" id="state">
						<form:option value="0" label="Select" />
						<form:options items="${stateList}" itemValue="stateValue" itemLabel="stateValue" />
						</form:select>
						<span class="required">(Required)</span>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							ZIP Code:
						</span>
						<form:input maxlength="5" id="zipCode" disabled="${employeeAccountForm.readOnly }" path="zipCode" name="zipCode" class="job_seeker_password" type="text"/>
						<span class="required">(Required)</span>
					</div>
					<div class="row">
						<span class="lableTextSelect marginTop13 ">
							Country:
						</span>
						<form:select path="country" name="country" disabled="${employeeAccountForm.readOnly }" class="jb_input3 jb_input_width3" id="country">
						<%-- <form:option value="0" label="Select" /> --%>
						<form:options items="${countryList}" itemValue="countryValue" itemLabel="countryValue" />
						</form:select>
						<span class="required">(Required)</span>
					</div>
					<form:hidden path="originalEmail"/>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							Email:
						</span>
						<!-- readonly="true" -->
						<c:if test="${!employeeAccountForm.adminLogin && !employeeAccountForm.readOnly}">
						<form:input path="email" id="emailId" name="email"  class="job_seeker_password disabled-input" readonly="true" retype="text"/>
						</c:if>
						<c:if test="${employeeAccountForm.readOnly }">
						<form:input path="email" id="emailId" name="email" disabled="${employeeAccountForm.readOnly }" class="job_seeker_password" retype="text"/>
						</c:if>
						<c:if test="${employeeAccountForm.adminLogin }">
						<form:input path="email" id="emailId" name="email" class="job_seeker_password" retype="text"/>
						</c:if>
						<span class="required">(Required)</span>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							Phone:
						</span>
						<form:input path="phone" id="phone" disabled="${employeeAccountForm.readOnly }" name="phone" class="job_seeker_password" type="text"/>
						<span class="required">(Required)</span>
					</div>
					<div class="rowEvenNewSpacing marginTop20 paddingBottom10">
						<span class="floatLeft marginTop10">
						<c:if test="${!employeeAccountForm.readOnly }">
							<input type="button" value="Save" name="btn-submit" id="btn-submit" class="btn_sm orange cursor" />
							</c:if>
							<input type="button" name="Cancel" id="Cancel" value="Cancel" class="btn_sm orange cursor" />
								
						</span>
					</div>
					</form:form>
					 <div class="borderBottomDotted row">
					</div>
					<form:form action="../agency/agencyBillingSetting.html" method="get" commandName="employeeBillingForm" id="editBillingSettingData" enctype="multipart/form-data">
				<div class="row marginLeft20 marginTop25">
					<h3>Billing Contact</h3>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3"> </span>
					<div id="errmsgData" class="FormErrorDisplayText" style="padding-left:0px;"></div>
				</div>
				<div class="rowEvenNewSpacing">
						<span class="lableText3"> Use my account address </span>
						<form:checkbox onclick="copyAccToBillingAddr()"  disabled="${employeeAccountForm.readOnly }" path="billingAddressForm.useMyAccountAddr" name="useAcctAddress" id="useAcctAddress"/>
					</div>
				
				<%-- <c:if test="${count == '1'}"> --%>
					<div class="rowEvenNewSpacing">
						<span class="lableText3"> First Name: </span>
						<form:input path="billingAddressForm.fnameForBillingAddr" id="firstName2" readonly="${employeeAccountForm.readOnly }" name="firstName2"
							class="job_seeker_email" type="text" />
							<span class="required">(Required)</span>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							Last Name:
						</span>
						<form:input path="billingAddressForm.lnameForBillingAddr" readonly="${employeeAccountForm.readOnly }" id="lastName2" name="lastName2" class="job_seeker_email" type="text"/>
						<span class="required">(Required)</span>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3"> Company: </span>
						<form:input path="company" name="company2" id="company2" readonly="${employeeAccountForm.readOnly }"
							class="job_seeker_password" type="text" />
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3"> Street Address: </span>
						<form:input path="billingAddressForm.streetForBillingAddr" readonly="${employeeAccountForm.readOnly }" name="streetAddress2" id="streetAddress2"
							class="job_seeker_password" type="text" />
							<span class="required">(Required)</span>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3"> City: </span>
						<form:input path="billingAddressForm.cityOrTownForBillingAddr"  readonly="${employeeAccountForm.readOnly }" name="cityOrTown2" id="cityOrTown2"
							class="job_seeker_password" type="text" />
							<span class="required">(Required)</span>
					</div>
					
					
					<div class="row">
						<span class="lableTextSelect marginTop13 "> State: </span>
						<form:select path="billingAddressForm.stateBillingAddress" name="state2"
							class="jb_input3 jb_input_width3" disabled="${employeeAccountForm.readOnly }" id="state2">
							<form:option value="0" label="Select" />
							<form:options items="${stateList}" itemValue="stateValue"
								itemLabel="stateValue" />
						</form:select>
						<span class="required">(Required)</span>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3"> ZIP Code: </span>
						<form:input path="billingAddressForm.zipCodeForBillingAddr" readonly="${employeeAccountForm.readOnly }" name="zipCode2" id="zipCode2"
							class="job_seeker_password" type="text" />
							<span class="required">(Required)</span>
					</div>
					<div class="row">
						<span class="lableTextSelect marginTop13 "> Country: </span>
						<form:select path="billingAddressForm.countryForBillingAddr"
							class="jb_input3 jb_input_width3" disabled="${employeeAccountForm.readOnly }" id="country2" name="country2">
							<form:option value="0" label="Select" />
							<form:options items="${countryList}" itemValue="countryValue"
								itemLabel="countryValue" />
						</form:select>
						<span class="required">(Required)</span>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3"> Email: </span>
						<form:input path="email" name="email2" readonly="${employeeAccountForm.readOnly }" id="email2" class="job_seeker_password" 
							type="text"/>
							<span class="required">(Required)</span>
					</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3"> Phone: </span>
						<form:input path="phone" name="phone2" readonly="${employeeAccountForm.readOnly }" id="phone2" class="job_seeker_password"
							type="text"/>
							<span class="required">(Required)</span>
					</div>
					<div class="rowEvenNewSpacing marginTop20 paddingBottom10">
						<span class="floatLeft marginTop10">
						<c:if test="${!employeeAccountForm.readOnly }">
							<input type="button" value="Save"  name="btn-submit2" id="btn-submit2" class="btn_sm orange cursor"/>
							</c:if>
							<input type="button" name="CancelData"  id="CancelData" value="Cancel" class="btn_sm orange cursor"/>
						<a href="#jobSeekerRegister1" id="BackToTopId" style="display: none;">Back To Top</a>
						<a href="#billingId" id="BackToMdlId" style="display: none;">Back To Middle</a>
						</span>
					</div>
					<div class="clearfix"></div>
				<%-- </c:if> --%>
			</form:form>
			</div>
			<div class="clearfix">
			</div>
		</div>
    </body>
</html>