<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<div style="width: 620px;height: 520px;margin-left: 5px;">
    <div style="background-color: #428bca ;margin:0 2px;word-break:break-all; width:600px;height:500px;" id="msg_map_video_div"></div>
</div>
<script type="text/javascript">
    $.ajax({
        type:"post",
        url:"${root}/map/video/show/${videoId}/${video_service_ip}/",//video_service_ip=193.169.100.31:9021
        success:function(msg) {
            $("#msg_map_video_div").html(msg);
        }
    });
</script>

