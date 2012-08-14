<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body class="job_board">
	<div class="header_wrapper">
		<a href="">
			<div class="logo"></div>
		</a>
		<div class="headerLoginSection">
			<security:authorize access="hasRole('ROLE_JOB_SEEKER')">
				<div class="headerLoginSection">
					<div class="headerLoginSectionColumns">
						<span class="boldText">${msg.jsWelcomeMsg}<%=(String) session.getAttribute("userName")%>
							${msg.commonExclamationMark}

						</span><br>
						<div class="floatRight">
							<span class="floatLeft"> <a href="../jobboard/logout">${msg.commonLogOut}</a>
								${msg.commonVerticalBar} <c:choose>
									<c:when test="${jobSeekerDashBoardForm != null}">
										<a href="../healthcarejobs/advanceweb.html">${msg.commonBackHome}</a>
									</c:when>
									<c:otherwise>
										<a href="../jobSeeker/jobSeekerDashBoard.html">${msg.commonDashboard}</a>
									</c:otherwise>
								</c:choose>
							</span>
						</div>
					</div>
					<!-- loginHeader -->

					<div class="headerLoginSectionColumns" style="display: none">
						<span class="boldText">${msg.commonEmployer}</span><br> <a
							href="">${msg.commonLogIn}</a>${msg.commonVerticalBar}<a href="">${msg.commonPostJobs}</a>
					</div>
					<!-- loginHeader -->
					<div class="headerLoginSectionColumns" style="display: none">
						<span class="boldText">${msg.commonAdAgency}</span><br> <a
							href="">${msg.commonLogIn}</a>${msg.commonVerticalBar}<a href="">${msg.commonPostJobs}</a>
					</div>
					<!-- loginHeader -->
				</div>
			</security:authorize>
			<security:authorize access="hasRole('ROLE_MERION_ADMIN')">
				<div class="headerLoginSection">
					<div class="headerLoginSectionColumns">
						<span class="boldText">${msg.jsWelcomeMsg}<%=(String) session.getAttribute("userName")%>
							${msg.commonExclamationMark}

						</span><br>
						<div class="floatRight">
							<span class="floatLeft"> <a href="../jobboard/logout">${msg.commonLogOut}</a>
								${msg.commonVerticalBar}<a href="">${msg.commonDashboard}</a></span>
						</div>
					</div>
					<!-- loginHeader -->

					<div class="headerLoginSectionColumns" style="display: none">
						<span class="boldText">${msg.commonEmployer}</span><br> <a
							href="">${msg.commonLogIn}</a>${msg.commonVerticalBar}<a href="">${msg.commonPostJobs}</a>
					</div>
					<!-- loginHeader -->
					<div class="headerLoginSectionColumns" style="display: none">
						<span class="boldText">${msg.commonAdAgency}</span><br> <a
							href="">${msg.commonLogIn}</a>${msg.commonVerticalBar}<a href="">${msg.commonPostJobs}</a>
					</div>
					<!-- loginHeader -->
				</div>
			</security:authorize>
			<!-- loginHeader -->
			<security:authorize access="hasRole('ROLE_FACILITY_USER')">
				<div class="headerLoginSection">
					<div class="headerLoginSectionColumns">
						<span class="boldText">${msg.jsWelcomeMsg}<%=(String) session.getAttribute("userName")%>
							${msg.commonExclamationMark}

						</span><br>
						<div class="floatRight">
							<span class="floatLeft"> <a href="../jobboard/logout">${msg.commonLogOut}</a>
								${msg.commonVerticalBar}<a href="">${msg.commonDashboard}</a></span>
						</div>
					</div>
					<!-- loginHeader -->

					<div class="headerLoginSectionColumns" style="display: none">
						<span class="boldText">${msg.commonEmployer}</span><br> <a
							href="">${msg.commonLogIn}</a>${msg.commonVerticalBar}<a href="">${msg.commonPostJobs}</a>
					</div>
					<!-- loginHeader -->
					<div class="headerLoginSectionColumns" style="display: none">
						<span class="boldText">${msg.commonAdAgency}</span><br> <a
							href="">${msg.commonLogIn}</a>${msg.commonVerticalBar}<a href="">${msg.commonPostJobs}</a>
					</div>
					<!-- loginHeader -->
				</div>
			</security:authorize>
			<%-- <c:if test="${sessionScope.userId == null}">  --%>
			<security:authorize
				access="!hasRole('ROLE_JOB_SEEKER') and !hasRole('ROLE_MERION_ADMIN') and !hasRole('ROLE_MERION_ADMIN')">
				<div class="headerLoginSectionColumns width205">
					<span class="boldText">Job Seeker:</span><br>
					<div class="PopUpToolTip">
						<a href="#">Why <strong>advance</strong>?
						</a> <span class="classic01">
							<p class="FontWeight marginBottom10">When you sign up,
								ADVANCE gives you:</p>
							<div class="FontWeight FontSize12 OrangeDot FontBlack">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Access to thousands of
								healthcare job opportunities</div>
							<div class="FontWeight FontSize12 OrangeDot FontBlack">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The best healthcare content
								you can get anywhere</div>
							<div class="FontWeight FontSize12 OrangeDot FontBlack">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hours of informative and
								entertaining multimedia</div>
							<div class="FontWeight FontSize12 OrangeDot FontBlack">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The latest news, articles,
								product reviews and much more!</div>
							<p class=" marginTop10">And it's all FREE!</p>
						</span>
					</div>
					<div class="floatleft">
						<span> <a href="/jobboard/loginFormForJobSeeker/login.html">Login</a>
							| <a
							href="/jobboard/jobseekerregistration/createJobSeekerCreateYrAcct.html">Sign
								Up</a> |
						</span>
					</div>
				</div>
				<div class="headerLoginSectionColumns">
					<span class="boldText">Employer:</span><br> <a href="">Login</a>
					| <a href="">Post Jobs</a>
				</div>
				<div class="headerLoginSectionColumns">
					<span class="boldText">Ad Agency:</span><br> <a href="">Login</a>
					| <a href="">Post Jobs</a>
				</div>

			</security:authorize>
			<%--  </c:if> --%>
			<!-- loginHeader -->
		</div>
		<!-- loginHeader -->
		<!-- loginHeader -->

	</div>


	<div id="nav">
		<ul class="megamenu">
			<li><a href="javascript:">Magazines</a>
				<div class="megamenuContainer">
					<div class="column">
						<a href="http://nursing.advanceweb.com/">
							<p>Nurses</p>
						</a> <a href="http://physical-therapy.advanceweb.com/">
							<p>Physical Therapy and Rehab Medicine</p>
						</a> <a href="http://occupational-therapy.advanceweb.com/">
							<p>Occupational Therapy Practitioners</p>
						</a> <a href="http://imaging-radiation-oncology.advanceweb.com/">
							<p>Imaging & Radiattion Oncology</p>
						</a> <a href="http://audiology.advanceweb.com/">
							<p>Hearing Practice Management</p>
						</a>
					</div>
					<div class="column">
						<a
							href="http://speech-language-pathology-audiology.advanceweb.com/">
							<p>Speech-Language Pathologists & Audiologists</p>
						</a> <a href="http://respiratory-care-sleep-medicine.advanceweb.com/">
							<p>Respiratory Care and Sleep Medicine</p>
						</a> <a href="http://laboratory-manager.advanceweb.com/">
							<p>Administrators of the Laboratory</p>
						</a> <a href="http://laboratorian.advanceweb.com/">
							<p>Medical Laboratory Professionals</p>
						</a> <a href="http://health-information.advanceweb.com/">
							<p>Health Information Professionals</p>
						</a>
					</div>
					<div class="column">
						<a href="http://long-term-care.advanceweb.com/">
							<p>Long-Term Care Management</p>
						</a> <a
							href="http://nurse-practitioners-and-physician-assistants.advanceweb.com/">
							<p>NPs &amp; PAs</p>
						</a> <a href="http://healthcare-executive-insight.advanceweb.com/">
							<p>Executive Insight</p>
						</a>
					</div>
				</div></li>
			<li><a href="../healthcarejobs/advanceweb.html">Job Search</a>
				<div class="megamenuContainer">
					<div class="column">
						<a href="http://health-care-jobs.advanceweb.com/">
							<p>Quick Search</p>
						</a> <a
							href="http://health-care-jobs.advanceweb.com/ResumeBuilder/Default.aspx">
							<p>Resume Builder</p>
						</a> <a
							href="http://health-care-jobs.advanceweb.com/Salary/Default.aspx">
							<p>Salary Calculator</p>
						</a> <a
							href="http://health-care-jobs.advanceweb.com/AdvanceMessenger/Default.aspx">
							<p>
								<i>ADVANCE</i> Messenger
							</p>
						</a> <a
							href="http://health-care-jobs.advanceweb.com/careers/article.aspx?cc=251059">
							<p>Career Resource Center</p>
						</a> <a
							href="http://health-care-jobs.advanceweb.com/FeaturedFacilities/Default.aspx">
							<p>Featured Facilities</p>
						</a> <a href="http://health-care-jobs.advanceweb.com/Default.aspx">
							<p>Home</p>
						</a>
					</div>
				</div></li>
			<li><a href="javascript:">Education</a></li>
			<li><a href="javascript:">Events</a></li>
			<li><a href="javascript:">Community</a></li>
			<li><a href="javascript:">Healthcare Shop</a></li>
			<li><a href="javascript:">Custom Promotions</a></li>
		</ul>
	</div>
	<!--nav-->
</body>
</html>