<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Advance Health care job</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<jsp:include page="common/include.jsp" />
		<script type="text/javascript">
		jQuery(document).ready(function(){ 		
			jQuery(".megamenu").megamenu();   
			});
		</script>
		
	<script type="text/javascript">
		$('#Cancel').click(function(){		
			parent.$.nmTop().close();		
		});		
		</script>
</head>
<body class="job_board">
	<div class="job_seeker_login popUpContainer" id="jobSeekerRegister1" style="display: block;">
		<div class="popupHeader">
			<h2>
				Employers who viewed my profile
			</h2>
			<a href="#"><img width="19" height="19" src="<%= request.getContextPath() %>/resources/images/Close.png" class="nyroModalClose" alt="Close"/></a>
		</div>
		<div class="popUpContainerWrapper">
			<form action="" method="get">
				<div class="rowEvenNewSpacing">
					<table width="100%" class="grid" border="0" cellSpacing="0" cellPadding="0">
						<tbody>
							<tr>
								<td width="50%">
									Companies that have viewed your profile:
								</td>
								<td width="50%" align="left">
								190
								</td>
							</tr>								
							<tr>
								<td width="20">
								Profile appearances in employer search results:
								</td>
								<td width="50%" align="left">
								122
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="row marginTop20 paddingBottom10">
					<input type="button" name="Cancel" id="Cancel" class="orange" value="Cancel"/>
				</div>
			</form>
		</div>
		<div class="clearfix">
		</div>
	</div>
</body>