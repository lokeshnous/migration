
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="rowEvenNewSpacing MarginBottom10">
	<span class="lableText3"></span>
	<div class="floatLeft marginRight10"></div>
	<span class="floatLeft marginRight10"></span>

	<form:input path="brandingTemplateForm.listAddImages[${imagePosId}].addImageFileData" type="file" class="job_seeker_login_email fileType" size="20" />
</div>