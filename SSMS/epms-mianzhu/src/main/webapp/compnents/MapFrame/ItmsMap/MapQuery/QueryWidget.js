MapFactory.Define("ItmsMap/MapQuery/QueryWidget*",[
    "MapFactory/Query",
    "MapFactory/GraphicManager",
    "MapFactory/LayerManager",
    "ItmsMap/Util/ResItemO*",
    "ItmsMap/Util/ResultBox*",
    "MapFactory/GeometryType*",
    "MapFactory/MapManager",
    "ItmsMap/SymbolConfig*",
    "MapFactory/Geometry*"
],function(query,graphicManager,layerManager,resItem,resBox,geoType,mapManager,symbolConfig,geoClass){
	return function(){

		var _mapManager = mapManager(),
			_layers = _mapManager.getMapConfig().layers,
			_layerMGM = layerManager(_layers.queryResult.id),
			_query = null,
			_resultLayerID = _layers.queryResult.id,
			_positionLayerID = _layers.position.id,
			_positionLayer = layerManager(_positionLayerID),
			_urls = [
				_layers.baseRoad.url, // 道路图层
				_layers.townsPOI.url, // 乡镇点
				_layers.governmentPOI.url, // 行政事业单位
				_layers.enterprisePOI.url, // 企业
				_layers.publicPOI.url, // 公共场所
				_layers.hospitalPOI.url, // 医疗卫生
				_layers.tourismPOI.url, // 旅游景点
				_layers.marketPOI.url, // 超市广场
				_layers.petrolFillPOI.url, // 加油站
				_layers.educationPOI.url, // 教育机构
				_layers.orgPOI.url, // 行政区划
			],
			_url,
			_resArr = [],
			_condition = null,
			_geo = null;

		this.setType = function(type){
			switch(type){
				case "baseRoad" : {
					_url = _urls[0];
					break;
				}
				case "townsPOI" : {
					_url = _urls[1];
					break;
				}
				case "governmentPOI" : {
					_url = _urls[2];
					break;
				}
				case "enterprisePOI" : {
					_url = _urls[3];
					break;
				}
				case "hospitalPOI" : {
					_url = _urls[5];
					break;
				}
				case "tourismPOI" : {
					_url = _urls[6];
					break;
				}
				case "marketPOI" : {
					_url = _urls[7];
					break;
				}
				case "petrolFillPOI" : {
					_url = _urls[8];
					break;
				}
				case "educationPOI" : {
					_url = _urls[9];
					break;
				}
				case "orgPOI" : {
					_url = _urls[10];
					break;
				}
				case "publicPOI" : {
					_url = _urls[4];
					break;
				}
				default : {
					_url = "";
					break;
				}
			}
		}

		/**
		 * 搜索成功回调
		 */
		function searchSuccess(result){
			var _resultItemArr = [],
				_relation = {
					NAME : "名称",
					TYPE : "类型"
				};
			_layerMGM.clear();
			for(var i=0,len=result.length;i<len;i++){
				var _g = result[i],
					_gMGM = graphicManager(result[i]),
					_attr = _g.attributes;
				_gMGM.addToLayer(_resultLayerID);
				_resultItemArr.push(new resItem(_attr.NAME || "无名",(function(_index){
					return function(){
						var coor = result[_index].geo.points.split(",");
						mapManager().centerAt(coor[0],coor[1]);
						_positionLayer.clear();
						graphicManager({
							geo : new geoClass({
								type : geoType.POINT,
								points : coor[0] + "," + coor[1]
							}),
							symbol : symbolConfig.positionSignSymbol
						}).addToLayer(_positionLayerID);
					}
				})(i),_attr));
			}
			resBox().init("mapResultC").addContent({content:_resultItemArr,relation:_relation,isAddChart:true,targetField:"TYPE"});
		}

		/**
		 * 搜索失败回调
		 */
		function searchFailed(e){
			
		}

//全部图层都查询的情况---------------
		function _iteratorUrls(urlIndex){
			if(urlIndex>=_urls.length){
				searchSuccess(_resArr);
			} else  {
				_query.setUrl(_urls[urlIndex]);
				if(_condition){
					_query.setCondition(_condition);
				} 
				if(_geo){
					_query.setGeometryPrecision(50);
					_query.setGeometry(_geo);
				}
				_query.execute(function(res){
					_resArr = _resArr.concat(res);
					urlIndex=urlIndex+1;
					_iteratorUrls(urlIndex);
				},function(e){
					if(urlIndex<_urls.length){
						urlIndex=urlIndex+1;
						_iteratorUrls(urlIndex);
					}
				});
			}
		}

		//属性查询的方法--------------
		this.search = function(keywords){
			_query = query();
			_resArr = [];
			if(_url){
				_query.setUrl(_url);
				_query.setCondition({"NAME like":keywords});
				_query.execute(searchSuccess,searchFailed);
				return;
			}else{
				_geo = null;
				_condition = {"NAME like":keywords};
				_iteratorUrls(0);
			}
		}

		//空间查询的方法-----------------
		this.geoSearch = function(geo){
			_query = query();
			_resArr = [];
			if(_url){
				_query.setUrl(_url);
				_query.setGeometry(geo);
				_query.setGeometryPrecision(50);
				_query.execute(searchSuccess,searchFailed);
				return;
			}else{
				_condition = null;
				_geo = geo;
				_iteratorUrls(0);
			}
		}

		// 清除图层-----------------
		this.clear = function(){
			_layerMGM.clear();
			_positionLayer.clear();
			resBox().init("mapResultC").clearBox();
		}
	}
});