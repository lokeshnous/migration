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
<div class="ad_page_top"> <img src="../resources/images/ads/banner_ad_fpo.png" /> </div>
<div class="main_wrapper_outside">
          <div class="main_wrapper_inside">
    <div class="main">
    <jsp:include page="../templates/templates_header.jsp"></jsp:include>
 <form action="" method="get" commandName="createResume" id="viewresumeId">
              <div class="clearfix"></div>
              <!--Start:MidContant-->
              <div class="MidContent_Wrapper floatLeft">
              <div class="popupHeader Padding0  OrangeBG">
                  <h2>VIEW YOUR RESUME</h2>
          <span class="floatRight marginRight10"><a href="/jobboard/jobSeeker/jobSeekerDashBoard.html" class="link_color3_emphasized FontSize12 FontWeight">Back to Dashboard</a></span></div>

        
        <div class="clearfix"></div>
        <div class="MidContent_Wrapper FloatLeft marginBottom10">
                  <div class="ResumeHeader"> <span class="FontSize18"><c:out value="${createResume.resumeName}"/></span>
            <div class="clearfix"></div>
            <span>
                    <h3 class="marginTop3"><c:out value="${createResume.desiredJobTitle}"/></h3>
                    </span>

            <div class="clearfix"></div>
            <span>
                   <!--  <p class="marginTop3">Available 08/01/2012</p> -->
                    </span> </div>
                  <div class="IconsArea"><a href="#"><img src="../resources/images/Download.png" width="20" height="20" alt=""></a>&nbsp; <a href="#"><img src="../resources/images/Print2.png" width="20" height="20" alt=""></a></div>
                </div>
           <div class="rowEvenNewSpacing">
					<span class="FontSize18">Resume:</span><br><br>
					<div class="clearfix"></div>
					<textarea readonly="readonly"
						cols="120" rows="50">${createResume.resumeText}</textarea>
						<!-- class="textareaBoxCResume Height255 marginTop5 " -->
		 </div>     
      </div>
 </form>                
              <!--Start:MidContant-->
              <div class="clearfix"></div>
              <!-- content_wrapper -->
              <div class="ad_wrapper"> <img src="../resources/images/ads/banner_ad_fpo.png" /> </div>
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