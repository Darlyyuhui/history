/**
 * 地图工厂初始化类
 * 
 * @author ZLT
 * @since 2014-3-25
 */

window.MapFactory = {};

/**
 * 框架基础url
 */
MapFactory.BaseUrl =MapFactoryBaseUrl;

/**
 * 框架基础路径
 */
MapFactory.FramePath = "compnents/MapFrame/MapFactory";

/**
 * 框架配置
 */
MapFactory.Config = {
	"MapFactory/InfoWindowManager" : {
		ref : "MapFactory/InfoWindowManager/InfoWindowManager"
	}
}

MapFactory.OverLayerMap;
/**
 * 框架初始化
 * 
 * @param conf
 *            Object 传入配置参数
 */
MapFactory.Init = function(conf){

	// 地图引擎，ArcGIS,OpenLayer以及以后更多的扩展
	MapFactory.Engine = conf.engine;

	// 放置地图的页面容器
	MapFactory.Container = conf.container;

	// 框架底层驱动dojo requirejs
	MapFactory._BootStrap("dojo",conf.callback);
}

/**
 * 为地图管理器提供地图资源,仅供框架内部使用，外部禁止调用。
 */
MapFactory._MapManagerResource = {};

/**
 * 为地图Graphic提供统一的管理，仅供框架内部使用，外部禁止调用。
 */
MapFactory._MapGraphicResource = {};

/**
 * 地图图层与Graphic关联键值对
 */
MapFactory._MapLayerGraphicResource = {};
/**
 * 新加模拟百度地图图层对象
 */
MapFactory._layermanager= {};
/**
 * 框架底层驱动器
 * 
 * @param bootStrapEngine
 *            String 驱动字符串dojo/requirejs
 */
MapFactory._BootStrap = function(bootStrapEngine,callback){

	MapFactory.DojoConfig = {
		async:0,
		baseUrl:MapFactory.BaseUrl,
		hasCache:{
			"config-selectorEngine":"acme",
			"config-tlmSiblingOfDojo":1,
			"dojo-built":1,
			"dojo-loader":1,
			"dojo-undef-api":0,
			dom:1,
			"extend-esri":1,
			"host-browser":1
		},
		packages:[
			// {location:MapFactory.FramePath +
			// "ThirdParty/ArcgisLib/js/dojo/dojox",name:"dojox"},
			// {location:"../../dgrid",main:"OnDemandGrid",name:"dgrid"},
			// {location:"../dijit",name:"dijit"},
			// {location:"../../xstyle",main:"css",name:"xstyle"},
			// {location:MapFactory.FramePath+"ThirdParty/ArcgisLib/js/esri",name:"esri"},
			{location:".",name:"dojo"}
			// {location:"../../put-selector",main:"put",name:"put-selector"}
		]
	};

	if("dojo" == bootStrapEngine){
		MapFactory._BootStrapByDojo(callback);
	}else if("requirejs" == bootStrapEngine){
		MapFactory._BootStrapByRequireJS(callback);
	}
}

