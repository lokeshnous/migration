<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@page import="com.advanceweb.afc.jb.common.util.MMJBCommonConstants"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />
<script type="text/javascript">
function cancelProcess(){
	parent.$.nmTop().close();
}
	function closePopup() {
		parent.window.location.reload();
	}
	jQuery(document).ready(function() {
		$("#addNewJobOwnerPopUp").displaypopup("#addNewJobOwnerPopUp","770","360");
		$('#save').click(function(){
		var selOwnerId = $("#selJobOwner").val();
		var validate = true;
		if(!$.isNumeric(selOwnerId)){
	    	 $("#errmsg").html("Please add a new Job Owner and set the alerts.");
	    	 validate=false;
	      }
			if(validate){
				$.ajax({url:"${pageContext.request.contextPath}/alerts/employer/saveAlerts.html?selOwnerId="+selOwnerId,
					data:$('#alertId').serialize(),
					type:"GET",
					success: function(data) {	
						alert("Data saved successfully !");
							parent.$.nmTop().close();
					 },
				});
		}
				
		}); 
		jQuery(".megamenu").megamenu();
	});
</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader marginBottom0">
			<h2>SET ALERTS</h2>
			<img title="Close" class="nyroModalClose cursor"
				src="../resources/images/Close.png" width="19" height="19"
				title="Close" alt="cancel">
		</div>
		<div class="popUpContainerWrapper">
		<div id="errmsg" class="validationMsg">
				</div>
			<form:form method="GET" action="../alerts/employer/saveAlerts.html"
				id="alertId" commandName="alertForm">
				<div class="rowEvenNewSpacing">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="grid2">
						<c:forEach items="${alertList}" var="alertList" varStatus="index">
							<c:if test="${index.count % 2 != 0 }">
								<tr>
							</c:if>
							<td><form:checkbox path="selectedAlerts"
									label="${alertList.optionName}" value="${alertList.optionId}"
									cssStyle="width:20px" /></td>
							<c:if test="${index.count % 2 == 0 }">
								</tr>
							</c:if>
						</c:forEach>
					</table>
					<div
						class="rowEvenNewSpacing borderBottomDotted paddingBottom10 marginTop0"></div>
					<div class="clearfix"></div>
					<span><h3 class="TextColor01 marginTop15">Select who
							you would like to receive the alert(s) checked above.</h3></span>
					<div class="rowEvenNewSpacing">
						<span class=" FloatLeft marginTop3">Job Owner: </span>

						<form:select class="jb_input3  marginTop0 width150 marginLeft5"
							path="selJobOwner" items="${jbOwnerList}" itemValue="optionId"
							itemLabel="optionName">
						</form:select>
						<c:if
							test="${enableAccess == 'true' && enablePostEditAccess == 'true'}">
							<input type="hidden" name="pageValue" value="setAlertPage" />
							<span class="required paddingTop4"> 
							<c:if
							test="<%=!(session.getAttribute(MMJBCommonConstants.FACILITY_POST_EDIT)!=null )%>">
							<c:if
								test="<%=!(session.getAttribute(MMJBCommonConstants.FACILITY_FULL_ACCESS)!=null )%>">
								<security:authorize
									access="!hasRole('ROLE_FACILITY_FULL_ACCESS') and !hasRole('ROLE_FACILITY_POST_EDIT') ">
								<a
								href="<%=request.getContextPath()%>/employer/addNewJobOwner.html?page=setAlertPage"
								id="addNewJobOwnerPopUp">Add NewJob Owner</a>
								</security:authorize>
							</c:if>
						</c:if>
							</span>
						</c:if>
					</div>

				</div>
				<div class="popUpButtonRow">
					<input type="button" id="save" value="Save" class="orange cursor" />
					<!-- <a href="" class="btn_sm orange">Save</a> -->
					<input type="button" value="Cancel" class="nyroModalClose orange cursor" name="Cancel" />
				</div>
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>
</body>
</html>