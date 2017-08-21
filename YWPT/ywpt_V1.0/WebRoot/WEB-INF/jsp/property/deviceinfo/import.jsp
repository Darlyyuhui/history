<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<link href="${root}/compnents/bootstrap/css/uniform.default.min.css" rel="stylesheet">
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${root}/compnents/bootstrap/js/charisma.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display: none">
	<p id="alert-content" align="center"></p>
</div>
<div class="conten_box" style="margin-left: 200px">
	<h4 class="xtcs_h4">设备信息--导入</h4>
	<ul class="nav nav-tabs" style="padding-left:10px;margin:0;">
		<li class="active" onclick="aa('1');" id="litab1"><a href="#" data-toggle="tab">设备信息导入</a></li>
		<li onclick="aa('2');" id="litab2"><a href="#" data-toggle="tab">数据格式说明</a></li>
	</ul>
	<div class="tab-content" style="overflow:hidden;">
		<!-- 货车信息导入页签 start -->
		<div class="tab-pane active" id="tab1">
		  <div  class="mar_5">
			<form style="margin: 5px;" method="post" id="importForm" action="${root}/property/deviceinfo/upload/${param.menuid}/" enctype="multipart/form-data" target="nextStepIframe">
				<input type="file" id="file" name="file" class="type-file-file"
					onkeypress="return false;" onkeydown="return false;"
					style="width:280px; border:1px solid #000;" contenteditable="false" />
				<span><input type="submit" value="预览" id="Button1"
						class="btn btn-info" onclick="validateFile()"
						class="l-button l-button-submit"
						style="display: inline; margin-left: 10px; margin-top: 5px;" />
						
						<input type="button" value="返回" id="Button1"
						class="btn btn-info" onclick="reloadPage()"
						class="l-button l-button-submit"
						style="display: inline; margin-left: 10px; margin-top: 5px;" />
				</span>
				<div>
					<a href="${root}/property/deviceinfo/downloadXls/">下载模板文件 </a>
				</div>
			</form>
			<div id="gps-div">
				<iframe id="message-frame" name="nextStepIframe" frameborder="0" scrolling="no" height="540" width="100%"
					src="${root}/forward/property/deviceinfo/errors/"></iframe>
			</div>
			<div class="btn_line" id="btn_div" style="display: none">
				<input class="btn btn-primary mar_r10" type="button" id="submit_btn" value="保存" onclick="submit_btn()" />
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="reloadPage()" />
			</div>
		  </div>
		</div>
		<!-- 数据格式说明页签 start -->
		<div  class="tab-pane" id="tab2">
		  <div class="mar_5">
			<table class="table table-striped table-bordered table-condensed table-style">
				<thead>
					<tr>
				  	    <th>列</th>	
				  	    <th>列名</th>	
				  	    <th>单元格值示例</th>	
						<th>备注说明</th>
					</tr>
				 </thead>
				 <tbody id="tbody">
				  	<tr>
						<td>A</td>
						<td>设备编号</td>
						<td>610000000003031033</td>
						<td></td>
					</tr>	
					<tr>
						<td>B</td>
						<td>设备名称</td>
						<td>永乐十字东方向</td>
						<td></td>
					</tr>
					<tr>
						<td>C</td>
						<td>设备IP</td>
						<td>192.168.10.132</td>
						<td></td>
					</tr>
					<tr>
						<td>D</td>
						<td>所在道路</td>
						<td>永乐十字</td>
						<td></td>
					</tr>
					<tr>
						<td>E</td>
						<td>管理部门</td>
						<td>交警支队</td>
						<td></td>
					</tr>
					<tr>
						<td>F</td>
						<td>建设厂商</td>
						<td>西安翔迅</td>
						<td></td>
					</tr>
					<tr>
						<td>G</td>
						<td>服务厂商</td>
						<td>西安翔讯科技有限责任公司</td>
						<td></td>
					</tr>
					<tr>
						<td>H</td>
						<td>设备功能</td>
						<td>卡口检测，流量监测，违法检测</td>
						<td></td>
					</tr>
					<tr>
						<td>I</td>
						<td>资产状态</td>
						<td>运行</td>
						<td>运行，损坏，维修，报废，库存，待安装，已安装，已调试</td>
					</tr>
					<tr>
						<td>J</td>
						<td>保修时间</td>
						<td>2017-04-05</td>
						<td></td>
					</tr>
					<tr>
						<td>K</td>
						<td>采购时间</td>
						<td>2017-04-05</td>
						<td></td>
					</tr>
					<tr>
						<td>L</td>
						<td>安装时间</td>
						<td>2017-04-05 10:58:04</td>
						<td></td>
					</tr>
					<tr>
				</tbody>
		  	</table>
		  </div>
		  <div id="buttons" class="btn_line">
				<input style="width:60px;" id="cancel_btn" class="btn" type="button" value="返回" onclick="reloadPage()" />
		  </div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function submit_btn(){
		$("#submit_btn").attr("disabled", true);
		nextStepIframe.save();
	};

	function aa(id){
		$("#submit_btn").attr("disabled", false);
		if(id=='1'){
			/* $("#litab1").className = "active";
			$("#litab2").className = ''; */
			$("#litab2").removeClass('active');
			$("#litab1").addClass('active');
			$("#tab1").show();
			$("#tab2").hide();
		}else{
			/* $("#litab2").className = "active";
			$("#litab1").className = ''; */
			$("#litab1").removeClass('active');
			$("#litab2").addClass('active');
			$("#tab2").show();
			$("#tab1").hide();
		}
	};

	function validateFile() {
		$("#submit_btn").attr("disabled", false);
		var obj = document.getElementById("file");
		if (obj.value == "") {
			alert('请选择导入文件！');
			return false;
		}
		var stuff = obj.value.match(/^(.*)(\.)(.{1,8})$/)[3];
		if (stuff == "xls") {
			document.getElementById("importForm").submit();
			return true;
		} else {
			alert('文件格式不正确，请选择.xls格式文件！');
			return false;
		}
	}

	//加入iframe自适应高度
	$("#message-frame").load(function() {
		$(this).height(0);
		var mainheight = $(this).contents().height() + 10;
		$(this).height(mainheight < 500 ? 500 : mainheight);
	});
	
	$("input:file").not('[data-no-uniform="true"],#uniform-is-ajax').uniform({fileButtonHtml:'请选择',fileDefaultHtml:''});
	
	$("div.uploader span.filename").width(185)
	function reloadPage() {
		window.location.href = "${root}/property/deviceinfo/list/${menuid}/";
		//window.back();
	}
</script>

