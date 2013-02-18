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
//Dual List Move Elements scripts STARTS here
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