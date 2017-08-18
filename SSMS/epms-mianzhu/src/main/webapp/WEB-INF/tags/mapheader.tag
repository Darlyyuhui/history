<%@tag  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="moduleId" type="java.lang.String" required="false" %>
<%@ attribute name="moduleName" type="java.lang.String" required="false" %>
<%@ attribute name="isMark" type="java.lang.Boolean" required="false" %>
<%@ attribute name="mapTools" type="java.lang.Boolean" required="false" %>
<%@ attribute name="rotateImage" type="java.lang.Boolean" required="false" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%
    String path = request.getContextPath();
    String encoding=request.getCharacterEncoding();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style type="text/css">

    #mapInfoWindow{
        -webkit-border-radius: 5px 5px 0px 0px;
    }
    #maplenged{
        position:absolute;
        left:12px;
        bottom: 70px;
        border: 1px solid #428bca;
        z-index:10000;
        background:#F0FFFF;
        /*height:410px;*/
        -moz-border-radius: 5px 5px 0px 0px;
        -webkit-border-radius: 5px 5px 0px 0px;
        -o-border-radius: 5px 5px 0px 0px;
    }
    #maplengedtitle{
        width:100%;
        font-size:12px;
        height:35px;
        background: #438eb9;
        color:white;
        -moz-border-radius: 5px 5px 0px 0px;
        -webkit-border-radius: 3px 3px 0px 0px;
        -o-border-radius: 5px 5px 0px 0px;
        padding:6px;
        text-align:center;
    }
    #maplengedcontent{
        padding:3px 5px 1px 5px;

    }
    .map-toolbox_item {
        float: left;
        height: 18px;
        padding: 0 12px;
        font-size: 12px;
        vertical-align: middle;
        cursor: pointer;
        overflow: visible;
        zoom: 1;
        color: #5f6477;
    }
    .map-toolbox_item:hover{color:#428bca;text-decoration:none;}
    .map-drop-em {
        width: 8px;
        height: 7px;
        float: right;
        margin-top: 6px;
        margin-left: 5px;
        background-image: url(${root}/images/map/newtools_9ddbaad.png);
        background-repeat: no-repeat;
        background-position: -13px -17px;
    }
    .map-text-active{color:#428bca;}
    .map-drop-em-active{background-position:-12px -177px;}

    .map-type-list-item{height:34px;line-height:34px;color:#5f6477;cursor:pointer;}
    .map-type-list-item:hover{color:#428bca;}

    .map-tools{z-index:2000;position:absolute;height:34px;top:20px;right:20px;-webkit-transition:-webkit-transform .3s ease-out;transition:transform .3s ease-out}
    .map-tools-bg{border-bottom:1px solid #ccc;box-shadow:1px 2px 1px rgba(0,0,0,.15);height:34px;overflow:hidden;background-color:#0a3a5c;z-index:10}
    .map-type-list{z-index:2000;position:absolute;width:100px;top:35px;left:0;background:#fff;box-shadow:1px 2px 1px rgba(0,0,0,.15);}

    .map-xyinfo{z-index:2000;position:absolute;left:20px;bottom:30px;height:24px;}
    .map-operate{z-index:2000;position:absolute;width:56px;height:84px;right:20px;bottom:10px;-webkit-transition:-webkit-transform .3s ease-out;transition:transform .3s ease-out}
    .map-zoom{position:absolute;border-bottom:1px solid #ccc;box-shadow:1px 2px 1px rgba(0,0,0,.15);cursor:pointer;width:26px;height:26px;left:30px;overflow:hidden;background-color:#FFF;z-index:10}

    .map-zoom-in{position:absolute;width:10px;height:10px;top:8px;left:8px;background-image:url(${root}/images/map/mapZoom.png);background-position:0 0}
    .map-zoom-out{position:absolute;width:10px;height:10px;top:8px;left:8px;background-image:url(${root}/images/map/mapZoom.png);background-position:-10px 0}
    .map-zoom-home{position:absolute;width:26px;height:26px;top:0px;left:0px;background-image:url(${root}/images/map/location.png);background-position:0 0}
    .map-zoom:hover .map-zoom-in{background-position:-20px 0}
    .map-zoom:hover .map-zoom-out{background-position:-30px 0}
    .map-zoom:hover .map-zoom-home{background-image:url(${root}/images/map/location_over.png)}
    .tool-box{z-index:2000;position:absolute;top:20px;right:20px;}
    
    .clear {
        clear:both;
    }
    .divcontent{
       float:left;border:1px solid #428bca;margin-left:2px;font-size:10px;text-align:center;height:50px;
    }
</style>
<link rel="stylesheet" href="${root}/css/GisStyle/Dialog.css" type="text/css">
<link rel="stylesheet" href="${root}/css/GisStyle/Common.css"/>
<%-- 地图统一样式引入 --%>
<script type="text/javascript">
    //字符串 数组转化为数字数组------
    function strListToNumberList(list) {
        var result = [];
        for(var i=0,len=list.length; i<len; i++) {
            result[i] = Number(list[i]);
        }
        return result;
    }
    var path = "<%=path%>",
            basePath = "<%=basePath%>",
            geoserverbaseurl = "http://193.169.100.111:8085/geowebcache/",
            supermapbaseurl = "http://10.10.15.201:8090/iserver",
            map_center_zoom = "104.12445,31.42520,1",
            map_max_extent = "103.902907838671, 31.1530368831954, 104.344413677999, 31.7032817292694",
            map_init_extent = "103.902907838671, 31.1530368831954, 104.344413677999, 31.7032817292694",
            geoserver_resolutions = "0.0023794610058302797, 0.0011897305029151398, 3.569191508745419E-4, 1.7845957543727095E-4, 8.328113520405979E-5, 3.569191508745419E-5, 1.7845957543727095E-5, 8.328113520405979E-6",
            MapFactoryBaseUrl = "<%=basePath%>";
    var centerAndZoom,
            _maxExtent,
            _initExtent,
            geoserverResolutions;
    if(map_center_zoom) {
        centerAndZoom = strListToNumberList(map_center_zoom.split(","));
    }
    if(map_max_extent) {
        var temp = strListToNumberList(map_max_extent.split(","));
        _maxExtent = {minX: temp[0], minY: temp[1], maxX: temp[2], maxY: temp[3]};
    }
    if(map_init_extent) {
        var temp = strListToNumberList(map_init_extent.split(","));
        _initExtent = {minX: temp[0], minY: temp[1], maxX: temp[2], maxY: temp[3]};
    }
    if(geoserver_resolutions) {
        geoserverResolutions = strListToNumberList(geoserver_resolutions.split(","));
    }
   
</script>
<script src="${root}/compnents/MapFrame/MapFactory.js" type="text/javascript"></script>

<script type="text/javascript">
var path = "${root}";
var menuid = "17062015184602618351fc2ff9bba239";
var mapTag = (function() {
    return function () {
        var api = {
            setParams : setParams,
            init : init
        };

        var _engine = "OpenLayers";
        var _tmpStart = 0, _tmpEnd = 7;
        //ArcGIS OpenLayers SuperMap
        function setParams(engine) {
            _engine = engine;
            if(_engine == "SuperMap") {
                _tmpStart = 5;
                _tmpEnd = 15;
                //centerAndZoom[2] += 5;
            }
        }
        setParams("SuperMap");
        function init(mapDiv,callback) {
            if(typeof mapDiv == "function") {
                callback = mapDiv;
                mapDiv = "map";
            }
            if(!mapDiv)mapDiv = "map";
            MapFactory.Init({
                engine : _engine,
                callback : function(){
                    MapFactory.Require([
                        "ItmsMap/MapConfig",
                        "MapFactory/MapManager",
                        "ItmsMap/Iframe/IframeMap*",
                    	"ItmsMap/Util/Navigation*",
						"ItmsMap/Util/ViewSwitch*",
						"MapFactory/Util/ZoomSlider*",
						"ItmsMap/SymbolConfig*"
                    ],function(mapConfig,mapManager,IframeMap,navigation,viewSwitch,zoomSlider,SymbolConfig){
                        var _mapConfig = mapConfig,
                                _userLayer,
                                _layers = _mapConfig.layers;
                        _mapConfig.showImage = false;// 显示影像图
                        var isMark = "${isMark}";
                        //OpenLayers.DOTS_PER_INCH = 96.0;
                        //OpenLayers.Util.onImageLoadErrorColor = 'transparent';
                        /**
                         * 初始化地图
                         */
                        var mapMGM = mapManager({
                            src : mapDiv,
                            mapConfig : _mapConfig,
                            loaded : function(){                            	
								new navigation({
									initExtent : _mapConfig.initExtent,
									container : "mapNavigationBox"
								});
								/**
								 * 初始化视图切换按钮
								 */
								new viewSwitch({
									src : "mapViewSwitcher"
								});
								/**
								 * 初始化地图缩放条
								 */
								var _zoomBarHeight = zoomSlider({
									src : "mapZoomSlider",
									levels : 7,
									levelStart :0,
									levelEnd : 6
								});
                                if(centerAndZoom)mapMGM.centerAndZoom(centerAndZoom[0], centerAndZoom[1], centerAndZoom[2]);
                                // 地图初始化完成后调用回调函数
                                if(typeof callback == "function"){
                                	callback(IframeMap());  
                                      if('${rotateImage}'){
                                    	  mapMGM.setBaseMapLayer(_layers.imageMap);
                                      }
                                }
                                	                                                                                              
                            }
                        });
                      
                        // 添加地图工具栏和放大缩小按钮
                        var _mapDiv = $("#"+mapDiv);
                        var _toolsDiv = $("<div>").appendTo(_mapDiv).addClass("map-tools");
                        $(_toolsDiv).attr("id","map_tools");
                        if("${mapTools}"==""){$("#map_tools").hide();}

                        /*
                        var _toolsDiv = $("<div>").appendTo(_mapDiv).addClass("btn-group").addClass("tool-box");                       
                        var basebutton= $("<button>").appendTo(_toolsDiv).addClass("btn").addClass("btn-primary").css("margin","0").html("电子底图");
                        var  mbutton= $("<button>").appendTo(_toolsDiv).addClass("btn").addClass("btn-primary").css("margin","0").html("测量");
                        var  hbutton= $("<button>").appendTo(_toolsDiv).addClass("btn").addClass("btn-primary").css("margin","0").html("绘制");
                        var  tbutton= $("<button>").appendTo(_toolsDiv).addClass("btn").addClass("btn-primary").css("margin","0").html("图层");
                        var  zbutton= $("<button>").appendTo(_toolsDiv).addClass("btn").addClass("btn-primary").css("margin","0").html("坐标定位");
                        var  menudiv= $("<div>").appendTo(_toolsDiv).addClass("btn-group");
                        var  menubutton= $("<button>").appendTo(menudiv).addClass("btn").addClass("btn-primary").addClass("dropdown-toggle").attr("data-toggle","dropdown").css("margin","0").css("height","38px").html("下列 <span class='caret'></span>");
                        var  uldiv =$("<ul>").appendTo(menudiv).addClass("dropdown-menu");
                        var  li1=$("<li>").appendTo(uldiv).html("<a href='#'>下拉链接 1</a>");
                        var  li2=$("<li>").appendTo(uldiv).html("<a href='#'>下拉链接 2</a>");
                        var  li3=$("<li>").appendTo(uldiv).html("<a href='#'>下拉链接 3</a>");
                        var  li4=$("<li>").appendTo(uldiv).html("<a href='#'>下拉链接 4</a>");
                        */                    
                        var _toolsBGDiv = $("<div>").appendTo(_toolsDiv).addClass("map-tools-bg");
                        var _toolsCenterDiv = $("<div>").appendTo(_toolsBGDiv).css("padding","7px 0 8px");
                        // 地图类型切换
                        var _mapType = $("<a>").appendTo(_toolsCenterDiv).addClass("map-toolbox_item").css("border-left","0");
                        $("<i>").appendTo(_mapType).addClass("ace-icon fa fa-star bigger-120").css("color","#FFF");
                        $("<span>").appendTo(_mapType).html("电子地图").css("color","#FFF");
                        $("<em>").appendTo(_mapType).addClass("map-drop-em");
                        var _mapTypeList = $("<div>").appendTo(_toolsDiv).addClass("map-type-list").css("display","none").css("background-color","#428bca");
                        var _ul = $("<ul>").appendTo(_mapTypeList).css("list-style-type","none");
                        var _li = $("<li>").appendTo(_ul).addClass("map-type-list-item").attr("type","base").html("电子地图").css("color","#FFF");
                        var _li2 = $("<li>").appendTo(_ul).addClass("map-type-list-item").attr("type","image").html("卫星地图").css("color","#FFF");

                        var onoff = true;
                        _mapType.click(function(){
                            if(onoff){
                                $(".map-type-list").slideDown();
                                $(".map-drop-em").css({backgroundPosition:"-12px -178px"});
                                onoff= false;
                            }else{
                                $(".map-type-list").slideUp();
                                $(".map-drop-em").css({backgroundPosition:"-13px -17px"});
                                onoff = true;
                            }
                        });

                        //点击切换地图类型
                        $(".map-type-list-item").click(function(){

                            var html = $(this).html();
                            if($(this).attr("type") == "base") {
                                mapMGM.setBaseMapLayer(_layers.baseMap);
                                MapFactory.Require(["ItmsMap/UserLayers/CustomLayers/Land*"],function(Land){
                                	Land().clearAllGraphic();
                                });
                            }
                            else if($(this).attr("type") == "image") {
                                mapMGM.setBaseMapLayer(_layers.imageMap);
                                MapFactory.Require(["ItmsMap/UserLayers/CustomLayers/Land*"],function(Land){
                                	Land().clearAllGraphic();
                                  	Land().drawRegions("1");
                                });
                            }
                            $(".map-toolbox_item span").eq(0).html(html);
                            $(".map-type-list").slideUp();
                            $(".map-drop-em").css({backgroundPosition:"-13px -17px"});
                        });

                        // 测距
                        var _line = $("<a>").appendTo(_toolsCenterDiv).addClass("map-toolbox_item");
                        $("<i>").appendTo(_line).addClass("ace-icon fa fa-star bigger-120").css("color","#FFF");
                        $("<span>").appendTo(_line).html("测量").css("color","#FFF");;
                        var _measureMent;
                        _line.click(function() {
                            $(".map-type-list").hide();
                            $(".map-drop-em").css({backgroundPosition:"-13px -17px"});
                            onoff = true;
                            MapFactory.Require(["MapFactory/MeasureMent"],function(measureMent){
                                if(_measureMent) {
                                    _measureMent.show();
                                    return;
                                }
                                _measureMent = measureMent({mapDiv: mapDiv,right: 136+48, top: 60});
                                _measureMent.setGeometryServiceUrl(mapConfig.layers.geometryService.url);
                                _measureMent.show();
                            });
                        });
                        // 坐标定位
                        var _location;
                        var _locationbyxy = $("<a>").appendTo(_toolsCenterDiv).addClass("map-toolbox_item");
                        $("<i>").appendTo(_locationbyxy).addClass("ace-icon fa fa-star bigger-120").css("color","#FFF");
                        $("<span>").appendTo(_locationbyxy).html("坐标定位").css("color","#FFF");;
                        _locationbyxy.click(function(){
                            $(".map-type-list").hide();
                            $(".map-drop-em").css({backgroundPosition:"-13px -17px"});
                            onoff = true;
                            MapFactory.Require(["ItmsMap/Location/LocationWidget*"],function(LocationWidget){
                                if(_location) {
                                    _location.show();
                                    return;
                                }
                                _location = LocationWidget({
                                    mapDiv: mapDiv,
                                    right: 110,
                                    top: 60
                                });
                                _location.show();
                            });
                        });
                        // 快速定位
                        var _bookMarks ;
                        var _position = $("<a>").appendTo(_toolsCenterDiv).addClass("map-toolbox_item");
                        $("<i>").appendTo(_position).addClass("ace-icon fa fa-star bigger-120").css("color","#FFF");
                        $("<span>").appendTo(_position).html("快速导航").css("color","#FFF");;
                        _position.click(function(){
                            $(".map-type-list").hide();
                            $(".map-drop-em").css({backgroundPosition:"-13px -17px"});
                            onoff = true;
                             MapFactory.Require(["MapFactory/BookMark*"],function(bookMarks){
                                if(_bookMarks) {
                                    _bookMarks.show();
                                    return;
                                }
                                _bookMarks = bookMarks({
                                    mapDiv: mapDiv,
                                    right: 20,
                                    top: 110,
                                    items : [
                                             
                                    ]
                                });
                                _bookMarks.show();
                            });
                        });
                        if(!isMark || isMark == "false") {
                            var _userLayer;
                            var _userLayerDiv = $("<a>").appendTo(_toolsCenterDiv).addClass("map-toolbox_item");
                            $("<i>").appendTo(_userLayerDiv).addClass("ace-icon fa fa-star bigger-120").css("color","#FFF");
                            $("<span>").appendTo(_userLayerDiv).html("用户图层").css("color","#FFF");
                            
                            MapFactory.Require(["ItmsMap/Compents/LeftController*"], function (LeftController){
                            	var leftConf = {
                                        mapDiv: mapDiv,
                                        top: 20,
                                        left: 80
                                       
                                    };
                     
                            	LeftController.setConf(leftConf);
                            	if("${mapTools}"!=""){LeftController.show();}
                            	
                            });
                            
                            _userLayerDiv.click(function () {
                                $(".map-type-list").hide();
                                $(".map-drop-em").css({backgroundPosition:"-13px -17px"});
                                onoff = true;
                               
                                MapFactory.Require(["ItmsMap/Compents/SimpleLegend*"], function (SimpleLegend){
                                	var legendConf = {
                                            mapDiv: mapDiv,
                                            left: 10,
                                            bottom: 12
                                        };
                         
                                	SimpleLegend.setConf(legendConf);
                                	SimpleLegend.show();
                                });
                                
                                MapFactory.Require(["ItmsMap/UserLayers/UserLayers*"], function (UserLayers) {
                                    if (_userLayer) {
                                        _userLayer.show();
                                        return;
                                    }
                                    var userConf = {
                                        moduleIds: ["161008142052010e9846b2dcf8d2418d","1608171435130687a6b2366128327610","1609080954079330a1a8bf482f118d34","1609080954360965b862d8fdeb97f0b9","1609080954360965b862d8fdeb97f0b7"],
                                        mapDiv: mapDiv,
                                        right: 20,
                                        top: 60
                                    };
                                    var mid = "${moduleId}";
                                    if (mid && mid != "0") {
                                        if(mid == "161018152420340899733c620fe78826"){
                                            userConf.moduleIds = ["161018152420340899733c620fe78826","161008142052010e9846b2dcf8d2418d"];
                                        }
                                        else { userConf.moduleIds = [mid];}
                                    }
                                    _userLayer = UserLayers(userConf);
                       
                                    _userLayer.init();
                                    _userLayer.show();
                                });
                            });
                        }
                        /* var legends=SymbolConfig.getlenged();
                         var  container=$("#maplengedcontent");
                        //container.css("width","80px");
                           for(var index in legends){
                        	   var legend=legends[index];
                        	   var name=legend.name;
                        	   var urls=legend.urls;
                        	   var divdom=$("<div>").appendTo(container);
                               //divdom.css("width","70px");
                               //divdom.css("float","left");
                        	   //$("<br>").appendTo(container);
                        	   var pdom= $("<span>");
                        	       pdom.css("text-align","center");
                        	   var  titlelabel = $("<span>");
                        	        titlelabel.html(name);
                        	        titlelabel.appendTo(pdom);
                        	        //divdom.append(pdom);
                        	    var contentdiv=$("<div>").appendTo(divdom);  
                        	        contentdiv.css("height","36px");

                        	    for(var j in urls){
                        	    	  var imgurl=urls[j];                       	    	
                        	    	  var item=_generateImgItem(name, imgurl);
                        	    	  contentdiv.append(item);
                        	       }
                           }*/
                         
                       	function _generateImgItem(name, obj){
                    		var _img = $("<img>"),
                    			_label = $("<span>"),
                    			_div = $("<div>"),
                    		    _br = $("<br>"); 
                            //_div.addClass("divcontent");
                            _div.css("height","32px");
                            _img.attr("src",obj.url);
                            _label.html(name);
                            _div.append(_img);
                            _div.append(_label);
                            //_div.append("&nbsp;&nbsp;");
                    		return _div;
                    	}                                                       
                        /*
                        // 地图缩放
                        var _opDiv = $("<div>").appendTo(_mapDiv).addClass("map-operate");
                        var _zoomHomeDiv = $("<div>").appendTo(_opDiv).addClass("map-zoom");
                        $("<div>").appendTo(_zoomHomeDiv).addClass("map-zoom-home").attr("title","全图");
                        var _zoomInDiv = $("<div>").appendTo(_opDiv).addClass("map-zoom").css("top","27px");
                        $("<div>").appendTo(_zoomInDiv).addClass("map-zoom-in").attr("title","放大一级");
                        var _zoomOutDiv = $("<div>").appendTo(_opDiv).addClass("map-zoom").css("top","54px");
                        $("<div>").appendTo(_zoomOutDiv).addClass("map-zoom-out").attr("title","缩小一级");
                        _zoomInDiv.bind('click',function(){
                            mapMGM.zoomIn();
                        });
                        _zoomOutDiv.bind('click',function(){
                            mapMGM.zoomOut();
                        });
                        _zoomHomeDiv.bind('click',function(){
                            mapMGM.setExtent(mapMGM.getInitExtent());
                        });
                      
                        var _xyinfo = $("<div>").appendTo($(document.body)).addClass("map-xyinfo");
                        _xyinfo.css("top", (ap.top+ap.height-24)+"px");
                        _xyinfo.css("left", (ap.left+10)+"px");
                        // 添加鼠标滑轮地图缩放事件
                        mapMGM.setMouseMoveEvent(function(e){
                            var xy = e.mapPoint.points.split(",")
                            if(xy[0].length > 7 && xy[1].length > 6) {
                                _xyinfo.text("经度："+xy[0].substr(0, 8)+"  纬度："+xy[1].substr(0, 7));
                            }
                        });
                        
                        if(!isMark || isMark == "false") {
                            MapFactory.Require(["ItmsMap/Util/Legend*"],function(Legend){
                                // 图例
                                Legend.setConf({
                                    mapDiv: mapDiv,
                                    left:mapMGM.getMapSize().width-285,
                                    bottom:30
                                });
                                var mid = "${moduleId}";
                                if(mid && mid != "0") {
                                    if(mid == "161018152420340899733c620fe78826"){
                                        Legend.addModule({id:"161018152420340899733c620fe78826", name:"保护区图例"});
                                        Legend.addModule({id:"161008142052010e9846b2dcf8d2418d", name:"企业图例"});
                                    }
                                    else Legend.addModule({id:mid, name:"${moduleName}图例"});
                                }
                                else {
                                    Legend.addModule({id:"161018152420340899733c620fe78826", name:"保护区图例"});
                                    Legend.addModule({id:"1609080955044864ac6e2c8a99d6422e", name:"矿区图例"});
                                    Legend.addModule({id:"161008142052010e9846b2dcf8d2418d", name:"企业图例"});
                                    Legend.addModule({id:"1609080954079330a1a8bf482f118d34", name:"油烟图例"});
                                    Legend.addModule({id:"1608171435130687a6b2366128327610", name:"声尘图例"});
                                    Legend.addModule({id:"1609080954360965b862d8fdeb97f0b9", name:"视频图例"});
                                }
                            });
                        }  
                        */
                    });

                }
            });

        }

        /**
         * 传入一个要素的dom对象，获取该要素的绝对为位置
         * @param obj
         * @return {width: xx, height: xx, left:xx, top: xx, right: xx, bottom: xx}
         */
        function _getAbsolutePosition(obj) {
            if(!obj)return null;
            var w = obj.offsetWidth, h = obj.offsetHeight;
            //从目标元素开始向外遍历，累加top和left值
            var t, l;
            for (t = obj.offsetTop, l = obj.offsetLeft; obj = obj.offsetParent;) {
                t += obj.offsetTop;
                l += obj.offsetLeft;
            }
            var r = document.body.offsetWidth - w - l;
            var b = document.body.offsetHeight - h - t;
            return {width: w, height: h, top: t, left: l, right: r, bottom: b};
        }

        return api;
    }
})();

</script>