MapFactory._BootStrapByDojo = function(callback){

	var framePath = MapFactory.FramePath;
	// 调用类定义器
	MapFactory.CreateScriptNode(
		framePath + "/ThirdParty/ArcgisLib/dojoinit.js",
		{},
		function(){

			// 底层框架入口文件名
			// var baseFrameEntrance = MapFactory.Engine + "/init*";
			var baseFrameEntrance;
			if(MapFactory.Engine == "ArcGIS"){
				baseFrameEntrance = MapFactory.BaseUrl + "compnents/MapFrame/MapFactory/ThirdParty/ArcgisLib/init.js";
			}

			if(MapFactory.Engine == "OpenLayers"){
				baseFrameEntrance = MapFactory.BaseUrl + "compnents/MapFrame/MapFactory/ThirdParty/OpenLayersLib/init.js";
			}			
			if(MapFactory.Engine == "SuperMap"){
				baseFrameEntrance = MapFactory.BaseUrl + "compnents/MapFrame/MapFactory/ThirdParty/SuperMapLib/init.js";
			}
            if(MapFactory.Engine == "BaiDu"){           	
            	MapFactory.CreateCssNode("compnents/MapFrame/MapFactory/ThirdParty/BaiDu/css/baidu.css");
            	MapFactory.CreateCssNode("compnents/MapFrame/MapFactory/ThirdParty/BaiDu/css/scxnTabDiv.css");           	
            	MapFactory.CreateScriptNode(MapFactory.BaseUrl + "compnents/MapFrame/MapFactory/ThirdParty/BaiDu/js/BaiduApi_2.0.js");
            	baseFrameEntrance = MapFactory.BaseUrl + "compnents/MapFrame/MapFactory/ThirdParty/BaiDu/init.js";           	                    	
            }
			// 配置基础路径
			dojo.registerModulePath("MapFactory",framePath);
			dojo.registerModulePath("dojo",framePath + "/ThirdParty/ArcgisLib/js/dojo/dojo");
			dojo.registerModulePath("dojox",framePath + "/ThirdParty/ArcgisLib/js/dojo/dojox");
			dojo.registerModulePath("dijit",framePath + "/ThirdParty/ArcgisLib/js/dojo/dijit");
			dojo.registerModulePath("ItmsMap",MapFactory.BaseUrl + "compnents/MapFrame/ItmsMap");

			if("OpenLayers" == MapFactory.Engine){
				dojo.registerModulePath("OpenLayers",framePath + "/ThirdParty/OpenLayersLib");
			}

			if("ArcGIS" == MapFactory.Engine){
				dojo.registerModulePath("ArcGIS",framePath + "/ThirdParty/ArcgisLib");
				dojo.registerModulePath("esri",framePath + "/ThirdParty/ArcgisLib/js/esri");
			}

			// 调用底层地图JSAPI框架
			MapFactory.CreateScriptNode(baseFrameEntrance,{},function(){
				MapFactory._JsonBootStrap(function(){
					MapFactory.CreateScriptNode(MapFactory.FramePath + "/ThirdParty/Jquery/jquery-1.7.2.min.js",{},function(){
						if(MapFactory.VariableTypes.isFunc(callback)){
							callback();
						}
					});
				});
			});
		}
	);
}


MapFactory._BootStrapByRequireJS = function(callback){
	var framePath = MapFactory.FramePath;
	// 调用类定义器
	MapFactory.CreateScriptNode(
		framePath + "/ThirdParty/RequireJS/require.js",
		{},
		function(){

			// 底层框架入口文件名
			var baseFrameEntrance = MapFactory.Engine + "/init";

			// 配置基础路径
			require.config({
				baseUrl : MapFactory.BaseUrl,
				paths : {
					"MapFactory" : framePath,
					"ArcGIS"  : framePath + "/ThirdParty/ArcgisLib",
					"OpenLayers" : framePath + "/ThirdParty/OpenLayersLib",
					"SuperMap" : framePath + "/ThirdParty/SuperMapLib",
					"ItmsMap" : "compnents/MapFrame/ItmsMap"
				}
			});

			// 调用底层地图JSAPI框架
			require([baseFrameEntrance],function(){
				MapFactory._JsonBootStrap(function(){
					MapFactory.CreateScriptNode(MapFactory.FramePath + "/ThirdParty/Jquery/jquery-1.7.2.min.js",{},function(){
						if(MapFactory.VariableTypes.isFunc(callback)){
							callback();
						}
					});
				});
			});
		}
	);
}


/**
 * 加载JSON处理器
 */
MapFactory._JsonBootStrap = function(callback){
	MapFactory.CreateScriptNode(MapFactory.FramePath + "/ThirdParty/JSON/json2.js",{},function(){
		if(MapFactory.VariableTypes.isFunc(callback)){
			callback();
		}
	});
}

/**
 * 创建JS标签节点
 * 
 * @param url
 *            String JS文件地址
 * @param attributes
 *            Object 属性对象
 * @param callback
 *            Function 回调函数
 */
