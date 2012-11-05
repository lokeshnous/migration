<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<script type="text/javascript" src="../resources/js/expandCollapse.js"></script>
<script>
	$(document).ready(function() {
		$('#submitval').click(function() {
			parent.right_frame.location.reload();
		});
	});

	$("#clearMe")
			.click(
					function() {

						$
								.ajax({
									url : "${pageContext.request.contextPath}/jobsearch/clearalllist.html",
									success : function(data) {
										//alert("Data is :::"+data);

									},
								});

					});
</script>
</head>
<body class="job_board">

	<div class="mainTwo">
		<div class="row">
			<div class="job_search_main job_search_main_height"
				style="margin-right: 10px;">
				<%-- <form method=""> --%>
				<%--  <form:form method="GET" action="findJobSearch.html" commandName="jobSearchResultForm">  --%>
				<form:form method="" action="" commandName="jobSearchResultForm">
					<div class="search_form">

						<h1 class="marginBottom5">
							Search <span id="TotalNoRecords"></span> Healthcare Jobs
						</h1>
						<form:input path="keywords" maxlength="60" id="keywords"
							cssClass="jb_input1" />
						<div class="toolTipBefore">
							<label for="keywords">Job Title, Keywords, Job ID,
								Company Name </label>
						</div>
						<div class="toolTip">
							<span class="classic"><p>Type in your search criteria
									here. Include any group of terms related to your desired
									position. Click on 'Advanced Search' below for more options.</p></span>
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
								<span class="classic"><p>Enter the city and state or
										zip code of the location you want to search. Then select a
										radius to expand your search up to 100 miles from your
										starting point.</p></span>
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
						<!-- <a href="#" class="btn_sm orange jb_search_submit">Find Jobs</a> -->
						<div class="FormErrorDisplayText" id="findSearchInfo"></div>
						<div class="rowEvenNewSpacing">
							<input type="button" id="submitval" onclick="findJobs();"
								value="Find Jobs" class="orange jb_search_submit" />
							<!-- <input type="submit" id= "submit" value="Find Jobs" class="orange jb_search_submit" /> -->
							<%-- </form:form>     --%>

							<!-- 	<a href="../jobsearch/advanceSearch.html">Advanced Search</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a
								onclick="postYourResume();" class="cursor">Post Your Resume</a> -->
							<a title="Coming Soon" href="../jobsearch/advanceSearch.html">Advanced
								Search</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a rel="nofollow,noindex"
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
							<a title="Coming Soon" href="">Create an Account</a>
						</div>
					</security:authorize>
					<!-- search_info_box1 -->
					<security:authorize access="hasRole('ROLE_JOB_SEEKER')">
						<div>MY RECENT SEARCHES:</div>
						<a class="cursor" id="clearMe" onclick="clearAll();">Clear All</a> | <a
							href="../jobsearch/seeallsearch.html" id="seeallpopup">See
							All</a>
						<div id="jobboardSearchResultsHitoryId"></div>

						<!-- search_info_box1 -->
					</security:authorize>
					<div class="search_info_box2"></div>
					<!-- search_info_box2 -->

					<div class="browse_bar bold">
						<span>BROWSE JOBS:</span>&nbsp;&nbsp; <a title="Coming Soon"
							onclick="getByJobTitle()" class="cursor">By Job Title</a>&nbsp;&nbsp;|&nbsp;&nbsp;
						<a title="Coming Soon" onclick="getByEmployer()" class="cursor">By
							Employer</a>&nbsp;&nbsp;|&nbsp;&nbsp; <a title="Coming Soon"
							onclick="getByLocation()" class="cursor">By Location</a>
					</div>

					<!-- browse_bar -->

					<form:hidden path="start" id="start" />
					<form:hidden path="rows" id="rows" />

					<form:hidden path="searchtype" id="searchtype" value="basic" />
					<!-- <input type="hidden" id="searchtype" value="basic"></input>
            </div> -->
				</form:form>
				<%-- </form> --%>
			</div>
			<div class="ad_col_right">${adPageRightTop }</div>
		</div>


	</div>
	<!-- main -->