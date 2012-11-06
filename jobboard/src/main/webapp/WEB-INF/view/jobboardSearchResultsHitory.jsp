<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<body>
<div class="search_info_box1" id="latestRecentListId">
							<div class="rowPadding borderBottomDotted" id="aaa">
								
								<div id="clear">
									
								
							<div class="" id="cleardata">
							
							<c:forEach items="${latestRecentList}" var="item" >
							 <div class="rowPadding borderBottomDotted">
							    ${item.createdDate.toLocaleString()}<br> Search by: <a href="" ></a>${item.keywords} / ${item.searchName}
							   
							   </div>
									
								</c:forEach>
								</div>
								</div>
						</div>
</body>
</html>