MapFactory.CreateScriptNode = function(url,attributes,callback){
	var doneFlag = false;
	var script = document.createElement('script');
	script.type = "text/javascript";
	script.language = "javascript";
	script.src = url;

	for(var elem in attributes){
		script.setAttribute(elem,attributes[elem]);
	}

	if(script.readyState){
		script.onreadystatechange = function(){
			if(script.readyState == "loaded" || script.readyState == "complete"){
				script.onreadystatechange = null;
				if(callback){
					callback.call(script);
				}
			}
		}
	}else{
		script.onload = function(){
			script.onload = null;
			if(callback){
				callback.call(script);
			}
		}
	}

	document.getElementsByTagName("head")[0].appendChild(script);
}

/**
 * 创建CSS标签节点
 * 
 * @param url
 *            String CSS文件地址
 */
MapFactory.CreateCssNode = function(url){
	var css = document.createElement('link');
	css.rel = "stylesheet";
	css.href = url;
	document.getElementsByTagName("head")[0].appendChild(css);
}

/**
 * 扩展对象
 * 
 * @param obj1
 *            Object 需要扩展的对象
 * @param obj2
 *            Object 用来扩展第一个对象
 */
MapFactory.Extend = function(Obj1,obj2){
	if( typeof obj2 != "undefined") {
		for(elem in Obj1) {
			if( typeof obj2[elem] != "undefined"){
				Obj1[elem] = obj2[elem];
			}
		}
		obj2 = null;
	}
}

/**
 * 对象定义
 * 
 * @param args[0]
 *            String 类名，可选
 * @param args[1]
 *            Array 依赖类，可选
 * @param args[2]
 *            Function 类主体
 * @return obj Object 返回类主体所返回的参数
 */
MapFactory.Define = function(){
	var className = arguments[0],
		deps = arguments[1],
		callback = arguments[2];

	if(MapFactory.VariableTypes.isFunc(className)){ // define(callback)
		callback = className;
		return define.apply(this,[callback]);
	}else{
		if(MapFactory.VariableTypes.isString(className)){
			className = MapFactory.GenerateRoute(className);
			if(MapFactory.VariableTypes.isFunc(deps)){  // define(className,callback)
				callback = deps;
				return define.apply(this,[className,callback]);
			}else{
				if(MapFactory.VariableTypes.isArray(deps)){ // define(className,deps,callback)
					for(var i=0,len=deps.length;i<len;i++){
						deps[i] = MapFactory.GenerateRoute(deps[i]);
					}
					return define.apply(this,[className,deps,callback]);
				}
			}
		}
	}

	return null;
}

/**
 * 引用对象
 * 
 * @param args[0]
 *            Array 要执行类的依赖类，可选
 * @param args[1]
 *            Function 执行类的主体
 * @return obj Object 返回类主体所返回的参数
 */
MapFactory.Require = function(){
	var deps = arguments[0],
		callback = arguments[1];

	if(MapFactory.VariableTypes.isArray(deps)){
		for(var j = 0,len = deps.length;j<len;j++){
			deps[j] = MapFactory.GenerateRoute(deps[j]);
		}

		if(MapFactory.VariableTypes.isFunc(callback)){ // require(deps,callback,isByRoute)
			require.apply(this,[deps,callback]);
		}else{
			require.apply(this,[deps]); // require(deps,isByRoute);
		}
	}

	return null;
}

/**
 * 路由规则
 * 
 * @param 传入类名
 * @return String 返回根据框架生成相应的类名
 */
MapFactory.GenerateRoute = function(className){
	var classObject = MapFactory.Config[className];
	if(classObject){
		return classObject.ref;
	}else{
		return className.match(/\*$/) ? className.replace("*","") : className + className.match(/\/[\w]*$/) + MapFactory.Engine;
	}
}

/**
 * 自动生成API
 * 
 * @param ApiObj
 *            Object 要传入的API模型
 * @param api
 *            Object 返回真实API
 */
MapFactory.GenerateAPI = function(ApiObj){
	if(MapFactory.VariableTypes.isObject(ApiObj)){
		var api = "({";
		var i = 0;
		for(var elem in ApiObj){
			if(i != 0){
				api += ",";
			}
			api += elem+":"+elem;
			i++;
		}
		api += "})";
		return api;
	}
	return null;
}

/**
 * 产生唯一ID
 * 
 * @param len
 *            int ID长度,最小8位,不算seed的长度
 * @param seed
 *            String ID标示符
 */
