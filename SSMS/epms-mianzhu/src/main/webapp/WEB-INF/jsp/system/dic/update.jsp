<%@ page language="java" pageEncoding="UTF-8"%>

<div class="page-content-area">
	<div class="page-header">
		<h1>
			数据字典
			<small> <i class="ace-icon fa fa-angle-double-right"></i>
				修改字典元素 </small>
		</h1>
	</div>

	<form id="inputForm" class="form-horizontal" action="${root}/system/dic/doUpdate" method="post" style="margin: 0; padding: 0">
        <input type="hidden" name="menuid" value="${menuid}"/>
        <input type="hidden" name="groupid" value="${groupid}"/>
        <input type="hidden" name="id" value="${dic.id}"/>
        <input type="hidden" name="page" value="${page}"/>
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name">
                    名称
                </div>
                <div class="profile-info-value">
                    <input minlength="2" maxlength="20" type="text" id="dic-name" value="${dic.name}"
                        placeholder="输入名称" name="name" class="input-large required"
                        specialcharfilter="true" >
                    <span style="color: red;">*</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">
                    类型
                </div>
                <div class="profile-info-value">
                    <c:forEach items="${dic_sec}" var="keyValue">${keyValue.key eq dic.type.value?keyValue.name:''}</c:forEach>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">
                    编码
                </div>
                <div class="profile-info-value">
                    <input type="text" id="dic-code" placeholder="录入编码" name="code"
                        maxlength="6" class="input-large required"
                        ${fn:length(param.type) gt 0?'':'readonly=\"readonly\"'} value="${dic.code}">
                    <span style="color: red;">*</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name">
                    备注
                </div>
                <div class="profile-info-value">
                    <textarea rows="5" maxlength="100" id="dic-remark"
                        class="autosize-transition form-control" name="remark">${dic.remark}</textarea>
                    <span></span>
                </div>
            </div>
</div>
    <div class="clearfix form-actions">
        <div class="col-md-9" style="margin-left: 100px;">
                <button class="btn btn-info"  onclick="save();">保存</button>
                <input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
        </div>
    </div>
    </form>
</div>



<script>
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#user-name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			 rules: {
					"code":{
						remote:{
							url:"${root}/system/dic/codeExist",
							type:"post",
							data:{
								code:function(){
									return $("#dic-code").val();
								},
								type:function(){
									return "${type}";
								},
								oper:function(){
									return "${dic.code}";
								}
							}
						
						}
					}
			}
			
		});
	});
</script>