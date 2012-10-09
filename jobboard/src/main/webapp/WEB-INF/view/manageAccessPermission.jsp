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
		    $("#addNewJobOwnerPopUp").displaypopup("#addNewJobOwnerPopUp","790","360");
		    $("#manageAccessPerm").displaypopup("#manageAccessPerm","790","360");
		   
		    $("#managePermission a").click(function() {
		    	
				val=$(this).attr("id");
				if (val != ""
					&& confirm("Are your sure you want to delete?")){
					
					$.ajax({url : "${pageContext.request.contextPath}/employer/deleteJobOwner.html?userId="+ val,
		    			data:$('#manageAcceccPermissionForm').serialize(),
						type: "POST",
						success : function(dataFound) {		
							 $("#manageAccessPerm").click();
						},
						error: function(response) {
							alert("Server Error : "+response.status);
						}					
						
					});	
					
				}
		});
			
			$("#saveClicked").click(function() {
				
				
				$.ajax({url : "${pageContext.request.contextPath}/employer/updateJobOwner.html",
	    			data:$('#manageAcceccPermissionForm').serialize(),
					type: "POST",
					success : function(dataFound) {	
						var listSize=$('#totalSize').val();
						if(listSize>0){
						alert("Permissions changed successfully.");
						 $("#manageAccessPerm").click();
						};
					  },
					error: function(response) {
						alert("Server Error : "+response.status);
					},
						
					
				});
				
	    		
			});	
		    jQuery(".megamenu").megamenu();
		});
		</script>
		</head>

		<body class="job_board">
		
		 <form:form action="updateJobOwner.html" commandName="manageAccessPermissionForm" id="manageAcceccPermissionForm" >
		 
        <div id="jobSeekerRegister1" class="job_seeker_login popUpContainer" style="display:block">
        <form:hidden path="totalSize" id="totalSize"/>
          <div class="popupHeader">
            <h2>MANAGE ACCESS PERMISSIONS</h2>
           <img title="Close" src="../resources/images/Close.png" width="19" height="19" class="nyroModalClose" alt="close"></div>
           <input type="hidden" name="pageValue" value="managePermPage"/>
          <div class="popUpContainerWrapper">
           <div class="row marginTop5 paddingBottom10"> <span class="floatLeft marginTop10"><a href="<%=request.getContextPath()%>/employer/addNewJobOwner.html?page=managePermPage"  id="addNewJobOwnerPopUp" class="btn_sm white">Add New Job Owner</a> </span>  </div>
            
              <div class="rowEvenNewSpacing marginTop10 marginTop0">
					<table id="managePermission" width="100%" border="0" cellspacing="0" cellpadding="0"
						class="grid">
						<tr>
							<th width="2%" align="left" scope="col">&nbsp;</th>
							<th width="33%" align="left" scope="col">Job Owner</th>
							<th colspan="2" align="Left" scope="col">Permission
								Settings</th>
							<th width="18%" align="left" scope="col">Action</th>
						</tr>
						<c:forEach items="${manageAccessPermissionForm.manageAccessPermissiondetails}" var="job"
							varStatus="status">
							<form:hidden path="manageAccessPermissiondetails[${status.index}].ownerId"/>
							<tr>
							 <td>&nbsp;</td>
								<td>${job.ownerName} </td>
								<td width="16%" align="left">
								<div class="required"><form:radiobutton id="radio1" path="manageAccessPermissiondetails[${status.index}].typeOfAccess"  value="5" /><label class="greyLabel">Full Access</label></div> 
							</td>
								<td width="31%" align="left">
								<div class="required"><form:radiobutton id="radio1" path="manageAccessPermissiondetails[${status.index}].typeOfAccess" value="6" /><label class="greyLabel">Post / Edit Only</label></div> 
								</td>
								<td align="left"><a id="${job.ownerId}" href="#"> Delete User</a></td> 
							</tr>
						</c:forEach>

					</table>
				</div>
              <div class="row marginTop20 paddingBottom10"> <span class="floatLeft marginTop10">
              <c:if test="${manageAccessPermissionForm.manageAccessPermissiondetails != null && .size() == 0}">
              <a href="#" class="btn_sm orange" id="saveClicked">Save</a> </c:if>
              <a class="nyroModalClose btn_sm orange">Cancel</a></span> 
              <a hidden="hidden" href="<%=request.getContextPath()%>/employer/manageAccessPermission.html" id="manageAccessPerm"></a>
              <span class="floatLeft marginTop10 marginLeft5" ></span> </div>
           
          </div>
          <div class="clearfix"></div>
        </div>
        </form:form>
</body>
</html>