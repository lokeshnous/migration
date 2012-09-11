<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="refresh" content="10">
<title>ADVANCE Heathcare Jobs</title>
<script type="text/javascript">
	/* jQuery(document).ready(function() {
 		$('#save').click(function(){			
			
			$.ajax({url:"${pageContext.request.contextPath}/admin/saveJobSeekerSubscription.html",
				data:$('#subscriptionsId').serialize(),
				type:"POST",
				success: function(data) {					
					//parent.$.nmTop().close();
					location.reload();
				 },
			});
		}); 
		
		jQuery(".megamenu").megamenu();
		
	}); */
</script>
</head>
<body class="job_board">
	<%-- <form:form method="GET" commandName="jobPostForm" id="saveJobId" name="saveJobId"> --%>
<div class="row marginTop10 FontSize11" id="tableContent">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="grid" id="tb_manage_job">
							<thead>
							<tr class="LightGrayBG Height35">
								<th width="2%" align="center" valign="middle" class="FontSize11">Job Id</th>
								<th width="6%" align="center" valign="middle" class="FontSize11">Adv. Job
										ID</th>
								<th width="18%" align="center" valign="middle" class="FontSize11"><strong>Job
										Title</strong></th>
								<th width="11%" align="center" valign="middle" class="FontSize11"><strong>Location</strong></th>
								<th width="8%" align="center" valign="middle" class="FontSize11"><strong>Job<br />
										Status
								</strong></th>																
							</tr>
							</thead>
							<tbody>					
						 <c:forEach items="${postedJobList}" var="job"	varStatus="status">								
								<tr class="Height35">
									<td align="center" valign="middle">${job.jobId}</td>
									<td align="center" valign="middle">${job.jobId}</td>
									<td align="center" valign="middle">${job.jobTitle}</td>
									<td align="center" valign="middle">${job.location}</td>
									<td align="center" valign="middle">${job.jobStatus}</td>									
								</tr>
						</c:forEach> 
							</tbody>
						</table>
					</div>   	
				<%-- </form:form> --%>
	</body>
</html>