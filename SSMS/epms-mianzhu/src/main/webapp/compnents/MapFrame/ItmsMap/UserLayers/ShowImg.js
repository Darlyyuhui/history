MapFactory.Define("ItmsMap/UserLayers/ShowImg*",["MapFactory/Util/Dialog*"],
		function(Dialog){
	var isinit=false;
	var _panel;
	return function(){
		var api={init:init,
				show:show};
		var _conf = {
				mapDiv : "map",
				left : 40,
				top : 50
		};
		
		var _left = pageWidth/2-300;
		var _top = 30;
		//MapFactory.Extend(_conf, conf);
		var contentid = "ShowImageWin";
		function init() {
			if(isinit){
				return;
			}
			_panel = Dialog({
				mapDiv : _conf.mapDiv,
				mutiDialog : true,
				mutiDialogSeed : "ShowImg",
				mask : false,
				title : '图片信息',
				left : _conf.left,
				top : _conf.top,
				buttonDisplay : {
					confirmButton : false,
					cancelButton : false
				}
			});
			var contentStr = "<div id='"
				+ contentid
				+ "' style='width:600px;max-height:500px;_height:expression(this.scrollHeight>500?\"500px\":\"auto\");overflow-y:auto;overflow-x:hidden;'><img id='imgInfoContent' style='margin-top:15px;margin-bottom:15px;margin-left:15px;width:100%;height:100%;'></img></div>";
			_panel.setDialogContent(contentStr).show();
			isinit=true;
		}
		function show(url) {
			$("#imgInfoContent").attr('src',url);
			
			_panel.show();
			_panel.setPosition(_left,_top);
		}
		return api;
	}
});