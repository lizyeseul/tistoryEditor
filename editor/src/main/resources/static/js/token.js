function requestAuthCode() {
	$.ajax({
	    url : "/tistory/api/getAuthCode.do",
	    async : false,
	    type : "GET",
	    dataType : "json",
	    contentType: "application/json"
	})
	.done(function(data) {
	    $("#code_form").show();
	    $("#code_url").prop('href', data.url);
	})
}
function requestToken() {
	var body ={
		url : $("#response_code").hide()
	}
	$.ajax({
	    url : "/tistory/api/getAccessToken.do",
	    async : false,
	    type : "POST",
	    dataType : "json",
	    contentType: "application/json",
	    data: JSON.stringify(body)
	})
	.done(function(data) {
	    $("#code_form").hide();
	    $("#token_info").show();
	})
}
