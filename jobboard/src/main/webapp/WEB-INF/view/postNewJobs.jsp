<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />
<!-- <link rel="stylesheet" type="text/css" media="screen" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css"> -->

<!-- JAVASCRIPT FILES -->
<!-- <script type="text/javascript"
	src="../resources/js/jquery.cycle.all.min.js"></script> -->
<script type="text/javascript" src="../resources/js/slider.js"></script>
<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" media="screen"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css">
<script type="text/javascript">
tinyMCE.init({
	mode : "specific_textareas",
	editor_selector : "mceEditor",
	theme : "advanced",
	theme_advanced_buttons1 : "mybutton,bold,italic,underline,separator,strikethrough,justifyleft,justifycenter,justifyright, justifyfull,bullist,numlist,undo,redo,link,unlink",
	theme_advanced_buttons2 : "",
	theme_advanced_buttons3 : "",
	theme_advanced_toolbar_location : "top",
	theme_advanced_toolbar_align : "left",	
	max_chars : 5000,
	max_chars_indicator : "characterCounter",
	/*plugins : 'inlinepopups',*/
	plugins : "autolink,advlink,maxchars",
	content_css : "css/mycontent.css"
});

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

function appendURL(){
	var str = $("#applyToURLTSId").val();
	if(str.indexOf('www')==0 && str.indexOf('.')==3){
		str = 'http://'+str;
		$("#applyToURLTSId").val(str);
	};
}

function appendURLForATS(){
	var str = $("#applyToATSIPId").val();
	if(str.indexOf('www')==0 && str.indexOf('.')==3){
		str = 'http://'+str;
		$("#applyToATSIPId").val(str);
	};
}

