<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />
<link href="../resources/css/JB.css" rel="stylesheet" type="text/css" />
<link href="../resources/css/jquery.megamenu.css" rel="stylesheet"
	type="text/css" />
<link href="../resources/css/SliderStyles.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" type="text/css" media="screen"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="jquery.autocomplete.css" />
	<!-- JAVASCRIPT FILES -->
	<!--  <script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>-->
	<script type="text/javascript"
		src="javascripts/jquery.cycle.all.min.js"></script>
	<script type="text/javascript" src="javascripts/slider.js"></script>
	<script type="text/javascript" src="javascripts/jquery.megamenu.js"></script>

	<!-- <script type="text/javascript" src="jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="jquery.autocomplete.min.js"></script>-->
	<script type="text/javascript">
/* function cancelProcess() {
	parent.$.nmTop().close();
} */
function closePopup() {
	parent.window.location.reload();
}

</script>

	<script type="text/javascript">
	jQuery(document).ready(function() {
		//$('[id^=nsId]').keypress(validateNumber);
		//$(".onlyNum").keypress(validateNumber);
		var empList = $.trim($("#empList").val());
		var nsId = $.trim($("#nsId").val());
		
		$('#jobPostingsDiv input').keydown(function(event) {
		     if(event.shiftKey && ((event.keyCode >=48 && event.keyCode <=57) 
		             || (event.keyCode >=186 &&  event.keyCode <=222))){
		        // Ensure that it is a number and stop the Special chars
		         event.preventDefault();
		     }
		     else if ((event.shiftKey || event.ctrlKey) && (event.keyCode > 34 && event.keyCode < 40)){     
		          // let it happen, don't do anything
		     }      
		     else{
		        // Allow only backspace , delete, numbers               
		        if (event.keyCode == 9 || event.keyCode == 46 || event.keyCode == 8 || event.keyCode == 39 ||event.keyCode == 37 
		                || (event.keyCode >=48 && event.keyCode <=57)) {
		            // let it happen, don't do anything
		        }
		        else {
		           // Ensure that it is a number and stop the key press
		                event.preventDefault();                   
		        }
		     }
		});
		
		$("#save").click(function(event){
			$("#ErrorMsg").text("");
			var flag = true;
			$("#jobPostingsDiv input").each(function(){
				if($(this).val() == 0){
					flag = false;
				}
			});
			
			if(!flag){
				$("#ErrorMsg").text("Please enter the the available quantity > 0");
			}else{
				var stringObj;
				var stringObjNew = '';
				//storing data in key  value manner
				$('#tb_save_search > tbody > tr').each(function(){
				    var inveId  = $(this).attr("id");   
				    var availQty = $(this).find("td").eq(3).children().val(); 
				    stringObj = inveId +"="+ availQty;
				    stringObjNew = stringObj +";" + stringObjNew ;
				 });
				$('#jp_slot_save > tbody > tr').each(function(){
				    var inveId  = $(this).attr("id");   
				    var availQty = $(this).find("td").eq(3).children().val(); 
				    stringObj = inveId +"="+ availQty;
				    stringObjNew = stringObj +";" + stringObjNew ;
				 });
				$.ajax({url: "${pageContext.request.contextPath}/admininventory/saveAvailJobQty.html?stringObjNew="+stringObjNew,
					data:$('#adminInventoryId').serialize(),
					type:"GET",
					success: function(data){
					    if(data == true){
					    	alert("Inventory details saved successfully!");
					    	parent.$.nmTop().close();
					    }else{
					    	alert("Error occured while saving the data!");
					    	parent.$.nmTop().close();
					    }
					    if(data.failure != null){
					    };
					},
					error: function(response) {
						alert("Server Error : "+response.status);
					},
					complete: function() {
					}
				});
			}	
		}); 
		
		$("#find").click(function(event){	
			/* if (empList == ''|| nsId == ''){
				$("#ErrorMsg").text("Please enter Employee List or Net Suite Id to continue");
				return false;
			} */
			empList = $.trim($("#empList").val());
			nsId = $.trim($("#nsId").val()); 
			 $("#ErrorMsg").text("");
			$.ajax({url: "${pageContext.request.contextPath}/admininventory/jobPostSearch.html?empList="+empList+"&nsId="+nsId,
				success: function(data){ 
						 var response = true;
						 if (data.success == response) {	
							//window.location.href = '${pageContext.request.contextPath}/admininventory/employer1/jobInventory1.html';
							$("#seachResult").click();							
						 }else{
							 $("#ErrorMsg").text(data.errMsg);
							 $("#tb_save_search tbody").remove();
							 $("#jp_slot_save tbody").remove();
							 
						 }
						 //alert(data.nsId);
				},
				error: function(response) {
					//alert("Server Error : "+response.status);
				},
				complete: function() {
				}
			});
		});
		jQuery(".megamenu").megamenu();
		
	});
