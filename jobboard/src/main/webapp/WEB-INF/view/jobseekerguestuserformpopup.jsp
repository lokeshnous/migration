<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>ADVANCE Heathcare Jobs</title>

		<!-- STYLESHEETS -->
		<link href="../resources/css/JB.css" rel="stylesheet" type="text/css" />
		<link href="../resources/css/jquery.megamenu.css" rel="stylesheet" type="text/css" />
		<link href="../resources/css/SliderStyles.css" rel="stylesheet" type="text/css">
        
<!--[if IE]>
	<link href="stylesheets/ie.css" rel="stylesheet" type="text/css">
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
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
        </script>
		</head>

<body class="job_board">
<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer" style="display:block">
                  <div class="popupHeader"><h2>GUEST USER FORM</h2>
                  <a href="#"><img src="../resources/images/Close.png" onclick="parent.$.nmTop().close();" width="19" height="19" alt=""></a></div>
                 
<div class="popUpContainerWrapper"><form:form  method="Get" action="/jobboard/anonymoususerjobapply /saveAnonymousUserJobapply.html" commandName="jobApplicationForm" enctype="multipart/form-data" >
            <div class="rowEvenSpacing"> <h3>Send Resume</h3></div>
            <div class="rowEvenSpacing">
<span class="lableText3">Name:</span>   
                <form:input type="text" path="userName" class="job_seeker_email" />
                <span class="required">(Required)</span>
                <div style="color: red" align="center"><form:errors path="userName"/></div>
            </div>
            <div class="rowEvenSpacing"><span class="lableText3">Email Address:</span>
              <form:input type="text" path="userEmail"  class="job_seeker_email" />
              <span class="required">(Required)</span>
              <div style="color: red" align="center"><form:errors path="userEmail"/></div>
    </div>
            <div class="rowEvenSpacing"> <span class="lableText3">Upload Resume File:</span>
            <div class="floatLeft"><form:input path="filePath" type="file" id="textfield4" size="20" class="job_seeker_login_email fileType" />
            </div>
            <span class="required">(Required)</span>
             <div style="color: red" align="center"><form:errors path="filePath"/></div>
</div>
      <div class="popUpButtonRow">
                         
             <input type="submit" class="btn_sm orange" value="Send"/> <a href="" onclick="parent.$.nmTop().close();" class="btn_sm orange">Cancel</a></span>
             
</div>
                    <div class="clearfix"></div>
          </form:form></div>
          <div class="clearfix"></div>
                </div>

</body>
</html>