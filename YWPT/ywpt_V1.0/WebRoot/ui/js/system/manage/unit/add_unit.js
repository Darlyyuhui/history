var currentpage;
var name_message;
var location_message = "null";
var longitude_message = "null";
var latitude_message = "null";
var unit_pic="";
var comp_key;
var index = parent.layer.getFrameIndex(window.name);
var i18Frame = parent.parent.window.frames["i18nframe"];
$(function(){
	currentpage = parent.getCurrentPage();
	comp_key = $("#comp_key").val();
	document.getElementById("unit_name").focus();
	
	$.ajax({
		type: "POST",
		url: "../../../../unit_type/getAllUnit_type",
		data: {
			"comp_key":comp_key
		},
		success:function(returnedData){
			var jar0 = eval("("+returnedData+")");
			var str0 = "<div class='model-select-box'>";
			if(0==jar0.length){
				str0 +="<div class='model-select-text' id='type_key_select' data-value=''>"+i18Frame.getpleaseadd()+i18Frame.getunittype()+"</div></div>"; 
			}else{
				for(var j=0;j<jar0.length;j++){
					if(0==j){
						str0 +="<div class='model-select-text' id='type_key_select' data-value='"+jar0[j].type_key+"'>"+jar0[j].type_name+"</div>"+ 
						"<ul class='model-select-option'>";
						str0 +="<li data-option='"+jar0[j].type_key+"' class='seleced'>"+jar0[j].type_name+"</li>";
					}else{
						str0 +="<li data-option='"+jar0[j].type_key+"'>"+jar0[j].type_name+"</li>";
					}
				}
				str0 +="</ul></div>";
			}
			$("#type_key").empty();
			$("#type_key").append(str0);
			selectModel();
		}
	});
	
	$(".btn-primary").click(function(){
		var unit_name = trim($("#unit_name").val());
		var type_key = $("#type_key_select").attr("data-value");
		var unit_location = trim($("#unit_location").val());
		var unit_longitude = trim($("#unit_longitude").val());
		var unit_latitude = trim($("#unit_latitude").val());
		var unit_remark = trim($("#unit_remark").val());
		if(0 == unit_pic.length)
			unit_pic = "images/blank.png;200;200";
		checkLocation();
		checkLongitude();
		checkLatitude();
		if("null" == name_message){
			layer.tips(i18Frame.getpleaseinput()+' '+i18Frame.getunitname(),'#unit_name',{
				tips:3,time:2000
			});
		}else if("error" == name_message){
			layer.tips('2~20'+i18Frame.getlength(),'#unit_name',{
				tips:3,time:2000
			});
		}else if("has" == name_message){
			layer.tips(i18Frame.getunitname()+' '+i18Frame.getexists(),'#unit_name',{
				tips:3,time:2000
			});
		}else if(""==type_key){
			layer.tips(i18Frame.getpleaseadd()+i18Frame.getunittype(),'#type_key',{
				tips:3,time:2000
			});
		}else{
			if("error" == location_message){
				layer.tips('0~100'+i18Frame.getlength(),'#unit_location',{
					tips:3,time:2000
				});
			}else{
				if("error" == longitude_message){
					layer.tips(i18Frame.getinput_true_longitude(),'#unit_longitude',{
						tips:3,time:2000
					});
				}else{
					if("error" == latitude_message){
						layer.tips(i18Frame.getinput_true_latitude(),'#unit_latitude',{
							tips:3,time:2000
						});
					}else if("ok" == longitude_message&&"null" == latitude_message){
						layer.tips(i18Frame.getpleaseinput()+' '+i18Frame.getlatitude(),'#unit_latitude',{
							tips:3,time:2000
						});
					}else if("null" == longitude_message&&"ok" == latitude_message){
						layer.tips(i18Frame.getpleaseinput()+' '+i18Frame.getlongitude(),'#unit_longitude',{
							tips:3,time:2000
						});
					}else{
						if(100<unit_remark.length){
							layer.tips('0~100'+i18Frame.getlength(),'#unit_remark',{
								tips:1,time:2000
							});
						}else{
							parent.layer.load(2,{shade:0.3});
							$.ajax({
								type: "POST",
								url: "../../../../unit/insertUnit",
								data: {
									"unit_name": unit_name,
									"type_key": type_key,
									"unit_location": unit_location,
									"unit_longitude": unit_longitude,
									"unit_latitude": unit_latitude,
									"unit_pic":unit_pic,
									"unit_remark": unit_remark,
									"comp_key":comp_key
								},
								success: function(returnedData){
									var jo0 = eval("("+returnedData+")");
									if(jo0.check == "true"){
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
		}
	});
	
	$(".btn-default").click(function(){
		parent.$("input[type='checkbox']").prop("checked",false);
		parent.layer.close(index);
	});
});

function checkName(){
	var unit_name = trim($("#unit_name").val());
	if(""==unit_name.replace(/\s/g,"")){
		name_message = "null";
	}else{
		if(unit_name.length<=20&&unit_name.length>=2){
			$.ajax({
				type: "POST",
				url: "../../../../unit/checkName",
				data:{
					"param":unit_name,
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

function checkLocation(){
	var unit_location = trim($("#unit_location").val());
	if(""==unit_location.replace(/\s/g,"")){
		location_message = "null";
	}else{
		if(unit_location.length<=100&&unit_location.length>=0){
			location_message = "ok";
			ti=1;
			return true;
		}else{
			location_message = "error";
			ti=0;
			return false;
		}
	}
}

function checkLongitude(){
	var unit_longitude = trim($("#unit_longitude").val());
	if(""==unit_longitude.replace(/\s/g,"")){
		longitude_message = "null";
	}else{
		var reg= /^((\d|[1-9]\d|1[0-7]\d)(\.\d{1,6})?$)|(180$)/;
		if(arr=unit_longitude.match(reg)){
			longitude_message = "ok";
			ti=1;
			return true;
		}else{
			longitude_message = "error";
			ti=0;
			return false;
		}
	}
}

function checkLatitude(){
	var unit_latitude = trim($("#unit_latitude").val());
	if(""==unit_latitude.replace(/\s/g,"")){
		latitude_message = "null";
	}else{
		var reg= /^((\d|[1-8]\d)(\.\d{1,6})?$)|(90$)/;
		if(arr=unit_latitude.match(reg)){
			latitude_message = "ok";
			ti=1;
			return true;
		}else{
			latitude_message = "error";
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
					unit_pic = jo[0].message1+";"+jo[0].widthHeight;
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