<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="refresh" content="10">
<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />
<script type="text/javascript">
jQuery(document).ready(function() {
		$("#SearchJob").click(function(event){		
			var advJobId = $("#advJobId").val().trim();
			$("#ErrorMsg").text("");
			if(advJobId == ''){
				$("#ErrorMsg").text("Please enter an Adv Job Id!");
				return false;
			}
			$.ajax({url: "${pageContext.request.contextPath}/admin/manageEditJobSearch.html?advJobId="+advJobId,
				success: function(data){ 
				 	loadTable();
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
									//alert("Data save successfully !");	
									//loadTable();
									parent.$.nmTop().close();
								}else{
									$("#errmsg").html(data);
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
		            alert('Invalid Date');
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

window.onload = function() {
	loadTable();
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
			alert('Unable to process');
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
			<h2>MANAGE/EDIT JOB POSTING</h2>
			<img id="closeCheckOut" src="<%= request.getContextPath() %>/resources/images/Close.png" class="nyroModalClose" alt="Close"/>
		</div>
		<div class="row">
		<span id="ErrorMsg" class="FormErrorDisplayText01"> </span>
		</div>
		<div class="popUpContainerWrapper">
		
		<input type="hidden" name="advJobId"/>
		<input type="hidden" name="method">		
		
				
			<div class="rowEvenNewSpacing">
					<span class="lableText3">
						Adv Job Id
					</span>
					<input name="advJobId" id="advJobId" class="job_seeker_email" type="text"/>
					<input type="button" value="Search" name="SearchJob" id="SearchJob" class="btn_sm orange"  />
					<!-- <div class="toolTip"><span class="classic">Example: Only Job id like 15030</span></div> -->
			</div>
			 <jsp:include page="adminEditJobSave.jsp" />
		 	 <div class="rowEvenNewSpacing marginTop20 paddingBottom10">
						<span class="floatLeft marginTop10">
							<input type="button" value="Save" name="Save" id="Save" class="btn_sm orange" />
							<input type="button" value="Cancel" name="Cancel" id="Cancel" class="orange" />
								
						</span>
					</div>
				<div class="clearfix">
				</div>
			
		</div>
		<div class="clearfix">
		</div>
	</div>
	</form:form>
</body>

</html>