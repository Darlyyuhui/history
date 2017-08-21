var key_key;
var currentpage;
var model_message;
var comp_key;
var user_id = "";
var index = parent.layer.getFrameIndex(window.name);
var i18Frame = parent.parent.window.frames["i18nframe"];

$(function(){
	currentpage = parent.getCurrentPage();
	key_key = parent.getKey_key();
	
	document.getElementById("key_id").focus();
	
	$.ajax({
		type: "POST",
		url: "../../../../key/getKeyByKey",
		data: {
			"key_key": key_key
		},
		success: function(returnedData){
			var jo0 = eval("("+returnedData+")");
			user_id = jo0.user_id;
			comp_key = jo0.comp_key;
			$("#key_id").val(jo0.key_id);
			$("#key_model").val(jo0.key_model);
			$.ajax({
				type: "POST",
				url: "../../../../user/getAllUser",
				data: {
					"comp_key": comp_key
				},
				success: function(returnedData){
					var jar0 = eval("("+returnedData+")");
					var str0 = "<div class='model-select-box'>";
					for(var i=0;i<jar0.length;i++){
						if(jar0[i].user_id==user_id){
							str0 +="<div class='model-select-text' id='key_select' data-value='"+jar0[i].user_id+"'>"+jar0[i].user_name+"("+jar0[i].user_id+")</div>"+ 
							"<ul class='model-select-option'>";
						}
					}
					for(var j=0;j<jar0.length;j++){
						if(jar0[j].user_id==user_id){
							str0 +="<li data-option='"+jar0[j].user_id+"' class='seleced'>"+jar0[j].user_name+"("+jar0[j].user_id+")</li>";
						}else{
							str0 +="<li data-option='"+jar0[j].user_id+"'>"+jar0[j].user_name+"("+jar0[j].user_id+")</li>";
						}
					}
					str0 +="</ul></div>";
					$("#user_key").empty();
					$("#user_key").append(str0);
					selectModel();
				}
			});
		}
	});
	
	$(".btn-primary").click(function(){
		var user_id = $("#key_select").attr("data-value");
		var key_model = $("#key_model").val();
		checkModel();
		if("error" == model_message){
			layer.tips('0~20'+i18Frame.getlength(),'#key_model',{
				tips:1,time:2000
			});
		}else{
			parent.layer.load(2,{shade:0.3});
			$.ajax({
				type: "POST",
				url: "../../../../key/updKey",
				data: {
					"key_key": key_key,
					"key_model": key_model,
					"user_id": user_id,
					"comp_key": comp_key
				},
				success:function(returnedData){
					var jo0 = eval("("+returnedData+")");
					if(jo0.check =="true"){
						parent.layer.closeAll('loading');
						parent.layer.msg(i18Frame.getedit()+' '+i18Frame.getsuccess()+'!',{icon:1,shade:0.3,time:1000});
						parent.flush(currentpage);
						parent.$("input[type='checkbox']").prop("checked",false);
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
		parent.$("input[type='checkbox']").prop("checked",false);
		parent.layer.close(index);
	});
	
});

function checkModel(){
	key_model = trim($("#key_model").val());
	if(key_model.length<=20&&key_model.length>=0){
		model_message = "ok";
		ti=1;
		return true;
	}else{
		model_message = "error";
		ti=0;
		return false;
	}
}

function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}