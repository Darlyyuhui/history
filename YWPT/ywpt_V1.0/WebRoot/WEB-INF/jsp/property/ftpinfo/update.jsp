<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<%
	String ismodify = request.getParameter("ismodify");
	Date date = new Date();
	String code = new SimpleDateFormat("yyMMddHHmmss").format(date)+new Random().nextInt(10000);
%>
<div class="conten_box" style="margin-left: 180px;">
	<form id="inputForm" class="form-inline" action="${root}/property/ftpinfo/doUpdate?assetid=${asset.id}&isasset=${isasset}" method="post">
		<h4 class="xtcs_h4" style="margin:0;">FTP信息-修改</h4>
		<input type="hidden" name="menuid" value="${menuid}"/>
		<input type="hidden" name="page" value="${page}"/>
		<input type="hidden" name="id" value="${ftp.id}"/>
		<input type="hidden" id="toasset" name="toasset" value=""/>
		<div id="common_div">
			<div>
				<table class="table tingche-table table-border-rl table-border-bot" width="100%">
					<tr>
						<td class="device_td_bg3" style="width: 14%">FTP名称：</td>
						<td style="width: 36%">
							<input style="width:160px;" type="text" id="name" name="name" value="${ftp.name}" maxlength="30" class="required" maxlength="50">
							<font color="red">*</font>
						</td> 
						<td class="device_td_bg3" style="width: 14%">FTP类型：</td>
						<td style="width: 36%">
							<input style="width:160px;" type="text" id="ftptype" name="ftptype" value="${ftp.ftptype}" maxlength="15" class="required" maxlength="50">
							<font color="red">*</font>
						</td>
					</tr>
					<tr> 
						<td class="device_td_bg3">IP地址：</td>
						<td>
							<input style="width:160px;" type="text" id="ip" name="ip" value="${ftp.ip}" ip="true" maxlength="15" class="required">
		                    <font color="red">*</font>
						</td>
						<td class="device_td_bg3">端口号：</td>
						<td>
							<input style="width:160px;" type="text" id="port" name="port" value="${ftp.port}" class="required" minlength="2" maxlength="5" digits="true">
							<font color="red">*</font>
						</td>
					</tr>
					<tr> 
						<td class="device_td_bg3">用户名：</td>
						<td>
							<input style="width:160px;" type="text" id="username" name="username" value="${ftp.username}" class="required" maxlength="10" specialcharfilter="true">
							<font color="red">*</font>
						</td>
						<td class="device_td_bg3">密码：</td>
						<td>
							<input style="width:160px;" type="text" id="password" name="password" value="${ftp.password}" password="true" maxlength="12" class="required">
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="device_td_bg3">文件虚拟目录：</td>
						<td>
							<input style="width:160px;" type="text" id="dirname" name="dirname" value="${ftp.dirname}" maxlength="15" class="required">
		                    <font style="color: red;"> *</font>
						</td>
				  	    <td class="device_td_bg3">运维服务商：</td>
				  	    <td>
				          <select style="width:170px;padding:4px;overflow:hidden;" id="factoryId" name="factoryId" class="required">
				            <option value="">请选择</option>
				            <c:forEach items="${factoryList}" var="factory">
				              <option value="${factory.id}" ${factory.id==ftp.factoryId?'selected':''}>${factory.name}</option>
				            </c:forEach>
					      </select>
					      <font color="red">*</font>
				        </td>
				  	</tr>
				</table>
			</div>
			<div id="toasset_div" style="height: 22px;margin-left: 5px;margin-bottom: 5px;">
				<input type="checkbox" onclick="show(this)"/><font style="font-family: inherit;font-weight: bolder;color: red;font-size: small;">转为资产</font>
			</div>
			<div id="asset_div" style="display: none;">
		  		<table class="table table-border-rl table-border-bot tingche-table" width="100%">
		  		  <tr>
		  		    <td class="device_td_bg3" style="width: 14%">资产编号：</td>
		  		    <td style="width: 36%">
		  		      <input style="width:160px;" name="assetcode" class="required" id="assetcode" value="<%=code %>" readonly="readonly" type="text" placeholder="资产编号" />
			          <font color="red">*</font>
		  		    </td>
		  		    <td class="device_td_bg3" style="width: 14%">资产状态：</td>
		  		    <td style="width: 36%">
	  			      <select style="width:170px;" id="assetstatus" name="assetstatus" placeholder="资产状态" class="required">
			            <option value="">请选择</option>
			            <c:forEach items="${assetstatusList}" var="status">
			                <option value="${status.code}" ${status.code==asset.assetstatus?'selected':''}>${status.name}</option>
			            </c:forEach>
			          </select>
			          	<font color="red">*</font>
			        </td>
		  		  </tr>
		  		  <tr>
		  		    <td class="device_td_bg3">保修时间：</td>
		  		    <td>
		  		    	<input style="width:160px;" id="guaranteetimestr" name="guaranteetimestr" class="required" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'new Date()'})" value="<fmt:formatDate value="<%=date %>" pattern="yyyy-MM-dd" />" type="text" placeholder="保修时间" />
			          	<font color="red">*</font>
			        </td>
		  		    <td class="device_td_bg3">采购时间：</td>
		  		    <td>
		  		    	<input style="width:160px;" id="purchasetimestr" name="purchasetimestr" class="required" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'new Date()'})" value="<fmt:formatDate value="<%=date %>" pattern="yyyy-MM-dd" />" type="text" placeholder="采购时间" />
			          	<font color="red">*</font>
			        </td>
		  		  </tr>
		  		  <tr>
		  		    <td class="device_td_bg3">安装时间：</td>
		  		    <td>
		  		    	<input style="width:160px;" id="installtimestr" name="installtimestr" class="required" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,maxDate:'new Date()'})" value="<fmt:formatDate value="<%=date %>" pattern="yyyy-MM-dd HH:mm:ss" />" type="text" placeholder="安装时间" />
			          	<font color="red">*</font>
			        </td>
		  		    <td class="device_td_bg3">安装地点：</td>
		  		    <td>
		  		    	<input style="width:160px;" name="installplace" value="${asset.installplace}" class="required" maxlength="50" type="text" placeholder="安装地点" />
			          	<font color="red">*</font>
			        </td>
		  		  </tr>
		  		  <tr>
		  		    <td class="device_td_bg3">资产型号：</td>
		  		    <td>
		  		    	<input style="width:160px;" name="assetmodel" value="${asset.assetmodel}" maxlength="30" type="text" placeholder="资产型号" />
			        </td>
		  		    <td class="device_td_bg3">MAC地址：</td>
		  		    <td>
		  		    	<input style="width:160px;" name="macaddress" value="${asset.macaddress}" maxlength="25" type="text" placeholder="MAC地址" />
			        </td>
				  </tr>
		  		</table>
		  	</div>
			  	</div>
			</div>
		</div>
		<div class="btn_line">
			<button class="btn btn-info mar_r10" onclick="javascript:return checkForm();" type="submit">保存</button>
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
		</div>
	</form>
