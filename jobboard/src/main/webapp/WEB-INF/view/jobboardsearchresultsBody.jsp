<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/expandCollapse.js"></script>
<script type="text/javascript">
function showMessage(){
	alert("You must be a registered Job-Seeker to apply to jobs");
}
function showSaveMessage(){
	alert("You must be a registered Job-Seeker to save the jobs");
}
function showSaveSearchMessage(){
	alert("You must be a registered Job-Seeker to save the searches");
}
</script>
		<c:choose>
		<c:when test="${jobTitlePage}">
			<jsp:include page="browseByJobTitle.jsp"></jsp:include>
		</c:when>
		<c:when test="${employerPage}">
			<jsp:include page="browseByEmployer.jsp"></jsp:include>
		</c:when> 
		<c:when test="${locationPage}">
		    <c:choose>
		       <c:when test="${areaPage}">
		       		<jsp:include page="browseByLocationRegions.jsp"></jsp:include>
		       </c:when>
		       <c:otherwise>
		         <jsp:include page="browseByLocation.jsp"></jsp:include>
		       </c:otherwise>
		    </c:choose>			
		</c:when> 		
		<c:otherwise>
			<form:form method="POST" action="" commandName="jobSearchResultForm" id="jobSearchResultBodyFormId"> 
					<!-- <div id="connectionStatus" class="FormErrorDisplayText"></div> -->
					<div class="row">

						<div class="row paddingBottom05 marginBottom10">
						
						<!-- <div class="row"><div id="errorMsg" class="FormErrorDisplayText"></div></div> -->
						
							<div class="floatLeft width100P">
								<h1>
								<c:if test="${searchedJobCount != null}">
									<%-- <span>${searchedJobCount}</span> ${jobSearchResultForm.keywords} jobs match your search criteria. --%>
									 ${jobSearchMatchInfo }
								</c:if>
								</h1>
							</div>
						</div>
					</div>

					
					<div class="content_columns_search_results">
						<div class="column1">
							<div class="section">
							<c:if test="${currentSearchList != null && not empty currentSearchList}">
								<h3>Current Search</h3>
								<c:forEach items="${currentSearchList}" var="item">
									<div class="buttonRow">
										${item.value}
										<div class="floatRight">
											<a onclick="deleteCurrentSearch('${item.key}','${item.value}')" class="cursor"><img src="<%=request.getContextPath()%>/resources/images/Close.png"
												alt="close" width="15" height="15"> </a>
										</div>
									</div>
								</c:forEach>
								<security:authorize 
		access="!hasRole('ROLE_FACILITY') and !hasRole('ROLE_FACILITY_GROUP') and !hasRole('ROLE_FACILITY_SYSTEM')">
								<div class="section">
									<div class="SaveSearchButton">
										<a  rel="nofollow" href="#" class="btn_sm orange nyroModal" id="saveThisSearchId" onclick="saveThisSearch();">Save This Search</a>
									</div>
								</div>
								</security:authorize>
								<security:authorize 
		access="hasRole('ROLE_FACILITY') or hasRole('ROLE_FACILITY_GROUP') or hasRole('ROLE_FACILITY_SYSTEM')">
								<div class="section">
									<div class="SaveSearchButton">
										<a  rel="nofollow" href="#" class="btn_sm orange nyroModal" id="saveThisSearchId" onclick="showSaveSearchMessage()">Save This Search</a>
									</div>
								</div>
								</security:authorize>
								</c:if>
							</div>
							<div class="section">
								<h3
								<c:if test="${(empty company) and (empty state) and (empty city) and (!displayRadius)}"> 
								style=" visibility: hidden;"
								</c:if>
								>
								Refine Results
								</h3>

								<div class="refineResults">
									<c:if test="${displayRadius}">
									<c:if test="${sessionMap.get('refineRadius') != '0' && (not empty sessionMap.get('refineRadius'))}">
									<script type="text/javascript">$("#radiusPlus").click();</script>
									</c:if>
									
									<span class="refineResultsItem plus" id="radiusPlus">Radius</span>

									<div class="refineResultsSubContent cursor">
										<ul>
											<c:choose>
												<c:when test="${sessionMap.get('refineRadius')== '5'}">
													<li><a onclick="refineByRadius('5', 'true')" style="font-weight:bold;color: #FE9400;">5 Miles</a></li>
												</c:when>
												<c:otherwise>
													<li><a onclick="refineByRadius('5', 'false')" >5 Miles</a></li>
												</c:otherwise>
											</c:choose>
											
											<c:choose>
												<c:when test="${sessionMap.get('refineRadius')== '10'}">
													<li><a onclick="refineByRadius('10', 'true')" style="font-weight:bold;color: #FE9400;">10 Miles</a></li>
												</c:when>
												<c:otherwise>
													<li><a onclick="refineByRadius('10', 'false')" >10 Miles</a></li>
												</c:otherwise>
											</c:choose>
											
											<c:choose>
												<c:when test="${sessionMap.get('refineRadius')== '25'}">
													<li><a onclick="refineByRadius('25', 'true')" style="font-weight:bold;color: #FE9400;">25 Miles</a></li>
												</c:when>
												<c:otherwise>
													<li><a onclick="refineByRadius('25', 'false')" >25 Miles</a></li>
												</c:otherwise>
											</c:choose>
											
											<c:choose>
												<c:when test="${sessionMap.get('refineRadius')== '50'}">
													<li><a onclick="refineByRadius('50', 'true')" style="font-weight:bold;color: #FE9400;">50 Miles</a></li>
												</c:when>
												<c:otherwise>
													<li><a onclick="refineByRadius('50', 'false')" >50 Miles</a></li>
												</c:otherwise>
											</c:choose>
											
											<c:choose>
												<c:when test="${sessionMap.get('refineRadius')== '100'}">
													<li><a onclick="refineByRadius('100', 'true')" style="font-weight:bold;color: #FE9400;">100 Miles</a></li>
												</c:when>
												<c:otherwise>
													<li><a onclick="refineByRadius('100', 'false')" >100 Miles</a></li>
												</c:otherwise>
											</c:choose>
										</ul>
									</div>
									</c:if>
									<c:if test="${sessionMap.get('secondFQParam') != ''}">
									<script type="text/javascript">$("#companyPlus").click();</script>
									</c:if> 
									<c:if test="${not empty company}"> 
									<span class="refineResultsItem plus" id="companyPlus">Employer</span>
									<div class="refineResultsSubContent cursor">
										<ul>
											<c:forEach items="${company}" var="displayCompany" varStatus="status" >
											<c:choose>
											<%-- <c:when test="${sessionMap.get('secondFQParam') != '' and  fn:startsWith(fn:toLowerCase(company[status.index]), fn:split(sessionMap.get('secondFQParam'), '\"')[1])}">
											<span style="font-weight:bold;"> 
											<c:choose>
											<c:when test="${fn:startsWith(fn:toLowerCase(browseByEmployer) , fn:split(sessionMap.get('secondFQParam'), '\"')[1])}">
												<li>${company[status.index]}</li>
											</c:when> --%>
											<c:when test="${sessionMap.get('secondFQParam') != '' and  fn:startsWith(company[status.index], fn:split(sessionMap.get('secondFQParam'), '\"')[1])}">
											<span style="font-weight:bold;"> 
											<c:choose>
											<c:when test="${fn:startsWith(browseByEmployer , fn:split(sessionMap.get('secondFQParam'), '\"')[1])}">
												<li>${company[status.index]}</li>
											</c:when>
											<c:otherwise>
												<li><a onclick="refineByCompany('${company[status.index]}', 'true');">${company[status.index]}</a></li>
											</c:otherwise>
											</c:choose>
											</span>
											</c:when>
											<c:otherwise>
												<li><a onclick="refineByCompany('${company[status.index]}', 'false');">${company[status.index]}</a></li>
											</c:otherwise>
											</c:choose>
											</c:forEach>
										</ul>
									</div>
									</c:if>
									
									<input type="hidden" name="refined" id="refined" />
									<c:if test="${sessionMap.get('thirdFQParam') != ''}">
									 <script type="text/javascript">$("#statePlus").click();</script>
									</c:if>
									<c:if test="${not empty state}"> 
									<span class="refineResultsItem plus" id="statePlus">State</span>
									<div class="refineResultsSubContent cursor">
										<ul>
											<c:forEach items="${state}" var="displayState" varStatus="status">
											<c:choose>
											<%-- <c:when test="${sessionMap.get('thirdFQParam') != '' and fn:startsWith(fn:toLowerCase(state[status.index]),fn:split(sessionMap.get('thirdFQParam'), '\"')[1])}">
											<span style="font-weight:bold;"> 
											<c:choose>
											<c:when test="${fn:startsWith(fn:toLowerCase(browseBystate) , fn:split(sessionMap.get('thirdFQParam'), '\"')[1])}">
												<li>${state[status.index]}</li>
											</c:when> --%>
											<c:when test="${sessionMap.get('thirdFQParam') != '' and fn:startsWith(state[status.index],fn:split(sessionMap.get('thirdFQParam'), '\"')[1])}">
											<span style="font-weight:bold;"> 
											<c:choose>
											<c:when test="${fn:startsWith(browseBystate , fn:split(sessionMap.get('thirdFQParam'), '\"')[1])}">
												<li>${state[status.index]}</li>
											</c:when>
											<c:otherwise>
												<li><a onclick="refineByState('${state[status.index]}','true');">${state[status.index]}</a></li>
											</c:otherwise>
											</c:choose>
											</span>
											</c:when>
											<c:otherwise>
												<li><a onclick="refineByState('${state[status.index]}','false');">${state[status.index]}</a></li>
											</c:otherwise>
											</c:choose>
												
											</c:forEach>
										</ul>
									</div>
									</c:if>
									<c:if test="${not empty city}"> 
									<span class="refineResultsItem plus" id="cityPlus">City</span>
									<c:if test="${sessionMap.get('fouthFQParam') != ''}">
									 <script type="text/javascript">$("#cityPlus").click();</script>
									</c:if>
									<div class="refineResultsSubContent cursor">
										<ul>
										<c:forEach items="${city}" var="displayCity" varStatus="status">
											<c:choose>
											<c:when test="${sessionMap.get('fouthFQParam') != '' and fn:startsWith(city[status.index], fn:split(sessionMap.get('fouthFQParam'), '\"')[1])}">
											<span style="font-weight:bold;"> 
												<li><a onclick="refineByCity('${city[status.index]}', 'true');">${city[status.index]}</a></li>
											</span>
											</c:when>
											<c:otherwise>
												<li><a onclick="refineByCity('${city[status.index]}', 'false');">${city[status.index]}</a></li>
											</c:otherwise>
											</c:choose>
											
										</c:forEach>
										</ul>
									</div>
									</c:if>
								</div>
							</div>


						</div>
						<!-- column1 -->

		<div class="column2">
