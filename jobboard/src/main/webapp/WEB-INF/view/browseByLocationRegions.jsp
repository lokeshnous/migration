<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="clearfix"></div>
<div class="row marginBottom20">
	<div class="row marginTop5 paddingBottom05">
		<h1 class="FontSize24">Browse Jobs by Location</h1>
	</div>
	<div class="row marginTop10 paddingBottom10DB marginBottom15">
		<p>
			<a href="#">View all jobs in Pennsylvania </a>or click on a metro
			area below to browse jobs in a specific part of the state.
		</p>
	</div>
<input type="hidden" name="browseByLocation" id="browseByLocation" value="browseByLocation"/>
	<div class="FloatLeft marginTop10">
		<div class="row width467 marginRight12">
			<div class="FloatLeft paddingBottom10DB">
			 <c:forEach items="${areaList}" varStatus="status" begin="1" end="${jbsByLocationList.size()}" step="2">
				<h3 class="marginBottom3">
					<a class="link_color2_basic cursor" onclick="searchByLocationRegion('${areaList[status.index]}');"> ${areaList[status.index]}</a>
				</h3>
			</c:forEach>	
			</div>
			<div class="FloatLeft marginTop10 paddingBottom10">
			 <c:forEach items="${areaList}" varStatus="status" begin="2" end="${jbsByLocationList.size()}" step="2">
				<h3 class="marginBottom3">
					<a href="#"> ${areaList[status.index]}</a>
				</h3>
			</c:forEach>	
			</div>
			<!-- <div class="FloatLeft marginTop10 paddingBottom10">
			
				<h3 class="marginBottom3">
					<a href="#"> Pittsburgh Metro Area </a>
				</h3>
			</div>

		</div>
		<div class="row width467 paddingLeft12 BorderLeft">
			<div class="FloatLeft paddingBottom10DB">
				<h3 class="marginBottom3">
					<a href="#">Pittsburgh Metro Area</a>
				</h3>
			</div>
			<div class="FloatLeft marginTop10 paddingBottom10">
				<h3 class="marginBottom3">
					<a href="#">Philadelphia Metro Area</a>
				</h3>
			</div>

		</div> -->
	</div>
</div>

<div class="clearfix"></div>

<div class="ad_wrapper">
	<img src="images/ads/banner_ad_fpo.png" />
</div>

