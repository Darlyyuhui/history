var s_id;
var cs_id;
var f_id;
var cf_id;
var parentTree = parent.window.frames["treeIframe"];
$(document).ready(function (){
	var dk = $(window.parent.document).find("#manageIframe").attr("src");
	cf_id=dk.split("=")[1];
	setF_id(cf_id);
	flush();
});

function flush(){
	var s_name=trim($(".input-text").val());
	document.getElementById("s_name").focus();
	$.ajax({
		type: "POST",
		url: "../../../../second/getSecond",
		data: {
			"f_id":f_id,
			"s_name":s_name,
			"s_type": 1
		},
		success:function(returnedData){
			var jar0 = eval("("+returnedData+")");
			var str0 = "";//显示列表
			$(".table tbody").empty();
			if(jar0.length==0){
				str0 += "<tr class='text-c'><td colspan='7' class='null' align='center'>无数据!</td></tr>";
			}else{
				for(var j=0;j<jar0.length;j++){
					str0 += "<tr class='text-c' style='cursor:pointer;' name='"+jar0[j].s_id+"' ondblclick='updSecond(this)'>"+
					"<td>"+(1+j)+"</td>"+
					"<td style='max-width:100px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;'>"+jar0[j].s_id+"</td>" +
					"<td style='max-width:100px;white-space:nowrap;overflow:hidden;text-align: center;text-overflow: ellipsis;'>"+jar0[j].s_name+"</td>" +
					"<td style='max-width:100px;white-space:nowrap;overflow:hidden;text-align: center;text-overflow: ellipsis;'>"+jar0[j].s_sort+"</td>" +
					"<td style='max-width:100px;white-space:nowrap;overflow:hidden;text-align: center;text-overflow: ellipsis;'>"+jar0[j].f_id+"</td>";
					if(0==jar0[j].s_type){
						str0 +="<td>网页端</td>";
					}else{
						str0 +="<td>移动端</td>";
					}
					str0 +="<td class='f-14 td-manage'><a style='text-decoration:none' class='ml-5' name='"+jar0[j].s_id+"' onClick='updSecond(this)' title='编辑'><i class='Hui-iconfont'>&#xe6df;</i></a> " +
					"<a style='text-decoration:none' class='ml-5' name='"+jar0[j].s_id+"' onClick='delSecond(this)' title='删除'><i class='Hui-iconfont'>&#xe6e2;</i></a></td>";
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

function addSecond(){
	parent.layer.open({
		type: 2,
		title: "添加二级菜单",
		shadeClose: true,
		maxmin: false,
		
		area:['530px','330px'],
		content: ['second/add_second1.jsp']
	});
}

function updSecond(obj){
	cs_id = obj.getAttribute("name");
	setS_id(cs_id);
	parent.layer.open({
		type: 2,
		title: "编辑二级菜单",
		shadeClose: true,
		maxmin: false,
		
		area:['530px','330px'],
		content: ['second/upd_second1.jsp']
	});
}

function delSecond(obj){
	s_id = obj.getAttribute("name");
	currentpage = getCurrentPage();
	parent.layer.confirm('确定删除?',{
		btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
	},function(){
		$.ajax({
			type: "POST",
			url: "../../../../second/delSecondByID",
			data:{"s_id":s_id},
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				if(jo0.check =="true"){
					parent.layer.msg('删除成功!',{icon:1,shade:0.3,time:1000});
					parentTree.delNode(s_id);
					$("input[type='checkbox']").prop("checked",false);
					flush(currentpage);
				}else if(jo0.check =="count"){
					parent.layer.alert('请先删除该菜单下的所有三级菜单!',{icon:7,shade:0.3,title:'提示'});
					flush(currentpage);
					$("input[type='checkbox']").prop("checked",false);
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
function setF_id(cf_id){
	f_id=cf_id;
}
function getF_id(){
	return f_id;
}
function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}