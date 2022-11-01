
var token_valid = false;

var blogs = {};
var selectedBlogName = "";

var selectedPage = 1;
var totalPage = 1;
//var postCount = 0;
//var totalCount = 1;
var posts = [];

var selectedPostId = -1;

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
		selectedPage = page;
		requestPostList();
	}
}