
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
</head>
<body>
<table class="gis_table mar_t10" style="width:100%;">
	<tr>
		<td width="90">请选择类型：</td>
		<td><select id="MultipleQuerySelect" style="width:110px;"></select></td>
	</tr>
	<tr>
		<td width="90">请输入关键字：</td>
		<td><input type="text" id="MultipleQueryInput" class="input" style="width:110px;"/></td>
	</tr>
</table>
<div class="btn_line mar_t10">
  <input id='MultipleQueryButton' type="button" class="btn btn-info" value="搜索">
  <input id='MultipleQueryClearButton' type="button" class="btn mar_l10" value="清除">
  <div id="ch_msg" style="color: red; font-weight: bold;"></div>
</div>
<script type="text/javascript">
	var $query = $("#MultipleQuerySelect").empty(),
		selectValue="",
		popup,
		html = "",
		searchLayer;

	/**
	*获取兴趣点获取类型
	*/
	$.ajax({
		url: "openmap/query/poitype",
		data: "",
		success: function(results) {
			if (results && Object.prototype.toString.call(results) == "[object Object]") {
				for (var key in results) {
					html += "<option value='" + key + "'>" + results[key] + "</option>";
				}

				$query.append(html);

				selectValue = $query[0].value;
			}
		},
		fail: function() {
			
		}
	});

	//下拉框选中事件
	$query.change(function() {
		selectValue = this.value;
	});

	
	$("#MultipleQueryButton").click(function() {
		//清除
		if(map.getLayer("searchLayer")){
			searchLayer = map.getLayer("searchLayer");
			searchLayer.removeAllFeatures();
		} else {
			searchLayer = new OpenLayers.Layer.Vector("searchLayer");
			searchLayer.id = "searchLayer";
			map.addLayer(searchLayer);
		}

		//if (!document.getElementById("MultipleQueryInput").value) {
		//	
		//}
		
	  	$.ajax({
			type: "POST",
			url: "openmap/query/poisearch", 
			dataType: "json",
			data: "name=" + $("#MultipleQueryInput").val() + "&type=" + selectValue,
			success : function(queryResoultlist) {	
		  		 var _relation = {},
		  		 	_res = [],
		  		 	queryResoultlistAttr,
		  		 	queryResoultlistGraphics = [],
		  		 	popup,
		  		 	position;

		  		 for(var m = 0; m < queryResoultlist.length; m ++){
					queryResoultlistAttr = {"name": queryResoultlist[m].name};		  					
  		   			queryResoultlistGraphics[m] =  new OpenLayers.Feature.Vector(OpenLayers.Geometry.fromWKT(queryResoultlist[m].geometry), queryResoultlistAttr);
  		   			searchLayer.addFeatures(queryResoultlistGraphics[m]);		
  		  			//右侧数据统计     		
  					_res.push(new resItemO(queryResoultlist[m].name, (function(_index){
								return function(){					
				 					if(popup){
					 					map.removePopup(popup);	    	        	  
				 	    	        }

									position = queryResoultlistGraphics[_index].geometry.getBounds().getCenterLonLat();
				 					popup = new OpenLayers.Popup.FramedCloud("", position, null, "<div style='line-height:25px;'>名称:" + queryResoultlist[_index].name + "</div>", null, true);
					 	            popup.autoSize=true;
					 	            queryResoultlistGraphics.popup = popup;	           
					 	            map.addPopup(popup); 
					 	        }
					 	   })(m), queryResoultlist[m].name));     			
		           }	   
		  		   itmap.util.mapResultboxNew().init("mapResultC").addContent({content:_res,switchtab:true,relation:_relation}); 	     
		  	   }
	  	   });
		});

	//清除操作
	$("#MultipleQueryClearButton").click(function() {
		if(map.getLayer("searchLayer")){
			map.getLayer("searchLayer").removeAllFeatures();
		 }
	});
</script>
</body>
</html>