<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
		<head>
    	<!-- <link href="../resources/css/jquery.dataTables.css" rel="stylesheet" type="text/css">
 	    <link href="../resources/css/jquery-ui.css" rel="stylesheet" type="text/css">
    	<script src="../resources/js/jquery.dataTables.nightly.js"></script>
    	<script src="../resources/js/jquery.dataTables.mm.js"></script>
    	<script type="text/javascript" language="javascript" src="./media/js/jquery.js"></script>
    	        JAVASCRIPT FILES
		<script type="text/javascript" src="../resources/js/slider.js"></script>
 	    <link href="../resources/css/jquery.dataTables.css" rel="stylesheet" type="text/css">
 	    <link href="../resources/css/jquery-ui.css" rel="stylesheet" type="text/css">
    	<script type="text/javascript" language="javascript" src="/media/js/jquery.js"></script>
    	<script src="../resources/js/jquery.dataTables.nightly.js"></script> -->

		<!-- <script type="text/javascript">
			jQuery(document).ready(function() {
				jQuery(".megamenu").megamenu();
				//$(".saveThisPopup").displaypopup(".saveThisPopup","775","252");

			});
		</script> -->
	<!-- <style type="text/css">
	.advertiseStyle{
		border: 2px solid white;
		background-image : url(../resources/images/ads/banner_ad_fpo.png);
		}
	.details{
	border: 2px solid;
	}
	</style> -->
