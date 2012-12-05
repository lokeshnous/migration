<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
                                                  prefix="fn" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/expandCollapse.js"></script>
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
					
					<div class="row">

						<div class="row marginTop5 paddingBottom05">
						<div class="row">
						<div id="errorMsg" class="FormErrorDisplayText"></div>
						</div>
							<div class="floatLeft width100P">
								<h1>
								<c:if test="${searchedJobCount != null}">
									<%-- <span>${searchedJobCount}</span> ${jobSearchResultForm.keywords} jobs match your search criteria. --%>
									<span>${searchedJobCount}</span> ${jobSearchMatchInfo }
								</c:if>
								</h1>
							</div>
						</div>
					</div>

					
					<div class="content_columns_search_results">
						<div class="column1">
							<div class="section">
							<c:if test="${currentSearchList != null}">
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
									<c:if test="${sessionMap.get('refineRadius') != '0'}">
									<script type="text/javascript">$("#radiusPlus").click();</script>
									</c:if>

									<div class="refineResultsSubContent cursor">
										<ul>
											<c:choose>
												<c:when test="${sessionMap.get('refineRadius')== '5'}">
												<span style="font-weight:bold;"> 
													<li><a onclick="refineByRadius('5', 'true')" >5 Miles</a></li>
												</span>
												</c:when>
												<c:otherwise>
													<li><a onclick="refineByRadius('5', 'false')" >5 Miles</a></li>
												</c:otherwise>
											</c:choose>
											
											<c:choose>
												<c:when test="${sessionMap.get('refineRadius')== '10'}">
												<span style="font-weight:bold;"> 
													<li><a onclick="refineByRadius('10', 'true')" >10 Miles</a></li>
												</span>
												</c:when>
												<c:otherwise>
													<li><a onclick="refineByRadius('10', 'false')" >10 Miles</a></li>
												</c:otherwise>
											</c:choose>
											
											<c:choose>
												<c:when test="${sessionMap.get('refineRadius')== '25'}">
												<span style="font-weight:bold;"> 
													<li><a onclick="refineByRadius('25', 'true')" >25 Miles</a></li>
												</span>
												</c:when>
												<c:otherwise>
													<li><a onclick="refineByRadius('25', 'false')" >25 Miles</a></li>
												</c:otherwise>
											</c:choose>
											
											<c:choose>
												<c:when test="${sessionMap.get('refineRadius')== '50'}">
												<span style="font-weight:bold;"> 
													<li><a onclick="refineByRadius('50', 'true')" >50 Miles</a></li>
												</span>
												</c:when>
												<c:otherwise>
													<li><a onclick="refineByRadius('50', 'false')" >50 Miles</a></li>
												</c:otherwise>
											</c:choose>
											
											<c:choose>
												<c:when test="${sessionMap.get('refineRadius')== '100'}">
												<span style="font-weight:bold;"> 
													<li><a onclick="refineByRadius('100', 'true')" >100 Miles</a></li>
												</span>
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
									<span class="refineResultsItem plus" id="companyPlus">Employer</span>
									<div class="refineResultsSubContent cursor">
										<ul>
											<c:forEach items="${company}" var="displayCompany" varStatus="status" >
											<c:choose>
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
									
									<input type="hidden" name="refined" id="refined" />
									<c:if test="${sessionMap.get('thirdFQParam') != ''}">
									 <script type="text/javascript">$("#statePlus").click();</script>
									</c:if>
									<span class="refineResultsItem plus" id="statePlus">State</span>
									<div class="refineResultsSubContent cursor">
										<ul>
											<c:forEach items="${state}" var="displayState" varStatus="status">
											<c:choose>
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
											
											<%-- <c:if test="${fn:startsWith(city[status.index], fouthFQParam) and fouthFQParam != null}">
											<span style="font-weight:bold;">  
											</c:if>
											<li><a onclick="refineByCity('${city[status.index]}');">${city[status.index]}</a></li>
											<c:if test="${fn:startsWith(city[status.index], fouthFQParam) and fouthFQParam != null}">
											</span>
											</c:if> --%>
										</c:forEach>
										</ul>
									</div>
								</div>
							<%-- <input type="text" name="selectedRadius" id="selectedRadius" value="${refineRadius}"/>
							<input type="text" name="selectedJobtitle" id="selectedJobtitle" value="${firstFQParam}"/>
							<input type="text" name="selectedCompany" id="selectedCompany" value="${secondFQParam}" />
							<input type="text" name="selectedState" id="selectedState" value="${thirdFQParam}" />
							<input type="text" name="selectedCity" id="selectedCity" value="${fouthFQParam}" />
							<input type="text" name="selectedArea" id="selectedArea" value="${fifthFQParam}" /> --%>
							<%-- <div id="selectedRadius" style="display: none">${refineRadius}</div>
							<div id="selectedJobtitle" style="display: none">${firstFQParam}</div>
							<div id="selectedCompany"  style="display: none">${secondFQParam}</div>
							<div id="selectedState" style="display: none">${thirdFQParam}</div>
							<div id="selectedCity"  style="display: none">${fouthFQParam}</div>
							<div id="selectedArea"  style="display: none">${fifthFQParam}</div>   --%>
							
							<%-- <div id="selectedRadius" >${refineRadius}</div>
							<div id="selectedJobtitle" >${firstFQParam}</div>
							<div id="selectedCompany" >${secondFQParam}</div>
							<div id="selectedState" >${thirdFQParam}</div>
							<div id="selectedCity" >${fouthFQParam}</div>
							<div id="selectedArea" >${fifthFQParam}</div>  --%>
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
							<c:if test="${searchedJobCount != null and searchedJobCount != 0}">
							${startRow} &#45; ${endRow} of ${searchedJobCount}&nbsp;
						</c:if>
						</div>
						<div class="searchResultsNavigationColumn2 GetNumPage">
						<!-- <span>Page:</span> -->
						<c:if test="${searchedJobCount != null and searchedJobCount != 0}">
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
									onclick="trackClick(${job.JobId});">
									<li class="searchResultsColumn1"><a class="clickableLink">${job.JobTitle}</a></li>

									<li class="searchResultsColumn2"><a class="clickableLink">${job.Company}</a></li>

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
										<a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/jobsearch/jobview/
										${job.JobId}/${fn:toLowerCase(fn:replace(job.JobTitle, 
                                					' ', '-'))}.html" class="btn_sm white">View
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
											<!-- <a href=""> --><img src="<%=request.getContextPath()%>/resources/images/FeaturedEmp.png"
												alt="featured emp Button" width="164" height="23"><!-- </a> -->
										</c:when>
										<c:otherwise>
											<img src="<%=request.getContextPath()%>/resources/images/tranBg.png"
												alt="featured emp Button" width="164" height="23">
										</c:otherwise>
									</c:choose>
								</div>

								<div class="ShareSearch">
									<span class="ShareText"> Send to
										Friend:&nbsp;</span><span><a onclick="sendToFrd(${job.JobId}, '${job.jobTitle}');"><span
												class="email cursor"></span></a></span>
								</div>

								<!-- <div class="searchResultsSubContentShare">
									<span class="marginTop3 floatLeft"> Send to
										Friend:&nbsp;</span><span><a href=""><img
											src="../resources/images/email.png"></a></span>
								</div> -->

								<div class="ShareSearch" >
								
								<a name="fb_share" class="fbook" href="http://www.facebook.com/sharer.php?u=${basePath}/jobsearch/viewJobDetails/${job.JobId}/${job.JobTitle}.html" target="_blank"></a>
								<a href="https://www.linkedin.com/cws/share?url=${basePath}/jobsearch/viewJobDetails/${job.JobId}/${job.JobTitle}.html" target="_blank"><div class="linkedIn"></div></a>
								<a href="https://twitter.com/share" class="twitter" data-url="${basePath}/jobsearch/viewJobDetails/${job.JobId}/${job.JobTitle}.html" data-count="none" target="_blank"></a>
								
							</div>
							
							<div class="FormErrorDisplayText row" id="topjobActionInfo${job.JobId}" ></div>
							
						</div>
						<%-- <c:if test="${(status.index + 1) % 10 == 0}"> 
					<div class="ad_wrapper">
						${adPageCenterMiddleList[((status.index + 1) / 10 -1)] }
					</div> 					
				</c:if> --%>
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
							<c:if test="${searchedJobCount != null and searchedJobCount != 0}">
							${startRow} &#45; ${endRow} of ${searchedJobCount}&nbsp;
						</c:if>
						</div>
						<div class="searchResultsNavigationColumn2 GetNumPage">
						<!-- <span>Page: </span> -->
						<c:if test="${searchedJobCount != null and searchedJobCount != 0}">
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
					<input value="false" type="hidden" id="isSortGrid">
					</c:otherwise>
		</c:choose>
		<div class="clearfix"></div>
