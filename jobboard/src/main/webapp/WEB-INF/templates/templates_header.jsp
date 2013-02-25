<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
	<html lang="en">
    <head>
    <link rel='shortcut icon' href='<%=request.getContextPath() %>/resources/images/favicon.ico' type="image/x-icon">
   <%--  <link href="${canonicalUrl}" rel="canonical" /> --%>
    </head>
	
	<div class="header_wrapper">
	<c:choose>
	<c:when test="${isHomePage}">
		<h1 class="logo">
		<a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcare/index.html" title="Advance Healthcare Jobs">
		<img src="<%=request.getContextPath() %>/resources/images/tranBg.png" alt="Advance Healthcare Jobs" width="397px" height="70px"/>
		</a>
		</h1> 
	</c:when>
	<c:otherwise>
		<h3 class="logo">
		<security:authorize 
		access="hasRole('ROLE_JOB_SEEKER') or hasRole('ROLE_FACILITY') or hasRole('ROLE_FACILITY_GROUP') or hasRole('ROLE_FACILITY_SYSTEM')">
		<a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcare/index.html" title="Advance Healthcare Jobs">
		<img src="<%=request.getContextPath() %>/resources/images/tranBg.png" alt="Advance Healthcare Jobs" width="397px" height="70px"/>
		</a>
		</security:authorize>
		<security:authorize 
		access="!hasRole('ROLE_JOB_SEEKER') and !hasRole('ROLE_FACILITY') and !hasRole('ROLE_FACILITY_GROUP') and !hasRole('ROLE_FACILITY_SYSTEM')">
		<a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcare/index.html" title="Advance Healthcare Jobs">
		<img src="<%=request.getContextPath() %>/resources/images/tranBg.png" alt="Advance Healthcare Jobs" width="397px" height="70px"/>
		</a>
		</security:authorize>
		<%-- <security:authorize 
		access="hasRole('ROLE_FACILITY') or hasRole('ROLE_FACILITY_GROUP')">
		<a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/employer/employerDashBoard.html" title="Advance Healthcare Jobs">
		<img src="<%=request.getContextPath() %>/resources/images/tranBg.png" alt="Advance Healthcare Jobs" width="397px" height="70px"/>
		</a>
		</security:authorize>
		<security:authorize 
		access="hasRole('ROLE_FACILITY_SYSTEM')">
		<a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/agency/agencyDashboard.html" title="Advance Healthcare Jobs">
		<img src="<%=request.getContextPath() %>/resources/images/tranBg.png" alt="Advance Healthcare Jobs" width="397px" height="70px"/>
		</a>
		</security:authorize> --%>
		</h3> 
	</c:otherwise>
	</c:choose>
		
			<%-- <div class="logo"><img src="<%=request.getContextPath() %>/resources/images/tranBg.png" width="397px" height="70px" alt="advanceweb.com"></img></div> --%>
		
		<div class="headerLoginSection">
			<security:authorize access="hasRole('ROLE_JOB_SEEKER')">
				<div class="headerLoginSection">
					<div class="headerLoginSectionColumns">
						<span class="boldText">${msg.jsWelcomeMsg}<%=(String) session.getAttribute("userName")%>${msg.commonExclamationMark}

						</span><br />
						<div class="floatRight">
							<span class="floatLeft"> <a href="<%=request.getContextPath()%>/logout.html">${msg.commonLogOut}</a>

								${msg.commonVerticalBar} 
										<a href="<%=request.getContextPath()%>/jobSeeker/jobSeekerDashBoard.html">${msg.commonDashboard}</a>
									
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
						<span class="boldText">${msg.jsWelcomeMsg}<%=(String) session.getAttribute("userName")%>${msg.commonExclamationMark}

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
						<span class="boldText">${msg.jsWelcomeMsg}<%=(String) session.getAttribute("userName")%>${msg.commonExclamationMark}

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
						<a href="#" rel="nofollow,noindex">Why <strong>ADVANCE </strong>?
						</a> <div class="classic01">
							<p class="FontWeight marginBottom10">When you sign up,
								<i>ADVANCE</i> gives you:</p>
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
						</div>
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
	<ul id="menu">
    <li><a href="#" class="drop bodnew">MAGAZINES</a>
        <div class="dropdown_4columns">
            <div class="col_1">
                <ul>
                    <li><a href="http://nursing.advanceweb.com/" target="_blank">Nurses</a></li>
					<li><a href="http://physical-therapy.advanceweb.com/" target="_blank">Physical Therapy and Rehab Medicine</a></li>
					<li><a href="http://occupational-therapy.advanceweb.com/" target="_blank">Occupational Therapy Practitioners</a></li>
					<li><a href="http://imaging-radiation-oncology.advanceweb.com/" target="_blank">Imaging &amp; Radiation Oncology</a></li>
					<li><a href="http://audiology.advanceweb.com/" target="_blank">Hearing Practice Management</a></li>
                </ul>   
            </div>
            <div class="col_1">
                <ul>
                    <li><a href="http://speech-language-pathology-audiology.advanceweb.com/" target="_blank">Speech-Language Pathologists &amp; Audiologists</a></li>
					<li><a href="http://respiratory-care-sleep-medicine.advanceweb.com/" target="_blank">Respiratory Care and Sleep Medicine</a></li>
					<li><a href="http://laboratory-manager.advanceweb.com/" target="_blank">Administrators of the Laboratory</a></li>
					<li><a href="http://laboratorian.advanceweb.com/" target="_blank">Medical Laboratory Professionals</a></li>
					<li><a href="http://health-information.advanceweb.com/" target="_blank">Health Information Professionals</a></li>
                </ul>   
            </div>
            <div class="col_1">
                <ul>
                    <li><a href="http://long-term-care.advanceweb.com/" target="_blank">Long-Term Care Management</a></li>
					<li><a href="http://nurse-practitioners-and-physician-assistants.advanceweb.com/" target="_blank">NPs &amp; PAs</a></li>
					<li><a href="http://healthcare-executive-insight.advanceweb.com/" target="_blank">Executive Insight</a></li>
                </ul>   
            </div>
        </div><!-- End 4 columns container -->
    </li>
    
    <li>
   <%--  <security:authorize access="!hasRole('ROLE_JOB_SEEKER') and !hasRole('ROLE_FACILITY') and !hasRole('ROLE_FACILITY_GROUP') and !hasRole('ROLE_FACILITY_SYSTEM')" >
				<a href="<%=request.getContextPath()%>/healthcare/index.html" class="bodnew">JOB SEARCH</a>
	</security:authorize> --%>
	<security:authorize access="!(hasRole('ROLE_FACILITY') or hasRole('ROLE_FACILITY_GROUP') or hasRole('ROLE_FACILITY_SYSTEM'))">
				<a href="<%=request.getContextPath()%>/healthcare/index.html" class="bodnew">JOB SEARCH</a>
				<div class="dropdown_2columns">
            <div class="col_2">
                <ul>
                    <li><a href="http://www.advancehealthcarejobs.com" target="_blank">Quick Search</a></li>
					<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcare/showCareers.html?careerType=resumeBuilder" target="_blank">Resume Builder</a></li>
					<!-- <li><a href="http://health-care-jobs.advanceweb.com/Salary/Default.aspx">Salary Calculator</a></li> -->
					<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcare/showCareers.html?careerType=messanger" target="_blank"><em>ADVANCE</em> Messenger</a></li>
					<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/healthcare/showCareers.html?careerType=career" target="_blank">Career Resource Center</a></li>
					<li><a href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"")%>/healthcare/featuredemployers.html" target="_blank">Featured Employers</a></li>
					<li><a href="http://www.advancehealthcarejobs.com" target="_blank">Home</a></li>
                </ul>   
            </div>
        </div><!-- End 4 columns container -->
	</security:authorize>
	<security:authorize access="hasRole('ROLE_FACILITY') or hasRole('ROLE_FACILITY_GROUP') or hasRole('ROLE_FACILITY_SYSTEM')">
				<a href="#" class="drop bodnew">JOB SEARCH</a>
	</security:authorize>
	
    </li>
    
    <li class="css_main_menu_item"><a href="http://www.advanceweb.com/Advertise/CE2.aspx" class="bodnew" target="_blank">EDUCATION</a></li>
	<li class="css_main_menu_item"><a href="http://events.advanceweb.com/Attendee/Default.aspx" class="bodnew" target="_blank">EVENTS</a></li>
	<li class="css_main_menu_item"><a href="http://community.advanceweb.com" class="bodnew" target="_blank">COMMUNITY</a></li>
	<li class="css_main_menu_item"><a href="http://shop.advanceweb.com" class="bodnew" target="_blank">HEALTHCARE SHOP</a></li>
	<li class="css_main_menu_item"><a href="http://promotions.advanceweb.com" class="bodnew" target="_blank">CUSTOM PROMOTIONS</a></li>
</ul>
</html>
	<!--css_nav-->
