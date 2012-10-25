<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="common/include.jsp" />
<title>ADVANCE Heathcare Jobs</title>
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery(".megamenu").megamenu();
		
	$('#impersonate').click(function(){			
 			
			$.ajax({url:"${pageContext.request.contextPath}/admin/authenticate.html",
				data:$('#adminLoginFormId').serialize(),
				type:"POST",
				success: function(data) {
					if(data == 'true'){
						alert("Impersonation Completed successfully !");
						parent.$.nmTop().close();
						window.location.reload();
					}else if(data == 'false'){
						alert("Error occured while Impersonation!");
						parent.$.nmTop().close();
					}else{
						$("#errmsg").html(data);
						
					}
				 },
					error : function(data) {
						// alert("Unable to process");
					},
					complete : function(data) {
					}
			});
		});
	});
</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>Impersonation</h2>
			<img id="closeCheckOut" src="../resources/images/Close.png" title="Close"
				width="19" height="19" alt="" class="nyroModalClose">
		</div>
		<div class="popUpContainerWrapper">
			<div class="popUpContainerWrapper">
			<div class="lableText5"></div>
				<form:form method="POST" action="" commandName="adminLoginForm" id="adminLoginFormId">
					<div class="row">
					<div class="row">
						<div class="lableText5"></div>
						<div id="errmsg" class="FormErrorDisplayText"></div>
						</div>
						<div class="rowEvenNewSpacing">
							<div class="lableText5">Email Address of Employer / Agency:</div>
							<form:input path="empOrAgencyEmail" name="Exclude" class="job_seeker_email" />
						</div>
						<div class="row">
						<span class="validationMsgPadding">
						<form:errors path="empOrAgencyEmail" />
						</span>
						</div>
						
						<div class="rowEvenNewSpacing">
						<div class="lableText5">User Email Address :</div>
						<form:input name="Exclude" path="userEmail" class="job_seeker_email" />
						</div>
						
						<div class="rowEvenNewSpacing">
						<div class="lableText5">Password :</div>
						<form:password name="Exclude" path="password" class="job_seeker_email" />
						</div>
						
						<div class="rowEvenNewSpacing">
							<span class="lableText5"></span>
							<input type="button" value="Impersonate" id="impersonate" class="orange"/>
							<!-- <a href="" class="btn_sm orange">Cancel</a> -->
							<input type="button" id="cancelbutton" class="orange" value="Cancel" onclick="parent.$.nmTop().close();" />
						</div>
					</div>
				</form:form>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</body>
</html>