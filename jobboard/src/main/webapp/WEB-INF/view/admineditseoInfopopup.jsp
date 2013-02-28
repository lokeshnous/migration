<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />
<script type="text/javascript">
tinyMCE.init({
	mode : "specific_textareas",
    editor_selector : "mceEditor",
	theme : "advanced",
	theme_advanced_buttons1 : "mybutton,bold,italic,underline,strikethrough,separator,|,justifyleft,justifycenter,justifyright, justifyfull,|,bullist,numlist,|,undo,redo,link,unlink",
	theme_advanced_buttons2 :"",
	theme_advanced_buttons3 :"",
	theme_advanced_toolbar_location : "top",
	theme_advanced_toolbar_align : "left",
	max_chars : 5000,
	max_chars_indicator : "characterCounter",
	/*plugins : 'inlinepopups',*/
	plugins : "autolink,advlink,maxchars",
	ontent_css : "css/mycontent.css"
});
		$.nmFilters({
			custom: {
			afterShowCont: function(nm) {
				$('#metaTitle').focus();
				}
			}
		 });
	$("#saveData").click(function(event){
		//validate the seo popup
		var content = tinyMCE.get('staticContent').getContent();
		var length = content.length;
		var value = $("#characterCounter").val();
		if(value == 5000 || length > 5000){
			$("#errmsg").html("The text characters have exceeded the limit of 5000. Please reduce the text characters.");
		}else{
		$("#description").val(content); 
		if(!validateSeoPopup()){
		$.ajax({url: "${pageContext.request.contextPath}/admin/saveseoinfo.html",
			data : $("#adminSeoFormId").serialize(), 
			success: function(data){
				alert("SEO info saved successfully!.");
				$("#Cancel").click();
			},
			
			error: function(response) {
				//alert("Server Error : "+response.status);
			},
			complete: function() {
				
			}
		});
		
		}else{
			$("#errmsg").text("Please enter all the required fields."); 	
		}
		}
	});
	function validateSeoPopup(){
		var status = false;
		if($.trim($("#metaTitle").val()) == ''){
			status = true;
		}else if($.trim($("#metaDesc").val()) == ''){
			status = true;
		}
		/* else if($.trim($("#staticContent").val()) == ''){
			status = true;
		} */
		return status;
	}
</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>EDIT SEO-INFO</h2>
			<a href="#" class="nyroModalClose"><img src="<%= request.getContextPath() %>/resources/images/Close.png" width="19" height="19" title="Close"
				alt=""></a>
		</div>

		<div class="popUpContainerWrapper">
			<form:form action="" method="GET" commandName="adminSeoForm" id="adminSeoFormId">
			<div class="row ">
			<div class="rowEvenNewSpacing">
								<span class="lableText7"> </span>
								<div id="errmsg" class="FormErrorDisplayText"></div>
							</div>
			<div class="rowEvenNewSpacing">
								<span class="lableText7">
								Job Title : 
								</span>
								<form:hidden path="jobTitle"/>
								<form:label path="jobTitle" >${adminSeoForm.jobTitle}</form:label>
						</div>
						<form:hidden path="seoInfoId" />
						<div class="rowEvenNewSpacing">
								<span class="lableText7">
								Meta Title:
								</span>
								<form:textarea path="metaTitle" class="textareaBoxCResume textareaBoxCResumeTemplate" resize="none"  rows="3" cols="20"
								/>
								<div class="required2">
									(Required)
								</div>
						</div>	
						<div class="rowEvenNewSpacing">
								<span class="lableText7">
								Meta Description:
								</span>
								<form:textarea path="metaDesc" class="textareaBoxCResume textareaBoxCResumeTemplate" resize="none"  rows="5" cols="20"
								/>
								<div class="required2">
									(Required)
								</div>
						</div>	
						<div class="rowEvenNewSpacing">
								<span class="lableText7" style="margin-top:8px;">
								Static Content:
								</span>
								<form:textarea id="staticContent" path="staticContent" class="mceEditor textareaBoxCResume textareaBoxCResumeTemplate" resize="none"  rows="5" cols="20"
								/>
									<!-- <div class="required2" style="float:right; margin-top:0px;">(Required)</div> -->
									</div>
									<div class="rowEvenNewSpacing magrin_top0">
								<span class="lableText7">
								&nbsp;
								</span>
								<p class="magrin_top0">
								<span class="lableText3 Padding0">
								<form:hidden path="description" id="description"></form:hidden>			
								<input readonly type="text" id="characterCounter" class="input2000_width" name="countdownCoverLetter" size="3" value="5000">characters remaining.</span>
								</p>
							</div>	
							<span class="floatLeft marginTop10">
								<input type="button" value="Save" name="save" id="saveData" class="btn_sm orange cursor"/>
								<input type="button" name="Cancel" id="Cancel" class="orange cursor nyroModalClose" value="Cancel"/>	
							</span>
						</div>			
					
			</form:form>
		</div>
		<div class="clearfix"></div>
	</div>

</body>
</html>