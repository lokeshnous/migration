<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>ADVANCE Heathcare Jobs</title>

		<!-- STYLESHEETS -->
		<link href="../resources/css/JB.css" rel="stylesheet" type="text/css" />
		<link href="../resources/css/jquery.megamenu.css" rel="stylesheet" type="text/css" />
		<link href="../resources/css/SliderStyles.css" rel="stylesheet" type="text/css">
        
<!--[if IE]>
	<link href="../resources/css/ie.css" rel="stylesheet" type="text/css">
<![endif]-->


		<!-- JAVASCRIPT FILES -->
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
		<script type="text/javascript" src="../resources/js/jquery.cycle.all.min.js"></script>
		<script type="text/javascript" src="../resources/js/slider.js"></script>
		<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>
		<script type="text/javascript" src="../resources/js/common/common.js"></script>
		<script type="text/javascript">
		
		
		    $(document).ready(function(){
		    	
		    	$(".megamenu").megamenu();
		    	
		    	var x = $("#results").val();
		    	$("#rows").val(x);
		    	$("#start").val("0");
		    	
		    	//$('#jobSearchResultTable').dataTable();
		    	
			});
		    
		    
		    
		   /*  jQuery(document).ready(function (){ 
		    	
		    	$("#submitval").live('click',function(){
		    		alert('a');
		    		
		    	$.ajax({
		    		  url: "http://localhost:8083/jobboard/jobsearchactivity/findJobSearch.html",
		    		  dataType: 'json',
		    		  success: function(data) {
		    			  $.each(data.jsonRows, function(key,val) {
		    				  $(".searchResultsItem").append('<ul id="orange-bg" class="searchResultsJobInfo closed orange-bg"><li class="searchResultsColumn1">'+val.JobTitle+'</li><li class="searchResultsColumn2">'+val.Company+'</li><li class="searchResultsColumn3">'+val.City+', '+val.City+'</li><li class="searchResultsColumn4">'+val.PostedDate.date+'</li></ul>');
		    			  });
		    		  }
		    		});
		    	});
				
		    });  */	
		    
		    
		    jQuery(document).ready(function (){ 
		    	
			    $("#submitval").click(function(event) {
			    	
			    	var x = $("#results").val();
			    	$("#rows").val(x);
			    	$("#start").val("0");
			    	
			    	
					var keywords = $("#keywords").val();
					var cityState = $("#cityState").val();
					var radius = $("#radius").val();
					var rows = $("#rows").val();
					var start = $("#start").val();
					//alert( getBaseURL());
						/* $.ajax({url: getBaseURL()+"/jobsearchactivity/findJobSearch.html?keywords="+keywords+"&cityState="
								+cityState+"&radius="+radius+"&rows="+rows+"&start="+start,
								
								success: function() {
									  
							  },
							error: function() {
							},
							complete: function() {
							}
						}); */
				
						
						//alert("hi");
						
						
						
					    $.get(getBaseURL()+"/jobsearchactivity/findJobSearch.html?keywords="+keywords+"&cityState="
								+cityState+"&radius="+radius+"&rows="+rows+"&start="+start, function(data){
					    	//alert(data);
					    	$(".searchResultsItem").empty();
					    	 $.each(data.jsonRows, function(key,val) {
					    		 //$(".searchResultsItem").clear();
					    		 
			    				  /* $(".searchResultsItem").append('<ul id="orange-bg" class="searchResultsJobInfo closed orange-bg"><li class="searchResultsColumn1">'+val.JobTitle+'</li><li class="searchResultsColumn2">'+val.Company+'</li><li class="searchResultsColumn3">'+val.City+', '+val.City+'</li><li class="searchResultsColumn4">'+val.PostedDate+'</li></ul>'); */
			    				  $(".searchResultsItem").append('<ul id="orange-bg" class="searchResultsJobInfo closed orange-bg"><li class="searchResultsColumn1">'+val.JobTitle+'</li><li class="searchResultsColumn2">'+val.Company+'</li><li class="searchResultsColumn3">'+val.City+', '+val.City+'</li><li class="searchResultsColumn4">'+ (new Date(val.PostedDate.time))+'</li></ul>');
			    				  //alert(data.jsonRows[key]);
			    				  //alert(key);
			    				  //delete data.jsonRows[key];
			    				  
			    				  
			    			  });
					    	
					    });
				});
			    
			    
			    
			    
		    });
		    
		    
		    
		    
		    
		    
		</script>
		<!-- js files for modalpopup------------------------------------------------- -->
