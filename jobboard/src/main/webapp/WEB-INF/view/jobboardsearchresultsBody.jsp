<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
                                                  prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
		<head>		
<script type="text/javascript" src="../resources/js/expandCollapse.js">
</script>
<script type="text/javascript"  src="https://platform.twitter.com/widgets.js">
</script>
<script src="http://static.ak.fbcdn.net/connect.php/js/FB.Share" type="text/javascript">
</script>
<script src="http://platform.linkedin.com/in.js" type="text/javascript">
</script>

</head>

		<body>
		<c:choose>
		<c:when test="${jobTitlePage}">
			<jsp:include page="browseByJobTitle.jsp"></jsp:include>
		</c:when>
		<c:when test="${employerPage}">
			<jsp:include page="browseByEmployer.jsp"></jsp:include>
		</c:when> 
		<c:when test="${locationPage}">
			<jsp:include page="browseByLocation.jsp"></jsp:include>
		</c:when> 		
		<c:otherwise>
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
							<c:if test="${currentSearchList != null}">
								<h3>Current Search</h3>
								<c:forEach items="${currentSearchList}" var="item">
									<div class="buttonRow">
										${item.value}
										<div class="floatRight">
											<a onclick="deleteCurrentSearch('${item.key}','${item.value}')" class="cursor"><img src="../resources/images/Close.png"
												alt="close" width="15" height="15"> </a>
										</div>
									</div>
								</c:forEach>
								<div class="section">
									<div class="SaveSearchButton">
										<a  rel="nofollow" href="#" class="btn_sm orange nyroModal" id="saveThisSearchId" onclick="saveThisSearch();">Save This Search</a>
									</div>
								</div>
								</c:if>
							</div>
							<div class="section">
								<h3>Refine Results</h3>

								<div class="refineResults">

									<c:if test="${displayRadius}">
									<span class="refineResultsItem plus" id="radiusPlus">Radius</span>
									<c:if test="${refineRadius != 0}">
									<script>$("#radiusPlus").click();</script>
									</c:if>

									<div class="refineResultsSubContent">
										<ul>
											<c:if test="${refineRadius==5}">
											<span style="font-weight:bold;"> 
											</c:if>
											<li><a onclick="refineByRadius('5')" >5 Miles</a></li>
											<c:if test="${refineRadius==5}">
											</span>
											</c:if>
											
											<c:if test="${refineRadius==10}">
											<span style="font-weight:bold;"> 
											</c:if>
											<li><a onclick="refineByRadius('10')" >10 Miles</a></li>
											<c:if test="${refineRadius==10}">
											</span>
											</c:if>
											
											<c:if test="${refineRadius==25}">
											<span style="font-weight:bold;"> 
											</c:if>
											<li><a onclick="refineByRadius('25')" >25 Miles</a></li>
											<c:if test="${refineRadius==25}">
											</span>
											</c:if>
											
											<c:if test="${refineRadius==50}">
											<span style="font-weight:bold;"> 
											</c:if>
											<li><a onclick="refineByRadius('50')" >50 Miles</a></li>
											<c:if test="${refineRadius==50}">
											</span>
											</c:if>
											
											<c:if test="${refineRadius==100}">
											<span style="font-weight:bold;"> 
											</c:if>
											<li><a onclick="refineByRadius('100')" >100 Miles</a></li>
											<c:if test="${refineRadius==100}">
											</span>
											</c:if>
										</ul>
									</div>
									</c:if>
									<span class="refineResultsItem plus" id="companyPlus">Employer</span>
									<c:if test="${secondFQParam != null}">
									<script>$("#companyPlus").click();</script>
									</c:if>

									<div class="refineResultsSubContent">
										<ul>
											<c:forEach items="${company}" var="displayCompany" varStatus="status" >
											<c:if test="${fn:startsWith(company[status.index], secondFQParam) and secondFQParam != null}">
											<span style="font-weight:bold;"> 
											</c:if>
												<li><a onclick="refineByCompany('${company[status.index]}');">${company[status.index]}</a></li>
											<c:if test="${fn:startsWith(company[status.index], secondFQParam) and secondFQParam != null}">
											</span> 
											</c:if>
											</c:forEach>
										</ul>
									</div>
									
									<input type="hidden" name="refined" id="refined" />
									
									<span class="refineResultsItem plus" id="statePlus">State</span>
									<c:if test="${thirdFQParam != null}">
									<script>$("#statePlus").click();</script>
									</c:if>

									<div class="refineResultsSubContent">
										<ul>
											<c:forEach items="${state}" var="displayState" varStatus="status">
												<c:if test="${fn:startsWith(state[status.index], thirdFQParam) and thirdFQParam != null}">
												<span style="font-weight:bold;"> 
												</c:if>
												<li><a onclick="refineByState('${state[status.index]}');">${state[status.index]}</a></li>
													<c:if test="${fn:startsWith(state[status.index], thirdFQParam) and thirdFQParam != null}">
												</span> 
												</c:if>
											</c:forEach>
										</ul>
									</div>

									<span class="refineResultsItem plus" id="cityPlus">City</span>
									<c:if test="${fouthFQParam != null}">
									<script>$("#cityPlus").click();</script>
									</c:if>
									<div class="refineResultsSubContent">
										<ul>
										<c:forEach items="${city}" var="displayCity" varStatus="status">
											<c:if test="${fn:startsWith(city[status.index], fouthFQParam) and fouthFQParam != null}">
											<span style="font-weight:bold;">  
											</c:if>
											<li><a onclick="refineByCity('${city[status.index]}');">${city[status.index]}</a></li>
											<c:if test="${fn:startsWith(city[status.index], fouthFQParam) and fouthFQParam != null}">
											</span>
											</c:if>
										</c:forEach>
										</ul>
									</div>
								</div>
							<input type="hidden" name="selectedRadius" id="selectedRadius" value="${refineRadius}"/>
							<input type="hidden" name="selectedCompany" id="selectedCompany" value="${secondFQParam}" />
							<input type="hidden" name="selectedState" id="selectedState" value="${thirdFQParam}" />
							<input type="hidden" name="selectedCity" id="selectedCity" value="${fouthFQParam}" />
							</div>


						</div>
						<!-- column1 -->

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
							<c:if test="${totalNoOfRecords != null and totalNoOfRecords != 0}">
							${startRow} &#45; ${endRow} of ${totalNoOfRecords}&nbsp;
						</c:if>
						</div>
						<div class="searchResultsNavigationColumn2 GetNumPage">
						<!-- <span>Page:</span> -->
						<c:if test="${totalNoOfRecords != null and totalNoOfRecords != null}">
						<c:if test="${currentPage > 9 && noOfPages gt 10}">
							<td><a class="cursor"
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
                       				 <a onclick="getNextPage(${i});" class="cursor">${i}</a></c:if></span>
                   				 </c:otherwise>
               				 </c:choose> 
           				 </c:forEach>
           				 <c:if test="${(begin+10) lt noOfPages}">
           				 <span><a onclick="getNextPages(${begin + 10} ,${begin+10});" class="cursor"
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
						<li class="searchResultsColumn4">Date Posted
						<a class="cursor table-sorted-${sortOrder}" style="text-decoration: none;" id="sortingId" onclick="sortTable();">
						&nbsp;&nbsp;&nbsp;&nbsp;
						</a> 
						</li>
					</ul>
				</div>
				<div class="searchResultsListing">

					<div class="searchResultsItem">
					<c:forEach items="${searchResultsList}" var="job" varStatus="status">
					<%-- <form:hidden path="jobDTOs[${status.index}].jobId"/> --%>
								<ul
									<c:choose>
										<c:when test="${job.IsPremium == 0}">
										   class="searchResultsJobInfo closed" 
										</c:when>
										<c:otherwise>
									       class="searchResultsJobInfo closed orange-bg" 
										</c:otherwise>
									</c:choose>
									id="searchResultsJobInfo${job.JobId}"
									onclick="trackClick(${job.JobId});">
									<li class="searchResultsColumn1">${job.JobTitle}</li>

									<li class="searchResultsColumn2">${job.Company}</li>

									<li class="searchResultsColumn3"><c:if
											test="${!(job.HideCity == 1 || job.HideState == 1 || job.HideCountry == 1)}">
							${job.City}
							</c:if></li>

									<li class="searchResultsColumn4">${job.PostedDate}</li>
								</ul>
								<div class="searchResultsSubContent">

								<div class="searchResultsJobDescription">
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

								<div class="ShareSearch" >
								<span class="ShareText">Share:&nbsp;</span> 
								<span >
								<a name="fb_share" href="http://www.facebook.com/sharer.php">Share</a>
								</span>
								<span ><script type="IN/Share"></script>

								</span> 
								<span >
								<a href="https://twitter.com/share" class="twitter-share-button" 
								data-counturl="<%=request.getContextPath()%>/jobsearch/viewJobDetails.html?id=${job.JobId}&currentUrl=/jobboard/healthcarejobs/advanceweb.html"
								data-text="<%=request.getContextPath()%>/jobsearch/viewJobDetails.html?id=${job.JobId}&currentUrl=/jobboard/healthcarejobs/advanceweb.html">Tweet</a>
								</span>
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
							<c:if test="${totalNoOfRecords != null and totalNoOfRecords != 0}">
							${startRow} &#45; ${endRow} of ${totalNoOfRecords}&nbsp;
						</c:if>
						</div>
						<div class="searchResultsNavigationColumn2 GetNumPage">
						<!-- <span>Page: </span> -->
						<c:if test="${totalNoOfRecords != null}">
						<c:if test="${currentPage > 9 && noOfPages gt 10}">
							<td><a class="cursor"
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
                       				 <a onclick="getNextPage(${i});" class="cursor">${i}</a></c:if></span>
                   				 </c:otherwise>
               				 </c:choose> 
           				 </c:forEach>
           				 <c:if test="${(begin+10) lt noOfPages}">
           				 <span><a onclick="getNextPages(${begin + 10} ,${begin+10});"
							class="cursor">Next<img src="../resources/images/ArrowRight.png">
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
					</c:otherwise>
		</c:choose>
</body>
</html>
