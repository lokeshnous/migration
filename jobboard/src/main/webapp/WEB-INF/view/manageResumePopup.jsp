<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
            <h2>MANAGE MY RESUMES</h2>
            <a href="#"><img src="../resources/images/Close.png" width="19" height="19" alt=""></a></div>
          <div class="popUpContainerWrapper">
            <form action="" method="">
              <div class="rowEvenNewSpacing marginTop0">
                <table width="100%" border="0" cellspacing="0" cellpadding="0" class="grid">
                  <thead>
                  <tr  class="borderTopNone">
                    <th width="36%" align="left" scope="col" >Resume Name</th>
                    <th width="25%" align="center" scope="col" >Visibility*</th>
                    <th width="17%" align="center" scope="col">Modified</th>
                    <th width="22%" align="center" scope="col">Actions</th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${resumeList}" var="resume" varStatus="status">
                  <tr id="${resume.uploadResumeId}">
                    <td>${resume.resume_name}</td>
                    <td align="center"><select id="select" class="jb_input3 select100 marginTopBottom0" name="select" disabled="disabled">
                       	<option selected="" value="Public">${resume.resume_visibility}</option>
                        <!-- <option value="Confidential">Confidential</option>
                        <option value="Private">Private</option> -->
                      </select></td>
                    <td align="center">${resume.updateDt}</td>
                    <td align="center"><a href="#"><img src="../resources/images/View.png" width="20" height="20" alt=""></a>&nbsp;<a href="#"><img src="../resources/images/Edit.png" width="20" height="20" alt=""></a>&nbsp;<a href="#"><img src="../resources/images/Download.png" width="20" height="20" alt=""></a>&nbsp;<a href="#"><img src="../resources/images/Print2.png" width="20" height="20" alt=""></a>&nbsp;<a href="#"><img src="../resources/images/Delete.png" width="20" height="20" alt=""></a></td>
                  </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>
              <div class="row marginTop5 paddingBottom10"> <span class="floatLeft marginTop10"><a href="" class="btn_sm orange">New Resume</a> <a href="" class="btn_sm orange">Cancel</a></span> <span class="floatLeft marginTop10 marginLeft5" ><em>*Only 1 resume may be made Public at a time</em></span> </div>
            </form>
          </div>
          <div class="clearfix"></div>
        </div>
</body>
</html>