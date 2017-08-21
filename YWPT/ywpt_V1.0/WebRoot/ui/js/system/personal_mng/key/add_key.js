var currentpage;
var id_message;
var model_message;
var comp_key;
var index = parent.layer.getFrameIndex(window.name);
var i18Frame = parent.parent.window.frames["i18nframe"];

$(function(){
	currentpage = parent.getCurrentPage();
	comp_key = $("#comp_key").val();
	document.getElementById("key_id").focus();
	
	$.ajax({
		type: "POST",
		url: "../../../../user/getAllUser",
		data: {
			"comp_key": comp_key
		},
		success: function(returnedData){
			var jar0 = eval("("+returnedData+")");
			var str0 = "<div class='model-select-box'>";
			str0 +="<div class='model-select-text' id='key_select' data-value='"+jar0[0].user_id+"'>"+jar0[0].user_name+"("+jar0[0].user_id+")</div>"+ 
			"<ul class='model-select-option'>";
			for(var j=0;j<jar0.length;j++){
				if(0==j){
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
	
	$(".btn-primary").click(function(){
		var key_id = trim($("#key_id").val());
		key_id = parseInt(key_id);
		var user_id = $("#key_select").attr("data-value");
		var key_model = trim($("#key_model").val());
		checkId();
		checkModel();
		if("null" == id_message){
			layer.tips(i18Frame.getpleaseinput()+' '+i18Frame.getkeynum(),'#key_id',{
				tips:3,time:2000
			});
		}else if("error" == id_message){
			layer.tips('1~9999','#key_id',{
				tips:3,time:2000
			});
		}else if("has" == id_message){
			layer.tips(i18Frame.getkeynum()+' '+i18Frame.getexists(),'#key_id',{
				tips:3,time:2000
			});
		}else{
			if("error" == model_message){
				layer.tips('0~20'+i18Frame.getlength(),'#key_model',{
					tips:1,time:2000
				});
			}else{
				parent.layer.load(2,{shade:0.3});
				$.ajax({
					type: "POST",
					url: "../../../../key/insertKey",
					data: {
						"key_id": key_id,
						"key_model": key_model,
						"user_id": user_id,
						"comp_key": comp_key
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
	});
	
	$(".btn-default").click(function(){
		parent.$("input[type='checkbox']").prop("checked",false);
		parent.layer.close(index);
	});
});


function checkId(){
	var key_id = trim($("#key_id").val());
	var reg1 = /^[0-9]*$/;
	if(arr=key_id.match(reg1)){
		var key_id1 = parseInt(key_id);
		key_id = key_id1.toString();
		if(""==key_id.replace(/\s/g,"")){
			id_message = "null";
		}else if(0==key_id1){
			id_message = "error";
		}else{
			var reg = /^\d{1,4}?$/;
			if(arr=key_id.match(reg)){
				$.ajax({
					type: "POST",
					url: "../../../../key/checkId",
					data: {
						"param": key_id1,
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
	}else{
		id_message = "error";
		ti=0;
		return false;
	}
}

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