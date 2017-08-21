<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<link href="${root}/css/listpage.css" type="text/css" rel="stylesheet">
<script src="<c:url value='/js/common.js'/>" type="text/javascript"></script>
<script type="text/javascript">

$(document).ready(function(){
	parent.$("#titlebar").css("display","block");
	
	// 岩涛 ADD  给表格的行增加单击选择事件  
	/*$("#tbody tr").click(function (e) {
		if ($(e.target).attr("type") != "checkbox") {
		   $(this).find(":checkbox").attr("checked", !$(this).find(":checkbox").attr("checked"));
		}
	});*/
		
	//隔行换色
	$("#tbody tr:odd").addClass("color1");
	$("#tbody tr:even").addClass("color2");
	
	
	//魏克明add鼠标划进划出tr行产生效果的js	
	$("#tbody tr:odd").mouseover(function(e){
		$(this).removeClass("color1").addClass("tr_over");
 	});
 	$("#tbody tr:even").mouseover(function(){
 		$(this).removeClass("color2").addClass("tr_over");
 	});
	
 	$("#tbody tr:odd").mouseout(function(){
 		if($(this).find(":checkbox").is(":checked")){
 			$(this).removeClass("color1").addClass("tr_over");
 		}else{
 			$(this).removeClass("tr_over").addClass("color1");
 		}
 	});
 	$("#tbody tr:even").mouseout(function(){
 		if($(this).find(":checkbox").is(":checked")){
 			$(this).removeClass("color2").addClass("tr_over");
 		}else{
 			$(this).removeClass("tr_over").addClass("color2");
 		}
 	});
	
	//点击偶数行复选框状态变化
	$("#tbody tr:odd").click(function(e){
		if($(e.target).attr("type") != "checkbox"){
			if($(this).find(":checkbox").is(":checked")){
				$(this).find(":checkbox").attr("checked",!$(this).find(":checkbox").attr("checked"));
				$(this).removeClass("tr_over").addClass("color1");
			}else{
				$(this).find(":checkbox").attr("checked","checked");
				$(this).removeClass("color1").addClass("tr_over");
				
			}
		}
	});
	//点击奇数行复选框状态变化
	$("#tbody tr:even").click(function(e){
		if($(e.target).attr("type") != "checkbox"){
			if($(this).find(":checkbox").is(":checked")){
				$(this).find(":checkbox").attr("checked",!$(this).find(":checkbox").attr("checked"));
				$(this).removeClass("tr_over").addClass("color2");
			}else{
				$(this).find(":checkbox").attr("checked","checked");
				$(this).removeClass("color2").addClass("tr_over");
			}
		}
	})
	
	
	
});

//-->
</script>
<c:if test="${not empty message}">
	<script type="text/javascript">
		$(document).ready(function(){
			if($("#submsg").val() != ""){
				parent.setMesg($("#submsg").val());
			}
			$("#submsg").val("");
		});
	</script>
</c:if>
<input id="submsg" type="hidden" name="submsg" value="${message}"/>
<div style="overflow:auto;">
    <table class="table table-striped table-bordered table-condensed table-style" id="table">
		<thead>
			<tr>
		  	  <th width="20"><input type="checkbox" id="selectAll" onclick="selectAll(this)" value="-1"/></th>	
			  <th>设备名称</th>
			  <th>设备编号</th>
			  <th>IP地址</th>
			  <th>所属部门</th>
			  <th>操作</th>
			</tr>
		 </thead>   
		 <tbody id="tbody">
			<c:forEach items="${pageList.result}" var="deviceInfo"  varStatus="s">
		  	<tr id="rowcount${s.index }" onClick="rowOnclick(this)" data="${deviceInfo.id}">
				<td style="text-align:center;"><input type="checkbox" value="${deviceInfo.id}" name="select-chk"/> </td>
				<td><div style="width:180px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px;white-space:nowrap;" title="${deviceInfo.name}">${deviceInfo.name}</div></td>
				<td>${deviceInfo.code}</td>
				<td>${deviceInfo.ip}</td>
				<td><tags:xiangxuncache keyName="Department" id="${deviceInfo.orgId}"></tags:xiangxuncache></td>
				<td>
				    <btn:authorBtn menuid="${menuid}" text="查看">
						<a  href="javascript:viewById('${deviceInfo.id}','${deviceInfo.roadId}','${searchParams}')"><i class="icon-th-list"></i>查看                                           
						</a>
					</btn:authorBtn>
					<btn:authorBtn menuid="${menuid}" text="修改">
						<a  href="javascript:updateById('${deviceInfo.id}','${deviceInfo.roadId}')">
							<i class="icon-edit"></i>修改                                           
						</a>
					</btn:authorBtn>
					<c:if test="${deviceInfo.hasModified == false}">
						<btn:authorBtn menuid="${menuid}" text="删除">
							<a  href="javascript:delById('${deviceInfo.id}')">
								<i class="icon-remove"></i>删除
							</a>
						</btn:authorBtn>
					</c:if>
					<btn:authorBtn menuid="${menuid}" text="变更">
						<a  href="javascript:updateById('${deviceInfo.id}','${deviceInfo.roadId}','1')">
							<i class="icon-refresh"></i>变更
						</a>
					</btn:authorBtn>
				</td>
			</tr>	
			</c:forEach>
			<c:if test="${pageList.result!=null}">
			  <c:forEach begin="1" end="${15-fn:length(pageList.result)}">
				 <tr>
				    <td colspan="10">&nbsp;</td>
				 </tr>
			  </c:forEach>
		    </c:if>
		</tbody>
	</table>            
	<div class="mar_t5">
	  <tags:pagination page="${pageList}"></tags:pagination>
	</div>		 
