<div class="tabs">
	<div class="tab">
		<label class="tab-label checked" id="one" for="one">토큰</label>
	</div>
	<#if token_valid=true>
	<div class="tab">
		<label class="tab-label" id="two" for="two">블로그 정보</label>
	</div>
	<div class="tab">
		<label class="tab-label" id="three" for="three">글 목록</label>
	</div>
	</#if>
</div>

<div class="panels">
	<div class="panel" id="one-panel">
		<#include "/token/tokenInfo.ftl">
	</div>
	<div class="panel" id="two-panel">
		<#include "/read/blogInfo.ftl">
	</div>
	<div class="panel" id="three-panel">
		<div class="panel-title">Title3</div>
		<p>Content3</p>
	</div>
</div>

