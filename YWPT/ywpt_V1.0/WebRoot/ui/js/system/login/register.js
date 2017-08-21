var apply_pic = "";
var comp_key;
var name_message= "null";
var phone_message= "null";
var email_message= "null";
var comp_message= "null";
var accont_message= "null";
var code_message= "null";

$(function(){
	document.getElementById("apply_name").focus();
	$.ajax({
		type: "POST",
		url: "../../company/getPlatformCompany",
		success:function(returnedData){
			var jo0 = eval("("+returnedData+")");
			comp_key = jo0.comp_key;
		}
	});
});

function Register(){
	var apply_name = trim($("#apply_name").val());
	var apply_phone = trim($("#apply_phone").val());
	var apply_email = trim($("#apply_email").val());
	var apply_comp = $("#apply_comp").val();
	var apply_accont = trim($("#apply_accont").val());
	var apply_machine_code = trim($("#apply_machine_code").val());
	var apply_pwd = trim($("#apply_pwd").val());
	if("null" == name_message){
		layer.tips('请输入联系人','#apply_name',{
			tips:3,time:2000
		});
	}else if("error" == name_message){
		layer.tips('2~20位','#apply_name',{
			tips:3,time:2000
		});
	}else if("null" == phone_message){
		layer.tips('请输入手机号','#apply_phone',{
			tips:3,time:2000
		});
	}else if("error" == phone_message){
		layer.tips('请输入11位手机号','#apply_phone',{
			tips:3,time:2000
		});
	}else if("null" == email_message){
		layer.tips('请输入邮箱地址','#apply_email',{
			tips:3,time:2000
		});
	}else if("error" == email_message){
		layer.tips('请输入正确的邮箱地址','#apply_email',{
			tips:3,time:2000
		});
	}else if("null" == comp_message){
		layer.tips('请输入公司名称','#apply_comp',{
			tips:3,time:2000
		});
	}else if("error" == comp_message){
		layer.tips('2~30','#apply_comp',{
			tips:3,time:2000
		});
	}else if("null" == accont_message){
		layer.tips('请输入申请账号','#apply_accont',{
			tips:3,time:2000
		});
	}else if("error" == accont_message){
		layer.tips('3~8位字母数字','#apply_accont',{
			tips:3,time:2000
		});
	}else if("null" == code_message){
		layer.tips('请输入单位简码','#apply_machine_code',{
			tips:3,time:2000
		});
	}else if("error" == code_message){
		layer.tips('1~10位字母、数字','#apply_machine_code',{
			tips:3,time:2000
		});
	}else if("has" == code_message){
		layer.tips('该单位简码已被申请','#apply_machine_code',{
			tips:3,time:2000
		});
	}else if(""==apply_pwd){
		layer.tips('请输入密码','#apply_pwd',{
			tips:3,time:2000
		});
	}else if(""==apply_pic){
		layer.tips('请上传单位营业执照','#img_2',{
			tips:3,time:2000
		});
	}else{
		layer.load(2,{shade:0.3});
		$.ajax({
			type: "POST",
			url: "../../applyment/insertApplyment",
			data: {
				"apply_name": apply_name,
				"apply_phone": apply_phone,
				"apply_email": apply_email,
				"apply_comp": apply_comp,
				"apply_accont": apply_accont,
				"apply_machine_code": apply_machine_code,
				"apply_pwd": apply_pwd,
				"apply_pic": apply_pic,
				"comp_key": comp_key
			},
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				if(jo0.check =="true"){
					layer.closeAll('loading');
					layer.alert('申请提交成功,请耐心等待!',{
						icon:1,shade:0.3,title:'提示'
					},function(index){
						window.open('login.jsp','_top');
					});
				}else{
					layer.closeAll('loading');
					layer.msg('申请提交失败!',{icon:2,shade:0.3,time:1000});
					return;
				}
			}
		});
	}
}

