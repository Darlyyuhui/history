<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/bootstrap/js/jquery.chosen.min.js" type="text/javascript"></script>
<link href='${root}/compnents/bootstrap/css/chosen.css' rel='stylesheet'>
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display:none">
	<p id="alert-content" align="center"></p>
</div>
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success">
		<button data-dismiss="alert" class="close">×</button>
		<p align="center">${message}</p>
	</div>
	<script>
		setTimeout('$("#message").hide("slow")', 1200);
	</script>
</c:if>
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
	function reValues(){
		$("#tracode").val("");
	    $("#name").val("");
	    $("#ip").val("");
	    //$("#isrealitime_chzn>a span").html("请选择");
        //$("#isrealitime").val("");
    }
</script>
<!-- 重置查询输入框结束 -->
<div class="conten_box">
	<div class="search-area" style="padding:6px 0 4px;">
		<form class="form-inline" action="${root}/property/videoaddress/list/${menuid}/" method="post" style="margin:0;padding:0 6px;">
			<table width="100%">
			  <tr>
			    <td class="search_item_td">模板名称</td>
			    <td><input title="支持 模板名称 模糊查询" style="width:90%;" id="name" name="search_name" value="${videoAddressInfo.name}" maxlength="20" type="text" placeholder="模板名称" /></td>
			    <td class="td54">IP地址</td>
			    <td><input title="支持 IP地址 模糊查询" style="width:90%;" id="ip" name="search_ip" value="${videoAddressInfo.ip}" maxlength="15" type="text" placeholder="IP地址" /></td>
			    <td class="search_item_td">端口号</td>
			    <td><input style="width:90%;" id="tracode" name="search_port" value="${videoAddressInfo.port}" maxlength="6" type="text" placeholder="端口号" /></td>
			    <!-- <td class="search_item_td">是否实时</td>
			    <td><select id="isrealitime" name="search_isrealitime" style="width:96%;">
					<option value="">请选择</option>
					<option value="1">实时播放</option>
					<option value="0">历史回放</option>
				</select></td> -->
			    <td style="min-width:140px;">
			      <input type="submit" class="btn btn-info pull-left mar_l10" value="查询"/>
			      <input onclick="reValues()" type="button" class="btn mar_l10" value="重置"/>
			    </td>
			  </tr>
			</table>
		</form>
	</div>
	<div class="table_box">
		<div class="btn-group-wrap mar_b5">
			<div class="btn-group pull-right">
				<btn:authorBtn menuid="${menuid}" text="添加">
					<button class="btn btn-small" onclick="addVAddressInfoInfo();"><i class="icon-plus"></i>添加</button>
				</btn:authorBtn>
				<btn:authorBtn menuid="${menuid}" text="删除">
					<button class="btn btn-small" onclick="removeVAddressInfo();"><i class="icon-remove"></i>删除</button>
				</btn:authorBtn>
			</div>
			<div class="clear"></div>
  	  </div>
  	  <div style="overflow: auto;">
		  <table class="table table-striped table-bordered table-condensed table-style" id="table">
			  <thead>
				  <tr>
					 <th width="20"><input type="checkbox" id="selectAll" onclick="selectAll(this)" value="-1"/></th>
					 <th>模板名称</th>	
					 <th>IP地址</th>
					 <th>端口号</th>
					 <th>用户名</th>
					 <th>密码</th>
					 <th width="130">操作</th>
				 </tr>
			  </thead>
			  <tbody id="tbody">
				 <c:forEach items="${pageList.result}" var="videoAddressInfo">
					 <tr onclick="rowOnclick(this)"  data="${videoAddressInfo.id}">
						 <td style="text-align:center;"><input type="checkbox" value="${videoAddressInfo.id}" name="select-chk"/></td>
						 <td>${videoAddressInfo.name}</td>
						 <td>${videoAddressInfo.ip}</td>
						 <td>${videoAddressInfo.port}</td>
						 <td>${videoAddressInfo.username}</td>
						 <td>${videoAddressInfo.password}</td>
						 <td>
						     <btn:authorBtn menuid="${menuid}" text="查看"><a  href="javascript:viewById('${videoAddressInfo.id}')"><i class="icon-eye-open"></i>查看</a>
							 </btn:authorBtn>
						     <btn:authorBtn menuid="${menuid}" text="修改"><a  href="javascript:updateVAddressInfoById('${videoAddressInfo.id}')"><i class="icon-edit"></i>修改</a>
							 </btn:authorBtn>
							 <btn:authorBtn menuid="${menuid}" text="删除"><a  href="javascript:delVAddressInfoById('${videoAddressInfo.id}')"><i class="icon-remove"></i>删除</a>
							 </btn:authorBtn>
						 </td>
					 </tr>
				 </c:forEach>
				 <c:if test="${pageList.result!=null}">
					 <c:forEach begin="1" end="${15-fn:length(pageList.result)}">
						 <tr>
							 <td colspan="9">&nbsp;</td>
						 </tr>
					 </c:forEach>
				 </c:if>
				</tbody>
			</table>
			<tags:pagination page="${pageList}"></tags:pagination>
  	  </div>
	</div>
</div>
<script type="text/javascript">
<!--
	function showList(){
		window.location.href = "${root}/property/videoaddress/list/${menuid}/?page=${current}&isgetsession=1";
    }
	
	function addVAddressInfoInfo(){
	    window.location.href="${root}/forward/property/videoaddress/add/?menuid=${menuid}";
	}
	
	function updateVAddressInfoById(id){
		var page='${current}';
		window.location.href="${root}/property/videoaddress/update/"+id+"/${menuid}/?page="+page;
	}
	
	function viewById(id){
		var page='${current}';
		window.location.href="${root}/property/videoaddress/view/"+id+"/${menuid}/?page="+page;
	}
	
	function removeVAddressInfo(){
		var ids = getSelectedValue();
		if(ids.length==0){
		   showMessage("请选择要删除的记录。");
		}else{
		   delVAddressInfoById(ids);
		}
	}
	
	
	function delVAddressInfoById(ids){
		if(confirm("删除后数据将无法恢复，确定要删除吗？")){
   			var url="${root}/property/videoaddress/delete/"+ids+"/";
			$.ajax( {
				type : 'delete',
				url : url,
				dataType : "json",
				success:function(msg){
					if(msg.result=="ok"){
		   				 $("#alert-div").removeClass("alert-error").addClass("alert-success");
						showMessage("删除成功");
						setTimeout("showList()", 1600);
		   			}else{
		   				 showMessage("删除失败");
		   			}
				}
			});
		   }
	}
	
   
 $(document).ready(function(){
    $("#role-content").width($('body').width())
    $("#alert-div").hide();
    
    var isrealitime = '${videoAddressInfo.isrealitime}';
    if(isrealitime == '0'){
    	$("#isrealitime").val("0");
    }
    if(isrealitime == '1'){
    	$("#isrealitime").val("1");
    }
 });
	
//-->
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>