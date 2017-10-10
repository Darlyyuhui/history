<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String moudelId = request.getParameter("moudelId");
	String sPath = request.getServletPath();
	if (moudelId == null && (sPath != null && sPath.indexOf("home/index.jsp") < 0)) {
		moudelId = "999";
	}
%>
<c:set var="moudelId" value="<%=moudelId %>"/>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<style>
    #baojing li{padding-top:10px; border-bottom: 1px solid #DADEE0;}
    #baojing li .fa{padding-right:5px;}

</style>
<div id="navbar" class="navbar navbar-fixed-top" style="background: #01253d">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>
    <div class="navbar-container" id="navbar-container" style="padding-right: 0px;">
        <!-- #section:basics/sidebar.mobile.toggle -->
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler">
            <span class="sr-only">Toggle sidebar</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <!-- /section:basics/sidebar.mobile.toggle -->
        <div class="navbar-header pull-left">
            <!-- #section:basics/navbar.layout.brand -->
            <c:if test="${hasIndex eq '1' }">
            <a href="javascript:goHome();" class="navbar-brand" style="height:63px;font-size: 36px; line-height:39px;">

                <img src="${root}/compnents/ace/images/top-logo.png" alt="" style="position: relative;left: 10px"/>
            </a>
            </c:if>
            <c:if test="${hasIndex ne '1' }">
            <a href="javascript:void();" class="navbar-brand" style="height:63px; line-height:58px;">
                <img src="${root}/compnents/ace/images/logo.png" alt="" style="position: relative;left: 20px"/>
            </a>
            </c:if>
            <!-- /section:basics/navbar.toggle -->
        </div>
        <!-- #section:basics/navbar.dropdown -->
        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <!-- #section:basics/navbar.user_menu -->
                <li class="light-blue">
                    <a data-toggle="dropdown" href="javascript:void(0)" class="dropdown-toggle" style="background-color: transparent">
                        <img class="nav-user-photo" src="${root}/compnents/ace/avatars/user.jpg"/>
                        <span class="user-info" style="color:#cccfd3">
									<small style="padding-top:15px;">欢迎你,</small>
									${userName } 
								</span>

                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>
                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close"
                        style="top: 80px;z-index: 10;border: none">
                        <li>
                            <a href="#modal-Profile" data-toggle="modal" data-backdrop="static">
                                <i class="ace-icon fa fa-user"></i>
                                修改密码
                            </a>
                        </li>

                        <li class="divider"></li>

                        <li>
                            <a href="${root}/j_spring_security_logout">
                                <i class="ace-icon fa fa-power-off"></i>
                                安全退出
                            </a>
                        </li>
                    </ul>
                </li>
                <!-- /section:basics/navbar.user_menu -->
            </ul>
        </div>
        <div id="top-menu" class="nav-collapse navbar-buttons navbar-header pull-right" style="margin-right:1px;line-height:90px;" role="navigation">
            <ul id="top-menu-ul" class="nav ace-nav navbar-inner">
            	<c:if test="${hasIndex eq '1' }">
                <li onclick="javascript:goHome(this);" class="lili" style="border:0;">
                    <a href="javascript:void(0);" style="background-color: transparent">
                        <img src="" alt="" style="margin-top:-4px;" class="zhuangshi"/>
                        <span style="padding: 0 2px;"></span>
                        <span style="font-size:18px;color:#cccfd3">全市总览</span>
                    </a>
                </li>
                </c:if>
                <c:forEach items="${one_menus}" var="menu" varStatus="m">
                    <li onclick="javascript:showSecondMenu('${menu.id}',this);" class="lili" style="border:0;">
                        <a href="javascript:void(0);" style="background-color: transparent">
                            <img src="" alt="" style="margin-top:-4px;" class="zhuangshi"/>
                            <span style="padding: 0 2px;"></span>
                            <span style="font-size:18px;color:#cccfd3">${menu.name}</span>
                        </a>
                        <img src="${root}/compnents/ace/images/j.png" alt="" style="position: absolute;top: 0;height: 60px;"/>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <!-- /section:basics/navbar.dropdown -->
    </div>
    <!-- /.navbar-container -->
