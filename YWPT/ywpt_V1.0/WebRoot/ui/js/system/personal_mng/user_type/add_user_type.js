var currentpage;
var name_message;
var comp_key;
var index = parent.layer.getFrameIndex(window.name);
var i18Frame = parent.parent.window.frames["i18nframe"];
$(function(){
	currentpage = parent.getCurrentPage();
	comp_key = $("#comp_key").val();
	document.getElementById("ut_name").focus();
});

function checkAdd(){
	return 0;
}

function addUsertype(){
	var ut_name = trim($("#ut_name").val());
	var ut_remark = $("#ut_remark").val();
	var ut_web_power = keyIframe.window.GetNavigation();
	var ut_app_power = lockIfrme.window.GetNavigation();
	if("null" == name_message){
		layer.tips(i18Frame.getpleaseinput()+' '+i18Frame.getroletypename(),'#ut_name',{
			tips:3,time:2000
		});
	}else if("error" == name_message){
		layer.tips('2~20'+i18Frame.getlength(),'#ut_name',{
			tips:3,time:2000
		});
	}else if("has" == name_message){
		layer.tips(i18Frame.getroletypename()+' '+i18Frame.getexists(),'#ut_name',{
			tips:3,time:2000
		});
	}else if(50<ut_remark.length){
		layer.tips('0~50'+i18Frame.getlength(),'#ut_remark',{
			tips:3,time:2000
		});
	}else if(1==ut_web_power.length&&1==ut_app_power.length){
		keyIframe.layer.tips(i18Frame.getchoosepower(),$("#keyIframe").contents().find("#key_id"),{
			tips:3,time:2000
		});
	}else{
		parent.layer.load(2,{shade:0.3});
		$.ajax({
			type: "POST",
			url: "../../../../user_type/insertUser_type",
			data: {
				"ut_name":ut_name,
				"ut_web_power":ut_web_power,
				"ut_app_power":ut_app_power,
				"ut_remark":ut_remark,
				"comp_key":comp_key
			},
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				if(jo0.check =="true"){
					parent.layer.closeAll('loading');
					parent.layer.msg(i18Frame.getadd()+' '+i18Frame.getsuccess()+'!',{icon:1,shade:0.3,time:1000});
					parent.flush(currentpage);
					parent.$("input[type='checkbox']").prop("checked",false);
					parent.layer.close(index);
				}else{
					parent.layer.closeAll('loading');
					parent.layer.msg(i18Frame.getadd()+' '+i18Frame.getfailed()+'!',{icon:2,shade:0.3,time:1000});
					return;
				}
			}
		});
	}
}

function cancel(){
	parent.$("input[type='checkbox']").prop("checked",false);
	parent.layer.close(index);
}

function checkName(){
	var ut_name = trim($("#ut_name").val());
	if(""==ut_name.replace(/\s/g,"")){
		name_message = "null";
	}else{
		if(ut_name.length<=20&&ut_name.length>=2){
			$.ajax({
				type: "POST",
				url: "../../../../user_type/checkName",
				data:{
					"param":ut_name,
					"comp_key": comp_key
				},
				success:function(returnedData){
					var jo0 = eval("("+returnedData+")");
					if(jo0.status=="y"){
						name_message = "ok";
					}else{
						name_message = "has";
					}
				}
			});
			ti=1;
			return true;
		}else{
			name_message = "error";
			ti=0;
			return false;
		}
	}
}
function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}