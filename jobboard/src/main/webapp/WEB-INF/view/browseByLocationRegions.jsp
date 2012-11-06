<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="clearfix"></div>
<div class="row marginBottom20">
	<div class="row marginTop5 paddingBottom05">
		<h1 class="FontSize24">Browse Jobs by Location</h1>
	</div>
	<div class="row marginTop10 paddingBottom10DB marginBottom15 cursor">
		<p>
			<a onclick="searchByLocReg('${location}');">View all jobs in ${location} </a>or click on a metro
			area below to browse jobs in a specific part of the state.
		</p>
	</div>
	 <input type="hidden"
		name="browseByLocationReg" id="browseByLocationReg" value="browseByLocationReg" />
	<div class="FloatLeft marginTop10">


		<div class="row width400 paddingLeft15 marginRight10 marginBottom15">
			<c:forEach items="${areaList}" varStatus="status" begin="0"
				end="${jbsByLocationList.size()}" step="2">
				<h3 class="marginBottom3 cursor">
					<a onclick="searchByLocationRegion('${areaList[status.index]}');">
						${areaList[status.index]}</a>
				</h3>
			</c:forEach>
		</div>

		<div class="row width400 paddingLeft15 BorderLeft marginBottom15">
			<c:forEach items="${areaList}" varStatus="status" begin="1"
				end="${jbsByLocationList.size()}" step="2">
				<h3 class="marginBottom3">
					<a onclick="searchByLocationRegion('${areaList[status.index]}');">
						${areaList[status.index]}</a>
				</h3>
			</c:forEach>
		</div>
	</div>
</div>

<div class="clearfix"></div>

