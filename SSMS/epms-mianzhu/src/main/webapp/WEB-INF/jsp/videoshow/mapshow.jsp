<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="root" value="${pageContext.request.contextPath}" />
<script src="${root}/compnents/ace/js/jquery-1.7.2.min.js"></script>
<script src="${root}/compnents/video/previewVedio.js"></script>
<link href="${root}/compnents/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<script src="${root}/compnents/ztree/js/jquery.ztree.core.min.js" type="text/javascript"></script>
<script type="text/javascript">
var treeData = ${treeData}
var setting = {
	callback:{
		onDblClick: zTreeOnDblClick
	}
};
$(function(){
	//设置默认单窗口
	var ocxObj = document.getElementById("RealTimePlayOcx");
	ocxObj.SetWndNum(1);
	//加载树
	var treeObj = $.fn.zTree.init($("#tree-rec"), setting, treeData);
	//触发选中节点的双击事件
	treeObj.setting.callback.onDblClick(null, "${videoId}", treeObj.getNodesByFilter(filter, true));
});
function filter(node) {
    return (node.id == "${videoId}" && node.type == "CameraGun");
}
//树的双击事件
function zTreeOnDblClick(event, treeId, treeNode){
	var type = treeNode["type"].toLocaleLowerCase();
	if (type==null || type == undefined)
		return;
	if(type.indexOf("camera")==0){
		type = "camerainfo";
	}
	
	if(type=="cascadecataloginfo"){// add by fang weihua on 2012-12-28
	   type = "camerainfo";
	}
	//双击区域节点的时候，树节点一直保持展开状态
	switch (type){
	case "camerainfo":
		viewCameraVedio(treeNode);
		break;
	case "regioninfo":
	case "regioninfoncg":
		viewRegionCameraVedios(treeNode);
		break;
	default:
		break;
	}
}
/**
 * 查看一个通道的视频
 * @param treeNode
 * @return
 */
function viewCameraVedio(treeNode){
	
	var regionId = 0;
	if(treeNode.getParentNode().type.toLocaleLowerCase().indexOf("controlunit") < 0){
		regionId = treeNode.getParentNode().id;
	}
	var type=treeNode.type;
	if(type.indexOf("cascade")>=0){//add by fang weihua on 2012-01-07
		treeNode["extra"]["cascade"]="1";
	}
	//已经在播放，则焦点到对应的窗口
	viewVedioByCameraId(treeNode["id"],treeNode["extra"]["unitId"],regionId,treeNode);
}
function viewVedioByCameraId(cId,unitId,regionId,cameraNode){
	m_iCameraControlUnitId = unitId;
	m_iCameraRegionId = regionId;
	getTreeDataAjax("camera", cId, m_iCameraControlUnitId, m_iCameraRegionId, cameraNode);

}
//获取视频预览参数
function getTreeDataAjax(type, id, controlUnitId, regionId, cameraNode,winId){
	var url = "";
	var cascade = cameraNode["extra"]["cascade"];
	if(cascade != 1){
		cascade = 0;
	}
	if (type == "camera") {
		url = "http://${ip}/previewAction!fetchCameraInfoXMLForPreview.action?cameraId=" + encodeURI(id) + "&unitId="
		        + encodeURI(controlUnitId) + "&regionId=" + encodeURI(regionId)+"&cascade="+encodeURI(cascade);
	} else if(type == "lane"){
		return;
	}else {
		return;
	}
	var dataObj = {
		"url": url,
		"type": "post"
	}
	$.ajax({
		url:"${root}/map/video/getData/",
		data:dataObj,
		async:false,
		type:"post",
		success:function(data){
			var str1 = data.result;
			var msg = "";
			if (str1.indexOf("NO_AUTH") != -1) { // 没有权限
				msg = "没有权限";
			} else if (str1.indexOf("NO_DATA") != -1) { // 没有数据
				msg = "没有数据";
			} else if (str1.indexOf("REQEST_FAILED") != -1) { // 获取信息异常
				msg = "获取信息异常";
			} else if (str1.indexOf("NO_STREAMSVR_INFO") != -1) {
				msg = "没有流媒体服务器";
			} else if (str1.indexOf("NO_IP_INFO") != -1) {
				msg = "流媒体服务器异常";
			} else {
				if(id!=null && str1!=null){
					dealCamera(str1, id,cameraNode,winId);
				}
			}
		}
	});
}
</script>
<div class="ztree_box col-xs-12" style="display: none">
    <ul id="tree-rec" class="ztree" style="margin-top: 5px;"></ul>
</div>
<object classid="clsid:D74575FC-EE89-4b05-8851-1A0C417038B9" id="RealTimePlayOcx" width="100%" height="100%" name="RealTimePlayOcx">
	<div class="download-control">
		<span>
			请<a href="${root}/video/hk/ocx/setup.exe">下载</a>并安装控件
		</span>
	</div>
</object>