<div class="header-container">
	<button onclick="requestPostList()">조회</button>
	<table class="header-info">
<!-- 		<colgroup> -->
<!-- 			<col style="width:60px;"/> -->
<!-- 			<col/> -->
<!-- 		</colgroup> -->
		
		<tr><td name="header-blog"></td></tr>
		<tr><td ><select class="wide" id="ctgy_select"></select></td></tr>
	</table>
</div>

<div class="divide-h"></div>

<div class="warpper">
	<ul id="pagination-ul" class="pagination"></ul>
</div>

<table id="postListTable" class="post-list-table">
	<colgroup>
		<col style="width:50px;"/>
		<col/>
		<col style="width:200px;"/>
		<col style="width:450px;"/>
	</colgroup>
	<tr>
		<th>id</th>
		<th>title</th>
		<th>date</th>
		<th>postUrl</th>
	</tr>
	<tbody id="postListTBody"></tbody>
</table>
