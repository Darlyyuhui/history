<%@tag pageEncoding="UTF-8" import="com.xiangxun.atms.framework.cache.*"%>
<%@ tag import="com.xiangxun.atms.common.system.cache.MenuCache" %>
<%@ tag import="java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="menus" type="java.util.List" required="true" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%
   Cache cache = (Cache) com.xiangxun.atms.framework.base.ApplicationContextHolder.getBean("ehcacheImpl");
   java.util.Map<String,String> maps = (Map)cache.get(MenuCache.MENU_CACHE_MAP);
   request.setAttribute("menumap",maps);
%>
<script type="text/javascript">
    document.getElementById("main-container").style.marginTop = "61px";
    var pmenu,smenu;
    function showMain(url) {
        var reg=new RegExp("^http");
        if (reg.test(url)) {
            window.open(url+"?index="+liliIndex+"");
        } else {
            window.location.href = "${root}/forward/page/?farurl=" + url;
        }
    }
</script>
<!-- #section:basics/sidebar -->
<div id="sidebar" class="sidebar responsive" style="margin-top:-1px;">
    <script type="text/javascript">
        try {
            ace.settings.check('sidebar', 'fixed')
        } catch (e) {
        }
    </script>
    <!-- /.sidebar-shortcuts -->
    <ul class="nav nav-list">
        <c:forEach items="${secmenus}" var="menu" varStatus="m">
            <li class="hsub ${menumap[menuid] eq menu.id?'active open':''} ${m.index eq '0'?'active open':''}">
                <a href="javascript:void(0)" class="dropdown-toggle">
                    <i class="menu-icon fa ${menu.icon}"></i>
                    <span class="menu-text">${menu.name}</span>
                    <b class="arrow fa fa-angle-down"></b>
                </a>
                <b class="arrow"></b>
                <c:if test="${menumap[menuid] eq menu.id}">
                    <script>
                        pmenu ="${menu.name}";
                        $("#li_0").removeClass("active open");
                    </script>
                </c:if>
                <ul class="submenu" id="menu_${menu.code}">
                    <c:forEach items="${menu.sonResourceList}" var="sonmenu">
                        <li class="hsub ${menuid eq sonmenu.id?'active':''}">
                            <a href="javascript:showMain('${sonmenu.content}')">
                                <i class="menu-icon fa ${menu.icon}"></i>
                                    ${sonmenu.name}
                                <b class="arrow"></b>
                            </a>
                        </li>
                        <c:if test="${menuid eq sonmenu.id}">
                            <script>smenu ="${sonmenu.name}"</script>
                        </c:if>
                    </c:forEach>
                </ul>
            </li>
        </c:forEach>
    </ul>
    <!-- #section:basics/sidebar.layout.minimize -->
    <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
        <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left"
           data-icon2="ace-icon fa fa-angle-double-right"></i>
    </div>
    <!-- /section:basics/sidebar.layout.minimize -->
    <script type="text/javascript">
        $(document).ready(function(){
            var p = '${menuid}';
            if(p.length>0){
                $("#home-li-active").removeClass("active");
            }
            $("#p_menu").html(pmenu);
            $("#s_menu").html(smenu);
        });
        try {
            ace.settings.check('sidebar', 'collapsed')
        } catch (e) {
        }
    </script>
</div>
