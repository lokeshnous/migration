jQuery(document).ready(function() {
				jQuery(".megamenu").megamenu();
				$(".saveThisPopup").displaypopup(".saveThisPopup","775","252");
				$(".sendtofriend").displaypopup(".sendtofriend","775","252");
				if($("#searchtypeToSavedSearch").val() == "basic"){
					
		            $("#keywords").val($("#keywordsToSavedSearch").val());
		            $("#cityState").val($("#cityStateToSavedSearch").val());
		            $("#radius").val($("#radiusToSavedSearch").val());
				}

			});


			function sendToFrd(jobId) {	
				var currentUrl = window.location.pathname;
				$.nmManual('../jobsearch/sendtofriend.html?id='+jobId+'&currentUrl='+currentUrl);
			}

		function validateRadius() {
			var cityState = $.trim($("#cityState").val());
			var radius = $.trim($("#radius").val());
			
			if(radius != 0 && cityState.length == 0){
				$("#TotalNoRecords").text("");
				$("#TotalRecord").text("");
				$('#findSearchInfo').html('Please enter the City and State or Zip Code');
			}else{
				$('#findSearchInfo').html('');
			}
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
							$('#topjobActionInfo').html(val);
							$('#bottomjobActionInfo').html('');
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
				url : '../jobsearch/applyJob.html?id='+jobId+'&currentUrl=null&clickType=apply',
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
							$('#topjobActionInfo').html(val);
							$('#bottomjobActionInfo').html('');
						}
					});
					$.each(data, function(key, val) {
						if (key == "NavigationPath") {
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

			var keywords ;
			var cityState ;
			var radius ;
			var rows ;
			var start ;
			var searchtype ;
			
			$(document)
					.ready(
							function() {
								$(".megamenu").megamenu();
								var autoLoad = $("#autoload").val();
								if(autoLoad == true || autoLoad == "true"){	
									findJobs();
								
								}
								$("#submitval").click(function(event) {
									$("#errorMsg").html("");
									$("#autoload").val(false);
									$('#findSearchInfo').html("");
									$("#rows").val(25000);
									$("#start").val("0");
									
									keywords = $("#keywords").val();
									cityState = $("#cityState").val();
									radius = $("#radius").val();
									rows = $("#rows").val();
									start = $("#start").val();
									searchtype = $("#searchtype").val();
									
									var navUrl =  "../jobsearch/searchJob.html?keywords="+keywords+"&cityState="
									+cityState+"&radius="+radius+"&rows="+rows+"&start="+start+"&searchtype="+searchtype;
									$("#TotalNoRecords").text("");
									$("#TotalRecord").text("");
									$.getJSON(navUrl,function(data) {
										 $.ajaxSetup({ cache: true });
										$.each(data, function(key, val) {
											if (key == "AjaxMSG") {
												$('#findSearchInfo').html(val);
											}
										});										
										processPaginationReq();
										$("#TotalNoRecords").text(data["TotalNoRecords"]);
										$("#TotalRecord").text(data["TotalNoRecords"]);
									});
									$(".otherContent").attr("style","display: none");
									$(".searchContent").attr("style","display: block");
									
									return true;
									
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
					
					keywords = $("#keywords").val();
					cityState = $("#cityState").val();
					radius = $("#radius").val();
					rows = $("#rows").val();
					start = $("#start").val();
					searchtype = $("#searchtype").val();
					var navUrl =  "../jobsearch/searchJob.html?keywords="+keywords+"&cityState="
					+cityState+"&radius="+radius+"&rows="+rows+"&start="+start+"&searchtype="+searchtype;
					$("#TotalNoRecords").text("");
					$("#TotalRecord").text("");
					$.getJSON(navUrl,function(data) {
							processPaginationReq();
							$("#TotalNoRecords").text(data["TotalNoRecords"]);
							$("#TotalRecord").text(data["TotalNoRecords"]);
							});
					$(".otherContent").attr("style","display: none");
					$(".searchContent").attr("style","display: block");
					
					return true;
				 }
				
			}
			 
				 function postYourResume() {
					$.ajax({
						url : '../jobsearch/jobseekerPostYourResume.html',
						data : ({
							userID : "userID"
							
						}),
						success : function(data) {
							$.each(data, function(key, val) {
								if (key == "LoggedInNavigationPath") {
									window.location.href = val;
								}
							});
							$.each(data, function(key, val) {
								if (key == "NavigationPath") {
									$.nmManual(val);
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

				function viewJobDetails(jobId) {
					window.location.href = "../jobsearch/viewJobDetails.html?id="
							+ jobId
							+ "&currentUrl="
							+ window.location.pathname
							+ "&clickType=view";
				}
				
				function getNextPage(page) {
					var navUrl =  "../jobsearch/searchJob.html?keywords="+keywords+"&cityState="
					+cityState+"&radius="+radius+"&rows="+rows+"&start="+start+"&searchtype="+searchtype
					+"&page="+page;
					$.getJSON(navUrl, function(data) {
						processPaginationReq();
					});
					
				}
				
				function getNextPages(page, begin) {
					var navUrl =  "../jobsearch/searchJob.html?keywords="+keywords+"&cityState="
					+cityState+"&radius="+radius+"&rows="+rows+"&start="+start+"&searchtype="+searchtype
					+"&page="+page+"&next="+begin;
					$.getJSON(navUrl, function(data) {
						processPaginationReq();
					});
					
				}
				
				function getPrevPages(page, begin) {
					var navUrl =  "../jobsearch/searchJob.html?keywords="+keywords+"&cityState="
					+cityState+"&radius="+radius+"&rows="+rows+"&start="+start+"&searchtype="+searchtype
					+"&page="+page+"&next="+begin;
					$.getJSON(navUrl, function(data) {
						processPaginationReq();
					});
					
				}
				
				function processPaginationReq(){
					$.ajaxSetup({ cache: false });
					$.ajax({
						url : '../jobsearch/jobboardsearchresultsBody.html',
						data : ({}),
						
						success : function(data) {
						$("#tableContent").html(data);
						},
						error : function(data) {
							alert('Unable to process');
						},
						complete : function(data) {
							// do nothing for now.
						}
					}
					);
				}

				window.onload = function() {
					processPaginationReq();
				}

				function applyFilter() {
					var displayRecordsPerPage = $("#noOfPage").val();
					var navUrl =  "../jobsearch/searchJob.html?keywords="+keywords+"&cityState="
					+cityState+"&radius="+radius+"&rows="+rows+"&start="+start+"&searchtype="+searchtype
					+"&displayRecordsPerPage="+ displayRecordsPerPage;
					$.getJSON(navUrl, function(data) {
						processPaginationReq();
					});
				}
				function applyLowerFilter() {
					var displayRecordsPerPage = $("#noOfPageLower").val();
					var navUrl =  "../jobsearch/searchJob.html?keywords="+keywords+"&cityState="
					+cityState+"&radius="+radius+"&rows="+rows+"&start="+start+"&searchtype="+searchtype
					+"&displayRecordsPerPage="+ displayRecordsPerPage;
					$.getJSON(navUrl, function(data) {
						processPaginationReq();
						
					});
				}
				
				function saveThisSearch() {
					var keywords = $.trim($("#keywords").val());
					$.ajax({url : "../savedSearches/saveThisSearch.html?keywords="+keywords,
						success: function(data){ 
							$.each(data, function(key, val) {
								if (key == "NavigationPath") {
									$.nmManual(val + '.html');
								}
								
								if (key == "LoggedInNavigationPath") {
									$.nmManual(val + '.html');
								}
							}); 
						    if(data.success != null){
						    }
						    if(data.failure != null){
						    	$("#errorMsg").html("<span style='color:red'><b>Please enter the required parameters.</b></span>");
						    }
						},
						error: function(response) {
							alert("Server Error : "+response.status);
						},
						complete: function() {
							
						}
					
					});
				} 
				
				function btsaveThisJob(jobId) {
					$.ajax({
						url : '../jobsearch/saveThisJob.html?id='+jobId,
						data : ({
							userID : "userID"
						}),
						success : function(data) {
							$.each(data, function(key, val) {
								if (key == "AjaxMSG") {
									$('#bottomjobActionInfo').html(val);
									$('#topjobActionInfo').html('');
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

				function btapplyThisJob(jobId) {
					$.ajax({
						url : '../jobsearch/applyJob.html?id='+jobId+'&currentUrl=null&clickType=apply',
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
									$('#bottomjobActionInfo').html(val);
									$('#topjobActionInfo').html('');
								}
							});
							$.each(data, function(key, val) {
								if (key == "NavigationPath") {
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
				
				function trackClick(jobId) {					
				if($("#searchResultsJobInfo"+jobId).attr('class').match('closed')){
				$.ajax({
					url : '../jobsearch/clicksTrack.html?id='+jobId+'&clickType=click',
					data : ({
						userID : "userID"
					}),
					/*success : function(data) {
					},
					;error : function(data) {
						alert('Unable to process');
					},
					complete : function(data) {
					}
*/
				});
				}
				}
	