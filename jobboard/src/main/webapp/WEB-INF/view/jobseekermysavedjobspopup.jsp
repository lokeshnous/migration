<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<jsp:include page="common/include.jsp" />
		<title>ADVANCE Heathcare Jobs</title>

		<!-- JAVASCRIPT FILES -->
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
		<!-- <script type="text/javascript" src="../resources/js/jquery.cycle.all.min.js"></script> -->
		<script type="text/javascript" src="../resources/js/slider.js"></script>
		<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>
		<script type="text/javascript">
		$(document).keyup(function(event) {
			if (event.keyCode == 27) {
				parent.window.location.reload();
			}
		});
            $(document).ready(function(){
            	$(".deleteSavedJob").displaypopup(".deleteSavedJob","770","360");
        		$("#id").click(function() {
     	           parent.window.location.href = "navigateToLogin.html";
     	           parent.$.nmTop().close();

     	      });
                $('.newWindow').click(function (event){
                     var url = $(this).attr("href");
                     parent.window.location.href = url;
     	            parent.$.nmTop().close();
                });
            });
        </script>
		<script type="text/javascript">
		    jQuery(document).ready(function(){
		    jQuery(".megamenu").megamenu();
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
	    function confirmDelete(id) {
	    	var r=confirm("Are you sure you want to delete?");
			if(r==true){
				$.nmManual("${pageContext.request.contextPath}/jobSeekerJobDetail/deleteSavedJob.html?appliedJobId="+id);
				}else{
					return false;
				}
	    }
     </script>
</head>
<div id="dialog-confirm" title="Confirm" style="display:none;">
    <p>Are you sure you want to do this?</p>
</div>
<body class="job_board">

<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer" style="display:block"> 
                  <div class="popupHeader marginBottom5"><h2>MY SAVED JOBS</h2>
                 <img src="../resources/images/Close.png"  title="Close" class="cursor" width="19" height="19" onclick="closePopup();"></div>
                 
<div class="popUpContainerWrapper"><form:form method="Post">
            <div class="rowEvenNewSpacing">
            
            <div class="row marginBottom5">To view or apply to a saved job, please click on the job title. You can save up to 30 job postings.</div>
            
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="grid">
              <tr>
                <th width="33%" align="left" scope="col">Job Title</th>
                <th width="33%" align="left" scope="col">Company Name</th>
                <th width="12%" align="center" scope="col">Saved</th>
                <th width="11%" align="center" scope="col">Job Age</th>
                <th width="11%" align="center" scope="col">Delete</th>
              </tr>
             <c:forEach items="${savedJobDTOList}" var = "dtoList" >  
               <tr>
                <td><a href='<c:url value="/search/viewMyJobDetails.html"><c:param name="id" value="${dtoList.getJpJob().getJobId()}"/></c:url>' rel="0" class="newWindow" >${dtoList.getJobTitle()}</a></td>
                <td align="left">${dtoList.getFacilityName()}</td>
                <td align="center">${dtoList.getCreateDt()}</td>
                <td align="center">${dtoList.getJobAge()} days</td>
                <td align="center"><div class="centerAlignMButton">
                <a title="Delete" onclick="confirmDelete(this.id); " id="${dtoList.getSaveJobId()}" class="deleteSavedJob"  >
                <div class="delete cursor"></div>
                </a></div></td>
              </tr>
              </c:forEach>
            </table>
          </div>
 <div class="popUpButtonRow"><a href="" onclick="closePopup();"  class="btn_sm orange">Cancel</a></div>
          </form:form></div>
          <div class="clearfix"></div>
                </div>

</body>
</html>