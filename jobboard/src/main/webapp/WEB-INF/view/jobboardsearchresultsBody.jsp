<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
		<head>
		
		<script type="text/javascript">
	
	function saveThisSearch() {
		
		var keywords = $.trim($("#keywords").val());
		$.ajax({url : "${pageContext.request.contextPath}/savedSearches/saveThisSearch.html?keywords="+keywords,
			success: function(data){ 
				$.each(data, function(key, val) {
					if (key == "NavigationPath") {
						window.location.href = val+".html?page=jobSeeker";
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
	
</script>
		</head>

		<body>

					
					<div class="row">

						<div class="row marginTop5 paddingBottom05">
						<div id="errorMsg"></div>
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

								<table id="jobSearchResultTable">

									

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