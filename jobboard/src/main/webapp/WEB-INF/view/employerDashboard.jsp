<!DOCTYPE html>
<html lang="en">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>ADVANCE Heathcare Jobs</title>
		<jsp:include page="common/include.jsp" />
		<script type="text/javascript">
		    jQuery(document).ready(
		    function(){
		    	$("#changePassword").displaypopup("#changePassword", "780",
				"370");
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
                  	<span class="boldText">${msg.jsWelcomeMsg}<%=(String) session.getAttribute("userName")%>
							${msg.commonExclamationMark}
                  	</span><br>
            <div class="floatRight"> <span class="floatLeft"> <a href="../jobboard/logout">Log Out</a> | <a href="../healthcarejobs/advanceweb.html">Home</a></span></div>
          </div>
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
              <!--nav-->
              <div class="clearfix"></div>
              <!--Start:MidContant-->
              <div class="MidContent_Wrapper floatLeft">
              <div class="job_search_Resume">
                  <form method="">
            <div class="row ">
                <div class="row marginTop10"><span class="FontSize18">Search Resumes</span> </div>
              <div class="row marginTop10 marginBottom10">
              <div class=" floatLeft  width255"><input type="text" name="keywords" id="keywords" class="jb_input2" />
               <div class="floatLeft"> <div class="toolTipBefore">
                  <label for="keywords">Keywords </label>
                </div> 
                <div class="toolTip"><span class="classic"><p>You can enter a job title, specialty, skill or any other keywords that describe the position you're trying to fill. Then select an option from the drop-down menu to match your exact phrase or any of the individual words you entered.</p></span></div></div>
              </div> 
              <div class="floatLeft marginLeft10">
              
              <select name="phrase" id="phrase" class="jb_input3 marginTop0" >
               <option>Match exact phrase</option>
                 <option>Match any word</option>
              </select>
              </div>
              <div class="FloatLeft marginLeft10 width255">
                
                <input type="text" name="city_state" id="city_state" class="jb_input2" />
                
                <div class="toolTipBefore"><label for="city_state">City and State or ZIP Code </label></div>
                <div class="toolTip"><span class="classic"><p>Narrow your search to job-seekers in a specific area by entering the location here. Then select a search radius in the following drop-down menu.</p></span></div>
      </div>
      <div class="floatLeft marginLeft10">
             
                  <select name="radius" id="radius" class="jb_input3" >
                    <option>--</option>
                    <option>5</option>
                    <option>10</option>
                    <option>25</option>
                    <option>50</option>
                    <option>100</option>
                  </select>
                  <div class="clearfix"></div>
                 <div class="toolTipBefore"><label for="city_state">Radius</label></div>
                </div>
               <div class="floatRight marginTop6 marginLeft10"> 
                <a href="#" class="btn_sm orange margin0">SEARCH</a><br>
                <div class="floatLeft marginTop13">
<a href="">Advanced Search</a> </div>
                </div>
                </div>
 
                      
                      
      
                      </div>
   
            <!-- search_info_box2 --><!-- browse_bar -->
            
                </form>
                <div class="clearfix"></div>
                   </div>
        <div class="dashboardHeader">
                  <h1 class=" FontSize20">[Employer Name] Dashboard</h1>
                </div>
        <div class="MidContent_Wrapper FloatLeft">
                  <div class="row width320 marginRight12">
            <div class="dashboardPanal">
                      <div class="dashboardPanalIconHolder"> <img src="../resources/images/UserProfile.jpg" width="30" height="30" alt="User Profile"></div>
                      <div class="dashboardPanalcontent marginTop5">
                <h2 class="noTopBorder">Profile Management</h2>
                <div class="lableTextDashBoard">
                          <p><a href="/jobboard/jobseekerregistration/jobSeekerChangePassword.html" id="changePassword">Change Password</a> </p>
                        </div>
                <div class="lableTextDashBoard">
                          <p><a href="">Account Settings</a> </p>
                        </div>
                <div class="lableTextDashBoard">
                          <p><a href="">Manage Access Permissions</a> </p>
                        </div>
                <div class="lableTextDashBoard">
                          <p><a href="/jobboard/empProfile/employerprofile.html">Manage Featured Employer Profile</a> </p>
                        </div>
              </div>
                    </div>
            <!---->
            <div class="dashboardPanal">
                      <div class="dashboardPanalIconHolder"> <img src="../resources/images/posting.png" width="30" height="30" alt="User Profile"></div>
                      <div class="dashboardPanalcontent marginTop5">
                <h2 class="noTopBorder">Job Posting</h2>
                <div class="lableTextDashBoard">
                          <p><a href="">Purchase Job Postings</a> </p>
                        </div>
                <div class="lableTextDashBoard">
                          <p><a href="">Post New Job</a> </p>
                        </div>
                <div class="lableTextDashBoard">
                          <p><a href="">Manage / Edit Job Postings</a> </p>
                        </div>
                <div class="lableTextDashBoard">
                          <p><a href="">Manage Job Posting Branding Templates</a> </p>
                        </div>
                <div class="lableTextDashBoard">
                          <p><a href="">Job Posting Inventory</a> </p>
                        </div>
              </div>
                    </div>
            <!---->
            <div class="dashboardPanal">
                      <div class="dashboardPanalIconHolder"> <img src="../resources/images/ManageApplicants.png" width="30" height="30" alt="User Profile"></div>
                      <div class="dashboardPanalcontent marginTop5">
                <h2 class="noTopBorder">Manage Applicants</h2>
                <div class="lableTextDashBoard">
                          <p><a href="">Purchase Resume Search Packages</a> </p>
                        </div>
                <div class="lableTextDashBoard">
                          <p><a href="">Manage Job-Seekers</a> </p>
                        </div>
                <div class="lableTextDashBoard">
                          <p><a href="">My Saved Resume Searches</a> </p>
                        </div>
              </div>
                    </div>
            <!---->
            <div class="dashboardPanal">
                      <div class="dashboardPanalIconHolder"> <img src="../resources/images/Subscriptions.jpg" width="30" height="30" alt="Subscription"></div>
                      <div class="dashboardPanalcontent marginTop5">
                <h2 class="noTopBorder">Current Subscriptions</h2>
                <div class="lableTextDashBoard">
                          <p>E-newsletters</p>
                        </div>
                <div class="lableTextDashBoard">
                          <p>E-mailer</p>
                        </div>
                <div class="lableTextDashBoard">
                          <p><a href="">Modify Subscriptions</a> </p>
                        </div>
              </div>
                    </div>
            <!---->
            
          </div>
                  <!--Right-->
                  <div class="row width615 paddingLeft12 BorderLeft">
            <div class="dashboardPanal">
                      <div class="dashboardPanalIconHolder width8P"><img src="../resources/images/Activity.jpg" width="30" height="30" alt="Resume"></div>
                      <div class="dashboardPanalcontent width92P FloatLeft"> 
                <!--T-->
                <div class="rowEvenNewSpacing marginTop10">
                          <table width="100%" border="0" cellspacing="0" cellpadding="0" class="grid marginTop3">
                    <tr  class="borderTopNone">
                              <th width="46%" align="left" scope="col"><h2 class="noTopBorder noTopBottomBorder">Metrics</h2></th>
                              <th width="18%" align="center" scope="col" class="BorderLeftWhite" ><div class="EDPrice">VIEWS</div></th>
                              <th width="18%" align="center" scope="col" class="BorderLeftWhite"><div class="EDPriceA">CLICKS</div></th>
                              <th width="18%" align="center" scope="col" class="BorderLeftWhite"><div class="EDPriceB">APPLIES</div></th>
                            </tr>
                    <tr class="gridB">
                              <td><input name="radio2" type="radio" id="radio4" value="radio" class="marginLeft10 marginRight10">
                        <label for="radio2">Your Job Posting Totals</label></td>
                              <td align="center" valign="middle" class="BorderLeft TcolorA">1000</td>
                              <td align="center" valign="middle" class="BorderLeft TcolorB">100</td>
                              <td align="center" valign="middle" class="BorderLeft TcolorC">10</td>
                            </tr>
                    <tr class="gridB">
                              <td><input name="radio2" type="radio" id="radio4" value="radio" class="marginLeft10 marginRight10">
                        <label for="radio2">Your Averages Per Job Posting</label></td>
                              <td align="center" valign="middle" class="BorderLeft TcolorA">200</td>
                              <td align="center" valign="middle" class="BorderLeft TcolorB">20</td>
                              <td align="center" valign="middle" class="BorderLeft TcolorC">2</td>
                            </tr>
                    <tr class="gridB">
                              <td class="TBorderNone"><input name="radio2" type="radio" id="radio4" value="radio" class="marginLeft10 marginRight10">
                        <label for="radio2">Site-wide Averages Per Job Posting</label></td>
                              <td align="center" valign="middle" class="BorderLeft TcolorA TBorderNone">300</td>
                              <td align="center" valign="middle" class="BorderLeft TcolorB TBorderNone">30</td>
                              <td align="center" valign="middle" class="BorderLeft TcolorC TBorderNone">3</td>
                            </tr>
                  </table>
                        </div>
                <!--T-->
                <div class="rowBox EDPricec">
                          <div class="floatLeft marginTop3"><strong>&nbsp;&nbsp;&nbsp;Date range</strong></div>
                          <div class="floatLeft marginTop3">&nbsp;&nbsp;&nbsp;From:</div>
                          <div class="floatLeft">
                            <div class="floatLeft">
                              <input type="text" name="firstName" class="EDTextBox" /></div>
                            <div class="calender"><a href="#"><img src="../resources/images/tranBg.png" width="14" height="14" alt="Datepick"></a> </div>
          </div>
                          <div class="floatLeft marginTop3 marginLeft25">To:</div>
                          <div class="floatLeft"><div class="floatLeft">
                    <input type="text" name="firstName" class="EDTextBox" /></div><div class="calender"><a href="#"><img src="../resources/images/tranBg.png" width="14" height="14" alt="Datepick"></a> </div>
                  </div>
                          <div class="EDBox01 marginLeft25"><strong>SHOW</strong></div>
                          <div class="floatLeft marginTop5 marginLeft15"><a href="#">Export</a></div>
                        </div>
                <!--T-->
                <div class="rowBox">
<div class="rowBox Padding0 AutoWidth AutoHeight">
                          <div class="EDBoxMinW">
                          <div class="EDBox02">
                    <div class="row borderBottomDotted Height25">
                              <p class="floatLeft">Available Job Postings</p>
                              <p class="floatRight TextAlignR">3</p>
                            </div>
                    <div class="row marginTop10">
                              <p class="floatLeft">Active Job Postings</p>
                              <p class="floatRight TextAlignR">3</p>
                            </div>
                  </div>
                  </div>
                  <div class=" clearfix"></div>
                  <span class="FloatLeft"><a href="">View Individual Job Posting Stats</a></span>
                  </div>
                  <div class="rowBox marginLeft25 Padding0 AutoWidth AutoHeight">
            <img src="../resources/images/EmpDimg.png" width="250" height="208" alt="img"></div>
                        </div>
              
              </div>
              <div class=" rowBox Padding0">
              <div class="dashboardPanal width285 marginRight15">
                      <div class="dashboardPanalIconHolder "> <img src="../resources/images/Alerts.png" width="30" height="30" alt="Subscription"></div>
                      <div class="dashboardPanalcontent marginTop5">
                <h2 class="noTopBorder">Alerts</h2>
                <div class="lableTextDashBoard ">
                          <p><a href="">View Alerts</a> </p>
                        </div>
                <div class="lableTextDashBoard ">
                          <p><a href="">Set Alerts</a> </p>
                        </div>
              </div>
                    </div>
                  <div class="dashboardPanal width285 paddingLeft10 FloatLeft">
                      <div class="dashboardPanalIconHolder"> <img src="../resources/images/media.png" width="30" height="30" alt="Subscription"></div>
                      <div class="dashboardPanalcontent marginTop5">
                <h2 class="noTopBorder">Solutions</h2>
                <div class="lableTextDashBoard">
                          <p><a href=""><em>ADVANCE </em>Recruitment Solutions Media Kit</a> </p>
                        </div>
              </div>
                    </div>
              </div>
                    </div>
          </div>
                  <!----> 
                  
                  <!----> 
                  
                </div>
      </div>
              <div class="clearfix"></div>
              
              <!---->
              
              <div class="clearfix"></div>
              <!----> 
              
              <!----> 
              
              <!----> 
              
              <!----> 
              
              <!----> 
              
              <!----> 
              
              <!----> 
              
              <!----> 
              
              <!----> 
              
              <!----> 
              
              <!----> 
            </div>
    
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