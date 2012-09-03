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
		    $("#addNewJobOwnerPopUp").displaypopup("#addNewJobOwnerPopUp","770","360");
		    jQuery(".megamenu").megamenu();
		});
		</script>
		</head>

		<body class="job_board">
        <div id="jobSeekerRegister1" class="job_seeker_login popUpContainer" style="display:block">
          <div class="popupHeader">
            <h2>MANAGE ACCESS PERMISSIONS</h2>
           <img src="../resources/images/Close.png" width="19" height="19" class="nyroModalClose" alt="close"></div>
          <div class="popUpContainerWrapper">
           <div class="row marginTop5 paddingBottom10"> <span class="floatLeft marginTop10"><a href="<%=request.getContextPath()%>/employer/addNewJobOwner.html"  id="addNewJobOwnerPopUp" class="btn_sm white">Add New Job Owner</a> </span>  </div>
          
            <form action="" method="">
            
              <div class="rowEvenNewSpacing marginTop10 marginTop0">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="grid">
						<tr>
							<th width="2%" align="left" scope="col">&nbsp;</th>
							<th width="33%" align="left" scope="col">Job Owner</th>
							<th colspan="2" align="Left" scope="col">Permission
								Settings</th>
							<th width="18%" align="left" scope="col">&nbsp;</th>
						</tr>
						<c:forEach items="${jobOwners}" var="job"
							varStatus="status">
							<tr>
								<td>&nbsp;</td>
								<td>${job.optionName}</td>
								<td width="16%" align="left"><input name="radio"
									type="radio" id="radio1" value="radio" checked> <label
									for="radio">Full Access</label></td>
								<td width="31%" align="left"><input name="radio"
									type="radio" id="radio1" value="radio"> <label
									for="radio">Post / Edit Only</label></td>
								<td align="left"><a href="#"> Delete User</a></td>
							</tr>
						</c:forEach>

					</table>
				</div>
              <div class="row marginTop20 paddingBottom10"> <span class="floatLeft marginTop10"><a href="" class="btn_sm orange">Save</a> <a href="<%=request.getContextPath()%>/employer/employerDashBoard.html" class="btn_sm orange">Cancel</a></span> <span class="floatLeft marginTop10 marginLeft5" ></span> </div>
            </form>
          </div>
          <div class="clearfix"></div>
        </div>
</body>
</html>