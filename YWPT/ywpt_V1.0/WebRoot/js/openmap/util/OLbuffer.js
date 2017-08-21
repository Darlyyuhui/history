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



var OLBuffer=function(){	
	  var  OLbuffer;
	  var  OLbufferResoult;
	  var geometry;
	return function  (){
			/**
			 * @param bufferURL：
			 * @return  buffer 
			 * @throws Exception  
			 * @explain :输入 geoserver 中 wps 扩展模块下的 bufferURL 地址，默认为：http://demo.opengeo.org/geoserver/wps；
			 *  根据该bufferURL生成OLbuffer并返回该对象。
			 */
			function createBuffer(bufferURL){		
				//创建wps 的 client对象
				 var  client = new OpenLayers.WPSClient({
			         servers: { opengeo: bufferURL }
			      });
			     //创建buffer对象并返回
				 OLbuffer   = client.getProcess('opengeo', 'JTS:buffer');		 
			      return OLbuffer;
			}
			/**
			 * @param distance：
			 * @return  OLbufferResoult 
			 * @throws Exception  
			 * @explain :输入在当前地图单位中需要缓冲的距离，根据该距离生成缓冲后的结果OLbufferResoult并返回。
			 */
			  function executeBuffer(geometry,distance,callback){
				  OLbuffer.execute({
				         inputs: {
				             geom: geometry,
				             distance: distance
				           },
				         success: function(outputs) {
				             //返回一个或者多个graphics
				             OLbufferResoult = outputs.result;
				             callback(OLbufferResoult);
				           }
				       });
				  return OLbufferResoult;				  
			   }
			var api={
					createBuffer:createBuffer,
					executeBuffer:executeBuffer
					
			};
			return api;
	}
	
}();
