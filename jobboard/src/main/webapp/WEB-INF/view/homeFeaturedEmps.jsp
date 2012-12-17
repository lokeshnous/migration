							<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
							<script type="text/javascript" src="../resources/js/slider.js"></script>
							<!-- <div id="slider1PrevBtn"></div> -->
							<c:if test="${prev != 0 }">
							<div id="slider4PrevBtn"></div></c:if>
							<div id="sliderVal">
							<c:forEach var="companyProfileDTO"
									items="${companyProfileDTOList}" varStatus="status" step="2">
									<div class="slider1Frames">
										<a
											href="featuredemployerdetails.html?id=${companyProfileDTOList[status.index].facilityid }">
											<div class="slider1FrameA1">
												<img src="<%=request.getContextPath()%>/healthcarejobs/viewImage.html?id=${companyProfileDTOList[status.index].logoPath}"
													alt="${companyProfileDTOList[status.index].companyName }"
													width="125" height="37">
											</div>
										</a> 
										<c:if test="${not empty companyProfileDTOList[status.index+1].facilityid }">
										<a
											href="featuredemployerdetails.html?id=${companyProfileDTOList[status.index+1].facilityid }">
											<div class="slider1FrameA2">
												<img src="<%=request.getContextPath()%>/healthcarejobs/viewImage.html?id=${companyProfileDTOList[status.index+1].logoPath}"
													alt="${companyProfileDTOList[status.index+1].companyName }"
													width="125" height="37">
											</div>
										</a>
										</c:if>
									</div>
								</c:forEach>
									</div>
									<c:if test="${next != count }">
							<div id="slider4NextBtn"></div></c:if>
						