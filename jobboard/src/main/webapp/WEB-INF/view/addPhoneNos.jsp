 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form:select path="createResume.listPhoneDtlForm[${status.index}].phoneType"
	id="exclude" class="jb_input75">
	<form:options items="${phoneTypeList}" itemValue="optionId" itemLabel="optionName" />
</form:select>
<form:input path="createResume.listPhoneDtlForm[${status.index}].phoneNumber" class="job_seeker_password" />
<span class="required ">(Required)</span>