<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>ADVANCE Heathcare Jobs</title>

		<!-- ../resources/css -->
		<link href="../resources/css/JB.css" rel="stylesheet" type="text/css" />
		<link href="../resources/css/jquery.megamenu.css" rel="stylesheet" type="text/css" />
		<link href="../resources/css/SliderStyles.css" rel="stylesheet" type="text/css">

		<!-- JAVASCRIPT FILES -->
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
		<script type="text/javascript" src="../resources/js/jquery.cycle.all.min.js"></script>
		<script type="text/javascript" src="../resources/js/slider.js"></script>
		<script type="text/javascript" src="../resources/js/jquery.megamenu.js"></script>
		<script type="text/javascript">
		    jQuery(document).ready(function(){
		    jQuery(".megamenu").megamenu();
		});
		</script>

		</head>

		<body class="job_board">
		<form action=""></form>		
        <div id="jobSeekerRegister1" class="job_seeker_login popUpContainer" style="display:block">
          <div class="popupHeader">
            <h2>MODIFY SUBSCRIPTIONS</h2>
            <a href="#"><img src="../resources/images/Close.png" width="19" height="19" alt=""></a></div>
          <div class="popUpContainerWrapper">

            <form:form method="Post" action="saveJobSeekerSubscription.html"  commandName="jobSeekerSubscriptionForm" >
              <div class="rowEvenNewSpacing marginTop0">
              	 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="grid2">
						<tr>
							<td valign="top">						
				     			<table> 
				     				<tr class="borderTopNone">
				    					<td class="Header3">Subscriptions</td>
				     				</tr>
				     				
  									<c:forEach items="${jobSubscriptionsList}" var="subscriptions" varStatus="index">
										<tr>	
											<td> <form:checkbox path="currentsubs" label="${subscriptions.subscriptionName}" value="${subscriptions.subscriptionId}"   cssStyle="width:20px"/></td>
										</tr>
									</c:forEach>						
				     			</table>
								<table> 
									<tr class="borderTopNone"><td class="Header3">Job Alerts</td></tr>	 			
 									<c:forEach items="${jobAlertsList}" var="jobAlert" varStatus="index">
										<tr>	
											<td> <form:checkbox path="currentJobAlerts" label="${jobAlert.alertName}" value="${jobAlert.alertId}"   cssStyle="width:20px"/></td>
										</tr>
									</c:forEach>	 				
							 	</table>
						 		<table> 
									<tr class="borderTopNone"><td class="Header3">Magazines</td></tr>	 			
									<c:forEach items="${jobMagazinesList}" var="magazine" varStatus="index">
										<tr>	
											<td> <form:checkbox path="currentmagazines" label="${magazine.magazineName}" value="${magazine.magazineId}"   cssStyle="width:20px"/></td>
										</tr>
									</c:forEach> 						
							 	</table>
						 	</td>
						</tr> 	
              	 </table>
<!--                 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="grid2">
                  <tr  class="borderTopNone">
                    <th width="46%" align="left" scope="col" >Magazines</th>
                    <th width="26%" align="left" scope="col">Job Alerts</th>
                    <th width="28%" align="left" scope="col">Subscriptions</th>

                  </tr>
                  <tr>
                    <td>
                      <input type="checkbox" name="checkbox" id="checkbox"> &nbsp;&nbsp;<label for="checkbox">Nurses</label></td>
                    <td><input type="checkbox" name="checkbox" id="checkbox"> 
                    &nbsp;&nbsp;<label for="checkbox">Mobile</label></td>
                    <td><input type="checkbox" name="checkbox" id="checkbox">&nbsp;&nbsp;<label for="checkbox">E-newsletters</label></td>

                  </tr>
                  <tr>
                    <td><input type="checkbox" name="checkbox" id="checkbox">
                    &nbsp;&nbsp;<label for="checkbox2"></label><label for="checkbox">NPs & PAs</label></td>
                    <td><input type="checkbox" name="checkbox" id="checkbox"> &nbsp;&nbsp;<label for="checkbox">Email</label></td>
                    <td ><input type="checkbox" name="checkbox" id="checkbox">&nbsp;&nbsp;<label for="checkbox">E-mailer</label></td>

                  </tr>
                  <tr>
                    <td><input type="checkbox" name="checkbox" id="checkbox"> &nbsp;&nbsp;<label for="checkbox">Executive Insight</label></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td><input type="checkbox" name="checkbox" id="checkbox"> &nbsp;&nbsp;<label for="checkbox">Medical Laboratory</label></td>

                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td><input type="checkbox" name="checkbox" id="checkbox"> &nbsp;&nbsp;<label for="checkbox">Health Information</label></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>

                  <tr>
                    <td><input type="checkbox" name="checkbox" id="checkbox"> &nbsp;&nbsp;<label for="checkbox">Occupational Therapy</label></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td><input type="checkbox" name="checkbox" id="checkbox"> &nbsp;&nbsp;<label for="checkbox">Speech-Language Pathologists &amp; Audiologists</label></td>

                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td><input type="checkbox" name="checkbox" id="checkbox"> &nbsp;&nbsp;<label for="checkbox">Imaging &amp; Radiation Oncology</label></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>

                  </tr>
                  <tr>
                    <td><input type="checkbox" name="checkbox" id="checkbox"> &nbsp;&nbsp;<label for="checkbox">Physical Therapy & Rehab Medicine</label></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>

                    <td><input type="checkbox" name="checkbox" id="checkbox"> 
                    &nbsp;&nbsp;<label for="checkbox">Long-Term Care Management</label></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td><input type="checkbox" name="checkbox" id="checkbox"> 
                    &nbsp;&nbsp;<label for="checkbox">Administrators of the  Laboratory</label></td>
                    <td>&nbsp;</td>

                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td><input type="checkbox" name="checkbox" id="checkbox"> 
                    &nbsp;&nbsp;<label for="checkbox">Respiratory Care & Sleep Medicine</label></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>

                  <tr>
                    <td><input type="checkbox" name="checkbox" id="checkbox"> 
                    &nbsp;&nbsp;<label for="checkbox">Hearing Practice Management</label></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                </table> -->
              </div>
              <div class="row marginTop5 paddingBottom10"> <span class="floatLeft marginTop10"><a href="" class="btn_sm orange">Save</a> <a href="" class="btn_sm orange">Cancel</a></span>  </div>

            </form:form>
          </div>
          <div class="clearfix"></div>
        </div>
</body>
</html>