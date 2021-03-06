<%@ page isELIgnored="false"%>
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
	jQuery(document).ready(function() {
		$.nmFilters({
			custom: {
			afterShowCont: function(nm) {
				$('#email').focus();
				}
			}
		 });
					
		$("#waitmsg").hide();
		
		$('#emailbutton').click(function(){	
			$("#emailmsg").html("<span>Processing...</span>");
			var email = $("#email").val();			
			/* $("#emailbutton").hide();
			$("#cancelbutton").hide();
			$("#waitmsg").show();
			$('body').css('cursor','wait');  */ 

			if(email==""){
				$("#emailmsg").html("Please enter an Email address");
				}
			else{
			$.ajax({url:"jobSeekerForgotPWDPopUp.html?email="+email,
				type:"POST",
				success: function(data) {
					$("#processingMsg").html("<span></span>");
					var substr = data.split(',');
					if(substr[0] == '${MMJBCommonConstantsok}'){
						$('body').css('cursor','default');  
						alert('Temporary password has been sent to your email address successfully.');
				        parent.$.nmTop().close();
					}
					if(substr[0] == '${MMJBCommonConstantserror}'){
						$("#emailmsg").html(substr[1]);
						$("#emailbutton").show();
						$("#cancelbutton").show();
						$("#waitmsg").hide();
						$('body').css('cursor','default');  
					}
				 },
			});
			
		}
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
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">		
		<div id="processingMsg" class="FormErrorDisplayText"></div>
		<div class="popupHeader">
			<h2>FORGOT YOUR PASSWORD?</h2>
			<a href="#"><img title="Close" src="../resources/images/Close.png" width="19" height="19" onclick="parent.$.nmTop().close();"
				alt=""></a>
		</div>
        <input type="hidden" class="testField" value="${MMJBCommonConstantsok}" >
		<div class="popUpContainerWrapper">
			<form:form method="" action="" commandName="loginForm" onsubmit="return false">
				<div class="rowEvenSpacingMargin0 borderBottomDotted paddingBottom10">
					<p>Enter the email address you use for this account and click
						the 'SEND' button. We'll email your password.</p>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Email Address:</span>
					<form:input type="text" path="emailAddress" id="email" class="job_seeker_email" />
					<span class="required">(Required)</span>
				</div>
				<span class="lableText3"></span>
				<span class="FormErrorDisplayText"><div id="emailmsg"></div></span>  
				
				<div class="popUpButtonRow">					 
					     <input type="button" id="emailbutton" class="orange cursor" value="Send"/>
					     <input type="button" id="cancelbutton" class="orange cursor" value="Cancel" onclick="parent.$.nmTop().close();" />
				</div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>