var currentpage1=1;
var currentpage;
var record_key;
var crecord_key;
var now_time;
var user_name;
var user_id;
var ut_power;
var comp_key;
var indexLayer = null;
var i18Frame = parent.window.frames["i18nframe"];
$(function(){
	user_name = window.parent.$("#user_name").text();
	user_id = window.parent.$("#user_id").val();
	ut_power = $("#ut_power").val();
	comp_key = $("#comp_key").val();
	$.ajax({
		type: "POST",
		url: "../../../unit_type/getAllUnit_type",
		data: {
			"comp_key":comp_key
		},
		success: function(returnedData){
			var jar0 = eval("("+returnedData+")");
			var str = "<div class='model-select-box' id='timeslice_flag' style='width: 147.5px;'>";
			str +="<div class='model-select-text' id='timeslice_select' data-value='2'>"+i18Frame.getall()+"</div>"+ 
			"<ul class='model-select-option'>";
			str +="<li data-option='2' class='seleced' title='"+i18Frame.getall()+"'>"+i18Frame.getall()+"</li>";
			str +="<li data-option='0' title='"+i18Frame.getyes()+"'>"+i18Frame.getyes()+"</li>";
			str +="<li data-option='1' title='"+i18Frame.getno()+"'>"+i18Frame.getno()+"</li>";
			str +="</ul></div>";
			$(".timeslice_flag").empty();
			$(".timeslice_flag").append(str);
			var str1 = "<div class='model-select-box' id='apply_flag' style='width: 147.5px;'>";
			str1 +="<div class='model-select-text' id='apply_select' data-value='2'>"+i18Frame.getall()+"</div>"+ 
			"<ul class='model-select-option'>";
			str1 +="<li data-option='2' class='seleced' title='"+i18Frame.getall()+"'>"+i18Frame.getall()+"</li>";
			str1 +="<li data-option='0' title='"+i18Frame.getauthrelease()+"'>"+i18Frame.getauthrelease()+"</li>";
			str1 +="<li data-option='1' title='"+i18Frame.getauthapply()+"'>"+i18Frame.getauthapply()+"</li>";
			str1 +="</ul></div>";
			$(".apply_flag").empty();
			$(".apply_flag").append(str1);
			var str2 = "<div class='model-select-box' id='authorize_review' style='width: 147.5px;'>";
			str2 +="<div class='model-select-text' id='review_select' data-value='0'>"+i18Frame.getall()+"</div>"+ 
			"<ul class='model-select-option'>";
			str2 +="<li data-option='0' class='seleced' title='"+i18Frame.getall()+"'>"+i18Frame.getall()+"</li>";
			str2 +="<li data-option='2' title='"+i18Frame.getpending_audit()+"'>"+i18Frame.getpending_audit()+"</li>";
			str2 +="<li data-option='1' title='"+i18Frame.getaudit_pass()+"'>"+i18Frame.getaudit_pass()+"</li>";
			str2 +="<li data-option='3' title='"+i18Frame.getaudit_no_pass()+"'>"+i18Frame.getaudit_no_pass()+"</li>";
			str2 +="</ul></div>";
			$(".authorize_review").empty();
			$(".authorize_review").append(str2);
			var str3 = "<div class='model-select-box' id='authorize_status' style='width: 147.5px;'>";
			str3 +="<div class='model-select-text' id='status_select' data-value='4'>"+i18Frame.getall()+"</div>"+ 
			"<ul class='model-select-option'>";
			str3 +="<li data-option='4' class='seleced' title='"+i18Frame.getall()+"'>"+i18Frame.getall()+"</li>";
			str3 +="<li data-option='0' title='"+i18Frame.getalready_enabled()+"'>"+i18Frame.getalready_enabled()+"</li>";
			str3 +="<li data-option='1' title='"+i18Frame.getalready_disabled()+"'>"+i18Frame.getalready_disabled()+"</li>";
			str3 +="<li data-option='2' title='"+i18Frame.gettimeout_shutdown()+"'>"+i18Frame.gettimeout_shutdown()+"</li>";
			str3 +="<li data-option='3' title='"+i18Frame.getpositive_closing()+"'>"+i18Frame.getpositive_closing()+"</li>";
			str3 +="</ul></div>";
			$(".authorize_status").empty();
			$(".authorize_status").append(str3);
			selectModel();
			timesliceChange();
			applyChange();
			reviewChange();
			statusChange();
			now_time = getnowtime();
			end.min=now_time;
			start.max = getendtime();
			$("#logmin").val(getnowtime());
			$("#logmax").val(getendtime());
			flush(currentpage1);
		}
	});
	$(window).resize(function(){  
		if(null != indexLayer){
			layer.full(indexLayer);
		}
	});
});

