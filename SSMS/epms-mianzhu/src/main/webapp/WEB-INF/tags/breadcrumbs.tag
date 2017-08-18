<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<div class="breadcrumbs" id="breadcrumbs">
    <script type="text/javascript">
        try {
            ace.settings.check('breadcrumbs', 'fixed')
        } catch (e) {
        }
    </script>

    <ul class="breadcrumb">
        <li>
            <i class="ace-icon fa  fa-map-marker home-icon"></i>
            <span id="p_menu"></span>
        </li>
        <li class="active" id="s_menu"></li>
    </ul>
    <!-- /.breadcrumb -->

    <!-- #section:basics/content.searchbox -->
<!--     <div class="nav-search" id="nav-search"> -->
<!--         <form class="form-search"> -->
<!-- 			<span class="input-icon"> -->
<!-- 				<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" -->
<!--                        autocomplete="off"/> -->
<!-- 				<i class="ace-icon fa fa-search nav-search-icon"></i> -->
<!-- 			</span> -->
<!--         </form> -->
<!--     </div> -->
    <!-- /.nav-search -->

    <!-- /section:basics/content.searchbox -->
</div>