</div>
<!-- 修改密码 -->
<div id="modal-Profile" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header no-padding">
                <div class="table-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <span class="white">&times;</span>
                    </button>
                    修改密码
                </div>
            </div>
            <div class="modal-body no-padding">
                <form class="form-horizontal" id="updatePasd" role="form" action="${root}/system/user/changepasswd" method="post">
                    <div class="form-group" style="margin-top: 10px;">
                        <label class="col-sm-3 control-label no-padding-right" for="pasd">原密码 </label>

                        <div class="col-sm-9">
                            <input type="password" id="pasd" name="old" class="form-control required"
                                   style="width: 80%"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="pasd1">新密码 </label>

                        <div class="col-sm-9">
                            <input type="password" id="pasd1" name="pwd" class="form-control required"
                                   style="width: 80%"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="pasd2">确认新密码</label>

                        <div class="col-sm-9">
                            <input type="password" name="pasd2" id="pasd2" class="form-control" required
                                   style="width: 80%"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer no-margin-top" style="padding-top:8px;padding-bottom:8px;">
                <span class="text-warning" id="upPasdMsg"></span>
                <button class="btn btn-primary btn-xs" type="button" onclick="return upPasd();">
                    <i class="ace-icon fa fa-check bigger-110"></i>
                    修改
                </button>
                <button class="btn btn-xs" class="close" data-dismiss="modal" aria-hidden="true">
                    <i class="glyphicon glyphicon-remove"></i>
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

        $(function(){
            for(var i=0;i<$(".zhuangshi").length;i++){
                var a = i+1;
                $(".zhuangshi").eq(i).attr("src","${root}/compnents/ace/images/"+a+".png");
            };      
            var r = window.location.search.substr(1).split("&");
            var liliIndex = "${index}";
            for(var i=0;i<$(".lili").length;i++){
                $(".lili").css("background","");
                $(".lili").eq(liliIndex).css("background","#1f4665");
            };

            $(".lili").hover(function(){
                $(".lili").css("background","");
                $(this).css("background","#1f4665");
            },function(){
                for(var i=0;i<$(".lili").length;i++){
                    $(".lili").css("background","");
                    $(".lili").eq(liliIndex).css("background","#1f4665");
                };
            });
        });
    function upPasd() {
        if ($("#pasd").val() == "") {
            $("#upPasdMsg").html("请输入原密码");
            $("#pasd").focus();
            return false;
        }
        if ($("#pasd1").val() == "" || $("#pasd2").val() == "") {
            $("#upPasdMsg").html("请输入新密码密码");
            return false;
        }
        if ($("#pasd1").val() != $("#pasd2").val()) {
            $("#upPasdMsg").html("新密码与确认密码不一致");
            $("#pasd2").focus();
            return false;
        }
        $.getJSON("${root}/system/user/changepasswd/" + $("#pasd").val() + "/" + $("#pasd1").val(),
                function (data) {
                    if (data.result == 'ok') {
                        $("#pasd").val("");
                        $("#pasd1").val("");
                        $("#pasd2").val("");
                        $('#modal-Profile').modal('hide');
                        alert("修改成功");
                    }
                    if (data.result == '502') {
                        alert(data.message);
                        window.top.location.href = "${root}/login";
                    }
                    if (data.result == 'error') {
                        $("#upPasdMsg").html(data.message);
                    }
                });
    }
    var onoff = true;
    function showSecondMenu(id, a) {
        var index = $(a).index();
        $(a).addClass("green");
        window.location.href = "${root}/home/admin/?moudelId=" + id+"&index="+index+"";
    }

    $(function() {
    	if ("${hasIndex}" == "0" && ("${moudelId}" == "" || "${moudelId}" == "0")) {
    		$(".btn-select-mobles:first").click();
    	}
    });
    <c:if test="${hasIndex eq '1' }">
    function goHome() {
       window.location.href = "${root}/home/admin/?moudelId=0"+"&index=0";
    }
    </c:if>
</script>
<!-- 修改密码 -->