<div class="searchResults">
				<div class="searchResultsNavigation">

		<div class="searchResultsNavigationColumn1 SearchPerPage">
		<input type="hidden" value="${beginVal}"/>
			<span class="ShareText">Results viewable:</span> <span
				class="Padding0"> 
				<select name="noOfPage" id="noOfPage"
						class="jb_input4 margin0 resuViewable" onchange="applyFilter();">
					<option value="20">20</option>
					<option value="30">30</option>
					<option value="40">40</option>
					<option value="50">50</option>
				</select>						
						</span><span class="ShareText">per page</span>
		</div>


						<div class="searchResultsNavigationColumn3 ViewNumPage">
							&nbsp;&nbsp;
							<c:if test="${searchedJobCount != null and searchedJobCount != '0'}">
							${startRow} &#45; ${endRow} of ${searchedJobCount}&nbsp;
						</c:if>
						</div>
						<div class="searchResultsNavigationColumn2 GetNumPage">
						<span>Page:</span>
						<c:if test="${searchedJobCount != null and searchedJobCount != '0'}">
						<c:if test="${currentPage > 10 && noOfPages gt 10}">
							<td><a class="cursor"
								onclick="getPrevPages(${begin - 10});">
									<img src="<%=request.getContextPath()%>/resources/images/ArrowLeft.png"> Previous
							</a></td>
						</c:if>

						<c:forEach begin="${begin}" end="${noOfPages}" var="i">
               				 <c:choose>
                  				  <c:when test="${currentPage eq i}">
									<span class="active">${i}</span>
									</c:when>
                   				 <c:otherwise>
                       				 <span><c:if test="${i lt begin+10}">
                       				 <a onclick="getPage(${i}, ${begin});" class="cursor">${i}</a></c:if></span>
                   				 </c:otherwise>
               				 </c:choose> 
           				 </c:forEach>
           				 <c:if test="${(begin+10) <= noOfPages}">
           				 <span><a onclick="getNextPages(${begin + 10});" class="cursor"
							>Next<img src="<%=request.getContextPath()%>/resources/images/ArrowRight.png">
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

					<div class="searchResultsItem" id="searchResultsItem">
					<c:if test="${searchResultsList == null || searchResultsList.size() == 0}">
					No results found
					</c:if>
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
									onclick="trackClick(${job.JobId},'1');">
									<li class="searchResultsColumn1"><a class="clickableLink">${job.JobTitle}</a></li>

									<li class="searchResultsColumn2"><a class="clickableLink">${job.Company}</a></li>

									<li class="searchResultsColumn3">
									<%-- <c:if
											test="${!(job.HideCity == 1 || job.HideState == 1 || job.HideCountry == 1)}"> --%>
							${job.City}
							<%-- </c:if> --%></li>

									<li class="searchResultsColumn4">${job.PostedDate}</li>
								</ul>
								<div class="searchResultsSubContent">

								<div class="searchResultsJobDescription">
								<p class="searchResultsSubContentJobDescription">								
									<span class="bold">Job Description:</span>
									${job.AdText}
								</p>
								</div>
