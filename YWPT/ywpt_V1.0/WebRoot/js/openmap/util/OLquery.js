/**   
 *     
 * @类描述：   该类目前支持输入属性字段，根据不同的逻辑计算出符合查询添加的数据 ; 输入字段为单一字段，输出字段为多字段，多字段输入后期完善
 * @创建人：wl   
 * @创建时间：2013-10-23 
 * @修改人： 
 * @修改时间：   
 * @修改备注：   
 * @version  1.0
 *    
 */
var OLQuery=function(){		
	return function  (config){
			//定义query构造体的参数
			var _conf ={
			    //输入  Geoserver的服务地址，  如:"http://193.169.100.111:8080/geoserver/wfs", 
				url : "",
				//输入WFS服务所对应的数据的工作空间  ，如："topp"
				workspaces :"",
				//输入wfs服务图层名称   ，如：'topp:XIANGXUN_jinrong'
				layerName:"",
			   //输入查询的属性字段    ，如：'topp:LEVEL_'：用要素等级查询
			   Attribute:"",		
			   //输入查询的属性字段对应的值  如：4
			   AttributeValue:"",	
			   //输入查询 类型,默认为 "LIKE"  具体解释如下：
			 //   EQUAL_TO = “==” ;NOT_EQUAL_TO = “!=”;LESS_THAN = “<”;GREATER_THAN = “>”;LESS_THAN_OR_EQUAL_TO = “<=”;
			  //  GREATER_THAN_OR_EQUAL_TO = “>=”;BETWEEN = “..”;LIKE = “~”;IS_NULL = “NULL”;
		       queryType:"LIKE",
		       //返回字段之间用逗号隔开 sample：name,type...... 默认有the——geom    注意：通过一般workspace把shp发布后的字段前面命名空间，放在postgis中的数据没有命名空间
		       outAttributes:["geom"],
		       //返回值类型，目前支持graphic和gml两种格式，默认为graphic
		       returnType:"Feature"
			}
			var xmlPara,url;
			configQuery(config);
			initQuery(_conf);
		
			// 配置参数：用用户传进来的参数复写默认参数
			function configQuery(confObj){
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
			function initQuery(_conf){					
				var  workspaces,layerName,Attribute,AttributeValue,queryType,outAttributes,outAttributesStr;
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
					if(_conf.AttributeValue!=""){
						AttributeValue=_conf.AttributeValue;					
					}
					if(_conf.Attribute!=""){
						Attribute=_conf.Attribute;					
					}
					if(_conf.queryType!=""){
						queryType=_conf.queryType;	
						if(queryType=="EQUAL_TO"){							
							queryType=OpenLayers.Filter.Comparison.EQUAL_TO;
						}
						if(queryType=="NOT_EQUAL_TO"){							
							queryType=OpenLayers.Filter.Comparison.NOT_EQUAL_TO;
						}
						if(queryType=="LESS_THAN"){							
							queryType=OpenLayers.Filter.Comparison.LESS_THAN;
						}
						if(queryType=="GREATER_THAN"){							
							queryType=OpenLayers.Filter.Comparison.GREATER_THAN;
						}
						if(queryType=="LESS_THAN_OR_EQUAL_TO"){							
							queryType=OpenLayers.Filter.Comparison.LESS_THAN_OR_EQUAL_TO;
						}
						if(queryType=="GREATER_THAN_OR_EQUAL_TO"){							
							queryType=OpenLayers.Filter.Comparison.GREATER_THAN_OR_EQUAL_TO;
						}
						if(queryType=="LIKE"){							
							queryType=OpenLayers.Filter.Comparison.LIKE;
							AttributeValue="%"+AttributeValue+"%"
						}
					}					
					if(_conf.outAttributes!=""){
						outAttributes=_conf.outAttributes;				
						outAttributesStr="";					
						  for(var i=0;i<outAttributes.length;i++){							
						 outAttributesStr=outAttributesStr+"<wfs:PropertyName>"+outAttributes[i]+"</wfs:PropertyName>";
						   }
					}
				//判断构造条件是否符合
					if( queryType != "undefined" &&Attribute != "undefined" 
						&&AttributeValue != "undefined"&&workspaces != "undefined" 
						&&outAttributesStr != "undefined" &&url != "undefined" &&layerName != "undefined"){						
					//根据传入的参数构造xml参数
				    var filter =  new OpenLayers.Filter.Comparison({
				        type: queryType,
				        property: Attribute ,
				        value: AttributeValue 
				     });                       
			       var filter_1_0 = new OpenLayers.Format.Filter.v1_0_0();   
			       var xml = new OpenLayers.Format.XML();  
			
			        xmlPara ="<wfs:GetFeature service='WFS' version='1.0.0' "
			           +"outputFormat='GML2' "
			           +"xmlns:"+workspaces+"='http://www.openplans.org/"+workspaces +"' "
			           +"xmlns:wfs='http://www.opengis.net/wfs' "
			           +"xmlns:ogc='http://www.opengis.net/ogc' "
			           +"xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' "
			           +"xsi:schemaLocation='http://www.opengis.net/wfs http://schemas.opengis.net/wfs/1.0.0/WFS-basic.xsd'>"  
			           + "<wfs:Query typeName='"+layerName +"' srsName='EPSG:4326' >"   				         
			           +outAttributesStr				    
			           + xml.write(filter_1_0.write(filter))  
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
			function executeQuery(callback){
				_callback = callback;
				
			//	alert(xmlPara);
				var request = OpenLayers.Request.POST({
			        url : url,  
			        data : xmlPara,   	
			    //    async:false,
			        callback : showQuery
			    }); 
			 return api;
		  }			
			function showQuery(req){  
				var queryResoult;	
           		var returnType;				
				if(_conf.returnType!=""){
					returnType=_conf.returnType;
				}							
				//在这里执行post方法向geoserver后太请求数据
				var gml =  new OpenLayers.Format.GML();			
				//判断返回类型
				if(returnType=="Feature"){							
					queryResoult = gml.read(req.responseText);							
				}
				if(returnType=="GML"){
					queryResoult = gml.write(req.responseText);							
			  	}					
				if(typeof _callback != "undefined" && _callback != null)_callback(queryResoult);
				return queryResoult;
	        }
			//所有query类的公共变量，方法在这里声明
		var api={
			    	configQuery:configQuery,			
					initQuery:initQuery,
					executeQuery:executeQuery
					
		};
		return api;
	}	
}();
/*
 次方法调用demo
 var q=new Query({
 //在这里配置查询初始化参数，_conf
	
});
//调用executeQuery
q.executeQuery();

 * 
 * */
