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
		
		
		<script type="text/javascript">
	jQuery(document).ready(
	window.onload = function() {
		loadMetricsDetails();
	});
	
	function loadMetricsDetails(){
		$.ajaxSetup({ cache: false });
		$.ajax({
			url : '${pageContext.request.contextPath}/agency/metricsDetails.html',
			data : ({}),
			
			success : function(data) {
			$("#metricsDetails").html(data);
			},
			error : function(data) {
			},
			complete : function(data) {
				// do nothing for now.
			}
		}
		);
	}
	 function changeMetrics(){
		 $(".EDTextBox").val("");
		var selEmployerId = $("#selEmployer").val();
		$.ajax({url:"${pageContext.request.contextPath}/agency/viewFacilityMetrics.html?facilityId="+selEmployerId,
			data:$('#selEmployerId').serialize(),
			type:"GET",
			success: function(data) {			
		loadMetricsDetails();
			 },
				error : function(data) {
				}
		});
	} 
</script>


<script type="text/javascript">
var chart;
var options = {
    chart: {
        renderTo: 'container',
        defaultSeriesType: 'funnel',
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
                enabled: true, 
                connectorWidth: 0,
        		connectorColor: '#FFFFFF',
                formatter: function() {
                    return '<b>'+ this.point.name +'</b> ('+ Highcharts.numberFormat(this.point.y, 0) +')';
                },
                color: 'black'
            }
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
				
				$("#metricsExcel")
				.click(
						function() {
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
								//$("#container").css("width", "550px");
								//$("#container").css("text-align", "center");
								//$("#container").css("color", "#FF0000");
								return false;
							}
							var convStartDate = new Date(startDate);
					        var convEndDate = new Date(endDate);
					        if(convEndDate < convStartDate){
					        	$("#showMetricsErr").html("Please enter end date greater than start date!");
								//$("#container").css("width", "550px");
								//$("#container").css("text-align", "center");
								//$("#container").css("color", "#FF0000");
								return false;
					        }
							var selEmployerId = $(
									"#selEmployer").val();
									$.ajax({
										url : "${pageContext.request.contextPath}/employer/showMertics.html?startDate="
												+ startDate
												+ "&endDate="
												+ endDate
												+ "&selEmployerId="
												+ selEmployerId,
										success : function(data) {
											loadMetricsDetails();
										},
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
			}
		});
	}
	
	
	function getActiveAndAvailJbPosting(selEmployerId) {
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
	
</script>

		</head>
		<body class="job_board">
        <div id="jobSeekerRegister1" class="job_seeker_login popUpContainer" style="display:block">
          <div class="popupHeader">
            <h2>EMPLOYER DETAILS</h2>
            <a href="#"><img src="../resources/images/Close.png" class="nyroModalClose" width="19" height="19" alt=""></a></div>
          <div class="popUpContainerWrapper">
   <form:form commandName="metricsForm" id="empMetrics">
              <div class="rowEvenNewSpacing marginTop0"><div class="FloatLeft"> 
                <!--T-->
                <div class="row">
               <c:if test="${not empty employerDetails.logoPath}">
                <div class="floatLeft"><img src="<%=request.getContextPath()%>/agency/viewImage.html?path=${employerDetails.logoPath}" width="204" height="50" alt="logo"></div>
               </c:if>
               <c:if test="${empty employerDetails.logoPath}">
                <div class="floatLeft" style="width:204px;height:50px;"></div>
               </c:if>
                <div class="floatRight marginTop20"><a href="<%=request.getContextPath()%>/agency/impersonateAgencyToFacility.html?facilityId=${employerDetails.facilityId}">Go To ${employerDetails.name} Dashboard</a> </div>
                
                </div>
                        <div class="rowEvenNewSpacing marginTop10">
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									class="grid marginTop3">
									<thead>
										<tr class="borderTopNone">
											<th width="46%" align="left" scope="col"><h2
													class="HeadTopBottomBorder">Metrics</h2> 
												<form:select
													class="jb_input3" path="selEmployer" items="${downDTOs}"
													itemValue="optionId" itemLabel="optionName" onchange="changeMetrics();">
												</form:select>
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
									<br/>
									<div id="metricsDetails" ></div>								
							</div>
                        <form:form method="GET" action="" commandName="epform"
								id="empMetricsForm">
								<div class="rowBox EDPricec">
									<div class="floatLeft marginTop3">
										<strong>&nbsp;&nbsp;&nbsp;Date range</strong>
									</div>
									<div class="floatLeft marginTop3">&nbsp;&nbsp;&nbsp;From:</div>
									<div class="floatLeft">

										<div class="floatLeft">
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
											<input type="text" name="endDate" class="EDTextBox" id="endDate" size="14"/> 
											
										</div>
										<div class="calender">
											<a href="#"><img src="../resources/images/tranBg.png"
												width="14" height="14" alt="Datepick"></a>
										</div>
									</div>
									<div>
										<input type="button" name="SHOW" id="showMertics"
											class="orange" value="SHOW" />
									</div>
									<div id="showMetricsErr" class="Error-ne"></div>
									
									<div class="floatLeft marginTop5 marginLeft15">
									<!-- commented the  code for phase 2A -->
										<a href="../employer/getExcelSheet.html" id="metricsExcel" style="display: none;">Export</a>
										
									</div>

								</div>
							</form:form>
                       
                        <div class="rowBox">
								<div class="rowBox Padding0 AutoWidth AutoHeight">
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
								</div>
								<!-- commented the  code for phase 2A -->
								<div style="display: none;"><div id="container" style="height: 250px; width: 290px"></div></div>
							</div>
              
              </div></div>
            </form:form>
          </div>
          <div class="clearfix"></div>
        </div>
</body>
</html>