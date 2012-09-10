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
 			
			$.ajax({url:"${pageContext.request.contextPath}/jobsearch/sendtofriendpost.html",
				data:$('#formid').serialize(),
				type:"POST",
				success: function(data) {
					if(data == ''){
						alert("Data send successfully !");						
						parent.$.nmTop().close();
					}else{
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

</head>
<body class="job_board">
	<div class="job_seeker_login popUpContainer" id="jobSeekerRegister1" style="display: block;">
		<div class="popupHeader">
			<h2>SEND TO A FRIEND</h2>
			<a href="#"><img width="19" height="19" src="<%= request.getContextPath() %>/resources/images/Close.png" class="nyroModalClose" alt="Close"/></a>
		</div>
      <div id="errmsg" class="FormErrorDisplay"></div>
		<div class="popUpContainerWrapper">
			<form:form method="post" action="../jobsearch/sendtofriendpost.html" commandName="sendtofriendmail" id="formid" >
				<form:input type="hidden" name="jobId" id="jobId" path="jobId"/>
			    <form:input type="hidden" name="joburl" id="joburl" path="joburl"/>
   
				<div class="rowEvenNewSpacing">
					<span class="lableText3">
						Your Friend's Email Address:
					</span>
					<form:input path="email" name="EmailAddress" id="EmailAddress" class="job_seeker_email" type="text"/>
					<span class="required">
					(Required)
					</span>
					<div class="toolTip"><span class="classic"><p>Example: John@yahoo.com;Dave@yahoo.com.</p></span></div>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">
						Message:
					</span>
					<form:textarea path="message" name="message" class="textareaBoxMessege" id="message" rows="5" cols="45" resize="none" />				
				</div>
				<div class="rowEvenNewSpacing marginTop20 paddingBottom10">
						<span class="floatLeft marginTop10">
							<input type="button" value="Send" name="send" id="send" class="btn_sm orange" />
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