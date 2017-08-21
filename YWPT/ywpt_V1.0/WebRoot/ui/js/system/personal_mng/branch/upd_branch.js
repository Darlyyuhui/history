var branch_key;
var branch_name;
var cbranch_name;
var currentpage;
var name_message;
var sort_message;
var index = parent.layer.getFrameIndex(window.name);
var parentIframe = parent.window.frames["manageIframe"];
var parentTree = parent.window.frames["treeIframe"];



$(function(){
	currentpage = parentIframe.getCurrentPage();
	branch_key = parentIframe.getBranch_key();
	$.ajax({
		type: "POST",
		url: "../../../../branch/getBranchByKey",
		data: {
			"branch_key":branch_key
		},
		success:function(returnedData){
			var jo0 = eval("("+returnedData+")");
			cbranch_name = jo0.branch_name;
			$("#branch_name").val(jo0.branch_name);
			/*$("#branch_sort").val(jo0.branch_sort);*/
			var comp_key = jo0.comp_key;
			$.ajax({
				type: "POST",
				url: "../../../../company/getAllCompany",
				success:function(returnedData){
					var jar0 = eval("("+returnedData+")");
					var str0 = "<div class='model-select-box'>";
					for(var i=0;i<jar0.length;i++){
						if(jar0[i].comp_key == comp_key){
							str0 +="<div class='model-select-text' id='comp_key_select' data-value='"+jar0[i].comp_key+"'>"+jar0[i].comp_name+"</div>"+ 
							"<ul class='model-select-option'>";
						}
					}
					for(var j=0;j<jar0.length;j++){
						if(jar0[j].comp_key == comp_key){
							str0 +="<li data-option='"+jar0[j].comp_key+"' class='seleced'>"+jar0[j].comp_name+"</li>";
						}else{
							str0 +="<li data-option='"+jar0[j].comp_key+"'>"+jar0[j].comp_name+"</li>";
						}
					}
					str0 +="</ul></div>";
					$("#comp_key").empty();
					$("#comp_key").append(str0);
					selectModel();
				}
			});
		}
	});
	
	document.getElementById("branch_name").focus();
	
	$(".btn-primary").click(function(){
		branch_name = trim($("#branch_name").val());
		/*var branch_sort = trim($("#branch_sort").val());*/
		var branch_sort = "1";
		var comp_key = $("#comp_key_select").attr("data-value");
		if("null" == name_message){
			layer.tips('请输入班组名称','#branch_name',{
				tips:1,time:2000
			});
		}else if("error" == name_message){
			layer.tips('2~20位','#branch_name',{
				tips:1,time:2000
			});
		}else if("has" == name_message){
			layer.tips('班组名称已存在','#branch_name',{
				tips:1,time:2000
			});
		}else{
			/*if("false" == sort_message){
				layer.tips('最多3位数字','#branch_sort',{
					tips:1,time:2000
				});
			}else{*/
			parent.layer.load(2,{shade:0.3});
				$.ajax({
					type: "POST",
					url: "../../../../branch/updBranch",
					data: {
						"branch_key":branch_key,
						"branch_name":branch_name,
						"comp_key":comp_key,
						"branch_sort":branch_sort
					},
					success:function(returnedData){
						var jo0 = eval("("+returnedData+")");
						if(jo0.check =="true"){
							$.ajax({
								type: "POST",
								url: "../../../../branch/updBranchNodes",
								data: {
									"branch_key": branch_key
								},
								success:function(returnedData){
									var jo1 = eval("("+returnedData+")");
									parent.layer.closeAll('loading');
									parent.layer.msg('编辑成功!',{icon:1,shade:0.3,time:1000});
									parentTree.updNode("b"+jo1.branch_key,jo1.branch_name+"("+jo1.count+")");
									parentIframe.flush(currentpage);
									parentIframe.$("input[type='checkbox']").prop("checked",false);
									parent.layer.close(index);
								}
							});
						}else{
							parent.layer.closeAll('loading');
							parent.layer.msg('编辑失败!',{icon:2,shade:0.3,time:1000});
							return;
						}
					}
				});
			}
//		}
	});
	
	$(".btn-default").click(function(){
		parentIframe.$("input[type='checkbox']").prop("checked",false);
		parent.layer.close(index);
	});
});



function checkName(){
	var branch_name = trim($("#branch_name").val());
	if(""==branch_name.replace(/\s/g,"")){
		name_message = "null";
	}else{
//		var reg= /^[\u4E00-\u9FA5a-zA-Z0-9_]{2,20}$/;
		if(branch_name.length<=20&&branch_name.length>=2){
			$.ajax({
				type: "POST",
				url: "../../../../branch/checkName",
				data:{
					"param":branch_name
				},
				success:function(returnedData){
					var jo0 = eval("("+returnedData+")");
					if(branch_name == cbranch_name){
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

function showSearch(obj,type){ 
    if(type){ 
        if(trim(obj.value)==""){
        	obj.value="999"; 
        }else{
        	var branch_sort = trim($("#branch_sort").val());
        	var reg1= /^\d{0,3}?$/;
        	if(arr1=branch_sort.match(reg1)){
        		sort_message = "true";
        		ti1=1;
    			return true;
        	}else{
        		sort_message = "false";
        		ti1=0;
    			return false;
        	}
        } 
    }else{ 
        if(obj.value=="999")
        	obj.value=""; 
    } 
}

function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}