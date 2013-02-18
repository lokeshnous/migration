 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="common/include.jsp" />
<script type="text/javascript">
	jQuery(document).ready(function() {	
		$(".addPhoneNo").inputmask("mask", {"mask": "(999) 999-9999"});
		$(".addPhoneNo").focus();
	});		
	
</script>
<div class="rowEvenNewSpacing MarginBottom10" id="phone${phNoPositionId}">
	<span class="lableText3"></span>
	<div class="floatLeft marginRight10"></div>
	<span class="floatLeft marginRight10"></span>
	<form:select path="createResume.listPhoneDtlForm[${phNoPositionId}].phoneType"
		id="exclude" class="jb_input75">
		<form:options items="${phoneTypeList}" itemValue="optionId" itemLabel="optionName" />
	</form:select>
	<form:input path="createResume.listPhoneDtlForm[${phNoPositionId}].phoneNumber" class="job_seeker_password addPhoneNo" />
	<span  class="required"><img id="closeCheckOut" onclick="pop('phone${phNoPositionId}',${phNoPositionId})" src="<%= request.getContextPath() %>/resources/images/Close.png" class="cursor" title="Delete" alt="Delete"/></span>
 <span class="required">(Required)</span> </div>