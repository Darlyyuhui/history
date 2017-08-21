<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
(function(){
	MapFactory.Require([
		"ItmsMap/Video/Video*"
	],function(Video){
		var video = Video();
		video.showAll();
		$("#videoLocation").click(function(){
			video.videoLocation($("#videoLocationName").val());
		});
	});
})();
	
</script>

<table class="map_table mar_5" style="margin-right:-20px\0;#margin-right:-20px;_margin-right:-20px;">
  <tr>
    <td>设备名称：</td>
    <td><input id="videoLocationName" type="text" style="width:100px;height:28px;padding:4px;overflow:hidden;"></td>
    <td><input id="videoLocation" type="button" class="btn btn-info" value="定位"></td>
  </tr>
</table>