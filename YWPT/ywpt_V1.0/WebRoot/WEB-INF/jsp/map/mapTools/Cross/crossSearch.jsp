<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="<%=basePath%>compnents/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<script src="<%=basePath%>compnents/ztree/js/jquery.ztree.core-3.5.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>compnents/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript">
//复选框改为单选框
$(function() {
	var $crossstat = $("input[name='crossstat']");
	
	$crossstat.click(function() {
		var that = this;
		if (!this.checked)this.checked = true;
		$crossstat.each(function(index, node) {
			if (node != that && node.checked) {
				node.checked = false;
			}
		});
	});
	
	//隐藏单选框 需要使用的话注销下面语句就可以了
	$crossstat.each(function(index, node) {
		node.style.cssText = "display: none";
	});
});
</script>
<style type="text/css">
	.ztree{
		width:260px;
		white-space:nowrap;
	}
</style>
<div class="zTabToolContent mar_5">
  <table width="100%" class="map_table">
	<tr>
		<td>设备状态：</td>
		<td>
			<select id="crossstatus" name="crossstatus" style="width:150px;height:28px;padding:4px;">
				<option value="">请选择</option>
				<option value="0">正常</option>
				<option value="1">故障</option>
				<option value="2">未接入</option>
			</select>
			<input type="checkbox" name="crossstat" checked/>
		</td>
	</tr>
	<tr>
		<td>设备类型：</td>
		<td>
			<select id="crosstype" style="width:150px;height:28px;padding:4px;">
				<option value="">请选择</option>
			</select>
			<input type="checkbox" name="crossstat"/>
		</td>
	</tr>
	<tr>
		<td >所属部门：</td>
		<td>
			<input id="crossdepart" type="text" style="width: 150px;height:28px;padding:4px; overflow: hidden;" readonly placeholder="点击选择部门"/>
			<input id="crossdepartHid" type="hidden"/>
			<input type="checkbox" name="crossstat" />
		</td>
	</tr>
	<tr>
		<td >建设厂家：</td>
		<td>
			<select id="crosscompany" style="width:150px;height:28px;padding:4px;">
				<option value="">请选择</option>
			</select>
			<input type="checkbox" name="crossstat"/>
		</td>
	</tr>
	<tr>
		<td >设备功能：</td>
		<td>
			<!-- 
			<select id="crossfunction" style="width:150px;">
				<option value="">请选择</option>
			</select>
			-->
			<input type="text" id="crossfunc" style="width: 150px;height:28px;padding:4px;overflow: hidden" readonly placeholder="点击选择功能" />
			<input type="hidden" id="crossfuncHid"/>
			<input type="checkbox" name="crossstat" />
		</td>
	</tr>
	<tr>
		<td >设备名称：</td>
		<td>
			<input type="text" id="crossname" style="width:150px;height:28px;padding:4px;overflow: hidden;"/>
		</td>
	</tr>
	<tr>
		<td >设备编码：</td>
		<td>
			<input type="text" id="crosscode" style="width:150px;height:28px;padding:4px; overflow: hidden;"/>
		</td>
	</tr>
  </table>
</div>
<div class="btn_line">
  <input id="crossQuery" type="button" class="btn btn-info" value="查询" />
  <input id="reSetQuery" type="button" class="btn btn-info mar_l10" value="重置" />
</div>
<script type="text/javascript">
	MapFactory.Require(["ItmsMap/Util/TreeWidget*", "ItmsMap/Cross/Cross*"], function(Tree, Cross) {
		
		//tree.init("crossFunc", {ajaxSettings: {url: "device/deviceinfo/getDeviceTypes/"}, isAjax: true});
		//初始化功能下拉框
		var typeTree = Tree();
		typeTree.init("crossfunc", {callback: funCallback}, {ajaxSettings: {url: "cross/map/getDeviceTypes/"}, isAjax: true}, "checkbox");

		//初始化部门下拉框
		var deptTree = Tree();
		deptTree.init("crossdepart", {idKey: "id", pIdKey: "pId", callback: depCallback}, 
				{ajaxSettings: {url: "map/getDeptAsJson/", type: "POST", data: {menuid:menuid}}, isAjax: true});
				
		//初始化卡口类型 建设厂家等元素
		Cross().getCrossInfo(function(e) {
			if (Object.prototype.toString.call(e) !== "[object Object]")return;
			var company = e["company"],
				devicetypes = e["devicetypes"];
			
			var companyHtml = "",
				typesHtml = "",
				companyDom = document.getElementById("crosscompany"),
				typesDom = document.getElementById("crosstype"),
				option,
				textNode,
				frag;
			
			frag = document.createDocumentFragment();
			for (var key in company) {
				option = document.createElement("option");
				option.value = key;
				textNode = document.createTextNode(company[key]);
				option.appendChild(textNode);
				frag.appendChild(option);
			}						
			companyDom.appendChild(frag);			
			option = null;
			textNode = null;
			
			frag = document.createDocumentFragment();
			for (var key in devicetypes) {
				option = document.createElement("option");
				option.value = key;
				textNode = document.createTextNode(devicetypes[key]);
				option.appendChild(textNode);
				frag.appendChild(option);
			}
			typesDom.appendChild(frag);
		});
		
		function funCallback(nodes) {
			var strs = [],
				ids = [];
			for (var i = nodes.length - 1; i >= 0; i--) {
				//if (!nodes[i].isParent) {
					strs.push(nodes[i]["name"]);
					ids.push(nodes[i]["id"]);
				//}
			}
			document.getElementById("crossfunc").value = strs.join(";");
			document.getElementById("crossfuncHid").value = ids.join(";");
		}
		
		function depCallback(nodes) {
			var strs = [],
				ids = [];
			for (var i = nodes.length - 1; i >= 0; i--) {
				//if (!nodes[i].isParent) {
					strs.push(nodes[i]["name"]);
					ids.push(nodes[i]["id"]);
				//}
			}

			document.getElementById("crossdepart").value = strs.join(";");
			document.getElementById("crossdepartHid").value = ids.join(";");
		}
		
		// 重置查询条件
		$("#reSetQuery").click(function() {
			$("#crossstatus").val("");
			$("#crosstype").val("");
			$("#crossdepart").val("");
			$("#crossdepartHid").val("");
			$("#crosscompany").val("");
			$("#crossfunc").val("");
			$("#crossfuncHid").val("");
			$("#crossname").val("");
			$("#crosscode").val("");
			typeTree.cancelSelectedNode();
			deptTree.cancelSelectedNode();
		});
		$("#crossQuery").click(function() {
			//参数对象
			var paramObj = {},
			
			//存储dom对象id的数组
			domIds = ["crossstatus", "crosstype", "crossdepartHid", "crosscompany", "crossfuncHid", "crossname", "crosscode"],
			//dom对象id
			id;

			for (var length = domIds.length, i = length - 1; i >= 0; i--) {
				id = domIds[i];
				paramObj[id] = document.getElementById(id).value;			
			}
			
			Cross().crossQuery(paramObj);
		});		
	});
	
</script>