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
			var currentUrl = window.location.pathname;
			$.ajax({
				url : $("#contextPath").val()+'/jobsearch/saveThisJob.html?id='+jobId+"&currentUrl=null",
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
												processPaginationReq(data, "20");
												
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
						//$.each(data, function(key, val) {
							
							// print if any validation messages
							//if (key == "AjaxMSG") {
								$('#findSearchInfo').html(data.AjaxMSG);
								//return false;
							//}
						//});	
						
						//$('#ad_page_top').append(data.adPageTop);
						//console.log(data.adPageTop);
						//OX_ads.push(data.adPageTop);
						//$.each(data.adPageTop, function(key, val) {
							// print if any validation messages
							//if (key == "adPageTop") {
								//document.getElementById("ad_page_top").innerHTML=val;
								
								//$('#ad_page_top').html('');
								//alert(val);
								//$('#ad_page_top').html("<script type=\"text/javascript\"> document.write('<script src=\"http://ox-d.advanceweb.com/w/1.0/jstag\"></script>'); </script> ");
								/*$('#ad_page_top').writeCapture().html("<script type=\"text/javascript\"> document.write('\<script src=\"http://ox-d.advanceweb.com/w/1.0/jstag\" \>\<\script>ppasd'); </script> ");*/
								//$('#ad_page_top').writeCapture().html("<script type=\"text/javascript\"> document.write('\<script src=\"http://ox-d.advanceweb.com/w/1.0/jstag\" \>\<\script>ppasd'); </script> ");
								//$.writeCapture.write(document.getElementById('ad_page_top'),"<script >alert('hi');</script>");
								//debugger;
								//$('#ad_page_top').text(val);
								//$('#ad_page_top').writeCapture().html(val);
								
								//$('#ad_page_top').writeCapture().html(val);
								//var pp = "<script>document.write('<script src="+"http://ox-d.advanceweb.com/w/1.0/jstag"+"></script>');</script>";
								//alert(pp);
								//$('#ad_page_top').writeCapture().html(val);
								//$('#ad_page_top').writeCapture().html($('#ad_page_top').html());
//								$('#ad_page_top').writeCapture().html('<script type="text/javascript">'+
//										'document.write('+'<script src="http://ox-d.advanceweb.com/w/1.0/jstag"></script>'+');'+
//								'</script>');
//								$('#ad_page_top').writeCapture().html('<script type="text/javascript">if (!window.OX_ads) { OX_ads = []; }'+
//										'OX_ads.push({ "auid" : "284880" ,"vars":{"keyword" : "Nursing"}});'+
//								'</script>');
								//$.writeCapture.write(document.getElementById('ad_page_top'),val);
								//$.writeCapture.autoAsync();
								//postscribe('#ad_page_top', val);
							//}
						//});
								processPaginationReq(data, "20");
					//$("#TotalRecord").text(data["TotalNoRecords"]);
					});
					$("#selectedCompany").val("");
					$("#selectedState").val("");
					$("#selectedCity").val("");
					$(".otherContent").attr("style","display: none");
					$(".searchContent").attr("style","display: block");
					
					return false;
				 
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
						processPaginationReq(data, pageSize);
					});
				}
				
				function applyFilter() {
					var pageSize = $("#noOfPage").val();
					isSorting = false;
					refined= false;
					var navUrl =  $("#contextPath").val()+"/jobsearch/searchJob.html?pageSize="+ pageSize +"&isSorting="+isSorting
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
					var navUrl =  $("#contextPath").val()+"/jobsearch/searchJob.html?pageSize="+ pageSize +"&isSorting="+isSorting
					+"&freshjobsearch=false"+"&refined="+refined;
					var formData= $("#jobSearchResultFormId").serialize()+$("#jobSearchResultHeaderFormId").serialize();
					$.getJSON(navUrl, formData, function(data) {
						processPaginationReq(data, pageSize);
					});
				}
				
				function getPage(page, begin) {
					var pageSize = $("#noOfPage").val();
					refined= false;
					var navUrl =  $("#contextPath").val()+"/jobsearch/searchJob.html?pageSize="+ pageSize+ "&page="+page
					+"&isSorting="+isSorting+"&freshjobsearch=false"+"&next="+begin+"&refined="+refined;
					var formData= $("#jobSearchResultFormId").serialize()+$("#jobSearchResultHeaderFormId").serialize();
					$.getJSON(navUrl, formData, function(data) {
						processPaginationReq(data, pageSize);
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
						processPaginationReq(data, pageSize);
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
						processPaginationReq(data, pageSize);
					});
					
				}
				
				function processPaginationReq(data, pageSize){
					$.ajaxSetup({ cache: false });
					$.ajax({
						url : $("#contextPath").val()+'/jobsearch/jobboardsearchresultsBody.html',
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
						}
					}
					);
					getHistory();
					// code to paste ads
					var count = 1 ;
					// populate the ads for grid
					if(data.adPageCenterMiddleList != 'undefined'){
					$.each(data.adPageCenterMiddleList, function(key, val) {
							var id = '#ad_wrapper'+count;
							//$(id).html(val);
							window.setTimeout(function() { 
								renderAd(val, id); 
								}, 100);

//							setTimeout(renderAd,100,val, id);
							//document.getElementById(id).innerHTML="pramoda";
							count = count+1;
					});
					}
					// populate the ads for leader banner
					if(data.adPageTop != 'undefined'){
					window.setTimeout(function() { 
						renderAd(data.adPageTop, ".ad_page_top"); 
						}, 100);
					}
					// populate the ads for bottom banner
					if(data.adPageBottom != 'undefined'){
					window.setTimeout(function() { 
						renderAd(data.adPageBottom, ".ad_wrapper"); 
						}, 100);
					}
					// populate the ads for bottom banner
					if(data.adPageRightTop != 'undefined'){
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
					//setTimeout(renderAd,100,data.adPagerighttop, ".ad_wrapper");	
					//setTimeout(renderAd,100,data.adPagerightBtm, ".ad_wrapper");	
				}
				
				function renderAd(val, id){
					$(id).html(val).submit();
				}

				
				function getHistory(){
					$.ajax({
						url : $("#contextPath").val()+'/jobsearch/jobboardSearchesHistory.html',
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
					getHistory();
				};
				function saveThisSearch() {
					$.ajax({url : $("#contextPath").val()+"/savedSearches/saveThisSearch.html?keywords="+keywords,
						success: function(data){ 
							$.each(data, function(key, val) {
								if (key == "NavigationPath") {
									alert("Search saved successfully!.Access saved search criterias using \"My saved searches\" in dashboard.");
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
					var currentUrl = window.location.pathname;
					$.ajax({
						url : $("#contextPath").val()+'/jobsearch/saveThisJob.html?id='+jobId+"&currentUrl=null",
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
						processPaginationReq(data, "20");
						$("#TotalRecord").text(data["TotalNoRecords"]);
					});
					$(".otherContent").attr("style","display: none");
					$(".searchContent").attr("style","display: block");
					
					return true;
				
				}
				
				function clearRecentSearches(){
					$.ajax({url: $("#contextPath").val()+"/jobsearch/clearrecentsearches.html",
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
										parent.window.location.href = $("#contextPath").val()+'/jobsearch/findJobPage.html';
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
						
					}
					
					/*$('.NameSelectonArea ul li a').click(function () {
						alert("asd");
					    $('.NameSelectonArea ul li a').removeClass('NameSelected');
					    $(this).addClass('NameSelected');
					});*/
						 
