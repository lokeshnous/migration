<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Advance Health care job</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<jsp:include page="common/include.jsp" />
		<script type="text/javascript">
		jQuery(document).ready(function(){  
			$.nmFilters({
	    	    custom: {
	    	        afterShowCont: function(nm) {
	    	        	$('#name').focus();
	    	        }
	    	    }
	    	});

			$('#save').click(function(){			
	 			
				$.ajax({url:"${pageContext.request.contextPath}/jobSeekerCoverLetter/jobseekerCoverLetterSub.html",
					data:$('#resCovLetForm').serialize(),
					type:"POST",
					success: function(data) {
						if(data == ''){
							alert("Data saved successfully!");	
							parent.$.nmTop().close();
						}else{
							$("#errmsg").html(data);
						}
					 },
				});
			});
			
			jQuery(".megamenu").megamenu();   
			});
		</script>
		<script type="text/javascript">
	function limitText(limitField, limitCount, limitNum) {
	
		if (limitField.value.length > limitNum) {
			limitField.value = limitField.value.substring(0, limitNum);
		} else {
			limitCount.value = limitNum - limitField.value.length;
		}
		
	}
	</script>
	<script type="text/javascript">
		$('#Cancel').click(function(){		
			parent.$.nmTop().close();		
		});
		
		</script>
	<script src="javascripts/expandCollapse.js" type="text/javascript"></script>
	</head>
	<body class="job_board">
		
		<form:form action="jobseekerCoverLetterSub.html" method="POST" commandName="resCoverLetterForm" id="resCovLetForm" name="resCovLetForm" enctype="multipart/form-data">
		<div class="job_seeker_login popUpContainer" id="jobSeekerRegister1" style="display: block;">
					<div class="popupHeader">
						<h2>
							CREATE NEW COVER LETTER 
						</h2>
						<a href="#"><img width="19" height="19" src="<%= request.getContextPath() %>/resources/images/Close.png" class="nyroModalClose" alt="Close" title="Close"/></a>
					</div>
					<div class="popUpContainerWrapper">
					<div class="row ">
					<!-- <div class="rowEvenNewSpacing">
								<span class="lableText3"> </span>
								<div id="errmsg" class="FormErrorDisplayText"></div>
							</div> -->
					
						<div class="rowEvenNewSpacing">
						<div class="rowEvenNewSpacing">
								<span class="lableText7"> </span>
								<div id="errmsg" class="FormErrorDisplayText"></div>
							</div>
								<span class="lableText7">
								Cover Letter Name:
								</span>
								<form:input path="name" name="name" id="name" class="jb_input2Coverletter" type="text"/>
								<div class="required2">
									(Required)
								</div>
							</div>	
											
						<div class="rowEvenNewSpacing">
								<span class="lableText7" style="margin-top:8px;">
								Body Text:
								</span>
								<form:textarea path="coverletterText" name="coverletterText"  class="textareaBoxCResume textareaBoxCResumeTemplate" resize="none"  rows="5" cols="20"
								id="coverletterText"
									onKeyDown="limitText(this.form.coverletterText,this.form.countdownCoverLetter,5000);"
									onKeyUp="limitText(this.form.coverletterText,this.form.countdownCoverLetter,5000);"/>
									<div class="required2" style="float:right; margin-right:21px;">(Required)</div>
									</div>
									
								<div class="rowEvenNewSpacing magrin_top0">
								<span class="lableText7">
								&nbsp;
								</span>
								<p class="magrin_top0">
								<span class="lableText3 Padding0">
								
								
								<input readonly type="text" class="input2000_width" name="countdownCoverLetter" size="3" value="5000">characters remaining.</span>
								</p>
							</div>						
						<div class="rowEvenNewSpacing">
								<span class="lableText7">
								Cover Letter Visibility:
								</span>
								<span class="required">								
									<label class="greyLabel">
										 <form:radiobutton name="RadioGroup10" id="RadioGroup10" value="1" path="active"  label="Public" /> 
									</label>
							</span>
							<span class="required">								
									<label class="greyLabel">
									<form:radiobutton name="RadioGroup10" id="RadioGroup10" value="0" path="active"  label="Private"  />										
									</label>
							</span>
							
							<div class="toolTip colorPkrAreaToolTip">
								<span class="classic">
									You can only have one Cover Letter Visibility to employers at a time, so select 
									Private if you already have a public cover letter  saved to your profile. Otherwise, 
									you may select Public and employers will be able to view your cover letter immediately.
									
								</span>
							</div>
							<div class="required2">
								(Required)
							</div>
					 </div>						
						<div class="rowEvenNewSpacing marginTop20 paddingBottom10">
							<span class="floatLeft marginTop10">
								<input type="button" value="Save" name="save" id="save" class="btn_sm orange cursor"/>
								<input type="button" name="Cancel" id="Cancel" class="orange cursor" value="Cancel"/>	
							</span>
						</div>			
					
				 </div>	
				</div>
			</div>
		
		</form:form>
		
	</body>
</html>