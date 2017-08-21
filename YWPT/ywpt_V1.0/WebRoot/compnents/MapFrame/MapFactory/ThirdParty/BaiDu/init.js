
(function() {
           var jsFiles = [                       
                "js/AreaRestriction_min.js",
                "js/TextIconOverlay_min.js",
                "js/scxnLabel.js",
                "js/DistanceTool_min.js",
                "js/data_Map.js",
                "js/RectangleZoom_min.js",
                "js/ScxnTools.js",
                "js/scxnTabDiv.js",
                "js/DrawingManager.js",
                "js/draw_load.js",
                "js/drawbysvg_load.js",
                "js/drawbyvml_load.js",
                "js/poly_load.js",
                "js/GeoUtils.js", 
                "js/navictrl_load.js",
                "js/map_load.js",
                "js/scommon_load.js",
                "js/panorama_load.js",
                "js/marker_load.js",
                "js/tile_load.js",
                "js/oppc_load.js",
                "js/mapclick_load.js",
                "js/copyrightctrl_load.js",
                "js/fromproduct_load.js",
                "js/hotspot_load.js",
                "js/fromproduct_load.js",
                "js/InfoBox.js"               
            ]; 

        var scriptTags = new Array(jsFiles.length);
        var host ="compnents/MapFrame/MapFactory/ThirdParty/BaiDu/";
        for (var i in jsFiles) {
        	var script = document.createElement('script');
        	script.type = "text/javascript";
        	script.language = "javascript";
        	script.src =host+jsFiles[i];
        	document.getElementsByTagName("head")[0].appendChild(script); 
            
        }          
})();


