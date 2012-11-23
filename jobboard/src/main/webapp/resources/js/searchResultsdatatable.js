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


			function sendToFrd(jobId,jobtitle) {	
				var currentUrl = window.location.pathname;
				$.nmManual($("#contextPath").val()+'/jobsearch/sendtofriend.html?id='+jobId+'&jobtitle='+jobtitle+'&currentUrl='+currentUrl);
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
				url : $("#contextPath").val()+'/jobsearch/saveThisJob.html?id='+jobId,
				data : ({
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
				},
				complete : function(data) {
				}
			});
		}
		function applyThisJob(jobId) {
			$('#topjobActionInfo'+jobId).html("Processing...");
			$('#topjobActionInfo').html("Processing...");
			var navUrl = $("#contextPath").val()+'/jobsearch/applyJob.html?id='+jobId+'&currentUrl=null';
			$.ajax({
				url : navUrl,
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
							$.nmManual(val);
						}
					});
				},
				error : function(data) {
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
			var refined ;
			
			$(document)
					.ready(
							function() {
								$(".megamenu").megamenu();
								
								// check for autoload on loading the page to get the results
								var autoLoad = $("#autoload").val();
								if(autoLoad == true || autoLoad == "true"){	
									// help to get the search results
									searchJobs();								
								}
								
								// after clicking the find job button
								$("#submitval").click(function(event) {
									$("#errorMsg").html("");
									$('#findSearchInfo').html("");									
									searchJobs();									
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
											var navUrl = $("#contextPath").val()+"/jobsearch/searchJob.html?keywords="
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
				var url = $("#contextPath").val()+"/jobsearch/findLocation.html?cityState="+cityState;
				if(typeof(cityState) != "undefined"){
				$( "#cityState" ).autocomplete({
					source: url
				});
				}
				
			}); 
			 
			 function findJobs() {
				var autoLoad = $("#autoload").val();
				 if(autoLoad == true || autoLoad == "true"){
					 // get the search results
					 searchJobs();
				 }				
			}
			
			 // helps to retrive jobs from criteria 
			function searchJobs() {
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
					var navUrl =  $("#contextPath").val()+"/jobsearch/searchJob.html";
					var formData= $("#jobSearchResultBodyFormId").serialize()+$("#jobSearchResultHeaderFormId").serialize();
					$("#TotalRecord").text("");
					$.getJSON(navUrl,formData,function(data) {
						/*$.each(data, function(key, val) {
							
							// print the rows for grid
							if (key == "jsonRows") {
								 //alert("JSON Data: " +  val);
								 //alert("JSON Data: " +  val);
								 //$("<input/>").attr("type", "text").attr("value", val).appendTo("#imagespp");
								var htmlData = "";
								for (var i = 0; i < val.length; i++) {
								    var object = val[i];
								   // for (var property in object) {
								        var JobId = object["JobId"];
								        var JobTitle = object["JobTitle"];
								        var Company = object["Company"];
								        var PostedDate = object["PostedDate"];
								        htmlData = htmlData+"<ul ";
										if(object["IsPremium"] == 0){
											htmlData = htmlData + "class='searchResultsJobInfo closed'";
										}else{
											htmlData = htmlData + "class='searchResultsJobInfo closed orange-bg'";
										}
										htmlData = htmlData + "id='searchResultsJobInfo"+JobId+"' onclick='trackClick("+JobId+");' >";
										
										htmlData = htmlData + "<li class='searchResultsColumn1'><a class='clickableLink'>"+JobTitle+"</a></li>";

										htmlData = htmlData + "<li class='searchResultsColumn2'><a class='clickableLink'>"+Company+"</a></li>";
										htmlData = htmlData + "<li class='searchResultsColumn3'>" ;
										alert(object["HideCity"]);
										alert(object["HideState"]);
										alert(object["HideCountry"]);
										if(object["HideCity"] == 1 || object["HideState"] == 1 || object["HideCountry"] == 1){
											htmlData = htmlData + object["City"];
										}
											htmlData = htmlData + "</li>";

										htmlData = htmlData + "<li class='searchResultsColumn4'>"+PostedDate+"</li>";
										htmlData = htmlData + " </ul>";
								    //}
								}
								$("#searchResultsItem").html(htmlData);
								//alert(val["JobId"]);
							}
					});	*/
						$.each(data, function(key, val) {
							
							// get the jobs count after search 
							if (key == "TotalNoRecords") {
								//alert("JSON Data: " +  val);
							}
						});	
						$.each(data, function(key, val) {
							
							// print if any validation messages
							if (key == "AjaxMSG") {
								$('#findSearchInfo').html(val);
							}
						});	
					processPaginationReq("20");
					//$("#TotalRecord").text(data["TotalNoRecords"]);
					});
					$("#selectedCompany").val("");
					$("#selectedState").val("");
					$("#selectedCity").val("");
					$(".otherContent").attr("style","display: none");
					$(".searchContent").attr("style","display: block");
					
					return true;
				 
			}
			
			 
				 function postYourResume() {
					$.ajax({
						url : $("#contextPath").val()+'/jobsearch/jobseekerPostYourResume.html',
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
						},
						complete : function(data) {
						}
					});
				}

				function viewJobDetails(jobId, jobTitle) {
					jobTitle = jobTitle.toLowerCase();
					jobTitle = ReplaceAll(jobTitle," ","-");
					window.location.href = $("#contextPath").val()+"/jobsearch/jobview/" + jobId + "/" +jobTitle+ ".html";
				}
				
				function ReplaceAll(Source,stringToFind,stringToReplace){
					  var temp = Source;
					    var index = temp.indexOf(stringToFind);
					        while(index != -1){
					            temp = temp.replace(stringToFind,stringToReplace);
					            index = temp.indexOf(stringToFind);
					        }
					        return temp;
					}
				
				function sortTable() {
					var pageSize = $("#noOfPage").val();
					isSorting = true;
					//$("#refined").val(refinedResults);
					var navUrl =  $("#contextPath").val()+"/jobsearch/searchJob.html?pageSize="+ pageSize+ "&isSorting="+isSorting
					+"&freshjobsearch=false"+"&refined="+refined;
					var formData= $("#jobSearchResultFormId").serialize()+$("#jobSearchResultHeaderFormId").serialize();
					$.getJSON(navUrl,formData, function(data) {
						processPaginationReq(pageSize);
					});
				}
				
				function applyFilter() {
					var pageSize = $("#noOfPage").val();
					isSorting = false;
					var navUrl =  $("#contextPath").val()+"/jobsearch/searchJob.html?pageSize="+ pageSize +"&isSorting="+isSorting
					+"&freshjobsearch=false"+"&refined="+refined;
					var formData= $("#jobSearchResultFormId").serialize()+$("#jobSearchResultHeaderFormId").serialize();
					$.getJSON(navUrl, formData, function(data) {
						processPaginationReq(pageSize);
					});
				}
				
				function applyLowerFilter() {
					var pageSize = $("#noOfPageLower").val();
					isSorting = false;
					var navUrl =  $("#contextPath").val()+"/jobsearch/searchJob.html?pageSize="+ pageSize +"&isSorting="+isSorting
					+"&freshjobsearch=false";
					var formData= $("#jobSearchResultFormId").serialize()+$("#jobSearchResultHeaderFormId").serialize();
					$.getJSON(navUrl, formData, function(data) {
						processPaginationReq(pageSize);
					});
				}
				
				function getPage(page, begin) {
					var pageSize = $("#noOfPage").val();
					refined= false;
					var navUrl =  $("#contextPath").val()+"/jobsearch/searchJob.html?pageSize="+ pageSize+ "&page="+page
					+"&isSorting="+isSorting+"&freshjobsearch=false"+"&next="+begin+"&refined="+refined;
					var formData= $("#jobSearchResultFormId").serialize()+$("#jobSearchResultHeaderFormId").serialize();
					$.getJSON(navUrl, formData, function(data) {
						processPaginationReq(pageSize);
					});
					
				}
				
				function getNextPages(page) {
					var pageSize = $("#noOfPage").val();
					var fastforward = true;
					refined= false;
					var formData= $("#jobSearchResultFormId").serialize()+$("#jobSearchResultHeaderFormId").serialize();
					var navUrl =  $("#contextPath").val()+"/jobsearch/searchJob.html?pageSize="+ pageSize+ "&page="+page+"&fastforward="+fastforward+"&isSorting="+isSorting
					+"&freshjobsearch=false"+"&next="+page+"&refined="+refined;
					$.getJSON(navUrl, formData, function(data) {
						processPaginationReq(pageSize);
					});
					
				}
				
				function getPrevPages(page) {
					var pageSize = $("#noOfPage").val();
					var fastforward = false;
					refined= false;
					var formData= $("#jobSearchResultFormId").serialize()+$("#jobSearchResultHeaderFormId").serialize();
					var navUrl =  $("#contextPath").val()+"/jobsearch/searchJob.html?pageSize="+ pageSize+ "&page="+page+"&fastforward="+fastforward+"&isSorting="+isSorting
					+"&freshjobsearch=false"+"&next="+page+"&refined="+refined;
					$.getJSON(navUrl, formData, function(data) {
						processPaginationReq(pageSize);
					});
					
				}
				
				function processPaginationReq(pageSize){
					$.ajaxSetup({ cache: false });
					$.ajax({
						url : $("#contextPath").val()+'/jobsearch/jobboardsearchresultsBody.html',
						data : ({}),
						
						success : function(data) {
						$("#tableContent").html(data);
						$("#noOfPage").val(pageSize);
						$("#noOfPageLower").val(pageSize);
						},
						error : function(data) {
						},
						complete : function(data) {
							// do nothing for now.
						}
					}
					);
					getHistory();
				}

				
				function getHistory(){
					$.ajax({
						url : $("#contextPath").val()+'/jobsearch/jobboardSearchResultsHitory.html',
						data : ({}),
						
						success : function(data) {
							$("#jobboardSearchResultsHitoryId").html(data);
						},
						error : function(data) {
						},
						complete : function(data) {
							// do nothing for now.
						}
					}
					);
				}
				window.onload = function() {
					var contextPath = $("#contextPath").val();
					$.ajax({
						url : contextPath+'/healthcarejobs/homeFeaturedEmps.html',
						data : ({}),
						
						success : function(data) {
						$("#slider1FramesId").html(data);
						},
						error : function(data) {
						},
						complete : function(data) {
							// do nothing for now.
						}
					});
					//processPaginationReq("20");
					$.ajaxSetup({ cache: false });
					$.ajax({
						url : contextPath+'/healthcarejobs/activeJobs.html',
						data : ({}),						
						success : function(data) {
						$("#TotalNoRecords").html(data);
						},
						error : function(data) {
						},
						complete : function(data) {
							// do nothing for now.
						}
					}
					);
					
				};

				function saveThisSearch() {
					$.ajax({url : $("#contextPath").val()+"/savedSearches/saveThisSearch.html?keywords="+keywords,
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
				
				function saveRecentSearch(savesearchid) {
				
					$.ajax({url : "../savedSearches/saveRecentSearch.html?keywords="+keywords+"&savesearchid="+savesearchid,
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
				
				function btsaveThisJob(jobId,context) {
					$.ajax({
						url : $("#contextPath").val()+'/jobsearch/saveThisJob.html?id='+jobId,
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
						},
						complete : function(data) {
						}
					});
				}

				function btapplyThisJob(jobId) {
					$('#bottomjobActionInfo').html("Processing...");
					$.ajax({
						url : $("#contextPath").val()+'/jobsearch/applyJob.html?id='+jobId+'&currentUrl=null',
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
									//window.location.href = val;
									$.nmManual(val);
								}
							});
						},
						error : function(data) {
						},
						complete : function(data) {
						}
					});
				}
				
				function trackClick(jobId) {					
				$.ajax({
					url : $("#contextPath").val()+'/jobsearch/clicksTrack.html?id='+jobId+'&clickType=click',
					data : ({
						userID : "userID"
					}),
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
					var navUrl =  $("#contextPath").val()+"/jobsearch/deleteCurrentSearch.html?key="+key+"&value="+value;
					$.ajax({url: navUrl,
						success: function(data){
							$("#autoload").val(true);
							if(data.keywords != null){
								$("#keywords").val(data.keywords);
							}else if(data.cityState != null){
								$("#cityState").val(data.cityState);
								
							}
							if(data.radius != null){
								$("#radius").val(data.radius);
							}
							//findJobs();
							var navUrl =  $("#contextPath").val()+"/jobsearch/searchJob.html";
							var formData= $("#jobSearchResultBodyFormId").serialize()+$("#jobSearchResultHeaderFormId").serialize();
							//$("#TotalRecord").text("");
							$.getJSON(navUrl,formData,function(data) {
								$.each(data, function(key, val) {
									
									// print if any validation messages
									if (key == "AjaxMSG") {
										$('#findSearchInfo').html(val);
									}
								});	
							processPaginationReq("20");
							});
						},
						error: function(response) {
							alert("Server Error : "+response.status);
						},
						complete: function() {
							
						}
					});
				}

				
				function refineByRadius(selectedRadius, isSelected){
					var radius = selectedRadius;
					if(isSelected == 'true'){
						radius = 0;
					}
					
					/*if(selectedRadius == $("#selectedRadius").text())
					{
						$("#selectedRadius").text(0);
					}
					else
					{
						$("#selectedRadius").text(selectedRadius);
					}*/
					refineSearch("refineRadius", radius);
				}
				
				function refineByCompany(selectedComp, isSelected){
					var company = selectedComp.split(" (");
					var selectedCompany = company[0];
					if(isSelected == 'true'){
						selectedCompany = '';
					}
					/*if(company[0] == $("#selectedCompany").text())
					{
						$("#selectedCompany").text('');
					}
					else
					{
						$("#selectedCompany").text(company[0]);
					}*/
					refineSearch("secondFQParam", selectedCompany);
				}
				
				function refineByState(selectedState, isSelected){
					var state = selectedState.split(" (");
					var selectedState = state[0];
					if(isSelected == 'true'){
						selectedState = '';
					}
					/*if(state[0] == $("#selectedState").text())
					{
						$("#selectedState").text('');
					}
					else
					{
						$("#selectedState").text(state[0]);
					}*/
					refineSearch("thirdFQParam",selectedState);
				}
				
				function refineByCity(selectedCity, isSelected){
					var city = selectedCity.split(" (");
					var selectedCity = city[0];
					if(isSelected == 'true'){
						selectedCity = '';
					}
					/*if(city[0] == $("#selectedCity").text())
					{
						$("#selectedCity").text('');
					}
					else
					{
						$("#selectedCity").text(city[0]);
					}*/
					refineSearch("fouthFQParam", selectedCity);
				}
				
				function refineSearch(refineKey, refineVal) {
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
					
					refined = true;
//					var secondFQParam = $.trim($("#selectedCompany").val());
//					var thirdFQParam = $.trim($("#selectedState").val());
//					var fouthFQParam = $.trim($("#selectedCity").val());
//					var firstFQParam = $("#selectedJobtitle").text();
//					var secondFQParam = $("#selectedCompany").text();
//					var thirdFQParam = $("#selectedState").text();
//					var fouthFQParam = $("#selectedCity").text();
//					var fifthFQParam = $("#selectedArea").text();
					if($("#selectedRadius").val() != 0 || $("#selectedRadius").val() != "")
					{
						radius = $("#selectedRadius").val();
					}
					
					/*var navUrl =  $("#contextPath").val()+"/jobsearch/searchJob.html?keywords="+keywords+"&cityState="
					+cityState+"&radius="+radius+"&rows="+rows+"&start="+start+"&searchtype="+searchtype+
					"&isSorting="+isSorting+"&firstFQParam="+firstFQParam+"&secondFQParam="+secondFQParam+"&thirdFQParam="+thirdFQParam+
					"&fouthFQParam="+fouthFQParam+"&fifthFQParam="+fifthFQParam+"&refined="+refined;*/
					var navUrl =  $("#contextPath").val()+"/jobsearch/searchJob.html?isSorting="+isSorting+
					"&refineKey="+refineKey+"&refineVal="+refineVal+"&refined="+refined+"&freshjobsearch=false";
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
				
				/*function getByJobTitle() {
					var navUrl =  $("#contextPath").val()+"/jobsearch/searchJbsByTitle.html";
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
					var navUrl =  $("#contextPath").val()+"/jobsearch/searchJbsByEmployer.html";
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
					var navUrl =  $("#contextPath").val()+"/jobsearch/searchJbsByLocation.html";
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
					$.ajax({url: $("#contextPath").val()+"/jobsearch/searchJob.html?firstFQParam="+jobTitle+"&browseByTitle="+browseByTitle,
								success: function(data){ 
									//$("#autoload").val(true);									
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
					var browseByLocation = $("#browseByLocation").val();
					$.ajax({url: $("#contextPath").val()+"/jobsearch/searchJob.html?thirdFQParam="+stateFullName+"&browseByLocation="+browseByLocation,
								success: function(data){ 
									//$("#autoload").val(true);									
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
			    }*/
				

				function clearAll(jobTitle){
					$.ajax({url: "../jobsearch/clearalllist.html",
							success: function(data){ 
								
								getHistory();
							},
							error: function(response) {
								alert("Server Error : "+response.status);
							},
							complete: function() {
								
							}
						});
				    }

				/*function searchByLocationRegion(stateFullName){
					var browseByLocationReg = $("#browseByLocationReg").val();
					var area=stateFullName.split(" ",1); 
					$.ajax({url: $("#contextPath").val()+"/jobsearch/searchJob.html?fifthFQParam="+area+"&browseByLocationReg="+browseByLocationReg,		
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
				
				function searchByLocReg(stateFullName){
					var browseByLocationReg = $("#browseByLocationReg").val();
					$.ajax({url: $("#contextPath").val()+"/jobsearch/searchJob.html?thirdFQParam="+stateFullName+"&browseByLocationReg="+browseByLocationReg,
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
				
				function searchByEmployer(company){
					var  browseByEmployer = $("#browseByEmployer").val();
					$.ajax({url: $("#contextPath").val()+"/jobsearch/searchJob.html?secondFQParam="+company+"&browseByEmployer="+browseByEmployer,
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
				}*/
				

