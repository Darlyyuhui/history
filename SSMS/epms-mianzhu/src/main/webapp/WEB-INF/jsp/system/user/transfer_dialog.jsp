<%@ page language="java" pageEncoding="UTF-8"%>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" type="text/css" />
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck.min.js'/>" type="text/javascript"></script>

<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none;">
    <p id="alert-content" align="center"></p>
</div>
<div class="alert alert-block pull-top alert-success" id="alertsuc-div" style="display:none;">
    <p id="alertsuc-content" align="center"></p>
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
<script type="text/javascript">
    function setMesg(s){
        $("#subinfo").css("display","");
        document.getElementById("mesg").innerHTML = s;
        setTimeout('$("#subinfo").hide("slow")', 1200);
    }
</script>
<!-- 控制iframe中的页面返回的结果显示结束 -->
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
    function reValues(){
        $("#name").val("");
        $("#account").val("");
    }
</script>
<!-- 重置查询输入框结束 -->
<div class="row-fluid conten_box">
    <div class="row-fluid search-area" style="padding:5px 0 4px;">
        <form class="form-inline" action="${root}/system/user/dialog/showtransfer/${menuid}/" method="post" style="margin:0;">
            <table>
                <tr>
                    <td class="td54">姓 名</td>
                    <td><input class="input" style="width:130px;height:18px;" id="name"
                               name="search_name" value="${search["name"]}" type="text" placeholder="姓名" /></td>
                    <td class="td54">账 号</td>
                    <td><input class="input" style="width:130px;height:18px;" id="account"
                               name="search_account" value="${search["account"]}" type="text" placeholder="账号" /></td>
                    <td><input type="submit" class="btn btn-info mar_l10 mar_r10" value="查询" style="height:28px;*+margin-left:10px;"/>
                        <input onclick="reValues()" type="button" class="btn" value="重置" style="height:28px;"/></td>
                </tr>
            </table>
        </form>
    </div>
    <div class="clear"></div>
    <div class="row-fluid">
        <div class="conten_box">
            <div class="row-fluid" style="margin-left:0;padding-left:0;">
                <div class="pull-left ztree_box span3" style="margin-right:0; margin-left:0; height:320px;height:320px\0;#height:320px;_height:320px;
					width:180px; overflow:scroll; position:fixed; left:11px;">
                    <ul id="tree-rec" class="ztree tree_border2 ztree_ul" style="width:240px;"></ul>
                </div>
                <div class="pull-left" style="margin-left:190px">
                    <table class="table table-striped table-bordered table-hover table-style" id="table" style="width:470px">
                        <thead>
                        <tr>
                            <th width="16">选择</th>
                            <th>姓名</th>
                            <th>账号</th>
                            <th>所属部门</th>
                            <th>联系方式</th>
                        </tr>
                        </thead>
                        <tbody id="tbody">
                        <c:forEach items="${pageList.result}" var="user">
                            <tr onclick="rowOnclick(this)">
                                <td style="text-align:center;"><input type="radio" value="${user.id}" name="select-chk" /></td>
                                <td>${user.name}</td>
                                <td>${user.account}</td>
                                <td><tags:cache keyName="Department" id="${user.deptid}"></tags:cache></td>
                                <td>${user.mobile}</td>
                            </tr>
                        </c:forEach>
                        <c:if test="${pageList.result!=null}">
                            <c:forEach begin="1" end="${10-fn:length(pageList.result)}">
                                <tr>
                                    <td colspan="8">&nbsp;</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                    <tags:pagination page="${pageList}"></tags:pagination>
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="row-fluid text-info muted"  style="margin-left:0;padding-left:0;">
            <label class="pull-left" style="margin-left: 5px;"> <span>移交方式：</span><input type="checkbox" name="transfer" value="1" style="margin-top: -2px;margin-right: 5px;">电话移交</label>
            <label  class="pull-left"  style="margin-left: 5px;"> <input type="checkbox" name="transfer" value="2" style="margin-top: -2px;margin-right: 5px;">F2F交接</label>
        </div>
    </div>
    <!--/row-->
</div>
<script type="text/javascript">
    <!--
    var chooseNode;
    var chooseId;
    var firstAsyncSuccessFlag = 0;

    function treeOnlick(event, treeId, treeNode, clickFlag) {
        chooseId = treeNode.id;
        window.location.href = "${root}/system/user/dialog/showtransfer/${menuid}/?search_orgId="+ chooseId;
    }

    //$(document).ready(function() {
    var setting = {
        async : {
            enable : true,
            autoParam : [ "id" ]
        },
        check : {
            enable : false,
            chkStyle : "radio"
        },
        data : {
            simpleData : {
                enable : true
            }
        },
        callback : {
            onClick : treeOnlick
        }
    };

    $.getJSON("${root}/system/org/showTreeNode/${menuid}/",function(zNodes){
        tree = $.fn.zTree.init($("#tree-rec"), setting, zNodes);
        var node = tree.getNodeByParam("id", 0, null);
        tree.expandNode(node, true, false, true);
        chooseId = '${orgId}';
        chooseNode = tree.getNodeByParam("id", chooseId, null);
        tree.selectNode(chooseNode);
    });

    $("#alert-div").hide();
    //});
    //-->

    function sendValue(){
        var id=$('input[name="select-chk"]:checked').val();
        if (id) {
           return id;
        }else{
         	showMessage("请选择招商专员！");

        }
        
    }
    function getTransferType(){
        return $("input[name='transfer']:checked");
    }

</script>
