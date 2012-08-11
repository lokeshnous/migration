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
		<script type="text/javascript" src="../resources/js/jquery.cycle.all.min.js"></script>
		<script type="text/javascript" src="../resources/js/slider.js"></script>
		<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>

		<script type="text/javascript">
		    jQuery(document).ready(function(){
		    jQuery(".megamenu").megamenu();
		});
		</script>
		</head>

		<body class="job_board">
<div class="ad_page_top"> <img src="../resources/images/ads/banner_ad_fpo.png" /> </div>
<div class="main_wrapper_outside">
          <div class="main_wrapper_inside">
    <div class="main">
              <div class="header_wrapper"> <a href="">

                <div class="logo"></div>
                </a>
        <div class="headerLoginSection">
                  <div class="headerLoginSectionColumns">
					<span class="boldText">Welcome, (Job Seeker Name)!</span><br>
					 <div class="floatRight"> <span class="floatLeft"> <a href="">Log Out</a> | <a href="">My Dashboard</a></span></div></div>

                  <!-- loginHeader --><!-- loginHeader -->
                  
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
 <form action="" method="get" commandName="createResume" id="viewresumeId">
              <!--nav-->
              <div class="clearfix"></div>
              <!--Start:MidContant-->
              <div class="MidContent_Wrapper floatLeft">
              <div class="popupHeader Padding0  OrangeBG">
                  <h2>VIEW YOUR RESUME</h2>
          <span class="floatRight marginRight10"><a href="/jobboard/jobSeeker/jobSeekerDashBoard.html" class="link_color3_emphasized FontSize12 FontWeight">Back to Dashboard</a></span></div>

        
        <div class="clearfix"></div>
        <div class="MidContent_Wrapper FloatLeft marginBottom10">
                  <div class="ResumeHeader"> <span class="FontSize18"><c:out value="${createResume.resumeName}"/></span>
            <div class="clearfix"></div>
            <span>
                    <h3 class="marginTop3"><c:out value="${createResume.desiredJobTitle}"/></h3>
                    </span>

            <div class="clearfix"></div>
            <span>
                    <p class="marginTop3">Available 08/01/2012</p>
                    </span> </div>
                  <div class="IconsArea"><a href="#"><img src="../resources/images/Download.png" width="20" height="20" alt=""></a>&nbsp; <a href="#"><img src="../resources/images/Print2.png" width="20" height="20" alt=""></a></div>
                </div>
        <!---->

        <div class="MidContent_Wrapper FloatLeft marginBottom20">
                  <div class="sectionHeader Padding0 Height28 LightGrayBG">
            <h2 class="width305 marginTop5 UpperCaseNone TextColor01">Contact Info</h2>
          </div>
                  <div class="clearfix"></div>
                  <div class="ContactInfoBox">
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>First Name:</strong></p>

              </span><span class="ContactInfoAreaRight"><c:out value="${createResume.contactInfoForm.firstName}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Last Name:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${createResume.contactInfoForm.lastName}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">

              <p><strong>Street Address:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${createResume.contactInfoForm.addressLine1}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>City:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${createResume.contactInfoForm.city}"/></span> </div>

            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>State / Province:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${createResume.contactInfoForm.state}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>ZIP Code:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${createResume.contactInfoForm.postalCode}"/></span> </div>

            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Country:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${createResume.contactInfoForm.country}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Phone:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${createResume.contactInfoForm.mobileNo}"/></span> </div>

          </div>
                </div>
        <div class="clearfix"></div>
        <!---->
        <div class="MidContent_Wrapper FloatLeft">
                  <div class="sectionHeader Padding0 Height28 LightGrayBG">
            <h2 class="width305 marginTop5 UpperCaseNone TextColor01">Objective</h2>
          </div>

                  <div class="clearfix"></div>
                  <div class="ContactInfoBox paddingLeft15 MarginBottom10">
            <p><c:out value="${createResume.objective}"/></p>
          </div>
                </div>
        <!---->
        <div class="MidContent_Wrapper FloatLeft marginBottom20">
                  <div class="sectionHeader Padding0 Height28 LightGrayBG">

          <h2 class="width305 marginTop5 UpperCaseNone TextColor01">Work Experience</h2>
          </div>
         <div class="clearfix"></div>
         <div class="ContactInfoBox">
         <c:forEach items="${createResume.listWorkExpForm}" var="workExp" varStatus="status">
         <c:if test="${status.count != 1}">   
            <p class="borderBottomDotted marginBottom15">&nbsp;</p> 
         </c:if>   
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Job Title:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${workExp.jobTitle}"/></span> </div>

            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Company Name:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${workExp.employerName}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Employment Type:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${workExp.employmentType}"/></span> </div>

            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Start Date:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${workExp.startDate}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>End Date:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${workExp.endDate}"/></span> </div>

            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Years at Position:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${workExp.yrsAtPostion}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Career Level:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${workExp.currentCareerLvl}"/></span> </div>

            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Annual Salary: </strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${workExp.annualSalary}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Hourly Pay Rate</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${workExp.hrlyPayRate}"/></span> </div>

            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Summary / Job Description:</strong></p>
              </span> <span class="ContactInfoAreaRight width505 AutoHeight"><c:out value="${workExp.description}"/></span> </div>
              <div class="clearfix"></div>
        </c:forEach>                          
       </div>
      </div>
        <!---->
        <div class="MidContent_Wrapper FloatLeft marginBottom20">
                  <div class="sectionHeader Padding0 Height28 LightGrayBG">
            <h2 class="width305 marginTop5 UpperCaseNone TextColor01">Education</h2>
          </div>
           <div class="clearfix"></div>

           <div class="ContactInfoBox">
           <c:forEach items="${createResume.listEduForm}" var="education" varStatus="status">    
	         <c:if test="${status.count != 1}">   
	            <p class="borderBottomDotted marginBottom15">&nbsp;</p> 
	         </c:if>   
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Institution Name:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${education.instituteName}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Degree Level:</strong></p>

              </span><span class="ContactInfoAreaRight"><c:out value="${education.degreeLvl}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Field of Study:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${education.fieldOfStudy}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">

              <p><strong>Start Date:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${education.startDate}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>End Date:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${education.endDate}"/></span> </div>

            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Degrees:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${education.degrees}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Certifications:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${education.certifications}"/></span> </div>
            <div class="clearfix"></div>
			</c:forEach>
          </div>
                </div>
        <!---->
        <div class="MidContent_Wrapper FloatLeft marginBottom20">
                  <div class="sectionHeader Padding0 Height28 LightGrayBG">
            <h2 class="width305 marginTop5 UpperCaseNone TextColor01">Certification</h2>
          </div>
           <div class="clearfix"></div>

           <div class="ContactInfoBox">
           <c:forEach items="${createResume.listCertForm}" var="certification" varStatus="status">    
           <c:if test="${status.count != 1}">   
            <p class="borderBottomDotted marginBottom15">&nbsp;</p> 
           </c:if>   
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Certification Name:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${education.certificationName}"/></span> </div>
<%--             <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Certifying Authority:</strong></p>

              </span><span class="ContactInfoAreaRight"><c:out value="${education.certifications}"/></span> </div> --%>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Received:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${education.dateOfReceipt}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">

              <p><strong>Summary:</strong></p>
              </span><span class="ContactInfoAreaRight width505 AutoHeight"><c:out value="${education.summary}"/></span> </div>
            <div class="clearfix"></div>
			</c:forEach>              
          </div>
        </div>
        <!---->
        <div class="MidContent_Wrapper FloatLeft">
                  <div class="sectionHeader Padding0 Height28 LightGrayBG">

            <h2 class="width305 marginTop5 UpperCaseNone TextColor01">Skills</h2>
          </div>
                  <div class="clearfix"></div>
                  <div class="ContactInfoBox paddingLeft15 MarginBottom10">
            <p><c:out value="${createResume.skills}"/></p>
          </div>
                </div>
        <!---->

        <div class="MidContent_Wrapper FloatLeft marginBottom20">
          <div class="sectionHeader Padding0 Height28 LightGrayBG">
            <h2 class="width305 marginTop5 UpperCaseNone TextColor01">Languages</h2>
          </div>
           <div class="clearfix"></div>
            <div class="ContactInfoBox">
           <c:forEach items="${createResume.listLangForm}" var="language" varStatus="status">   
			<c:if test="${not empty language.language}">
			  <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong><c:out value="${language.language}"/> :</strong></p>

              </span><span class="ContactInfoAreaRight"><c:out value="${language.expLvl}"/></span> </div>
			</c:if>	
           </c:forEach>
          </div> 
         </div>
          <!---->
          <div class="MidContent_Wrapper FloatLeft">
                  <div class="sectionHeader Padding0 Height28 LightGrayBG">

            <h2 class="width305 marginTop5 UpperCaseNone TextColor01">Awards</h2>
          </div>
                  <div class="clearfix"></div>
                  <div class="ContactInfoBox paddingLeft15 MarginBottom10">
            <p><c:out value="${createResume.awards}"/></p>
          </div>
                </div>
          <!---->

           <div class="MidContent_Wrapper FloatLeft">
                  <div class="sectionHeader Padding0 Height28 LightGrayBG">
            <h2 class="width305 marginTop5 UpperCaseNone TextColor01">Memberships</h2>
          </div>
                  <div class="clearfix"></div>
                  <div class="ContactInfoBox paddingLeft15 MarginBottom10">
            <p><c:out value="${createResume.memberships}"/></p>
          </div>

                </div>
          <!---->
           <div class="MidContent_Wrapper FloatLeft">
                  <div class="sectionHeader Padding0 Height28 LightGrayBG">
            <h2 class="width305 marginTop5 UpperCaseNone TextColor01">Other</h2>
          </div>
                  <div class="clearfix"></div>
                  <div class="ContactInfoBox paddingLeft15 MarginBottom10">

            <p><c:out value="${createResume.otherDetails}"/></p>
          </div>
                </div>
          <!---->
          <div class="MidContent_Wrapper FloatLeft marginBottom10">
                  <div class="sectionHeader Padding0 Height28 LightGrayBG">
            <h2 class="width305 marginTop5 UpperCaseNone TextColor01">References</h2>
          </div>

           <div class="clearfix"></div>
           <div class="ContactInfoBox">
           <c:forEach items="${createResume.listRefForm}" var="reference" varStatus="status">    
           <span>
             <h3 class=" marginLeft10 marginBottom10 FloatLeft width305">Professional References</h3>
             </span>
             <div class="clearfix"></div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Name:</strong></p>

              </span><span class="ContactInfoAreaRight"><c:out value="${reference.name}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Job Title:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${reference.jobTitle}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">

              <p><strong>Company Name:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${reference.companyName}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Phone Number:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${reference.phoneNo}"/></span> </div>

            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Email Address:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${reference.email}"/></span> </div>
            </c:forEach>
            
           <!--  <span>
                    <h3 class=" marginLeft10 marginTop10 marginBottom10 FloatLeft width305">Personal References</h3>
                    </span>

            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Name:</strong></p>
              </span><span class="ContactInfoAreaRight">Mark Shireman</span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Job Title:</strong></p>
              </span><span class="ContactInfoAreaRight">Associate Art Director</span> </div>

            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Company Name:</strong></p>
              </span><span class="ContactInfoAreaRight">Merion Matters, Inc.</span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Phone Number:</strong></p>
              </span><span class="ContactInfoAreaRight">610-278-1400 x1629</span> </div>

            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Email Address:</strong></p>
              </span><span class="ContactInfoAreaRight">mshireman@advanceweb.com</span> </div> -->
              <div class="IconsArea"><a href="#"><img src="../resources/images/Download.png" width="20" height="20" alt=""></a>&nbsp; <a href="#"><img src="../resources/images/Print2.png" width="20" height="20" alt=""></a></div>
          </div>
                </div>

          <!---->
      </div>
 </form>                
              <!--Start:MidContant-->
              <div class="clearfix"></div>
              <!-- content_wrapper -->
              <div class="ad_wrapper"> <img src="../resources/images/ads/banner_ad_fpo.png" /> </div>
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

</body>
</html>