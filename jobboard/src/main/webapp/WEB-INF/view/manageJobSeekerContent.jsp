<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>

</head>
<body class="job_board">
	<form:form commandName="manageJobSeekerForm" id="manageJobSeeker">
		<form:hidden path="folderId" />
		<form:hidden path="folderName" />
		
		<div class="row marginTop10 FontSize11">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="grid" id="tb_manage_job_seeker">
				<tr class="LightGrayBG Height35">
					<td width="6%" align="center" valign="middle" class="">&nbsp;</td>
					<td width="29%" align="left" valign="middle"><strong>Name</strong></td>
					<td width="14%" align="center" valign="middle"><strong>Rating</strong></td>
					<td width="22%" align="center" valign="middle"><strong>Applicant
							Status</strong></td>
					<td width="11%" align="center" valign="middle"><strong>Saved</strong></td>
					<td width="18%" align="center" valign="middle"><strong>Actions</strong></td>
				</tr>
				<c:forEach items="${manageJobSeekerForm.manageJobSeekerDTOList}"
					var="resume" varStatus="status">

					<form:hidden
						path="manageJobSeekerDTOList[${status.index}].folderResumeId" />
						<form:hidden
						path="manageJobSeekerDTOList[${status.index}].orgResumeId" id="orgResumeId"/>
						<tr class="Height35" id=${resume.folderResumeId}>
						<td align="center" valign="middle" ><input type="checkbox"
							name="checkbox" id=${resume.folderResumeId}
							value="${resume.folderResumeId}"></td>
						<td align="left" valign="middle"><a href="#">${resume.resumeName}</a></td>
										
						 <td align="center" valign="middle">
						 <c:forEach var="i" begin="1" end="${resume.rating}">
						   <c:if test="${i>1}">
						   </span>
						   </c:if>
						<span class="starOn" id="${i}" onclick="onChangeRatining(this);"/>
						 </c:forEach>
						 <c:forEach var="j" begin="${resume.rating+1}" end="${resume.rating + (5-resume.rating)}">
						 <c:if test="${j>resume.rating}">
						   </span>
						   </c:if>
						<span class="starOff" id="${j}" onclick="onChangeRatining(this);" />
						
						 </c:forEach>
						 </td> 
						<td align="center" valign="middle"><form:select onchange="onChangeAppStatus(this);"
								path="manageJobSeekerDTOList[${status.index}].applicationStatus"
								class="jb_input3 marginLeft10 marginTopBottom0" name="select"
								items="${appStatusList}" itemLabel="optionName"
								itemValue="optionId">
							</form:select></td>
						<td align="center" valign="middle">${resume.savedDate}</td>
						<td align="center" valign="middle"><a href="${pageContext.request.contextPath}/employer/viewResume.html?resumeId=${resume.orgResumeId }"><img
								src="../resources/images/View.png" width="20" height="20" alt="view"></a>&nbsp;<a
							href="${pageContext.request.contextPath}/employer/downloadResume.html?resumeId=${resume.orgResumeId }"><img src="../resources/images/Download.png"
								width="20" height="20" alt="download"></a>&nbsp;<a href="#"><img
								src="../resources/images/Print2.png" width="20" height="20"
								alt="print"></a>&nbsp;<a href=""><img
								src="../resources/images/EmailOrange.png" width="20" height="20"
								alt="email"></a>&nbsp;<a href="#"><img
								src="../resources/images/Delete.png" width="20" height="20"
								alt="delete"></a></td>
					</tr>
				</c:forEach>

			</table>
		</div>

	</form:form>
</body>
</html>