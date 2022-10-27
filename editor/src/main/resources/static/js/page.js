/**
탭 이동 css
 */
function onTabClick(event) {
	var tabId = event.target.id;
	var panelId = event.target.id.slice(0,-4)+"-panel";
	
	if(tabId == "token-tab" || token_valid == true) {
		$(".tab-label").removeClass("tab-selected")
		$("#"+tabId).addClass("tab-selected")
		$(".panel").hide()
		$("#"+panelId).show()
	}
	else {
		//disabled 상태
		alert("토큰을 먼저 발급받으세요.")
	}
}
$(document).on("click", ".tab-label", onTabClick)

function onEnableTab() {
	var tabList = $(".tab-label").not("#token-tab")
	if(token_valid == true) {
		tabList.removeClass("tab-disabled")
	}
	else {
		tabList.addClass("tab-disabled")
	}
}

/**
ready
 */
$(document).ready(function() {
	getTokenValid()
	$("#one").trigger("click")
})
