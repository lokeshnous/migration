<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

 <script>
	
	 function performCurrentSearch(searchJobId){
			$.ajax({url: "${pageContext.request.contextPath}/savedSearches/editSavedSearch.html?searchId="+searchJobId+"&performSearch=performSearch",
				success: function(data){ 
					$.each(data, function(key, val) {
						 if (key == "searchtype" && val == "basic") {
							parent.window.location.href = '${pageContext.request.contextPath}/jobsearch/findJobPage.html';
							parent.$.nmTop().close();
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
	</script>
<body>
<div class="search_info_box1" id="latestRecentListId">
							<div class="rowPadding borderBottomDotted" id="aaa">
								
								<div id="clear">
									
								
							<div class="" id="cleardata">
							
							<c:forEach items="${latestRecentList}" var="item" >
							 <div class="rowPadding borderBottomDotted">
							    ${item.createdDate.toLocaleString()}<br> Search by: <a href="#"	id="${item.saveSearchID}" onclick="performCurrentSearch(this.id);"						
									 class="newWindow">${item.keywords} / ${item.searchName}</a>
							   
							   </div>
									
								</c:forEach>
								</div>
								</div>
						</div>
						</div>
</body>
</html>