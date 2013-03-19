 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="rowEvenNewSpacing" id="testimoni${testimonyPosId}">
	<span class="lableTextCoverletter marginTop10 width150"></span> 
	<span class="floatLeft marginRight10">
		<form:textarea
			path="brandingTemplateForm.listTestimony[${testimonyPosId}].testimony" class="textareaBoxCResumeTemplate" rows="5" cols="45" />
	<div class="floatRight margin0">
	<p class="floatRight margin0">
	<img id="testimoniDel${testimonyPosId}" onclick="removeTestimoni(this.id)" src="<%= request.getContextPath() %>/resources/images/Close.png"
																class="cursor" title="Delete" alt="Delete" />
	</p>
													</div>
									</span>	
</div>