<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<tags:selTree idElement="regionId" nameElement="regionName" treeType="region" />
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<c:if test="${not empty message}">
    <div id="message" class="alert alert-success">
        <button data-dismiss="alert" class="close">×</button>
        <p align="center">${message}</p>
    </div>
    <script>
        setTimeout('$("#message").hide("slow")', 1200);
    </script>
</c:if>
<div class="page-header">
    <h1>
        水样底泥采样污染源
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            修改污染源信息
        </small>
    </h1>
</div>
<div class="row">
    <form id="inputForm" class="form-horizontal" action="${root}/reg/water/pollute/doUpdate/${menuid }/" method="post"
          style="margin-bottom:0;padding:0;" enctype="multipart/form-data">
        <input type="hidden" name="page" value="${page }" />
        <input type="hidden" name="id" value="${info.id }" />
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name">采样信息</div>
                <div class="profile-info-value">
                	${waterInfo.code }
				</div>
				
				<div class="profile-info-name" style="width: 130px;">污染源类型</div>
                <div class="profile-info-value">
                	<select id="typeCode" name="typeCode" style="min-width:120px; width: 350px;">
                		<tags:diccache typeCode="SAMPLING_WATER_POLLUTE_TYPE" defaultValue="${info.typeCode }" />
                	</select>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">污染源名称</div>
                <div class="profile-info-value">
					<input type="text" id="name" name="name" maxlength="50" value="${info.name }"
						style="min-width:120px; width: 350px;" class="input-large required"/>
					<span style="color: red">*</span>
				</div>
				
				<div class="profile-info-name">特征污染物</div>
                <div class="profile-info-value">
					<input type="text" id="features" name="features" maxlength="50" value="${info.features }"
						style="min-width:120px; width: 350px;" class="input-large"/>
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">污染源规模</div>
                <div class="profile-info-value">
					<input type="text" id="scale" name="scale" maxlength="50" value="${info.scale }"
						style="min-width:120px; width: 350px;" class="input-large"/>
				</div>
				
				<div class="profile-info-name">所属乡镇</div>
                <div class="profile-info-value">
					<input type="text" id="regionName" readonly="readonly" value='<tags:xiangxuncache keyName="TREGION_NAME" id="${info.regionId }"/>'
						style="min-width:120px; width: 350px;" class="input-large" />
					<input type="hidden" id="regionId" name="regionId" value="${info.regionId }" />
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">状态</div>
                <div class="profile-info-value">
					<select id="status" name="status" style="min-width:120px; width: 350px;">
						<tags:diccache typeCode="SAMPLING_WATER_POLLUTE2" defaultValue="${info.status }" />
					</select>
				</div>
				
				<div class="profile-info-name">是否有明显排污口</div>
                <div class="profile-info-value">
					<input type="radio" name="rdo_isOutfall" value="1" ${info.isOutfall eq '1' ? 'checked' : '' }/>是
					<input type="radio" name="rdo_isOutfall" value="0" style="margin-left: 10px;" ${info.isOutfall eq '0' ? 'checked' : '' } />否
					<input type="hidden" id="isOutfall" name="isOutfall" />
				</div>
            </div>
            
            <div class="profile-info-row">
                <div class="profile-info-name">与河距离</div>
                <div class="profile-info-value">
					<input type="text" id="riverDistance" name="riverDistance" maxlength="20" value="${info.riverDistance }"
						style="min-width:120px; width: 350px;" class="input-large number"/>
				</div>
				
				<div class="profile-info-name"></div>
                <div class="profile-info-value">
				</div>
            </div>
        </div>
        <%-- 
        <div class="profile-user-info profile-user-info-striped" style="border-top-style: none;">
            <div class="profile-info-row" >
                <div class="profile-info-name">现场照片</div>
				<div class="profile-info-value">
					<tags:fileinputs uploadFileTypes=".jpg.jpeg.png.bmp" maxFileSize="10" />
				</div>
            </div>
        </div>
         --%>
        <div class="clearfix form-actions">
            <div class="col-md-offset-2 col-md-10">
                <button class="btn btn-primary" type="button" onclick="checkForm()">
                    <i class="ace-icon fa fa-submit bigger-110"></i> 保存
                </button>
                <button class="btn" type="reset" onclick="window.location.href='${root}/reg/water/pollute/list/${menuid }/${waterInfo.id }/'">
                    <i class="ace-icon fa fa-undo bigger-110"></i> 返回
                </button>
            </div>
        </div>
    </form>
</div>

<script>
	var v;
    $(document).ready(function () {
        //聚焦第一个输入框
        //为inputForm注册validate函数
        v = $("#inputForm").validate();
        $("#name").focus();
    });
    function checkForm() {
    	$("#isOutfall").val($("input[name='rdo_isOutfall']:checked").val());
    	if (v.checkForm()) {
    		$("#inputForm").submit();
    	}else{
    		v.showErrors();
    	}
    }
</script>

<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>