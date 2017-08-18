<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.mian_white {
	background: white;
	color: #000;
	
	margin-left: 5px;
    
}
.mian_white tr{
  height: 28px;
}
.apb-TabMargin{
  height: 100%;
  padding-bottom: 5px;
}
.apb-td-word-size{
  font-weight: bold;
  width: 30%;
}
</style>

<div class="apb-TabMargin">
<table class="width-100 text-center mian_white">

	<tbody>
		<tr>
			<td class="apb-td-word-size">基地名称</td>
			<td>${bpb.name }</td>
		</tr>


		<tr>
			<td class="apb-td-word-size">详细地址</td>
			<td>${bpb.address }</td>
		</tr>

		<tr>
			<td class="apb-td-word-size">年产量</td>
			<td>${bpb.annualOutput }</td>
		</tr>

		<tr>
			<td class="apb-td-word-size">种植面积(亩)</td>
			<td>${bpb.area }</td>
		</tr>

		<tr>
			<td class="apb-td-word-size">主营产品</td>
			<td>${bpb.mainProduct }</td>
		</tr>

	</tbody>
</table>
</div>
