<%@tag pageEncoding="UTF-8" import="com.xiangxun.atms.framework.cache.*"%>
<%@ tag import="com.google.common.collect.Table" %>
<%@ tag import="com.xiangxun.atms.module.reserve.vo.Nature" %>
<%@ attribute name="type" type="java.lang.String" required="true"%>
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
    Cache cache =(Cache)com.xiangxun.atms.framework.base.ApplicationContextHolder.getBean("ehcacheImpl");
    com.google.common.collect.Table table = (com.google.common.collect.Table)cache.get("Reserve");
    if(table==null){
        out.write("");
    }else{
        out.write(table.column(type).get(codeValue)==null?"":table.column(type).get(codeValue)+"");
    }
%>
" maxlength="20" readonly  style="${style}" onclick="showReserveDialog();"  class="input-large ">
<input type="hidden" id="${codeName}" name="${codeName}" value='${codeValue}' />
<div id="reservedialog-message" class="hide">
    <div class="ztree_box" style="margin-right:0; margin-left:-11px;margin-bottom:5px; height:320px; width:290px; overflow:auto;">
        <ul id="reservetree-rec" class="ztree tree_border2 ztree_ul"></ul>
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
    function reserveTreeOnclick(event, treeId, treeNode, clickFlag) {
        var chooseId = treeNode.id;
        if(chooseId!='00'){
            document.getElementById('${name}').value= treeNode.name;
            document.getElementById('${codeName}').value= treeNode.id;
            $("#reservedialog-message").dialog( "close" );
        }
    }

    var reserveSetting = {
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
            onClick : reserveTreeOnclick
        }
    };
    var tree;
    var url = "${root}/nature/showTreeNode/${menuid}/";
    <c:if test="${type == 'Water'}">
        url = "${root}/water/showTreeNode/${menuid}/";
    </c:if>
    $.getJSON(url, function(zNodes) {
        tree = $.fn.zTree.init($("#reservetree-rec"), reserveSetting, zNodes);
        var node = tree.getNodeByParam("pId", 0, null);
        tree.expandNode(node, true, true, true);
    });
    function showReserveDialog(){
        var dialog = $( "#reservedialog-message" ).removeClass('hide').dialog({
            modal: true,
            title: "<div class='widget-header' style='margin:0;padding:0'><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i>请选择保护区</h4></div>",
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