<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>

<jsp:include page="common/include.jsp" />
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery(".megamenu").megamenu();
	});
</script>
</head>

<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<div class="popupHeader">
			<h2>MANAGE/EDIT JOB POSTING</h2>
			<img id="closeCheckOut" src="<%= request.getContextPath() %>/resources/images/Close.png" class="nyroModalClose" alt="Close"/>
		</div>
		<div class="popUpContainerWrapper">
			<form:form method="POST">
			<div class="rowEvenNewSpacing">
					<span class="lableText3">
						Adv Job Id
					</span>
					<input  name="advJobId" id="advJobId" class="job_seeker_email" type="text"/>
					<input type="button" value="Search" name="Search" id="Search" class="btn_sm orange" />
					<div class="toolTip"><span class="classic">Example: Only Job id like 15030</span></div>
			</div>
			  <div class="rowEvenNewSpacing">           
	            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="grid">
	              <tr class="orange">
	              	<th width="20%" align="left" scope="col">Job Id</th>
	              	<th width="20%" align="left" scope="col">Adv Job Id</th>
	                <th width="33%" align="left" scope="col">Job Title</th>
	                <th width="33%" align="left" scope="col">Location</th>
	                <th width="20%" align="center" scope="col">Job Status</th>               
	              </tr>	            
	               <tr>               
	                <td align="left"></td>
	                <td align="center"></td>
	                <td align="center"></td>
	                <td align="center"></td>
	                <td align="center"></td>                
	              </tr>              
	            </table> 
	            </div>        	
              		<div class="rowEvenNewSpacing marginTop20 paddingBottom10">
						<span class="floatLeft marginTop10">
							<input type="button" value="Save" name="Save" id="Save" class="btn_sm orange" />
							<input type="button" name="Cancel" id="Cancel" class="orange" value="Cancel"/>
								
						</span>
					</div>
				<div class="clearfix">
				</div>
			</form:form>
		</div>
		<div class="clearfix">
		</div>
	</div>
</body>

</html>