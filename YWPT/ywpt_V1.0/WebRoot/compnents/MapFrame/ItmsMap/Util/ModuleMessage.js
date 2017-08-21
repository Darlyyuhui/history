/**
 * 模块提示信息显示类
 * @author ZLT
 * @since 2014-5-16
 */
MapFactory.Define("ItmsMap/Util/ModuleMessage*",function(){

	var ERROR = 1,
		LOG = 2,
		SUCCESS = 3,
		WARNING = 4;

	var api = {
		ERROR : ERROR,
		LOG : LOG,
		SUCCESS : SUCCESS,
		WARNING : WARNING,
		showMessage : showMessage,
		hideMessage : hideMessage
	};

	var _timeHandler = null;

	/**
	 * 显示信息
	 * @param message String 信息文本
	 * @param type Constants 文本类型，参考静态变量，可选
	 * @param time Number 显示时长，可选
	 */
	function showMessage(message,type,time){
		var //_parentBox = $(".mapToolMenuContent:visible"),
			//_childBox = $(".mapToolSubMenuContent:visible"),
			_messageBox = null,
			_font = "",
			_color = "";
		/*if(_childBox[0]){
			_messageBox = _childBox.find(".mapModuleMessage");
		}else{
			_messageBox = _parentBox.find(".mapModuleMessage");
		}*/
		_messageBox = $(".mapModuleMessage");
		if(!_messageBox[0]){
			return;
		}
		switch(type){
			case ERROR : {
				_color = "red";
				break;
			}
			case LOG : {
				_color = "gray";
				break;
			}
			case SUCCESS : {
				_color = "green";
				break;
			}
			case WARNING : {
				_color = "tomato";
				break;
			}
			default : {
				_color = "gray";
				break;
			}
		}
		message = "<font color='"+_color+"'>"+message+"</font>";
		_messageBox.show().html(message);
		if(!time){
			time = 2000;
		}
		if(_timeHandler){
			clearTimeout(_timeHandler);
		}
		_timeHandler = setTimeout(function(){
			hideMessage();
		},time);
	}

	/**
	 * 隐藏提示信息
	 */
	function hideMessage(){
		$(".mapModuleMessage").hide();
	}

	return api;
});