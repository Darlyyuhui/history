MapFactory.Define("ItmsMap/Video/VideoDahua*",[
	"MapFactory/MapManager",
	"MapFactory/Geometry*",
	"MapFactory/GraphicManager",
	"MapFactory/LayerManager",
	"MapFactory/InfoWindowManager",
	"MapFactory/MeasureMent",
	"MapFactory/Util/Dialog*",
	"ItmsMap/SymbolConfig*",
	"ItmsMap/Util/ResultBox*",
	"ItmsMap/Util/ResItemO*",
	"ItmsMap/Util/Pager*",
	"ItmsMap/Util/PositionManager*",
	"ItmsMap/Util/ModuleMessage*",
	"ItmsMap/Util/Tip*",
	"ItmsMap/Util/MouseOverTip*"
],function(MapManager,Geometry,GraphicManager,LayerManager,InfoWindow,MeasureMent,Dialog,symbolConfig,resultBox,resItem,Pager,PositionManager,ModuleMessage,Tip,MouseOverTip){
	var _isInit=false, positionManager, dahuaList, dahuaLogin;
	return function(){
		var videoLayer, map = MapManager(), mm = MeasureMent();
		
		var api = {
			init: _init,//初始化
			videoLocation: videoLocation,
			getVideoDevice : getVideoDevice// 根据视频设备code，坐标信息，弹出卡口信息框
		};
		
		var tip = Tip();
		var moTip = MouseOverTip();
		//latitude="34.265685" longitude="108.953567"百度坐标
		//108.94245 34.26102实际坐标
		var baiduX = 108.94245 - 108.953567;
		var baiduY = 34.26102 - 34.265685;
		
		var videoWidth = 100, videoHeight = 100;
		
		// 初始化方法，只执行一次
		function _init() {
			if(dahuaList && dahuaList.length) {
				showGraphics();
				return;
			}
			_videoDahuaPlayer = Dialog({
				mutiDialog : true,
				mask : false,
				isMove : false,
				title : "无线视频播放器",
				content : '<div id="dahuamapDialog" style="width:500px;height:480px;">11111111111</div>',
				buttonDisplay : {
					'confirmButton' : false,
					'cancelButton' : false
				},
				right:10,
				top:35
			});
			_videoDahuaPlayer.show();
			MapFactory.XHR.Load("dahuamapDialog","forward/map/mapTools/video/dialogDaHua2/");
			_videoDahuaPlayer.hide();
			
			_isInit = true;
			positionManager = PositionManager();
			
			ModuleMessage.showMessage("正在查询，请稍后...",ModuleMessage.LOG,99999);
			// 同步获取大华用户名密码
			setTimeout(function(){
			$.ajax({
				url: "map/video/videointernet/",
				type: "POST",
				async: false,
				success: function(result) {
					if(result) {
						dahuaLogin = result;
						ModuleMessage.showMessage("正在登录大华平台，请稍后...",ModuleMessage.LOG,99999);
						_initDH();
						_login(result.ip, result.port, result.username, result.password);
						ModuleMessage.showMessage("正在获取大华数据，请稍后...",ModuleMessage.LOG,99999);
						_loadData();
						_loadDataByAjax();
						ModuleMessage.hideMessage();
					}
					else {
						// 获取大华登录账户信息出错
						ModuleMessage.showMessage("获取大华用户名密码时出错！", ModuleMessage.ERROR);
					}
				}
			});
			}, 1000);
		}
		
		// 信息框总大小500×480  视频播放框object大小500×300
		function getVideoDevice(att) {
			InfoWindow().hide();
			if(_videoDahuaDialog)_videoDahuaDialog.hide();
			if(!att.longitude || !att.latitude) {
				showTipMsg("此设备在地图上不存在！");
				return;
			}
			_videoDahuaPlayer.show();
			$("#dahuaCurrentCameraID").val(att.cameraID);
			_play(att.cameraID, att.streamType, att.mediaType, att.transType);
			return;
			var params = "cameraID="+att.cameraID+"&streamType="+att.streamType+"&mediaType="+att.mediaType+"&transType="+att.transType+
						"&szIp="+dahuaLogin.ip+"&nPort="+dahuaLogin.port+"&szUsername="+dahuaLogin.username+"&szPassword="+dahuaLogin.password;
			var info = InfoWindow();
			var geo = new Geometry({type: "point", points: att.longitude+","+att.latitude});
			info.setAnchor(geo);
			info.setTitle(att["name"]);
			info.setWidth(500);
			info.setHeight(480);
			//info.setContent('<iframe src="forward/map/mapTools/video/dialogDaHua/?'+params+'&videoWidth=500&videoHeight=300" frameborder="0" scrolling="no" style="width:500px;height:300px;padding:0;margin:0;"></iframe>');
			info.setContent('<div id="dahuamapDiv"></div>');
			positionManager.addHighLightPosition(geo);
			info.showAndCenterInfowindow(3);
			MapFactory.XHR.Load("dahuamapDiv","forward/map/mapTools/video/dialogDaHua/?"+params+"&videoWidth=500&videoHeight=300");
		}
		
		function getVideoDevices(graphic) {
			// 这里进行查询，看周围20像素内是否还有该图层的要素
			// 先将地图坐标转换为屏幕坐标，然后在平移20像素，获取新点的地图坐标，再获取两点之间的距离，按照该距离进行缓冲，缓冲结果在做查询，看是否有多个值，如果有多个值，则列表列出，并显示在图标右侧
			var distance = 16;
			var geo = graphic.geo;
			var points = geo.points.split(",");
			var screenP = map.toScreen(parseFloat(points[0]), parseFloat(points[1]));
			var mapP = map.toMap(screenP.x + distance, screenP.y);
			var bufferm = mm.getLength({type:"polyline", points:mapP.x+","+mapP.y+","+geo.points});
			
			var rsultListHtml = "<div id='videoDahuaDialogC' style='width:200px;'><ul>";
			var num=0;
			// 循环遍历，找出该点和其他设备点之间的距离，取出小于bufferm的点的集合
			for(var i=0,len=dahuaList.length; i<len; i++) {
				var tempData = dahuaList[i];
				var _bufferm = mm.getLength({type:"polyline", points:tempData.longitude+","+tempData.latitude+","+geo.points});
				if(_bufferm < bufferm) {
					num++;
					rsultListHtml += "<li onclick='showdahuavideo("+tempData.longitude+","+tempData.latitude+",\""+tempData.name+"\",\""+tempData.cameraID+"\","+tempData.streamType+","+tempData.mediaType+","+tempData.transType+")'>"+tempData.name+"</li>";
				}
			}
			if(num == 1) {
				getVideoDevice(graphic.attributes);
				return;
			}
			rsultListHtml += "</ul>";
			rsultListHtml += "</div>";
			var right = $("#mapContainer").width() - screenP.x - 230;
			_videoDahuaDialog = Dialog({
				mutiDialog : true,
				mask : false,
				title : "请选择...",
				content : rsultListHtml,
				buttonDisplay : {
					'confirmButton' : false,
					'cancelButton' : false
				},
				right: right,
				top: screenP.y+30
			});
			_videoDahuaDialog.show();
		}
		
		// 初始化
		function _initDH() {
			var obj = document.getElementById("DPSDK_OCX_MAIN");
			try{
			    gWndId = obj.DPSDK_CreateSmartWnd(0, 0, 100, 100);
			    obj.DPSDK_SetWndCount(gWndId, 1);//第二个参数为窗口数
				obj.DPSDK_SetSelWnd(gWndId, 0);
			}catch(e){
				ModuleMessage.hideMessage();
				alert("浏览器未安装ocx插件，请在管理员下拉菜单中下载ocx插件，并按照文档提示，对IE浏览器进行相关设置！");
			}
		}
		// 登录
		function _login(szIp, nPort, szUsername, szPassword) {
			var obj = document.getElementById("DPSDK_OCX_MAIN");
		    ShowCallRetInfo(obj.DPSDK_Login(szIp, nPort, szUsername, szPassword), "登录");
		}
		// 加载数据
		function _loadData() {
			var obj = document.getElementById("DPSDK_OCX_MAIN");
			
			ShowCallRetInfo(obj.DPSDK_LoadDGroupInfo(), "加载组织结构");
		}
		// 播放视频
		function _play(szCameraId, nStreamType, nMediaType, nTransType) {
			var obj = document.getElementById("DPSDK_OCX_MAIN");
			//alert(obj);
			var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
			//alert(gWndId+","+nWndNo+","+szCameraId+","+nStreamType+","+nMediaType+","+nTransType+"播放视频");
		    ShowCallRetInfo(obj.DPSDK_StartRealplayByWndNo(gWndId, nWndNo, szCameraId, nStreamType, nMediaType, nTransType), "播放视频");
		}
		// 后台获取大华数据，返回json格式
		function _loadDataByAjax() {
			var obj = document.getElementById("DPSDK_OCX_MAIN");
			$.ajax({
				type: "POST",
				url: "vedio/index/dahua",
				dataType: "json",
				data: {
	                d: obj.DPSDK_GetDGroupStr(),
	                deviceIp: '2',
	                rtspName:'3'
	            },
	            success: function(dd) {
	                var list = dd.msg;
	                dahuaList = [];
	                for(var i=0,len=list.length; i<len; i++) {
	                	var item = list[i];
	                	if(!item.isParent) {
	                		// 遍历通道，父节点忽略
	                		item.longitude = parseFloat(item.longitude)+baiduX;
	                		item.latitude = parseFloat(item.latitude)+baiduY;
	                		dahuaList.push(item);
	                	}
	                }
	                // 将大华设备添加到地图上
	                showGraphics();
	            },
	            error: function() {
	            	ModuleMessage.showMessage("获取大华数据时出错！", ModuleMessage.ERROR);
	            }
			});
		}
		
		function ShowCallRetInfo(nRet, strInfo) {
		    if(nRet != 0){
		        var obj = document.getElementById("DPSDK_OCX_MAIN");
		         try{
		         	//alert(strInfo + ": ErrorCode = "+obj.DPSDK_GetLastError());
		         	//ModuleMessage.showMessage(strInfo + ": ErrorCode = "+obj.DPSDK_GetLastError(), ModuleMessage.ERROR);
		         }catch(e){}
		    }
		}
		
		function showGraphics() {
			if(!map.isLayerExist("videoDahuaLayer")) {
				videoLayer = LayerManager("videoDahuaLayer");
			}
			else {
				videoLayer = LayerManager("videoDahuaLayer");
				videoLayer.removeFromMap();
			}
			videoLayer.addMouseOverEvent(function(_graphic) {
				showMouseOverTip(_graphic.graphic);
			});
			videoLayer.addMouseOutEvent(function(_graphic) {
				moTip.setContent("");
				moTip.hide();
			});
			videoLayer.addOnClickEvent(function(_graphic) {
				//getVideoDevice(_graphic.graphic.attributes);
				getVideoDevices(_graphic.graphic);
			});
			videoLayer.clear();//清除视频图层，准备重新渲染添加
			positionManager.clear();
			InfoWindow().hide();
			if (Object.prototype.toString.call(dahuaList) != "[object Array]") return;
			var symbol,tempData,point,x,y;
			for(var i=0,len=dahuaList.length; i<len; i++) {
				var tempData = dahuaList[i];
				x = parseFloat(tempData.longitude);
				y = parseFloat(tempData.latitude);
				if (isNaN(x) || isNaN(y))continue;//剔除没有坐标的卡口设备
				symbol = symbolConfig.videoQiujiSymbol;
				//symbol = symbolConfig.videoQiangjiSymbol;
				point = {type: "point", points: x + "," + y};
				GraphicManager({geo: point, symbol: symbol, attributes: tempData}).addToLayer("videoDahuaLayer");
			}
		}
		
		function videoLocation(videoName) {
			positionManager.clear();
			InfoWindow().hide();
			var res = [], tempData, x, y;
			for(var i=0,len=dahuaList.length; i<len; i++) {
				tempData = dahuaList[i];
				if(tempData.name.search(videoName) < 0) {
					continue;
				}
				res.push(tempData);
				x = parseFloat(tempData.longitude);
				y = parseFloat(tempData.latitude);
				if (isNaN(x) || isNaN(y))continue;//剔除没有坐标的卡口设备
				positionManager.addPositionPoint(x, y);
			}
			videoList = res;
			videoListSize = videoList.length;
			showVideoPagerResult(1);
		}
		
		var videoList,videoListSize;
		function showVideoPagerResult(pageNum) {
			var result = [];
			for(var i=(pageNum-1)*19,j=1; i<videoListSize && j<=19; i++,j++) {
				result.push(videoList[i]);
			}
			showVideoResult(result);
			Pager({
				parentSrc : "mapResultC",
				currentPage : pageNum,
				pageSize : 19,
				totalNum : videoListSize,
				pageOffset : 1,
				enableJump : true,
				enableStatistic : true,
				clickEvt : function(e){
					showVideoPagerResult(e.currentPage);
				}
			});
		}
		function showVideoResult(result) {
			var res = [];
			var tempData, x, y;
			for(var i=0,len=result.length; i<len; i++) {
				tempData = result[i];
				res.push(new resItem(tempData.name, getVideoDevice, tempData, tempData));
			}
			
			if(!res.length)res.push(new resItem("没有查询到相关数据"));
			resultBox().init("mapResultC").addContent({content:res,switchtab:true});
		}
		// 地图要素鼠标悬停提示消息
		function showMouseOverTip(graphic) {
			var att = graphic.attributes;
			var label = att.NAME || att.name;
			moTip.setContent(label);
			var geo = new Geometry({type: "point", points: att.longitude+","+att.latitude});
			moTip.show(geo);
		}
		// 地图中心提示消息
		function showTipMsg(msg) {
			tip.setContent(msg);
			tip.show();
			setTimeout(function(){
				tip.setContent("");
				tip.hide();
			}, 1500);
		}
		
		return api;
	}
});