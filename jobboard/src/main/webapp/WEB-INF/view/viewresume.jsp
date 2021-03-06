<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<jsp:include page="common/include.jsp" />
		<title>ADVANCE Heathcare Jobs</title>

		<!-- JAVASCRIPT FILES -->
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
		<!-- <script type="text/javascript" src="../resources/js/jquery.cycle.all.min.js"></script> -->
		<script type="text/javascript" src="../resources/js/slider.js"></script>
		<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>

		<script type="text/javascript">
		    jQuery(document).ready(function(){
		    $(".megamenu").megamenu();
		    
		   /*  $(".IconsArea a").click(function(){
		    	var resumeId = $(this).attr("id");
		    	if(resumeId == 0){
		    		alert("Please save the resume before you download/print.");
		    		return false;
		    	}
		    	return true;*/
		    }); 
		    
		    function downloadResume(resumeId){
		    	if(resumeId == 0){
		    		alert("Please save the resume before you download.");
		    		return false;
		    	}
		    	return true;
		    }
		    
		    function printResume(resumeId){
		    	if(resumeId == 0){
		    		alert("Please save the resume before you print.");
		    		return false;
		    	}
		    	return true;
		    }
		</script>
		</head>

		<body class="job_board">
<div class="ad_page_top">
 ${adPageTop}
 </div>
