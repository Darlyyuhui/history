
var selectRoadObj;
var selectRoad = (function () {
	return function(map) {
		var api = {
			selRoadByClick : selRoadByClick,
			selRoad : selRoad,
			addRoad : addRoad,
			dialogOver : dialogOver,
			dialogOut : dialogOut,
			dialogClick : dialogClick
		};
		
		var selRoadCallback;
		var roadList=[];
		var seldialog;
		var sPoint;
		var pointGraphicLayer;
		var lineGraphicLayer;
		
		function _init() {
			lineGraphicLayer = new esri.layers.GraphicsLayer();
			lineGraphicLayer.id = "selectRoad_lgLayer";
			map.addLayer(lineGraphicLayer);
			pointGraphicLayer = new esri.layers.GraphicsLayer();
			pointGraphicLayer.id = "selectRoad_pgLayer";
			map.addLayer(pointGraphicLayer);
		}
		_init();
		
		function selRoad(callback, isAddPoint, imgsrc) {
			pointGraphicLayer.clear();
			selRoadCallback = callback;
			var mybuffer = itmap.arcgis.buffer(baseServiceURL.geometry.url);
			var handler = dojo.connect(map, "onClick", function(evt) {
				var geometry = evt.mapPoint;
				if(isAddPoint) {
					// 将点添加到地图上
					var selPointGraphic = new esri.Graphic(geometry, createPicSymbol(imgsrc));
					pointGraphicLayer.add(selPointGraphic);
				}
				sPoint = evt.screenPoint;
				mybuffer.bufferSearch(geometry, function(returnGeometry){
					// 根据geometry查询道路信息
					queryRoadByGeometry(returnGeometry[0]);
				}, 100);
				dojo.disconnect(handler);
			});
		}
		
		// 高亮渲染一条道路--callback返回所选道路的graphic对象或者""
		function addRoad(road, isSetExtent, callback) {
			queryByWhere("DLMC='"+road+"'", function(queryResult) {
				lineGraphicLayer.clear();
				if(queryResult.features.length == 0) {
					if(callback)callback("");
					return;
				}
				var polyline = new esri.geometry.Polyline(map.spatialReference);
				for(var i=0; i<queryResult.features.length; i++) {
					var graphic = queryResult.features[i];
					if(graphic.geometry.type == "polyline") {
						for(var j in graphic.geometry.paths) {
							polyline.addPath(graphic.geometry.paths[j]);
						}
					}
				}
				var newGraphic = new esri.Graphic(polyline, polylineSymbol());
				lineGraphicLayer.add(newGraphic);
				if(callback)callback({graphic:newGraphic, layer:lineGraphicLayer});
				if(isSetExtent)map.setExtent(polyline.getExtent());
			}, baseServiceURL.roadline.url);
		}
		
		// 根据geometry查询道路信息
		function queryRoadByGeometry(geometry) {
			queryByGeometry(geometry, function(queryResult1) {
				if(queryResult1.features.length < 1) {
					return;
				}
				var list = [];
				for(var i=0; i<queryResult1.features.length; i++) {
					var dlmc = queryResult1.features[i].attributes.DLMC;
					if(!dlmc || dlmc==" ") {
						dlmc = "未知道路";
					}
					list.push("'"+dlmc+"'");
				}
				var wherestr = list.join(',');
				
				// 根据道路名称查询所有道路并显示
				queryByWhere(" DLMC in ("+wherestr+")", function(queryResult) {
					lineGraphicLayer.clear();
					if(queryResult.features.length == 1) {
						var graphic = queryResult.features[0];
						graphic.setSymbol(polylineSymbol());
						pointGraphicLayer.add(graphic);
						var dlmc = graphic.attributes.DLMC || "未知道路";
						selRoadCallback(dlmc);
					}
					else if(queryResult.features.length > 1) {
						var content = "";
						var list = [];
						var flag = false;
						var num=0;
						var dlmc;
						for(var i=0; i<queryResult.features.length; i++) {
							flag = false;
							var graphic = queryResult.features[i];
							graphic.setSymbol(polylineSymbol());
							dlmc = graphic.attributes.DLMC || "未知道路";
							if(!dlmc || dlmc==" ") {
								dlmc = "未知道路";
							}
							var j=0;
							for(jlen=list.length; j<jlen; j++) {
								if(dlmc == list[j].name) {
									flag = true;
									break;
								}
							}
							if(flag) {
								list[j].graphics.push(graphic);
							}
							else {
								list.push({name:dlmc, graphics:[graphic]});
								content += '<span onclick=\"selectRoadObj.dialogClick(this.innerText)\" '+
								' onmouseover="selectRoadObj.dialogOver(this.innerText)" onmouseout="selectRoadObj.dialogOut(this.innerText)">'+
								dlmc+"</span></br>";
								
							}
							lineGraphicLayer.add(graphic);
						}
						roadList = list;
						if(list.length == 1) {
							selRoadCallback(dlmc);
							return;
						}
						seldialog = itmap.util.dialog({
							title : '请选择...',
							mask : false,
							left : sPoint.x,
							top : sPoint.y,
							content : content,
							buttonDisplay : {
								confirmButton : false,
								cancelButton : false
							}
						}).show();
					}
				}, baseServiceURL.roadline.url);
			}, baseServiceURL.roadline.url);
		}
		var isdialogClick = false;
		function dialogClick(roadname) {
			isdialogClick = true;
			seldialog.hide();
			seldialog = null;
			lineGraphicLayer.clear();
			for(var i in roadList) {
				if(roadList[i].name == roadname) {
					for(var j in roadList[i].graphics) {
						lineGraphicLayer.add(roadList[i].graphics[j]);
					}
				}
			}
			selRoadCallback(roadname);
		}
		function dialogOver(roadname) {
			isdialogClick = false;
			lineGraphicLayer.clear();
			for(var i in roadList) {
				if(roadList[i].name == roadname) {
					for(var j in roadList[i].graphics) {
						lineGraphicLayer.add(roadList[i].graphics[j]);
					}
				}
			}
		}
		function dialogOut(roadname) {
			if(isdialogClick)return;
			lineGraphicLayer.clear();
			for(var i in roadList) {
				for(var j in roadList[i].graphics) {
					lineGraphicLayer.add(roadList[i].graphics[j]);
				}
			}
		}
		
		function queryByWhere(wherestr, showQueryResult, layerUrl) {
			var queryTask = new esri.tasks.QueryTask(layerUrl);
			var query = new esri.tasks.Query();
			query.where = wherestr;
			query.outFields = ["*"];
			query.returnGeometry = true;
			
			queryTask.execute(query, showQueryResult, function(e){
				debugger;
			});
		}
		function queryByGeometry(geometry, showQueryResult, layerUrl) {
			var queryTask = new esri.tasks.QueryTask(layerUrl);
			var query = new esri.tasks.Query();
			query.geometry = geometry;
			query.outFields = ["*"];
			query.returnGeometry = true;
			
			queryTask.execute(query, showQueryResult, function(e){
				debugger;
			});
		}
		function polylineSymbol(){
			return new esri.symbol.SimpleLineSymbol(
						esri.symbol.SimpleLineSymbol.STYLE_SOLID,
						new dojo.Color([255,0,0]),3
			       );
		}
		function createPicSymbol(imgsrc) {
			if(imgsrc)return new esri.symbol.PictureMarkerSymbol(imgsrc, 24, 24);
			var ps = new esri.symbol.PictureMarkerSymbol("images/map/position.png", 34, 40);
			ps.setOffset(7, 20);
			return ps;
		}
		
		selectRoadObj = api;
		return api;
	}
})();
