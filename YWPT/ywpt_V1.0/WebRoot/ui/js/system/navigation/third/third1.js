var t_id;
var ct_id;
var s_id;
var cs_id;
var parentTree = parent.window.frames["treeIframe"];
$(document).ready(function (){
	var dk = $(window.parent.document).find("#manageIframe").attr("src");
	cs_id=dk.split("=")[1];
	setS_id(cs_id);
	flush();
});

function flush(){
	var t_name=trim($(".input-text").val());
	document.getElementById("t_name").focus();
	$.ajax({
		type: "POST",
		url: "../../../../third/getThird",
		data: {
			"s_id":s_id,
			"t_name":t_name,
			"t_type": 1
		},
		success:function(returnedData){
			var jar0 = eval("("+returnedData+")");
			var str0 = "";//显示列表
			$(".table tbody").empty();
			if(jar0.length==0){
				str0 += "<tr class='text-c'><td colspan='7' class='null' align='center'>无数据!</td></tr>";
			}else{
				for(var j=0;j<jar0.length;j++){
					str0 += "<tr class='text-c' style='cursor:pointer;' name='"+jar0[j].t_id+"' ondblclick='updThird(this)'>"+
					"<td>"+(1+j)+"</td>"+
					"<td style='max-width:170px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;'>"+jar0[j].t_id+"</td>" +
					"<td style='max-width:165px;white-space:nowrap;overflow:hidden;text-align: center;text-overflow: ellipsis;'>"+jar0[j].t_name+"</td>" +
					"<td style='max-width:165px;white-space:nowrap;overflow:hidden;text-align: center;text-overflow: ellipsis;'>"+jar0[j].t_sort+"</td>" +
					"<td style='max-width:260px;white-space:nowrap;overflow:hidden;text-align: center;text-overflow: ellipsis;'>"+jar0[j].s_id+"</td>";
					if(0==jar0[j].t_type){
						str0 +="<td>网页端</td>";
					}else{
						str0 +="<td>移动端</td>";
					}
					str0 +="<td class='f-14 td-manage'><a style='text-decoration:none' class='ml-5' name='"+jar0[j].t_id+"' onClick='updThird(this)' title='编辑'><i class='Hui-iconfont'>&#xe6df;</i></a> " +
					"<a style='text-decoration:none' class='ml-5' name='"+jar0[j].t_id+"' onClick='delThird(this)' title='删除'><i class='Hui-iconfont'>&#xe6e2;</i></a></td>";
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

function addThird(){
	parent.layer.open({
		type: 2,
		title: "添加三级菜单",
		shadeClose: true,
		maxmin: false,
		
		area:['530px','350px'],
		content: ['third/add_third1.jsp', 'no']
	});
}

function updThird(obj){
	ct_id = obj.getAttribute("name");
	setT_id(ct_id);
	parent.layer.open({
		type: 2,
		title: "编辑三级菜单",
		shadeClose: true,
		maxmin: false,
		
		area:['530px','350px'],
		content: ['third/upd_third1.jsp', 'no']
	});
}

function delThird(obj){
	t_id = obj.getAttribute("name");
	parent.layer.confirm('确定删除?',{
		btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
	},function(){
		$.ajax({
			type: "POST",
			url: "../../../../third/delThirdByID",
			data:{"t_id":t_id},
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				if(jo0.check =="true"){
					parent.layer.msg('删除成功!',{icon:1,shade:0.3,time:1000});
					parentTree.delNode(t_id);
					$("input[type='checkbox']").prop("checked",false);
					flush();
				}else{
					parent.layer.msg('删除失败!',{icon:2,shade:0.3,time:1000});
				}
			}
		});
	},function(){
		
	});
}

function setS_id(cs_id){
	s_id=cs_id;
}
function getS_id(){
	return s_id;
}
function setT_id(ct_id){
	t_id=ct_id;
}
function getT_id(){
	return t_id;
}
function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}