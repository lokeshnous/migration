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
	parent.window.location.reload();
	//parent.$.nmTop().close();
}
$(document).keyup(function(event) {
	if (event.keyCode == 27) {
		parent.window.location.reload();
	}
});
	jQuery(document).ready(function() {
						$("#tb_save_search img").click(function(event) {
							
							var action = $(this).attr("class");
							var rowObj = $(this).parent().parent().parent().parent();
							var saveSearchId = rowObj.attr("id");
							
							var saveSearchedUrl = rowObj.attr("href");
							//var searchName = $("#searchName").text();
							
							//alert(action);
							switch (action) {
							 case "edit":		
								
							   break; 
							case "delete":{
								var r=confirm("Are you sure you want to delete?");
								if(r==true){
									$.ajax({url: "${pageContext.request.contextPath}/savedSearches/deleteSavedSearch.html?saveSearchId="+saveSearchId,
											success: function(data){ 
											    if(data.success != null){
											    	$("#mySavedSearches").click();
											    	//rowObj.remove();
											    }
											    if(data.failure != null){
											    	alert(data.failure);
											    }
											},
											error: function(response) {
												alert("Server Error : "+response.status);
											},
											complete: function() {
												
											}
										});
									}
									break;
							}
							}
						});											
						//For saving the data					
						$("#saveData").click(function(event){						
							var stringObj;
							var stringObjNew = '';
							//storing data in key  value manner
							$('#tb_save_search > tbody > tr').each(function(){
							    var saveSearchId  = $(this).attr("id");   
							    var notifyMe = $(this).find("td").eq(2).children().val(); 
							    stringObj = saveSearchId +"="+ notifyMe;
							    stringObjNew = stringObjNew +";"+ stringObj;
							 });
							$.ajax({url: "${pageContext.request.contextPath}/savedSearches/saveSearchedNames.html?stringObjNew="+stringObjNew,
								success: function(data){ 
								    if(data.success != null){
								    	alert("Data saved successfully!");
								    	//parent.$.nmTop().close();
								    	parent.window.location.reload();
								    }
								    if(data.failure != null){
			
								    }
								},
								error: function(response) {
									alert("Server Error : "+response.status);
								},
								complete: function() {
									
								}
							}); 
						}); 
						jQuery(".megamenu").megamenu();
					});
</script>
<script type="text/javascript">

function geteditSavedSearch(searchJobId){
	
		var id = searchJobId.replace("editSavedSearch", "");
		$.ajax({url: "${pageContext.request.contextPath}/savedSearches/editSavedSearch.html?searchId="+id+"&performSearch=",
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
    
    
    
function performSavedSearch(searchJobId){
	
	var id = searchJobId.replace("performSavedSearch", "");
	
	$.ajax({url: "${pageContext.request.contextPath}/savedSearches/editSavedSearch.html?searchId="+id+"&performSearch=performSearch",
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
    
    
function viewSavedSearch(searchJobId){
	
var id = searchJobId.replace("viewSavedSearch", "");
	
	
	$.ajax({url: "${pageContext.request.contextPath}/savedSearches/editSavedSearch.html?searchId="+id+"&performSearch=performSearch",
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
    
    
    
</script>

</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>My Saved Searches</h2>
			<a href="#"><img src="../resources/images/Close.png" width="19" title="Close"
				height="19" onclick="closePopup();" alt=""></a>
		</div>

		<div class="popUpContainerWrapper">
			<form:form  method="GET" action ="" commandName="saveSearchForm">
				<div class="row">
					<table id="tb_save_search" width="100%" border="0" cellspacing="0"
						cellpadding="0" class="grid">
					<thead>
						<tr>
							<th width="49%" align="left" scope="col">Saved Search Name</th>
							<th width="15%" align="center" scope="col">Updated</th>
							<th width="22%" align="center" scope="col">&nbsp;&nbsp;Notify
								Me</th>
							<th width="14%" align="center" scope="col">Actions</th>
						</tr>
					</thead>
					<tbody>	
						<c:forEach items="${saveSearchedJobsDTOList}"
							var="saveSearchdtoList"  varStatus="status">
							<tr id="${saveSearchdtoList.saveSearchID}">
								<td><a href="#"	id="performSavedSearch${saveSearchdtoList.saveSearchID}" onclick="performSavedSearch(this.id);"						
									 class="newWindow" >${saveSearchdtoList.getSearchName()}</a></td>
								<td align="center">${saveSearchdtoList.getModifyDate()}</td>
								
								<td align="center">					
								 <form:select class="jb_input3 select100"									
										path="saveSearchedJobsDTOList[${status.index}].emailFrequency" items="${notifyMeList}"
										itemValue="optionId" itemLabel="optionName">																				
								    </form:select>	 						    										
								</td>
								
								<td align="center">
								<div class="SearchIcons">
								<a href='#' id="viewSavedSearch${saveSearchdtoList.saveSearchID}" onclick="viewSavedSearch(this.id);" class="newWindow"> <img title="Perform Search" src="../resources/images/tranBg.png" class="view"></a>
								<a href='#' id="editSavedSearch${saveSearchdtoList.saveSearchID}" onclick="geteditSavedSearch(this.id);" class="newWindow"><img title="Edit" src="../resources/images/tranBg.png" class="editFile"></a>
								<a href="#"><img title="Delete" src="../resources/images/tranBg.png" class="delete"></a>
								</div>
										</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="popUpButtonRow">
					<a href="<%=request.getContextPath()%>/search/findJobPage.html?isNewSearch=true">
						<h3>${msg.jsCreateNewSavedSearch}</h3>
					</a> <em class="lineHeight16">You can create up to 5 saved searches</em>
				</div>			
			<div class="popUpButtonRow">
			 <c:if test="${not empty saveSearchedJobsDTOList}">
				<input type="button" id="saveData" class="orange cursor" value="Save"/>
			 </c:if>	
				<input type="button" onclick="closePopup();" class="orange cursor" value="Cancel"/>
			</div>
			<a id="mySavedSearches" href="<%=request.getContextPath()%>/savedSearches/viewMySavedSearches.html" class="nyroModal"></a>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>