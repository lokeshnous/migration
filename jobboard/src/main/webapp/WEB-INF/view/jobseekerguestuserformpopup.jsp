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
		
		jQuery(document).ready(function() {
		$("#sendButton").click(function(event){    
			if(validate()){
			var file=$("#filePath").val();		
			$.ajax({url: "${pageContext.request.contextPath}/anonymoususerjobapply/saveAnonymousUserJobapply.html?filePath="+file,
				data:$('#applyJobForm').serialize(),
				type:"POST",
				success: function(data) {
					if(data ==''){
						 parent.window.location.href ="${pageContext.request.contextPath}/healthcarejobs/advanceweb.html?message=true";
						 parent.$.nmTop().close();
					}else{
						$("#errmsg").html("Please try again");
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
				$("#userNameError").text("Please enter name");
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
				$("#filePathError").text("Please chose the appropriate file format");
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
		</head>

<body class="job_board">
<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer" style="display:block">
                  <div class="popupHeader"><h2>GUEST USER FORM</h2>
                  <a href="#"><img src="../resources/images/Close.png" onclick="parent.$.nmTop().close();" width="19" height="19" alt=""></a></div>
                 <div id="errmsg" style="color: red" align="middle"></div>
<div class="popUpContainerWrapper"><form:form id="applyJobForm" action=""  method="POST" commandName="jobApplicationForm" enctype="multipart/form-data" >
            <div class="rowEvenNewSpacing"> <h3>Send Resume</h3></div>
            <div class="rowEvenNewSpacing">
<span class="lableText3">Name:</span>   
                <form:input id="userName" path="userName" class="job_seeker_email" />
                <span class="required">(Required)</span>
                <div style="color: red" align="center"><form:errors path="userName"/></div>
               <div id="userNameError" style="color: red" align="center"></div>
            </div>
            <div class="rowEvenNewSpacing"><span class="lableText3">Email Address:</span>
              <form:input id="userEmail" path="userEmail"  class="job_seeker_email" />
              <span class="required">(Required)</span>
              <div style="color: red" align="center"><form:errors path="userEmail"/></div>
              <div style="color: red" align="center" id="userEmailError"></div>
    </div>
            <div class="rowEvenNewSpacing"> <span class="lableText3">Upload Resume File:</span>
            <div class="floatLeft"><form:input path="filePath" type="file" id="filePath" size="20" class="job_seeker_login_email fileType" />
            </div>
            <span class="required">(Required)</span>
             <div style="color: red" align="center"><form:errors path="filePath"/></div>
             <div style="color: red" align="center" id="filePathError"></div>
</div>
      <div class="popUpButtonRow">
                         
             <input type="button" class="btn_sm orange" id="sendButton" value="Send"/> <a href="" onclick="parent.$.nmTop().close();" class="btn_sm orange">Cancel</a></span>
             
</div>
                    <div class="clearfix"></div>
          </form:form></div>
          <div class="clearfix"></div>
                </div>

</body>
</html>