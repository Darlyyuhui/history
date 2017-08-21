/**
 * 统计表格
 * @author ZLT
 * @since 2014-5-26
 */
MapFactory.Define("ItmsMap/Util/StatisticTable*",function(){
	return function(conf){

		/**
		 * 配置信息
		 * @param src String 表格要填充的位置ID
		 * @param data Object 数据
		 * * * 数据格式：
		 * * * {
		 * * *  rowsTitle : [], 行标题
		 * * *  colsTitle : [], 列标题
		 * * *  data : [][], 数据体
		 * * *  rowsTitleAttributes : [], 行标题属性
		 * * *  colsTitleAttributes : [], 列标题属性
		 * * *  dataAttributes : [][] 数据属性
		 * * * }
		 * @param isSelectAll Boolean 初始化是否要全选
		 * @param rowEnableSelect Boolean 是否允许整行选择
		 * @param colEnableSelect Boolean 是否允许整列选择
		 * @param enableSelectAll Boolean 是否允许全选
		 * @param rowSelectUnique Boolean 是否只允许选一行
		 * @param colSelectUnique Boolean 是否只允许选一列
		 * @param celSelectUnique Boolean 是否只允许选一个单元格
		 * @param celClick Function 单元格点击事件
		 * @param rowClick Function 行点击事件
		 * @param colClick Function 列点击事件
		 * @param selectAllClick Function 全选事件
		 * @param rowHeight Array 行高度
		 * 
		 */
		var _conf = {
			src : "",
			data : "",
			isSelectAll : false,
			rowEnableSelect : true,
			colEnableSelect : true,
			enableSelectAll : true,
			rowSelectUnique : false,
			colSelectUnique : false,
			celSelectUnique : false,
			celClick : "",
			rowClick : "",
			colClick : "",
			selectAllClick : "",
			rowHeight : [],
			colWidth : []
		};

		var _tableContainer = null,
			_table = null,
			_tBody = null,
			_rowNum = 0,
			_colNum = 0,
			_hasRowTitle = false,
			_hasColTitle = false,
			_tdHover = 'tdHover',
			_tdSelected = 'tdSelected',
			_rowHover = 'rowHover',
			_rowSelected = 'rowSelected',
			_colSelected = 'colSelected',
			_dataAttributes = null,
			_rowsTitleAttributes = null,
			_colsTitleAttributes = null,
			_rowHeight = [],
			_colWidth = [];

		function _init(){
			MapFactory.Extend(_conf,conf);
			if(!_conf.src){
				return;
			}
			if(!_conf.data){
				return;
			}
			_tableContainer = document.getElementById(_conf.src);
			if(!_tableContainer){
				return;
			}
			if(_conf.rowHeight.length){
				_rowHeight = _conf.rowHeight;
			}
			if(_conf.colWidth.length){
				_colWidth = _conf.colWidth;
			}
			_table = document.createElement("table");
			_table.className = "commonResultTable commonActiveTable";
			_tableContainer.appendChild(_table);
			_tBody = document.createElement("tbody");
			_table.appendChild(_tBody);
			if(_conf.data.rowsTitle){
				_hasRowTitle = true;
				if(_conf.data.rowsTitleAttributes){
					_rowsTitleAttributes = _conf.data.rowsTitleAttributes;
				}
			}
			if(_conf.data.colsTitle){
				_hasColTitle = true;
				if(_conf.data.colsTitleAttributes){
					_colsTitleAttributes = _conf.data.colsTitleAttributes;
				}
				_addRow(_conf.data.colsTitle,true);
			}
			if(_conf.data.data){
				if(_hasRowTitle){
					_addData(_conf.data.data,_conf.data.rowsTitle);
				}else{
					_addData(_conf.data.data);
				}
				if(_conf.data.dataAttributes){
					_dataAttributes = _conf.data.dataAttributes;
				}
			}
		}
		_init();

		function _addData(data,rowTitles){
			for(var i=0,rowLen=data.length;i<rowLen;i++){
				var _rowData = [];
				if(rowTitles){
					_rowData.push(rowTitles[i]);
				}
				_rowData = _rowData.concat(data[i]);
				_addRow(_rowData,false);
			}
		}

		function _addRow(rowData,isTitle){
			var _row = document.createElement("tr"),
				_rowNum = _tBody.childNodes.length;

			if(isTitle){
				_addClass(_row,"commonResultTableTitleRow");
			}else if(_rowNum%2){
				_addClass(_row,"commonResultTableEvenRow");
			}else{
				_addClass(_row,"commonResultTableOddRow");
			}
			_tBody.appendChild(_row);
			for(var i=0,len=rowData.length;i<len;i++){
				var _td = document.createElement("td");
				_td.innerHTML = rowData[i];
				if(_colWidth.length && _colWidth.length > i){
					_td.width = _colWidth[i];
				}
				if(_rowHeight.length && _rowHeight.length > (_rowNum - 1)){
					_td.height = _rowHeight[_rowNum-1];
				}
				_row.appendChild(_td);
				if(_hasRowTitle && i==0 && !isTitle){
					if(_conf.isSelectAll){
						_addClass(_td.parentElement,_rowSelected);
					}
					if(_conf.rowEnableSelect){
						_bindRowEvt(_td);
					}
					continue;
				}
				if(isTitle){
					if(_conf.colEnableSelect){
						if(_hasColTitle && i==0){
							if(_hasRowTitle){
								_td.setAttribute("isSelectedAll",_conf.isSelectAll);
								_bindSelectAll(_td);
							}
							continue;
						}
						if(_conf.isSelectAll){
							_addClass(_td,_colSelected);
						}
						_bindColEvt(_td);
					}
					continue;
				}
				if(_conf.isSelectAll){
					_addClass(_td,_tdSelected);
				}
				_bindTdEvt(_td);
			}
		}

		function _bindTdEvt(td){
			_bindEvt(td,"mouseover",(function(_td){
				return function(){
					_addClass(_td,_tdHover);
				}
			})(td));
			_bindEvt(td,"mouseout",(function(_td){
				return function(){
					_removeClass(_td,_tdHover);
				}
			})(td));
			_bindEvt(td,"click",(function(_td){
				return function(){
					var _className = _td.className,
						_isSelected = false;
					if(!_className || !_className.match(_tdSelected)){
						if(_conf.celSelectUnique){
							_selectAll(false);
						}
						_addClass(_td,_tdSelected);
					}else{
						_isSelected = true;
						_removeClass(_td,_tdSelected);
					}
					if(_conf.celClick){
						_tdClickCallback(_td,_isSelected,_conf.celClick);
					}
				}
			})(td));
		}

		function _bindRowEvt(td){
			_bindEvt(td,"mouseover",(function(_td){
				return function(){
					_addClass(_td.parentElement,_rowHover);
				}
			})(td));
			_bindEvt(td,"mouseout",(function(_td){
				return function(){
					_removeClass(_td.parentElement,_rowHover);
				}
			})(td));
			_bindEvt(td,"click",(function(_td){
				return function(){

					var _parentElement = _td.parentElement,
						_className = _parentElement.className,
						_isSelected = false,
						_elem = _td.nextSibling;
					if(!_className || !_className.match(_rowSelected)){
						if(_conf.rowSelectUnique){
							_selectAll(false);
						}
						_addClass(_td.parentElement,_rowSelected);
						while(_elem){
							_addClass(_elem,_tdSelected);
							_elem = _elem.nextSibling;
						}
					}else{
						_isSelected = true;
						_removeClass(_td.parentElement,_rowSelected);
						while(_elem){
							_removeClass(_elem,_tdSelected);
							_elem = _elem.nextSibling;
						}
					}
					
					if(_conf.rowClick){
						_tdClickCallback(_td,_isSelected,_conf.rowClick,true,false);
					}
				}
			})(td));
		}

		function _bindColEvt(td){
			_bindEvt(td,"mouseover",(function(_td){
				return function(){
					var _index = _getIndex(_td),
						_trs = _tBody.childNodes;
					for(var i=0,len=_trs.length;i<len;i++){
						var _child = _trs[i].childNodes[_index];
						_addClass(_child,_tdHover);
					}
				}
			})(td));
			_bindEvt(td,"mouseout",(function(_td){
				return function(){
					var _index = _getIndex(_td),
						_trs = _tBody.childNodes;
					for(var i=0,len=_trs.length;i<len;i++){
						var _child = _trs[i].childNodes[_index];
						_removeClass(_child,_tdHover);
					}
				}
			})(td));
			_bindEvt(td,"click",(function(_td){
				return function(){

					var _index = _getIndex(_td),
						_trs = _tBody.childNodes,
						_isSelected = false;
					if(_td.className.match(_colSelected)){
						_isSelected = true;
						_removeClass(_td,_colSelected);
					}else{
						if(_conf.colSelectUnique){
							_selectAll(false);
						}
						_addClass(_td,_colSelected);
					}
					for(var i=1,len=_trs.length;i<len;i++){
						var _child = _trs[i].childNodes[_index];
						if(_isSelected){
							_removeClass(_child,_tdSelected);
						}else{
							_addClass(_child,_tdSelected);
						}
					}
					if(_conf.colClick){
						_tdClickCallback(_td,_isSelected,_conf.colClick,false,true);
					}
				}
			})(td));
		}

		function _tdClickCallback(td,isSelected,callback,isRowTitle,isColTitle){
			var _row = _getIndex(td.parentElement),
				_col = _getIndex(td),
				_attrRow = _row,
				_attrCol = _col,
				_attr = null;
			if(_hasRowTitle){
				_attrRow -= 1;
			}
			if(_hasColTitle){
				_attrCol -= 1;
			}
			if(isRowTitle && _rowsTitleAttributes){
				if(_hasColTitle){
					_attr = _rowsTitleAttributes[_attrRow];
				}else{
					_attr = _rowsTitleAttributes[_row];
				}
			}
			if(isColTitle && _colsTitleAttributes){
				if(_hasRowTitle){
					_attr = _colsTitleAttributes[_attrCol];
				}else{
					_attr = _colsTitleAttributes[_col];
				}
			}
			if(!isRowTitle && !isColTitle && !_attr && _dataAttributes && _dataAttributes[_attrRow] && _dataAttributes[_attrRow][_attrCol]){
				_attr = _dataAttributes[_attrRow][_attrCol];
			}
			callback({
				isSelected : isSelected,
				row : _row,
				col : _col,
				attributes : _attr
			});
		}

		function _bindSelectAll(td){
			if(!_conf.enableSelectAll){
				return;
			}
			_bindEvt(td,"click",(function(_td){
				return function(){
					var _isSelectedAll = _td.getAttribute("isSelectedAll"),
						_isSelected = (_isSelectedAll == "true" ? true : false);
					_selectAll(!_isSelected);
					if(_isSelectedAll == "true"){
						_td.setAttribute("isSelectedAll",false);
					}else{
						_td.setAttribute("isSelectedAll",true);
					}
					if(_conf.selectAllClick){
						_conf.selectAllClick({
							isSelected : _isSelected
						});
					}
				}
			})(td));
		}

		function _selectAll(isSelect){
			var _elem = _tBody.firstChild,
				_rowIndex = 0;
			while(_elem){
				var _tdElem = _elem.firstChild,
					_colIndex = 0;
				while(_tdElem){
					if(isSelect){
						if(_rowIndex == 0 && _hasColTitle){
							_addClass(_tdElem,_colSelected);
						}else{
							if((_colIndex != 0 && _hasRowTitle) || !_hasRowTitle){
								_addClass(_tdElem,_tdSelected);
							}
						}
					}else{
						if(_rowIndex == 0 && _hasColTitle){
							_removeClass(_tdElem,_colSelected);
						}else{
							if((_colIndex != 0 && _hasRowTitle) || !_hasRowTitle){
								_removeClass(_tdElem,_tdSelected);
							}
						}
					}
					_colIndex++;
					_tdElem = _tdElem.nextSibling;
				}
				if(isSelect){
					_addClass(_elem,_rowSelected);
				}else{
					_removeClass(_elem,_rowSelected);
				}
				_rowIndex++;
				_elem = _elem.nextSibling;
			}
		}

		function _bindEvt(elem,type,fn){
			if(elem.addEventListener){
				elem.addEventListener(type,fn,false);
			}else if(elem.attachEvent){
				elem.attachEvent("on"+type,fn);
			}
		}

		function _getIndex(elem){
			var _index = 0;
			while(elem.previousSibling){
				elem = elem.previousSibling;
				_index++;
			}
			return _index;
		}

		function _addClass(elem,className){
			var _trim = MapFactory.String.trim,
				_className = _trim(elem.className);
			if(!_className){
				_className = "";
			}
			if(!_className.match(className)){
				elem.className = _trim(_className + " " + className);
			}
		}

		function _removeClass(elem,className){
			var _trim = MapFactory.String.trim,
				_className = _trim(elem.className);
			if(_className.match(className)){
				elem.className = _trim(_className.replace(className,""));
			}
		}

	}
});