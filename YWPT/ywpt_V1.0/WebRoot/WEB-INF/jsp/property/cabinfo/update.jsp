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
<div class="conten_box" style="margin-left: 8px;">
	<form id="inputForm" class="form-inline" action="${root}/property/cabinfo/doUpdate?assetid=${asset.id}&isasset=${isasset}" method="post">
		<h4 class="xtcs_h4" style="margin:0;">机柜信息-修改
		  <div style="float: right;" id="toasset_div">
				<input type="checkbox" onclick="show(this)"/><font style="font-family: inherit;font-weight: bolder;color: red;">转为资产</font>
			</div>
		</h4>
		<input type="hidden" name="menuid" value="${menuid}"/>
		<input type="hidden" name="page" value="${page}"/>
		<input type="hidden" name="id" value="${cab.id}"/>
		<input type="hidden" name="asset.id" value="${asset.id}"/>
		<input type="hidden" id="toasset" name="toasset" value=""/>
		 <!-- 选项卡 开始 -->	 
		<ul class="nav nav-tabs" style="padding-left:10px; margin:0;">
		    <li id="tab_li1" class="active"><a href="#tab1" data-toggle="tab">基本信息<strong style="color:red">*</strong></a></li>
		    <li><a href="#tab5" data-toggle="tab" onclick="mapTabShow()">地图位置</a></li>
		    <li style="display: none;" id= "dodo"><a href="#tab7" data-toggle="tab">资产配置</a></li>
		</ul>
		<div class="tab-content" style="overflow:hidden;">
			<!-- 1111 -->
			<div class="tab-pane mar_5 active" style="min-height:430px;" id="tab1" >
			  <div class="pull-left" style="width:74%;">
			    <table class="table table-border-rl table-border-bot tingche-table">
			  	  <tr>
			  	    <td class="device_td_bg3">所属道路：</td>
			  	    <td><input readonly type="text" value='<tags:xiangxuncache keyName="RoadInfo" id="${roadId}"></tags:xiangxuncache>' style="width:200px;"></td>
			  	  </tr>
			  	  
			  	  
			  	  <tr>
			  	    <td class="device_td_bg3">设备名称：</td>
			  	    <td><input type="text" id="name"  maxlength="20"placeholder="设备名称" name="name" style="width:200px;" class="required" specialcharfilter="true" value="${cab.name}"> <strong style="color:red">&nbsp;*</strong></td>
			  	  </tr>
			  	  <tr>
			  	    <td class="device_td_bg3">设备编号：</td>
			  	    <td><input type="text" id="code"  maxlength="20"placeholder="设备编号" name="code" style="width:200px;" class="required" specialcharfilter="true" min="1" max="255" value="${cab.code}"> <strong style="color:red">&nbsp;*</strong></td>
			  	  </tr>
			  	 <tr>
			        <td class="device_td_bg3">设备 IP：</td>
			        <td><input type="text" id="ip" placeholder="IP地址" name="ip" maxlength="15" style="width:200px;" ip="true" class="required" value="${cab.ip}">
			          <strong style="color:red">&nbsp;*</strong>
			        </td>
			      </tr>
			       <tr>
		  		    <td class="device_td_bg3">建设厂家：</td>
		  		    <td>
		  		      <select style="width:210px;" id="companyId" name="companyId" placeholder="建设厂家" class="required" data-rel="chosen">
			            <option value="">请选择</option>
			              <c:forEach items="${companyList}" var="company">
			                <option value="${company.id}" ${company.id==cab.companyId?'selected':''}>${company.name}</option>
			              </c:forEach>
			          </select>
			          <strong style="color:red">&nbsp;*</strong>
		  		    </td>
		  		  </tr>
			      <tr>
	  			    <td class="device_td_bg3">服务厂商：</td>
	  			    <td>
	  			      <select style="width:210px;" id="factoryId" name="factoryId" onchange="showService()" placeholder="服务厂商" class="required" data-rel="chosen">
			            <option value="">请选择</option>
			            <c:forEach items="${factoryList}" var="factory">
			                <option value="${factory.id}" ${factory.id==cab.factoryId?'selected':''}>${factory.name}</option>
			            </c:forEach>
			          </select>
			          <strong style="color:red">&nbsp;*</strong>
	  			    </td>
	  			  </tr>
			  	 
			  	</table>
			   </div>
			   <div class="pull-right" style="margin:0; margin-right:2px;width:24%;">
				  <div class="box-header well"> <small><i class="icon-info-sign"></i>&nbsp;&nbsp;设备基本信息&nbsp;&nbsp;添加说明：<br/><br/>
				    <p>1.&nbsp;&nbsp;带 <strong style="color:red">*</strong> 号的选项卡下的内容是<strong style="color:blue">必填信息</strong></p>
				    <p>2.&nbsp;&nbsp;<strong style="color:blue">若需要在资产监测中实时显示其状态信息，则必须勾选右上角的</strong><strong style="color:red">转为资产</strong><strong style="color:blue">选项</strong> 
				    <p>3.&nbsp;&nbsp;设备信息属于整个系统的基础信息请您认真填写。</p>
				    </small> </div>
				</div>
				<div class="clear"></div>
			</div>
		
			
			<!-- 444444444 -->
			
			<!-- 55555 -->
			<div class="tab-pane mar_5" style="min-height:430px;" id="tab5" >
			  <table width="100%" style="border:1px solid #ddd;">
			    <tr>
			      <td valign="top" style="border-right:1px solid #ccc;">
			        <input type="hidden" id="gid" name="gid" value="${gid }"/>
				  	<input type="hidden" id="geometryText" name="geometryText" value="${geometryText }"/>
				  	<iframe id="result_form" name="result_form" src="${root}/forward/map/mapTools/iframe/iframe_map/" width="100%" height="420" scrolling="no" style="padding:0; margin:0;" frameborder="0"></iframe>
			      </td>
			      <td valign="top" width="260">
			        <table class="table tingche-table table-border-bot" style="margin-top:-2px;">
			          <tr>
			            <td class="device_td_bg3">设备经度(X)：</td>
			            <td><input autocomplete="off" style="width:120px;" name="mapx" id="mapx" value="${cab.mapx}" type="text"  readonly /></td>
			          </tr>
			          <tr>
			            <td class="device_td_bg3">设备纬度(Y)：</td>
			            <td><input autocomplete="off" style="width:120px" name="mapy" id="mapy" value="${cab.mapy}" type="text"  readonly /></td>
			          </tr>
			        </table>
			        <div id="errorDiv" style="display:none;" class="alert alert-block pull-top alert-error"></div>
			      </td>
			    </tr>
			  </table>
			</div>
			<!-- 6666666 -->
			
			<!-- 66666结束 -->
			<!-- 77777 -->
				<div class="tab-pane mar_5" style="min-height:430px;height:430px;overflow: auto;" id="tab7" >
				<!-- 444444 -->
				<div class="pull-left" style="width:76%;">
			  		<table class="table table-border-rl table-border-bot tingche-table">
			  		  <tr>
			  		    <td class="device_td_bg3" style="width: 8%">资产编号：</td>
			  		    <td>
			  		      <input style="width:200px;" id="assetcode" name="assetcode" class="required" value="<%=code %>" readonly="readonly" type="text" placeholder="资产编号" />
				          <font color="red">*</font>
			  		    </td>
			  		  </tr>
			  		  <tr>
			  		    <td class="device_td_bg3">资产型号：</td>
			  		    <td>
			  		    	<input style="width:200px;" name="assetmodel" maxlength="30" type="text"  readonly="readonly" placeholder="DS-TP3200-EC" />
				        </td>
			  		  </tr>
			  		  <tr>
			  		    <td class="device_td_bg3">资产状态：</td>
			  		    <td>
		  			      <select style="width:210px;" id="assetstatus" name="assetstatus" placeholder="资产状态" class="required">
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
			  		    	<input style="width:200px;" id="guaranteetimestr" name="guaranteetimestr" class="required" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'new Date()'})" value="<fmt:formatDate value="<%=date %>" pattern="yyyy-MM-dd" />" type="text" placeholder="保修时间" />
				          	<font color="red">*</font>
				        </td>
			  		  </tr>
			  		  <tr>
			  		    <td class="device_td_bg3">生产厂商：</td>
			  		    <td>
			  		    	<input style="width:200px;" id="manufacturer" name="manufacturer" class="required" maxlength="30" type="text" placeholder="生产厂商" value="${asset.manufacturer}" />
				          	<font color="red">*</font>
				        </td>
			  		  </tr>
			  		  <tr>
			  		    <td class="device_td_bg3">采购时间：</td>
			  		    <td>
			  		    	<input style="width:200px;" id="purchasetimestr" name="purchasetimestr" class="required" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'new Date()'})" value="<fmt:formatDate value="<%=date %>" pattern="yyyy-MM-dd" />" type="text" placeholder="采购时间" />
				          	<font color="red">*</font>
				        </td>
			  		  </tr>
			  		  <tr>
			  		    <td class="device_td_bg3">工&nbsp;&nbsp;程&nbsp;&nbsp;商：</td>
			  		    <td>
			  		    	<input style="width:200px;" id="engineering" name="engineering" class="required" maxlength="30" type="text" placeholder="工&nbsp;&nbsp;程&nbsp;&nbsp;商"  value="${asset.engineering}"/>
				          	<font color="red">*</font>
				        </td>
			  		  </tr>
			  		  <tr>
			  		    <td class="device_td_bg3">安装时间：</td>
			  		    <td>
			  		    	<input style="width:200px;" id="installtimestr" name="installtimestr" class="required" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,maxDate:'new Date()'})" value="<fmt:formatDate value="<%=date %>" pattern="yyyy-MM-dd HH:mm:ss" />" type="text" placeholder="安装时间" />
				          	<font color="red">*</font>
				        </td>
			  		  </tr>
			  		  <tr>
			  		    <td class="device_td_bg3">安装地点：</td>
			  		    <td>
			  		    	<input style="width:200px;" id="installplace" name="installplace" value="${asset.installplace}" class="required" maxlength="50" type="text" placeholder="安装地点" />
				          	<font color="red">*</font>
				        </td>
			  		  </tr>
			  		  <tr>
						<td class="device_td_bg3">服务厂商：</td>
						<td width="40%">
							<input style="width:200px;" id="servicename" name="servicename"  type="text" readonly="readonly" placeholder="服务厂商" />
							<input type="hidden" id="serviceid" name="serviceid" value='${factory.id}' /> 
						</td>
					  </tr>
					  <tr>
			  		    <td class="device_td_bg3">MAC地址：</td>
			  		    <td>
			  		    	<input style="width:200px;" name="macaddress" maxlength="25" type="text"  value="${asset.macaddress}" placeholder="MAC地址" />
				        </td>
			  		  </tr>
			  		</table>
			  	</div>
		  	</div>
		</div>			
		<div class="btn_line">
			<button class="btn btn-info mar_r10"   onclick="javascript: return checkForm();" type="submit">保存</button>
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
			$(".nav-tabs li:last").css("display","");
	    }else{
	    	$(this).removeAttr("checked");
	    	$("#toasset").val("");
	    	$(".nav-tabs li:last").css("display","none");
	    	$(".nav-tabs li:first").addClass("active").siblings().removeClass("active");
			$("#tab1").addClass("active").siblings().removeClass("active");
	    }
}

