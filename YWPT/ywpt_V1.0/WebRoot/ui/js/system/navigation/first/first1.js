var f_id;
var cf_id;
var parentTree = parent.window.frames["treeIframe"];
$(document).ready(function (){
	flush();
});

function flush(){
	var f_name=trim($(".input-text").val());
	document.getElementById("f_name").focus();
	$.ajax({
		type: "POST",
		url: "../../../../first/getFirst",
		data: {
			"f_name": f_name,
			"f_type": 1
		},
		success:function(returnedData){
			var jar0 = eval("("+returnedData+")");
			var str0 = "";//显示列表
			$(".table tbody").empty();
			if(jar0.length==0){
				str0 += "<tr class='text-c'><td colspan='6' class='null' align='center'>无数据!</td></tr>";
			}else{
				for(var j=0;j<jar0.length;j++){
					str0 += "<tr class='text-c' style='cursor:pointer;' name='"+jar0[j].f_id+"' ondblclick='updFirst(this)'>"+
					"<td>"+(1+j)+"</td>"+
					"<td style='max-width:260px;white-space:nowrap;overflow:hidden;text-align: center;text-overflow: ellipsis;' title='"+jar0[j].f_id+"'>"+jar0[j].f_id+"</td>" +
					"<td>"+jar0[j].f_name+"</td>"+
					"<td>"+jar0[j].f_sort+"</td>";
					if(0==jar0[j].f_type){
						str0 +="<td>网页端</td>";
					}else{
						str0 +="<td>移动端</td>";
					}
					str0 +="<td class='f-14 td-manage'><a style='text-decoration:none' class='ml-5' name='"+jar0[j].f_id+"' onClick='updFirst(this)' title='编辑'><i class='Hui-iconfont'>&#xe6df;</i></a> " +
					"<a style='text-decoration:none' class='ml-5' name='"+jar0[j].f_id+"' onClick='delFirst(this)' title='删除'><i class='Hui-iconfont'>&#xe6e2;</i></a></td>";
				}
			}
			$(".table tbody").append(str0);
		}
	});
	
}

document.onkeydown = function(e){
	e = e ? e : window.event;
    var keycode = e.which ? e.which : e.keyCode;
	if(keycode == 13){
		flush();
	}
};

function addFirst(){
	parent.layer.open({
		type: 2,
		title: "添加一级菜单",
		shadeClose: true,
		maxmin: false,
		
		area:['530px','280px'],
		content: ['first/add_first1.jsp']
	});
}

function updFirst(obj){
	cf_id = obj.getAttribute("name");
	setF_id(cf_id);
	parent.layer.open({
		type: 2,
		title: "编辑一级菜单",
		shadeClose: true,
		maxmin: false,
		
		area:['530px','280px'],
		content: ['first/upd_first1.jsp']
	});
}

function delFirst(obj){
	f_id = obj.getAttribute("name");
	parent.layer.confirm('确定删除?',{
		btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
	},function(){
		$.ajax({
			type: "POST",
			url: "../../../../first/delFirstByID",
			data:{"f_id":f_id},
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				if(jo0.check =="true"){
					parent.layer.msg('删除成功!',{icon:1,shade:0.3,time:1000});
					parentTree.delNode(f_id);
					$("input[type='checkbox']").prop("checked",false);
					flush();
				}else if(jo0.check =="count"){
					parent.layer.alert('请先删除该菜单下的所有二级菜单!',{icon:7,shade:0.3,title:'提示'});
					flush();
					$("input[type='checkbox']").prop("checked",false);
				}else{
					parent.layer.msg('删除失败!',{icon:2,shade:0.3,time:1000});
				}
			}
		});
	},function(){
		
	});
}

function setF_id(cf_id){
	f_id=cf_id;
}
function getF_id(){
	return f_id;
}
function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}