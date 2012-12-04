<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
	<div class="header_wrapper">
	<c:choose>
	<c:when test="${homePage}">
		<h1 class="logo">
		<a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcarejobs/advanceweb.html" title="Advance Healthcare Jobs">
		<img src="<%=request.getContextPath() %>/resources/images/tranBg.png" alt="Advance Healthcare Jobs" width="397px" height="70px"/>
		</a>
		</h1> 
	</c:when>
	<c:otherwise>
		<h3 class="logo">
		<a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcarejobs/advanceweb.html" title="Advance Healthcare Jobs">
		<img src="<%=request.getContextPath() %>/resources/images/tranBg.png" alt="Advance Healthcare Jobs" width="397px" height="70px"/>
		</a>
		</h3> 
	</c:otherwise>
	</c:choose>
		
			<%-- <div class="logo"><img src="<%=request.getContextPath() %>/resources/images/tranBg.png" width="397px" height="70px" alt="advanceweb.com"></img></div> --%>
		
		<div class="headerLoginSection">
			<security:authorize access="hasRole('ROLE_JOB_SEEKER')">
				<div class="headerLoginSection">
					<div class="headerLoginSectionColumns">
						<span class="boldText">${msg.jsWelcomeMsg}<%=(String) session.getAttribute("userName")%>
							${msg.commonExclamationMark}

						</span><br />
						<div class="floatRight">
							<span class="floatLeft"> <a href="<%=request.getContextPath()%>/logout.html">${msg.commonLogOut}</a>

								${msg.commonVerticalBar} <c:choose>
									<c:when test="${jobSeekerDashBoardForm != null}">
										<a href="<%=request.getContextPath()%>/healthcarejobs/advanceweb.html">${msg.commonBackHome}</a>
									</c:when>
									<c:otherwise>
										<a href="<%=request.getContextPath()%>/jobSeeker/jobSeekerDashBoard.html">${msg.commonDashboard}</a>
									</c:otherwise>
								</c:choose>
							</span>
						</div>
					</div>
					<!-- loginHeader -->

					<div class="headerLoginSectionColumns" style="display: none">
						<span class="boldText">${msg.commonEmployer}</span><br /> <a
							href="">${msg.commonLogIn}</a>${msg.commonVerticalBar}<a href="">${msg.commonPostJobs}</a>
					</div>
					<!-- loginHeader -->
					<div class="headerLoginSectionColumns" style="display: none">
						<span class="boldText">${msg.commonAdAgency}</span><br /> <a
							href="">${msg.commonLogIn}</a>${msg.commonVerticalBar}<a href="">${msg.commonPostJobs}</a>
					</div>
					<!-- loginHeader -->
				</div>
			</security:authorize>
			<security:authorize access="hasRole('ROLE_FACILITY') or hasRole('ROLE_FACILITY_GROUP')">
				<div class="headerLoginSection">
					<div class="headerLoginSectionColumns">
						<span class="boldText">${msg.jsWelcomeMsg}<%=(String) session.getAttribute("userName")%>
							${msg.commonExclamationMark}

						</span><br />
						<div class="floatRight">
							<span class="floatLeft"> <a href="<%=request.getContextPath()%>/logout.html">${msg.commonLogOut}</a>
								${msg.commonVerticalBar}<a href="<%=request.getContextPath()%>/employer/employerDashBoard.html">${msg.commonDashboard}</a>
								<c:if test="<%=session.getAttribute(\"agencyUserId\")!=null%>"> 
								<a href="<%=request.getContextPath()%>/agency/impersonateFacilityToAgency.html">Agency Dashboard</a></c:if></span>
						</div>
					</div>
					<!-- loginHeader -->

					<div class="headerLoginSectionColumns" style="display: none">
						<span class="boldText">${msg.commonEmployer}</span><br/> <a
							href="">${msg.commonLogIn}</a>${msg.commonVerticalBar}<a href="">${msg.commonPostJobs}</a>
					</div>
					<!-- loginHeader -->
					<div class="headerLoginSectionColumns" style="display: none">
						<span class="boldText">${msg.commonAdAgency}</span><br /> <a
							href="">${msg.commonLogIn}</a>${msg.commonVerticalBar}<a href="">${msg.commonPostJobs}</a>
					</div>
					<!-- loginHeader -->
				</div>
			</security:authorize>
			<!-- loginHeader -->
			<security:authorize access="hasRole('ROLE_FACILITY_SYSTEM')">
				<div class="headerLoginSection">
					<div class="headerLoginSectionColumns">
						<span class="boldText">${msg.jsWelcomeMsg}<%=(String) session.getAttribute("userName")%>
							${msg.commonExclamationMark}

						</span><br />
						<div class="floatRight">
							<span class="floatLeft"> <a href="<%=request.getContextPath()%>/logout.html">${msg.commonLogOut}</a>
								${msg.commonVerticalBar}<a href="<%=request.getContextPath()%>/agency/agencyDashboard.html">${msg.commonDashboard}</a>
								</span>
						</div>
					</div>
					<!-- loginHeader -->

					<div class="headerLoginSectionColumns" style="display: none">
						<span class="boldText">${msg.commonEmployer}</span><br /> <a
							href="">${msg.commonLogIn}</a>${msg.commonVerticalBar}<a href="">${msg.commonPostJobs}</a>
					</div>
					<!-- loginHeader -->
					<div class="headerLoginSectionColumns" style="display: none">
						<span class="boldText">${msg.commonAdAgency}</span><br /> <a
							href="">${msg.commonLogIn}</a>${msg.commonVerticalBar}<a href="">${msg.commonPostJobs}</a>
					</div>
					<!-- loginHeader -->
				</div>
			</security:authorize>
			<%-- <c:if test="${sessionScope.userId == null}">  --%>
			<security:authorize
				access="!hasRole('ROLE_JOB_SEEKER') and !hasRole('ROLE_FACILITY') and !hasRole('ROLE_FACILITY_GROUP') and !hasRole('ROLE_FACILITY_SYSTEM')">
				<div class="headerLoginSectionColumns width205">
					<span class="boldText">Job Seeker:</span><br />
					<div class="PopUpToolTip">
						<a href="#" rel="nofollow,noindex">Why <strong>advance</strong>?
						</a> <span class="classic01">
							<p class="FontWeight marginBottom10">When you sign up,
								ADVANCE gives you:</p>
							<p class="FontWeight FontSize12 OrangeDot FontBlack">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Access to thousands of
								healthcare job opportunities</p>
							<p class="FontWeight FontSize12 OrangeDot FontBlack">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The best healthcare content
								you can get anywhere</p>
							<p class="FontWeight FontSize12 OrangeDot FontBlack">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hours of informative and
								entertaining multimedia</p>
							<p class="FontWeight FontSize12 OrangeDot FontBlack">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The latest news, articles,
								product reviews and much more!</p>
							<p class=" marginTop10">And it's all FREE!</p>
						</span>
					</div>
					<div class="floatleft">
					
						<span> <a href="<%=request.getContextPath()%>/commonLogin/login.html?page=jobSeeker">Login</a>
							| <a
							href="<%=request.getContextPath()%>/jobseekerregistration/createJobSeekerCreateYrAcct.html">Sign
								Up</a> |
						</span>
					</div>
				</div>
				<div class="headerLoginSectionColumns">
					<span class="boldText">Employer:</span><br /> <a href="<%=request.getContextPath()%>/commonLogin/login.html?page=employer">Login</a>
					| <a href="<%=request.getContextPath()%>/employerRegistration/employerregistration.html">Post Jobs</a>
				</div>
				<div class="headerLoginSectionColumns">
					<span class="boldText">Ad Agency:</span><br /> <a href="<%=request.getContextPath()%>/commonLogin/login.html?page=agency">Login</a>
					| <a href="<%=request.getContextPath()%>/agencyRegistration/agencyregistration.html">Post Jobs</a>
				</div>

			</security:authorize>
			<%--  </c:if> --%>
			<!-- loginHeader -->
		</div>
		<!-- loginHeader -->
		<!-- loginHeader -->

	</div>
	<div class="css_nav">
		<ul class="css_main_menu">
			<li class="css_main_menu_item"><a href="#">Magazines</a>
				<ul class="css_subContainer">
					<div class="css_column">
						<li><a href="http://nursing.advanceweb.com/">Nurses</a></li>
						<li><a href="http://physical-therapy.advanceweb.com/">Physical
								Therapy and Rehab Medicine</a></li>
						<li><a href="http://occupational-therapy.advanceweb.com/">Occupational
								Therapy Practitioners</a></li>
						<li><a
							href="http://imaging-radiation-oncology.advanceweb.com/">Imaging
								& Radiattion Oncology</a></li>
						<li><a href="http://audiology.advanceweb.com/">Hearing
								Practice Management</a></li>
					</div>
					<!-- END css_column -->

					<div class="css_column">
						<li><a
							href="http://speech-language-pathology-audiology.advanceweb.com/">Speech-Language
								Pathologists & Audiologists</a></li>
						<li><a
							href="http://respiratory-care-sleep-medicine.advanceweb.com/">Respiratory
								Care and Sleep Medicine</a></li>
						<li><a href="http://laboratory-manager.advanceweb.com/">Administrators
								of the Laboratory</a></li>
						<li><a href="http://laboratorian.advanceweb.com/">Medical
								Laboratory Professionals</a></li>
						<li><a href="http://health-information.advanceweb.com/">Health
								Information Professionals</a></li>
					</div>
					<!-- END css_column -->

					<div class="css_column">
						<li><a href="http://long-term-care.advanceweb.com/">Long-Term
								Care Management</a></li>
						<li><a
							href="http://nurse-practitioners-and-physician-assistants.advanceweb.com/">NPs
								& PAs</a></li>
						<li><a
							href="http://healthcare-executive-insight.advanceweb.com/">Executive
								Insight</a></li>
					</div>
					<!-- END css_column -->
				</ul>
				<!-- END css_subContainer --></li>
			<!-- END css_main_menu_item -->

			<li class="css_main_menu_item"><a href="#">Job Search</a>
				<ul class="css_subContainer">
					<div class="css_column">
						<li><a href="http://health-care-jobs.advanceweb.com/">Quick
								Search</a></li>
						<li><a
							href="http://health-care-jobs.advanceweb.com/ResumeBuilder/Default.aspx">Resume
								Builder</a></li>
						<li><a
							href="http://health-care-jobs.advanceweb.com/Salary/Default.aspx">Salary
								Calculator</a></li>
						<li><a
							href="http://health-care-jobs.advanceweb.com/AdvanceMessenger/Default.aspx"><em>ADVANCE</em>
								Messenger</a></li>
						<li><a
							href="http://health-care-jobs.advanceweb.com/careers/article.aspx?cc=251059">Career
								Resource Center</a></li>
						<li><a
							href="http://health-care-jobs.advanceweb.com/FeaturedFacilities/Default.aspx">Featured
								Facilities</a></li>
						<li><a
							href="http://health-care-jobs.advanceweb.com/Default.aspx">Home</a></li>
					</div>
					<!-- END css_column -->
				</ul>
				<!-- END css_subContainer --></li>
			<!-- END css_main_menu_item -->

			<li class="css_main_menu_item"><a
				href="http://www.advanceweb.com/Advertise/CE2.aspx">Education</a></li>
			<li class="css_main_menu_item"><a
				href="http://events.advanceweb.com/Attendee/Default.aspx">Events</a></li>
			<li class="css_main_menu_item"><a
				href="http://community.advanceweb.com/bloggroups/2/Home.aspx">Community</a></li>
			<li class="css_main_menu_item"><a
				href="http://shop.advanceweb.com">Healthcare Shop</a></li>
			<li class="css_main_menu_item"><a
				href="http://promotions.advanceweb.com">Custom Promotions</a></li>
		</ul>
	</div>
	<!--css_nav-->
