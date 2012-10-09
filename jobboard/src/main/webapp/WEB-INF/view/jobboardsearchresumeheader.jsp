<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>

</head>

<body class="job_board">

	<div class="mainTwo">
		<!-- header_wrapper -->
		<!--nav-->
		<!--Start:MidContant-->
		<div class="row">
			<form:form method="" action="" commandName="searchResumeForm">
				<div class="job_search_Resume">

					<div class="row ">
						<div class="row marginTop10">
							<h2 class="sectionSubHeader">
								Search Resumes
								</h3>
						</div>
						<div class="row marginTop10 marginBottom10">
							<div class=" floatLeft  width255">
								<form:input path="keywords" maxlength="60" id="keywords"
									class="jb_input2" />
								<div class="floatLeft">
									<div class="toolTipBefore">
										<label for="keywords">Keywords </label>
									</div>
									<div class="toolTip">
										<span class="classic"><p>You can enter a job title,
												specialty, skill or any other keywords that describe the
												position you're trying to fill. Then select an option from
												the drop-down menu to match your exact phrase or any of the
												individual words you entered.</p></span>
									</div>
								</div>
							</div>
							<div class="floatLeft marginLeft10">

								<form:select path="phrase" id="phrase" class="jb_input3 margin0">
									<form:option label="Match exact phrase" value="Match exact phrase"/>
									<form:option label="Match any word" value="Match any word"/>
								</form:select>
							</div>
							<div class="FloatLeft marginLeft10 width255">

								<form:input path="cityState" id="cityState"
									class="jb_input2" />

								<div class="toolTipBefore">
									<label for="city_state">City and State or ZIP Code</label>
								</div>
								<div class="toolTip">
									<span class="classic"><p>Narrow your search to
											job-seekers in a specific area by entering the location here.
											Then select a search radius in the following drop-down menu.</p></span>
								</div>
							</div>
							<div class="floatLeft marginLeft10">

								<form:select path="radius" id="radius" class="jb_input3">
									<form:option label="--" value="0" />
									<form:option label="5 Miles" value="5" />
									<form:option label="10 Miles" value="10" />
									<form:option label="25 Miles" value="25" />
									<form:option label="50 Miles" value="50" />
									<form:option label="100 Miles" value="100" />
								</form:select>
								<div class="clearfix"></div>
								<div class="toolTipBefore">
									<label for="city_state">Radius</label>
								</div>
							</div>
							<div class="floatRight marginTop5 marginLeft10">
							<!-- Setting the hidden parameters for pagination -->
							<form:hidden path="start" id="start"/>
            				<form:hidden path="rows" id="rows"/>
							<form:hidden path="searchtype" id="searchtype" value= "resume"/>
							
							<input type="button" id="submitval" onclick="searchResume();"
								value="SEARCH" class="btn_sm orange margin0" />
								<!-- <a href="#" class="btn_sm orange margin0"
									onclick="searchResume();">SEARCH</a><br> -->
								<div class="floatLeft marginTop10">
									<a title="Coming Soon" href="../employerSearchResume/advanceresumesearch.html">Advanced Search</a>
								</div>
							</div>
						</div>

					</div>

					<!-- search_info_box2 -->
					<!-- browse_bar -->


					<div class="clearfix"></div>
				</div>
			 </form:form>    
		</div>

	</div>

	<!--Start:MidContant-->

	<!-- content_wrapper -->
	<!-- ad_wrapper -->


	<!-- main -->
</body>
</html>