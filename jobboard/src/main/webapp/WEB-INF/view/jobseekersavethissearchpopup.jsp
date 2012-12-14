<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.advanceweb.afc.jb.common.util.MMJBCommonConstants"%>
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
function saveRecentSearch(searchJobId){
	var searchName = $.trim($("#searchTitleName").val());
	$.ajax({url: "${pageContext.request.contextPath}/savedSearches/saveRecentSearch.html?searchName="+searchName+"&searchJobId"+searchJobId,
		success: function(data){ 
			$.each(data, function(key, val) {				
				if(key == "EmptySearchName"){
					$("#ErrorMsg").text("${msg.EmptySearchName}");
				}
				if(key == "DuplicateSearchName"){
					$("#ErrorMsg").text("${msg.DuplicateSearchName}");
				}
				if (key == "NavigationPath") {
					$.nmManual(val + '.html');
					alert("Search saved successfully!.Access saved search criterias using \"My saved searches\" in dashboard.");
					//parent.$.nmTop().close();
				}
			}); 
		},
		
		error: function(response) {
			alert("Server Error : "+response.status);
		},
		complete: function() {
			
		}
	});
}
function cancelRecentSearch(){
	$.nmManual("${pageContext.request.contextPath}/savedSearches/viewrecentsearches.html");
}
function closePopup() {
	//parent.window.location.reload();
	
	parent.$.nmTop().close();
	
}
	jQuery(document).ready(function() {
		$.nmFilters({
			custom: {
			afterShowCont: function(nm) {
				$('#searchTitleName').focus();
				}
			}
		 });
		jQuery(".megamenu").megamenu();
		$("#saveData").click(function(event){	
			var searchName = $.trim($("#searchTitleName").val());
			
			$.ajax({url: "${pageContext.request.contextPath}/savedSearches/saveSearchedJobs.html?searchName="+searchName,
				success: function(data){ 
					$.each(data, function(key, val) {
						if (key == "NavigationPath") {
							$.nmManual(val + '.html');
							parent.$.nmTop().close();
						}
						
						if (key == "LoggedInNavigationPath") {
							parent.$.nmTop().close();
						}
						
						if(key == "EmptySearchName"){
							$("#ErrorMsg").text("${msg.EmptySearchName}");
						}
						if(key == "DuplicateSearchName"){
							$("#ErrorMsg").text("${msg.DuplicateSearchName}");
						}
						
						if(key == "success"){
							alert("Search saved successfully!");
							if(data.retainSaveSearch != 'undefined'){
								window.location.reload();
						    }
						}
					}); 
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
			<a href="#" class="nyroModalClose"><img src="<%= request.getContextPath() %>/resources/images/Close.png" width="19" height="19" title="Close"
				alt=""></a>
		</div>

		<div class="popUpContainerWrapper">
			<form:form action="" method="GET" commandName="saveSearchForm">
				<span class="lableText3 width505 TextAlignL">Search Title</span>
				<div class="rowEvenSpacingMargin0">
					<input type="text" name="searchTitleName" id="searchTitleName" class="jb_input1" /><br />
				</div>
				<span id="ErrorMsg" class="FormErrorDisplayText"></span>
				<div class="popUpButtonRow">
				<c:choose>
				<c:when test="${not empty recentSearchId}">
  				<input type="button" id="saveRecentId" onclick="saveRecentSearch(${recentSearchId})" class="orange cursor" value="Save"/>
  				<input type="button" id="cancelRecentId" onclick="cancelRecentSearch()" class="orange cursor" value="CANCEL"/>
  				<%-- <a class="orange btn_sm cursor nyroModal" href="<%=request.getContextPath()%>/savedSearches/viewrecentsearches.html" id="cancelRecentId">CANCEL</a> --%>
				</c:when>					
				<c:otherwise>
				<input type="button" id="saveData" class="orange cursor" value="Save"/>
				<input type="button" class="nyroModalClose orange cursor" value="Cancel"/>
				</c:otherwise>
				</c:choose>
				</div>
				<div class="clearfix"></div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>