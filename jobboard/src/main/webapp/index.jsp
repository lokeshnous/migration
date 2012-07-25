<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to merion matters</title>


<!-- js files for modalpopup------------------------------------------------- -->
<script src="resources/js/jquery-1.7.1.js"></script>
<script src="resources/js/jquery-1.7.1.min.js"></script>

		<script src="resources/nyroModal/js/jquery.nyroModal.custom.js"></script>
        <script src="resources/nyroModal/js/jquery.nyroModal.custom.min.js"></script>
 	    <link href="resources/nyroModal/styles/nyroModal.css" rel="stylesheet" type="text/css">

        <style type="text/css" media="screen">
           @import url("${pageContext.request.contextPath}/resources/jquery.nyroModal/styles/nyroModal.css");
        </style>
<!-- -------------------------------------------------------------------------- -->


<!-- js function for modalpopup------------------------------------------------- -->

<script type="text/javascript">
$(function() {
    $(".basicnew").nyroModal({minWidth:50,minHeight:50});
   
	});
function go(url)
{
window.location = url;
}

function deleteContact(url)
{
	var isOK = confirm("Are you sure to delete?");
if(isOK)
{
go(url);
}
}
</script>
<!-- -------------------------------------------------------------------------- -->

</head>
<body>
	<div >
	<h1>Welcome to merion matters</h1>
	<ul>
	<!-- js link for modalpopup------------------------------------------------- -->
	
	    <li><a href="jobSeekerResume/createResumePopUp.html"  target="_blank" class="basicnew">JS Link for modal popup</a></li>
    <!-- -------------------------------------------------------------------------- -->
    <!-- for closing iframe use >><a href="#" onclick="parent.$.nyroModalRemove(); return false;">Close From Iframe</a>   -->
	
	<%//response.sendRedirect("jobSeeker/jobSeekerDashBoard.html"); %>
	<li><a href="jobseekerregistration/jobSeekerChangePassword.html">JobSeeker ChangePassword</li>
		<li><a href="jobseekerregistration/createJobSeekerProfile.html">JobSeekerRegistration Form</a></li>
		<li><a href="employerRegistration/createEmployerProfile.html">Employer Registration Form</a></li>
		<li><a href="jobSeekerResume/viewResumeBuilder.html">View Resume</a></li>
		<li><a href="jobSeekerResume/createResumePopUp.html">Create Resume</a></li>
		<li><a href="postjob/postnewjob.html">Post New Job</a></li>
		<li><a href="anonymoususerjobapply/anonymousUser.html">Anonymous User</a></li>
		<li><a href="jspContentView/copyhtmltolocal.html">Copy html to local(D drive)&nbsp;&nbsp;&nbsp;</a><a href="jspContentView/viewcareerhtmlContents.html">htmlContents</a></li>
	</ul>
	<h2>Job Seeker Activity</h2>
	<ul>
		<li><a href="jobsearchactivity/findJobPage.html">Find Job</a></li>
		<li><a href="viewAppliedJob.html">View Job I've Applied To</a></li>
		<li><a href="viewSavedJob.html">view My Saved Jobs</a></li>
		<li><a href="jobSeekerResume/createResumeBuilder.html">Create Resume Builder</a></li>
		<!-- <li><a href="deleteAppliedJob.html">Delete Applied Jobs</a></li>
		<li><a href="viewSavedJob.html">Delete Saved Jobs</a></li> -->


<input value="Delete"
			onclick="javascript:deleteContact('deleteAppliedJob.html?id=13100');"
			type="button">

<h2>Job Seeker Subscriptions</h2>
		<li><a href="modifySubscription.html">Modify Subscriptions</a></li>
		

	</ul>
	<input value="Delete"
		onclick="javascript:deleteContact('deleteAppliedJob.html?id=13100');"
		type="button">
		
	<br/> <h4>job id :1103</h4> 
	<input value="View Details"
		onclick="javascript:go('./jobsearchactivity/viewJobDetails.html?id=13101');" type="button">
	<input value="Apply"
		onclick="javascript:go('./jobsearchactivity/applyJob.html?id=13100');" type="button">
	<input value="Save This Job"
		onclick="javascript:go('./jobsearchactivity/saveThisJob.html?id=13100');" type="button">
		
			<h2>Manage Featured Employer Profile</h2>
		<li><a href="employerprofile.html">Manage Featured Employer Profile</a></li>
				</div>	
		<%//response.sendRedirect("healthcarejobs/advanceweb.html"); %> 
<h2>Employeer Activity</h2>
<a href="brandingTemplates/empBrandTempList.html">Manage Job
		Posting Branding Template</a	><br/>

	<h3>Login</h3>

	<li><a href="loginFormForJObSeeker/login.html">Login</a></li>


</body>
</html>