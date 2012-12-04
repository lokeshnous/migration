<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Insert title here</title>
<script type="text/javascript">
	function cancelProcess() {
		parent.$.nmTop().close();
		//location.reload();
	}

	 
	$("#Clear")
			.click(
					function() {

						$
								.ajax({
									url : "${pageContext.request.contextPath}/jobsearch/clearallsearches.html",
									success : function(data) {
										//alert("All recent search has been cleared!");
										parent.$.nmTop().close();
										window.location.reload();
									},
								});
					});
</script>
</head>
<body class="job_board">
	<div id="jobSeekerRegister1" class="job_seeker_login popUpContainer"
		style="display: block">
		<input type="hidden" name="myrecentserach" id="myrecentserach"
			value="myrecentserach" />
		<div class="popupHeader">
			<h2>MY RECENT SEARCHES</h2>
			<img src="../resources/images/Close.png" width="19" height="19"
				title="Close" alt="" onclick="parent.$.nmTop().close();">
		</div>
		<div id="recentId">
			<ul>

				<!--  <li> -->
				<!--  <a href="" ></a>Clear All&nbsp;&nbsp;|&nbsp;&nbsp;<a href="" ></a>See All </li>-->
				<c:forEach items="${recentSplit}" var="item">
					<div class="rowPadding borderBottomDotted" id="searchdata">
						<li>${item.createdDate.toLocaleString()}, Search by: <span class="jb"><a
								href="#" onclick="performCurrentSearch(${item.recentURL})">${item.recentURL}</a></span> <input
							type="button" value="Save This Search" class="white floatRight"
							id="SaveThisSearch" onclick="saveRecentSearch(${item.saveSearchID});"/>
						</li>

					</div>
				</c:forEach>

			</ul>
		</div>



		<div class="popUpButtonRow" align="left">
			<input type="button" value="Clear All" class="orange" id="Clear" />
		    <input type="button" value="Cancel" onclick="cancelProcess();" class="orange" name="Cancel" />

		</div>



	</div>
	</div>
</body>
</html>