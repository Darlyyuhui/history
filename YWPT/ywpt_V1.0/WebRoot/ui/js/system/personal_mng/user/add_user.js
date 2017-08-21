var currentpage;
var id_message;
var name_message;
var phone_message;
var tel_message;
var comp_key;
var index = parent.layer.getFrameIndex(window.name);
var i18Frame = parent.parent.window.frames["i18nframe"];
$(function(){
	currentpage = parent.getCurrentPage();
	comp_key = $("#comp_key").val();
	document.getElementById("user_id").focus();
	
	$.ajax({
		type: "POST",
		url: "../../../../user_type/getAllUser_type",
		data: {
			"comp_key":comp_key
		},
		success: function(returnedData){
			var jar1 = eval("("+returnedData+")");
			var str1 = "<div class='model-select-box' style='width: 324.5px;'>";
			for(var m=0;m<jar1.length;m++){
				if(0==m){
					str1 +="<div class='model-select-text' id='type_select' data-value='"+jar1[m].ut_key+"'>"+jar1[m].ut_name+"</div>"+ 
					"<ul class='model-select-option'>";
					str1 +="<li data-option='"+jar1[m].ut_key+"' class='seleced'>"+jar1[m].ut_name+"</li>";
				}else{
					str1 +="<li data-option='"+jar1[m].ut_key+"'>"+jar1[m].ut_name+"</li>";
				}
			}
			str1 +="</ul></div>";
			$("#ut_key").empty();
			$("#ut_key").append(str1);
			selectModel();
		}
	});
	
	$(".btn-primary").click(function(){
		var user_id = trim($("#user_id").val());
		var user_name = trim($("#user_name").val());
		var ut_key = $("#type_select").attr("data-value");
		var user_phone = trim($("#user_phone").val());
		var user_pwd = $("#user_pwd").val();
		var user_tel = trim($("#user_tel").val());
		checkName();
		checkPhone();
		checkTel();
		if("null" == id_message){
			layer.tips(i18Frame.getpleaseinput()+' '+i18Frame.getusernum(),'#user_id',{
				tips:3,time:2000
			});
		}else if("error" == id_message){
			layer.tips('3~8'+i18Frame.getletternumber(),'#user_id',{
				tips:3,time:2000
			});
		}else if("has" == id_message){
			layer.tips(i18Frame.getusernum()+' '+i18Frame.getexists(),'#user_id',{
				tips:3,time:2000
			});
		}else{
			if("null" == name_message){
				layer.tips(i18Frame.getpleaseinput()+' '+i18Frame.getusername(),'#user_name',{
					tips:3,time:2000
				});
			}else if("error" == name_message){
				layer.tips('2~20'+i18Frame.getlength(),'#user_name',{
					tips:3,time:2000
				});
			}else if(""==ut_key){
				layer.tips(i18Frame.getpleaseadd()+i18Frame.getroletype(),'#ut_key',{
					tips:3,time:2000
				});
			}else{
				if("error" == phone_message){
					layer.tips(i18Frame.getpleaseinput()+' '+i18Frame.getuser_phone(),'#user_phone',{
						tips:3,time:2000
					});
				}else if(""==user_pwd){
					layer.tips(i18Frame.getpleaseinput()+' '+i18Frame.getpwd(),'#user_pwd',{
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
							url: "../../../../user/insertUser",
							data: {
								"user_id": user_id,
								"user_name": user_name,
								"ut_key": ut_key,
								"user_phone": user_phone,
								"user_tel": user_tel,
								"user_pwd": user_pwd,
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
			}
		}
	});
	
	$(".btn-default").click(function(){
		parent.$("input[type='checkbox']").prop("checked",false);
		parent.layer.close(index);
	});
});


function checkId(){
	var user_id = trim($("#user_id").val());
	if(""==user_id.replace(/\s/g,"")){
		id_message = "null";
	}else{
		var reg = /^[A-Za-z0-9]{3,8}?$/;
		if(arr=user_id.match(reg)){
			$.ajax({
				type: "POST",
				url: "../../../../user/checkId",
				data: {
					"param": user_id,
					"comp_key": comp_key
				},
				success: function(returnedData){
					var jo0 = eval("("+returnedData+")");
					if(jo0.status=="y"){
						id_message = "ok";
					}else{
						id_message = "has";
					}
				}
			});
			ti=1;
			return true;
		}else{
			id_message = "error";
			ti=0;
			return false;
		}
	}
}

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