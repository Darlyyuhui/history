<%@ page language="java" pageEncoding="UTF-8"%>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" type="text/css" />
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/js/tree.js'/>" type="text/javascript"></script>
<div class="row-fluid">
	<div class="conten_box">
		<div class="row-fluid">
			<div class="row" style="margin:0;">
				<!-- 左侧树菜单构开始 -->
				<div class="span2" style="float:left; margin-left:0;">
					<div id="devTree" class="pull-left" title="目录列表" style="height:630px;height:639px\0;#height:639px;_height:639px; width:190px; overflow:scroll; position:fixed; margin-right:0; margin-left:0; left:11px;">
						<ul id="tree-dev" class="ztree" style="width:220px;"></ul>
					</div>
				</div>
				<!-- 左侧树菜单构结束 -->
				<div style="margin-right:0; margin-left:200px;">
					<div class="row-fluid">
						<form method="post" id="jvForm" class="form-inline" onsubmit="ajaxSubmit();return false;" style="border: 1px #EEE solid;display:block">
							<div class="sys-page1" style="padding:5px 0;">
								<div class="search_item pull-left mar_l10">
									<span>文件名：</span> <input type="text" value="" disabled="disabled" id="name" size="40" class="disabled" />
								</div>
								<div class="search_item pull-left mar_l15" style="line-height:30px;">按Ctrl+s保存</div>
								<div class="clear"></div>
							</div>
							<div class="row-fluid" style="padding:10px 0;" id="source_div">
								<textarea class="textarea" id="source" onkeydown="if((event.keyCode==115||event.keyCode==83)&&event.ctrlKey){ajaxSubmit();return false;}" style="height:428px; height:439px\0;#height:439px;_height:439px; width:98%; font-size:14px;overflow:yes;"></textarea>
							</div>
							<div class="btn_line" style="padding:15px;">
								<button class="btn btn-info" style="width:70px; white-space:nowrap; margin-right:15px;">确 定</button>
								<button class="btn" style="width:70px; white-space:nowrap;" onclick="resetValue()">重 置</button>
							</div>
							<input type="hidden" id="root" name="root" />
							<textarea id="old-source" style="display:none;"></textarea>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function treeClick(event, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("tree-dev");
		if (treeNode.isfolder == "1") {
			zTree.expandNode(treeNode);
		}

		if (treeNode.isfolder == "0") {
			var url = "${root}/system/protect/getFileContent/?name="+ treeNode.path;
			$("#root").val(treeNode.path)
			$("#name").val(treeNode.name)
			$.ajax({
				type : "POST",
				url : url,
				success : function(msg) {
					$("#source").val(msg);
					$("#old-source").val(msg);
				}
			});
		}

	}

	function resetValue() {
		$("#source").val($("#old-source").val());
	}

	function ajaxSubmit() {
		$.post("${root}/system/protect/doSave", {
			"root" : $("#root").val(),
			"source" : $("#source").val()
		}, function(data) {
			if (data.result == 'ok') {
				alert("保存成功");
			} else {
				alert(data.message);
			}
		}, "json");
	}

	$(document).ready(function() {
		var url = "${root}/system/protect/folderList/?name=";
		var settings = {
			async : {
				enable : true,
				url : url,
				autoParam : [ "name=path" ]
			},
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "pId",
					rootPId : "0"
				}
			},
			callback : {
				onClick : treeClick,
				onExpand:zTreeOnExpand
			}
		};

		$.fn.zTree.init($("#tree-dev"), settings, null);
		$("#source").height($('body').height() * 0.85);
	});
	
	function zTreeOnExpand() {
		var mainheight = $("body").height() + 50;
		parent.$("#content-frame").height(mainheight);
	}
</script>