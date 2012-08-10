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
		<link href="../resources/css/jquery.megamenu.css" rel="stylesheet" type="text/css" />
		<link href="../resources/css/SliderStyles.css" rel="stylesheet" type="text/css">

		<!--[if IE]>
	<link href="../resources/css/ie.css" rel="stylesheet" type="text/css">
<![endif]-->

		<!-- JAVASCRIPT FILES -->
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
		<script type="text/javascript" src="../resources/css/jquery.cycle.all.min.js"></script>
		<script type="text/javascript" src="../resources/css/slider.js"></script>
		<script type="text/javascript" src="../resources/css/jquery.megamenu.js"></script>

		<script type="text/javascript">
		    jQuery(document).ready(function(){
		    	
/* 		 		//$('#certAjaxCallIdButton').click(function(){		
		 			 $('#certAjaxCallIdButton').live('click', function() {
		 			
					$.ajax({url:"/jobboard/jobSeekerResume/addCertifications.html",
						//data:$('#passwordChange').serialize(),
						type:"POST",
						success: function(data) {
							if(data == ''){
								alert("once again");
								$('#listOfCertsId').jqprint();
								  //$('#listOfCertsId').append($('addCertDivId'));
							}else{
								alert("error");
							}
						 },
					});
				}); 
		    }); */
		    jQuery(".megamenu").megamenu();		    
		</script>
		<script type="text/javascript" src="../resources/css/expandCollapse.js"></script>
		</head>

<body class="job_board">
<form:form method="Post" action="saveResumeBuilder.html" commandName="createResume" id="createResumeBuilderId">
<div class="ad_page_top"> <img src="../resources/images/ads/banner_ad_fpo.png" /> </div>
<div class="main_wrapper_outside">
          <div class="main_wrapper_inside">

    <div class="main">
              <div class="header_wrapper"> <a href="">
                <div class="logo"></div>
                </a>
        <div class="headerLoginSection"> 
                  
                  <!-- loginHeader --> 
                  
                  <!-- loginHeader -->
                  <div class="headerLoginSectionColumns"> <span class="boldText">Welcome, (Job Seeker Name)!</span><br>

            <div class="floatRight"> <span class="floatLeft"> <a href="">Log Out</a> | <a href="">My Dashboard</a></span></div>
          </div>
                  <!-- loginHeader --> 
                </div>
        <!-- loginHeader --> 
        <!-- loginHeader --> 
        
      </div>

              <!-- header_wrapper -->
              
              <div id="nav">
        <ul class="megamenu">
                  <li> <a href="javascript:">Magazines</a>
            <div class="megamenuContainer">
                      <div class="column"> <a href="http://nursing.advanceweb.com/">
                        <p>Nurses</p>

                        </a> <a href="http://physical-therapy.advanceweb.com/">
                        <p>Physical Therapy and Rehab Medicine</p>
                        </a> <a href="http://occupational-therapy.advanceweb.com/">
                        <p>Occupational Therapy Practitioners</p>
                        </a> <a href="http://imaging-radiation-oncology.advanceweb.com/">
                        <p>Imaging & Radiattion Oncology</p>

                        </a> <a href="http://audiology.advanceweb.com/">
                        <p>Hearing Practice Management</p>
                        </a> </div>
                      <div class="column"> <a href="http://speech-language-pathology-audiology.advanceweb.com/">
                        <p>Speech-Language Pathologists & Audiologists</p>
                        </a> <a href="http://respiratory-care-sleep-medicine.advanceweb.com/">

                        <p>Respiratory Care and Sleep Medicine</p>
                        </a> <a href="http://laboratory-manager.advanceweb.com/">
                        <p>Administrators of the Laboratory</p>
                        </a> <a href="http://laboratorian.advanceweb.com/">
                        <p>Medical Laboratory Professionals</p>
                        </a> <a href="http://health-information.advanceweb.com/">

                        <p>Health Information Professionals</p>
                        </a> </div>
                      <div class="column"> <a href="http://long-term-care.advanceweb.com/">
                        <p>Long-Term Care Management</p>
                        </a> <a href="http://nurse-practitioners-and-physician-assistants.advanceweb.com/">
                        <p>NPs & PAs</p>

                        </a> <a href="http://healthcare-executive-insight.advanceweb.com/">
                        <p>Executive Insight</p>
                        </a> </div>
                    </div>
          </li>
                  <li> <a href="javascript:">Job Search</a>

            <div class="megamenuContainer">
                      <div class="column"> <a href="http://health-care-jobs.advanceweb.com/">
                        <p>Quick Search</p>
                        </a> <a href="http://health-care-jobs.advanceweb.com/ResumeBuilder/Default.aspx">
                        <p>Resume Builder</p>
                        </a> <a href="http://health-care-jobs.advanceweb.com/Salary/Default.aspx">

                        <p>Salary Calculator</p>
                        </a> <a href="http://health-care-jobs.advanceweb.com/AdvanceMessenger/Default.aspx">
                        <p><i>ADVANCE</i> Messenger</p>
                        </a> <a href="http://health-care-jobs.advanceweb.com/careers/article.aspx?cc=251059">
                        <p>Career Resource Center</p>

                        </a> <a href="http://health-care-jobs.advanceweb.com/FeaturedFacilities/Default.aspx">
                        <p>Featured Facilities</p>
                        </a> <a href="http://health-care-jobs.advanceweb.com/Default.aspx">
                        <p>Home</p>
                        </a> </div>
                    </div>

          </li>
                  <li> <a href="javascript:">Education</a> </li>
                  <li> <a href="javascript:">Events</a> </li>
                  <li> <a href="javascript:">Community</a> </li>

                  <li> <a href="javascript:">Healthcare Shop</a> </li>
                  <li> <a href="javascript:">Custom Promotions</a> </li>
                </ul>
      </div>
              <!--nav-->
              <div class="clearfix"></div>

              <!--Start:MidContant-->
              <div class="MidContent_Wrapper floatLeft">
        <div class="popupHeader Padding0  OrangeBG">
                  <h2>CREATE YOUR RESUME</h2>
                  <span class="floatRight marginRight10"><a href="/jobboard/jobSeeker/jobSeekerDashBoard.html" class="link_color3_emphasized FontSize12 FontWeight">Back to Dashboard</a></span></div>
        <div class="clearfix"></div>
        <div class="MidContent_Wrapper FloatLeft"><span>
          <h1 class="marginTop3 marginLeft10 width305 FloatLeft FontSize18 TextColor03">Resume Name:</h1>

          <div class=" floatRight width255"><span class="FloatLeft"><img src="../resources/images/percimg.png" width="149" height="16" alt="img"></span>
            <h3 class="floatRight">60% Complete</h3>
          </div>
          </span> </div>
        <div class="clearfix"></div>
      </div>
              <!---->
              <div class="clearfix"></div>

              <div class="searchResultsListing"> </div>
              
              <!--Test-->
              <div class="searchResultsListing">
        <div class="searchResultsItem MarginBottom10">
                  <ul class="searchResultsJobInfo closed">
            <li class="searchResultsColumn1">
                      <div class="sectionHeader Padding0 Height28 LightGrayBG">
                <div class="floatLeft">

                          <h2 class="width305 marginTop6 UpperCaseNone TextColor01">Contact Info</h2>
                        </div>
                <div class="floatRight marginTop5 marginRight5 accord-open">&nbsp;</div>
              </div>
                    </li>
          </ul>
                  <div class="searchResultsSubContent">
            <div class="job_seeker_login leftFormHolderResumepage marginTop0">

              <div class="rowEvenNewSpacing"> <span class="lableText3">First Name:</span>                
                <form:input path="contactInfoForm.firstName" class="job_seeker_password textBox350" />
                <span class="required">(Required)</span> 
              </div>
				<div>
					<span class="lableText3"></span>
					<FONT color="red"><form:errors path="contactInfoForm.firstName" /></FONT> 
				</div>
              
               <div class="rowEvenNewSpacing"> <span class="lableText3">Middle Name:</span>
                <form:input path="contactInfoForm.middleName" class="job_seeker_password textBox350" />
              </div>

               <div class="rowEvenNewSpacing"> <span class="lableText3">Last Name:</span>
                <form:input path="contactInfoForm.lastName" class="job_seeker_password textBox350" />
                <span class="required">(Required)</span> 
               </div>
				<div>
					<span class="lableText3"></span>
					<FONT color="red"><form:errors path="contactInfoForm.lastName" /></FONT> 
				</div>               
               <div class="rowEvenNewSpacing"> <span class="lableText3">Street Address:</span>
                <form:input path="contactInfoForm.addressLine1" class="job_seeker_password textBox350" />
        		<span class="required">(Required)</span> 
        		</div>
				<div>
					<span class="lableText3"></span>
					<FONT color="red"><form:errors path="contactInfoForm.addressLine1" /></FONT> 
				</div>  
               <div class="rowEvenNewSpacing"> <span class="lableText3"></span>
	                <form:input path="contactInfoForm.addressLine2" class="job_seeker_password textBox350" />
	        		<span class="required"></span> 
        		</div>
               <div class="rowEvenNewSpacing"> <span class="lableText3">City:</span>
                <form:input path="contactInfoForm.city" class="job_seeker_password textBox350"/>
        		<span class="required">(Required)</span> 
        	  </div>
				<div>
					<span class="lableText3"></span>
					<FONT color="red"><form:errors path="contactInfoForm.city" /></FONT> 
				</div> 
               <div class="row"> <span class="lableTextSelect marginTop13 ">State / Province:</span>
		            <form:select path="contactInfoForm.state" class="jb_input3 jb_input_width3">
						<form:option value="0" label="Select" />
						<form:options items="${stateList}" itemValue="stateId" itemLabel="stateValue" />
					</form:select>                
				<span class="required marginTop8">(Required)</span> 
			</div>
			<div>
				<span class="lableText3"></span>
				<FONT color="red"><form:errors path="contactInfoForm.state" /></FONT> 
			</div> 
            <div class="rowEvenNewSpacing"> <span class="lableText3">Zip Code:</span>
                 <form:input path="contactInfoForm.postalCode" class="job_seeker_password textBox350"/>
        		<span class="required">(Required)</span> 
        	</div>
        	<div>
				<span class="lableText3"></span>
				<FONT color="red"><form:errors path="contactInfoForm.postalCode" /></FONT> 
			</div>
            <div class="row"> <span class="lableTextSelect marginTop13 ">Country:</span>
		 		<form:select path="contactInfoForm.country" class="jb_input3 jb_input_width3">
					<form:option value="0" label="Select" />
					<form:options items="${countryList}" itemValue="countryId" itemLabel="countryValue" />
				</form:select>	                
				<span class="required marginTop8">(Required)</span> 
			</div>
			<div>
				<span class="lableText3"></span>
				<FONT color="red"><form:errors path="contactInfoForm.country" /></FONT> 
			</div> 
            <div class="rowEvenNewSpacing MarginBottom10"> <span class="lableText3">Phone Number:</span>
                <div class="floatLeft marginRight10"></div>
                <span class="floatLeft marginRight10">
                <c:forEach items="${createResume.listPhoneDtlForm}" var="phoneDtl" varStatus="status">                
                	<form:select path="listPhoneDtlForm[${status.index}].phoneType" id="exclude" class="jb_input75 marginTop0" >
						<form:option value="0" label="Select" />
						<form:options items="${countryList}" itemValue="countryId" itemLabel="countryValue" />
					</form:select>	
					<form:input path="listPhoneDtlForm[${status.index}].phoneNumber" class="job_seeker_password"/>
					<span class="required ">(Required)</span>                 
                </c:forEach> 
               </span>
                </div>
                <div>
                <a href="" class="link_color1_emphasized">Save and add another phone number</a></span> </div>
                </div>
          </div>
                </div>
        <div class="searchResultsItem">
                  <ul class="searchResultsJobInfo closed">

            <li class="searchResultsColumn1">
                      <div class="sectionHeader Padding0 Height28 LightGrayBG">
                <div class="floatLeft">
                          <h2 class="width305 marginTop6 UpperCaseNone TextColor01">Objective</h2>
                        </div>
                <div class="floatRight marginTop5 marginRight5 accord-open">&nbsp;</div>
              </div>
                    </li>

          </ul>
                  <div class="searchResultsSubContent">
            <div class="job_seeker_login leftFormHolderResumepage marginTop0">
                      <div class="row MarginBottom10 ">
                <div class="lableText3 marginTop10"> Your Career Objective:</div>
                <div class="input_grp5 ">
                <form:textarea path="objective" class="textareaBoxCResume" rows="5" cols="45"/>
                          <p>2000 characters remaining</p>
                        </div>
              </div>
                    </div>
          </div>
                </div>
        <div class="searchResultsItem">
                  <ul class="searchResultsJobInfo closed">

            <li class="searchResultsColumn1">
                      <div class="sectionHeader Padding0 Height28 LightGrayBG">
                <div class="floatLeft">
                          <h2 class="width305 marginTop6 UpperCaseNone TextColor01">Work Experience</h2>
                        </div>
                <div class="floatRight marginTop5 marginRight5 accord-open">&nbsp;</div>
              </div>
                    </li>

          </ul>
           <div class="searchResultsSubContent">
           <c:forEach items="${createResume.listWorkExpForm}" var="workExp" varStatus="status">
            <div class="job_seeker_login leftFormHolderResumepage marginTop0">
                      <div class="rowEvenNewSpacing"> <span class="lableText3">Job Title:</span>
                <form:input path="listWorkExpForm[${status.index}].jobTitle"  class="job_seeker_Resume" />
                <span class="required">(Required)</span> </div>
                      <div class="rowEvenNewSpacing"> <span class="lableText3">Company Name:</span>

                <form:input path="listWorkExpForm[${status.index}].employerName"  class="job_seeker_Resume"/>
                <span class="required">(Required)</span> </div>
                <div class="class="row""> <span class="lableTextSelect marginTop13 ">Employment Type:</span>
                
                <form:select path="listWorkExpForm[${status.index}].employmentType" class="jb_input3 jb_input_width3">
					<form:option value="0" label="--- Employment Type ---" />
					<form:options items="${empTypeList}" itemValue="optionId" itemLabel="optionName" />
				</form:select>

                <span class="required marginTop8">(Required)</span> </div>
                      <div class="rowEvenNewSpacing"> <span class="lableText3"> Start Date:</span>
                <form:input path="listWorkExpForm[${status.index}].startDate"  class="job_seeker_Resume" /> 
                <div class="calender"><a href="#"><img src="../resources/images/tranBg.png" width="14" height="14" alt="Datepick"></a> </div>
        			<span class="required">(Required)</span> </div>
                      <div class="row marginTop10"> <span class="lableTextSelect marginTop10">End Date:</span>
                <form:input path="listWorkExpForm[${status.index}].endDate"  class="job_seeker_Resume" />
                 <div class="calender"><a href="#"><img src="../resources/images/tranBg.png" width="14" height="14" alt="Datepick"></a> </div>
                <span class="required">
                <input name="" type="checkbox" value="">
                        </span>
                <div class="floatLeft marginLeft10 marginTop8"> present</div>

                <span class="required">(Required)</span> </div>
                      <div class="rowEvenNewSpacing"> <span class="lableText3">Years at Position:</span>
                <form:input path="listWorkExpForm[${status.index}].yrsAtPostion"  class="job_seeker_Resume" />
        			<span class="required">(Required)</span> </div>
                <div class="row"> <span class="lableTextSelect marginTop13 ">Career Level:</span>
	
				<form:select path="listWorkExpForm[${status.index}].currentCareerLvl" class="jb_input3 jb_input_width3">
					<form:option value="0" label="--- Career Level ---" />
					<form:options items="${careerLvlList}" itemValue="optionId" itemLabel="optionName" />
				</form:select>
						
                <span class="required marginTop8">
                        <input name="" type="checkbox" value="">
                        </span>

                <div class=" floatLeft marginLeft10 marginTop5">
                          <p >This is my current career level</p>
                        </div>
                <span class="required marginTop8">(Required)</span> </div>
                <div class="row"> <span class="lableTextSelect marginTop13 ">Annual Salary:</span>
                                       						
				<form:select path="listWorkExpForm[${status.index}].annualSalary" class="jb_input3 jb_input_width3">
					<form:option value="0" label="--- Annual Salary ---" />
					<form:options items="${annualSalarylList}" itemValue="optionId" itemLabel="optionName" />
				</form:select>
				
                <div class="toolTip marginTop15 marginLeft10"><span class="classic">Select your annual salary from this drop-down menu or enter your hourly pay rate in the following text box.</span></div>
                <span class="required marginTop8"></span> </div>
                      <div class="rowEvenNewSpacing MarginBottom10"> <span class="lableText3"> Hourly Pay Rate:</span>
                <form:input path="${workExp.hrlyPayRate}"  class="job_seeker_Resume" />

        <span class="required "></span> </div>
                      <div class="row MarginBottom10 ">
                <div class="lableText3 marginTop10"> Summary/Job Description:</div>
                <div class="input_grp5 ">                          
                          <form:textarea path="objective" class="textareaBoxCResume" rows="3" cols="45"/>
                          <p>2000 characters remaining</p>

                          <p><a href="" class="link_color1_emphasized">Save and add another work experience</a></p>
                        </div>
              </div>
            </div>
           </c:forEach> 
          </div>
        </div>
        <div class="searchResultsItem">
                  <ul class="searchResultsJobInfo closed">

            <li class="searchResultsColumn1">
                      <div class="sectionHeader Padding0 Height28 LightGrayBG">
                <div class="floatLeft">
                          <h2 class="width305 marginTop6 UpperCaseNone TextColor01">Education</h2>
                        </div>
                <div class="floatRight marginTop5 marginRight5 accord-open">&nbsp;</div>
              </div>
                    </li>

          </ul>
           <div class="searchResultsSubContent">
           <c:forEach items="${createResume.listEduForm}" var="education" varStatus="status">
            <div class="job_seeker_login leftFormHolderResumepage marginTop0">
                      <div class="rowEvenNewSpacing"> <span class="lableText3">Institution Name:</span>
                <form:input path="listEduForm[${status.index}].instituteName" class="job_seeker_password textBox350" />
                <span class="required">(Required)</span> </div>
                      <div class="row"> <span class="lableTextSelect marginTop13 ">Degree Level:</span>
				
				<form:select path="listEduForm[${status.index}].degreeLvl" class="jb_input3 jb_input_width3">
					<form:option value="0" label="--- Degree Level ---" />
					<form:options items="${eduDegreeList}" itemValue="optionId" itemLabel="optionName" />
				</form:select>
            
	                <span class="required marginTop8">
	                     <input name="" type="checkbox" value="">
	                </span>
	
	                <div class=" floatLeft marginLeft10 marginTop5">
	                     <p >I haven't graduated yet.</p>
	                </div>
                <span class="required marginTop8">(Required)</span> </div>
                      <div class="rowEvenNewSpacing"> <span class="lableText3">Field of Study:</span>
                <form:input path="listEduForm[${status.index}].fieldOfStudy" class="job_seeker_password textBox350" />
              </div>
                      <div class="rowEvenNewSpacing"> <span class="lableText3"> Start Date:</span>
                <form:input path="listEduForm[${status.index}].startDate" class="job_seeker_Resume" />
                 <div class="calender"><a href="#"><img src="../resources/images/tranBg.png" width="14" height="14" alt="Datepick"></a> </div>
      				</div>
                      <div class="row marginTop10"> <span class="lableTextSelect marginTop10">End Date:</span>

                <form:input path="listEduForm[${status.index}].endDate" class="job_seeker_Resume" /> <div class="calender"><a href="#"><img src="../resources/images/tranBg.png" width="14" height="14" alt="Datepick"></a> </div>
              </div>
                      <div class="row MarginBottom10 ">

                <div class="lableText3 marginTop10"> Degrees:</div>
                <div class="input_grp5 ">
                          <form:textarea path="listEduForm[${status.index}].degrees" class="textareaBoxCResume" rows="3" cols="45"/>
                          <p>2000 characters remaining</p>
                        </div>
              </div>
                      <div class="row MarginBottom10 ">

                <div class="lableText3 marginTop10"> Certifications:</div>
                <div class="input_grp5 ">
                          <form:textarea path="listEduForm[${status.index}].certifications" class="textareaBoxCResume" rows="3" cols="45"/>
                          <p>2000 characters remaining</p>
                          <p><a href="" class="link_color1_emphasized">Save and add another institution</a></p>
                        </div>

              </div>
             </div>
           </c:forEach>  
          </div>
                </div>
        <div class="searchResultsItem">
                  <ul class="searchResultsJobInfo closed">
            <li class="searchResultsColumn1">
                      <div class="sectionHeader Padding0 Height28 LightGrayBG">
                <div class="floatLeft">

                          <h2 class="width305 marginTop6 UpperCaseNone TextColor01">Certification</h2>
                        </div>
                <div class="floatRight marginTop5 marginRight5 accord-open">&nbsp;</div>
              </div>
                    </li>
          </ul>
         <div class="searchResultsSubContent" id="listOfCertsId">
           <c:forEach items="${createResume.listCertForm}" var="certification" varStatus="status"> 
				    <div class="job_seeker_login leftFormHolderResumepage marginTop0" id="addCertDivId">
					     <div class="rowEvenNewSpacing"> <span class="lableText3">Certification Name:</span>
					         <form:input path="listCertForm[${status.index}].certificationName" class="job_seeker_password textBox350"/>
					         <span class="required">(Required)</span> </div>
					       <div class="rowEvenNewSpacing"> <span class="lableText3">Certifying Authority:</span>
					         <form:input path="listCertForm[${status.index}].certificationName" class="job_seeker_password textBox350"/>
					       </div>
					
					               <div class="rowEvenNewSpacing"> <span class="lableText3"> Received:</span>
					         <form:input path="listCertForm[${status.index}].dateOfReceipt" class="job_seeker_Resume"/>
						</div>
					               <div class="row MarginBottom10 ">
					         <div class="lableText3 marginTop10"> Summary:</div>
					         <div class="input_grp5 ">
					
					                   <form:textarea path="listCertForm[${status.index}].summary" class="textareaBoxCResume" rows="3" cols="45"/>
					                   <p>2000 characters remaining</p>
					                 </div>
					       </div>
					     </div>
			</c:forEach> 
          </div>
          <div id="certAjaxCallId">
          	<input type="button" value="Save and add another certification" class="btn_sm orange" id="certAjaxCallIdButton"/>
          	<!-- <p><a href="#" class="link_color1_emphasized">Save and add another certification</a></p> -->
          </div>
       </div>
        <div class="searchResultsItem">
                  <ul class="searchResultsJobInfo closed">
            <li class="searchResultsColumn1">
                      <div class="sectionHeader Padding0 Height28 LightGrayBG">
                <div class="floatLeft">
                          <h2 class="width305 marginTop6 UpperCaseNone TextColor01">Skills</h2>
                        </div>

                <div class="floatRight marginTop5 marginRight5 accord-open">&nbsp;</div>
              </div>
                    </li>
          </ul>
                  <div class="searchResultsSubContent">
            <div class="job_seeker_login leftFormHolderResumepage marginTop0">
                      <div class="rowEvenNewSpacing"> <span class="lableText3">Skill:</span>
                <input type="text" name="mobileNo" class="job_seeker_password textBox350" />

                <span class="required"><a href="" class="btn_sm orange">Add</a></span>
                <div class="toolTip marginTop8 marginLeft5"><span class="classic">Show potential employers what your strengths are by entering up to 50 special skills in this section.  Use brief keywords and phrases like "Triage" and "Emergency Care" so your list of skills can be browsed with ease.</span></div>
              </div>
                      <div class="row MarginBottom10 ">
                <div class="lableText3 marginTop10"> </div>
                <div class="input_grp5 ">
                          <textarea id="Body Text:" class="textareaBoxCResume" rows="3" cols="45" name="Body Text:"></textarea>

                        </div>
              </div>
                    </div>
          </div>
                </div>
        <div class="searchResultsItem">
                  <ul class="searchResultsJobInfo closed">
            <li class="searchResultsColumn1">
                      <div class="sectionHeader Padding0 Height28 LightGrayBG">

                <div class="floatLeft">
                          <h2 class="width305 marginTop6 UpperCaseNone TextColor01">Languages</h2>
                        </div>
                <div class="floatRight marginTop5 marginRight5 accord-open">&nbsp;</div>
              </div>
                    </li>
          </ul>
          <div class="searchResultsSubContent">
			<c:forEach items="${createResume.listLangForm}" var="language" varStatus="status">   
            <div class="job_seeker_login leftFormHolderResumepage marginTop0">
               <div class="row"> <span class="lableText3 marginTop13 ">Language:</span>
				<form:select path="listLangForm[${status.index}].language" class="jb_input3 jb_input_width3">
					<form:option value="0" label="English" />
					<form:options items="${eduDegreeList}" itemValue="optionId" itemLabel="optionName" />
				</form:select>
              </div>
              <div class="row"> <span class="lableText3 marginTop13 ">Proficiency Level:</span>
              
              	<form:select path="listLangForm[${status.index}].expLvl" class="jb_input3 jb_input_width3">
					<form:option value="0" label="--- Proficiency Level ---" />
					<form:options items="${langProficiencylList}" itemValue="optionId" itemLabel="optionName" />
				</form:select>
              </div>
               <div class="row MarginBottom10"> <span class="lableText3 marginTop13 "></span>
                <p><a href="" class="link_color1_emphasized">Save and add another language</a></p>
              </div>
            </div>
		   </c:forEach>	
          </div>
                </div>
        <div class="searchResultsItem">
                  <ul class="searchResultsJobInfo closed">
            <li class="searchResultsColumn1">
                      <div class="sectionHeader Padding0 Height28 LightGrayBG">
                <div class="floatLeft">
                          <h2 class="width305 marginTop6 UpperCaseNone TextColor01">Awards</h2>

                        </div>
                <div class="floatRight marginTop5 marginRight5 accord-open">&nbsp;</div>
              </div>
                    </li>
          </ul>
                  <div class="searchResultsSubContent">
            <div class="job_seeker_login leftFormHolderResumepage marginTop0">
                      <div class="row MarginBottom10 ">
                <div class="lableText3 marginTop10">If you've received any<br>

                          professional awards,<br>
                          please list them here<br>
                          along with the years in<br>
                          which you earned them: </div>
                <div class="input_grp5 ">
						  <form:textarea path="${createResume.awards}" class="textareaBoxCResume" rows="3" cols="45"/>	
                          <p>2000 characters remaining</p>
                        </div>
              </div>
                    </div>
          </div>
                </div>
        <div class="searchResultsItem">
                  <ul class="searchResultsJobInfo closed">

            <li class="searchResultsColumn1">
                      <div class="sectionHeader Padding0 Height28 LightGrayBG">
                <div class="floatLeft">
                          <h2 class="width305 marginTop6 UpperCaseNone TextColor01">Memberships</h2>
                        </div>
                <div class="floatRight marginTop5 marginRight5 accord-open">&nbsp;</div>
              </div>
                    </li>

          </ul>
                  <div class="searchResultsSubContent">
            <div class="job_seeker_login leftFormHolderResumepage marginTop0">
                      <div class="row MarginBottom10 ">
                <div class="lableText3 marginTop10">If you're a member of
                          any professionals
                          associations, please
                          list them here: </div>
                <div class="input_grp5 ">                          
                          <form:textarea path="${createResume.memberships}" class="textareaBoxCResume" rows="3" cols="45"/>	
                          <p>2000 characters remaining</p>

                        </div>
              </div>
                    </div>
          </div>
                </div>
        <div class="searchResultsItem">
                  <ul class="searchResultsJobInfo closed">
            <li class="searchResultsColumn1">
                      <div class="sectionHeader Padding0 Height28 LightGrayBG">

                <div class="floatLeft">
                          <h2 class="width305 marginTop6 UpperCaseNone TextColor01">Other</h2>
                        </div>
                <div class="floatRight marginTop5 marginRight5 accord-open">&nbsp;</div>
              </div>
                    </li>
          </ul>
                  <div class="searchResultsSubContent">

            <div class="job_seeker_login leftFormHolderResumepage marginTop0">
                      <div class="row MarginBottom10 ">
                <div class="lableText3 marginTop10"> If you have more<br>
                          information that you<br>
                          would like to include,<br>
                          please enter it here:</div>

                <div class="input_grp5 ">
                          <form:textarea path="${createResume.otherDetails}" class="textareaBoxCResume" rows="3" cols="45"/>	
                          <p>2000 characters remaining</p>
                        </div>
              </div>
                    </div>
          </div>
                </div>

        <div class="searchResultsItem MarginBottom10">
                  <ul class="searchResultsJobInfo closed">
            <li class="searchResultsColumn1">
                      <div class="sectionHeader Padding0 Height28 LightGrayBG">
                <div class="floatLeft">
                          <h2 class="width305 marginTop6 UpperCaseNone TextColor01">References</h2>
                        </div>
                <div class="floatRight marginTop5 marginRight5 accord-open">&nbsp;</div>

              </div>
                    </li>
          </ul>
         <div class="searchResultsSubContent">
         <c:forEach items="${createResume.listRefForm}" var="reference" varStatus="status">    
            <div class="job_seeker_login leftFormHolderResumepage marginTop0">
                      <div class="rowEvenNewSpacing"> <span class="lableText3">Name:</span>
                <form:input path="listRefForm[${status.index}].name" class="job_seeker_password textBox350"/>
              </div>

                      <div class="rowEvenNewSpacing"> <span class="lableText3">Job Title:</span>
                <form:input path="listRefForm[${status.index}].jobTitle" class="job_seeker_password textBox350"/>
              </div>
                      <div class="rowEvenNewSpacing"> <span class="lableText3">Company Name:</span>
                <form:input path="listRefForm[${status.index}].companyName" class="job_seeker_password textBox350"/>
      </div>
                      <div class="rowEvenNewSpacing"> <span class="lableText3">Phone Number:</span>

                <form:input path="listRefForm[${status.index}].phoneNo" class="job_seeker_password textBox350"/>
      </div>
                      <div class="rowEvenNewSpacing"> <span class="lableText3">Email Address:</span>
                <form:input path="listRefForm[${status.index}].email" class="job_seeker_password textBox350"/>
        <span class="required"></span> </div>
                      <div class="rowEvenNewSpacing"> <span class="lableText3">Reference Type:</span><span class="requiredleft0">

                        <input name="Professional" type="radio" value="" class="marginRight5">
                        <form:radiobutton path="${reference.referenceType}" value="Professional"/>
                        <label class="greyLabel">Professional</label>
                        </span>&nbsp;&nbsp;&nbsp;<span class="required">
                        <form:radiobutton path="${reference.referenceType}" value="Personal"/>
                        <label class="greyLabel">Personal</label>
                        </span> </div>
                      <div class="rowEvenNewSpacing MarginBottom10"> <span class="lableText3"></span> <a href="" class="link_color1_emphasized">Save and add another reference</a> </div>

                    </div>
                    </c:forEach>
          		  </div>          		  
                </div>               
      </div>
      <div class="clearfix"></div><br />
       <span class="marginBottom10 FloatLeft">
	       <input type="submit" value="Save" name="Save" class="btn_sm orange"/>
	       <input type="submit" value="Preview" name="Preview" class="btn_sm orange"/>
	       <a href="/jobboard/jobSeeker/jobSeekerDashBoard.html" class="btn_sm orange">Cancel</a>	       
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

<!-- end main_wrapper_outside -->
<div class="footer_wrapper">
          <div class="container1">
    <h4>Professions:</h4>
    <ul>
              <li><a href="#">Nursing</a></li>
              <li><a href="#">Imaging & Radiation</a></li>
              <li><a href="#">Oncology</a></li>

              <li><a href="#">Physical Therapy and Rehab Medicine</a></li>
              <li><a href="#">Occupational Therapy</a></li>
              <li><a href="#">Speech-Language Pathology</a></li>
              <li><a href="#">Audiology</a></li>
              <li><a href="#">Hearing Practice Management</a></li>
              <li><a href="#">Long-Term Care Managament</a></li>

              <li><a href="#">Respiratory Care</a></li>
              <li><a href="#">Sleep Medicine</a></li>
              <li><a href="#">Labortory</a></li>
              <li><a href="#">Health Information</a></li>
              <li><a href="#">Nurse Practitioners</a></li>
              <li><a href="#">Physician Assistants</a></li>

              <li><a href="#">Healthcare Executives</a></li>
            </ul>
  </div>
          <!-- end container1 -->
          
          <div class="container2">
    <h4>Content:</h4>
    <ul>
              <li><a href="#">News</a></li>

              <li><a href="#">Features</a></li>
              <li><a href="#">Multimedia</a></li>
              <li><a href="#">Buyers Guide</a></li>
              <li><a href="#">Community</a></li>
              <li><a href="#">Downloads</a></li>
            </ul>

  </div>
          <!-- end container2 -->
          
          <div class="container3">
    <h4>Services:</h4>
    <ul>
              <li><a href="#">ADVANCE Healthcare Jobs</a></li>
              <li><a href="#">Subscribe</a></li>

              <li><img src="../resources/images/email.png" class="foot_icon"/><a href="#">Sign Up for Enewsletter</a></li>
              <li><img src="../resources/images/fbook_sm.png" class="foot_icon"/><a href="#">Connect on Facebook</a></li>
              <li><img src="../resources/images/L_In_sm.png" class="foot_icon"/><a href="#">Connect on LinkedIn</a></li>
              <li><img src="../resources/images/twitter_sm.png" class="foot_icon"/><a href="#">Connect on Twitter</a></li>
              <li><a href="#">Download the Mobile App</a></li>
              <li><a href="#">Order Promotional Items</a></li>

            </ul>
  </div>
          <!-- end container3 -->
          
          <div class="container4">
    <h4>More from ADVANCE:</h4>
    <ul>
              <li><a href="#">ADVANCE Heathcare Shop</a></li>
              <li><a href="#">ADVANCE Custom Promotions</a></li>

              <li><a href="#">ADVANCE Heathcare Jobs</a></li>
              <li><a href="#">ADVANCE Job Fairs</a></li>
              <li><a href="#">ADVANCE Continuing Ediucation</a></li>
              <li><a href="#">ADVANCE Custom Publishing</a></li>
            </ul>
  </div>
          <!-- end container4 -->

          
          <div class="container5">
    <h4>Corporate:</h4>
    <ul>
              <li><a href="#">About Us</a></li>
              <li><a href="#">Contact Us</a></li>
              <li><a href="#">Advertise with Us</a></li>
              <li><a href="#">Work for Us</a></li>

              <li><a href="#">Privacy Policy</a></li>
              <li><a href="#">Term of Service</a></li>
              <li><a href="#">Help</a></li>
            </ul>
  </div>
          <!-- end container5 --> 
          
          <br class="clearfix" />
          <p class="copyright">&copy; 2012 Merion Matters 2900 Horizon Drive King of Prussia PA 19406 800-355-5627</p>

        </div>
<!-- footer_wrapper -->
</form:form>
</body>
</html>