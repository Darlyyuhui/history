<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript" src="swfobject.js"></script>
        <script type="text/javascript">
            <!-- For version detection, set to min. required Flash Player version, or 0 (or 0.0.0), for no version detection. --> 
            var swfVersionStr = "10.0.0";
            swfobject.embedSWF(
                "videoPlayer.swf?videourl=rtmp://193.169.100.200/live/mp4:610000610000050025&width=100&height=100", "flashContent", 
                "100", "100", swfVersionStr, "", {}, {allowfullscreen: true}, {"id": "videoPlayer"});
			//swfobject.embedSWF(
            //    "videoPlayer.swf?videourl=rtmp://193.169.100.204/live/mp4:610100000600050031&width=600&height=400", "flashContent2", 
            //    "600", "400", swfVersionStr, "", {}, {allowfullscreen: true}, {"id": "videoPlayer"});
			
        </script>
	</head>
	<body>
		<div id="flashContent"></div><div id="flashContent2"></div>
	</body>
</html>
