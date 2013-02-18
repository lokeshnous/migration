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
	
	<c:forEach items="${jbPostTotalList}" var="jobList" varStatus="index">
		<tr class="gridB">
			<td 
			<c:if test="${index.count ==  jbPostTotalList.size()}">
			class="borderBottomNone"
			</c:if>
			><input width="46%" name="radio2" type="radio" id="${jobList.getViews()}-${jobList.getClicks()}-${jobList.getApplies()}"
				value="radio" class="marginLeft10 marginRight10" onclick="getData(this);" > 
				
				<label for="radio2" >${jobList.getMetricsName()}</label></td>
			<td width="18%" align="center" valign="middle"
			class="BorderLeft TcolorA 
			<c:if test="${index.count ==  jbPostTotalList.size()}">
			borderBottomNone
			</c:if>
			" 
				id="view">${jobList.getViews()}</td>
			<td width="18%" align="center" valign="middle"
				class="BorderLeft TcolorB 
				<c:if test="${index.count ==  jbPostTotalList.size()}">
			borderBottomNone
			</c:if>
			" id="click">${jobList.getClicks()}</td>
			<td width="18%" align="center" valign="middle"
				class="BorderLeft TcolorC
				<c:if test="${index.count ==  jbPostTotalList.size()}">
			borderBottomNone
			</c:if> 
				" id="apply">${jobList.getApplies()}</td>
</tr>
	</c:forEach>
</tbody>




</table>

