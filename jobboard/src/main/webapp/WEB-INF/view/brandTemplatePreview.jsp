<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>ADVANCE Heathcare Jobs</title>

<jsp:include page="common/include.jsp" />
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/slider.js"></script>
<script type='text/javascript' src="../resources/js/silverlight.js"></script>
<script type='text/javascript' src="../resources/js/wmvplayer.js"></script>

		<script type="text/javascript">
		    jQuery(document).ready(function(){
		    popUpIds();
		});
		    
		    
		    
		    function popUpIds()
		    {
			    
			 	// Then hide the second div
			    $("#VideoSlideContant").hide();
			    $("#VideoSlideButton a").css("background-color", "${brandingTemplateForm.getColor().substring(4)}");
			    $("#VideoSlideButton a").css("color", "#FFFFFF");
				$("#TestSlideContant").hide();
				$("#TestSlideButton a").css("background-color", "${brandingTemplateForm.getColor().substring(4)}");
			    $("#TestSlideButton a").css("color", "#FFFFFF");

			    // Then add a click handlers to the buttons
			    $("#PhotoSlideButton").click(function() {
			      $("#PhotoSlideContant").show();
			      $("#PhotoSlideButton a").css("background-color", "#FFFFFF");
			      $("#PhotoSlideButton a").css("color", "${brandingTemplateForm.getColor().substring(4)}");
			      $("#VideoSlideContant").hide();
			      $("#VideoSlideButton a").css("background-color", "${brandingTemplateForm.getColor().substring(4)}");
			      $("#VideoSlideButton a").css("color", "#FFFFFF");
				  $("#TestSlideContant").hide();
				  $("#TestSlideButton a").css("background-color", "${brandingTemplateForm.getColor().substring(4)}");
			      $("#TestSlideButton a").css("color", "#FFFFFF");
				 
			    });
			    $("#VideoSlideButton").click(function() {
			      $("#PhotoSlideContant").hide();
			      $("#PhotoSlideButton a").css("background-color", "${brandingTemplateForm.getColor().substring(4)}");
			      $("#PhotoSlideButton a").css("color", "#FFFFFF");
			      $("#VideoSlideContant").show();
			      $("#VideoSlideButton a").css("background-color", "#FFFFFF");
			      $("#VideoSlideButton a").css("color", "${brandingTemplateForm.getColor().substring(4)}");
				  $("#TestSlideContant").hide();
				  $("#TestSlideButton a").css("background-color", "${brandingTemplateForm.getColor().substring(4)}");
			      $("#TestSlideButton a").css("color", "#FFFFFF");
				 
			    });
				$("#TestSlideButton").click(function() {
			      $("#PhotoSlideContant").hide();
			      $("#PhotoSlideButton a").css("background-color", "${brandingTemplateForm.getColor().substring(4)}");
			      $("#PhotoSlideButton a").css("color", "#FFFFFF");
			      $("#VideoSlideContant").hide();
			      $("#VideoSlideButton a").css("background-color", "${brandingTemplateForm.getColor().substring(4)}");
			      $("#VideoSlideButton a").css("color", "#FFFFFF");
				  $("#TestSlideContant").show();
				  $("#TestSlideButton a").css("background-color", "#FFFFFF");
			      $("#TestSlideButton a").css("color", "${brandingTemplateForm.getColor().substring(4)}");
				 
			    });
		    }
		    
		</script>
		<script type="text/javascript">
		function popImage(path) {
			
			/* $.nmManual('../brandingTemplates/viewImage.html?id='+path); */
			/* path.preventDefault(); */
              if(path!=''){
			$.nmManual('../brandingTemplates/viewImage.html?id=' + path,  {closeOnEscape: true, showCloseButton: true, closeOnClick: true, sizes:{initW: 500, initH: 500, minW: 500, minH: 500,  w: 500, h: 500}});
              }

			}

			function popTestimony(path) {
				if(path!=''){
				$.nmManual('../brandingTemplates/viewTestimonial.html?id='
						+ path,  {closeOnEscape: true, showCloseButton: true, closeOnClick: true, sizes:{initW: 600, initH: 600, minW: 600, minH: 600,  w: 600, h: 600}});
				}
			}
		</script>
		</head>

		<body class="job_board">

