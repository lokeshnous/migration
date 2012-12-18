<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ADVANCE Heathcare Jobs</title>

		<jsp:include page="common/include.jsp" />
		<script type="text/javascript">
		    
		function isPositiveInt(number){
			var intRegex = /^\d+$/;
			if(!intRegex.test(number.toString())) {
				return true;
			}
			return false;
		}
		
		jQuery(document).ready(function(){
		    
		    jQuery(".megamenu").megamenu();
		    
		    $("#resSearchPackageTab input[type='radio']").change(function() {
				$("#resSearchPackageTab input[type='radio']").each(function(){
					if(!$(this).is(':checked')){
						$(this).parent().parent().find("td").eq(2).children(0).val("");
						$(this).parent().parent().find("td").eq(2).children(0).attr("readonly",true);
					}
					else{
						$(this).parent().parent().find("td").eq(2).children(0).attr("readonly",false);
					}
				});
			});
			
		    $("#resSearchPackageCart a").click(function(){
				if($(this).html()== "Remove"){
					$.ajax({url: "${pageContext.request.contextPath}/purchaseResumeSearch/removeResumeSearchPack.html",
						type: "POST",
				        data: {"cartItemIndex" : parseInt($(this).attr("id"))},
						success: function(data){ 
							if(null != data){
							    $("#showResumeSearchPackageCart").click();
							}	
						},
						error: function(response) {
							alert("Server Error : "+response.status);
						}
					});
				}
				
			});
		    
		    $("#resSearchPackageCart input").change(function(){
		    	var quantity = $(this).val();
		    	if(isNaN(quantity) || quantity <= 0 || isPositiveInt(quantity)){
					alert("Please enter quantity in numerics( > 0)");
					return;
				}
		    	$.ajax({url: "${pageContext.request.contextPath}/purchaseResumeSearch/updateQuantity.html",
						type: "POST",
				        data: {"cartItemIndex" : parseInt($(this).parent().next().children(0).attr("id")),"quantity" : parseInt($(this).val())},
						success: function(data){ 
							if(null != data){
							    $("#showResumeSearchPackageCart").click();
							}	
						},
						error: function(response) {
							alert("Server Error : "+response.status);
						}
				});
			});
		    
		    $("#resSearchPackageTab input[type='radio']").each(function(){
					$(this).parent().parent().find("td").eq(2).children(0).val("");
					$(this).parent().parent().find("td").eq(2).children(0).attr("readonly",true);
			});
		    
		    $("#proceedToCheckout").click(function(){
				if($("#grandTotalId").text() == "$0.00"){
					alert("Please select any one of the package to proceed to checkout");
					return false;
				}
				return true;
			});
		    
		    $("#addToCart").click(function() {
		    	$(".AddToCartfloatRight").hide();
				var count = 0; 
				$("#resSearchPackageTab input[type='radio']").each(function(){
					if($(this).is(':checked')){
						count++;
						var quantity = $(this).parent().parent().find("td").eq(2).children(0).val();
						
						if(isNaN(quantity) || quantity <= 0 || isPositiveInt(quantity)){
							$(".AddToCartfloatRight").show();
							alert("Please enter quantity in numerics ( > 0)");
							return;
						}
						else{
							var selPackage = $(this).parent().parent();
							var packageIndex = selPackage.attr("id");
							var quantity = selPackage.find("td").eq(2).children(0).val();
							
							 $.ajax({url: "${pageContext.request.contextPath}/purchaseResumeSearch/addToCart.html",
								type: "POST",
						        data: {"packageIndex" : packageIndex,"quantity" : quantity},
						        success: function(data){ 
									if(null != data){
									    $("#showResumeSearchPackageCart").click();
									}	
								},
								error: function(response) {
									$(".AddToCartfloatRight").show();
									alert("Server Error : "+response.status);
								}
							});
						}	
					}
				});
				if(count == 0){
					$(".AddToCartfloatRight").show();
					alert("Please select any one of the package to Add To Cart");
				}
			});
		});
		</script>
		</head>

		<body class="job_board">
        <div id="jobSeekerRegister1" class="job_seeker_login popUpContainer" style="display:block">
          <div class="popupHeader">
            <h2>PURCHASE RESUME SEARCH PACKAGES</h2>
            <img id="closeCheckOut" src="../resources/images/Close.png" width="19" height="19" title="Close"
				alt="" class="nyroModalClose cursor"></div>
          <div class="popUpContainerWrapper">
            <form:form action="" method="POST" commandName="purchaseResumeSearchForm">
              
              <c:if test="${empty purchaseResumeSearchForm.resumeSearchPackageList}">
						<tr><td>
							<br><p align="left" class="FormErrorDisplayText">No Resume Search Packages Found</p><br><br><br><br><br>
							<a href="#" class="nyroModalClose btn_sm orange">Cancel</a>
						</td></tr>
			  </c:if>
			  <c:if test="${not empty purchaseResumeSearchForm.resumeSearchPackageList}">	
              <div class="rowEvenNewSpacing marginTop5 marginTop0">
                <table id="resSearchPackageTab" width="100%" border="0" cellspacing="0" cellpadding="0" class="grid">
                  <thead>
                  <tr  class="borderTopNone">
                    <th width="45%" align="left" scope="col" >&nbsp;</th>
                    <th align="Left" scope="col" >Price</th>
                    <th align="Left" scope="col" >Quantity</th>
                    <th width="17%" align="left" scope="col">&nbsp;</th>
                  </tr>
                  </thead>
                  <tbody>
                  	<% int i= 0; %>
                  	
                  	<c:forEach items="${purchaseResumeSearchForm.resumeSearchPackageList}" var="resSearchPackage" varStatus="status">
	                  <tr id="<%=i++%>">
	                    <td><input name="radio" type="radio" id="radio1" value="radio" class="marginRight5"> 
	                    	<label for="radio">${resSearchPackage.packageName}</label>
	                    </td>
	                    <td width="23%" align="left"><span>$</span>${resSearchPackage.priceAmt}0 
	                    <c:if test="${resSearchPackage.discount != 0 }">
	                    	<span class="link_color2_selected">(<span>${resSearchPackage.discount}</span>% off)</span>
	                    </c:if>
	                    </td>
	                    <td width="15%" align="left"><input type="text" name="healthCareSubSplty" class="jb_input4 marginTop0" maxlength="3"/></td>
	                    <td align="center">&nbsp;</td>
	                  </tr>
	                </c:forEach>
	              </tbody>
                </table>
              </div>
              <div class="row marginTop20 paddingBottom10"> <div class="AddToCartfloatRight"><a href="#" id="addToCart" class="btn_sm orange">Add To Cart</a></div> </div>
              <div class="rowEvenNewSpacing marginTop20">
                <table id="resSearchPackageCart" width="100%" border="0" cellspacing="0" cellpadding="0" class="grid">
                  <thead>
                  <tr  class="borderTopNone">
                    <th width="45%" align="left" scope="col" >My Shopping Cart</th>
                    <th width="23%" align="Left" scope="col" >Price</th>
                    <th width="15%" align="Left" scope="col" >Quantity</th>
                    <th width="17%" align="left" scope="col">&nbsp;</th>
                  </tr>
                  </thead>
                  <tbody>
                  	<c:if test="${empty purchaseResumeSearchForm.resumeSearchPackageCart}">
						<tr><td>
							<br><p align="left" class="FormErrorDisplayText">Please select the packages</p><br><br><br>
						</td></tr>
					</c:if>	
                  <% int j= 0; %>
                  	<c:forEach items="${purchaseResumeSearchForm.resumeSearchPackageCart}" var="resSearchCartItem" varStatus="status">
		               <tr>
	                    <td>
	                    <label>${resSearchCartItem.packageName}</label></td>
	                    <td align="left"><span>$</span>${resSearchCartItem.packageTotal}0</td>
	                    <td align="left"><input name="healthCareSubSplty" type="text" class="jb_input4 marginTop0" value="${resSearchCartItem.quantity}" maxlength="3"/></td>
	                    <td align="center"><a href="#" id="<%=j++%>">Remove</sa></td>
	                  </tr>
                  </c:forEach>
                  <tr class="borderGrandTotal">
                    <td>Grand Total:</td>
                    <td align="left" id="grandTotalId"><span>$</span>${purchaseResumeSearchForm.grandTotal}0</td>
                    <td align="left">&nbsp;</td>
                    <td align="center">&nbsp;</td>
                  </tr>
                  </tbody>
                </table>
              </div>
              <div class="row marginTop20"> 
                      <div class="floatLeft marginBottom5">Promotion Code:</div><br>
                      <div class=" row floatLeft marginBottom5">
                <input type="text" name="firstName" class="job_seeker_email" /> <span class="required">Redeem</span></div>
             </div>
              <div class="row marginTop20 paddingBottom10"> <span class="floatLeft marginTop10"><a href="<%=request.getContextPath()%>/purchaseResumeSearch/proceedToCheckOut.html" id="proceedToCheckout" class="btn_sm orange">Proceed to Checkout</a> 
              <a href="#" class="nyroModalClose btn_sm orange">Cancel</a></span> <span class="floatLeft marginTop10 marginLeft5" ></span> </div>
               </c:if>
              <a id="showResumeSearchPackageCart" class="nyroModal" href="<%=request.getContextPath()%>/purchaseResumeSearch/showResumeSearchPackageCart.html"></a>
            </form:form>
          </div>
          <div class="clearfix"></div>
        </div>
</body>
</html>