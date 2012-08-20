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
              <!-- <div class="row">

        <div class="header_wrapper"> <a href="">
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
          </div> <div class="floatleft"><span> <a href="">Login</a> | <a href="">Sign Up</a> | </span></div></div>

            loginHeader
            <div class="headerLoginSectionColumns"> <span class="boldText">Employer:</span><br>
                      <a href="">Login</a> | <a href="">Post Jobs</a> </div>
            loginHeader
            <div class="headerLoginSectionColumns"> <span class="boldText">Ad Agency:</span><br>

                      <a href="">Login</a> | <a href="">Post Jobs</a> </div>
            loginHeader 
          </div>
                  loginHeader 
                  loginHeader 
                  
                </div>
      </div> -->
              <!-- header_wrapper -->

              <jsp:include page="../templates/templates_header.jsp"></jsp:include>
       <div class="row"> 
        <!-- Step 1 -->

        <div id="jobSeekerRegister1" class="job_seeker_login leftFormHolder" style="display:block">
           <h2 class="sectionSubHeader">Step 1: Create Your Account</h2>
           <form:form method="Post" action="createJobSeekerYourInfo.html" commandName="registerForm" enctype="multipart/form-data">
	            <div class="rowEvenSpacingMargin0"> <span class="lableText3">Email Address:</span>
	                      <form:input path="emailId" class="job_seeker_password textBox350"/>
	                      <span class="required">(Required)</span> 
	            </div>
				<div>
				<span class="lableText3"></span>
					<FONT color="red"><form:errors path="emailId" /></FONT> 
				</div>
	            <div class="rowEvenNewSpacing"> <span class="lableText3">Confirm Email Address:</span>
	                      <form:input path="confirmEmailId" class="job_seeker_password textBox350"/>
	                      <span class="required">(Required)</span> 
	            </div>
	            <div>
				<span class="lableText3"></span>
					<FONT color="red"><form:errors path="confirmEmailId" /></FONT> 
				</div>         
	            <div class="rowEvenNewSpacing"> <span class="lableText3">Password:</span>
	                      <form:password path="password" class="job_seeker_password textBox350"/>
	                      <span class="required">(Required)</span> 
	            </div>
				<div>
				<span class="lableText3"></span>
					<FONT color="red"><form:errors path="password" /></FONT> 
				</div>
	            <div class="row marginTop5"> <span class="lableText3"></span> (8-20 characters, including at least 1 number)</div>
	            <div class="rowEvenNewSpacing"> <span class="lableText3">Confirm Password:</span>
	            		  <form:password path="retypepassword" class="job_seeker_password textBox350" />
	                      <span class="required">(Required)</span> </div>
	            <div>
				<span class="lableText3"></span>
					<FONT color="red"><form:errors path="retypepassword" /></FONT> 
				</div>
	            <%-- <div class="row marginTop15"> <span class="lableText3">&nbsp;</span> <img src="../resources/images/SecureText.jpg" width="294" height="48" alt=""> </div>
	
	           <div class="rowEvenNewSpacing"> <span class="lableText3">Type the two words:</span>
	                       <form:password path="retypepassword" class="job_seeker_password textBox350" /> 
	                       <form:errors path="retypepassword" /> 
	                      <span class="required">(Required)</span> </div>--%>
				<div class="popUpButtonRow">
				
	               <input type="submit" value="Next" class="btn_sm orange"  name="Next"/>
	               <a href="/jobboard/healthcarejobs/advanceweb.html" class="btn_sm orange">Cancel</a>
	               <!-- <input type="submit" value="Cancel" class="btn_sm orange"  name="Cancel"/> -->  
	            </div>
	            <div class="clearfix"></div>
          </form:form>
          <div class="clearfix"></div>
          </div>
        <!-- Step 2 -->
        
        <div class="ad_col_right"> <img src="../resources/images/ads/300x250ad1.png" /> <br class="clearfix" />
                </div>
      </div>

              <div class="clearfix"></div>
              <div class="ad_wrapper"> <img src="../resources/images/ads/banner_ad_fpo.png" /> </div>
            </div>
    <!-- main --> 
    
  </div>
          <!-- end main_wrapper_inside --> 
        </div>
<!-- end main_wrapper_outside -->
	<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
</body>
</html>