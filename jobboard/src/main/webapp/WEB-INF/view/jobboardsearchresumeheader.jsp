<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<jsp:include page="common/include.jsp" />
<link href="../resources/css/JB.css" rel="stylesheet" type="text/css" />
<link href="../resources/css/jquery.megamenu.css" rel="stylesheet"
	type="text/css" />
<link href="../resources/css/SliderStyles.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" type="text/css" media="screen"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="jquery.autocomplete.css" />
	<!-- JAVASCRIPT FILES -->
	<!--  <script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>-->
	<script type="text/javascript"
		src="javascripts/jquery.cycle.all.min.js"></script>
	<script type="text/javascript" src="javascripts/slider.js"></script>
	<script type="text/javascript" src="javascripts/jquery.megamenu.js"></script>

	<!-- <script type="text/javascript" src="jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="jquery.autocomplete.min.js"></script>-->

<script type="text/javascript">
	jQuery(document).ready(function() {
		if (${keywords} != null){
			$("#keywords").click(function(event){
				searchResume();
			}
	   }
	});
	</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>

</head>

<body class="job_board">

	<div class="mainTwo">
		<!-- header_wrapper -->
		<!--nav-->
		<!--Start:MidContant-->
		<div class="row">
			<form:form action="" commandName="searchResumeForm">
				<div class="job_search_Resume">

					<div class="row ">
						<div class="row marginTop10">
							<h2 class="sectionSubHeader">
								Search Resumes
								</h2>
						</div>
						<div class="FormErrorDisplayText" id="findSearchInfo"></div>
						<div class="row marginTop10 marginBottom10">
							<div class=" floatLeft  width255">
							<form:input type="text"  maxlength="60" id="keywords"
									class="jb_input2" path="keywords" />
									
							<%-- <c:if test="<%=session.getAttribute(\"keywords\") != null %>">
								<input type="text"  maxlength="60" id="keywords"
									class="jb_input2" value="<%=session.getAttribute("keywords")%>" />
							</c:if>		
							<c:if test="<%=session.getAttribute(\"keywords\") == null %>">
								<input type="text" maxlength="60" id="keywords"
									class="jb_input2"/>
							</c:if>	 --%>
								<form:hidden path="keywords" maxlength="60"
									class="jb_input2" />
								<div class="floatLeft">
									<div class="toolTipBefore">
										<label for="keywords">Keywords </label>
									</div>
									<div class="toolTip">
										<div class="classic"><p>You can enter a job title,
												specialty, skill or any other keywords that describe the
												position you're trying to fill. Then select an option from
												the drop-down menu to match your exact phrase or any of the
												individual words you entered.</p></div>
									</div>
								</div>
							</div>
							<form:hidden path="autoload" id="autoload" />
							<div class="floatLeft marginLeft10">

								<form:select path="phrase" id="phrase" class="jb_input3 margin0">
									<form:option label="Match exact phrase" value="Match exact phrase"/>
									<form:option label="Match any word" value="Match any word"/>
								</form:select>
							</div>
							<div class="FloatLeft marginLeft10 width255">

								<form:input path="cityState" id="cityState"
									class="jb_input2" disabled="true"/>

								<div class="toolTipBefore">
									<label for="city_state">City and State or ZIP Code</label>
								</div>
								<div class="toolTip">
									<div class="classic"><p>Narrow your search to
											job-seekers in a specific area by entering the location here.
											Then select a search radius in the following drop-down menu.</p></div>
								</div>
							</div>
							<div class="floatLeft marginLeft10">

								<form:select path="radius" id="radius" class="jb_input3" disabled="true">
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
							<div class="floatRight marginLeft10">
							<!-- Setting the hidden parameters for pagination -->
							<form:hidden path="start" id="start"/>
            				<form:hidden path="rows" id="rows"/>
							<form:hidden path="searchtype" id="searchtype" value= "resume"/>
							
							<input type="button" id="submitval" title="Coming Soon"
								value="SEARCH" class="btn_sm orange margin0 cursor" />
								<!-- <a href="#" class="btn_sm orange margin0"
									onclick="searchResume();">SEARCH</a> --><br>
								<div class="floatRight marginTop5" title="Coming Soon">
									<a href="#">Advanced Search</a>
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