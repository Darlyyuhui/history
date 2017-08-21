var currentpage1=1;
var currentpage;
var user_key;
var cuser_key;
var login_user;
var ut_power;
var comp_key;
var i18Frame = parent.window.frames["i18nframe"];

$(document).ready(function (){
	ut_power = $("#ut_power").val();
	comp_key = $("#comp_key").val();
	var user_id = $("#user_id").val();
	$.ajax({
		type: "POST",
		url: "../../../../user/getUserById",
		data: {
			"user_id": user_id,
			"comp_key": comp_key
		},
		success: function(returnedData){
			var jo0 = eval("("+returnedData+")");
			login_user = jo0.user_key;
		}
	}); 
	flush(currentpage1);
});

function flush(page){
	layer.load(2,{shade:0.3});
	setCurrentPage(page);
	var user_like=trim($(".input-text").val());
	document.getElementById("user_like").focus();
	$.ajax({
		type: "POST",
		url: "../../../../user/getUserByPage1",
		data: {
			"currentpage":currentpage,
			"user_like":user_like,
			"comp_key":comp_key
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
					if(ut_power.indexOf(",wt5_5_3,")>-1){
						str0 += "<tr class='text-c'><td colspan='9' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}else{
						str0 += "<tr class='text-c'><td colspan='8' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}
					str3 +=i18Frame.getdisplay()+" 0 "+i18Frame.getto()+" "+jar0[i].recordCount+" ，"+i18Frame.gettotal()+" "+jar0[i].recordCount+" "+i18Frame.getrow1();
					str1 ="<span><a class='paginate_current'>1</a></span>";
				}else{
					for(var j=0;j<jar0[i].rows.length;j++){
						if(ut_power.indexOf(",wt5_5_3,")>-1){
							if(comp_key==jar0[i].rows[j].comp_key){
								str0 += "<tr class='text-c' style='cursor:pointer;' name='"+jar0[i].rows[j].user_key+"' ondblclick='updUser(this)'>";
							}else{
								str0 += "<tr class='text-c'>";
							}
						}else{
							str0 += "<tr class='text-c'>";
						}
						str0 += "<td>"+(1+jar0[i].pageSize*(jar0[i].currentPage-1)+j)+"</td>"+
						"<td style='max-width:80px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].user_id+"'>"+jar0[i].rows[j].user_id+"</u></td>" +
						"<td style='max-width:100px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].user_name+"'>"+jar0[i].rows[j].user_name+"</td>";
						str0 += "<td style='max-width:80px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].comp_name+"'>"+jar0[i].rows[j].comp_name+"</td>";
						str0 += "<td style='max-width:80px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].ut_name+"'>"+jar0[i].rows[j].ut_name+"</td>";
						if('0'==jar0[i].rows[j].comp_status){
							if('0'==jar0[i].rows[j].del_flag){
								str0 += "<td style='color:green;'>"+i18Frame.getalready_enabled()+"</td>";
							}else if('1'==jar0[i].rows[j].del_flag){
								str0 += "<td style='color:red;'>"+i18Frame.getalready_disabled()+"</td>";
							}
						}else{
							str0 += "<td style='color:red;'>"+i18Frame.getalready_disabled()+"</td>";
						}
						str0 += "<td title='"+jar0[i].rows[j].user_phone+"'>"+jar0[i].rows[j].user_phone+"</td>" +
						"<td title='"+jar0[i].rows[j].user_tel+"'>"+jar0[i].rows[j].user_tel+"</td>";
						if(ut_power.indexOf(",wt5_5_3,")>-1){
							str0 += "<td class='f-14 td-manage'>";
							if(comp_key==jar0[i].rows[j].comp_key){
								str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].user_key+"' onClick='updUser(this)' title='"+i18Frame.getedit()+"'><i class='Hui-iconfont'>&#xe6df;</i></a>";
								if(login_user!=jar0[i].rows[j].user_key){
									if('0'==jar0[i].rows[j].del_flag){
										str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].user_key+"' onClick='disableUser(this)' title='"+i18Frame.getdisable()+"'><i class='Hui-iconfont'>&#xe631;</i></a>";
									}else if('1'==jar0[i].rows[j].del_flag){
										str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].user_key+"' onClick='enableUser(this)' title='"+i18Frame.getenable()+"'><i class='Hui-iconfont'>&#xe601;</i></a>";
									}
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

function addUser(){
	layer.open({
		type: 2,
		title: i18Frame.getadd(),
		shadeClose: true,
		maxmin: false,
		
		area:['530px','405px'],
		content: ['add_user1.jsp']
	});
}

function updUser(obj){
	cuser_key = obj.getAttribute("name");
	setUser_key(cuser_key);
	layer.open({
		type: 2,
		title: i18Frame.getedit(),
		shadeClose: true,
		maxmin: false,
		
		area:['530px','365px'],
		content: ['upd_user1.jsp']
	});
}

function enableUser(obj){
	user_key = obj.getAttribute("name");
	currentpage = getCurrentPage();
	layer.confirm(i18Frame.getdetermine_enable()+'?',{
		btn: [i18Frame.getok(),i18Frame.getcancel()],icon:3,title:i18Frame.getinfo(),closeBtn:1
	},function(){
		if(login_user==user_key){
			layer.alert(i18Frame.getdonot_enable_oneself()+'!',{icon:7,shade:0.3,title:i18Frame.getinfo()});
		}else{
			$.ajax({
				type: "POST",
				url: "../../../../user/enableUser",
				data:{"user_key":user_key},
				success:function(returnedData){
					var jo0 = eval("("+returnedData+")");
					if(jo0.check =="true"){
						layer.msg(i18Frame.getenable_success()+'!',{icon:1,shade:0.3,time:1000});
						flush(currentpage);
					}else{
						layer.msg(i18Frame.getenable_failed()+'!',{icon:2,shade:0.3,time:1000});
					}
				}
			});
		}
	},function(){
		
	});
}

function disableUser(obj){
	user_key = obj.getAttribute("name");
	currentpage = getCurrentPage();
	layer.confirm(i18Frame.getdetermine_disable()+'?',{
		btn: [i18Frame.getok(),i18Frame.getcancel()],icon:3,title:i18Frame.getinfo(),closeBtn:1
	},function(){
		if(login_user==user_key){
			layer.alert(i18Frame.getdonot_disable_oneself()+'!',{icon:7,shade:0.3,title:i18Frame.getinfo()});
		}else{
			$.ajax({
				type: "POST",
				url: "../../../../user/disableUser",
				data:{"user_key":user_key},
				success:function(returnedData){
					var jo0 = eval("("+returnedData+")");
					if(jo0.check =="true"){
						layer.msg(i18Frame.getdisable_success()+'!',{icon:1,shade:0.3,time:1000});
						flush(currentpage);
					}else{
						layer.msg(i18Frame.getdisable_failed()+'!',{icon:2,shade:0.3,time:1000});
					}
				}
			});
		}
	},function(){
		
	});
}

function setCurrentPage(currentpage1){
	currentpage=currentpage1;
}
function getCurrentPage(){
	return currentpage;
}
function setUser_key(cuser_key){
	user_key=cuser_key;
}
function getUser_key(){
	return user_key;
}
function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
function in_array(needle, haystack) {
	  var i = 0, n = haystack.length;

	  for (;i < n;++i)
	    if (haystack[i] == needle)
	      return true;

	  return false;
	}