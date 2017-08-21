var f_id;
var sort_message;
var index = parent.layer.getFrameIndex(window.name);
var parentIframe = parent.window.frames["manageIframe"];
var parentTree = parent.window.frames["treeIframe"];
$(function(){
	f_id = parentIframe.getF_id();
	document.getElementById("f_name").focus();
	$.ajax({
		type: "POST",
		url: "../../../../first/getFirstByID",
		data: {
			"f_id": f_id
		},
		success:function(returnedData){
			var jo0 = eval("("+returnedData+")");
			$("#f_id").val(jo0.f_id);
			$("#f_name").val(jo0.f_name);
			$("#f_sort").val(jo0.f_sort);
		}
	});
	
	$(".btn-primary").click(function(){
		var f_id = trim($("#f_id").val());
		var f_name = trim($("#f_name").val());
		var f_sort = trim($("#f_sort").val());
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
					url: "../../../../first/updFirst",
					data: {
						"f_id":f_id,
						"f_name":f_name,
						"f_sort":f_sort
					},
					success:function(returnedData){
						var jo0 = eval("("+returnedData+")");
						if(jo0.check =="true"){
							parent.layer.msg('编辑成功!',{icon:1,shade:0.3,time:1000});
							parentTree.updNode(f_id,f_name);
							parentIframe.flush();
							parentIframe.$("input[type='checkbox']").prop("checked",false);
							parent.layer.close(index);
						}else{
							parent.layer.msg('编辑失败!',{icon:2,shade:0.3,time:1000});
							return;
						}
					}
				});
			}
		}
	});
	
	$(".btn-default").click(function(){
		parentIframe.$("input[type='checkbox']").prop("checked",false);
		parent.layer.close(index);
	});
});

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