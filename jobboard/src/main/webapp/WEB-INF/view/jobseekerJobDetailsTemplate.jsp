<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="common/include.jsp" />
        <title>${jobDetail.jobTitle} on ADVANCE Heathcare Jobs.com</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="description" content="${metaDesc}"> 
		<link href="${canonicalUrl}" rel="canonical" />
	
        <!-- JAVASCRIPT FILES -->
		<!-- <script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.cycle.all.min.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/slider.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.megamenu.js"></script> -->
		<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/searchResultsdatatable.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/FB.Share" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/resources/js/in.js" type="text/javascript"></script>
		<script  src="<%=request.getContextPath()%>/resources/js/widgets.js"></script>
  
  <jsp:include page="common/include.jsp" />
  <script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/slider.js"></script>
		<script type="text/javascript">
		    jQuery(document).ready(function(){
		    	jQuery(".megamenu").megamenu();
		    	popUpIds();
		});
		    
		    function popUpIds()
		    {
			    
		    	// Then hide the second div
			    $("#VideoSlideContant").hide();
			    $("#VideoSlideButton a").css("background-color", "${jobDetail.getColor().substring(4)}");
			    $("#VideoSlideButton a").css("color", "#FFFFFF");
				$("#TestSlideContant").hide();
				$("#TestSlideButton a").css("background-color", "${jobDetail.getColor().substring(4)}");
			    $("#TestSlideButton a").css("color", "#FFFFFF");

				// Then add a click handlers to the buttons
			    $("#PhotoSlideButton").click(function() {
			      $("#PhotoSlideContant").show();
			      $("#PhotoSlideButton a").css("background-color", "#FFFFFF");
			      $("#PhotoSlideButton a").css("color", "${jobDetail.getColor().substring(4)}");
			      $("#VideoSlideContant").hide();
			      $("#VideoSlideButton a").css("background-color", "${jobDetail.getColor().substring(4)}");
			      $("#VideoSlideButton a").css("color", "#FFFFFF");
				  $("#TestSlideContant").hide();
				  $("#TestSlideButton a").css("background-color", "${jobDetail.getColor().substring(4)}");
			      $("#TestSlideButton a").css("color", "#FFFFFF");
				 
			    });
			    $("#VideoSlideButton").click(function() {
			      $("#PhotoSlideContant").hide();
			      $("#PhotoSlideButton a").css("background-color", "${jobDetail.getColor().substring(4)}");
			      $("#PhotoSlideButton a").css("color", "#FFFFFF");
			      $("#VideoSlideContant").show();
			      $("#VideoSlideButton a").css("background-color", "#FFFFFF");
			      $("#VideoSlideButton a").css("color", "${jobDetail.getColor().substring(4)}");
				  $("#TestSlideContant").hide();
				  $("#TestSlideButton a").css("background-color", "${jobDetail.getColor().substring(4)}");
			      $("#TestSlideButton a").css("color", "#FFFFFF");
				 
			    });
				$("#TestSlideButton").click(function() {
			      $("#PhotoSlideContant").hide();
			      $("#PhotoSlideButton a").css("background-color", "${jobDetail.getColor().substring(4)}");
			      $("#PhotoSlideButton a").css("color", "#FFFFFF");
			      $("#VideoSlideContant").hide();
			      $("#VideoSlideButton a").css("background-color", "${jobDetail.getColor().substring(4)}");
			      $("#VideoSlideButton a").css("color", "#FFFFFF");
				  $("#TestSlideContant").show();
				  $("#TestSlideButton a").css("background-color", "#FFFFFF");
			      $("#TestSlideButton a").css("color", "${jobDetail.getColor().substring(4)}");
				 
			    });
		    }
		    
		</script>
		<script type="text/javascript">
		function popImage(path) {
			
			$.nmManual('<%= request.getContextPath() %>/jobsearch/viewImage.html?id='+path ,  {sizes:{initW: 500, initH: 500, minW: 500, minH: 500,  w: 500, h: 500}});
		}
		
		function popTestimony(path) {
			
			$.nmManual('<%= request.getContextPath() %>/jobsearch/viewTestimonial.html?id='+path,  {sizes:{initW: 600, initH: 600, minW: 600, minH: 600,  w: 600, h: 600}});
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
				

                <!-- <div class="content_wrapper"> -->

		    <!-- <div class="jobDetails"> -->
			<div class="jobDetailsEyebrow">
			<div class="floatLeft"> <h3 class="jobDetailsEyebrowHeader">Job Details </h3> </div> 
			<div class="floatRight">
				<c:choose><c:when test="${returnResults != 'null'}">
                   <a href='<%= request.getContextPath() %>/jobsearch/findJobPage.html' class="link_color2_emphasized">Return to Search Results &nbsp; </a>
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
          <input value="<%=request.getContextPath()%>" type="hidden" id="contextPath">
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
							<div id="slider2PrevBtn" ></div>
							<!-- Photo -->
							<div id="slider2">
							<c:forEach var="companyProfileDTO"
									items="${videoList}" varStatus="status" step="1">
									
									<div class="slider1Frames">
									
										<div class="floatLeft width285 marginLeft10 marginRight10">
										&nbsp;
										<div id="mediaspacePath" style="display: none;">${videoList[status.index]}</div> 
										<div name="mediaspace" id="mediaspace"></div> 
										<script type='text/javascript' src="<%= request.getContextPath() %>/resources/js/silverlight.js"></script>
										<script type='text/javascript' src="<%= request.getContextPath() %>/resources/js/wmvplayer.js"></script>
										<script type="text/javascript">
											var cnt = document.getElementById("mediaspace");
											var src = '<%= request.getContextPath() %>/resources/MediaFiles/wmvplayer.xaml';
											var filePath = $("#mediaspacePath").text();
											var cfg = {
												file: filePath,
												height:'165',
												width:'260',
												autostart:'false'
											};
											var ply = new jeroenwijering.Player(cnt,src,cfg);
										</script> 
										</div>
										
									</div>
								</c:forEach>
								</div>
							<div id="slider2NextBtn"></div>	
						</div> 
	                   </div>	
	               </div>
	               <!--Testimonials Area  -->
	               <div id="TestSlideContant" >
	               	<div class="row">
	                     <div class="featured_emp_slider" id="test"  >
	                     	<!--  -->
							<div id="slider3PrevBtn" ></div>
								<!-- Testimonials -->
								<div id="slider3">
								<c:forEach var="companyProfileDTO"
									items="${jobDetail.listTestimony}" varStatus="status" step="4">
									<div class="slider1Frames">
										<a
											id="${jobDetail.listTestimony[status.index].testimony}"  onclick="popTestimony(this.id);">
											<div class="slider1FrameA1">
												<p class="BannerTextBoxBlankSlide" >${jobDetail.listTestimony[status.index].testimony}</p>
											</div>
										</a> 
										<a
											id="${jobDetail.listTestimony[status.index+1].testimony}" onclick="popTestimony(this.id);" >
											<div class="slider1FrameA1">
												<p class="BannerTextBoxBlankSlide" >${jobDetail.listTestimony[status.index+1].testimony}</p>
											</div>
										</a>
										<a
											id="${jobDetail.listTestimony[status.index+2].testimony}" onclick="popTestimony(this.id);">
											<div class="slider1FrameA1">
												<p class="BannerTextBoxBlankSlide" >${jobDetail.listTestimony[status.index+2].testimony}</p>
											</div>
										</a>
										<a
											id="${jobDetail.listTestimony[status.index+3].testimony}" onclick="popTestimony(this.id);">
											<div class="slider1FrameA1">
												<p class="BannerTextBoxBlankSlide" >${jobDetail.listTestimony[status.index+3].testimony}</p>
											</div>
										</a>
									</div>
								</c:forEach>
								</div>
								
							
							<div id="slider3NextBtn"></div>	
								
							
								
							
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
                    <div class="rowEvenButSpacing paddingBottom10"> <span class="floatLeft marginTop10">
                    <a class="btn_smB ColorButton cursor" style="background-color: ${jobDetail.getColor().substring(4)}" onclick="applyThisJob(${jobDetail.jobId});" >Apply now</a> 
                    <a class="btn_smC white01 cursor" style="color: ${jobDetail.getColor().substring(4)}" onclick="saveThisJob(${jobDetail.jobId});" id="btsaveThisJobId" >save this job</a></span> </div>
                 <br/><br/>
			    <div class="FormErrorDisplayText" id="topjobActionInfo" ></div><br/><br/><br/>
                  </div>
                  <div class="ShareSearchView">
                  	<div class="ShareArea">
                    <span>
                    <div class="ShareText">Send to friend:&nbsp;</div>
                    <img class = "email cursor" onclick="sendToFrd(${jobDetail.jobId}, '${jobDetail.jobTitle}');">
                    </span>
                    </div>
                    <div class="ShareArea">
                    <span>
                    <div class="ShareText">|&nbsp;&nbsp;Share:&nbsp;</div>
                    
                    <a name="fb_share" class="fbook" href="http://www.facebook.com/sharer.php?u=${basePath}/jobsearch/jobview/${jobDetail.jobId}/${jobDetail.jobTitle}.html" target="_blank"></a>
				   <a href="https://www.linkedin.com/cws/share?url=${basePath}/jobsearch/jobview/${jobDetail.jobId}/${jobDetail.jobTitle}.html" target="_blank"><div class="linkedIn"></div></a>
				   <a href="https://twitter.com/share?url=${basePath}/jobsearch/jobview/${jobDetail.jobId}/${jobDetail.jobTitle}.html" class="twitter" data-url="${basePath}/jobsearch/jobview/${jobDetail.jobId}/${jobDetail.jobTitle}.html" data-count="none" target="_blank"></a>
                    
                      </span>
                    </div>
                    <div class="ShareArea">
                    <span>
                    <div class="ShareText">|&nbsp;&nbsp;Print:&nbsp;</div>
                     <a href="" onclick="window.print();"
											><div class="printJBdetail"></div></a>
                    </span>
                    </div>
                  </div>
                          <div class="clearfix"></div>
                          <div class="DotBorderBottom marginTop20"></div>
                </div>
                        
                <div class="row marginTop20">
                          <h3 class="TextColorA01 FontSize18">Name of Facility</h3>
                  <c:if test="${jobDetail.hideCity == 0}" >        
                  <div class="row marginTop5">
                    <h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${jobDetail.getColor().substring(4)}"><strong>CITY :</strong></h1>
                    <p>${jobDetail.city}</p>
                  </div>
                  </c:if>     
                         
                  <c:if test="${jobDetail.hideState == 0}" >        
                  <div class="row marginTop5">
                    <h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${jobDetail.getColor().substring(4)}"><strong>STATE :</strong></h1>
                    <p>${jobDetail.state}</p>
                  </div>
                  </c:if>
                  
                  <c:if test="${jobDetail.hideCountry == 0}" >
                  <div class="row marginTop5">
                    <h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${jobDetail.getColor().substring(4)}"><strong>COUNTRY :</strong></h1>
                    <p>${jobDetail.country}</p>
                  </div>
                  </c:if>
                  <c:if test="${jobDetail.hidePostcode == 0}" >
                  <div class="row marginTop5">
                    <h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${jobDetail.getColor().substring(4)}"><strong>Zip Code :</strong></h1>
                    <p>${jobDetail.postCode}</p>
                  </div>
                  </c:if>
                          <div class="row marginTop5">
                    <h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${jobDetail.getColor().substring(4)}"><strong>JOB ID NUMBER :</strong></h1>
                    <p>${jobDetail.jobId}</p>
                  </div>
                 </div>
                 
                 <c:if test="${jobDetail.getPackageId()==3}">
                 <div class="row">
                 <div class="ContantLeftBlueBox " style="background: ${jobDetail.getColor().substring(4)} ">
                    <div class="BlueBoxCont TextColor02">
                              <h1 class="FontSize18">More Job Opportunities <br />
                        From This Employer </h1>
                            </div>
                    <c:forEach items="${jobDTOList}" var="jobDTO">   
	                    <div class="BlueBoxCont"><a href="#" class="TextColorA02Link" onclick="viewJobDetails(${jobDTO.jobId},'${jobDTO.jobTitle}')">
	                      <h3 class="TextColor02">${jobDTO.jobTitle}</h3>
	                      </a></div>
                    </c:forEach> 
                 </div>
               	 </div>
                 
                 
	             <div class="row marginTop15">
	             <div class="LeftBoxLink" style="border-top: 5px solid ${jobDetail.getColor().substring(4)};">
                    <div class="BlueBoxContA"> <a href="<%= request.getContextPath() %>/jobsearch/getPlatinumNewsList.html" target="_blank" class="UnderLineNone">
                      <h2 class="more_link noTopBottomBorder more_link02">News From This Employer<span>More</span></h2>
                      </a> </div>
                       <c:forEach items="${newsDTOList}" var="newsDTO">   
                    <div class="BlueBoxCont"> <a href="${newsDTO.link}" target="_blank" class="TextColorA02Link">
                      <h3 class="TextColor01 FontSize12">${newsDTO.title}</h3>
                      </a> </div>
                      </c:forEach> 
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
				            <img src="<%= request.getContextPath() %>/resources/images/FeaturedEmp.png" width="164" height="23" alt="Featured Employer">
			            </c:if> 
			            </div>
		                <h3 class="HeadText" style="color: ${jobDetail.getColor().substring(4)}">JOB  SUMMARY:</h3>
		                <br />
		                <div class="lineHeight16">
		                <!-- <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p> -->
		                <p class="article">${jobDetail.adText}</p>
		                <br />
	               		</div>
                	</div>
                
	                <div class="ContantMiddleLeftLink marginBottom20">
	                          <div class="row">
	                    <div class="rowEvenButSpacing paddingBottom10"> <span class="floatLeft marginTop10">
	                    <a class="btn_smB ColorButton cursor" style="background-color: ${jobDetail.getColor().substring(4)}" onclick="btapplyThisJob(${jobDetail.jobId});" >Apply now</a> 
	                    <a class="btn_smC white01 cursor" style="color: ${jobDetail.getColor().substring(4)}" onclick="btsaveThisJob(${jobDetail.jobId});" id="btsaveThisJobId">save this job</a>
	                    </span> </div>
	                     <br/><br/>
			     <div class="FormErrorDisplayText" id="bottomjobActionInfo" ></div><br/><br/><br/>
	                  </div>
	                  <div class="ShareSearchView">
	                  	<div class="ShareArea">
                    <span>
                    <div class="ShareText">Send to friend:&nbsp;</div>
                    <img class = "email cursor" onclick="sendToFrd(${jobDetail.jobId}, '${jobDetail.jobTitle}');">
                    </span>
                    </div>
                    <div class="ShareArea">
                    <span>
                    <div class="ShareText">|&nbsp;&nbsp;Share:&nbsp;</div>
                    
                    <a name="fb_share" class="fbook" href="http://www.facebook.com/sharer.php?u=${basePath}/jobsearch/jobview/${jobDetail.jobId}/${jobDetail.jobTitle}.html" target="_blank"></a>
				   <a href="https://www.linkedin.com/cws/share?url=${basePath}/jobsearch/jobview/${jobDetail.jobId}/${jobDetail.jobTitle}.html" target="_blank"><div class="linkedIn"></div></a>
				   <a href="https://twitter.com/share?url=${basePath}/jobsearch/jobview/${jobDetail.jobId}/${jobDetail.jobTitle}.html" class="twitter" data-url="${basePath}/jobsearch/jobview/${jobDetail.jobId}/${jobDetail.jobTitle}.html" data-count="none" target="_blank"></a>
                    
                      </span>
                    </div>
                    <div class="ShareArea">
                    <span>
                    <div class="ShareText">|&nbsp;&nbsp;Print:&nbsp;</div>
                    <a href="" onclick="window.print();" ><div class="printJBdetail"></div></a>
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
					${adPageBottom}
                </div><!-- ad_wrapper -->

 </div><!-- main -->
 </div> <!-- end main_wrapper_inside -->   
</div> <!-- end main_wrapper_outside -->

<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
    </body>
</html>