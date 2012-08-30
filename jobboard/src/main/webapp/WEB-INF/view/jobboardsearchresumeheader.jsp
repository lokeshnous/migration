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

<!-- STYLESHEETS -->
<link href="stylesheets/JB.css" rel="stylesheet" type="text/css" />
<link href="stylesheets/jquery.megamenu.css" rel="stylesheet"
	type="text/css" />
<link href="stylesheets/SliderStyles.css" rel="stylesheet"
	type="text/css">
<!--[if IE]>
	<link href="stylesheets/ie.css" rel="stylesheet" type="text/css">
<![endif]-->

<!-- JAVASCRIPT FILES -->

<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery(".megamenu").megamenu();
	});

	function searchResume() {
		$(".otherContent").attr("style", "display: none");
		$(".searchContent").attr("style", "display: block");

	}
</script>
</head>

<body class="job_board">

	<div class="mainTwo">
		<!-- header_wrapper -->
		<!--nav-->
		<!--Start:MidContant-->
		<div class="row">
		<form method="">
			<div class="job_search_Resume">
				
					<div class="row ">
						<div class="row marginTop10">
							<h2 class="sectionSubHeader">Search Resumes</h3>
						</div>
						<div class="row marginTop10 marginBottom10">
							<div class=" floatLeft  width255">
								<input type="text" name="keywords" id="keywords"
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

								<select name="phrase" id="phrase" class="jb_input3 margin0">
									<option>Match exact phrase</option>
									<option>Match any word</option>
								</select>
							</div>
							<div class="FloatLeft marginLeft10 width255">

								<input type="text" name="city_state" id="city_state"
									class="jb_input2" />

								<div class="toolTipBefore">
									<label for="city_state">City and State or ZIP Code </label>
								</div>
								<div class="toolTip">
									<span class="classic"><p>Narrow your search to
											job-seekers in a specific area by entering the location here.
											Then select a search radius in the following drop-down menu.</p></span>
								</div>
							</div>
							<div class="floatLeft marginLeft10">

								<select name="radius" id="radius" class="jb_input3">
									<option>--</option>
									<option>5</option>
									<option>10</option>
									<option>25</option>
									<option>50</option>
									<option>100</option>
								</select>
								<div class="clearfix"></div>
								<div class="toolTipBefore">
									<label for="city_state">Radius</label>
								</div>
							</div>
							<div class="floatRight marginTop5 marginLeft10">
								<a href="#" class="btn_sm orange margin0"
									onclick="searchResume();">SEARCH</a><br>
								<div class="floatLeft marginTop10">
									<a href="">Advanced Search</a>
								</div>
							</div>
						</div>




					</div>

					<!-- search_info_box2 -->
					<!-- browse_bar -->

				
				<div class="clearfix"></div>
			</div>
			</form>
		</div>

	</div>

	<!--Start:MidContant-->

	<!-- content_wrapper -->
	<!-- ad_wrapper -->


	<!-- main -->
</body>
</html>