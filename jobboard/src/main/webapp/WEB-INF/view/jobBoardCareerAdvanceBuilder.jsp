            <%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
            <div class="BoxLeftStyle marginTop50"><img alt="img" src="<%=request.getContextPath() %>/resources/images/triangle.png" /></div>
            <div class="RowMidContantArea MinHeight600">
                      <div class="MidContantAreaHead">
                <h1 class="TextColor03">Build Versatile Online Resumes with Ease</h1>
              </div>
                 <div class="row">
                 <div class="BuildImgBox"><img width="170" height="150" alt="img" src="<%=request.getContextPath() %>/resources/images/careerResume.jpg"></div>
                 <div class="BuildTextBox">
                 	<div class="BuildTextBoxCont">
                 	<img style="float:left;margin-right:5px;"alt="" src="<%=request.getContextPath() %>/resources/images/checkmark.png">
                    <p>Apply for jobs in minutes with the click of a button</p>
                    </div>
                    <div class="BuildTextBoxCont">
                    <img style="float:left;margin-right:5px;"alt="" src="<%=request.getContextPath() %>/resources/images/checkmark.png">
                    <p>Manage multiple resumes under one account</p>
                    </div>
                    <div class="BuildTextBoxCont">
                    <img style="float:left;margin-right:5px;"alt="" src="<%=request.getContextPath() %>/resources/images/checkmark.png">
                    <p>Add, edit or delete resumes at your convenience</p>
                    </div>
                    <div class="BuildTextBoxCont">
                    <img style="float:left;margin-right:5px;"alt="" src="<%=request.getContextPath() %>/resources/images/checkmark.png">
                    <p>Print out your resumes for offline use</p>
                    </div>
                 </div>
                 <security:authorize access="(!hasRole('ROLE_JOB_SEEKER') and !hasRole('ROLE_FACILITY') and !hasRole('ROLE_FACILITY_GROUP') and !hasRole('ROLE_FACILITY_SYSTEM')) or (hasRole('ROLE_JOB_SEEKER'))">
                 <div class="row marginTop25">                 
                 	<a class="btn_sm orange cursor" onclick="postYourResumeLink();">Get started now</a>
                 </div>
                 </security:authorize>
                 <security:authorize access="!hasRole('ROLE_JOB_SEEKER') and !hasRole('ROLE_FACILITY') and !hasRole('ROLE_FACILITY_GROUP') and !hasRole('ROLE_FACILITY_SYSTEM')">
                 <div class="row marginTop20">
                 <p>To view your existing resumes, please <a href="<%=request.getContextPath() %>/commonlogin/login.html?page=jobSeeker">log in</a></p>
                 </div>
                 </security:authorize>
                 </div>                          
                    </div>
