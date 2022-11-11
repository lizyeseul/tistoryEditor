/**
탭별 DB api 실행
@param {string} tabNm(info, list, read)
@returns 없음
 */
function refreshDB() {
	var tabNm = $(".tab-selected")[0].id.slice(0,-4)
	var refreshDBBody = {
		tab : tabNm,
		blog_uuid : $("#selected-blog-uuid").val()
	}
	$.ajax({
	    url : "/tistory/api/refreshDB.do",
	    async : false,
	    type : "POST",
	    dataType : "json",
	    contentType: "application/json",
	    data: JSON.stringify(refreshDBBody)
	})
	.done(function(data) {
		if(data.response_result == "E") {
			alert("다시 조회하세요");
		}
		else {
			if(tabNm == "info") {
				requestBlogList();
			}
		}
	})
}

/**
블로그 목록 조회
@param 없음
@returns {list} 블로그이름, 블로그uuid
 */
function requestBlogList() {
	$.ajax({
	    url : "/tistory/api/getBlogList.do",
	    async : false,
	    type : "GET",
	    dataType : "json",
	    contentType: "application/json"
	})
	.done(function (blogList) {
		$("#blogList_select").empty(); //블로그 목록 비우기
		//select 옵션 정의
		for(blogIdx in blogList) {
			var blogInfo = blogList[blogIdx];
			var s = (blogIdx == 0) ? "selected" : ""; //첫번째 블로그 자동 선택
			$("#blogList_select").append("<option "+s+" blog-uuid="+blogInfo["BLOG_UUID"]+">"+blogInfo["BLOG_NM"]+"</option>")
		}
		$("select").niceSelect("update");
		blogChange();
	})
}



/**
블로그 정보 조회
@param {map} blog_uuid
@returns {map} 블로그 정보
 */
function requestBlogInfo() {
	var body = {
		blog_uuid : $("#selected-blog-uuid").val()
	}
	$.ajax({
	    url : "/tistory/api/getBlogInfo.do",
	    async : false,
	    type : "POST",
	    dataType : "json",
	    contentType: "application/json",
	    data: JSON.stringify(body)
	})
	.done(function (blogInfo) {
		$("#name_label").text(blogInfo["BLOG_NM"]);
		$("#secondaryUrl_label").text(blogInfo["SCND_URL"]);
	})
}
 
/**
게시글 목록 조회
@param {map} blog_uuid, post_uuid, 게시글 출력 개수
@returns {list} 게시글 목록
 */
function requestPostList() {
	var selectedPage = $("#selected-page").val();
	var postlistBody = {
		blog_uuid : $("#selected-blog-uuid").val(),
		selected_page : Number(selectedPage),
		post_per_page : 10
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
			setPostList(data.postList);
			var $target = $('#pagination-ul');
			$target.twbsPagination('destroy');
			$target.twbsPagination($.extend({}, defaultPagingOpts, {
				totalPages: data.totalPage,
				startPage : selectedPage
			}));
		}
	})
}

function requestPostDetail() {
	var postdetailBody = {
		blogName : $("#selected-blog-uuid").val(),
		postId : $("#selected-post-uuid").val()
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
			$("#post-content-div").text(data.postContent);
		}
	})
}