<!DOCTYPE html>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.advanceweb.afc.jb.common.util.MMJBCommonConstants"%>
<html lang="en">
		<head>

		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="robots" content="noindex, follow">
		<title>ADVANCE Heathcare Jobs</title>


		<!-- JAVASCRIPT FILES -->
		<jsp:include page="common/include.jsp" />
		<script src="../resources/js/recaptcha_ajax.js"></script>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
		<script type="text/javascript">
		    jQuery(document).ready(function(){
		    $('.focus').focus();
		    jQuery(".megamenu").megamenu();
		});
		    function cancelProcess(){
		    	window.location.href = '${pageContext.request.contextPath}/healthcare/index.html';
		    }	
		    
		    /*var RecaptchaOptions = {
		    	    theme : 'clean'
		   	};*/
		</script>
		</head>
		
	<body class="job_board">
	<div class="ad_page_top"> 
	${adPageTop }
	</div>
	<div class="main_wrapper_outside">
    <div class="main_wrapper_inside">
    <div class="main">

              <jsp:include page="../templates/templates_header.jsp"></jsp:include>
       <div class="row"> 
        <!-- Step 1 -->

        <div id="jobSeekerRegister1" class="job_seeker_login leftFormHolder" style="display:block">
           <h2 class="sectionSubHeader">Step 1: Create Your Account</h2>
           <div>
           <div style="flot:left;">
							<img src="<%=request.getContextPath()%>/resources/images/advancePass.png" style="margin:0px;"/>
							</div>
								<span class="lableText3"></span> <FONT color="red"> <c:if
										test="${not empty socialSignUpMsg}">
										<div id="errmsg" style="margin-bottom:8px; color: red" align="left">
											<c:out value="${socialSignUpMsg}"></c:out>
										</div>
									</c:if>
								</FONT>
							</div>
           <form:form method="POST" action="createJobSeekerYourInfo.html" commandName="registerForm" enctype="multipart/form-data">
           
           	
	            <div class="rowEvenSpacingMargin0"> <span class="lableText3">Email Address:</span>
	                      <form:input path="emailId" class="job_seeker_password textBox350 focus" readonly="${registerForm.bReadOnly}"/>
	                      <span class="required">(Required)</span> 
	            </div>
				<div class="validationMsg">
					<form:errors path="emailId" htmlEscape="false"/> 
				</div>
	            <div class="rowEvenNewSpacing"> <span class="lableText3">Confirm Email Address:</span>
	                      <form:input path="confirmEmailId" class="job_seeker_password textBox350" readonly="${registerForm.bReadOnly}"/>
	                      <span class="required">(Required)</span> 
	            </div>
	            <div class="validationMsg">				
					<form:errors path="confirmEmailId"/>
				</div>         
	            <div class="rowEvenNewSpacing"> <span class="lableText3">Password:</span>
	                      <form:password path="password" class="job_seeker_password textBox350" readonly="${registerForm.bReadOnly}" showPassword="true"/>
	                      <span class="required">(Required)</span> 
	            </div>
				<div class="validationMsg">
					<form:errors path="password" /> 
				</div>
	            <div class="row marginTop5"> <span class="lableText3"></span> (8-20 characters, including at least 1 number)</div>
	            <div class="rowEvenNewSpacing"> <span class="lableText3">Confirm Password:</span>
	            		  <form:password path="retypepassword" class="job_seeker_password textBox350" readonly="${registerForm.bReadOnly}" showPassword="true"/>
	                      <span class="required">(Required)</span> </div>
	            <div class="validationMsg">
					<form:errors path="retypepassword" /> 
				</div>

							<div class="row marginTop5">

								<div class="width526"> 
								<%
									String pubKey = MMJBCommonConstants.PUBLIC_KEY;
										String privKey = MMJBCommonConstants.PRIVATE_KEY;
										/* ReCaptcha c = ReCaptchaFactory.newReCaptcha("ADD-YOUR-PUBLIC-KEY-HERE", "ADD-YOUR-PRIVATE-KEY-HERE", false); */
										ReCaptcha c = ReCaptchaFactory.newReCaptcha(pubKey, privKey,
												false);
										out.print(c.createRecaptchaHtml(null, null));
								%>
								</div>
									<!-- <span class="required">(Required)</span> -->
							</div>
								<div class="row">
									<span class="lableText3"></span> <FONT color="red"> <c:if
											test="${not empty errorMessage}">
											<div id="errmsg" style="color: red" align="left">
												<c:out value="${errorMessage}"></c:out>
											</div>
										</c:if>
									</FONT>
								</div>
							<div>
				 	<c:out value=""></c:out>
				 </div>
				
				<div class="popUpButtonRow">
				
	               <input type="submit" value="Next" class="orange cursor"  name="Next"/>
	               <c:if test="${registerForm.bReadOnly == false}">
		               <input type="button" value="Cancel" onclick="cancelProcess()" 
										class="orange cursor" name="Cancel" />
	               </c:if>
	            </div>
	            <div class="clearfix"></div>
          </form:form>
          <div class="clearfix"></div>
          </div>
        <!-- Step 2 -->
        
        <div class="ad_col_right"> 
       <div id="adPageRightTop"> ${adPageRightTop} </div>
        <br class="clearfix" />
                </div>
      </div>

              <div class="clearfix"></div>
              <div class="ad_wrapper">
               ${adPageBottom } 
               </div>
            </div>
    <!-- main --> 
    
  </div>
          <!-- end main_wrapper_inside --> 
        </div>
<!-- end main_wrapper_outside -->
	<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
</body>
</html>