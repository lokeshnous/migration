<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
                                                  prefix="fn" %>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${metaTitle}</title>
<meta name="description" content="${metaDesc}">
<link href="${canonicalUrl}" rel="canonical" />

<script src="<%=request.getContextPath()%>/resources/js/FB.Share" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/in.js" type="text/javascript"></script>
<script  src="<%=request.getContextPath()%>/resources/js/widgets.js"></script>
<jsp:include page="common/include.jsp" />

<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/searchResultsdatatable.js"></script>
<script type="text/javascript">
			jQuery(document).ready(function() {
				$("#descriptionText a").live("click", function() {     
                    trackClick('${jobDetail.jobId}','8');        
					if($(this).attr("target")== null || $(this).attr("target")=="_self"){
						$(this).attr("target","_blank");
					}
            	});
				jQuery(".megamenu").megamenu();
				window.onload = function() {
				};
			});
			function showMessage(){
				alert("You must be a registered Job-Seeker to apply to jobs");
			}
			function showSaveMessage(){
		    	alert("You must be a registered Job-Seeker to save the jobs");
		    }
		</script>
</head>

<body class="job_board_home">
	<div class="ad_page_top">${adPageTop}</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">


			<div class="main">
				<jsp:include page="../templates/templates_header.jsp"></jsp:include>
				<div class="ad_col_right">
					<div id="adPageRightTop"> ${adPageRightTop} </div>
					<div id="adPageRightMiddle"> ${adPageRightMiddle} </div>
					 <br class="clearfix" />

				</div>
				<!-- ad_col_right -->

				<div class="content_wrapper">

					<div class="jobDetails">

						<div class="jobDetailsEyebrow">

							<div class="floatLeft">
								<h3 class="jobDetailsEyebrowHeader">Job Details</h3>
								<input value="<%=request.getContextPath()%>" type="hidden" id="contextPath">
								<!-- <a name="fb_share" style="display: none;visibility: hidden;" href="http://www.facebook.com/sharer.php" target="_blank"></a> -->
							</div>
							<div class="floatRight">
								<%-- <a href="${returnResults}" class="link_color2_emphasized">Return to Search Results &nbsp; </a> --%>
								<c:choose>
									<c:when test="${isReturnResults}">
										<%-- <a href="${returnResults}" class="link_color2_emphasized">Return to Search Results &nbsp; </a> --%>
										<a 
											href='<%=request.getContextPath()%>/search/findJobPage.html'
											class="link_color2_emphasized">Return to Search Results
											&nbsp; </a>
									</c:when>
									<c:otherwise></c:otherwise>
								</c:choose>
							</div>


						</div>
						<div class="JobDetailHeaderLeft">
							<h1>
								<span>${jobDetail.jobTitle}</span>
							</h1>
							<h2 class="sectionSubHeader MarginBottom10">${jobDetail.companyNameDisp}</h2>
						</div>
						<div class="JobDetailHeaderRight">
							<c:if test="${(jobDetail.blindAd == '0') && isFeatureEmployer}">
	<a	href="<%=request.getContextPath()%>/healthcare/featuredemployerdetails.html?id=${jobDetail.facilityId}"><img
									onclick="trackClick(${jobDetail.jobId},'6');"
									src="<%=request.getContextPath()%>/resources/images/FeaturedEmp.png"
									alt="Featured Employer" width="164" height="23"></img> </a>
							</c:if>
						</div>
						<div class="jobDetailsIntro">
							<div class="rowpadding">
								<p>
									<c:if test="${jobDetail.hideCity == 0 && jobDetail.city != null}">
										<span class="specs">City:</span>&nbsp;&nbsp;${jobDetail.city}&nbsp;&nbsp;|&nbsp;&nbsp;
					</c:if>
									<c:if test="${jobDetail.hideState == 0 && jobDetail.city != null}">
										<span class="specs">State:</span>&nbsp;&nbsp;${jobDetail.state}&nbsp;&nbsp;|&nbsp;&nbsp;
					</c:if>
									<c:if test="${jobDetail.hideCountry == 0 && jobDetail.city != null}">
										<span class="specs">Country:</span>&nbsp;&nbsp;${jobDetail.country}&nbsp;&nbsp;|&nbsp;&nbsp;
					</c:if>
									<c:if test="${jobDetail.hidePostcode == 0 && jobDetail.city != null}">
										<span class="specs">ZIP Code:</span>&nbsp;&nbsp;${jobDetail.postCode}&nbsp;&nbsp;|&nbsp;&nbsp;
					</c:if>
									<span class="specs">Job ID Number:</span>&nbsp;&nbsp;${jobDetail.jobNumber}
									</p>
		<%-- <c:if test="${fn:contains(theString, 'test')}">
   <p>Found test string<p>
