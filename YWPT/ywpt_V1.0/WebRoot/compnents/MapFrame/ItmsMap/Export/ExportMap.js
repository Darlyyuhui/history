MapFactory.Define("ItmsMap/Export/ExportMap*",[
    "MapFactory/Util/Dialog*",
    "ItmsMap/SymbolConfig*",
    "MapFactory/MapManager",
    "MapFactory/LayerManager",
    "MapFactory/Export"
],function(Dialog,SymbolConfig,MapManager,LayerManager,ExportClass){
	return function(){
		var _urls = {
			exportMapParamPage : "map/getExportPage/",
			exportMap : "http://193.169.100.111:8399/arcgis/rest/services/xianBaseMapNew/MapServer/exts/PageLayout/pageLayoutExport",
			exportMapProxy : "map/exportMap/"
		};

		var api = {
			init : init
		};

		var _d = null,
			_legendBox = null,
			_templateContainer = null,
			_dom = MapFactory.Dom,
			_xhr = MapFactory.XHR,
			_arr = MapFactory.Array,
			_json = MapFactory.JSON;

		var _legendItemHoverClass = "mapExportLegendItemHover",
			_legendItemSelectedClass = "mapExportLegendSelected",
			_templateHoverClass = "exportTemplateBoxHover",
			_templateSelected = "exportTemplateBoxSelected";

		var _mapManager = MapManager();

		var _selectedLegendItem = []; // 用于存储已经选择过的图例ID

		var _templateIndex = null;

		function _initContainer(){
			_d = Dialog({
				mutiDialog : true,
				mutiDialogSeed : "exportmap",
				mask : false,
				title : "地图导出",
				top : 35,
				right : 10,
				content : "<div id='mapExportParamSet' style='width:350px;'></div>",
				buttonDisplay : {
					'confirmButton' : false,
					'cancelButton' : false
				},
				closeCall : function(){
					if(_legendBox){
						_legendBox.hide();
					}
					if(_templateContainer){
						_templateContainer.hide();
					}
					_selectedLegendItem.length = 0;
				}
			}).show();
		}

		function _loadParamPage(){
			_xhr.Load("mapExportParamSet",_urls.exportMapParamPage,{},function(){
				_initAddLegendBtn();
				_initAddTemplateBtn();
				_initExportBtn();
			});
		}

		function _initLegendContainer(){
			_legendBox = Dialog({
				mutiDialog : true,
				mutiDialogSeed : "exportmaplegend",
				mask : false,
				title : "图例设置",
				top : 200,
				left : 910,
				content : "<div id='mapLegendContianer' style='width:350px;height:150px;overflow:auto;'></div>",
				buttonDisplay : {
					'confirmButton' : false,
					'cancelButton' : false
				}
			}).show();
			var _legendC = _dom.getById("mapLegendContianer"),
				_table = _dom.createElem("table"),
				_tbody = _dom.createElem("tbody"),
				_i = 0,
				_tr = null;
			for(var elem in SymbolConfig){
				if(!_tr || _i%3 == 0){
					_tr = _dom.createElem("tr");
					_dom.append(_tbody,_tr);
				}
				var _item = SymbolConfig[elem];
				if(_item.url){
					var _td = _dom.createElem("td");
					_td.innerHTML = "<div class='LegendItem'><img src='"+_item.url+"' style='width:20px;height:20px;'/><span>"+_item.name+"</span></div>";
					_dom.css(_td,"textAlign","left");
					_dom.css(_td,"cursor","pointer");
					_dom.attr(_td,"legendItemID",elem);
					if(_arr.inArray(elem,_selectedLegendItem) >= 0){
						_dom.addClass(_td,_legendItemSelectedClass);
					}
					_td.onmouseover = function(){
						_dom.addClass(this,_legendItemHoverClass);
					};
					_td.onmouseout = function(){
						_dom.removeClass(this,_legendItemHoverClass);
					};
					_td.onclick = function(){
						if(_dom.hasClass(this,_legendItemSelectedClass)){
							_dom.removeClass(this,_legendItemSelectedClass);
							_arr.removeItem(_dom.attr(this,"legendItemID"),_selectedLegendItem)
						}else{
							_dom.addClass(this,_legendItemSelectedClass);
							_selectedLegendItem.push(_dom.attr(this,"legendItemID"));
						}
						_dom.getById("exportLegendBox").innerHTML = "已选择"+_selectedLegendItem.length+"个";
					};
					_dom.append(_tr,_td);
					_i++;
				}
			}
			_dom.append(_table,_tbody);
			_dom.append(_legendC,_table);
		}

		function _initAddLegendBtn(){
			_dom.getById("exportMapAddLegend").onclick = function(e){
				_initLegendContainer();
			}
		}

		function _initAddTemplateBtn(){
			_dom.getById("exportMapTemplates").onclick = function(e){
				_initTemplateContainer();
			}
		}

		function _initExportBtn(){
			_dom.getById("exportMapConfirm").onclick = function(e){
				_disableExportBtn();
				_export();
			}
		}

		function _disableExportBtn(){
			var _btn = _dom.getById("exportMapConfirm");
			_dom.html(_btn,"正在导出...");
			_dom.attr(_btn,"disabled","true");
		}

		function _enableExportBtn(){
			var _btn = _dom.getById("exportMapConfirm");
			_dom.html(_btn,"导出");
			_dom.removeAttr(_btn,"disabled");
		}

		function _initTemplateContainer(){
			_templateContainer = Dialog({
				mutiDialog : true,
				mutiDialogSeed : "exportmaptemplate",
				mask : false,
				title : "模板设置",
				top : 200,
				left : 910,
				content : "<div id='maptemplateContianer' style='width:320px;height:150px;overflow:auto;'></div>",
				buttonDisplay : {
					'confirmButton' : false,
					'cancelButton' : false
				}
			}).show();
			var _templatesC = _dom.getById("maptemplateContianer"),
				_templates = [
				     {
				    	 img : "images/map/template_1.jpg",
				    	 name : "模板一"
				     },
				     {
				    	 img : "images/map/template_2.jpg",
				    	 name : "模板二"
				     },
				     {
				    	 img : "images/map/template_3.jpg",
				    	 name : "模板三"
				     }
				];
			for(var i=0,len=_templates.length;i<len;i++){
				var _templateBox = _dom.createElem("div"),
					_templateImg = _dom.createElem("div"),
					_templateTitle = _dom.createElem("div"),
					_template = _templates[i];
				_dom.addClass(_templateBox,"exportTemplateBox");
				_dom.addClass(_templateImg,"exportTemplateImg");
				_dom.addClass(_templateTitle,"exportTemplateTitle");
				if(_templateIndex==i){
					_dom.addClass(_templateBox,_templateSelected);
				}
				_templateImg.innerHTML = "<img src='"+_template.img+"'/>";
				_templateTitle.innerHTML = _template.name;
				_dom.append(_templateBox,_templateImg);
				_dom.append(_templateBox,_templateTitle);
				_dom.append(_templatesC,_templateBox);
				_templateBox.onmouseover = function(){
					_dom.addClass(this,_templateHoverClass);
				}
				_templateBox.onmouseout = function(){
					_dom.removeClass(this,_templateHoverClass);
				}
				_templateBox.onclick = function(){
					var _siblings = _dom.siblings(this),
						_index = _dom.getIndex(this);
					for(var i=0,len=_siblings.length;i<len;i++){
						_dom.removeClass(_siblings[i],_templateSelected);
					}
					if(!_dom.hasClass(this,_templateSelected)){
						_dom.getById("exportTemplateBox").innerHTML = "您选择的是'"+_templates[_index].name+"'";
						_dom.addClass(this,_templateSelected);
						_templateIndex = _index;
					}
				}
			}
		}

		function _displayResult(result){
			Dialog({
				mutiDialog : true,
				mutiDialogSeed : "exportMapResult",
				mask : false,
				title : "导出结果",
				top : 200,
				content : "<div id='mapExportResult' style='width:500px;height:400px'></div>",
				buttonDisplay : {
					'confirmButton' : false,
					'cancelButton' : false
				},
				closeCall : function(){
				}
			}).show();
			var _resultContainer = _dom.getById("mapExportResult");
			if(result){
				var _r = _json.Parse(result);
				if(_r.outPutUrl){
					_dom.html(_resultContainer,"<img src='"+_r.outPutUrl+"' style='width:500px;height:400px;'/>");
				}else{
					_dom.html(_resultContainer,"导出失败！");
				}
			}else{
				_dom.html(_resultContainer,"导出失败！");
			}
		}

		function _export(){
			var _legendArr = [];
			for(var i=0,len=_selectedLegendItem.length;i<len;i++){
				_legendArr.push(SymbolConfig[_selectedLegendItem[i]]);
			}

			var _currentExtent = _mapManager.getCurrentExtent();

			var _layerids = _mapManager.getAllLayersID();

			var _layeridsFiltered = [];

			for(var i=0,len=_layerids.length;i<len;i++){
				var _layer = LayerManager(_layerids[i]);
				if(_layer.isVisible()){
					_layeridsFiltered.push(_layerids[i]);
				}
			}

			var _params = {
				export_url : _urls.exportMap,
				export_title : _dom.getById("mapTitle").value,
				export_width : 1000,
				export_height : 800,
				export_templateIndex : _templateIndex,
				export_xMin : _currentExtent.minX,
				export_xMax : _currentExtent.maxX,
				export_yMin : _currentExtent.minY,
				export_yMax : _currentExtent.maxY,
				layerids : _layeridsFiltered,
				export_legends : _json.Stringify(_legendArr)
			};

			var _exportClass = ExportClass();
			_exportClass.setUrl(_urls.exportMapProxy);
			_exportClass.setParams(_params);
			_exportClass.exportMap(function(data){
				_displayResult(data);
				_enableExportBtn();
			});
		}

		function init(){
			_initContainer();
			_loadParamPage();
		}

		return api;
	}
});