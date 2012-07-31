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
<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer" style="display:block">
       <div class="popupHeader"><h2>Change Password</h2>
       <a href="#"><img src="../resources/images/Close.png" width="19" height="19" onclick="parent.$.nmTop().close();" alt=""></a></div>
                 
       <div class="popUpContainerWrapper">
       <form:form method="Get" action="/jobboard/jobseekerregistration/jobSeekerUpdatePassword.html" commandName="changePasswordForm" id="formtouse"> 
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
            <div class="rowEvenNewSpacing marginTop10 paddingBottom10">
             <span class="floatLeft marginTop10"><input type="submit" value="Save" class="btn_sm orange"/><!-- <a href="" class="btn_sm orange">Save</a> --> <a href="#" onclick="parent.$.nmTop().close();"  class="btn_sm orange">Cancel</a></span>

            </div>
            <div class="clearfix"></div>
     </form:form>
        </div>
</div>

</body>
</html>