<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		
		<script type="text/javascript">
		  itmap.util.MapTabTool().init(
				{src:"omTab"},
				{src:"omTabContent"},
				0
			);
     //初始化路网服务测试
			function initNA(){
                  var OLNAServerobj=OLNAServer();
                  OLNAServerobj.init();
				}
		var	OLonWayAccessobj=OLonWayAccess();
				function  search(){
					OLonWayAccessobj.loadInfo();
					}
				function drawPolygon(){
					OLonWayAccessobj.drawPolygon();
					}
			   function saveEdit(){
				   OLonWayAccessobj.saveEdit();
				  }
				 function deleteFeature(){
					 OLonWayAccessobj.deleteFeature();
					 }
				 function editFeature(){					
					 OLonWayAccessobj.updateFeature();
					 }
		</script>
	</head>

	<body>
	<ul id="omTab" class='zTabTool'>
	  <li>查询统计</li>
	  <li>数据维护</li> 
    </ul>
<div id="omTabContent" class='zTabToolContent'>
		<div>
		<table width="100%" border="0" style="margin-top: 10px;">
			<tr>
				<td width="64">
					<label for="onWayAccessName">
						单行道名称
					</label>
				</td>
				<td>
					<input class="input" type="text" id="olonWayAccessName"
						name="onWayAccessName" style="width: 135px; margin-top: 10px;">
				</td>
			</tr>

			<tr>
				<td colspan=2 style="text-align: center;">
					<input id="onWayAccessQuery" type="button" class="btn btn-info" onclick="search()"
						style="margin: 0; padding: 0 10px; height: 24px; line-height: 12px;"
						value="查询" />

				</td>
			</tr>
		</table>
	</div>
    
	  	<div>
		<table border=0 style="width: 100%; margin-top: 10px;">
			<tr>
				<td width="64">
					<label for="onWayAccessName">
						单行道名称
					</label>
				</td>
				<td>
					<input class="input" type="text" id="onWayAccessName2"
						name="onWayAccessName2" style="width: 135px; margin-top: 6px;">
				</td>
			</tr>
		
		
			<tr>
				<td width="64">
					<label for="guanzhiStart">
						开始时间
					</label>
				</td>
				<td>
					<input class="input" type="text" readonly="readonly"
						id="guanzhiStart" name="guanzhiStart"
						onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'guanzhiStart\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						style="width: 135px; margin-top: 6px;">
				</td>
			</tr>
			<tr>
				<td width="64">
					<label for="guanzhiEnd">
						结束时间
					</label>
				</td>
				<td>
					<input class="input" type="text" readonly="readonly"
						id="guanzhiEnd" name="guanzhiEnd"
						onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'guanzhiEnd\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						style="width: 135px; margin-top: 6px;">
				</td>
			</tr>
			<tr>
				<td width="64">
					<label for="guanzhiName">
						备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注
					</label>
				</td>
				<td>
					<textarea rows="" cols="" id="guanzhiNote" name="guanzhiNote"
						style="width: 135px; margin-top: 6px;"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan=2 style="text-align: center;">
					<span id="guanzhiDraw" class="btn" onclick="drawPolygon()"
						style="width: 40px; padding: 3px 4px;">
						绘制
					</span>
					<span id="guanzhiEdit" class="btn" onclick="editFeature()"
						style="width: 40px; padding: 3px 4px;">
						编辑
					</span>
						<span id="guanzhiDelete" class="btn" onclick="deleteFeature()"
						style="width: 40px; padding: 3px 4px;">
						删除
					</span>
					<span id="guanzhiSave" class="btn" onclick="saveEdit()"
						style="width: 40px; padding: 3px 4px;">
						保存
					</span>
				</td>
			</tr>
		</table>
	</div>
</div>
	</body>
</html>