<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--  <meta http-equiv="refresh" content="10"> -->
<title>ADVANCE Heathcare Jobs</title>

</head>
<body class="job_board">
	
<div class="row marginTop10 FontSize11" id="tableContent">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="grid" id="tb_manage_job">
							<thead>
							<tr class="LightGrayBG Height35">
								<th width="6%" align="center" valign="middle" class="FontSize11">Adv. Job Id</th>
								<th width="10%" align="center" valign="middle" class="FontSize11">Job ID</th>
								<th width="18%" align="center" valign="middle" class="FontSize11"><strong>Job
										Title</strong></th>
								<th width="18%" align="center" valign="middle" class="FontSize11"><strong>Company Name</strong></th>		
								<th width="11%" align="center" valign="middle" class="FontSize11"><strong>Location</strong></th>
								<th width="8%" align="center" valign="middle" class="FontSize11"><strong>Job
										Status
								</strong></th>
								<th width="35%" align="center" valign="middle" class="FontSize11"><strong>E-Mail
								</strong></th>
								<th width="10%" align="center" valign="middle" class="FontSize11"><strong>Start
										Date
								</strong></th>
								<th width="8%" align="center" valign="middle" class="FontSize11"><strong>End
										Date
								</strong></th>	
								<th width="4%" align="center" valign="middle" class="FontSize11"><strong>&#160;
								</strong></th>																
							</tr>
							</thead>
							<tbody>					
						 <c:forEach items="${postedJobList}" var="job"	varStatus="status">								
								<tr class="Height35">
									<td align="center" valign="middle">${job.jobId}</td>
									<td align="center" valign="middle">${job.jobNumber}</td>
									<td align="center" valign="middle">${job.jobTitle}</td>
									<td align="center" valign="middle">${job.disCompanyName}</td>
									<td align="center" valign="middle">${job.location}</td>
									<td align="center" valign="middle">${job.jobStatus}</td>
									<td align="center" valign="middle">${job.emailId}</td>
									<td align="center" valign="middle">${job.startDt}</td>
									<td align="right" valign="middle"><input type="text" class="InputTextRight jb_input75 Heightf5" name="endDt" id="endDt" value='<c:out value="${job.endDt}"></c:out>'>
													
									
									
									<!--
									<small><a href="javascript:showCal('Calendar1')">Select Date</a></small>									
									 <div class="calender">
												<a href="#"><img src="../resources/images/tranBg.png"
													width="14" height="14" alt="Datepick"></a>
											</div> -->
											
									</td>
									<td align="center" valign="middle"><div class="toolTip01 colorPkrArea"><span class="classic">Example: 12/30/2010(mm/dd/yyyy)</span></div>	</td>
								 </tr>
								<input type="hidden" name="postedJobListId" id="postedJobListId" value="${postedJobList != null && postedJobList.size() >0}"/>
								<input type="hidden" name="startDate" id="startDate" value='<c:out value="${job.startDt}"></c:out>'/>
								 <input type="hidden" name="endDate" id="endDate" value='<c:out value="${job.endDt}"></c:out>'/>
								  <input type="hidden"name="startDate" id="startDate"/> 
								  <input type="hidden" name="endDt" id="endDt"/> 
						</c:forEach> 
							</tbody>
						</table>
					</div>   	
				
	</body>
</html>