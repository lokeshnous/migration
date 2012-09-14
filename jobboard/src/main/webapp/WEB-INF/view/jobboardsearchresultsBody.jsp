<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
		<head>		
<script type="text/javascript" src="../resources/js/expandCollapse.js"></script>
		</head>

		<body>
<form:form method="POST" action="" commandName="jobSearchResultForm">
					
					<div class="row">

						<div class="row marginTop5 paddingBottom05">
						<div class="row">
						<div id="errorMsg" class="FormErrorDisplayText"></div>
						</div>
							<div class="floatLeft">
								<h1 >
									<span>${totalNoOfRecords}</span> jobs match your search criteria.
								</h1>
							</div>
						</div>


					</div>

					<div class="clearfix"></div>
					<div class="content_columns_search_results">
						<div class="column1">


							<div class="section">
								<h2>Current Search</h2>
								<div class="buttonRow">
									Nurse
									<div class="floatRight">
										<a href="" style="visibility:hidden;"><img src="../resources/images/CloseGray.jpg"
											alt="close" width="15" height="15"> </a>
									</div>
								</div>
								<div class="buttonRow">
									10001
									<div class="floatRight">
										<a href="" style="visibility:hidden;"><img src="../resources/images/CloseGray.jpg"
											alt="close"> </a>
									</div>
								</div>
								<div class="buttonRow">
									25 miles
									<div class="floatRight">
										<a href="" style="visibility:hidden;"><img src="../resources/images/CloseGray.jpg"
											alt="close" width="15" height="15"> </a>
									</div>
								</div>
								<div class="section">
									<div class="SaveSearchButton">
										<a href="#" class="btn_sm orange nyroModal" id="saveThisSearchId" onclick="saveThisSearch();">Save This Search</a>
									</div>
								</div>
							</div>
							<div class="section">
								<h2>Refine Results</h2>

								<div class="refineResults">

									<span class="refineResultsItem plus">Radius</span>
									<div class="refineResultsSubContent">
										<ul>
											<li><!-- <a href="" ></a> -->5 miles</li>
											<li><!-- <a href="" ></a> -->10 miles</li>
											<li><!-- <a href="" ></a> -->25 miles</li>
											<li><!-- <a href="" ></a> -->50 miles</li>
											<li><!-- <a href="" ></a> -->100 miles</li>
										</ul>
									</div>

									<span class="refineResultsItem plus">Employer</span>
									<div class="refineResultsSubContent">
										<ul>
											<li><!-- <a href="" ></a> -->Allegiant (5)</li>
											<li><!-- <a href="" ></a> -->Nova Care (2)</li>
											<li><!-- <a href="" ></a> -->Mount Sinai (3)</li>
										</ul>
									</div>

									<span class="refineResultsItem plus">State</span>
									<div class="refineResultsSubContent">
										<ul>
											<li><!-- <a href="" ></a> -->Virginia (5)</li>
											<li><!-- <a href="" ></a> -->Maryland (2)</li>
											<li><!-- <a href="" ></a> -->New York (3)</li>
										</ul>
									</div>

									<span class="refineResultsItem plus">City</span>
									<div class="refineResultsSubContent">
										<ul>
											<li><!-- <a href="" ></a> -->Alexandria (5)</li>
											<li><!-- <a href="" ></a> -->Baltimore (2)</li>
											<li><!-- <a href="" ></a> -->Manhattan (3)</li>
										</ul>
									</div>
								</div>


							</div>


						</div>
						<!-- column1 -->

						<!-- <div class="column2">

							<div class="searchResults">

								<table id="jobSearchResultTable">

									

									<div id="jobSearchResultDiv" class="searchResultsListing">


										<div class="searchResultsItem">
											<table cellpadding="0" cellspacing="0" style="width: 100%"
												border="0" class="display" id="jsonTable">
												<thead>
													<tr >
														<th class="searchResultsColumn1">Job Title</th>
														<th class="searchResultsColumn2">Employer</th>
														<th class="searchResultsColumn3">Location</th>
														<th class="searchResultsColumn4">Date Posted</th>
													</tr>
												</thead>
											</table>
										</div>
										<div id="pager2"></div>

									</div>


								</table>
							</div>
						</div> -->
		<div class="column2">
