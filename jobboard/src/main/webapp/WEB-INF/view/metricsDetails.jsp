<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
jQuery(document).ready(function() {
	
	var metricId = $("input:radio").attr("id");
	
	jQuery("#"+metricId).trigger("click");
	
});
</script>
<table id="tb_matrix" width="100%" border="0" cellspacing="0" cellpadding="0"
									class="grid marginTop3">
									
									

<tbody>
	
	<c:forEach items="${jbPostTotalList}" var="jobList">
		<tr class="gridB">
			<td><input width="46%" name="radio2" type="radio" id="${jobList.getViews()}-${jobList.getClicks()}-${jobList.getApplies()}"
				value="radio" class="marginLeft10 marginRight10" onclick="getData(this);" > 
				
				<label for="radio2" >${jobList.getMetricsName()}</label></td>
			<td width="18%" align="center" valign="middle"
				class="BorderLeft TcolorA" id="view">${jobList.getViews()}</td>
			<td width="18%" align="center" valign="middle"
				class="BorderLeft TcolorB" id="click">${jobList.getClicks()}</td>
			<td width="18%" align="center" valign="middle"
				class="BorderLeft TcolorC" id="apply">${jobList.getApplies()}</td>
		</tr>
	</c:forEach>
</tbody>




</table>

