<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <jsp:include page="common/include.jsp" />
        <title>ADVANCE Heathcare Jobs</title>

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
		<script type="text/javascript" src="../resources/js/searchResultsdatatable.js"></script>
		
  
  <jsp:include page="common/include.jsp" />
		<script type="text/javascript">
		    jQuery(document).ready(function(){
		    	jQuery(".megamenu").megamenu();
		    	popUpIds();
		});
		    
		    function popUpIds()
		    {
			    
			 	// Then hide the second div
			    $("#VideoSlideContant").hide();
				$("#TestSlideContant").hide();

			    // Then add a click handlers to the buttons
			    $("#PhotoSlideButton").click(function() {
			      $("#PhotoSlideContant").show();
			      $("#VideoSlideContant").hide();
				  $("#TestSlideContant").hide();
				 
			    });
			    $("#VideoSlideButton").click(function() {
			      $("#PhotoSlideContant").hide();
			      $("#VideoSlideContant").show();
				  $("#TestSlideContant").hide();
				 
			    });
				$("#TestSlideButton").click(function() {
			      $("#PhotoSlideContant").hide();
			      $("#VideoSlideContant").hide();
				  $("#TestSlideContant").show();
				 
			    });
		    }
		    
		</script>
		<script type="text/javascript">
		function popImage(path) {
			
			$.nmManual('../jobsearch/viewImage.html?id='+path);
		}
		
		function popTestimony(path) {
			
			$.nmManual('../jobsearch/viewTestimonial.html?id='+path);
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
                        <%-- <a href="${returnResults}" class="link_color2_emphasized">Return to Search Results &nbsp; </a> --%>
                        <a href='${pageContext.request.contextPath}/jobsearch/findJobPage.html' class="link_color2_emphasized">Return to Search Results &nbsp; </a>
                        </c:when>
                        <c:otherwise></c:otherwise>
                        </c:choose>
			</div>
			
			
			</div>
			
			<!-- Start Branding -->
			
<div class="row" >
        
         <!--LOGO AREA-->
       	 <div class="row marginTop5">
          <div class="row marginTop16">
               	<div class="LogoAreaBox" ><img src="<%=request.getContextPath()%>/jobsearch/viewImage.html?id=${jobDetail.getLogo()}"  alt="logo" width="335" height="60" border="0" /></div>
          </div>
          <div class="BoxText" alt="Color" width="500" height="60" border="0" style="color: ${jobDetail.getColor().substring(4)}"/></div>
         </div>
         
          <!--BANNER AREA-->
          <div class="row">
         	<div class="BannerAreaBox" style="background: ${jobDetail.getColor().substring(4)}">
                   <div class="BannerAreaInnerBox">
               <div class="BannerImgBox" > <img src="<%=request.getContextPath()%>/jobsearch/viewImage.html?id=${jobDetail.getImagePath()}" width="490" height="319" alt="Main image"></div>
               <div class="BannerTextBoxBlank" style="background: #c0c0c0">
				                
                       <h1 style="color: ${jobDetail.getColor().substring(4)}">About This Employer </h1>
                       <br />
                       <p class="lineHeight16" style="color: ${jobDetail.getColor().substring(4)}">${jobDetail.getCompanyOverview()}</p>
                       <br />
                       <!-- <p class="lineHeight16">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.</p> -->
                     </div>
           		</div>
           	</div>
       	  </div>
                  
                  
            <%-- <c:if test="${!jobDetail.getIsSilverCustomer()}">  
                
			<!--IMAGE SLIDER-->
           <div class="row">
	           <div class="ImageSlideArea"> 
	           <div class="row">
	           	<div class="ImageSlideButtonArea">
	               <div id="PhotoSlideButton" >
	               <a href="#" style="color: ${jobDetail.getColor().substring(4)}"><strong>PHOTOS</strong> </a>
	               </div>
	               <div id="VideoSlideButton" >
	               <a href="#" style="background-color: ${jobDetail.getColor().substring(4)}" ><strong>VIDEOS</strong></a>
	               </div>
	               <div id="TestSlideButton" >
	               <a href="#" style="background-color: ${jobDetail.getColor().substring(4)}" ><strong>TESTIMONIALS</strong></a>
	               </div>
	            </div>
	            
	               <div class="row">
	               <!--Photo Area  -->
	               <div id="PhotoSlideContant" >
	               	<div class="row">
	                   	<!-- <div class="LeftSlidNav"><a href="#"><img src="images/Multimedia_ArrowLeft.png" alt="icon" width="21" height="30" border="0"></a></div>
	                       <div class="PhotoSlideAreaBox"><span class=" floatLeft marginLeft18"><a href="#"><img src="images/Slid_img01.png" alt="img" width="150" height="153" border="0"></a></span><span class=" floatLeft marginLeft18"><a href="#"><img src="images/Slid_img02.png" alt="img" width="150" height="153" border="0"></a></span><span class=" floatLeft marginLeft18"><a href="#"><img src="images/Slid_img03.png" alt="img" width="150" height="153" border="0"></a></span><span class=" floatLeft marginLeft18"><a href="#"><img src="images/Slid_img04.png" alt="img" width="150" height="153" border="0"></a></span><span class=" floatLeft marginLeft18"><a href="#"><img src="images/Slid_img05.png" alt="img" width="150" height="153" border="0"></a></span> </div>
	                     <div class="RightSlidNav"><a href="#"><img src="images/Multimedia_ArrowRight.png" alt="icon" width="21" height="30" border="0"></a></div> -->
	                     
	                    
	                     <div class="featured_emp_slider" id="test"  >
	                     	<!--Photo Area  -->
							<div id="slider1PrevBtn" ></div>
							<!-- Photo -->
							<div id="slider1">
							<c:forEach var="companyProfileDTO"
									items="${jobDetail.listAddImages}" varStatus="status" step="4">
									<% i++; %>
									<div class="slider1Frames">
										<a id="${jobDetail.listAddImages[status.index].mediaPath}" onclick="popImage(this.id);" >
											<div class="slider1FrameA1">
												<img src="<%=request.getContextPath()%>/jobsearch/viewImage.html?id=${jobDetail.listAddImages[status.index].mediaPath}"
													
													>
											</div>
										</a> <a
											id="${jobDetail.listAddImages[status.index+1].mediaPath}" onclick="popImage(this.id);">
											<div class="slider1FrameA1">
												<img src="<%=request.getContextPath()%>/jobsearch/viewImage.html?id=${jobDetail.listAddImages[status.index+1].mediaPath}"
													
													>
											</div>
										</a>
										<a
											id="${jobDetail.listAddImages[status.index+2].mediaPath}" onclick="popImage(this.id);">
											<div class="slider1FrameA1">
												<img src="<%=request.getContextPath()%>/jobsearch/viewImage.html?id=${jobDetail.listAddImages[status.index+2].mediaPath}"
													
													>
											</div>
										</a>
										<a
											id="${jobDetail.listAddImages[status.index+3].mediaPath}" onclick="popImage(this.id);">
											<div class="slider1FrameA1">
												<img src="<%=request.getContextPath()%>/jobsearch/viewImage.html?id=${jobDetail.listAddImages[status.index+3].mediaPath}"
													
													>
											</div>
										</a>
									</div>
								</c:forEach>
								</div>
							<div id="slider1NextBtn"></div>	
						</div> 
	                   </div>	
	               </div>
	               <!--Video Area  -->
	               <div id="VideoSlideContant" >
	               	<div class="row">
	                   	<!-- <div class="LeftSlidNav"><a href="#"><img src="images/Multimedia_ArrowLeft.png" alt="icon" width="21" height="30" border="0"></a></div>
	                       <div class="PhotoSlideAreaBox"><span class=" floatLeft marginLeft18"><a href="#"><img src="images/Slid_img01.png" alt="img" width="150" height="153" border="0"></a></span><span class=" floatLeft marginLeft18"><a href="#"><img src="images/Slid_img02.png" alt="img" width="150" height="153" border="0"></a></span><span class=" floatLeft marginLeft18"><a href="#"><img src="images/Slid_img03.png" alt="img" width="150" height="153" border="0"></a></span><span class=" floatLeft marginLeft18"><a href="#"><img src="images/Slid_img04.png" alt="img" width="150" height="153" border="0"></a></span><span class=" floatLeft marginLeft18"><a href="#"><img src="images/Slid_img05.png" alt="img" width="150" height="153" border="0"></a></span> </div>
	                     <div class="RightSlidNav"><a href="#"><img src="images/Multimedia_ArrowRight.png" alt="icon" width="21" height="30" border="0"></a></div> -->
	                     
	                    
	                     <div class="featured_emp_slider" id="test"  >
	                     	<!--Photo Area  -->
							<div id="slider1PrevBtn" ></div>
							<!-- Photo -->
							<div id="slider1">
							<c:forEach var="companyProfileDTO"
									items="${jobDetail.listAddImages}" varStatus="status" step="4">
									<% i++; %>
									<div class="slider1Frames">
										<a id="${jobDetail.listAddImages[status.index].mediaPath}" onclick="popImage(this.id);" >
											<div class="slider1FrameA1">
												<img src="<%=request.getContextPath()%>/jobsearch/viewImage.html?id=${jobDetail.listAddImages[status.index].mediaPath}"
													
													>
											</div>
										</a> <a
											id="${jobDetail.listAddImages[status.index+1].mediaPath}" onclick="popImage(this.id);">
											<div class="slider1FrameA1">
												<img src="<%=request.getContextPath()%>/jobsearch/viewImage.html?id=${jobDetail.listAddImages[status.index+1].mediaPath}"
													
													>
											</div>
										</a>
										<a
											id="${jobDetail.listAddImages[status.index+2].mediaPath}" onclick="popImage(this.id);">
											<div class="slider1FrameA1">
												<img src="<%=request.getContextPath()%>/jobsearch/viewImage.html?id=${jobDetail.listAddImages[status.index+2].mediaPath}"
													
													>
											</div>
										</a>
										<a
											id="${jobDetail.listAddImages[status.index+3].mediaPath}" onclick="popImage(this.id);">
											<div class="slider1FrameA1">
												<img src="<%=request.getContextPath()%>/jobsearch/viewImage.html?id=${jobDetail.listAddImages[status.index+3].mediaPath}"
													
													>
											</div>
										</a>
									</div>
								</c:forEach>
								</div>
							<div id="slider1NextBtn"></div>	
						</div> 
	                   </div>	
	               </div>
	               <!--Testimonials Area  -->
	               <div id="TestSlideContant" >
	               	<div class="row">
	                     <div class="featured_emp_slider" id="test"  >
	                     	<!--  -->
							<div id="slider1PrevBtn" ></div>
								<!-- Testimonials -->
								<div id="slider1">
								<c:forEach var="companyProfileDTO"
									items="${jobDetail.listAddImages}" varStatus="status" step="4">
									<% i++; %>
									<div class="slider1Frames">
										<a
											id="${jobDetail.listTestimony[status.index].testimony}"  onclick="popTestimony(this.id);">
											<div class="slider1FrameA1">
												<p class="BannerTextBoxBlank" style="height:144px; width: 180px;" >${jobDetail.listTestimony[status.index].testimony}</p>
											</div>
										</a> 
										<a
											id="${jobDetail.listTestimony[status.index+1].testimony}" onclick="popTestimony(this.id);" >
											<div class="slider1FrameA1">
												<p class="BannerTextBoxBlank" style="height:144px; width: 180px;" >${jobDetail.listTestimony[status.index+1].testimony}</p>
											</div>
										</a>
										<a
											id="${jobDetail.listTestimony[status.index+2].testimony}" onclick="popTestimony(this.id);">
											<div class="slider1FrameA1">
												<p class="BannerTextBoxBlank" style="height:144px; width: 180px;" >${jobDetail.listTestimony[status.index+2].testimony}</p>
											</div>
										</a>
										<a
											id="${jobDetail.listTestimony[status.index+3].testimony}" onclick="popTestimony(this.id);">
											<div class="slider1FrameA1">
												<p class="BannerTextBoxBlank" style="height:144px; width: 180px;" >${jobDetail.listTestimony[status.index+3].testimony}</p>
											</div>
										</a>
									</div>
								</c:forEach>
								</div>
								
							
							<div id="slider1NextBtn"></div>	
								
							
								
							
						</div> 
	                     
	                   </div>	
	               </div>
	               </div>
	           </div>
	           </div>
          </div>
          </c:if>	 --%>
                  <!--CONT SLIDER-->
          
            <div class="row">
            <div class="ContantMiddleBox marginTop0 " style="border-top: 5px solid ${jobDetail.getColor().substring(4)}">
            <div class="ContantMiddleLeftBox">
            
                <div class="ContantMiddleLeftLink">
                          <div class="row">
                    <div class="rowEvenButSpacing paddingBottom10"> <span class="floatLeft marginTop10"><a class="btn_smB ColorButton" style="background-color: ${jobDetail.getColor().substring(4)}" href="#">Apply now</a> <a class="btn_smC white01" style="color: ${jobDetail.getColor().substring(4)}" href="#">save job</a></span> </div>
                  </div>
                  <div class="row marginTop10">
                  	<div class="ShareArea marginLeft5">
                    <span><p class="FloatLeft marginTop3">Send to friend:&nbsp;</p><img class = "email"></span>
                    </div>
                    <div class="ShareArea BorderLeft">
                    <span><p class="FloatLeft marginTop3">Share:&nbsp;</p><span><img class="fbook" ></span> <span><img class="linkedIn" ></span> <span><img class="twitter"></span></span>
                    </div>
                    <div class="ShareArea BorderLeft paddingRight0">
                    <span><p class="FloatLeft marginTop3">Print:&nbsp;</p><span><img class="print"></span></span>
                    </div>
                  </div>
                          <div class="clearfix"></div>
                          <div class="DotBorderBottom marginTop20"></div>
                </div>
                        
                <div class="row marginTop20">
                          <h3 class="TextColorA01 FontSize18">Name of Facility</h3>
                          <div class="row marginTop5">
                    <h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${jobDetail.getColor().substring(4)}"><strong>CITY :</strong></h1>
                    <p>Baltimore</p>
                  </div>
                          <div class="row marginTop5">
                    <h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${jobDetail.getColor().substring(4)}"><strong>STATE :</strong></h1>
                    <p>Maryland</p>
                  </div>
                          <div class="row marginTop5">
                    <h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${jobDetail.getColor().substring(4)}"><strong>COUNTRY :</strong></h1>
                    <p>US</p>
                  </div>
                          <div class="row marginTop5">
                    <h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${jobDetail.getColor().substring(4)}"><strong>JOB ID NUMBER :</strong></h1>
                    <p>00000000</p>
                  </div>
                 </div>
                 
                 <%-- <c:if test="${jobDetail.getPackageId()==3}">
                 <div class="row">
                 <div class="ContantLeftBlueBox " style="background: ${jobDetail.getColor().substring(4)} ">
                    <div class="BlueBoxCont TextColor02">
                              <h1 class="FontSize18">More Job Opportunities <br />
                        From This Employer </h1>
                            </div>
                    <div class="BlueBoxCont"><a href="#" class="TextColorA02Link">
                      <h3 class="TextColor02">Nurse Team Lead/RN Manager</h3>
                      </a></div>
                    <div class="BlueBoxCont"><a href="#" class="TextColorA02Link">
                      <h3 class="TextColor02">Nurse Team Lead/RN Manager</h3>
                      </a></div>
                    <div class="BlueBoxCont"><a href="#" class="TextColorA02Link">
                      <h3 class="TextColor02">Nurse Team Lead/RN Manager</h3>
                      </a></div>
                    <div class="BlueBoxCont"><a href="#" class="TextColorA02Link">
                      <h3 class="TextColor02">Nurse Team Lead/RN Manager</h3>
                      </a></div>
                    <div class="BlueBoxCont borderBottomNone"><a href="#" class="TextColorA02Link">
                      <h3 class="TextColor02">Nurse Team Lead/RN Manager</h3>
                      </a></div>
                 </div>
               	 </div>
                 
                 
	             <div class="row marginTop15">
	             <div class="LeftBoxLink" style="border-top: 5px solid ${jobDetail.getColor().substring(4)};">
                    <div class="BlueBoxContA"> <a href="#" class="UnderLineNone">
                      <h2 class="more_link noTopBottomBorder more_link02">News From This Employer<span>More</span></h2>
                      </a> </div>
                    <div class="BlueBoxCont"> <a href="#" class="TextColorA02Link">
                      <h3 class="TextColor01 FontSize12">Lorem Ipsum is simply dummy text of the printing and typesetting industry. </h3>
                      </a> </div>
                    <div class="BlueBoxCont"> <a href="#" class="TextColorA02Link">
                      <h3 class="TextColor01 FontSize12">Lorem Ipsum is simply dummy text of the printing and typesetting industry. </h3>
                      </a> </div>
                    <div class="BlueBoxCont"> <a href="#" class="TextColorA02Link">
                      <h3 class="TextColor01 FontSize12">Lorem Ipsum is simply dummy text of the printing and typesetting industry. </h3>
                      </a> </div>
                    <div class="BlueBoxCont borderBottomNone"> <a href="#" class="TextColorA02Link">
                      <h3 class="TextColor01 FontSize12">Lorem Ipsum is simply dummy text of the printing and typesetting industry. </h3>
                      </a> </div>
	             </div>
	             </div>
                 </c:if> --%>
              </div>
              
              
              <div class="ContantMiddleRightBox">
                	<DIV class="row">
		                <h1 class="HeadText" style="color: ${jobDetail.getColor().substring(4)}">Manager, Home Care Coordination/Discharge Planning </h1>
		                <br />
		                <br />
		                <h3 class="HeadText" style="color: ${jobDetail.getColor().substring(4)}">JOB  SUMMARY:</h3>
		                <br />
		                <div class="lineHeight16">
		                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
		                <br />
	               		</div>
                	</div>
                
	                <div class="ContantMiddleLeftLink marginBottom20">
	                          <div class="row">
	                    <div class="rowEvenButSpacing paddingBottom10"> <span class="floatLeft marginTop10"><a class="btn_smB ColorButton" style="background-color: ${jobDetail.getColor().substring(4)}" href="#">Apply now</a> <a class="btn_smC white01" style="color: ${jobDetail.getColor().substring(4)}" href="#">save job</a></span> </div>
	                  </div>
	                  <div class="row marginTop10">
	                  	<div class="ShareArea marginLeft5">
	                    <span><p class="FloatLeft marginTop3">Send to friend:&nbsp;</p><img class = "email"></span>
	                    </div>
	                    <div class="ShareArea BorderLeft">
	                    <span><p class="FloatLeft marginTop3">Share:&nbsp;</p><span><img class="fbook" ></span> <span><img class="linkedIn" ></span> <span><img class="twitter"></span></span>
	                    </div>
	                    <div class="ShareArea BorderLeft paddingRight0">
	                    <span><p class="FloatLeft marginTop3">Print:&nbsp;</p><span><img class="print"></span></span>
	                    </div>
	                  </div>
	                </div>
              </div>
              
              
              </div>
              </div>
              
                   
        
        
</div>             
 			
			<!-- End -->
			
			
			<%-- <div class="JobDetailHeaderLeft">
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
				<a onclick="sendToFrd(${jobDetail.jobID});"><div class="email"></div></a><div class="ShareText"> |&nbsp;&nbsp;Share:&nbsp;</div> <a href=""><div class="fbook"></div></a><a href=""><div class="linkedIn"></div></a><a href=""><div class="twitter"></div></a><div class="ShareText"> |&nbsp;&nbsp;Print:&nbsp;</div> <a href=""><div class="printJBdetail"></div></a></div>
				<div class="rowEvenTB10Spacing">
				<a onclick="applyThisJob(${jobDetail.jobID});" class="btn_sm orange" >Apply Now</a>&nbsp;&nbsp;&nbsp;&nbsp;				
				<a onclick="saveThisJob(${jobDetail.jobID})" id="saveThisJobId" class="btn_sm orange">SAVE THIS JOB</a></div>
			    
			    <br/><br/><br/>
			    <div class="FormErrorDisplayText" id="topjobActionInfo" ></div><br/><br/><br/>
			    <h3 class="jobSummaryTitle"><span>Job Summary:</span></h3>
			    <p class="article">${jobDetail.jobDesc}</p>     
			    <div class="jobDetailsIntroOptionsTborder">
				<div class="jobDetailsIntroOptions">
				<div class="rowEvenTB10Spacing">
				<div class="ShareText">Send to friend:&nbsp;</div>
				<a onclick="sendToFrd(${jobDetail.jobID});"><div class="email"></div></a><div class="ShareText"> |&nbsp;&nbsp;Share:&nbsp;</div> <a href=""><div class="fbook"></div></a><a href=""><div class="linkedIn"></div></a><a href=""><div class="twitter"></div></a><div class="ShareText"> |&nbsp;&nbsp;Print:&nbsp;</div> <a href=""><div class="printJBdetail"></div></a></div>
				<div class="rowEvenTB10Spacing">
				<a onclick="btapplyThisJob(${jobDetail.jobID});" class="btn_sm orange">Apply Now</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a onclick="btsaveThisJob(${jobDetail.jobID});" id="btsaveThisJobId" class="btn_sm orange" >SAVE THIS JOB</a></div>
			    <br/><br/>
			    <div class="FormErrorDisplayText" id="bottomjobActionInfo" ></div><br/><br/><br/>
			    </div>
			    </div>
			    
			    
			</div>
		    </div>
		    
		     --%>
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