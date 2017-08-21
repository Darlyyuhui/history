MapFactory.Define("MapFactory/GraphicManager",[
	"MapFactory/GraphicManagerAPI*",
	"MapFactory/GeometryType*",
	"MapFactory/LayerManager",
	"MapFactory/GeometryUtil",
	"MapFactory/Geometry*"
],function(api,gType,layerManager,geoUtil,geoClass){
	return function(id,conf){
		var _conf = {
			geo : "",
			symbol : "",
			attributes : ""
		},
		geo = null,sym = null,attr = {},g = null,
		graphicID = "",geoType = "";	
		
		var _mapEngine = MapFactory._MapManagerResource[MapFactory.Engine];
		var _geoUtil = geoUtil();		
		if(MapFactory.VariableTypes.isString(id)){
			graphicID = id;
			g = MapFactory._MapGraphicResource[graphicID];	
			var map = g.getMap();				
			if(!map){
				g.setMap(_mapEngine);
			}
			if(g["attributes"]){
			   attr= g.attributes;
			} 
			if(g instanceof BMap.Marker){
				var icon=g.getIcon();				   
				var label=g.getLabel();
				geoType = gType.POINT;
				geo=g.getPosition();
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
			   }else{							
				geo = g;			
			    }													
			 if(geo instanceof BMap.Polyline){
				geoType = gType.POLYLINE;
				sym={outLineColor:g.getStrokeColor(),outlineOpacity:g.getStrokeOpacity(),outLineWidth:g.getStrokeWeight(),outlineStyle:g.getStrokeStyle()};
			}else if(geo instanceof BMap.Polygon){
				geoType = gType.POLYGON;
				sym={outLineColor:g.getStrokeColor(),outlineOpacity:g.getStrokeOpacity(),outLineWidth:g.getStrokeWeight(),outlineStyle:g.getStrokeStyle(),backgroundColor:g.getFillColor(),backgroundOpacity:g.getFillOpacity()};
			}
		}else{
			conf = id;
			graphicID = MapFactory.GenerateID();
		}		
		MapFactory.Extend(_conf,conf);
		if(!g){
			if(_conf.geo){
				if(MapFactory.VariableTypes.isString(_conf.geo)){
					var _geom = MapFactory.JSON.Parse(_conf.geo);
					geo = _convertGeostrtoGeo(_geom);
					geoType = _geom.type;
				}
				if(MapFactory.VariableTypes.isObject(_conf.geo)){
					geo = _convertGeostrtoGeo(_conf.geo);
					geoType = _conf.geo.type;
				}
			}
			if(geo){
				if(_conf.attributes){
					attr = _conf.attributes;
				}
				if(_conf.symbol){
					sym=_conf.symbol;									
				 }												
			}
		}
		_setCoverageSym();//自动执行-------
        function _setCoverageSym(){ 
            debugger;         	
        	 if(geoType==gType.POINT){ 
        		     g=new BMap.Marker(geo);
        		 var icon=new BMap.Icon(sym.url); 
        		     //icon.anchor=new BMap.Size(10, 40);
        		     icon.anchor=new BMap.Size((sym.width-sym.xOffset)/2, sym.height);
        		     icon.size=new BMap.Size(sym.width, sym.height+sym.yOffset);
        		     //icon.imageOffset=new BMap.Size(-17, -30);
        		     icon.imageOffset=new BMap.Size(0, 0);
        		     icon.imageUrl=sym.url;
        		    // icon.infoWindowAnchor=new BMap.Size(5,5);
        		     g.setIcon(icon);
        		     //g.setOffset(new BMap.Size(sym.xOffset,sym.yOffset));
        		  if(sym.text){        		    	
        		    var label=new BMap.Label(sym.text);       	
        				label.setOffset(new BMap.Size(sym.xOffset,sym.yOffset));//获取文本标注和位置的关系------
        		        label.setStyle({fontFamily:sym.textFontFamily,fontStyle:sym.textStyle,fontWeight:sym.textFontFamily,color:sym.textColor});  
        		         g.setLabel(label);
        		     }
        		   //g.enableDragging();
        	  }
             if(geoType==gType.POLYLINE){       		 
            	 g=geo;
            	 if(sym){
            	    g.setStrokeColor(sym.outLineColor); //设置折线的颜色
            	    g.setStrokeOpacity(sym.outlineOpacity);//设置透明度，取值范围0 - 1。
            	    g.setStrokeWeight(parseInt(sym.outLineWidth));//设置线的宽度，范围为大于等于1的整数。
            	    g.setStrokeStyle(sym.outlineStyle);//设置是为实线或虚线，solid或dashed。
            	 }
            	// g.enableEditing();//开启编辑功能
        	 }
             if(geoType==gType.POLYGON){      		 
            	 g=geo;
            	 if(sym){
            	 g.setStrokeColor(sym.outLineColor);//设置多边型的边线颜色，参数为合法的CSS颜色值。
            	 g.setFillColor(sym.backgroundColor);//设置多边形的填充颜色，参数为合法的CSS颜色值。当参数为空字符串时，折线覆盖物将没有填充效果
            	 g.setStrokeOpacity(sym.outlineOpacity);//设置多边形的边线透明度，取值范围0 - 1。
            	 g.setFillOpacity(sym.backgroundOpacity);//设置多边形的填充透明度，取值范围0 - 1。
            	 g.setStrokeWeight(parseInt(sym.outLineWidth));//设置多边形边线的宽度，取值为大于等于1的整数。
            	 g.setStrokeStyle(sym.outlineStyle);//设置多边形边线样式为实线或虚线，取值solid或dashed。
            	 }
            	// g.enableEditing();//开启编辑功能
       	     }
                g.id = graphicID;
				g.attributes=attr;        	
         }		
        //解析字符串-------
		function _convertGeostrtoGeo(gStr){			
			return geoUtil().convertFromMapFactory(gStr);
		}		
		//获取要素几何字符串-------
		function getGeometryString(){
			if(geo.x && geo.y){
				return geo.x+","+geo.y;
			}
			if(geo.getPath()){
				return _convertArrToPoints(geo.getPath());
			}
			return "";
		}
		
		function _convertArrToPoints(points){
			var _points = "";			
			for(var index in points){
				if(index!=0){
					_points += ",";
				}
				_points +=points[index].lng+","+points[index].lat;
			}
			return _points;
		}		
		
/*
 * 返回要素类型
 * */		
		function getGeometryType(){
			return geoType;
		}

		function getAttributes(){
			return attr;
		}		
		/*
		 * 实现添加到地图上-----
		 * */
		function addToLayer(layerid){				
			   MapFactory._MapGraphicResource[graphicID] = g;
			if(MapFactory._layermanager[layerid]){
				MapFactory._layermanager[layerid].features.push(g);
			} else{
			    MapFactory._layermanager[layerid]={layerid:layerid,features:[],visibility:true}; 
			    MapFactory._layermanager[layerid].features.push(g);				
			}	
			  _mapEngine.addOverlay(g);
			if(MapFactory.VariableTypes.isFunc(MapFactory._layermanager[layerid]["clickfunc"])){
				g.addEventListener("click",function(event){					      
				      var _geometry=_geoUtil.convertFromObject(geo);
				      MapFactory._layermanager[layerid]["clickfunc"]({
						layerid : layerid,
						graphic:{
							id : g.id,
							geo : _geometry,
							attributes : g.attributes
						},
						mapPoint : _geometry
					})						   						   
			   });				
			}		
			if(MapFactory.VariableTypes.isFunc(MapFactory._layermanager[layerid]["mousedownfunc"])){
				g.addEventListener("mousedown",function(evt){					      
				      var _geometry=_geoUtil.convertFromObject(geo);
				      MapFactory._layermanager[layerid]["mousedownfunc"]({
				    	    button : evt.button,
							which : evt.which,
							graphic : {
								id :  g.id,
								geo : _geometry,
								attributes : g.attributes
							},
							screenX : evt.screenX,
							screenY : evt.screenY,
							offsetX : evt.offsetX,
							offsetY : evt.offsetY,
							clientX : evt.clientX,
							clientY : evt.clientY
					})						   						   
			   });				
			}			
			 
		}

		//从地图上删除------
		function remove(){
			var map = g.getMap();				
			if(!map){
				return;
			}
			var gIndex,arrayUtil = MapFactory.Array;
			
			for(var elem in MapFactory._layermanager){
				var features=MapFactory._layermanager[elem].features;
				gIndex = arrayUtil.inArray(g,features);
				if(gIndex){
					arrayUtil.removeItem(g,features);					
					break;
				}				
			}
			delete MapFactory._MapGraphicResource[graphicID];
			_mapEngine.removeOverlay(g);
		}
/*  获取要素中心点  */
		function getCenter(){
			if(gType.POINT==geoType){
				return new geoClass({
					type : gType.POINT,
					points : geo.lng + "," + geo.lat
				});
			}else{
				var centerP = geo.getBounds().getCenter();
				return new geoClass({
					type : gType.POINT,
					points : centerP.lng + "," + centerP.lat
				});
			}
		}
/*
 * 返回要素几何范围-------
 * */
		function getExtent(){
			if(geoType==gType.POINT){
				return null;
			}
			var extent = geo.getBounds();
			return {
				minX : extent.getSouthWest().lng,
				minY : extent.getSouthWest().lat,
				maxX : extent.getNorthEast().lng,
				maxY : extent.getNorthEast().lat
			};
		}

		
		//返回要素的对象表达式-----
		function getGraphic(){
			var graphic = MapFactory._MapGraphicResource[graphicID];
			if(graphic){
				return {
					id : graphicID,
					geo : geoUtil().convertFromObject(geo),
					attributes :attr
				}
			}else{
				return null;
			}
		}
            /*获取graphic所在的图层id*/
		function getLayerID(){	
			var layerid="";
			var map = g.getMap();				
			if(!map){
			  return;
			}
			var gIndex,arrayUtil = MapFactory.Array;
			for(var elem in MapFactory._layermanager){
				var features=MapFactory._layermanager[elem].features;
				gIndex = arrayUtil.inArray(g,features);
				if(gIndex){
					layerid=elem;
					break;
				}
				
			}			
			return layerid;
		}
		
       /*显示要素*/
		function show(){
			g.show();
		}
		  /*隐藏要素*/
		function hide(){
			g.hide();
		}
        /*暂时无法实现*/
		function highlight(){
			
		}

		
		function clearAllHighlight(){
			
		}

		return eval(MapFactory.GenerateAPI(api));
	}
});