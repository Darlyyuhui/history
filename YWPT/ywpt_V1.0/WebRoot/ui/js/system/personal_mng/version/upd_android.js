var androidcurrentpage;
var app_id;
var capk_name;
var cver_name;
var cver_code;
var app_message;
var apk_message;
var vername_message;
var code_message;
var index = parent.layer.getFrameIndex(window.name);
var i18Frame = parent.parent.window.frames["i18nframe"];

$(function(){
	androidcurrentpage = parent.getAndroidCurrentPage();
	app_id = parent.getApp_id_a();
	document.getElementById("app_name").focus();
	
	$.ajax({
		type : "POST",
		url: "../../../../androidversion/getAndroidVersionByID",
		data: {
			"app_id": app_id
		},
		success: function(returnedData){
			var jo0 = eval("("+returnedData+")");
			capk_name = jo0.apk_name;
			cver_name = jo0.ver_name;
			cver_code = jo0.ver_code;
			$("#app_name").val(jo0.app_name);
			$("#apk_name").val(capk_name);
			$("#ver_name").val(cver_name);
			$("#ver_code").val(cver_code);
			$("#change_log").val(jo0.change_log);
			textarealength("#change_log",100);
		}
	});
	
	$(".btn-primary").click(function(){
		var app_name = trim($("#app_name").val());
		var apk_name = trim($("#apk_name").val());
		var ver_name = trim($("#ver_name").val());
		var ver_code = trim($("#ver_code").val());
		var change_log = $("#change_log").val();
		if("null" == app_message){
			layer.tips(i18Frame.getpleaseinput()+'App名称','#app_name',{
				tips:3,time:2000
			});
		}else if("error" == app_message){
			layer.tips('5~20'+i18Frame.getlength(),'#app_name',{
				tips:3,time:2000
			});
		}else if("null" == apk_message){
			layer.tips(i18Frame.getpleaseinput()+'Apk名称','#apk_name',{
				tips:3,time:2000
			});
		}else if("error" == apk_message){
			layer.tips('5~32'+i18Frame.getlength(),'#apk_name',{
				tips:3,time:2000
			});
		}else if("has" == apk_message){
			layer.tips('Apk名称'+i18Frame.getexists(),'#apk_name',{
				tips:3,time:2000
			});
		}else if("null" == vername_message){
			layer.tips(i18Frame.getpleaseinput()+'版本名称','#ver_name',{
				tips:3,time:2000
			});
		}else if("error" == vername_message){
			layer.tips('1~10'+i18Frame.getlength(),'#ver_name',{
				tips:3,time:2000
			});
		}else if("has" == vername_message){
			layer.tips('版本名称'+i18Frame.getexists(),'#ver_name',{
				tips:3,time:2000
			});
		}else if("null" == code_message){
			layer.tips(i18Frame.getpleaseinput()+'版本号','#ver_code',{
				tips:3,time:2000
			});
		}else if("error" == code_message){
			layer.tips('1~3'+i18Frame.getlength()+'的数字','#ver_code',{
				tips:3,time:2000
			});
		}else if("has" == code_message){
			layer.tips('版本号'+i18Frame.getexists(),'#ver_code',{
				tips:3,time:2000
			});
		}else if(100<change_log.length){
			layer.tips('0~100'+i18Frame.getlength(),'#change_log',{
				tips:1,time:2000
			});
		}else{
			parent.layer.load(2,{shade:0.3});
			$.ajax({
				type: "POST",
				url: "../../../../androidversion/updAndroidVersion",
				data: {
					"app_id": app_id,
					"app_name": app_name,
					"apk_name": apk_name,
					"ver_name": ver_name,
					"ver_code": ver_code,
					"change_log": change_log
				},
				success:function(returnedData){
					var jo0 = eval("("+returnedData+")");
					if(jo0.check =="true"){
						parent.layer.closeAll('loading');
						parent.layer.msg(i18Frame.getedit()+' '+i18Frame.getsuccess()+'!',{icon:1,shade:0.3,time:1000});
						parent.androidflush(androidcurrentpage);
						parent.$("#android_check").prop("checked",false);
						parent.layer.close(index);
					}else{
						parent.layer.closeAll('loading');
						parent.layer.msg(i18Frame.getedit()+' '+i18Frame.getfailed()+'!',{icon:2,shade:0.3,time:1000});
						return;
					}
				}
			});
		}
	});
	
	
	$(".btn-default").click(function(){
		parent.$("#android_check").prop("checked",false);
		parent.layer.close(index);
	});
});

function checkApp(){
	var app_name = trim($("#app_name").val());
	if(""==app_name.replace(/\s/g,"")){
		app_message = "null";
	}else{
		if(app_name.length<=20&&app_name.length>=5){
			app_message = "ok";
			ti=1;
			return true;
		}else{
			app_message = "error";
			ti=0;
			return false;
		}
	}
}

function checkApk(){
	var apk_name = trim($("#apk_name").val());
	if(""==apk_name.replace(/\s/g,"")){
		apk_message = "null";
	}else{
		if(apk_name.length<=32&&apk_name.length>=5){
			$.ajax({
				type: "POST",
				url: "../../../../androidversion/checkApk",
				data:{
					"param":apk_name
				},
				success:function(returnedData){
					var jo0 = eval("("+returnedData+")");
					if(apk_name==capk_name){
						apk_message = "ok";
					}else if(jo0.status=="y"){
						apk_message = "ok";
					}else{
						apk_message = "has";
					}
				}
			});
			ti=1;
			return true;
		}else{
			apk_message = "error";
			ti=0;
			return false;
		}
	}
}

function checkVersionName(){
	var ver_name = trim($("#ver_name").val());
	if(""==ver_name.replace(/\s/g,"")){
		vername_message = "null";
	}else{
		if(ver_name.length<=10&&ver_name.length>=1){
			$.ajax({
				type: "POST",
				url: "../../../../androidversion/checkVersionName",
				data:{
					"param":ver_name
				},
				success:function(returnedData){
					var jo0 = eval("("+returnedData+")");
					if(ver_name==cver_name){
						vername_message = "ok";
					}else if(jo0.status=="y"){
						vername_message = "ok";
					}else{
						vername_message = "has";
					}
				}
			});
			ti=1;
			return true;
		}else{
			vername_message = "error";
			ti=0;
			return false;
		}
	}
}

function checkCode(){
	var ver_code = trim($("#ver_code").val());
	var reg1 = /^[0-9]*$/;
	if(arr=ver_code.match(reg1)){
		if(""==ver_code.replace(/\s/g,"")){
			code_message = "null";
		}else{
			var reg = /^\d{1,3}?$/;
			if(arr=ver_code.match(reg)){
				$.ajax({
					type: "POST",
					url: "../../../../androidversion/checkCode",
					data: {
						"param": ver_code
					},
					success: function(returnedData){
						var jo0 = eval("("+returnedData+")");
						if(ver_code==cver_code){
							code_message = "ok";
						}else if(jo0.status=="y"){
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
	}else{
		code_message = "error";
		ti=0;
		return false;
	}
}

function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}