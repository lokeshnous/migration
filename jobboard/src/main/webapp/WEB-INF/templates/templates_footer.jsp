<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<div class="footer_wrapper">
		<div class="container1">
			<h4>Professions:</h4>
			<ul>
				<li><a href="http://nursing.advanceweb.com/" target="_blank">Nursing</a></li>
				<li><a href="http://imaging-radiation-oncology.advanceweb.com/Default.aspx" target="_blank">Imaging &amp; Radiation Oncology</a></li>
				<li><a href="http://physical-therapy.advanceweb.com" target="_blank">Physical Therapy and Rehab Medicine</a></li>
				<li><a href="http://occupational-therapy.advanceweb.com" target="_blank">Occupational Therapy</a></li>
				<li><a href="http://speech-language-pathology-audiology.advanceweb.com" target="_blank">Speech-Language Pathology & Audiology</a></li>
				<li><a href="http://audiology.advanceweb.com" target="_blank">Hearing Practice Management</a></li>
				<li><a href="http://long-term-care.advanceweb.com" target="_blank">Long-Term Care Management</a></li>
				<li><a href="http://respiratory-care-sleep-medicine.advanceweb.com" target="_blank">Respiratory Care & Sleep Medicine</a></li>
				<li><a href="http://laboratory-manager.advanceweb.com" target="_blank">Administrators of the Laboratory</a></li>
				<li><a href="http://laboratorian.advanceweb.com" target="_blank">Medical Laboratory Professionals</a></li>
				<li><a href="http://health-information.advanceweb.com" target="_blank">Health Information</a></li>
				<li><a href="http://nurse-practitioners-and-physician-assistants.advanceweb.com" target="_blank">NPs & PAs</a></li>
				<li><a href="http://healthcare-executive-insight.advanceweb.com" target="_blank">Healthcare Executives</a></li>
			</ul>
		</div>
		<!-- end container1 -->

		<%-- <div class="container2">
			<h4>Content:</h4>
			<ul>
				<li><a href="#">News</a></li>
				<li><a href="#">Features</a></li>
				<li><a href="#">Multimedia</a></li>
				<li><a href="#">Buyers Guide</a></li>
				<li><a href="#">Community</a></li>
				<li><a href="#">Downloads</a></li>
				<li><a href="<%=request.getContextPath()%>/healthcare/sitemap.html">Site Map</a></li>
			</ul>
		</div> --%>
		<!-- end container2 -->

		<div class="container3">
			<h4>Services:</h4>
			<ul>
				<li><a href="http://www.advancehealthcarejobs.com" target="_blank">ADVANCE Healthcare Jobs</a></li>
				<li><a href="http://advancecustomcommunications.com" target="_blank">ADVANCE Custom Publishing</a></li>
				<li><a href="http://advanceweb.com/General/Subscriptions.aspx" target="_blank">Subscribe</a></li>
				<%-- <li><img src="<%=request.getContextPath()%>/resources/images/tranBg.png" alt="&nbsp;" class="foot_icon emailFooter email" /><a href="#">Sign Up for Enewsletter</a></li> --%>
				<li><img src="<%=request.getContextPath()%>/resources/images/tranBg.png" alt="&nbsp;" class="foot_icon fbook" /><a href="https://www.facebook.com/ADVANCECareers?fref=ts" target="_blank">Connect on Facebook</a></li>
				<%-- <li><img src="<%=request.getContextPath()%>/resources/images/tranBg.png" alt="&nbsp;" class="foot_icon linkedIn" /><a href="#">Connect on LinkedIn</a></li> --%>
				<li><img src="<%=request.getContextPath()%>/resources/images/tranBg.png" alt="&nbsp;" class="foot_icon twitter" /><a href="https://twitter.com/ADVANCECareers" target="_blank">Connect on Twitter</a></li>
				<!-- <li><a href="#">Download the Mobile App</a></li> -->
				<li><a href="http://promotions.advanceweb.com" target="_blank">Order Promotional Items</a></li>
				<security:authorize 
		         access="!hasRole('ROLE_FACILITY') and !hasRole('ROLE_FACILITY_GROUP') and !hasRole('ROLE_FACILITY_SYSTEM') and !hasRole('ROLE_MERION_ADMIN')">					
				<li><a href="<%=request.getContextPath()%>/healthcare/sitemap.html" target="_blank">Site Map</a></li>
				</security:authorize>
			</ul>
		</div>
		<!-- end container3 -->

		<div class="container4">
			<h4>More from ADVANCE:</h4>
			<ul>
				<li><a href="http://shop.advanceweb.com" target="_blank">ADVANCE Healthcare Shop</a></li>
				<li><a href="http://promotions.advanceweb.com" target="_blank">ADVANCE Custom Promotions</a></li>
				<li><a href="http://www.advancehealthcarejobs.com" target="_blank">ADVANCE Healthcare Jobs</a></li>
				<li><a href="http://events.advanceweb.com/Attendee/Default.aspx" target="_blank">ADVANCE Job Fairs</a></li>
				<li><a href="http://www.advanceweb.com/Advertise/CE2.aspx" target="_blank">ADVANCE Continuing Education</a></li>
			</ul>
		</div>
		<!-- end container4 -->

		<div class="container5">
			<h4>Corporate:</h4>
			<ul>
				<li><a href="http://www.advanceweb.com/Corporate/AboutUs.aspx" target="_blank">About Us</a></li>
				<li><a href="http://www.advanceweb.com/ContactUs.aspx" target="_blank">Contact Us</a></li>
				<li><a href="http://www.advanceweb.com/web/Media_Kit/intro.html" target="_blank">Advertise with Us</a></li>
				<li><a href="http://www.advanceweb.com/Corporate/Jobs/Openings.aspx" target="_blank">Work for Us</a></li>
				<li><a href="http://www.advanceweb.com/Copyright.aspx" target="_blank">Privacy Policy & Terms of Service</a></li>
				<!-- <li><a href="http://www.advanceweb.com/Copyright.aspx" target="_blank">Terms of Service</a></li> -->
				<!-- <li><a href="#">Help</a></li> -->
				<li><c:if test="${not empty jobsUrl}">
				<a  target="_blank" href="${jobsUrl}">${jobsUrlTitle}</a>
				</c:if></li>
			</ul>
		</div>
		<!-- end container5 -->

		<br class="clearfix" />
		<p class="copyright">&copy; 2013 Merion Matters 2900 Horizon Drive
			King of Prussia PA 19406 800-355-5627</p>
	</div>
	</div>
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-2498293-50']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
	<!-- footer_wrapper -->