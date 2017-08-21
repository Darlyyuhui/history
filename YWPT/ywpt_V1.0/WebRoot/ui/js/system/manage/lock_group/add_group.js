var currentpage;
var name_message;
var comp_key;
var index = parent.layer.getFrameIndex(window.name);
var i18Frame = parent.parent.window.frames["i18nframe"];
var parentIframe = parent.window.frames["manageIframe"];
var parentTree = parent.window.frames["treeIframe"];
$(function(){
	currentpage = parentIframe.getCurrentPage();
	comp_key = $("#comp_key").val();
	document.getElementById("group_name").focus();
	$(".btn-primary").click(function(){
		var group_name = trim($("#group_name").val());
		var group_remark = $("#group_remark").val();
		if("null" == name_message){
			layer.tips(i18Frame.getpleaseinput()+' '+i18Frame.getlockgroupname(),'#group_name',{
				tips:3,time:2000
			});
		}else if("error" == name_message){
			layer.tips('2~20'+i18Frame.getlength(),'#group_name',{
				tips:3,time:2000
			});
		}else if("has" == name_message){
			layer.tips(i18Frame.getlockgroupname()+' '+i18Frame.getexists(),'#group_name',{
				tips:3,time:2000
			});
		}else{
				if(100<group_remark.length){
					layer.tips('0~100'+i18Frame.getlength(),'#group_remark',{
						tips:1,time:2000
					});
				}else{
					parent.layer.load(2,{shade:0.3});
					$.ajax({
						type: "POST",
						url: "../../../../group/insertGroup",
						data: {
							"group_name":group_name,
							"group_remark":group_remark,
							"comp_key": comp_key
						},
						success:function(returnedData){
							var jo0 = eval("("+returnedData+")");
							if(jo0.check =="true"){
								$.ajax({
									type: "POST",
									url: "../../../../group/getGroupCount",
									data: {
										"comp_key": comp_key
									},
									success:function(returnedData){
										var jo1 = eval("("+returnedData+")");
										parentTree.updNode("s1",i18Frame.getlockgroup()+"("+jo1.count+")");
										parent.layer.closeAll('loading');
										parent.layer.msg(i18Frame.getadd()+' '+i18Frame.getsuccess()+'!',{icon:1,shade:0.3,time:1000});
										parentIframe.addGroup_Node(group_name);
										parentIframe.flush(currentpage);
										parentIframe.$("input[type='checkbox']").prop("checked",false);
										parent.layer.close(index);
									}
								});
							}else{
								parent.layer.closeAll('loading');
								parent.layer.msg(i18Frame.getadd()+' '+i18Frame.getfailed()+'!',{icon:2,shade:0.3,time:1000});
								return;
							}
						}
					});
				}
			}
//		}
	});
	$(".btn-default").click(function(){
		parentIframe.$("input[type='checkbox']").prop("checked",false);
		parent.layer.close(index);
	});
});

function checkName(){
	var group_name = trim($("#group_name").val());
	if(""==group_name.replace(/\s/g,"")){
		name_message = "null";
	}else{
		if(group_name.length<=20&&group_name.length>=2){
			$.ajax({
				type: "POST",
				url: "../../../../group/checkName",
				data:{
					"param":group_name,
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