</div>
<!--/row-->
<script type="text/javascript">
	function showDiv(a){
		$("#menu_"+a).css("display","");
	}
	
	function hideDiv(a){
		$("#menu_"+a).css("display","none");
	}
	
	function showList(){
		window.location.href = "${root}/property/deviceinfo/sublist/${menuid}/";
	}
  
	function add(chooseId){
		if(chooseId == '00'){
			parent.showMessage("道路根节点不允许添加");
			return;
		}
		if(chooseId.length==0){
			parent.showMessage('请选择设备安装点'); 
			return;
		}
		parent.$("#titlebar").css("display","none");
		window.location.href="${root}/property/deviceinfo/showadd/${menuid}/"+chooseId+"/";
	    
	}
	
	function viewById(id,roadId){
		var page='${current}';
		parent.$("#titlebar").css("display","none");
		window.location.href="${root}/property/deviceinfo/view/"+id+"/${menuid}/"+roadId+"/?page="+page;
	}
	
	function updateById(id,roadId,ismodify){
		var page='${current}';
		parent.$("#titlebar").css("display","none");
		window.location.href="${root}/property/deviceinfo/update/"+id+"/${menuid}/"+roadId+"/?page="+page+"&ismodify="+ismodify;
	}
	
	function remove(){
	//岩涛修改 解决之前框架的选项框 子页面无法使用的问题
	  var checkboxObject = document.getElementsByName("select-chk");
	     var devArr = new Array();
	     for(var i = 0; i<checkboxObject.length; i++){
	        if(checkboxObject[i].checked){
	           devArr.push(checkboxObject[i].value);
	        }
	     }
		if(devArr.length==0){
		   parent.showMessage("请选择要删除的记录");
		}else{
		   delById(devArr);
		}
	}
		
	function delById(ids){
		if(confirm("删除后无法恢复，并同步删除资产信息、服务商分配信息、卡口历史记录、更改记录及工单信息等，确定删除吗？")){
	  			var url="${root}/property/deviceinfo/delete/"+ids+"/";
			$.ajax( {
				type : 'delete',
				url : url,
				dataType : "json",
				success:function(msg){
					if(msg.result=="ok"){
		   				 parent.showSucMessage("删除成功");
		   				 setTimeout("showList()",1600);
		   			}
					//else if(msg.result=="can't"){
		   			//	 parent.showMessage("设备已进行变更操作，无法删除！");
		   			//	 setTimeout("showList()", 1600);
		   			//}
					else{
		   				 parent.showMessage(msg.message);
		   			}
				}
			});
		   }
	}
	
	function modifyById(type) {
		var typename = "维修";
		if(type == '2'){
			typename = "移除";
		}else if(type == '3'){
			typename = "报废";
		}
		var ids = getSelectedValue();
		if(ids.length==0){
			parent.showMessage("请选择要"+typename+"的记录");
		}else{
			parent.showModifyDialog(ids,type);
		}
	}
	
	//<!-- 鼠标双击跳转到查看页面开始 -->
	$("#tbody tr").dblclick(function (){
		var data = $(this).attr("data");
		if(data != '' && data != undefined){
		viewById(data);
	}
	});
</script>
<!-- 鼠标双击跳转到查看页面结束 -->
