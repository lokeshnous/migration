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
			$('#topjobActionInfo'+jobId).html("Processing...");
			$('#topjobActionInfo').html("Processing...");
			$.ajax({
				url : '../jobsearch/applyJob.html?id='+jobId+'&currentUrl=null',
				data : ({
					userID : "userID"
				}),

				success : function(data) {
					$.each(data, function(key, val) {
						if (key == "applyLink") {
							$('#topjobActionInfo'+jobId).html("");
							$('#topjobActionInfo').html("");
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
							$('#topjobActionInfo'+jobId).html("");
							$('#topjobActionInfo').html("");
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
			var isSorting ;
			var sortOrder ;
			
			
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
									isSorting  = false;
									
									var navUrl =  "../jobsearch/searchJob.html?keywords="+keywords+"&cityState="
									+cityState+"&radius="+radius+"&rows="+rows+"&start="+start+"&searchtype="+searchtype+
									"&isSorting="+isSorting;
									$("#TotalRecord").text("");
									$.getJSON(navUrl,function(data) {
										 $.ajaxSetup({ cache: true });
										$.each(data, function(key, val) {
											if (key == "AjaxMSG") {
												$('#findSearchInfo').html(val);
											}
										});										
										processPaginationReq("20");
										$("#TotalRecord").text(data["TotalNoRecords"]);
									});
									$(".otherContent").attr("style","display: none");
									$(".searchContent").attr("style","display: block");
									
									return true;
									
								});
								
								$("#submitvalAdv").click(
										function(event) {

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
											isSorting = false;
											var navUrl = "../jobsearch/searchJob.html?keywords="
													+ keywords + "&cityState=" + cityState
													+ "&radius=" + radius + "&rows=" + rows
													+ "&start=" + start + "&searchtype="
													+ searchtype+"&isSorting="+isSorting;

											$.getJSON(navUrl, function(data) {
												$.ajaxSetup({
													cache : true
												});
												$.each(data, function(key, val) {
													if (key == "AjaxMSG") {
														$('#findSearchInfo').html(val);
													}
												});
												processPaginationReq("20");
												
											});
											return true;

										});
								
							});
			
			
			 $(document).ready(function() {
				var cityState = $("#cityState").val();
				var url = "../jobsearch/findLocation.html?cityState="+cityState;
				if(typeof(cityState) != "undefined"){
				$( "#cityState" ).autocomplete({
					source: url
				});
				}
				
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
					isSorting = false;
					var navUrl =  "../jobsearch/searchJob.html?keywords="+keywords+"&cityState="
					+cityState+"&radius="+radius+"&rows="+rows+"&start="+start+"&searchtype="+searchtype+
					"&isSorting="+isSorting;;
					$("#TotalRecord").text("");
					$.getJSON(navUrl,function(data) {
						$.each(data, function(key, val) {
							if (key == "AjaxMSG") {
								$('#findSearchInfo').html(val);
							}
						});	
							processPaginationReq("20");
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
					var pageSize = $("#noOfPage").val();
					var navUrl =  "../jobsearch/searchJob.html?keywords="+keywords+"&cityState="
					+cityState+"&radius="+radius+"&rows="+rows+"&start="+start+"&searchtype="+searchtype
					+"&page="+page+"&pageSize"+pageSize+"&displayRecordsPerPage="+ pageSize
					+"&isSorting="+isSorting;
					$.getJSON(navUrl, function(data) {
						processPaginationReq(pageSize);
					});
					
				}
				
				function getNextPages(page, begin) {
					var pageSize = $("#noOfPage").val();
					var navUrl =  "../jobsearch/searchJob.html?keywords="+keywords+"&cityState="
					+cityState+"&radius="+radius+"&rows="+rows+"&start="+start+"&searchtype="+searchtype
					+"&page="+page+"&next="+begin+"&pageSize"+pageSize+"&displayRecordsPerPage="+ pageSize
					+"&isSorting="+isSorting;
					$.getJSON(navUrl, function(data) {
						processPaginationReq(pageSize);
					});
					
				}
				
				function getPrevPages(page, begin) {
					var pageSize = $("#noOfPage").val();
					var navUrl =  "../jobsearch/searchJob.html?keywords="+keywords+"&cityState="
					+cityState+"&radius="+radius+"&rows="+rows+"&start="+start+"&searchtype="+searchtype
					+"&page="+page+"&next="+begin+"&pageSize"+pageSize+"&displayRecordsPerPage="+ pageSize
					+"&isSorting="+isSorting;
					$.getJSON(navUrl, function(data) {
						processPaginationReq(pageSize);
					});
					
				}
				
				function processPaginationReq(pageSize){
					$.ajaxSetup({ cache: false });
					$.ajax({
						url : '../jobsearch/jobboardsearchresultsBody.html',
						data : ({}),
						
						success : function(data) {
						$("#tableContent").html(data);
						$("#noOfPage").val(pageSize);
						$("#noOfPageLower").val(pageSize);
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
					processPaginationReq("20");
					$.ajaxSetup({ cache: false });
					$.ajax({
						url : '../healthcarejobs/activeJobs.html',
						data : ({}),						
						success : function(data) {
						$("#TotalNoRecords").html(data);
						},
						error : function(data) {
							alert('Unable to process'+
									data);
						},
						complete : function(data) {
							// do nothing for now.
						}
					}
					);
					
				};

				function applyFilter() {
					var pageSize = $("#noOfPage").val();
					isSorting = false;
					var navUrl =  "../jobsearch/searchJob.html?keywords="+keywords+"&cityState="
					+cityState+"&radius="+radius+"&rows="+rows+"&start="+start+"&searchtype="+searchtype
					+"&displayRecordsPerPage="+ pageSize+"&isSorting="+isSorting;
					$.getJSON(navUrl, function(data) {
						processPaginationReq(pageSize);
					});
				}
				function applyLowerFilter() {
					var pageSize = $("#noOfPageLower").val();
					isSorting = false;
					var navUrl =  "../jobsearch/searchJob.html?keywords="+keywords+"&cityState="
					+cityState+"&radius="+radius+"&rows="+rows+"&start="+start+"&searchtype="+searchtype
					+"&displayRecordsPerPage="+ pageSize+"&isSorting="+isSorting;
					$.getJSON(navUrl, function(data) {
						processPaginationReq(pageSize);
						
					});
				}
				
				function saveThisSearch() {
					var keywords = $.trim($("#keywords").val());
					$.ajax({url : "../savedSearches/saveThisSearch.html?keywords="+keywords,
						success: function(data){ 
							$.each(data, function(key, val) {
								if (key == "NavigationPath") {
									window.location.href = val+ '.html';
								}
								
								if (key == "LoggedInNavigationPath") {
									$.nmManual(val + '.html');
								}
							}); 
						    if(data.success != null){
						    }
						    if(data.failure != null){
						    	$("#errorMsg").html("Please enter the required parameters.");
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
					$('#bottomjobActionInfo').html("Processing...");
					$.ajax({
						url : '../jobsearch/applyJob.html?id='+jobId+'&currentUrl=null',
						data : ({
							userID : "userID"
						}),
						
						success : function(data) {
							$.each(data, function(key, val) {
								if (key == "applyLink") {
									$('#bottomjobActionInfo').html("");
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
									$('#bottomjobActionInfo').html("");
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
				});
				}
				}
	
				function sortTable() {
					var pageSize = $("#noOfPage").val();
					isSorting = true;
					var navUrl =  "../jobsearch/searchJob.html?keywords="+keywords+"&cityState="
					+cityState+"&radius="+radius+"&rows="+rows+"&start="+start+"&searchtype="+searchtype+
					"&pageSize"+pageSize+"&displayRecordsPerPage="+ pageSize
					+"&isSorting="+isSorting;
					$.getJSON(navUrl, function(data) {
						processPaginationReq(pageSize);
					});
				}
				
				function getSearchByCompany(compName){
				$.ajax({url: "../healthcarejobs/searchByCompany.html?keywords="+compName,
						success: function(data){ 
							$.each(data, function(key, val) {
								 if (key == "searchtype" && val == "basic") {									
									 parent.window.location.href = '../jobsearch/findJobPage.html';
								}								
							}); 						
						},
						error: function(response) {
							alert("Server Error : "+response.status);
						},
						complete: function() {
							
						}
					});
			    }
				
				function deleteCurrentSearch(key, value) {
					var navUrl =  "../jobsearch/deleteCurrentSearch.html?key="+key+"&value="+value;
					$.ajax({url: navUrl,
						success: function(data){
							$("#autoload").val(true);
							if(data.keywords != null){
								$("#keywords").val(data.keywords);
							}else if(data.cityState != null){
								$("#cityState").val(data.cityState);
								
							}else if(data.radius != null){
								$("#radius").val(data.radius);
							}
							findJobs();
						},
						error: function(response) {
							alert("Server Error : "+response.status);
						},
						complete: function() {
							
						}
					});
				}

				
				function refineByRadius(selectedRadius){
					if(selectedRadius == $("#selectedRadius").val())
					{
						$("#selectedRadius").val(0);
					}
					else
					{
						$("#selectedRadius").val(selectedRadius);
					}
					refineSearch();
				}
				
				function refineByCompany(selectedComp){
					var company = selectedComp.split(" (");
					if(company[0] == $("#selectedCompany").val())
					{
						$("#selectedCompany").val('');
					}
					else
					{
						$("#selectedCompany").val(company[0]);
					}
					refineSearch();
				}
				
				function refineByState(selectedState){
					var state = selectedState.split(" (");
					if(state[0] == $("#selectedState").val())
					{
						$("#selectedState").val('');
					}
					else
					{
						$("#selectedState").val(state[0]);
					}
					refineSearch();
				}
				
				function refineByCity(selectedCity){
					var city = selectedCity.split(" (");
					if(city[0] == $("#selectedCity").val())
					{
						$("#selectedCity").val('');
					}
					else
					{
						$("#selectedCity").val(city[0]);
					}
					refineSearch();
				}
				
				function refineSearch() {
					$("#autoload").val(false);
					$("#rows").val(25000);
					$("#start").val("0");
					$("#refined").val(true);
					
					keywords = $("#keywords").val();
					cityState = $("#cityState").val();
					radius = $("#radius").val();
					rows = $("#rows").val();
					start = $("#start").val();
					searchtype = $("#searchtype").val();
					isSorting = false;
					
					refined = $("#refined").val();
					var secondFQParam = $("#selectedCompany").val();
					var thirdFQParam = $("#selectedState").val();
					var fouthFQParam = $("#selectedCity").val();
					if($("#selectedRadius").val() != 0 || $("#selectedRadius").val() != "")
					{
						radius = $("#selectedRadius").val();
					}
					
					var navUrl =  "../jobsearch/searchJob.html?keywords="+keywords+"&cityState="
					+cityState+"&radius="+radius+"&rows="+rows+"&start="+start+"&searchtype="+searchtype+
					"&isSorting="+isSorting+"&secondFQParam="+secondFQParam+"&thirdFQParam="+thirdFQParam+
					"&fouthFQParam="+fouthFQParam+"&refined="+refined;
					$("#TotalRecord").text("");
					$.getJSON(navUrl,function(data) {
						 $.ajaxSetup({ cache: true });
						$.each(data, function(key, val) {
							if (key == "AjaxMSG") {
								$('#findSearchInfo').html(val);
							}
						});										
						processPaginationReq("20");
						$("#TotalRecord").text(data["TotalNoRecords"]);
					});
					$(".otherContent").attr("style","display: none");
					$(".searchContent").attr("style","display: block");
					
					return true;
				
				}
				
				function getByJobTitle() {
					var navUrl =  "../jobsearch/searchJbsByTitle.html";
					$.ajax({url: navUrl,
						success: function(data){
							processPaginationReq("20");
							$(".otherContent").attr("style","display: none");
							$(".searchContent").attr("style","display: block");
						},
						error: function(response) {
							alert("Server Error : "+response.status);
						},
						complete: function() {
							// nothing to do now
						}
					});
				}
								
				function getByEmployer() {
					var navUrl =  "../jobsearch/searchJbsByEmployer.html";
					$.ajax({url: navUrl,
						success: function(data){
							processPaginationReq("20");
							$(".otherContent").attr("style","display: none");
							$(".searchContent").attr("style","display: block");
						},
						error: function(response) {
							alert("Server Error : "+response.status);
						},
						complete: function() {
							// nothing to do now
						}
					});
				}
				
				function getByLocation() {
					var navUrl =  "../jobsearch/searchJbsByLocation.html";
					$.ajax({url: navUrl,
						success: function(data){
							processPaginationReq("20");
							$(".otherContent").attr("style","display: none");
							$(".searchContent").attr("style","display: block");
						},
						error: function(response) {
							alert("Server Error : "+response.status);
						},
						complete: function() {
							// nothing to do now
						}
					});
				}
				
				function searchByTitle(jobTitle){
					var browseByTitle = $("#browseByTitle").val();
					$.ajax({url: "../jobsearch/searchJob.html?firstFQParam="+jobTitle+"&browseByTitle="+browseByTitle,
								success: function(data){ 
									$("#autoload").val(true);									
									processPaginationReq("20");
									$(".otherContent").attr("style","display: none");
									$(".searchContent").attr("style","display: block");
								},
								error: function(response) {
									alert("Server Error : "+response.status);
								},
								complete: function() {
					
								}
					});
			    }
				
				function searchByLocation(stateFullName){
					alert('stateFullName'+stateFullName);
					$.ajax({url: "../jobsearch/searchJob.html?thirdFQParam="+stateFullName+"&browseByLocation="+browseByLocation,
								success: function(data){ 
									$("#autoload").val(true);									
									processPaginationReq("20");
									$(".otherContent").attr("style","display: none");
									$(".searchContent").attr("style","display: block");
								},
								error: function(response) {
									alert("Server Error : "+response.status);
								},
								complete: function() {
					
								}
					});
			    }
				
				
				
				
