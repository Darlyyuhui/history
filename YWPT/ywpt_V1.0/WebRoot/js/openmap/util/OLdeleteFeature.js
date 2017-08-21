/**   
 *     
 * @类描述：   
 * @创建人：wl   
 * @创建时间：2013-10-23 
 * @修改人： 
 * @修改时间：   
 * @修改备注：   
 * @version  1.0
 *    
 */
var OLDelteFeature=function(){	
	return function  (config){
		//定义query构造体的参数
		var _conf ={
				 //输入  Geoserver的服务地址，  如:"http://193.169.100.111:8080/geoserver/wfs", 
				url : "",
				//输入WFS服务所对应的数据的工作空间  ，如："topp"
				workspaces :"",
				//输入wfs服务图层名称   ，如：'topp:XIANGXUN_jinrong'
				layerName:"",
				layerNameNOws:"",
				//传入图层对应的几何体字段名称
				geo:"geom",
			   //根据那个字段删除？
		        FeatureID:""
	  	}
		var xmlPara, geo,url,workspaces,layerName,layerNameNOws,deleteStatus,deleteStatusstr;
		configDialog(config);
		initDelteFeature(_conf);
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
	
		function initDelteFeature(_conf){
		
			if(typeof _conf != "object"){
				return null
			}
			else {
				if(_conf.url!=""){
					url=_conf.url;
				}
				if(_conf.workspaces!=""){
					workspaces=	_conf.workspaces;		
				 }
				if(_conf.layerName!=""){
					layerName=_conf.layerName;
				}
				if(_conf.layerNameNOws!=""){
					layerNameNOws=_conf.layerNameNOws;
				}
				
				if(_conf.FeatureID!=""){
					FeatureID=_conf.FeatureID;
				}
				if(_conf.geo!=""){
					geo=_conf.geo;
				}
			     var layerListeners = {
			    		 featureclick: executeDelete 		    	   
			      }
				 if(map.getLayer("deleteLayer")){
					 map.removeLayer(map.getLayer("deleteLayer"));
			      }	
		       var deleteLayer = new OpenLayers.Layer.Vector("deleteLayer", {
		    	    protocol: new OpenLayers.Protocol.WFS({
		    	        url: url,
		    	        featureType: layerNameNOws,
		    	        geometryName:geo,
		    	        featureNS: "http://www.openplans.org/"+workspaces
		    	    }),
		    	    eventListeners: layerListeners,
		    	    strategies: [new OpenLayers.Strategy.BBOX()]
		    	}); 
			    deleteLayer.id="deleteLayer";
		       map.addLayer(deleteLayer);
		       /*
		         selectControl=new OpenLayers.Control.SelectFeature(
		        		deleteLayer,
		               {
		             	   clickout:true,toggle:false,
		                   multiple:false,hover:false,box:true,
		                   onSelect:executeDelete ,
		                  toggleKey:"ctrlKey",
		                  multipleKey:"shifeKey"
		                 }
		               );	
		       map.addControl(selectControl);
		       selectControl.activate();
		       */
			}
		}

		/**
		 * @param 
		 * @return   
		 * @throws Exception  
		 * @explain 
		 */		
		//var _callback;
		function executeDelete(feature){
		//	FeatureID="TEXT_"
		//	_callback = callback;
			feature=feature.feature;	
			 var FeatureValue=eval("feature.attributes."+FeatureID);;   
			
                 //在这里删除选中的要素
             var filterDelete=  new OpenLayers.Filter.Comparison({
		           type: OpenLayers.Filter.Comparison.EQUAL_TO,		
		           property : FeatureID,		            
		           value: FeatureValue		          
		          });    
            var filter_1_0 = new OpenLayers.Format.Filter.v1_0_0();   
            var xml = new OpenLayers.Format.XML();              
          	xmlPara ="<wfs:Transaction service='WFS' version='1.0.0'  "
              +"  xmlns:cdf='http://www.opengis.net/"+workspaces+"/data'  "
              +"xmlns:ogc='http://www.opengis.net/ogc' "
              +" xmlns:wfs='http://www.opengis.net/wfs' "
              +"xmlns:"+workspaces+"='http://www.openplans.org/"+workspaces +"'>"
              + " <wfs:Delete typeName='"+layerName+  "'>"                                                      
              + xml.write(filter_1_0.write(filterDelete))                             
              + "</wfs:Delete>"  
              + "</wfs:Transaction>";    
          //	alert(xmlPara);
         	  var request = OpenLayers.Request.POST({  
         	      url : url,  
         	      data : xmlPara,  
         	     async  :false,
         	      callback : deleteStatus
         	  });   
			return api;
		}
		  
		function deleteStatus (response){
			deleteStatusstr=response.statusText;
		//	 if(typeof _callback != "undefined" && _callback != null)_callback(deleteStatusstr);
		//	alert(deleteStatusstr);
			OpenLayers.Layer.Vector.refresh(map.getLayer("deleteLayer"));
		//	selectControl.deactivate();
			 return deleteStatusstr;
		} 
		//所有Dletert类的公共变量，方法在这里声明
		var api={
				xmlPara:xmlPara,
				url:url,
				workspaces:workspaces,
				layerName:layerName,
				layerNameNOws:layerNameNOws,
				deleteStatus:deleteStatus,
				configDialog:configDialog,
				initDelteFeature:initDelteFeature,				
				executeDelete:executeDelete,
				deleteStatusstr:deleteStatusstr
				 
		};
	return api;
}	
	
}();
