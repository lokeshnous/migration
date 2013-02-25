<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
                                                  prefix="fn" %>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
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
		<script type='text/javascript' src="<%= request.getContextPath() %>/resources/js/silverlight.js"></script>
		<script type='text/javascript' src="<%= request.getContextPath() %>/resources/js/wmvplayer.js"></script>
										  
  <jsp:include page="common/include.jsp" />
  <script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/slider.js"></script>
		<script type="text/javascript">
		    jQuery(document).ready(function(){
		    	jQuery(".megamenu").megamenu();
		    	popUpIds();
		    	$("#descriptionText a").live("click", function() {     
                    trackClick('${jobDetail.jobId}','8');        
					if($(this).attr("target")== null || $(this).attr("target")=="_self"){
						$(this).attr("target","_blank");
					}
            	});
		});
		    
		    function showMessage(){
		    	alert("You must be a registered Job-Seeker to apply to jobs");
		    }
		    
		    function showSaveMessage(){
		    	alert("You must be a registered Job-Seeker to save the jobs");
		    }
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
			if(path!=''){
			$.nmManual('<%= request.getContextPath() %>/search/viewImage.html?id='+path ,  {closeOnEscape: true, showCloseButton: true, sizes:{initW: 100, initH: 100, minW: 150, minH: 150,  w: 500, h: 500}});
			}
		}
		
		function popTestimony(testimonyId, templateId) {
			if(testimonyId !=''){
			$.nmManual('<%= request.getContextPath() %>/search/viewTestimonial.html?id='+testimonyId+'&templateId='+templateId,  {closeOnEscape: true, showCloseButton: true, sizes:{initW: 100, initH: 50, minW: 200, minH: 100,  w: 600, h: 600}});
			}
		}
		</script>
  
</head>
    
    <body class="job_board">    
        <%-- <div class="ad_page_top">
			${adPageTop}
        </div> --%>
        
<div class="main_wrapper_outside">
 <div class="main_wrapper_inside">
 <div class="main">
            <jsp:include page="../templates/templates_header.jsp"></jsp:include>
				

                <!-- <div class="content_wrapper"> -->

		    <div class="jobDetails"> 
			<div class="jobDetailsEyebrow">
			<div class="floatLeft"> <h3 class="jobDetailsEyebrowHeader">Job Details </h3> </div> 
			<div class="floatRight">
				<c:choose><c:when test="${returnResults != 'null'}">
                   <a href='<%= request.getContextPath() %>/search/findJobPage.html' class="link_color2_emphasized">Return to Search Results &nbsp; </a>
                </c:when>
                <c:otherwise></c:otherwise>
                </c:choose>
			</div>
			</div>
			</div>
			
			<!-- Start Branding -->
			
