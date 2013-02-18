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
	<!-- <script type="text/javascript"
		src="javascripts/jquery.cycle.all.min.js"></script>
	<script type="text/javascript" src="javascripts/slider.js"></script>
	<script type="text/javascript" src="javascripts/jquery.megamenu.js"></script> -->

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
function validateFacility() {
	var valid = true;
	var compName = $.trim($("#empList").val());
	var netsuiteId= $.trim($("#nsId").val()); 
	if(compName == "" && netsuiteId == ""){
		$("#ErrorMsg").text("Please enter Company name or NetSuite ID");
		valid = false;
	}
	//alert(valid);
	return valid;
}
</script>

	<script type="text/javascript">
	jQuery(document).ready(function() {
		
		$.nmFilters({
    	    custom: {
    	        afterShowCont: function(nm) {
    	        	$('.focus').focus();
    	        }
    	    }
    	});
		
		//$('[id^=nsId]').keypress(validateNumber);
		//$(".onlyNum").keypress(validateNumber);
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
			var compName = $.trim($("#empList").val());
			var netsuiteId= $.trim($("#nsId").val());
			if(compName == "" && netsuiteId == ""){
				$("#ErrorMsg").text("Please enter Company name or NetSuite ID");
			}
			else{
			$.ajax({url: "${pageContext.request.contextPath}/impersonationForFacility/saveEditedFacilty.html",
				data:$('#form').serialize(),
				type:"POST",
				success: function(data){ 
				    if(data.failureMsg != null){
				    	alert(data.failureMsg);
				    	parent.$.nmTop().close();
				    }else{
				    	alert("Data saved successfully!");
				    	parent.$.nmTop().close();
				    }
				},
				error: function(response) {
					//alert("Server Error : "+response.status);
				},
				complete: function() {
				}
			}); 
		}
		}); 
		
		$("#find").click(function(event){	
			empList = $.trim($("#empList").val());
			nsId = $.trim($("#nsId").val()); 
			 $("#ErrorMsg").text("");
			$.ajax({url: "${pageContext.request.contextPath}/admininventory/jobPostSearch.html?empList="+empList+"&nsId="+nsId,
				success: function(data){ 
						 var val = true;
						 if (data.success == val) {	
							//window.location.href = '${pageContext.request.contextPath}/admininventory/employer1/jobInventory1.html';
							$.nmManual('${pageContext.request.contextPath}/impersonationForFacility/jobSearchBycompanyName.html');	
						 }else{
							 $("#ErrorMsg").text(data.errMsg);
							 $("#facilityListId").html('');
							 $("#healthSystemId").attr('checked', false);
						 }
						 //alert(data.nsId);
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
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer width750"
		style="display: block">
		<div class="popupHeader">
			<h2>MANAGE/EDIT FACILITY</h2>
			<a href="#"><img src="../resources/images/Close.png"
				title="Close" width="19" height="19" class="nyroModalClose cursor"
				alt=""></a>
		</div>
		<div class="row">
		<span id="ErrorMsg" class="FormErrorDisplayText01"> </span>
		</div>
		<div class="popUpContainerWrapper">
			<form:form method="GET" action="../impersonationForFacility/saveEditedFacilty.html"
				commandName="adminForm" id="form">

			<div class="row">
				<span class="splLableText">Company Name: </span>
				<form:input path="compName" id="empList" name="empList"
					class="job_seeker_Resume focus textBox2" value="${empList}"/>
						<span class="splLableText FormErrorDisplayText01">&nbsp;&nbsp;OR&nbsp;</span>

				<span class="lableText7">Net Suite ID Number:</span>
				<form:input path="nsId" id="nsId" name="nsId" class="job_seeker_Resume onlyNum"
					value="${nsId}" />&nbsp;&nbsp;
 				<c:if test="${result != 'result'}">
				<input type="button" value="find" name="find" id="find"
					class="btn_sm orange cursor" />
				</c:if>
			</div>
			<c:if test="${pageName != 'adminDashboard'}">
			<div class="rowEvenNewSpacing" >
				<span class="splLableText">Company Name:</span>
				<div id="facilityListId" class="splLableText" >
				<div class="row" style="text-align: left">
				<c:forEach items="${facilityList}" var="item">
					${item.companyName}<br/>
				</c:forEach>
				</div>
				</div>
				<div class="splLableText" >
				<form:checkbox path="healthSystem" label="Health System" id="healthSystemId"/> 
				</div>
			</div>
				<input type="hidden" name="pageValue" value="inventoryPage" />
				<div class="row marginTop20 paddingBottom10">
					<input type="button" id="save" value="SAVE" class="purchaseJobPostings btn_sm orange cursor" >
					<input type="button" id="cancelbutton" class="orange cursor" value="Cancel" onclick="parent.$.nmTop().close();" />
				</div>
				</c:if>
			</form:form>
			
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>