</c:if> --%>							<c:set var="urlString" value="${jobDetail.url}"/>
									<p class="jobDetailsIntroReview">
									<c:if test="${not empty jobDetail.url and not empty jobDetail.urlDisplay and jobDetail.urlDisplay!='None' and jobDetail.url!='None'}">
									<br/>
										<span class="specs">WEBSITE:</span>&nbsp;&nbsp;<a class="color2" target="_blank" onclick="trackClick(${jobDetail.jobId},'7');" href="${jobDetail.url}">${jobDetail.urlDisplay}</a>&nbsp;&nbsp;
					</c:if>
					<c:if test="${not empty jobDetail.url and  empty jobDetail.urlDisplay and jobDetail.url!='None' and fn:containsIgnoreCase(urlString, 'http')}">
									<br/>
										<span class="specs">WEBSITE:</span>&nbsp;&nbsp;<a class="color2" target="_blank" onclick="trackClick(${jobDetail.jobId},'7');" href="${jobDetail.url}">${jobDetail.url}</a>&nbsp;&nbsp;
					</c:if>
					<c:if test="${not empty jobDetail.url and  empty jobDetail.urlDisplay and jobDetail.url!='None' and !fn:containsIgnoreCase(urlString, 'http')}">
									<br/>
										<span class="specs">WEBSITE:</span>&nbsp;&nbsp;<a class="color2" target="_blank" onclick="trackClick(${jobDetail.jobId},'7');" href=" http://${jobDetail.url}">${jobDetail.url}</a>&nbsp;&nbsp;
					</c:if>
					<%-- <c:if test="${empty jobDetail.url and not empty jobDetail.urlDisplay and jobDetail.urlDisplay!='None'}">
									<br/>
										<span class="specs">Web Site:</span>&nbsp;&nbsp;<a class="color2" target="_blank" href="${jobDetail.urlDisplay}">${jobDetail.urlDisplay}</a>&nbsp;&nbsp;|&nbsp;&nbsp;
					</c:if> --%>
					<c:if test="${not empty jobDetail.email and not empty jobDetail.emailDisplay and jobDetail.emailDisplay!='None' and jobDetail.email!='None'}">
										|&nbsp;&nbsp;<span class="specs">Email:</span>&nbsp;&nbsp;<a class="color2" onclick="trackClick(${jobDetail.jobId},'5');" href="mailto: ${jobDetail.email}?subject=Apply%20via%20email">${jobDetail.emailDisplay}</a>
					</c:if>
					<%-- <c:if test="${empty jobDetail.email and not empty jobDetail.emailDisplay and jobDetail.emailDisplay!='None'}">
										<span class="specs">Email:</span>&nbsp;&nbsp;<a class="color2" target="_blank" href="mailto: ${jobDetail.emailDisplay}?subject=Apply%20via%20email">${jobDetail.emailDisplay}</a>
					</c:if> --%>
					<c:if test="${not empty jobDetail.email and empty jobDetail.emailDisplay and jobDetail.email!='None'}">
										|&nbsp;&nbsp;<span class="specs">Email:</span>&nbsp;&nbsp;<a class="color2" onclick="trackClick(${jobDetail.jobId},'5');" href="mailto: ${jobDetail.email}?subject=Apply%20via%20email">${jobDetail.email}</a>
					</c:if>
					</p>
					</div>
								
							</div>
							<div class="jobDetailsIntroOptions">
								<div class="rowEvenTB10Spacing">
									<div class="ShareText">Send to friend: &nbsp;</div>
									
										<a onclick="sendToFrd(${jobDetail.jobId}, '${jobDetail.jobTitle}');">
											<div class="email cursor"></div>
											</a>
									 <div class="ShareText">|&nbsp;&nbsp;Share:&nbsp;</div>
											<a class="fbook" onclick="trackClick(${jobDetail.jobId},'9');" href="http://www.facebook.com/sharer.php?u=${basePath}/search/jobview/${jobDetail.jobId}/${fn:toLowerCase(fn:replace(jobDetail.jobTitleEncode, 
                                					' ', '-'))}.html" target="_blank"></a>
									 		<a onclick="trackClick(${jobDetail.jobId},'9');" href="https://www.linkedin.com/cws/share?url=${basePath}/search/jobview/${jobDetail.jobId}/${fn:toLowerCase(fn:replace(jobDetail.jobTitleEncode, 
                                					' ', '-'))}.html" target="_blank"><div class="linkedIn"></div></a>
									 		<a onclick="trackClick(${jobDetail.jobId},'9');" href="https://twitter.com/share" class="twitter" data-url="${basePath}/search/jobview/${jobDetail.jobId}/${fn:toLowerCase(fn:replace(jobDetail.jobTitleEncode, 
                                					' ', '-'))}.html" data-count="none" target="_blank"></a>
	 		
									<div class="ShareText">|&nbsp;&nbsp;Print:&nbsp;</div>
									<a rel="nofollow,noindex" href="" onclick="trackPrint(${jobDetail.jobId},'3');">
									<div class="printJBdetail"></div>
									</a>
								</div>
								<div class="rowEvenTB10Spacing">
									<security:authorize	access=" !hasRole('ROLE_FACILITY') and !hasRole('ROLE_FACILITY_GROUP') and !hasRole('ROLE_FACILITY_SYSTEM')">
									<a
										onclick="selectResume(${jobDetail.jobId});"
										class="btn_sm orange">Apply Now</a>&nbsp;&nbsp;&nbsp;&nbsp; <a
										onclick="saveThisJob(${jobDetail.jobId})"
										id="saveThisJobId" class="btn_sm orange">SAVE THIS JOB</a>
								</security:authorize>
								<security:authorize	access="hasRole('ROLE_FACILITY') or hasRole('ROLE_FACILITY_GROUP') or hasRole('ROLE_FACILITY_SYSTEM')">
									<a
										onclick="showMessage();"
										class="btn_sm orange">Apply Now</a>&nbsp;&nbsp;&nbsp;&nbsp; <a
										onclick="showSaveMessage();"
										id="saveThisJobId" class="btn_sm orange">SAVE THIS JOB</a>
								</security:authorize>
								</div>

								 <div class="rowEvenNewSpacing">
								<div class="FormErrorDisplayText" id="topjobActionInfo"></div>
								</div>
								<c:if test="${not empty jobDetail.headLine}">
								<div class="rowPadding">
									<p>
										<span class="detailHeaderOrange"> HEADLINE:&nbsp;</span>
																
								${jobDetail.headLine}</p></div> 
								</c:if>
								<c:if test="${not empty jobDetail.positionLevel}">
								<div class="rowPadding">
								<p> <span class="detailHeaderOrange">
									POSITION LEVEL:
								</span>
								${jobDetail.positionLevel}</p>
								</div>
								</c:if>
								<c:if test="${not empty jobDetail.positionType}">
								<div class="rowPadding">
								<p>
									<span class="detailHeaderOrange">POSITION TYPE:</span>
								
								${jobDetail.positionType}</p>
								</div>
								</c:if>
								<h3 class="jobSummaryTitle">
									<span>Job Summary:</span>
								</h3>
								<div id="descriptionText" class="article">${jobDetail.adText}</div>
								<br />
								<div>
									<c:if test="${jobDetail.trackingPixel != ''}">
										<img src="${jobDetail.trackingPixel}" />
									</c:if>
								</div>
								<div class="jobDetailsIntroOptionsTborder">
									<div class="jobDetailsIntroOptions">
										<div class="rowEvenTB10Spacing">
											<div class="ShareText">Send to friend:&nbsp;</div>
											<a onclick="sendToFrd(${jobDetail.jobId}, '${jobDetail.jobTitle}','<%= request.getContextPath() %>');">
											<div class="email cursor"></div></a>
											<div class="ShareText">|&nbsp;&nbsp;Share:&nbsp;</div>
											
											<a class="fbook" onclick="trackClick(${jobDetail.jobId},'9');" href="http://www.facebook.com/sharer.php?u=${basePath}/search/jobview/${jobDetail.jobId}/${fn:toLowerCase(fn:replace(jobDetail.jobTitleEncode, 
                                					' ', '-'))}.html" target="_blank"></a>
									 		<a onclick="trackClick(${jobDetail.jobId},'9');" href="https://www.linkedin.com/cws/share?url=${basePath}/search/jobview/${jobDetail.jobId}/${fn:toLowerCase(fn:replace(jobDetail.jobTitleEncode, 
                                					' ', '-'))}.html" target="_blank"><div class="linkedIn"></div></a>
									 		<a onclick="trackClick(${jobDetail.jobId},'9');" href="https://twitter.com/share" class="twitter" data-url="${basePath}/search/jobview/${jobDetail.jobId}/${fn:toLowerCase(fn:replace(jobDetail.jobTitleEncode, 
                                					' ', '-'))}.html" data-count="none" target="_blank"></a>
									 		
													
											<div class="ShareText">|&nbsp;&nbsp;Print:&nbsp;</div>
											<a href="" onclick="trackPrint(${jobDetail.jobId},'3');">
											<div class="printJBdetail"></div>
											</a>
										</div>
										<div class="rowEvenTB10Spacing">
										<security:authorize	access=" !hasRole('ROLE_FACILITY') and !hasRole('ROLE_FACILITY_GROUP') and !hasRole('ROLE_FACILITY_SYSTEM')">
											<a
												onclick="bottomSelectResume(${jobDetail.jobId},'<%= request.getContextPath() %>');"
												class="btn_sm orange">Apply Now</a>&nbsp;&nbsp;&nbsp;&nbsp;
											<a
												onclick="btsaveThisJob(${jobDetail.jobId},'<%= request.getContextPath() %>');"
												id="btsaveThisJobId" class="btn_sm orange">SAVE THIS
												JOB</a>
												</security:authorize>
										
										<security:authorize	access="hasRole('ROLE_FACILITY') or hasRole('ROLE_FACILITY_GROUP') or hasRole('ROLE_FACILITY_SYSTEM')">
											<a
												onclick="showMessage();"
												class="btn_sm orange">Apply Now</a>&nbsp;&nbsp;&nbsp;&nbsp;
											<a
												onclick="showSaveMessage();"
												id="btsaveThisJobId" class="btn_sm orange">SAVE THIS
												JOB</a>
												</security:authorize>
										</div>
										<br />
										<br />
										<div class="FormErrorDisplayText" id="bottomjobActionInfo"></div>
										<br />
										<br />
										<br />
									</div>
								</div>


							</div>
						</div>
						<br class="clearfix" />

					</div>
					<!-- content_wrapper -->

					<div class="ad_wrapper">${adPageBottom }</div>
					<!-- ad_wrapper -->

				</div>
				<!-- main -->

			</div>
			<!-- end main_wrapper_inside -->
		</div>
		<!-- end main_wrapper_outside -->
		<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
</body>
</html>