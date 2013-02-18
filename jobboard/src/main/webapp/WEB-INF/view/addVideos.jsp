
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="rowEvenNewSpacing MarginBottom10" id="video${videoPosId}">
	<span class="lableText7"></span>
	<div class="floatLeft marginRight10"></div>
	<span class="floatLeft marginRight10"></span>
    <div class="clearfix"></div>
    <span class="lableTextCoverletter width150">&nbsp;</span>
	<form:input path="brandingTemplateForm.listVideos[${videoPosId}].videoFileData" type="file" class="job_seeker_login_email fileType" size="20" />
	
	<span> <img id="closeCheckOut" onclick="removeVideo('video${videoPosId}',${videoPosId})"	src="<%= request.getContextPath() %>/resources/images/Close.png"
																class="cursor" title="Delete" alt="Delete" /></span>
	
</div>