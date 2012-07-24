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
	                  <div class="popupHeader"><h2>Create Or Upload My New Resume</h2>
	                  <a href="#"><img src="../resources/images/Close.png" width="19" height="19" alt=""></a></div>
	                 
	<div class="popUpContainerWrapper">
	<form method="post" action="copyPasteResume.html" commandName="createResume" id="formtouse" enctype="multipart/form-data" >
	<div class="rowEvenNewSpacing">
	
	<div class="floatLeft marginTop5 marginRight20">How would you like to create your resume?</div>
	            <form:select path="resumeType" id="resumetype" class="jb_input3 jb_input_width3 marginTop0">
                     <form:option value="NONE" label="--- Select ---"/>
                     <form:options items="${resumeTypeList}" />
                </form:select>
	               <span class="required">(Required)</span>
	
	            </div>
	            <div class="rowEvenNewSpacing">
					<span class="lableText4">Resume Name:</span>
	                <!-- <input type="text" name="lastname" class="job_seeker_password textBox2" /><span class="required">(Required)</span> -->
	               <form:input path="resume_name" class="job_seeker_password textBox2"/>
	               <span class="required">(Required)</span>
	               <form:errors path="resume_name" /> 
	            </div>
	            <div class="rowEvenNewSpacing"><span class="lableText4">Desired job title:</span>
	              <!-- <input type="text" name="lastname2" class="job_seeker_password textBox2" /><span class="required">(Required)</span> -->
	               <form:input path="desired_job_title" class="job_seeker_password textBox2"/>
	               <span class="required">(Required)</span>
	               <form:errors path="desired_job_title" /> 
	
	    </div>
	            <div class="rowEvenNewSpacing"><span class="lableText4">Desired Employment Type:</span>
	              
	              <form:select path="desired_employment_type" id="select14" class="jb_input3 jb_input_width3 marginTop0" name="select9">
                     <form:option value="NONE" label="--- Select ---"/>
                     <form:options items="${employmentTypeList}" />
                     <form:errors path="desired_employment_type" /> 
                </form:select>
	              
	    </div>
	            <div class="rowEvenNewSpacing"><span class="lableText4">U.S. Work Authorization:</span>
	             
	             <form:select path="work_authorization_US" id="select3" class="jb_input3 marginTop0 width350" name="select3" style="width:auto">
                     <form:option value="NONE" label="--- Select ---"/>
                     <form:options items="${workauthUSList}" />
                     <form:errors path="work_authorization_US" /> 
                </form:select>
	             <span class="required">(Required)</span>
	
	    </div>
	<div class="rowEvenNewSpacing">
	                   <span class="lableText4"> Willing to relocate:</span>
			           <div class="redioButtonHolderWidth marginTop5">
			           		<label><form:radiobutton path="willing_to_relocate" value="Y" />Yes </label>
			           </div>
	                      <div class="redioButtonHolderWidth marginTop5">
	                      	<label><form:radiobutton path="willing_to_relocate" value="N" />No</label>
	                      </div>                  	                        
	<div class="toolTip marginTop8"><span class="classic">Select 'Yes' to let potential employers know you're willing to move to a new location for the right job opportunity.</span></div> <div class="toolTipBefore"><span class="required">(Required)</span></div>
	</div>
	    <div class="rowEvenNewSpacing"> <span class="lableText4">Resume Visibility:</span>
	     <div class="redioButtonHolderWidth marginTop5">	
	        <label>
	          <form:radiobutton path="resume_visibility" value="P" />Public 
	         </label>
	     </div>
	     <div class="redioButtonHolderWidth marginTop5">
	        <label>
	        <form:radiobutton path="resume_visibility" value="R" />Private	          
	          </label>
	     </div>
	     <div class="toolTip marginTop8"><span class="classic">You can only have one resume visible to employers at a time, so select 'Private' if you already have a public resume saved to your profile. Otherwise, you may select 'Public' and employers will be able to view your resume immediately.</span></div> <div class="toolTipBefore"><span class="required">(Required)</span></div> </div>
	    <div class="rowEvenNewSpacing marginTop10 paddingBottom10">
	             <span class="floatLeft marginTop10">
	             <input type="submit" value="Create" class="btn_sm orange" />
	             <a href="" class="btn_sm orange">Cancel</a></span>
	</div>
	                    <div class="clearfix"></div>
	
	          </form></div>
	          <div class="clearfix"></div>
	                </div>
	
	</body>
</html>