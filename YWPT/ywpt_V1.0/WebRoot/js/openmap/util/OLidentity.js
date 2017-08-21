/**   
 *     
 * @类描述：   该类目前支持输入属性字段，根据传入的几何体（对与点和线类型必须经过buffer处理后方能传入进行计算）对象来进行空间计算，返回结果
 * @创建人：wl   
 * @创建时间：2013-10-23 
 * @修改人： 
 * @修改时间：   
 * @修改备注：   
 * @version  1.0
 *    
 */
var OLIdentity=function(){		
	return function  (config){
			//定义query构造体的参数
			var _conf ={
			    //输入  Geoserver的服务地址，  如:"http://193.169.100.111:8080/geoserver/wfs", 
				url : "",
				//输入WFS服务所对应的数据的工作空间  ，如："topp"
				workspaces :"",
				//输入wfs服务图层名称   ，如：'topp:XIANGXUN_jinrong'
				layerName:"",
			   //传入查询的几何体
			   identityGEO:{},		
			   //输入查询 类型,默认为 "LIKE"  具体解释如下：
			 //   BBOX,INTERSECTS,DWITHIN,WITHIN,CONTAINS
			   identityType:"INTERSECTS",
		       //返回字段之间用逗号隔开 sample：name,type...... 默认有the——geom
		       outAttributes:["geom"],
		       //返回值类型，目前支持graphic和gml两种格式，默认为graphic
		       returnType:"Feature"
			}
			var xmlPara,url;
			configIdentity(config);
			initIdentity(_conf);
			// 配置参数：用用户传进来的参数复写默认参数
			function configIdentity(confObj){
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
			function initIdentity(_conf){		
				var  workspaces,layerName,identityGEO,distance,identityType,bufferURL,outAttributes,outAttributesStr;
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
					if(_conf.identityType!=""){
						identityType=_conf.identityType;
						if(identityType=="INTERSECTS"){							
							OpenLayers.Filter.Spatial.INTERSECTS
						}
						if(identityType=="DWITHIN"){							
							OpenLayers.Filter.Spatial.DWITHIN
						}
						if(identityType=="WITHIN"){							
							OpenLayers.Filter.Spatial.WITHIN
						}
						if(identityType=="CONTAINS"){							
							OpenLayers.Filter.Spatial.CONTAINS
						}
						if(identityType=="BBOX"){							
							OpenLayers.Filter.Spatial.BBOX
						}						
					}					
					if(_conf.outAttributes!=""){
						outAttributes=_conf.outAttributes;	
						outAttributesStr="";
						  for(var i=0;i<outAttributes.length;i++){							
						     outAttributesStr=outAttributesStr+"<wfs:PropertyName>"+outAttributes[i]+"</wfs:PropertyName>";
						   }
					}
					if(_conf.identityGEO!=""){
						identityGEO=_conf.identityGEO.identityGEO;					
					  }					
					if( url != "undefined" && url != null&&workspaces != "undefined" && workspaces != null
							&&layerName != "undefined" && layerName != null&&identityType != "undefined" && identityType != null
							&&outAttributes != "undefined" && outAttributes != null&&identityGEO != "undefined" && identityGEO != null){					
					//根据传入的参数构造xml参数
				    var filtergeometry =  new OpenLayers.Filter.Comparison({
				        type: identityType,
				        value: identityGEO,
					     projection : "EPSG:4326"
				     });    
				    //因为buffer时异步调用过程，所以把xmlPara在这里构造					 
			       var filter_1_0 = new OpenLayers.Format.Filter.v1_0_0();   
			       var xml = new OpenLayers.Format.XML();   
			    
			        xmlPara ="<wfs:GetFeature service='WFS' version='1.0.0'  "
			           +"outputFormat='GML2' "
			           +"xmlns:"+workspaces+"='http://www.openplans.org/"+workspaces +"'  "
			           +"xmlns:wfs='http://www.opengis.net/wfs' "
			           +"xmlns:ogc='http://www.opengis.net/ogc' "
			           +"xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' "
			           +"xsi:schemaLocation='http://www.opengis.net/wfs  http://schemas.opengis.net/wfs/1.0.0/WFS-basic.xsd'>"  
			           + "<wfs:Query typeName='"+layerName +"' srsName='EPSG:4326' >"   				         
			           +outAttributesStr
			           + xml.write(filter_1_0.write(filtergeometry))  
			           + "</wfs:Query>"  
			           + "</wfs:GetFeature>";    
					 }
				  }
		        }
			
			/**
			 * @param 
			 * @return   
			 * @throws Exception  
			 * @explain 
			 */	
			var _callback;
			function executeIdentity(callback){
				_callback = callback;
			
				//在这里执行post方法向geoserver后太请求数据
				var request = OpenLayers.Request.POST({
			        url : url,  
			        data : xmlPara,           
			        callback : showQuery
			    }); 
			 return api;
		  }
		 function showQuery(req){  		    
				var returnType;
				var identityResoult;
				if(_conf.returnType!=""){
					returnType=_conf.returnType;
				}
				var gml =  new OpenLayers.Format.GML();
				//判断返回类型
				if(returnType=="Feature"){
					identityResoult = gml.read(req.responseText);
				}
				if(returnType=="GML"){
					identityResoult = gml.write(req.responseText);							
			  	}		
				if(typeof _callback != "undefined" && _callback != null)_callback(identityResoult);
				return identityResoult;
	        }
			
			//所有query类的公共变量，方法在这里声明
		var api={
				    configIdentity:configIdentity,			
					initIdentity:initIdentity,
					executeIdentity:executeIdentity	,
					showQuery:showQuery
		};
		return api;
	}	
}();
/*
 次方法调用demo
 var i=new Identity({
 //在这里配置查询初始化参数，_conf
	
});
//调用executeQuery
i.executeIdentity();

 * 
 * */
