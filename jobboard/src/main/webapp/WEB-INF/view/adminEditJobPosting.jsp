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
	<!-- <script type="text/javascript"
		src="javascripts/jquery.cycle.all.min.js"></script>
	<script type="text/javascript" src="javascripts/slider.js"></script>
	<script type="text/javascript" src="javascripts/jquery.megamenu.js"></script> -->

	<!-- <script type="text/javascript" src="jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="jquery.autocomplete.min.js"></script>-->
	<script type="text/javascript">
	$("#advJobId").keypress(function (event) {
		if(event.which == 13){
			return false;
		}
	  });
jQuery(document).ready(function() {
		$("#bodyPart").hide();
		$("#SearchJob").click(function(event){		
			var advJobId = $.trim($("#advJobId").val());
			$("#ErrorMsg").text("");
			if(advJobId == ''){
				$("#ErrorMsg").text("Please enter an Adv Job Id!");
				$("#bodyPart").hide();
				return false;
			}
			if(!advJobId.match(/^\d+$/)){
				$("#ErrorMsg").text("Please enter an valid Adv Job Id!");
				$("#bodyPart").hide();
				return false;
			}
			$.ajax({url: "${pageContext.request.contextPath}/admin/manageEditJobSearch.html?advJobId="+advJobId,
				success: function(data){ 
					if(data.record =='scheduleOrDraft'){
						$("#ErrorMsg").text("Scheduled or Draft job cannot be updated");
						//loadTable();
						$("#bodyPart").hide();
				    }
					if(data.record =='no record'){
						$("#ErrorMsg").text("No result found");
						$("#bodyPart").hide();
						loadTable();
				    }
					if(data.record =='')
					{
				 		$("#bodyPart").show();
				 		loadTable();
					}
				 	/* else{
				 		$("#bodyPart").show();
				 		loadTable();
				 	} */
				},
				error: function(response) {
					alert("Server Error : "+response.status);
				},
				complete: function() {
					
				}
			}); 
		});
		
		$(function() {
		    $('#Save').bind('click', function(event){
		    	var advJobId = $("#advJobId").val();
				var startDate=$("#startDate").val();
				var endDate=$("#endDt").val();
		        var txtVal =  $('#endDt').val();
		        var postedJobListId =  $('#postedJobListId').val();
		        var convStartDate = new Date(startDate);
		        var convEndDate = new Date(endDate);
		        if(convEndDate < convStartDate){
		        	$("#ErrorMsg").text("Your starting date must take place before your ending date.");
					return false;
		        }
		        if(postedJobListId != 'true'){
					$("#ErrorMsg").text("Please enter an Adv Job Id!");
					return false;
				}
		        if(isDate(txtVal)){
		           // alert('Valid Date');
		        //	return true;
		        	$.ajax({url: "${pageContext.request.contextPath}/admin/manageEditJobSearchSave.html?advJobId="+advJobId+"&endDate="+endDate+"&startDate="+startDate,
						 success: function(data){ 
							 if(data == ''){
									alert("Successfully changed!");	
									//loadTable();
									parent.$.nmTop().close();
								}else{
									$("#errmsg").html(data);
									$("#bodyPart").hide();
								} 
						},
						error: function(response) {
							alert("Server Error for Save data: "+response.status);
						},
						complete: function() {
							
						}
					});  
		            }
		        else{
		            alert('Invalid Date!');
		        	return false;
		       		}
		    });
				    
		function isDate(txtDate)
		{
		    var currVal = txtDate;
		    if(currVal == '')
		        return false;
		    
		    var rxDatePattern = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/; //Declare Regex
		    var dtArray = currVal.match(rxDatePattern); // is format OK?
		    
		    if (dtArray == null)
		        return false;
		    
		    //Checks for mm/dd/yyyy format.
		    dtMonth = dtArray[1];
		    dtDay= dtArray[3];
		    dtYear = dtArray[5];        
		    
		    if (dtMonth < 1 || dtMonth > 12)
		        return false;
		    else if (dtDay < 1 || dtDay> 31)
		        return false;
		    else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay ==31)
		        return false;
		    else if (dtMonth == 2)
		    {
		        var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
		        if (dtDay> 29 || (dtDay ==29 && !isleap))
		                return false;
		    }
		    return true;
		}

		});
		
		
		jQuery(".megamenu").megamenu();
		$.nmFilters({
    	    custom: {
    	        afterShowCont: function(nm) {
    	        	$('#advJobId').focus();
    	        }
    	    }
    	});
window.onload = function() {
	loadTable();
}

function closePopup() {
	parent.window.location.reload();
}
function loadTable(){
	$.ajaxSetup({ cache: false });
	
	$.ajax({
		url : '../admin/adminEditJobSave.html',
		data : ({}),
		
		success : function(data) {
		$("#tableContent").html(data);
		},
		error : function(data) {
		},
		complete : function(data) {
			
		}
	}
	);
  }

});
			
</script>
<script type="text/javascript">
		$('#Cancel').click(function(){		
			parent.$.nmTop().close();		
		});
		
		</script>
</head>
<body class="job_board">
<form:form method="GET" id="formid" name="formid">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>MANAGE/EDIT JOB POSTING EXPIRE DATE</h2>
			<img id="closeCheckOut" src="<%= request.getContextPath() %>/resources/images/Close.png" class="nyroModalClose cursor" title="Close" alt="Close"/>
		</div>
		<div class="row">
		<span class="lableText3"></span><span id="ErrorMsg" class="FormErrorDisplayText01"> </span>
		</div>
		<div class="popUpContainerWrapper">
		
		<input type="hidden" name="advJobId"/>
		<input type="hidden" name="method">		
		
				
			<div class="rowEvenNewSpacing">
					<span class="lableText3">
						Adv Job Id
					</span>
					<input name="advJobId" id="advJobId" class="job_seeker_email focus" type="text"/>&nbsp;&nbsp;&nbsp;
					<input type="button" value="Search" name="SearchJob" id="SearchJob" class="btn_sm orange cursor"  />
					<!-- ${! empty( numList ) }<div class="toolTip"><span class="classic">Example: Only Job id like 15030</span></div> -->
			</div>
			<%-- <c:if test="<%=request.getAttribute(\"postedJobList\")!=null%>">
			<c:if test="${not empty postedJobList}">  --%>
			<div id="bodyPart">
			 <jsp:include page="adminEditJobSave.jsp" />
		 	 <div class="rowEvenNewSpacing marginTop20 paddingBottom10">
						<span class="floatLeft marginTop10">
							<input type="button" value="Save" name="Save" id="Save" class="btn_sm orange cursor" />
							<input type="button" value="Cancel" name="Cancel" id="Cancel" class="orange cursor" />
								
						</span>
					</div>
				<div class="clearfix">
				</div>
				</div>
			<%-- </c:if> --%>
		</div>
		<div class="clearfix">
		</div>
	</div>
	</form:form>
</body>

</html>