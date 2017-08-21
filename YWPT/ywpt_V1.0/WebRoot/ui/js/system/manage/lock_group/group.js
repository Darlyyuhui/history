var currentpage1=1;
var currentpage;
var group_key;
var cgroup_key;
var recordcount;
var ut_power;
var comp_key;
var parentTree = parent.window.frames["treeIframe"];
var i18Frame = parent.parent.window.frames["i18nframe"];
$(document).ready(function (){
	ut_power = $("#ut_power").val();
	comp_key = $("#comp_key").val();
	flush(currentpage1);
});

function flush(page){
	parent.layer.load(2,{shade:0.3});
	setCurrentPage(page);
	var group_name=trim($(".input-text").val());
	document.getElementById("group_name").focus();
	$.ajax({
		type: "POST",
		url: "../../../../group/getGroupByPage",
		data: {
			"currentpage": currentpage,
			"group_name": group_name,
			"comp_key": comp_key
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
					if(ut_power.indexOf(",wt5_3_2,")>-1){
						str0 += "<tr class='text-c'><td colspan='5' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}else if(ut_power.indexOf(",wt5_3_2,")==-1&&ut_power.indexOf(",wt5_3_3,")>-1){
						str0 += "<tr class='text-c'><td colspan='4' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}else{
						str0 += "<tr class='text-c'><td colspan='3' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}
					str3 +=i18Frame.getdisplay()+" 0 "+i18Frame.getto()+" "+jar0[i].recordCount+" ，"+i18Frame.gettotal()+" "+jar0[i].recordCount+" "+i18Frame.getrow1();
					str1 ="<span><a class='paginate_current'>1</a></span>";
				}else{
					for(var j=0;j<jar0[i].rows.length;j++){
						if(ut_power.indexOf(",wt5_3_3,")>-1){
							str0 += "<tr class='text-c' style='cursor:pointer;' name='"+jar0[i].rows[j].group_key+"' ondblclick='updGroup(this)'>";
						}else{
							str0 += "<tr class='text-c'>";
						}
						if(ut_power.indexOf(",wt5_3_2,")>-1){
							str0 += "<td><input type='checkbox' value='"+jar0[i].rows[j].group_key+"' name='cbox'/></td>";
						}
						str0 += "<td>"+(1+jar0[i].pageSize*(jar0[i].currentPage-1)+j)+"</td>"+
						"<td>"+jar0[i].rows[j].group_name+"</td>" +
						"<td style='max-width:260px;white-space:nowrap;overflow:hidden;text-align: center;text-overflow: ellipsis;' title='"+jar0[i].rows[j].group_remark+"'>"+jar0[i].rows[j].group_remark+"</td>";
						if(ut_power.indexOf(",wt5_3_3,")>-1||ut_power.indexOf(",wt5_3_2,")>-1){
							str0 += "<td class='f-14 td-manage'>";
							if(ut_power.indexOf(",wt5_3_3,")>-1){
								str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].group_key+"' onClick='updGroup(this)' title='"+i18Frame.getedit()+"'><i class='Hui-iconfont'>&#xe6df;</i></a>";
							}
							if(ut_power.indexOf(",wt5_3_2,")>-1){
								str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].group_key+"' onClick='delGroup(this)' title='"+i18Frame.getdelete()+"'><i class='Hui-iconfont'>&#xe6e2;</i></a>";
							}
							str0 += "</td>";
						}
					}
					
					
					
					if(jar0[i].currentPage==jar0[i].pageCount){
						str3 +=i18Frame.getdisplay()+" "+(1+jar0[i].pageSize*(jar0[i].currentPage-1))+" "+i18Frame.getto()+" "+jar0[i].recordCount+" ，"+i18Frame.gettotal()+" "+jar0[i].recordCount+" "+i18Frame.getrow2();
					}else{
						str3 +=i18Frame.getdisplay()+" "+(1+jar0[i].pageSize*(jar0[i].currentPage-1))+" "+i18Frame.getto()+" "+jar0[i].pageSize*(jar0[i].currentPage)+" ，"+i18Frame.gettotal()+" "+jar0[i].recordCount+" "+i18Frame.getrow2();
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

function addGroup(){
	parent.layer.open({
		type: 2,
		title: i18Frame.getadd(),
		shadeClose: true,
		maxmin: false,
		
		area:['650px','300px'],
		content: ['add_group.jsp']
	});
}
function updGroup(obj){
	cgroup_key = obj.getAttribute("name");
	setGroup_key(cgroup_key);
	parent.layer.open({
		type: 2,
		title: i18Frame.getedit(),
		shadeClose: true,
		maxmin: false,
		
		area:['650px','300px'],
		content: ['upd_group.jsp']
	});
}
function delGroup(obj){
	group_key = obj.getAttribute("name");
	currentpage = getCurrentPage();
	parent.layer.confirm(i18Frame.getdetermine_delete()+'?',{
		btn: [i18Frame.getok(),i18Frame.getcancel()],icon:3,title:i18Frame.getinfo(),closeBtn:1
	},function(){
		$.ajax({
			type: "POST",
			url: "../../../../group/delGroupByKey",
			data:{
				"group_key":group_key,
				"comp_key": comp_key
			},
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				if(jo0.check =="true"){
					$.ajax({
						type: "POST",
						url: "../../../../group/getGroupCount",
						data:{
							"comp_key": comp_key
						},
						success:function(returnedData){
							var jo1 = eval("("+returnedData+")");
							parent.layer.msg(i18Frame.getdelete_success()+'!',{icon:1,shade:0.3,time:1000});
							parentTree.delNode("g"+group_key);
							$("input[type='checkbox']").prop("checked",false);
							parentTree.updNode("s1",i18Frame.getlockgroup()+"("+jo1.count+")");
							flush(currentpage);
						}
					});
				}else if(jo0.check =="count"){
					parent.layer.alert(i18Frame.getdelete_all_group_unit()+'!',{icon:7,shade:0.3,title:i18Frame.getinfo()});
					flush(currentpage);
					$("input[type='checkbox']").prop("checked",false);
				}else{
					parent.layer.msg(i18Frame.getdelete_failed()+'!',{icon:2,shade:0.3,time:1000});
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
		parent.layer.alert(i18Frame.getchoose_delete_group()+'!',{icon:7,title:i18Frame.getinfo(),closeBtn:1});
	}else{
		parent.layer.confirm(i18Frame.getdetermine_delete()+'?',{
			btn: [i18Frame.getok(),i18Frame.getcancel()],icon:3,title:i18Frame.getinfo(),closeBtn:1
		},function(){
			$.ajax({
				type: "POST",
				url: "../../../../group/delMoreGroup",
				data: {
					"chk_value[]":chk_value,
					"comp_key": comp_key
				},
				success:function(returnedData){
					var jar0 = eval("("+returnedData+")");
					$.ajax({
						type: "POST",
						url: "../../../../group/getGroupCount",
						data:{
							"comp_key": comp_key
						},
						success:function(returnedData){
							var jo0 = eval("("+returnedData+")");
							if(jar0.length>0){
								var str0="";
								for(var j=0;j<jar0.length;j++){
									if(j==jar0.length-1){
										str0 +=jar0[j].group_name;
									}else{
										str0 +=jar0[j].group_name+",";
									}
									for(var m=0;m<chk_value.length;m++){
										if(jar0[j].group_key==chk_value[m]){
											chk_value.splice(m,1);
										}
									}
								}
								parent.layer.alert(i18Frame.getdelete_all_group_unit()+':'+str0+'!',{icon:7,shade:0.3,title:i18Frame.getinfo()});
								for(var n=0;n<chk_value.length;n++){
									parentTree.delNode("g"+chk_value[n]);
								}
								$("input[type='checkbox']").prop("checked",false);
								parentTree.updNode("s1",i18Frame.getlockgroup()+"("+jo0.count+")");
								flush(currentpage);
							}else{
								parent.layer.msg(i18Frame.getdelete_success()+'!',{icon:1,shade:0.3,time:1000});
								for(var k=0;k<chk_value.length;k++){
									parentTree.delNode("g"+chk_value[k]);
								}
								$("input[type='checkbox']").prop("checked",false);
								parentTree.updNode("s1",i18Frame.getlockgroup()+"("+jo0.count+")");
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

function addGroup_Node(name){
	$.ajax({
		type: "POST",
		url: "../../../../group/getGroupByName",
		data: {
			"group_name": name,
			"comp_key": comp_key
		},
		success:function(returnedData){
			var jo0 = eval("("+returnedData+")");
			var id = "g"+jo0.group_key;
			var pId = "s1";
			var zname= jo0.group_name+"(0)";
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
function setGroup_key(cgroup_key){
	group_key=cgroup_key;
}
function getGroup_key(){
	return group_key;
}
function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}