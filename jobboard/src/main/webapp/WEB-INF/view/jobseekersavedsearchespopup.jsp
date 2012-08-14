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
}
$(document).keyup(function(event) {
	if (event.keyCode == 27) {
		parent.window.location.reload();
	}
});
	jQuery(document).ready(function() {
						$("#tb_save_search img").click(function(event) {
							
							var action = $(this).attr("alt");
							var rowObj = $(this).parent().parent().parent();
							var saveSearchId = rowObj.attr("id");
							var saveSearchedUrl = rowObj.attr("href");
							//var searchName = $("#searchName").text();
							
							//alert(action);
							switch (action) {
							 case "edit":		
								
							   break; 
							case "delete":{
								var r=confirm("You want to delete this?");
								if(r==true){
									$.ajax({url: "${pageContext.request.contextPath}/savedSearches/deleteSavedSearch.html?saveSearchId="+saveSearchId,
											success: function(data){ 
											    if(data.success != null){
											    	rowObj.remove();
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
						/* $('.newWindow').click(function (event){			
							
	
							
		                    var url = $(this).attr("href");
		                    
		 
		                    parent.window.location.href = url;
		     	            parent.$.nmTop().close();
		                   event.preventDefault();
		                });	 */
						
						$("#editSavedSearch").click(function (event){	
							var searchName = $(this).parent().parent().children().children().html();
							
							
							$.ajax({url: "${pageContext.request.contextPath}/savedSearches/editSavedSearch.html?searchName="+searchName,
								success: function(data){ 
									$.each(data, function(key, val) {
					//alert(val);
										 if (key == "searchtype" && val == "basic") {
											//window.location.href = val+".html";
					//alert(getBaseURL()+'/jobSeeker/jobSeekerDashBoard' + '.html');
											parent.window.location.href = '${pageContext.request.contextPath}/jobSeeker/jobSeekerDashBoard' + '.html';
											//$.nmManual(getBaseURL()+'/jobSeeker/jobSeekerDashBoard' + '.html');
											parent.$.nmTop().close();
										}
										 /*
										if (key == "LoggedInNavigationPath") {
											parent.$.nmTop().close();
											//$.nmManual(val + '.html');
										}
										
										if(key == "EmptySearchName"){
											$("#ErrorMsg").text("${msg.EmptySearchName}");
										}
										if(key == "DuplicateSearchName"){
											$("#ErrorMsg").text("${msg.DuplicateSearchName}");
										} */
									}); 
									
								    /* if(data.success != null){
								    	//rowObj.remove();
								    	alert("Hi");
								    }
								    if(data.failure != null){
								    	alert(data.failure);
								    } */
								},
								error: function(response) {
									alert("Server Error : "+response.status);
								},
								complete: function() {
									
								}
							}); 
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
							//alert('objects are'+stringObjNew);
							$.ajax({url: "${pageContext.request.contextPath}/savedSearches/saveSearchedNames.html?stringObjNew="+stringObjNew,
								success: function(data){ 
								    if(data.success != null){
								    	//alert(data.success);
								    	parent.$.nmTop().close();
								    }
								    if(data.failure != null){
								    	//alert(data.failure);
								    }
								},
								error: function(response) {
									alert("Server Error : "+response.status);
								},
								complete: function() {
									
								}
							}); 
						}); $("select").change(function () { 
					           $('select option:eq(5)').attr('selected', true);
				        })
				        .trigger('change');
						jQuery(".megamenu").megamenu();
					});
</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>My Saved Searches</h2>
			<a href="#"><img src="../resources/images/Close.png" width="19"
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
								<td><a href="#">${saveSearchdtoList.getSearchName()}</a></td>
								<%-- <a href="${saveSearchdtoList.getUrl()}"							
									rel="0" target="_blank" class="newWindow" >${saveSearchdtoList.getSearchName()}</a></td> --%>
								<td align="center">${saveSearchdtoList.getModifyDate()}</td>
								
								<td align="center">						
										
								    <form:select class="jb_input3 select100"									
										path="saveSearchedJobsDTOList[${status.index}].emailFrequency" items="${notifyMeList}"
										itemValue="optionId" itemLabel="optionName">																				
								    </form:select>
								    <script>
								    $("select").change(function () { 
         							  $('select option:eq(5)').attr('selected', true);
       							   })
        						  .trigger('change');
								    </script>
								    										
								</td>
								
								<td align="center"><a href='' class="newWindow"><img
										src="../resources/images/View.png" width="20" height="20"
										alt="view"></a>&nbsp;<a href='#' id="editSavedSearch" class="newWindow"><img
										src="../resources/images/Edit.png"   width="20" height="20"
										alt="edit" onclick=""></a>&nbsp;<a href="#"><img
										src="../resources/images/Delete.png" width="20" height="20"
										alt="delete" ></a>
										</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="popUpButtonRow">
					<a href="/jobboard/jobsearchactivity/findJobPage.html">
						<h3>${msg.jsCreateNewSavedSearch}</h3>
					</a> <em class="lineHeight16">${msg.jsSavedSearchInfo}</em>
				</div>			
			<div class="popUpButtonRow">
				<input type="button" id="saveData" class="btn_sm orange" value="Save"/>
				<input type="button" onclick="closePopup();" class="btn_sm orange" value="Cancel"/>
			</div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>