function showService(){
	var text = $("#factoryId").find("option:selected").text();
	$("#servicename").val($("#factoryId").val()==''?"":text);
	$("#serviceid").val($("#factoryId").val());
}
	function showList(){
		window.location.href = "${root}/property/cabinfo/sublist/${menuid}/?isgetsession=1&page=${page}";
	}
	
	function checkForm(){
		var submit = true;
		var name = $("#name").val();
		var ip = $("#ip").val();
		var code = $("#code").val();
		var companyId = $("#companyId").val();
		var factoryId = $("#factoryId").val();
		
		var namestyle = $("#name").attr("class");
    	var ipstyle = $("#ip").attr("class");
    	var codestyle = $("#code").attr("class");
    	
		if(namestyle == 'required error' ||name== ''){
			alert("[基本信息]标签页-设备名称为空或者不正确");
			submit = false;
		}else if(codestyle=='required error' ||code==''){
			alert ("[基本信息]标签页-设备编号为空或者不正确");
			submit = false;
		}else if(ipstyle == 'required error' ||ip==''){
			alert("[基本信息]标签页-IP 地址为空或者不正确");
			submit = false;
	    }else if(companyId==''){
	    	alert("[基本信息]标签页-建设厂家为空");	
			submit = false;
	    }else if(factoryId==''){
	    	alert("[基本信息]标签页-服务厂商为空");
	    	submit = false;
	    }else{
	    		if($("#toasset").val() == '1'){
	    	    	//资产状态
	    	    	var assetstatus =$("#assetstatus").val();
	    	    	//保修时间
	    	    	var guaranteetimestr =$("#guaranteetimestr").val();
	    	    	//生产厂商
	    	    	var manufacturer =$("#manufacturer").val();
	    	    	//采购时间
	    	    	var purchasetimestr =$("#purchasetimestr").val();
	    	    	//工程商
	    	    	var engineering = $("#engineering").val();
	    	    	//安装时间
	    	    	var installtimestr = $("#installtimestr").val();
	    	    	//安装地点
	    	    	var installplace = $("#installplace").val();
	    	    	if(assetstatus==''&&manufacturer==''&&engineering==''&&installplace==''){
	    	    		alert("请输入资产配置信息或者取消勾选");
	    	    		submit = false;
	    	    	}else{
	    	    		if(assetstatus==''){
	    	    			alert("[资产配置]标签页-资产状态不能为空");
	    	    			submit = false;
	    	    		}else if(guaranteetimestr==''){
	    	    			alert("[资产配置]标签页-保修时间不能为空");
	    	    			submit = false;
	    	    		}else if(manufacturer==''){
	    	    			alert("[资产配置]标签页-生产厂商不能为空");
	    	    			submit = false;
	    	    		}else if(purchasetimestr==''){
	    	    			alert("[资产配置]标签页-采购时间不能为空");
	    	    			submit = false;
	    	    		}else if(engineering==''){
	    	    			alert("[资产配置]标签页-工程商不能为空");
	    	    			submit = false;
	    	    		}else if(installtimestr==''){
	    	    			alert("[资产配置]标签页-安装时间不能为空");
	    	    			submit = false;
	    	    		}else if(installplace==''){
	    	    			alert("[资产配置]标签页-安装地点不能为空");
	    	    			submit = false;
	    	    		}
	    	    	}
	    	    }
	    	}
		
		return submit;
	}
	
