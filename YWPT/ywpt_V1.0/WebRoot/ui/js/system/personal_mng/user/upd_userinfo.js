var user_key;
var user_id;
var ut_key;
var name_message;
var phone_message;
var tel_message;
var comp_key;
var index = parent.layer.getFrameIndex(window.name);
var i18Frame = parent.window.frames["i18nframe"];

$(function(){
	user_id = parent.getUserid();
	comp_key = $("#comp_key").val();
	$.ajax({
		type: "POST",
		url: "../../../../user/getUserById",
		data: {
			"user_id": user_id,
			"comp_key": comp_key
		},
		success: function(returnedData){
			var jo0 = eval("("+returnedData+")");
			user_key = jo0.user_key;
			ut_key = jo0.ut_key;
			$("#user_id").val(jo0.user_id);
			$("#user_name").val(jo0.user_name);
			$("#ut_name").val(jo0.ut_name);
			$("#user_phone").val(jo0.user_phone);
			$("#user_tel").val(jo0.user_tel);
		}
	});
	
	document.getElementById("user_id").focus();
	
	$(".btn-primary").click(function(){
		var user_name = trim($("#user_name").val());
		var user_phone = trim($("#user_phone").val());
		var user_tel = trim($("#user_tel").val());
		checkName();
		checkPhone();
		checkTel();
		if("null" == name_message){
			layer.tips(i18Frame.getpleaseinput()+' '+i18Frame.getusername(),'#user_name',{
				tips:3,time:2000
			});
		}else if("error" == name_message){
			layer.tips('2~20'+i18Frame.getlength(),'#user_name',{
				tips:3,time:2000
			});
		}else{
			if("error" == phone_message){
				layer.tips(i18Frame.getpleaseinput()+' '+i18Frame.getuser_phone(),'#user_phone',{
					tips:3,time:2000
				});
			}else{
				if("error" == tel_message){
					layer.tips(i18Frame.getpleaseinput()+' '+i18Frame.getuser_tel(),'#user_tel',{
						tips:1,time:2000
					});
				}else{
					parent.layer.load(2,{shade:0.3});
					$.ajax({
						type: "POST",
						url: "../../../../user/updUser",
						data: {
							"user_key": user_key,
							"user_name": user_name,
							"ut_key": ut_key,
							"user_phone": user_phone,
							"user_tel": user_tel,
							"comp_key":comp_key
						},
						success:function(returnedData){
							var jo0 = eval("("+returnedData+")");
							if(jo0.check =="true"){
								parent.layer.closeAll('loading');
								parent.layer.alert(i18Frame.getedit()+' '+i18Frame.getsuccess()+'!',{
									icon:1,shade:0.3,title:i18Frame.getinfo(),closeBtn:0
								},function(index1){
									window.open('../../jsp/system/index.jsp','_top');
								});
							}else{
								parent.layer.closeAll('loading');
								parent.layer.alert(i18Frame.getedit()+' '+i18Frame.getfailed()+'!',{icon:2,shade:0.3,offset:'130px',title:i18Frame.getinfo()});
								return;
							}
						}
					});
				}
			}
		}
	});
	
	$(".btn-default").click(function(){
		parent.layer.close(index);
	});
});

function checkName(){
	user_name = trim($("#user_name").val());
	if(""==user_name.replace(/\s/g,"")){
		name_message = "null";
	}else{
		if(user_name.length<=20&&user_name.length>=2){
			name_message = "ok";
			ti=1;
			return true;
		}else{
			name_message = "error";
			ti=0;
			return false;
		}
	}
}

function checkPhone(){
	user_phone = trim($("#user_phone").val());
	if(""==user_phone.replace(/\s/g,"")){
		phone_message = "null";
	}else{
		var reg = /^\d{11}?$/;
		if(arr=user_phone.match(reg)){
			phone_message = "ok";
			ti=1;
			return true;
		}else{
			phone_message = "error";
			ti=0;
			return false;
		}
	}
}

function checkTel(){
	user_tel = trim($("#user_tel").val());
	if(""==user_tel.replace(/\s/g,"")){
		tel_message = "null";
	}else{
		var reg = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
		if(arr=user_tel.match(reg)){
			tel_message = "ok";
			ti=1;
			return true;
		}else{
			tel_message = "error";
			ti=0;
			return false;
		}
	}
}

function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}