<div class="searchResults">
				<div class="searchResultsNavigation">

		<div class="searchResultsNavigationColumn1 SearchPerPage">
		<input type="hidden" value="${beginVal}"/>
			<span class="ShareText">Results viewable:</span> <span
				class="Padding0"> <select name="noOfPage" id="noOfPage"
				class="jb_input4 margin0" onchange="applyFilter();">
					<option value="20">20</option>
					<option value="30">30</option>
					<option value="40">40</option>
					<option value="50">50</option>
			</select>
			<%-- <form:select path="${filterVal}" itemValue="optionId" 
								itemLabel="optionName" items="${filterVals}"></form:select>  --%> 
						</span><span class="ShareText">per page</span>
		</div>


						<div class="searchResultsNavigationColumn3 ViewNumPage">
							&nbsp;&nbsp;
							<c:if test="${totalNoOfRecords != null}">
							${startRow} &#45; ${endRow} of ${totalNoOfRecords}&nbsp;
						</c:if>
						</div>
						<div class="searchResultsNavigationColumn2 GetNumPage">
						<!-- <span>Page:</span> -->
						<c:if test="${totalNoOfRecords != null}">
						<c:if test="${currentPage > 9 && noOfPages gt 10}">
							<td><a
								onclick="getPrevPages(${begin - 10}, ${begin-10});">
									<img src="../resources/images/ArrowLeft.png"> Previous
							</a></td>
						</c:if>

						<c:forEach begin="${begin}" end="${noOfPages}" var="i">
               				 <c:choose>
                  				  <c:when test="${currentPage eq i}">
									<span class="active">${i}</span>
									</c:when>
                   				 <c:otherwise>
                       				 <span><c:if test="${i lt begin+10}">
                       				 <a onclick="getNextPage(${i});" >${i}</a></c:if></span>
                   				 </c:otherwise>
               				 </c:choose> 
           				 </c:forEach>
           				 <c:if test="${(begin+10) lt noOfPages}">
           				 <span><a onclick="getNextPages(${begin + 10} ,${begin+10});"
							>Next<img src="../resources/images/ArrowRight.png">
							</a></span>
       					</c:if>  
       					</c:if>     		 
					</div>
				</div>


				<div class="searchResultsHeader">
					<ul>
						<li class="searchResultsColumn1">Job Title</li>
						<li class="searchResultsColumn2">Employer</li>
						<li class="searchResultsColumn3">Location</li>
						<li class="searchResultsColumn4">Date Posted</li>
					</ul>
				</div>

				<div class="searchResultsListing">

					<div class="searchResultsItem">
					<c:forEach items="${searchResultsList}" var="job" varStatus="status">
					<%-- <form:hidden path="jobDTOs[${status.index}].jobId"/> --%>
						<ul class="searchResultsJobInfo closed" id="searchResultsJobInfo${job.JobId}" onclick="trackClick(${job.JobId});">
							<li class="searchResultsColumn1">${job.JobTitle}</li>
							
							<li class="searchResultsColumn2">${job.Company}</li>
							
							<li class="searchResultsColumn3">
							<c:if test="${!(job.HideCity == 1 || job.HideState == 1 || job.HideCountry == 1)}">
							${job.City}
							</c:if></li>
							
							<li class="searchResultsColumn4">${job.PostedDate}</li>
						</ul>
						<div class="searchResultsSubContent">

							<%-- <p class="searchResultsSubContentJobDescription" >
								<span class="bold">Job Description:</span>
								${job.AdText}
							</p> --%>

								<div style="height: 42px; overflow: hidden;margin-bottom: 5px;">
								<p class="searchResultsSubContentJobDescription">								
									<span class="bold">Job Description:</span>
									${job.AdText}
								</p>
								</div>

								<div class="searchResultsSubContentButtonArea">
									<div class="searchResultsSubContentButtons">
										<a onclick="applyThisJob(${job.JobId});" class="btn_sm white"
											id="applyJobid${job.JobId}">Apply</a>
									</div>
									<div class="searchResultsSubContentButtons">
										<a onclick="viewJobDetails(${job.JobId})" class="btn_sm white">View
											Details</a>
									</div>
									<div class="searchResultsSubContentButtons">
										<a onclick="saveThisJob(${job.JobId})"
											id="saveThisJobId${job.JobId}"
											class="btn_sm white">Save This Job</a>
									</div>
								</div>
								<div class="featured_empButton">
									<c:choose>
										<c:when test="${job.IsFeatured}">
											<!-- <a href=""> --><img src="../resources/images/FeaturedEmp.png"
												alt="featured emp Button" width="164" height="23"><!-- </a> -->
										</c:when>
										<c:otherwise>
											<img src="../resources/images/tranBg.png"
												alt="featured emp Button" width="164" height="23">
										</c:otherwise>
									</c:choose>
								</div>

								<div class="ShareSearch">
									<span class="ShareText"> Send to
										Friend:&nbsp;</span><span><a onclick="sendToFrd(${job.JobId});"><span
												class="email"></span></a></span>
								</div>

								<!-- <div class="searchResultsSubContentShare">
									<span class="marginTop3 floatLeft"> Send to
										Friend:&nbsp;</span><span><a href=""><img
											src="../resources/images/email.png"></a></span>
								</div> -->

								<div class="ShareSearch">
								<span class="ShareText">Share:&nbsp;</span> 
								<span class="fbook"><!-- <a href=""> --><!-- </a> --></span>
								<span class="linkedIn"><!-- <a href=""> --><!-- </a> --></span> 
								<span class="twitter"><!-- <a href=""> --><!-- </a> --></span>
							</div>
							
							<div class="FormErrorDisplayText row" id="topjobActionInfo${job.JobId}" ></div>
							
						</div>
				<c:if test="${(status.index + 1) % 10 == 0}">
					<div class="ad_wrapper">
						<img src="../resources/images/ads/banner_ad_fpo.png">
					</div>
				</c:if>
			</c:forEach>
					</div>
				</div>


				<div class="searchResultsNavigation searchResultsNavigationBottom">

		<div class="searchResultsNavigationColumn1 SearchPerPage">
			<span class="ShareText">Results viewable:</span> <span
				class="Padding0"> <%-- <form:select path="noOfPageLower"
							name="results" class="jb_input4 margin0">
								<form:option value="20">20</form:option>
								<form:option value="30">30</form:option>
								<form:option value="40">40</form:option>
								<form:option value="50">50</form:option>
						</form:select> --%> <select name="results" id="noOfPageLower"
				class="jb_input4 margin0" onchange="applyLowerFilter();">
					<option value="20">20</option>
					<option value="30">30</option>
					<option value="40">40</option>
					<option value="50">50</option>
			</select>
			</span><span class="ShareText">per page</span>
		</div>


						<div class="searchResultsNavigationColumn3 ViewNumPage">
							&nbsp;&nbsp;
							<c:if test="${totalNoOfRecords != null}">
							${startRow} &#45; ${endRow} of ${totalNoOfRecords}&nbsp;
						</c:if>
						</div>
						<div class="searchResultsNavigationColumn2 GetNumPage">
						<!-- <span>Page: </span> -->
						<c:if test="${totalNoOfRecords != null}">
						<c:if test="${currentPage > 9 && noOfPages gt 10}">
							<td><a
								onclick="getPrevPages(${begin - 10}, ${begin-10});">
									<img src="../resources/images/ArrowLeft.png"> Previous
							</a></td>
						</c:if>

						<c:forEach begin="${begin}" end="${noOfPages}" var="i">
               				 <c:choose>
                  				  <c:when test="${currentPage eq i}">
									<span class="active">${i}</span>
									</c:when>
                   				 <c:otherwise>
                       				 <span><c:if test="${i lt begin+10}">
                       				 <a onclick="getNextPage(${i});" >${i}</a></c:if></span>
                   				 </c:otherwise>
               				 </c:choose> 
           				 </c:forEach>
           				 <c:if test="${(begin+10) lt noOfPages}">
           				 <span><a onclick="getNextPages(${begin + 10} ,${begin+10});"
							>Next<img src="../resources/images/ArrowRight.png">
							</a></span>
       					</c:if>  
       					</c:if>   
					</div>
				</div>
			</div>
		
		</div>
		<!-- column2-->

					</div> 
					</form:form>
</body>
</html>