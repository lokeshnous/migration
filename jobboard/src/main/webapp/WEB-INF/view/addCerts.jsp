 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
    <div class="job_seeker_login leftFormHolderResumepage marginTop0" id="addCertDivId">
     <div class="rowEvenNewSpacing"> <span class="lableText3">Certification Name:</span>
         <form:input path="${education.certificationName}" class="job_seeker_password textBox350"/>
         <span class="required">(Required)</span> </div>
       <div class="rowEvenNewSpacing"> <span class="lableText3">Certifying Authority:</span>
         <form:input path="${education.certificationName}" class="job_seeker_password textBox350"/>
       </div>

               <div class="rowEvenNewSpacing"> <span class="lableText3"> Received:</span>
         <form:input path="${education.dateOfReceipt}" class="job_seeker_Resume"/>
	</div>
               <div class="row MarginBottom10 ">
         <div class="lableText3 marginTop10"> Summary:</div>
         <div class="input_grp5 ">

                   <form:textarea path="${education.summary}" class="textareaBoxCResume" rows="3" cols="45"/>
                   <p>2000 characters remaining</p>
                 </div>
       </div>
     </div>

      