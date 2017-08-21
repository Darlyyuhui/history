var currentpage1=1;
var currentpage;
var branch_key;
var cbranch_key;
var ut_power;
var parentTree = parent.window.frames["treeIframe"];
$(document).ready(function (){
	ut_power = $("#ut_power").val();
	$.ajax({
		type: "POST",
		url: "../../../../company/getAllCompany",
		success:function(returnedData){
			var jar0 = eval("("+returnedData+")");
			var str0 = "<div class='model-select-box' id='unit_type'>";
			str0 +="<div class='model-select-text' id='comp_select' data-value=''>全部单位</div>"+ 
			"<ul class='model-select-option'>";
			str0 +="<li data-option='' class='seleced'>全部单位</li>";
			for(var j=0;j<jar0.length;j++){
				str0 +="<li data-option='"+jar0[j].comp_key+"'>"+jar0[j].comp_name+"</li>";
			}
			str0 +="</ul></div>";
			$(".company").empty();
			$(".company").append(str0);
			selectModel();
			Comp_Change();
		}
	});
	/*parent.$("#s_mana").text("班组管理");*/
	flush(currentpage1);
});

function flush(page){
	parent.layer.load(2,{shade:0.3});
	setCurrentPage(page);
	var branch_name = trim($(".input-text").val());
	var comp_key = $("#comp_select").attr("data-value");
	document.getElementById("branch_name").focus();
	$.ajax({
		type: "POST",
		url: "../../../../branch/getBranchByPage",
		data: {
			"currentpage":currentpage,
			"comp_key":comp_key,
			"branch_name":branch_name
		},
		success:function(returnedData){
			var jar0 = eval("("+returnedData+")");
			var str0 = "";//显示列表
			var str1;//显示页码
			var str3 = "";//显示当前为第几条到第几条数据
			$("#pagin").empty();
			$(".table tbody").empty();
			$("#page_info").empty();
			for(var i=0;i<jar0.length;i++){
				if(jar0[i].rows.length==0){
					if(ut_power.indexOf(",t4_4_2,")>-1){
						str0 += "<tr class='text-c'><td colspan='5' class='null' align='center'>无数据!</td></tr>";
					}else if(ut_power.indexOf(",t4_4_2,")==-1&&ut_power.indexOf(",t4_4_3,")>-1){
						str0 += "<tr class='text-c'><td colspan='4' class='null' align='center'>无数据!</td></tr>";
					}else{
						str0 += "<tr class='text-c'><td colspan='3' class='null' align='center'>无数据!</td></tr>";
					}
					str3 +="显示 0 到 "+jar0[i].recordCount+" ，共 "+jar0[i].recordCount+" 条";
					str1 ="<span><a class='paginate_current'>1</a></span>";
				}else{
					for(var j=0;j<jar0[i].rows.length;j++){
						if(ut_power.indexOf(",t4_4_3,")>-1){
							str0 += "<tr class='text-c' style='cursor:pointer;' name='"+jar0[i].rows[j].branch_key+"' ondblclick='updBranch(this)'>";
						}else{
							str0 += "<tr class='text-c'>";
						}
						if(ut_power.indexOf(",t4_4_2,")>-1){
							str0 += "<td><input type='checkbox' value='"+jar0[i].rows[j].branch_key+"' name='cbox'/></td>";
						}
						str0 += "<td style='display: none;'>"+jar0[i].rows[j].branch_key+"</td>" +
						"<td>"+(1+jar0[i].pageSize*(jar0[i].currentPage-1)+j)+"</td>"+
						"<td style='max-width:250px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].branch_name+"'>"+jar0[i].rows[j].branch_name+"</td>" +
						"<td style='max-width:260px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].comp_name+"'>"+jar0[i].rows[j].comp_name+"</td>";
						/*"<td class='td-status'><span class='label label-success radius'>"+jar0[i].rows[j].branch_sort+"</span></td>" +*/
						if(ut_power.indexOf(",t4_4_3,")>-1||ut_power.indexOf(",t4_4_2,")>-1){
							str0 += "<td class='f-14 td-manage'>";
							if(ut_power.indexOf(",t4_4_3,")>-1){
								str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].branch_key+"' onClick='updBranch(this)' title='编辑'><i class='Hui-iconfont'>&#xe6df;</i></a>";
							}
							if(ut_power.indexOf(",t4_4_2,")>-1){
								str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].branch_key+"' onClick='delBranch(this)' title='删除'><i class='Hui-iconfont'>&#xe6e2;</i></a>";
							}
							str0 += "</td>";
						}
					}
					
					
					
					if(jar0[i].currentPage==jar0[i].pageCount){
						str3 +="显示 "+(1+jar0[i].pageSize*(jar0[i].currentPage-1))+" 到 "+jar0[i].recordCount+" ，共 "+jar0[i].recordCount+" 条";
					}else{
						str3 +="显示 "+(1+jar0[i].pageSize*(jar0[i].currentPage-1))+" 到 "+jar0[i].pageSize*(jar0[i].currentPage)+" ，共 "+jar0[i].recordCount+" 条";
					}
					str1 = setPage(jar0[i].currentPage,jar0[i].pageCount,jar0[i].leftPagemore,jar0[i].rightPagemore,jar0[i].prePage,jar0[i].nxtPage);
				}
			}
			$(".table tbody").append(str0);
			$("#pagin").append(str1);
			$("#page_info").append(str3);
			setTimeout(function(){
				parent.layer.closeAll('loading');
			},300);
		}
	});
}

