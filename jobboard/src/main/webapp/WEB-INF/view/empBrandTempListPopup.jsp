<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<script type="text/javascript" src="javascripts/jquery.cycle.all.min.js"></script>
		<script type="text/javascript" src="javascripts/slider.js"></script>
		<script type="text/javascript" src="javascripts/jquery.megamenu.js"></script>
		<script type="text/javascript">
		    jQuery(document).ready(function(){
		    jQuery(".megamenu").megamenu();
		});
		</script>
		</head>

		<body class="job_board">
        <div id="jobSeekerRegister1" class="job_seeker_login popUpContainer" style="display:block">
          <div class="popupHeader">
            <h2>MANAGE JOB POSTING BRANDING TEMPLATES </h2>
            <a href="cancelEmpBrandTemp.html"><img src="../resources/images/Close.png" width="19" height="19" alt="Close"></a></div>
          <div class="popUpContainerWrapper">
            <form action="" method="get">
              <div class="rowEvenSpacingMargin0">
                <table width="100%" border="0" cellspacing="0" cellpadding="0" class="grid">
                <thead>
                 <tr  class="borderTopNone">
                    <th align="left" scope="col" >Name</th>
                    <th width="18%" align="center" scope="col"> Last Modified</th>
                    <th width="21%" align="center" scope="col">Actions</th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${templatesList}" var="templates" varStatus="status">
                  <tr id="${templates.description}">
                    <td>${templates.description}</td>
                    <td align="center">${templates.updatedDate}</td>
                    <td align="center">
                    <a href="#"><img src="../resources/images/View.png" width="20" height="20" alt=""></a>&nbsp;
                    <a href="#"><img src="../resources/images/Edit.png" width="20" height="20" alt=""></a>&nbsp;
                    <a href="deleteEmpBrandTemp.html"><img src="../resources/images/Delete.png" width="20" height="20" alt=""></a></td>
                  </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>
              <div class="row marginTop10 paddingBottom10"> 
              <a href="" class="btn_sm orange">New  Branding TEMPLATE</a> 
              <a href="cancelEmpBrandTemp.html" class="btn_sm orange">Cancel</a>
              </div>
            </form>
          </div>
          <div class="clearfix"></div>
        </div>
</body>
</html>