<security:authorize 
		access="!hasRole('ROLE_FACILITY') and !hasRole('ROLE_FACILITY_GROUP') and !hasRole('ROLE_FACILITY_SYSTEM')">
								<div class="searchResultsSubContentButtonArea">
									<div class="searchResultsSubContentButtons">
										<a onclick="selectResume(${job.JobId});" class="btn_sm white"
											id="applyJobid${job.JobId}">Apply</a>
									</div>
									<div class="searchResultsSubContentButtons">
										<a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/search/jobview/
										${job.JobId}/${fn:toLowerCase(fn:replace(job.JobTitleEncode, 
                                					' ', '-'))}.html" class="btn_sm white">View
											Details</a>
									</div>
									<div class="searchResultsSubContentButtons">
										<a onclick="saveThisJob(${job.JobId})"
											id="saveThisJobId${job.JobId}"
											class="btn_sm white">Save This Job</a>
									</div>
								</div>
								</security:authorize>
								
								<security:authorize 
		access="hasRole('ROLE_FACILITY') or hasRole('ROLE_FACILITY_GROUP') or hasRole('ROLE_FACILITY_SYSTEM')">
								<div class="searchResultsSubContentButtonArea">
									<div class="searchResultsSubContentButtons">
										<a onclick="showMessage();" class="btn_sm white"
											id="applyJobid${job.JobId}" style="cursor: default;">Apply</a>
									</div>
									<div class="searchResultsSubContentButtons">
										<a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/search/jobview/
										${job.JobId}/${fn:toLowerCase(fn:replace(job.JobTitleEncode, 
                                					' ', '-'))}.html" class="btn_sm white">View
											Details</a>
									</div>
									<div class="searchResultsSubContentButtons">
										<a onclick="showSaveMessage();"
											id="saveThisJobId${job.JobId}"
											class="btn_sm white" style="cursor: default;">Save This Job</a>
									</div>
								</div>
								</security:authorize>
								
								<div class="featured_empButton">
									<c:choose>
										<c:when test="${(job.BlindAd == '0') && job.IsFeatured}">
											<%-- <a href="<%=request.getContextPath()%>/healthcare/featuredemployerdetails.html?id=${job.facilityId}"> <img src="<%=request.getContextPath()%>/resources/images/FeaturedEmp.png"
												alt="featured emp Button" width="164" height="23"></a> --%>
									<a
										href="<%=request.getContextPath()%>/healthcare/featuredemployerdetails.html?id=${job.facilityId}"><img
										onclick="trackClick(${job.JobId},'6');"
										src="<%=request.getContextPath()%>/resources/images/FeaturedEmp.png"
										alt="Featured Employer" width="164" height="23"></img> </a>

								</c:when>
										<c:otherwise>
											<img src="${basePath}/resources/images/tranBg.png"
												alt="featured emp Button" width="164" height="23">
										</c:otherwise>
									</c:choose>
								</div>

								<div class="ShareSearch">
									<span class="ShareText"> Send to
										friend:&nbsp;</span><span><a onclick="sendToFrd(${job.JobId}, '${job.jobTitle}');"><span
												class="email cursor"></span></a></span>
								</div>

								<div class="ShareSearch" >
								<div class="ShareText">&nbsp;&nbsp;Share:&nbsp;</div>
								<a class="fbook" onclick="trackClick(${job.JobId},'9');" href="http://www.facebook.com/sharer.php?u=${basePath}/search/jobview/${job.JobId}/${fn:toLowerCase(fn:replace(job.JobTitleEncode, 
                                					' ', '-'))}.html" target="_blank"></a>
								<a onclick="trackClick(${job.JobId},'9');" href="https://www.linkedin.com/cws/share?url=${basePath}/search/jobview/${job.JobId}/${fn:toLowerCase(fn:replace(job.JobTitleEncode, 
                                					' ', '-'))}.html" target="_blank"><div class="linkedIn"></div></a>
								<%-- <a onclick="trackClick(${job.JobId},'9');" href="https://twitter.com/share" class="twitter" data-url="${basePath}/search/jobview/${job.JobId}/${fn:toLowerCase(fn:replace(job.JobTitleEncode, 
                                					' ', '-'))}.html" data-count="vertical" target="_blank"></a> --%>
								<a onclick="trackClick(${job.JobId},'9');" href="http://twitter.com/home?status=${basePath}/search/jobview/${job.JobId}/${fn:toLowerCase(fn:replace(job.JobTitleEncode, 
                                					' ', '-'))}.html" target="_blank" class="twitter" ></a>
								
							</div>
							
							<div class="FormErrorDisplayText row margin5" id="topjobActionInfo${job.JobId}" ></div>
							
						</div>
				 <c:if test="${(status.index + 1) % 10 == 0}"> 
					<div class="ad_wrapper" id="ad_wrapper${fn:split((status.index + 1) / 10, '.')[0]}">
						${adPageCenterMiddleList[fn:split(((status.index + 1) / 10)-1, '.')[0]] }
					</div>  					
				</c:if>
			</c:forEach>
			
					</div>
				</div>


				<div class="searchResultsNavigation searchResultsNavigationBottom">

		<div class="searchResultsNavigationColumn1 SearchPerPage">
			<span class="ShareText">Results viewable:</span> <span
				class="Padding0">
			 <select name="results" id="noOfPageLower"
				class="jb_input4 margin0 resuViewable" onchange="applyLowerFilter();">
					<option value="20">20</option>
					<option value="30">30</option>
					<option value="40">40</option>
					<option value="50">50</option>
			</select>
			</span><span class="ShareText">per page</span>
		</div>


						<div class="searchResultsNavigationColumn3 ViewNumPage">
							&nbsp;&nbsp;
							<c:if test="${searchedJobCount != null and searchedJobCount != '0'}">
							${startRow} &#45; ${endRow} of ${searchedJobCount}&nbsp;
						</c:if>
						</div>
						<div class="searchResultsNavigationColumn2 GetNumPage">
						<span>Page: </span>
						<c:if test="${searchedJobCount != null and searchedJobCount != '0'}">
						<c:if test="${currentPage > 10 && noOfPages gt 10}">
							<td><a class="cursor"
								onclick="getPrevPages(${begin - 10})">
									<img src="<%=request.getContextPath()%>/resources/images/ArrowLeft.png"> Previous
							</a></td>
						</c:if>

						<c:forEach begin="${begin}" end="${noOfPages}" var="i">
               				 <c:choose>
                  				  <c:when test="${currentPage eq i}">
									<span class="active">${i}</span>
									</c:when>
                   				 <c:otherwise>
                       				 <span><c:if test="${i lt begin+10}">
                       				 <a onclick="getPage(${i}, ${begin});" class="cursor">${i}</a></c:if></span>
                   				 </c:otherwise>
               				 </c:choose> 
           				 </c:forEach>
           				 <c:if test="${(begin+10) <= noOfPages}">
           				 <span><a onclick="getNextPages(${begin + 10});"
							class="cursor">Next<img src="<%=request.getContextPath()%>/resources/images/ArrowRight.png">
							</a></span>
       					</c:if>  
       					</c:if>   
					</div>
				</div>
			<div class="clearfix"></div>
			</div>
		
		</div>
		<!-- column2-->

					</div> 
					</form:form>
					<input value="false" type="hidden" id="isSortGrid" />
					</c:otherwise>
		</c:choose>
		<div class="clearfix"></div>
