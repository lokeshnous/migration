<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

	<div class="mainTwo">
		<div class="row">
			<div class="job_search_main job_search_main_height"
				style="margin-right: 10px;">
				<%-- <form method=""> --%>
				 <%-- <form:form method="GET" action="findJobSearch.html" commandName="jobSearchResultForm">   --%>
				<form:form method="" action="" commandName="jobSearchResultForm" id="jobSearchResultHeaderFormId"> 
				<%-- <form:form method="POST" action="../jobsearch/findjobs.html" commandName="jobSearchResultForm" id="jobSearchResultHeaderFormId"> --%> 
					<div class="search_form">
					<c:choose>
						<c:when test="${isHomePage}">
						<h1 class="marginBottom5">
							Search <span id="TotalNoRecords"></span> Healthcare Jobs
						</h1>
						</c:when>
						<c:otherwise>
						<h2 class=" option2 marginBottom5">
							Search <span id="TotalNoRecords"></span> Healthcare Jobs
						</h2>
						</c:otherwise>
						</c:choose>
						<form:input path="keywords" maxlength="60" id="keywords" cssClass="jb_input1" />
						<div class="toolTipBefore">
							<label for="keywords">Job Title, Keywords, Job ID,
								Company Name </label>
						</div>
						<div class="toolTip">
							<div class="classic"><p>Type in your search criteria
									here. Include any group of terms related to your desired
									position. Click on 'Advanced Search' below for more options.</p></div>
						</div>
						<br />
						<div class="input_grp1 marginTop10">
							<form:input path="cityState" id="cityState" cssClass="jb_input2" />
							<!-- <input type="text" name="cityState" id="cityState" class="jb_input2" /> -->
							<br />
							<div class="toolTipBefore">
								<label for="cityState">City and State or ZIP Code </label>
							</div>
							<div class="toolTip">
								<div class="classic"><p>Enter the city and state or
										zip code of the location you want to search. Then select a
										radius to expand your search up to 100 miles from your
										starting point.</p></div>
							</div>
						</div>
						<div class="input_grp2 marginTop10">
							<form:select path="radius" id="radius" cssClass="jb_input3"
								onchange="validateRadius();">
								<form:option label="--" value="0" />
								<!-- USE <form:options/> while dynamically populating the values  -->
								<form:option label="5 Miles" value="5" />
								<form:option label="10 Miles" value="10" />
								<form:option label="25 Miles" value="25" />
								<form:option label="50 Miles" value="50" />
								<form:option label="100 Miles" value="100" />
							</form:select>
							<label for="radius">Radius</label>
						</div>
						<form:hidden path="autoload" id="autoload" />

						<div class="clearfix"></div>
						<div class="FormErrorDisplayText" id="findSearchInfo"></div>
						<div class="rowEvenNewSpacing">
							<input type="button" id="submitval" 
								value="Find Jobs" class="orange jb_search_submit cursor" />
							<!-- <input type="submit" id= "submit" value="Find Jobs" class="orange jb_search_submit" /> -->  

							<a title="Coming Soon" href="#">Advanced
								Search</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a
								onclick="postYourResume();" class="cursor">Post Your Resume</a>
						</div>
					</div>
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
							<a href="<%=request.getContextPath()%>/jobseekerregistration/createJobSeekerCreateYrAcct.html">Create an Account</a>
						</div>
					</security:authorize>
					<!-- search_info_box1 -->
					<security:authorize access="hasRole('ROLE_JOB_SEEKER')">
						<div>MY RECENT SEARCHES:</div>
						<div id="jobboardSearchesHistoryId"></div>

						<!-- search_info_box1 -->
					</security:authorize>
					<div class="search_info_box2"></div>
					<!-- search_info_box2 -->

            <div class="browse_bar bold"> <span>BROWSE JOBS:</span>&nbsp;&nbsp;
            <a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/jobsearch/browse/jobtitles.html" class="cursor">By Job Title</a>&nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/jobsearch/browse/employers.html" class="cursor">By Employer</a>&nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/jobsearch/browse/locations.html" class="cursor">By Location</a>
            </div>
            <!-- browse_bar -->
            
            <form:hidden path="start" id="start"/>
            <form:hidden path="rows" id="rows"/>
            <input value="<%=request.getContextPath()%>" type="hidden" id="contextPath" />
            <form:hidden path="searchtype" id="searchtype" />
            <!-- <input type="hidden" id="searchtype" value="basic"></input>
            </div> -->
				</form:form>
				<%-- </form> --%>
			</div>
			<div class="ad_col_right">
			<div id="adPageRightTop"> ${adPageRightTop} </div>
			</div>
		</div>


	</div>
	<!-- main -->