MapFactory.GenerateID = function(len,seed){
	var idLen = len ? (len < 12 ? 12 : len) : 12,
		idSeed = seed ? seed : "",
		randomSeed = "ABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz",
		randomStr = '';
	while(randomStr.length < idLen){
		randomStr += randomSeed.substr(Math.ceil(Math.random()*62),1);
	}
	return idSeed + randomStr;
}

/**
 * 复制对象
 * 
 * @param obj
 *            Object 传入的对象
 * @return newObj Object 新生成的对象
 */
MapFactory.Clone = function(obj){
	var newObj = {};
	for(var elem in obj){
		newObj[elem] = obj[elem];
	}
	return newObj;
}

/**
 * JSON
 */
MapFactory.JSON = {
	/**
	 * 将对象JSON转换为字符串
	 * 
	 * @param obj
	 *            Object 要转换的对象
	 * @return JsonString String 字符串化后的Object
	 */
	Stringify : function(obj){
		return JSON2.stringify(obj);
	},

	/**
	 * 将JSON字符串转化为对象
	 * 
	 * @param JsonString
	 *            String 要转换的JSON字符串
	 * @return obj Object 将JSON字符串转换为Object
	 */
	Parse : function(JsonString){
		return JSON2.parse(JsonString);
	}
}

/**
 * 异步
 */
