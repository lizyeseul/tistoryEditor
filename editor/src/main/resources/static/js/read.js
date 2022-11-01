
/**
블로그 정보 조회
param 없음
response 
- defaultBlogName : 기본 블로그 이름
- data : 블로그 목록(블로그 이름이 key이고 블로그 정보가 value인 object 형태로 return)
 */
function requestBlogInfo() {
	$.ajax({
	    url : "/tistory/api/getBlogInfo.do",
	    async : false,
	    type : "GET",
	    dataType : "json",
	    contentType: "application/json"
	})
	.done(responseBlogInfo)
}
function responseBlogInfo(data) {
	blogs = data.blogMap;
	selectedBlogName = data.defaultBlogName;
	$("#blogList_select").empty(); //블로그 목록 비우기
	for(blogName in blogs) {
		var blogInfo = blogs[blogName];
		var s = "";
		if(blogInfo.default == "Y") {
			s = "selected";
		}
		$("#blogList_select").append("<option "+s+">"+blogInfo.name+"</option>")
	}
	$("select").niceSelect("update");
	setBlogInfo(selectedBlogName);
}

function blogChange(){
	selectedBlogName = $("#blogList_select").val();
	setBlogInfo(selectedBlogName);
}

function setBlogInfo(blogName) {
	var blogInfo = blogs[blogName];
	$("#name_label").text(blogInfo.name);
}



function requestPostList() {
	var postlistBody = {
		blogName : selectedBlogName,
		page : selectedPage
	}
	$.ajax({
	    url : "/tistory/api/getPostList.do",
	    async : false,
	    type : "POST",
	    dataType : "json",
	    contentType: "application/json",
	    data: JSON.stringify(postlistBody)
	})
	.done(function(data) {
		if(data.response_result == "E") {
			alert("다시 조회하세요");
		}
		else {
			posts = data.postList;
			totalPage = data.totalPage;
			var $target = $('#pagination-ul');
			$target.twbsPagination('destroy');
			$target.twbsPagination($.extend({}, defaultPagingOpts, {
				totalPages: totalPage,
				startPage : selectedPage
			}));
			setPostList();
		}
	})
}
function setPostList() {
	$("#postListTBody").empty();
	for(var p in posts) {
//		$("#postListTBody").append(makePostListTr(posts[p]));
		
		var postListTr = "<tr>";
		postListTr += '<td class="post-id">'+posts[p]["id"]+"</td>";
		postListTr += "<td>"+posts[p]["title"]+"</td>";
		postListTr += '<td style="text-align:center;">'+posts[p]["date"]+"</td>";
		postListTr += '<td style="text-align:center;">'+posts[p]["postUrl"]+'</td>';
		postListTr += "</tr>";
		$("#postListTBody").append(postListTr);
		
	}
}
//function makePostListTr(data) {
//	var postListTr = "<tr>";
//	for(var t in postColList) {
//		postListTr += "<td>"+data[postColList[t]]+"</td>";
//	}
//	postListTr += "</tr>";
//	return postListTr;
//}
$(document).on("click", ".post-id", onSetPostId)
function onSetPostId(event) {
	var $target = $(event.target);
	selectedPostId = $target.text();
	onSetPostInfo();
}
function onSetPostInfo() {
	$("td[name=header-blog]").text(selectedBlogName);
	for(var i in posts) {
		if(posts[i]["id"] == selectedPostId) {
			$("td[name=header-post-id]").text(posts[i]["id"]);
			$("td[name=header-post-title]").text(posts[i]["title"]);
		}
	}
}


function requestPostDetail() {
	var postdetailBody = {
		blogName : selectedBlogName,
		postId : selectedPostId
	}
	$.ajax({
	    url : "/tistory/api/getPostDetail.do",
	    async : false,
	    type : "POST",
	    dataType : "json",
	    contentType: "application/json",
	    data: JSON.stringify(postdetailBody)
	})
	.done(function(data) {
		if(data.response_result == "E") {
			alert("다시 조회하세요");
		}
		else {
		$("#post-content-div").html(data.postContent);
		}
	})
}