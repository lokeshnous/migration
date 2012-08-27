<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Page styles -->
<link type='text/css' href="resources/css/demo.css" rel='stylesheet'
	media='screen' />

<!-- Contact Form CSS files -->
<link type='text/css' href="resources/css/basic.css" rel='stylesheet'
	media='screen' />
	
	<script type="text/javascript">
	function setbg(color)
	{
	document.getElementById("styled").style.background=color
	}
	</script>

<title>Insert title here</title>
</head>
<body>
		<div >
	
		<form:form method="post" action="copyPasteResumeSubmit.html"
			commandName="createResume">
			<input type="hidden" name="resume_name" value="${createResume.resume_name}"/>
			<input type="hidden" name="resumeType" value="${createResume.resumeType}"/>
			<input type="hidden" name="desired_job_title" value="${createResume.desired_job_title}"/>
			<input type="hidden" name="desired_employment_type" value="${createResume.desired_employment_type}"/>
			<input type="hidden" name="resume_visibility" value="${createResume.resume_visibility}"/>
			<input type="hidden" name="work_authorization_US" value="${createResume.work_authorization_US}"/>
			<div><h3 style="font: bold; color: white;">Copy Paste Resume</h3><br></div>
			
			<div  style="text-align: center;">
				<form:textarea path="resumeText" id="styled" onfocus="this.value=''; setbg('#e5fff3');" onblur="setbg('white')" rows="20" cols="100" />
			</div>
			<div style="text-align: center;">
				<input type="submit" value="create" />
				<input type="button"
					value="cancel" />
			</div>

		</form:form>
	</div>
</body>
</html>