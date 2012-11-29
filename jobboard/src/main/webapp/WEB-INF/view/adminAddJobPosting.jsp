<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>

<jsp:include page="common/include.jsp" />

<script type="text/javascript">
	
	function isPositiveInt(number){
		var intRegex = /^\d+$/;
		if(!intRegex.test(number.toString())) {
			return true;
		}
		return false;
	}

	$(document).ready(function() {
		$("#quantity").val("");
		$("#saveJobPosting").click(function(){
			var quantity = $("#quantity").val();
			if(isNaN(quantity) || quantity <= 0 || isPositiveInt(quantity)){
				alert("Please enter quantity in numerics( > 0)");
				return;
			}
			else{
				$.ajax({url : "${pageContext.request.contextPath}/admininventory/updateJobPostInventory.html",
					   data:$('#addJobPostingForm').serialize(),
					   type: "POST",
					   success : function(data) {
							if (data.status != null && data.status == "success") {
								alert("Inventory has been updated successfully");
								$("#closeButton").click();
							}
							else{
								alert(data.status);
							}
						},
					   error : function(response) {
						   alert("Server Error : "+ response.status);
					   },
					   complete : function() {
						
					   }
				});
			}
		});
		
		$("#postTypeToolTip").hover(function() {
			$("#postTypeToolTip").attr("title",$("#jbTypeId :selected").text());
		});
		
	});
</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>ADD JOB POSTING</h2>
			<a id="closeButton" class="nyroModal" href="<%=request.getContextPath()%>/admininventory/employer1/jobInventory1.html"> <img src="../resources/images/Close.png" width="19" title="Close"
				height="19" alt="Close"></a>
		</div>
		<div class="popUpContainerWrapper">
			<form:form method="POST" action="" id="addJobPostingForm" commandName="inventoryForm">
				<div class="rowEvenNewSpacing">
					<div class="splLableText">Select Job Posing :</div>
					<div id="postTypeToolTip" title="">
						<form:select class="jb_input3 jb_input_width3"
							path="jbTypeId" items="${jobPostingList}"
							itemValue="optionId" itemLabel="optionName" />
							<span class="required">(Required)</span>
					</div>
				</div>
				<div class="rowEvenNewSpacing">
					<span class="splLableText">Enter The Quantity :</span>
					<form:input path="quantity" class="job_seeker_Resume" maxlength="3"/>
					<span class="required">(Required)</span>
				</div>
				<br><br><br><br><br><br><br><br>
				<div class="popUpButtonRow">
					<span class="floatLeft ">
						<a class="btn_sm orange" href="#" id="saveJobPosting">Save</a>
						<a class="nyroModal btn_sm orange" href="<%=request.getContextPath()%>/admininventory/employer1/jobInventory1.html">Cancel</a>	
					</span>				
				</div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>
</body>
</html>