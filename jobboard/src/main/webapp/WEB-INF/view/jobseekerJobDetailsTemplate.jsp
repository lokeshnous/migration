<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <jsp:include page="common/include.jsp" />
        <title>ADVANCE Heathcare Jobs</title>

	
        <!-- JAVASCRIPT FILES -->
		<!-- <script type="text/javascript" src="../resources/js/jquery.cycle.all.min.js"></script>
		<script type="text/javascript" src="../resources/js/slider.js"></script>
		<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script> -->
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
    
    <body class="job_board">    
        <div class="ad_page_top">
			<img src="../resources/images/ads/banner_ad_fpo.png" />
        </div>
        
<div class="main_wrapper_outside">
 <div class="main_wrapper_inside">
 <div class="main">
            <jsp:include page="../templates/templates_header.jsp"></jsp:include>
				

                <!-- <div class="content_wrapper"> -->

		    <!-- <div class="jobDetails"> -->
			<div class="jobDetailsEyebrow">
			<div class="floatLeft"> <h3 class="jobDetailsEyebrowHeader">Job Details </h3> </div> 
			<div class="floatRight">
				<c:choose><c:when test="${returnResults != 'null'}">
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
                  
                  
            <c:if test="${!jobDetail.getIsSilverCustomer()}">  
                
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
									items="${jobDetail.listTestimony}" varStatus="status" step="4">
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
          </c:if>	
                  <!--CONT SLIDER-->
          
            <div class="row">
            <div class="ContantMiddleBox01" style="border-top: 5px solid ${jobDetail.getColor().substring(4)}">
            <div class="ContantMiddleLeftBox">
            
                <div class="ContantMiddleLeftLink">
                          <div class="row">
                    <div class="rowEvenButSpacing paddingBottom10"> <span class="floatLeft marginTop10"><a class="btn_smB ColorButton" style="background-color: ${jobDetail.getColor().substring(4)}" onclick="applyThisJob(${jobDetail.jobID});" href="#">Apply now</a> <a class="btn_smC white01" style="color: ${jobDetail.getColor().substring(4)}" onclick="btsaveThisJob(${jobDetail.jobID});" id="btsaveThisJobId" href="#">save job</a></span> </div>
                  </div>
                  <div class="ShareSearchView">
                  	<div class="ShareArea">
                    <span>
                    <div class="ShareText">Send to friend:&nbsp;</div>
                    <img class = "email" onclick="sendToFrd(${jobDetail.jobID});">
                    </span>
                    </div>
                    <div class="ShareArea">
                    <span>
                    <div class="ShareText">|&nbsp;&nbsp;Share:&nbsp;</div>
                    <img class="fbook" src="../resources/images/tranBg.png" >
                     <img class="linkedIn" src="../resources/images/tranBg.png" >
                     <img class="twitter" src="../resources/images/tranBg.png">
                      </span>
                    </div>
                    <div class="ShareArea">
                    <span>
                    <div class="ShareText">|&nbsp;&nbsp;Print:&nbsp;</div>
                     <img class="printJBdetail" src="../resources/images/tranBg.png">
                    </span>
                    </div>
                  </div>
                          <div class="clearfix"></div>
                          <div class="DotBorderBottom marginTop20"></div>
                </div>
                        
                <div class="row marginTop20">
                          <h3 class="TextColorA01 FontSize18">Name of Facility</h3>
                  <c:if test="${not empty jobDetail.city}" >        
                  <div class="row marginTop5">
                    <h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${jobDetail.getColor().substring(4)}"><strong>CITY :</strong></h1>
                    <p>${jobDetail.city}</p>
                  </div>
                  </c:if>     
                         
                  <c:if test="${not empty jobDetail.stateFullName}" >        
                  <div class="row marginTop5">
                    <h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${jobDetail.getColor().substring(4)}"><strong>STATE :</strong></h1>
                    <p>${jobDetail.stateFullName}</p>
                  </div>
                  </c:if>
                  
                  <c:if test="${not empty jobDetail.country}" >
                  <div class="row marginTop5">
                    <h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${jobDetail.getColor().substring(4)}"><strong>COUNTRY :</strong></h1>
                    <p>${jobDetail.country}</p>
                  </div>
                  </c:if>
                  
                          <div class="row marginTop5">
                    <h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${jobDetail.getColor().substring(4)}"><strong>JOB ID NUMBER :</strong></h1>
                    <p>${jobDetail.jobID}</p>
                  </div>
                 </div>
                 
                 <c:if test="${jobDetail.getPackageId()==3}">
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
                 </c:if>
              </div>
              
              
              <div class="ContantMiddleRightBox">
                	<DIV class="row">
		                <h1 class="HeadText" style="color: ${jobDetail.getColor().substring(4)}">${jobDetail.jobTitle} </h1>
		                <br />
		                <h2 class="sectionSubHeader MarginBottom10">${jobDetail.companyNameDisp}</h2>
		                <br />
		                <div class="JobDetailHeaderRightView">
				            <c:if test="${isFeatureEmployer}">
				            <img src="../resources/images/FeaturedEmp.png" width="164" height="23" alt="Featured Employer">
			            </c:if> 
			            </div>
		                <h3 class="HeadText" style="color: ${jobDetail.getColor().substring(4)}">JOB  SUMMARY:</h3>
		                <br />
		                <div class="lineHeight16">
		                <!-- <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p> -->
		                <p class="article">${jobDetail.jobDesc}</p>
		                <br />
	               		</div>
                	</div>
                
	                <div class="ContantMiddleLeftLink marginBottom20">
	                          <div class="row">
	                    <div class="rowEvenButSpacing paddingBottom10"> <span class="floatLeft marginTop10"><a class="btn_smB ColorButton" style="background-color: ${jobDetail.getColor().substring(4)}" onclick="applyThisJob(${jobDetail.jobID});" href="#">Apply now</a> <a class="btn_smC white01" style="color: ${jobDetail.getColor().substring(4)}" onclick="btsaveThisJob(${jobDetail.jobID});" id="btsaveThisJobId" href="#">save job</a></span> </div>
	                  </div>
	                  <div class="ShareSearchView">
	                  	<div class="ShareArea">
                    <span>
                    <div class="ShareText">Send to friend:&nbsp;</div>
                    <img class = "email" onclick="sendToFrd(${jobDetail.jobID});">
                    </span>
                    </div>
                    <div class="ShareArea">
                    <span>
                    <div class="ShareText">|&nbsp;&nbsp;Share:&nbsp;</div>
                    <img class="fbook" src="../resources/images/tranBg.png" >
                     <img class="linkedIn" src="../resources/images/tranBg.png" >
                     <img class="twitter" src="../resources/images/tranBg.png">
                      </span>
                    </div>
                    <div class="ShareArea">
                    <span>
                    <div class="ShareText">|&nbsp;&nbsp;Print:&nbsp;</div>
                     <img class="printJBdetail" src="../resources/images/tranBg.png">
                    </span>
                    </div>
	                  </div>
	                </div>
              </div>
              
              
              </div>
              </div>
        
			 </div>       
			<!-- </div> -->             
 			
			<!-- End -->
			
			
		    <br class="clearfix" />

                <!-- </div> -->
                <!-- content_wrapper -->

                <div class="ad_wrapper">
					<img src="../resources/images/ads/banner_ad_fpo.png" />
                </div><!-- ad_wrapper -->

 </div><!-- main -->
 </div> <!-- end main_wrapper_inside -->   
</div> <!-- end main_wrapper_outside -->

<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
    </body>
</html>