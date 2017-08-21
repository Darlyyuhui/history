/**
 * 书签工具
 * @author ZLT
 * @since 2014-5-13
 */
MapFactory.Define("MapFactory/BookMark*",[
    "MapFactory/Util/Dialog*",
    "MapFactory/MapManager"
],function(Dialog,MapManager){
	var _dialog = null;
	return function(conf){
		var _container = null,
			_bookmarksBox = null,
			_isEdit = false,
			_mapManager = MapManager(),
			_itemList = [],
			_cookieName = "BookMarkExtentJSON";

		var _conf = {
			items : []
		};

		var api = {
			/**
			 * 显示书签
			 */
			show : show,

			/**
			 * 隐藏书签
			 */
			hide : hide
		};

		MapFactory.Extend(_conf,conf);

		function _initContainer(){
			_dialog = Dialog({
				mutiDialog : true,
				mutiDialogSeed : "bookmarks",
				title:'快速导航',
				content:'<div id="bookmarksDIV" style="width:240px;"></div>',
				mask:false,
				zindex:10000,
				right:10,
				top:35,
				confirmButton :"添加",
				confirmButtonCall : _addBookMarkEvt,
				cancelButton : "清空",
				cancelButtonCall : _clearBookmarksEvt
			}).show();
			_container = $("#bookmarksDIV");
			_bookmarksBox = $("<ul id='BookmarksBox'></ul>");
			_container.append(_bookmarksBox);
		}

		function _init(){
			var _cookieVal = MapFactory.Cookie.Get(_cookieName);
			if(_cookieVal.length){
				var _items = MapFactory.JSON.Parse(_cookieVal);
				for(var i=0,len=_items.length;i<len;i++){
					_addBookMark(_items[i].name,_items[i].extent);
				}
			}
		}

		function _addBookMarkEvt(){
			if(_isEdit){
				return;
			}
			var input = $("<input type='text'/>");
			_bookmarksBox.append(input);
			input[0].focus();
			input.blur(function(){
				var _name = $(this).val(),
					_currentExtent = _mapManager.getCurrentExtent();
				_addBookMark(_name,_currentExtent);
				$(this).remove();
				_isEdit = false;
			});
			_isEdit = true;
		}
		
		function _addBookMark(name,extent){
			if(!name){
				return;
			}
			var _item = $("<li><span style='float:right;'>删除</span><span style='float:left;'>"+name+"</span></li>");
			var _currentExtent = _mapManager.getCurrentExtent();
			_bookmarksBox.append(_item);
			_itemList.push({name:name,extent:extent});
			MapFactory.Cookie.Set(_cookieName,MapFactory.JSON.Stringify(_itemList),60*60*24);
			_item.click(function(){
				var _curItem = _itemList[$(this).index()];
				if(_curItem){
					_mapManager.setExtent(_curItem.extent);
				}
			}).mouseenter(function(){
				$(this).addClass("BookmarkMouseEnter");
			}).mouseleave(function(){
				$(this).removeClass("BookmarkMouseEnter");
			});
			_item.children("span").eq(0).click(function(){
				var _itemParent = $(this).parent(),
					_index = _itemParent.index();
				_delBookMark(_index);
				_itemParent.remove();
				return false;
			});
		}

		function _clearBookmarksEvt(){
			_itemList.length = 0;
			_bookmarksBox.empty();
			MapFactory.Cookie.Del(_cookieName);
		}

		function _delBookMark(index){
			var _newList = [];
			for(var i=0,len=_itemList.length;i<len;i++){
				if(index != i){
					_newList.push(_itemList[i]);
				}
			}
			_itemList = _newList;
			if(_itemList.length){
				MapFactory.Cookie.Set(_cookieName,MapFactory.JSON.Stringify(_itemList),60*60*24);
			}else{
				MapFactory.Cookie.Del(_cookieName);
			}
		}

		function show(){
			if(!_dialog){
				_initContainer();
				_init();
				if(_conf.items.length && !MapFactory.Cookie.Get(_cookieName)){
					var _items = _conf.items,
						_len = _items.length;
					for(var i=0;i<_len;i++){
						_addBookMark(_items[i].name,_items[i].extent);
					}
				}
			}else{
				_dialog.show();
			}
		}

		function hide(){
			if(_dialog){
				_dialog.hide();
			}
		}
		return api;
	}
});