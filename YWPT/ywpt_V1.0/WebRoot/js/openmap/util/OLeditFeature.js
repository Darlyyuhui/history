/**   
 *     
 * @类描述：根据初始化参数配置用于编辑的图层（当初始化时wfs图层被默认加载到map中）和ModifyFeature相关控件，利用ModifyFeature修改要素的形状并重新填写要素属性信息，利用事务方式将几何体提交给服务器并添加到图层中
 * @创建人：wl   
 * @创建时间：2013-10-23 
 * @修改人： 
 * @修改时间：   
 * @修改备注：   
 * @version  1.0
 *    
 */
var OLEditFeature=function(){	
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
				layerNameNOws:"",
		        //根据那个字段选择要素
			   FeatureID:""
		}
		var url,geo,workspaces,layerName,layerNameNOws,FeatureID, xmlParaGEO,Editstutus,vectors_wfs,resoultGeometry,xmlPara,filter,
		attributeslenth,ModifyType,Modifycontrol,the_geomString,Vertices,updateValue,editStutus,attkeyValueStr,featureAttStart;
		configDialog(config);
		initEditFeature(_conf);
		var callback=null;
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
		 var editToolBar;
		function createEditToolBar(container){	
			  editToolBar=" <div style='padding:3px; width:110px;/'>"
           	  +" <ul style='list-style:none; padding:0; margin:0;height:24px;'>"
           	//  +"<li  title='开始编辑' style='float:left; margin:1px' onclick='getstartEdit()' id='startEdit'  value='startEdit'><img style='display:block; vertical-align:middle;' src='images/edit-now.png' alt='开始编辑'></li>"
           	  +"<li title='改变大小'   style='float:left; margin:1px' id='resize'   value='resize' ><img style='display:block; vertical-align:middle;'  src='compnents/openmap/img/editToolBar/change.png' alt='改变大小'></li>"		             
           	  +" <li title='创建节点'  style='float:left; margin:1px' id='rotate' value='rotate' ><img style='display:block; vertical-align:middle;'  src='compnents/openmap/img/editToolBar/cjjd.png' alt='创建节点'></li>"
	          +"<li title='平移'   style='float:left; margin:1px'  id='drag'    value='drag'  ><img style='display:block; vertical-align:middle;'  src='compnents/openmap/img/editToolBar/move.png' alt='平移'></li>"
	          +  "<li title='旋转'  style='float:left; margin:1px' id='keepAspectRatio'  value='keepAspectRatio' ><img style='display:block; vertical-align:middle;'  src='compnents/openmap/img/editToolBar/rotate.png' alt='旋转'></li>  " 
	          +  "</ul></div>";			  
			  document.getElementById(container).innerHTML = editToolBar;
			  document.getElementById("resize").onclick = getresize;
			  document.getElementById("rotate").onclick = getrotate;
			  document.getElementById("drag").onclick = getdrag;
			  document.getElementById("keepAspectRatio").onclick = getkeepAspectRatio;
			  return api;
	       }
		   //返回编辑要素的所有信息,具体解析留给调用它的函数处理
		    function getresize(){			        
				  Modifycontrol.mode = OpenLayers.Control.ModifyFeature.RESIZE;	
				  return api;
			 }
			function getrotate(){
	               Modifycontrol.mode = OpenLayers.Control.ModifyFeature.ROTATE;
	              return api;
		    }
			function getdrag(){
				   Modifycontrol.mode = OpenLayers.Control.ModifyFeature.DRAG;
				   return api;
			 }
			 function getkeepAspectRatio(){				
				  Modifycontrol.mode = OpenLayers.Control.ModifyFeature.RESHAPE;
				  return api;
			 }
		/**
		 * @param 
		 * @return   
		 * @throws Exception  
		 * @explain 
		 */
		function initEditFeature(_conf){
				if(_conf.url!=""){
					url=_conf.url;
				}
				if(_conf.workspaces!=""){
					workspaces=	_conf.workspaces;			
				}
				if(_conf.layerName!=""){
					layerName=_conf.layerName;
				}
				if(_conf.layerName!=""){
					layerNameNOws=_conf.layerNameNOws;
				}
				if(_conf.FeatureID!=""){
					FeatureID=_conf.FeatureID;
				}		
				if(_conf.geo!=""){
					geo=_conf.geo;
				}
				
				//初始化modifiedFeature相对应的控件
	            OpenLayers.Feature.Vector.style['default']['strokeWidth'] = '2';
	            //初始化render
	             var renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
	             renderer = (renderer) ? [renderer] : OpenLayers.Layer.Vector.prototype.renderers;
	        	 if(map.getLayer("editLayer")){
					 map.removeLayer(map.getLayer("editLayer"));
			      }	
	              //根据参数定义用于编辑的wfs图层	        
	              editLayer = new OpenLayers.Layer.Vector("editLayer", {
	           	    protocol: new OpenLayers.Protocol.WFS({
	           	        url: url,
	           	        featureType: layerNameNOws,
	           	       geometryName:geo,
	           	        featureNS: "http://www.openplans.org/"+workspaces
	           	    }),
	           	  renderers: renderer,
	           	    strategies: [new OpenLayers.Strategy.BBOX()]
	             	});  	               	
	              editLayer.id="editLayer";
		          //默认将用于编辑的wfs图层添加到地图中
	             map.addLayers([editLayer]);
	            //定义ModifyFeature对应的options
	             var ModifyFeatureOpt = {
                		 onModificationStart:ModificationStart,                			   
                		 onModificationEnd: ModificationEnd
			       };    
		          	//新建Modifycontrol
	               Modifycontrol = new OpenLayers.Control.ModifyFeature(editLayer,ModifyFeatureOpt);                        
	              map.addControl(Modifycontrol);
	              Modifycontrol.activate();//问题：Modifycontrol何时调用销毁方法待定 Modifycontrol.destroy();	 
	              return api;
      		}	    
		function setCallBack(func){
			callback = func;
		}
		   function ModificationStart(features){
			   if(callback){
				   callback(features);
			   }
			   featureAttStart="";
			    featureAttStart=features.attributes;	
				//根据传入的参数构造xml参数
			     filter =  new OpenLayers.Filter.Comparison({
			        type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        property: FeatureID ,
			        value: features.attributes[FeatureID]
			     }); 
			     console.log(filter);
                return features;
            }
           function ModificationEnd(featuresResoult){
           // alert("停止编辑"+e);
             resoultGeometry =  featuresResoult.geometry;
             Vertices=resoultGeometry.getVertices();
             if(resoultGeometry!=""){
			 		//判断传入的几何体类型，对于不同的几何体类型做不同的处理,构造出编辑后的几何体信息放在
			 		geometryType=resoultGeometry["CLASS_NAME"];
			 		//alert("几何体类型:"+geometryType);
			 	//	var the_geomString;
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
		                 the_geomString =  "<wfs:Name>"+geo+"</wfs:Name>"
					          + "<wfs:Value>"
					          +"<gml:MultiLineString srsName='http://www.opengis.net/gml/srs/epsg.xml#4326'>"
					          + "<gml:lineStringMember>"
					          +"<gml:LineString>"
					          +"<gml:coordinates>"
					          +xyString+"</gml:coordinates>"
					          +"</gml:LineString>"
					          +"</gml:lineStringMember>"
					          +"</gml:MultiLineString>"
					          + "</wfs:Value>";                
				 		}
				 		if(geometryType=="OpenLayers.Geometry.MultiLineString"){
				 			return "暂不支持MultiLineString类型";
				 		}
		                if(geometryType=="OpenLayers.Geometry.MultiPoint"){
		                	return "暂不支持MultiPoint类型";
				 		}
		                if(geometryType=="OpenLayers.Geometry.MultiPolygon"){
		                	var   xyString="" ;
				            //解析坐标数据,直接把数据传入coordinates中                
				            for(var i=0;i<Vertices.length;i++){
				                 var zz= Vertices[i].x+","+Vertices[i].y+" ";
				               	 var xyString =xyString+zz;
				                }
				             xyString=xyString+Vertices[0].x+","+Vertices[0].y+" ";
				             the_geomString ="<wfs:Name>"+geo+"</wfs:Name>"
						     +  "<wfs:Value>"
				             +"<gml:MultiPolygon srsName='http://www.opengis.net/gml/srs/epsg.xml#4326'>"
				             +"<gml:polygonMember>"
				             +"<gml:Polygon>"
				             +"<gml:outerBoundaryIs>"
				             +"<gml:LinearRing>"
				             +" <gml:coordinates decimal='.' cs=',' ts=' '>  "                        
				             + xyString
				             +"</gml:coordinates>"
				             +"</gml:LinearRing>"
				             +"</gml:outerBoundaryIs>"
				             +"</gml:Polygon>"
				             +"</gml:polygonMember>"
				             +"</gml:MultiPolygon>"
				             + "</wfs:Value>";  	
				 		}	
		                if(geometryType=="OpenLayers.Geometry.Point"){
		                	 var   xyString="" ;
		                     //解析坐标数据,直接把数据传入coordinates中                
		                     for(var i=0;i<Vertices.length;i++){
		                         var zz= Vertices[i].x+","+Vertices[i].y+" ";
		                        var  xyString =xyString+zz;
		                        }
		                     the_geomString = "<wfs:Name>"+geo+"</wfs:Name>"
		                     +"<wfs:Value> "                               
	                           +"<gml:Point srsName='http://www.opengis.net/gml/srs/epsg.xml#4326'>"
	                           +" <gml:coordinates decimal='.' cs=',' ts=' '>  "                        
	                           +xyString
	                           +"</gml:coordinates>"
	                           +"</gml:Point>"                             
	                           +" </wfs:Value>" 
		                	
				 		}	
				 		if(geometryType=="OpenLayers.Geometry.Polygon"){
				 			var   xyString="" ;
				            //解析坐标数据,直接把数据传入coordinates中                
				            for(var i=0;i<Vertices.length;i++){
				                 var zz= Vertices[i].x+","+Vertices[i].y+" ";
				               	 var xyString =xyString+zz;
				                }
				             xyString=xyString+Vertices[0].x+","+Vertices[0].y+" ";
				             the_geomString ="<wfs:Name>"+geo+"</wfs:Name>"
						     +  "<wfs:Value>"
				             +"<gml:MultiPolygon srsName='http://www.opengis.net/gml/srs/epsg.xml#4326'>"
				             +"<gml:polygonMember>"
				             +"<gml:Polygon>"
				             +"<gml:outerBoundaryIs>"
				             +"<gml:LinearRing>"
				             +" <gml:coordinates decimal='.' cs=',' ts=' '>  "                        
				             + xyString
				             +"</gml:coordinates>"
				             +"</gml:LinearRing>"
				             +"</gml:outerBoundaryIs>"
				             +"</gml:Polygon>"
				             +"</gml:polygonMember>"
				             +"</gml:MultiPolygon>"
				             + "</wfs:Value>";  				             
				 		}		
				  }
         	 return the_geomString;
           }
          
          function updateEdit(attributesKey,attributesValue){
        	  attkeyValueStr="";
        	    updateValue="";
        		//循环将字段和字段对应的属性值按照ogc规范写入字符串
         	    if(attributesKey!=""&&attributesKey!= "undefined"&&attributesValue!=""&&attributesValue!= "undefined"){			 	
         	    	for(var i=0;i<attributesKey.length;i++){			 		
         	    		attkeyValueStr=attkeyValueStr+" <wfs:Name>"+attributesKey[i]+"</wfs:Name>"+"<wfs:Value>"+attributesValue[i]+"</wfs:Value>";			    		
			  /*  		 if(attributesKey[i]==FeatureID){
				    			updateValue=attributesValue[i];
				    		}*/
         	    	}	
				  }
 				 
				    console.log(updateValue);
			    	  var filter_1_0 = new OpenLayers.Format.Filter.v1_0_0();   
				      var xml = new OpenLayers.Format.XML();  
				 //    alert("gml几何体："+the_geomString);
	  			      	xmlParaGEO ="<wfs:Transaction service='WFS' version='1.0.0' "			           
			            +"xmlns:"+workspaces+"='http://www.openplans.org/"+workspaces +"'  "
			            +"xmlns:ogc='http://www.opengis.net/ogc'  "
			            +" xmlns:wfs='http://www.opengis.net/wfs'  "
			            +"xmlns:gml='http://www.opengis.net/gml'  "
			            +"xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'  "
			            +"xsi:schemaLocation='http://www.opengis.net/wfs  http://schemas.opengis.net/wfs/1.0.0/WFS-transaction.xsd'>"
			            +"<wfs:Update typeName='"+layerName+"'>"
			            + " <wfs:Property>"   
			            +the_geomString		         
			            +"</wfs:Property>"
		         	    +xml.write(filter_1_0.write(filter)) 
			            + "</wfs:Update>"
			            + "</wfs:Transaction>";   			     			      
	  			      xmlPara="<wfs:Transaction service='WFS' version='1.0.0' "			           
			            +"xmlns:"+workspaces+"='http://www.openplans.org/"+workspaces +"'  "
			            +"xmlns:ogc='http://www.opengis.net/ogc'  "
			            +" xmlns:wfs='http://www.opengis.net/wfs'>"			           
			            +"<wfs:Update typeName='"+layerName+"'>"
			            + " <wfs:Property>"   
			            +attkeyValueStr			         
			            +"</wfs:Property>"
		         	    +xml.write(filter_1_0.write(filter)) 
			            + "</wfs:Update>"
			            + "</wfs:Transaction>";			      	
  			      return api;
            }
  		/**
		 * @param 
		 * @return   
		 * @throws Exception  
		 * @explain 
		 */			  
		function executeEditFeature(){
		//	 alert(xmlParaGEO);
			 var request = OpenLayers.Request.POST({  
                 url : url,  
                 data : xmlParaGEO,           
                 callback : function showStatus(statusGEO){    
				 editStutus=statusGEO.statusText;  
		//	 alert(xmlPara);
				 var requestAtt = OpenLayers.Request.POST({  
	                 url : url,  
	                 data : xmlPara,           
	                 callback : function showStatus1(statusAtt){   
					 editStutus=statusGEO.statusText;  
						} 
	               });  
				 } 
             });  			
			 
		//	 OpenLayers.Layer.Vector.refresh(map.getLayer("editLayer"));
		 return editStutus;
		}
		//所有query类的公共变量，方法在这里声明
		var api={
				 url:url,
				 workspaces:workspaces,
				 layerName:layerName,
				 FeatureID:FeatureID, 
				 xmlParaGEO:xmlParaGEO,
				 xmlPara:xmlPara,
				 Editstutus:Editstutus,
				 vectors_wfs:vectors_wfs,
				 attkeyValueStr:attkeyValueStr,
				 resoultGeometry:resoultGeometry,
				 attributeslenth:attributeslenth,
				 ModifyType:ModifyType,
				 Modifycontrol:Modifycontrol,
			 	configDialog:configDialog,
			 	initEditFeature:initEditFeature,
			 	createEditToolBar:createEditToolBar,
			 	getresize:getresize,
			 	editToolBar:editToolBar,
			 	getrotate:getrotate,
				getdrag:getdrag,
				editStutus:editStutus,
				getkeepAspectRatio:getkeepAspectRatio,
				ModificationStart:ModificationStart,
				ModificationEnd:ModificationEnd,				
				updateEdit:updateEdit,
				featureAttStart:featureAttStart,
				executeEditFeature:executeEditFeature,
				setCallBack:setCallBack
		};
	return api;
}		
}();
/* 1.var  e=new EditFeature({这里填写构造编辑类实例的时候的参数}); configDialog(config); initEditFeature(_conf);在类中自调用完成数据的加载，控件的配置
 * 2.调用createEditToolBar()来创造EditToolBar控件，把string 类型表示的结果放在div中便能看见编辑看控件
 2.调用ModificationStart，返回要素信息，根据返回的要素信息填写属性信息并保存
 3.把编辑后的要素的属性信息以key-value的方式，中间用逗号隔开，传入到updateEdit(attributesKey,attributesValue)中
 4.调用executeEditFeature执行结果，返回执行状态

*/
