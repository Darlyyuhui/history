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
            mapDiv: "",
            right: 10,//默认显示位置
            top: 35,//默认显示位置
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
                mapDiv: _conf.mapDiv,
				mutiDialog : true,
				mutiDialogSeed : "bookmarks",
				title:'快速导航',
				content:'<div id="bookmarksDIV" style="width:240px;"></div>',
				mask:false,
				zindex:10000,
				right: _conf.right,
				top: _conf.top,
				confirmButton :"添加",
				confirmButtonCall : _addBookMarkEvt,
				cancelButton : "清空",
				cancelButtonCall : _clearBookmarksEvt
			}).show();
			_container = $("#bookmarksDIV");
			var input = $('<div class="input-group" style="padding-left:16px;"><span class="input-group-addon">书签名称</span> <input type="text" class="form-control"  id="bookmark" placeholder="请输入书签名称"></div><br>');
			_container.append(input);
			_bookmarksBox = $("<ul id='BookmarksBox' style='margin-left:5px;'></ul>");
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
			var _name = $("#bookmark").val(),
			    _currentExtent = _mapManager.getCurrentExtent();
				_addBookMark(_name,_currentExtent);;
		}
		
		function _addBookMark(name,extent){
			if(!name){
				return;
			}
			var _item = $("<li><span style='float:left;margin-top: 4px;'>"+name+"</span><span class='label label-info' style='border-radius:5px;padding:10px 5px 10px 5px;text-align:center;font-size:14px;height:36px;float:right;'>删除</span></li>");
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
			_item.children("span").eq(1).click(function(){
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