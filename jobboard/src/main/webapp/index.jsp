<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to merion matters</title>


<script type="text/javascript">
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
</head>
<body>
	<h1>Welcome to merion matters</h1>
	<ul>
		<li><a href="jobseekerregistration/createJobSeekerProfile.html">JobSeeker
				Registration Form</a></li>
		<li><a href="createResumePopUp.html">Create Resume</a></li>
		<li><a href="createResumePopUp.html">Create Resume</a></li>
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
	<input value="View Details"
		onclick="javascript:go('viewJobDetails.html?id=1');" type="button">
	<input value="Apply"
		onclick="javascript:go('applyJob.html?id=1');" type="button">
</body>
</html>