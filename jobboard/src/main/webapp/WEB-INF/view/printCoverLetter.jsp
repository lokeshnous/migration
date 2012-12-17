<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Advance Health care job</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<jsp:include page="common/include.jsp" />
 <script type="text/javascript">
function prepareForm()
{
	window.print(); 
	function revertback(){
	document.location.href =("../jobSeeker/jobSeekerDashBoard.html");
	//$.nmManual("../jobSeekerCoverLetter/manageExistProfile.html?resumeType=manageCover");
	}
	window.onafterprint=revertback;
}
</script> 
</head>
	<body class="job_board" onLoad="javascript:prepareForm();">
		
		<form:form action="" method="GET" commandName="resCoverLetterForm" id="resCovLetForm" name="resCovLetForm" enctype="multipart/form-data">
		<div class="main_wrapper_outside">
			<div class="main_wrapper_inside">
				<div class="main">
					<div >
						<h2>
							COVER LETTER 
						</h2>
						<%-- <a href="#"><img width="19" height="19" src="<%= request.getContextPath() %>/resources/images/Close.png" class="nyroModalClose" alt="Close" title="Close"/></a> --%>
					</div>
					
					<div class="row ">
					
					   <!--This is down load option Begin  -->
					  
						<div class="row marginTop15">
							<div class="lableTextCoverletter">
								<b>Cover Letter Name:</b>
							</div>
							<div class="input_grp5 marginTop5">
								<div class="floatLeft">	
								<b>${resCoverLetterForm.name}</b>							
								<%-- <form:input path="name" name="name" id="name" class="jb_input2Coverletter" type="text" readonly="true"/> --%>						
								</div>
								
							</div>
						</div>
						<div id="errmsg" class="FormErrorDisplayText"></div>
						<div class="row marginTop15">
							<div class="lableTextCoverletter">
								<b>Body Text:</b>
							</div>
							
							<div class="input_grp5 marginTop5">
							<b>${resCoverLetterForm.coverletterText}</b>
								<%-- <form:textarea path="coverletterText" name="coverletterText"  class="textareaBoxCResume" resize="none"  rows="5" cols="45"
								id="coverletterText" readonly="true"/> --%>
							</div>
							
						</div>						
						
					    <!--This is down load option End  -->
					   
					</div>
					
					
					
					
					
				</div>
			</div>
		</div>
		</form:form>		
	</body>	
		<%-- <a href="<%=request.getContextPath()%>/jobSeekerCoverLetter/manageExistProfile.html?resumeType=manageCover" id="manageCoverLett">${msg.jsManageExistingCoverLetters}</a> --%>
	
	
</html>