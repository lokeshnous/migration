<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="description" content="${metaDesc}"> 
		<link href="${canonicalUrl}" rel="canonical" /></head>
        <jsp:include page="common/include.jsp" />
        <title>${metaTitle}</title>

		<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/searchResultsdatatable.js"></script>
		<script type="text/javascript">
			jQuery(document).ready(function() {
				jQuery(".megamenu").megamenu();
				window.onload = function() {
				};
			});
		</script> 
</head>
    
    <body class="job_board_home">    
        <div class="ad_page_top">
			<img src="<%= request.getContextPath() %>/resources/images/ads/banner_ad_fpo.png" />
        </div>
        <div class="main_wrapper_outside">
        <div class="main_wrapper_inside">


            <div class="main">
            <jsp:include page="../templates/templates_header.jsp"></jsp:include>
				<div class="ad_col_right">
                    <img src="<%= request.getContextPath() %>/resources/images/ads/300x250ad1.png" />
                    <img src="<%= request.getContextPath() %>/resources/images/ads/300x250ad2.png" />

		    <br class="clearfix" />

                </div><!-- ad_col_right -->

                <div class="content_wrapper">

		    <div class="jobDetails">
			
			<div class="jobDetailsEyebrow">
            
			<div class="floatLeft"> <h3 class="jobDetailsEyebrowHeader">Job Details </h3> </div> <div class="floatRight">
			<%-- <a href="${returnResults}" class="link_color2_emphasized">Return to Search Results &nbsp; </a> --%>
			 <c:choose><c:when test="${isReturnResults}">
                        <%-- <a href="${returnResults}" class="link_color2_emphasized">Return to Search Results &nbsp; </a> --%>
                        <a rel="nofollow,noindex" href='<%= request.getContextPath() %>/jobsearch/findJobPage.html' class="link_color2_emphasized">Return to Search Results &nbsp; </a>
                        </c:when>
                        <c:otherwise></c:otherwise>
                        </c:choose>
			</div>
			
			
			</div>
			<div class="JobDetailHeaderLeft">
			<h1 ><span>${jobDetail.jobTitle}</span></h1>
            <h2 class="sectionSubHeader MarginBottom10">${jobDetail.companyNameDisp}</h2>
            </div>
            <div class="JobDetailHeaderRight">
            <c:if test="${isFeatureEmployer}">
            <img src="<%= request.getContextPath() %>/resources/images/FeaturedEmp.png" width="164" height="23" alt="Featured Employer">
            </c:if> 
            </div>
			<div class="jobDetailsIntro">
			    <div class="jobDetailsIntroReview">
				<p>
					<c:if test="${not empty jobDetail.city}" >
						<span class="specs">City:</span>&nbsp;&nbsp;${jobDetail.city}&nbsp;&nbsp;|&nbsp;&nbsp;
					</c:if>
					<c:if test="${not empty jobDetail.stateFullName}" >
						<span class="specs">State:</span>&nbsp;&nbsp;${jobDetail.stateFullName}&nbsp;&nbsp;|&nbsp;&nbsp;
					</c:if>
					<c:if test="${not empty jobDetail.country}" >
						<span class="specs">Country:</span>&nbsp;&nbsp;${jobDetail.country}&nbsp;&nbsp;|&nbsp;&nbsp;
					</c:if>
					<span class="specs">Job ID Number:</span>&nbsp;&nbsp;${jobDetail.jobID}</p>
			    </div>
			    <div class="jobDetailsIntroOptions">
			    <div class="rowEvenTB10Spacing">
				<div class="ShareText">Send to friend: &nbsp;</div>
				<a rel="nofollow,noindex" onclick="sendToFrd(${jobDetail.jobID}, ${jobDetail.JobTitle},'<%= request.getContextPath() %>');"><div class="email"></div></a><div class="ShareText"> |&nbsp;&nbsp;Share:&nbsp;</div> <a rel="nofollow,noindex" href=""><div class="fbook"></div></a><a rel="nofollow,noindex" href=""><div class="linkedIn"></div></a><a rel="nofollow,noindex" href=""><div class="twitter"></div></a><div class="ShareText"> |&nbsp;&nbsp;Print:&nbsp;</div> <a rel="nofollow,noindex" href=""><div class="printJBdetail"></div></a></div>
				<div class="rowEvenTB10Spacing">
				<a onclick="applyThisJob(${jobDetail.jobID},'<%= request.getContextPath() %>');" class="btn_sm orange" >Apply Now</a>&nbsp;&nbsp;&nbsp;&nbsp;				
				<a onclick="saveThisJob(${jobDetail.jobID},'<%= request.getContextPath() %>')" id="saveThisJobId" class="btn_sm orange">SAVE THIS JOB</a></div>
			    
			    <br/><br/><br/>
			    <div class="FormErrorDisplayText" id="topjobActionInfo" ></div><br/><br/><br/>
			    <h3 class="jobSummaryTitle"><span>Job Summary:</span></h3>
			    <p class="article">${jobDetail.jobDesc}</p>     
			    <div class="jobDetailsIntroOptionsTborder">
				<div class="jobDetailsIntroOptions">
				<div class="rowEvenTB10Spacing">
				<div class="ShareText">Send to friend:&nbsp;</div>
				<a onclick="sendToFrd(${jobDetail.jobID}, ${jobDetail.JobTitle}, ${jobDetail.JobTitle},'<%= request.getContextPath() %>');"><div class="email"></div></a><div class="ShareText"> |&nbsp;&nbsp;Share:&nbsp;</div> <a href=""><div class="fbook"></div></a><a href=""><div class="linkedIn"></div></a><a href=""><div class="twitter"></div></a><div class="ShareText"> |&nbsp;&nbsp;Print:&nbsp;</div> <a href=""><div class="printJBdetail"></div></a></div>
				<div class="rowEvenTB10Spacing">
				<a onclick="btapplyThisJob(${jobDetail.jobID},'<%= request.getContextPath() %>');" class="btn_sm orange">Apply Now</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a onclick="btsaveThisJob(${jobDetail.jobID},'<%= request.getContextPath() %>');" id="btsaveThisJobId" class="btn_sm orange" >SAVE THIS JOB</a></div>
			    <br/><br/>
			    <div class="FormErrorDisplayText" id="bottomjobActionInfo" ></div><br/><br/><br/>
			    </div>
			    </div>
			    
			    
			</div>
		    </div>
		    <br class="clearfix" />

                </div><!-- content_wrapper -->

                <div class="ad_wrapper">
					<img src="<%= request.getContextPath() %>/resources/images/ads/banner_ad_fpo.png" />
                </div><!-- ad_wrapper -->

            </div><!-- main -->

        </div> <!-- end main_wrapper_inside -->   
        </div> <!-- end main_wrapper_outside -->
<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
    </body>
</html>