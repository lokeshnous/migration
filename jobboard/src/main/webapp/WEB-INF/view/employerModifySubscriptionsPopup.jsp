<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />
<script type="text/javascript">
	jQuery(document).ready(function() {

		$('#save').click(function(){
			 var digCheckbox = $('#digCheckbox').is(':checked');
			 var enewsCheckbox = $('#enewsCheckbox').is(':checked');
			 var mailCheckbox = $('#mailCheckbox').is(':checked');
				
			$.ajax({url:"${pageContext.request.contextPath}/subscriptions/saveFacilitySubscription.html?digCheckbox="+digCheckbox+
				"&enewsCheckbox="+enewsCheckbox+"&mailCheckbox="+mailCheckbox,
				data:$('#subscriptionsId').serialize(),
				type:"GET",
				success: function(data) {	
					alert("Data saved successfully!");
					location.reload();
				 },
				 error : function (response) {
					 //alert("Unable to process");
				 }
			});
			
	     }); 
		
		modifyDig('${digitalSubList.size()}');
		modifyNews('${enewSubList.size()}');
		
		jQuery(".megamenu").megamenu();		
	});
	
	function modifyDig(size){
		var digCheckbox = $('#digCheckbox').is(':checked');
		if(!digCheckbox){	
			for(var i=0;i<size;i++)
			{
				document.getElementById('dig'+i).disabled = true;
			}
		}
		else{
			for(var i=0;i<size;i++)
			{
				document.getElementById('dig'+i).disabled = false;
			}
		}
	} 
	
	function modifyNews(size){
		var enewsCheckbox = $('#enewsCheckbox').is(':checked');
		if(!enewsCheckbox){	
			for(var i=0;i<size;i++)
			{
				document.getElementById('news'+i).disabled = true;
			}
		}
		else{
			for(var i=0;i<size;i++)
			{
				document.getElementById('news'+i).disabled = false;
			}
		}
	}
</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>MODIFY SUBSCRIPTIONS</h2>
			<img src="../resources/images/Close.png" class="nyroModalClose cursor"
				title="Close" width="19" height="19" alt="">
		</div>
		<div class="popUpContainerWrapper">
			<form:form action="../subscriptions/modifyFacilitySubscriptions.html"
				method="GET" id="subscriptionsId" commandName="facilitySubsForm">
				<div class="rowEvenNewSpacing marginTop0">
					<table>
						<tr class="borderTopNone">
							<th align="left" scope="col">Subscriptions</th>
						</tr>
						<tr>
							<td valign="top"><form:checkbox path="digCheckbox"
									id="digCheckbox" onchange="modifyDig('${digitalSubList.size()}')" /><label for="checkbox">Digital-Magazine</label>&nbsp;&nbsp;<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<c:if test="${!digitalSubList.isEmpty()}">
									<c:forEach items="${digitalSubList}" var="subscriptionsprint"
										varStatus="status">

										<form:checkbox path="digSub"
											label="${subscriptionsprint.optionName}"
											value="${subscriptionsprint.optionId}" cssStyle="width:20px"
											id="dig${status.index}" />
										<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;														
										       </c:forEach>
								</c:if></td>

							<td valign="top"><form:checkbox path="enewsCheckbox"
									id="enewsCheckbox" onchange="modifyNews('${enewSubList.size()}')" /><label
								for="checkbox">E-newsletters</label>&nbsp;&nbsp;<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<c:if test="${!enewSubList.isEmpty()}">
									<c:forEach items="${enewSubList}" var="subscriptionsprint"
										varStatus="status">

										<form:checkbox path="newsSub"
											label="${subscriptionsprint.optionName}"
											value="${subscriptionsprint.optionId}" cssStyle="width:20px"
											id="news${status.index}" />
										<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;														
										       </c:forEach>
								</c:if></td>

							<td valign="top"><form:checkbox path="mailCheckbox"
									id="mailCheckbox" /><label for="checkbox">E-mailer</label><br />
							</td>

						</tr>
					</table>
				</div>
				<div class="row marginTop10 paddingBottom10">
					<span class="floatLeft marginTop10"> <input type="button"
						id="save" class="orange cursor" value="Save" /> <input
						type="button" value="Cancel" class="nyroModalClose orange cursor"
						name="Cancel" />
					</span>
				</div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>
</body>
</html>