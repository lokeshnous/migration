<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />
<script type="text/javascript">
function closePopup() {
	//parent.window.location.reload();
	parent.$.nmTop().close();
}
	jQuery(document).ready(function() {
		jQuery(".megamenu").megamenu();
		$("#saveData").click(function(event){	
			var searchName = $.trim($("#searchTitleName").val());
			$.ajax({url: getBaseURL()+"/savedSearches/saveSearchedJobs.html?searchName="+searchName,
				success: function(data){ 
						//parent.window.location.href = "../loginFormForJobSeeker/login.html";
					parent.$.nmTop().close();
				},
				error: function(response) {
					alert("Server Error : "+response.status);
				},
				complete: function() {
					
				}
			});
		});
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
			<h2>SAVE SEARCHES</h2>
			<a href="#"><img src="../resources/images/Close.png" width="19" height="19"
				alt=""></a>
		</div>

		<div class="popUpContainerWrapper">
			<form:form action="/jobboard/savedSearches/saveSearchedJobs.html" method="" commandName="saveSearchForm">
				<span class="lableText3 width505 TextAlignL">Search Title</a></span>
				<div class="rowEvenNewSpacing margin0">
					<input type="text" name="searchTitleName" id="searchTitleName" class="jb_input1" /><br />
				</div>

				<div class="popUpButtonRow">
				<input type="button" id="saveData" class="btn_sm orange" value="Save"/>
				<input type="button" onclick="closePopup();" class="btn_sm orange" value="Cancel"/>					
				</div>
				<div class="clearfix"></div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>