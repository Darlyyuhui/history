MapFactory.Define("MapFactory/Layer/HeatLayer*",[
	"MapFactory/MapManager",
	"MapFactory/LayerManager",
	"MapFactory/ThirdParty/HeatMap/HeatMap*"
],function(MapManager,LayerManager,HeatMap){
	var _originDatas = {};
	return function(){
		var api = {
			create : create,
			addDataSet : addDataSet,
			clear : clear
		};

		var _conf = {
			radius : 30,
			opacity : 50
		};

		var _mapManager = MapManager();
		var _layerManager = LayerManager("heatLayer");
		var _heatMap = HeatMap();
		var _dom = MapFactory.Dom;
		var _domId = null;

		function create(config){
			_domId = _layerManager.getDomId();
			var _element = _dom.getById(_domId);
			var _heatMapDiv = _dom.createElem("div");
			var _size = _mapManager.getMapSize();
			_dom.css(_element,"width",_size.width);
			_dom.css(_element,"height",_size.height);
			_dom.css(_element,"position","absolute");
			MapFactory.Extend(_conf,config);
			_conf.element = _element;
			_heatMap = _heatMap.create(_conf);			
			_mapManager.removeExtentChangeEvent();
			_mapManager.setExtentChangeEvent(
				function(){
				if(!MapFactory.VariableTypes.isEmptyObject(_originDatas)){
					addDataSet(_originDatas);
				}						   						
		});
		}

		/**
		 * 添加数据集
		 * @param datas Object 数据集
		 *  + max int 最大数
		 *  + graphics Graphics[]
		 */
		function addDataSet(datas){
			if(!datas || !datas.graphics || !datas.graphics.length){
				return;
			}
			_originDatas = datas;
			var _dataSet = {};
			var _graphics = datas.graphics;
			var _data = [];
			_dataSet.max = datas.max;

			for(var i=0,len= _graphics.length;i<len;i++){
				var _graphic = _graphics[i];
				var _attr = _graphic.attributes;
				var _geo = _graphic.geo;
				var _coor = _geo.points.split(",");
				var _screenP = _mapManager.toScreen(parseFloat(_coor[0]),parseFloat(_coor[1]));
				_data.push({"x":_screenP.x,"y":_screenP.y,"count":_attr.count});
			}
			_dataSet.data = _data;
			_heatMap.store.setDataSet(_dataSet);
		}

		function clear(){
			var _element = _dom.getById(_domId);
			if(_element){
				_dom.empty(_element);
			}
		}

		return api;
	}
});