// This function will be called for Employer resume search
function searchResume() {
	
		keywords = $("#keywords").val();
		cityState = $("#cityState").val();
		radius = $("#radius").val();
		rows = $("#rows").val();
		start = $("#start").val();
		searchtype = $("#searchtype").val();
		phrase = $("#phrase").val();
		var navUrl = "../employerSearchResume/searchResume.html?keywords=" + keywords
				+ "&cityState=" + cityState + "&radius=" + radius + "&rows="
				+ rows + "&start=" + start + "&searchtype=" + searchtype + "&phrase=pp";
		
		// Calling the Search resume controller for getting the result
		$.getJSON(navUrl, function(data) {
			//processPaginationReq("20");
			 //$("#TotalNoRecords").text(data["TotalNoRecords"]);
			//$("#TotalRecord").text(data["TotalNoRecords"]);
		})
		.success(function() { })
		.error(function() { alert("error"); })
		.complete(function() { });

		
		// For displaying the search result frames
		$(".otherContent").attr("style", "display: none");
		$(".searchContent").attr("style", "display: block");

}
$(document).ready(function() {
	
	var cityState = $("#cityState").val();
	var url = "../jobsearch/findLocation.html?cityState="+cityState;
	if(typeof(cityState) != "undefined"){
	$( "#cityState" ).autocomplete({
		source: url
	});
	}
	
}); 