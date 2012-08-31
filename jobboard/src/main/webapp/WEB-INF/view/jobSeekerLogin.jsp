<%-- <%@ page isELIgnored="false"%> --%>
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
		$("#guestUser").displaypopup("#guestUser", "775",
		"355");
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
			<h2 class="noTopBottomBorder">Login</h2>
			<div class="FormErrorDisplayText">
			${error}<br /><br />
			</div>
			<form method="post" action="../j_spring_security_check">
			    <div class="rowEvenSpacingMargin0">
				<span class="lableText1">Email Address:</span> <input type="text" id="j_username" name="j_username" class="job_seeker_email" />
			    </div>
			    <div class="rowEvenNewSpacing">
				<span class="lableText1">Password:</span> <input id="j_password" name="j_password" type="password" class="job_seeker_password" />
			    </div>
                <input type="hidden" name="pageValue" value="jobSeeker"/>
			    <div class="loginOptions">
				<div class="rowEvenNewSpacing">
				    <input type="checkbox" name='_spring_security_remember_me'
										id="_spring_security_remember_me"  value="stayLoggedIn" /> Stay Logged In<br />
				</div>		    
				<div class="rowEvenNewSpacing">
							
				    	<input type="submit" class="orange" value="Login"/>				    
										
                    	<a href="forgrtPasswordLogin.html?page=jobSeeker" id="forgrtpassword">Forgot your password?</a>
			
				</div>
				<div class="row">
				    <p><span class="bold">Not a member?</span> <a href="<%=request.getContextPath()%>/jobseekerregistration/createJobSeekerCreateYrAcct.html">Sign up now!</a></p>
				</div>
				<c:if test="<%=session.getAttribute(\"jobId\")!=null%>">
				<div class="rowEvenNewSpacing ">
			      <span class="bold">Or </span> <a id="guestUser" class="nyroModal"  href="<%=request.getContextPath()%>/anonymoususerjobapply/anonymousUser.html">continue as a guest.</a>
			     </div>
			     </c:if>
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
<div class="clearfix"></div>
                <div class="ad_wrapper marginTop0">
					<img src="../resources/images/ads/banner_ad_fpo.png" />
                </div><!-- ad_wrapper -->

            </div><!-- main -->

        </div> <!-- end main_wrapper_inside -->   
        </div> <!-- end main_wrapper_outside -->
	<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
    </body>
</html>