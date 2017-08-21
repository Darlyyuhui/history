var f_id;
var id_message;
var sort_message;
var index = parent.layer.getFrameIndex(window.name);
var parentIframe = parent.window.frames["manageIframe"];
var parentTree = parent.window.frames["treeIframe"];
$(function(){
	f_id=parentIframe.getF_id();
	document.getElementById("s_id").focus();
	$.ajax({
		type: "POST",
		url: "../../../../first/getAllFirst",
		data: {
			"f_type": 1
		},
		success:function(returnedData){
			var jar0 = eval("("+returnedData+")");
			var str0 = "<div class='model-select-box'>";
			for(var i=0;i<jar0.length;i++){
				if(jar0[i].f_id==f_id){
					str0 +="<div class='model-select-text' id='id_select' data-value='"+jar0[i].f_id+"'>"+jar0[i].f_name+"("+jar0[i].f_id+")</div>"+ 
					"<ul class='model-select-option'>";
				}
			}
			for(var j=0;j<jar0.length;j++){
				if(jar0[j].f_id==f_id){
					str0 +="<li data-option='"+jar0[j].f_id+"' class='seleced'>"+jar0[j].f_name+"("+jar0[j].f_id+")</li>";
				}else{
					str0 +="<li data-option='"+jar0[j].f_id+"'>"+jar0[j].f_name+"("+jar0[j].f_id+")</li>";
				}
			}
			str0 +="</ul></div>";
			$("#f_id").empty();
			$("#f_id").append(str0);
			selectModel();
		}
	});
	
	$(".btn-primary").click(function(){
		var s_id = trim($("#s_id").val());
		var s_name = trim($("#s_name").val());
		var s_sort = trim($("#s_sort").val());
		var f_id = $("#id_select").attr("data-value");
		var s_type = 1;
		if("null" == id_message){
			layer.tips('请输入菜单编号','#s_id',{
				tips:3,time:2000
			});
		}else if("error" == id_message){
			layer.tips('2~11位','#s_id',{
				tips:3,time:2000
			});
		}else if("has" == id_message){
			layer.tips('菜单编号已存在','#s_id',{
				tips:3,time:2000
			});
		}else{
			if("false" == sort_message){
				layer.tips('最多4位数字','#s_sort',{
					tips:3,time:2000
				});
			}else{
				if(32<s_name.length||s_name.length<2){
					layer.tips('2~32位','#s_name',{
						tips:1,time:2000
					});
				}else{
					$.ajax({
						type: "POST",
						url: "../../../../second/insertSecond",
						data: {
							"s_id":s_id,
							"s_name":s_name,
							"s_sort":s_sort,
							"f_id":f_id,
							"s_type":s_type
						},
						success:function(returnedData){
							var jo0 = eval("("+returnedData+")");
							if(jo0.check =="true"){
								parent.layer.msg('添加成功!',{icon:1,shade:0.3,time:1000});
								parentTree.addNode(s_id,f_id,s_name,"third/third");
								parentIframe.flush();
								parentIframe.$("input[type='checkbox']").prop("checked",false);
								parent.layer.close(index);
							}else{
								parent.layer.msg('添加失败!',{icon:2,shade:0.3,time:1000});
								return;
							}
						}
					});
				}
			}
		}
	});
	
	$(".btn-default").click(function(){
		parentIframe.$("input[type='checkbox']").prop("checked",false);
		parent.layer.close(index);
	});
});

function checkID(){
	var s_id = trim($("#s_id").val());
	if(""==s_id.replace(/\s/g,"")){
		id_message = "null";
	}else{
		if(s_id.length<=20&&s_id.length>=2){
			$.ajax({
				type: "POST",
				url: "../../../../second/checkID",
				data:{
					"param":s_id
				},
				success:function(returnedData){
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

function showSearch(obj,type){ 
    if(type){ 
        if(trim(obj.value)==""){
        	obj.value="0000"; 
        }else{
        	var s_sort = trim($("#s_sort").val());
        	var reg1= /^\d{0,4}?$/;
        	if(arr1=s_sort.match(reg1)){
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
        if(obj.value=="0000")
        	obj.value=""; 
    } 
}

function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}