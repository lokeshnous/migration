<%@ page isELIgnored="false"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">
<head>
	<jsp:include page="common/include.jsp" />
<script type="text/javascript">
	jQuery(document).ready(function() {

	    <c:if test="${visible}">
        parent.window.location.reload();
        parent.$.nmTop().close();
        </c:if>
		
		$("#send").click(function() {
               $("#formid").submit();
       });
		
		$("#cancel").click(function() {
 	           parent.window.location.reload();
	           parent.$.nmTop().close();
    });

		jQuery(".megamenu").megamenu();

		
	});
	function MM_jumpMenu(targ, selObj, restore) { //v3.0
		eval(targ + ".location='" + selObj.options[selObj.selectedIndex].value
				+ "'");
		if (restore)
			selObj.selectedIndex = 0;
	}
</script>

<script type="text/javascript">
  $('#btn-submit').click(function() { 
 
    $(".error").hide();
    var hasError = false;
    var emailReg=/^[a-zA-Z0-9_\+-]+(\.[a-zA-Z0-9_\+-]+)*@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.([a-zA-Z]{2,4})$/;
    //var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    var emailblockReg =
     /^([\w-\.]+@(?!gmail.com)(?!yahoo.com)(?!hotmail.com)([\w-]+\.)+[\w-]{2,4})?$/;
   
    var emailaddressVal = $("#email").val();
   
    if(emailaddressVal == '') {
      $("#email").after('<div class="rowEvenNewSpacing"><span class="lableText3"></span><span class="error" STYLE="color: red; font-size: 10pt">Please enter the correct E-Mail address</span></div>');
      hasError = true;
    }
 
    else if(!emailReg.test(emailaddressVal)) {
      $("#email").after('<div class="rowEvenNewSpacing"><span class="lableText3"></span><span class="error" STYLE="color: red; font-size: 10pt">Please enter the correct E-Mail address</span></div>');
      hasError = true;
    }
 
  
    if(hasError == true) { return false; }
 
    });	
  </script>
	<script type="text/javascript">
		$('#Cancel').click(function(){		
			parent.$.nmTop().close();		
		});
		
		</script>
</head>
<body class="job_board">
	<div class="job_seeker_login popUpContainer" id="jobSeekerRegister1" style="display: block;">
		<div class="popupHeader">
			<h2>SEND TO A FRIEND</h2>
			<a href="#"><img width="19" height="19" src="<%= request.getContextPath() %>/resources/images/Close.png" class="nyroModalClose" alt="Close"/></a>
		</div>
		<div class="popUpContainerWrapper">
			<form:form method="post" action="../jobsearch/sendtofriendpost.html" commandName="sendtofriendmail" id="formid" >
				<form:input type="hidden" name="jobId" id="jobId" path="jobId"/>
			    <form:input type="hidden" name="joburl" id="joburl" path="joburl"/>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">
						Your Friend's Email Address:
					</span>
					<form:input path="email" name="EmailAddress" class="job_seeker_email" type="text"/>
					<span class="required">
					(Required)
					</span>
				</div>
					<div class="rowEvenNewSpacing">
						<span class="lableText3">
							<c:if test="${fn:length(notempty) gt 0}">*${notempty}</c:if>
	 						<c:if test="${fn:length(invalidemail) gt 0}">*${invalidemail}</c:if>
 						</span>
					</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">
						Message:
					</span>
					<form:textarea path="message" name="message" class="textareaBoxMessege" id="message" rows="5" cols="45" resize="none" />				
				</div>
				<div class="rowEvenNewSpacing marginTop20 paddingBottom10">
						<span class="floatLeft marginTop10">
							<input type="submit" value="Send" name="Send" id="btn-submit" class="btn_sm orange" />
							<input type="button" name="Cancel" id="Cancel" class="orange" value="Cancel"/>
								
						</span>
					</div>
				<div class="clearfix">
				</div>
			</form:form>
		</div>
		<div class="clearfix">
		</div>
	</div>
</body>

</html>