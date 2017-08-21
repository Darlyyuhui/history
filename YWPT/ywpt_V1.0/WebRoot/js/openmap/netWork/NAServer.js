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
 



var OLNAServer=function(){	
	var  writer, reader, stopLayer,routeLayer,handler;
	return function  (){
		function init() {
			routeLayer = new OpenLayers.Layer.Vector("Route Layer",{styleMap:new OpenLayers.StyleMap({
				"default":new OpenLayers.Style({strokeColor: "#ff0000",strokeWidth: 5})})});
			stopLayer = new OpenLayers.Layer.Vector("Stops Layer");
			map.addLayers([routeLayer,stopLayer]);
			map.addControl(new OpenLayers.Control.LayerSwitcher());
			map.addControl(new OpenLayers.Control.MousePosition());
			var out_options = {
				'internalProjection' : map.baseLayer.projection,
				'externalProjection' : map.baseLayer.projection
			};
			writer = new OpenLayers.Format.WKT(out_options)
			var in_options = {
				'internalProjection' : map.baseLayer.projection,
				'externalProjection' : map.baseLayer.projection
			};
			reader = new OpenLayers.Format.WKT(in_options);
			var drawStops = new OpenLayers.Control.DrawFeature(stopLayer,
					OpenLayers.Handler.Point, {
						eventListeners : {
							"featureadded" : function(e) {
								if(handler)
									handler(writer.write(e.feature));
							}
						}
					});

			map.addControl(drawStops);
			
			drawStops.activate();
			
			setHandler("route");
		}
		var stops="";
		var stopnum = 0;
		function route(str){
			stops += (stopnum > 0?"|":"") + str;
			stopnum++;
			if(stopnum > 1){
		$.ajax({
			  type: "POST",
			  url: "openmap/naServer",
			  cache: false,
			  data: {reqUrl:"http://193.169.100.111:8080/geoserver/ows",stopPoint:stops},
			  success: function(ee){
				  routeLayer.removeAllFeatures();
					stopLayer.removeAllFeatures();
					var strs = ee.split("|");
					for ( var i in strs) {
						var features = reader.read(strs[i]);
						routeLayer.addFeatures(features);
					}
				  
			  },
			  error: function(e) {
				  alert("cuowu:"+e);
				  console.log(e);
			  }
			}); 
				stops = "";
				stopnum = 0;
			}
		}
			
		function length(str){
			OpenLayers.Request
			.GET({
				url : "http://193.169.100.185:8080/geoserver/ows?request=getlengths&service=wj.demos.gp&version=1.0.0&geoms="
						+ str,
				"success" : function(ee) {
					alert(ee.responseText);
				}
			});
		}

		function buffer(str) {		
			OpenLayers.Request
					.GET({
						url : "http://193.169.100.185:8080/geoserver/ows?request=getbuffers&service=wj.demos.gp&version=1.0.0&distance=10&geoms="
								+ str,
						"success" : function(ee) {
							var strs = ee.responseText.split("|");
							for ( var i in strs) {
								var features = reader.read(strs[i]);
								plyLayer.addFeatures(features);
							}
						}
					});
		}
		
		var handlers={length:length,buffer:buffer,route:route};
		function setHandler(name){
			handler = handlers[name];
			routeLayer.removeAllFeatures();
			stopLayer.removeAllFeatures();
		}
		
		
			var api={
					init:init,
					route:route,
					length:length,
					buffer:buffer,
					setHandler:setHandler
					
			};
			return api;
	}
	
}();
*/