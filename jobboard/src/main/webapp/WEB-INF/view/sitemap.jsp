<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> Site Map</title>
<jsp:include page="common/include.jsp" />
</head>

<body class="job_board">
	<div class="ad_page_top">
		${adPageTop}
	</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">
			<div class="main">
				<jsp:include page="../templates/templates_header.jsp"></jsp:include>
				<div class="otherContent ">
				<h1>Site Map</h1><br/>
<div class="row job_seeker_login_width">
<div class="NameOrderNormal"><h3 class="color2 TextAlignL">&nbsp;Job Searching</h3></div>
<ul>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcarejobs/advanceweb.html">Advance Healthcare Jobs Homepage</a></li>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/jobsearch/advanceSearch.html">	Advance Search</a></li>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/jobsearch/browse/jobtitles.html">	Search by Job Title</a></li>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/jobsearch/browse/employers.html">	Search by Employer</a></li>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/jobsearch/browse/locations.html">	Search by Location</a></li>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcarejobs/featuredemployers.html">	Featured Employers</a></li>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/jobs/alljobs.html">	View All Jobs</a></li>
</ul>
<div class="NameOrderNormal"><h3 class="color2 TextAlignL">&nbsp;My Account Info</h3></div>
<ul>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/jobseekerregistration/createJobSeekerCreateYrAcct.html">	Job Seeker Sign Up</a></li>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/commonLogin/login.html?page=jobSeeker">	Job Seeker Login</a></li>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/commonLogin/login.html?page=employer">	Employer Login</a></li>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/employerRegistration/employerregistration.html">	Employer Sign Up</a></li>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/commonLogin/login.html?page=agency">	Ad Agency Login</a></li>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/agencyRegistration/agencyregistration.html">	Ad Agency Sign Up</a></li>
</ul>
<div class="NameOrderNormal"><h3 class="color2 TextAlignL">&nbsp;Resume Services</h3></div>
<ul>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/jobsearch/jobseekerPostYourResume.html">	Post your Resume</a></li>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcarejobs/advanceweb.html">	Resume Builder </a></li>
</ul>

<div class="NameOrderNormal"><h3 class="color2 TextAlignL">&nbsp;About Advance Healthcare Jobs</h3></div>
<ul>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcarejobs/advanceweb.html">	About Us</a></li>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcarejobs/advanceweb.html">	Contact Us</a></li>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcarejobs/advanceweb.html">	Advertise with Us</a></li>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcarejobs/advanceweb.html">	Work for Us</a></li>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcarejobs/advanceweb.html">	Privacy Policy</a></li>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcarejobs/advanceweb.html">	Terms of Service</a></li>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcarejobs/advanceweb.html">	Help</a></li>
</ul>
</div>
<div class="row job_seeker_login_width">
<div class="NameOrderNormal"><h3 class="color2 TextAlignL">&nbsp;Career Resources</h3></div>
<ul>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcarejobs/advanceweb.html">	Salary Calculator</a></li> 
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcarejobs/advanceweb.html">	ADVANCE Messenger </a></li>
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcarejobs/advanceweb.html">	Career Resource Center</a></li> 
<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcarejobs/advanceweb.html">	Featured Facilities </a></li>
</ul>
</div>			
				</div>
				<br class="clearfix" />
				
				<div class="ad_wrapper">
					${adPageBottom}
				</div>
			
				
			</div>
			<!-- main -->
			</div>
</div>
		<!-- end main_wrapper_inside -->

	<!-- end main_wrapper_outside -->
	<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
</body>
</html>