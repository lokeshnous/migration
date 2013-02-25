<%@page import="com.advanceweb.afc.jb.common.util.MMJBCommonConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>

<jsp:include page="common/include.jsp" />
<script type="text/javascript" src="../resources/js/highcharts.js"></script>
<script type="text/javascript" src="../resources/js/funnel.src.js"></script>
<script type="text/javascript" src="../resources/js/exporting.src.js"></script>

<link rel="stylesheet" type="text/css" media="screen" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css">

<!-- JAVASCRIPT FILES -->
		<!-- <script type="text/javascript" src="../resources/js/jquery.cycle.all.min.js"></script> -->
		<script type="text/javascript" src="../resources/js/slider.js"></script>
		<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.js"></script>

<script type="text/javascript">
var chart;
var options = {
    chart: {
        renderTo: 'container',
        defaultSeriesType: 'funnel',
        /*margin: [50, 10, 60, 170],*/
        borderWidth: 1
    },
    title: {
        text: ''
    },
    plotArea: {
        shadow: null,
        borderWidth: null,
        backgroundColor: null
    },
    tooltip: {
        formatter: function() {
            return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.y, 0);
        }
    },
    plotOptions: {
        series: {
            dataLabels: {            	
                //align: 'center',
                //x: -50,
                enabled: true, 
                connectorWidth: 0,
        		connectorColor: '#FFFFFF',
                formatter: function() {
                    return '<b>'+ this.point.name +'</b> ('+ Highcharts.numberFormat(this.point.y, 0) +')';
                },
                color: 'black'
            }/*,
            
            neckWidth: '30%',
            neckHeight: '25%'*/
        }
    },
    legend: {
        enabled: false
    },
    series: [{
        name: 'Browser share',
         data: [
                  ['Views',   10],
                  ['Clicks',   20],
                  ['Applies', 30]
              ]       
    }]
};
	jQuery(document).ready(
			function() {
				
				var brandingTemplate=<%= request.getAttribute("brandingTemplate")%>
				if(brandingTemplate){
					$("#manageBrandingTemplatePopup").displaypopup(
							"#manageBrandingTemplatePopup", "775", "252");
					$('#manageBrandingTemplatePopup').trigger('click');
				}
				
				$("#changePassword").displaypopup("#changePassword", "790",
						"370");
				$("#accountSettingpopUp").displaypopup("#accountSettingpopUp",
						"790", "360");
				jQuery(".megamenu").megamenu();
				$("#manageBrandingTemplatePopup").displaypopup(
						"#manageBrandingTemplatePopup", "775", "252");
				$("#postingInventory").displaypopup("#postingInventory", "790",
						"370");
				$("#purchaseJobPostings").displaypopup("#purchaseJobPostings",
						"790", "360");
				$("#accessPermissioPopUp").displaypopup(
						"#accessPermissioPopUp", "770", "360");
				$("#setAlertPopUp")
						.displaypopup("#setAlertPopUp", "770", "360");
				$("#viewAlertPopUp").displaypopup("#viewAlertPopUp", "770",
						"360");
				$("#manageFacilityPopUp").displaypopup("#manageFacilityPopUp",
						"790", "360");
				$("#mySavedResumeSearches").displaypopup(
						"#mySavedResumeSearches", "790", "360");
				$("#modifySubs").displaypopup("#modifySubs", "770", "360");
				
				
				
				$("#metricsExcel")
				.click(
						function() {
						alert("metricsExcel iis calling......");
						
						
						$("#exportButton").trigger("onclick");
						
						$.ajax({
									url : "${pageContext.request.contextPath}/employer/getExcelSheet.html",
								    

									success : function(data) {
									
										
										
										defaultOptions.exporting = {
												
												type: 'image/png',
												
												width: 800,
												buttons: {
													exportButton: {
														//enabled: true,
														symbol: 'exportIcon',
														x: -10,
														symbolFill: '#A8BF77',
														hoverSymbolFill: '#768F3E',
														_id: 'exportButton',
														_titleKey: 'exportButtonTitle',
														menuItems: [{
															textKey: 'downloadPNG',
															onclick: function () {
																this.exportChart();
															}
														}, {
															textKey: 'downloadJPEG',
															onclick: function () {
																this.exportChart({
																	type: 'image/jpeg'
																});
															}
														}, 
														
														]

													},
													printButton: {
														//enabled: true,
														symbol: 'printIcon',
														x: -36,
														symbolFill: '#B5C9DF',
														hoverSymbolFill: '#779ABF',
														_id: 'printButton',
														_titleKey: 'printButtonTitle',
														onclick: function () {
															this.print();
														}
													}
												}
											};
										
									}
									
								});
					}); 
				
				
				
				$("#showMertics")
				.click(
						function() {
							$("#showMetricsErr").html("");
							var startDate = $("#startDate")
									.val();
							var endDate = $("#endDate").val();
							if(startDate == '' || endDate == ''){
								$("#showMetricsErr").html("Please enter both start date and end date");
								//$("#showMetricsErr").css("width", "550px");
								//$("#showMetricsErr").css("text-align", "center");
								//$("#showMetricsErr").css("color", "#FF0000");
								return false;
							}
							var arr = startDate.split("/");
							startDate = arr[1]+"/"+arr[0]+"/"+arr[2];
							arr = endDate.split("/");
							endDate = arr[1]+"/"+arr[0]+"/"+arr[2];
							var convStartDate = new Date(startDate);
					        var convEndDate = new Date(endDate);
					        if(convEndDate < convStartDate){
					        	$("#showMetricsErr").html("Your starting date must take place before your ending date.");
								//$("#showMetricsErr").css("width", "550px");
								//$("#showMetricsErr").css("text-align", "center");
								//$("#showMetricsErr").css("color", "#FF0000");
								return false;
					        }
							
							var startDate = $("#startDate")
									.val();
							
							var endDate = $("#endDate").val();
							
							var selEmployerId = $(
									"#selEmployer").val();
							
							$
									.ajax({
										url : "${pageContext.request.contextPath}/employer/showMertics.html?startDate="
												+ startDate
												+ "&endDate="
												+ endDate
												+ "&selEmployerId="
												+ selEmployerId,
										success : function(data) {
											//alert("Data is :::"+data);									
											loadMetricsDetails();											
										}
									});
						});
				
				

				
				 chart = new Highcharts.Chart(options);
				 

									

			});
	
	

	function getData(obj) {

		id = $(obj).attr("id");

		var metrices = id.split("-");

		views = parseInt(metrices[0]);

		clicks = parseInt(metrices[1]);

		applies = parseInt(metrices[2]);

		if ((views == 0) && (clicks == 0) && (applies == 0)) {

			
			$("#container").html("No data available.");
			$("#container").css("width", "550px");
			$("#container").css("text-align", "center");
			$("#container").css("color", "#FF0000");
			//alert("No data available");

		} else {
			
			$("#container").html('');
			$("#container").css("width", "290px");
			
			chart = new Highcharts.Chart(options);

			chart.series[0].setData([ [ 'Views', views ], [ 'Clicks', clicks ],
					[ 'Applies', applies ] ], false);

			chart.redraw(true);
		}

	}
	window.onload = function() {
		loadMetricsDetails();
	};
	function loadMetricsDetails() {
		$.ajaxSetup({
			cache : false
		});
		$.ajax({
			url : '../employer/metricsDetails.html',
			data : ({}),

			success : function(data) {
				$("#metricsDetails").html(data);
			},
			error : function(data) {
			},
			complete : function(data) {
				// do nothing for now.
			}
		});
	}
	function changeMetrics() {
		$(".EDTextBox").val("");
		var selEmployerId = $("#selEmployer").val();
		$
				.ajax({
					url : "${pageContext.request.contextPath}/employer/viewEmployerMetrics.html?selEmployerId="
							+ selEmployerId,
					data : $('#selEmployerId').serialize(),
					type : "GET",
					success : function(data) {
						loadMetricsDetails();
					},
					error : function(data) {
					}
				});
		$("#postTypeToolTip").attr("title",$("#selEmployer :selected").text());
	}
	
	function getActiveAndAvailJbPosting(selEmployerId) {
		//var selEmployerId = $("#selEmployer").val();
		alert("getActiveAndAvailJbPosting::" + selEmployerId);
		$
				.ajax({
					url : "${pageContext.request.contextPath}/employer/availableJobPostings.html?selEmployerId="
							+ selEmployerId,
					data : $('#selEmployerId').serialize(),
					type : "GET",
					success : function(data) {
						$("#avaQuantity").text(data.avaQuantity);
						$("#count").text(data.count);
					},
					error : function(data) {

					}
				});
	}

	
	$(function() {
		$("#startDate").datepicker({
			dateFormat : "dd/mm/yy"
		}).val();
	});

	$(function() {
		$("#endDate").datepicker({
			dateFormat : "dd/mm/yy"
		}).val();
	});
	jQuery(document).ready(
			function() {
	/* showing Metrics type tooltip */
	$("#postTypeToolTip").attr("title",$("#selEmployer :selected").text());
	});
	
	
