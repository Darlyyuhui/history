<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<link href="${root }/compnents/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<script src="${root }/compnents/ztree/js/jquery.ztree.core.min.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none;">
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
<!-- 控制iframe中的页面返回的结果显示开始 -->
<div id="subinfo" class="alert alert-success" style="display: none;">
    <button data-dismiss="alert" class="close">×</button>
    <p id="mesg" align="center"></p>
</div>
<div class="page-header">
    <h1>
        土壤类型
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            土壤类型管理
        </small>
    </h1>
</div>
<div class="row">
    <div class="col-sm-12" id="default-buttons">
        <h3 class="row header smaller lighter purple">
            <button class="btn btn-purple" onclick="addBtn();">
                <i class="icon-home"></i>添加
            </button>
            <button class="btn btn-large" onclick="updateBtn();">
                <i class="icon-edit"></i>修改
            </button>
            <button class="btn btn-danger" onclick="deleteBtn();">
                <i class="icon-remove"></i>删除
            </button>
        </h3>
    </div>
    <div class="space"></div>
    <div>
        <div class="ztree_box col-xs-12">
            <!-- <div class="pull-left ztree_box" style="width:20%;height: 580px;overflow: auto;"> -->
            <ul id="tree-rec" class="ztree" style="margin-top: 5px;"></ul>
        </div>
    </div>
</div>
<script type="text/javascript">
   var zNodes = ${treeData};
    var tree;
    var ids = "";
    $(function () {
    	var setting = {
   			data: {
   				simpleData: {
   					enable: true
   				}
   			}
   			,callback: {
   				onClick : treeClick
   			}
   		};
        tree = $.fn.zTree.init($("#tree-rec"), setting, zNodes);
        var node = tree.getNodeByParam("name", "地块土壤类型", null);
        tree.expandNode(node, true, false, false);
    });


    function treeClick(event, treeId, treeNode, clickFlag) {
        ids = treeNode.id;
    }

    //添加操作
    function addBtn() {
        if (ids == "") {
            showMessage("请选择父节点");
        } else {
            var node = tree.getSelectedNodes()[0];
            window.location.href = "${root}/bs/landtype/add/${menuid}/" + ids + "/";
        }
    }
	//修改
    function updateBtn() {
        if (ids == "") {
            showMessage("请选择要修改的节点");
        } else if (ids == "0") {
        	showMessage("根节点不允许修改");
        } else {
            window.location.href = "${root}/bs/landtype/update/${menuid}/" + ids + "/";
        }

    }
	//删除操作
    function deleteBtn() {
    	if (ids == "") {
            showMessage("请选择要删除的节点");
        } else {
        	var node = tree.getSelectedNodes()[0];
        	if (node.isParent) {
        		showMessage("该节点下仍有子节点存在，不能删除");
        	} else if (node.id == "0") {
        		showMessage("根节点不能删除");
        	} else {
        		bootbox.confirm("删除后数据将无法恢复，确定要删除吗？",function(a) {
                    if (!a) return;
                    var url = "${root}/bs/landtype/doDelete/";
                    $.ajax({
                        type: 'post',
                        url: url,
                        data : "ids="+ids,
                        success: function (msg) {
                            if (msg.result == "ok") {
                                $("#alert-div").removeClass("alert-danger").addClass("alert-success");
                                showMessage("删除成功");
                                setTimeout("window.location.reload()", 1600);
                            }
                            if (msg.result == "error") {
                                showMessage(msg.message);
                            }
                        }
                    });
                });
        	}
        }
    }
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>