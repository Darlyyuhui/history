<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<%@ page import="com.xiangxun.atms.framework.util.FileUtils"%>
<%@ page import="com.xiangxun.atms.framework.util.SwfUtils"%>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<script src="${root}/compnents/flexpaper/js/swfobject.js" type="text/javascript"></script>
<% 
String path = request.getContextPath();
String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String openOfficeName = SwfUtils.toolsInstallPath("sys.openoffice.filename");
String swfToolsName = SwfUtils.toolsInstallPath("sys.swftools.filename");
String flashPlayerName = SwfUtils.toolsInstallPath("sys.flashplayer.filename");
%>
<div class="conten_box" style="margin-right:0; margin-left:180px;">
	<h4 class="xtcs_h4" style="margin: 0;">文件上传-详情</h4>
	<table width="100%">
	  <tr>
	    <td>
          <div id="flashContent" style="border: 1px solid #ccc; background-color:gray;width:100%;height: 410px;" title="附件预览区域">
	   		  <c:if test="${filexist == '-1'}">
	   			<img style="width:100%;height:410px;" src="${root}/images/crossErrorPic.png"/>
	   		  </c:if>
          </div>
		</td>
	</tr>
	<tr>
		<td>
			<table class="table" style="width: 100%;border:1px solid #ccc;margin-top: -5px;">
				<tr>
					<td class="device_td_bg3">文件名称：</td>
					<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:22px;" title="${knowledge.name}">${knowledge.name}</div></td>
					<td class="device_td_bg3">知识类别：</td>
					<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:22px;" title="${knowledge.knowledgetype}">${knowledge.knowledgetype}</div></td>
				</tr>
				<tr>
					<td class="device_td_bg3">创建人：</td>
					<td><tags:xiangxuncache keyName="username_cache" id="${knowledge.operator}"></tags:xiangxuncache></td>
					<td class="device_td_bg3">创建时间：</td>
					<td><fmt:formatDate value="${knowledge.createtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<td class="device_td_bg3">备&nbsp;&nbsp;注：</td>
					<td><div style="width:260px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px;" title="${knowledge.note}">${knowledge.note}</div></td>
					<td class="device_td_bg3">附件下载：</td>
					<td>
						<a id="showView" title="点击下载:${knowledge.name}">
							<img onclick="download('${knowledge.id}')" style="height: 20px;" src="${root}/images/download.png">
						</a>
					</td>
				</tr>
			</table>
			<div style="height: 25px;">
				<a class="mar_l5 mar_r5" href="javascript:;" onclick="showTools()"><font style="color: red;">无法预览？点击下载安装插件。</font></a>
			</div>
			<div id="swfTools" class="mar_l5 mar_r5" style="display: none">
				<a href="${root}/repository/upload/downTools/openOffice/" title="点击下载OpenOffice控件"><font style="font-size: small;font-weight: normal;color: blue;"><%=openOfficeName%></font></a><br>
				<a href="${root}/repository/upload/downTools/swfTools/" title="点击下载SwfTools控件"><font style="font-size: small;font-weight: normal;color: blue;"><%=swfToolsName%></font></a><br>
				<a href="${root}/repository/upload/downTools/flashPlayer/" title="点击下载FlashPlayer控件"><font style="font-size: small;font-weight: normal;color: blue;"><%=flashPlayerName%></font></a>
			</div>
			<div class="btn_line">
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
			</div>
		</td>
	  </tr>
	</table>
</div>
<script type="text/javascript">
		
        function show(filename){
	        	var swfVersionStr = "10.0.0";
		        var xiSwfUrlStr = "${root}/compnents/flexpaper/swf/playerProductInstall.swf";
		        var flashvars = {
		                  SwfFile : escape('<%=url%>'+filename),
		        Scale : 0.6,
		        ZoomTransition : "easeOut",
		        ZoomTime : 0.5,
		        ZoomInterval : 0.1,
		        FitPageOnLoad : false,
		        FitWidthOnLoad : true,
		        PrintEnabled : true,
		        FullScreenAsMaxWindow : false,
		        ProgressiveLoading : true,
		        PrintToolsVisible : true,
		        ViewModeToolsVisible : true,
		        ZoomToolsVisible : true,
		        FullScreenVisible : true,
		        NavToolsVisible : true,
		        CursorToolsVisible : false,
		        SearchToolsVisible : false,
		        localeChain: "zh_CN"
		      };
		
		    var params = {
		
		       }
		       params.quality = "high";
		       params.bgcolor = "#ffffff";
		       params.allowscriptaccess = "sameDomain";
		       params.allowfullscreen = "true";
		       var attributes = {};
		       attributes.id = "FlexPaperViewer";
		       attributes.name = "FlexPaperViewer";
		       swfobject.embedSWF(
		           "${root}/compnents/flexpaper/swf/flexpaper.swf", "flashContent",
		           "100%", "405",
		       swfVersionStr, xiSwfUrlStr,
		       flashvars, params, attributes);
			   swfobject.createCSS("#flashContent", "display:block;text-align:center;");
        }
	        
</script>
<script type="text/javascript">
	function showTools(){
		if($("#swfTools").attr("style") == "display: none"){
			$("#swfTools").attr("style","display: block");
		}else{
			$("#swfTools").attr("style","display: none");
		}
	}
	
	function download(id){
		window.location.href="${root}/repository/upload/download/"+id+"/";
	}

	$(document).ready(function() {
		//聚焦第一个输入框
		$("#filename").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate();
		var filexist = '${filexist}';
		if(filexist != '-1'){
			var filename = '${uploadname}';
			show(filename);
		}else{
			//alert("文件不存在或格式有误");
		}
	});
</script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>
