<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
		<head>
		</head>

		<body class="job_board">
					<div class="clearfix"></div>
					<div class="row ">
						<div class="row marginTop5 paddingBottom05">
							<div class="floatLeft">
								<h1 class="FontSize24">
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
										<a href=""><img src="../resources/images/CloseGray.jpg"
											alt="close" width="15" height="15"> </a>
									</div>
								</div>
								<div class="buttonRow">
									10001
									<div class="floatRight">
										<a href=""><img src="../resources/images/CloseGray.jpg"
											alt="close"> </a>
									</div>
								</div>
								<div class="buttonRow">
									25 miles
									<div class="floatRight">
										<a href=""><img src="../resources/images/CloseGray.jpg"
											alt="close" width="15" height="15"> </a>
									</div>
								</div>
								<div class="section">
									<div class="SaveSearchButton">
										<a href="" class="btn_sm orange">Save This Search</a>
									</div>
								</div>
							</div>
							<div class="section">
								<h2>Refine Results</h2>

								<div class="refineResults">

									<span class="refineResultsItem plus">Radius</span>
									<div class="refineResultsSubContent">
										<ul>
											<li><a href="">5 miles</a></li>
											<li><a href="">10 miles</a></li>
											<li><a href="">25 miles</a></li>
											<li><a href="">50 miles</a></li>
											<li><a href="">100 miles</a></li>
										</ul>
									</div>

									<span class="refineResultsItem plus">Employer</span>
									<div class="refineResultsSubContent">
										<ul>
											<li><a href="">Allegiant (5)</a></li>
											<li><a href="">Nova Care (2)</a></li>
											<li><a href="">Mount Sinai (3)</a></li>
										</ul>
									</div>

									<span class="refineResultsItem plus">State</span>
									<div class="refineResultsSubContent">
										<ul>
											<li><a href="">Virginia (5)</a></li>
											<li><a href="">Maryland (2)</a></li>
											<li><a href="">New York (3)</a></li>
										</ul>
									</div>

									<span class="refineResultsItem plus">City</span>
									<div class="refineResultsSubContent">
										<ul>
											<li><a href="">Alexandria (5)</a></li>
											<li><a href="">Baltimore (2)</a></li>
											<li><a href="">Manhattan (3)</a></li>
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

									<div class="searchResultsHeader">
										<ul style="height: 0px;">
											<li class="searchResultsColumn1"></li>
											<li class="searchResultsColumn2"></li>
											<li class="searchResultsColumn3"></li>
											<li class="searchResultsColumn4"></li>
										</ul>
									</div>

									<div id="jobSearchResultDiv" class="searchResultsListing">


										<div class="searchResultsItem">
											<table cellpadding="0" cellspacing="0" style="width: 100%"
												border="0" class="display" id="jsonTable">
												<thead>
													<tr class="searchResultsHeader">
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



						<BR class="clearfix">
					</div> 
</body>
</html>