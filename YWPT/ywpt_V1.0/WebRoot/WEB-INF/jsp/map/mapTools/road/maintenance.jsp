<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- 总共000个路段，尚有184个路段没有道路名称。 -->
<div class="mar_5">
	<input id="selectRoad" type="button" class="btn btn-info" value="选择路段"/>
	<input id="unselectRoad" type="button" class="btn btn-info" value="取消"/>
	<input id="updateRoadInfo" type="button" class="btn btn-info" value="提交修改"/>
</div>
<!--
<button id="selectRoad">选择路段</button>
<button id="unselectRoad">取消</button>
<button id="updateRoadInfo">提交修改</button>
-->
<table id="roadResultTable" class="commonResultTable"></table>

<script type="text/javascript">
MapFactory.Require([
	"MapFactory/MapManager",
	"MapFactory/Query",
	"ItmsMap/MapConfig",
	"MapFactory/Draw",
	"MapFactory/GraphicManager",
	"MapFactory/LayerManager",
	"MapFactory/Buffer",
	"ItmsMap/Util/ModuleMessage*"
],function(MapManager,Query,MapConfig,Draw,GraphicManager,LayerManager,Buffer,ModuleMessage){
	var _mapManager = MapManager();
	var _dom = MapFactory.Dom;
	var _json = MapFactory.JSON;
	var _xhr = MapFactory.XHR;
	var _selectBtn = _dom.getById("selectRoad");
	var _unselectBtn = _dom.getById("unselectRoad");
	var _updateBtn = _dom.getById("updateRoadInfo");
	var _layers = MapConfig.layers;
	var _draw = Draw();
	var _resultTable = _dom.getById("roadResultTable");
	var _tbody = _dom.createElem("tbody");
	var _roadMaintenanceLayerId = "roadMaintenance";
	var _selectedIds = []; // 存储已经选择过的道路的ID
	var _drawStopped = false;
	var _changedItems = {};

	_selectBtn.onclick = startDraw;
	_unselectBtn.onclick = stopDraw;
	_updateBtn.onclick = updateInfo;
	_dom.attr(_updateBtn,"disabled","disabled");

	function init(){
		_changedItems = {};
		LayerManager(_roadMaintenanceLayerId).clear();
		_dom.empty(_tbody);
		_dom.empty(_resultTable);
		_dom.append(_resultTable,_tbody);
		_drawStopped = false;
		_selectedIds = [];
		_dom.attr(_updateBtn,"disabled","disabled");
	}

	function startDraw(){
		init();
		var _titleTr = _dom.createElem("tr");
		var _tdID = _dom.createElem("td");
		var _tdName = _dom.createElem("td");
		var _tdType = _dom.createElem("td");
		_dom.css(_tdName,"width","120px");
		_dom.html(_tdID,"ID");
		_dom.html(_tdName,"路名");
		_dom.html(_tdType,"类型");
		_dom.addClass(_titleTr,"commonResultTableTitleRow");
		_dom.append(_titleTr,_tdID);
		_dom.append(_titleTr,_tdName);
		_dom.append(_titleTr,_tdType);
		_dom.append(_tbody,_titleTr);
		_dom.attr(_selectBtn,"disabled","disabled");

		_draw = Draw();// 每次都初始化的原因：不重新初始化，打开关闭在打开编辑后回调函数会执行2次
		_draw.setGeoType("point");
		_draw.setDrawEndEvent(function(geometry){
			// 将点捕捉到路网上
			_getClosedPoint(geometry, function(closedPoint){
				// 缓冲5米在进行点的查询
				if(!closedPoint)return;
				var _buffer = Buffer();
				_buffer.setUrl(_layers.buffer.url);
				_buffer.setDistance([5]);//5米缓冲查询
				_buffer.setGeometry([closedPoint]);
				_buffer.execute(function(_arr){
					if(_arr && _arr.length) {
						_queryRoadByGeo(_arr[0]);
					}
				}, function(_err){});
			});
		});
		//_draw.openSnapping({
		//	urls : [_layers.baseRoad.url]
		//});
		_draw.activate();

		_mapManager.setMouseDownEvent(function(e){
			if(e.which == 3 || e.button == 2 && !_drawStopped){
				_drawStopped = true;
				_dom.removeAttr(_selectBtn,"disabled");
				//_draw.closeSnapping();
				_draw.deactivate();
			}
		});
	}
	
	function _queryRoadByGeo(geometry) {
		var _q = Query();
		_q.setUrl(_layers.baseRoad.url);
		_q.setGeometry(geometry);
		_q.setGeometryPrecision(10);
		_q.execute(function(data){
			if(!data || !data.length){
				return;
			}
			for(var i=0,len=data.length;i<len;i++){
				addItem(data[i]);
			}
		},function(){

		});
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

	function addItem(graphic){
		var _tr = _dom.createElem("tr");
		var _td_id = _dom.createElem("td");
		var _td_name = _dom.createElem("td");
		var _td_type = _dom.createElem("td");
		var _attr = graphic.attributes;
		for(var i in _selectedIds) {
			if(_selectedIds[i] == _attr.ID) {
				return;// 提出重复选择
			}
		}
		var _graphicMGM = GraphicManager(graphic);

		_selectedIds.push(_attr.ID);
		_dom.addClass(_tr,_selectedIds.length % 2 ? "commonResultTableOddRow" : "commonResultTableEvenRow");
		_td_id.innerHTML = _attr.id;
		_dom.html(_td_id,_attr.ID ? _attr.ID : _attr.OBJECTID);
		_dom.html(_td_name,_attr.NAME);
		_dom.html(_td_type,_attr.TYPE);
		_dom.append(_tr,_td_id);
		_dom.append(_tr,_td_name);
		_dom.append(_tr,_td_type);
		_dom.append(_tbody,_tr);

		_dom.css(_td_name,"width","120px");

		_graphicMGM.addToLayer(_roadMaintenanceLayerId);
		var _graphic = _graphicMGM.getGraphic();

		_td_name.onclick = tdNameClickEvt;
		_tr.onclick = trItemClickEvt;
		
		_dom.attr(_tr,"roadData",_json.Stringify(_graphic));
	}

	function tdNameClickEvt(e){
		if(!_drawStopped){
			stopDraw();
		}
		var _name = this.innerHTML;
		var _input = _dom.createElem("input");
		var _td = this;
		_dom.attr(_dom.parent(_td),"roadData");
		var _graphic = _json.Parse(_dom.attr(_dom.parent(_td),"roadData"));
		var _id = _graphic.id;
		_td.innerHTML = "";
		_dom.attr(_input,"value",_name);
		_dom.append(_td,_input);
		_dom.css(_input,"width","100px");
		_input.focus();
		_td.onclick = null;
		_input.onblur = function(){
			_name = _input.value;
			_td.onclick = tdNameClickEvt;
			_dom.remove(_input);
			_td.innerHTML = _name;
		}

		_input.onchange = function(){
			_graphic.attributes.NAME = _input.value;
			_changedItems[_id] = _graphic;
			_dom.removeAttr(_updateBtn,"disabled");
		}
	}

	function trItemClickEvt(){
		stopDraw();
		var _graphic = _json.Parse(_dom.attr(this,"roadData"));
		var _highlightGraphicMGM = GraphicManager(_graphic.id);
		_highlightGraphicMGM.clearAllHighlight();
		_highlightGraphicMGM.highlight();
	}

	function updateInfo(){
		_dom.attr(_updateBtn,"disabled","disabled");
		ModuleMessage.showMessage("正在提交...",ModuleMessage.LOG,10000);
		var _data = [];
		for(var elem in _changedItems){
			_data.push(_changedItems[elem]);
		}
		_xhr.Post("roadmaintenance/updateroadinfo/",{graphicList:_json.Stringify(_data)},function(){
			ModuleMessage.showMessage("提交成功！",ModuleMessage.SUCCESS,2000);
		});
	}
});
</script>