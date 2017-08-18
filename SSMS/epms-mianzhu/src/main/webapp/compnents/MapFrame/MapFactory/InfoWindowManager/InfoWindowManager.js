MapFactory.Define("MapFactory/InfoWindowManager",[
	"MapFactory/InfoWindowManagerAPI*",
	"MapFactory/MapManager",
	"MapFactory/Util/Dialog*"
],function(api,MapManager,Dialog){
	var _d = null;
	var _closeCallBack = null;
	return function(conf){

		var _mapManager = MapManager();

		var _xhr = MapFactory.XHR,
			_dom = MapFactory.Dom;

		var _conf = {
			url : "",
			option : {},
			callback : "",
			anchor : "",
			title : "信息框",
			content : "",
			width : "",
			height : ""
		};

		MapFactory.Extend(_conf,conf);

		function setAnchor(point){
			_conf.anchor = point;
		}

		function setTitle(title){
			_conf.title = title;
		}

		function setContent(content){
			_conf.content = content;
		}

		function setLoadPage(url,option,callback){
			_conf.url = url;
			_conf.option = option;
			_conf.callback = callback;
		}

		function setWidth(width){
			_conf.width = width;
		}

		function setHeight(height){
			_conf.height = height;
		}
		
		function setCloseFunc(func){
			_closeCallBack = func;
		}

		function showAndCenterInfowindow(level){
			show();
		}

		function showAndCenter(level){
			show();
		}

		function show(){
			var contentBoxId = "infoWindowBaseBox";
			var content = "<div id='"+contentBoxId+"' ";
			var contentBox = null;
			if(_conf.width && _conf.height){
				content += "style='width:"+_conf.width+"px;height:"+_conf.height+"px;'";
			}
			content += "></div>";
			_d = Dialog({
				mutiDialog : true,
				mutiDialogSeed : "InfoWindow",
				isMove : false,
				title : _conf.title,
				right:10,
				top:35,
				content : content,
				mask : false,
				buttonDisplay : {
					'confirmButton' : false,
					'cancelButton' : false
				},
				closeCall: function(){
					//移动警务 info框消失前先移去ocx控件。
					var length  = $('#temp_video').children().length;
					if(length ==0 ){
						var v =  $('#object_video');
						$('#temp_video').append(v);
						P.cancelsomeone();
					}
					$("#infoWindowBaseBox").empty();
					if(_closeCallBack){
						_closeCallBack();
						_closeCallBack = null;
					}
				}
			}).show();
			contentBox = _dom.getById(contentBoxId);
			if(_conf.content){
				_dom.html(contentBox,_conf.content);
			}
			if(_conf.url){
				_xhr.Load(contentBoxId,_conf.url,_conf.option,function(data){
					if(_conf.width && _conf.height){
						_dom.css(contentBox,"width",_conf.width);
						_dom.css(contentBox,"height",_conf.height);
					}else{
						var style = _getInnerStyle(contentBox);
						_width = style.width;
						_height = style.height;
						_dom.css(contentBox,"width",_width);
						_dom.css(contentBox,"height",_height);
					}
					_d.setDialogContent(_dom.outerHTML(contentBox));
					if(_conf.callback){
						_conf.callback(data);
					}
				});
			}
			if(_conf.anchor){
				var _p = _conf.anchor.points.split(",");
				//_mapManager.setLevel(6,function(){
				//	_mapManager.centerAtWithOffset(_p[0],_p[1],200,0,function(){});
				//});
				_mapManager.centerAtWithOffset(_p[0],_p[1],_conf.width/2,0,function(){});
			}
		}

		// 初始化的时候执行一次
		if(_closeCallBack){
			_closeCallBack();
			_closeCallBack = null;
		}

		function hide(){
			if(_d){
				_d.hide();
				$("#infoWindowBaseBox").empty();
			}
		}

		function _getInnerStyle(elem){
			var _maxWidth = 0, _maxHeight = 0,
				_children = elem.childNodes,
				_num = _children.length;

			if(_num){
				if(elem.offsetWidth > _maxWidth){
					_maxWidth = elem.offsetWidth;
				}
				if(elem.offsetHeight > _maxHeight){
					_maxHeight = elem.offsetHeight;
				}
				for(var i=0;i<_num;i++){
					var _style = _getInnerStyle(_children[i]);
					if(_style.width > _maxWidth){
						_maxWidth = _style.width;
					}
					if(_style.height > _maxHeight){
						_maxHeight = _style.height;
					}
				}

				return {
					width : _maxWidth,
					height : _maxHeight
				};
			}else{
				_maxWidth = elem.offsetWidth || 0;
				_maxHeight = elem.offsetHeight || 0;
			}

			return {
				width : _maxWidth,
				height : _maxHeight
			};
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});