<!-- 			<script type="text/javascript">
		
		function validateRadius() {
			var cityState = $.trim($("#cityState").val());
			var radius = $.trim($("#radius").val());
			if(radius.length != 0 && cityState.length == 0){
				//$("#radius").val("");
				$('#findSearchInfo').html('Please enter the City and State or Zip Code');
			}else{
				$('#findSearchInfo').html('');
			}
		}
		function validateSearch() {
			var cityState = $.trim($("#cityState").val());
			var radius = $.trim($("#radius").val());
			var keywords = $.trim($("#keywords").val());
			var status = true;
			if(keywords.length == 0){
				status = false;
				$('#findSearchInfo').html('Please enter the \"Job Title, Keyword, Job Id, Company Name\" to perform a search.');
			}else if(radius.length != 0 && cityState.length == 0){
				status = false;
				$('#findSearchInfo').html('Please enter the City and State or Zip Code.');
			}else if(cityState.length != 0 && radius.length == 0){
				status = false;
				$('#findSearchInfo').html('Please select a mileage range to search within');
			}else{
				$('#findSearchInfo').html('');
			}
			return status;
		}
		function saveThisJob(jobId) {
			var saveThisJobIdid = "#saveThisJobId"+jobId;
			$.ajax({
				url : 'saveThisJob.html?id='+jobId,
				data : ({
					userID : "userID"
				}),
				success : function(data) {
					$.each(data, function(key, val) {
						if (key == "AjaxMSG") {
							$('#topjobActionInfo'+jobId).html(val);
						}
					});
					$.each(data, function(key, val) {
						if (key == "NavigationPath") {
							$(saveThisJobIdid).attr('target', '_blank');
							$(saveThisJobIdid).attr('href', val + '.html');
							$(saveThisJobIdid).displaypopup(saveThisJobIdid,
									"775", "252");

						}
					});
				},
				error : function(data) {
					alert('Unable to process');
				},
				complete : function(data) {
				}
			});
		}
		function applyThisJob(jobId) {
			$.ajax({
				url : 'applyJob.html?id='+jobId,
				data : ({
					userID : "userID"
				}),

				success : function(data) {
					$.each(data, function(key, val) {
						if (key == "AjaxMSG") {
							$('#topjobActionInfo'+jobId).html(val);
						}
					});
					$.each(data, function(key, val) {
						if (key == "NavigationPath") {
							$.ajax({
								url : val + '.html',
								data : ({
									userID : "userID"
								}),
								success : function(data) {
									$.each(data, function(key, val) {
										if (key == "AjaxMSG") {
											$('#topjobActionInfo'+jobId).html(val);
										}
									});
									$.each(data, function(key, val) {
										if (key == "NavigationPath") {
											$(saveThisJobIdid).attr('target', '_blank');
											$(saveThisJobIdid).attr('href', val + '.html');
											$(saveThisJobIdid).displaypopup(saveThisJobIdid,
													"775", "252");

										}
									});
								},
								error : function(data) {
									alert('Unable to process');
								},
								complete : function(data) {
								}
							});
						}
					});
				},
				error : function(data) {
					alert('Unable to process');
				},
				complete : function(data) {
				}
			});
		}
			function fnFormatDetails(table, nTr) {
				var aData = table.fnGetData(nTr);
				//alert('--' + aData['Company']+aData['JobId']);
				var jobId = aData['JobId'];
				var jobDesc = aData['AdText'];
				var saveThisJobIdid= "saveThisJobId"+jobId;
				var sOut = '<div class="searchResultsSubContent">';	
				sOut += '<p class="searchResultsSubContentJobDescription"><span class="bold">Job Description:</span>'+jobDesc+'</p><br/>';
				sOut += '<a onclick="applyThisJob('+jobId+');" class="btn_sm white" style=" cursor: default;">';
				sOut += 'Apply</a>';
				sOut += '<a href="viewJobDetails.html?id='+jobId;
				sOut += '" class="btn_sm white">View Details</a>';
				sOut += '<a onclick="saveThisJob('+jobId+')" id="'+saveThisJobIdid+'" style=" cursor: default;"';
				sOut += '" class="btn_sm white">Save This Job</a>';
				sOut += '<div class="featured_empButton"><a href=""><img src="../resources/images/FeaturedEmp.png" alt="featured emp Button" width="164" height="23"></a> </div>';
				sOut += '<br/><br/>';
				sOut += '<span class="marginTop3 floatLeft"> Send to Friend:&nbsp;</span><span><a href=""><img src="../resources/images/email.png"></a></span>';
				sOut += '<span class="marginTop3 floatLeft">Share:&nbsp;</span> <span><a href=""><img src="../resources/images/fbook_sm.png"></a></span> <span><a href=""><img src="../resources/images/L_In_sm.png"></a></span> <span><a href=""><img src="../resources/images/twitter_sm.png"></a></span>';
				sOut += '<h4><div style="color: red" id="topjobActionInfo'+jobId+'" ></div></h4>';
				sOut += '</div>';
				return sOut;
			}
			var table;

			function generateTable() {
				table = $("#jsonTable")
						.dataTable(
								{
									"bFilter" : false,
								    "fnCreatedRow": function( nRow, aData, iDataIndex ) {
								        if (iDataIndex  != 0 && (iDataIndex % 10) == 0)
								        {
								        //alert(iDataIndex+aData['City']+nRow+"--"+$('td:eq(4)', nRow));
								        //nRow
								        //$(this).fnOpen(nRow,'<tr><td>pppppppppppppppppppppp</td></tr>','details');
								        //table.fnOpen(nTr,fnFormatDetails(table,nTr),'details');
								        }
								      },
									"bProcessing" : true,
									"sPaginationType" : "full_numbers",
									"bJQueryUI" : true,
									"bSort" : true,
									"iDisplayLength": 25,
									"oLanguage" : {
										"sLengthMenu" : "Results viewable: _MENU_ per page",
										"sZeroRecords" : "No Records found",
										"sInfo" : "",
										//"sInfo" : "Showing _START_ to _END_ of _TOTAL_ records",
										//"sInfoEmpty" : "Showing 0 to 0 of 0 records",
										//"sInfoFiltered" : "(filtered from _MAX_ total records)"
									},
									"sDom": 'l<"pagination"p>t<"bottom"i>l<"pagination"pr><"clear">',
									 //"sDom": 'T<"clear">lfrtip',
									"sScrollY" : 500,
									"aoColumns" : [ {
										"mDataProp" : "JobTitle","bSortable": "false"
									}, {
										"mDataProp" : "Company","bSortable": "false"
									}, {
										"mDataProp" : "City","bSortable": "false"
									}, {
										"mDataProp" : "PostedDate","bSortable": "true"
									} ]
								});
			};

			$(document)
					.ready(
							function() {
								$(".megamenu").megamenu();
								
								$("#submitval").click(function(event) {
									if(!validateSearch()){
										return false;
									}
									var x = $("#results").val();
									$('.otherContent').attr('style','display:none;');
									$('.searchContent').attr('style',' display: block;');
									$("#rows").val(12000);
									$("#start").val("0");
									var keywords = $("#keywords").val();
									var cityState = $("#cityState").val();
									var radius = $("#radius").val();
									var rows = $("#rows").val();
									var start = $("#start").val();
									var navUrl =  "../jobsearchactivity/findJobSearch.html?keywords="+keywords+"&cityState="
									+cityState+"&radius="+radius+"&rows="+rows+"&start="+start;
									$("#TotalNoRecords").text("");
									//alert("navUrl="+navUrl);
									$.getJSON(navUrl,function(data) {
											table.fnClearTable();
											table.fnAddData(data.jsonRows);
											var nNodes = table.fnGetNodes();
											var count = 0;
											for(var i=0;i<nNodes.length;i++)
										        {
												if(i  != 0 && (i % 9) == 0){
												//nNodes[i+count] = 	 "<center><br><br>-----------------<b>Advertise"+(count+1)+" Here</b>-----------------<br><br></center>";
												//$('#jsonTable').dataTable().fnAddData(["row 3, cell 1", "row 3, cell 2","row 3, cell 1", "row 3, cell 2"]);
												//table.fnOpen( nNodes[i+count], "<center><br><br>-----------------<b>Advertise"+(count+1)+" Here</b>-----------------<br><br></center>", "advertiseStyle" );
												table.fnOpen( nNodes[i+count], "<img src='../resources/images/ads/banner_ad_fpo.png'>", "advertiseStyle" );
												count = count+1;
												}
										        }
											$("#TotalNoRecords").text(data.TotalNoRecords);
											});
									return true;
									
								});
								generateTable();
								/* Add event listener for opening and closing details
								 * Note that the indicator for showing which row is open is not controlled by DataTables,
								 * rather it is done here
								 */
								$('#jsonTable tbody tr')
										.live(
												'click',
												function() {
													var nTr = this;
													if ($(this).attr('popup')) {
													} else {
														$(this).attr('popup',
																'show');
													}

													if ($(this).attr('popup')
															.match('show')) {
														$(this).attr('popup',
																'hide');
														table
																.fnOpen(
																		nTr,
																		fnFormatDetails(
																				table,
																				nTr),
																		'details');
													} else {
														$(this).attr('popup',
																'show');
														table.fnClose(nTr);
													}
												});
							});
		</script> -->
	
