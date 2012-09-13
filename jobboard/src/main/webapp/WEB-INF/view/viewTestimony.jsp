<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="common/include.jsp" />


</head>
<body>
	<%-- <img src="<%=request.getContextPath()%>/brandingTemplates/viewImage.html?id=${imageId}"/> --%>
	<%-- ${imageId} --%>
	<span style="color:#000000 !important; font-size:14px !important;">
	${brandingTemplateForm.testimonyContainer}
	</span>
</body>
</html>