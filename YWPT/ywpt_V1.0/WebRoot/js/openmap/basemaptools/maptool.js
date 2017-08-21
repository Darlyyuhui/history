
		
		function showbookmarksTool(){
			var bookmarksDialog = new Dialog();
			bookmarksDialog.conf({
				title:'书签',
				content:'<div id="bookmarksDIV"  style="width: 240px !important;"><label for="bookmark">书签名称：</label><input type="text"  id="bookmark"/><br/><ul id="list" style="margin:5px;padding-left:55px"></ul></div>',
				mask:false,
				zindex:1002,
				confirmButton :"获取书签",
				confirmButtonCall :getBookmarks		
			});
			bookmarksDialog.show();
		}		
		
		   var arr=new Array();
		   function getBookmarks(){  
		       var extent=map.getExtent();
		       var bookname=$('#bookmark').val();       
		       var obj={name:bookname,extent:extent};        
		            arr.push(obj);
		          $('#list').append("<li><a href='#' onclick=positionMap('"+obj.name+"');>"+obj.name+"</a></li><br/>");
		       
		     }		        
		    function positionMap(name){         
		           var obj=null;
		           for(var i=0;i<arr.length;i++) {
		            var ob=arr[i];
		            if(ob.name==name){
		                obj=ob;
		                break;           
		              }          
		           }           
		           map.zoomToExtent(obj.extent);                  
		        }		      
		       function printdialogMap(){
		        	Dialog({
						mask: false,
						title : '打印',
						content : "<div style='width:250px;text-align:center;height:80px;line-height:80px;'></div>",
						confirmButton : "量算面积",
						confirmButtonCall :alert("hello")				
					
				}).show();		    	   
		        }		       
		     //地图基本控制开始---------------------
		        function handleMeasurements(event) {		       
		            var geometry = event.geometry;
		            var units = event.units;
		            var order = event.order;
		            var measure = event.measure;  
		            var out = "";
		            var vector=null;
		            if(order == 1 ) {
		                out += "所绘的折线的实际距离为: " + measure.toFixed(3) + " " + units;		              
		                measureControls['line'].deactivate();
		                  var linesymbol=new OpenLayers.Symbolizer.Line();
		                       linesymbol.strokeColor="red";
		                       linesymbol.strokeOpacity="0.5";
		                       linesymbol.strokeWidth=2;
		                      // linesymbol.strokeLinecap="round";,
		                  vector=new OpenLayers.Feature.Vector(geometry,null,linesymbol);               
		            } 
		            if(order == 2 ){		         
		                out += "所绘多边形区域的面积为: " + measure.toFixed(3) + " " + units + "<sup>2</" + "sup>";		                
		                measureControls['polygon'].deactivate();		                
		             var polygonsymbol=new OpenLayers.Symbolizer.Polygon();
		                 polygonsymbol.strokeColor="#ffffff";
		                 polygonsymbol.strokeOpacity=0.5;
		                 polygonsymbol.strokeWidth=1;
		                 polygonsymbol.fillColor="red";
		                 polygonsymbol.fillOpacity=0.5;
		                 vector=new OpenLayers.Feature.Vector(geometry,null,polygonsymbol);                
		            }
		                track_layer.removeAllFeatures();
		                track_layer.addFeatures([vector]);
		            var popup = new OpenLayers.Popup.FramedCloud("featurePopup",
		    		                vector.geometry.getBounds().getCenterLonLat(),
		                             new OpenLayers.Size(100,100),
		                             "<h2>测量结果:</h2>" +
		                               out,
		                             null, true, null);		         
		            map.addPopup(popup);		              
		        }      
		        //切换绘制测量控制器的开关
		        function switchControl(value) {		           
		            for(key in measureControls) {
		                var control = measureControls[key];
		                if(key==value){
		                    control.activate();
		                } else {
		                    control.deactivate();
		                }              
		            }		       
		        }
		        function measureGeometry(){		        	
		        	Dialog({
						mask: false,
						title : '测量',
						content : "<div style='width:250px;text-align:center;height:80px;line-height:80px;'></div>",
						confirmButton : "量算面积",
						confirmButtonCall : switchControl('line'),
						cancelButton : "量算距离",
						cancelButtonCall : switchControl('polygon')
				    }).show();		        	
		        }
		        
		     
		        var sketchSymbolizers = {
		                "Point": {
		                    pointRadius: 5,
		                    graphicName: "square",
		                    fillColor: "red",
		                    fillOpacity: 1,
		                    strokeWidth: 1,
		                    strokeOpacity: 1,
		                    strokeColor: "#333333"
		                },
		                "Line": {
		                    strokeWidth: 3,
		                    strokeOpacity: 1,
		                    strokeColor: "#666666",
		                    strokeDashstyle: "dash"
		                },
		                "Polygon": {
		                    strokeWidth: 2,
		                    strokeOpacity: 1,
		                    strokeColor: "#666666",
		                    fillColor: "white",
		                    fillOpacity: 0.3
		                }
		            };
		            var style = new OpenLayers.Style();
		            style.addRules([
		                new OpenLayers.Rule({symbolizer: sketchSymbolizers})
		            ]);
		            var styleMap = new OpenLayers.StyleMap({"default": style});
		        function addDrawControl(type, callback){	
		        	var drawControl=null;
		        	var geometry;
		        	var option={
	                        persist: true,
	                        handlerOptions: {
	                            layerOptions: {	                         
	                                styleMap: styleMap
	                            }
	                        }
	                    }
		        	if(type=="point"){
		        		drawControl=new OpenLayers.Control.DrawFeature(track_layer,OpenLayers.Handler.Point,option)		        			        		
		        	 }
		        	if(type=="line"){
		        		drawControl=new OpenLayers.Control.DrawFeature(track_layer,OpenLayers.Handler.Path,option);		        			        		
		        	 }
		        	if(type=="polygon"){
		        		drawControl=new OpenLayers.Control.DrawFeature(track_layer,OpenLayers.Handler.Polygon,option);		        			        		
		        	 }
		        	map.addControl(drawControl);
		        	drawControl.activate();
		        	drawControl.featureAdded=  function drawEnd(event){		       			  
				        	 map.removeControl(drawControl);
				        	 drawControl.deactivate();
				        	 geometry = event.geometry;
				        	 callback(geometry);
				        	 track_layer.removeAllFeatures();
				        	 };
		        }
		     