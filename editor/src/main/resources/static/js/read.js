function requestBlogInfo() {
	$.ajax({
	    url : "/tistory/api/getBlogInfo.do",
	    async : false,
	    type : "GET",
	    dataType : "json",
	    contentType: "application/json"
	})
	.done(function(data) {
		$("#name_label").text(data.name);
		$("#secondaryUrl_label").text(data.secondaryUrl);
	})
}