<script src="../resources/js/jquery-1.7.1.js"></script>
<script src="../resources/js/jquery-1.7.1.min.js"></script>
		<script src="../resources/jquery.nyroModal/js/popup.js"></script>
		<script src="../resources/jquery.nyroModal/js/jquery.nyroModal.custom.js"></script>
        <script src="../resources/jquery.nyroModal/js/jquery.nyroModal.custom.min.js"></script>
 	    <link href="../resources/jquery.nyroModal/styles/nyroModal.css" rel="stylesheet" type="text/css">

        <style type="text/css" media="screen">
           @import url("${pageContext.request.contextPath}/resources/jquery.nyroModal/styles/nyroModal.css");
        </style>
<!-- -------------------------------------------------------------------------- -->
		
	
	
        <!-- JAVASCRIPT FILES -->
		<script type="text/javascript" src="../resources/js/jquery.cycle.all.min.js"></script>
		<script type="text/javascript" src="../resources/js/slider.js"></script>
		<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>
		<script type="text/javascript">
			jQuery(document).ready(function() {
				jQuery(".megamenu").megamenu();
				$(".saveThisPopup").displaypopup(".saveThisPopup","775","252");

			});
		</script>
        <script type="text/javascript" src="../resources/js/expandCollapse.js"></script>
		</head>

		<body class="job_board">
