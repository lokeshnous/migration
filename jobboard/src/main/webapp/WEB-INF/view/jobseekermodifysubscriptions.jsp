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
		<form:form method="Post" action="saveJobSeekerSubscription.html"  commandName="jobSeekerSubscriptionForm" >
        <div id="jobSeekerRegister1" class="job_seeker_login popUpContainer" style="display:block">
          <div class="popupHeader">
            <h2>MODIFY SUBSCRIPTIONS</h2>
            <a href="#"><img src="../resources/images/Close.png" width="19" height="19" alt=""></a></div>
          <div class="popUpContainerWrapper">

            
              <div class="rowEvenNewSpacing marginTop0">
              	 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="grid2">
						<tr>
							<td valign="top">						
				     			<table>
				     				<tr  class="borderTopNone">
					     				<th class="borderTopNone" width="46%" align="left" scope="col">
					    					Subscriptions
					     				</th>
				     				</tr>
  									<c:forEach items="${jobSubscriptionsList}" var="subscriptions" varStatus="index">
										<tr>	
											<td> <form:checkbox path="currentsubs" label="${subscriptions.subscriptionName}" value="${subscriptions.subscriptionId}"   cssStyle="width:20px"/></td>
										</tr>
									</c:forEach>						
				     			</table>
				     		</td>
				     		<td valign="top">
								<table> 
									<tr  class="borderTopNone">
										<th class="borderTopNone" width="46%" align="left" scope="col">Job Alerts</th>
									</tr>			 			
 									<c:forEach items="${jobAlertsList}" var="jobAlert" varStatus="index">
										<tr>	
											<td> <form:checkbox path="currentJobAlerts" label="${jobAlert.alertName}" value="${jobAlert.alertId}"   cssStyle="width:20px"/></td>
										</tr>
									</c:forEach>	 				
							 	</table>
							</td>
							<td valign="top">
						 		<table> 
						 			<tr  class="borderTopNone">
										<th class="borderTopNone" width="46%" align="left" scope="col">Magazines</th>
									</tr>	 			
									<c:forEach items="${jobMagazinesList}" var="magazine" varStatus="index">
										<tr>	
											<td> <form:checkbox path="currentmagazines" label="${magazine.magazineName}" value="${magazine.magazineId}"   cssStyle="width:20px"/></td>
										</tr>
									</c:forEach> 						
							 	</table>
						 	</td>
						</tr> 	
              	 </table>
              </div>
              <div class="row marginTop5 paddingBottom10"> <span class="floatLeft marginTop10"><a href="" class="btn_sm orange">Save</a> <a href="" class="btn_sm orange">Cancel</a></span>  </div>


          </div>
          <div class="clearfix"></div>
        </div>
   </form:form>
</body>
</html>