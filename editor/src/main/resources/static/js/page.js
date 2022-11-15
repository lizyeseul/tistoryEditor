

/**
ready
 */
$(document).ready(function() {
	$("#test-tab").trigger("click")
	
	
//	$("select").niceSelect();
//	$("#token-tab").trigger("click")
////	$("#info-tab").trigger("click")
////	$("#list-tab").trigger("click")
//	onchangeDisabledTab();
})

/**
탭 클릭 listener
 */
function onTabClick(event) {
	var tabId = event.target.id;
	var panelId = event.target.id.slice(0,-4)+"-panel";
	
	if(isPossibleTab(tabId) == false) {
		return;
	}
	
	$(".tab-label").removeClass("tab-selected")
	$("#"+tabId).addClass("tab-selected")
	$(".panel").hide()
	$("#"+panelId).show()
	
	if(tabId == "token-tab") {
		getTokenValid()
	}
	else if(tabId == "info-tab") {
		if($("#blogList_select option").length <= 0) {
			requestBlogList();
		}
	}
	else if(tabId == "list-tab") {
		$("td[name=header-blog]").text( $("#blogList_select option:selected").val() );
		if($("#ctgy_select option").length <= 0) {
			requestCtgyList()
		}
	}
	else if(tabId == "read-tab") {
		;
	}
}

//tab disabled css 변경
function onchangeDisabledTab() {
	var tabList = $(".tab-label").not("#token-tab")
	tabList.removeClass("tab-disabled")
	
	for(var tabIdx in tabList) {
		var tabId = tabList[tabIdx].id
		if(isPossibleTab(tabId) == false) {
			$("#"+tabId).addClass("tab-disabled")
		}
	}
}

//tab별 선택 가능 여부
function isPossibleTab(tabId) {
	if(tabId == "test-tab") {
		return true;
	}
	var selectedBlogName = $("#selected-blog-uuid").val();
	var selectedPostId = $("#selected-post-uuid").val();
	
	if(tabId == "token-tab") {
		return true;
	}
	else if(tabId == "info-tab") {
		return true;
	}
	else if(tabId == "list-tab") {
		if(selectedBlogName == "") {
			return false;
		}
		return true;
	}
	else if(tabId == "read-tab") {
		if(selectedBlogName == "") {
			return false;
		}
		else if(selectedPostId == "") {
			return false;
		}
		return true;
	}
}