</script>
<!-- <script type="text/javascript" src="javascripts/expandCollapse.js"></script> -->
<style>
form {
margin:0;
padding:0;
}
</style> 

</head>

<body class="job_board">

	<form:form commandName="employerDashBoardForm" id="empDashBoard">
		<div class="EmployerDashboardHeader">
			<h1 class="dashboardHeader Padding0"><%=(String) session
						.getAttribute(MMJBCommonConstants.COMPANY_EMP)%>
				Dashboard
			</h1>
		</div>
		<div class="MidContent_Wrapper FloatLeft">
			<div class="dashboardColumns newempdbsub">
				<div class="dashboardPanal dashboardpanalHightOne">
					<div class="profile">
						<img src="../resources/images/tranBg.png" width="30" height="30"
							alt="User Profile">
					</div>

					<div class="dashboardPanalcontent marginTop5">
						<h2 class="noTopBorder">Profile Management</h2>
													<c:if
							test="<%=(session.getAttribute(\"adminLogin\") != null)%>">
								<div class="lableTextDashBoard">
									<p>
										<a
											href="<%=request.getContextPath()%>/employerRegistration/viewEmpAccountProfile.html"
											id="accountSettingpopUp">Account Settings</a>
									</p>
								</div>
							<div class="lableTextDashBoard">
										<p>
											<a id="accessPermissioPopUp"
												href="<%=request.getContextPath()%>/employer/manageAccessPermission.html">Manage
												Access Permissions</a>
										</p>
									</div>
								</c:if>
						
						<c:if
							test="<%=!(session
							.getAttribute(MMJBCommonConstants.FACILITY_POST_EDIT) != null)%>">
							<c:if
								test="<%=!(session
								.getAttribute(MMJBCommonConstants.FACILITY_FULL_ACCESS) != null)%>">
								<div class="lableTextDashBoard">
									<p>
										<a
											href="<%=request.getContextPath()%>/jobseekerregistration/jobSeekerChangePassword.html"
											id="changePassword">${msg.jsChangePwd}</a>

									</p>
								</div>
								<security:authorize
									access="!hasRole('ROLE_FACILITY_POST_EDIT') ">
								<div class="lableTextDashBoard">
									<p>
										<a
											href="<%=request.getContextPath()%>/employerRegistration/viewEmpAccountProfile.html"
											id="accountSettingpopUp">Account Settings</a>
									</p>
								</div>
								</security:authorize>
								<%-- <c:if
							test="${enableAccess == 'true' && enablePostEditAccess == 'true'}"> --%>
								<security:authorize
									access="!hasRole('ROLE_FACILITY_FULL_ACCESS') and !hasRole('ROLE_FACILITY_POST_EDIT') ">

									<div class="lableTextDashBoard">
										<p>
											<a id="accessPermissioPopUp"
												href="<%=request.getContextPath()%>/employer/manageAccessPermission.html">Manage
												Access Permissions</a>
										</p>
									</div>

								</security:authorize>
							</c:if>
						</c:if>
						<%-- </c:if> --%>
						<div class="lableTextDashBoard">
							<p>
								<a
									href="<%=request.getContextPath()%>/empProfile/employerprofile.html">Manage
									Featured Employer Profile</a>
							</p>
						</div>
						<security:authorize access="hasRole('ROLE_FACILITY_GROUP')">
							<div class="lableTextDashBoard">
								<p>
									<a id="manageFacilityPopUp"
										href="<%=request.getContextPath()%>/facility/updateFacilityDetail.html">Manage
										Facility List</a>
								</p>
							</div>
						</security:authorize>
