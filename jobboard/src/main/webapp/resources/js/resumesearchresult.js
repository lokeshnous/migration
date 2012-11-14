// This function will be called for Employer resume search
function searchResume() {

	$('#findSearchInfo').html("");
	keywords = $("#keywords").val();
	cityState = $("#cityState").val();
	radius = $("#radius").val();
	rows = $("#rows").val();
	start = $("#start").val();
	searchtype = $("#searchtype").val();
	phrase = $("#phrase").val();
	var navUrl = "../employerSearchResume/searchResumeFromDB.html?keywords="
			+ keywords + "&cityState=" + cityState + "&radius=" + radius
			+ "&rows=" + rows + "&start=" + start + "&searchtype=" + searchtype
			+ "&phrase=pp";

	// Calling the Search resume controller for getting the result
	$.getJSON(navUrl, function(data) {
		$.ajaxSetup({ cache: true });
		$.each(data, function(key, val) {
			if (key == "AjaxMSG") {
				$('#findSearchInfo').html(val);
			}
		});
		processResumePaginationReq("20");
		// $("#TotalNoRecords").text(data["TotalNoRecords"]);
		// $("#TotalRecord").text(data["TotalNoRecords"]);
	}).success(function() {
	}).error(function() {
		alert("error");
	}).complete(function() {
	});

	// For displaying the search result frames
	$(".otherContent").attr("style", "display: none");
	$(".searchContent").attr("style", "display: block");

	return true;
}

function processResumePaginationReq(pageSize) {
	$.ajaxSetup({
		cache : false
	});
	$.ajax({
		url : '../employerSearchResume/jobboardsearchresumeresultbody.html',
		data : ({}),

		success : function(data) {
			$("#resumeTableContent").html(data);
			$("#noOfPage").val(pageSize);
			$("#noOfPageLower").val(pageSize);
		},
		error : function(data) {
		},
		complete : function(data) {
			// do nothing for now.
		}
	});
}

$(document).ready(function() {
	var autoLoad = $("#autoload").val();
	if (autoLoad == true || autoLoad == "true") {
		searchResume();
		$("#autoload").val("false");
	}
	var keywords = $("#keywords").val();

	/*
	 * if(keywords != null){ searchResume(); }
	 */

});

function moveToFolder() {

	var publishResumeIdArr = [];
	var createddateArr = [];
	var resumeIdAndDateArr = [];

	$('#resumeTable input[type="checkbox"]:checked').each(
			function(i) {
				publishResumeIdArr[i] = $(this).val();
				var $row = $(this).parents('tr');
				createddateArr[i] = $row.find('td:eq(7)').html();
				resumeIdAndDateArr[i] = publishResumeIdArr[i] + ","
						+ createddateArr[i];
			});

	if(resumeIdAndDateArr.length == 0){
		alert("Please select resumes to move.");
		return false;
	}
	$.ajaxSetup({
		cache : false
	});

	$
			.ajax({
				url : '../employerSearchResume/moveResumeToFolder.html?resumeIdAndDateArr='
						+ resumeIdAndDateArr,
				success : function(data) {
					// $("#resumeTableContent").html(data);
					// $("#noOfPage").val(pageSize);
					// $("#noOfPageLower").val(pageSize);
					alert(data);
				},
				error : function(data) {
					alert('Error');
				},
				complete : function(data) {

				}
			});

}
function saveThisResume() {
	var keywords = $.trim($("#keywords").val());
	$.ajax({
		url : "../employerSearchResume/saveThisResumeSearch.html?keywords="
				+ keywords,
		success : function(data) {
			$.each(data, function(key, val) {
				if (key == "NavigationPath") {
					window.location.href = val + '.html';
				}
				if (key == "LoggedInNavigationPath") {
					$.nmManual(val + '.html');
				}
			});
			if (data.success != null) {
			}
			if (data.failure != null) {
				$("#errorMsg").html("Please enter the required parameters.");
			}
		},
		error : function(response) {
			alert("Server Error : " + response.status);
		},
		complete : function() {

		}
	});
}

function openPopUpToPurchase(){
	
	$.nmManual("../employerSearchResume/employerpurchaseresumepopup.html");
	
}

/*
 * function viewResume(id){ alert(id);
 * //$.nmManual("../employerSearchResume/viewResume.html?resumeId="+id);
 * 
 * $("form").attr("action",
 * "../employerSearchResume/viewResume.html?resumeId="+id);
 * $("form").attr("method","POST"); $("form").submit();
 * 
 * $.ajax({ url : '../employerSearchResume/viewResume.html?resumeId='+id,
 * success : function(data) { // $("#resumeTableContent").html(data); //
 * $("#noOfPage").val(pageSize); // $("#noOfPageLower").val(pageSize);
 * alert(data); }, error : function(data) { alert('Error'); }, complete :
 * function(data) {
 *  } });
 *  }
 */

