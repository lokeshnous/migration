<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
    <head>
        <link rel='shortcut icon' href='<%=request.getContextPath()%>/resources/images/favicon.ico' type="image/x-icon">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>ADVANCE Heathcare Jobs</title>
	<jsp:include page="common/include.jsp" />
		<script type="text/javascript">
		    jQuery(document).ready(function(){
		    	$('#j_username').focus();
		   
							});
		    
		    function validate() {
				var userName = $.trim($("#j_username").val());
				var userPassword = $.trim($("#j_password").val());
				var x = userName.indexOf('@');
				var y = userName.lastIndexOf('.');
				var result = true;
				if (userName.length == 0) {
					$("#errorMsgDiv").text("The information you entered was invalid. Please try again.");
					$("#errorMsgDiv").show();
					$("#errorDiv").hide();
					result = false;
				} 
				
				if(userPassword.length == 0){
					$("#errorMsgDiv").text("The information you entered was invalid. Please try again.");
					$("#errorMsgDiv").show();
					$("#errorDiv").hide();
					result = false;
				}
				if (x == -1 || y == -1 || (x + 2) >= y) {
					$("#errorMsgDiv")
							.text("The information you entered was invalid. Please try again.");
					$("#errorMsgDiv").show();
					$("#errorDiv").hide();
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
        <div class="main_wrapper_outside">
        <div class="main_wrapper_inside">


            <div class="main">

				<div class="header_wrapper">
				<div class="socialLoginLogo"><a href="<%=request.getContextPath()%>/healthcare/index.html">
          <div class="logo"></div>
          </a></div> 

				</div><!-- header_wrapper -->


		<form style="align:center" method="post" id="loginForm" action="../admin/authenticateAdmin.html" onsubmit="return validate();">
		    <div id="loginPage" class="job_seeker_login width526 ">
		    <div style="width:70%; float:left;">
		    <div id="errorDiv" class="FormErrorDisplayText paddingBottom10">
								${error}
							</div>
							<div id="errorMsgDiv" class="FormErrorDisplayText" style=" display: none"></div>
			<div class="row"><h2 class="noTopBottomBorder">Admin Login</h2></div>
			<br/>
			
			<p class="row">Enter your Email Address and Password.</p><br/><br/><br/>
			
			    <div class="rowEvenSpacingMargin0">
				<span class="lableText1">Email Address:</span> 
				<input type="text" id="j_username" name="j_username" class="job_seeker_email" />
			    </div>
			    <div class="rowEvenNewSpacing">
				<span class="lableText1">Password:</span> <input id="j_password" name="j_password" type="password" class="job_seeker_password" />
			    </div>
                
			    <div class="loginOptions">
				<div class="rowEvenSpacing marginTopBottom10">
				</div>		    
				<div class="rowEvenNewSpacing marginTop15">
				<span></span>
				 <span class="floatLeft"><input type="submit" id="loginButton" class="orange" value="Login"/></span>
				 <!--  <span class="floatLeft"><input type="button" id="back" class="orange" value="Cancel"/></span> -->
				</div>
				</div>
				<div class="clearfix"></div>
			    </div>
			    <div style="width:21%; float:left; margin-top:70px; ">
		    <img src="<%=request.getContextPath()%>/resources/images/advancePass.png"/> </div>
			
		    </div>
		    <br class="clearfix" />
</form>
                </div>
                <!-- content_wrapper -->
<div class="clearfix"></div>
            </div><!-- main -->

        </div> <!-- end main_wrapper_inside -->   
        </div> <!-- end main_wrapper_outside -->
    </body>
</html>