<%@tag pageEncoding="UTF-8" import="com.xiangxun.atms.framework.cache.*"%>
<%@ tag import="com.google.common.collect.Table" %>
<%@ tag import="com.xiangxun.atms.module.reserve.vo.Nature" %>
<%@ tag import="com.xiangxun.atms.module.nd.service.NdInfoService" %>
<%@ tag import="java.util.List" %>
<%@ tag import="com.xiangxun.atms.module.nd.vo.NdInfo" %>
<%@ attribute name="style" type="java.lang.String" required="false"%>
<%@ attribute name="name" type="java.lang.String" required="false"%>
<%@ attribute name="value" type="java.lang.String" required="false"%>

<%@ attribute name="codeValue" type="java.lang.String" required="false"%>
<%@ attribute name="codeName" type="java.lang.String" required="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="menuid" type="java.lang.String" required="false"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" type="text/css" />
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck-3.5.min.js'/>" type="text/javascript"></script>
<link rel="stylesheet" href="${root}/compnents/ace/css/jquery-ui.min.css" />
<script src="${root}/compnents/ace/js/jquery-ui.min.js"></script>
<input type="text" id="${name}" name="${name}" value="
<%
    NdInfoService ndInfo =(NdInfoService)com.xiangxun.atms.framework.base.ApplicationContextHolder.getBean("ndInfoServiceImpl");
    String ndInfoName = ndInfo.getNameByKey(codeValue);

    if(ndInfoName==null){
        out.write("");
    }else{
        out.write(ndInfoName);
    }
%>
" maxlength="20" readonly  style="${style}" onclick="showNdDialog();"  class="input-large ">
<input type="hidden" id="${codeName}" name="${codeName}" value='${codeValue}' />
<div id="nddialog-message" class="hide">
    <div class="ztree_box" style="margin-right:0; margin-left:-11px;margin-bottom:5px; height:320px; width:290px; overflow:auto;">
        <ul id="ndtree-rec" class="ztree tree_border2 ztree_ul"></ul>
    </div>
</div>

<script type="text/javascript">
    $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
        _title: function(title) {
            var $title = this.options.title || '&nbsp;'
            if( ("title_html" in this.options) && this.options.title_html == true )
                title.html($title);
            else title.text($title);
        }
    }));
    function ndTreeOnclick(event, treeId, treeNode, clickFlag) {
        var chooseId = treeNode.id;
        if(chooseId!='00'){
            document.getElementById('${name}').value= treeNode.name;
            document.getElementById('${codeName}').value= treeNode.id;
            $("#nddialog-message").dialog( "close" );
        }
    }

    var ndSetting = {
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
            onClick : ndTreeOnclick
        }
    };
    var ndtree;
    $.getJSON("${root}/nd/info/showTreeNode/${menuid}/", function(zNodes) {
        ndtree = $.fn.zTree.init($("#ndtree-rec"), ndSetting, zNodes);
        var node = ndtree.getNodeByParam("pId", 0, null);
        ndtree.expandNode(node, true, true, true);
    });
    function showNdDialog(){
        var dialog = $( "#nddialog-message" ).removeClass('hide').dialog({
            modal: true,
            title: "<div class='widget-header' style='margin:0;padding:0'><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i>请选择矿区</h4></div>",
            title_html: true,
            buttons: [
                {
                    text: "关闭",
                    "class" : "btn btn-xs",
                    click: function() {
                        $( this ).dialog( "close" );
                    }
                },
                {
                    text: "确定",
                    "class" : "btn btn-primary btn-xs",
                    click: function() {
                        $( this ).dialog( "close" );
                    }
                }
            ]
        });
        $(".ui-dialog .ui-dialog-titlebar").css("padding","0");
    }


</script>