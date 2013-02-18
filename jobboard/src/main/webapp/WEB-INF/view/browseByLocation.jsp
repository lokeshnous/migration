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
	             <c:forEach items="${firstColStatesList}" var="locationList">
	             	 <li><a class="link_color2_basic"
	             	 href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/search/location/${fn:toLowerCase(fn:replace(fn:trim(fn:split(locationList.encodestate, '\\(')[0]),' ', '-'))}.html"
	             	 >${locationList.state}</a></li>
	             </c:forEach>
	         </ul>
     		 </div>
     		 <div class="LocationNameArea LocationBorderRight LocationPaddingLeft">
     		 <ul>
	            <c:forEach items="${secColStatesList}" var="locationList">
	             	<li><a class="link_color2_basic"
	             	href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/search/location/${fn:toLowerCase(fn:replace(fn:trim(fn:split(locationList.encodestate, '\\(')[0]),' ', '-'))}.html" 
					>${locationList.state}</a></li>
	             </c:forEach>
	             </ul>
     		 </div>
     		 <div class="LocationNameArea LocationBorderRight LocationPaddingLeft">
     		 <ul>
	            <c:forEach items="${thirdColStatesList}" var="locationList">
	             <li><a class="link_color2_basic" 
	             href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/search/location/${fn:toLowerCase(fn:replace(fn:trim(fn:split(locationList.encodestate, '\\(')[0]),' ', '-'))}.html"
	             >${locationList.state}</a></li>
	             </c:forEach>
	             </ul>
     		 </div>
     		 <div class="LocationNameArea LocationBorderRight LocationPaddingLeft">
     		 <ul>
	            <c:forEach items="${fourtColStatesList}" var="locationList">
	             	<li><a class="link_color2_basic"
	             	 href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/search/location/${fn:toLowerCase(fn:replace(fn:trim(fn:split(locationList.encodestate, '\\(')[0]),' ', '-'))}.html"
	             	 >${locationList.state}</a></li>
	             </c:forEach>
	             </ul>
     		 </div>
     		 <div class="LocationNameArea LocationBorderRight LocationPaddingLeft">
     		 <ul>
	            <c:forEach items="${fifthColStatesList}" var="locationList">
	             	<li><a class="link_color2_basic" 
	             	href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/search/location/${fn:toLowerCase(fn:replace(fn:trim(fn:split(locationList.encodestate, '\\(')[0]),' ', '-'))}.html"
	             	>${locationList.state}</a></li>
	             </c:forEach>
	             </ul>
     		 </div>
     		 
	
</div>
