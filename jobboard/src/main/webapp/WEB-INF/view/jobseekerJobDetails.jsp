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
						$('#saveThisJobId').attr('target', '_blank');
						$('#saveThisJobId').attr('href', val + '.html');
						$("#saveThisJobId").displaypopup("#saveThisJobId",
								"775", "252");

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
						window.location.href = val+".html";
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
						$('#btsaveThisJobId').attr('target', '_blank');
						$('#btsaveThisJobId').attr('href', val + '.html');
						$("#btsaveThisJobId").displaypopup("#btsaveThisJobId",
								"775", "252");
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
						window.location.href = val+".html";
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

<!-- 				<div class="header_wrapper">

					 <a href="">
          <div class="logo"></div>
          </a>
					<div class="headerLoginSection">
					<div class="headerLoginSectionColumns width205">
					<span class="boldText">Job Seeker:</span><br>
		  <div class="PopUpToolTip"><a href="#">Why <strong>advance</strong>?</a>
          <span class="classic01">
         	<p class="FontWeight marginBottom10">When you sign up, ADVANCE gives you:</p>
            <div class="FontWeight FontSize12 OrangeDot FontBlack">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Access to thousands of healthcare job opportunities
            </div>
            <div class="FontWeight FontSize12 OrangeDot FontBlack">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The best healthcare content you can get anywhere
            </div>
            <div class="FontWeight FontSize12 OrangeDot FontBlack">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hours of informative and entertaining multimedia
            </div>
            <div class="FontWeight FontSize12 OrangeDot FontBlack">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The latest news, articles, product reviews and much more!
            </div>
            <p class=" marginTop10">And it's all FREE!</p>
          </span>
          </div> <div class="floatleft"><span> <a href="">Login</a> | <a href="">Sign Up</a> | </span></div></div>loginHeader
                    <div class="headerLoginSectionColumns">
					<span class="boldText">Employer:</span><br>
                    	<a href="">Login</a> | <a href="">Post Jobs</a>
					</div>loginHeader
					<div class="headerLoginSectionColumns">
					<span class="boldText">Ad Agency:</span><br>
						 <a href="">Login</a> | <a href="">Post Jobs</a>
					</div>loginHeader
                    </div>loginHeader
					loginHeader

				</div>header_wrapper

				<div id="nav">
					<ul class="megamenu">
					  <li>
						<a href="javascript:">Magazines</a>
						<div class="megamenuContainer">
						  <div class="column">
							<a href="http://nursing.advanceweb.com/"><p>Nurses</p></a>
							<a href="http://physical-therapy.advanceweb.com/"><p>Physical Therapy and Rehab Medicine</p></a>
							<a href="http://occupational-therapy.advanceweb.com/"><p>Occupational Therapy Practitioners</p></a>
							<a href="http://imaging-radiation-oncology.advanceweb.com/"><p>Imaging & Radiattion Oncology</p></a>
							<a href="http://audiology.advanceweb.com/"><p>Hearing Practice Management</p></a>
						  </div>
						  <div class="column">
							<a href="http://speech-language-pathology-audiology.advanceweb.com/"><p>Speech-Language Pathologists & Audiologists</p></a>
							<a href="http://respiratory-care-sleep-medicine.advanceweb.com/"><p>Respiratory Care and Sleep Medicine</p></a>
							<a href="http://laboratory-manager.advanceweb.com/"><p>Administrators of the Laboratory</p></a>
							<a href="http://laboratorian.advanceweb.com/"><p>Medical Laboratory Professionals</p></a>
							<a href="http://health-information.advanceweb.com/"><p>Health Information Professionals</p></a>
						  </div>
						  <div class="column">
							<a href="http://long-term-care.advanceweb.com/"><p>Long-Term Care Management</p></a>
							<a href="http://nurse-practitioners-and-physician-assistants.advanceweb.com/"><p>NPs & PAs</p></a>
							<a href="http://healthcare-executive-insight.advanceweb.com/"><p>Executive Insight</p></a>
						  </div>
						</div>
					  </li>
					  <li>
						<a href="javascript:">Job Search</a>
						<div class="megamenuContainer">
							<div class="column">
								<a href="http://health-care-jobs.advanceweb.com/"><p>Quick Search</p></a>
								<a href="http://health-care-jobs.advanceweb.com/ResumeBuilder/Default.aspx"><p>Resume Builder</p></a>
								<a href="http://health-care-jobs.advanceweb.com/Salary/Default.aspx"><p>Salary Calculator</p></a>
								<a href="http://health-care-jobs.advanceweb.com/AdvanceMessenger/Default.aspx"><p><i>ADVANCE</i> Messenger</p></a>
								<a href="http://health-care-jobs.advanceweb.com/careers/article.aspx?cc=251059"><p>Career Resource Center</p></a>
								<a href="http://health-care-jobs.advanceweb.com/FeaturedFacilities/Default.aspx"><p>Featured Facilities</p></a>
								<a href="http://health-care-jobs.advanceweb.com/Default.aspx"><p>Home</p></a>
							</div>
						</div>
					  </li>
					  <li>
						<a href="javascript:">Education</a>
					  </li>
					  <li>
						<a href="javascript:">Events</a>
					  </li>
					  
					  <li>
						<a href="javascript:">Community</a>
					  </li>
					  <li>
						<a href="javascript:">Healthcare Shop</a>
					  </li>
					  <li>
						<a href="javascript:">Custom Promotions</a>
					  </li>
					</ul>
				</div>nav -->

				<div class="ad_col_right">
                    <img src="../resources/images/ads/300x250ad1.png" />
                    <img src="../resources/images/ads/300x250ad2.png" />

		    <br class="clearfix" />

                </div><!-- ad_col_right -->

                <div class="content_wrapper">

		    <div class="jobDetails">
			
			<div class="jobDetailsEyebrow">
            
			<div class="floatLeft"> <h3 class="jobDetailsEyebrowHeader">Job Details </h3> </div> <div class="floatRight"><a href="findJobPage.html" class="link_color2_emphasized">Return to Search Results &nbsp; </a></div>
			</div>
			<div class="JobDetailHeaderLeft">
			<h1 class="marginBottom0"><span>${jobDetail.jobTitle}</span></h1>
            <h2 class="sectionSubHeader MarginBottom10">${jobDetail.companyNameDisp}</h2>
            </div>
            <div class="JobDetailHeaderRight"><img src="../resources/images/FeaturedEmp.png" width="164" height="23" alt="Featured Employer"> </div>