MapFactory.XHR = {

	/**
	 * 以post方式请求
	 * 
	 * @param url
	 *            String 请求地址
	 * @param params
	 *            Object 请求时传送的参数，键值对
	 * @param callback
	 *            Function 请求成功后的回调函数
	 */
	Post : function(url,params,callback){
		if(MapFactory.VariableTypes.isObject(params)){
			$.post(url,params,callback);
			/*
			 * MapFactory.XHR.Ajax({ type : "POST", url : url, data : params,
			 * onSuccess : callback });
			 */
		}
		if(MapFactory.VariableTypes.isFunc(params)){
			callback = params;
			$.post(url,callback);
			/*
			 * MapFactory.XHR.Ajax({ type : "POST", url : url, onSuccess :
			 * callback });
			 */
		}
	},

	/**
	 * 以get方式请求
	 * 
	 * @param url
	 *            String 请求地址
	 * @param params
	 *            Object 请求时传送的参数，键值对
	 * @param callback
	 *            Function 请求成功后的回调函数
	 */
	Get : function(url,params,callback){
		if(MapFactory.VariableTypes.isObject(params)){
			$.get(url,params,callback);
			/*
			 * MapFactory.XHR.Ajax({ type : "GET", url : url, data : params,
			 * onSuccess : callback });
			 */
		}
		if(MapFactory.VariableTypes.isFunc(params)){
			callback = params;
			$.get(url,callback);
			/*
			 * MapFactory.XHR.Ajax({ type : "GET", url : url, onSuccess :
			 * callback });
			 */
		}
	},

	/**
	 * 异步读取
	 * 
	 * @param target
	 *            String 要读取位置ID
	 * @param url
	 *            String 请求地址
	 * @param params
	 *            Object 请求时传送的参数，键值对
	 * @param callback
	 *            Function 请求成功后的回调函数
	 */
	Load : function(target,url,params,callback){
		target = $("#"+target);
		/*
		 * target = MapFactory.Dom.getById(target); console.log(target);
		 * if(!target){ return; }
		 */
		if(MapFactory.VariableTypes.isString(url)){
			if(MapFactory.VariableTypes.isObject(params)){
				if(MapFactory.VariableTypes.isFunc(callback)){
					target.load(url,params,callback);
					/*
					 * params, onSuccess : function(data){ target.innerHTML =
					 * data; callback(); } }); MapFactory.XHR.Ajax({ type : "POST", url : url, data :
					 *
					 */
				}else{
					target.load(url,params);
					/*
					 * MapFactory.XHR.Ajax({ type : "POST", url : url, data :
					 * params, onSuccess : function(data){ target.innerHTML =
					 * data; } });
					 */
				}
			}else if(MapFactory.VariableTypes.isFunc(params)){
				callback = params;
				target.load(url,callback);
				/*
				 * MapFactory.XHR.Ajax({ type : "GET", url : url, onSuccess :
				 * function(data){ target.innerHTML = data; callback(); } });
				 */
				
			}else{
				target.load(url);
				/*
				 * MapFactory.XHR.Ajax({ type : "GET", url : url, onSuccess :
				 * function(data){ target.innerHTML = data; } });
				 */
			}
		}
	},

	/**
	 * 序列化键值对
	 * 
	 * @param param
	 *            Object + key : value
	 */
	Serialize : function(param){
		var _paramArr = [];
		for(var elem in param){
			_paramArr.push(elem+"="+param[elem]);
		}
		return _paramArr.join("&");
	},

	/**
	 * 异步请求函数
	 * 
	 * @param options
	 *            Object + type String Http请求类型，Post/Get，默认Post + url String
	 *            请求地址，默认为空 + timeout Number 超时时间，默认5000 + onComplete Function
	 *            所有状态下，函数执行完调用的函数 + onError Function 请求失败回调函数 + onSuccess
	 *            Function 请求成功回调函数
	 */
	Ajax : function(options){
		var _options = {
			type : "POST",
			url : "",
			data : {},
			timeout : 5000,
			onComplete : function(){},
			onError : function(){},
			onSuccess : function(){}
		};
		MapFactory.Extend(_options,options);

		if(typeof XMLHttpRequest == "undefined"){
			XMLHttpRequest = function(){
				return new ActiveXObject(
					navigator.userAgent.indexOf("MSIE 5") >= 0 ? "Microsoft.XMLHTTP" : "Msxml2.XMLHTTP"
				);
			}
		}

		var _xml = new XMLHttpRequest(),
			_requestDone = false,
			_serializeData = MapFactory.XHR.Serialize(_options.data),
			_isGet = "GET" == _options.type ? true : false;

		if(_isGet){
			_xml.open(_options.type,_options.url+"?"+_serializeData,true);
		}else{
			_xml.open(_options.type,_options.url,true);
			_xml.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			if(_xml.overrideMimeType){
				// _xml.setRequestHeader("Connection","close");
			}
		}
		_xml.onreadystatechange = function(){

			if(
			   // 得不到服务器状态，且正在请求本地文件
			   !_xml.status && location.protocol == "file:" ||
			   // 所有200至300之间的状态码
			   (_xml.status >= 200 && _xml.status < 300) ||
			   // 文档未修改
			   _xml.status == 304 ||
			   // Safari在文档未修改时返回空状态
			   navigator.userAgent.indexOf("Safari") >=0 && typeof _xml.status == "undefined"
			){
				var _contentType = _xml.getResponseHeader("content-type"),
					_data = _contentType && _contentType.indexOf("xml") >=0;
				_data = _data ? _xml.responseXML : _xml.responseText;

				if(MapFactory.String.Trim(_data)!=""){
					if(_contentType.indexOf("json")>=0){
						_data = MapFactory.JSON.Parse(_data);
					}
				}else{
					_data = "";
				}

				_options.onSuccess(_data);
			}else{
				_options.onError();
			}
			_options.onComplete();
		}
		if(_isGet){
			_xml.send();
		}else{
			_xml.send(_serializeData);
		}
	}
}

/**
 * Cookie设置
 */
MapFactory.Cookie = {
	/**
	 * 设置Cookie
	 * 
	 * @param name
	 *            String Cookie名
	 * @param value
	 *            String Cookie值
	 * @param value
	 *            Number expires有效时长，单位：秒
	 */
	Set : function(name,value,expires){
		var cookie = "";
		cookie += name + "=" + escape(value) + ";path=/"
		if(expires){
			var expdate = new Date();
			expdate.setTime(expdate.getTime() + parseInt(expires) * 1000);
			cookie += ";expires=" + expdate.toGMTString();
		}
		document.cookie = cookie;
	},

	/**
	 * 删除Cookie
	 * 
	 * @param name
	 *            String Cookie名
	 */
	Del : function(name){
		MapFactory.Cookie.Set(name,"",-1);
	},

	/**
	 * 获取Cookie
	 * 
	 * @param name
	 *            String Cookie名
	 * @return value String Cookie值
	 */
	Get : function(name){
		var cookie = document.cookie;
		if(!cookie.length){
			return "";
		}
		var prefix = name + "=",
			start = cookie.indexOf(prefix);
		if(start == -1){
			return "";
		}
		start += prefix.length;
		var end = cookie.indexOf(";",start);
		if(end == -1){
			end = cookie.length;
		}
		return unescape(cookie.substr(start,end));
	}
}