</script>
<script type="text/javascript">
	function populateTemplates() {

		var isChecked = $("#templateOverride").is(':checked');

		$
				.ajax({
					url : '${pageContext.request.contextPath}/employer/getFacilityTemplate.html?isChecked='
							+ isChecked
							+ '&company='
							+ $("#companyAutoPopulation").val()+'&product='+$("#postTypeId").val(),
							
					success : function(data) {
						$('#templateId option').remove();

						$('#templateId').append(
								'<option value="0">None</option>');

						var dataLen = data.length;
						if (dataLen > 0) {

							if (dataLen > 1) {

								for ( var i = 0; i < dataLen; i++) {
									$('#templateId').append(
											'<option value="' + data[i]["optionId"] + '">'
													+ data[i]["optionName"]
													+ '</option>');
								}

							} else {
								$('#templateId').append(
										'<option value="' + data[0]["optionId"] + '" selected="selected">'
												+ data[0]["optionName"]
												+ '</option>');
							}

						}
					},
				});
	}

	function callTemplate() {
		$
				.ajax({
					url : '${pageContext.request.contextPath}/employer/getTemplate.html?company='
							+ $("#companyAutoPopulation").val()+'&product='+ $("#postTypeId").val(),
					success : function(data) {
						$('#templateId option').remove();

						$('#templateId').append(
								'<option value="0">None</option>');

						var dataLen = data.length;
						if (dataLen > 0) {

							if (dataLen > 1) {

								for ( var i = 0; i < dataLen; i++) {
									$('#templateId').append(
											'<option value="' + data[i]["optionId"] + '">'
													+ data[i]["optionName"]
													+ '</option>');
								}

							} else {
								$('#templateId').append(
										'<option value="' + data[0]["optionId"] + '" selected="selected">'
												+ data[0]["optionName"]
												+ '</option>');
							}
						}
					},
				});
	}
	//Limit text area characters
	function limitText(limitField, limitCount, limitNum) {
		/* 		alert(limitField.value.length+""+limitCount.value+""+limitNum); */
		if (limitField.value.length > limitNum) {
			limitField.value = limitField.value.substring(0, limitNum);
		} else {
			limitCount.value = limitNum - limitField.value.length;
		}
	}

	jQuery(document)
			.ready(
					function() {
						//$('[id^=zipCodeITId]').keypress(validateNumber);
						$(".postingInventory").displaypopup(
								".postingInventory", "790", "360");

						$("#scheduleStartDivId").hide();

						function validateData() {
							$("#errTrackPixcel").text('');
							var hasError = true;
							var trackUrl = $.trim($('#trackPixel').val());
					    	var regExUrl = /^([a-z]([a-z]|\d|\+|-|\.)*):(\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?((\[(|(v[\da-f]{1,}\.(([a-z]|\d|-|\.|_|~)|[!\$&'\(\)\*\+,;=]|:)+))\])|((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=])*)(:\d*)?)(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*|(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)|((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)|((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)){0})(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i; 
					    	if(trackUrl != "" && !regExUrl.test(trackUrl)) {
								hasError = false;
								$("#errTrackPixcel").text(
										'Please enter the valid url');
							}
							return hasError;
						}
						$("#postNewJobButId").click(function() {
							if (!validateData()) {
								return false;
							}
							if (confirm("Do you want to post this job?")) {
								$("#postNewJobButHideId").click();
							}
						});
						
						$("#saveJobPostId").click(function() {
							$("#saveJobPostId").attr("disable",true);
							$("#saveJobPostId").hide();
							$("#saveJobPostId").attr("class","btn_sm white");
							if (!validateData()) {
								return false;
							}
							if ($("#activeOrInactive").val() == 'true') {
									$('#postNewJobFormId select').each(function(){
										$(this).attr("disabled", false);
						    		});
									
									$('#postNewJobFormId input').each(function(){
										$(this).attr("disabled", false);
						    		});
									
							}
							$("#postNewJobButHideId").click();
						});

						$("#saveAsDraftJobButId")
								.click(
										function() {
											if (!validateData()) {
												return false;
											}
											if (confirm("Do you want to save this job as Draft?")) {
												$("#savePostJobButHideId")
														.click();
											}
										});

						//Date picker
						$(function() {
							$(".datepicker")
									.datepicker(
											{
												minDate : 1,
												onSelect : function(dateText,
														inst) {
													var datepicker = $(
															"#startDate").val();
													var joindate = new Date(
															datepicker);
													var numberOfDaysToAdd = 30;
													joindate
															.setDate(joindate
																	.getDate()
																	+ numberOfDaysToAdd);
													var dd = joindate.getDate();
													if (dd < 10)
														dd = '0' + dd;
													var mm = joindate
															.getMonth() + 1;
													if (mm < 10)
														mm = '0' + mm;
													var y = joindate
															.getFullYear();
													var joinFormattedDate = mm
															+ '/' + dd + '/'
															+ y;
													$("#endDate").val(
															joinFormattedDate);
													$("#startDateHdId").val(
															datepicker);
													$("#endDateHdId").val(
															joinFormattedDate);
												}
											});
						});

						//Popup on click of schedule button
						$("#scheduleNewJobButId").click(function() {
							if (!validateData()) {
								return false;
							}
							$("#errMsgDialog").text("");
							$("#scheduleStartDivId").dialog({
								resizable : false,
								height : 400,
								width : 500,
								zIndex : -1,
								modal : true,
								data : $('#postNewJobFormId').serialize(),
								backgroundColor : '#F0F0F0',
								buttons : {
									"Schedule" : function() {
										if ($("#startDate").val() != '') {
											$(this).dialog("close");
											$("#scheduleJobButHideId").click();
										}else{
											$("#errMsgDialog").text("Please Enter the Dates.");
										}
									},
									"Cancel" : function() {
										$(this).dialog("close");
									}
								},
								modal : true
							});
							$("#startDate").val($("#startDateHdId").val());
							$("#endDate").val($("#endDateHdId").val());
						});

						//Auto complete on selecting city
						$("#cityAutoPopulation")
								.autocomplete(
										{
											source : '${pageContext.request.contextPath}/employer/getCityList.html',
											width : 500,
											select : function(event, ui) {
												$("#cityAutoPopulation").val(
														ui.item.value);
												$
														.ajax({
															url : '${pageContext.request.contextPath}/employer/getState.html?city='
																	+ $(
																			"#cityAutoPopulation")
																			.val(),
															success : function(
																	data) {
																$('#stateDpId')
																		.val(
																				data);

																$
																		.ajax({
																			url : '${pageContext.request.contextPath}/employer/getPostalCode.html?city='
																					+ $(
																							"#cityAutoPopulation")
																							.val()
																					+ '&state='
																					+ $(
																							"#stateDpId")
																							.val(),
																			success : function(
																					data) {
																				$(
																						'#zipCodeITId')
																						.val(
																								data);
																			},
																		});
																$
																		.ajax({
																			url : '${pageContext.request.contextPath}/employer/getCountry.html?city='
																					+ $(
																							"#cityAutoPopulation")
																							.val()
																					+ '&state='
																					+ $(
																							"#stateDpId")
																							.val()
																					+ '&postalCode='
																					+ $(
																							"#zipCodeITId")
																							.val(),
																			success : function(
																					country) {
																				$(
																						'#countryDpId')
																						.val(
																								country);
																				var modCity = $("#cityAutoPopulation").val();
																				modCity = modCity.substring(0,modCity.lastIndexOf(", "));
																				$("#cityAutoPopulation").val(modCity);
																			},
																		});
															},
														});
											},
										});
						
						
									
									//Need to set default country as USA
									//$('#countryDpId').val("USA");
									
						//Auto complete on selecting zipcode			
						$("#zipCodeITId")
								.autocomplete(
										{
											source : '${pageContext.request.contextPath}/employer/getPostalCodeAutoPopulation.html',
											select : function(event, ui) {
												$("#zipCodeITId").val(
														ui.item.value);
												$('#cityAutoPopulation')
														.val("");
												$('#stateDpId').val("");
												$
														.ajax({
															url : '${pageContext.request.contextPath}/employer/getLocations.html?zipCode='
																	+ $(
																			"#zipCodeITId")
																			.val(),
															success : function(
																	data) {
																$('#stateDpId')
																		.val(
																				data.state);
																$(
																		'#countryDpId')
																		.val(
																				data.country);
																$(
																		"#cityAutoPopulation")
																		.val(
																				data.city);
															},
														});
											}
										});
						$("#zipCodeITId").change(function(){
							$('#cityAutoPopulation').val("");
							$('#stateDpId').val("");
							$('#countryDpId').val("");
						});
						
						$("#cityAutoPopulation").change(function(){
							$('#zipCodeITId').val("");
							$('#stateDpId').val("");
							$('#countryDpId').val("");
						});
						
						//Need to set default country as USA
						$('#countryDpId').val("USA");
						
						if ($("#readOnly").val() == 'true') {
							$('#postNewJobFormId').each(
									function() {
										$("#postNewJobFormId :input").attr(
												"disabled", true);
										$("#postNewJobFormId :button").attr(
												"hidden", true);
										$("#cancel").attr("hidden", false);
										$("#cancel").attr("disabled", false);
										
									});
							
							/* IE-8 - hide the buttons*/
				            $("#postNewJobButId").hide();
				            $("#scheduleNewJobButId").hide();
				            $("#saveAsDraftJobButId").hide();
				            
				            /* showing job post type tooltip */
							$("#postTypeToolTip").attr("title",$("#postTypeId :selected").text());

						}
						
						if ($("#activeOrInactive").val() == 'true') {
							//populateTemplates();
							//callTemplate();
							if ($("#enableJobTitle").val() == 'true') {
								$("#jobTitleId").attr("disabled", false);
								$("#jobOwner").attr("disabled", false);
								$("#companyAutoPopulation").attr("disabled", false);
								$("#dispCompNameId").attr("disabled", false);
								$("#bHideCompName1").attr("disabled", false);
								$("#applyToEMailId").attr("disabled", false);
								$("#applyToEMailTSId").attr("disabled", false);
								$("#applyToATSIPId").attr("disabled", false);
								$("#applyToURLId").attr("disabled", false);
								$("#applyToURLTSId").attr("disabled", false);
								$("#applyToATSId").attr("disabled", false);
								$("#reqSkillsId").attr("disabled", false);
								$("#jobDesc").attr("disabled", false);
								$("#trackPixel").attr("disabled", false);
								$("#templateId").attr("disabled", false);
								$("#autoRenew1").attr("disabled", false);
								$("#autoRenew2").attr("disabled", false);
								$("#templateOverride").attr("disabled", false);
								$("#employmentType").attr("disabled", false);
								$("#jobNumber").attr("disabled", false);
								$("#cityAutoPopulation").attr("disabled", false);
								$("#bHideCity1").attr("disabled", false);
								$("#stateDpId").attr("disabled", false);
								$("#bHideState1").attr("disabled", false);
								$("#zipCodeITId").attr("disabled", false);
								$("#bHideZipCode1").attr("disabled", false);
								$("#countryDpId").attr("disabled", false);
								$("#bHideCountry1").attr("disabled", false);
							}
							else{
								//populateTemplates();
								//callTemplate();
								$("#jobTitleId").attr("disabled", true);
								$("#jobOwner").attr("disabled", false);
								$("#companyAutoPopulation").attr("disabled", false);
								$("#dispCompNameId").attr("disabled", false);
								$("#bHideCompName1").attr("disabled", false);
								$("#applyToEMailId").attr("disabled", false);
								$("#applyToEMailTSId").attr("disabled", false);
								$("#applyToURLId").attr("disabled", false);
								$("#applyToURLTSId").attr("disabled", false);
								$("#applyToATSId").attr("disabled", false);
								$("#applyToATSIPId").attr("disabled", false);
								$("#reqSkillsId").attr("disabled", false);
								$("#jobDesc").attr("disabled", false);
								$("#trackPixel").attr("disabled", false);
								$("#templateId").attr("disabled", false);
								$("#autoRenew1").attr("disabled", false);
								$("#autoRenew2").attr("disabled", false);
								$("#templateOverride").attr("disabled", false);
								$("#employmentType").attr("disabled", false);
								
							}
							
							$("#jobDesc").attr("disabled", false);
							
							$("#saveJobPostId").attr("hidden", false);
							$('#saveJobPostId').attr("disabled", false);
							$('#cancel').attr("hidden",false);
							$('#postNewJobButHideId').attr("disabled", false);							

						}
						
						
						$("#applyToEMailTSId").click(function() {
							$('#applyToURLId').attr("checked", false);
							$('#applyToATSId').attr("checked", false);
							$('#applyToEMailTSId').removeAttr('readonly');
							$('#applyToURLTSId').val('');
							$('#applyToATSIPId').val('');
							$('#applyToATSIPId').attr('readonly', true);
							$('#applyToURLTSId').attr('readonly', true);
							$('#applyToEMailId').attr("checked","checked");
						});
						
						$("#applyToURLTSId").click(function() {
							$('#applyToEMailId').attr("checked", false);
							$('#applyToATSId').attr("checked", false);
							$('#applyToURLTSId').removeAttr('readonly');
							$('#applyToEMailTSId').attr('readonly', true);
							$('#applyToATSIPId').attr('readonly', true);
							$('#applyToEMailTSId').val('');
							$('#applyToATSIPId').val('');
							$('#applyToURLId').attr("checked","checked");
						});
						
						$("#applyToATSIPId").click(function() {
							$('#applyToEMailId').attr("checked", false);
							$('#applyToURLId').attr("checked", false);
							$('#applyToATSIPId').removeAttr('readonly');
							$('#applyToURLTSId').attr('readonly', true);
							$('#applyToEMailTSId').attr('readonly', true);
							$('#applyToURLTSId').val('');
							$('#applyToEMailTSId').val('');
							$('#applyToATSId').attr("checked","checked");
						});

				 		$("#applyToEMailId").click(function() {
							if ($("#applyToEMailId").val() == 'ApplyToEMail') {
								$('#applyToEMailTSId').removeAttr('readonly');
								$('#applyToATSIPId').attr('readonly', true);
								$('#applyToURLTSId').attr('readonly', true);
								$('#applyToURLTSId').val('');
								$('#applyToATSIPId').val('');
							}
						});
						$("#applyToURLId").click(function() {
							if ($("#applyToURLId").val() == 'ApplyToURL') {
								$('#applyToURLTSId').removeAttr('readonly');
								$('#applyToEMailTSId').attr('readonly', true);
								$('#applyToATSIPId').attr('readonly', true);
								$('#applyToEMailTSId').val('');
								$('#applyToATSIPId').val('');
							}
						});

						$("#applyToATSId").click(function() {
							if ($("#applyToATSId").val() == 'ApplyToATS') {
								$('#applyToATSIPId').removeAttr('readonly');
								$('#applyToURLTSId').attr('readonly', true);
								$('#applyToEMailTSId').attr('readonly', true);
								$('#applyToURLTSId').val('');
								$('#applyToEMailTSId').val('');
							}
						});
						
						$("#postTypeToolTip").hover(function() {
							$("#postTypeToolTip").attr("title",$("#postTypeId :selected").text());
						});
							
							
						jQuery(".megamenu").megamenu();
						$('#jobOwner').focus();
						
						
						if ($("#adminLogin").val() == 'true') {
							$('#postNewJobFormId').each(
									function() {
										$("#postNewJobFormId :input").attr(
												"disabled", false);
										$("#postTypeId").attr("disabled", true);
										$("#customerNo").attr("disabled", true);
									});
						 	
							
						}
					});
	
			function cancelProcess() {
				if ($("#jobId").val() > 0) {
					window.location.href = '${pageContext.request.contextPath}/employer/manageJobPost.html';
				}else{
				window.location.href = '${pageContext.request.contextPath}/employer/employerDashBoard.html';
				}
			}
</script>

<style type="text/css">
.ui-widget input {
	background-color: #F0F0F0;
	border-radius: 4px 4px 4px 4px;
	height: 25px;
	border: 1px solid #CCCCCC;
}

.ui-widget-header {
	background: #FE9400;
	border: 1px solid #AAAAAA;
	color: #222222;
	font-weight: bold;
}

.ui-widget-content {
	border: 1px solid #aaaaaa;
	background: #ffffff;
	color: #222222;
}

.ui-state-default,.ui-widget-content .ui-state-default {
	background: #FE9400;
	border: 1px solid #D3D3D3;
	color: #555555;
	font-weight: normal;
	outline: medium none;
}

.ui-button,.ui-button-text-only {
	
}
</style>
</head>

<body class="job_board">
	<form:form action="saveNewJob.html" commandName="jobPostForm"
		id="postNewJobFormId">
		<div class="ad_page_top">${adPageTop}</div>
		<div class="main_wrapper_outside">
			<div class="main_wrapper_inside">
				<form:hidden path="readOnly" />
				<form:hidden path="jobId" />
				<form:hidden path="activeOrInactive" />
				<form:hidden path="enableJobTitle" />
				<form:hidden path="jobStatus" />
				<form:hidden path="adminLogin" />
				<div class="main">
					<jsp:include page="../templates/templates_header.jsp"></jsp:include>
					<div class="clearfix"></div>
					<!--Start:MidContant-->
					<div class="MidContent_Wrapper floatLeft">
						<div class="popupHeader Padding0  OrangeBG">
						<c:if test="${jobPostForm.viewPage == 'false'}">
							<h2>POST NEW JOB</h2>
							</c:if>
							<c:if test="${jobPostForm.viewPage == 'true' }">
							<h2>VIEW JOB</h2>
							</c:if>
							<span class="floatRight marginRight10"><a
								href="<%=request.getContextPath()%>/employer/manageJobPost.html"
								class="link_color3_emphasized FontSize12 FontWeight">Back to
									Manage / Edit Job Postings</a></span>
						</div>

						<div class="clearfix"></div>
						<c:if test="${not empty errorMessage}">
							<div style="color: red;"left">${errorMessage}</div>
						</c:if>
						<!--*-->
						<div class="row">
							<div class="job_seeker_login leftFormHolderLMargin width100P"
								style="display: block">
								<div class="row">
									<span class="lableTextSelect marginTop13 ">Job Owner:</span>
									<form:select path="jobOwner" class="jb_input3 jb_input_width3">
										<form:option value="0" label="Select" />
										<form:options items="${jbOwnerList}" itemValue="optionId"
											itemLabel="optionName" />
									</form:select>
									<div class="toolTip DropDownAreaToolTip">
										<span class="classic">Choose the person who will be
											responsible for this job posting.</span>
									</div>
								</div>

								<div class="rowEvenNewSpacing">
									<span class="lableText3">Customer Number:</span>
									<form:input path="customerNo"
										class="job_seeker_password textBox350" readonly="true" />
									<div class="toolTip colorPkrAreaToolTip">
										<span class="classic">This is the customer number shown
											in your employer profile.</span>
									</div>
								</div>
								<div class="rowEvenNewSpacing">
									<span class="lableText3">Company Name:</span>
									<%-- <form:input path="companyName" class="job_seeker_password textBox350"  id="companyAutoPopulation"/> --%>
									<form:select path="facilityId"
										class="jb_input3 jb_input_width3" id="companyAutoPopulation"
										onchange="callTemplate()">
										<%-- <form:option value="0" label="None" /> --%>
										<form:options items="${companyList}" itemValue="optionId"
											itemLabel="optionName" />
									</form:select>
								</div>

								<div class="rowEvenNewSpacing">
									<span class="lableText3">Display Company Name:</span>
									<form:input path="disCompanyName"
										class="job_seeker_password textBox350" id="dispCompNameId"
										onKeyDown="limitText(this.form.dispCompNameId,this.form.countdownId2,60);"
										onKeyUp="limitText(this.form.dispCompNameId,this.form.countdownId2,60);" />
									<form:hidden path="" class="input2000_width"
										name="countdownId2" size="3" value="60" id="countdown"
										hidden="true" />
									<div class="toolTip colorPkrAreaToolTip">
										<span class="classic">If you want your company name to
											be displayed a certain way for this particular job posting,
											enter it here.</span>
									</div>
									<div class="clearfix"></div>
									<div class="rowEvenNewSpacing">
										<span class="lableText3">&nbsp;</span><span
											class="required marginRight5"> <form:checkbox
												path="bHideCompName" />
										</span> <span class="splLableText marginTop5">Hide the company
											name in this job posting.</span>

									</div>
								</div>
								<div class="clearfix"></div>
								<div class="paddingBottom05 MarginBottom10 marginTop10"></div>
								<div class="row marginTop10">
									<h3>Job Posting Details</h3>
								</div>
								<div class="row">
									<span class="lableTextSelect marginTop13 ">Job Posting
										Type:</span>
									<div id="postTypeToolTip" title="">
										<form:select path="jobPostingType"
											class="jb_input3 jb_input_width3" id="postTypeId"
											onchange="populateTemplates()">
											<form:option value="0" label="Select" />
											<form:options items="${jbPostingTypeList}"
												itemValue="optionId" itemLabel="optionName" />
										</form:select>
									</div>
									<span class="required colorPkrAreaToolTip">(Required)</span>
								</div>

								<div class="rowEvenNewSpacing">
									<span class="lableText3">Job Posting Inventory:</span> <span
										class="lableText3">
										<div class="floatLeft">
											<a
												href="<%=request.getContextPath()%>/inventory/employer/jobInventory.html?page=postJobPage"
												class="postingInventory" id="postingInventory">View Job
												Posting Inventory</a>
										</div>
									</span>
								</div>
								<div class="clearfix"></div>
								<div class="paddingBottom05 MarginBottom10 marginTop10"></div>
								<div class="row marginTop10">

									<h3>Job Title and Number</h3>
								</div>
								<div class="rowEvenNewSpacing">
									<span class="lableText3">Job ID:</span>
									<form:input path="jobNumber"
										class="job_seeker_password textBox350" />
									<div class="toolTip colorPkrAreaToolTip">
										<span class="classic">If you're posting multiple
											positions with the same job title, you can enter a 4 to 10
											digit number here to help you reference this specific job
											posting in the future.</span>
									</div>
								</div>
								<div class="rowEvenNewSpacing">
									<span class="lableText3">Job Title:</span>

									<form:input path="jobTitle"
										class="job_seeker_password textBox350" id="jobTitleId"
										onKeyDown="limitText(this.form.jobTitleId,this.form.countdownId1,60);"
										onKeyUp="limitText(this.form.jobTitleId,this.form.countdownId1,60);" />
									<form:hidden path="" class="input2000_width"
										name="countdownId1" size="3" value="60" id="countdown"
										hidden="true" />
									<span class="required">(Required)</span>
									<div class="toolTip colorPkrAreaToolTip">
										<span class="classic">Enter the name of the position
											you're trying to fill here.</span>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="paddingBottom05 MarginBottom10 marginTop10"></div>
								<div class="row marginTop10">
									<h3>Application Method</h3>
								</div>

								<div class="row marginLeft30 marginTop8 width450">
									<span class="floatLeft">
										<p>Choose your preferred method to receive applications.</p>
									</span><span class="required requiredTopmargin2">(Required)</span>
								</div>
								<div class="rowEvenNewSpacing">
									<span class="required width25p100"> <form:radiobutton
											path="applMethod" class="marginLeft30" value="ApplyToEMail"
											id="applyToEMailId" />
									</span><span class="splLableText" style="width:35px">Email:</span>
									<form:input path="applyEmail"
										class="job_seeker_password textBox350" id="applyToEMailTSId"
										readonly="readonly" />

									<span class="required requiredWidth">Enter the email
										address where you would like resumes to be sent.</span>
								</div>
								<div class="rowEvenNewSpacing">
									<span class="required width25p100"> <form:radiobutton
											path="applMethod" class="marginLeft30" value="ApplyToURL"
											id="applyToURLId" />
									</span><span class="splLableText" style="width:35px">URL:</span>
									<form:input path="applyUrl"
										class="job_seeker_password textBox350" id="applyToURLTSId"
										onblur="appendURL()" readonly="readonly" />
									<span class="required requiredWidth">Enter the URL where
										you would like to send job seekers to apply.</span>
								</div>

								<div class="rowEvenNewSpacing">
									<span class="required width25p100"> <form:radiobutton
											path="applMethod" class="marginLeft30" value="ApplyToATS"
											id="applyToATSId" />
									</span><span class="splLableText" style="width:35px">ATS :</span>
									<form:input path="atsUrl"
										class="job_seeker_password textBox350" id="applyToATSIPId"
										onblur="appendURLForATS()" readonly="readonly" />
									<span class="required requiredWidth">Enter the URL to
										the corresponding job posting or application on your company's
										website.</span>
								</div>
								<div class="clearfix"></div>
								<div class="paddingBottom05 MarginBottom10 marginTop10"></div>
								<div class="row marginTop10">

									<h3 class="floatLeft">Location</h3>
									<p class="required margin0 paddingTop0">All fields are required</p>	
									
								</div>
								<div class="rowEvenNewSpacing" id="divCityAutoPopulate">
									<span class="lableText3">Job City:</span>
									<form:input path="jobCity"
										class="job_seeker_password textBox350" id="cityAutoPopulation" />
									<div class="floatLeft width210">
										<span class="required marginRight10"> <form:checkbox
												path="bHideCity" />
										</span>
										<div class="Auto">
											<p>Hide the city from job seekers</p>
										</div>
									</div>
								</div>
								<div class="rowEvenNewSpacing">
									<span class="lableText3">Job State:</span>

									<form:select path="jobState" class="jb_input3 jb_input_width3"
										id="stateDpId">
										<form:option value="0" label="Select" />
										<form:options items="${stateList}" itemValue="stateValue"
											itemLabel="stateValue" />
									</form:select>

									<div class="floatLeft width210">
										<span class="required marginRight10"> <form:checkbox
												path="bHideState" />
										</span>
										<div class="Auto">
											<p>Hide the state from job seekers</p>
										</div>
									</div>

								</div>
								<div class="rowEvenNewSpacing">
									<span class="lableText3">Job ZIP Code:</span>
									<form:input path="jobZipCode"
										class="job_seeker_password textBox350" id="zipCodeITId"
										maxlength="5" />
									<div class="floatLeft width210">
										<span class="required marginRight10"> <form:checkbox
												path="bHideZipCode" />
										</span>
										<div class="Auto">
											<p>Hide the ZIP Code from job seekers</p>
										</div>
									</div>
								</div>
								<div class="rowEvenNewSpacing MarginBottom10">
									<span class="lableText3">Job Country:</span>
									<form:select path="jobCountry"
										class="jb_input3 jb_input_width3" id="countryDpId">
										<form:option value="0" label="Select" />
										<form:options items="${countryList}" itemValue="countryValue"
											itemLabel="countryValue" />
									</form:select>
									<div class="floatLeft width210">
										<span class="required marginRight10"> <form:checkbox
												path="bHideCountry" />
										</span>
										<div class="Auto">
											<p>Hide the country from job seekers</p>
										</div>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="paddingBottom05 MarginBottom10 marginTop10"></div>
								<div class="row marginTop10">
									<h3>Employment Type</h3>
								</div>
								<div class="rowEvenNewSpacing">
									<span class="lableText3">Employment Type:</span>
									<form:select path="employmentType"
										class="jb_input3 jb_input_width3">
										<form:option value="0" label="Select" />
										<form:options items="${empTypeList}" itemValue="optionId"
											itemLabel="optionName" />
									</form:select>
								</div>
								<div class="clearfix"></div>
								<div class="paddingBottom05 MarginBottom10 marginTop10"></div>
								<div class="row marginTop10">
									<h3>Job Details</h3>
								</div>
								<div class="rowEvenNewSpacing">
									<span class="lableText3">Required Skills:</span>

									<form:input path="reqSkills"
										class="job_seeker_password textBox350" id="reqSkillsId"
										onKeyDown="limitText(this.form.reqSkillsId,this.form.countdownId1,60);"
										onKeyUp="limitText(this.form.reqSkillsId,this.form.countdownId1,60);" />
									<form:hidden path="" class="input2000_width"
										name="countdownId1" size="3" value="60" id="countdown"
										hidden="true" />
									<div class="toolTip colorPkrAreaToolTip">
										<span class="classic">If this position requires
											specific skills, enter them here. Use brief keywords and
											phrases like "Triage" and "Emergency Care" to attract
											job seekers who are including skills in their search.</span>
									</div>
								</div>
								<div class="rowEvenNewSpacing">
									<span class="lableText3">Job Description :</span>
									<c:if test="${jobPostForm.viewPage == 'false'}">
										<div class="input_grp6">
											<form:textarea path="jobDesc" id="jobDesc"
												class="mceEditor textareaBoxCResume width450" rows="3"
												cols="45" />
										</div>
									</c:if>
									<c:if test="${jobPostForm.viewPage == 'true'}">
										<div class="input_grp6">
											<form:textarea path="jobDesc" id="jobDesc"
												class="textareaBoxCResume width450" rows="3" cols="45" />
										</div>
									</c:if>

									<span class="required">(Required)</span>
									<div class="toolTip01 colorPkrAreaToolTip">
										<span class="classic">Enter all of the pertinent
											information regarding this position here. You can include
											anything from job responsibilities to education requirements
											to information about your facility or health system.</span>
									</div>
								</div>

								<div class="rowEvenNewSpacing MarginBottom10">
									<span class="lableText3">Tracking Pixel:</span>
									<form:input path="trackPixel" id="trackPixel"
										class="job_seeker_password textBox350" />
									<div class="toolTip colorPkrAreaToolTip">
										<span class="classic">If you want to track the response
											to this job posting independently, you can enter your
											tracking pixel HTML code here.</span>
									</div>
								</div>
								<div class="row">
									<span class="lableText3"></span>
									<div class="FormErrorDisplayText">
										<span id="errTrackPixcel"></span>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="paddingBottom05 MarginBottom10 marginTop10"></div>
								<div class="row marginTop10">
									<h3>Job Posting Branding Template</h3>

								</div>
								<div class="rowEvenNewSpacing MarginBottom10">
									<span class="lableText3 ">Branding Template:</span>
									<form:select path="brandTemplate"
										class="jb_input3 jb_input_width3" id="templateId">
										<form:option value="0" label="None" />
										<form:options items="${templateList}" itemValue="optionId"
											itemLabel="optionName" />
									</form:select>
									<div class="toolTip colorPkrAreaToolTip">
										<span class="classic">Select one of these templates to
											give your job posting a branded look. New branding templates
											can be created by clicking on the related link when you
											return to your dashboard.</span>
									</div>
									<c:if test="${displayOverride == true}">
										<div class="floatLeft width210">
											<span class="required marginRight10"> <form:checkbox
													path="bTemplateOverride" onchange="populateTemplates()"
													id="templateOverride" />
											</span>
											<div class="Auto">
												<p>Override Package Template</p>
											</div>
										</div>
									</c:if>
								</div>

								<div class="clearfix"></div>
								<div class="paddingBottom05 MarginBottom10 marginTop10"></div>
								<div class="row marginTop10">
									<h3>Auto Renew</h3>
								</div>

								<div class="rowEvenNewSpacing MarginBottom10">
									<span class="lableText3">Auto Renew:</span>
									<div class="required">
										<form:radiobutton path="autoRenew" value="Yes" />
										<label class="greyLabel17">Yes</label>
									</div>
									<div class="required">
										<form:radiobutton path="autoRenew" value="No" />
										<label class="greyLabel17">No</label>
									</div>
									<span class="required"> <%-- <form:radiobutton path="autoRenew" value="No"/><label class="greyLabel">No</label> --%>
									</span>
									<div class="toolTip colorPkrAreaToolTip">
										<span class="classic">Select 'Yes' if you would like
											this job posting to be automatically renewed when it reaches
											its expiration date.</span>
									</div>
								</div>
								<form:hidden path="scheduleStartDate"
									class="job_seeker_password datepicker" id="startDateHdId"
									readonly="true" />
								<form:hidden path="scheduleEndDate" class="job_seeker_password"
									id="endDateHdId" readonly="true" />
								<div id="scheduleStartDivId" title="Schedule the post new job">

									<div class="rowEvenNewSpacing">
										<span class="lableText3">Schedule Date:</span>
										<form:input path="" class="job_seeker_password datepicker"
											id="startDate" readonly="true" />
									</div>
									<div class="rowEvenNewSpacing">
										<span class="lableText3">Expiry Date:</span>
										<form:input path="" class="job_seeker_password" id="endDate"
											readonly="true" />
									</div>
									<div class="rowEvenNewSpacing">
										<div id="errMsgDialog" class="validationMsg"></div>
									</div>
								</div>

								<div class="clearfix"></div>
								<div class="paddingBottom05 MarginBottom10 marginTop10"></div>
								<div class="clearfix"></div>
								<div class="clearfix"></div>
							</div>
						</div>
						<!--*-->
						<div class="clearfix"></div>
					</div>
					<!---->

					<div class="clearfix"></div>
					<div class="searchResultsListing"></div>

					<!--Test-->
					<div class="clearfix"></div>
					<div class="row marginTopBottom20">
						<input type="button" value="Post new job"
							class="btn_sm white cursor" id="postNewJobButId" /> <input
							type="button" value="Schedule job" class="btn_sm white cursor"
							id="scheduleNewJobButId"> <input type="button"
							value="Save as draft" class="btn_sm white cursor"
							name="SaveAsDraft" id="saveAsDraftJobButId">
						<!-- <input type="submit" value="Cancel" class="btn_sm white" name="Cancel" id="cancel"> -->

						<c:if test="${jobPostForm.activeOrInactive == true}">
							<input type="button" value="Save" class="btn_sm white cursor"
								id="saveJobPostId" />
						</c:if>
						<input type="button" value="Cancel" class="btn_sm white cursor"
							name="Cancel" id="cancel" onclick="cancelProcess()">
						<%-- <a href="<%=request.getContextPath()%>/employer/employerDashBoard.html" id="cancel" class="btn_sm white">Cancel</a> --%>
						<input type="submit" value="Post new job"
							class="btn_sm white cursor" name="PostNewJob"
							id="postNewJobButHideId" style="visibility: hidden;" /> <input
							type="submit" value="Schedule job" class="btn_sm white cursor"
							name="ScheduleJob" id="scheduleJobButHideId"
							style="visibility: hidden;" /> <input type="submit"
							value="Save as draft" class="btn_sm white cursor"
							name="SaveAsDraft" id="savePostJobButHideId"
							style="visibility: hidden;" />
						<!-- 	              	<a href="#" class="btn_sm white">Post new job</a> 
	              	<a href="#" class="btn_sm white">Schedule job</a> 
	              	<a href="#" class="btn_sm white">save as draft</a> 
	              	<a href="#" class="btn_sm white">Cancel</a> -->
					</div>
					<div class="clearfix"></div>
					<div class="ad_wrapper">
						<span class="input_grp5 "> </span> ${adPageBottom}
					</div>
					<!-- ad_wrapper -->
				</div>


				<!--Start:MidContant-->
				<div class="clearfix"></div>
				<!-- content_wrapper -->


			</div>
			<!-- main -->

		</div>

		<!-- end main_wrapper_inside -->
		</div>
		<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
		<!-- end main_wrapper_outside -->
	</form:form>
</body>
</html>