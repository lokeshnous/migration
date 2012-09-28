<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>ADVANCE Heathcare Jobs</title>
		<jsp:include page="common/include.jsp"/>
<!-- 
		STYLESHEETS
		<link href="stylesheets/JB.css" rel="stylesheet" type="text/css" />
		<link href="stylesheets/jquery.megamenu.css" rel="stylesheet" type="text/css" />
		<link href="stylesheets/SliderStyles.css" rel="stylesheet" type="text/css"> -->

		</head>

		<body class="job_board">
        <div id="jobSeekerRegister1" class="job_seeker_login popUpContainer" style="display:block">
          <div class="popupHeader">
            <h2>EMPLOYER DETAILS</h2>
            <a href="#"><img src="../resources/images/Close.png" class="nyroModalClose" width="19" height="19" alt=""></a></div>
          <div class="popUpContainerWrapper">
            <form action="" method="">
              <div class="rowEvenNewSpacing marginTop0"><div class="FloatLeft"> 
                <!--T-->
                <div class="row">
               <c:if test="${not empty employerDetails.logoPath}">
                <div class="floatLeft"><img src="<%=request.getContextPath()%>/agency/viewImage.html?path=${employerDetails.logoPath}" width="204" height="50" alt="logo"></div>
               </c:if>
               <c:if test="${empty employerDetails.logoPath}">
                <div class="floatLeft" style="width:204px;height:50px;"></div>
               </c:if>
                <div class="floatRight marginTop20"><a href="<%=request.getContextPath()%>/agency/impersonateAgencyToEmployer.html?facilityId=${employerDetails.facilityId}">Go To ${employerDetails.name} Dashboard</a> </div>
                
                </div>
<div class="row">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="grid marginTop3">
							<thead>
								<tr class="borderTopNone">
									<th width="46%" align="left" scope="col"><h2
											class="noTopBorder noTopBottomBorder">Metrics</h2></th>
									<th width="18%" align="center" scope="col"
										class="BorderLeftWhite"><div class="EDPrice">VIEWS</div></th>
									<th width="18%" align="center" scope="col"
										class="BorderLeftWhite"><div class="EDPriceA">CLICKS</div></th>
									<th width="18%" align="center" scope="col"
										class="BorderLeftWhite"><div class="EDPriceB">APPLIES</div></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${jbPostTotalList}" var="jobList">
									<tr class="gridB">
										<td><input name="radio2" type="radio" id="radio4"
											value="radio" class="marginLeft10 marginRight10"> <label
											for="radio2">${jobList.getMetricsName()}</label></td>
										<td align="center" valign="middle" class="BorderLeft TcolorA">${jobList.getViews()}</td>
										<td align="center" valign="middle" class="BorderLeft TcolorB">${jobList.getClicks()}</td>
										<td align="center" valign="middle" class="BorderLeft TcolorC">${jobList.getApplies()}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
                        </div>
                <!--T-->
                <div class="rowBox EDPricec marginLeft5">
                          <div class="floatLeft marginTop3"><strong>&nbsp;&nbsp;&nbsp;Date range</strong></div>
                          <div class="floatLeft marginTop3">&nbsp;&nbsp;&nbsp;From:</div>
                          <div class="floatLeft">
                            <div class="floatLeft">
                              <input type="text" name="firstName" class="EDTextBox" /></div>
                            <div class="calender"><a href="#"><img src="../resources/images/tranBg.png" width="14" height="14" alt="Datepick"></a> </div>
          </div>
                          <div class="floatLeft marginTop3 marginLeft25">To:</div>
                          <div class="floatLeft"><div class="floatLeft">
                    <input type="text" name="firstName" class="EDTextBox" /></div><div class="calender"><a href="#"><img src="../resources/images/tranBg.png" width="14" height="14" alt="Datepick"></a> </div>
                  </div>
                          <div class="EDBox01 marginLeft25"><strong>SHOW</strong></div>
                          <div class="floatLeft marginTop5 marginLeft15"><a href="#">Export</a></div>
                        </div>
                <!--T-->
                <div class="rowBox marginLeft5">
<div class="rowBox Padding0 AutoWidth AutoHeight">
                          <div class="EDBoxMinW">
                          <div class="EDBox02">
                    <div class="row borderBottomDotted Height25">
                              <p class="floatLeft">Available Job Postings</p>
                              <p class="floatRight TextAlignR">3</p>
                            </div>
                    <div class="row marginTop10">
                              <p class="floatLeft">Active Job Postings</p>
                              <p class="floatRight TextAlignR">3</p>
                            </div>
                  </div>
                  </div>
                  <div class=" clearfix"></div>
                  <span class="FloatLeft"><a title="Coming Soon" href="">View Individual Job Posting Stats</a></span>
                  </div>
                  <div class="rowBox marginLeft25 Padding0 AutoWidth AutoHeight">
            <img src="../resources/images/EmpDimg.png" width="250" height="208" alt="img"></div>
                        </div>
              
              </div></div>
            </form>
          </div>
          <div class="clearfix"></div>
        </div>
</body>
</html>