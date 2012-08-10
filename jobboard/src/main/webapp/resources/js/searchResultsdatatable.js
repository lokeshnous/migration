jQuery(document).ready(function() {
				jQuery(".megamenu").megamenu();
				$(".saveThisPopup").displaypopup(".saveThisPopup","775","252");

			});

function validateRadius() {
			var cityState = $.trim($("#cityState").val());
			var radius = $.trim($("#radius").val());
			
			if(radius != 0 && cityState.length == 0){
				//$("#radius").val("");
				table.fnClearTable();
				$("#TotalNoRecords").text("");
				$("#TotalRecord").text("");
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
				table.fnClearTable();
				$("#TotalNoRecords").text("");
				$("#TotalRecord").text("");
				status = false;
				$('#findSearchInfo').html('Please enter the \"Job Title, Keyword, Job Id, Company Name\" to perform a search.');
			}else if(radius != 0 && cityState.length == 0){
				table.fnClearTable();
				$("#TotalNoRecords").text("");
				$("#TotalRecord").text("");
				status = false;
				$('#findSearchInfo').html('Please enter the City and State or Zip Code.');
			}else{
				$('#findSearchInfo').html('');
			}
			return status;
		}
		function saveThisJob(jobId) {
			//var saveThisJobIdid = "#saveThisJobId"+jobId;
			//alert(saveThisJobIdid);
			$.ajax({
				url : '../jobsearchactivity/saveThisJob.html?id='+jobId,
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
							//$(saveThisJobIdid).attr('target', '_blank');
							//$(saveThisJobIdid).attr('href', val + '.html');
							//$(saveThisJobIdid).displaypopup(saveThisJobIdid,
								//	"775", "252");
							$.nmManual(val + '.html');
							//location.href = val+".html";

						}
					});
					//$.each(data, function(key, val) {
					//	if (key == "NavigationPath") {
					//		window.location.href = val+".html";
					//	}
					//});
				},
				error : function(data) {
					alert('Unable to process');
				},
				complete : function(data) {
				}
			});
		}
		function applyThisJob(jobId) {
			//var applyJobidId = "#applyJobid"+jobId;
			$.ajax({
				url : '../jobsearchactivity/applyJob.html?id='+jobId,
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
							//$(applyJobidId).attr('href', val + '.html');
							window.location.href = val+".html";
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
				var isFeaturedEmployer = aData['IsFeatured'];
				//alert(jobDesc.toLowerCase().indexOf("job description"));
				var saveThisJobIdid= "saveThisJobId"+jobId;
				var applyJobId= "applyJobid"+jobId;
				var sOut = '<div class="searchResultsSubContent">';	
				sOut += '<p class="searchResultsSubContentJobDescription"><div  style="height: 30px;overflow: hidden;"><span class="bold">Job Description:</span>'+jobDesc+'</div></p><br/>';
				sOut += '<div class="searchResultsSubContentButtonArea"><a onclick="applyThisJob('+jobId+');" class="btn_sm white" style=" cursor: default;" id="'+applyJobId+'">Apply</a>';
				sOut += '<a href="../jobsearchactivity/viewJobDetails.html?id='+jobId;
				sOut += '" class="btn_sm white">View Details</a>';
				sOut += '<a onclick="saveThisJob('+jobId+')" id="'+saveThisJobIdid+'" style=" cursor: default;"';
				sOut += '" class="btn_sm white">Save This Job</a></div>';
				if(isFeaturedEmployer){
					sOut += '<div class="featured_empButton"><a href=""><img src="../resources/images/FeaturedEmp.png" alt="featured emp Button" width="164" height="23"></a> </div>';
				}else{
					sOut += '<div class="featured_empButton1"><a href=""><img src="../resources/images/tranBg.png" alt="featured emp Button" width="164" height="23"></a> </div>';
				}
				sOut += '';
				sOut += '<div class="searchResultsSubContentShare"><span class="marginTop3 floatLeft"> Send to Friend:&nbsp;</span><span><a href=""><img src="../resources/images/email.png"></a></span></div>';
				sOut += '<div class="searchResultsSubContentShare"><span class="marginTop3 floatLeft">Share:&nbsp;</span> <span><a href=""><img src="../resources/images/fbook_sm.png"></a></span> <span><a href=""><img src="../resources/images/L_In_sm.png"></a></span> <span><a href=""><img src="../resources/images/twitter_sm.png"></a></span></div>';
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
									"iDisplayLength": 30,
									 "aLengthMenu": [[20, 30, 40, 50], [20, 30, 40, 50]],
									"oLanguage" : {
										"sLengthMenu" : "<span>Results viewable: </span>_MENU_ <span>per page</span>",
										//"sZeroRecords" : "Nothing found - sorry",
										"sInfo" : "",
										//"sInfo" : "Showing _START_ to _END_ of _TOTAL_ records",
										//"sInfoEmpty" : "Showing 0 to 0 of 0 records",
										//"sInfoFiltered" : "(filtered from _MAX_ total records)"
									},
									"sEmptyTable": "No results found",
									//"sDom": 'l<"pagination"p>t<"bottom"i>l<"pagination"pr><"clear">',
									"sDom": '<"searchResultsNavigation"<"searchResultsNavigationColumn1"l><"searchResultsNavigationColumn3"><"searchResultsNavigationColumn2"p>>t<"bottom"i><"searchResultsNavigation"<"searchResultsNavigationColumn1"l><"searchResultsNavigationColumn2"pr>><"clear">',
									 //"sDom": 'T<"clear">lfrtip',
									//"sScrollY" : 500,
									'fnRowCallback' : function(nRow, aData, iDisplayIndex,iDisplayIndexFull) {
									// alert(aData['IsFeatured']);
									if (aData['IsFeatured'] == true) {
										//$('td:eq(0)', nRow).addClass('featuredEmployerRowColor');
										//$('td:eq(1)', nRow).addClass('featuredEmployerRowColor');
										//$('td:eq(1)', nRow).addClass('featuredEmployerRowColor');
										//$('td:eq(2)', nRow).addClass('featuredEmployerRowColor');
										//$('td:eq(3)', nRow).addClass('featuredEmployerRowColor');
										$(nRow).addClass('featuredEmployerRowColor');
									}
									//else{
										//$('td:eq(0)',nRow).addClass('leftCornerBorder');
										//$('td:eq(1)',nRow).addClass('middleBorder');
										//$('td:eq(2)',nRow).addClass('middleBorder');
										//$('td:eq(3)',nRow).addClass('rightCornerBorder');
									//}
										return nRow;
									},
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
									$("#rows").val(25000);
									$("#start").val("0");
									var keywords = $("#keywords").val();
									var cityState = $("#cityState").val();
									var radius = $("#radius").val();
									var rows = $("#rows").val();
									var start = $("#start").val();
									var navUrl =  "../jobsearchactivity/findJobSearch.html?keywords="+keywords+"&cityState="
									+cityState+"&radius="+radius+"&rows="+rows+"&start="+start;
									$("#TotalNoRecords").text("");
									$("#TotalRecord").text("");
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
											$("#TotalRecord").text(data.TotalNoRecords);
											/* $("#SearchCriteria").text(keywords); */
											});
									$(".otherContent").attr("style","display: none");
									$(".searchContent").attr("style","display: block");
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
														if ($(this).find('td').attr('class')
																.match('details')) {
														}else{
															$(this).addClass('row_selected'); 
															$('td:eq(0)',$(this)).addClass('leftCornerBorder');
															$('td:eq(1)',$(this)).addClass('middleBorder');
															$('td:eq(2)',$(this)).addClass('middleBorder');
															$('td:eq(3)',$(this)).addClass('rightCornerBorder');
														}
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
														$(this).removeClass('row_selected');
														$('td:eq(0)',$(this)).removeClass('leftCornerBorder');
														$('td:eq(1)',$(this)).removeClass('middleBorder');
														$('td:eq(2)',$(this)).removeClass('middleBorder');
														$('td:eq(3)',$(this)).removeClass('rightCornerBorder');
													
														table.fnClose(nTr);
													}
												});
								
								
								
							});
			
			
			 $(document).ready(function() {
				var cityState = $("#cityState").val();
				var url = "../jobsearchactivity/findLocation.html?cityState="+cityState;
				//alert(url);
				$( "#cityState" ).autocomplete({
					source: url
				});
				
			}); 

	