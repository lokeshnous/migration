<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
		<head>	
</head>
		<body class="job_board">

              <div class="mainTwo" >
              <div class="row">
                            <div class="job_search_main job_search_main_height" style="margin-right: 10px;">
                  <%-- <form method=""> --%>
                 <%--  <form:form method="GET" action="findJobSearch.html" commandName="jobSearchResultForm">  --%>
                  <form:form method="" action="" commandName="jobSearchResultForm"> 
            <div class="search_form">
            
	                      <h1 class="marginBottom5">Search <span id="TotalNoRecords"></span> Healthcare Jobs</h1>
	                      <form:input path="keywords" maxlength="60" id="keywords" cssClass="jb_input1" />
	                      <div class="toolTipBefore"><label for="keywords">Job Title, Keywords, Job ID, Company Name </label></div> <div class="toolTip"><span class="classic"><p>Type in your search criteria here. Include any group of terms related to your desired position. Click on 'Advanced Search' below for more options.</p></span></div>
	                      <br/>
	                      <div class="input_grp1 marginTop10">
	                       <form:input path="cityState"  id="cityState" cssClass="jb_input2" />
	                	  <!-- <input type="text" name="cityState" id="cityState" class="jb_input2" /> -->
	                <br/>
	                <div class="toolTipBefore"><label for="cityState">City and State or ZIP Code </label></div> <div class="toolTip"><span class="classic"><p>Enter the city and state or zip code of the location you want to search. Then select a radius to expand your search up to 100 miles from your starting point.</p></span></div>
	              </div>
	                      <div class="input_grp2 marginTop10">
	                <form:select path="radius" id="radius" cssClass="jb_input3" onchange="validateRadius();">
	                	<form:option label="--" value=""/>
	                	<!-- USE <form:options/> while dynamically populating the values  -->
	                	<form:option label="5 Miles" value="5"/>
	                	<form:option label="10 Miles" value="10"/>
	                	<form:option label="25 Miles" value="25"/>
	                	<form:option label="50 Miles" value="50"/>
	                	<form:option label="100 Miles" value="100"/>
	                </form:select>
	                <label for="radius">Radius</label>
	              </div>
	              
	              <div class="clearfix"></div>
	                      <!-- <a href="#" class="btn_sm orange jb_search_submit">Find Jobs</a> -->
	                      <div style="color: red;font-weight:bold;" id="findSearchInfo" ></div>
	                    <input type="button" id= "submitval" value="Find Jobs" class="btn_sm orange jb_search_submit" />
	                    <!-- <input type="submit" id= "submit" value="Find Jobs" class="btn_sm orange jb_search_submit" /> -->
             <%-- </form:form>     --%>  
                      
                      <a href="../jobsearchactivity/advanceSearch.html">Advanced Search</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">Post Your Resume</a></div>
            <!-- search_form -->

					<security:authorize access="!hasRole('ROLE_JOB_SEEKER')">
						<div class="search_info_box1">
							<p class="search_message">
								JOIN THE <span>ADVANCE</span> NETWORK
							</p>
							<ul>
								<li>Apply to jobs faster</li>
								<li>Post a resume to be found by registered employers</li>
								<li>Create a Job Alert and more for free</li>
							</ul>
							<a href="">Create an Account</a>
						</div>
					</security:authorize>
					<!-- search_info_box1 -->
					<security:authorize access="hasRole('ROLE_JOB_SEEKER')">
						<div class="search_info_box1">
							<div class="rowPadding borderBottomDotted">
								<span class="rowEvenSpacing capsText">MY RECENT SEARCHES:</span><br>
								<a href="#">Clear All</a> | <a href="#">See All</a>
							</div>
							<div class="rowPadding borderBottomDotted">
								May 16, 2012, 7:00am<br> Search by: <a href="#">Physical
									Therapist / Philadelphia, PA</a>
							</div>
							<div class="rowPadding borderBottomDotted">
								May 16, 2012, 7:00am<br> Search by: <a href="#">Physical
									Therapist / Philadelphia, PA</a>
							</div>
							<div class="rowPadding">
								May 16, 2012, 7:00am<br> Search by: <a href="#">Physical
									Therapist / Philadelphia, PA</a>
							</div>
						</div>
						<!-- search_info_box1 -->
					</security:authorize>

					<div class="search_info_box2">
                      <ul>
                <li><span class="uppercase bold">My Recent Searches:</span><br/>
                          <a href="">Clear All</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">See All</a></li>
                <li>May 16, 2012, 7:00 am<br/>
                          Search by: <span class="jb"><a href="">Physical Therapist</a></span></li>
                <li>May 17, 2012, 8:00 am<br/>
                          Search by: <span class="jb"><a href="">Pediatric Nurse</a></span></li>
                <li class="last">May 18, 2012, 9:00 am<br/>
                          Search by: <span class="jb"><a href="">ER Nurse</a></span></li>
              </ul>
                    </div>
            <!-- search_info_box2 -->
            
            <div class="browse_bar bold"> <span>BROWSE JOBS:</span>&nbsp;&nbsp;<a href="">By Job Title</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">By Employer</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">By Location</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">By Employment Type</a> </div>

            <!-- browse_bar -->
            
            <form:hidden path="start" id="start"/>
            <form:hidden path="rows" id="rows"/>
            <input type="hidden" id="searchtype" value="basic"></input>
            
            </form:form>    
                <%-- </form> --%>
                </div>
              <div class="ad_col_right"> <img src="../resources/images/ads/300x250ad1.png" class="paddingBottom0" />
        
              </div></div>

    </div>
    <!-- main --> 


<!-- footer_wrapper -->

</body>
</html>