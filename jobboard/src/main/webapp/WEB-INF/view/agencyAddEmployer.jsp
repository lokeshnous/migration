<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Healthcare Jobs</title>

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
 <link rel="stylesheet" type="text/css" href="jquery.autocomplete.css" />
</head>
<body>
	<script type="text/javascript">
		jQuery(document)
				.ready(
						function() {	
							
							var IdData = new Array();
				        	var NameData = new Array();
				        	
							var empName = $("#emplyrNameAutoComplte").val();
							$.ajax({
						        type: "GET",
						        url: "${pageContext.request.contextPath}/agency/getFacilityNamesList.html?term="+empName,
						        dataType: "json",							        
						        contentType: "application/json; charset=utf-8",
						        success: function(data) {							        	
						        								        	
						        	for (var x = 0; x < data.EmpList.length; x++) {
						        		
						               IdData.push(data.EmpList[x].ID);
						               
						               NameData.push(data.EmpList[x].NAME);
						            }
						        	
						        	$("#emplyrNameAutoComplte").autocomplete({
						        		source: NameData,
						        		select : function(event, ui) {
						        			var IndexVal = $.inArray(ui.item.value, NameData);					        			
						        			
						        			var IdVal = IdData[IndexVal];
						        			
											$("#emplyrNameAutoComplte")
													.val(ui.item.value);
											
											$.ajax({
												url: '${pageContext.request.contextPath}/agency/getSelectedFacility.html?facilityId='+IdVal,
												success : function(data) {															
													$('#city').val(data.city);
													$('#street').val(data.street);		
													$('#zipCode').val(data.postcode);
													$('#state').val(data.state);	
													$('#country').val(data.country);
													$('#phone').val(data.phone);
													$('#facilityId').val(data.facilityId);
												},
											});	
											
						        		},
						        	});
										
						        },
						        error: function(XMLHttpRequest, textStatus, errorThrown) {
						           alert(textStatus);
						        }
						    });
							
							$('#save').click(function(){			
					 			
								$.ajax({url:"${pageContext.request.contextPath}/agency/linkSelectedFacility.html",
									data:$('#employerAdd').serialize(),
									type:"POST",
									success: function(data) {
										if(data == ''){
											alert("Data saved successfully!");
											parent.$.nmTop().close();
											window.location.reload();
										}else{
											$("#errmsg").html(data);
										}
									 },
								});
							}); 
							
							$("#emplyrNameAutoComplte").change(function(){
								$('#emplyrNameAutoComplte').val("");
								$('#city').val("");
								$('#street').val("");
								$('#zipCode').val("");
								$('#state').val("");
								$('#country').val("");
								$('#phone').val("");
								$('#facilityId').val("");
							});
						});

		function MM_jumpMenu(targ, selObj, restore) { //v3.0
			eval(targ + ".location='"
					+ selObj.options[selObj.selectedIndex].value + "'");
			if (restore)
				selObj.selectedIndex = 0;
		}
	</script>
</head>

<body class="job_board">
	<form:form method="post" id="employerAdd"
		action="../agency/linkSelectedFacility.html"
		commandName="empRegisterForm" enctype="multipart/form-data">
		<div id="empRegister1" class="job_seeker_login popUpContainer"
			style="display: block">
			<div class="popupHeader">
				<h2>ADD EMPLOYER</h2>
				<a href="#"><img src="../resources/images/Close.png" width="19"
					height="19" alt="" class="nyroModalClose cursor"></a>
			</div>
			<form:hidden path="facilityId" id="facilityId"/>

			<div class="popUpContainerWrapper">

				<div class="rowEvenNewSpacing marginTop10 marginLeft15">
					<h3>EMPLOYER DETAILS</h3>
				</div>
				<div id="errmsg" class="validationMsg">
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Employer Name:</span>
					<form:input path="firstName" class="job_seeker_email"
						id="emplyrNameAutoComplte" />
					<span class="required">(Required)</span>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Street Address:</span> <input readonly
						type="text" name="street" id="street" class="job_seeker_email" /> <!-- <span
						class="required">(Required)</span> -->
				</div>

				<div class="rowEvenNewSpacing">
					<span class="lableText3">City:</span> <input readonly type="text"
						name="city" id="city" class="job_seeker_email" /> <!-- <span class="required">(Required)</span> -->
				</div>				

				<div class="rowEvenNewSpacing">
					<span class="lableText3">State:</span> <input readonly type="text"
						name="state" id="state" class="job_seeker_email" /> <!-- <span
						class="required">(Required)</span> -->
				</div>

				<div class="rowEvenNewSpacing">
					<span class="lableText3">Country:</span> <input readonly
						type="text" id="country" name="country" class="job_seeker_email" /> <!-- <span
						class="required">(Required)</span> -->
				</div>

				<div class="rowEvenNewSpacing">
					<span class="lableText3">Zip Code:</span> <input readonly
						type="text" id="zipCode" name="zipCode" class="job_seeker_email" /> <!-- <span
						class="required">(Required)</span> -->
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Phone:</span> <input readonly type="text"
						name="primaryPhone" id="phone" class="job_seeker_email" /> <!-- <span
						class="required">(Required)</span> -->
				</div>
				<div class="rowEvenNewSpacing marginTop10 paddingBottom10">
					<span class="floatLeft marginTop10"><input type="button"
						style="margin-top: -4px;" id="save" value="Save" class="btn_sm orange cursor"/>
						<a class="nyroModalClose btn_sm orange cursor" href="#">Cancel</a>	
						</span>
				</div> 
				<div class="clearfix"></div>

			</div>
			<div class="clearfix"></div>
		</div>
	</form:form>
</body>
</html>