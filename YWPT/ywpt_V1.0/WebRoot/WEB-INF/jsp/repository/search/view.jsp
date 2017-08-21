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
<c:if test="${knowledge.type == '1'}">
	<h4 class="xtcs_h4" style="margin:0;">知识库-详情</h4>
	<div id="has-attach-div" class="conten_box" style="display: block;">
		<table width="100%">
		  <tr>
		    <td>
	          <div id="flashContent" style="border: 1px solid #ccc; background-color:gray;width:100%;height: 440px;" title="附件预览区域">
		   		  <c:if test="${filexist == '-1'}">
		   			<img style="width:100%;height:440px;" src="${root}/images/crossErrorPic.png"/>
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
						<td class="device_td_bg3">创建日期：</td>
						<td><fmt:formatDate value="${knowledge.createtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
					<tr>
						<td class="device_td_bg3">备&nbsp;&nbsp;注：</td>
						<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:22px;" title="${knowledge.note}">${knowledge.note}</div></td>
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
	<div id="no-attach-div" style="display: none;">
		<table width="100%">
		  <tr>
			<td>
				<div class="conten_box" >
					<table class="table tingche-table table-border-rl table-border-bot">
						<tr>
							<td class="device_td_bg3">文件名称：</td>
							<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:22px;" title="${knowledge.name}">${knowledge.name}</div></td>
							<td class="device_td_bg3">知识类别：</td>
							<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:22px;" title="${knowledge.knowledgetype}">${knowledge.knowledgetype}</div></td>
						</tr>
						<tr>
							<td class="device_td_bg3">创建人：</td>
							<td><tags:xiangxuncache keyName="username_cache" id="${knowledge.operator}"></tags:xiangxuncache></td>
							<td class="device_td_bg3">创建日期：</td>
							<td><fmt:formatDate value="${knowledge.createtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
						<tr>
							<td class="device_td_bg3">备&nbsp;&nbsp;注：</td>
							<td colspan="3"><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:22px;" title="${knowledge.note}">${knowledge.note}</div></td>
						</tr>
					</table>
					<div class="btn_line" style="text-align:center; margin-bottom:0;">
						<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
					</div>
				</div>
			 </td>
		  </tr>
		</table>
	</div>
</c:if>
<c:if test="${knowledge.type == '0'}">
	<div class="conten_box">
		<c:if test="${workorder != null}">
			<h4 class="xtcs_h4" style="margin:0;">工单信息-详情</h4>
			<div class="mar_5">
				<table class="table tingche-table table-border-rl table-border-bot" width="100%">
					<tr>
						<td class="device_td_bg2" style="width: 10%">设备名称：</td>
						<td style="width: 22%"><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:22px;" title="${workorder.devicename}">${workorder.devicename}</div></td> 
						<td class="device_td_bg2" style="width: 10%">设备编号：</td>
						<td style="width: 22%">${workorder.devicecode}</td>			
						<td class="device_td_bg2" style="width: 10%">设备IP：</td>
						<td style="width: 26%">${workorder.deviceip}</td> 
					</tr>
					<tr>
						<td class="device_td_bg2">设备类型：</td>
						<td>${workorder.devicetype}</td>			
						<td class="device_td_bg2">位置信息：</td>
						<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:22px;" title="${workorder.position}">${workorder.position}</div></td>
						<td class="device_td_bg2">所属部门：</td>
						<td><tags:xiangxuncache keyName="Department" id="${workorder.orgid}"></tags:xiangxuncache></td>			
					</tr>
					<tr>
						<td class="device_td_bg2">派发人：</td>
						<td><tags:xiangxuncache keyName="username_cache" id="${workorder.assignaccount}"></tags:xiangxuncache></td>
						<td class="device_td_bg2">派发时间：</td>
						<td><fmt:formatDate value="${workorder.assigntime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>	
						<td class="device_td_bg2">工单状态：</td>
						<td>${workorder.statusHtml}
						</td>
					</tr>
				</table>
		    </div>
		</c:if>
		<h4 class="xtcs_h4" style="margin:0;">知识库-详情</h4>
		<div class="mar_5">
			<table class="table table-border-bot table-border-rl bukong-table" width="100%">
				<tr>
					<td class="device_td_bg3" style="width: 10%;">文件名称：</td>
					<td style="width: 40%;"><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:22px;" title="${knowledge.name}">${knowledge.name}</div></td>
					<td class="device_td_bg3" style="width: 10%;">知识类别：</td>
					<td style="width: 40%;"><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:22px;" title="${knowledge.knowledgetype}">${knowledge.knowledgetype}</div></td>
				</tr>
				<tr>
					<td class="device_td_bg3">创建人：</td>
					<td><tags:xiangxuncache keyName="username_cache" id="${knowledge.operator}"></tags:xiangxuncache></td>
					<td class="device_td_bg3">创建日期：</td>
					<td><fmt:formatDate value="${knowledge.createtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<td class="device_td_bg3">文件内容：</td>
					<td><div style="height: 300px;overflow: auto;"><jsp:include page="../input/context.jsp"></jsp:include></div></td>
					<c:if test="${workorder != null}">
						<td></td>
						<td></td>
					</c:if>
					<c:if test="${workorder == null}">
						<td class="device_td_bg3">备&nbsp;&nbsp;注：</td>
						<td>${knowledge.note}</td>
					</c:if>
				</tr>
			</table>
	    </div>
		<div class="btn_line">
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
		</div>
	</div>
</c:if>
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

	function showList(){
		window.location.href = "${root}/repository/search/list/${menuid}/?page=${page}";
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
			$("#has-attach-div").css("display","none");
			$("#no-attach-div").css("display","");
			alert("文件不存在或格式有误");
		}
	});
</script>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>
