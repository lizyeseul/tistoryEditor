/**
탭 이동 css
 */
$(document).on("click", ".tab-label", function (event) {
	$(".tab-label").removeClass("checked")
	$("#"+event.target.id).addClass("checked")
	$(".panel").hide()
	$("#"+event.target.id+"-panel").show()
})
