<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="true" %>
<html lang="en">
     <head>
        	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        	<title>ADVANCE Heathcare Jobs</title>
        	
        	
        	
        	<!-- js files for modalpopup------------------------------------------------- -->
<script src="../resources/js/jquery-1.7.1.js"></script>
<script src="../resources/js/jquery-1.7.1.min.js"></script>
		<script src="../resources/jquery.nyroModal/js/popup.js"></script>
		<script src="../resources/jquery.nyroModal/js/jquery.nyroModal.custom.js"></script>
        <script src="../resources/jquery.nyroModal/js/jquery.nyroModal.custom.min.js"></script>
 	    <link href="../resources/jquery.nyroModal/styles/nyroModal.css" rel="stylesheet" type="text/css">

        <style type="text/css" media="screen">
           @import url("${pageContext.request.contextPath}/resources/jquery.nyroModal/styles/nyroModal.css");
        </style>
<!-- -------------------------------------------------------------------------- -->
        	

			<!-- STYLESHEETS -->
			<link href="../resources/css/JB.css" rel="stylesheet" type="text/css" />
			<link href="../resources/css/jquery.megamenu.css" rel="stylesheet" type="text/css" />
			<link href="../resources/css/SliderStyles.css" rel="stylesheet" type="text/css">
		
			<!--[if IE]>
				<link href="stylesheets/ie.css" rel="stylesheet" type="text/css">
			<![endif]-->

       	    <!-- JAVASCRIPT FILES -->
			<script type="text/javascript" src="../resources/js/jquery.cycle.all.min.js"></script>
			<script type="text/javascript" src="../resources/js/slider.js"></script>
			<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>
		
			<script type="text/javascript">
		    	jQuery(document).ready(function(){
				$("#forgrtpassword").displaypopup("#forgrtpassword","775","252");
		   		jQuery(".megamenu").megamenu();
			});
		   </script>
			
		
     </head>
    
    <body class="job_board_home">
        <div class="ad_page_top">
			<img src="../resources/images/ads/banner_ad_fpo.png" />
        </div>
        <div class="main_wrapper_outside">
        <div class="main_wrapper_inside">


            <div class="main">

				<div class="header_wrapper">

					 <a href="">
          <div class="logo"></div>
          </a>
					<div class="headerLoginSection">
					<div class="headerLoginSectionColumns width205">
					<span class="boldText">Job Seeker:</span><br>
		  <div class="PopUpToolTip"><a href="#">Why <strong>advance</strong>?</a>
          <span class="classic01">
         	<p class="FontWeight marginBottom10">When you sign up, ADVANCE gives you:</p>
            <div class="FontWeight FontSize12 OrangeDot FontBlack">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Access to thousands of healthcare job opportunities
            </div>
            <div class="FontWeight FontSize12 OrangeDot FontBlack">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The best healthcare content you can get anywhere
            </div>
            <div class="FontWeight FontSize12 OrangeDot FontBlack">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hours of informative and entertaining multimedia
            </div>
            <div class="FontWeight FontSize12 OrangeDot FontBlack">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The latest news, articles, product reviews and much more!
            </div>
            <p class=" marginTop10">And it's all FREE!</p>
          </span>
          </div> <div class="floatleft"><span> <a href="">Login</a> | <a href="">Sign Up</a> | </span></div></div><!-- loginHeader -->
                   <!-- <div class="headerLoginSectionColumns">
					<span class="boldText">Employer:</span><br>
                    	<a href="">Login</a> | <a href="">Post Jobs</a>
					</div>loginHeader
					<div class="headerLoginSectionColumns">
					<span class="boldText">Ad Agency:</span><br>
						 <a href="">Login</a> | <a href="">Post Jobs</a>
					</div>loginHeader -->
                    </div><!-- loginHeader --> 
					<!-- loginHeader -->

				</div><!-- header_wrapper -->

				<div id="nav">
					<ul class="megamenu">
					  <li>
						<a href="javascript:">Magazines</a>
						<div class="megamenuContainer">
						  <div class="column">
							<a href="http://nursing.advanceweb.com/"><p>Nurses</p></a>
							<a href="http://physical-therapy.advanceweb.com/"><p>Physical Therapy and Rehab Medicine</p></a>
							<a href="http://occupational-therapy.advanceweb.com/"><p>Occupational Therapy Practitioners</p></a>
							<a href="http://imaging-radiation-oncology.advanceweb.com/"><p>Imaging & Radiattion Oncology</p></a>
							<a href="http://audiology.advanceweb.com/"><p>Hearing Practice Management</p></a>
						  </div>
						  <div class="column">
							<a href="http://speech-language-pathology-audiology.advanceweb.com/"><p>Speech-Language Pathologists & Audiologists</p></a>
							<a href="http://respiratory-care-sleep-medicine.advanceweb.com/"><p>Respiratory Care and Sleep Medicine</p></a>
							<a href="http://laboratory-manager.advanceweb.com/"><p>Administrators of the Laboratory</p></a>
							<a href="http://laboratorian.advanceweb.com/"><p>Medical Laboratory Professionals</p></a>
							<a href="http://health-information.advanceweb.com/"><p>Health Information Professionals</p></a>
						  </div>
						  <div class="column">
							<a href="http://long-term-care.advanceweb.com/"><p>Long-Term Care Management</p></a>
							<a href="http://nurse-practitioners-and-physician-assistants.advanceweb.com/"><p>NPs & PAs</p></a>
							<a href="http://healthcare-executive-insight.advanceweb.com/"><p>Executive Insight</p></a>
						  </div>
						</div>
					  </li>
					  <li>
						<a href="javascript:">Job Search</a>
						<div class="megamenuContainer">
							<div class="column">
								<a href="http://health-care-jobs.advanceweb.com/"><p>Quick Search</p></a>
								<a href="http://health-care-jobs.advanceweb.com/ResumeBuilder/Default.aspx"><p>Resume Builder</p></a>
								<a href="http://health-care-jobs.advanceweb.com/Salary/Default.aspx"><p>Salary Calculator</p></a>
								<a href="http://health-care-jobs.advanceweb.com/AdvanceMessenger/Default.aspx"><p><i>ADVANCE</i> Messenger</p></a>
								<a href="http://health-care-jobs.advanceweb.com/careers/article.aspx?cc=251059"><p>Career Resource Center</p></a>
								<a href="http://health-care-jobs.advanceweb.com/FeaturedFacilities/Default.aspx"><p>Featured Facilities</p></a>
								<a href="http://health-care-jobs.advanceweb.com/Default.aspx"><p>Home</p></a>
							</div>
						</div>
					  </li>
					  <li>
						<a href="javascript:">Education</a>
					  </li>
					  <li>
						<a href="javascript:">Events</a>
					  </li>
					  
					  <li>
						<a href="javascript:">Community</a>
					  </li>
					  <li>
						<a href="javascript:">Healthcare Shop</a>
					  </li>
					  <li>
						<a href="javascript:">Custom Promotions</a>
					  </li>
					</ul>
				</div><!--nav-->

				<div class="ad_col_right">
                    <img src="../resources/images/ads/300x250ad1.png" class="paddingBottom0" />

		    <br class="clearfix" />

                </div><!-- ad_col_right -->

                <div class="content_wrapper">

		    <div class="job_seeker_login">
			<h2 class="noTopBottomBorder">Login</h2>
			<div style="color: red" align="right"><b>${message}</b></div>
			
			<form:form method="Post" action="jobSeekerLogin.html"  commandName="loginForm" >
			    <div class="rowEvenSpacing marginTop0"><span class="lableText1">Email Address:</span> 
					<form:input type="text" path="emailAddress"  class="job_seeker_email" />
					<FONT color="red"><form:errors path="emailAddress" align="right"/></FONT>
			    </div>
			    
			    <div class="rowEvenSpacing"><span class="lableText1">Password:</span> 
			    	<form:input type="password" path="password"  class="job_seeker_password" />
			    	<FONT color="red"><form:errors path="password" align="right"/></FONT>
			    </div>
                
			    <div class="loginOptions">
				<div class="rowEvenSpacing marginTopBottom10">
				    <input type="checkbox" value="stayLoggedIn" /> Stay Logged In<br />
				</div>		    
				<div class="rowEvenSpacing">				    
				    <span class="floatLeft"><input type="submit" class="btn_sm orange" value="Login" /></span> 
                    <span class="floatLeft"><a href="forgrtPasswordLogin.html" id="forgrtpassword" >Forgot your password?</a></span>
				</div>
				<div class="rowEvenSpacing">
				    <p><span class="bold">Not a member?</span> <a href="/jobboard/jobseekerregistration/createJobSeekerCreateYrAcct.html">Sign up now!</a></p>
				</div>
				<div class="rowEvenSpacing marginTopBottom10">
			      <c:if test="${postjobfeatures}" >
			      <span class="bold">Or </span>
			       <a href="#">continue as a guest.</a>
			      </c:if>
			    </div>
                  <div class="clearfix"></div>
			    </div>
			</form:form>
		    </div>
		    <div class="job_seeker_social_login">
			<p class="bold lableLetterSpacer">Login with your social networking account</p>
			<a href=""><img src="../resources/images/loginFB.png" alt="Login with Facebook" /></a>
			<a href=""><img src="../resources/images/loginIN.png" alt="Login with LinkedIn" /></a>
		    </div>
		    
		    <br class="clearfix" />

                </div><!-- content_wrapper -->
			<div class="clearfix"></div>
                <div class="ad_wrapper marginTop0">
					<img src="../resources/images/ads/banner_ad_fpo.png" />
                </div><!-- ad_wrapper -->

            </div><!-- main -->

        </div> <!-- end main_wrapper_inside -->   
        </div> <!-- end main_wrapper_outside -->
			<div class="footer_wrapper">
            
            <div class="container1">
            <h4>Professions:</h4>
             <ul>
              <li><a href="#">Nursing</a></li>
              <li><a href="#">Imaging & Radiation Oncology</a></li>
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
            </div><!-- end container1 -->

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
            </div><!-- end container2 -->

            <div class="container3">
            <h4>Services:</h4>
            <ul>
            <li><a href="#">ADVANCE Healthcare Jobs</a></li>
            <li><a href="#">Subscribe</a></li>
              <li><img src="../resources/images/email.png" class="foot_icon"/><a href="#">Sign Up for E-Newsletter</a></li>

            <li><img src="../resources/images/fbook_sm.png" class="foot_icon"/><a href="#">Connect on Facebook</a></li>

            <li><img src="../resources/images/L_In_sm.png" class="foot_icon"/><a href="#">Connect on LinkedIn</a></li>

            <li><img src="../resources/images/twitter_sm.png" class="foot_icon"/><a href="#">Connect on Twitter</a></li>

            <li><a href="#">Download the Mobile App</a></li>
			<li><a href="#">Order Promotional Items</a></li>
            </ul>
            </div><!-- end container3 -->
			

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
			</div><!-- end container4 -->

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
            </div><!-- end container5 -->

<br class="clearfix" />
			<p class="copyright">&copy; 2012 Merion Matters 2900 Horizon Drive King of Prussia PA 19406 800-355-5627</p>

        </div><!-- footer_wrapper -->
    </body>
</html>