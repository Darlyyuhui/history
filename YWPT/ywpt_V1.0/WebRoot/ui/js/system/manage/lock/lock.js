var currentpage1=1;
var currentpage;
var lock_key;
var clock_key;
var recordcount;
var ut_power;
var comp_key;
var indexLayer = null;
var i18Frame = parent.window.frames["i18nframe"];
$(document).ready(function (){
	ut_power = $("#ut_power").val();
	comp_key = $("#comp_key").val();
	flush(currentpage1);
	$(window).resize(function(){  
		if(null != indexLayer){
			layer.full(indexLayer);
		}
	});
});

function flush(page){
	setCurrentPage(page);
	var lock_like = trim($("#lock_like").val());
	document.getElementById("lock_like").focus();
	var unit_name = trim($("#unit_name").val());
	layer.load(2,{shade:0.3});
	$.ajax({
		type: "POST",
		url: "../../../../lock/getLockByPage",
		data: {
			"currentpage":currentpage,
			"unit_name": unit_name,
			"lock_like": lock_like,
			"comp_key": comp_key
		},
		success: function(returnedData){
			var jar0 = eval("("+returnedData+")");
			var str0 = "";//显示列表
			var str1;//显示页码
			var str3 = "";//显示当前为第几条到第几条数据
			$("#pagin").empty();
			$(".table tbody").empty();
			$("#page_info").empty();
			for(var i=0;i<jar0.length;i++){
				if(jar0[i].rows.length==0){
					if(ut_power.indexOf(",wt5_1_3,")>-1){
						str0 += "<tr class='text-c'><td colspan='9' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}else{
						str0 += "<tr class='text-c'><td colspan='8' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}
					str3 +=i18Frame.getdisplay()+" 0 "+i18Frame.getto()+" "+jar0[i].recordCount+" ，"+i18Frame.gettotal()+" "+jar0[i].recordCount+" "+i18Frame.getrow1();
					str1 ="<span><a class='paginate_current'>1</a></span>";
				}else{
					for(var j=0;j<jar0[i].rows.length;j++){
						if(ut_power.indexOf(",wt5_1_3,")>-1){
							str0 += "<tr class='text-c' style='cursor:pointer;' name='"+jar0[i].rows[j].lock_key+"' ondblclick='updLock(this)'>";
							/*str0 += "<td><input type='checkbox' value='"+jar0[i].rows[j].lock_key+"' name='cbox'/></td>";*/
						}else{
							str0 += "<tr class='text-c'>";
						}
						str0 += "<td>"+(1+jar0[i].pageSize*(jar0[i].currentPage-1)+j)+"</td>"+
						"<td title='"+jar0[i].rows[j].lock_id+"'>"+jar0[i].rows[j].lock_id+"</td>" +
						"<td style='max-width:70px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].lock_name+"'>"+jar0[i].rows[j].lock_name+"</td>" +
						"<td style='max-width:70px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].unit_name+"'>"+jar0[i].rows[j].unit_name+"</td>";
						if('0'==jar0[i].rows[j].del_flag){
							str0 += "<td style='color:green;'>"+i18Frame.getalready_enabled()+"</td>";
						}else if('1'==jar0[i].rows[j].del_flag){
							str0 += "<td style='color:red;'>"+i18Frame.getalready_disabled()+"</td>";
						}
						str0 += "<td style='max-width:70px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].lock_model+"'>"+jar0[i].rows[j].lock_model+"</td>" +
						"<td>"+jar0[i].rows[j].lock_open_num+"</td>" +
						"<td style='max-width:70px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].lock_remark+"'>"+jar0[i].rows[j].lock_remark+"</td>";
						if(ut_power.indexOf(",wt5_1_3,")>-1){
							str0 += "<td class='f-14 td-manage'>";
							str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].lock_key+"' onClick='updLock(this)' title='"+i18Frame.getedit()+"'><i class='Hui-iconfont'>&#xe6df;</i></a> ";
							if('0'==jar0[i].rows[j].del_flag){
								str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].lock_key+"' onClick='disableLock(this)' title='"+i18Frame.getdisable()+"'><i class='Hui-iconfont'>&#xe631;</i></a>";
							}else if('1'==jar0[i].rows[j].del_flag){
								str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].lock_key+"' onClick='enableLock(this)' title='"+i18Frame.getenable()+"'><i class='Hui-iconfont'>&#xe601;</i></a>";
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
				recordcount = jar0[i].recordCount;
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

function addLock(){
	layer.open({
		type: 2,
		title: i18Frame.getadd(),
		shadeClose: true,
		maxmin: false,
		
		area:['800px','585px'],
		content: ['add_lock.jsp']
	});
}

function updLock(obj){
	clock_key = obj.getAttribute("name");
	setLock_key(clock_key);
	layer.open({
		type: 2,
		title: i18Frame.getedit(),
		shadeClose: true,
		maxmin: false,
		
		area:['800px','585px'],
		content: ['upd_lock.jsp']
	});
}

function enableLock(obj){
	lock_key = obj.getAttribute("name");
	currentpage = getCurrentPage();
	layer.confirm(i18Frame.getdetermine_enable()+'?',{
		btn: [i18Frame.getok(),i18Frame.getcancel()],icon:3,title:i18Frame.getinfo(),closeBtn:1
	},function(){
		$.ajax({
			type: "POST",
			url: "../../../../lock/enableLockByKey",
			data:{"lock_key":lock_key},
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
	},function(){
		
	});
}

function disableLock(obj){
	lock_key = obj.getAttribute("name");
	currentpage = getCurrentPage();
	layer.confirm(i18Frame.getdetermine_disable()+'?',{
		btn: [i18Frame.getok(),i18Frame.getcancel()],icon:3,title:i18Frame.getinfo(),closeBtn:1
	},function(){
		$.ajax({
			type: "POST",
			url: "../../../../lock/disableLockByKey",
			data:{"lock_key":lock_key},
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
	},function(){
		
	});
}

function ImportExcel(){
	indexLayer = layer.open({
		type: 2,
		title: i18Frame.getimportexcel(),
		shadeClose: true,
		maxmin: false,
		
		area:['530px','485px'],
		content: ['import.jsp'],
	    success: function(index){
	    	
	    },end: function(){
	    	indexLayer = null;
	    }
	});
	layer.full(indexLayer);
}

function ExportExcel(){
	flush(currentpage);
	if(0==recordcount){
		layer.alert(i18Frame.getno_lock_info()+'!',{icon:7,title:i18Frame.getinfo(),closeBtn:1});
	}else{
		layer.confirm(i18Frame.getdetermine_export()+'?',{
			btn: [i18Frame.getok(),i18Frame.getcancel()],icon:3,title:i18Frame.getinfo(),closeBtn:1
		},function(index1){
			var lock_like = trim($("#lock_like").val());
			var unit_name = trim($("#unit_name").val());
			
			$("#form_unit").val(unit_name);
			$("#form_like").val(lock_like);
			var url="./../../../../export/exportLock"; 
			$("#hiddenForm").attr("action",url); 
			$("#hiddenForm").submit();
			layer.close(index1);
		},function(){
			
		});
	}
}

function setCurrentPage(currentpage1){
	currentpage=currentpage1;
}
function getCurrentPage(){
	return currentpage;
}
function setLock_key(clock_key){
	lock_key=clock_key;
}
function getLock_key(){
	return lock_key;
}
function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}