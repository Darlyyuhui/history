<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<div class="alert alert-block pull-top alert-danger" id="alert-div" style="display:none">
    <p id="alert-content" align="center"></p>
</div>
<c:if test="${not empty message}">
    <div id="message" class="alert alert-success">
        <button data-dismiss="alert" class="close">×</button>
        <p align="center">${message}</p>
    </div>
    <script>
        setTimeout('$("#message").hide("slow")', 1200);    </script>
</c:if>
<div class="page-header">
    <h1>
      数据校验结果
        <small><i class="ace-icon fa fa-angle-double-right"></i>
            结果列表
        </small>
    </h1>
</div>
<div class="row">
	<div class="profile-user-info profile-user-info-striped">
		<div class="profile-info-row">
	        <div class="profile-info-name">样品来源</div>
	        <div class="profile-info-value">
	        	${dataCheckInfo.schemeName }
	        </div>
	        
	        <div class="profile-info-name">样品种类</div>
	        <div class="profile-info-value">
	        	<tags:xiangxuncache keyName="Dic" typeCode="SAMPLING_TYPES" id="${dataCheckInfo.sampleType }"/>
	        </div>
	    </div>
	    
	    <div class="profile-info-row">
	        <div class="profile-info-name">校验样品数</div>
	        <div class="profile-info-value">
	        	${dataCheckInfo.analysisCount }
	        </div>
	        
	        <div class="profile-info-name">异常个数</div>
	        <div class="profile-info-value">
	        	${pageList.totalSize }
	        </div>
	    </div>
	    
	    <div class="profile-info-row">
	        <div class="profile-info-name">采样单位</div>
	        <div class="profile-info-value">
	        	${dataCheckInfo.samplingDept }
	        </div>
	        
	        <div class="profile-info-name">分析单位</div>
	        <div class="profile-info-value">
	        	${dataCheckInfo.analysisDept }
	        </div>
	    </div>
	    
	    <div class="profile-info-row">
	        <div class="profile-info-name">监测项目</div>
	        <div class="profile-info-value">
	        	${dataCheckInfo.testItems }
	        </div>
	        
	        <div class="profile-info-name">校验时间</div>
	        <div class="profile-info-value">
	        	<fmt:formatDate value="${dataCheckInfo.checkTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
	        </div>
	    </div>
	</div>
    <div class="col-xs-12">
		<button class="btn btn-sm btn-success pull-left" style="margin-left:2px;" onclick="goPList();"><i class="icon-plus"></i>返回上级列表</button>
    </div>
</div>
<div class="row" style="margin-top:1px;">
    <div class="col-xs-12">
        <table id="table" class="table table-striped table-bordered table-hover table-style" style="text-align: center">
            <thead>
            <tr>
                <th>样品编号</th>
                <th>异常分析项</th>
                <th>异常类型</th>
                <th>是否无效数据</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <c:forEach items="${pageList.result}" var="item">
                <tr ${item.isInvalid eq '1' ? 'style="color:red"' : '' }>
	                <td>${item.regCode }</td>
	                <td>${item.errItems }</td>
	                <td>${item.errTypes }</td>
	                <td>${item.isInvalid eq '1' ? '是' : '否' }</td>
                </tr>
            </c:forEach>
            <c:if test="${pageList.result!=null}">
                <c:forEach begin="1" end="${15-fn:length(pageList.result)}">
                    <tr>
                        <td colspan="68">&nbsp;</td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
        <div class="row">
            <c:if test="${pageList!=null}">
                <tags:pagination page="${pageList}"></tags:pagination>
            </c:if>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
	function goPList() {
		window.location.href = "${root}/check/info/list/${menuid}/?isgetsesion=1";
	}
    $(document).ready(function () {
        $("#alert-div").hide();
    });
</script>
<%@ include file="/WEB-INF/jsp/common/fooltertags.jspf" %>