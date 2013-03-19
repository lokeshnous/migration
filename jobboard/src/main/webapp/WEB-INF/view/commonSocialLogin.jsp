<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>ADVANCE Heathcare Jobs</title>
	<jsp:include page="common/include.jsp" />
		<script type="text/javascript">
		    jQuery(document).ready(function(){
		    $("#loginButton").click(function() {
							if (validate()) {
								$("form").attr("action","${pageContext.request.contextPath}/signin/verifyUserAccount.html");
								$("#loginForm").submit();
							}
						});
		    jQuery(".megamenu").megamenu();
		    $("#signInButton").click(function() {
		    	 $("#commonPage").hide();
		    	 $("#loginPage").show();
		    	 $("#errorDiv").hide();
		    	 if ($("#pageValue").val() == 'employer') {
		    		 $("#jsLogin").hide();
		    		 $("#employerLogin").show();
		    		 $("#agencyLogin").hide();
		    	 }
		    	 if ($("#pageValue").val() == 'jobSeeker') {
		    		 $("#jsLogin").show();
		    		 $("#employerLogin").hide();
		    		 $("#agencyLogin").hide();
		    	 }
		    	 if ($("#pageValue").val() == 'agency') {
		    		 $("#jsLogin").hide();
		    		 $("#employerLogin").hide();
		    		 $("#agencyLogin").show();
		    	 }
		    });
		    $("#back").click(function() {
		    	$("#j_username").val('');
		    	$("#j_password").val('');
		    	$("#errorMsgDiv").hide();
		    	$("#loginPage").hide();
				$("#commonPage").show();
				$("#jsLogin").hide();
	    		 $("#employerLogin").hide();
	    		 $("#agencyLogin").hide();
		    });
		   
				$("#loginPage").hide();
				$("#jsLogin").hide();
	    		 $("#employerLogin").hide();
	    		 $("#agencyLogin").hide();
								if ($("#error").val() == 'true') {
									$("#j_username").val('');
							    	$("#j_password").val('');
									$("#loginPage").show();
									$("#commonPage").hide();
									if ($("#pageValue").val() == 'employer') {
							    		 $("#jsLogin").hide();
							    		 $("#employerLogin").show();
							    		 $("#agencyLogin").hide();
							    	 }
							    	 if ($("#pageValue").val() == 'jobSeeker') {
							    		 $("#jsLogin").show();
							    		 $("#employerLogin").hide();
							    		 $("#agencyLogin").hide();
							    	 }
							    	 if ($("#pageValue").val() == 'agency') {
							    		 $("#jsLogin").hide();
							    		 $("#employerLogin").hide();
							    		 $("#agencyLogin").show();
							    	 }
								}

								if ($("#pageValue").val() == 'employer') {
									$("#signUpLink")
											.attr(
													"href",
													"${pageContext.request.contextPath}/employerreg/employerregistration.html?profileId="
															+ $("#profileId")
																	.val()
															+ "&serviceProviderId="
															+ $(
																	"#serviceProviderId")
																	.val());
								}
								if ($("#pageValue").val() == 'jobSeeker') {
									$("#signUpLink")
											.attr(
													"href",
													"${pageContext.request.contextPath}/jobseekerregistration/createjobseekercreateyracct.html?profileId="
															+ $("#profileId")
																	.val()
															+ "&serviceProviderId="
															+ $(
																	"#serviceProviderId")
																	.val());
								}
								if ($("#pageValue").val() == 'agency') {
									$("#signUpLink")
											.attr(
													"href",
													"${pageContext.request.contextPath}/agencyreg/agencyregistration.html?profileId="
															+ $("#profileId")
																	.val()
															+ "&serviceProviderId="
															+ $(
																	"#serviceProviderId")
																	.val());
								}
								
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


		<form:form commandName="socialLoginForm" id="loginForm" action="verifyUserAccount.html">
                <div class="alignCenter">
              <h2 class="noTopBottomBorder" id="jsLogin" align="center">Job Seeker Login</h2>
			<h2 class="noTopBottomBorder" id="employerLogin" align="center">Employer Login</h2>
			<h2 class="noTopBottomBorder" id="agencyLogin" align="center">Ad Agency Login</h2>
			<div  id="commonPage" class="job_seeker_login">
            <h2 class="noTopBottomBorder">Already have a Job Board ID? </h2>
	                      <form:hidden path="profileId"/>
	                      <form:hidden path="pageValue"/>
	                      <form:hidden path="serviceProviderId"/>
	            		  <form:hidden path="error"/>
            <span ><input type="button" value="Sign in to connect" id="signInButton" class="btn_sm white"/></span><br>

            <h4 class="noTopBottomBorder marginTop10">or</h4>
            <h2 class="noTopBottomBorder"><a id="signUpLink" href="#">Click here to sign up.... </a> </h2> 
            </div>
		    <div id="loginPage" class="job_seeker_login width400 ">
		    
		    <div id="errorDiv">
								<FONT color="red" > <c:if
										test="${not empty errorMessage}">
										<div id="errmsg" style="color: red" >
											<c:out value="${errorMessage}"></c:out>
										</div>
									</c:if>
								</FONT>
							</div>
							<div id="errorMsgDiv" class="FormErrorDisplayText" style=" display: none"></div>
			<div class="row"><h2 class="noTopBottomBorder">Already have a Job Board ID? </h2></div>
			<p class="row">Enter your Email Address and Password.</p><br/>
			
			    <div class="rowEvenSpacingMargin0">
				<span class="lableText1">Email Address:</span> 
				<form:input path="emailId" id="j_username" class="job_seeker_email" />
			    </div>
			    <div class="rowEvenNewSpacing">
				<span class="lableText1">Password:</span> <form:password
											path="password" id="j_password" class="job_seeker_password" />
			    </div>
                
			    <div class="loginOptions">
				<div class="rowEvenSpacing marginTopBottom10">
				   <!--  <input type="checkbox" value="stayLoggedIn" /> Stay Logged In<br /> -->
				</div>		    
				<div class="rowEvenNewSpacing marginTop15">
				 <span class="floatLeft"><input type="button" id="loginButton" class="orange" value="Login"/></span>
				  <span class="floatLeft"><input type="button" id="back" class="orange" value="Back"/></span>
				</div>
				<div class="clearfix"></div>
			    </div>
			
		    </div>
		    <br class="clearfix" />

                </div>
                </form:form><!-- content_wrapper -->
<div class="clearfix"></div>
            </div><!-- main -->

        </div> <!-- end main_wrapper_inside -->   
        </div> <!-- end main_wrapper_outside -->
    </body>
</html>