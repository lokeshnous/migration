<%@ page isELIgnored="false"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">
<head>
	<jsp:include page="common/include.jsp" />
<script type="text/javascript">
	jQuery(document).ready(function() {
		
 		$('#send').click(function(){			
 			$("#mailSending").html("<span>Processing...</span>");
			$.ajax({url:"${pageContext.request.contextPath}/employer/sendtofriendpost.html",
				data:$('#formid').serialize(),
				type:"POST",
				success: function(data) {
					if(data == ''){
						//alert("Successfully sent!");
						$("#mailSending").html("<span></span>");
						parent.$.nmTop().close();
					}else{
						$("#mailSending").html("<span></span>");
						$("#errmsg").html(data);
					}
				 },
			});
		});
		
 		$('#send').click(function(){			
 			$("#mailSending").html("<span>Processing...</span>");
			$.ajax({url:"${pageContext.request.contextPath}/search/sendtofriendpost.html",
				data:$('#formid').serialize(),
				type:"POST",
				success: function(data) {
					if(data == ''){
						//alert("Successfully sent!");
						$("#mailSending").html("<span></span>");
						parent.$.nmTop().close();
					}else{
						$("#mailSending").html("<span></span>");
						$("#errmsg").html(data);
					}
				 },
			});
		});
	     <c:if test="${visible}">
        parent.window.location.reload();
        parent.$.nmTop().close();
        </c:if> 

		
		$("#Cancel").click(function() {
 	          // parent.window.location.reload();
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

</head>
<body class="job_board">
	<div class="job_seeker_login popUpContainer" id="jobSeekerRegister1" style="display: block;">
		<div class="popupHeader">
			<h2>FORWARD RESUME</h2>
			<a href="#"><img width="19" title="Close" height="19" src="<%= request.getContextPath() %>/resources/images/Close.png" class="nyroModalClose" alt="Close"/></a>
		</div>
      
		<div class="popUpContainerWrapper">
			<form:form method="post" action="../search/sendtofriendpost.html" commandName="sendtofriendmail" id="formid" >
			<form:hidden path="resumeId" />
			    <form:hidden  path="joburl"/>
   				<div id="mailSending" class="validationMsg"></div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">
						Email Address:
					</span>
					<form:input path="email" name="EmailAddress" id="EmailAddress" class="job_seeker_email" type="text"/>
					<span class="required">
					(Required)
					</span>
					<div class="toolTip colorPkrAreaToolTip"><span class="classic">Example: John@yahoo.com;Dave@yahoo.com.</span></div>
				</div>
				<div id="errmsg" class="validationMsg"></div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">
						Message:
					</span>
					<form:textarea path="message" name="message" class="textareaBoxMessege" id="message" rows="5" cols="45" resize="none" />				
				</div>
				<div class="rowEvenNewSpacing marginTop20 paddingBottom10">
						<span class="floatLeft marginTop10">
							<input type="button" value="Send" name="send" id="send" class="btn_sm orange cursor" />
							<input type="button" name="Cancel" id="Cancel" class="orange cursor" value="Cancel"/>
								
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