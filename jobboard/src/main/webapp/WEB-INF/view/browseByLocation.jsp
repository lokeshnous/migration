<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
                                                  prefix="fn" %>
<div class="clearfix"></div>
<div class="row marginBottom20">
	<div class="row marginTop5 paddingBottom05">
		<h1 class="FontSize24">Browse Jobs by Location</h1>
	</div>
	
		<input type="hidden" name="browseByLocation" id="browseByLocation" value="browseByLocation"/>
		<div class="LocationNameArea LocationBorderRight LocationPaddingLeft">
             <ul>
	            <c:forEach items="${jbsByLocationList}" var="locationList" begin="0" end="${jbsByLocationList.size()}" step="4">
	             	 <li><a class="link_color2_basic"
	             	 href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/jobsearch/browse/${fn:replace(fn:trim(fn:split(locationList, '\\(')[0]),' ', '-')}/areas.html"
	             	 >${locationList}</a></li>
	             </c:forEach>
	         </ul>
     		 </div>
     		 <div class="LocationNameArea LocationBorderRight LocationPaddingLeft">
     		 <ul>
	            <c:forEach items="${jbsByLocationList}" var="locationList" begin="1" end="${jbsByLocationList.size()}" step="4">
	             	<li><a class="link_color2_basic"
	             	href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/jobsearch/browse/${fn:replace(fn:trim(fn:split(locationList, '\\(')[0]),' ', '-')}/areas.html" 
					>${locationList}</a></li>
	             </c:forEach>
	             </ul>
     		 </div>
     		 <div class="LocationNameArea LocationBorderRight LocationPaddingLeft">
     		 <ul>
	            <c:forEach items="${jbsByLocationList}" var="locationList" begin="2" end="${jbsByLocationList.size()}" step="4">
	             <li><a class="link_color2_basic" 
	             href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/jobsearch/browse/${fn:replace(fn:trim(fn:split(locationList, '\\(')[0]),' ', '-')}/areas.html"
	             >${locationList}</a></li>
	             </c:forEach>
	             </ul>
     		 </div>
     		 <div class="LocationNameArea LocationBorderRight LocationPaddingLeft">
     		 <ul>
	            <c:forEach items="${jbsByLocationList}" var="locationList" begin="3" end="${jbsByLocationList.size()}" step="4">
	             	<li><a class="link_color2_basic"
	             	 href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/jobsearch/browse/${fn:replace(fn:trim(fn:split(locationList, '\\(')[0]),' ', '-')}/areas.html"
	             	 >${locationList}</a></li>
	             </c:forEach>
	             </ul>
     		 </div>
     		 <div class="LocationNameArea LocationBorderRight LocationPaddingLeft">
     		 <ul>
	            <c:forEach items="${jbsByLocationList}" var="locationList" begin="4" end="${jbsByLocationList.size()}" step="4">
	             	<li><a class="link_color2_basic" 
	             	href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/jobsearch/browse/${fn:replace(fn:trim(fn:split(locationList, '\\(')[0]),' ', '-')}/areas.html"
	             	>${locationList}</a></li>
	             </c:forEach>
	             </ul>
     		 </div>
     		 
	
</div>