</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>MANAGE / EDIT JOB POSTING INVENTORY</h2>
			<img id="closeButton" class="nyroModalClose" src="../resources/images/Close.png"
				title="Close" width="19" height="19"
				alt="Close">
		</div>
		<div class="row">
		<span id="ErrorMsg" class="FormErrorDisplayText01"> </span>
		</div>
		<div class="popUpContainerWrapper">
			<form:form  id="adminInventoryId"
				commandName="inventoryForm">

			<div class="row">
				<span class="splLableText">Company Name: &nbsp;</span>
				<input type="text" id="empList" name="empList"
					class="job_seeker_Resume" value="${empList}"/>
				<span class="splLableText FormErrorDisplayText01">&nbsp;&nbsp;OR</span>
				<span class="lableText7">Net Suite ID Number:</span>
				<input type="text" id="nsId" name="nsId" class="job_seeker_Resume onlyNum"
					value="${nsId}" />&nbsp;&nbsp;
				<c:if test="${pageName == 'adminDashboard'}">
				<input type="button" value="find" name="find" id="find"
					class="btn_sm orange cursor" />
				</c:if>	
			</div>
			<input type="hidden" name="nsId" />
			<input type="hidden" name="empList" />
			<input id="tb_save_search_total" value="${jbPostList.size()}" type="hidden">
			<input id="jp_slot_save_total" value="${jbSlotList.size()}" type="hidden">
			<div id="jobPostingsDiv">
			<c:if test="${pageName != 'adminDashboard'}">
				<c:if test="${jbPostList.size() != 0}">
				<div class="rowEvenNewSpacing marginTop0">
					<div class="row FontSize18 boldText">Standard Job Posting</div>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						id="tb_save_search" class="grid">
						<thead>
							<tr>
								<th width="41%" align="left" valign="top" scope="col">Type</th>
								<th width="13%" align="center" valign="top" scope="col">Duration</th>
								<th width="13%" align="center" valign="top" scope="col">Purchased</th>
								<th width="11%" align="center" valign="top" scope="col">Available</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${jbPostList}" var="jbPostList" varStatus="rowCount">
								<tr id="${jbPostList.invDetailId}" class="Height30">
									<td align="left">${jbPostList.getAddon()}</td>
									<td align="center">${jbPostList.getDuration()}</td>
									<td align="center">${jbPostList.getQuantity()}</td>
									<td align="center">
									
									<input id="tb_save_search_${rowCount.index+1}" class="jb_input4 onlyNum"
									maxlength="3"	type="text" value="${jbPostList.getAvailableQty()}" /></td>
								</tr>
							</c:forEach>							
						</tbody>						
					</table>
					
					
				</div>
				</c:if>
				<c:if test="${jbSlotList.size() != 0}">
				<div class="rowEvenNewSpacing marginTop20">
					<div class="row FontSize18 boldText">Job Posting Slot</div>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						id="jp_slot_save" class="grid">
						<thead>
							<tr>
								<th width="41%" align="left" valign="top" scope="col">Type</th>
								<th width="13%" align="center" valign="top" scope="col">Duration</th>
								<th width="13%" align="center" valign="top" scope="col">Purchased</th>
								<th width="11%" align="center" valign="top" scope="col">Available</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${jbSlotList}" var="jbSlotList" varStatus="rowCount">
								<tr id="${jbSlotList.invDetailId}" class="Height30">
									<td align="left">${jbSlotList.getAddon()}</td>
									<td align="center">${jbSlotList.getDuration()}</td>
									<td align="center">${jbSlotList.getQuantity()}</td>
									<td align="center">
									<input id="jp_slot_save_${rowCount.index+1}"
									 class="jb_input4 onlyNum" maxlength="3"
										type="text" value="${jbSlotList.getAvailableQty()}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				</c:if>
				</div>
				<input type="hidden" name="pageValue" value="inventoryPage" />
				<div class="row marginTop20 paddingBottom10">
				<c:if test="${jbPostList.size() != 0 || jbSlotList.size() != 0 }">
					<a id="save" class="purchaseJobPostings btn_sm orange cursor">SAVE</a>
				</c:if>	
					<a href="" class="nyroModalClose btn_sm orange cursor">Cancel</a>
					<a href="<%=request.getContextPath()%>/admininventory/addJobPosting.html" class="nyroModal btn_sm orange cursor">Add New Job Posting</a>
				</div>
				</c:if>
				<a id="seachResult" class="nyroModal" href="<%=request.getContextPath()%>/admininventory/employer1/jobInventory1.html" style="visibility: hidden;"></a>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>