</div>
<script type="text/javascript">

	function show(a){
		var checked = $(a).attr("checked");
		if((checked=="checked") || checked){
				$(this).attr("checked",checked);
				$("#toasset").val("1");
				$("#asset_div").css("display","");
		    }else{
		    	$(this).removeAttr("checked");
		    	$("#toasset").val("");
		    	$("#asset_div").css("display","none");
		    }
	}
	
	function showList(){
		window.location.href = "${root}/property/ftpinfo/list/${menuid}/?isgetsession=1&page=${page}";
	}
	function checkForm(){
		return true;
	}
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules: {
				"name":{
					remote:{
						url:"${root}/property/ftpinfo/nameExist",
						type:"post",
						data:{
							name:function(){
								return $("#name").val();
							},
							oper:function(){
								return "${ftp.name}";
							}
						}
					}
				}
			}
		});
		var isasset = '${isasset}';
		if(isasset == '1'){
			$("#assetcode").val('${asset.assetcode}');
			$("#guaranteetimestr").val("<fmt:formatDate value="${asset.guaranteetime}" pattern="yyyy-MM-dd" />");
			$("#purchasetimestr").val("<fmt:formatDate value="${asset.purchasetime}" pattern="yyyy-MM-dd" />");
			$("#installtimestr").val("<fmt:formatDate value="${asset.installtime}" pattern="yyyy-MM-dd HH:mm:ss" />");
			$("#toasset_div").hide();	
			$("#asset_div").css("display","");
			$("#asset_div").attr("style","margin-top:-6px;");
		}
	});
</script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>
