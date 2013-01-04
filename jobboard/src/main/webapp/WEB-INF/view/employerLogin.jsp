	
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
<meta name="robots" content="noindex, follow"> 
<title>ADVANCE Heathcare Jobs</title>

<jsp:include page="common/include.jsp" />
<script type="text/javascript">
	jQuery(document).ready(function() {
		$('#j_username').focus();
		
		$("#forgrtpassword").displaypopup("#forgrtpassword", "790", "252");
		jQuery(".megamenu").megamenu();
		
		$("#loginButton")
		.click(
				function(event) {
					if (validate()) {
						$("form").attr("action","../j_spring_security_check");
						$("#loginForm").submit();
					}
				});
		});
	
	function validate() {
		var userName = $.trim($("#j_username").val());
		var userPassword = $.trim($("#j_password").val());
		var x = userName.indexOf('@');
		var y = userName.lastIndexOf('.');
		var result = true;
		if (userName.length == 0) {
			$("#error").text("The Username/Password you have entered is invalid, please enter the correct Username/Password");
			$("#error").show();
			$("#error1").hide();
			result = false;
		} 
		
		if(userPassword.length == 0){
			$("#error").text("The Username/Password you have entered is invalid, please enter the correct Username/Password");
			$("#error").show();
			$("#error1").hide();
			result = false;
		}
		if (x == -1 || y == -1 || (x + 2) >= y) {
			$("#error")
					.text("The Username/Password you have entered is invalid, please enter the correct Username/Password");
			$("#error").show();
			$("#error1").hide();
			result = false;
		} 
		
		if (!result) {
			return false;
		} else {
			return true;
		}
	}
</script>			
		
    </head>
    
    <body class="job_board_home">
        <div class="ad_page_top">
			${adPageTop }
        </div>
        <div class="main_wrapper_outside">
        <div class="main_wrapper_inside">


            <div class="main">
			<jsp:include page="../templates/templates_header.jsp"></jsp:include>
		    <div class="ad_col_right">
                   <div id="adPageRightTop"> ${adPageRightTop} </div>

		    <br class="clearfix" />

                </div><!-- ad_col_right -->

                <div class="content_wrapper">

		    <div class="job_seeker_login">
			<h2 class="noTopBottomBorder">Employer Login</h2>
			<div class="FormErrorDisplayText">
			<div id="error1">${error}</div>
			<div id="error" style=" display: none"></div>
			</div>
			
			<form method="post" id="loginForm" action="../j_spring_security_check">
			    <div class="rowEvenSpacingMargin0">
				<span class="lableText1">Email Address:</span> <input type="text" id="j_username" name="j_username" class="job_seeker_email" />
			    </div>
			    <div class="rowEvenNewSpacing">
				<span class="lableText1">Password:</span> <input id="j_password" name="j_password" type="password" class="job_seeker_password" />
			    </div>
                <input type="hidden" name="pageValue" value="employer"/>
			    <div class="loginOptions">
				<div class="rowEvenNewSpacing">
				    <input type="checkbox" name="_spring_security_remember_me"
										id="_spring_security_remember_me" /> Stay Logged In<br />
				</div>		    
				<div class="rowEvenNewSpacing">
							
				    	<input type="button" id="loginButton" class="orange cursor" value="Login"/>				    
										
                    	<a href="forgrtPasswordLogin.html?page=employer" id="forgrtpassword">Forgot your password?</a>
			
				</div>
				<div class="row">
				    <p><span class="bold">Not a member?</span> <a href="<%=request.getContextPath()%>/employerRegistration/employerregistration.html">Sign up now!</a></p>
				</div>
				<div class="clearfix"></div>
			    </div>
			</form>
				    </div>
		     <div class="job_seeker_social_login">
		     <p class="bold lableLetterSpacer">Login with your social networking account</p>
		    <form action="<c:url value="/signin/facebook.html"/>" method="POST">
		    <input type="hidden" name="pageValue" value="employer"/>
		    <input type="hidden" name="scope" value="email">
		     <button type="submit" class="faceBooklogin cursor"></button>
		    </form>
		     <form action="<c:url value="/signin/linkedin.html"/>" method="POST">
		    <input type="hidden" name="pageValue" value="employer"/>
		    <input type="hidden" name="scope" value="email">
		    <button type="submit" class="linkedInLogin cursor"></button>
		    </form>
		    <div style="color: red" align="left" class="row">
			${socialLoginError}
			</div>
		     </div>
            <br class="clearfix" />

                </div><!-- content_wrapper -->

                <div class="ad_wrapper marginTop0">
					${adPageBottom }
                </div><!-- ad_wrapper -->

            </div><!-- main -->

        </div> <!-- end main_wrapper_inside -->   
        </div> <!-- end main_wrapper_outside -->
			<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
			<!-- footer_wrapper -->

    </body>
</html>