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
		
		var postListTr = "<tr post-uuid="+posts[p]["POST_UUID"]+">";
		postListTr += '<td class="post-id">'+posts[p]["POST_ID"]+"</td>";
		postListTr += "<td>"+posts[p]["POST_NM"]+"</td>";
		postListTr += '<td style="text-align:center;">'+posts[p]["POST_DT"]+"</td>";
		postListTr += '<td style="text-align:center;">'+posts[p]["POST_URL"]+'</td>';
		postListTr += "</tr>";
		$("#postListTBody").append(postListTr);
		
	}
}
function onSetPostId(event) {
	var $postTr = $(event.target)[0].parentNode;
	$("#selected-post-uuid").val( $($postTr).attr("post-uuid") ).trigger("change");
	$("td[name=header-post-id]").text( $($postTr.children[0]).text());
	$("td[name=header-post-title]").text($($postTr.children[1]).text());
}