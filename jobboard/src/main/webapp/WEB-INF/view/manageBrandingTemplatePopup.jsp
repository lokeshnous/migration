<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>ADVANCE Heathcare Jobs</title>
<jsp:include page="common/include.jsp" />

<script type="text/javascript">
	jQuery(document).ready(function() {
		$("#tb_manage_brand_template img").click(function(event) {

			var action = $(this).attr("alt");
			var rowObj = $(this).parent().parent().parent().parent();			
			var templateId = rowObj.attr("id");
			
			switch (action) {
			
			case "delete":{
				if (confirm("Are you sure you want to delete?")) {
						$.ajax({url: "${pageContext.request.contextPath}/brandingTemplates/employer/deleteBrandingTemplate.html?templateId="+templateId,
							type: "GET",
							success: function(data){ 
							    if(data.success != null){
							    	rowObj.remove();
							    }
							    if(data.failure != null){
							    	alert(data.failure);
							    }
							},
							error: function(response) {
								alert("Server Error : "+response.status);
							}
						});
					return true;
				 } else {
					return false;
				 }
   			   }
				break;
			}

		});
	});
</script>
</head>
		<body class="job_board">
        <div id="jobSeekerRegister1" class="job_seeker_login popUpContainer" style="display:block">
          <div class="popupHeader">
            <h2>MANAGE JOB POSTING BRANDING TEMPLATES </h2>

            <%-- <a href="<%=request.getContextPath()%>/brandingTemplates/cancelBrandTemp.html"><img src="../resources/images/Close.png" width="19" height="19" alt="Close"></a></div> --%>
<!--             <a href="#"><img src="../resources/images/Close.png" class="nyroModalClose" width="19" height="19" alt="Close"></a></div> -->

            <img src="../resources/images/Close.png" width="19" class="nyroModalClose" title="Close"
				height="19" alt="Close"></div>

          <div class="popUpContainerWrapper">
            <form:form method="POST" action="">
              <div class="rowEvenSpacingMargin0">
                <table id="tb_manage_brand_template" width="100%" border="0"
						cellspacing="0" cellpadding="0" class="grid">
                <thead>
                 <tr  class="borderTopNone">
                    <th align="left" scope="col" >Name</th>
                    <th width="18%" align="center" scope="col"> Last Modified</th>
                    <th width="21%" align="center" scope="col">Actions</th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${templatesList}" var="template" varStatus="status">
								<tr id="${template.jpBrandTempId}">
									<td>${template.templateName}</td>
									<td align="center"><label>
											${template.createdDate}
									</label></td>
									<td align="center"><div class="BTemplateIconAlign">
									
									<a title="View" href='<%=request.getContextPath()%>/brandingTemplates/previewExisting.html?templateId=${template.jpBrandTempId}'>
									<img  src="../resources/images/tranBg.png" width="20" height="20" alt="view" class="view">
											</a>&nbsp;<a title="Edit" href='<%=request.getContextPath()%>/brandingTemplates/editTemplate.html?templateId=${template.jpBrandTempId}'><img src="../resources/images/tranBg.png" width="20" height="20" alt="edit" class="editFile"></a>&nbsp;<a href="#"><img title="delete" src="../resources/images/tranBg.png" width="20" height="20" alt="delete" class="delete"></a></div></td>
								</tr>
							</c:forEach>
                  </tbody>
                </table>
              </div>
              <div class="row marginTop10 paddingBottom10"> 

<%--               <a href="<%=request.getContextPath()%>/brandingTemplates/newBrandingTemplate.html" class="btn_sm orange">New  Branding TEMPLATE</a> 
              <a href="<%=request.getContextPath()%>/brandingTemplates/cancelBrandTemp.html" class="btn_sm orange">Cancel</a>
 --%>
              <a class="btn_sm orange" href="<%=request.getContextPath()%>/brandingTemplates/newBrandingTemplate.html">New  Branding TEMPLATE</a> 
              <a class="nyroModalClose btn_sm orange" href="#">Cancel</a>	

              </div>
            </form:form>
          </div>
          <div class="clearfix"></div>
        </div>
</body>
</html>