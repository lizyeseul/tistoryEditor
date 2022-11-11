var blogs = {};
var posts = [];

var postColList = ["id","title","date","postUrl"];

var defaultPagingOpts = {
	initiateStartPageClick: false,
	visiblePages: 10,
	first : "&laquo;",
	prev : "&lt;",
	next : "&gt;",
	last : "&raquo;",
	activeClass : "active-page",
	onPageClick : function(event, page) {
		$("#selected-page").val(page).trigger("change");
		requestPostList();
	}
}

$(document).on("change", "input[name=global]", function() {
	onchangeDisabledTab();
})
$(document).on("click", ".refresh-btn", refreshDB);
$(document).on("click", ".post-id", onSetPostId);
$(document).on("click", ".tab-label", onTabClick);

function blogChange() {
	$("#selected-blog-uuid").val( $("#blogList_select option:selected").attr("blog-uuid") ).trigger("change");
}

function setPostList(posts) {
	$("#postListTBody").empty();
	for(var p in posts) {
//		$("#postListTBody").append(makePostListTr(posts[p]));
		
		var postListTr = "<tr>";
		postListTr += '<td class="post-id">'+posts[p]["POST_ID"]+"</td>";
		postListTr += "<td>"+posts[p]["POST_NM"]+"</td>";
		postListTr += '<td style="text-align:center;">'+posts[p]["POST_DT"]+"</td>";
		postListTr += '<td style="text-align:center;">'+posts[p]["POST_URL"]+'</td>';
		postListTr += "</tr>";
		$("#postListTBody").append(postListTr);
		
	}
}
function onSetPostId(event) {
	$("#selected-post-uuid").val( $(event.target).text() ).trigger("change");
	onSetPostInfo();
}

function onSetPostInfo() {
	$("td[name=header-blog]").text( $("#blogList_select option:selected").val() );
	
	var selectedPostId = $("#selected-post-uuid").val();
	if(selectedPostId != -1) {
		var post = {};
		for(var i in posts) {
			if(posts[i]["id"] == selectedPostId) {
				post = posts[i];
				break;
			}
		}
		$("td[name=header-post-id]").text(post["id"]);
		$("td[name=header-post-title]").text(post["title"]);
	}
}