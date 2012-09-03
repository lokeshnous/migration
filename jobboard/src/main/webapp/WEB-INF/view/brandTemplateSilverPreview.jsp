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

		<script type="text/javascript">
		    jQuery(document).ready(function(){
		    jQuery(".megamenu").megamenu();
		});
		</script>
		</head>

		<body class="job_board">
<!--<div class="ad_page_top"> <img src="images/ads/banner_ad_fpo.png" /> </div>-->
<div class="main_wrapper_outside marginTop30">
          <div class="main_wrapper_insideRC">
    <div class="main">
               <!-- header_wrapper -->
              
              
        				<!-- <div class="abc" align="right">
							<img src="../resources/images/Close.png" width="19"
								class="nyroModalClose" height="19" alt="Close" >
						</div> -->
     		 
     		 <div class="floatRight">
							<!-- <span class="floatRight"> <a href="../employer/employerDashBoard.html">Back To Dashboard </a></span> -->
							<span class="floatRight"> <a href="../brandingTemplates/displayTemplate.html">Back</a></span>
			</div>
						
              <!--nav--> 
              
              <!-- ad_col_right -->
              <!-- <div class="clearfix"></div> -->
              <!--Cont-->
              <div class="row">
        <div class="row marginTop5">
					


						<!--LOGO AREA-->
          <div class="row marginTop16">
               	<div class="LogoAreaBox" ><img src="<%=request.getContextPath()%>/brandingTemplates/viewImage.html?id=${brandingTemplateForm.getLogoPath()}"  alt="logo" width="335" height="60" border="0" /></div>
          </div>
          
          <div class="BoxText" alt="Color" width="500" height="60" border="0" style="color: #FF0000"/></div>
          </div>
                  <!--BANNER AREA-->
                  <div class="row">
            <div class="BannerAreaBox" >
                      <div class="BannerAreaInnerBox">
                <div class="BannerImgBox"> <img src="<%=request.getContextPath()%>/brandingTemplates/viewImage.html?id=${brandingTemplateForm.getMainImagePath()}" width="490" height="319" alt="Main image"></div>
                <div class="BannerTextBox">
							                
                          <h1>About This Employer</h1>
                          <br />
                          <p class="lineHeight16">${brandingTemplateForm.getCompanyOverview()}</p>
                          <br />
                          <!-- <p class="lineHeight16">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.</p> -->
                        </div>
              </div>
                    </div>
          </div>
                  
                  
                  
                  <div class="row">
            <div class="ContantMiddleBox marginTop0 borderTopNone">
                      <div class="ContantMiddleLeftBox">
                <div class="ContantMiddleLeftLink">
                          <div class="row">
                    <div class="rowEvenButSpacing paddingBottom10"> <span class="floatLeft marginTop10"><a class="btn_smB Blue" href="">Apply now</a> <a class="btn_smC white01" href="">save job</a></span> </div>
                  </div>
                  <div class="row marginTop10">
                  	<div class="ShareArea marginLeft5">
                    <span><p class="FloatLeft marginTop3">Send to friend:</p><a href="#" >&nbsp;<img src="images/email.png"></a></span>
                    </div>
                    <div class="ShareArea BorderLeft">
                    <span><p class="FloatLeft marginTop3">Share:</p><span>&nbsp;<a href=""><img src="images/fbook_sm.png"></a></span> <span><a href=""><img src="images/L_In_sm.png"></a></span> <span><a href=""><img src="images/twitter_sm.png"></a></span></span>
                    </div>
                    <div class="ShareArea BorderLeft paddingRight0">
                    <span><p class="FloatLeft marginTop3">Print:</p><span>&nbsp;<a href=""><img src="images/Print.png"></a></span></span>
                    </div>
                  </div>
                          <div class="clearfix"></div>
                          <div class="DotBorderBottom marginTop20"></div>
                        </div>
                <div class="row marginTop20">
                          <h3 class="TextColorA01 FontSize18">Name of Facility</h3>
                          <div class="row marginTop5">
                    <h1 class="FloatLeft FontSize12 HeadTextBlue marginRight5"><strong>CITY :</strong></h1>
                    <p>Baltimore</p>
                  </div>
                          <div class="row marginTop5">
                    <h1 class="FloatLeft FontSize12 HeadTextBlue marginRight5"><strong>STATE :</strong></h1>
                    <p>Maryland</p>
                  </div>
                          <div class="row marginTop5">
                    <h1 class="FloatLeft FontSize12 HeadTextBlue marginRight5"><strong>COUNTRY :</strong></h1>
                    <p>US</p>
                  </div>
                          <div class="row marginTop5">
                    <h1 class="FloatLeft FontSize12 HeadTextBlue marginRight5"><strong>JOB ID NUMBER :</strong></h1>
                    <p>00000000</p>
                  </div>
                        </div>
              </div>
                      <!---->
                      <div class="ContantMiddleRightBox">
                <DIV class="row">
                <h1 class="HeadTextBlue">Manager, Home Care Coordination/Discharge Planning </h1>
                <br />
                <br />
                <h3 class="HeadTextBlue">JOB  SUMMARY:</h3>
                <br />
                <div class="lineHeight16">
                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                <br />
                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                <br />
                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                <br />
                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                <br />
                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                <br />
                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                <br />
                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                <br />
                
                </div>
                </div>
                <div class="ContantMiddleLeftLink marginBottom20">
                          <div class="row">
                    <div class="rowEvenButSpacing paddingBottom10"> <span class="floatLeft marginTop10"><a class="btn_smB Blue" href="">Apply now</a> <a class="btn_smC white01" href="">save job</a></span> </div>
                  </div>
                  <div class="row marginTop10">
                  	<div class="ShareArea marginLeft5">
                    <span><p class="FloatLeft marginTop3">Send to friend:</p><a href="#" >&nbsp;<img src="images/email.png"></a></span>
                    </div>
                    <div class="ShareArea BorderLeft">
                    <span><p class="FloatLeft marginTop3">Share:</p><span>&nbsp;<a href=""><img src="images/fbook_sm.png"></a></span> <span><a href=""><img src="images/L_In_sm.png"></a></span> <span><a href=""><img src="images/twitter_sm.png"></a></span></span>
                    </div>
                    <div class="ShareArea BorderLeft paddingRight0">
                    <span><p class="FloatLeft marginTop3">Print:</p><span>&nbsp;<a href=""><img src="images/Print.png"></a></span></span>
                    </div>
                  </div>
                          
                        </div>
              </div>
                    </div>
          </div>
                </div>
        <!--Cont--> 
        
      </div>
              <div class="clearfix"></div>
              <div class="ad_wrapper marginTop0"><img src="images/ads/banner_ad_fpo.png" /> </div>
            </div>
    <!-- main --> 
    
  </div>
          <!-- end main_wrapper_inside --> 
        </div>
<!-- end main_wrapper_outside -->

<!-- footer_wrapper -->

</body>
</html>