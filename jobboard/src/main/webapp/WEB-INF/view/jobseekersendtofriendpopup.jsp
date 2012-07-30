<%@ page isELIgnored="false"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>

<!-- STYLESHEETS -->
<link href="../resources/css/JB.css" rel="stylesheet" type="text/css" />
<link href="../resources/css/jquery.megamenu.css" rel="stylesheet"
	type="text/css" />
<link href="../resources/css/SliderStyles.css" rel="stylesheet"
	type="text/css">

<!--[if IE]>
	<link href="stylesheets/ie.css" rel="stylesheet" type="text/css">
<![endif]-->

        	<!-- js files for modalpopup------------------------------------------------- -->
<script src="../resources/js/jquery-1.7.1.js"></script>
<script src="../resources/js/jquery-1.7.1.min.js"></script>
		<script src="../resources/jquery.nyroModal/js/popup.js"></script>
		<script src="../resources/jquery.nyroModal/js/jquery.nyroModal.custom.js"></script>
        <script src="../resources/jquery.nyroModal/js/jquery.nyroModal.custom.min.js"></script>
 	    <link href="../resources/jquery.nyroModal/styles/nyroModal.css" rel="stylesheet" type="text/css">

        <style type="text/css" media="screen">
           @import url("${pageContext.request.contextPath}/resources/jquery.nyroModal/styles/nyroModal.css");
        </style>
<!-- -------------------------------------------------------------------------- -->



<!-- JAVASCRIPT FILES -->
<script type="text/javascript"
	src="../resources/js/jquery.cycle.all.min.js"></script>
<script type="text/javascript" src="../resources/js/slider.js"></script>
<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>
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
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>SEND TO A FRIEND</h2>
			<a href="#"><img src="../resources/images/Close.png" width="19" onclick="parent.$.nmTop().close();"
				height="19" alt=""></a>
		</div>
           
		<div class="popUpContainerWrapper">
			<form:form method="post" action="sendtofriendpost.html" commandName="sendtofriendmail" id="formid" >
			    <input type="hidden" name="joburl" value="${joburl}" />
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Your Friend's Email Address:</span> 
                      <form:input path="email" class="job_seeker_email"/>
 					<span class="required">(Required)</span>
				</div>
				<div>
				  <span class="lableText3"></span> 
				  <span style="color: red;font-style:italic;" >
				 	<c:if test="${fn:length(notempty) gt 0}">*${notempty}</c:if>
 					<c:if test="${fn:length(invalidemail) gt 0}">*${invalidemail}</c:if>
 				  </span>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="lableText3">Message:</span>
				    <form:textarea path="message" id="Body Text:" cols="45" rows="5" class="textareaBoxMessege" />
				</div>
				<div class="rowEvenSpacing marginTop10 paddingBottom10">
					<span class="floatLeft marginTop10"> 
					<a href="#"	class="btn_sm orange" id="send" >Send</a> <a href="" class="btn_sm orange" id="cancel">Cancel</a>
					</span>
				</div>
				<div class="clearfix"></div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>