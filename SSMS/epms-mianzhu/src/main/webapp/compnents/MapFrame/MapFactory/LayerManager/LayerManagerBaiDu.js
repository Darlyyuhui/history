MapFactory.Define("MapFactory/LayerManager",[
	"MapFactory/LayerManagerAPI*",
	"MapFactory/Geometry*",
	"MapFactory/GeometryType*",
	"MapFactory/Graphic*",
	"MapFactory/GeometryUtil"
],function(api,Geometry,GeometryType,Graphic,GeometryUtil){
	var _eventHandlers = {
			"mouseClick" : {},
			"visibleChanged" : {},
			"mouseDown" : {},
			"mouseOver" : {},
			"mouseOut" : {}
		};
	return function(id,conf){
		var _layerid = "",
		    _config={},
		    _mapHandler = MapFactory._MapManagerResource[MapFactory.Engine],
		    _layerHandler = null;
		    _layerid=id;
		var _geoUtil=GeometryUtil();
		var _variableTypes = MapFactory.VariableTypes;
		 MapFactory.Extend(_config,conf);
		 if(!MapFactory.VariableTypes.isNull(_layerid)){			 
			 _layerHandler=MapFactory._layermanager[_layerid];		 
		 }						
		 if(MapFactory.VariableTypes.isUndefined(_layerHandler)){			 
			 MapFactory._layermanager[_layerid]=_layerHandler={layerid:_layerid,features:[],visibility:true}; 			 
		 }
		function getId(){
			return _layerHandler.layerid;
		}
		function getDomId(){
			return _layerHandler.layerid;
		}
		/*图层显示*/
		function show(){
			if( _layerHandler.visibility){				
				return;
			}
			   var markers=_layerHandler.features;
			   for(var index in markers){
				   var marker=markers[index];
				       marker.show();				   				   
			   }
			   _layerHandler.visibility=true;
		}
		/*图层隐藏*/
		function hide(){
			if( !_layerHandler.visibility){				
				return;
			}
			   var markers=_layerHandler.features;
			   for(var index in markers){
				   var marker=markers[index];
				       marker.hide();				   				   
			   }
			   _layerHandler.visibility=false;
		}
		/*图层清除*/
		function clear(){
			var markers = _layerHandler.features;
			for(var index in markers){
				_mapHandler.removeOverlay(markers[index]);
			}

			_layerHandler.features=[];		
		}
		/*图层是否可见*/
		function isVisible(){
			return _layerHandler.visibility;
		}
		/*图层从地图中删除*/
		function removeFromMap(){
			clear();
		   delete MapFactory._layermanager[_layerid];		   
		}
          //模拟图层单击事件
		function addOnClickEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			_layerHandler.clickfunc=func;
			 var features=_layerHandler.features;
			   for(var index in features){
				   var feature=features[index];				  
				   (function(i){
					   var _geometry=null;
					   features[i].addEventListener("click",function(event){
						      var geometry=event.point;						      
						      var _geometry=_geoUtil.convertFromObject(geometry);
						   func({
								layerid : _layerid,
								graphic : {
									id :  features[i].id,
									geo : _geometry,
									attributes : features[i].attributes
								},
								mapPoint : _geometry
							})						   						   
					   });
			        })(index);			        				   			   				   
			   }			
		}		
		
		function addVisibleChangeEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _evtId = MapFactory.GenerateID();
			if(!_eventHandlers["visibleChanged"][_evtId]){
				_eventHandlers["visibleChanged"][_evtId] = {};
			}
			_eventHandlers["visibleChanged"][_evtId]["func"] = func;
			_eventHandlers["visibleChanged"][_evtId]["layerid"] = _layerid;
			return _evtId;
		}
		function addMouseDownEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			_layerHandler.mousedownfunc=func;
			 var features=_layerHandler.features;
			   for(var index in features){
				   var feature=features[index];				  
				   (function(i){
					   var _geometry=null;
					   features[i].addEventListener("mousedown",function(evt){
						      var geometry=evt.point;						      
						      var _geometry=_geoUtil.convertFromObject(geometry);
						   func({
							    button : evt.button,
								which : evt.which,
								graphic : {
									id :  features[i].id,
									geo : _geometry,
									attributes : features[i].attributes
								},
								screenX : evt.screenX,
								screenY : evt.screenY,
								offsetX : evt.offsetX,
								offsetY : evt.offsetY,
								clientX : evt.clientX,
								clientY : evt.clientY
							})						   						   
					   });
			        })(index);			        				   			   				   
			   }	
		}
		function addMouseOverEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _evtId = MapFactory.GenerateID();
			if(!_eventHandlers["mouseOver"][_evtId]){
				_eventHandlers["mouseOver"][_evtId] = {};
			}
			_eventHandlers["mouseOver"][_evtId]["func"] = func;
			_eventHandlers["mouseOver"][_evtId]["layerid"] = _layerid;
			return _evtId;
		}

	
		function addMouseOutEvent(func){
			if(!_variableTypes.isFunc(func)){
				return;
			}
			var _evtId = MapFactory.GenerateID();
			if(!_eventHandlers["mouseOut"][_evtId]){
				_eventHandlers["mouseOut"][_evtId] = {};
			}
			_eventHandlers["mouseOut"][_evtId]["func"] = func;
			_eventHandlers["mouseOut"][_evtId]["layerid"] = _layerid;
			return _evtId;
		}

		function removeEvent(evtId){
			if(!_variableTypes.isString(evtId)){
				return;
			}
			for(var elem in _eventHandlers){
				if(_eventHandlers[elem][evtId]){
					delete _eventHandlers[elem][evtId];
					break;
				}
			}
		}
		
		/*获取图层中的所有要素  测试通过*/
		function getFeatures(){
			var _features = _layerHandler.features,
				_newFeatures = [];
			if(!_features){
				return _newFeatures;
			}			
			for(var i in  _features){
				var feature=_features[i];
			    var geom=null;
				if(feature instanceof BMap.Marker){
					geom=feature.getPosition();					
				} else{
					geom=feature;					
				}
				var geo = _geoUtil.convertFromObject(geom);
				_newFeatures.push({
					id : feature.id,
					geo : geo,
					attributes : feature.attributes,
					symbol : _getSymbolFromFeature(feature)
				});
			}
			return _newFeatures;
		}
		//获取要素符号------
		 function _getSymbolFromFeature(feature){
			      var sym={};
			 if( feature instanceof BMap.Marker){
					var icon=feature.getIcon();				   
					var label=feature.getLabel();
					var anchoroffset=icon.anchor?icon.anchor:new BMap.Size(0,0);//图标的定位点相对于图标左上角的偏移值。
					var size=icon.size?icon.size:new BMap.Size(0,0);//图标可视区域的大小。
					var imageOffset=icon.imageOffset?icon.imageOffset:new BMap.Size(0,0);//图标所用的图片相对于可视区域的偏移值
					var url=icon.imageUrl//图标所用图像资源的位置
					var infoWindowAnchor=icon.infoWindowAnchor?icon.infoWindowAnchor:new BMap.Size(0,0);//信息窗口开启位置相对于图标左上角的偏移值。
	                    sym={width:size.width,height:size.height,url:url,xOffset:anchoroffset.width,yOffset:anchoroffset.height};
					var labeloffset,text,style;
					if(label){
						 labeloffset=label.getOffset();//获取文本标注和位置的关系------
						 text=label.getContent();//获取文本标注的内容----
					     style=label.getStyle();//设置文本标注样式	
					     sym.text=text;
					     sym.textFontFamily=style.fontFamily?style.fontFamily: "黑体";
					     sym.textStyle=style.fontStyle?style.fontStyle:"";
					     sym.textWeight=style.fontWeight?style.fontWeight:"";
					     sym.textColor=style.color?style.color:"";
					     sym.xOffset=labeloffset?labeloffset.width:0;				     
					     sym.yOffset=labeloffset?labeloffset.height:0;
					}				
				   }												
				 if(feature instanceof BMap.Polyline){				
					sym={outLineColor:feature.getStrokeColor(),outlineOpacity:feature.getStrokeOpacity(),outLineWidth:feature.getStrokeWeight(),outlineStyle:feature.getStrokeStyle()};
				}
				 if(feature instanceof BMap.Polygon){
					sym={outLineColor:feature.getStrokeColor(),outlineOpacity:feature.getStrokeOpacity(),outLineWidth:feature.getStrokeWeight(),outlineStyle:feature.getStrokeStyle(),backgroundColor:feature.getFillColor(),backgroundOpacity:feature.getFillOpacity()};
				}
			   return sym;			 
		 }
		
           /*属性过滤---------------*/
		function filterByAttr(condition,isremove){
			
			
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});