/**
 * 类型判断
 * 
 * @param obj
 *            Object 需要检查的对象
 * @return true/false Boolean 返回布尔类型
 */
MapFactory.VariableTypes = {

	_type : Object.prototype.toString,

	/**
	 * 判断是否为对象类型
	 */
	isObject : function(obj){
		return obj ? this._type.call(obj) === "[object Object]" : false;
	},

	/**
	 * 判断是否为空对象
	 */
	isEmptyObject : function(obj){
		for(var elem in obj){
			return false;
		}
		return true;
	},

	/**
	 * 判断是否为Undefined
	 */
	isUndefined : function(obj){
		return typeof obj === "undefined";
	},

	/**
	 * 判断是否为数组
	 */
	isArray : function(obj){
		return this._type.call(obj) === "[object Array]";
	},

	/**
	 * 判断是否为字符串
	 */
	isString : function(obj){
		return this._type.call(obj) === "[object String]";
	},

	/**
	 * 判断是否为数字
	 */
	isNumeric : function(obj){
		return this._type.call(obj) === "[object Number]";
	},

	/**
	 * 判断是否为布尔类型
	 */
	isBoolean : function(obj){
		return this._type.call(obj) === "[object Boolean]";
	},

	/**
	 * 判断是否为正则类型
	 */
	isReg : function(obj){
		return this._type.call(obj) === "[object Regexp]";
	},

	/**
	 * 判断是否为日期类型
	 */
	isDate : function(obj){
		return this._type.call(obj) === "[object Date]";
	},

	/**
	 * 判断是否为函数类型
	 */
	isFunc : function(obj){
		return this._type.call(obj) === "[object Function]";
	},

	/**
	 * 判断是否为Null类型
	 */
	isNull : function(obj){
		return null === obj;
	}
}

MapFactory.String = {
	/**
	 * 去除字符串两端空格
	 * 
	 * @param obj
	 *            String 传入字符串
	 */
	trim : function(obj){
		return obj.replace(/^\s+|\s+$/g,"");
	}
}

MapFactory.Array = {

	/**
	 * 是否在数组中
	 * 
	 * @param item
	 *            要判断的元素
	 * @param array
	 *            Array 要被判断的数组
	 * @return index Number 返回元素在数组中的位置
	 */
	inArray : function(item,array){
		if(!MapFactory.VariableTypes.isArray(array)){
			return -1;
		}
		for(var i=0,len=array.length;i<len;i++){
			if(item === array[i]){
				return i;
			}
		}
		return -1;
	},

	/**
	 * 从数组中移除元素
	 * 
	 * @param item
	 *            要判断的元素
	 * @param array
	 *            Array 要被判断的数组
	 */
	removeItem : function(item,array){
		if(!MapFactory.VariableTypes.isArray(array)){
			return -1;
		}
		var _isFind = false;
		for(var i=0,len=array.length;i<len;i++){
			if(!_isFind && item === array[i]){
				_isFind = true;
			}
			if(_isFind){
				if(i<=len-2){
					array[i] = array[i+1];
				}else{
					break;
				}
			}
		}
		if(_isFind){
			array.length = array.length -1;
		}
	}
}

