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
		    	$("#addFacility").displaypopup("#addFacility","770","360");
		    	$("#updateFacility").displaypopup("#updateFacility","770","360");
		    jQuery(".megamenu").megamenu();
		});
		</script>
</head>
<body class="job_board">
<form:form  commandName="manageFacilityForm" id="maangeFacility">
<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer" style="display:block">
                  <div class="popupHeader"><h2> MANAGE FACILITY LIST</h2>
                  <a href="#"><img src="../resources/images/Close.png" width="19" height="19" alt=""></a></div>
                 
<div class="popUpContainerWrapper"><form action="" method="">
            <div class="rowEvenNewSpacing"><h3 class=" color2"> ${manageFacilityForm.facilityName}</h3>
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class=" grid2">
							<tr>
								<td><a href="#">${manageFacilityForm.facilityName}</a></td>
							</tr>

							<c:forEach items="${manageFacilityForm.facilityDTOList}"
								var="facility" varStatus="status">
								<form:hidden path="facilityDTOList[${status.index}].facilityId" />
								<tr>
									<td><a href="#">${facility.name}</a></td>
							</c:forEach>



						</table>
					</div>
 <div class="row marginTop20 paddingBottom10"><a href="<%=request.getContextPath()%>/facility/addFacility.html" id="addFacility" class="btn_sm orange">ADD FACILITY</a>
  <a href="<%=request.getContextPath()%>/facility/updateFacilityDetail.html" id="updateFacility" class="btn_sm orange">MANAGE FACILITIES</a></div>
          </form></div>
          <div class="clearfix"></div>
                </div>
</form:form>
</body>
</html>