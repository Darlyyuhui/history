/**   
 *     
 * @类描述：用DrawFeature方法绘制几何体并填写属性，利用事务方式将几何体提交给服务器并添加到图层中
 * @创建人：wl   
 * @创建时间：2013-10-23 
 * @修改人： 
 * @修改时间：   
 * @修改备注：   
 * @version  1.0
 *    
 */
var OLAddFeature=function(){	
	return function  (config){
		//定义query构造体的参数
		var _conf ={
				//输入  Geoserver的服务地址，  如:"http://193.169.100.111:8080/geoserver/wfs", 
				url : "",
				//输入WFS服务所对应的数据的工作空间  ，如："topp"
				workspaces :"",
				//输入wfs服务图层名称   ，如：'topp:XIANGXUN_jinrong'
				layerName:"",
				//传入图层对应的几何体字段名称
				geo:"",
			   //传入要素的几何体
			    addGeometry:{},	
			    //传入要素的属性字段
			    attributes:[],
			    //传入要素的属性字段对应属性值
			    attributesValue:[],			 
		 }
		var url,xmlPara,Resoultstutus;
		configDialog(config);
		initAddFeature(_conf);
		// 配置参数：用用户传进来的参数复写默认参数
		function configDialog(confObj){
			// 设置参数
			if( typeof confObj != "undefined") {
				for(var elem in _conf) {
					if( typeof confObj[elem] != "undefined"){ //&& confObj[elem] !== "") {
						_conf[elem] = confObj[elem];
					}
				}
				confObj = null;
			}
			return api;
		}
	
		/**
		 * @param 
		 * @return   
		 * @throws Exception  
		 * @explain 
		 */
		
		function initAddFeature(_conf){
			var geo,workspaces,layerName,addGeometry,geometryType,the_geomString,attributes,attributesValue,attributes_Value_String,attributeslenth,Vertices;
			if(typeof _conf != "object"){
				return null
			}
			 else {
				 if(_conf.url!=""){
			 		url=_conf.url;
				  }
			 	  if(_conf.workspaces!=""){
				  	workspaces=_conf.workspaces;
				  }
			 	 if(_conf.layerName!=""){
			 		layerName=_conf.layerName;
				   }
			 	 if(_conf.geo!=""){
			 		geo=_conf.geo;
					   }
			 	if(_conf.addGeometry!=""){
			 		//判断传入的几何体类型，对于不同的几何体类型做不同的处理
			 		addGeometry=_conf.addGeometry.addGeo;
			 		geometryType=addGeometry["CLASS_NAME"];
					//alert("几何体类型:"+geometryType);
			 	    Vertices=addGeometry.getVertices();
				 		if(geometryType=="OpenLayers.Geometry.Collection"){
				 			return "暂不支持collection类型"; 
				 		}
		                if(geometryType=="OpenLayers.Geometry.Curve"){
		                	return "暂不支持curve类型";
				 		}
				 		if(geometryType=="OpenLayers.Geometry.LinearRing"){
				 			return "暂不支持LinearRing类型";
				 		}
		                if(geometryType=="OpenLayers.Geometry.LineString"){
		                	 var   xyString="" ;
		                     //解析坐标数据,直接把数据传入coordinates中                
		                       for(var i=0;i<Vertices.length;i++){
		                         var zz= Vertices[i].x+","+Vertices[i].y+" ";
		                     	  var xyString =xyString+zz;
		                         }		                	
		                	the_geomString ="<"+workspaces+":"+geo+">" 
		                    +"<gml:MultiLineString srsName='http://www.opengis.net/gml/srs/epsg.xml#4326'>"
		                    +"<gml:lineStringMember>"
		                    +"<gml:LineString>"
		                    +" <gml:coordinates decimal='.' cs=',' ts=' '>  "                        
		                    +xyString
		                    +"</gml:coordinates>"
		                    +"</gml:LineString>"
		                    +"</gml:lineStringMember>"
		                    +"</gml:MultiLineString>"
		                    +"</"+workspaces+":"+geo+">"  		                
				 		}
				 		if(geometryType=="OpenLayers.Geometry.MultiLineString"){
				 			return "暂不支持MultiLineString类型";
				 		}
		                if(geometryType=="OpenLayers.Geometry.MultiPoint"){
		                	return "暂不支持MultiPoint类型";
				 		}
		                if(geometryType=="OpenLayers.Geometry.MultiPolygon"){
		                	return "暂不支持MultiPolygon类型";
				 		}	
		                if(geometryType=="OpenLayers.Geometry.Point"){
		                	 var   xyString="" ;
		                     //解析坐标数据,直接把数据传入coordinates中                
		                     for(var i=0;i<Vertices.length;i++){
		                         var zz= Vertices[i].x+","+Vertices[i].y+" ";
		                        var  xyString =xyString+zz;
		                        }
		                     the_geomString ="  <"+workspaces+":"+geo+">"                                 
	                           +"<gml:Point srsName='http://www.opengis.net/gml/srs/epsg.xml#4326'>"
	                           +" <gml:coordinates decimal='.' cs=',' ts=' '>  "                        
	                           +xyString
	                           +"</gml:coordinates>"
	                           +"</gml:Point>"                             
	                           +"</"+workspaces+":"+geo+">"  	
		                	
				 		}	
				 		if(geometryType=="OpenLayers.Geometry.Polygon"){
				 			var   xyString="" ;
				            //解析坐标数据,直接把数据传入coordinates中                
				            for(var i=0;i<Vertices.length;i++){
				                 var zz= Vertices[i].x+","+Vertices[i].y+" ";
				               	 var xyString =xyString+zz;
				                }
				             xyString=xyString+Vertices[0].x+","+Vertices[0].y+" ";
				             the_geomString ="  <"+workspaces+":"+geo+">" 
				             +"<gml:MultiPolygon srsName='http://www.opengis.net/gml/srs/epsg.xml#4326'>"
				             +"<gml:polygonMember>"
				             +"<gml:Polygon>"
				             +"<gml:outerBoundaryIs>"
				             +"<gml:LinearRing>"
				             +" <gml:coordinates decimal='.' cs=',' ts=' '>  "                        
				             +xyString 
				             +"</gml:coordinates>"
				             +"</gml:LinearRing>"
				             +"</gml:outerBoundaryIs>"
				             +"</gml:Polygon>"
				             +"</gml:polygonMember>"
				             +"</gml:MultiPolygon>"
				             +"</"+workspaces+":"+geo+">"  	
				 		}		
				  }
			 	if(_conf.attributes!=""){
			 		attributes=_conf.attributes;
				  }
			 	if(_conf.attributesValue!=""){
			 		attributesValue=_conf.attributesValue;
				  }
			 	//循环将字段和字段对应的属性值按照ogc规范写入字符串
			 	attributes_Value_String="";
			    	for(var i=0;i<attributesValue.length;i++){			 		
			 		attributes_Value_String=attributes_Value_String+" <"+attributes[i]+">"+attributesValue[i]+"</"+attributes[i]+">";			 		
			    	}			 	
			    	xmlPara ="<wfs:Transaction service='WFS' version='1.0.0'  "
			            +"xmlns:wfs='http://www.opengis.net/wfs' "
			            +"xmlns:"+workspaces+"='http://www.openplans.org/"+workspaces +"' "
			            +"xmlns:gml='http://www.opengis.net/gml'  "
			            +"xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'  "
			            +"xsi:schemaLocation='http://www.opengis.net/wfs http://schemas.opengis.net/wfs/1.0.0/WFS-transaction.xsd http://www.openplans.org/"+workspaces+"  "+url+"/DescribeFeatureType?typename="+layerName+"'>"  
			            + "<wfs:Insert>"   
			            +" <"+layerName+">" 
			            +the_geomString 
			            +attributes_Value_String       
			            +" </"+layerName+">"
			            + "</wfs:Insert>"  
			            + "</wfs:Transaction>";  
					}
  	    	}

		/**
		 * @param 
		 * @return   
		 * @throws Exception  
		 * @explain 
		 */			  
		 var _callback;
		function executeAddFeature(callback){
			_callback = callback;
			//alert(xmlPara);
			  var request = OpenLayers.Request.POST({  
	                 url : url,  
	                 data : xmlPara,           
	                 callback : showStatus
	             });  
			
		}	
		//所有query类的公共变量，方法在这里声明
		 function showStatus(status){    
	           Resoultstutus=status.statusText;  
	           if(typeof _callback != "undefined" && _callback != null)_callback(Resoultstutus);
	           return Resoultstutus;
			  } 
		var api={
				url:url,
				xmlPara:xmlPara,
				Resoultstutus:Resoultstutus,
				configDialog:configDialog,
				initAddFeature:initAddFeature,			
				executeAddFeature:executeAddFeature,
				showStatus:showStatus
		};
	return api;
}	
	
}();
/*
 * 调用demo  注意字段和字段值的对应关系
 *  var add=new AddFeature({
 //在这里配置查询初始化参数，_conf
	
});
//调用AddFeature
  var status=add.executeAddFeature();
 * 
 * 
 */