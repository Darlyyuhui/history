var currentpage1=1;
var currentpage;
var key_key;
var ckey_key;
var ut_power;
var comp_key;
var i18Frame = parent.window.frames["i18nframe"];

$(document).ready(function (){
	ut_power = $("#ut_power").val();
	comp_key = $("#comp_key").val();
	flush(currentpage1);
});


function flush(page){
	parent.layer.load(2,{shade:0.3});
	setCurrentPage(page);
	var key_id = trim($("#key_id").val());
	document.getElementById("key_id").focus();
	var user_name = trim($("#user_name").val());
	$.ajax({
		type: "POST",
		url: "../../../../key/getKeyByPage",
		data: {
			"currentpage":currentpage,
			"user_name": user_name,
			"key_id":key_id,
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
					if(ut_power.indexOf(",wt5_4_3,")>-1){
						str0 += "<tr class='text-c'><td colspan='6' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}else{
						str0 += "<tr class='text-c'><td colspan='5' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}
					str3 +=i18Frame.getdisplay()+" 0 "+i18Frame.getto()+" "+jar0[i].recordCount+" ，"+i18Frame.gettotal()+" "+jar0[i].recordCount+" "+i18Frame.getrow1();
					str1 ="<span><a class='paginate_current'>1</a></span>";
				}else{
					for(var j=0;j<jar0[i].rows.length;j++){
						if(ut_power.indexOf(",wt5_4_3,")>-1){
							str0 += "<tr class='text-c' style='cursor:pointer;' name='"+jar0[i].rows[j].key_key+"' ondblclick='updKey(this)'>";
							/*str0 += "<td><input type='checkbox' value='"+jar0[i].rows[j].key_key+"' name='cbox'/></td>";*/
						}else{
							str0 += "<tr class='text-c'>";
						}
						str0 += "<td>"+(1+jar0[i].pageSize*(jar0[i].currentPage-1)+j)+"</td>"+
						"<td style='max-width:65px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].key_id+"'>"+jar0[i].rows[j].key_id+"</u></td>" +
						"<td style='max-width:190px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].user_name+"("+jar0[i].rows[j].user_id+")'>"+jar0[i].rows[j].user_name+"("+jar0[i].rows[j].user_id+")</td>";
						if('0'==jar0[i].rows[j].del_flag){
							if('0'==jar0[i].rows[j].key_flag){
								str0 += "<td style='color:green;'>"+i18Frame.getalready_enabled()+"</td>";
							}else if('1'==jar0[i].rows[j].key_flag){
								str0 += "<td style='color:red;'>"+i18Frame.getalready_disabled()+"</td>";
							}
						}else if('1'==jar0[i].rows[j].del_flag){
							str0 += "<td style='color:red;'>"+i18Frame.getalready_disabled()+"</td>";
						}
						str0 += "<td style='max-width:180px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].key_model+"'>"+jar0[i].rows[j].key_model+"</td>";
						if(ut_power.indexOf(",wt5_4_3,")>-1){
							str0 += "<td class='f-14 td-manage'>";
							str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].key_key+"' onClick='updKey(this)' title='"+i18Frame.getedit()+"'><i class='Hui-iconfont'>&#xe6df;</i></a>";
							if('0'==jar0[i].rows[j].del_flag){
								if('0'==jar0[i].rows[j].key_flag){
									str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].key_key+"' onClick='disableKey(this)' title='"+i18Frame.getdisable()+"'><i class='Hui-iconfont'>&#xe631;</i></a>";
								}else if('1'==jar0[i].rows[j].key_flag){
									str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].key_key+"' onClick='enableKey(this)' title='"+i18Frame.getenable()+"'><i class='Hui-iconfont'>&#xe601;</i></a>";
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

function addKey(){
	layer.open({
		type: 2,
		title: i18Frame.getadd(),
		shadeClose: true,
		maxmin: false,
		
		area:['530px','320px'],
		content: ['add_key.jsp', 'no']
	});
}

function updKey(obj){
	ckey_key = obj.getAttribute("name");
	setKey_key(ckey_key);
	layer.open({
		type: 2,
		title: i18Frame.getedit(),
		shadeClose: true,
		maxmin: false,
		
		area:['530px','320px'],
		content: ['upd_key.jsp', 'no']
	});
}

function enableKey(obj){
	key_key = obj.getAttribute("name");
	currentpage = getCurrentPage();
	layer.confirm(i18Frame.getdetermine_enable()+'?',{
		btn: [i18Frame.getok(),i18Frame.getcancel()],icon:3,title:i18Frame.getinfo(),closeBtn:1
	},function(){
		$.ajax({
			type: "POST",
			url: "../../../../key/enableKey",
			data:{"key_key":key_key},
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				if(jo0.check =="true"){
					layer.msg(i18Frame.getenable_success()+'!',{icon:1,shade:0.3,time:1000});
					flush(currentpage);
				}else if(jo0.check =="false"){
					layer.msg(i18Frame.getenable_failed()+','+i18Frame.getkey_user_disabled()+'!',{icon:2,shade:0.3,time:1000});
					flush(currentpage);
				}else{
					layer.msg(i18Frame.getenable_failed()+'!',{icon:2,shade:0.3,time:1000});
				}
			}
		});
	},function(){
		
	});
}

function disableKey(obj){
	key_key = obj.getAttribute("name");
	currentpage = getCurrentPage();
	layer.confirm(i18Frame.getdetermine_disable()+'?',{
		btn: [i18Frame.getok(),i18Frame.getcancel()],icon:3,title:i18Frame.getinfo(),closeBtn:1
	},function(){
		$.ajax({
			type: "POST",
			url: "../../../../key/disableKey",
			data:{"key_key":key_key},
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				if(jo0.check =="true"){
					layer.msg(i18Frame.getdisable_success()+'!',{icon:1,shade:0.3,time:1000});
					flush(currentpage);
				}else if(jo0.check =="false"){
					layer.msg(i18Frame.getdisable_failed()+','+i18Frame.getkey_user_disabled()+'!',{icon:2,shade:0.3,time:1000});
					flush(currentpage);
				}else{
					layer.msg(i18Frame.getdisable_failed()+'!',{icon:2,shade:0.3,time:1000});
				}
			}
		});
	},function(){
		
	});
}


function setCurrentPage(currentpage1){
	currentpage=currentpage1;
}
function getCurrentPage(){
	return currentpage;
}
function setKey_key(ckey_key){
	key_key=ckey_key;
}
function getKey_key(){
	return key_key;
}
function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