</script>
<script type="text/javascript">

$(document).ready(function() {
	//自定义验证
	$.extend($.validator.messages, {
		max: $.validator.format("请输入一个最大为 {0} 的整数"),
	    min: $.validator.format("请输入一个最小为 {0} 的整数")
	});
	//聚焦第一个输入框
	$("#name").focus();
	//为inputForm注册validate函数
	$("#inputForm").validate({
		rules: {
			"ip":{
				remote:{
					url:"${root}/property/cabinfo/ipExist",
					type:"post",
					data:{
						ip:function(){
							return $("#ip").val();
						},
						oper:function(){
							return "${cab.ip}";
						}
					}
				}
			},
			"name":{
				remote:{
					url:"${root}/property/cabinfo/nameExist",
					type:"post",
					data:{
						name:function(){
							return $("#name").val();
						},
						oper:function(){
							return "${cab.name}";
						}
					}
				}
			},
			"code":{
				remote:{
					url:"${root}/property/cabinfo/codeExist",
					type:"post",
					data:{
						name:function(){
							return $("#code").val();
						},
						oper:function(){
							return "${cab.code}";
						}
					}
				}
			}
		}
	});
	showService();
	var isasset = '${isasset}';
	if(isasset == '1'){
		$("#assetcode").val('${asset.assetcode}');
		$("#guaranteetimestr").val("<fmt:formatDate value="${asset.guaranteetime}" pattern="yyyy-MM-dd" />");
		$("#purchasetimestr").val("<fmt:formatDate value="${asset.purchasetime}" pattern="yyyy-MM-dd" />");
		$("#installtimestr").val("<fmt:formatDate value="${asset.installtime}" pattern="yyyy-MM-dd HH:mm:ss" />");
		$("#toasset_div").hide();	
		$("#dodo").css("display","");
		$("#tab7").attr("style","margin-top:-6px;");
	}
});

</script>
<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>
