<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>ADVANCE Heathcare Jobs</title>
		<jsp:include page="common/include.jsp" />
		<script type="text/javascript">
		    jQuery(document).ready(function(){
		    jQuery(".megamenu").megamenu();
		    $("#signInButton").click(function() {
		    	$(this).parent().hide();
		    	 $("#loginPage").show();
		    	 $("#errorDiv").hide();
		    });
		    $("#back").click(function() {
		    	$("#loginPage").hide();
				$("#commonPage").show();
		    });
		   
		   /*  if ($("#socialSignUp").val() == 'true') {
		    	
				$("#emailId").attr("readonly", true);
				$("#confirmEmailId").attr("readonly", true); 
	} */
				$("#loginPage").hide();
	
								if ($("#error").val() == 'true') {
									$("#loginPage").show();
									$("#commonPage").hide();
								}

								if ($("#pageValue").val() == 'employer') {
									$("#signUpLink")
											.attr(
													"href",
													"${pageContext.request.contextPath}/employerRegistration/employerregistration.html?profileId="
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
													"${pageContext.request.contextPath}/jobseekerregistration/createJobSeekerCreateYrAcct.html?profileId="
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
													"${pageContext.request.contextPath}/agencyRegistration/agencyregistration.html?profileId="
															+ $("#profileId")
																	.val()
															+ "&serviceProviderId="
															+ $(
																	"#serviceProviderId")
																	.val());
								}
								/* if ($("#socialSignUp").val() == 'true') {
									
									$("#emailId").attr("readonly", true);
									$("#confirmEmailId").attr("readonly", true); 
								 } */
							});
		</script>
</head>
<body>
<form:form commandName="socialLoginForm" action="verifyUserAccount.html">
 <div class="rowEvenSpacingMargin0" id="commonPage"> <span class="lableText3  floatLeft">Already have a Jobboard ID ? </span>
	                      <input type="button" value="Sign in to connect" id="signInButton"/>
	                      <form:hidden path="profileId"/>
	                      <form:hidden path="pageValue"/>
	                      <form:hidden path="serviceProviderId"/>
	            		  <form:hidden path="error"/>
	            <div> <h1>____________ OR __________</h1> </div>
	            <div>
	            <span class="floatLeft"> <a id="signUpLink" href="#">Click here for Sign Up</a></span>
				</div>
				</div>
				<div class="rowEvenSpacingMargin0" id="loginPage">
				<div id="errorDiv">
								<span class="lableText3"></span> <FONT color="red"> <c:if
										test="${not empty errorMessage}">
										<div id="errmsg" style="color: red" align="left">
											<c:out value="${errorMessage}"></c:out>
										</div>
									</c:if>
								</FONT>
							</div>
				<div class="rowEvenSpacingMargin0">
				<span class="lableText1">Email Address:</span> <form:input
											path="emailId"
											class="job_seeker_password textBox350 focus" />
			    </div>
			    <div class="rowEvenNewSpacing">
				<span class="lableText1">Password:</span> <form:input
											path="password"
											class="job_seeker_password textBox350 focus" showPassword="true"/>
			    </div>
                
				<div class="rowEvenNewSpacing">
				    	<input type="submit" class="orange" value="Login"/><input type="button" id="back" class="orange" value="Back"/>		    
				</div>
				
				</div>
</form:form>
</body>
</html>