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
				<li><c:forEach items="${jbsByEmployerList}" var="jobByEmployer"
						varStatus="status">
						<a href="#" id="status">${jobByEmployer.key}</a>
					</c:forEach></li>
			</ul>
		</div>
	</div>
	<input type="hidden" name="browseByEmployer" id="browseByEmployer"
		value="browseByEmployer" />
	<div class="marginTop10">
		<div class="LocationNameArea LocationBorderRight threecolumn">
			<c:forEach items="${jbsByEmployerList}" var="jobByEmployer"
				varStatus="status" begin="0" end="${jbsByEmployerList.size()}"
				step="3">
				<div class="NameOrderNormal">${jobByEmployer.key}</div>
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
			<c:forEach items="${jbsByEmployerList}" var="jobByEmployer"
				varStatus="status" begin="1" end="${jbsByEmployerList.size()}"
				step="3">
				<div class="NameOrderNormal">${jobByEmployer.key}</div>
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
			<c:forEach items="${jbsByEmployerList}" var="jobByEmployer"
				varStatus="status" begin="2" end="${jbsByEmployerList.size()}"
				step="3">
				<div class="NameOrderNormal">${jobByEmployer.key}</div>
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
