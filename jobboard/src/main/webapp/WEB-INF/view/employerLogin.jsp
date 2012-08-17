<!-- <!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>ADVANCE Heathcare Jobs</title>

	STYLESHEETS
        <link href="stylesheets/JB.css" rel="stylesheet" type="text/css" />
		<link href="stylesheets/jquery.megamenu.css" rel="stylesheet" type="text/css" />
		<link href="stylesheets/SliderStyles.css" rel="stylesheet" type="text/css" />
		
[if IE]>
	<link href="stylesheets/ie.css" rel="stylesheet" type="text/css">
<![endif]
		
	
	
        JAVASCRIPT FILES
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
		<script type="text/javascript" src="javascripts/jquery.cycle.all.min.js"></script>
		<script type="text/javascript" src="javascripts/slider.js"></script>
		<script type="text/javascript" src="javascripts/jquery.megamenu.js"></script>
		<script type="text/javascript">
		    jQuery(document).ready(function(){
		    jQuery(".megamenu").megamenu();
		});
		</script> -->
			
			
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="true"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>

<jsp:include page="common/include.jsp" />
<script type="text/javascript">
	jQuery(document).ready(function() {
		$("#forgrtpassword").displaypopup("#forgrtpassword", "775", "252");
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
			<jsp:include page="../templates/templates_header.jsp"></jsp:include>
		    <div class="ad_col_right">
                    <img src="../resources/images/ads/300x250ad1.png" class="paddingBottom0" />

		    <br class="clearfix" />

                </div><!-- ad_col_right -->

                <div class="content_wrapper">

		    <div class="job_seeker_login">
			<h2 class="noTopBottomBorder">Employer Login</h2>
			<div style="color: red" align="left">
			<b>${error}</b>
			</div>
			<form method="post" action="../j_spring_security_check">
			    <div class="rowEvenSpacing marginTop0">
				<span class="lableText1">Email Address:</span> <input type="text" id="j_username" name="j_username" class="job_seeker_email" />
			    </div>
			    <div class="rowEvenSpacing">
				<span class="lableText1">Password:</span> <input id="j_password" name="j_password" type="password" class="job_seeker_password" />
			    </div>
			    <input type="hidden" name="pageValue" value="employer"/>
			    <div class="loginOptions">
				<div class="rowEvenSpacing marginTopBottom10">
				    <input type="checkbox" name='_spring_security_remember_me'
										id="_spring_security_remember_me"  value="stayLoggedIn"/> Stay Logged In<br />
				</div>		    
				<div class="rowEvenSpacing">
				    <!-- <span class="floatLeft"><a href="" class="btn_sm orange">Login</a></span>
                    <span class="floatLeft"><a href="#">Forgot your password?</a></span> -->
                <table>
				<tr>
					<td>					
				    	<input type="submit" class="btn_sm orange" value="Login"/>				    
					</td>
					<td>
											
                    	<a href="forgrtPasswordLogin.html" id="forgrtpassword">Forgot your password?</a>
					</td>
				</tr>	
				</table>
				</div>
				<div class="rowEvenSpacing">
				    <p><span class="bold">Not a member?</span> <a href="">Sign up now!</a></p>
				</div>
                 <div class="clearfix"></div>
			    </div>
			</form>
		    </div>
            <div class="job_seeker_social_login">
			<p class="bold lableLetterSpacer">Login with your social networking account</p>
			<a href=""><img src="../resources/images/loginFB.png" alt="Login with Facebook" /></a>
			<a href=""><img src="../resources/images/loginIN.png" alt="Login with LinkedIn" /></a>
		    </div>
            <br class="clearfix" />

                </div><!-- content_wrapper -->

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