function gotoPage(obj){
	page = obj.getAttribute("name");
	flush(page);
	$("input[type='checkbox']").prop("checked",false);
}

document.onkeydown = function(e){
	e = e ? e : window.event;
    var keycode = e.which ? e.which : e.keyCode;
	if(keycode == 13){
		flush(currentpage1);
	}
};

function addBranch(){
	parent.layer.open({
		type: 2,
		title: "添加班组",
		shadeClose: true,
		maxmin: false,
		
		area:['530px','265px'],
		content: ['branch/add_branch.jsp']
	});
}

function updBranch(obj){
	cbranch_key = obj.getAttribute("name");
	setBranch_key(cbranch_key);
	parent.layer.open({
		type: 2,
		title: "编辑班组",
		shadeClose: true,
		maxmin: false,
		
		area:['530px','265px'],
		content: ['branch/upd_branch.jsp']
	});
}

function delBranch(obj){
	branch_key = obj.getAttribute("name");
	currentpage = getCurrentPage();
	parent.layer.confirm('确定删除?',{
		btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
	},function(){
		$.ajax({
			type: "POST",
			url: "../../../../branch/delBranchByKey",
			data:{"branch_key":branch_key},
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				if(jo0.check =="true"){
					$.ajax({
						type: "POST",
						url: "../../../../branch/getBranchCount",
						success:function(returnedData){
							var jo1 = eval("("+returnedData+")");
							parent.layer.msg('删除成功!',{icon:1,shade:0.3,time:1000});
							parentTree.delNode("b"+branch_key);
							$("input[type='checkbox']").prop("checked",false);
							parentTree.updNode("s2","班组管理("+jo1.count+")");
							flush(currentpage);
						}
					});
				}else if(jo0.check =="count"){
					parent.layer.alert('请先删除该班组中所有人员!',{icon:7,shade:0.3,title:'提示'});
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

function datadel(){
	currentpage = getCurrentPage();
	var obj = document.getElementsByName("cbox");
	var chk_value=[];
	var count =0;
	for(var i=0;i<obj.length;i++){
		if(obj[i].checked){
			count++;
			chk_value.push(obj[i].value);
		}
	}
	if(count==0){
		parent.layer.alert('请选择要删除的班组!',{icon:7,title:'提示',closeBtn:1});
	}else{
		parent.layer.confirm('确定删除?',{
			btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
		},function(){
			$.ajax({
				type: "POST",
				url: "../../../../branch/delMoreBranch",
				data: {
					"chk_value[]":chk_value
				},
				success:function(returnedData){
					var jar0 = eval("("+returnedData+")");
					$.ajax({
						type: "POST",
						url: "../../../../branch/getBranchCount",
						success:function(returnedData){
							var jo0 = eval("("+returnedData+")");
							if(jar0.length>0){
								var str0="";
								for(var j=0;j<jar0.length;j++){
									if(j==jar0.length-1){
										str0 +=jar0[j].branch_name;
									}else{
										str0 +=jar0[j].branch_name+",";
									}
									for(var m=0;m<chk_value.length;m++){
										if(jar0[j].branch_key==chk_value[m]){
											chk_value.splice(m,1);
										}
									}
								}
								parent.layer.alert("请先删除班组"+str0+'中所有人员!',{icon:7,shade:0.3,title:'提示'});
								for(var n=0;n<chk_value.length;n++){
									parentTree.delNode("b"+chk_value[n]);
								}
								$("input[type='checkbox']").prop("checked",false);
								parentTree.updNode("s2","班组管理("+jo0.count+")");
								flush(currentpage);
							}else{
								parent.layer.msg('删除成功!',{icon:1,shade:0.3,time:1000});
								for(var k=0;k<chk_value.length;k++){
									parentTree.delNode("b"+chk_value[k]);
								}
								$("input[type='checkbox']").prop("checked",false);
								parentTree.updNode("s2","班组管理("+jo0.count+")");
								flush(currentpage);
							}
						}
					});
				}
			});
		},function(){
			$("input[type='checkbox']").prop("checked",false);
		});
	}
}

function Comp_Change(){
	var $box = $('div.model-select-box');
	var $option = $('ul.model-select-option', $box);
	$option.find('li').mousedown(function(){
		flush(currentpage1);
	});
}

function addBranch_Node(branch_name){
	$.ajax({
		type: "POST",
		url: "../../../../branch/getBranchByName",
		data: {
			"branch_name": branch_name
		},
		success: function(returnedData){
			var jo0 = eval("("+returnedData+")");
			var id = "b"+jo0.branch_key;
			var pId = "s2";
			var zname = jo0.branch_name+"(0)";
			parentTree.addNode(id,pId,zname);
		}
	});
}

function setCurrentPage(currentpage1){
	currentpage=currentpage1;
}
function getCurrentPage(){
	return currentpage;
}
function setBranch_key(cbranch_key){
	branch_key=cbranch_key;
}
function getBranch_key(){
	return branch_key;
}
function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}