<%-- 						<div class="FormErrorDisplayText">
							${error}<br /> <br />
						</div>
 --%>					</div>
				</div>
				<!---->
				<div class="dashboardPanal dashboardpanalHightTwo">
					<div class="jobPosting">
						<img src="../resources/images/tranBg.png" width="30" height="30"
							alt="Job Posting">
					</div>
					<div class="dashboardPanalcontent marginTop5">
						<h2 class="noTopBorder">Job Posting</h2>
						<%-- <c:if test="${ enablePostEditAccess eq 'true'}"> --%>
						<c:if
							test="<%=!(session
							.getAttribute(MMJBCommonConstants.FACILITY_POST_EDIT) != null)%>">
							<security:authorize access="!hasRole('ROLE_FACILITY_POST_EDIT')">
								<div class="lableTextDashBoard">
									<p>
										<a id="purchaseJobPostings"
											href="<%=request.getContextPath()%>/purchaseJobPosting/purchaseJobPostings.html">Purchase
											Job Postings</a>
									</p>
								</div>
							</security:authorize>
						</c:if>
						<div class="lableTextDashBoard">
							<p>
								<a
									href="<%=request.getContextPath()%>/employer/postNewJobs.html">Post
									New Job</a>
							</p>
						</div>
						<div class="lableTextDashBoard">
							<p>
								<a
									href="<%=request.getContextPath()%>/employer/manageJobPost.html">Manage
									/ Edit Job Postings</a>
							</p>
						</div>
						<div class="lableTextDashBoard">
							<p>


								<%-- 								<a href="<%=request.getContextPath()%>/brandingTemplates/manageBrandingTemplate.html" id="branding">Manage Job Posting Branding Templates</a> --%>

								<a
									href="<%=request.getContextPath()%>/brandingTemplates/employer/manageBrandingTemplate.html"
									id="manageBrandingTemplatePopup">Manage Job Posting
									Branding Templates</a>


							</p>
						</div>
						<div class="lableTextDashBoard">
							<p>
								<a
									href="<%=request.getContextPath()%>/inventory/employer/jobInventory.html?page=inventoryPage"
									id="postingInventory">Job Posting Inventory</a>
							</p>
						</div>
					</div>
				</div>
				<!---->
				<div class="comingSoon"></div>
				<div class="dashboardPanal dashboardpanalHight ">
					<div class="Applicants">
						<img src="../resources/images/tranBg.png" width="30" height="30"
							alt="User Profile">
					</div>
					<div class="dashboardPanalcontent marginTop5">
						<h2 class="noTopBorder">Manage Applicants</h2>
						<c:if
							test="<%=!(session
							.getAttribute(MMJBCommonConstants.FACILITY_POST_EDIT) != null)%>">
							<security:authorize access="!hasRole('ROLE_FACILITY_POST_EDIT')">
								<%-- 	<c:if test="${enablePostEditAccess eq 'true'}"> --%>
								<div class="lableTextDashBoard">
									<p>
										<a id="purchaseResumeSearch" title="Coming Soon">Purchase Resume Search
											Packages</a>
									</p>
								</div>
							</security:authorize>
						</c:if>
						<div class="lableTextDashBoard">
							<p>
								<a title="Coming Soon">Manage Job Seekers</a>
							</p>
						</div>
						<div class="lableTextDashBoard">
							<p>
								<a title="Coming Soon"> My Saved
									Resume Searches&nbsp;<%-- ${msg.commonOpenBrace}<c:out
										value="${employerDashBoardForm.resumeSearchCount}" />${msg.commonCloseBrace} --%>
								</a>
							</p>
						</div>
					</div>
				</div>
				<!---->
				<div class="dashboardPanal">
					<div class="subscriptions">
						<img src="../resources/images/tranBg.png" width="30" height="30"
							alt="Subscription">
					</div>
					<div class="dashboardPanalcontent marginTop5">
						<h2 class="noTopBorder">Subscriptions</h2>
						<div>
							<c:forEach items="${currentSubs}" var="subscription"
								varStatus="index">
								<tr>
									<td><c:out value="${subscription.optionName}" /></td>
								</tr>
								<br />
							</c:forEach>
						</div>
						<div class="lableTextDashBoard">
							<p>
								<a id="modifySubs"
									href="<%=request.getContextPath()%>/subscriptions/modifyFacilitySubscriptions.html">Modify
									Subscriptions</a>
							</p>
						</div>
					</div>
				</div>
				<!---->

			</div>
			<!--Right-->
			<div class="dBEmpRightColumns BorderLeft newempdb"> 
				<div class="dashboardPanal" style="border:none;" >
				<div class="borderTop"></div>
						<div class="activity" >
							<img src="../resources/images/tranBg.png" width="30" height="30">
						</div>
						<div class="empDBPanalTablecontent">
							<!--T-->
							<div class="rowEvenNewSpacing marginTop10">
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									class="grid marginTop3">
									<thead>
										<tr class="borderTopNone">
											<th width="46%" align="left" scope="col"><h2
													class="HeadTopBottomBorder">Metrics</h2> 
													<div id="postTypeToolTip" title="">
												<form:select class="jb_input3"
													path="selEmployer" items="${downDTOs}" itemValue="optionId"
													itemLabel="optionName" onchange="changeMetrics();">
												</form:select>
											</div>
												</th>
											<th width="18%" align="center" scope="col"
												class="BorderLeftWhite"><div class="EDPrice">VIEWS</div></th>
											<th width="18%" align="center" scope="col"
												class="BorderLeftWhite"><div class="EDPriceA">CLICKS</div></th>
											<th width="18%" align="center" scope="col"
												class="BorderLeftWhite"><div class="EDPriceB">APPLIES</div></th>
										</tr>
									</thead>
								</table>
								<br />
								<div id="metricsDetails"></div>
							</div>
							<!--T-->
							<form:form method="GET" action="" commandName="epform"
								id="empMetricsForm">
								<div class="rowBox EDPricec margin0 width100P height45" >
									<div class="floatLeft marginTop3">
										<strong>&nbsp;&nbsp;&nbsp;Date range</strong>
									</div>
									<div class="floatLeft marginTop3">&nbsp;&nbsp;&nbsp;From:</div>
									<div class="floatLeft">

										<div class="floatLeft">
											<!--  <input type="text" name="startDate" class="EDTextBox" id="startDate"/> -->
											
											<input type="text" name="startDate" class="EDTextBox" id="startDate" size="14"/> 
										</div>


										<div class="calender">
											<a href="#"><img src="../resources/images/tranBg.png"
												width="14" height="14" alt="Datepick"></a>
										</div>
									</div>
									<div class="floatLeft marginTop3 marginLeft25">To:</div>
									<div class="floatLeft">
										<div class="floatLeft">
											<!--  <input type="text" name="endDate" class="EDTextBox" id="endDate"/>  -->
											<input type="text" name="endDate" class="EDTextBox" id="endDate" size="14"/> 
											
										</div>
										<div class="calender">
											<a href="#"><img src="../resources/images/tranBg.png"
												width="14" height="14" alt="Datepick"></a>
										</div>
									</div>
									<div>
										<input type="button" name="SHOW" id="showMertics"
											class="orange cursor" value="SHOW" />
										<!-- <button type="button" class="orange" onclick="myFunction()">SHOW</button> -->
									</div>
									<div id="showMetricsErr" class="Error-ne"></div>

									<div class="floatLeft marginTop5 marginLeft15">
									<!-- Disable the link for phase 2A -->
										 <a href="../employer/getExcelSheet.html" id="metricsExcel" style="display: none;">Export</a> 
										
									</div>

								</div>
							</form:form>
							<!--T-->
							<div class="rowBox ">
								<div class="rowBox Padding0 AutoWidth AutoHeight with269 negativeMargin16">
									<div class="EDBoxMinW">
										<div class="EDBox02">
											<div class="row borderBottomDotted Height25">
											
												<p class="floatLeft">Available Job Postings</p>
												
												<p class="floatRight TextAlignR" id="avaQuantity">${avaQuantity}</p>
										
											</div>
											<div class="row marginTop10">
												<p class="floatLeft">Active Job Postings</p>
											
											<p class="floatRight TextAlignR" id="count">${count}</p> 
												
											</div>
										</div>
									</div>
									<div class=" clearfix"></div>
									<span class="FloatLeft"><a href="<%=request.getContextPath()%>/employer/manageJobPost.html">View Individual
											Job Posting Stats</a></span>
											
											
								</div>
								<!-- Funnel part: Code is commented for Phase 2A: -->
								<div style="display: none;"><div id="container">
								</div> </div>
							</div>

						</div>
					<div class=" rowBox Padding0">
						<div class="innerColumn marginRight10">
							<div class="dashboardPanal ">
								<div class="Alerts">
									<img src="../resources/images/tranBg.png" width="30"
										height="30" alt="Subscription">
								</div>
								<div class="dashboardPanalcontentTwo">
									<div class="dashboardPanalcontentTwo marginTop5">
										<h2 class="noTopBorder">Alerts</h2>
										<div class="lableTextDashBoard ">
											<p>
												<a
													href="<%=request.getContextPath()%>/alerts/employer/viewAlerts.html"
													id="viewAlertPopUp">View Alerts</a>
											</p>
										</div>
										<div class="lableTextDashBoard ">
											<p>
												<a
													href="<%=request.getContextPath()%>/alerts/employer/setAlerts.html"
													id="setAlertPopUp">Set Alerts</a>
											</p>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="innerColumn ">
							<div class="dashboardPanal ">
								<div class="Solutions">
									<img src="../resources/images/tranBg.png" width="30"
										height="30" alt="Subscription">
								</div>
								<div class="dashboardPanalcontentTwo marginTop5">
									<h2 class="noTopBorder">Solutions</h2>
									<div class="lableTextDashBoard">
										<p>
											<a target="_blank" href="${msg.employerMediaKitURL}"><em>ADVANCE </em>
												Online Media Kit</a>
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!---->

			<!---->

		</div>
	</form:form>
</body>
</html>