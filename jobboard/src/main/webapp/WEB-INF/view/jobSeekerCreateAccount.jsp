<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>ADVANCE Heathcare Jobs</title>


		<!-- JAVASCRIPT FILES -->
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
		<jsp:include page="common/include.jsp" />
		<script type="text/javascript">
		    jQuery(document).ready(function(){
		    jQuery(".megamenu").megamenu();
		    $('#emailId').focus();
		});
		</script>
		<script type="text/javascript">
		    function cancelProcess(){
		    	window.location.href = '${pageContext.request.contextPath}/healthcarejobs/advanceweb.html';
		    }	
		    
		    var RecaptchaOptions = {
		    	    theme : 'clean'
		   	};
		</script>
		</head>
		
	<body class="job_board">
	<div class="ad_page_top"> <img src="../resources/images/ads/banner_ad_fpo.png" /> </div>
	<div class="main_wrapper_outside">
    <div class="main_wrapper_inside">
    <div class="main">

              <jsp:include page="../templates/templates_header.jsp"></jsp:include>
       <div class="row"> 
        <!-- Step 1 -->

        <div id="jobSeekerRegister1" class="job_seeker_login leftFormHolder" style="display:block">
           <h2 class="sectionSubHeader">Step 1: Create Your Account</h2>
           <form:form method="POST" action="createJobSeekerYourInfo.html" commandName="registerForm" enctype="multipart/form-data">
           
           	
	            <div class="rowEvenSpacingMargin0"> <span class="lableText3">Email Address:</span>
	                      <form:input path="emailId" class="job_seeker_password textBox350" readonly="${registerForm.bReadOnly}"/>
	                      <span class="required">(Required)</span> 
	            </div>
				<div class="validationMsg">
					<form:errors path="emailId" /> 
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

<%-- 	            <div class="row marginTop15"> <span class="lableText3">&nbsp;</span> 
					<%
				        /* ReCaptcha c = ReCaptchaFactory.newReCaptcha("ADD-YOUR-PUBLIC-KEY-HERE", "ADD-YOUR-PRIVATE-KEY-HERE", false); */
				        ReCaptcha c = ReCaptchaFactory.newReCaptcha("6Lel19USAAAAAPRKVOy7gFpRBpn6iSPONG1o9ouZ", "6Lel19USAAAAAHC7mqzT-Q0WpThoqiKr0DnhYtpN", false);
				        out.print(c.createRecaptchaHtml(null, null));
				    %>
				 </div> --%>
				 <div>
				 	<c:out value=""></c:out>
				 </div>
				<div>
					<span class="lableText3"></span>
					<FONT color="red">
						<c:if test="${not empty errorMessage}">
					    	<div id="errmsg" style="color: red" align="left" >
				    			<c:out value="${errorMessage}"></c:out>
							</div>
						</c:if>
					</FONT> 
				</div>
				<div class="popUpButtonRow">
				
	               <input type="submit" value="Next" class="orange"  name="Next"/>
	               <c:if test="${registerForm.bReadOnly == false}">
		               <input type="button" value="Cancel" onclick="cancelProcess()" 
										class="orange" name="Cancel" />
	               </c:if>
	               <%-- <a href="<%=request.getContextPath()%>/healthcarejobs/advanceweb.html" class="orange">Cancel</a> --%>
	               <!-- <input type="submit" value="Cancel" class="orange"  name="Cancel"/> -->  
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