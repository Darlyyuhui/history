<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="<%=basePath%>compnents/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<script src="<%=basePath%>compnents/ztree/js/jquery.ztree.core-3.5.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>compnents/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>

<style type="text/css">
	.ztree{
		width:260px;
		white-space:nowrap;
	}
</style>
<div class='zTabToolContent'>
  <table class="map_table mar_5">
	<tr>
		<td width="64">所属部门：</td>
		<td>
			<input id="videoorg" type="text" style="width: 150px;height:28px;padding:4px; overflow: hidden;" readonly placeholder="点击选择部门"/>
			<input id="videoorgHid" type="hidden"/>
		</td>
	</tr>
	<tr>
		<td>设备类型：</td>
		<td>
			<select id="videodevicetype" style="width:154px;">
				<option value="" selected="selected">请选择</option>
				<option value="0">枪机</option>
				<option value="1">球机</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>监控类型：</td>
		<td>
			<select id="videotype" style="width:154px;">
				<option value="" selected="selected">请选择</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>建设厂家：</td>
		<td>
			<select id="videocom" style="width:154px;">
				<option value="">请选择</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>监控方向：</td>
		<td>
			<select id="videodir" style="width:154px;">
				<option value="">请选择</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>监控名称：</td>
		<td>
			<input type="text" id="videoName" name="videoName" style="width:154px;height:28px;padding:4px;overflow:hidden;">
		</td>
	</tr>
	<tr>
		<td>设备编号：</td>
		<td>
			<input type="text" id="videoCode" name="videoCode" style="width:154px;height:28px;padding:4px;overflow:hidden;">
		</td>
	</tr>
  </table>
  <div class="btn_line" style="padding:5px;">
    <input id="videoSearch" type="button" class="btn btn-info" value="查询" />
    <input id="videoReSetQuery" type="button" class="btn btn-info mar_l10" value="重置" />
  </div>
</div>

<script type="text/javascript">
(function(){
MapFactory.Require([
	"MapFactory/MapManager",
	"MapFactory/LayerManager",
	"ItmsMap/Util/ResultBox*",
	"ItmsMap/Util/ResItemO*",
	"MapFactory/Geometry*",
	"MapFactory/GraphicManager",
	"MapFactory/InfoWindowManager",
	"ItmsMap/Util/PositionManager*",
	"ItmsMap/SymbolConfig*",
	"ItmsMap/Util/DateFormat*",
	"ItmsMap/Util/Tip*",
	"ItmsMap/Util/TreeWidget*",
	"ItmsMap/Video/Video*"
],function(MapManager,LayerManager,ResultBox,ResItem,Geometry,GraphicManager,InfoWindow,PositionManager,symbolConfig,dateFormat,Tip,Tree,Video){
	var positionManager = PositionManager();
	var map = MapManager();
	var tip = Tip();
	var video = Video();
	
	//初始化部门下拉框
	var deptTree = Tree();
	deptTree.init("videoorg", {idKey: "id", pIdKey: "pId", callback: depCallback}, 
			{ajaxSettings: {url: "map/getDeptAsJson/", type: "POST", data: {menuid:menuid}}, isAjax: true});
	
	// 获取设备方向
	$.ajax({
		type: "POST",
		url: "system/diccache/devicetdirection",
		dataType : "json",
		cache: false,
		success: function(list){
			$("#videodir").empty();
			$("#videodir").append("<option value=''>请选择</option>");
			for(var j in list) {
				$("#videodir").append("<option value=\""+list[j].code+"\">"+list[j].name+"</option>");
			}
		},
   		error : function() {
   		}
	});
	// 获取设备监控类型
	$.ajax({
		type: "POST",
		url: "system/diccache/devicetmonittype",
		dataType : "json",
		cache: false,
		success: function(list){
			$("#videotype").empty();
			$("#videotype").append("<option value=''>请选择</option>");
			for(var j in list) {
				$("#videotype").append("<option value=\""+list[j].code+"\">"+list[j].name+"</option>");
			}
		},
   		error : function() {
   		}
	});
	// 获取设备厂家类型列表
	$.ajax({
		type: "POST",
		url: "device/devicecompany/getall/",
		dataType : "json",
		cache: false,
		success: function(list) {
			$("#videocom").empty();
			$("#videocom").append("<option value=''>请选择</option>");
			for(var j in list) {
				$("#videocom").append("<option value=\""+j+"\">"+list[j]+"</option>");
			}
		},
   		error : function() {
       		
   		}
	});
	
	function depCallback(nodes) {
		var strs = [],
			ids = [];
		for (var i = nodes.length - 1; i >= 0; i--) {
			//if (!nodes[i].isParent) {
				strs.push(nodes[i]["name"]);
				ids.push(nodes[i]["id"]);
			//}
		}
		$("#videoorg").val(strs.join(";"));
		$("#videoorgHid").val(ids.join(";"));
	}
	
	$("#videoSearch").click(function(){
		var params = "orgId="+$("#videoorgHid").val()+"&typeCode="+$("#videodevicetype").val()+"&directionCode="+$("#videodir").val()+"&companyId="+$("#videocom").val()+"&name="+$("#videoName").val()+"&code="+$("#videoCode").val()+"&monitType="+$("#videotype").val();
		video.videoQuery(params);
	});
	// 重置查询条件
	$("#videoReSetQuery").click(function() {
		$("#videotype").val("");
		$("#videodir").val("");
		$("#videocom").val("");
		$("#videoName").val("");
		$("#videoCode").val("");
		$("#videoorg").val("");
		$("#videoorgHid").val("");
		deptTree.cancelSelectedNode();
	});
	
});
})();
</script>