</head>
		<body class="job_board">

              <div class="mainTwo" >
              <div class="row">
                            <div class="job_search_main job_search_main_height" style="margin-right: 10px;">
                  <%-- <form method=""> --%>
                 <%--  <form:form method="GET" action="findJobSearch.html" commandName="jobSearchResultForm">  --%>
                  <form:form method="" action="" commandName="jobSearchResultForm"> 
            <div class="search_form">
            
	                      <h1 class="marginBottom5">Search <span id="TotalNoRecords"></span> Healthcare Jobs</h1>
	                      <form:input path="keywords" maxlength="60" id="keywords" cssClass="jb_input1" />
	                      <div class="toolTipBefore"><label for="keywords">Job Title, Keywords, Job ID, Company Name </label></div> <div class="toolTip"><span class="classic"><p>Type in your search criteria here. Include any group of terms related to your desired position. Click on 'Advanced Search' below for more options.</p></span></div>
	                      <br/>
	                      <div class="input_grp1 marginTop10">
	                       <form:input path="cityState"  id="cityState" cssClass="jb_input2" />
	                	  <!-- <input type="text" name="cityState" id="cityState" class="jb_input2" /> -->
	                <br/>
	                <div class="toolTipBefore"><label for="cityState">City and State or ZIP Code </label></div> <div class="toolTip"><span class="classic"><p>Enter the city and state or zip code of the location you want to search. Then select a radius to expand your search up to 100 miles from your starting point.</p></span></div>
	              </div>
	                      <div class="input_grp2 marginTop10">
	                <form:select path="radius" id="radius" cssClass="jb_input3" onchange="validateRadius();">
	                	<form:option label="--" value=""/>
	                	<!-- USE <form:options/> while dynamically populating the values  -->
	                	<form:option label="5 Miles" value="5"/>
	                	<form:option label="10 Miles" value="10"/>
	                	<form:option label="25 Miles" value="25"/>
	                	<form:option label="50 Miles" value="50"/>
	                	<form:option label="100 Miles" value="100"/>
	                </form:select>
	                <label for="radius">Radius</label>
	              </div>
	              
	              <div class="clearfix"></div>
	                      <!-- <a href="#" class="btn_sm orange jb_search_submit">Find Jobs</a> -->
	                      <div style="color: red;font-weight:bold; height: 30px;" id="findSearchInfo" ></div>
	                    <input type="button" id= "submitval" value="Find Jobs" class="btn_sm orange jb_search_submit" />
	                    <!-- <input type="submit" id= "submit" value="Find Jobs" class="btn_sm orange jb_search_submit" /> -->
             <%-- </form:form>     --%>  
                      
                      <a href="../jobsearchactivity/advanceSearch.html">Advanced Search</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">Post Your Resume</a></div>
            <!-- search_form -->

					<security:authorize access="!hasRole('ROLE_JOB_SEEKER')">
						<div class="search_info_box1">
							<p class="search_message">
								JOIN THE <span>ADVANCE</span> NETWORK
							</p>
							<ul>
								<li>Apply to jobs faster</li>
								<li>Post a resume to be found by registered employers</li>
								<li>Create a Job Alert and more for free</li>
							</ul>
							<a href="">Create an Account</a>
						</div>
					</security:authorize>
					<!-- search_info_box1 -->
					<security:authorize access="hasRole('ROLE_JOB_SEEKER')">
						<div class="search_info_box1">
							<div class="rowPadding borderBottomDotted">
								<span class="rowEvenSpacing capsText">MY RECENT SEARCHES:</span><br>
								<a href="#">Clear All</a> | <a href="#">See All</a>
							</div>
							<div class="rowPadding borderBottomDotted">
								May 16, 2012, 7:00am<br> Search by: <a href="#">Physical
									Therapist / Philadelphia, PA</a>
							</div>
							<div class="rowPadding borderBottomDotted">
								May 16, 2012, 7:00am<br> Search by: <a href="#">Physical
									Therapist / Philadelphia, PA</a>
							</div>
							<div class="rowPadding">
								May 16, 2012, 7:00am<br> Search by: <a href="#">Physical
									Therapist / Philadelphia, PA</a>
							</div>
						</div>
						<!-- search_info_box1 -->
					</security:authorize>

					<div class="search_info_box2">
                      <ul>
                <li><span class="uppercase bold">My Recent Searches:</span><br/>
                          <a href="">Clear All</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">See All</a></li>
                <li>May 16, 2012, 7:00 am<br/>
                          Search by: <span class="jb"><a href="">Physical Therapist</a></span></li>
                <li>May 17, 2012, 8:00 am<br/>
                          Search by: <span class="jb"><a href="">Pediatric Nurse</a></span></li>
                <li class="last">May 18, 2012, 9:00 am<br/>
                          Search by: <span class="jb"><a href="">ER Nurse</a></span></li>
              </ul>
                    </div>
            <!-- search_info_box2 -->
            
            <div class="browse_bar bold"> <span>BROWSE JOBS:</span>&nbsp;&nbsp;<a href="">By Job Title</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">By Employer</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">By Location</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">By Employment Type</a> </div>

            <!-- browse_bar -->
            
            <form:hidden path="start" id="start"/>
            <form:hidden path="rows" id="rows"/>
            
            
            </form:form>    
                <%-- </form> --%>
                </div>
              <div class="ad_col_right"> <img src="../resources/images/ads/300x250ad1.png" class="paddingBottom0" />
        
              </div></div>

    </div>
    <!-- main --> 


<!-- footer_wrapper -->

</body>
</html>