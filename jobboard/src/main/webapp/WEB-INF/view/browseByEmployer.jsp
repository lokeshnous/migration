<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

     <div class="clearfix"></div>
      <div class="row marginBottom20">
        <div class="row marginTop5 paddingBottom05"><div class="floatLeft"><h1 class="FontSize24">Browse Jobs by Employer</h1></div> <div class="NameSelectonArea">
         <ul>
         <li><a href="#">A</a></li>
         <li><a href="#">B</a></li>
         <li><a href="#">C</a></li>
         <li><a href="#">D</a></li>
         <li><a href="#">E</a></li>
         <li><a href="#">F</a></li>
         <li><a href="#">G</a></li>
         <li><a href="#" class="NameSelected">H</a></li>
         <li><a href="#">I</a></li>
         <li><a href="#">J</a></li>
         <li><a href="#">K</a></li>
         <li><a href="#">L</a></li>
         <li><a href="#">M</a></li>
         <li><a href="#">N</a></li>
         <li><a href="#">O</a></li>
         <li><a href="#">P</a></li>
         <li><a href="#">K</a></li>
         <li><a href="#">R</a></li>
         <li><a href="#">S</a></li>
         <li><a href="#">T</a></li>
         <li><a href="#">U</a></li>
         <li><a href="#">V</a></li>
         <li><a href="#">W</a></li>
         <li><a href="#">X</a></li>
         <li><a href="#">Y</a></li>
         <li><a href="#">Z</a></li>
         </ul>
         </div></div>  
         <div>
         <c:forEach items="${jbsByEmployerList}" var="titleList">
	             	<li><a class="LocationNameArea LocationBorderRight width305 AutoHeight" href="">${titleList.company} ( ${titleList.count} )</a></li>
	             </c:forEach>
	             </div>
       	  </div>

     
              
         
       
              <div class="ad_wrapper"> <img src="images/ads/banner_ad_fpo.png" /> </div>
           
   