<div class="jobDetailsIntro">
			    <div class="jobDetailsIntroReview">
				<p style="width: 440px;"><c:if test="${isHideCity}" ><span class="specs">City:</span>&nbsp;&nbsp;${jobDetail.city}&nbsp;&nbsp;|&nbsp;&nbsp;</c:if><c:if test="${isHideState}" ><span class="specs">State:</span>&nbsp;&nbsp;${jobDetail.stateFullName}&nbsp;&nbsp;|&nbsp;&nbsp;</c:if><c:if test="${isHideCoutry}" ><span class="specs">Country:</span>&nbsp;&nbsp;${jobDetail.country}&nbsp;&nbsp;|&nbsp;&nbsp;</c:if><span class="specs">Job ID Number:</span>&nbsp;&nbsp;${jobDetail.jobID}</p>
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
        
			<!-- <div class="footer_wrapper">
            
            <div class="container1">
            <h4>Professions:</h4>
            <ul>
				<li><a href="#">Nursing</a></li>
				<li><a href="#">Imaging & Radiation</a></li>
				<li><a href="#">Oncology</a></li>
				<li><a href="#">Physical Therapy and Rehab Medicine</a></li>
				<li><a href="#">Occupational Therapy</a></li>
				<li><a href="#">Speech-Language Pathology</a></li>
				<li><a href="#">Audiology</a></li>
				<li><a href="#">Hearing Practice Management</a></li>
				<li><a href="#">Long-Term Care Managament</a></li>
				<li><a href="#">Respiratory Care</a></li>
				<li><a href="#">Sleep Medicine</a></li>
				<li><a href="#">Labortory</a></li>
				<li><a href="#">Health Information</a></li>
				<li><a href="#">Nurse Practitioners</a></li>
				<li><a href="#">Physician Assistants</a></li>
				<li><a href="#">Healthcare Executives</a></li>
            </ul>
            </div>end container1

			<div class="container2">
            <h4>Content:</h4>
            <ul>
				<li><a href="#">News</a></li>
				<li><a href="#">Features</a></li>
				<li><a href="#">Multimedia</a></li>
				<li><a href="#">Buyers Guide</a></li>
				<li><a href="#">Community</a></li>
				<li><a href="#">Downloads</a></li>
            </ul>
            </div>end container2

            <div class="container3">
            <h4>Services:</h4>
            <ul>
            <li><a href="#">ADVANCE Healthcare Jobs</a></li>
            <li><a href="#">Subscribe</a></li>

            <li><img src="../resources/images/email.png" class="foot_icon"/><a href="#">Sign Up for Enewsletter</a></li>

            <li><img src="../resources/images/fbook_sm.png" class="foot_icon"/><a href="#">Connect on Facebook</a></li>

            <li><img src="../resources/images/L_In_sm.png" class="foot_icon"/><a href="#">Connect on LinkedIn</a></li>

            <li><img src="../resources/images/twitter_sm.png" class="foot_icon"/><a href="#">Connect on Twitter</a></li>

            <li><a href="#">Download the Mobile App</a></li>
			<li><a href="#">Order Promotional Items</a></li>
            </ul>
            </div>end container3
			

            <div class="container4">
            <h4>More from ADVANCE:</h4>
            <ul>
            <li><a href="#">ADVANCE Heathcare Shop</a></li>
			<li><a href="#">ADVANCE Custom Promotions</a></li>
			<li><a href="#">ADVANCE Heathcare Jobs</a></li>
			<li><a href="#">ADVANCE Job Fairs</a></li>
			<li><a href="#">ADVANCE Continuing Ediucation</a></li>
			<li><a href="#">ADVANCE Custom Publishing</a></li>
			</ul>
			</div>end container4

			<div class="container5">
            <h4>Corporate:</h4>
            <ul>
            <li><a href="#">About Us</a></li>
            <li><a href="#">Contact Us</a></li>
            <li><a href="#">Advertise with Us</a></li>
            <li><a href="#">Work for Us</a></li>
            <li><a href="#">Privacy Policy</a></li>
            <li><a href="#">Term of Service</a></li>
            <li><a href="#">Help</a></li>
            </ul>
            </div>end container5

<br class="clearfix" />
			<p class="copyright">&copy; 2012 Merion Matters 2900 Horizon Drive King of Prussia PA 19406 800-355-5627</p>
        </div><!-- footer_wrapper -->        
<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
    </body>
</html>