function getBaseURL() {
			var url = location.href;  // entire url including querystring - also: window.location.href;
			var baseURL = url.substring(0, url.indexOf('/', 14));


			if (baseURL.indexOf('http://localhost') != -1) {
			    // Base Url for localhost
			    var url = location.href;  // window.location.href;
			    var pathname = location.pathname;  // window.location.pathname;
			    var index1 = url.indexOf(pathname);
			    var index2 = url.indexOf("/", index1 + 1);
			    var baseLocalUrl = url.substr(0, index2);

			    return baseLocalUrl + "/";
			}
			else {
			    // Root Url for domain name
			    return baseURL + "/";
			}
}

function enableDisableCompanyPanel(id){
	var radioBtn = document.getElementById(id);
	if(radioBtn.value>0){
		$("#managePrivacy").show();
	}else{
		$("#managePrivacy").hide();
	}
}
function searchByName(url) {
	var IdData = new Array();
	var NameData = new Array();

	var empName = $("#searchComapnyName").val();
	// Remove the available list
	$('#availableList option').each(function(index, option) {
		$(option).remove();

	});
	if ($.trim(empName).length <= 0) {
		$('#availableList').attr('size', 7);
		$('#selectedeList').attr('size', 7);

	} else {
		$
				.ajax({
					type : "GET",
					url : url
							+ empName,
					dataType : "json",
					contentType : "application/json; charset=utf-8",
					success : function(data) {

						for ( var x = 0; x < data.EmpList.length; x++) {

							IdData.push(data.EmpList[x].ID);
							NameData.push(data.EmpList[x].NAME);
							
							if(data.EmpList.length>10){
				        		$('#availableList').attr('size', 10);
					        	$('#selectedeList').attr('size', 10);
				        	}else{	
					        	$('#availableList').attr('size', data.EmpList.length);
					        	$('#selectedeList').attr('size', data.EmpList.length);
				        	}
							// appends options
							var availableList = document
									.getElementById("availableList");
							var exists = false;
							$('#availableList option')
									.each(
											function() {

												if ((this.text).toLowerCase() == (data.EmpList[x].NAME)
														.toLowerCase()) {
													exists = true;
												}
											});
							if (!exists) {
								availableList.options[availableList.options.length] = new Option(
										data.EmpList[x].NAME,
										data.EmpList[x].ID, false, false);
							}

						}

					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(textStatus);
					}
				});
	}
}
// Dual List Move Elements scripts STARTS here
function addAttribute(){
	var selectedList;
	var availableList;
	availableList = document.getElementById("availableList");
    selectedList = document.getElementById("selectedeList");
   var addIndex = availableList.selectedIndex;
   if(addIndex < 0)
      return;
   	var len = availableList.length;
	for(var j=0; j<len; j++)
	{
		if(availableList[j] != null && availableList[j].selected)
		{		
			selectedList.appendChild(availableList.options.item(j));
		   j--;
		}
	}
   selectNone(selectedList,availableList);
   
}

function deleteAttribute(){
	var selectedList;
	var availableList;
	availableList = document.getElementById("availableList");
    selectedList = document.getElementById("selectedeList");
   var selIndex = selectedList.selectedIndex;
   if(selIndex < 0)
      return;
   	var len = selectedList.length;
	for(var j=0; j<len; j++)
	{
		if(selectedList[j] != null && selectedList[j].selected)
		{
			  availableList.appendChild(
      selectedList.options.item(selIndex));
			  j--;
		}
	}
   selectNone(selectedList,availableList);
   
}

function selectNone(list1,list2){
    list1.selectedIndex = -1;
    list2.selectedIndex = -1;
    addIndex = -1;
    selIndex = -1;
}
function delAll(){
	var selectedList;
	var availableList;
	availableList = document.getElementById("availableList");
    selectedList = document.getElementById("selectedeList");
    var len = selectedList.length -1;
    for(var i=len; i>=0; i--){
        availableList.appendChild(selectedList.item(i));
    }
      selectNone(selectedList,availableList);
}

function addAll(){
	var selectedList;
	var availableList;
	availableList = document.getElementById("availableList");
    selectedList = document.getElementById("selectedeList");
    var len = availableList.length -1;
    for(var i=len; i>=0; i--){
        selectedList.appendChild(availableList.item(i));
    }
    selectNone(selectedList,availableList);  
}

function selectAllElementsInDualList(){
var selectedList = document.getElementById("selectedeList");
var len = selectedList.length;
for(var j=0; j<len; j++)
{
	selectedList.options[j].selected=true;
}
}



//Dual List Move Elements scripts ENDS here