<div class="ad_page_top"> <img src="../resources/images/ads/banner_ad_fpo.png" /> </div>
<div class="main_wrapper_outside">
          <div class="main_wrapper_inside">
    <div class="main">
              <div class="row">
              <div class="header_wrapper">

					 <a href="">
          <div class="logo"></div>
          </a>
					<div class="headerLoginSection">
					<div class="headerLoginSectionColumns width205">
					<span class="boldText">Job Seeker:</span><br>
		  <div class="PopUpToolTip"><a href="#">Why <strong>advance</strong>?</a>
          <span class="classic01">
         	<p class="FontWeight marginBottom10">When you sign up, ADVANCE gives you:</p>
            <div class="FontWeight FontSize12 OrangeDot FontBlack">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Access to thousands of healthcare job opportunities
            </div>
            <div class="FontWeight FontSize12 OrangeDot FontBlack">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The best healthcare content you can get anywhere
            </div>
            <div class="FontWeight FontSize12 OrangeDot FontBlack">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hours of informative and entertaining multimedia
            </div>
            <div class="FontWeight FontSize12 OrangeDot FontBlack">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The latest news, articles, product reviews and much more!
            </div>
            <p class=" marginTop10">And it's all FREE!</p>
          </span>
          </div> <div class="floatleft"><span> <a href="">Login</a> | <a href="">Sign Up</a> | </span></div></div><!-- loginHeader -->
                    <div class="headerLoginSectionColumns">
					<span class="boldText">Employer:</span><br>
                    	<a href="">Login</a> | <a href="">Post Jobs</a>
					</div><!-- loginHeader -->
					<div class="headerLoginSectionColumns">
					<span class="boldText">Ad Agency:</span><br>
						 <a href="">Login</a> | <a href="">Post Jobs</a>
					</div><!-- loginHeader -->
                    </div><!-- loginHeader -->
					<!-- loginHeader -->

				</div></div>
              <!-- header_wrapper -->
              
              <div class="row"><div id="nav">
        <ul class="megamenu">
                  <li> <a href="javascript:">Magazines</a>
            <div class="megamenuContainer">
                      <div class="column"> <a href="http://nursing.advanceweb.com/">
                        <p>Nurses</p>
                        </a> <a href="http://physical-therapy.advanceweb.com/">
                        <p>Physical Therapy and Rehab Medicine</p>
                        </a> <a href="http://occupational-therapy.advanceweb.com/">
                        <p>Occupational Therapy Practitioners</p>
                        </a> <a href="http://imaging-radiation-oncology.advanceweb.com/">
                        <p>Imaging & Radiattion Oncology</p>
                        </a> <a href="http://audiology.advanceweb.com/">
                        <p>Hearing Practice Management</p>
                        </a> </div>
                      <div class="column"> <a href="http://speech-language-pathology-audiology.advanceweb.com/">
                        <p>Speech-Language Pathologists & Audiologists</p>
                        </a> <a href="http://respiratory-care-sleep-medicine.advanceweb.com/">
                        <p>Respiratory Care and Sleep Medicine</p>
                        </a> <a href="http://laboratory-manager.advanceweb.com/">
                        <p>Administrators of the Laboratory</p>
                        </a> <a href="http://laboratorian.advanceweb.com/">
                        <p>Medical Laboratory Professionals</p>
                        </a> <a href="http://health-information.advanceweb.com/">
                        <p>Health Information Professionals</p>
                        </a> </div>
                      <div class="column"> <a href="http://long-term-care.advanceweb.com/">
                        <p>Long-Term Care Management</p>
                        </a> <a href="http://nurse-practitioners-and-physician-assistants.advanceweb.com/">
                        <p>NPs & PAs</p>
                        </a> <a href="http://healthcare-executive-insight.advanceweb.com/">
                        <p>Executive Insight</p>
                        </a> </div>
                    </div>
          </li>
                  <li> <a href="javascript:">Job Search</a>
            <div class="megamenuContainer">
                      <div class="column"> <a href="http://health-care-jobs.advanceweb.com/">
                        <p>Quick Search</p>
                        </a> <a href="http://health-care-jobs.advanceweb.com/ResumeBuilder/Default.aspx">
                        <p>Resume Builder</p>
                        </a> <a href="http://health-care-jobs.advanceweb.com/Salary/Default.aspx">
                        <p>Salary Calculator</p>
                        </a> <a href="http://health-care-jobs.advanceweb.com/AdvanceMessenger/Default.aspx">
                        <p><i>ADVANCE</i> Messenger</p>
                        </a> <a href="http://health-care-jobs.advanceweb.com/careers/article.aspx?cc=251059">
                        <p>Career Resource Center</p>
                        </a> <a href="http://health-care-jobs.advanceweb.com/FeaturedFacilities/Default.aspx">
                        <p>Featured Facilities</p>
                        </a> <a href="http://health-care-jobs.advanceweb.com/Default.aspx">
                        <p>Home</p>
                        </a> </div>
                    </div>
          </li>
                  <li> <a href="javascript:">Education</a> </li>
                  <li> <a href="javascript:">Events</a> </li>
                  <li> <a href="javascript:">Community</a> </li>
                  <li> <a href="javascript:">Healthcare Shop</a> </li>
                  <li> <a href="javascript:">Custom Promotions</a> </li>
                </ul>
      </div></div>
              <!--nav-->
              <div class="row">
              <div class="job_search_main job_search_main_height">
                  <%-- <form method=""> --%>
                 <%--  <form:form method="GET" action="findJobSearch.html" commandName="jobSearchResultForm">  --%>
                  <form:form method="" action="" commandName="jobSearchResultForm"> 
            <div class="search_form">
            
	                      <h1 class="marginBottom5">Search <span>60,262</span> Healthcare Jobs</h1>
	                      <form:input path="keywords"  id="keywords" cssClass="jb_input1" />
	                      <div class="toolTipBefore"><label for="keywords">Job Title, Keywords, Job ID, Company Name </label></div> <div class="toolTip"><span class="classic"><p>Type in your search criteria here. Include any group of terms related to your desired position. Click on 'Advanced Search' below for more options.</p></span></div>
	                      <br/>
	                      <div class="input_grp1 marginTop10">
	                       <form:input path="cityState"  id="cityState" cssClass="jb_input2" />
	                	  <!-- <input type="text" name="cityState" id="cityState" class="jb_input2" /> -->
	                <br/>
	                <div class="toolTipBefore"><label for="cityState">City and State or ZIP Code </label></div> <div class="toolTip"><span class="classic"><p>Enter the city and state or zip code of the location you want to search. Then select a radius to expand your search up to 100 miles from your starting point.</p></span></div>
	              </div>
	                      <div class="input_grp2 marginTop10">
	                <form:select path="radius" id="radius" cssClass="jb_input3">
	                	<form:option label="--" value=""/>
	                	<!-- USE <form:options/> while dynamically populating the values  -->
	                	<form:option label="5" value="5"/>
	                	<form:option label="10" value="10"/>
	                	<form:option label="25" value="25"/>
	                	<form:option label="50" value="50"/>
	                	<form:option label="100" value="100"/>
	                </form:select>
	                <label for="radius">Radius</label>
	              </div>
	              
	              <div class="clearfix"></div>
	                      <!-- <a href="#" class="btn_sm orange jb_search_submit">Find Jobs</a> -->
	                    <input type="button" id= "submitval" value="Find Jobs" class="btn_sm orange jb_search_submit" />
	                    <!-- <input type="submit" id= "submit" value="Find Jobs" class="btn_sm orange jb_search_submit" /> -->
             <%-- </form:form>     --%>  
                      
                      <a href="advanceSearch.html">Advanced Search</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">Post Your Resume</a></div>
            <!-- search_form -->
            
            <div class="search_info_box1">
             <div class="rowPadding borderBottomDotted">
								<span class="rowEvenSpacing capsText">MY RECENT SEARCHES:</span><br>
								<a href="#">Clear All</a> | <a href="#">See All</a>
                                </div>
                                 <div class="rowPadding borderBottomDotted">
                                    May 16, 2012, 7:00am<br>
                                    Search by: <a href="#">Physical Therapist / Philadelphia, PA</a>
                                </div>
                                <div class="rowPadding borderBottomDotted">
                                   May 16, 2012, 7:00am<br>
                                    Search by: <a href="#">Physical Therapist / Philadelphia, PA</a>
                                </div>
                                <div class="rowPadding">
                                    May 16, 2012, 7:00am<br>
                                    Search by: <a href="#">Physical Therapist / Philadelphia, PA</a>
                                </div>
                                
                                
					</div>
            <!-- search_info_box1 -->
            
            <div class="search_info_box2">
                      <ul>
                <li><span class="uppercase bold">My Recent Searches:</span><br/>
                          <a href="">Clear All</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">See All</a></li>
                <li>May 16, 2012, 7:00 am<br/>
                          Search by: <span class="jb"><a href="">Physical Therapist</a></span></li>
                <li>May 17, 2012, 8:00 am<br/>
                          Search by: <span class="jb"><a href="">Pediatric Nurse</a></span></li>
                <li class="last">May 18, 2012, 9:00 am<br/>
                          Search by: <span class="jb"><a href="">ER Nurse</a></span></li>
              </ul>
                    </div>
            <!-- search_info_box2 -->
            
            <div class="browse_bar bold"> <span>BROWSE JOBS:</span>&nbsp;&nbsp;<a href="">By Job Title</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">By Employer</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">By Location</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="">By Employment Type</a> </div>

            <!-- browse_bar -->
            
            <form:hidden path="start" id="start"/>
            <form:hidden path="rows" id="rows"/>
            
            
            </form:form>    
                <%-- </form> --%>
                </div>
              <div class="ad_col_right"> <img src="../resources/images/ads/300x250ad1.png" class="paddingBottom0" />
        
        <br class="clearfix" />
      </div></div>
              <!-- ad_col_right -->
              <div class="clearfix"></div>
      <div class="row ">
        <div class="row marginTop5 paddingBottom05"><div class="floatLeft"><h1 class="FontSize24">200 Nurse jobs match your search criteria.</h1></div> </div>
       	

      </div>
              
             <div class="clearfix"></div>
             <div class="content_columns_search_results">
                        <div class="column1">
                        
                        
                        <div class="section">
                        <h2>Current Search</h2>
                        <div class="buttonRow">Nurse <div class="floatRight"><a href=""><img src="../resources/images/CloseGray.jpg" alt="close" width="15" height="15"> </a></div>
                          </div><div class="buttonRow">
                          10001
                          <div class="floatRight"><a href=""><img src="../resources/images/CloseGray.jpg" alt="close"> </a></div> 
                          </div><div class="buttonRow">
                         25 miles 
                         <div class="floatRight"><a href=""><img src="../resources/images/CloseGray.jpg"  alt="close" width="15" height="15"> </a></div></div>
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
                            
                            
                        </div><!-- column1 -->
                    
                        <div class="column2">
			
							<div class="searchResults">
                            
                            	<div class="searchResultsNavigation">
                            
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
                              </div>
                            
                            <table id="jobSearchResultTable">
                            
                             <div class="searchResultsHeader">
                                        <ul>
                                        <li class="searchResultsColumn1">
                                            Job Title
                                        </li>
                                        <li class="searchResultsColumn2">
                                            Employer
                                        </li>
                                        <li class="searchResultsColumn3">
                                            Location
                                        </li>
                                        <li class="searchResultsColumn4">
                                            Date Posted
                                        </li>
                                        </ul>
                                </div> 
                                
                                 <div id="jobSearchResultDiv" class="searchResultsListing">
                                
                                    
                                    <div class="searchResultsItem">
                                                                           <div class="searchResultsItem">
                                        <ul id="orange-bg" class="searchResultsJobInfo closed orange-bg">
                                            <li class="searchResultsColumn1">
                                                Nurse Team Lead/RN Manager
                                            </li>
                                            <li class="searchResultsColumn2">
                                                NOVA Medical Group
                                            </li>
                                            <li class="searchResultsColumn3">
                                                Ashburn, VA
                                            </li>
                                            <li class="searchResultsColumn4">
                                                07/1${isJobAction}2/2012
                                            <c:if test="${isJobAction}"><div style="color: red">${jobActionInfo}</div></c:if>
                                            </li>
                                        </ul>
                                        <div class="searchResultsSubContent">
                                            
                                            <p class="searchResultsSubContentJobDescription"><span class="bold">Job Description:</span> Busy multi-specialty Medical Group/Urgent Care Center seeks Nursing Team Leader/RN Manager to coordinate and supervise nursing staff in our Ashburn Facility. Qualified candidate will be an RN/LPN and must have a proven track record of...</p>
                                            <div class="searchResultsSubContentButtonArea">
                                                <div class="searchResultsSubContentButtons">
                                                    <a href="applyJob.html?id=13100" class="btn_sm white">Apply</a>
                                                </div>
                                                <div class="searchResultsSubContentButtons">
                                                    <a href="viewJobDetails.html?id=13101" class="btn_sm white">View Details</a>
                                                </div>
                                                <div class="searchResultsSubContentButtons">
                                                    <a href="saveThisJob.html?id=13100" target="_blank" class="saveThisPopup btn_sm white">Save This Job</a>
                                                </div>
                                             </div>
                                            <div class="featured_empButton"><a href=""><img src="../resources/images/FeaturedEmp.png" alt="featured emp Button" width="164" height="23"></a> </div>
                                            
                                          <div class="searchResultsSubContentShare">
                                           <span class="marginTop3 floatLeft"> Send to Friend:&nbsp;</span><span><a href=""><img src="../resources/images/email.png"></a></span>
                                            </div>
                                            
                                            <div class="searchResultsSubContentShare">
                                            <span class="marginTop3 floatLeft">Share:&nbsp;</span> <span><a href=""><img src="../resources/images/fbook_sm.png"></a></span> <span><a href=""><img src="../resources/images/L_In_sm.png"></a></span> <span><a href=""><img src="../resources/images/twitter_sm.png"></a></span>
                                            </div>
                                            
                                            
                                          </div>
                                    </div>
                                        <!--
                                        <div class="searchResultsSubContent">
                                            
                                            <p class="searchResultsSubContentJobDescription"><span class="bold">Job Description:</span> Busy multi-specialty Medical Group/Urgent Care Center seeks Nursing Team Leader/RN Manager to coordinate and supervise nursing staff in our Ashburn Facility. Qualified candidate will be an RN/LPN and must have a proven track record of...</p>
                                            
                                            <div class="searchResultsSubContentButtonArea">
                                                <div class="searchResultsSubContentButtons">
                                                    <a href="" class="btn_sm white">Apply</a>
                                                </div>
                                                <div class="searchResultsSubContentButtons">
                                                    <a href="" class="btn_sm white">View Details</a>
                                                </div>
                                                <div class="searchResultsSubContentButtons">
                                                    <a href="" class="btn_sm white">Save This Job</a>
                                                </div>
                                             </div>
                                            <div class="featured_empButton"><a href=""><img src="../resources/images/FeaturedEmp.png" alt="featured emp Button" width="164" height="23"></a> </div>
                                            
                                          <div class="searchResultsSubContentShare">
                                           <span class="marginTop3 floatLeft"> Send to Friend:&nbsp;</span><span><a href=""><img src="../resources/images/email.png"></a></span>
                                            </div>
                                            
                                            <div class="searchResultsSubContentShare">
                                            <span class="marginTop3 floatLeft">Share:&nbsp;</span> <span><a href=""><img src="../resources/images/fbook_sm.png"></a></span> <span><a href=""><img src="../resources/images/L_In_sm.png"></a></span> <span><a href=""><img src="../resources/images/twitter_sm.png"></a></span>
                                            </div>
                                            
                                            
                                          </div>
                                         -->
                                    </div>
                                   
                            
                            </div>
                            
                            
                            </table>
                            
                            <div class="searchResultsNavigation searchResultsNavigationBottom">
                            
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
                              </div>
                            
                            </div>
			
			  
			    
                       
                            
                        </div><!-- column2-->
                        
                        
                        
					<BR class="clearfix">
                    </div>
       
              <div class="ad_wrapper"> <img src="../resources/images/ads/banner_ad_fpo.png" /> </div>
            </div>
    <!-- main --> 
    
  </div>
          <!-- end main_wrapper_inside --> 
        </div>
