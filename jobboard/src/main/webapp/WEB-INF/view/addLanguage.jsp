 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="job_seeker_login leftFormHolderResumepage">
<p class="borderBottomDotted marginBottom15">&nbsp;</p> 
	<div class="row">
		<span class="lableTextSelect">Language:</span>
		<form:select path="createResume.listLangForm[${langPositionId}].language"
			class="jb_input3 jb_input_width3">
			<form:options items="${languagelList}"
				itemValue="optionName" itemLabel="optionName" />
		</form:select>
	</div>
	<div class="row">
		<span class="lableTextSelect ">Proficiency Level:</span>
		<form:select path="createResume.listLangForm[${langPositionId}].expLvl"
			class="jb_input3 jb_input_width3">
			<form:option value="0" label="--- Proficiency Level ---" />
			<form:options items="${langProficiencylList}"
				itemValue="optionName" itemLabel="optionName" />
		</form:select>
	</div>
	<div class="row MarginBottom10">
		<span class="lableTextSelect"></span>
	</div>
</div>
