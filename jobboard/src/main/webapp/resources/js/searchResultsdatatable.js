jQuery(document).ready(function() {
				jQuery(".megamenu").megamenu();
				$(".saveThisPopup").displaypopup(".saveThisPopup","775","252");
				$(".sendtofriend").displaypopup(".sendtofriend","775","252");
				$("#keywords").focus();
				if($("#searchtypeToSavedSearch").val() == "basic"){
					
		            $("#keywords").val($("#keywordsToSavedSearch").val());
		            $("#cityState").val($("#cityStateToSavedSearch").val());
		            $("#radius").val($("#radiusToSavedSearch").val());
				}
				$(".normalList").hide();
				$(".LeftContantLinks").click(function(e){					
					$(".cursor").show();
					$(".normalList").hide();
					$(this).parent().children().removeClass('LeftContantLinksActive');
					$(this).parent().children().addClass('LeftContantLinks');
					$(this).addClass('LeftContantLinksActive');
					$(this).removeClass('LeftContantLinks');
					$(this).children(".cursor").hide();
					$(this).children(".normalList").show();
				});

			});



			function sendToFrd(jobId,jobtitle) {	
				var currentUrl = window.location.pathname;
				$.nmManual($("#contextPath").val()+'/search/sendtofriend.html?id='+jobId+'&jobtitle='+jobtitle+'&currentUrl='+currentUrl, {closeOnEscape: false, showCloseButton: false, closeOnClick: false});
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

		function setDefaultRadius(){
			var cityState = $('#cityState').val();
			if(cityState.length >0){
				$('#radius').val("25");
			}else{
				$('#radius').val("0");
			}
		}
		
		function saveThisJob(jobId) {
			//var currentUrl = window.location.pathname;
			$.ajax({
				url : $("#contextPath").val()+'/search/saveThisJob.html?id='+jobId+"&currentUrl=null",
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
							$.nmManual(val + '.html', {closeOnEscape: false, showCloseButton: false, closeOnClick: false});

						}
					});
					
				},
				error : function(data) {
				},
				complete : function(data) {
				}
			});
		}
		
		
		
		function bottomSelectResume(jobId){
			$('#bottomjobActionInfo').html("Processing...");
			var navUrl = $("#contextPath").val()+'/search/selectResume.html?id='+jobId+'&position=bottom';
			$.ajax({
				url : navUrl,
				data : ({
					userID : "userID"
				}),

				success : function(data) {
					$.each(data, function(key, val) {
						if (key == "applyLink") {
							$('#bottomjobActionInfo').html("");
							if((/^\s*$/).test(val)){
								alert("Insufficient information to apply. Please contact Advance team.");
							}else{
							window.open(val, '_blank');
							}
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
							$.nmManual(val, {closeOnEscape: false, showCloseButton: false, closeOnClick: false});
						}
					});
				},
				error : function(data) {
				},
				complete : function(data) {
				}
			});
		}
		
		function selectResume(jobId){
			$('#topjobActionInfo'+jobId).html("Processing...");
			$('#topjobActionInfo').html("Processing...");
			var navUrl = $("#contextPath").val()+'/search/selectResume.html?id='+jobId+'&position=top';
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
							if((/^\s*$/).test(val)){
								alert("Insufficient information to apply. Please contact Advance team.");
							}else{
							window.open(val, '_blank');
							}
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
							$.nmManual(val, {closeOnEscape: false, showCloseButton: false, closeOnClick: false});
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
			var navUrl = $("#contextPath").val()+'/search/applyJob.html?id='+jobId+'&currentUrl=null';
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
							$.nmManual(val, {closeOnEscape: false, showCloseButton: false, closeOnClick: false});
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
								}else if($("#autoloadDiv").text() == true || $("#autoloadDiv").text() == "true"){	
									// help to get the search results
									searchJobs();		
									
								}
								
								// after clicking the find job button
								$("#submitval").click(function(event) {
									$("#errorMsg").html("");
									$('#findSearchInfo').html("");									
									searchJobs();									
								});
								
								// Function to search jobs after clicking the enter button 
								$(function () {
									  $("#keywords").keypress(function (event) {
										if(event.which == 13){
											$("#errorMsg").html("");
											$('#findSearchInfo').html("");									
											searchJobs();
										}
									  });
									});
								$(function () {
									$("#cityState").keypress(function (event) {
										if(event.which == 13){
											$("#errorMsg").html("");
											$('#findSearchInfo').html("");									
											searchJobs();
										}
									});
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
											var navUrl = $("#contextPath").val()+"/search/searchJob.html?keywords="
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
												processPaginationReq(data, "20");
												
											});
											return true;

										});
								
							});
			
			
			 $(document).ready(function() {
				var cityState = $("#cityState").val();
				var url = $("#contextPath").val()+"/search/findLocation.html?cityState="+cityState;
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
			 $("#loading-image").hide();
			 // helps to retrive jobs from criteria 
			function searchJobs() {
				$("#loading-image").show();
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
					var navUrl =  $("#contextPath").val()+"/search/searchJob.html";
					var formData= $("#jobSearchResultBodyFormId").serialize()+$("#jobSearchResultHeaderFormId").serialize();
					$("#TotalRecord").text("");
					//$("#connectionStatus").text("Searching..");
					$.getJSON(navUrl,formData,function(data) {
						$.each(data, function(key, val) {							
							// get the jobs count after search 
							if (key == "TotalNoRecords") {
							}
						});	
								$('#findSearchInfo').html(data.AjaxMSG);
								processPaginationReq(data, "20");
					})
					$("#selectedCompany").val("");
					$("#selectedState").val("");
					$("#selectedCity").val("");
					$(".otherContent").attr("style","display: none");
					$(".careersContent").attr("style","display: none");
					$(".searchContent").attr("style","display: block");
					return true;
				 
			}			
			 
			function postYourResumeLink() {
				$.ajax({url : $("#contextPath").val()+'/search/jobseekerPostYourResume.html',
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
									$.nmManual(val, {closeOnEscape: false, showCloseButton: false, closeOnClick: false});
								}
							});
						},
						error : function(data) {
						},
						complete : function(data) {
						}
					});
				}
			
			function postYourResume(context) {
				$.ajax({url : context+'/search/jobseekerPostYourResume.html',
						data : ({}),
						success : function(data) {
							$.each(data, function(key, val) {
								if (key == "LoggedInNavigationPath") {
									window.location.href = val;
								}
							});
							$.each(data, function(key, val) {
								if (key == "NavigationPath") {
									$.nmManual(val, {closeOnEscape: false, showCloseButton: false, closeOnClick: false});
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
					window.location.href = $("#contextPath").val()+"/search/jobview/" + jobId + "/" +jobTitle+ ".html";
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
					var navUrl =  $("#contextPath").val()+"/search/searchJob.html?pageSize="+ pageSize+ "&isSorting="+isSorting
					+"&freshjobsearch=false"+"&refined="+refined;
					var formData= $("#jobSearchResultFormId").serialize()+$("#jobSearchResultHeaderFormId").serialize();
					$.getJSON(navUrl,formData, function(data) {
						processPaginationReq(data, pageSize);
					});
				}
				
				function applyFilter() {
					var pageSize = $("#noOfPage").val();
					isSorting = false;
					refined= false;
					var navUrl =  $("#contextPath").val()+"/search/searchJob.html?pageSize="+ pageSize +"&isSorting="+isSorting
					+"&freshjobsearch=false"+"&refined="+refined;
					var formData= $("#jobSearchResultFormId").serialize()+$("#jobSearchResultHeaderFormId").serialize();
					$.getJSON(navUrl, formData, function(data) {
						processPaginationReq(data, pageSize);
					});
				}
				
				function applyLowerFilter() {
					var pageSize = $("#noOfPageLower").val();
					isSorting = false;
					refined= false;
					var navUrl =  $("#contextPath").val()+"/search/searchJob.html?pageSize="+ pageSize +"&isSorting="+isSorting
					+"&freshjobsearch=false"+"&refined="+refined;
					var formData= $("#jobSearchResultFormId").serialize()+$("#jobSearchResultHeaderFormId").serialize();
					$.getJSON(navUrl, formData, function(data) {
						processPaginationReq(data, pageSize);
					});
				}
				
				function getPage(page, begin) {
					//$("#loading-image").show();
					var pageSize = $("#noOfPage").val();
					refined= false;
					var navUrl =  $("#contextPath").val()+"/search/searchJob.html?pageSize="+ pageSize+ "&page="+page
					+"&isSorting="+isSorting+"&freshjobsearch=false"+"&next="+begin+"&refined="+refined;
					var formData= $("#jobSearchResultFormId").serialize()+$("#jobSearchResultHeaderFormId").serialize();
					$.getJSON(navUrl, formData, function(data) {
						processPaginationReq(data, pageSize);
					});
					
				}
				
				function getNextPages(page) {
					//$("#loading-image").show();
					var pageSize = $("#noOfPage").val();
					var fastforward = true;
					refined= false;
					var formData= $("#jobSearchResultFormId").serialize()+$("#jobSearchResultHeaderFormId").serialize();
					var navUrl =  $("#contextPath").val()+"/search/searchJob.html?pageSize="+ pageSize+ "&page="+page+"&fastforward="+fastforward+"&isSorting="+isSorting
					+"&freshjobsearch=false"+"&next="+page+"&refined="+refined;
					$.getJSON(navUrl, formData, function(data) {
						processPaginationReq(data, pageSize);
					});
					
				}
				
				function getPrevPages(page) {
					//$("#loading-image").show();
					var pageSize = $("#noOfPage").val();
					var fastforward = false;
					refined= false;
					var formData= $("#jobSearchResultFormId").serialize()+$("#jobSearchResultHeaderFormId").serialize();
					var navUrl =  $("#contextPath").val()+"/search/searchJob.html?pageSize="+ pageSize+ "&page="+page+"&fastforward="+fastforward+"&isSorting="+isSorting
					+"&freshjobsearch=false"+"&next="+page+"&refined="+refined;
					$.getJSON(navUrl, formData, function(data) {
						processPaginationReq(data, pageSize);
					});
					
				}
				
				function processPaginationReq(data, pageSize){
					$.ajaxSetup({ cache: false });
					$.ajax({
						url : $("#contextPath").val()+'/search/jobboardsearchresultsBody.html',
						pgdata : ({}),
						
						success : function(pgdata) {
						$("#tableContent").html(pgdata);
						$("#noOfPage").val(pageSize);
						$("#noOfPageLower").val(pageSize);
						},
						error : function(pgdata) {
						},
						complete : function(pgdata) {
							// do nothing for now.
							//$("#loading-image").hide();
							 $('#loading-image').hide('fast', function() {
								// code to paste ads
									var count = 1 ;
									// populate the ads for grid
									if(data.adPageCenterMiddleList != undefined){
									$.each(data.adPageCenterMiddleList, function(key, val) {
											var id = '#ad_wrapper'+count;
											window.setTimeout(function() { 
												renderAd(val, id); 
												}, 100);
											count = count+1;
									});
									}
								  });
							// populate the ads for leader banner
								if(data.adPageTop != undefined){
								window.setTimeout(function() { 
									renderAd(data.adPageTop, ".ad_page_top"); 
									}, 100);
								}
								// populate the ads for bottom banner
								if(data.adPageBottom != undefined){
								window.setTimeout(function() { 
									renderAd(data.adPageBottom, ".ad_wrapper"); 
									}, 100);
								}
								// populate the ads for bottom banner
								if(data.adPageRightTop != undefined){
								window.setTimeout(function() { 
									renderAd(data.adPageRightTop, "#adPageRightTop"); 
									}, 100);
								}
								// populate the ads for bottom banner
								if(data.adPageRightMiddle != 'undefined'){
								window.setTimeout(function() { 
									renderAd(data.adPageRightMiddle, "#adPageRightMiddle"); 
									}, 100);
								}							
						}
					});
					getHistory();
					//setTimeout(renderAd,100,data.adPagerighttop, ".ad_wrapper");	
					//setTimeout(renderAd,100,data.adPagerightBtm, ".ad_wrapper");	
				}
				
				function renderAd(val, id){
					if(val != undefined){
					$(id).html(val).submit();
					}
				}

				
				function getHistory(){
					$.ajax({
						url : $("#contextPath").val()+'/search/jobboardSearchesHistory.html',
						data : ({}),
						
						success : function(data) {
							$("#jobboardSearchesHistoryId").html(data);
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
					if(contextPath != undefined){
					$.ajax({
						url : contextPath+'/healthcare/homeFeaturedEmps.html',
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
						url : contextPath+'/search/activeJobs.html',
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
					getHistory();
					}
				};
				function saveThisSearch() {
					$.ajax({url : $("#contextPath").val()+"/savedSearches/saveThisSearch.html?keywords="+keywords,
						success: function(data){ 
							$.each(data, function(key, val) {
								if (key == "NavigationPath") {
									alert("Search saved successfully! Access saved search criteria using  \"My saved searches\" on your dashboard.");
									window.location.href = val+ '.html';
								}
								
								if (key == "LoggedInNavigationPath") {
									$.nmManual(val + '.html', {closeOnEscape: false, showCloseButton: false, closeOnClick: false});
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
					//var currentUrl = window.location.pathname;
					$.ajax({
						url : $("#contextPath").val()+'/search/saveThisJob.html?id='+jobId+"&currentUrl=null",
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
									$.nmManual(val + '.html', {closeOnEscape: false, showCloseButton: false, closeOnClick: false});
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
						url : $("#contextPath").val()+'/search/applyJob.html?id='+jobId+'&currentUrl=null',
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
									$.nmManual(val, {closeOnEscape: false, showCloseButton: false, closeOnClick: false});
								}
							});
						},
						error : function(data) {
						},
						complete : function(data) {
						}
					});
				}
				
				function trackClick(jobId,clickType) {					
				$.ajax({
					url : $("#contextPath").val()+'/search/clicksTrack.html?id='+jobId+'&clickType='+clickType,
				});
				}
	
				function trackPrint(jobId,clickType) {
					$.ajax({
						url : $("#contextPath").val()+'/search/clicksTrack.html?id='+jobId+'&clickType='+clickType,
					});
					window.print();
					}
				
				function deleteCurrentSearch(key, value) {
					var navUrl =  $("#contextPath").val()+"/search/deleteCurrentSearch.html?key="+key+"&value="+value;
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
							var navUrl =  $("#contextPath").val()+"/search/searchJob.html";
							var formData= $("#jobSearchResultBodyFormId").serialize()+$("#jobSearchResultHeaderFormId").serialize();
							//$("#TotalRecord").text("");
							$.getJSON(navUrl,formData,function(data) {
								$.each(data, function(key, val) {
									
									// print if any validation messages
									if (key == "AjaxMSG") {
										$('#findSearchInfo').html(val);
									}
								});	
							processPaginationReq(data, "20");
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
					
					/*var navUrl =  $("#contextPath").val()+"/search/searchJob.html?keywords="+keywords+"&cityState="
					+cityState+"&radius="+radius+"&rows="+rows+"&start="+start+"&searchtype="+searchtype+
					"&isSorting="+isSorting+"&firstFQParam="+firstFQParam+"&secondFQParam="+secondFQParam+"&thirdFQParam="+thirdFQParam+
					"&fouthFQParam="+fouthFQParam+"&fifthFQParam="+fifthFQParam+"&refined="+refined;*/
					var navUrl =  $("#contextPath").val()+"/search/searchJob.html?isSorting="+isSorting+
					"&refineKey="+refineKey+"&refineVal="+refineVal+"&refined="+refined+"&freshjobsearch=false";
					$("#TotalRecord").text("");
					$.getJSON(navUrl,function(data) {
						 $.ajaxSetup({ cache: true });
						$.each(data, function(key, val) {
							if (key == "AjaxMSG") {
								$('#findSearchInfo').html(val);
							}
						});										
						processPaginationReq(data, "20");
						$("#TotalRecord").text(data["TotalNoRecords"]);
					});
					$(".otherContent").attr("style","display: none");
					$(".careersContent").attr("style","display: none");
					$(".searchContent").attr("style","display: block");
					
					return true;
				
				}
				
				function clearRecentSearches(){
					$.ajax({url: $("#contextPath").val()+"/search/clearrecentsearches.html",
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
				
				 function loadRecentSearch(searchJobId){
						$.ajax({url: $("#contextPath").val()+"/savedSearches/editSavedSearch.html?searchId="+searchJobId+"&performSearch=performSearch",
							success: function(data){ 
								$.each(data, function(key, val) {
									 if (key == "searchtype" && val == "basic") {
										parent.window.location.href = $("#contextPath").val()+'/search/findJobPage.html';
										parent.$.nmTop().close();
									}									
								}); 								
							},
							error: function(response) {
								alert("Server Error : "+response.status);
							},
							complete: function() {
								// noting to do now
							}
						}); 
					}
				 
				 // select the employers block on selecting alphabets 
					function selectEmployerBlock(id, letter, size){
						// clear classes of links
						for ( var i = 0; i <= size; i++) {
							$("#empKey"+i).removeClass('NameSelected');
						}						
						$("#"+id).addClass('NameSelected');	
						// clear classes of blocks
						var blockLetter = '';
						var blockId = '';
						for ( var i = 0; i < size; i++) {
							$("#empBlockKey"+i).removeClass('NameOrderSelected');
							blockLetter = $("#empBlockKey"+i).attr('key');
							if(blockLetter == letter){
								blockId = "#empBlockKey"+i;
							}
						}
						$(blockId).addClass('NameOrderSelected');
						// The href is added to focus the selected block
						$("#"+id).attr('href',blockId);
					}
					
					/*$('.NameSelectonArea ul li a').click(function () {
						alert("asd");
					    $('.NameSelectonArea ul li a').removeClass('NameSelected');
					    $(this).addClass('NameSelected');
					});*/
					
					// to show careers parts in home page
					function showCareersPart(careerType){
						$.ajaxSetup({ cache: false });
						$.ajax({
								url : $("#contextPath").val()+'/healthcare/showCareersPart.html?careerType='+careerType,
								page : ({}),								
								success : function(page) {
								//	alert(page);
								$(".careersSubContent").html(page);
								$(".otherContent").attr("style","display: none");
								$(".careersContent").attr("style","display: block");
								$(".careersSubContent").attr("style","display: block");
								$(".searchContent").attr("style","display: none");
								if(careerType == 'careers'){
									$("#resourceCarrerId").addClass('LeftContantLinksActive');
									$("#resourceCarrerId").removeClass('LeftContantLinks');
									$("#resourceCarrerId").children(".cursor").hide();
									$("#resourceCarrerId").children(".normalList").show();
								}else if(careerType == 'resumeBuilder'){
									$("#resumeBuilderId").addClass('LeftContantLinksActive');
									$("#resumeBuilderId").removeClass('LeftContantLinks');
									$("#resumeBuilderId").children(".cursor").hide();
									$("#resumeBuilderId").children(".normalList").show();
								}else if(careerType == 'messanger'){
									$("#messaangerId").addClass('LeftContantLinksActive');
									$("#messaangerId").removeClass('LeftContantLinks');
									$("#messaangerId").children(".cursor").hide();
									$("#messaangerId").children(".normalList").show();
								}
								
								},
								error : function(pgdata) {
								},
								complete : function(pgdata) {
									// do nothing for now.
								}
							});
					}
					// to show careers parts in home page
					function showCareersSubPart(careerType){
						$.ajaxSetup({ cache: false });
						$.ajax({
							url : $("#contextPath").val()+'/healthcare/showCareersPart.html?careerType='+careerType,
							page : ({}),								
							success : function(page) {
								//alert(page);
								$(".careersSubContent").html(page);
								$(".otherContent").attr("style","display: none");
								$(".careersContent").attr("style","display: block");
								$(".careersSubContent").attr("style","display: block");
								$(".searchContent").attr("style","display: none");
							},
							error : function(pgdata) {
							},
							complete : function(pgdata) {
								// do nothing for now.
							}
						});
					}				
						 
