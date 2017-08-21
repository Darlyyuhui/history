var currentpage;
var name_message;
var type_pic="";
var comp_key;
var index = parent.layer.getFrameIndex(window.name);
var i18Frame = parent.parent.window.frames["i18nframe"];
$(function(){
	currentpage = parent.getCurrentPage();
	comp_key = $("#comp_key").val();
	document.getElementById("type_name").focus();
	
	$(".btn-primary").click(function(){
		var type_name = trim($("#type_name").val());
		if(0 == type_pic.length)
			type_pic = "images/blank.png;200;200";
		if("null" == name_message){
			layer.tips(i18Frame.getpleaseinput()+' '+i18Frame.gettypename(),'#type_name',{
				tips:3,time:2000
			});
		}else if("error" == name_message){
			layer.tips('2~20'+i18Frame.getlength(),'#type_name',{
				tips:3,time:2000
			});
		}else if("has" == name_message){
			layer.tips(i18Frame.gettypename()+' '+i18Frame.getexists(),'#type_name',{
				tips:3,time:2000
			});
		}else{
			parent.layer.load(2,{shade:0.3});
			$.ajax({
				type: "POST",
				url: "../../../../unit_type/insertUnit_type",
				data: {
					"type_name":type_name,
					"type_pic":type_pic,
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
	});
	
	$(".btn-default").click(function(){
		parent.$("input[type='checkbox']").prop("checked",false);
		parent.layer.close(index);
	});
	
});

function checkName(){
	var type_name = trim($("#type_name").val());
	if(""==type_name.replace(/\s/g,"")){
		name_message = "null";
	}else{
		if(type_name.length<=20&&type_name.length>=2){
			$.ajax({
				type: "POST",
				url: "../../../../unit_type/checkName",
				data:{
					"param":type_name,
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
function ajaxFileUpload(){
	var filepath = $("#upfile2").val();
	var extStart = filepath.lastIndexOf(".");
	var ext = filepath.substring(extStart,filepath.length).toLowerCase();
	if((ext!=".jpg")&&(ext!=".jpeg")&&(ext!=".bmp")&&(ext!=".png")&&(ext!=".gif")){
		layer.alert(i18Frame.getchoosepic()+"(jpg jpeg bmp png gif)!", {icon: 0, title:i18Frame.getinfo(),btn: [i18Frame.getok()] });
	}else{
		var loading = layer.load(1, {shade: [0.5, '#000', true],offset: ['50%','50%']});
		$.ajaxFileUpload
		(
			{
				url:'../../../../imgUpload/import',//用于文件上传的服务器端请求地址
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
					document.getElementById("img_1").src = "../../../../"+jo[0].message1;
					document.getElementById("img_1").style.display = "block";
					document.getElementById("img_1").style.height = "220px";
					document.getElementById("img_1").style.width = "220px";
					type_pic = jo[0].message1+";"+jo[0].widthHeight;
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