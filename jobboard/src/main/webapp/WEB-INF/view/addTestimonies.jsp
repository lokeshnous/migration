 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="rowEvenNewSpacing MarginBottom10" id="testimoni${testimonyPosId}">
	<span class="lableText7"></span>
	<div class="floatLeft marginRight10"></div>
	<span class="floatLeft marginRight10"></span>
	
	
<!-- <div class="rowEvenNewSpacing MarginBottom10">
	<span class="floatRight marginRight10"/>
	<span class="floatLeft marginRight10"> 
	 <div class="floatLeft marginRight10"></div> -->
		<form:textarea
			path="brandingTemplateForm.listTestimony[${testimonyPosId}].testimony" class="textareaBoxCResumeTemplate" rows="5" cols="45" />
	</span><span> <img id="closeCheckOut" onclick="removeTestimoni('testimoni${testimonyPosId}',${testimonyPosId})"	src="<%= request.getContextPath() %>/resources/images/Close.png"
																class="cursor" title="Delete" alt="Delete" /></span>
</div>