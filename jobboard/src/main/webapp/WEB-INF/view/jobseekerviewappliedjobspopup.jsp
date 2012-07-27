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

		<!-- JAVASCRIPT FILES -->
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
		<script type="text/javascript" src="../resources/js/jquery.cycle.all.min.js"></script>
		<script type="text/javascript" src="../resources/js/slider.js"></script>
		<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>
		<script type="text/javascript">
            var windowSizeArray = [ "width=800,height=800",
                                    "width=300,height=400,scrollbars=yes" ];
 
            $(document).ready(function(){
                $('.newWindow').click(function (event){
 
                    var url = $(this).attr("href");
                    var windowName = $(this).attr("name");// "/* popUp";*/
                    var windowSize = windowSizeArray[$(this).attr("rel")];
 
                    window.open(url, windowName, windowSize);
 
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
                  <a href="#"><img src="../resources/images/Close.png" width="19" height="19" alt=""></a></div>
                 
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
                <td><a href="/jobboard/jobsearchactivity /viewJobDetails.html" rel="0" class="newWindow" >${dtoList.getJobtitle()}</a></td>
                <td align="left">${dtoList.getFacilityName()}</td>
                <td align="left">${dtoList.getAppliedDt()}</td>
                <td align="center"><a href='<c:url value="/jobSeekerActivity/deleteAppliedJob.html"><c:param name="appliedJobId" value="${dtoList.getSaveJobId()}"/> </c:url>'><img src="../resources/images/Delete.png" width="20" height="20" alt=""></a></td>
              </tr>
              </c:forEach>
              
            </table>
          </div>
 <div class="row marginTop20 paddingBottom10"><a href="" class="btn_sm orange">Cancel</a></div>
          </form:form></div>
          <div class="clearfix"></div>
                </div>

</body>
</html>