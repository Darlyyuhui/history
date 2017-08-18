<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/dialog.jspf" %>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin-top:0;">添加公司</h4>
    <form id="inputForm" class="form-horizontal" action="${root}/system/org/doAdd/${menuid}/" method="post" style="margin:0;padding:0">
		<input type="hidden" name="parentid" value="00"/>
        <input type="hidden" name="levels" value="0"/>
        <input type="hidden" name="isCorpor" value="1"/>
        <input type="hidden" id="corporId" name="corporId"/>
		<div class="row-fluid" >
			<table class="table bukong-table table-border-bot" style="margin-top:-10px">
				<tr>
					<td class="device_td_bg3">公司编码：</td>
					<td>
					        <input style="width:150px;" value="${code}" maxlength="20" minlength="6" type="text" id="org-code" placeholder="机构编码" name="code" class="input-small required" />
						<font color="red">&nbsp;*</font><a class="btn btn-sm"
                                                           href="javascript:showDialog('#corporId','#org-name');">选择</a></td>
				</tr>
				<tr>
					<td class="device_td_bg3">公司名称：</td>
					<td><input style="width:50%; min-width:200px;" type="text" id="org-name" placeholder="机构名称" maxlength="20" name="name" specialcharfilter="true" class="input-large required" /><font color="red">&nbsp;*</font>
						<div id="messageDiv" style="display: none;">
							<font id="info" color="red"></font>
						</div>
					</td>
				</tr>
				<tr>
					<td class="device_td_bg3">公司法人：</td>
					<td><input style="width:50%; min-width:200px;" type="text" id="org-principal" placeholder="责任人" maxlength="10" name="principalName" class="input-large required" chinese="true" /><font color="red">&nbsp;*</font>
						<input type="hidden" name="principal" id="principal-id">
						<div id="messageDiv" style="display: none;">
							<font id="info" color="red"></font>
						</div>
					</td>
				</tr>
				
					<tr>
					<td class="device_td_bg3">联系方式：</td>
					<td><input style="width:50%; min-width:200px;" type="text" id="org-mobile" placeholder="联系方式" teletest="true"  maxlength="13" name="mobile" class="input-large required"/><font color="red">&nbsp;*</font>
						<div id="messageDiv" style="display: none;">
							<font id="info" color="red"></font>
						</div>
					</td>
				</tr>
				<tr>
					<td class="device_td_bg3">公司地址：</td>
					<td><textarea rows="6" class="span8" id="memo" style="min-width:200px;" maxlength="100" name="memo"></textarea><span></span></td>
				</tr>
			</table>
		</div>
       	<div class="clear"></div>
		<div class="btn_line" style="margin-bottom:0;">
	        <button class="btn btn-info mar_r10" type="submit" style="margin-left:120px">保存</button>
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
		</div>
	</form>
</div>
<script>
		function showList(){
			window.location.href="${root}/system/org/list/${menuid}/";
		}
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#org-name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				 rules: {
					"code":{
						remote:{
							url:"${root}/system/org/codeExist",
							type:"post",
							data:{
								code:function(){
									return $("#org-code").val();
								}
							}
						
						}
					}
				}
			});
			$("#code-div span.error").height(50).width(200);
		});

        function showDialog(a, b) {
            jBox.setDefaults({defaults: {top: '10px', left: '50px'}});
            jBox.open("iframe:${root}/corporation/dialog/${menuid}/", '选择公司', 800, 500, {
                submit: function (v, h, f) {
                    if (v == 'ok') {
                        var jf = h.find("#jbox-iframe")[0].contentWindow;
                        var v = jf.sendValue();
                        if (v.length == 0) {
                            return false;
                        }
                        var val = v.split("#");
                        var ids = val[0];
                        var names = val[1];
                        var code = val[2];
                        $(a).val(ids);
                        $(b).val(names);
                        $("#org-code").val(code);
                        $("#org-principal").val(val[3]);
                        $("#memo").val(val[4]);
                        $("#org-mobile").val(val[5]);
                        return true;
                    }
                }
            });
            $("#jbox").css("left", 100);
        }


</script>