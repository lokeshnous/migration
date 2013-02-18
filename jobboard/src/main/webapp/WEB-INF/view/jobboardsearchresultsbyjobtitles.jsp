<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${metaTitle}</title>
<meta name="description" content="${metaDesc}">
<meta name="robots" content="noindex, follow">
<link rel='shortcut icon' href='<%=request.getContextPath() %>/resources/images/favicon.ico' type="image/x-icon">
<c:if test="${page == 1}">
<link href="${canonicalUrl}" rel="canonical" />
</c:if>
<jsp:include page="common/include.jsp" />
</head>

<body class="job_board">
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">
			<div class="main">
				<form:form method="POST" action="" commandName="jobSearchResultForm">

					<div class="row">

						<div class="row marginTop10">
							<div class="row">
								<div id="errorMsg" class="FormErrorDisplayText"></div>
							</div>
							<%-- <div class="floatLeft width100P">
								<h1>
									<c:if test="${TotalNoRecords != null}">
										<span>${TotalNoRecords}</span>
									 jobs match your search criteria.
								</c:if>
								</h1>
							</div> --%>
						</div>
					</div>


					<div class="content_columns_search_results">

						<div class="column2 BorderLeftWhite alignCenter">
							<div class="searchResults ">
								<div class="searchResultsNavigation">

									<div class="searchResultsNavigationColumn1 SearchPerPage">
									</div>


									<div class="searchResultsNavigationColumn3 ViewNumPage">
										&nbsp;&nbsp;</div>
									<div class="searchResultsNavigationColumn2 GetNumPage">
										<!-- <span>Page:</span> -->
										<c:if
											test="${TotalNoRecords != null and TotalNoRecords != null}">
											<c:if test="${page != 1}">
												<td><a href="${prevPageUrl}?page=${page-1}">
														<img
														src="<%=request.getContextPath()%>/resources/images/ArrowLeft.png">
														Previous
												</a></td>
											</c:if>
											<span>
											<c:if
													test="${noOfPages > 1 and page < noOfPages }">
													<a href="${nextPageUrl}?page=${page+1}" class="cursor">Next<img
														src="<%=request.getContextPath()%>/resources/images/ArrowRight.png">
													</a>
												</c:if>
											</span>
										</c:if>
									</div>
								</div>


								<div class="searchResultsHeader">
									<ul>
										<li class="searchResultsColumn1">Job Title</li>
										<li class="searchResultsColumn2">Employer</li>
										<li class="searchResultsColumn3">Location</li>
										<li class="searchResultsColumn4">Date Posted </li>
									</ul>
								</div>
								<div class="searchResultsListing">

									<div class="searchResultsItem">
										<c:if
											test="${searchResultsList == null || searchResultsList.size() == 0}">
					No results found
					</c:if>
										<c:set var="adindex" scope="session" value="${0}" />
										<c:forEach items="${searchResultsList}" var="job"
											varStatus="status">
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
												>
												<%-- onclick="trackClick(${job.JobId});" --%>
												<li class="searchResultsColumn1"><a
													href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/search/jobview/
										${job.JobId}/${fn:toLowerCase(fn:replace(job.JobTitleEncode, 
                                					' ', '-'))}.html">${job.JobTitle}</a>
												</li>

												<li class="searchResultsColumn2"><a
													class="clickableLink">${job.Company}</a></li>

												<li class="searchResultsColumn3"><c:if
														test="${!(job.HideCity == 1 || job.HideState == 1 || job.HideCountry == 1)}">
							${job.City}
							</c:if></li>

												<li class="searchResultsColumn4">${job.PostedDate}</li>
											</ul>
										</c:forEach>
									</div>
								</div>


								<div
									class="searchResultsNavigation searchResultsNavigationBottom">

									<div class="searchResultsNavigationColumn1 SearchPerPage">
									</div>


									<div class="searchResultsNavigationColumn3 ViewNumPage">
										&nbsp;&nbsp;</div>
									<div class="searchResultsNavigationColumn2 GetNumPage"></div>
								</div>
								<div class="clearfix"></div>
							</div>

						</div>
						<!-- column2-->

					</div>
				</form:form>
				<br class="clearfix" />




			</div>
			<!-- main -->
		</div>

		<!-- end main_wrapper_inside -->
	</div>
	<!-- end main_wrapper_outside -->


</body>
</html>