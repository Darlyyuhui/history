<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="mar_5">
	<input id="drawTmpPoint" type="button" class="btn btn-info" value="标注点"/>
	<input id="undoTmpPoint" type="button" class="btn btn-info" value="停止"/>
	<input id="addTmpPoint" type="button" class="btn btn-info" value="保存"/>
	<input id="queryTmpPoint" type="button" class="btn btn-info" value="查询"/>
</div>
<table id="roadResultTable" class="commonResultTable"></table>
<div id="tmpResultC"></div>

<script type="text/javascript">
MapFactory.Require([
	"MapFactory/MapManager",
	"MapFactory/Query",
	"ItmsMap/MapConfig",
	"MapFactory/Draw",
	"MapFactory/Geometry*",
	"MapFactory/GraphicManager",
	"MapFactory/LayerManager",
	"MapFactory/Buffer",
	"MapFactory/InfoWindowManager",
	"ItmsMap/Util/ModuleMessage*",
	"ItmsMap/Util/Pager*",
	"ItmsMap/SymbolConfig*"
],function(MapManager,Query,MapConfig,Draw,Geometry,GraphicManager,LayerManager,Buffer,InfoWindow,ModuleMessage,Pager,SymbolConfig){
	var _mapManager = MapManager();
	var _dom = MapFactory.Dom;
	var _json = MapFactory.JSON;
	var _xhr = MapFactory.XHR;
	var _selectBtn = _dom.getById("drawTmpPoint");
	var _unselectBtn = _dom.getById("undoTmpPoint");
	var _updateBtn = _dom.getById("addTmpPoint");
	var _queryBtn = _dom.getById("queryTmpPoint");
	var _layers = MapConfig.layers;
	var _draw = Draw();
	var _resultTable = _dom.getById("roadResultTable");
	var _tbody = _dom.createElem("tbody");
	var _tmpLayerId = "tmpLayer";
	var _tmpLabelLayerId = "tmpLabelLayer";
	var _selectedIds = []; // 存储已经选择过的道路的ID
	var _drawStopped = false;
	var _changedItems = {};
	var _pointsGid = {};// 存放地图上点对应的Graphic的id

	_selectBtn.onclick = startDraw;
	_unselectBtn.onclick = stopDraw;
	_updateBtn.onclick = updateInfo;
	_queryBtn.onclick = queryInfo;
	_dom.attr(_updateBtn,"disabled","disabled");

	function init(){
		_changedItems = {};
		LayerManager("graphicHighlightLayer");
		if(!_mapManager.isLayerExist(_tmpLayerId)) {
			console.log(1);
			var layer = LayerManager(_tmpLayerId);
			layer.addOnClickEvent(function(e) {
				showTmpPoint(e.graphic);
			});
		}
		else {
			LayerManager(_tmpLayerId);
		}
		LayerManager(_tmpLayerId).clear();
		LayerManager(_tmpLabelLayerId).clear();
		_dom.empty(_tbody);
		_dom.empty(_resultTable);
		_dom.append(_resultTable,_tbody);
		_drawStopped = false;
		_selectedIds = [];
		_dom.attr(_updateBtn,"disabled","disabled");
		
		// 初始化的时候加载列表头
		var _titleTr = _dom.createElem("tr");
		var _tdName = _dom.createElem("td");
		var _tdCode = _dom.createElem("td");
		var _tdOp = _dom.createElem("td");
		_dom.css(_tdName,"width","100px");
		_dom.css(_tdCode,"width","70px");
		_dom.html(_tdName,"名称");
		_dom.html(_tdCode,"编号");
		_dom.html(_tdOp,"操作");
		_dom.addClass(_titleTr,"commonResultTableTitleRow");
		_dom.append(_titleTr,_tdName);
		_dom.append(_titleTr,_tdCode);
		_dom.append(_titleTr,_tdOp);
		_dom.append(_tbody,_titleTr);
	}
	init();
	
	function showTmpPoint(g) {
		console.log(g);
		var att = g.attributes;
		if(!att || "undefined"==typeof att.NAME) {
			InfoWindow().hide();
			LayerManager("graphicHighlightLayer").clear();
			return;
		}
		var info = InfoWindow();
		info.setWidth(300);
		info.setHeight(60);
		info.setAnchor(new Geometry(g.geo));
		info.setTitle("信息框");
		info.setContent('<table class="commonResultTable"><tr class="commonResultTableTitleRow"><td>名称</td><td>编码</td></tr><tr class="commonResultTableOddRow"><td>'+att.NAME+'</td><td>'+att.CODE+'</td></tr></table>');
		info.showAndCenterInfowindow(3);
		
		var _geoId = _pointsGid[att.ID];
		if(_geoId) {
			var _highlightGraphicMGM = GraphicManager(_geoId);
			_highlightGraphicMGM.clearAllHighlight();
			_highlightGraphicMGM.highlight();
		}
	}

	function startDraw(){
		_draw = Draw();// 每次都初始化的原因：不重新初始化，打开关闭在打开编辑后回调函数会执行2次
		_draw.setGeoType("point");
		_draw.setDrawEndEvent(function(geometry){
			// 将点捕捉到路网上
			_getClosedPoint(geometry, function(closedPoint){
				if(!closedPoint)return;
				addItem(closedPoint);
			});
		});
		_draw.activate();
	}
	
	var _closedQuery;
	// 异步查询获取路网上对应的投影点
	function _getClosedPoint(point, callback) {
		if(!_closedQuery) {
			_closedQuery = Query();
			_closedQuery.setUrl(_layers.closedpoint.url);
			_closedQuery.setSpatialRelationShip("closedpoint");
		}
		_closedQuery.setGeometry(point);
		_closedQuery.execute(function(result) {
			if(result && result.length) {
				callback(result[0].geo);
			}
			else {
				callback("");
			}
		}, function(error) {
			callback("");
		});
	}

	function stopDraw(){
		_selectedIds = [];
		_drawStopped = true;
		_dom.removeAttr(_selectBtn,"disabled");
		//_draw.closeSnapping();
		_draw.deactivate();
	}

	var i = 0;
	function addItem(geo){
		var _tr = _dom.createElem("tr");
		var _td_name = _dom.createElem("td");
		var _td_code = _dom.createElem("td");
		var _td_op = _dom.createElem("td");
		var _graphicMGM = GraphicManager({"geo": geo, "symbol": {url:"images/map/i_pin2.png", width:24, height:24, yOffset:8}});
		i++;

		_dom.addClass(_tr,i % 2 ? "commonResultTableOddRow" : "commonResultTableEvenRow");
		var _td_op_position = _dom.createElem("a");
		var _td_op_del = _dom.createElem("a");
		_dom.html(_td_op_position,"定位  ");
		_dom.html(_td_op_del,"删除");
		_dom.append(_td_op,_td_op_position);
		_dom.append(_td_op,_td_op_del);
		_dom.css(_td_op_position,"cursor","pointer");
		_dom.css(_td_op_del,"cursor","pointer");
		
		_dom.append(_tr,_td_name);
		_dom.append(_tr,_td_code);
		_dom.append(_tr,_td_op);
		_dom.append(_tbody,_tr);

		_dom.css(_td_name,"width","100px");
		_dom.css(_td_code,"width","70px");

		_graphicMGM.addToLayer(_tmpLayerId);

		_td_name.onclick = tdNameClickEvt;
		_td_code.onclick = tdCodeClickEvt;
		_td_op_position.onclick = postionItem;
		_td_op_del.onclick = delItem;
		
		_dom.attr(_tr,"geoData",_json.Stringify(geo));
		_dom.attr(_tr,"geoId", _graphicMGM.getGraphic().id);
	}

	function tdNameClickEvt(e){
		if(!_drawStopped){
			stopDraw();
		}
		var _name = this.innerHTML;
		var _input = _dom.createElem("input");
		var _td = this;
		var _tr = _dom.parent(_td);
		var _geoId = _dom.attr(_tr,"geoId");
		var _graphicMGM = GraphicManager(_geoId);
		var _geo = _graphicMGM.getGraphic().geo;
		
		var _td_w = _dom.css(_td,"width");
		var _input_w = parseInt(_td_w) - 20;
		_td.innerHTML = "";
		_dom.attr(_input,"value",_name);
		_dom.append(_td,_input);
		_dom.css(_input,"width", _input_w+"px");
		_input.focus();
		_td.onclick = null;
		_input.onblur = function(){
			_name = _input.value;
			_td.onclick = tdNameClickEvt;
			_dom.remove(_input);
			_td.innerHTML = _name;
			var _geoId = _dom.attr(_td,"geoId");
			if(_geoId)GraphicManager(_geoId).remove();
			if(_name) {
				var color="#000000";
				var _graphicLayerMGM = GraphicManager({"geo":_geo, "symbol": {textFontFamily:"黑体",textStyle:"normal",textWeight:"bold",textColor:color,text:_name,size:10,xOffset:0,yOffset:28}});
				_graphicLayerMGM.addToLayer(_tmpLabelLayerId);
				_geoId = _graphicLayerMGM.getGraphic().id;
				_dom.attr(_td,"geoId",_geoId);
			}
		}

		_input.onchange = function(){
			_dom.removeAttr(_updateBtn,"disabled");
		}
	}
	
	function tdCodeClickEvt(e){
		if(!_drawStopped){
			stopDraw();
		}
		var _name = this.innerHTML;
		var _input = _dom.createElem("input");
		var _td = this;
		var _tr = _dom.parent(_td);
		
		var _td_w = _dom.css(_td,"width");
		var _input_w = parseInt(_td_w) - 20;
		_td.innerHTML = "";
		_dom.attr(_input,"value",_name);
		_dom.append(_td,_input);
		_dom.css(_input,"width", _input_w+"px");
		_input.focus();
		_td.onclick = null;
		_input.onblur = function(){
			_name = _input.value;
			_td.onclick = tdCodeClickEvt;
			_dom.remove(_input);
			_td.innerHTML = _name;
		}

		_input.onchange = function(){
			_dom.removeAttr(_updateBtn,"disabled");
		}
	}

	function postionItem(){
		stopDraw();
		var _tr = _dom.parent(_dom.parent(this));
		var _geoId = _dom.attr(_tr,"geoId");
		//定位该点
		var _highlightGraphicMGM = GraphicManager(_geoId);
		_highlightGraphicMGM.clearAllHighlight();
		_highlightGraphicMGM.highlight();
	}
	
	function delItem(){
		// 需要重新绘制table或者重新设置table中tr的样式
		stopDraw();
		var _tr = _dom.parent(_dom.parent(this));
		var _geoId = _dom.attr(_tr,"geoId");
		var _tr_1 = _dom.children(_tr)[0];
		var _tr_1_geoId = _dom.attr(_tr_1,"geoId");
		if(_tr_1_geoId)GraphicManager(_tr_1_geoId).remove();
		var _highlightGraphicMGM = GraphicManager(_geoId);
		_highlightGraphicMGM.remove();
		_highlightGraphicMGM.clearAllHighlight();
		_dom.remove(_tr);
		//重新给table添加样式
		var tableTrs = _dom.children(_dom.children(_resultTable)[0]);
		var _tr;
		for(var i=1,len=tableTrs.length; i<len; i++) {
			_tr = tableTrs[i];
			_dom.removeClass(_tr, "commonResultTableOddRow");
			_dom.removeClass(_tr, "commonResultTableEvenRow");
			_dom.addClass(_tr,i % 2 ? "commonResultTableOddRow" : "commonResultTableEvenRow");
		}
	}
	
	function queryInfo() {
		// 执行查询之前，先清空图层，并关闭绘制
		stopDraw();
		LayerManager(_tmpLayerId).clear();
		LayerManager(_tmpLabelLayerId).clear();
		LayerManager("graphicHighlightLayer").clear();
		
		_dom.attr(_queryBtn,"disabled","disabled");
		ModuleMessage.showMessage("正在查询...",ModuleMessage.LOG,10000);
		_xhr.Post("openmap/query/gettmp/",{type:"point"},function(result){
			ModuleMessage.hideMessage();
			_dom.removeAttr(_queryBtn,"disabled");
			
			for(var i=0,len=result.length; i<len; i++) {
				// 添加要素到地图上
				var _graphicMGM = GraphicManager({"geo": result[i].geo, "symbol": {url:"images/map/i_pin2.png", width:24, height:24, yOffset:8}, "attributes": result[i].attributes});
				_graphicMGM.addToLayer(_tmpLayerId);
				_pointsGid[result[i].attributes.ID] = _graphicMGM.getGraphic().id;
			}
		});
	}
	
	function updateInfo(){
		_dom.attr(_updateBtn,"disabled","disabled");
		ModuleMessage.showMessage("正在提交...",ModuleMessage.LOG,10000);
		var _data = [];
		var tableTrs = _dom.children(_dom.children(_resultTable)[0]);
		for(var i=1,len=tableTrs.length; i<len; i++) {
			var _tr = tableTrs[i];
			var _geoPoint = _dom.attr(_tr,"geoData");
			var _td_1 = _dom.children(_tr)[0];
			var _td_2 = _dom.children(_tr)[1];
			var _attName = _td_1.innerHTML;
			var _attCode = _td_2.innerHTML;
			_data.push({"geo":_json.Parse(_geoPoint), "attributes":{"NAME":_attName,"CODE":_attCode}});
		}
		_xhr.Post("openmap/query/addtmp/",{tmps:_json.Stringify(_data), type:"point"},function(){
			_dom.removeAttr(_updateBtn,"disabled");
			ModuleMessage.showMessage("提交成功！",ModuleMessage.SUCCESS,2000);
		});
	}
});
</script>