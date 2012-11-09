<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="common/include.jsp" />

<!-- <title>ADVANCE Heathcare Jobs</title> -->
<title>${metaTitle}</title>
<meta name="description" content="${metaDesc}"> 
		<link href="${canonicalUrl}" rel="canonical" /></head>


<!-- JAVASCRIPT FILES -->
<script type="text/javascript" src="../resources/js/slider.js"></script>
<link href="../resources/css/jquery-ui.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" language="javascript"
	src="/media/js/jquery.js"></script>
<script src="../resources/js/jquery.dataTables.nightly.js"></script>
<script src="../resources/js/searchResultsdatatable.js"></script>

<script type="text/javascript" src="../resources/js/jquery-ui.min.js"></script>
<!-- <link rel="stylesheet" type="text/css"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" /> -->
<!-- 	<script type="text/javascript" src="../resources/js/jquery.simplyscroll.js"></script>
<link rel="stylesheet" href="../resources/css/jquery.simplyscroll.css" media="all" 
type="text/css">
	<script type="text/javascript">
(function($) {
	$(function() { 
		$("#scroller").simplyScroll({
			customClass: 'custom',
			autoMode: 'loop',
        	auto: true,
			speed: 200
		});
	});
})(jQuery);
</script> -->
</head>

<body class="job_board">
	<div class="ad_page_top">
		${adPageTop}
	</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">
			<div class="main">
				<jsp:include page="../templates/templates_header.jsp"></jsp:include>
				 <!-- <a href="../pgiController/callPaymentMethod.html" class="btn_sm white">Payment Order</a>  -->
				<jsp:include page="jobboardsearchresultsHeader.jsp"></jsp:include>
				<div class="searchContent" style="display: none;" id="tableContent">
					<jsp:include page="jobboardsearchresultsBody.jsp"></jsp:include>
					<br class="clearfix" />
					</div>
				<div class="otherContent ">
				<div class="ad_col_right">
						 ${adPageRightMiddle}
					<div class="follow_us">
						<h2>Follow Us</h2> 
						<p>Stay connected to the latest jobs.</p>
						<a href="${followuplinkfacebook}" target="_blank">
							<div class="social facebook_link">Facebook</div>
						</a> <a href="${followuplinktwitter}" target="_blank">
							<div class="social twitter_link">Twitter</div>
						</a> <a href="${followuplinkyoutube}" target="_blank">
							<div class="social youTube_link">YouTube</div>
						</a> <a href="${followuplinklinkedin}" target="_blank">
							<div class="last social linkedIn_link">LinkedIn</div>
						</a>
					</div>
					<br class="clearfix" />
				</div>
				<!-- ad_col_right -->
				<div class="content_columns">
					<div class="column1">
						<a href="">
							<h2 class="more_link">
								Career Tools<span>More</span>
							</h2> 
						</a> ${careerstoolresource}
					</div>
					<!-- column1 -->

					<div class="column2">
						<a href="featuredemployers.html">
							<h2 class="more_link">
								Featured Employers<span>More</span>
							</h2>
						</a>
						<div class="featured_emp_slider" id="slider1FramesId"></div> 
								<!-- 	<ul id="scroller">
									<c:forEach var="companyProfileDTO"
									items="${companyProfileDTOList}" varStatus="status" step="1">
									<div class="slider1Frames">
										<li><a
											href="featuredemployerdetails.html?id=${companyProfileDTOList[status.index].facilityid }">
											<div class="slider1FrameA1">
												<img src="${companyProfileDTOList[status.index].logoPath}"
													alt="${companyProfileDTOList[status.index].companyName }"
													width="125" height="37">
											</div>
										</a> <%-- <a
											href="featuredemployerdetails.html?id=${companyProfileDTOList[status.index+1].facilityid }">
											<div class="slider1FrameA2">
												<img src="${companyProfileDTOList[status.index+1].logoPath}"
													alt="${companyProfileDTOList[status.index+1].companyName }"
													width="125" height="37">
											</div>
										</a> --%>
										</li>
									</div>
								</c:forEach>
										</ul>
						<!-- featured_emp_slider -->

						<a href="" target="_blank">
							<h2 class="more_link Bgnone">Healthcare News</h2>
						</a> ${healthcarenew}

					</div>
					<!-- column2-->
					<!--<a href="<%=request.getContextPath()%>/employer/postNewJobs.html">Post New Job</a>-->
				</div>
					<div class="SEO_text">
						<!-- added November 8, 2012 -->
						<p>
							According to the Bureau of Labor Statistics, 296,000 jobs were
							added in the healthcare industry between October 2011 and October
							2012. New opportunities are popping up every day, and the best
							place to find them is on the <em>ADVANCE Healthcare Jobs</em>
							website. Other healthcare job boards simply can't match our
							massive database of job listings, our helpful healthcare career
							advice or our incredibly accurate search functions. We've
							optimized every piece of this resource so you can find exactly
							what you need as quickly as possible.
						</p>

						<p>When you search for healthcare jobs here, you'll notice we
							have thousands of quality opportunities for professionals in
							every field. We're striving to include all types of healthcare
							careers, so you can be sure you'll find the openings you're
							looking for no matter what your specialty is. You have free
							access to all of the healthcare jobs in our database, and you can
							apply for as many as you like with just a click of the mouse.</p>

						<p>
							We've integrated a powerful Resume Builder so you can upload your
							resume and create new ones with our step-by-step program,
							allowing you to apply for a wide variety of healthcare jobs
							instantly. And if you've signed up for a free <em>ADVANCE</em>
							account, you can keep tabs on all of the jobs you've applied for
							with your personalized job-seeker dashboard. This handy homepage
							features everything you need to lead a successful job hunt. It's
							an invaluable resource you won't find on any other healthcare job
							boards.
						</p>

						<p>In addition to helping you track your applications, your
							dashboard shows you at a glance how many times your resumes have
							been viewed by employers. It also displays jobs you've saved
							during your searches and lets you set job alerts so you're the
							first to know when new healthcare jobs are posted. All you need
							to do is save your favorite job searches and our precision job
							match technology guarantees you never miss the opportunities that
							are perfect for you. Get started today and we'll help you take
							your healthcare career to the next level.</p>
					</div>
				</div>
				<br class="clearfix" />
				
				<div class="ad_wrapper">
					${adPageBtm}
				</div>
			
				
			</div>
			<!-- main -->
			</div>

		<!-- end main_wrapper_inside -->

	<!-- end main_wrapper_outside -->
	<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
</body>
</html>