MapFactory.Dom = {

	/**
	 * 根据ID获取元素
	 * 
	 * @param id
	 *            String 元素ID
	 * @return DOMElement
	 */
	getById : function(id){
		return document.getElementById(id);
	},

	/**
	 * 根据Class获取元素
	 * 
	 * @param className
	 *            String 元素Class名称
	 * @return Array[DOMElement]
	 */
	getByClass : function(){
	
	},

	/**
	 * 创建元素
	 * 
	 * @param tag
	 *            String 要创建的元素名
	 * @return DOMElement
	 */
	createElem : function(tag){
		return document.createElement(tag);
	},

	/**
	 * 在父元素里追加子元素
	 * 
	 * @param pelem
	 *            DOMElement 父元素
	 * @param celem
	 *            DOMElement 子元素
	 */
	append : function(pelem,celem){
		pelem.appendChild(celem);
	},

	/**
	 * 在父元素子元素的最前面添加元素
	 * 
	 * @param pelem
	 *            DOMElement 父元素
	 * @param celem
	 *            DOMElement 子元素
	 */
	prepend : function(pelem,celem){
		if(pelem.firstChild){
			pelem.insertBefore(celem,pelem.firstChild);
		}else{
			pelem.appendChild(celem);
		}
	},

	/**
	 * 返回指定元素的父元素
	 * 
	 * @param elem
	 *            DOMElement 指定元素
	 * @return DOMElement 父元素
	 */
	parent : function(elem){
		return elem.parentElement;
	},

	/**
	 * 返回指定元素的子元素列表
	 * 
	 * @param elem
	 *            DOMElement 指定元素
	 * @return DOMElement[] 子元素集
	 */
	children : function(elem){
		var childNodes = [],r;
		if(elem && elem.nodeType == 1){
			r = elem.childNodes;
			for(var i=0,len=r.length;i<len;i++){
				if(r[i] && r[i].nodeType == 1){
					childNodes.push(r[i]);
				}
			}
		}
		return childNodes;
	},

	/**
	 * 移除某一元素
	 * 
	 * @param elem
	 *            DOMElement 指定元素
	 */
	remove : function(elem){
		if(elem && elem.parentElement){
			elem.parentElement.removeChild(elem);
		}
	},

	/**
	 * 清空某一元素的内容
	 * 
	 * @param elem
	 *            DOMElement 指定元素
	 */
	empty : function(elem){
		while(elem.firstChild){
			elem.removeChild(elem.firstChild);
		}
	},

	/**
	 * 返回指定元素的平行元素
	 * 
	 * @param elem
	 *            DOMElement 指定元素
	 * @return [DOMElement] 元素数组
	 */
	siblings : function(elem){
		var _sibling = elem.previousSibling,
			_preSiblings = [],
			_nextSiblings = [];
		while(_sibling){
			_preSiblings.push(_sibling);
			_sibling = _sibling.previousSibling;
		}
		_sibling = elem.nextSibling;
		while(_sibling){
			_nextSiblings.push(_sibling);
			_sibling = _sibling.nextSibling;
		}
		return _preSiblings.reverse().concat(_nextSiblings);
	},

	/**
	 * 获取当前元素在父元素内的索引
	 * 
	 * @param elem
	 *            DOMElement 指定元素
	 * @return index Number 索引
	 */
	getIndex : function(elem){
		var _i = 0;
		while(elem.previousSibling){
			_i += 1;
			elem = elem.previousSibling;
		}
		return _i;
	},

	/**
	 * 设置当前元素的内部HTML
	 * 
	 * @param elem
	 *            DOMElement 指定元素
	 * @param content
	 *            String 指定HTML
	 */
	html : function(elem,content){
		if(typeof content == "undefined"){
			return elem.innerHTML;
		}else{
			elem.innerHTML = content;
		}
	},

	/**
	 * 返回包含元素自身的HTML
	 * 
	 * @param elem
	 *            DOMElement 指定元素
	 * @return html String html字符串
	 */
	outerHTML : function(elem){
		return elem.outerHTML;
	},

	/**
	 * 显示元素
	 * 
	 * @param elem
	 *            DOMElement 要显示的元素
	 */
	show : function(elem){
		elem.style.display = "block";
	},

	/**
	 * 隐藏元素
	 * 
	 * @param elem
	 *            DOMElement 要隐藏的元素
	 */
	hide : function(elem){
		elem.style.display = "none";
	},

	/**
	 * 设置元素的CSS样式
	 * 
	 * @param elem
	 *            DOMElement 传入元素
	 * @param name
	 *            String 样式名
	 * @param value
	 *            String 样式值
	 */
	css : function(elem,name,value){
		if(typeof value != "undefined"){
			elem.style[name] = value;
		}else{
			return elem.style[name];
		}
	},

	/**
	 * 添加样式
	 * 
	 * @param elem
	 *            DOMElement 添加样式
	 * @param className
	 *            String 样式名
	 */
	addClass : function(elem,className){
		var _trim = MapFactory.String.trim,
			_className = _trim(elem.className);
		if(!_className){
			_className = "";
		}
		if(!_className.match(className)){
			elem.className = _trim(_className + " " + className);
		}
	},

	/**
	 * 移除样式
	 * 
	 * @param elem
	 *            DOMElement 要操作的元素
	 * @param className
	 *            String 样式名
	 */
	removeClass : function(elem,className){
		var _trim = MapFactory.String.trim,
			_className = _trim(elem.className);
		if(_className.match(className)){
			elem.className = _trim(_className.replace(className,""));
		}
	},

	/**
	 * 是否拥有样式
	 * 
	 * @param elem
	 *            DOMElement 要操作的元素
	 * @param className
	 *            String 样式名
	 */
	hasClass : function(elem,className){
		return elem.className && elem.className.match(new RegExp("\\b"+className+"\\b")); 
	},

	/**
	 * 设置和获取元素属性信息
	 * 
	 * @param elem
	 *            DOMElement 要操作的元素
	 * @param name
	 *            String 属性名称
	 * @param value
	 *            String 属性值
	 */
	attr : function(elem,name,value){
		if(typeof value == "undefined"){
			return elem.getAttribute(name) || elem[name];
		}else{
			elem.setAttribute(name,value);
		}
	},

	/**
	 * 移除元素的属性
	 * 
	 * @param elem
	 *            DOMElement 元素
	 * @param name
	 *            String 元素属性名
	 */
	removeAttr : function(elem,name){
		if(elem.removeAttribute){
			elem.removeAttribute(name);
		}else{
			elem[name] = null;
		}
	}
}

