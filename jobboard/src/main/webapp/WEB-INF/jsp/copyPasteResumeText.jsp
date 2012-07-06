<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div style="text-align: center;">
		<form:form method="post" action="copyPasteResumeSubmit.html"
			commandName="createResume">
			<input type="hidden" name="resume_name" value="${createResume.resume_name}"/>
			<input type="hidden" name="resumeType" value="${createResume.resumeType}"/>
			<input type="hidden" name="desired_job_title" value="${createResume.desired_job_title}"/>
			<input type="hidden" name="desired_employment_type" value="${createResume.desired_employment_type}"/>
			<input type="hidden" name="resume_visibility" value="${createResume.resume_visibility}"/>
			<input type="hidden" name="work_authorization_US" value="${createResume.work_authorization_US}"/>
			
			<div  style="text-align: left;">
				<form:textarea path="resumeText" rows="20" />
			</div>
			<div style="text-align: left;">
				<input type="submit" value="create" /><input type="button"
					value="cancel" />
			</div>

		</form:form>
	</div>
</body>
</html>