<%@ page language="java" pageEncoding="UTF-8" %>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" type="text/css"/>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/js/tree.js'/>" type="text/javascript"></script>
<div class="row" style="margin:0;">
    <form method="post" id="jvForm" class="form-inline" onsubmit="ajaxSubmit();return false;">
        <!-- 左侧树菜单构开始 -->
        <div class="col-sm-2" style="border:1px solid #bdc5d9">
            <ul id="tree-dev" class="ztree" style="margin-left:1px;min-width:200px;height:450px;overflow: auto"></ul>
        </div>
        <!-- 左侧树菜单构结束 -->
        <div class="col-sm-10" style="margin-right:0;">

            <input type="hidden" id="root" name="root"/>
            <textarea id="old-source" style="display:none;"></textarea>

            <div class="col-xs-12">
                <div class="search_item" style="margin-bottom:10px">
                    <span>文件名：</span> <input type="text" value="" disabled="disabled" id="name" size="40" class="disabled"/>
                    <span style="line-height:30px;">按Ctrl+s保存</span>
                </div>

                <div id="source_div">
                    <textarea class="textarea" id="source"
                              onkeydown="if((event.keyCode==115||event.keyCode==83)&&event.ctrlKey){ajaxSubmit();return false;}"
                              style="height:439px;width:98%; font-size:14px;overflow:auto;color:#000"></textarea>
                </div>

                <div class="clearfix form-actions">
                    <div class="col-md-offset-2 col-md-10">
                        <button class="btn btn-primary" type="button">
                            <i class="ace-icon fa fa-submit bigger-110"></i> 确 定
                        </button>
                        <button class="btn" type="reset" onclick="resetValue()">
                            <i class="ace-icon fa fa-undo bigger-110"></i> 重 置
                        </button>
                    </div>
                </div>
            </div>
         </div>
    </form>
</div>
<script type="text/javascript">
    function treeClick(event, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("tree-dev");
        if (treeNode.isfolder == "1") {
            zTree.expandNode(treeNode);
        }

        if (treeNode.isfolder == "0") {
            var url = "${root}/system/protect/getFileContent/?name=" + treeNode.path;
            $("#root").val(treeNode.path);
            $("#name").val(treeNode.name);
            $.ajax({
                type: "POST",
                url: url,
                success: function (msg) {
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
            "root": $("#root").val(),
            "source": $("#source").val()
        }, function (data) {
            if (data.result == 'ok') {
                alert("保存成功");
            } else {
                alert(data.message);
            }
        }, "json");
    }

    $(document).ready(function () {
        var url = "${root}/system/protect/folderList/?name=";
        var settings = {
            async: {
                enable: true,
                url: url,
                autoParam: [ "name=path" ]
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: "0"
                }
            },
            callback: {
                onClick: treeClick,
                onExpand: zTreeOnExpand
            }
        };

        $.fn.zTree.init($("#tree-dev"), settings, null);
        $("#source").height($('body').height() * 0.85);
        $("#tree-dev").height($('body').height() * 0.85 + 150);
    });

    function zTreeOnExpand() {
    }
</script>