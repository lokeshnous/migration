<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>ADVANCE Heathcare Jobs</title>
<script type="text/javascript">
	function loadRecentSearch(searchJobId){
		$.ajax({url: "${pageContext.request.contextPath}/savedSearches/editSavedSearch.html?searchId="+searchJobId+"&performSearch=performSearch",
			success: function(data){ 
				$.each(data, function(key, val) {
					 if (key == "searchtype" && val == "basic") {
						parent.window.location.href = '${pageContext.request.contextPath}/search/findJobPage.html';
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
	
	function displaysavesearchpopup(savesearchid) {
		$.ajax({url : "${pageContext.request.contextPath}/savedSearches/displaysavesearchpopup.html?savesearchid="+savesearchid,
			success: function(data){ 
				$.each(data, function(key, val) {
					if (key == "NavigationPath") {
						$.nmManual(val+ '.html',{closeOnEscape: false, showCloseButton: false, closeOnClick: false});
					}
					
					/*if (key == "LoggedInNavigationPath") {
						$.nmManual(val + '.html');
					}*/
				}); 
			    /*if(data.success != null){
			    }
			    if(data.failure != null){
			    	$("#errorMsg").html("Please enter the required parameters.");
			    }*/
			},
			error: function(response) {
				alert("Server Error : "+response.status);
			},
			complete: function() {
				
			}
		
		});
	} 
	 
	$("#Clear").click(function() {
		$.ajax({
			url : "${pageContext.request.contextPath}/search/clearrecentsearches.html",
			success : function(data) {
			//alert("All recent search has been cleared!");
			parent.$.nmTop().close();
			window.location.reload();
			},
			});
		});
</script>
</head>
<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<input type="hidden" name="myrecentserach" id="myrecentserach"
			value="myrecentserach" />
		<div class="popupHeader">
			<h2>MY RECENT SEARCHES</h2>
			<img src="<%=request.getContextPath()%>/resources/images/Close.png" width="19" height="19"
			class="nyroModalClose cursor" title="Close" alt="" onclick="window.location.reload();">
		</div>
		<div id="recentId">
			<ul>
				<!--  <li> -->
				<!--  <a href="" ></a>Clear All&nbsp;&nbsp;|&nbsp;&nbsp;<a href="" ></a>See All </li>-->
				<c:forEach items="${recentSearchList}" var="item">
					<div class="rowPadding borderBottomDotted" id="searchdata">
						<li>${item.createdDate.toLocaleString()}, Criteria: 
						<span class="jb">
						<a
								href="#" onclick="loadRecentSearch(${item.saveSearchID})">${item.recentURL}</a>
								</span> 
								<input 
							type="button" value="Save This Search" class="white floatRight cursor"
							 onclick="displaysavesearchpopup('${item.saveSearchID}')"
							id="${item.saveSearchID}" />
						</li>

					</div>
				</c:forEach>

			</ul>
		</div>
		<div class="popUpButtonRow" align="left">
			<input type="button" value="Clear All" class="orange cursor" id="Clear" />
		    <input type="button" value="Cancel" class="orange nyroModalClose cursor" onclick="window.location.reload();" name="Cancel" />
		</div>
	<%--</div>--%>
	</div>
</body>
</html>