<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>ADVANCE Heathcare Jobs</title>

		<!-- STYLESHEETS -->
        <link href="../resources/css/JB.css" rel="stylesheet" type="text/css" />
		<link href="../resources/css/jquery.megamenu.css" rel="stylesheet" type="text/css" />
		<link href="../resources/css/SliderStyles.css" rel="stylesheet" type="text/css">
		
<!-- js files for modalpopup------------------------------------------------- -->
<script src="../resources/js/jquery-1.7.1.js"></script>
<script src="../resources/js/jquery-1.7.1.min.js"></script>
		<script src="../resources/jquery.nyroModal/js/popup.js"></script>
		<script src="../resources/jquery.nyroModal/js/jquery.nyroModal.custom.js"></script>
        <script src="../resources/jquery.nyroModal/js/jquery.nyroModal.custom.min.js"></script>
 	    <link href="../resources/jquery.nyroModal/styles/nyroModal.css" rel="stylesheet" type="text/css">

        <style type="text/css" media="screen">
           @import url("${pageContext.request.contextPath}/resources/jquery.nyroModal/styles/nyroModal.css");
        </style>
<!-- -------------------------------------------------------------------------- -->
		
	
	
        <!-- JAVASCRIPT FILES -->
		<script type="text/javascript" src="../resources/js/jquery.cycle.all.min.js"></script>
		<script type="text/javascript" src="../resources/js/slider.js"></script>
		<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>
		<script type="text/javascript">
			jQuery(document).ready(function() {
				jQuery(".megamenu").megamenu();
			});
		</script>
  
<script type="text/javascript">
	function saveThisJob(jobId) {
		$.ajax({
			url : 'saveThisJob.html?id='+jobId,
			data : ({
				userID : "userID"
			}),
			success : function(data) {
				$.each(data, function(key, val) {
					if (key == "AjaxMSG") {
						$('#topjobActionInfo').html(val);
						$('#bottomjobActionInfo').html('');
					}
				});
				$.each(data, function(key, val) {
					if (key == "NavigationPath") {
						//$('#saveThisJobId').attr('target', '_blank');
						//$('#saveThisJobId').attr('href', val + '.html');
						//$("#saveThisJobId").displaypopup("#saveThisJobId",
							//	"775", "252");
						$.nmManual(val + '.html');

					}
				});
			},
			error : function(data) {
				alert('Unable to process');
			},
			complete : function(data) {
			}
		});
	}

	function applyThisJob(jobId) {
		$.ajax({
			url : 'applyJob.html?id='+jobId,
			data : ({
				userID : "userID"
			}),

			success : function(data) {
				$.each(data, function(key, val) {
					if (key == "AjaxMSG") {
						$('#topjobActionInfo').html(val);
						$('#bottomjobActionInfo').html('');
					}
				});
				$.each(data, function(key, val) {
					if (key == "NavigationPath") {
						//$(applyJobidId).attr('href', val + '.html');
						window.location.href = val;
					}
				});
			},
			error : function(data) {
				alert('Unable to process');
			},
			complete : function(data) {
			}
		});
	}
	function btsaveThisJob(jobId) {
		$.ajax({
			url : 'saveThisJob.html?id='+jobId,
			data : ({
				userID : "userID"
			}),
			success : function(data) {
				$.each(data, function(key, val) {
					if (key == "AjaxMSG") {
						$('#bottomjobActionInfo').html(val);
						$('#topjobActionInfo').html('');
					}
				});
				$.each(data, function(key, val) {
					if (key == "NavigationPath") {
						//$('#btsaveThisJobId').attr('target', '_blank');
						//$('#btsaveThisJobId').attr('href', val + '.html');
						//$("#btsaveThisJobId").displaypopup("#btsaveThisJobId",
						//		"775", "252");
						$.nmManual(val + '.html');
					}
				});
			},
			error : function(data) {
				alert('Unable to process');
			},
			complete : function(data) {
			}
		});
	}

	function btapplyThisJob(jobId) {
		$.ajax({
			url : 'applyJob.html?id='+jobId,
			data : ({
				userID : "userID"
			}),

			success : function(data) {
				$.each(data, function(key, val) {
					if (key == "AjaxMSG") {
						$('#bottomjobActionInfo').html(val);
						$('#topjobActionInfo').html('');
					}
				});
				$.each(data, function(key, val) {
					if (key == "NavigationPath") {
						//$(applyJobidId).attr('href', val + '.html');
						window.location.href = val;
					}
				});
			},
			error : function(data) {
				alert('Unable to process');
			},
			complete : function(data) {
			}
		});
	}
