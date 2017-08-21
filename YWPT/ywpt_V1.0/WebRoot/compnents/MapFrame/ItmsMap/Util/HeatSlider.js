/**
 * @author  胡红勋
 * @Date 2015-5-22
 */
MapFactory.Define("ItmsMap/Util/HeatSlider*",function(){
	return function(){

		/**
		 * 接口
		 */
		var api = {
			show : show,
		}	
		/**
		 * 初始化
		 */
		function show(){
			if($("#heatslider")[0] == null){
				$("body").append("<div style='width:50%;z-index:100000;position:absolute' id='heatslider'><input id='Slider3' type='slider' name='area' width='100%' value='0'/></div>");
			}
			_setPosition();
		}
		

		/**
		 * 设置位置
		 */
		function _setPosition(){
			var left = ($("body").width() - $("#heatslider").width()+ $("#left").width())/2;
			$("#heatslider").css({				
				left : left,
				top : 50 + $("body").scrollTop()
			});	     
		    jQuery("#Slider3").slider({from: 480, to: 1020, step: 15, dimension: '', scale: ['8:00', '9:00', '10:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00'],limits: false, calculate: function(value){
		        var hours = Math.floor(value / 60);
		        var mins = (value - hours*60);
		        return (hours < 10 ? "0"+hours : hours) + ":" + ( mins == 0 ? "00" : mins );
		      }});
		}	
		return api;
	}
});