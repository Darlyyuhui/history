MapFactory.Define("ItmsMap/Util/RightClick*",function(){
	var _isInitted = false,
		_menuID = "mapRightClickContextMenu",
		_menuContainer = $("#"+_menuID);
	return function(){
		var api = {
			init : init,
			add : add,
			show : show,
			hide : hide
		};

		var _menuContext = [];
		
		var _target = null;

		function init(src){
			_target = $("#"+src);

			_target[0].oncontextmenu = function(e){
				var e = e || window.event;
				show(e.clientX,e.clientY,e.offsetX,e.offsetY);
				return false;
			}
		}

		function add(label,func,object){
			_menuContext.push({label:label,func:func,object:object});
		}

		function show(x,y,offsetx,offsety){
			if(!_isInitted && _target){
				_t = _target[0];
				_t.onclick = function(){
					hide();
				}
				var _tChildren = _t.childNodes;
				for(var i=0,len = _tChildren.length;i<len;i++){
					_tChildren[i].onclick = function(){
						hide();
					}
				}
				_isInitted = true;
			}
			var _positionRelative = _getPosition(offsetx,offsety),
				_positionAbsolute = _getPosition(x,y);

			if(!_menuContainer[0]){
				_menuContainer = $("<ul id='"+_menuID+"'></ul>");
				$("body").append(_menuContainer);
			}
			_menuContainer.empty();
			for(var i=0,len=_menuContext.length;i<len;i++){
				_menuContainer.append("<li>"+_menuContext[i].label+"</li>");
			}
			_menuContainer.children().bind("click",function(){
				var _itemIndex = $(this).index(),
					_menuItem = _menuContext[_itemIndex];
				if(!_menuItem){
					return false;
				}
				var returnObject = {
					index : _itemIndex,
					label : _menuItem.label,
					positionR : _positionRelative,
					positionA : _positionAbsolute,
					target : this
				};
				if(_menuItem.object){
					returnObject["object"] = _menuItem.object;
				}
				_menuItem.func(returnObject);
				hide();
			});
			_menuContainer.css({
				left : _positionAbsolute.x,
				top : _positionAbsolute.y
			}).show();
		}

		function hide(){
			if(_menuContainer){
				_menuContainer.hide();
			}
		}

		/**
		 * 获取点击位置
		 */
		function _getPosition(x,y){
			// 位置判断
			var scrollLeft = Math.max(document.body.scrollLeft,document.documentElement.scrollLeft),
				scrollTop = Math.max(document.body.scrollTop,document.documentElement.scrollTop);
			return {
				x : scrollLeft + x,
				y : scrollTop + y
			};
		}

		return api;
	}
});