MapFactory.Define("ItmsMap/UserLayers/ZBXQInfoWin*",["MapFactory/Util/Dialog*"],
		function(Dialog){
	var isinit=false;
	var _panel;
	return function(){
		var api={init:init,
				show:show};
		var _conf = {
				mapDiv : "map",
				left : 40,
				top : 70
		};
		
		var _left = 450;
		var _top = 70;
		//MapFactory.Extend(_conf, conf);
		var contentid = "ShowImageWin";
		function init() {
			if(!isinit){
				_panel = Dialog({
					mapDiv : _conf.mapDiv,
					mutiDialog : true,
					mutiDialogSeed : "zbxqwin",
					mask : false,
					title : '指标详情',
					left : _left,
					top : _top,
					buttonDisplay : {
						confirmButton : false,
						cancelButton : false
					}
				});
				var contentStr = "<div id='"
					+ contentid
					+ "' style='width:400px;max-height:500px;_height:expression(this.scrollHeight>500?\"500px\":\"auto\");overflow-y:auto;overflow-x:hidden;'><img id='imgInfoContent' style='margin-top:15px;margin-bottom:15px;margin-left:15px;width:100%;height:100%;'></img></div>";
				_panel.setDialogContent(contentStr).show();
				isinit=true;
			}
			$("#"+contentid).empty();
			
			$.ajax({
			      type:"post",
			      url:path+"/map/homeStatic/show/",
			      success:function(msg) {
			          $("#"+contentid).html(msg);
			      }
		    });
			show();
		}
		function show() {
			_panel.show();
			_panel.setPosition(_left,_top);
		}
		
		return api;
	}
});