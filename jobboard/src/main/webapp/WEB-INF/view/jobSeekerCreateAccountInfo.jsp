<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="robots" content="noindex, follow">
<title>ADVANCE Heathcare Jobs</title>
<!-- JAVASCRIPT FILES -->
<jsp:include page="common/include.jsp" />
<link href="../resources/css/JB.css" rel="stylesheet" type="text/css" />
<!-- <script type="text/javascript" language="javascript"
	src="/media/js/jquery.js"></script> -->
<!--  <link href="../resources/css/jquery-auto-ui.css" rel="stylesheet"
	type="text/css"> -->
<script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>
<link href="../resources/css/jquery.megamenu.css" rel="stylesheet"
	type="text/css" />
<link href="../resources/css/SliderStyles.css" rel="stylesheet"
	type="text/css">


<link rel="stylesheet" type="text/css" media="screen"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css">

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
		    jQuery(document).ready(function(){
		    	//$('#mailCheckboxSub').prop('checked', true);
		    	$("#phone").inputmask("mask", {"mask": "(999) 999-9999"});
		    //	$('[id^=zipCode]').keypress(validateNumber);
		    jQuery(".megamenu").megamenu();

		    if($("#MyProfession :selected").text() == "Others"){
		         $("#OtherProffession").show();
		    }else{
		    	$("#OtherProffession").hide();
		    }
		    
			
			/* $('#printCheckboxSub').change(function(){
				var printCheckbox = $('#printCheckboxSub').is(':checked');
				var digCheckbox = $('#digCheckboxSub').is(':checked');
				var enewsCheckboxSub = $('#enewsCheckboxSub').is(':checked');				
				var professionId = $('#MyProfession').val();
				var selCountry = $('#countryDpId').val();
				
				 $.ajax({url:"${pageContext.request.contextPath}/jobseekerregistration/getPublicationDetails.html?professionId="+professionId+
						"&selCountry="+selCountry+"&printCheckbox="+printCheckbox+"&digCheckbox="+digCheckbox+"&enewsCheckboxSub="+enewsCheckboxSub,
					data:$('#professionId').serialize(),
					type:"GET",
					success: function(data) {					
						$("#publicationDetails").html(data);
						var country = $('#countryDpId').val();
						if (country == "CA") {    
							$("#printCheckboxSub").attr("disabled", true);
							$('#printCheckboxSub').attr("checked",false);
		            	}else{
		            		$("#printCheckboxSub").attr("disabled", false);  
		            	} 
					 },
				});
			}); */
			
			/* $('#digCheckboxSub').change(function(){
				var printCheckbox = $('#printCheckboxSub').is(':checked');
				var digCheckbox = $('#digCheckboxSub').is(':checked');
				var enewsCheckboxSub = $('#enewsCheckboxSub').is(':checked');
				var professionId = $('#MyProfession').val();
				var selCountry = $('#countryDpId').val();
				
				 $.ajax({url:"${pageContext.request.contextPath}/jobseekerregistration/getPublicationDetails.html?professionId="+professionId+
					 "&selCountry="+selCountry+"&printCheckbox="+printCheckbox+"&digCheckbox="+digCheckbox+"&enewsCheckboxSub="+enewsCheckboxSub,
					data:$('#professionId').serialize(),
					type:"GET",
					success: function(data) {					
						$("#publicationDetails").html(data);
						var country = $('#countryDpId').val();
						if (country == "CA") {    
							$("#printCheckboxSub").attr("disabled", true);   
							$('#printCheckboxSub').attr("checked",false);
		            	}else{
		            		$("#printCheckboxSub").attr("disabled", false);  
		            	} 
					 },
				});
			}); */
			
			/* $('#enewsCheckboxSub').change(function(){
				var printCheckbox = $('#printCheckboxSub').is(':checked');
				var digCheckbox = $('#digCheckboxSub').is(':checked');
				var enewsCheckboxSub = $('#enewsCheckboxSub').is(':checked');
				var professionId = $('#MyProfession').val();
				var selCountry = $('#countryDpId').val();
				
				 $.ajax({url:"${pageContext.request.contextPath}/jobseekerregistration/getPublicationDetails.html?professionId="+professionId+
					 "&selCountry="+selCountry+"&printCheckbox="+printCheckbox+"&digCheckbox="+digCheckbox+"&enewsCheckboxSub="+enewsCheckboxSub,
					data:$('#professionId').serialize(),
					type:"GET",
					success: function(data) {					
						$("#publicationDetails").html(data);
						var country = $('#countryDpId').val();
						if (country == "CA") {    
							$("#printCheckboxSub").attr("disabled", true);   
							$('#printCheckboxSub').attr("checked",false);
		            	}else{
		            		$("#printCheckboxSub").attr("disabled", false);  
		            	} 
					 },
				});
			}); */
			
			 $('#countryDpId').change(function(){
				var professionId = $('#MyProfession').val();
				var selCountry = $('#countryDpId').val();
				
				 $.ajax({url:"${pageContext.request.contextPath}/jobseekerregistration/getPublicationDetails.html?professionId="+professionId+
					 "&selCountry="+selCountry,
					data:$('#professionId').serialize(),
					type:"GET",
					success: function(data) {					
						$("#publicationDetails").html(data);
						var country = $('#countryDpId').val();
						if (country == "CA") {    
							$("#printCheckboxSub").attr("disabled", true);   
							$('#printCheckboxSub').attr("checked",false);
		            	}else{
		            		$("#printCheckboxSub").attr("disabled", false);  
		            	} 
					 },
				});
			}); 
			
			$('#MyProfession').change(function(){
				var professionId = $('#MyProfession').val();
				var selCountry = $('#countryDpId').val();
				if($("#MyProfession :selected").text() == "Others"){
			         $("#OtherProffession").show();
			    }else{
			    	$("#OtherProffession").hide();
			    	$.ajax({url:"${pageContext.request.contextPath}/jobseekerregistration/getPublicationDetails.html?professionId="+professionId+
						 "&selCountry="+selCountry,
						data:$('#professionId').serialize(),
						type:"GET",
						success: function(data) {					
							$("#publicationDetails").html(data);
							var country = $('#countryDpId').val();
							if (country == "CA") {    
								$("#printCheckboxSub").attr("disabled", true);   
								$('#printCheckboxSub').attr("checked",false);
			            	}else{
			            		$("#printCheckboxSub").attr("disabled", false);  
			            	} 
						 },
					});
			    }
							 
			});
					
			$('#finishId').click(function() {
			    $.ajax({url:"${pageContext.request.contextPath}/jobseekerregistration/saveJobSeekerProfileFinish.html",
				data: $('#registerFormId').serialize()+ "&"+$('#subscriptionFormId').serialize(),
				type: "POST",
				success: function(data) {
					if(data.message != undefined){
						$("#errorMsg").text(data.message);
						var href = $('#BackToTopId').attr('href');
						location.href = href;
					}
					if(data.success != undefined){
						/* alert('You are an existing Advance application user! Your existing credentials are used to register. Please use your old credentials to access Job Board application.'); */
						window.location.href = "${pageContext.request.contextPath}/jobseekerregistration/authenticateJs.html";
					}
				 },error : function(data) {
					 alert("error");
					},
					complete : function(data) {
					}
			});
			   
			});
			
			$('#backId').click(function() {				
				$("form").attr("action","${pageContext.request.contextPath}/jobseekerregistration/saveJobSeekerProfile.html?Back=true");
				$("#registerFormId").submit();
			    
			});
			   
	
		    
			$('.focus').focus();
			//Auto complete on selecting city
		/* 	$("#cityAutoPopulation").autocomplete({
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
								//alert(country);
								$('#countryDpId').val(country);
								var modCity = $("#cityAutoPopulation").val();
								modCity = modCity.substring(0,modCity.lastIndexOf(", "));
								$("#cityAutoPopulation").val(modCity);
								if (country == "CA") {    
									$("#printCheckboxSub").attr("disabled", true);   
									$('#printCheckboxSub').attr("checked",false);
				            	}else{
				            		$("#printCheckboxSub").attr("disabled", false);  
				            	} 
							},
						}); 
					},error : function(data) {
					},
					complete : function(data) {
					}
					});
				}
			});  */

			//Auto complete on selecting zipcode
			/* $("#zipCode").autocomplete({
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
						},
						complete : function(data) {
							if ($("#countryDpId").val() == "CA") {  
								$("#printCheckboxSub").attr("disabled", true);  
								$('#printCheckboxSub').attr("checked",false);
			            		 //$("#waitmsg").hide();              	
			            	}else{
			            		$("#printCheckboxSub").attr("disabled", false);  
			            	} 
						}
					});		
				}				
			});	 */
			
			/* $("#zipCode").change(function(){
				//$('#cityAutoPopulation').val("");
				$('#stateDpId').val("");
				$('#countryDpId').val("");
			}); */
			
			/* $("#cityAutoPopulation").change(function(){
				//$('#zipCode').val("");
				//$('#stateDpId').val("");
				//$('#countryDpId').val("");
			}); */
			
			$("#waitmsg").hide();
		     
			var countryVal = $('.countryDpId').val();
			$('#countryDpId').val("USA");
		    /* if($(":checkbox").is(':checked')){
		     	$("#waitmsg").show();
		    } */
		    if (countryVal == "CA") {
		    	var flag = false;
		    	
		    	if($(":checkbox:first").is(":checked")){
		    		flag = true;
		    	}
		    	$("#printCheckboxSub").attr("disabled", true); 
		    	$('#printCheckboxSub').attr("checked",false);
	          	if(flag){
	          		$("#waitmsg").show();
	          		//$(":checkbox:first").attr("checked",true);
		    	}
		    	else{
		    		$("#waitmsg").hide();
		    	}
          	}
		    
		});
		   
		    function modifyPrint(size) {
				var printCheckbox = $('#printCheckboxSub').is(':checked');
				if (!printCheckbox) {
					for ( var i = 0; i < size; i++) {
						document.getElementById('print' + i).disabled = true;
					}
				} else {
					for ( var i = 0; i < size; i++) {
						document.getElementById('print' + i).disabled = false;
					}
				}
			}

			function modifyDig(size) {
				var digCheckbox = $('#digCheckboxSub').is(':checked');
				if (!digCheckbox) {
					for ( var i = 0; i < size; i++) {
						document.getElementById('dig' + i).disabled = true;
					}
				} else {
					for ( var i = 0; i < size; i++) {
						document.getElementById('dig' + i).disabled = false;
					}
				}
			}

			function modifyNews(size) {
				var enewsCheckbox = $('#enewsCheckboxSub').is(':checked');
				if (!enewsCheckbox) {
					for ( var i = 0; i < size; i++) {
						document.getElementById('news' + i).disabled = true;
					}
				} else {
					for ( var i = 0; i < size; i++) {
						document.getElementById('news' + i).disabled = false;
					}
				}
			}    
		</script>