function checkName(){
	var apply_name = trim($("#apply_name").val());
	if(""==apply_name.replace(/\s/g,"")){
		name_message = "null";
	}else{
		if(apply_name.length<=20&&apply_name.length>=2){
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
	var apply_phone = trim($("#apply_phone").val());
	if(""==apply_phone.replace(/\s/g,"")){
		phone_message = "null";
	}else{
		var reg = /^\d{11}?$/;
		if(arr=apply_phone.match(reg)){
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

function checkEmail(){
	var apply_email = trim($("#apply_email").val());
	if(""==apply_email.replace(/\s/g,"")){
		email_message = "null";
	}else{
		var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
		if(arr=apply_email.match(reg)){
			email_message = "ok";
			ti=1;
			return true;
		}else{
			email_message = "error";
			ti=0;
			return false;
		}
	}
}

function checkComp(){
	var apply_comp = trim($("#apply_comp").val());
	if(""==apply_comp.replace(/\s/g,"")){
		comp_message = "null";
	}else{
		if(apply_comp.length<=30&&apply_comp.length>=2){
			comp_message = "ok";
			ti=1;
			return true;
		}else{
			comp_message = "error";
			ti=0;
			return false;
		}
	}
}

function checkAccont(){
	var apply_accont = trim($("#apply_accont").val());
	if(""==apply_accont.replace(/\s/g,"")){
		accont_message = "null";
	}else{
		var reg = /^[A-Za-z0-9]{3,8}?$/;
		if(arr=apply_accont.match(reg)){
			accont_message = "ok";
			ti=1;
			return true;
		}else{
			accont_message = "error";
			ti=0;
			return false;
		}
	}
}

function checkCode(){
	var apply_machine_code = trim($("#apply_machine_code").val());
	if(""==apply_machine_code.replace(/\s/g,"")){
		code_message = "null";
	}else{
		var reg = /^[A-Za-z0-9]{1,5}?$/;
		if(arr=apply_machine_code.match(reg)){
			$.ajax({
				type: "POST",
				url: "../../company/checkMachine",
				data:{
					"param":apply_machine_code
				},
				success:function(returnedData){
					var jo0 = eval("("+returnedData+")");
					if(jo0.status=="y"){
						code_message = "ok";
					}else{
						code_message = "has";
					}
				}
			});
			ti=1;
			return true;
		}else{
			code_message = "error";
			ti=0;
			return false;
		}
	}
}

function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

function ajaxFileUpload(){
	var filepath = $("#upfile2").val();
	var extStart = filepath.lastIndexOf(".");
	var ext = filepath.substring(extStart,filepath.length).toLowerCase();
	if((ext!=".jpg")&&(ext!=".jpeg")&&(ext!=".bmp")&&(ext!=".png")&&(ext!=".gif")){
		layer.alert("对不起,请选择图片上传(jpg jpeg bmp png gif)!", {icon: 0, title:'提示',btn: ['确定'] });
	}else{
		var loading = layer.load(1, {shade: [0.5, '#000', true],offset: ['50%','50%']});
		$.ajaxFileUpload
		(
			{
				url:'../../imgUpload/import',//用于文件上传的服务器端请求地址
				type: 'post',
				secureuri:false,//一般设置为false
				fileElementId:'upfile2',//文件上传空间的id属性  <input type="file" id="file" name="file" />
				dataType: 'json',//返回值类型 一般设置为json
				data: {
					"comp_key": comp_key
	            },
				success: function (data, status)  //服务器成功响应处理函数
				{
					layer.close(loading);
					var jo = eval("("+data+")");
					document.getElementById("img_2").src = "../../"+jo[0].message1;
					document.getElementById("img_2").style.display = "block";
					document.getElementById("img_2").style.height = "237px";
					document.getElementById("img_2").style.width = "337px";
					apply_pic = jo[0].message1+";"+jo[0].widthHeight;
					if(typeof(data.error) != 'undefined')
					{
						if(data.error != '')
							{
								alert(data.error);
							}else
							{
								alert(data.msg);
							}
						}
							
					},
				error: function (data, status, e)//服务器响应失败处理函数
					{
						layer.close(loading);
						alert(e);
					}
			}
		);
	}
}