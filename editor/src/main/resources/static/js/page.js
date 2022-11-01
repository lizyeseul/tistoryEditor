

/**
ready
 */
$(document).ready(function() {
	$("select").niceSelect();
	getTokenValid()
//	$("#token-tab").trigger("click")
	$("#info-tab").trigger("click")
//	$("#list-tab").trigger("click")
})

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
		onLoadPage(panelId)
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
page load
 */
function onLoadPage(panelId) {
	if(panelId == "info-panel") {
		if(selectedBlogName == "") {
			requestBlogInfo();
		}
	}
	else if(panelId == "list-panel") {
		if(selectedBlogName == "") {
			alert("블로그 정보 조회 필요");
			$("#info-tab").trigger("click")
			return;
		}
		onSetPostInfo()
		
		if(posts.length == 0) {
			requestPostList()
		}
	}
	else if(panelId == "read-panel") {
		if(selectedBlogName == "") {
			alert("블로그 정보 조회 필요");
			$("#info-tab").trigger("click")
			return;
		}
		else if(selectedPostId == -1) {
			alert("게시글 정보 조회 필요");
			$("#list-tab").trigger("click")
			return;
		}
		onSetPostInfo()
	}
}





