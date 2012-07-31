<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>ADVANCE Heathcare Jobs</title>

		<!-- STYLESHEETS -->
		<link href="../resources/css/JB.css" rel="stylesheet" type="text/css" />
		<link href="../resources/css/jquery.megamenu.css" rel="stylesheet" type="text/css" />
		<link href="../resources/css/SliderStyles.css" rel="stylesheet" type="text/css">

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
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
		<script type="text/javascript" src="../resources/js/jquery.cycle.all.min.js"></script>
		<script type="text/javascript" src="../resources/js/slider.js"></script>
		<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>
		<script type="text/javascript">
            $(document).ready(function(){
        		$("#id").click(function() {
     	           parent.window.location.href = "navigateToLogin.html";
     	           parent.$.nmTop().close();

     	      });
                $('.newWindow').click(function (event){
 
                    var url = $(this).attr("href");
                    parent.window.location.href = url;
     	            parent.$.nmTop().close();
                   event.preventDefault();
                });
            });
        </script>
		<script type="text/javascript">
		    jQuery(document).ready(function(){
		    jQuery(".megamenu").megamenu();
		});
		</script>
		</head>

<body class="job_board">
<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer" style="display:block">
                  <div class="popupHeader">
                  <h2>JOBS I'VE APPLIED TO</h2>
                  <a href="#"><img src="../resources/images/Close.png" width="19" height="19" onclick="parent.$.nmTop().close();" alt=""></a></div>
                 
<div class="popUpContainerWrapper"><form:form method="Post">
            <div class="rowEvenNewSpacing marginTop0">
            
              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="grid">
              <tr class="borderTopNone">
                <th width="36%" align="left" scope="col">Job Title</th>
                <th width="32%" align="left" scope="col">Company Name</th>
                <th width="20%" align="left" scope="col">Applied</th>
                <th width="12%" align="center" scope="col">Delete</th>
              </tr>
                 <c:forEach items="${appliedJobDTOList}" var = "dtoList" > 
               <tr>
                <td><a href='<c:url value="/jobsearchactivity/viewJobDetails.html"><c:param name="id" value="${dtoList.getJpJob().getJobId()}"/> </c:url>'  rel="0" target="_blank" class="newWindow" >${dtoList.getJobTitle()}</a></td>
                <td align="left">${dtoList.getFacilityName()}</td>
                <td align="left">${dtoList.getAppliedDt()}</td>
                <td align="center"><a href='<c:url value="/jobSeekerActivity/deleteAppliedJob.html" ><c:param name="appliedJobId" value="${dtoList.getSaveJobId()}"/> </c:url>'><img src="../resources/images/Delete.png" width="20" height="20" alt="" ></a></td>
              </tr>
              </c:forEach>
            </table>
             
          </div>
 <div class="row marginTop20 paddingBottom10"><a href=""  onclick="parent.$.nmTop().close();" class="btn_sm orange">Cancel</a></div>
          </form:form></div>
          <div class="clearfix"></div>
                </div>

</body>
</html>