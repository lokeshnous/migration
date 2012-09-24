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
</head>
<body>
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
function validateNumber(event) {
    var keyval = window.event ? event.keyCode : event.which;

    if (event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 46
     || event.keyCode == 37 || event.keyCode == 39) {
        return true;
    }
    else if ( keyval < 48 || keyval > 57 ) {
        return false;
    }
    else return true;
};

function closePopup() {
	parent.window.location.reload();
}
</script>

	<script type="text/javascript">
	jQuery(document).ready(function() {
		$('[id^=nsId]').keypress(validateNumber);
		$(".job_seeker_Resume").keypress(validateNumber);
			var empList = $.trim($("#empList").val());
			var nsId = $.trim($("#nsId").val());
		function cancelProcess() {
			parent.$.nmTop().close();
		}
		function validateTable() {
			var count = $("#tb_save_search_total").val();
			var valid = false;
			for(var i=1;i<=count;i++){
				//alert($("#tb_save_search_"+i).val());
				var val = $.trim($("#tb_save_search_"+i).val());
				if(val==''){
					valid = false;
					break;
				}
				valid = true;
			}			
		
			return valid;
		}
		
		$("#save").click(function(event){
			if(!validateTable()){
				alert("Please enter the value!");
				return false;
			}
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
				success: function(data){ 
				    if(data.success != null){
				    	parent.$.nmTop().close();
				    }
				    if(data.failure != null){
				    }
				},
				error: function(response) {
					//alert("Server Error : "+response.status);
				},
				complete: function() {
				}
			}); 
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
						 var pp = true;
						 //alert(data.success == pp);
						 if (data.success == pp) {	
							//window.location.href = '${pageContext.request.contextPath}/admininventory/employer1/jobInventory1.html';
							$.nmManual('${pageContext.request.contextPath}/admininventory/employer1/jobInventory1.html');							
						 }else{
							 $("#ErrorMsg").text(data.errMsg);
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
			<h2>JOB POSTING INVENTORY</h2>
			<a href="#"><img src="../resources/images/Close.png"
				title="Close" width="19" height="19" onclick="closePopup();"
				alt=""></a>
		</div>
		<div class="row">
		<span id="ErrorMsg" class="FormErrorDisplayText01"> </span>
		</div>
		<div class="popUpContainerWrapper">
			<form:form method="GET" action="../admininventory/save.html"
				commandName="inventoryForm">

			<div class="row">
				<span class="lableText8">Employee List: &nbsp;&nbsp; </span>
				<input type="text" id="empList" name="empList"
					class="job_seeker_Resume" value="${empList}"/>

				<span class="lableText6">Net Suite ID Number:</span>
				<input type="text" id="nsId" name="nsId" class="job_seeker_Resume"
					value="${nsId}" />&nbsp;&nbsp;

				<input type="button" value="find" name="find" id="find"
					class="btn_sm orange" />
			</div>
				<input type="hidden" name="nsId" />
				<input type="hidden" name="empList" />
				<input type="hidden" name="pageValue" value="inventoryPage" />
				<div class="row marginTop20 paddingBottom10">
					<a id="save" href="" class="purchaseJobPostings btn_sm orange">SAVE</a>
					<a href="" onclick="cancelProcess();" class="btn_sm orange">Cancel</a>
				</div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>