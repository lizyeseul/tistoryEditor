<div class="tabs">
	<div class="tab">
		<label class="tab-label" id="token-tab">토큰</label>
	</div>
	<div class="tab">
		<label class="tab-label" id="info-tab">블로그 정보</label>
	</div>
	<div class="tab">
		<label class="tab-label" id="list-tab">글 목록</label>
	</div>
	<div class="tab">
		<label class="tab-label" id="read-tab">글 읽기</label>
	</div>
</div>

<div class="panels">
	<div class="panel" id="token-panel">
		<#include "/token/tokenInfo.ftl">
	</div>
	<div class="panel" id="info-panel">
		<#include "/read/blogInfo.ftl">
	</div>
	<div class="panel" id="list-panel">
		<#include "/read/postList.ftl">
	</div>
	<div class="panel" id="read-panel">
		<#include "/read/postRead.ftl">
	</div>
</div>

