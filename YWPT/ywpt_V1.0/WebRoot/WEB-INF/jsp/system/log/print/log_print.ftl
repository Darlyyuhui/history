<style type="text/css">
* {
	margin:0 auto;
	padding:0;
}
table{
	font-family:'SimSun';
	font-size:11px;
}
.thead2 tr th {
	text-align:center;
	font-weight:normal;
	height:26px;
	background:#ccc;
	line-height:26px;
	border: 1px solid #666666;
}
.body_table tr td {
	height:24px;
	line-height:24px;
	border: 1px solid #666666;
}
</style>
<table width="100%" style="border-collapse: collapse;padding:0 20px;" border="0" cellpadding="0" cellspacing="0">
	<thead>
    <tr>
      <th colspan="30" style="font-size:20px; padding:5px;">按操作员统计违法确认操作日志信息</th>
    </tr>
    <tr>
      <th colspan="30" style="padding:10px; font-weight:normal; text-align:right;">${secondTitle}</th>
    </tr>
  </thead>
  <thead class="thead2">
    <tr>
      <th>操作员</th>
      <th>违法确认数</th>
    </tr>
  </thead>
  <tbody  class="body_table" align="center">
		<#list pageList as log>
			<tr style='font-size:10px; height: 24; font-weight: lighter; text-align: center;'>
				<td>${log.operator}</td>
				<td>${log.counts}</td>
			</tr>
		</#list> 
	</tbody>
	<tfoot>
	  <tr>
	      <td colspan="2" style="border-left:0; border-right:0; border-bottom:0; text-align:right; padding:10px 10px;">审核人:__________________  复核人:__________________  制表单位:__________________ </td>
	    </tr>
	</tfoot>
</table>	
