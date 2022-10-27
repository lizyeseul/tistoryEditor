var token_valid = false;


function getTokenValid() {
	$.ajax({
	    url : "/tistory/api/getTokenValid.do",
	    async : false,
	    type : "GET",
	    dataType : "json",
	    contentType: "application/json"
	})
	.done(function(data) {
		onSetTokenValid(data.token_valid)
	})
}



function onSetTokenValid(valid) {
	token_valid = valid;
	onEnableTab()
}

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
		url : $("#response_code").val()
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
	    onSetTokenValid(data.token_valid);
	    if(token_valid) {
		    $("#code_form").hide();
		    $("#token_info").show();
			$("#access_token_label").text(data.access_token);
		}
	})
}


