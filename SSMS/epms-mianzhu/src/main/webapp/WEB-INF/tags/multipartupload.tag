<%@tag pageEncoding="UTF-8" %>
<%@ attribute name="attachments" type="java.lang.String" required="false"%>
<%@ attribute name="isShowPage" type="java.lang.Boolean" required="false"%>
<%@ attribute name="modulName" type="java.lang.String" required="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<script src="${root}/compnents/MapFrame/MapFactory/ThirdParty/JSON/json2.js"></script>

<table id="_filetable" style="width:100%;">
</table>
<c:if test="${!isShowPage}">
    <iframe id="_fileUpload" src="${root}/forward/common/fileUpload/?modulName=${modulName}"
            width="100%" height="30" scrolling="no" style="padding:0; background:none;" frameborder="0"></iframe>
    <input type="button" value="上传" id="_fileUploadBtn">
</c:if>



<script>
    $(document).ready(function() {
        <c:if test="${!isShowPage}">
            _initUpload();
        </c:if>
        // 如果attachments有值则给table中添加内容
        var files = '${attachments}';
        if(files) {
            var fileList = JSON2.parse(files);

            for(var _file in fileList) {
                _addFileDateToTable(fileList[_file]);
            }
        }

    });

    function getUploadFilesJsonStr() {
        var jsonObj = [];
        $("#_filetable tr").each(function() {
            var tds = $(this).children();
            var td1 = $($(tds[0]).children()[0]).text();
            var td2 = $(tds[1]).attr("originalpath");
            var td3 = $(tds[2]).attr("webpath");
            jsonObj.push({"original":td1, "originalpath": td2, "webpath": td3});
        });
        var jsonObjStr = JSON2.stringify(jsonObj);
        return jsonObjStr;
    }
    // onload次数
    var _iframeOnload = 1;
    // 初始化上传组建
    function _initUpload(){
        $("#_fileUploadBtn").click(function(){
            _iframeOnload++;
            var val = document.getElementById("_fileUpload").contentWindow.document.getElementById("filename").value;
            if(val == ""){
                alert("请选择添加的附件");
                return;
            }

            var typeName = val.substr(val.lastIndexOf("."), val.length);
            if (".txt.doc.docx.xls.xlsx.ppt.pptx.pdf".indexOf(typeName) == -1) {
                alert("附件类型必须为所列举的一种：.txt.doc.docx.xls.xlsx.ppt.pptx.pdf");
                return;
            }
            $(document.getElementById("_fileUpload").contentWindow.document.getElementById("fileUploadForm")).submit();
        });

        // 根据ie中iframe不识别onload事件，进行其进行单独onload事件绑定
        if($.browser.msie){
            document.getElementById("_fileUpload").attachEvent("onload",iframeOnloadEvt);
        }else{
            document.getElementById("_fileUpload").onload = iframeOnloadEvt;
        }

        // iframe的onload事件
        function iframeOnloadEvt(e){
            // 防止IE第一次onload运行
            if(_iframeOnload == 1){// && $.browser.msie){
                return false;
            }
            var returnStringFlag;
            if($.browser.msie){
                returnStringFlag = document.getElementById("_fileUpload").contentWindow.document.body.innerText;
                //returnStringFlag = $.parseJSON(returnStringFlag);
                //returnStringFlag = eval("("+this.contentDocument.body.innerText+")");
            }else{
                returnStringFlag = this.contentDocument.body.innerText;
            }
            var reg = new RegExp("<pre>|</pre>","ig");
            returnStringFlag = returnStringFlag.replace(reg,"");
            returnStringFlag = JSON2.parse(returnStringFlag);

            $(document.getElementById("_fileUpload").contentWindow.document.body).css({
                "margin":0,
                "padding":0
            });

            if(returnStringFlag.status == "success"){
                // 添加数据到table
                _addFileDateToTable(returnStringFlag);
            }else if(returnStringFlag.status == "failure"){
                alert("上传失败了");
            }

            _iframeOnload = 1;
            document.getElementById("_fileUpload").setAttribute("src","${root}/forward/common/fileUpload/?modulName=${modulName}");
        }
    }
    function _addFileDateToTable(returnStringFlag) {
        var _t = $("#_filetable");
        var _tr = $("<tr>").appendTo(_t);

        var filea = $("<td><a href=\"${root}/downloadfile/" + returnStringFlag.original + "/?filepath=" + returnStringFlag.originalpath + "\" title=\"点击下载附件\" style=\"margin-right: 15px;\">" + returnStringFlag.original + "</a></td>");
        filea.appendTo(_tr);

        var _td2 = $('<td>').attr("originalpath",returnStringFlag.originalpath).css("width","30px");
        $('<a href="javascript:void(0);" title="点击在线浏览">浏览</a>').appendTo(_td2);
        _td2.appendTo(_tr);
        _td2.click(function(){
            window.open("${root}/files/viewByPath?path="+returnStringFlag.webpath, "", "location:no,menubar:no,status:no,toolbar:no,titlebar:no");
        });

        <c:if test="${!isShowPage}">
            $("<td>").appendTo(_tr).css("width","30px").attr("webpath",returnStringFlag.webpath).html("删除").bind('click',function(){
                if(confirm("确定要删除吗?")){
                    $(this).parents("tr").remove();
                }
            });
        </c:if>
    }
</script>