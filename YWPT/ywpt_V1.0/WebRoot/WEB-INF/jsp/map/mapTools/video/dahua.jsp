<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript">
var videoDH;
(function(){
	MapFactory.Require([
		"ItmsMap/Video/VideoDahua*"
	],function(VideoDahua){
		videoDH = VideoDahua();
		videoDH.init();
		
		// 绑定定位事件
		$("#dahuaLocation").click(function(){
			videoDH.videoLocation($("#dahuaLocationName").val());
		});
	});
})();

function showdahuavideo(lng, lat, name, cameraID, streamType, mediaType, transType) {
	if(videoDH)videoDH.getVideoDevice({longitude:lng, latitude:lat, name:name, cameraID:cameraID, streamType:streamType, mediaType:mediaType, transType:transType});
}
</script>

<table class="map_table mar_5" style="margin-right:-20px\0;#margin-right:-20px;_margin-right:-20px;">
  <tr>
    <td>设备名称：</td>
    <td><input id="dahuaLocationName" type="text" style="width:100px;height:28px;padding:4px;overflow:hidden;"></td>
    <td><input id="dahuaLocation" type="button" class="btn btn-info" value="定位"></td>
  </tr>
</table>