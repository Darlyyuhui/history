var comp_key;
var ccomp_name;
var currentpage;
var name_message;
var index = parent.layer.getFrameIndex(window.name);
var i18Frame = parent.parent.window.frames["i18nframe"];

$(function(){
	currentpage = parent.getCurrentPage();
	comp_key = parent.getComp_key();
	
	$.ajax({
		type: "POST",
		url: "../../../../company/getCompanyByKey1",
		data: {
			"comp_key":comp_key
		},
		success:function(returnedData){
			var jo0 = eval("("+returnedData+")");
			ccomp_name = jo0.comp_name;
			$("#comp_name").val(jo0.comp_name);
			$("#comp_machine_code").val(jo0.comp_machine_code);
		}
	});
	
	document.getElementById("comp_name").focus();
	
	
	$(".btn-primary").click(function(){
		comp_name = trim($("#comp_name").val());
		if("null" == name_message){
			layer.tips('请输入单位名称','#comp_name',{
				tips:3,time:2000
			});
		}else if("error" == name_message){
			layer.tips('2~30位','#comp_name',{
				tips:3,time:2000,area: ['260px']
			});
		}else if("has" == name_message){
			layer.tips('单位名称已存在','#comp_name',{
				tips:3,time:2000
			});
		}else{
			layer.load(2,{shade:0.3});
			$.ajax({
				type: "POST",
				url: "../../../../company/updCompany",
				data: {
					"comp_key":comp_key,
					"comp_name":comp_name
				},
				success:function(returnedData){
					var jo0 = eval("("+returnedData+")");
					if(jo0.check =="true"){
						parent.layer.closeAll('loading');
						parent.layer.msg('编辑成功!',{icon:1,shade:0.3,time:1000});
						parent.flush(currentpage);
						parent.layer.close(index);
					}else{
						parent.layer.closeAll('loading');
						parent.layer.msg('编辑失败!',{icon:2,shade:0.3,time:1000});
						return;
					}
				}
			});
		}
	});
	
	$(".btn-default").click(function(){
		parent.layer.close(index);
	});
});


function checkName(){
	var comp_name = trim($("#comp_name").val());
	if(""==comp_name.replace(/\s/g,"")){
		name_message = "null";
	}else{
		if(comp_name.length<=30&&comp_name.length>=2){
			$.ajax({
				type: "POST",
				url: "../../../../company/checkName",
				data:{
					"param":comp_name
				},
				success:function(returnedData){
					var jo0 = eval("("+returnedData+")");
					if(comp_name == ccomp_name){
						name_message = "ok";
					}else if(jo0.status=="y"){
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