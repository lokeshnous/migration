jQuery(document).ready(function() {
				jQuery(".megamenu").megamenu();
				$(".saveThisPopup").displaypopup(".saveThisPopup","775","252");
				
				if($("#searchtypeToSavedSearch").val() == "basic"){
					
		            $("#keywords").val($("#keywordsToSavedSearch").val());
		            $("#cityState").val($("#cityStateToSavedSearch").val());
		            $("#radius").val($("#radiusToSavedSearch").val());
				}

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
			
			$.ajax({
				url : '../jobsearch/saveThisJob.html?id='+jobId,
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
							$.nmManual(val + '.html');

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
				url : '../jobsearch/applyJob.html?id='+jobId+'&currentUrl=null',
				data : ({
					userID : "userID"
				}),

				success : function(data) {
					$.each(data, function(key, val) {
						if (key == "applyLink") {
							window.open(val, '_blank');
						}
					});
					$.each(data, function(key, val) {
						if (key == "AjaxMSG") {
							$('#topjobActionInfo'+jobId).html(val);
						}
					});
					$.each(data, function(key, val) {
						if (key == "NavigationPath") {
							//$(applyJobidId).attr('href', val + '.html');
							window.location.href = val;
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
				var currentUrl = window.location.pathname;
				//alert(jobDesc.toLowerCase().indexOf("job description"));
				var saveThisJobIdid= "saveThisJobId"+jobId;
				var applyJobId= "applyJobid"+jobId;
				var sOut = '<div class="searchResultsSubContent">';	
				sOut += '<p class="searchResultsSubContentJobDescription"><div  style="height: 32px;overflow: hidden;"><span class="bold">Job Description:</span>'+jobDesc+'</div></p><br/>';
				sOut += '<div class="searchResultsSubContentButtonArea"><a onclick="applyThisJob('+jobId+');" class="btn_sm white" style=" cursor: default;" id="'+applyJobId+'">Apply</a>';
				sOut += '<a href="../jobsearch/viewJobDetails.html?id='+jobId+'&currentUrl='+currentUrl;
				sOut += '" class="btn_sm white">View Details</a>';
				sOut += '<a onclick="saveThisJob('+jobId+')" id="'+saveThisJobIdid+'" style=" cursor: default;"';
				sOut += '" class="btn_sm white">Save This Job</a></div>';
				if(isFeaturedEmployer){
					sOut += '<div class="featured_empButton"><a href=""><img src="../resources/images/FeaturedEmp.png" alt="featured emp Button" width="164" height="23"></a> </div>';
				}else{
					sOut += '<div class="featured_empButton"><a href=""><img src="../resources/images/tranBg.png" alt="featured emp Button" width="164" height="23"></a> </div>';
				}
				sOut += '';
				sOut += '<div class="searchResultsSubContentShare"><span class="marginTop5 floatLeft"> Send to Friend:&nbsp;</span><span><a href=""><img src="../resources/images/email.png"></a></span></div>';
				sOut += '<div class="searchResultsSubContentShare"><span class="marginTop5 floatLeft">Share:&nbsp;</span> <span><a href=""><img src="../resources/images/fbook_sm.png"></a></span> <span><a href=""><img src="../resources/images/L_In_sm.png"></a></span> <span><a href=""><img src="../resources/images/twitter_sm.png"></a></span></div>';
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
								        
								        }
								      },
									"bProcessing" : true,
									"sPaginationType" : "full_numbers",
									"bJQueryUI" : true,
									"bSort" : true,
									"iDisplayLength": 20,
									"aLengthMenu": [[20, 30, 40, 50], [20, 30, 40, 50]],
									"oLanguage" : {
										"sLengthMenu" : "<span>Results viewable: </span>_MENU_ <span>per page</span>",
										//"sZeroRecords" : "Nothing found - sorry",										
										"sInfo" : "_START_ - _END_ of _TOTAL_",
										
									},
									"sEmptyTable": "No results found",
									//"sDom": 'l<"pagination"p>t<"bottom"i>l<"pagination"pr><"clear">',
									"sDom": '<"searchResultsNavigation"<"searchResultsNavigationColumn1"l><"searchResultsNavigationColumn3"i><"searchResultsNavigationColumn2"p>>t<"searchResultsNavigation"<"searchResultsNavigationColumn1"l><"searchResultsNavigationColumn3"i><"searchResultsNavigationColumn2"pr>><"clear">',
									 //"sDom": 'T<"clear">lfrtip',
									//"sScrollY" : 500,
									'fnRowCallback' : function(nRow, aData, iDisplayIndex,iDisplayIndexFull) {
									// alert(aData['IsFeatured']);
									if (aData['IsFeatured'] == true) {
										
										$(nRow).addClass('featuredEmployerRowColor');
									}
									
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
								generateTable();
								var autoLoad = $("#autoload").val();
								
								if(autoLoad == true || autoLoad == "true"){	
									findJobs();
								
								}
								//$('#submitval').trigger('click');
								$("#submitval").click(function(event) {
									$("#errorMsg").html("");
									$("#autoload").val(false);
									if(!validateSearch()){
										return false;
									}
									//var x = $("#results").val();
									$("#rows").val(25000);
									$("#start").val("0");
									
									var keywords = $("#keywords").val();
									var cityState = $("#cityState").val();
									var radius = $("#radius").val();
									var rows = $("#rows").val();
									var start = $("#start").val();
									var searchtype = $("#searchtype").val();
									
									var navUrl =  "../jobsearch/searchJob.html?keywords="+keywords+"&cityState="
									+cityState+"&radius="+radius+"&rows="+rows+"&start="+start+"&searchtype="+searchtype;
									$("#TotalNoRecords").text("");
									$("#TotalRecord").text("");
									$.getJSON(navUrl,function(data) {
											table.fnClearTable();
											table.fnAddData(data.jsonRows);
											var nNodes = table.fnGetNodes();
											var count = 0;
											for(var i=0;i<nNodes.length;i++)
										        {
												if(i  != 0 && (i % 9) == 0){
												
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
				var url = "../jobsearch/findLocation.html?cityState="+cityState;
				$( "#cityState" ).autocomplete({
					source: url
				});
				
			}); 
			 
			 function findJobs() {
				 
				var autoLoad = $("#autoload").val();
				 if(autoLoad == true || autoLoad == "true"){
				 	$("#autoload").val(false);
					$("#rows").val(25000);
					$("#start").val("0");
					
					var keywords = $("#keywords").val();
					var cityState = $("#cityState").val();
					var radius = $("#radius").val();
					var rows = $("#rows").val();
					var start = $("#start").val();
					var searchtype = $("#searchtype").val();
					var navUrl =  "../jobsearch/searchJob.html?keywords="+keywords+"&cityState="
					+cityState+"&radius="+radius+"&rows="+rows+"&start="+start+"&searchtype="+searchtype;
					$("#TotalNoRecords").text("");
					$("#TotalRecord").text("");
					$.getJSON(navUrl,function(data) {
							table.fnClearTable();
							table.fnAddData(data.jsonRows);
							var nNodes = table.fnGetNodes();
							var count = 0;
							for(var i=0;i<nNodes.length;i++)
						        {
								if(i  != 0 && (i % 9) == 0){
								
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
				 }
				
			}

	