<!-- end main_wrapper_outside -->
<div class="footer_wrapper">
          <div class="container1">
    <h4>Professions:</h4>
    <ul>
              <li><a href="#">Nursing</a></li>
              <li><a href="#">Imaging & Radiation</a></li>
              <li><a href="#">Oncology</a></li>
              <li><a href="#">Physical Therapy and Rehab Medicine</a></li>
              <li><a href="#">Occupational Therapy</a></li>
              <li><a href="#">Speech-Language Pathology</a></li>
              <li><a href="#">Audiology</a></li>
              <li><a href="#">Hearing Practice Management</a></li>
              <li><a href="#">Long-Term Care Managament</a></li>
              <li><a href="#">Respiratory Care</a></li>
              <li><a href="#">Sleep Medicine</a></li>
              <li><a href="#">Labortory</a></li>
              <li><a href="#">Health Information</a></li>
              <li><a href="#">Nurse Practitioners</a></li>
              <li><a href="#">Physician Assistants</a></li>
              <li><a href="#">Healthcare Executives</a></li>
            </ul>
  </div>
          <!-- end container1 -->
          
          <div class="container2">
    <h4>Content:</h4>
    <ul>
              <li><a href="#">News</a></li>
              <li><a href="#">Features</a></li>
              <li><a href="#">Multimedia</a></li>
              <li><a href="#">Buyers Guide</a></li>
              <li><a href="#">Community</a></li>
              <li><a href="#">Downloads</a></li>
            </ul>
  </div>
          <!-- end container2 -->
          
          <div class="container3">
    <h4>Services:</h4>
    <ul>
              <li><a href="#">ADVANCE Healthcare Jobs</a></li>
              <li><a href="#">Subscribe</a></li>
              <li><img src="../resources/images/email.png" class="foot_icon"/><a href="#">Sign Up for Enewsletter</a></li>
              <li><img src="../resources/images/fbook_sm.png" class="foot_icon"/><a href="#">Connect on Facebook</a></li>
              <li><img src="../resources/images/L_In_sm.png" class="foot_icon"/><a href="#">Connect on LinkedIn</a></li>
              <li><img src="../resources/images/twitter_sm.png" class="foot_icon"/><a href="#">Connect on Twitter</a></li>
              <li><a href="#">Download the Mobile App</a></li>
              <li><a href="#">Order Promotional Items</a></li>
            </ul>
  </div>
          <!-- end container3 -->
          
          <div class="container4">
    <h4>More from ADVANCE:</h4>
    <ul>
              <li><a href="#">ADVANCE Heathcare Shop</a></li>
              <li><a href="#">ADVANCE Custom Promotions</a></li>
              <li><a href="#">ADVANCE Heathcare Jobs</a></li>
              <li><a href="#">ADVANCE Job Fairs</a></li>
              <li><a href="#">ADVANCE Continuing Ediucation</a></li>
              <li><a href="#">ADVANCE Custom Publishing</a></li>
            </ul>
  </div>
          <!-- end container4 -->
          
          <div class="container5">
    <h4>Corporate:</h4>
    <ul>
              <li><a href="#">About Us</a></li>
              <li><a href="#">Contact Us</a></li>
              <li><a href="#">Advertise with Us</a></li>
              <li><a href="#">Work for Us</a></li>
              <li><a href="#">Privacy Policy</a></li>
              <li><a href="#">Term of Service</a></li>
              <li><a href="#">Help</a></li>
            </ul>
  </div>
          <!-- end container5 --> 
          
          <br class="clearfix" />
          <p class="copyright">&copy; 2012 Merion Matters 2900 Horizon Drive King of Prussia PA 19406 800-355-5627</p>
        </div>
<!-- footer_wrapper -->

</body>
</html>