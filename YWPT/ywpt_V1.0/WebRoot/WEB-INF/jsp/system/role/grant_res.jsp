<%@ page language="java" pageEncoding="UTF-8"%>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" 	type="text/css" />
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/js/tree.js'/>" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display:none;">
  <p id="alert-content" align="center"></p>
</div>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin-top:0;">授权系统资源</h4>
	<form id="inputForm" class="form-horizontal" method="post" style="margin:0;">
		<input type="hidden" name="menuid" value="${menuid}"/>
		<input type="hidden" name="page" value="${page}"/>
		<input type="hidden" name="roleid" value="${roleids}"/>
		<input type="hidden" name="res" id="grant_res" value=""/>
		<fieldset class="mar_5">
			<div class="row-fluid">
              <div class="btn-group-wrap" style="padding:5px 10px;">待授权的角色名称：<c:forEach items="${roles}" var="role">	<span>${role.name}</span>,</c:forEach></div>
			  <div class="clear"></div>
			     <ul id="tree-rec" class="ztree" style="min-height:400px;border:1px solid #ebebeb;overflow:auto;"></ul>
			</div>
		</fieldset>
		<div class="btn_line">
			  <input id="submit_btn" class="btn btn-info mar_r10" type="button" value="确定"  onclick="submitForm()"/>
			  <input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
		</div>
	</form>
</div>
  <div style="display: none" id="tree-data">${data}</div>
  <script>
  	
  	var tree;
  	function submitForm(){
  		var rel = [];
  		if(tree.getCheckedNodes().length==0){
		   showMessage("请选择要授权的系统资源");
		   return;
		}
		$.each(tree.getCheckedNodes(), function(i, n) {
			rel.push(n.id);
		});
		$("#grant_res").val(rel.join(","));
	    var url = "${root}/system/role/doGrantRes/${roleids}/";
 		$("#inputForm").attr("action",url);	
 		$("#inputForm").submit();
  	}
  	
  	function zTreeOnExpand(){
  		 var mainheight = $("body").height()+50;
		 parent.$("#content-frame").height(mainheight);
  	}
   
   $(document).ready(function(){
     	var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onExpand: zTreeOnExpand
			}		
			
		};
        var zNodes = eval("("+$('#tree-data').html()+")");	
        tree= $.fn.zTree.init($("#tree-rec"), setting, zNodes);
        zTreeOnExpand();
 });
  	
 </script>

  