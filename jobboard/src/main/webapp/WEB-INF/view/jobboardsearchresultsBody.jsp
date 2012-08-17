<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
		<head>
		
		<script type="text/javascript">
	
	function saveThisSearch() {
		
		
		$.ajax({
			url : '${pageContext.request.contextPath}/savedSearches/saveThisSearch.html',
			success: function(data){ 
				$.each(data, function(key, val) {
					if (key == "NavigationPath") {
						window.location.href = val+".html";
					}
					
					if (key == "LoggedInNavigationPath") {
						$.nmManual(val + '.html');
					}
				}); 
			    if(data.success != null){
			    }
			    if(data.failure != null){
			    }
			},
			error: function(response) {
				alert("Server Error : "+response.status);
			},
			complete: function() {
				
			}
		
		});
	} 
	
</script>
		</head>

		<body>

					
					<div class="row">

						<div class="row marginTop5 paddingBottom05">
							<div class="floatLeft">
								<h1 >
									<span id="TotalRecord"></span> jobs match your search criteria.
								</h1>
							</div>
						</div>


					</div>

					<div class="clearfix"></div>
					<div class="content_columns_search_results">
						<div class="column1">


							<div class="section">
								<h2>Current Search</h2>
								<div class="buttonRow">
									Nurse
									<div class="floatRight">
										<a href="" style="visibility:hidden;"><img src="../resources/images/CloseGray.jpg"
											alt="close" width="15" height="15"> </a>
									</div>
								</div>
								<div class="buttonRow">
									10001
									<div class="floatRight">
										<a href="" style="visibility:hidden;"><img src="../resources/images/CloseGray.jpg"
											alt="close"> </a>
									</div>
								</div>
								<div class="buttonRow">
									25 miles
									<div class="floatRight">
										<a href="" style="visibility:hidden;"><img src="../resources/images/CloseGray.jpg"
											alt="close" width="15" height="15"> </a>
									</div>
								</div>
								<div class="section">
									<div class="SaveSearchButton">
										<a href="#" class="btn_sm orange" id="saveThisSearchId" onclick="saveThisSearch();">Save This Search</a>
									</div>
								</div>
							</div>
							<div class="section">
								<h2>Refine Results</h2>

								<div class="refineResults">

									<span class="refineResultsItem plus">Radius</span>
									<div class="refineResultsSubContent">
										<ul>
											<li><!-- <a href="" ></a> -->5 miles</li>
											<li><!-- <a href="" ></a> -->10 miles</li>
											<li><!-- <a href="" ></a> -->25 miles</li>
											<li><!-- <a href="" ></a> -->50 miles</li>
											<li><!-- <a href="" ></a> -->100 miles</li>
										</ul>
									</div>

									<span class="refineResultsItem plus">Employer</span>
									<div class="refineResultsSubContent">
										<ul>
											<li><!-- <a href="" ></a> -->Allegiant (5)</li>
											<li><!-- <a href="" ></a> -->Nova Care (2)</li>
											<li><!-- <a href="" ></a> -->Mount Sinai (3)</li>
										</ul>
									</div>

									<span class="refineResultsItem plus">State</span>
									<div class="refineResultsSubContent">
										<ul>
											<li><!-- <a href="" ></a> -->Virginia (5)</li>
											<li><!-- <a href="" ></a> -->Maryland (2)</li>
											<li><!-- <a href="" ></a> -->New York (3)</li>
										</ul>
									</div>

									<span class="refineResultsItem plus">City</span>
									<div class="refineResultsSubContent">
										<ul>
											<li><!-- <a href="" ></a> -->Alexandria (5)</li>
											<li><!-- <a href="" ></a> -->Baltimore (2)</li>
											<li><!-- <a href="" ></a> -->Manhattan (3)</li>
										</ul>
									</div>
								</div>


							</div>


						</div>
						<!-- column1 -->

						<div class="column2">

							<div class="searchResults">

								<!-- <div class="searchResultsNavigation">
                            
                                     <div class="searchResultsNavigationColumn1">
                                     	<span class="marginTop5">Results viewable:</span>
                                        <span><select id="results" name="results" class="jb_input4">
                                        <option value="20">20</option>
										<option value="30">30</option>
										<option value="40">40</option>
                                        <option value="50">50</option>
                                        <option value="All">All</option>
										</select></span>
                                        <span class="marginTop5">per page</span>
                                     </div>
                                     
                                     
                                     
                                     <div class="searchResultsNavigationColumn3">&nbsp;&nbsp;&nbsp; </div>
<div class="searchResultsNavigationColumn2">
                                     	<span>Page:</span>
                                        <span class="active">1</span>
                                        <span><a href="">2</a></span>
                                        <span><a href="">3</a></span>
                                        <span><a href="">4</a></span>
                                        <span><a href="">5</a></span>
                                        <span><a href="">6</a></span>
                                        <span><a href="">7</a></span>
                                        <span><a href="">8</a></span>
                                        <span><a href="">9</a></span>
                                        <span><a href="">Next<img src="../resources/images/ArrowRight.png"></a></span>
                                  </div>
                              </div> -->

								<table id="jobSearchResultTable">

									<!-- <div class="searchResultsHeader">
										<ul style="height: 0px;">
											<li class="searchResultsColumn1"></li>
											<li class="searchResultsColumn2"></li>
											<li class="searchResultsColumn3"></li>
											<li class="searchResultsColumn4"></li>
										</ul>
									</div> -->

									<div id="jobSearchResultDiv" class="searchResultsListing">


										<div class="searchResultsItem">
											<table cellpadding="0" cellspacing="0" style="width: 100%"
												border="0" class="display" id="jsonTable">
												<thead>
													<tr >
														<th class="searchResultsColumn1">Job Title</th>
														<th class="searchResultsColumn2">Employer</th>
														<th class="searchResultsColumn3">Location</th>
														<th class="searchResultsColumn4">Date Posted</th>
													</tr>
												</thead>
											</table>
										</div>
										<!-- <div id="pager2"></div> -->

									</div>


								</table>
							</div>
						</div>
						<!-- column2-->

					</div> 
</body>
</html>