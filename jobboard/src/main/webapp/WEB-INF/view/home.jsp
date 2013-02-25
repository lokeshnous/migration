<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<title>${metaTitle}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="google-site-verification" content="YaPtgACbX-BIKOnLbNBz5kczfzba1zE3KOAHj6kknN4" />
<meta name="msvalidate.01" content="27D52E6E7C0F836124611E207E2B3AD4" />
<meta name="description" content="${metaDesc}" />
<meta property="og:image"  content="<%=request.getContextPath()%>/resources/images/ADVANCE-Favicon.png"/>
<link href="${canonicalUrl}" rel="canonical" />
<jsp:include page="common/include.jsp" />

<!-- <title>ADVANCE Heathcare Jobs</title> -->

<!-- JAVASCRIPT FILES -->
<script language="javascript" type="text/javascript" src="../resources/js/slider.js"></script>
<link href="../resources/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<!-- <script language="javascript" type="text/javascript" src="/media/js/jquery.js"></script> -->
<script language="javascript" type="text/javascript" src="../resources/js/jquery.dataTables.nightly.js"></script>
<script language="javascript" type="text/javascript" src="../resources/js/searchResultsdatatable.js"></script>
<script language="javascript" type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>

  <script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script> 
</head>

<body class="job_board">
<div class="ad_page_top">${adPageTop}</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">
			<div class="main">
				<jsp:include page="../templates/templates_header.jsp" />
				<jsp:include page="jobboardsearchresultsHeader.jsp" />
				<div class="searchContent" style="display: none;" id="tableContent">
					<jsp:include page="jobboardsearchresultsBody.jsp" />
					<br class="clearfix" />
				</div>
				<div class="otherContent">
					<div class="ad_col_right">
						<div id="adPageRightMiddle"> ${adPageRightMiddle} </div>
						<div class="follow_us">
							<h2>Stay Connected</h2>
							<!-- <p>Stay connected to the latest jobs.</p> -->
							<a href="${followuplinkfacebook}" target="_blank"><span class="social facebook_link">Facebook</span></a> 
							<a href="${followuplinktwitter}" target="_blank"><span class="social twitter_link">Twitter</span></a> 
							<a href="${followuplinkyoutube}" target="_blank"><span class="social youTube_link">YouTube</span></a>
							<%-- <a href="${followuplinklinkedin}" target="_blank"><span class="last social linkedIn_link">LinkedIn</span></a> --%>
						</div>
						<br class="clearfix" />
					</div>
					<!-- ad_col_right -->
					<div class="content_columns">
						<div class="column1">
						<c:choose>
						<c:when test="${showCareersType == 'career'}">
						<script type="text/javascript">showCareersPart('careers');</script>
						</c:when>
						<c:when test="${showCareersType == 'messanger'}">
						<script type="text/javascript">showCareersPart('messanger');</script>
						</c:when>
						<c:when test="${showCareersType == 'resumeBuilder'}">
						<script type="text/javascript">showCareersPart('resumeBuilder');</script>
						</c:when>
						</c:choose>
								<h2 class="more_link">
								<!-- <a href="viewallcareertools.html" target="_blank">
									Career Tools</a> -->Career Tools<span>
									<!-- <a href="viewallcareertools.html" target="_blank">More</a> --></span>
								</h2>
								<ul class="tools">
										<li>
										<a class="cursor" onclick="showCareersPart('careers')" id="mainResourceCarrerId">
											<img src="../resources/images/Interview_60x.jpg" width="60"
												height="60" />
											<div class="toolsTextHolder">
												<span class="jb">Career Resource Center</span><br />Advice,
													blogs, webinars and more.
											</div>											
										</a>
											</li>
										<li>
										<a class="cursor" onclick="showCareersPart('resumeBuilder')" id="mainResumeBuilderId">
											<img src="../resources/images/CareerTools_A.jpg" />
											<div class="toolsTextHolder">
												<span class="jb"><i>ADVANCE</i> Resume Builder:</span><br /> Create or
												upload an online resume and apply to jobs in healthcare instantly.
								 			</div>
										</a>
							 			</li>
										<li class="last">
										<a class="cursor" onclick="showCareersPart('messanger')" id="mainMessaangerId">
										<img src="../resources/images/CareerTools_C.jpg" />
											<div class="toolsTextHolder">
												<span class="jb"><i>ADVANCE</i> Messenger:</span><br /> Save custom job searches and get email notifications.
											</div>
										</a>		
										</li>
								</ul>
							<%--  ${careerstoolresource} --%>
						</div>
						<!-- column1 -->
						<div class="column2">
								<h2 class="more_link">
								<a href="featuredemployers.html">
									Featured Employers</a><span><a href="featuredemployers.html">More</a></span>
								</h2>
							<div class="featured_emp_slider" id="slider1FramesId"></div>
							<!-- featured_emp_slider -->

								<h2 class="more_link Bgnone">Healthcare News</h2>
							<%--  ${healthcarenew} --%>
							 <c:forEach items="${newsDTOList}" var="newsDTO">   
                    				<div class="BlueBoxCont"> <a href="${newsDTO.link}" target="_blank" class="TextColorA02Link">
                      				<h3 class="TextColor01 FontSize12">${newsDTO.title}</h3>
                      				</a> </div>
                      		 </c:forEach>

						</div>
						<!-- column2-->
						<!--<a href="<%=request.getContextPath()%>/employer/postNewJobs.html">Post New Job</a>-->
					</div>
					<div class="Content_Description">
						<!-- added November 8, 2012 -->
						${staticContent}
					</div>
				</div>
				<div class="row careersContent" style="display: none;">
        <div class="row">
                  <div class="sectionHeader marginTop5">
            <h2 class="width305 FloatLeft TextColor02">CAREER TOOLS</h2>
          </div>
                </div>
        <!--Cont-->
        <div class="row marginBottom15"> 
                  <!--Links-->
                  <div class="RowLeftContant">
            <div class="LeftContantLinks" id="resourceCarrerId">
            <a class="cursor" onclick="showCareersSubPart('careers')">Career Resource Center</a>
            <div class="normalList">Career Resource Center</div>
            </div>
            <div class="LeftContantLinks" id="resumeBuilderId">
            <a class="cursor" onclick="showCareersSubPart('resumeBuilder')"><i>ADVANCE</i> Resume Builder</a>
            <div class="normalList"><i>ADVANCE</i> Resume Builder</div>
            </div>
            <div class="LeftContantLinks" id="messaangerId">            
            <a class="cursor" onclick="showCareersSubPart('messanger')"><i>ADVANCE</i> Messenger</a>   
            <div class="normalList"><i>ADVANCE</i> Messenger</div>         
            </div>
          <!-- LeftContantLinksActive -->
          </div>
                  <!--Links--> 
                  <!--Mid-->
                  <div class="RowMidContant">
                  <div class="careersSubContent"></div>
          </div>
                  <!--Mid--> 
                  <!--Right-->
                  <div class="RowRightContant">
                  <!-- <img width="160" height="600" alt="imf" src="images/sky.gif"/> -->
                  ${adPageRightTopMiddle}
                  </div>
                  <!--Right--> 
                </div>
      </div>
				<br class="clearfix" />

				<div class="ad_wrapper">${adPageBottom}</div>


			</div>
			<!-- main -->
		</div>

		<!-- end main_wrapper_inside -->

		<!-- end main_wrapper_outside -->
		<jsp:include page="../templates/templates_footer.jsp" />
</body>
</html>