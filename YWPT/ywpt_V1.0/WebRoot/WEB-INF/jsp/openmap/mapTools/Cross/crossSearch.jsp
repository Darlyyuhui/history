<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="omTabContent" class='zTabToolContent'>
  <table class="table-sel-input mar_b5 mar_l5">
	<tr>
		<td width="56">卡口名称</td>
		<td>
			<input type="text" id="crossName" name="crossName" style="width:150px;">
		</td>
	</tr>
	<tr>
		<td>所属部门</td>
		<td>
			<select id="csorgid" style="width:164px;">
				<option value="">请选择</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>卡口类型</td>
		<td>
			<select id="cstype" style="width:164px;">
				<option value="">请选择</option>
			</select>
		</td>
	</tr>
  </table>
  <div class="btn_line" style="padding:5px;">
    <input id="crossQuery" type="button" class="btn btn-info" style="margin: 0; padding:3px 10px;" value="查询" />
  </div>
</div>
<div id="cs_msg" style="color: red; font-weight: bold;"></div>

<script type="text/javascript">
	var orgList=[];
	var crossTypeList=[];
	
	if(!mapCrossObj)mapCrossObj = mapCross();
	mapCrossObj.showAll();// 显示所有卡口设备
	
	// 获取部门列表
	$.ajax({
		type: "POST",
		url: "system/org/getall",
		dataType : "json",
		cache: false,
		success: function(list){
       		orgList = list;
			$("#csorgid").empty();
			$("#csorgid").append("<option value=''>请选择</option>");
			for(var j in list) {
				$("#csorgid").append("<option value=\""+list[j].id+"\">"+list[j].name+"</option>");
			}
		},
   		error : function() {
   		}
	});
	// 获取卡口类型列表
	$.ajax({
		type: "POST",
		url: "system/diccache/devicetypecode",
		dataType : "json",
		cache: false,
		success: function(list){
       		crossTypeList = list;
			$("#cstype").empty();
			$("#cstype").append("<option value=''>请选择</option>");
			for(var j in list) {
				$("#cstype").append("<option value=\""+list[j].code+"\">"+list[j].name+"</option>");
			}
		},
   		error : function() {
   		}
	});

	$("#crossQuery").click(function(){
		var gpsorgid = $("#csorgid").val();
		$("#cs_msg").html("");
		$.ajax({
    		type : "POST",
    		url:"cross/search/getDevices",
    		dataType : "json",
    		cache: false,
    		data:"deviceName="+$("#crossName").val()+"&orgId="+$("#csorgid").val()+"&type="+$("#cstype").val(),
    		success : function(list) {
        		crossSearch_addResultToMap(list);
    		},
    		error : function() {
    		}
    	});
	});
	
	function crossSearch_addResultToMap(list) {
    	var _relation = {}
    	var _resArr = [];
    	var _codes = [];
        for(var i in list) {
        	for(var j in orgList) {
        		if(list[i].orgId == orgList[j].id) {
        			list[i].org = orgList[j].name;
        		}
        	}
        	for(var j in crossTypeList) {
        		if(list[i].devicetypecode == crossTypeList[j].code) {
        			list[i].type = crossTypeList[j].name;
        		}
        	}
        	_codes.push("'"+list[i].code+"'");
			_resArr.push(new resItemO(list[i].name, function(obj){
				// 根据code在地图上查询对应设备并显示信息框
	        	var graphic = {geometry: new OpenLayers.Geometry.Point(obj.mapx, obj.mapy), attributes: obj};
	        	mapCrossObj.getCrossDeviceByCode(graphic);
			},{},list[i]));
        }
        if(!list || !list.length) {
        	$("#cs_msg").html("查询结果为空！");
        	itmap.util.mapResultboxNew().init("mapResultC").clearBox();
	        $("#mapOther").css("display", "none");
	        return;
        }
        
        var mslayer = map.getLayer("cross");
        // 清除图层要素
		mslayer.removeAllFeatures();
		for(var m=0; m<list.length; m++){
			var devicept= new OpenLayers.Geometry.Point(list[m].mapx, list[m].mapy);
			var deviceAttr = {"buildtime":itmap.util.DateFromat.dateFormartFull(list[m].buildtime),"devicetypecode":list[m].devicetypecode,"ip":list[m].ip,"name":list[m].name,"code":list[m].code};
			var deviceGraphic=  new OpenLayers.Feature.Vector(devicept, deviceAttr);
			mslayer.addFeatures(deviceGraphic);
		}        
        itmap.util.mapResultboxNew().init("mapResultC").addContent({content:_resArr,switchtab:true,relation:_relation});
    	var mapChart = new MapChart();
    	$("#mapOther").css("display","block");
        if($("#csorgid").val()) {
        	// 根据类型统计
	    	mapChart.setParams(list,"type").createChart();
        }
        else {
        	// 根据部门统计
	    	mapChart.setParams(list,"org").createChart();
        }
    }
</script>