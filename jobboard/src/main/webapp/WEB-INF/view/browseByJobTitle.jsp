<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="clearfix"></div>
<div class="row marginBottom20">
	<div class="row marginTop10 paddingBottom05">
		<div class="floatLeft">
			<h1 class="FontSize24">Browse Jobs by Job Title</h1>
		</div>
		<div class="NameSelectonArea">
			<ul>
				<li><c:forEach items="${firstColPositionList}" var="jobByTitle"
						varStatus="status">
						<a class="cursor" onclick="selectEmployerBlock(this.id, '${jobByTitle.key}', ${firstColPositionList.size()+secColPositionList.size()+thirdColPositionList.size()})" id="empKey${status.index}">${jobByTitle.key}</a>
					</c:forEach>
					<c:forEach items="${secColPositionList}" var="jobByTitle"
							varStatus="status">
							<a class="cursor" onclick="selectEmployerBlock(this.id, '${jobByTitle.key}', ${firstColPositionList.size()+secColPositionList.size()+thirdColPositionList.size()})" id="empKey${status.index+firstColPositionList.size()}">${jobByTitle.key}</a>
						</c:forEach>
					<c:forEach items="${thirdColPositionList}" var="jobByTitle"
							varStatus="status">
							<a class="cursor" onclick="selectEmployerBlock(this.id, '${jobByTitle.key}', ${firstColPositionList.size()+secColPositionList.size()+thirdColPositionList.size()})" id="empKey${status.index+firstColPositionList.size()+secColPositionList.size()}">${jobByTitle.key}</a>
						</c:forEach>					
				</li>
			</ul>
		</div>
	</div>
	<input type="hidden" name="browseByTitle" id="browseByTitle"
		value="browseByTitle" />
	<div class="marginTop10">
		<div class="LocationNameArea LocationBorderRight threecolumn">
			<c:forEach items="${firstColPositionList}" var="jobByTitle"
				varStatus="status" begin="0" end="${firstColPositionList.size()}"
				>
				<div class="NameOrderNormal"  key="${jobByTitle.key}"
				id="empBlockKey${status.index}">${jobByTitle.key}</div>
				<ul class="MarginBottom10">
					<c:forEach items="${jobByTitle.value}" var="jobTitle"
						varStatus="emplyrsStatus">
						<li><a 
						href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/search/title/${jobTitle.encodeJobtitle}.html"
							class="link_color2_basic cursor">${jobTitle.jobtitle}</a></li>
					</c:forEach>
				</ul>
			</c:forEach>
		</div>

		<div
			class="LocationNameArea LocationBorderRight LocationPaddingLeft threecolumn ">
			<c:forEach items="${secColPositionList}" var="jobByTitle"
				varStatus="status" begin="0" end="${secColPositionList.size()}"
				>
				<div class="NameOrderNormal"  key="${jobByTitle.key}"
				id="empBlockKey${status.index+firstColPositionList.size()}"
				>${jobByTitle.key}</div>
				<ul class="MarginBottom10">
					<c:forEach items="${jobByTitle.value}" var="jobTitle"
						varStatus="emplyrsStatus">
						<li><a
						href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/search/title/${jobTitle.encodeJobtitle}.html" 
							class="link_color2_basic cursor">${jobTitle.jobtitle}</a></li>
					</c:forEach>
				</ul>
			</c:forEach>
		</div> 
		<div class="LocationNameArea LocationPaddingLeft threecolumn ">
			<c:forEach items="${thirdColPositionList}" var="jobByTitle"
				varStatus="status" begin="0" end="${thirdColPositionList.size()}"
				>
				<div class="NameOrderNormal" key="${jobByTitle.key}"
				id="empBlockKey${status.index+firstColPositionList.size()+secColPositionList.size()}"
				>${jobByTitle.key}</div>
				<ul class="MarginBottom10">
					<c:forEach items="${jobByTitle.value}" var="jobTitle"
						varStatus="emplyrsStatus">
						<li><a 
						href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/search/title/${jobTitle.encodeJobtitle}.html"
							class="link_color2_basic cursor">${jobTitle.jobtitle}</a></li>
					</c:forEach>
				</ul>
			</c:forEach>
		</div>
	</div>
</div>