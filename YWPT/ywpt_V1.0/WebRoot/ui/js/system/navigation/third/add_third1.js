var s_id;
var id_message;
var sort_message;
var index = parent.layer.getFrameIndex(window.name);
var parentIframe = parent.window.frames["manageIframe"];
var parentTree = parent.window.frames["treeIframe"];
$(function(){
	s_id=parentIframe.getS_id();
	document.getElementById("t_id").focus();
	$.ajax({
		type: "POST",
		url: "../../../../second/getAllSecondByFirst",
		data: {
			"s_id": s_id
		},
		success:function(returnedData){
			var jar0 = eval("("+returnedData+")");
			var str0 = "<div class='model-select-box'>";
			for(var i=0;i<jar0.length;i++){
				if(jar0[i].s_id==s_id){
					str0 +="<div class='model-select-text' id='id_select' data-value='"+jar0[i].s_id+"'>"+jar0[i].s_name+"("+jar0[i].s_id+")</div>"+ 
					"<ul class='model-select-option'>";
				}
			}
			for(var j=0;j<jar0.length;j++){
				if(jar0[j].s_id==s_id){
					str0 +="<li data-option='"+jar0[j].s_id+"' class='seleced'>"+jar0[j].s_name+"("+jar0[j].s_id+")</li>";
				}else{
					str0 +="<li data-option='"+jar0[j].s_id+"'>"+jar0[j].s_name+"("+jar0[j].s_id+")</li>";
				}
			}
			str0 +="</ul></div>";
			var str1 = "<div class='model-select-box'>";
			str1 +="<div class='model-select-text' id='name_select' data-value='add'>添加</div>"+ 
			"<ul class='model-select-option'>";
			str1 +="<li data-option='add' class='seleced'>添加</li>";
			str1 +="<li data-option='delete'>删除</li>";
			str1 +="<li data-option='edit'>编辑</li>";
			str1 +="<li data-option='query'>查询</li>";
			str1 +="<li data-option='exportexcel'>Excel导出</li>";
			str1 +="</ul></div>";
			$("#s_id").empty();
			$("#s_id").append(str0);
			$("#t_name").empty();
			$("#t_name").append(str1);
			selectModel();
		}
	});
	
	$(".btn-primary").click(function(){
		var t_id = trim($("#t_id").val());
		var t_name = $("#name_select").attr("data-value");
		var s_id = $("#id_select").attr("data-value");
		var t_sort = trim($("#t_sort").val());
		var t_type = 1;
		if("null" == id_message){
			layer.tips('请输入菜单编号','#t_id',{
				tips:3,time:2000
			});
		}else if("error" == id_message){
			layer.tips('2~11位','#t_id',{
				tips:3,time:2000
			});
		}else if("has" == id_message){
			layer.tips('菜单编号已存在','#t_id',{
				tips:3,time:2000
			});
		}else{
			if("false" == sort_message){
				layer.tips('最多4位数字','#s_sort',{
					tips:3,time:2000
				});
			}else{
				$.ajax({
					type: "POST",
					url: "../../../../third/insertThird",
					data: {
						"t_id":t_id,
						"t_name":t_name,
						"t_sort":t_sort,
						"s_id":s_id,
						"t_type":t_type
					},
					success:function(returnedData){
						var jo0 = eval("("+returnedData+")");
						if(jo0.check =="true"){
							parent.layer.msg('添加成功!',{icon:1,shade:0.3,time:1000});
							parentTree.addNode(t_id,s_id,t_name,"");
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
	});
	
	$(".btn-default").click(function(){
		parentIframe.$("input[type='checkbox']").prop("checked",false);
		parent.layer.close(index);
	});
});

function checkID(){
	var t_id = trim($("#t_id").val());
	if(""==t_id.replace(/\s/g,"")){
		id_message = "null";
	}else{
		if(t_id.length<=20&&t_id.length>=2){
			$.ajax({
				type: "POST",
				url: "../../../../third/checkID",
				data:{
					"param":t_id
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
        	var t_sort = trim($("#t_sort").val());
        	var reg1= /^\d{0,4}?$/;
        	if(arr1=t_sort.match(reg1)){
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