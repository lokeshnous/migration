<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
                                                  prefix="fn" %>
<div class="clearfix"></div>
      <div class="row marginBottom20">
        <div class="row marginTop5 paddingBottom05"><h1 class="FontSize24">Browse Jobs by Job Title</h1></div>
         <input type="hidden" name="browseByTitle" id="browseByTitle" value="browseByTitle"/>
             <div class="browsByColumns browsByColumnsNoBorder">
             <ul>
			<c:forEach items="${jbsByTitleList}" var="titleList" begin="0"
				end="${jbsByTitleList.size()}" step="4">
				<li><a
					href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/jobsearch/jobtitle/${fn:replace(fn:trim(fn:split(titleList, '\\(')[0]),' ', '-')}.html"
					class="link_color2_basic cursor">${titleList}</a></li>
			</c:forEach>
		</ul>
     		 </div>
     		 <div class="browsByColumns browsByColumnsWithBorder">
     		 <ul>
	            <c:forEach items="${jbsByTitleList}" var="titleList" begin="1" end="${jbsByTitleList.size()}" step="4">
	             	<li><a
					href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/jobsearch/jobtitle/${fn:replace(fn:trim(fn:split(titleList, '\\(')[0]),' ', '-')}.html"
					class="link_color2_basic cursor">${titleList}</a></li>
	             </c:forEach>
	             </ul>
     		 </div>
     		 <div class="browsByColumns browsByColumnsWithBorder">
     		 <ul>
	            <c:forEach items="${jbsByTitleList}" var="titleList" begin="2" end="${jbsByTitleList.size()}" step="4">
	             <li><a
					href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/jobsearch/jobtitle/${fn:replace(fn:trim(fn:split(titleList, '\\(')[0]),' ', '-')}.html"
					class="link_color2_basic cursor">${titleList}</a></li>
	             </c:forEach>
	             </ul>
     		 </div>
     		 <div class="browsByColumns browsByColumnsWithBorder">
     		 <ul>
	            <c:forEach items="${jbsByTitleList}" var="titleList" begin="3" end="${jbsByTitleList.size()}" step="4">
	             	<li><a
					href="<%=request.getRequestURL().toString().replace(request.getServletPath(),"") %>/jobsearch/jobtitle/${fn:replace(fn:trim(fn:split(titleList, '\\(')[0]),' ', '-')}.html"
					class="link_color2_basic cursor">${titleList}</a></li>
	             </c:forEach>
	             </ul>
     		 </div>
                </div>