<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Job Seeker Change Password</title>

<jsp:include page="common/include.jsp" />

		<script type="text/javascript">
		    jQuery(document).ready(function(){
		    	
		 		$('#save').click(function(){			
		 			
					$.ajax({url:"${pageContext.request.contextPath}/jobseekerregistration/jobSeekerUpdatePassword.html",
						data:$('#passwordChange').serialize(),
						type:"POST",
						success: function(data) {
							if(data == ''){
								alert("Password changed successfully !");
								parent.$.nmTop().close();
							}else{
								$("#errmsg").html(data);
							}
						 },
					});
				}); 
		    			    	
		    jQuery(".megamenu").megamenu();
		});
		</script>
		<script type="text/javascript">
		    function cancelProcess(){
		    	parent.$.nmTop().close();
		    }		
		</script>
		
		</head>

<body class="job_board">
<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer" style="display:block">
       <div class="popupHeader"><h2>Change Password</h2>
       <a href="#"><img title="close" src="../resources/images/Close.png" width="19" height="19" onclick="parent.$.nmTop().close();" alt=""></a></div>
                 
       <div class="popUpContainerWrapper">
       <form:form method="Get" action="/<%=request.getContextPath()%>/jobseekerregistration/jobSeekerUpdatePassword.html" commandName="changePasswordForm" id="passwordChange"> 
       		<div class="validationMsg" id="errmsg"> </div>
            <div class="rowEvenNewSpacing">
              	<span class="lableText3">Email Address:</span> 
	            <form:input path="emailId" class="job_seeker_email textBox2" readonly="true"/>
	            <form:errors path="emailId"/>
            </div>
			
            <div class="rowEvenNewSpacing">
            <span class="lableText3">Current Password:</span>
            <form:password path="currentPassword" class="job_seeker_password textBox2"/>
            <span class="required">(Required)</span>
            </div>
            <div>
				<span class="lableText3"></span> 
				<FONT color="red"><form:errors path="currentPassword" /></FONT>
			</div>
            <div class="rowEvenNewSpacing">
            <span class="lableText3">New Password:</span>
            <form:password path="password" class="job_seeker_password textBox2"/>
            <span class="required">(Required)</span>
            <div class="row marginTop5">
            	<span class="lableText3"></span>(8-20 characters, including at least 1 number)</div>
            </div>
			<div>
				<span class="lableText3"></span> 
				<FONT color="red"><form:errors path="password" /></FONT>
			</div>
            <div class="rowEvenNewSpacing">
             <span class="lableText3"> Confirm New Password:</span>
             <form:password path="retypepassword" class="job_seeker_password textBox2"/>
            <span class="required">(Required)</span>
            </div>
            <div>
				<span class="lableText3"></span> 
				<FONT color="red"><form:errors path="retypepassword" /></FONT>
			</div>
            <div class="popUpButtonRow">
             <input type="button" id="save" value="Save" class="orange"/><!-- <a href="" class="btn_sm orange">Save</a> --> 
             <input type="button" value="Cancel" onclick="cancelProcess()"
									class="orange" name="Cancel" />
             
             <!-- <a href="#" onclick="parent.$.nmTop().close();"  class="btn_sm orange">Cancel</a> -->

            </div>
            <div class="clearfix"></div>
     </form:form>
        </div>
</div>

</body>
</html>