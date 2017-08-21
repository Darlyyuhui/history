<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<script src="${root}/swfs/videoplayer/swfobject.js" type="text/javascript"></script>
<script type="text/javascript">

  var videoCode,videoWidth,videoHeight;
  // 页面初始化加载
  $(document).ready(function(){
  	videoCode = '${param.videoCode}';
  	videoWidth = '${param.videoWidth}';
  	if(!videoWidth)videoWidth = 400;
  	else videoWidth = Number(videoWidth);
  	videoHeight = '${param.videoHeight}';
  	if(!videoHeight)videoHeight = 240;
  	else videoHeight = Number(videoHeight);
  	
  	initvideoplay('${root}', videoCode, 2);
  });
  
  
  function initvideoplay(root, devicecode, command) {
      var rtspName = devicecode;// 设备
      $("#videoplayerjs").html("加载中...");
      $.ajax({
          type : "POST",
          url : root + "/vedio/index/sendCommandInfo/1/",
          dataType : "json",
          data : {
              command : command,
              rtspName : rtspName
          },
          success : function(dd) {
              var serverIp = dd[1];
              rtspName = "mp4:" + devicecode;// 设备
              
              // 新视频播放控件
              var swfVersionStr = "10.0.0";
          	  swfobject.embedSWF(
                 "${root}/swfs/videoplayer/videoPlayer.swf?videourl=rtmp://"+serverIp+"/live/"+rtspName+"&width="+videoWidth+"&height="+videoHeight,
                 "videoplayerjs",  videoWidth, videoHeight, swfVersionStr, "", {}, {allowfullscreen: true}, {"id": "videoplayerjs"}
              );
          },
          error : function() {
		      $("#videoplayerjs").html("加载失败");
          }
      });
  }
</script>

<div id="videoplayerjs">
</div>

<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>
