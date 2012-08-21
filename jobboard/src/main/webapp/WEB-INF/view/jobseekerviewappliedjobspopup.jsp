<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>ADVANCE Heathcare Jobs</title>

		<jsp:include page="common/include.jsp" />
		
		<script type="text/javascript">
           $(document).ready(function(){
        	   /*$("#id").click(function() {
     	           parent.window.location.href = "navigateToLogin.html";
     	           parent.$.nmTop().close();

     	      }); */
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
  <script type="text/javascript">
	function closePopup() {
		parent.window.location.reload();
	}
	$(document).keyup(function(event) {
		if (event.keyCode == 27) {
			parent.window.location.reload();
		}
	});
   </script>
      <script type="text/javascript">
	    function confirmDelete() {
		if (confirm("Are you sure you want to Delete")) {
			return true;
		} else {
			return false;
		}
	    }
     </script>
</head>

<body class="job_board">
<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer" style="display:block">
                  <div class="popupHeader">
                  <h2>JOBS I'VE APPLIED TO</h2>
                  <img src="../resources/images/Close.png" width="19" height="19" onclick="closePopup();"></div>
                 
<div class="popUpContainerWrapper"><form:form method="Post">
            <div class="rowEvenSpacingMargin0">
            
              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="grid">
              <tr class="borderTopNone">
                <th width="36%" align="left" scope="col">Job Title</th>
                <th width="32%" align="left" scope="col">Company Name</th>
                <th width="20%" align="left" scope="col">Applied</th>
                <th width="12%" align="center" scope="col">Delete</th>
              </tr>
                 <c:forEach items="${appliedJobDTOList}" var = "dtoList" > 
               <tr>
                <td><a href='<c:url value="/jobsearchactivity/viewJobDetails.html"><c:param name="id" value="${dtoList.getJpJob().getJobId()}"/><c:param name="currentUrl" value="null"/></c:url>'  rel="0" class="newWindow" >${dtoList.getJobTitle()}</a></td>
                <td align="left">${dtoList.getFacilityName()}</td>
                <td align="left">${dtoList.getAppliedDt()}</td>
                <td align="center"><a href='<c:url value="/jobSeekerActivity/deleteAppliedJob.html" ><c:param name="appliedJobId" value="${dtoList.getSaveJobId()}"/> </c:url>' onclick="return confirmDelete();"><img src="../resources/images/Delete.png" width="20" height="20" alt="" ></a></td>
              </tr>
              </c:forEach>
            </table>
             
          </div>
 <div class="popUpButtonRow"><a href="#" onclick="closePopup();" class="nyroModalClose btn_sm orange">Cancel</a></div>
          </form:form></div>
          <div class="clearfix"></div>
                </div>

</body>
</html>