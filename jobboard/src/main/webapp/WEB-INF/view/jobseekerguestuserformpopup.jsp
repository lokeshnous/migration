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
		/* function closePopup() {
			parent.window.location.reload();
		} */
		jQuery(document).ready(function() {
		$("#sendButton").click(function(event){    
			if(validate()){
			var file=$("#filePath").val();		
			$.ajax({url: "${pageContext.request.contextPath}/anonymoususerjobapply/saveAnonymousUserJobapply.html?filePath="+file,
				data:$('#applyJobForm').serialize(),
				type:"POST",
				success: function(data) {
					if(data ==''){
						 parent.window.location.href ="${pageContext.request.contextPath}/jobsearch/findJobPage.html";
						 parent.$.nmTop().close();
					}else{
						$("#errmsg").html("Some problem happned,please try again");
					}
				 },
			});
			}
            });
		});
		function validate(){
			var userName=$("#userName").val();
			var userEmail=$("#userEmail").val();
			var file=$("#filePath").val();
			var x=userEmail.indexOf('@');
			var y=userEmail.lastIndexOf('.');
			var result=true;
			if(userName.length == 0){
				$("#userNameError").text("Please enter the name");
				result=false;
			}
			else{
				$("#userNameError").text("");
			}
			if(x==-1 || y==-1 || (x+2)>=y){
				$("#userEmailError").text("Please enter the correct e-mail address");
				result=false;
			}
			else{
				$("#userEmailError").text("");
			}
			if(!file.toLowerCase().match(/(\.doc|\.pdf|\.docx)$/)){
				$("#filePathError").text("Please choose the appropriate file format");
				result=false;
			}
			else{
				$("#filePathError").text("");
			}
			if(!result){
			return false;
			}
			else{
				return true;
			}
		}
		
        </script>
        <script type="text/javascript">
		    function cancelProcess(){
		    	parent.window.location.reload();
		    }		
		</script>
		</head>

<body class="job_board">
<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer" style="display:block">
                  <div class="popupHeader"><h2>GUEST USER FORM</h2>
                  <a href=""><img src="../resources/images/Close.png" onclick="cancelProcess();" width="19" height="19" alt=""></a></div>
                 <div id="errmsg" style="font:bold; color: red" align="left"></div>
<div class="popUpContainerWrapper"><form:form id="applyJobForm" action=""  method="POST" commandName="jobApplicationForm" enctype="multipart/form-data" >
            <div class="rowEvenNewSpacing"> <h3>Send Resume</h3></div>
            <div class="rowEvenNewSpacing">
<span class="lableText3">Name:</span>   
                <form:input id="userName" path="userName" class="job_seeker_email" />
                <span class="required">(Required)</span>
               <div id="userNameError" style="color: red" align="left"></div>
            </div>
            <div class="rowEvenNewSpacing"><span class="lableText3">Email Address:</span>
              <form:input id="userEmail" path="userEmail"  class="job_seeker_email" />
              <span class="required">(Required)</span>
              <div style="color: red" align="left" id="userEmailError"></div>
    </div>
            <div class="rowEvenNewSpacing"> <span class="lableText3">Upload Resume File:</span>
            <div class="FileTypeWidth"><form:input path="filePath" type="file" id="filePath" size="17" class="job_seeker_login_email fileType" />
            </div>
            <span class="required">(Required)</span>
             <div style="color: red" align="left" id="filePathError"></div>
</div>
      <div class="popUpButtonRow">
                         
             <input type="button" class="orange" id="sendButton" value="Send"/> 
              <input type="button" value="Cancel" onclick="cancelProcess()"
									class="orange" name="Cancel" />
             <!-- <a href="" onclick="closePopup();" class="btn_sm orange">Cancel</a> -->
             
</div>
                    <div class="clearfix"></div>
          </form:form></div>
          <div class="clearfix"></div>
                </div>

</body>
</html>