<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
                                                  prefix="fn" %>
<div class="clearfix"></div>
<div class="row marginBottom20 ">
	<div class="row marginTop10 paddingBottom05">
		<div class="floatLeft">
			<h1 class="FontSize24">Browse Jobs by Employer</h1>
		</div>
		<div class="NameSelectonArea">
			<ul>
				<li><c:forEach items="${employerFirstList}" var="jobByEmployer"
						varStatus="status">
						<a class="cursor" onclick="selectEmployerBlock(this.id, '${jobByEmployer.key}', ${employerFirstList.size()+employerSecList.size()+employerThirdList.size()})" id="empKey${status.index}">${jobByEmployer.key}</a>
					</c:forEach>
					<c:forEach items="${employerSecList}" var="jobByEmployer"
							varStatus="status">
							<a class="cursor" onclick="selectEmployerBlock(this.id, '${jobByEmployer.key}', ${employerFirstList.size()+employerSecList.size()+employerThirdList.size()})" id="empKey${status.index+employerFirstList.size()}">${jobByEmployer.key}</a>
						</c:forEach>
					<c:forEach items="${employerThirdList}" var="jobByEmployer"
							varStatus="status">
							<a class="cursor" onclick="selectEmployerBlock(this.id, '${jobByEmployer.key}', ${employerFirstList.size()+employerSecList.size()+employerThirdList.size()})" id="empKey${status.index+employerFirstList.size()+employerSecList.size()}">${jobByEmployer.key}</a>
						</c:forEach>					
				</li>
			</ul>
		</div>
	</div>
	<input type="hidden" name="browseByEmployer" id="browseByEmployer"
		value="browseByEmployer" />
	<div class="marginTop10">
		<div class="LocationNameArea LocationBorderRight threecolumn">
			<c:forEach items="${employerFirstList}" var="jobByEmployer"
				varStatus="status" begin="0" end="${employerFirstList.size()}"
				>
				<div class="NameOrderNormal"  key="${jobByEmployer.key}"
				id="empBlockKey${status.index}">${jobByEmployer.key}</div>
				<ul class="MarginBottom10">
					<c:forEach items="${jobByEmployer.value}" var="emplyrsName"
						varStatus="emplyrsStatus">
						<li><a 
						href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/jobsearch/employer/${fn:replace(fn:trim(fn:split(emplyrsName, '\\(')[0]),' ', '-')}.html"
							class="link_color2_basic cursor">${emplyrsName}</a></li>
					</c:forEach>
				</ul>
			</c:forEach>
		</div>

		<div
			class="LocationNameArea LocationBorderRight LocationPaddingLeft threecolumn ">
			<c:forEach items="${employerSecList}" var="jobByEmployer"
				varStatus="status" begin="0" end="${employerSecList.size()}"
				>
				<div class="NameOrderNormal"  key="${jobByEmployer.key}"
				id="empBlockKey${status.index+employerFirstList.size()}"
				>${jobByEmployer.key}</div>
				<ul class="MarginBottom10">
					<c:forEach items="${jobByEmployer.value}" var="emplyrsName"
						varStatus="emplyrsStatus">
						<li><a
						href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/jobsearch/employer/${fn:replace(fn:trim(fn:split(emplyrsName, '\\(')[0]),' ', '-')}.html" 
							class="link_color2_basic cursor">${emplyrsName}</a></li>
					</c:forEach>
				</ul>
			</c:forEach>
		</div> 
		<div class="LocationNameArea LocationPaddingLeft threecolumn ">
			<c:forEach items="${employerThirdList}" var="jobByEmployer"
				varStatus="status" begin="0" end="${employerThirdList.size()}"
				>
				<div class="NameOrderNormal" key="${jobByEmployer.key}"
				id="empBlockKey${status.index+employerFirstList.size()+employerSecList.size()}"
				>${jobByEmployer.key}</div>
				<ul class="MarginBottom10">
					<c:forEach items="${jobByEmployer.value}" var="emplyrsName"
						varStatus="emplyrsStatus">
						<li><a 
						href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/jobsearch/employer/${fn:replace(fn:trim(fn:split(emplyrsName, '\\(')[0]),' ', '-')}.html"
							class="link_color2_basic cursor">${emplyrsName}</a></li>
					</c:forEach>
				</ul>
			</c:forEach>
		</div>
	</div>
</div>
<div class="ad_wrapper">
	<img src="images/ads/banner_ad_fpo.png" />
</div>
