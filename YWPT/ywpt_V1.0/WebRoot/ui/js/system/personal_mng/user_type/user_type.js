var currentpage1=1;
var currentpage;
var ut_key;
var cut_key;
var ut_power;
var comp_key;
var i18Frame = parent.window.frames["i18nframe"];
$(document).ready(function (){
	ut_power = $("#ut_power").val();
	comp_key = $("#comp_key").val();
	flush(currentpage1);
});

function flush(page){
	layer.load(2,{shade:0.3});
	setCurrentPage(page);
	var ut_name=trim($(".input-text").val());
	document.getElementById("ut_name").focus();
	$.ajax({
		type: "POST",
		url: "../../../../user_type/getUser_typeByPage",
		data: {
			"currentpage": currentpage,
			"ut_name": ut_name,
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
					if(ut_power.indexOf(",wt5_8_2,")>-1){
						str0 += "<tr class='text-c'><td colspan='5' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}else if(ut_power.indexOf(",wt5_8_2,")==-1&&ut_power.indexOf(",wt5_8_3,")>-1){
						str0 += "<tr class='text-c'><td colspan='4' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}else{
						str0 += "<tr class='text-c'><td colspan='3' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}
					str3 +=i18Frame.getdisplay()+" 0 "+i18Frame.getto()+" "+jar0[i].recordCount+" ，"+i18Frame.gettotal()+" "+jar0[i].recordCount+" "+i18Frame.getrow1();
					str1 ="<span><a class='paginate_current'>1</a></span>";
				}else{
					for(var j=0;j<jar0[i].rows.length;j++){
						if(ut_power.indexOf(",wt5_8_3,")>-1){
							if("0"==jar0[i].rows[j].ut_flag){
								str0 += "<tr class='text-c'>";
							}else{
								str0 += "<tr class='text-c' style='cursor:pointer;' name='"+jar0[i].rows[j].ut_key+"' ondblclick='updUser_type(this)'>";
							}
						}else{
							str0 += "<tr class='text-c'>";
						}
						if(ut_power.indexOf(",wt5_8_2,")>-1){
							if("0"==jar0[i].rows[j].ut_flag){
								str0 += "<td></td>";
							}else{
								str0 += "<td><input type='checkbox' value='"+jar0[i].rows[j].ut_key+"' name='cbox'/></td>";
							}
						}
						str0 += "<td>"+(1+jar0[i].pageSize*(jar0[i].currentPage-1)+j)+"</td>"+
						"<td>"+jar0[i].rows[j].ut_name+"</td>" +
						"<td style='max-width:260px;white-space:nowrap;overflow:hidden;text-align: center;text-overflow: ellipsis;' title='"+jar0[i].rows[j].ut_remark+"'>"+jar0[i].rows[j].ut_remark+"</td>";
						if(ut_power.indexOf(",wt5_8_3,")>-1||ut_power.indexOf(",wt5_8_2,")>-1){
							str0 += "<td class='f-14 td-manage'>";
							if(ut_power.indexOf(",wt5_8_3,")>-1){
								if("1"==jar0[i].rows[j].ut_flag){
									str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].ut_key+"' onClick='updUser_type(this)' title='"+i18Frame.getedit()+"'><i class='Hui-iconfont'>&#xe6df;</i></a>";
								}
							}
							if(ut_power.indexOf(",wt5_8_2,")>-1){
								if("1"==jar0[i].rows[j].ut_flag){
									str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].ut_key+"' onClick='delUser_type(this)' title='"+i18Frame.getdelete()+"'><i class='Hui-iconfont'>&#xe6e2;</i></a>";
								}
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
				layer.closeAll('loading');
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

function addUser_type(){
	layer.open({
		type: 2,
		title: i18Frame.getadd(),
		shadeClose: true,
		maxmin: false,
		
		area:['763px','400px'],
		content: ['add_user_type.jsp']
	});
}

function updUser_type(obj){
	cut_key = obj.getAttribute("name");
	setUt_key(cut_key);
	layer.open({
		type: 2,
		title: i18Frame.getedit(),
		shadeClose: true,
		maxmin: false,
		
		area:['763px','400px'],
		content: ['upd_user_type.jsp']
	});
}

function delUser_type(obj){
	ut_key = obj.getAttribute("name");
	currentpage = getCurrentPage();
	layer.confirm(i18Frame.getdetermine_delete()+'?',{
		btn: [i18Frame.getok(),i18Frame.getcancel()],icon:3,title:i18Frame.getinfo(),closeBtn:1
	},function(){
		$.ajax({
			type: "POST",
			url: "../../../../user_type/delUser_typeByKey",
			data:{"ut_key":ut_key},
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				if(jo0.check =="true"){
					layer.msg(i18Frame.getdelete_success()+'!',{icon:1,shade:0.3,time:1000});
					$("input[type='checkbox']").prop("checked",false);
					flush(currentpage);
				}else if(jo0.check =="count"){
					layer.alert('请先删除该角色类型下所有用户!',{icon:7,shade:0.3,title:i18Frame.getinfo()});
					flush(currentpage);
					$("input[type='checkbox']").prop("checked",false);
				}else{
					layer.msg(i18Frame.getdelete_failed()+'!',{icon:2,shade:0.3,time:1000});
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
		layer.alert('请选择要删除的角色类型!',{icon:7,title:'提示',closeBtn:1});
	}else{
		layer.confirm(i18Frame.getdetermine_delete()+'?',{
			btn: [i18Frame.getok(),i18Frame.getcancel()],icon:3,title:i18Frame.getinfo(),closeBtn:1
		},function(){
			$.ajax({
				type: "POST",
				url: "../../../../user_type/delMoreUser_type",
				data: {
					"chk_value[]":chk_value
				},
				success:function(returnedData){
					var jar0 = eval("("+returnedData+")");
					if(jar0.length>0){
						var str0="";
						for(var j=0;j<jar0.length;j++){
							if(j==jar0.length-1){
								str0 +=jar0[j].ut_name;
							}else{
								str0 +=jar0[j].ut_name+",";
							}
						}
						layer.alert("请先删除"+str0+'下所有用户!',{icon:7,shade:0.3,title:i18Frame.getinfo()});
						$("input[type='checkbox']").prop("checked",false);
						flush(currentpage);
					}else{
						layer.msg(i18Frame.getdelete_success()+'!',{icon:1,shade:0.3,time:1000});
						$("input[type='checkbox']").prop("checked",false);
						flush(currentpage);
					}
				}
			});
		},function(){
			$("input[type='checkbox']").prop("checked",false);
		});
	}
}

function setCurrentPage(currentpage1){
	currentpage=currentpage1;
}
function getCurrentPage(){
	return currentpage;
}
function setUt_key(cut_key){
	ut_key=cut_key;
}
function getUt_key(){
	return ut_key;
}
function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}