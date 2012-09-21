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
<link rel="stylesheet" type="text/css" media="screen" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css">

		<!-- JAVASCRIPT FILES -->
		<script type="text/javascript" src="../resources/js/jquery.cycle.all.min.js"></script>
		<script type="text/javascript" src="../resources/js/slider.js"></script>
		<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.js"></script>
		
		<script type="text/javascript">
		
		
			//Limit text area characters
			function limitText(limitField, limitCount, limitNum) {
			/* 		alert(limitField.value.length+""+limitCount.value+""+limitNum); */
				if (limitField.value.length > limitNum) {
					limitField.value = limitField.value.substring(0, limitNum);
				} else {
					limitCount.value = limitNum - limitField.value.length;
				}
			}			
			
		    jQuery(document).ready(function(){
		    	
		     $(".postingInventory").displaypopup(".postingInventory",
						"790", "360"); 
		    	
			$( "#scheduleStartDivId" ).hide();		    
		    $("#postNewJobButId").click(function(){
		    	if(confirm("Do you want to post this job?")){
		    		$("#postNewJobButHideId").click();
		    	}
		    });
		    
		    $("#saveAsDraftJobButId").click(function(){
		    	if(confirm("Do you want to save this job as Draft?")){
		    		$("#savePostJobButHideId").click();
		    	}
		    });		    
		    
			//Date picker
	    	$(function() {
	    		$( ".datepicker" ).datepicker({
	    			 minDate: 0,
	    			onSelect: function(dateText, inst) { 
	    				 var datepicker = $("#startDate").val();
	    		            var joindate = new Date(datepicker);
	    		            var numberOfDaysToAdd = 30;
	    		            joindate.setDate(joindate.getDate() + numberOfDaysToAdd);
	    		            var dd = joindate.getDate();
    		            	if(dd<10)
    		            		dd='0'+dd;    		            	
	    		            var mm = joindate.getMonth() + 1;
    		            	if(mm<10)
    		            		mm='0'+mm;  
	    		            var y = joindate.getFullYear();
	    		            var joinFormattedDate = mm + '/' + dd + '/' + y;
	    		            $("#endDate").val(joinFormattedDate);
	    		            $("#startDateHdId").val(datepicker);
	    		            $("#endDateHdId").val(joinFormattedDate);
	    		      }
	    		});
	    	}); 	
		    
			//Popup on click of schedule button
  		   $("#scheduleNewJobButId").click(function() {
				$( "#scheduleStartDivId" ).dialog({
					resizable: false,
					height:400,
					width:500,
					zIndex: -1,
					modal: true,
					data:$('#postNewJobFormId').serialize(),
					backgroundColor:'#F0F0F0',
					buttons: {
						"Schedule": function() {
							if($("#startDate").val() != ''){
								$( this ).dialog( "close" );
								$("#scheduleJobButHideId").click();
							}
						},
						"Cancel": function() {
							$( this ).dialog( "close" );
						}
					},
					modal: true
				});
				$("#startDate").val( $("#startDateHdId").val());
				$("#endDate").val($("#endDateHdId").val());
			});  
		  
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
							$('#zipCodeITId').val(data);
						},
						});						
 						$.ajax({
							url: '${pageContext.request.contextPath}/employer/getCountry.html?city='+$("#cityAutoPopulation").val()+'&state='+$("#stateDpId").val()+'&postalCode='+$("#zipCodeITId").val(),
							success : function(country) {
								$('#countryDpId').val(country);
							},
						}); 						
					},
					});
				},
			}); 

			//Auto complete on selecting zipcode			
			$("#zipCodeITId").autocomplete({
				source: '${pageContext.request.contextPath}/employer/getPostalCodeAutoPopulation.html',
				select: function(event, ui) {
					$("#zipCodeITId").val(ui.item.value);	
					$('#cityAutoPopulation').val("");
					$('#stateDpId').val("");
					$.ajax({
						url: '${pageContext.request.contextPath}/employer/getLocations.html?zipCode='+$("#zipCodeITId").val(),
						success : function(data) {
							$('#stateDpId').val(data.state);
							$('#countryDpId').val(data.country);
							$("#cityAutoPopulation").val(data.city);
						},
					});		
				}
			});			
			if ($("#readOnly").val()=='true') {
				$('#postNewJobFormId')
						.each(
								function() {
									$(
											"#postNewJobFormId :input")
											.attr(
													"disabled",
													true);
									$(
									"#postNewJobFormId :button")
									.attr(
											"hidden",
											true);
									$(
									"#cancel")
									.attr(
											"hidden",
											true);
								});

			}
			
			$("#applyToEMailId").click(function(){
				if( $("#applyToEMailId").val()=='ApplyToEMail'){
					$('#applyToEMailTSId').removeAttr('readonly');
					$('#applyToATSIPId').attr('readonly', true);
					$('#applyToURLTSId').attr('readonly', true);	
					$('#applyToURLTSId').val('');
					$('#applyToATSIPId').val('');
				}
			});
			
			$("#applyToURLId").click(function(){
				if($("#applyToURLId").val()=='ApplyToURL'){
					$('#applyToURLTSId').removeAttr('readonly');
					$('#applyToEMailTSId').attr('readonly', true);
					$('#applyToATSIPId').attr('readonly', true);
					$('#applyToEMailTSId').val('');
					$('#applyToATSIPId').val('');
				}
			});
			
			$("#applyToATSId").click(function(){
				if($("#applyToATSId").val()=='ApplyToATS'){
					$('#applyToATSIPId').removeAttr('readonly');
					$('#applyToURLTSId').attr('readonly', true);
					$('#applyToEMailTSId').attr('readonly', true);
					$('#applyToURLTSId').val('');
					$('#applyToEMailTSId').val('');
				}
			});

		    jQuery(".megamenu").megamenu();
		});
		</script>

		<style type="text/css">
		 .ui-widget input  { background-color: #F0F0F0; border-radius: 4px 4px 4px 4px; height: 25px; border: 1px solid #CCCCCC;}  
    	.ui-widget-header { background:#FE9400; border: 1px solid #AAAAAA; color: #222222;font-weight: bold;}
    	.ui-state-default, .ui-widget-content .ui-state-default { background: #FE9400; border: 1px solid #D3D3D3; color: #555555; font-weight: normal; outline: medium none;}
    	.ui-button, .ui-button-text-only{
    	
    	}
    	
    	

		</style>
		</head>

<body class="job_board">
<form:form action="saveNewJob.html" commandName="jobPostForm" id="postNewJobFormId">
<div class="ad_page_top"> <img src="../resources/images/ads/banner_ad_fpo.png" /> </div>
<div class="main_wrapper_outside">
          <div class="main_wrapper_inside">
<form:hidden path="readOnly"/>
<form:hidden path="jobId"/>
    <div class="main">
      <jsp:include page="../templates/templates_header.jsp"></jsp:include>
              <div class="clearfix"></div>
              <!--Start:MidContant-->
              <div class="MidContent_Wrapper floatLeft">
        <div class="popupHeader Padding0  OrangeBG">
                  <h2>POST NEW JOB</h2>
                  <span class="floatRight marginRight10"><a href="<%=request.getContextPath()%>/employer/manageJobPost.html" class="link_color3_emphasized FontSize12 FontWeight">Back to Manage / Edit Job Postings</a></span></div>

        <div class="clearfix"></div>
      <c:if test="${not empty errorMessage}">
  			<div style="color: red; align="left">
				${errorMessage}
			</div>      
      </c:if>
        <!--*-->
        <div class="row">
                  <div class="job_seeker_login leftFormHolderLMargin width100P" style="display:block">
                      <div class="row"> <span class="lableTextSelect marginTop13 ">Job Owner:</span>
 				<form:select path="jobOwner" class="jb_input3 jb_input_width3">
					<form:option value="0" label="Select" />
					<form:options items="${jbOwnerList}" itemValue="optionId" itemLabel="optionName" />
				</form:select>
                        <div class="toolTip DropDownAreaToolTip"><span class="classic">Choose the person who will be responsible for this job posting.</span></div>
              </div>

              <div class="rowEvenNewSpacing"> <span class="lableText3">Customer Number:</span>
			    <form:input path="customerNo" class="job_seeker_password textBox350"  readonly="true"/>
                <div class="toolTip colorPkrAreaToolTip"><span class="classic">This is the customer number shown in your employer profile.</span></div>
              </div>
                      <div class="rowEvenNewSpacing"> <span class="lableText3">Company Name:</span>
                <form:input path="companyName" class="job_seeker_password textBox350"  readonly="true"/>
              </div>

               <div class="rowEvenNewSpacing"> <span class="lableText3">Display Company Name:</span>
                <form:input path="disCompanyName" class="job_seeker_password textBox350"  id="dispCompNameId"  
                								onKeyDown="limitText(this.form.dispCompNameId,this.form.countdownId2,60);"
												onKeyUp="limitText(this.form.dispCompNameId,this.form.countdownId2,60);"/>
				<form:hidden path="" class="input2000_width" name="countdownId2" size="3" value="60" id="countdown"   hidden="true"/>									
                <div class="toolTip colorPkrAreaToolTip"><span class="classic">If you want your company name to be displayed a certain way for this particular job posting, enter it here.</span></div>
                <div class="clearfix"></div>
                <div class="rowEvenNewSpacing"><span class="lableText3">&nbsp;</span><span class="required marginRight5">
                  <form:checkbox path="bHideCompName"/>
                  </span>
                          <span class="splLableText marginTop5">Hide the company name in this job posting.</span>

                        </div>
              </div>
                      <div class="clearfix"></div>
                      <div class="paddingBottom05 MarginBottom10 marginTop10"></div>
                      <div class="row marginTop10">
                <h3>Job Posting Details</h3>
              </div>
                      <div class="row"> <span class="lableTextSelect marginTop13 ">Job Posting Type:</span>

				<form:select path="jobPostingType" class="jb_input3 jb_input_width3">
					<form:option value="0" label="Select" />
					<form:options items="${jbPostingTypeList}" itemValue="optionId" itemLabel="optionName" />
				</form:select>
				<span class="required colorPkrAreaToolTip">(Required)</span>
              </div>

              <div class="rowEvenNewSpacing"> <span class="lableText3">Job Posting Inventory:</span>              
              		<span class="FloatLeft marginTop6">
              			<a href="<%=request.getContextPath()%>/inventory/employer/jobInventory.html?page=postJobPage" class="postingInventory" id="postingInventory" >View Job Posting Inventory</a>
              		</span>            
             </div>
              <div class="clearfix"></div>
              <div class="paddingBottom05 MarginBottom10 marginTop10"></div>
              <div class="row marginTop10">

                <h3>Job Title and Number</h3>
              </div>
                      <div class="rowEvenNewSpacing"> <span class="lableText3">Job ID:</span>
                <form:input path="jobNumber" class="job_seeker_password textBox350" />
                <div class="toolTip colorPkrAreaToolTip"><span class="classic">If you're posting multiple positions with the same job title, you can enter a 4 to 10 digit number here to help you reference this specific job posting in the future.</span></div>
              </div>
                      <div class="rowEvenNewSpacing"> <span class="lableText3">Job Title:</span>

                <form:input path="jobTitle" class="job_seeker_password textBox350" id="jobTitleId"  
                								onKeyDown="limitText(this.form.jobTitleId,this.form.countdownId1,60);"
												onKeyUp="limitText(this.form.jobTitleId,this.form.countdownId1,60);"/>
				<form:hidden path="" class="input2000_width" name="countdownId1" size="3" value="60" id="countdown"   hidden="true"/>																		
      			<span class="required">(Required)</span><div class="toolTip colorPkrAreaToolTip"><span class="classic">Enter the name of the position you're trying to fill here.</span></div>  </div>
                      <div class="clearfix"></div>
                      <div class="paddingBottom05 MarginBottom10 marginTop10"></div>
                      <div class="row marginTop10">
                <h3>Application Method</h3>
              </div>

                      <div class="row marginLeft30 marginTop8 width450">
               		<span class="floatLeft"> <p >Choose your preferred method to receive application. </p></span><span class="required requiredTopmargin2">(Required)</span>
              </div>
                      <div class="rowEvenNewSpacing"><span class="required">
                      <form:radiobutton path="applMethod" class="marginLeft30" value="ApplyToEMail" id="applyToEMailId"/>
                        </span><span class="lableText6">Apply-to Email:</span>
                <form:input path="applyEmail" class="job_seeker_password textBox350"  id="applyToEMailTSId" readonly="readonly"/>

                <span class="required requiredWidth">Enter the email address where you would like resumes to be sent.</span> </div>
                      <div class="rowEvenNewSpacing"><span class="required">
                        <form:radiobutton path="applMethod" class="marginLeft30" value="ApplyToURL" id="applyToURLId"/>
                        </span><span class="lableText6">Apply-to URL</span>
                <form:input path="applyUrl" class="job_seeker_password textBox350"  id="applyToURLTSId" readonly="readonly"/>
                <span class="required requiredWidth">Enter the URL where you would like to send job-seekers to apply.</span> </div>

                      <div class="rowEvenNewSpacing"><span class="required">
                        <form:radiobutton path="applMethod" class="marginLeft30" value="ApplyToATS" id="applyToATSId"/>
                        </span><span  class="lableText6">Apply-to ATS</span>
               <form:input path="atsUrl" class="job_seeker_password textBox350" id="applyToATSIPId" readonly="readonly"/>
				<span class="required requiredWidth">Enter the URL to the corresponding job posting or application on your company's website.</span></div>
                      <div class="clearfix"></div>
                      <div class="paddingBottom05 MarginBottom10 marginTop10"></div>
                 <div class="row marginTop10">

                <h3>Location</h3>
                <p class="required">(All fields are required)</p>
              </div>
                      <div class="rowEvenNewSpacing" id="divCityAutoPopulate"> <span class="lableText3">Job City:</span>               
                <form:input path="jobCity" class="job_seeker_password textBox350"  id="cityAutoPopulation"/>
                <div class="floatLeft width210"><span class="required marginRight10">
                  <form:checkbox path="bHideCity"/>
                  </span>
                          <div class="Auto">
                    <p>Hide the city from job-seekers</p>
                  </div>
                        </div>
              </div>
                      <div class="rowEvenNewSpacing"> <span class="lableText3">Job State:</span>

				<form:select path="jobState" class="jb_input3 jb_input_width3" id="stateDpId">
					<form:option value="0" label="Select" />
					<form:options items="${stateList}" itemValue="stateValue"
						itemLabel="stateValue" />
				</form:select>    
				            
				<div class="floatLeft width210"><span class="required marginRight10">
                  <form:checkbox path="bHideState"/>
                  </span>
                          <div class="Auto">
                    <p>Hide the state from job-seekers</p>
                  </div>
                        </div>

              </div>
                <div class="rowEvenNewSpacing"> <span class="lableText3">Job Zip Code:</span>
                <form:input path="jobZipCode" class="job_seeker_password textBox350"  id="zipCodeITId"/>
                <div class="floatLeft width210"><span class="required marginRight10">
                  <form:checkbox path="bHideZipCode"/>
                  </span>
                  <div class="Auto">
                    <p>Hide the zip code from job-seekers</p>
                  </div>
                </div>
              </div>
                <div class="rowEvenNewSpacing MarginBottom10"> <span class="lableText3">Job Country:</span>
				<form:select path="jobCountry" class="jb_input3 jb_input_width3" id="countryDpId">
					<form:option value="0" label="Select" />
					<form:options items="${countryList}" itemValue="countryValue"
						itemLabel="countryValue" />
				</form:select>
                <div class="floatLeft width210"><span class="required marginRight10">
                  <form:checkbox path="bHideCountry"/>
                  </span>
                  <div class="Auto">
                    <p>Hide the country from job-seekers</p>
                  </div>
                        </div>
              </div>
              <div class="clearfix"></div>
                <div class="paddingBottom05 MarginBottom10 marginTop10"></div>
                <div class="row marginTop10">
                <h3>Employment Type</h3>
              </div>
              <div class="rowEvenNewSpacing"> <span class="lableText3">Employment Type:</span>
				<form:select path="employmentType" class="jb_input3 jb_input_width3">
					<form:option value="0" label="Select" />
					<form:options items="${empTypeList}" itemValue="optionId" itemLabel="optionName" />
				</form:select>
              </div>
               <div class="clearfix"></div>
               <div class="paddingBottom05 MarginBottom10 marginTop10"></div>
               <div class="row marginTop10">
                <h3>Job Details</h3>
              </div>
              <div class="rowEvenNewSpacing"> <span class="lableText3">Required Skills:</span>

                <form:input path="reqSkills" class="job_seeker_password textBox350" id="reqSkillsId" onKeyDown="limitText(this.form.reqSkillsId,this.form.countdownId1,60);"
												onKeyUp="limitText(this.form.reqSkillsId,this.form.countdownId1,60);"/>
				<form:hidden path="" class="input2000_width" name="countdownId1" size="3" value="60" id="countdown"   hidden="true"/>											
                <div class="toolTip colorPkrAreaToolTip"><span class="classic">If this position requires specific skills, enter them here. Use brief keywords and phrases like "Triage" and "Emergency Care" to attract job-seekers who are including skills in their search.</span></div>
              </div>
                      <div class="rowEvenNewSpacing"> <span class="lableText3">Job Description</span>
                <div class="input_grp6">
                		<form:textarea path="jobDesc" class="textareaBoxCResume width450" rows="3" cols="45"/>
                        </div>
                <span class="required">(Required)</span><div class="toolTip01 colorPkrAreaToolTip"><span class="classic">Enter all of the pertinent information regarding this position here. You can include anything from job responsibilities to education requirements to information about your facility or health system.</span></div></div>

                      <div class="rowEvenNewSpacing MarginBottom10"> <span class="lableText3">Tracking Pixel:</span>
                <form:input path="trackPixel" class="job_seeker_password textBox350" />
                <div class="toolTip colorPkrAreaToolTip"><span class="classic">If you want to track the response to this job posting independently, you can enter your tracking pixel HTML code here.</span></div>
              </div>
                      <div class="clearfix"></div>
                      <div class="paddingBottom05 MarginBottom10 marginTop10"></div>
                      <div class="row marginTop10">
                <h3>Job Posting Branding Template</h3>

              </div>
                      <div class="rowEvenNewSpacing MarginBottom10"><span class="lableText3 ">Branding Template:</span>
						<form:select path="brandTemplate" class="jb_input3 jb_input_width3">
							<form:option value="0" label="Select" />
							<form:options items="${templateList}" itemValue="optionId" itemLabel="optionName" />
						</form:select>
                        <div class="toolTip colorPkrAreaToolTip"><span class="classic">Select one of these templates to give your job posting a branded look. New branding templates can be created by clicking on the related link when you return to your dashboard.</span></div>
              </div>
              <div class="clearfix"></div>
                      <div class="paddingBottom05 MarginBottom10 marginTop10"></div>
                      <div class="row marginTop10">
                <h3>Auto Renew</h3>
              </div>

                      <div class="rowEvenNewSpacing MarginBottom10">
                      <span class="lableText3">Auto Renew:</span>
                      <span>
                      <div class="required"><form:radiobutton path="autoRenew" value="Yes"/><label class="greyLabel">Yes</label></div>
                      <div class="required"><form:radiobutton path="autoRenew" value="No"/><label class="greyLabel">No</label></div>
                      </span>
                      <span class="required"> 
                      <%-- <form:radiobutton path="autoRenew" value="No"/><label class="greyLabel">No</label> --%>
                      </span> 
                      <div class="toolTip colorPkrAreaToolTip">
                      <span class="classic">Select 'Yes' if you would like this job posting to be automatically renewed when it reaches its expiration date.</span></div>                      
			   </div>
			   <form:hidden path="scheduleStartDate" class="job_seeker_password datepicker" id="startDateHdId" readonly="true"/>
			   <form:hidden path="scheduleEndDate" class="job_seeker_password" id="endDateHdId" readonly="true"/>
			   <div id="scheduleStartDivId" title="Schedule the post new job"  > 
			   
              	  	<div class="rowEvenNewSpacing"> <span class="lableText3">Schedule Date:</span>               
               			<form:input path="" class="job_seeker_password datepicker" id="startDate" readonly="true"/>
               		</div>
               		<div class="rowEvenNewSpacing"> <span class="lableText3">Expiry Date:</span>               
               			<form:input path="" class="job_seeker_password" id="endDate" readonly="true"/>
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
              <div class="searchResultsListing"> </div>
              
              <!--Test-->
              <div class="clearfix"></div>
              <br />
	              <span class="marginBottom50 FloatLeft" >
	              <input type="button" value="Post new job" class="btn_sm white"  id="postNewJobButId"/>
	              <input type="button" value="Schedule job" class="btn_sm white"  id="scheduleNewJobButId">
	              <input type="button" value="Save as draft" class="btn_sm white" name="SaveAsDraft" id="saveAsDraftJobButId">
	              <input type="submit" value="Cancel" class="btn_sm white" name="Cancel" id="cancel" >
	             
	              <input type="submit" value="Post new job" class="btn_sm white" name="PostNewJob" id="postNewJobButHideId" style="visibility: hidden;"/>
	              <input type="submit" value="Schedule job" class="btn_sm white" name="ScheduleJob" id="scheduleJobButHideId" style="visibility: hidden;"/>
	              <input type="submit" value="Save as draft" class="btn_sm white" name="SaveAsDraft" id="savePostJobButHideId" style="visibility: hidden;"/>
<!-- 	              	<a href="#" class="btn_sm white">Post new job</a> 
	              	<a href="#" class="btn_sm white">Schedule job</a> 
	              	<a href="#" class="btn_sm white">save as draft</a> 
	              	<a href="#" class="btn_sm white">Cancel</a> -->
	              </span> 	              
             </div>

    
    <!--Start:MidContant-->
    <div class="clearfix"></div>
    <!-- content_wrapper -->
    <div class="ad_wrapper"> <span class="input_grp5 "> </span><img src="../resources/images/ads/banner_ad_fpo.png" /> </div>
    <!-- ad_wrapper --> 
    
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