<div class="main_wrapper_outside">
          <div class="main_wrapper_inside">
    <div class="main">
    <jsp:include page="../templates/templates_header.jsp"></jsp:include>
 <form action="saveResumeBuilder.html" method="POST" commandName="createResume" id="viewresumeId">
              <div class="clearfix"></div>
              <!--Start:MidContant-->
              <div class="MidContent_Wrapper ">
              <div class="popupHeader Padding0  OrangeBG">
                  <h2>VIEW YOUR RESUME</h2>
          <span class="floatRight marginRight10">
          
          <c:if test="<%=session.getAttribute(\"module\") == \"employer\" %>">
          	<a href="<%=request.getContextPath()%>/employerSearchResume/findResumePage.html" class="link_color3_emphasized FontSize12 FontWeight">
          	 Return to Resume Search Results</a>
          </c:if>
          <c:if test="<%=session.getAttribute(\"module\") ==null %>">
          	<a href="<%=request.getContextPath()%>/jobSeeker/jobSeekerDashBoard.html" class="link_color3_emphasized FontSize12 FontWeight">
          	 Back to Dashboard</a>
           </c:if>
           <c:if test="<%=session.getAttribute(\"module\") == \"manageJobSeeker\" %>">
          	<a href="<%=request.getContextPath()%>/employer/manageJobSeeker.html?folderId=-1" class="link_color3_emphasized FontSize12 FontWeight">
          	 Back to Manage Job-Seeker</a>
          </c:if>
         </span></div>

        
        <div class="clearfix"></div>
        <div class="MidContent_Wrapper ">
                  <div class="ResumeHeader"> <h2 class="noTopBottomBorder"><c:out value="${createResume.resumeName}"/></h2>
            <div class="clearfix"></div>
            <span>
                    <h3 class="marginTop3"><c:out value="${createResume.desiredJobTitle}"/></h3>
                    </span>

            <div class="clearfix"></div>
            <span>
                    <p class="marginTop3">Available ${createResume.availableDate}</p>
                    </span> </div>
                  <div class="IconsArea">
								<c:if test="${createResume.uploadResumeId>0}">
									<a id="downloadId"
										href="${pageContext.request.contextPath}/employer/downloadResume.html?resumeId=${createResume.uploadResumeId}"
										title="Download">
										<div class="download"></div>
									</a>
									<a id="printId"
											href="${pageContext.request.contextPath}/employer/printResume.html?resumeId=${createResume.uploadResumeId}"
										target="_blank" title="Print">
										<div class="printOrange"></div>
									</a>
								</c:if>
								<c:if test="${createResume.uploadResumeId==0}">
									<a onclick="downloadResume(${createResume.uploadResumeId})"
										id="${createResume.uploadResumeId}" title="Download"> 
										<div class="download"></div></a>&nbsp; 
                  <a
										onclick="printResume(${createResume.uploadResumeId})"
										id="${createResume.uploadResumeId}" title="Print"> 
										<div class="printOrange"></div></a>
								</c:if>
							</div>
                </div>
        <!---->

        <div class="MidContent_Wrapper">
                  <div class="sectionHeaderResume">
            <h2 class="noBorder">Contact Info</h2>
          </div>
                  <div class="clearfix"></div>
                  <div class="floatLeft">
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>First Name:</strong></p>

              </span><span class="ContactInfoAreaRight"><c:out value="${createResume.contactInfoForm.firstName}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Last Name:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${createResume.contactInfoForm.lastName}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">

              <p><strong>Street Address:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${createResume.contactInfoForm.addressLine1}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>City:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${createResume.contactInfoForm.city}"/></span> </div>

            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>State / Province:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${createResume.contactInfoForm.state}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>ZIP Code:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${createResume.contactInfoForm.postalCode}"/></span> </div>

            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Country:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${createResume.contactInfoForm.country}"/></span> </div>
            <c:forEach items="${createResume.listPhoneDtlForm}" var="phoneDtl" varStatus="status">
	            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
	            	 <c:if test="${status.count == 1}">   
	            	 	  <p><strong>Phone:</strong></p>
			         </c:if>   
	              </span><span class="ContactInfoAreaRight"><c:out value="${phoneDtl.phoneNumber}"/></span>  
	           </div>
           </c:forEach> 
          </div>
                </div>
        <div class="clearfix"></div>
        <!---->
        <div class="MidContent_Wrapper">
                  <div class="sectionHeaderResume">
            <h2 class="noBorder">Objective</h2>
          </div>

                  <div class="clearfix"></div>
                  <div class="floatLeft paddingLeft15 MarginBottom10">
            <p><textarea readonly="readonly" style="background-color: #F0F0F0;font-family: Arial, Helvetica, sans-serif;font-size: 15px;" rows="3" cols="100">${createResume.objective}</textarea></p>
          </div>
                </div>
        <!---->
        <div class="MidContent_Wrapper">
                  <div class="sectionHeaderResume">
            <h2 class="noBorder">Work Experience</h2>
          </div>
         <div class="clearfix"></div>
         <div class="floatLeft">
         <c:forEach items="${createResume.listWorkExpForm}" var="workExp" varStatus="status">
         <c:if test="${status.count != 1}">   
            <p class="borderBottomDotted marginBottom15">&nbsp;</p> 
         </c:if>   
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Job Title:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${workExp.jobTitle}"/></span> </div>

            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Company Name:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${workExp.employerName}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Employment Type:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${workExp.employmentType}"/></span> </div>

            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Start Date:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${workExp.startDate}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>End Date:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${workExp.endDate}"/></span> 
              <c:if test="${workExp.bPresent eq 'true'}">
              	<input type="checkbox" value="${workExp.bPresent}" disabled="true" checked="checked"/>
              </c:if> 
              <c:if test="${workExp.bPresent eq 'false'}">
              	<input type="checkbox" value="" disabled="true"/>
              </c:if>         
				<span>&nbsp;&nbsp;&nbsp;</span>present
			</div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Years at Position:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${workExp.yrsAtPostion}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Career Level:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${workExp.currentCareerLvl}"/></span> 
               <c:if test="${workExp.bCurrentCareerLevel eq 'true'}">
              	<input type="checkbox" value="${workExp.bCurrentCareerLevel}" disabled="true" checked="checked"/>
              </c:if>  
              <c:if test="${workExp.bCurrentCareerLevel eq 'false'}">
              	<input type="checkbox" value="" disabled="true"/>
              </c:if> 
	               <span>&nbsp;&nbsp;&nbsp;</span>This is my current career level              
              </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Annual Salary: </strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${workExp.annualSalary}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Hourly Pay Rate</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${workExp.hrlyPayRate}"/></span> </div>

            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Summary / Job Description:</strong></p>
              </span> <span class="ContactInfoAreaRight width505 AutoHeight">
              <textarea readonly="readonly" style="background-color: #F0F0F0;font-family: Arial, Helvetica, sans-serif;font-size: 15px;" rows="3" cols="70">${workExp.description}</textarea></span> </div>
              <div class="clearfix"></div>
        </c:forEach>                          
       </div>
      </div>
        <!---->
        <div class="MidContent_Wrapper">
                  <div class="sectionHeaderResume">
            <h2 class="noBorder">Education</h2>
          </div>
           <div class="clearfix"></div>

           <div class="floatLeft">
           <c:forEach items="${createResume.listEduForm}" var="education" varStatus="status">    
	         <c:if test="${status.count != 1}">   
	            <p class="borderBottomDotted marginBottom15">&nbsp;</p> 
	         </c:if>   
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Institution Name:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${education.instituteName}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Degree Level:</strong></p>

              </span><span class="ContactInfoAreaRight"><c:out value="${education.degreeLvl}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Field of Study:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${education.fieldOfStudy}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">

              <p><strong>Start Date:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${education.startDate}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>End Date:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${education.endDate}"/></span>               
              <c:if test="${education.bNotGraduatedYet eq 'true'}">
              	<input type="checkbox" value="${education.bNotGraduatedYet}" disabled="true" checked="checked"/>
              </c:if> 
              <c:if test="${education.bNotGraduatedYet eq 'false'}">
              	<input type="checkbox" value="" disabled="true"/>
              </c:if> 
              <span>&nbsp;&nbsp;&nbsp;</span>I haven't graduated yet.   
              </div>

            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Degrees:</strong></p>
              </span><span class="ContactInfoAreaRight width505 AutoHeight">
              <textarea readonly="readonly" style="background-color: #F0F0F0;font-family: Arial, Helvetica, sans-serif;font-size: 15px;" rows="3" cols="70">${education.degrees}</textarea></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Certifications:</strong></p>
              </span><span class="ContactInfoAreaRight width505 AutoHeight">
              <textarea readonly="readonly" style="background-color: #F0F0F0;font-family: Arial, Helvetica, sans-serif;font-size: 15px;" rows="3" cols="70">${education.certifications}</textarea></span> </div>
            <div class="clearfix"></div>
			</c:forEach>
          </div>
                </div>
        <!---->
        <div class="MidContent_Wrapper">
                  <div class="sectionHeaderResume">
            <h2 class="noBorder">Certification</h2>
          </div>
           <div class="clearfix"></div>

           <div class="floatLeft">
           <c:forEach items="${createResume.listCertForm}" var="certification" varStatus="status">    
           <c:if test="${status.count != 1}">   
            <p class="borderBottomDotted marginBottom15">&nbsp;</p> 
           </c:if>   
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Certification Name:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${certification.certificationName}"/></span> </div>
             <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Certifying Authority:</strong></p>

              </span><span class="ContactInfoAreaRight"><c:out value="${certification.certifyingAuthority}"/></span> </div> 
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Received:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${certification.dateOfReceipt}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Summary:</strong></p>
              </span><span class="ContactInfoAreaRight width505 AutoHeight">
                <textarea readonly="readonly" style="background-color: #F0F0F0;font-family: Arial, Helvetica, sans-serif;font-size: 15px;" rows="3" cols="70">${certification.summary}</textarea></span> </div>
            <div class="clearfix"></div>
			</c:forEach>              
          </div>
        </div>
        <!---->
        <div class="MidContent_Wrapper">
                  <div class="sectionHeaderResume">
            <h2 class="noBorder">Skills</h2>
          </div>
                  <div class="clearfix"></div>
                  <div class="floatLeft paddingLeft15 MarginBottom10">
            <p> <textarea readonly="readonly" style="background-color: #F0F0F0;font-family: Arial, Helvetica, sans-serif;font-size: 15px;" rows="3" cols="100">${createResume.skills}</textarea></p>
          </div>
                </div>
        <!---->

        <div class="MidContent_Wrapper">
            <div class="sectionHeaderResume">
            <h2 class="noBorder">Languages</h2>
          </div>
           <div class="clearfix"></div>
            <div class="floatLeft">
           <c:forEach items="${createResume.listLangForm}" var="language" varStatus="status">   
			<c:if test="${not empty language.language}">
			  <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong><c:out value="${language.language}"/> :</strong></p>

              </span><span class="ContactInfoAreaRight"><c:out value="${language.expLvl}"/></span> </div>
			</c:if>	
           </c:forEach>
          </div> 
         </div>
          <!---->
          <div class="MidContent_Wrapper">
                  <div class="sectionHeaderResume">
            <h2 class="noBorder">Awards</h2>
          </div>
                  <div class="clearfix"></div>
                  <div class="floatLeft paddingLeft15 MarginBottom10">
            <p> <textarea readonly="readonly" style="background-color: #F0F0F0;font-family: Arial, Helvetica, sans-serif;font-size: 15px;" rows="3" cols="100">${createResume.awards}</textarea></p>
          </div>
                </div>
          <!---->

           <div class="MidContent_Wrapper">
                  <div class="sectionHeaderResume">
            <h2 class="noBorder">Memberships</h2>
          </div>
                  <div class="clearfix"></div>
                  <div class="floatLeft paddingLeft15 MarginBottom10">
            <p> <textarea readonly="readonly"style="background-color: #F0F0F0;font-family: Arial, Helvetica, sans-serif;font-size: 15px;"  rows="3" cols="100">${createResume.memberships}</textarea></p>
          </div>

                </div>
          <!---->
           <div class="MidContent_Wrapper">
                  <div class="sectionHeaderResume">
            <h2 class="noBorder">Other</h2>
          </div>
                  <div class="clearfix"></div>
                  <div class="floatLeft paddingLeft15 MarginBottom10">

            <p> <textarea readonly="readonly" style="background-color: #F0F0F0;font-family: Arial, Helvetica, sans-serif;font-size: 15px;" rows="3" cols="100">${createResume.otherDetails}</textarea></p>
          </div>
                </div>
          <!---->
          <div class="MidContent_Wrapper">
                  <div class="sectionHeaderResume">
            <h2 class="noBorder">References</h2>
          </div>

           <div class="clearfix"></div>
           <div class="floatLeft">
           <c:forEach items="${createResume.listRefForm}" var="reference" varStatus="status">    
           <span>
             <h3 class=" marginLeft10 marginBottom10 FloatLeft width305 marginTop10">Professional References</h3>
             </span>
             <div class="clearfix"></div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Name:</strong></p>

              </span><span class="ContactInfoAreaRight"><c:out value="${reference.name}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Job Title:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${reference.jobTitle}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">

              <p><strong>Company Name:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${reference.companyName}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Phone Number:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${reference.phoneNo}"/></span> </div>

            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Email Address:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${reference.email}"/></span> </div>
            <div class="ContactInfoArea"> <span class="ContactInfoAreaLeft ">
              <p><strong>Reference Type:</strong></p>
              </span><span class="ContactInfoAreaRight"><c:out value="${reference.referenceType}"/></span></div>
            </c:forEach>

              <div class="IconsArea">
              <c:if test="${createResume.uploadResumeId>0}">
									<a id="downloadId"
										href="${pageContext.request.contextPath}/employer/downloadResume.html?resumeId=${createResume.uploadResumeId}"
										title="Download">
										<div class="download"></div>
									</a>
									<a id="printId"
										href="${pageContext.request.contextPath}/employer/printResume.html?resumeId=${createResume.uploadResumeId}"
										target="_blank" title="Print">
										<div class="printOrange"></div>
									</a>
								</c:if>
								<c:if test="${createResume.uploadResumeId==0}">
									<a onclick="downloadResume(${createResume.uploadResumeId})"
										id="${createResume.uploadResumeId}" title="Download"> 
										<div class="download"></div></a>&nbsp; 
                  <a
										onclick="printResume(${createResume.uploadResumeId})"
										id="${createResume.uploadResumeId}" title="Print"> 
										<div class="printOrange"></div></a>
								</c:if></div>
          </div>
                </div>

          <!---->
      </div>
   		<div class="clearfix"></div>
		<br /> <span class="marginBottom10 FloatLeft"> 
		<c:if test="${createResume.bHideBackButton == false}"> 
			<input type="submit" value="Back" name="Back" class="orange cursor"  id="saveResBuilderBtId"/>
		</c:if>
		</span>
 </form>                
              <!--Start:MidContant-->
              <div class="clearfix"></div>
              <!-- content_wrapper -->
              <div class="ad_wrapper"> 
              ${adPageBottom} 
              </div>
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