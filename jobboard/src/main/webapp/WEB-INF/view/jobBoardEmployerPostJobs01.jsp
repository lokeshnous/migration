<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery(".megamenu").megamenu();
		
		$("#purchaseJobPostingId").displaypopup("#purchaseJobPostingId",
				"770", "360");
		$("#purchaseJobPostingId2").displaypopup("#purchaseJobPostingId2",
				"770", "360");
		$("#purchaseJobPostingId3").displaypopup("#purchaseJobPostingId3",
				"770", "360");
	});
	function sendEmail(){
		$("#processingMsg").html("<span>Processing...</span>");
	}
</script>
</head>

<body class="job_board">
	<div class="ad_page_top">
		${adPageTop }
	</div>
	<div class="main_wrapper_outside">
		<div class="main_wrapper_inside">
			<div class="main">
				<jsp:include page="../templates/templates_header.jsp"></jsp:include>
				<div class="row">
					<!-- Step 1 -->
					<div class="MidContent_Wrapper FloatLeft">
						<h1 class=" TextColor03 FontSize35">Find the Perfect
							Candidates Today!</h1>
						<p class="FontSize17 FloatLeft">
							Post your listings instantly on the <em>ADVANCE</em> Healthcare
							Jobs website.
						</p>
						<span class="floatRight"><a href="${viewMediaUrl}" target="_blank"
							class="btn_sm white marginRight5">VIEW MEDIA KIT</a></span>
					</div>
					<div id="processingMsg" class="FormErrorDisplayText"></div>
					<!--***-->
					<div class=" clearfix"></div>
					<div class="MidContent_Wrapper FloatLeft marginTop15">
						<!--1-->
						<div class="MidContentBox01">
							<h3 class="FontSize16">STANDARD JOB POSTING</h3>
							<p class="marginTop5 lineHeight16">30-day listings can be
								posted anytime with no long-term commitment.</p>
							<div class=" paddingBottom05 marginTop5"></div>
							<div class="cont_info_box2 AutoWidth marginTop5">
								<ul>
									<li>1-2 postings: $349 each</li>
									<li>3-5 postings: $334 each</li>
									<li>6-9 postings: $317 each</li>
									<li>10 postings: $286 each</li>
								</ul>
							</div>
							<div class=" paddingBottom05 "></div>
							<p class="marginTop5 FloatLeft lineHeight16">
								For more than 10 postings, contact your <em>ADVANCE</em> sales
								rep.
							</p>
							<div class="clearfix"></div>
							<span class="FloatLeft marginTop25"><a
								id="purchaseJobPostingId" class="btn_sm orange"
								href="<%=request.getContextPath()%>/purchaseJobPosting/purchaseJobPostings.html"
								name="standardJob">BUY ONLINE NOW</a></span>
							<div class="PlayDemo marginTop15">
								<a
									href="https://www.google.co.in/url?url=http://www.youtube.com/watch%3Fv%3Du5O5JKV7H5A&rct=j&sa=X&ei=9Zo7UL-fDMHtrAfh1oCIBQ&ved=0CEIQuAIwBQ&q=flowers+vide&usg=AFQjCNEik81RAbq6ffmdq3EprRgPio6tRg&cad=rja"
									target="_blank" tclass="TextColor01 PlayDemo">Video Demo</a>
							</div>
						</div>
						<!--2-->
						<div class="MidContentBox01 marginLeft20">
							<h3 class="FontSize16">JOB POSTING SLOTS</h3>
							<p class="marginTop10 lineHeight16">Do you have staffing
								needs that change rapidly? Purchase 'slots' that can be used and
								reused as many times as you need for 30 days.</p>
							<div class=" paddingBottom05 marginTop5"></div>
							<div class="cont_info_box2 AutoWidth marginTop5">
								<ul>
									<li>1-2 Postings: $499 each</li>
									<li>3-7 Postings: $425 each</li>
								</ul>
							</div>
							<div class=" paddingBottom05 "></div>
							<p class="marginTop3 FloatLeft lineHeight16">
								For more than 8 slots, contact your <em>ADVANCE</em> sales rep.
							</p>
							<div class="clearfix"></div>
							<span class="FloatLeft marginTop40"><a
								id="purchaseJobPostingId2"
								href="<%=request.getContextPath()%>/purchaseJobPosting/purchaseJobPostings.html"
								name="jobPostingSlot" class="btn_sm orange">BUY ONLINE NOW</a></span>
							<div class="PlayDemo marginTop30">
								<a
									href="https://www.google.co.in/url?url=http://www.youtube.com/watch%3Fv%3Du5O5JKV7H5A&rct=j&sa=X&ei=9Zo7UL-fDMHtrAfh1oCIBQ&ved=0CEIQuAIwBQ&q=flowers+vide&usg=AFQjCNEik81RAbq6ffmdq3EprRgPio6tRg&cad=rja"
									target="_blank" class="TextColor01 PlayDemo">Video Demo</a>
							</div>
						</div>
						<!--3-->
						<div class="MidContentBox01 marginLeft20">
							<h3 class="FontSize16">EZ POST</h3>
							<p class="marginTop10 lineHeight16">
								Save time and eliminate errors using <em>ADVANCE</em> EZ Post.
								This valuable service automatically adds all of your listings to
								the <em>ADVANCE</em> Healthcare Jobs website. Choose from a
								daily XML feed or a weekly job scraping. With XML feeds, jobs
								are automatically pushed from your website to ours. With job
								scraping, we pull them over from your website each week. Contact
								us today for pricing!
							<div class="clearfix"></div>
							<span class="FloatLeft marginTop40"><a onclick="sendEmail();"
								name="purchaseJobPostingId2"
								href="../employerPostJobs/sendEmailForGold.html?package=ezpost"
								class="btn_sm orange">REQUEST PRICING</a></span>
							<div class="PlayDemo marginTop30">
								<a
									href="https://www.google.co.in/url?url=http://www.youtube.com/watch%3Fv%3Du5O5JKV7H5A&rct=j&sa=X&ei=9Zo7UL-fDMHtrAfh1oCIBQ&ved=0CEIQuAIwBQ&q=flowers+vide&usg=AFQjCNEik81RAbq6ffmdq3EprRgPio6tRg&cad=rja"
									target="_blank" class="TextColor01 PlayDemo">Video Demo</a>
							</div>
						</div>
					</div>
					<!--***-->
					<div class="MidContent_Wrapper FloatLeft">
						<h1 class=" TextColorA01 FontSize35">Attention-grabbing
							Upgrades</h1>
						<p class="FontSize17 FloatLeft">Showcase your postings and
							reinforce your brand with these special enhancement packages.</p>
						<span class="floatRight"><a href="${viewMediaUrl}" target="_blank"
							class="btn_sm white marginRight5">VIEW MEDIA KIT</a></span>
					</div>
					<!--***-->
					<div class="MidContent_Wrapper FloatLeft marginTop15">
						<!--1-->
						<div
							class="MidContentBox01 SilverBorder Padding0 width300 Height695">
							<div class="BoxLogoHeadSilver SilverColor">
								<div class="BoxText marginTop10 marginLeft25">
									<h3 class="FontSize18 SilverColor">SILVER</h3>
									<p>Add your logo, an image and an accent color to any job
										posting.</p>
								</div>
							</div>
							<div class="BoxContantIn lineHeight16">
								<p>
									<strong>Exclusivity Upgrade:</strong>
								</p>
								<p>No competitor ads will ever run on the pages with your
									full job postings.</p>
								<div class="cont_info_box3 AutoWidth marginTop5">
									<table width="100%" border="1" cellspacing="0" cellpadding="0">
										<tr>
											<td width="10%"><ul>
													<li></li>
												</ul></td>
											<td width="70%">1-9
												posting.....................................</td>
											<td width="20%" align="right">$99 each</td>
										</tr>
										<tr>
											<td><ul>
													<li></li>
												</ul></td>
											<td>10-24...............................................</td>
											<td align="right">$79 each</td>
										</tr>
										<tr>
											<td><ul>
													<li></li>
												</ul></td>
											<td>25 and above.................................</td>
											<td align="right">$59 each</td>
										</tr>
										<tr>
											<td><ul>
													<li></li>
												</ul></td>
											<td>EZ Post Enhancements</td>
											<td align="right">&nbsp;</td>
										</tr>
									</table>
								</div>
								<span class="FloatLeft marginTop20"><a
									id="purchaseJobPostingId3"
									href="<%=request.getContextPath()%>/purchaseJobPosting/purchaseJobPostings.html"
									name="silver" class="btn_sm orange">BUY ONLINE NOW</a></span>
								<div class="PlayDemo marginTop10">
									<a
										href="https://www.google.co.in/url?url=http://www.youtube.com/watch%3Fv%3Du5O5JKV7H5A&rct=j&sa=X&ei=9Zo7UL-fDMHtrAfh1oCIBQ&ved=0CEIQuAIwBQ&q=flowers+vide&usg=AFQjCNEik81RAbq6ffmdq3EprRgPio6tRg&cad=rja"
										target="_blank" class="TextColor01 PlayDemo">Video Demo</a>
								</div>
								<div class="clearfix"></div>
								<div class="AutoWidth TextAlignC marginTop20">
									<img src="../resources/images/img01.png" width="242"
										height="303">
								</div>
							</div>
						</div>
						<!--2-->
						<div
							class="MidContentBox01 marginLeft20 GoldBorder Padding0 width300  Height695">
							<div class="BoxLogoHeadGold SilverColor">
								<div class="BoxText marginTop10 marginLeft25">
									<h3 class="FontSize18 GoldColor">GOLD</h3>
									<p>All the features in the Silver package, plus these
										additional upgrades:</p>
								</div>
							</div>
							<div class="BoxContantIn lineHeight16">
								<p>
									<strong>Multimedia Upgrades:</strong>
								</p>
								<p>
									Add a multimedia section to your jobs postings with videos,
									slideshows and testimonials. This helps you fully engage
									job-seekers while immersing them in your brand.&nbsp;&nbsp;<span><a
										href="#"><img src="../resources/images/more_orange.png"
											width="11" height="10"></a></span>
								</p>
								<span class="FloatLeft marginTop60"><a onclick="sendEmail();"
									href="../employerPostJobs/sendEmailForGold.html?package=gold" 
									class="btn_sm orange">REQUEST PRICING</a></span>
								<div class="PlayDemo marginTop50">
									<a
										href="https://www.google.co.in/url?url=http://www.youtube.com/watch%3Fv%3Du5O5JKV7H5A&rct=j&sa=X&ei=9Zo7UL-fDMHtrAfh1oCIBQ&ved=0CEIQuAIwBQ&q=flowers+vide&usg=AFQjCNEik81RAbq6ffmdq3EprRgPio6tRg&cad=rja"
										target="_blank" class="TextColor01 PlayDemo">Video Demo</a>
								</div>
								<div class="clearfix"></div>
								<div class="AutoWidth TextAlignC marginTop20">
									<img src="../resources/images/img02.png" width="242"
										height="374">
								</div>
							</div>
						</div>
						<!--3-->
						<div
							class="MidContentBox01 marginLeft20 PlatinumBorder Padding0 width300  Height695">
							<div class="BoxLogoHeadPlatinum SilverColor">
								<div class="BoxText marginTop10 marginLeft25">
									<h3 class="FontSize18 PlatinumColor">PLATINUM</h3>
									<p>All the features in the Silver & Gold packages, plus
										these additional upgrades:</p>
								</div>
							</div>
							<div class="BoxContantIn lineHeight16">
								<p>
									<strong>Related Jobs Upgrade: </strong>Include a comprehensive
									list of your other openings on each of your full job posting
									pages.&nbsp;&nbsp;<span><a href="#"><img
											src="../resources/images/more_orange.png" width="11"
											height="10"></a></span>
								</p>
								<br />
								<p>
									<strong>PR Upgrade:</strong> We include a feed on your full job
									posting pages to highlight the latest news, articles and
									features that ADVANCE has written about your facility.
									&nbsp;&nbsp;<span><a href="#"><img
											src="../resources/images/more_orange.png" width="11"
											height="10"></a></span>
								</p>
								<span class="FloatLeft marginTop20"><a onclick="sendEmail();"
									href="../employerPostJobs/sendEmailForGold.html?package=platinum"
									class="btn_sm orange">REQUEST PRICING</a></span>
								<div class="PlayDemo marginTop15">
									<a
										href="https://www.google.co.in/url?url=http://www.youtube.com/watch%3Fv%3Du5O5JKV7H5A&rct=j&sa=X&ei=9Zo7UL-fDMHtrAfh1oCIBQ&ved=0CEIQuAIwBQ&q=flowers+vide&usg=AFQjCNEik81RAbq6ffmdq3EprRgPio6tRg&cad=rja"
										target="_blank" class="TextColor01 PlayDemo">Video Demo </a>
								</div>
								<div class="clearfix"></div>
								<div class="AutoWidth TextAlignC marginTop10">
									<img src="../resources/images/img03.png" width="242"
										height="374">
								</div>
							</div>
						</div>
					</div>
					<!--***-->
					<div
						class="MidContent_Wrapper FloatLeft marginTop20 marginBottom20">
						<h1 class=" TextColorA01 FontSize19">
							<strong>Contact ADVANCE Sales to purchase any of these
								upgrade</strong>
						</h1>
						<h1 class=" TextColorA01 FontSize19 marginTop4 width255 FloatLeft">
							<strong>packages: 1.800.355.5627</strong>
						</h1>
						<div class="TextBoxLBorder width100 marginTop4">
							<h1 class=" TextColorA01 FontSize19  FloatLeft">
								<strong>EMAIL</strong>
							</h1>
							<a href="#" class="floatRight marginTop1"><img
								src="images/email.png" width="22" height="22"></a>
						</div>
						<div class="TextBoxLBorder width100 marginLeft15 marginTop4">
							<h1 class=" TextColorA01 FontSize19  FloatLeft">
								<strong>MEDIA KIT</strong>
							</h1>
						</div>
						<span class="floatRight"><a
							href="../employer/employerDashBoard.html"
							class="btn_sm orange marginRight5">RETURN TO MY DASHBOARD</a></span>
					</div>
				</div>

				<!-- Step 2 -->
				<!--  <div class="ad_col_right"> <img src="images/ads/300x250ad1.png" /> <br class="clearfix" /></div>-->
			</div>
			<div class="clearfix"></div>
			<div class="ad_wrapper">
				${adPageBottom}
			</div>
		</div>
		<!-- main -->

	</div>
	<!-- end main_wrapper_inside -->
	<!-- end main_wrapper_outside -->

	<jsp:include page="../templates/templates_footer.jsp"></jsp:include>
</body>
</html>