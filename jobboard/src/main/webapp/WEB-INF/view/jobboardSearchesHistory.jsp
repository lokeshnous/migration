<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script language="javascript" type="text/javascript" >
	$(document).ready(function() {
		$(".seeallpopup").displaypopup(".seeallpopup", "790", "370");
	});
</script>

<c:choose>
	<c:when test="${empty latestRecentList}">
		Clear All | 
		See All
	</c:when>
	<c:otherwise>
		<a class="cursor" id="clearLatestSearches" onclick="clearRecentSearches();">Clear All</a> | 
		<a  class="nyroModal seeallpopup" href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/savedSearches/viewrecentsearches.html" id="seeallpopup">See All</a>
	</c:otherwise>
</c:choose>

<div class="search_info_box1" id="latestRecentListId">
							<div class="rowPadding borderBottomDotted" id="aaa">
								
								<div id="clear">
									
								
							<div class="" id="cleardata">
							
							<c:forEach items="${latestRecentList}" var="item" >
							 <div class="rowPadding borderBottomDotted">
							    ${item.createdDate.toLocaleString()}<br> Search by: <a href="#"	id="${item.saveSearchID}"					
									onclick="loadRecentSearch(${item.saveSearchID})" class="newWindow">${item.recentURL}</a>
							   
							   </div>
									
								</c:forEach>
								</div>
								</div>
						</div>
						</div>
