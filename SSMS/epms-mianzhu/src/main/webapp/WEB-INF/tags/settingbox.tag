<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<div class="ace-settings-container hide" id="ace-settings-container">
    <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
        <i class="ace-icon fa fa-cog bigger-150"></i>
    </div>

    <div class="ace-settings-box clearfix" id="ace-settings-box" style="min-height: 70px;">
        <div class="pull-left width-50">
            <!-- #section:settings.skins -->
            <div class="ace-settings-item">
                <div class="pull-left">
                    <select id="skin-colorpicker" class="hide" data-userskin="${skin }">
                        <option data-skin="no-skin" value="#438EB9" ${skin eq 'no-skin'?'selected':''}>#438EB9</option>
                        <option data-skin="skin-1" value="#222A2D" ${skin eq 'skin-1'?'selected':''}>#222A2D</option>
                        <option data-skin="skin-2" value="#C6487E" ${skin eq 'skin-2'?'selected':''}>#C6487E</option>
                        <option data-skin="skin-3" value="#D0D0D0" ${skin eq 'skin-3'?'selected':''}>#D0D0D0</option>
                    </select>
                </div>
                <span>&nbsp; 选择皮肤 </span>
            </div>

            <!-- /section:settings.skins -->

        
            <!-- /section:settings.container -->
        </div>

    </div>
    <!-- /.ace-settings-box -->
</div>
<script type="text/javascript">
	function changeskin(){
		$.getJSON("${root}/system/user/changeskin/"+$('#skin-colorpicker').find('option:selected').data('skin'),
		function(data) {
			if (data.result == 'ok') {
				$("#ace-settings-box").toggleClass("open");
			}
			if (data.result == '502') {
				alert(data.message);
				window.top.location.href = "${root}/login";
			}
			if (data.result == 'error') {
				alert("皮肤选择失败");
			}
		});
	}
</script>
<!-- /.ace-settings-container -->