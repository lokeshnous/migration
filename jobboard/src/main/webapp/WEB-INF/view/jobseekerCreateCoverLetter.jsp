<html lang="en">
	<head>
		<title>ADVANCE Heathcare Jobs</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<jsp:include page="common/include.jsp" />
		<script type="text/javascript">jQuery(document).ready(function(){       jQuery(".megamenu").megamenu();   });</script>
		<script src="javascripts/expandCollapse.js" type="text/javascript"></script>
	</head>
	<body class="job_board">
		<%-- <div class="ad_page_top">
			<img src="<%= request.getContextPath() %>/resources/images/ads/banner_ad_fpo.png"/>
		</div> --%>
		<div class="main_wrapper_outside">
			<div class="main_wrapper_inside">
				<div class="main">
				<%-- <jsp:include page="../templates/templates_header.jsp"></jsp:include> --%>
					<div class="popupHeader">
						<h2>
							CREATE NEW COVER LETTER 
						</h2>
						<a href="#"><img width="19" height="19" src="<%= request.getContextPath() %>/resources/images/Close.png" class="nyroModalClose" alt="Close"/></a>
					</div>
					<div class="row ">
						<div class="row marginTop15">
							<div class="lableTextCoverletter">
								Cover Letter Name:
							</div>
							<div class="input_grp5 ">
								<div class="floatLeft">
									<input name="Exclude" class="jb_input2Coverletter" type="text"/>									
								</div>
								<div class="required2">
									(Required)
								</div>
							</div>
						</div>
						<div class="row marginTop15">
							<div class="lableTextCoverletter marginTop10">
								Body Text:
							</div>
							<div class="input_grp5 ">
								<textarea name="Body Text:" class="textareaBoxMessege" resize="none" id="Body Text:" rows="5" cols="45">
								</textarea>
								<p>
									5000 characters remaining
								</p>
							</div>
						</div>
						<div class="row marginTop15 MarginBottom10">
							<div class="lableTextCoverletter">
								Cover Letter Visibility:
							</div>
							<span class="required">
								<input name="autoRenew" id="autoRenew" type="radio"/>
									<label class="greyLabel">
										Public
									</label>
							</span>
							<span class="required">
								<input name="autoRenew" id="autoRenew" type="radio" CHECKED="checked"/>
									<label class="greyLabel">
										Private
									</label>
							</span>
							<div class="toolTip marginTop6 marginLeft10">
								<span class="classic">
									Tool Tip
								</span>
							</div>
							<div class="required2">
								(Required)
							</div>
						</div>
						<div class="rowEvenNewSpacing marginTop20 paddingBottom10">
						<span class="floatLeft marginTop10">
							<input type="button" value="Save" name="save" id="save" class="btn_sm orange" />
							<input type="button" name="Cancel" id="Cancel" class="orange" value="Cancel"/>
								
						</span>
					</div>
					</div>
					<%-- <div class="clearfix">
					</div>
					<div class="ad_wrapper">
						<img src="<%= request.getContextPath() %>/resources/images/ads/banner_ad_fpo.png"/>
						
					</div> --%>
				</div>
			</div>
		</div>
		<!-- <div class="footer_wrapper">
		</div> -->
		<%-- <jsp:include page="../templates/templates_footer.jsp"></jsp:include> --%>
	</body>
</html>