<input id="token_valid_input" type="checkbox" onchange = "onChangeTokenValid(this)" hidden/>

<table>
	<tr>
		<td>토큰</td>
		<td><button onclick="requestAuthCode()">발급</button></td>
	</tr>
</table>
	
<table id="code_form" style="display:none;">
	<tr>
		<td><a id="code_url" href="./" target="_blank">허용하러 가기</a></td>
	</tr>
	<tr>
		<td><input type="text" id="response_code" style="width:100%;"></input></td>
	</tr>
	<tr>
		<td><button onclick="requestToken()">토큰 요청</button></td>
	</tr>
</table>

<table id="token_info" style="display:none;">
	<tr>
		<th>access_token</th>
		<td><label id="access_token_label"></label></td>
	</tr>
	<tr>
		<th>토큰 발급시간</th>
		<td>${start_time!}</td>
	</tr>
	<tr>
		<th>토큰 만료시간</th>
		<td>${end_time!}</td>
	</tr>
</table>
