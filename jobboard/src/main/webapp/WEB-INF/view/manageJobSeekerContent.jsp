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
						path="manageJobSeekerDTOList[${status.index}].resumeId" />
					<tr class="Height35">
						<td align="center" valign="middle"><input type="checkbox"
							name="checkbox" id=${resume.folderResumeId}
							value="${resume.folderResumeId}"></td>
						<td align="left" valign="middle"><a href="#">${resume.resumeName}</a></td>
						<td align="center" valign="middle"><input type="radio"
							name="rating" value="1" class="star"> <input type="radio"
							name="rating" value="2" class="star"> <input type="radio"
							name="rating" value="3" class="star"> <input type="radio"
							name="rating" value="4" class="star"> <input type="radio"
							name="rating" value="5" class="star"></td>
						<td align="center" valign="middle"><form:select
								id="appStatus"
								path="manageJobSeekerDTOList[${status.index}].applicationStatus"
								class="jb_input3 marginLeft10 marginTopBottom0 " name="select"
								items="${appStatusList}" itemLabel="optionName"
								itemValue="optionId">
							</form:select></td>
						<td align="center" valign="middle">12/12/2011</td>
						<td align="center" valign="middle"><a href="#"><img
								src="../resources/images/View.png" width="20" height="20" alt=""></a>&nbsp;<a
							href="#"><img src="../resources/images/Download.png"
								width="20" height="20" alt="Download"></a>&nbsp;<a href="#"><img
								src="../resources/images/Print2.png" width="20" height="20"
								alt="Print"></a>&nbsp;<a href=""><img
								src="../resources/images/EmailOrange.png" width="20" height="20"
								alt="E-mail"></a>&nbsp;<a href="#"><img
								src="../resources/images/Delete.png" width="20" height="20"
								alt="Delete"></a></td>
					</tr>
				</c:forEach>

			</table>
		</div>

	</form:form>
</body>
</html>