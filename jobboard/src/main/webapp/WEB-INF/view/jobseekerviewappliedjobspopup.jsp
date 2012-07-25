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
		    jQuery(document).ready(function(){
		    jQuery(".megamenu").megamenu();
		});
		</script>
		</head>

<body class="job_board">
<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer" style="display:block">
                  <div class="popupHeader">
                  <h2>JOBS I'VE APPLIED TO</h2>
                  <a href="#"><img src="images/Close.png" width="19" height="19" alt=""></a></div>
                 
<div class="popUpContainerWrapper"><form action="" method="">
            <div class="rowEvenNewSpacing marginTop0">
              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="grid">
              <tr class="borderTopNone">
                <th width="36%" align="left" scope="col">Job Title</th>
                <th width="32%" align="left" scope="col">Company Name</th>
                <th width="20%" align="left" scope="col">Applied</th>
                <th width="12%" align="center" scope="col">Delete</th>
              </tr>
              
  <tr>  
   <td>${requestScope.list1[status.count - 1]}</td>  
   <td>${requestScope.list2[status.count - 1]}</td>  
   <td>${requestScope.list3[status.count - 1]}</td>  
  </tr>  
 
               <c:forEach items="${list}" var = "var" >  
               <tr>
                <td><a href="javascript:void(0)">${var.get }</a></td>
                <td align="left">Mt. Sinai Medical Center</td>
                <td align="left">06/01/2012</td>
                <td align="center"><a href="#"><img src="images/Delete.png" width="20" height="20" alt=""></a></td>
              </tr>
              </c:forEach>
              <!--
              <tr>
                <td><a href="javascript:void(0)">Nurse Practitioner</a></td>
                <td align="left">Johns Hopkins</td>
                <td align="left">12/30/2011</td>
                <td align="center"><a href="#"><img src="images/Delete.png" width="20" height="20" alt=""></a></td>
              </tr>
              <tr>
                <td><a href="#">Physical Therapist</a></td>
                <td align="left">Sacred Heart</td>
                <td align="left">11/29/2011</td>
                <td align="center"><a href="#"><img src="images/Delete.png" width="20" height="20" alt=""></a></td>
              </tr>
              <tr>
                <td><a href="#">Per Diem Surgical Care Associate</a></td>
                <td align="left">Mt. Sinai Medical Center</td>
                <td align="left">12/25/2012</td>
                <td align="center"><a href="#"><img src="images/Delete.png" width="20" height="20" alt=""></a></td>
              </tr>
              <tr>
                <td><a href="#">Physician Assistant</a></td>
                <td align="left">St. Joseph's Hospital</td>
                <td align="left">01/01/2013</td>
                <td align="center"><a href="#"><img src="images/Delete.png" width="20" height="20" alt=""></a></td>
              </tr> -->
            </table>
          </div>
 <div class="row marginTop20 paddingBottom10"><a href="" class="btn_sm orange">Cancel</a></div>
          </form></div>
          <div class="clearfix"></div>
                </div>

</body>
</html>