MapFactory.Events = {

	/**
	 * 用于存储各个事件的回调函数集 key => value key String 事件名 value Object
	 * 对应事件名的回调函数集合，键为随机ID，值为回调函数
	 */
	_events : {},

	/**
	 * 绑定事件
	 * 
	 * @param elem
	 *            DOMElement元素
	 * @param type
	 *            String 事件类型
	 * @param fn
	 *            Function 事件回调函数
	 */
	bindEvent : function(elem,type,fn){
		if(elem.addEventListener){
			elem.addEventListener(type,fn,false);
		}else if(elem.attachEvent){
			elem.attachEvent("on"+type,fn);
		}
	},

	/**
	 * 移除绑定事件
	 * 
	 * @param elem
	 *            DOMElement元素
	 * @param type
	 *            String 事件类型
	 */
	unbindEvent : function(elem,type){
		
	},

	/**
	 * 鼠标滑轮事件
	 */
	onMouseWheel : function(elem,fn){
		this.bindEvent(elem,"mousewheel",function(e){
			var flag = fn(e);
			if(flag == false){
				MapFactory.Events.stop(e);
			}
		});
		this.bindEvent(elem,"DOMMouseScroll",function(e){
			e.wheelDelta = -(e.detail * 40);
			var flag = fn(e);
			if(flag == false){
				MapFactory.Events.stop(e);
			}
		});
	},

	/**
	 * 停止事件冒泡
	 */
	stop : function(event,allowDefault){
		if(!allowDefault){
			if(event.preventDefault){
				event.preventDefault();
			}else{
				event.returnValue = false;
			}
		}

		if(event.stopPropagation){
			event.stopPropagation();
		}else{
			event.cancelBubble = true;
		}
	}
}

MapFactory.Browser = {
	/**
	 * 是否IE
	 * 
	 * @return Boolean
	 */
	isIE : function(){
		return !!(document.all || window.ActiveXObject);
	},

	/**
	 * 是否Chrome
	 * 
	 * @return Boolean
	 */
	isChrome : function(){
		return navigator.userAgent.indexOf("Chrome") > 0;
	},

	/**
	 * 是否FireFox
	 * 
	 * @return Boolean
	 */
	isFireFox : function(){
		return navigator.userAgent.indexOf("Firefox") > 0;
	}
}