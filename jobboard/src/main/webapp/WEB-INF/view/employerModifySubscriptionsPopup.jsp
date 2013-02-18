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
		$('#mailCheckbox').prop('checked', true);
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
				 }
			});
			
	     }); 
		
		/* modifyDig('${digitalSubList.size()}');
		modifyNews('${enewSubList.size()}'); */
		
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
					<table class="news">
						<tr class="borderTopNone">
							<div class="row paddingBottom10 marginLeft10">I would like
								the following sent to me so I can stay up to date with the
								latest healthcare news and information. The following
								subscriptions are always a free service.</div>
						</tr>
						<tr>
							<td valign="top"><form:hidden path="digCheckbox"
									id="digCheckbox"/><label for="checkbox"><strong>Digital-Magazine</strong></label>&nbsp;&nbsp;<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<c:if test="${!digitalSubList.isEmpty()}">
									<c:forEach items="${digitalSubList}" var="subscriptionsprint"
										varStatus="status">
									<div style=" width:170px; padding-left:10px;">
										<form:checkbox path="digSub"
											label="${subscriptionsprint.optionName}"
											value="${subscriptionsprint.optionId}" cssStyle="width:20px;"
											id="dig${status.index}" />
										</div>													
								</c:forEach>
								</c:if></td>

							<td valign="top"><form:hidden path="enewsCheckbox"
									id="enewsCheckbox"/><label
								for="checkbox"><strong>E-newsletters</strong></label>&nbsp;&nbsp;<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<c:if test="${!enewSubList.isEmpty()}">
									<c:forEach items="${enewSubList}" var="subscriptionsprint"
										varStatus="status">
										<div style=" width:170px; padding-left:10px;">
										<form:checkbox path="newsSub"
											label="${subscriptionsprint.optionName}"
											value="${subscriptionsprint.optionId}" cssStyle="width:20px; "
											id="news${status.index}" />
										</div>												
								    </c:forEach>
								</c:if></td>

							<td valign="top" width="200"><form:hidden class="floatLeft"
									path="mailCheckbox" id="mailCheckbox" /><label
								class="floatLeft" for="checkbox"><strong>E-mails</strong></label>
								<div class="toolTip marginLeft5">
									<span class="classic">Select this option if you want us
										to send you emails regarding featured career opportunities</span>
								</div>&nbsp;&nbsp;<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <c:if
									test="${!listEmailer.isEmpty()}">

									<c:forEach items="${listEmailer}" var="subscriptionsprint"
										varStatus="status">
										<div style="width: 120px; padding-left: 10px;">
											<form:checkbox path="emailSub"
												label="${subscriptionsprint.optionName}"
												value="${subscriptionsprint.optionId}"
												id="news${status.index}" />
										</div>
									</c:forEach>
								</c:if></td>

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