function flush(page){
	layer.load(2,{shade:0.3});
	setCurrentPage(page);
	var start_date = $("#logmin").val();
	var end_date = $("#logmax").val();
	var timeslice_flag = $("#timeslice_select").attr("data-value");
	var apply_flag = $("#apply_select").attr("data-value");
	var authorize_review = $("#review_select").attr("data-value");
	var authorize_status = $("#status_select").attr("data-value");
	var authorize_name = trim($("#authorize_name").val());
	document.getElementById("authorize_name").focus();
	if(""==start_date){
		layer.closeAll('loading');
		layer.tips(i18Frame.getchoose_effective_start_time(),'#logmin',{
			tips:3,time:2000
		});
	}else if(""==end_date){
		layer.closeAll('loading');
		layer.tips(i18Frame.getchoose_effective_end_time(),'#logmax',{
			tips:3,time:2000
		});
	}else{
		$.ajax({
			type: "POST",
			url: "../../../lockauth/getLockAuthsByPage",
			data: {
				"currentpage": currentpage,
				"start_date": start_date,
				"end_date": end_date,
				"timeslice_flag": timeslice_flag,
				"apply_flag": apply_flag,
				"authorize_review": authorize_review,
				"authorize_status": authorize_status,
				"authorize_name": authorize_name,
				"comp_key":comp_key
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
						str0 += "<tr class='text-c'><td colspan='10' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
						str3 +=i18Frame.getdisplay()+" 0 "+i18Frame.getto()+" "+jar0[i].recordCount+" ，"+i18Frame.gettotal()+" "+jar0[i].recordCount+" "+i18Frame.getrow1();
						str1 ="<span><a class='paginate_current'>1</a></span>";
					}else{
						for(var j=0;j<jar0[i].rows.length;j++){
							str0 += "<tr class='text-c' style='cursor:pointer;' name='"+jar0[i].rows[j].record_key+"' ondblclick='seeAuthorize(this)'>";
							str0 += "<td>"+(1+jar0[i].pageSize*(jar0[i].currentPage-1)+j)+"</td>";
							if("0"==jar0[i].rows[j].timeslice_flag){
								str0 += "<td style='max-width:50px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].authorize_name+"'><i class='Hui-iconfont' style='color:red;'>&#xe606;</i>"+jar0[i].rows[j].authorize_name+"</td>";
							}else{
								str0 += "<td style='max-width:50px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].authorize_name+"'>"+jar0[i].rows[j].authorize_name+"</td>";
							}
							str0 += "<td style='max-width:50px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].user_name+"("+jar0[i].rows[j].user_id+")'>"+jar0[i].rows[j].user_name+"("+jar0[i].rows[j].user_id+")</td>";
							str0 += "<td style='max-width:50px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].start_date+"~"+jar0[i].rows[j].end_date+"'>"+jar0[i].rows[j].start_date+"~"+jar0[i].rows[j].end_date+"</td>" +
							"<td style='max-width:50px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].start_time+"~"+jar0[i].rows[j].end_time+"'>"+jar0[i].rows[j].start_time+"~"+jar0[i].rows[j].end_time+"</td>";
							if("0"==jar0[i].rows[j].authorize_review){
								str0 +="<td style='color:green;'>"+i18Frame.getno_audit()+"</td>";
							}else if("1"==jar0[i].rows[j].authorize_review){
								str0 +="<td style='color:green;'>"+i18Frame.getaudit_pass()+"</td>";
							}else if("2"==jar0[i].rows[j].authorize_review){
								str0 +="<td style='color:red;'>"+i18Frame.getpending_audit()+"</td>";
							}else{
								str0 +="<td style='color:gray;'>"+i18Frame.getaudit_no_pass()+"</td>";
							}
							if("1"==jar0[i].rows[j].authorize_flag){
								str0 +="<td style='color:red;'>"+i18Frame.getalready_disabled()+"</td>";
							}else if("0"==jar0[i].rows[j].authorize_flag){
								str0 +="<td style='color:green;'>"+i18Frame.getalready_enabled()+"</td>";
							}else if("2"==jar0[i].rows[j].authorize_flag){
								str0 +="<td style='color:gray;'>"+i18Frame.gettimeout_shutdown()+"</td>";
							}else if("3"==jar0[i].rows[j].authorize_flag){
								str0 +="<td style='color:gray;'>"+i18Frame.getpositive_closing()+"</td>";
							}
							str0 += "<td style='max-width:50px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].authorize_time+"'>"+jar0[i].rows[j].authorize_time+"</td>" +
							"<td style='max-width:50px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].applied_name+"("+jar0[i].rows[j].applied_id+")'>"+jar0[i].rows[j].applied_name+"("+jar0[i].rows[j].applied_id+")</td>";
							str0 +="<td class='f-14 td-manage'>";
							if(ut_power.indexOf(",wt6_2_3,")>-1){
								if("0"==jar0[i].rows[j].authorize_review||"1"==jar0[i].rows[j].authorize_review){//无需审核或审核通过即可对任务进行启用停用操作
									if("1"==jar0[i].rows[j].authorize_flag){
										str0 +="<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].record_key+"' onClick='enableAuthorize(this)' title='"+i18Frame.getenable()+"'><i class='Hui-iconfont'>&#xe601;</i></a> ";
									}else if("0"==jar0[i].rows[j].authorize_flag){
										str0 +="<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].record_key+"' onClick='disableAuthorize(this)' title='"+i18Frame.getdisable()+"'><i class='Hui-iconfont'>&#xe631;</i></a> ";
									}
								}
							}
							str0 +="<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].record_key+"' onClick='seeAuthorize(this)' title='"+i18Frame.getsee()+"'><i class='Hui-iconfont'>&#xe695;</i></a> ";
							if("0"==jar0[i].rows[j].timeslice_flag){
								if("0"==jar0[i].rows[j].authorize_review||"1"==jar0[i].rows[j].authorize_review){//无需审核或审核通过才存在时间片任务
									str0 +="<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].record_key+"' onClick='seeTimeSlice(this)' title='"+i18Frame.getsee_time_slice()+"'><i class='Hui-iconfont'>&#xe606;</i></a> ";
								}
							}
							if(ut_power.indexOf(",wt6_2_3,")>-1){
								if("1"==jar0[i].rows[j].authorize_flag||"0"==jar0[i].rows[j].authorize_flag){
									str0 +="<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].record_key+"' onClick='closeAuthorize(this)' title='"+i18Frame.getpositive_closing()+"'><i class='Hui-iconfont'>&#xe60b;</i></a> ";
								}	
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


function enableAuthorize(obj){
	crecord_key = obj.getAttribute("name");
	currentpage = getCurrentPage();
	layer.confirm(i18Frame.getdetermine_enable()+'?',{
		btn: [i18Frame.getok(),i18Frame.getcancel()],icon:3,title:i18Frame.getinfo(),closeBtn:1
	},function(){
		$.ajax({
			type: "POST",
			url: "../../../lockauth/enableLockAuthBy",
			data: {
				"record_key": crecord_key,
				"comp_key":comp_key
			},
			success: function(returnedData){
				var jo0 = eval("("+returnedData+")");
				if(jo0.check =="true"){
					layer.msg(i18Frame.getenable_success()+'!',{icon:1,shade:0.3,time:1000});
					flush(currentpage);
				}else if(jo0.check =="false"){
					layer.msg(i18Frame.getenable_failed()+','+i18Frame.gettask_closed()+'!',{icon:2,shade:0.3,time:1000});
					flush(currentpage);
				}else{
					layer.msg(i18Frame.getenable_failed()+','+i18Frame.gettask_no_exist()+'!',{icon:2,shade:0.3,time:1000});
					flush(currentpage);
				}
			}
		});
	},function(){
		
	});
};

function disableAuthorize(obj){
	crecord_key = obj.getAttribute("name");
	currentpage = getCurrentPage();
	layer.confirm(i18Frame.getdetermine_disable()+'?',{
		btn: [i18Frame.getok(),i18Frame.getcancel()],icon:3,title:i18Frame.getinfo(),closeBtn:1
	},function(){
		$.ajax({
			type: "POST",
			url: "../../../lockauth/disableLockAuthBy",
			data: {
				"record_key": crecord_key,
				"comp_key":comp_key
			},
			success: function(returnedData){
				var jo0 = eval("("+returnedData+")");
				if(jo0.check =="true"){
					layer.msg(i18Frame.getdisable_success()+'!',{icon:1,shade:0.3,time:1000});
					flush(currentpage);
				}else if(jo0.check =="false"){
					layer.msg(i18Frame.getdisable_failed()+','+i18Frame.gettask_closed()+'!',{icon:2,shade:0.3,time:1000});
					flush(currentpage);
				}else{
					layer.msg(i18Frame.getdisable_failed()+','+i18Frame.gettask_no_exist()+'!',{icon:2,shade:0.3,time:1000});
					flush(currentpage);
				}
			}
		});
	},function(){
		
	});
};

function closeAuthorize(obj){
	crecord_key = obj.getAttribute("name");
	currentpage = getCurrentPage();
	layer.confirm(i18Frame.getdetermine_close()+'?',{
		btn: [i18Frame.getok(),i18Frame.getcancel()],icon:3,title:i18Frame.getinfo(),closeBtn:1
	},function(){
		$.ajax({
			type: "POST",
			url: "../../../lockauth/closeLockAuthBy",
			data: {
				"record_key": crecord_key,
				"comp_key":comp_key
			},
			success: function(returnedData){
				var jo0 = eval("("+returnedData+")");
				if(jo0.check =="true"){
					layer.msg(i18Frame.getclose_success()+'!',{icon:1,shade:0.3,time:1000});
					flush(currentpage);
				}else if(jo0.check =="false"){
					layer.msg(i18Frame.getclose_failed()+','+i18Frame.gettask_closed()+'!',{icon:2,shade:0.3,time:1000});
					flush(currentpage);
				}else{
					layer.msg(i18Frame.getclose_failed()+','+i18Frame.gettask_no_exist()+'!',{icon:2,shade:0.3,time:1000});
					flush(currentpage);
				}
			}
		});
	},function(){
		
	});
};

function seeAuthorize(obj){
	crecord_key = obj.getAttribute("name");
	setRecord_key(crecord_key);
	currentpage = getCurrentPage();
	$.ajax({
		type: "POST",
		url: "../../../lockauth/getLockAuthCount",
		data: {
			"record_key": crecord_key,
			"comp_key":comp_key
		},
		success: function(returnedData){
			var jo0 = eval("("+returnedData+")");
			if(jo0.check =="1"){
				indexLayer = layer.open({
					type: 2,
					title: i18Frame.getsee(),
					shadeClose: true,
					maxmin: false,
					
					area:['530px','485px'],
					content: ['authorize_record.jsp'],
				    success: function(index){
				    	
				    },end: function(){
				    	indexLayer = null;
				    }
				});
				layer.full(indexLayer);
			}else{
				layer.msg(i18Frame.getsee_failed()+','+i18Frame.getrecord_no_exist()+'!',{icon:2,shade:0.3,time:1000});
				$("input[type='checkbox']").prop("checked",false);
				flush(currentpage);
			}
		}
	});
}

function seeTimeSlice(obj){
	crecord_key = obj.getAttribute("name");
	setRecord_key(crecord_key);
	currentpage = getCurrentPage();
	$.ajax({
		type: "POST",
		url: "../../../authorize/getAuthorizeCount",
		data: {
			"record_key": crecord_key,
			"comp_key":comp_key
		},
		success: function(returnedData){
			var jo0 = eval("("+returnedData+")");
			if(jo0.check =="1"){
				indexLayer = layer.open({
					type: 2,
					title: i18Frame.getsee_time_slice(),
					shadeClose: true,
					maxmin: false,
					
					area:['530px','485px'],
					content: ['timeslicetaskdetail.jsp'],
				    success: function(index){
				    	
				    },end: function(){
				    	indexLayer = null;
				    }
				});
				layer.full(indexLayer);
			}else{
				layer.msg(i18Frame.getsee_failed()+','+i18Frame.getrecord_no_exist()+'!',{icon:2,shade:0.3,time:1000});
				$("input[type='checkbox']").prop("checked",false);
				flush(currentpage);
			}
		}
	});
}

function timesliceChange(){
	var $box = $('#timeslice_flag');
	var $option = $('ul.model-select-option', $box);
	$option.find('li').mousedown(function(){
		flush(currentpage1);
	});
}
function applyChange(){
	var $box = $('#apply_flag');
	var $option = $('ul.model-select-option', $box);
	$option.find('li').mousedown(function(){
		flush(currentpage1);
	});
}
function reviewChange(){
	var $box = $('#authorize_review');
	var $option = $('ul.model-select-option', $box);
	$option.find('li').mousedown(function(){
		flush(currentpage1);
	});
}
function statusChange(){
	var $box = $('#authorize_status');
	var $option = $('ul.model-select-option', $box);
	$option.find('li').mousedown(function(){
		flush(currentpage1);
	});
}

function getnowtime(){
	var date = new Date();
	var seperator1 = "-";
	var seperator2 = ":";
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	var hours = date.getHours();
	var minutes = date.getMinutes();
	var seconds = date.getSeconds();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
	return currentdate;
}

function getfirsttime(){
	var date = new Date();
	var seperator1 = "-";
	var seperator2 = ":";
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	var hours = date.getHours();
	var minutes = date.getMinutes();
	var seconds = date.getSeconds();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	strDate = "01";
	var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
	return currentdate;
}

function getendtime(){
	var date = new Date();
	var seperator1 = "-";
	var seperator2 = ":";
	date.setMonth(date.getMonth()+1);
	date.setDate(0);
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 1 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
	return currentdate;
}

function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

function setCurrentPage(currentpage1){
	currentpage=currentpage1;
}
function getCurrentPage(){
	return currentpage;
}
function setRecord_key(crecord_key){
	record_key=crecord_key;
}
function getRecord_key(){
	return record_key;
}