<script type="text/javascript">
		    function cancelProcess(){
		    	window.location.href = '${pageContext.request.contextPath}/healthcare/index.html';
		    }		
		</script>

<script type="text/javascript">
		    function modifyMsg(id){
				/* var checkIt = $(id).is(':checked');
				if(checkIt){
					 $("#waitmsg").show();
				}else{
					 $("#waitmsg").hide();
				} */
		    	/* if($(":checkbox").is(':checked')){//and not equal to disabled
				     $("#waitmsg").show();
				     }else{
				    	 $("#waitmsg").hide();	 
				     } */
			}
           
            function displayMsg(selectedVal) {
            	if (selectedVal == "CA") { 
            		$("#printCheckboxSub").attr("disabled", true);  
            		$('#printCheckboxSub').attr("checked",false);
           		 $("#waitmsg").hide();
           		               	
           	}else{
           		$("#printCheckboxSub").attr("disabled", false);  
           	}
			}
            function modify(selectedVal){
            	displayMsg(selectedVal);
            }
            
        </script>
</head>

<body class="job_board">
	<div class="ad_page_top">${adPageTop }</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">
			<div class="main">
				<jsp:include page="../templates/templates_header.jsp"></jsp:include>
				<div class="row">
					<!-- Step 1 -->

					<div id="jobSeekerRegister1"
						class="job_seeker_login leftFormHolder" style="display: block">
						<h2 class="sectionSubHeader">Step 2: Your Information</h2>
						<h3 class="marginLeft10">Contact Information</h3>
						<%-- <div class="rowEvenNewSpacing">
						
						<!-- <span class="lableText3"></span> -->
						<img src="<%=request.getContextPath()%>/resources/images/advancePass.png" style="margin:0 0 0px 95px;"/>
						</div> --%>
						<%-- <c:if test="${not empty message}"> --%>
							<div class="rowEvenNewSpacing" >
							<div style="flot:left;">
							<img src="<%=request.getContextPath()%>/resources/images/advancePass.png" style="margin:0px;"/>
							</div>
								<span class="lableText3"></span> <span
									class="FormErrorDisplayText" id= "errorMsg"> ${message}<br /></span>

							</div>
						<%-- </c:if> --%>
						<form:form method="Post" action="saveJobSeekerProfile.html"
							commandName="registerForm" enctype="multipart/form-data" id="registerFormId">
							<form:hidden path="clickBack" />
							<form:hidden path="advPassUser" />
							<form:hidden path="advPassUserWithNullPass" />
							<c:forEach items="${registerForm.listProfAttribForms}"
								var="profAttrib" varStatus="status">

								<c:if test="${profAttrib.strLabelName == 'First Name'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">First Name:</span>
										<form:input
											path="listProfAttribForms[${status.index}].strLabelValue"
											class="job_seeker_password textBox350 focus" />
										<span class="required">(Required)</span>
									</div>
								</c:if>

								<c:if test="${profAttrib.strLabelName == 'Middle Name'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">Middle Name:</span>
										<form:input
											path="listProfAttribForms[${status.index}].strLabelValue"
											class="job_seeker_password textBox350" />
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Last Name'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">Last Name:</span>

										<form:input
											path="listProfAttribForms[${status.index}].strLabelValue"
											class="job_seeker_password textBox350" />
										<span class="required">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Street Address'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">Street Address:</span>
										<form:input
											path="listProfAttribForms[${status.index}].strLabelValue"
											class="job_seeker_password textBox350" />
										<span class="required">(Required)</span>

									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Street Address1'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3"></span>
										<form:input
											path="listProfAttribForms[${status.index}].strLabelValue"
											class="job_seeker_password textBox350" />
										<span class="required"></span>
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
								<c:if test="${profAttrib.strLabelName == 'City'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">City:</span>
										<form:input
											path="listProfAttribForms[${status.index}].strLabelValue"
											class="job_seeker_password textBox350"
											id="cityAutoPopulation" />
										<span class="required">(Required)</span>
									</div>
								</c:if>
								
								<c:if test="${profAttrib.strLabelName == 'State / Province'}">
									<div class="row">
										<span class="lableTextSelect ">State / Province:</span>
										<form:select
											path="listProfAttribForms[${status.index}].strLabelValue"
											id="stateDpId" class="jb_input3 jb_input_width3">
											<form:option value="0" label="Select" />
											<form:options items="${profAttrib.dropdown}"
												itemValue="optionId" itemLabel="optionName" />
										</form:select>
										<span class="requiredTopmargin">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Country'}">
									<div class="row">
										<span class="lableTextSelect ">Country:</span>
										<form:select
											path="listProfAttribForms[${status.index}].strLabelValue"
											id="countryDpId"
											class="countryDpId jb_input3 jb_input_width3"
											onchange="modify(this.value);">
											<form:option value="0" label="Select" />
											<form:options items="${profAttrib.dropdown}"
												itemValue="optionId" itemLabel="optionName" />
										</form:select>
										<span class="requiredTopmargin">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Phone Number'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">Phone Number:</span>
										<form:input
											path="listProfAttribForms[${status.index}].strLabelValue"
											id="phone" class="job_seeker_password textBox350" />
										<!-- <div class="toolTip marginTop5 marginLeft5">
											<span class="classic">Valid format for Phone number is
												(xxx)xxx-xxxx</span>
										</div> -->
										<span class="required"></span>
									</div>
								</c:if>


								<c:if test="${profAttrib.strLabelName == 'My Industry'}">
									<div class="rowH3Holder">
										<h3 class="marginLeft10">Employment information</h3>
									</div>
									<div class="rowEvenNewSpacing">
										<span class="lableText3">My Industry:</span>
										<form:input readonly="true" path="myIndustry"
											class="job_seeker_password textBox350" />
										<!-- <div class="toolTip marginTop5 marginLeft5">
											<span class="classic">Enter the industry you serve.
												Example: Healthcare</span>
										</div>
										<span class="required">(Required)</span> -->
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'My Profession'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">My Profession:</span>

										<form:select id="MyProfession"
											path="listProfAttribForms[${status.index}].strLabelValue"
											class="jb_input3 jb_input_width2">
											<form:option value="0" label="Select" />
											<form:options items="${profAttrib.dropdown}"
												itemValue="optionId" itemLabel="optionName" />
										</form:select>

										<form:input id="OtherProffession" path="otherProfession"
											class="job_seeker_password textBox150" />
										<div class="toolTip marginTop5 marginLeft5">
											<span class="classic">Enter the general field in which
												you work. Example: Respiratory Therapy</span>
										</div>
										<span class="required">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'My Specialty'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">My Specialty:</span>
										<form:input
											path="listProfAttribForms[${status.index}].strLabelValue"
											class="job_seeker_password textBox350" />
										<div class="toolTip marginTop5 marginLeft5">
											<span class="classic">Enter the area in which you
												specialize. Example: Neonatal/Pediatrics</span>
										</div>
										<span class="required">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'My Job Title'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">My Job Title:</span>
										<form:input
											path="listProfAttribForms[${status.index}].strLabelValue"
											class="job_seeker_password textBox350" />
										<div class="toolTip marginTop5 marginLeft5">
											<span class="classic">Enter your official job title.
												Example: Registered Respiratory Therapist</span>
										</div>
										<span class="required">(Required)</span>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'My Resume'}">
									<div class="rowEvenNewSpacing">
										<span class="lableText3">My Resume:</span>

										<!-- <div class="floatLeft"><input name="textfield4" type="file" id="textfield4" size="20" class="job_seeker_login_email fileType" /></div> -->
										<div>
											<form:input path="uploadResume" id="image" type="file"
												class="job_seeker_login_email fileType" />
										</div>
										<div class="required">&nbsp;&nbsp;&nbsp;&nbsp;</div>
									</div>
								</c:if>
								<c:if test="${profAttrib.strLabelName == 'Im seeking'}">
									<div class="row">
										<span class="lableTextSelect ">I'm seeking:</span>
										<form:select
											path="listProfAttribForms[${status.index}].strLabelValue"
											class="jb_input3 jb_input_width3">
											<form:option value="0" label="Select" />
											<form:options items="${profAttrib.dropdown}"
												itemValue="optionId" itemLabel="optionName" />
										</form:select>
									</div>
								</c:if>

								<c:if test="${profAttrib.strLabelName == 'Subscriptions'}">
									<div class="rowH3Holder">
										<h3 class="marginLeft10">Subscriptions</h3>
									</div>
									<div class="row paddingBottom10 marginLeft10">I would
										like the following sent to me so I can stay up to date with
										the latest healthcare news and information. The following
										subscriptions are always a free service.</div>

									<table  border="0" cellspacing="0" cellpadding="0"
						class="grid2 " id="publicationId">
						<tr>
							<td valign="top" class="Padding0">
								<table>
									<tr>
										<td width="290" valign="top" class="Padding0"><%-- <form:checkbox path="printCheckbox"
												id="printCheckboxSub"
												 /> --%>
												 <%-- onchange="modifyPrint('${listpublicationprint.size()}')" --%>
												 <label
											for="checkbox"><strong>Print Magazine</strong></label>
											<%-- <c:if test="${!listpublicationprint.isEmpty()}">
												<c:forEach items="${listpublicationprint}"
													var="subscriptionsprint" varStatus="status">

													<form:checkbox path="printSub"
														label="${subscriptionsprint.optionName}"
														value="${subscriptionsprint.optionId}"
														cssStyle="width:20px" id="print${status.index}" />
													<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
													 												
										       </c:forEach>

											</c:if> --%></td>

										<td width="290" valign="top" class="Padding0"><%-- <form:checkbox path="digCheckbox"
												id="digCheckboxSub" /> --%>
												<%-- onchange="modifyDig('${listpublicationdigital.size()}')"  --%>
												<label
											for="checkbox"><strong>Digital Magazine</strong></label>
											<%-- <c:if test="${!listpublicationdigital.isEmpty()}">
												<c:forEach items="${listpublicationdigital}"
													var="subscriptionsprint" varStatus="status">

													<form:checkbox path="digSub"
														label="${subscriptionsprint.optionName}"
														value="${subscriptionsprint.optionId}"
														cssStyle="width:20px" id="dig${status.index}" />
													<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;														
										       </c:forEach>
											</c:if> --%></td>

										<td width="290" valign="top" class="Padding0"><%-- <form:checkbox path="enewsCheckbox"
												id="enewsCheckboxSub" /> --%>
												<%-- onchange="modifyNews('${listnewsletter.size()}')" /> --%>
												<label
											for="checkbox"><strong>E-newsletters</strong></label>
											<%-- <c:if test="${!listnewsletter.isEmpty()}">
												<c:forEach items="${listnewsletter}"
													var="subscriptionsprint" varStatus="status">

													<form:checkbox path="newsSub"
														label="${subscriptionsprint.optionName}"
														value="${subscriptionsprint.optionId}"
														cssStyle="width:20px" id="news${status.index}" />
													<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;														
										       </c:forEach>
											</c:if> --%></td>

										<td width="130" valign="top" class="Padding0"><%-- <form:checkbox path="mailCheckbox"
												id="mailCheckboxSub" /> --%><label class="floatLeft"   for="checkbox"><strong>Emails</strong></label>
												<div class="toolTip marginLeft5">
											<span class="classic">Select this option if you want us to send you emails regarding featured career opportunities.</span>
										</div></td>									
									</tr>
									
								</table>
							</td>
							
						</tr>
					</table>
					
										<div id="publicationDetails" class="publicationDetails"></div>

									<%-- <div class="centerAlign">
										<ul>
											<c:forEach items="${profAttrib.dropdown}" var="dropdown"
												varStatus="subIndex">
												<li>
													<div>
														<form:checkbox
															path="listProfAttribForms[${status.index}].subs"
															label="${dropdown.optionName}"
															value="${dropdown.optionId}" cssStyle="width:20px"
															id="checkIt${subIndex.index}" onchange="modifyMsg(this)" />
													</div>
												</li>
											</c:forEach>
										</ul>
									</div> --%>
								</c:if>
							</c:forEach>
							<div id="waitmsg" class="FormErrorDisplayText">
								<span>Please choose required publications for selected
									subscriptions using DASHBOARD -> MODIFY SUBSCRIPTION option.</span>
							</div>
							<div class="popUpButtonRow">
								<!-- <a href="" class="btn_sm white">Back</a> -->
								<input type="button" value="Back" class="white cursor" id="backId"
									name="Back" /> <input type="button" id="finishId"
									value="Finish" class="white cursor" name="Finish" />
								<c:if test="${registerForm.bReadOnly == false}">
									<input type="button" value="Cancel" onclick="cancelProcess();"
										class="white cursor" name="Cancel" />
								</c:if>
								<%-- <a
									href="<%=request.getContextPath()%>/healthcare/index.html"
									class="btn_sm orange cancelacount">Cancel</a> --%>
								<!-- <input type="submit" value="Cancel" class="orange" name="Cancel"/>  -->
							</div>
							<div class="clearfix"></div>
						</form:form>
						<div class="clearfix"></div>
					</div>
					<!-- Step 2 -->

					<div class="ad_col_right">
						<div id="adPageRightTop">${adPageRightTop}</div>
						<div id="adPageRightMiddle">${adPageRightMiddle}</div>

						<div class="follow_us">
							<h2>Stay Connected</h2>
							<!-- <p>Stay connected to the latest jobs.</p> -->
							<a href="${followuplinkfacebook}" target="_blank"><span
								class="social facebook_link">Facebook</span></a> <a
								href="${followuplinktwitter}" target="_blank"><span
								class="social twitter_link">Twitter</span></a> <a
								href="${followuplinkyoutube}" target="_blank"><span
								class="social youTube_link">YouTube</span></a>
						</div>
						<br class="clearfix" />

					</div>
				</div>
				<div class="clearfix"></div>
				<div class="ad_wrapper">${adPageBottom }</div>
				<a href="#errorMsg" id="BackToTopId" style="display: none;">Back To Top</a>
			</div>
			<!-- main -->

		</div>
		<!-- end main_wrapper_inside -->
	</div>
	<!-- end main_wrapper_outside -->
	<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
</body>
</html>