<div class="main_wrapper_outside ">
 <div class="main_wrapper_inside">
 <div class="main">
 <jsp:include page="../templates/templates_header.jsp"></jsp:include>
 <div class="clearfix"></div>
 <div class="sectionHeader marginTop20">
 <div class="floatLeft">
 <h2 class="TextColor02 floatLeft">NEW BRANDING TEMPLATE PREVIEW</h2>
 </div>
     		 <div class="floatRight">
				<span class="headerRightLink"> <a href="../brandingTemplates/displayTemplate.html?id=${brandingTemplateForm.getBrowsePath()}">Return to Branding Template</a></span>
			</div>
	</div>			
  <div class="row" >
        
         <!--LOGO AREA-->
       	 <div class="row marginTop5">
          <div class="row marginTop16">
               	<div class="LogoAreaBox" ><img src="<%=request.getContextPath()%>/brandingTemplates/viewImage.html?id=${brandingTemplateForm.getLogoPath()}"  alt="logo" style="min-width:50px; max-width:100%; max-height:100%;" border="0" /></div>
          </div>
          <div class="BoxText" alt="Color" width="500" height="60" border="0" style="color: ${brandingTemplateForm.getColor().substring(4)}"/></div>
         </div>
         
          <!--BANNER AREA-->
          <div class="row">
         	<div class="BannerAreaBox" style="background: ${brandingTemplateForm.getColor().substring(4)}">
                   <div class="BannerAreaInnerBox">
               <div class="BannerImgBox" > <img class="imgCenter" src="<%=request.getContextPath()%>/brandingTemplates/viewImage.html?id=${brandingTemplateForm.getMainImagePath()}" alt="Main image"></div>
               <div class="BannerTextBoxBlank" style="background: #c0c0c0">
				                
                       <h1 style="color: ${brandingTemplateForm.getColor().substring(4)}">About This Employer </h1>
                       <br />
                       <p class="lineHeight16" style="color: ${brandingTemplateForm.getColor().substring(4)}">${brandingTemplateForm.getCompanyOverview()}</p>
                       <br />
                       <!-- <p class="lineHeight16">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.</p> -->
                     </div>
           		</div>
           	</div>
       	  </div>
                  
                  
            <c:if test="${!brandingTemplateForm.getIsSilverCustomer()}">  
                
			<!--IMAGE SLIDER-->
           <div class="row">
	           <div class="ImageSlideArea"> 
	           <div class="row">
	           	<div class="ImageSlideButtonArea">
	               <div id="PhotoSlideButton" >
	               <a href="#" style="color: ${brandingTemplateForm.getColor().substring(4)}"><strong>PHOTOS</strong> </a>
	               </div>
	               <div id="VideoSlideButton" >
	               <a href="#" style="background-color: ${brandingTemplateForm.getColor().substring(4)}" ><strong>VIDEOS</strong></a>
	               </div>
	               <div id="TestSlideButton" >
	               <a href="#" style="background-color: ${brandingTemplateForm.getColor().substring(4)}" ><strong>TESTIMONIALS</strong></a>
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
									items="${brandingTemplateForm.listAddImages}" varStatus="status" step="4">
									<div class="slider1Frames">
									<c:if test="${not empty brandingTemplateForm.listAddImages[status.index].mediaPath}">
										<a id="${brandingTemplateForm.listAddImages[status.index].mediaPath}" onclick="popImage(this.id);" >
											<div class="slider1FrameA1">
												<img src="<%=request.getContextPath()%>/brandingTemplates/viewImage.html?id=${brandingTemplateForm.listAddImages[status.index].mediaPath}"
													
													>
											</div>
										</a>
										</c:if>
										<c:if test="${not empty brandingTemplateForm.listAddImages[status.index+1].mediaPath}">
										 <a
											id="${brandingTemplateForm.listAddImages[status.index+1].mediaPath}" onclick="popImage(this.id);">
											<div class="slider1FrameA1">
												<img src="<%=request.getContextPath()%>/brandingTemplates/viewImage.html?id=${brandingTemplateForm.listAddImages[status.index+1].mediaPath}"
													
													>
											</div>
										</a>
										</c:if>
										<c:if test="${not empty brandingTemplateForm.listAddImages[status.index+2].mediaPath}">
										<a
											id="${brandingTemplateForm.listAddImages[status.index+2].mediaPath}" onclick="popImage(this.id);">
											<div class="slider1FrameA1">
												<img src="<%=request.getContextPath()%>/brandingTemplates/viewImage.html?id=${brandingTemplateForm.listAddImages[status.index+2].mediaPath}"
													
													>
											</div>
										</a>
										</c:if>
										<c:if test="${not empty brandingTemplateForm.listAddImages[status.index+3].mediaPath}">
										<a
											id="${brandingTemplateForm.listAddImages[status.index+3].mediaPath}" onclick="popImage(this.id);">
											<div class="slider1FrameA1">
												<img src="<%=request.getContextPath()%>/brandingTemplates/viewImage.html?id=${brandingTemplateForm.listAddImages[status.index+3].mediaPath}"
													
													>
											</div>
										</a>
										</c:if>
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
									items="${brandingTemplateForm.listVideos}" varStatus="status" step="4">
									<div class="slider1Frames">
									<c:if test="${not empty brandingTemplateForm.listVideos[status.index].mediaPath}">
									<div class="floatLeft width285 marginLeft10 marginRight10">
									&nbsp;
									<div id="mediaspacePath" style="display: none;">${brandingTemplateForm.listVideos[status.index].mediaPath}</div> 
									<div id="mediaspace"></div> 
									
									<script type="text/javascript">
										var cnt = document.getElementById("mediaspace");
										var src = '../resources/MediaFiles/wmvplayer.xaml';
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
									</c:if>
									<c:if test="${not empty brandingTemplateForm.listVideos[status.index+1].mediaPath}">
									<div class="floatLeft width285 marginLeft10 marginRight10">
									&nbsp;
									<div id="mediaspacePath2" style="display: none;">${brandingTemplateForm.listVideos[status.index+1].mediaPath}</div> 
									<div id="mediaspace2"></div> 
									
									<script type="text/javascript">
										var cnt = document.getElementById("mediaspace2");
										//var src2 = '../resources/MediaFiles/wmvplayer.xaml';
										var filePath = $("#mediaspacePath2").text();
										var cfg = {
											file: filePath,
											height:'165',
											width:'260',
											autostart:'false'
										};
										var ply = new jeroenwijering.Player(cnt,src,cfg);
									</script> 
									</div>
									</c:if>
									<c:if test="${not empty brandingTemplateForm.listVideos[status.index+2].mediaPath}">
									<div class="floatLeft width285 marginLeft10 marginRight10">
									&nbsp;
									<div id="mediaspacePath3" style="display: none;">${brandingTemplateForm.listVideos[status.index+2].mediaPath}</div> 
									<div id="mediaspace3"></div> 
									
									<script type="text/javascript">
										var cnt = document.getElementById("mediaspace3");
										//var src = '../resources/MediaFiles/wmvplayer.xaml';
										var filePath = $("#mediaspacePath3").text();
										var cfg = {
											file: filePath,
											height:'165',
											width:'260',
											autostart:'false'
										};
										var ply = new jeroenwijering.Player(cnt,src,cfg);
									</script> 
									</div>
									</c:if>
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
									items="${brandingTemplateForm.listTestimony}" varStatus="status" step="4">
									<div class="slider1Frames">
									<c:if test="${not empty brandingTemplateForm.listTestimony[status.index].testimony}">
										<a
											id="${brandingTemplateForm.listTestimony[status.index].testimony}"  onclick="popTestimony(this.id);">
											<div class="slider1FrameA1">
												<p class="BannerTextBoxBlankSlide"  >${brandingTemplateForm.listTestimony[status.index].testimony}</p>
											</div>
										</a>
										</c:if>
										<c:if test="${not empty brandingTemplateForm.listTestimony[status.index+1].testimony}"> 
										<a
											id="${brandingTemplateForm.listTestimony[status.index+1].testimony}" onclick="popTestimony(this.id);" >
											<div class="slider1FrameA1">
												<p class="BannerTextBoxBlankSlide" >${brandingTemplateForm.listTestimony[status.index+1].testimony}</p>
											</div>
										</a>
										</c:if>
										<c:if test="${not empty brandingTemplateForm.listTestimony[status.index+2].testimony}">
										<a
											id="${brandingTemplateForm.listTestimony[status.index+2].testimony}" onclick="popTestimony(this.id);">
											<div class="slider1FrameA1">
												<p class="BannerTextBoxBlankSlide"  >${brandingTemplateForm.listTestimony[status.index+2].testimony}</p>
											</div>
										</a>
										</c:if>
										<c:if test="${not empty brandingTemplateForm.listTestimony[status.index+3].testimony}">
										<a
											id="${brandingTemplateForm.listTestimony[status.index+3].testimony}" onclick="popTestimony(this.id);">
											<div class="slider1FrameA1">
												<p class="BannerTextBoxBlankSlide" >${brandingTemplateForm.listTestimony[status.index+3].testimony}</p>
											</div>
										</a>
										</c:if>
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
            <div class="ContantMiddleBox marginTop0 " style="border-top: 5px solid ${brandingTemplateForm.getColor().substring(4)}">
            <div class="ContantMiddleLeftBox">
            
                <div class="ContantMiddleLeftLink">
                          <div class="row">
                    <div class="rowEvenButSpacing paddingBottom10"> <span class="floatLeft marginTop10"><a class="btn_smB ColorButton" style="background-color: ${brandingTemplateForm.getColor().substring(4)}" href="#">Apply now</a> <a class="btn_smC white01" style="color: ${brandingTemplateForm.getColor().substring(4)}" href="#">save this job</a></span> </div>
                  </div>
                  <div class="ShareSearchView">
                  	<div class="ShareArea">
                    <span>
                    <div class="ShareText">Send to friend:&nbsp;</div>
                    <img class = "email" onclick="sendToFrd(${jobDetail.jobID}, '${job.jobTitle}');">
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
                          <div class="row marginTop5">
                    <h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${brandingTemplateForm.getColor().substring(4)}"><strong>CITY :</strong></h1>
                    <p>Job City</p>
                    <!-- <p>Baltimore</p> -->
                  </div>
                          <div class="row marginTop5">
                    <h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${brandingTemplateForm.getColor().substring(4)}"><strong>STATE :</strong></h1>
                    <p>Job State</p>
                    <!-- <p>Maryland</p> -->
                  </div>
                          <div class="row marginTop5">
                    <h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${brandingTemplateForm.getColor().substring(4)}"><strong>COUNTRY :</strong></h1>
                    <p>Job Country</p>
                  </div>
                          <div class="row marginTop5">
                    <h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${brandingTemplateForm.getColor().substring(4)}"><strong>JOB ID NUMBER :</strong></h1>
                    <p>00000000</p>
                  </div>
                 </div>
                 
                 <c:if test="${brandingTemplateForm.getPackageId()==3}">
                 <div class="row">
                 <div class="ContantLeftBlueBox " style="background: ${brandingTemplateForm.getColor().substring(4)} ">
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
	             <div class="LeftBoxLink" style="border-top: 5px solid ${brandingTemplateForm.getColor().substring(4)};">
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
		                <h1 class="HeadText" style="color: ${brandingTemplateForm.getColor().substring(4)}">Job Posting Title</h1>
		                <br />
		                <br />
		                <h3 class="HeadText" style="color: ${brandingTemplateForm.getColor().substring(4)}">JOB  SUMMARY:</h3>
		                <br />
		                <div class="lineHeight16">
		                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
		                <!-- <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p> -->
		                <br />
	               		</div>
                	</div>
                
	                <div class="ContantMiddleLeftLink marginBottom20">
	                          <div class="row">
	                    <div class="rowEvenButSpacing paddingBottom10"> <span class="floatLeft marginTop10"><a class="btn_smB ColorButton" style="background-color: ${brandingTemplateForm.getColor().substring(4)}" href="#">Apply now</a> <a class="btn_smC white01" style="color: ${brandingTemplateForm.getColor().substring(4)}" href="#">save this job</a></span> </div>
	                  </div>
	                 <div class="ShareSearchView">
                  	<div class="ShareArea">
                    <span>
                    <div class="ShareText">Send to friend:&nbsp;</div>
                    <img class = "email" onclick="sendToFrd(${jobDetail.jobID}, '${jobDetail.jobTitle}');">
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
              <div class="clearfix"></div>
                   
        
        
  </div>             
 </div>        
  <div class="clearfix"></div>
  </div>       
</div>
<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
              

</body>
</html>