<div class="row" >
        
         <!--LOGO AREA-->
       	 <div class="row marginTop5">
          <div class="row marginTop16">
               	<div class="LogoAreaBox" ><img style="min-width:50px; max-width:100%; max-height:100%;" src="<%=request.getContextPath()%>/search/viewImage.html?id=${jobDetail.getLogo()}"  alt="logo" border="0" /></div>
          </div>
          <div class="BoxText" alt="Color" width="500" height="60" border="0" style="color: ${jobDetail.getColor().substring(4)}"/></div>
         </div>
         
          <!--BANNER AREA-->
          <div class="row">
          <input value="<%=request.getContextPath()%>" type="hidden" id="contextPath">
         	<div class="BannerAreaBox" style="background: ${jobDetail.getColor().substring(4)}">
                   <div class="BannerAreaInnerBox">
               <div class="BannerImgBox" > <img src="<%=request.getContextPath()%>/search/viewImage.html?id=${jobDetail.getImagePath()}" class="imgCenter" alt="Main image"></div>
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
									<c:if test="${not empty jobDetail.listAddImages[status.index].mediaPath}">
										<a id="${jobDetail.listAddImages[status.index].mediaPath}" onclick="popImage(this.id);" >
											<div class="slider1FrameA1">
												<img src="<%=request.getContextPath()%>/search/viewImage.html?id=${jobDetail.listAddImages[status.index].mediaPath}"
													
													>
											</div>
										</a>
										</c:if>
										<c:if test="${not empty jobDetail.listAddImages[status.index+1].mediaPath}"> <a
											id="${jobDetail.listAddImages[status.index+1].mediaPath}" onclick="popImage(this.id);">
											<div class="slider1FrameA1">
												<img src="<%=request.getContextPath()%>/search/viewImage.html?id=${jobDetail.listAddImages[status.index+1].mediaPath}"
													
													>
											</div>
										</a>
										</c:if>
										<c:if test="${not empty jobDetail.listAddImages[status.index+2].mediaPath}">
										<a
											id="${jobDetail.listAddImages[status.index+2].mediaPath}" onclick="popImage(this.id);">
											<div class="slider1FrameA1">
												<img src="<%=request.getContextPath()%>/search/viewImage.html?id=${jobDetail.listAddImages[status.index+2].mediaPath}"
													
													>
											</div>
										</a>
										</c:if>
										<c:if test="${not empty jobDetail.listAddImages[status.index+3].mediaPath}">
										<a
											id="${jobDetail.listAddImages[status.index+3].mediaPath}" onclick="popImage(this.id);">
											<div class="slider1FrameA1">
												<img src="<%=request.getContextPath()%>/search/viewImage.html?id=${jobDetail.listAddImages[status.index+3].mediaPath}"
													
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
	               <script type="text/javascript">
								var src = '<%= request.getContextPath() %>/resources/MediaFiles/wmvplayer.xaml';
					</script>
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
									items="${videoList}" varStatus="status" step="3">
									
									<div class="slider1Frames">
									<c:if test="${not empty videoList[status.index]}">
										<div class="floatLeft width285 marginLeft10 marginRight10">
										&nbsp;
										<div id="mediaspacePath${status.index}" style="display: none;">${videoList[status.index]}</div> 
										<div name="mediaspace${status.index}" id="mediaspace${status.index}"></div> 
										<script type="text/javascript">
											var statusIndex = parseInt('${status.index}');
											var cnt = document.getElementById("mediaspace"+statusIndex);
											var filePath = $("#mediaspacePath"+statusIndex).text();
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
										<c:if test="${not empty videoList[status.index+1]}">
										<div class="floatLeft width285 marginLeft10 marginRight10">
										&nbsp;
										<div id="mediaspacePath${status.index+1}" style="display: none;">${videoList[status.index+1]}</div> 
										<div id="mediaspace${status.index+1}"></div> 
										<script type="text/javascript">
											var statusIndex = parseInt('${status.index+1}');
											var cnt = document.getElementById("mediaspace"+statusIndex);
											var filePath = $("#mediaspacePath"+statusIndex).text();
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
										<c:if test="${not empty videoList[status.index+2]}">
										<div class="floatLeft width285 marginLeft10 marginRight10">
										&nbsp;
										<div id="mediaspacePath${status.index+2}" style="display: none;">${videoList[status.index+2]}</div> 
										<div id="mediaspace${status.index+2}"></div> 
										<script type="text/javascript">
											var statusIndex = parseInt('${status.index+2}');
											var cnt = document.getElementById("mediaspace"+statusIndex);
											var filePath = $("#mediaspacePath"+statusIndex).text();
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
									items="${jobDetail.listTestimony}" varStatus="status" step="4">
									<div class="slider1Frames">
									<c:if test="${not empty jobDetail.listTestimony[status.index].testimony}">
										<a class="cursor"
											id="${jobDetail.listTestimony[status.index].testimonyId}"  onclick="popTestimony(this.id, ${jobDetail.templateId});">
											<div class="slider1FrameA1">
												<p class="BannerTextBoxBlankSlide" >${jobDetail.listTestimony[status.index].testimony}</p>
											</div>
										</a> 
										</c:if>
									<c:if test="${not empty jobDetail.listTestimony[status.index+1].testimony}">
										<a class="cursor"
											id="${jobDetail.listTestimony[status.index+1].testimonyId}" onclick="popTestimony(this.id, ${jobDetail.templateId});" >
											<div class="slider1FrameA1">
												<p class="BannerTextBoxBlankSlide" >${jobDetail.listTestimony[status.index+1].testimony}</p>
											</div>
										</a>
										</c:if>
									<c:if test="${not empty jobDetail.listTestimony[status.index+2].testimony}">
										<a class="cursor"
											id="${jobDetail.listTestimony[status.index+2].testimonyId}" onclick="popTestimony(this.id, ${jobDetail.templateId});">
											<div class="slider1FrameA1">
												<p class="BannerTextBoxBlankSlide" >${jobDetail.listTestimony[status.index+2].testimony}</p>
											</div>
										</a>
										</c:if>
									<c:if test="${not empty jobDetail.listTestimony[status.index+3].testimony}">
										<a class="cursor"
											id="${jobDetail.listTestimony[status.index+3].testimonyId}" onclick="popTestimony(this.id, ${jobDetail.templateId});">
											<div class="slider1FrameA1">
												<p class="BannerTextBoxBlankSlide" >${jobDetail.listTestimony[status.index+3].testimony}</p>
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
            <div class="ContantMiddleBox01" style="border-top: 5px solid ${jobDetail.getColor().substring(4)}">
            <div class="ContantMiddleLeftBox">
            
                <div class="ContantMiddleLeftLink">
                          <div class="row">
                    <div class="rowEvenButSpacing paddingBottom10">
                    <security:authorize	access=" !hasRole('ROLE_FACILITY') and !hasRole('ROLE_FACILITY_GROUP') and !hasRole('ROLE_FACILITY_SYSTEM')">
                     <span class="floatLeft marginTop10">
                    <a class="btn_smB ColorButton cursor" style="background-color: ${jobDetail.getColor().substring(4)}" onclick="selectResume(${jobDetail.jobId});" >Apply now</a> 
                    <a class="btn_smC white01 cursor" style="color: ${jobDetail.getColor().substring(4)}" onclick="saveThisJob(${jobDetail.jobId});" id="btsaveThisJobId" >save this job</a></span> 
                    </security:authorize>
                    
                    <security:authorize	access="hasRole('ROLE_FACILITY') or hasRole('ROLE_FACILITY_GROUP') or hasRole('ROLE_FACILITY_SYSTEM')">
                     <span class="floatLeft marginTop10">
                    <a class="btn_smB ColorButton cursor" style="background-color: ${jobDetail.getColor().substring(4)}" onclick="showMessage();" >Apply now</a> 
                    <a class="btn_smC white01 cursor" style="color: ${jobDetail.getColor().substring(4)}" onclick="showSaveMessage();" id="btsaveThisJobId" >save this job</a></span> 
                    </security:authorize>
                    </div>
                 <br/><br/>
			    <div class="FormErrorDisplayText" id="topjobActionInfo" ></div><br/><br/><br/>
                  </div>
                  <div class="ShareSearchView">
                  	<div class="ShareArea">
                    <div class="ShareText">Send to friend:&nbsp;</div>
                    <img class = "email cursor" onclick="sendToFrd(${jobDetail.jobId}, '${jobDetail.jobTitle}');">
                    </div>
                    <div class="ShareArea">
                    <div class="ShareText">|&nbsp;&nbsp;Share:&nbsp;</div>
                    <a class="fbook" onclick="trackClick(${jobDetail.jobId},'9');" href="http://www.facebook.com/sharer.php?u=${basePath}/search/jobview/${jobDetail.jobId}/${fn:toLowerCase(fn:replace(jobDetail.jobTitleEncode, 
                                					' ', '-'))}.html" target="_blank"></a>
				   <a onclick="trackClick(${jobDetail.jobId},'9');" href="https://www.linkedin.com/cws/share?url=${basePath}/search/jobview/${jobDetail.jobId}/${fn:toLowerCase(fn:replace(jobDetail.jobTitleEncode, 
                                					' ', '-'))}.html" target="_blank"><div class="linkedIn"></div></a>
				   <a onclick="trackClick(${jobDetail.jobId},'9');" href="https://twitter.com/share?url=${basePath}/search/jobview/${jobDetail.jobId}/${fn:toLowerCase(fn:replace(jobDetail.jobTitleEncode, 
                                					' ', '-'))}.html" class="twitter" data-url="${basePath}/search/jobview/${jobDetail.jobId}/${fn:toLowerCase(fn:replace(jobDetail.jobTitleEncode, 
                                					' ', '-'))}.html" data-count="none" target="_blank"></a>
                    </div>
                    <div class="ShareArea">
                    <div class="ShareText">|&nbsp;&nbsp;Print:&nbsp;</div>
                     <a href="" onclick="trackPrint(${jobDetail.jobId},'3');">
                     <div class="printJBdetail"></div></a>
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
                    <h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${jobDetail.getColor().substring(4)}"><strong>ZIP CODE :</strong></h1>
                    <p>${jobDetail.postCode}</p>
                  </div>
                  </c:if>
                          <div class="row marginTop5">
                    <h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${jobDetail.getColor().substring(4)}"><strong>JOB ID NUMBER :</strong></h1>
                    <p>${jobDetail.jobNumber}</p>
                  </div>
                  
                  
                  
                  <div class="row marginTop5">
                    <c:if test="${not empty jobDetail.url and not empty jobDetail.urlDisplay and jobDetail.urlDisplay!='None' and jobDetail.url!='None'}">
									<h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${jobDetail.getColor().substring(4)}"><strong>WEBSITE :</strong></h1>
										<p><a class="color2" target="_blank" onclick="trackClick(${jobDetail.jobId},'7');" href="${jobDetail.url}">${jobDetail.urlDisplay}</a></p>
										<%-- <span class="specs">Web Site:</span>&nbsp;&nbsp;<a class="color2" target="_blank" href="${jobDetail.url}">${jobDetail.urlDisplay}</a>&nbsp;&nbsp;|&nbsp;&nbsp; --%>
					</c:if>
					<c:if test="${not empty jobDetail.url and  empty jobDetail.urlDisplay and jobDetail.url!='None' and fn:containsIgnoreCase(urlString, 'http')}">
									<h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${jobDetail.getColor().substring(4)}"><strong>WEBSITE :</strong></h1>
										
										<p><a class="color2" target="_blank" onclick="trackClick(${jobDetail.jobId},'7');" href="${jobDetail.url}">${jobDetail.url}</a></p>
										<!-- <span class="specs">Web Site:</span>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp; -->
					</c:if>
					<c:if test="${not empty jobDetail.url and  empty jobDetail.urlDisplay and jobDetail.url!='None' and !fn:containsIgnoreCase(urlString, 'http')}">
									<h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${jobDetail.getColor().substring(4)}"><strong>WEBSITE :</strong></h1>
										<p><a class="color2" target="_blank" onclick="trackClick(${jobDetail.jobId},'7');" href=" http://${jobDetail.url}">${jobDetail.url}</a></p>
										<!-- <span class="specs">Web Site:</span>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp; -->
					</c:if>
                  </div>
                  
                    <div class="row marginTop5">
                    <c:if test="${not empty jobDetail.email and not empty jobDetail.emailDisplay and jobDetail.emailDisplay!='None' and jobDetail.email!='None'}">
										<%-- <span class="specs">Email:</span>&nbsp;&nbsp;<a class="color2" target="_blank" href="mailto: ${jobDetail.email}?subject=Apply%20via%20email">${jobDetail.emailDisplay}</a> --%>
										<h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${jobDetail.getColor().substring(4)}"><strong>EMAIL :</strong></h1>
										<p><a class="color2" target="_blank" onclick="trackClick(${jobDetail.jobId},'5');" href="mailto: ${jobDetail.email}?subject=Apply%20via%20email">${jobDetail.emailDisplay}</a></p>
					</c:if>
					<c:if test="${not empty jobDetail.email and empty jobDetail.emailDisplay and jobDetail.email!='None'}">
										<%-- <span class="specs">Email:</span>&nbsp;&nbsp;<a class="color2" href="mailto: ${jobDetail.email}?subject=Apply%20via%20email">${jobDetail.email}</a> --%>
										<h1 class="FloatLeft FontSize12 HeadText marginRight5" style="color: ${jobDetail.getColor().substring(4)}"><strong>EMAIL :</strong></h1>
										<p><a class="color2"  onclick="trackClick(${jobDetail.jobId},'5');" href="mailto: ${jobDetail.email}?subject=Apply%20via%20email">${jobDetail.email}</a></p>
					</c:if>
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
	                    <div class="BlueBoxCont"><a href="#" class="TextColorA02Link" onclick="viewJobDetails(${jobDTO.jobId},'${jobDTO.encodedJobTitle}')">
	                      <h3 class="TextColor02">${jobDTO.jobTitle}</h3>
	                      </a></div>
                    </c:forEach> 
                 </div>
               	 </div>
                 
                 <c:if test="${not empty newsDTOList}">
	             <div class="row marginTop15">
	             <div class="LeftBoxLink" style="border-top: 5px solid ${jobDetail.getColor().substring(4)};">
                    <div class="BlueBoxContA"> <a href="<%= request.getContextPath() %>/search/getPlatinumNewsList.html?jobId=${jobDetail.jobId}" target="_blank" class="UnderLineNone">
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
                 </c:if>
              </div>
              
              
              <div class="ContantMiddleRightBox">
                	<DIV class="row">
		                <h1 class="HeadText" style="color: ${jobDetail.getColor().substring(4)}">${jobDetail.jobTitle} </h1>
		                <br />
		                <h2 class="sectionSubHeader MarginBottom10">${jobDetail.companyNameDisp}</h2>
		                <br />
		                
			            <%-- <c:if test="${not empty jobDetail.getHeadLine() and jobDetail.getHeadLine()!='none'}">
			            <h3 style="color: ${jobDetail.getColor().substring(4)}">${jobDetail.getHeadLine()}</h3>
			            </c:if>
			            <c:if test="${not empty jobDetail.getPositionLevel() and jobDetail.getPositionLevel()!='none'}">
			            <h3 style="color: ${jobDetail.getColor().substring(4)}">${jobDetail.getPositionLevel()}</h3>
			            </c:if>
			            <c:if test="${not empty jobDetail.getPositionType() and jobDetail.getPositionType()!='none'}">
			            <h3 style="color: ${jobDetail.getColor().substring(4)}">${jobDetail.getPositionType()}</h3>
			            </c:if> --%>
			            
			            
			            
			            <c:if test="${not empty jobDetail.headLine}">
			            
			            
			            <div class="rowPadding">
								<p> <span class="detailHeaderOrange" style="color: ${jobDetail.getColor().substring(4)}">HEADLINE:&nbsp;</span>
								
								${jobDetail.headLine}</p>
								</div>
			            
			            
			           <%--  <h3 class="HeadText" style="color: ${jobDetail.getColor().substring(4)}">HEADLINE:</h3>
		               
		                <div class="lineHeight16">
		                <!-- <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p> -->
		                <p class="article">${jobDetail.headLine}</p>
		                <br />
	               		</div> --%>
			           
								</c:if>
								<c:if test="${not empty jobDetail.positionLevel}">
								<div class="rowPadding">
								<p> <span class="detailHeaderOrange" style="color: ${jobDetail.getColor().substring(4)}">POSITION LEVEL:&nbsp;</span>
								
								${jobDetail.positionLevel}</p>
								</div>
								
								
								<%-- <h3 class="HeadText" style="color: ${jobDetail.getColor().substring(4)}">POSITION LEVEL:</h3>
		                
		                <div class="lineHeight16">
		                <!-- <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p> -->
		                <p class="article">${jobDetail.positionLevel}</p>
		                <br />
	               		</div> --%>
								</c:if>
								<c:if test="${not empty jobDetail.positionType}">
								<div class="rowPadding">
								
									<p> <span class="detailHeaderOrange" style=" color: ${jobDetail.getColor().substring(4)}">POSITION TYPE:&nbsp;</span>
									
								
								${jobDetail.positionType}</p>
								</div>
								
								 <%-- <h3 class="HeadText" style="color: ${jobDetail.getColor().substring(4)}">POSITION TYPE:</h3>
		                <div class="lineHeight16">
		                <!-- <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p> -->
		                <p class="article">${jobDetail.positionType}</p>
		                <br />
	               		</div> --%>
								</c:if>
			            
			           <div class="rowPadding"> 
		                <span class="detailHeaderOrange marginTop5" style="color: ${jobDetail.getColor().substring(4)}">JOB  SUMMARY:</span>
		                <div class="JobDetailHeaderRightView">
				            <c:if test="${(jobDetail.blindAd == '0') && isFeatureEmployer}">
				            <a	href="<%=request.getContextPath()%>/healthcare/featuredemployerdetails.html?id=${jobDetail.facilityId}"><img
									onclick="trackClick(${jobDetail.jobId},'6');"
									src="<%=request.getContextPath()%>/resources/images/FeaturedEmp.png"
									alt="Featured Employer" width="164" height="23"></img> </a>
			            </c:if> 
			            </div>
		                <br />
		                <br />
		                <div class="lineHeight16">
		                <!-- <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p> -->
		                <div id="descriptionText" class="article">${jobDetail.adText}</div>
		                <br />
		                <img src="${jobDetail.trackingPixel}" />
	               		</div>
                	</div>
                
	                <div class="ContantMiddleLeftLink marginBottom20">
	                          <div class="row">
	                    <div class="rowEvenButSpacing paddingBottom10"> 
	                    <security:authorize	access=" !hasRole('ROLE_FACILITY') and !hasRole('ROLE_FACILITY_GROUP') and !hasRole('ROLE_FACILITY_SYSTEM')">
	                    <span class="floatLeft marginTop10">
	                    <a class="btn_smB ColorButton cursor" style="background-color: ${jobDetail.getColor().substring(4)}" onclick="bottomSelectResume(${jobDetail.jobId});" >Apply now</a> 
	                    <a class="btn_smC white01 cursor" style="color: ${jobDetail.getColor().substring(4)}" onclick="btsaveThisJob(${jobDetail.jobId});" id="btsaveThisJobId">save this job</a>
	                    </span> 
	                    </security:authorize>
	                    
	                    <security:authorize	access="hasRole('ROLE_FACILITY') or hasRole('ROLE_FACILITY_GROUP') or hasRole('ROLE_FACILITY_SYSTEM')">
	                    <span class="floatLeft marginTop10">
	                    <a class="btn_smB ColorButton cursor" style="background-color: ${jobDetail.getColor().substring(4)}" onclick="showMessage();" >Apply now</a> 
	                    <a class="btn_smC white01 cursor" style="color: ${jobDetail.getColor().substring(4)}" onclick="showSaveMessage();" id="btsaveThisJobId">save this job</a>
	                    </span> 
	                    </security:authorize>
	                    </div>
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
                    <a class="fbook" onclick="trackClick(${jobDetail.jobId},'9');" href="http://www.facebook.com/sharer.php?u=${basePath}/search/jobview/${jobDetail.jobId}/${fn:toLowerCase(fn:replace(jobDetail.jobTitle, 
                                					' ', '-'))}.html" target="_blank"></a>
				   <a onclick="trackClick(${jobDetail.jobId},'9');" href="https://www.linkedin.com/cws/share?url=${basePath}/search/jobview/${jobDetail.jobId}/${fn:toLowerCase(fn:replace(jobDetail.jobTitle, 
                                					' ', '-'))}.html" target="_blank"><div class="linkedIn"></div></a>
				   <a onclick="trackClick(${jobDetail.jobId},'9');" href="https://twitter.com/share?url=${basePath}/search/jobview/${jobDetail.jobId}/${fn:toLowerCase(fn:replace(jobDetail.jobTitle, 
                                					' ', '-'))}.html" class="twitter" data-url="${basePath}/search/jobview/${jobDetail.jobId}/${jobDetail.jobTitle}.html" data-count="none" target="_blank"></a>
                      </span>
                    </div>
                    <div class="ShareArea">
                    <span>
                    <div class="ShareText">|&nbsp;&nbsp;Print:&nbsp;</div>
                    <a href="" onclick="trackPrint(${jobDetail.jobId},'3');" ><div class="printJBdetail"></div></a>
                    </span>
                    </div>
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

                <%-- <div class="ad_wrapper">
					${adPageBottom}
                </div> --%>
                <!-- ad_wrapper -->

 </div><!-- main -->
 </div> <!-- end main_wrapper_inside -->   
</div> <!-- end main_wrapper_outside -->

<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
    </body>
</html>