</script>
</head>
    
    <body class="job_board_home">    
        <div class="ad_page_top">
			<img src="../resources/images/ads/banner_ad_fpo.png" />
        </div>
        <div class="main_wrapper_outside">
        <div class="main_wrapper_inside">


            <div class="main">
            <jsp:include page="../templates/templates_header.jsp"></jsp:include>
				<div class="ad_col_right">
                    <img src="../resources/images/ads/300x250ad1.png" />
                    <img src="../resources/images/ads/300x250ad2.png" />

		    <br class="clearfix" />

                </div><!-- ad_col_right -->

                <div class="content_wrapper">

		    <div class="jobDetails">
			
			<div class="jobDetailsEyebrow">
            
			<div class="floatLeft"> <h3 class="jobDetailsEyebrowHeader">Job Details </h3> </div> <div class="floatRight">
			<%-- <a href="${returnResults}" class="link_color2_emphasized">Return to Search Results &nbsp; </a> --%>
			 <c:choose><c:when test="${returnResults != 'null'}">
                        <a href="${returnResults}" class="link_color2_emphasized">Return to Search Results &nbsp; </a>
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
            <img src="../resources/images/FeaturedEmp.png" width="164" height="23" alt="Featured Employer">
            </c:if> 
            </div>
			<div class="jobDetailsIntro">
			    <div class="jobDetailsIntroReview">
				<p><c:if test="${isHideCity}" ><span class="specs">City:</span>&nbsp;&nbsp;${jobDetail.city}&nbsp;&nbsp;|&nbsp;&nbsp;</c:if><c:if test="${isHideState}" ><span class="specs">State:</span>&nbsp;&nbsp;${jobDetail.stateFullName}&nbsp;&nbsp;|&nbsp;&nbsp;</c:if><c:if test="${isHideCoutry}" ><span class="specs">Country:</span>&nbsp;&nbsp;${jobDetail.country}&nbsp;&nbsp;|&nbsp;&nbsp;</c:if><span class="specs">Job ID Number:</span>&nbsp;&nbsp;${jobDetail.jobID}</p>
			    </div>
			    <div class="jobDetailsIntroOptions">
				<p class="marginBottom15">Send to friend: <a href=""><img src="../resources/images/email.png" alt="Send to Friend"></a> |&nbsp;&nbsp;Share: <a href=""><img src="../resources/images/fbook_sm.png" alt="Share on Facebook" /></a><a href=""><img src="../resources/images/L_In_sm.png" alt="Share on LinkedIn" /></a><a href=""><img src="../resources/images/twitter_sm.png" alt="Tweet on Twitter" /></a> |&nbsp;&nbsp;Print: <a href=""><img src="../resources/images/Print.png" alt="Print" /></a></p>
				<a onclick="applyThisJob('+${jobDetail.jobID}+');" class="btn_sm orange" style=" cursor: default;">Apply Now</a>&nbsp;&nbsp;&nbsp;&nbsp;
				
				<a onclick="saveThisJob('+${jobDetail.jobID}+')" id="saveThisJobId" class="btn_sm orange" style=" cursor: default;">SAVE THIS JOB</a>
			    
			    <br/><br/>
			    <h4><div style="color: red" id="topjobActionInfo" ></div></h4>
			    <h3 class="jobSummaryTitle"><span>Job Summary:</span></h3>
			    <p class="article">${jobDetail.jobDesc}</p>     
			    <div class="jobDetailsIntroOptionsTborder">
				<div class="jobDetailsIntroOptions">
				<p class="marginBottom15">Send to friend: <a href=""><img src="../resources/images/email.png" alt="Send to Friend"></a> |&nbsp;&nbsp;Share: <a href=""><img src="../resources/images/fbook_sm.png" alt="Share on Facebook" /></a><a href=""><img src="../resources/images/L_In_sm.png" alt="Share on LinkedIn" /></a><a href=""><img src="../resources/images/twitter_sm.png" alt="Tweet on Twitter" /></a> |&nbsp;&nbsp;Print: <a href=""><img src="../resources/images/Print.png" alt="Print" /></a></p>
				<a onclick="btapplyThisJob('+${jobDetail.jobID}+');" style=" cursor: default;" class="btn_sm orange">Apply Now</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a onclick="btsaveThisJob('+${jobDetail.jobID}+');" id="btsaveThisJobId" class="btn_sm orange" style=" cursor: default;">SAVE THIS JOB</a>
			    <br/><br/>
			    <h4><div style="color: red" id="bottomjobActionInfo" ></div></h4>
			    </div>
			    </div>
			    
			    
			</div>
		    </div>
		    <br class="clearfix" />

                </div><!-- content_wrapper -->

                <div class="ad_wrapper">
					<img src="../resources/images/ads/banner_ad_fpo.png" />
                </div><!-- ad_wrapper -->

            </div><!-- main -->

        </div> <!-- end main_wrapper_inside -->   
        </div> <!-- end main_wrapper_outside -->
<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
    </body>
</html>