<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0"
									class="grid marginTop3">
<tbody>
	<c:forEach items="${jbPostTotalList}" var="jobList">
		<tr class="gridB">
			<td><input width="46%" name="radio2" type="radio" id="radio4"
				value="radio" class="marginLeft10 marginRight10"> <label
				for="radio2">${jobList.getMetricsName()}</label></td>
			<td width="18%" align="center" valign="middle"
				class="BorderLeft TcolorA">${jobList.getViews()}</td>
			<td width="18%" align="center" valign="middle"
				class="BorderLeft TcolorB">${jobList.getClicks()}</td>
			<td width="18%" align="center" valign="middle"
				class="BorderLeft TcolorC">${jobList.getApplies()}</td>
		</tr>
	</c:forEach>
</tbody>
</table>