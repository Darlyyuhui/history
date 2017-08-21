var currentpage1=1;
var currentpage;
var record_key;
var crecord_key;
var now_time;
var user_name;
var user_id;
var ut_power;
var comp_key;
var start_date;
var end_date;
var key_value;
var indexLayer = null;
var i18Frame = parent.window.frames["i18nframe"];
$(function(){
	user_name = window.parent.$("#user_name").text();
	user_id = window.parent.$("#user_id").val();
	ut_power = $("#ut_power").val();
	comp_key = $("#comp_key").val();
	now_time = getnowtime();
	end.min=now_time;
	start.max = getendtime();
	$("#logmin").val(getnowtime());
	$("#logmax").val(getendtime());
	flush(currentpage1);
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
	var timeslice_flag = '2';
	var apply_flag = '1';
	var authorize_review = '2';
	var authorize_status = '0';
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
			url: "../../../authorize/getAuthorizesByPage",
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
						str0 += "<tr class='text-c'><td colspan='9' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
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
							str0 += "<td>"+jar0[i].rows[j].key_id+"</td>";
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
							str0 += "<td style='max-width:50px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].authorize_time+"'>"+jar0[i].rows[j].authorize_time+"</td>" +
							"<td style='max-width:50px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].applied_name+"("+jar0[i].rows[j].applied_id+")'>"+jar0[i].rows[j].applied_name+"("+jar0[i].rows[j].applied_id+")</td>";
							str0 +="<td class='f-14 td-manage'>";
							str0 +="<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].record_key+"' onClick='seeAuthorize(this)' title='"+i18Frame.getsee()+"'><i class='Hui-iconfont'>&#xe695;</i></a> ";
							if(ut_power.indexOf(",wt1_3_3,")>-1){
								if("2"==jar0[i].rows[j].authorize_review){
									str0 +="<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].record_key+"' onClick='refuseAuthorize(this)' title='"+i18Frame.getrefuse()+"'><i class='Hui-iconfont'>&#xe6dd;</i></a> ";
									str0 +="<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].record_key+"' onClick='adoptAuthorize(this)' title='"+i18Frame.getadopt()+"'><i class='Hui-iconfont'>&#xe6e1;</i></a> ";
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

function seeAuthorize(obj){
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

function refuseAuthorize(obj){
	record_key = obj.getAttribute("name");
	currentpage = getCurrentPage();
	layer.prompt({title: i18Frame.getrefusal_reason(), formType: 2,maxlength: 100}, function(text, index){
		if(100<text.length){
			layer.alert('0~100'+i18Frame.getlength(),{icon:2,shade:0.3,title:i18Frame.getinfo()});
		}else{
			$.ajax({
				type: "POST",
				url: "../../../authorize/refuseAuthorize",
				data: {
					"record_key": record_key,
					"reviewed_remark": text,
					"reviewed_id": user_id,
					"comp_key":comp_key
				},
				success: function(returnedData){
					var jo0 = eval("("+returnedData+")");
					if(jo0.check="true"){
						layer.close(index);
						layer.msg(i18Frame.getrefuse_success()+'!',{icon:1,shade:0.3,time:1000});
						$("input[type='checkbox']").prop("checked",false);
						flush(currentpage);
					}else{
						layer.close(index);
						$("input[type='checkbox']").prop("checked",false);
						flush(currentpage);
					}
				}
			});
		}
	});
}

function adoptAuthorize(obj){
	record_key = obj.getAttribute("name");
	currentpage = getCurrentPage();
	$.ajax({
		type: "POST",
		url: "../../../authorize/getAuthorizeByKey",
		data: {
			"record_key": record_key,
			"comp_key":comp_key
		},
		success:function(returnedData){
			var jo0 = eval("("+returnedData+")");
			start_date = jo0.start_date;
			end_date = jo0.end_date;
			key_value = jo0.key_id+",";
			if(0==jo0.authorize_flag&&2==jo0.authorize_review){
				$.ajax({
					type: "POST",
					url: "../../../authorizeassist/checkAuthorize",
					data: {
						"start_date":start_date,
						"end_date":end_date,
						"key_value":key_value,
						"comp_key":comp_key
					},
					success: function(returnedData){
						var jo0= eval("("+returnedData+")");
						if(jo0.check =="true"){
							$.ajax({
								type: "POST",
								url: "../../../authorize/adoptAuthorize",
								data: {
									"record_key": record_key,
									"reviewed_id": user_id,
									"comp_key":comp_key
								},
								success: function(returnedData){
									var jo0= eval("("+returnedData+")");
									layer.msg(i18Frame.getaudit_success()+'!',{icon:1,shade:0.3,time:1000});
									$("input[type='checkbox']").prop("checked",false);
									flush(currentpage);
								}
							});
						}else{
							layer.open({
								type: 2,
								title: i18Frame.getconflict_list(),
								shadeClose: false,
								maxmin: false,
								area:['800px','485px'],
								content: ['conflict.jsp']
							});
						}
					}
				});
			}
		}
	});
}

function Authorize(){
	$.ajax({
		type: "POST",
		url: "../../../authorize/adoptAuthorize",
		data: {
			"record_key": record_key,
			"reviewed_id": user_id,
			"comp_key":comp_key
		},
		success: function(returnedData){
			var jo0= eval("("+returnedData+")");
			layer.msg(i18Frame.getaudit_success()+'!',{icon:1,shade:0.3,time:1000});
			$("input[type='checkbox']").prop("checked",false);
			flush(currentpage);
		}
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
function getCompKey(){
	return comp_key;
}

function getKeyValue(){
	return key_value;
}

function getStartDate(){
	return start_date;
}

function getEndDate(){
	return end_date;
}