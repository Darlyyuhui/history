<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.xiangxun.atms.framework.security.OperatorDetails"%>

<div id="mainInfo" class="row-fluid" style="margin:0px;padding:0;height:600px;overflow:hidden;">
  <div class="conten_box">
    <h4 class="title_intro"><span><img src="${root}/images/picone/device.png"></span>当前用户</h4>
    <div class="mar_5">
    <table class="table table-style table-border-bot table-padd10-lr table-border-rl" id="table">
      <tr>
        <td class="device_td_bg2">姓名：</td>
        <td>${operator.realName}</td>
      </tr>
      <tr>
        <td class="device_td_bg2">部门：</td>
        <td><tags:xiangxuncache keyName="Department" id="${operator.deptId}"></tags:xiangxuncache></td>
      </tr>
      <tr>
        <td class="device_td_bg2">拥有角色：</td>
        <td><c:forEach items="${operator_roles}" var="role"> ${role} </c:forEach></td>
      </tr>
      <tr>
        <td class="device_td_bg2">上次登录时间：</td>
        <td><fmt:formatDate value="${operatorLogin.operationTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
      </tr>
      <tr>
        <td class="device_td_bg2">上次登录IP：</td>
        <td>${operatorLogin.ipAddress}</td>
      </tr>
    </table>
    </div>
  </div>
  <div class="conten_box mar_t10">
    <h4 class="h4_title"><cite><img src="${root}/images/picone/help.png"></cite>最近操作记录</h4>
    <div class="mar_5">
      <table class="table table-striped table-bordered table-condensed table-style" id="table">
        <thead>
          <tr>
            <th>描述</th>
            <th>IP地址</th>
            <th>发生时间</th>
            <th>是否成功</th>
            <th>操作员</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${operation_list}" var="log">
            <tr>
              <td>${log.des}</td>
              <td>${log.ip}</td>
              <td><fmt:formatDate value="${log.operateTime}" pattern="yyyy-MM-dd HH:mm:SS" /></td>
              <td>${log.isSuccess eq '1'?'成功':'失败'}</td>
              <td>${log.operator}</td>
            </tr>
          </c:forEach>
          <c:if test="${operation_list!=null}">
            <c:forEach begin="1" end="${10-fn:length(operation_list)}">
              <tr>
                <td colspan="6">&nbsp;</td>
              </tr>
            </c:forEach>
          </c:if>
        </tbody>
      </table>
    </div>
  </div>
</div>
<script type="text/javascript">
	$(function(){
		var parentiframe = window.parent.document.getElementById("content-frame");
		$(parentiframe)
	})
</script>