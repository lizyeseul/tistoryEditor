<div class="tabs">
	<div class="tab">
		<label class="tab-label" id="token-tab">토큰</label>
	</div>
	<div class="tab">
		<label class="tab-label" id="info-tab" for="two">블로그 정보</label>
	</div>
	<div class="tab">
		<label class="tab-label" id="list-tab" for="three">글 목록</label>
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
		<div class="panel-title">Title3</div>
		<p>Content3</p>
	</div>
</div>

