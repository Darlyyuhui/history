var id_message;
var sort_message;
var index = parent.layer.getFrameIndex(window.name);
var parentIframe = parent.window.frames["manageIframe"];
var parentTree = parent.window.frames["treeIframe"];
$(function(){
	document.getElementById("f_id").focus();
	$(".btn-primary").click(function(){
		var f_id = trim($("#f_id").val());
		var f_name = trim($("#f_name").val());
		var f_sort = trim($("#f_sort").val());
		var f_type = 0;
		if("null" == id_message){
			layer.tips('请输入菜单编号','#f_id',{
				tips:3,time:2000
			});
		}else if("error" == id_message){
			layer.tips('2~11位','#f_id',{
				tips:3,time:2000
			});
		}else if("has" == id_message){
			layer.tips('菜单编号已存在','#f_id',{
				tips:3,time:2000
			});
		}else{
			if("false" == sort_message){
				layer.tips('最多4位数字','#f_sort',{
					tips:3,time:2000
				});
			}else{
				if(32<f_name.length||f_name.length<2){
					layer.tips('2~32位','#f_name',{
						tips:1,time:2000
					});
				}else{
					$.ajax({
						type: "POST",
						url: "../../../../first/insertFirst",
						data: {
							"f_id":f_id,
							"f_name":f_name,
							"f_sort":f_sort,
							"f_type":f_type
						},
						success:function(returnedData){
							var jo0 = eval("("+returnedData+")");
							if(jo0.check =="true"){
								parent.layer.msg('添加成功!',{icon:1,shade:0.3,time:1000});
								parentTree.addNode(f_id,"n1",f_name,"second/second");
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
	var f_id = trim($("#f_id").val());
	if(""==f_id.replace(/\s/g,"")){
		id_message = "null";
	}else{
		if(f_id.length<=20&&f_id.length>=2){
			$.ajax({
				type: "POST",
				url: "../../../../first/checkID",
				data:{
					"param":f_id
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
        	var f_sort = trim($("#f_sort").val());
        	var reg1= /^\d{0,4}?$/;
        	if(arr1=f_sort.match(reg1)){
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