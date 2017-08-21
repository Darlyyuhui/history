var index = parent.layer.getFrameIndex(window.name);
var record_key;
var ut_power;
var comp_key;
var authorize_flag;
var i18Frame = parent.parent.window.frames["i18nframe"];
$(function(){
	ut_power = $("#ut_power").val();
	comp_key = $("#comp_key").val();
	record_key = parent.getRecord_key();
	flush();
	flush1();
});

function flush(){
	$.ajax({
		type: "POST",
		url: "../../../authorize/getAuthorizeByKey",
		data: {
			"record_key": record_key,
			"comp_key":comp_key
		},
		success:function(returnedData){
			var jo0 = eval("("+returnedData+")");
			var str0 = "";//显示列表
			$("#authorize tbody").empty();
			str0 += "<tr class='text-c'>";
			str0 += "<td style='max-width:50px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jo0.authorize_name+"'><i class='Hui-iconfont' style='color:red;'>&#xe606;</i>"+jo0.authorize_name+"</td>";
			str0 += "<td>"+jo0.key_id+"</td>";
			str0 += "<td style='max-width:50px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jo0.start_date+"~"+jo0.end_date+"'>"+jo0.start_date+"~"+jo0.end_date+"</td>" +
			"<td style='max-width:50px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jo0.start_time+"~"+jo0.end_time+"'>"+jo0.start_time+"~"+jo0.end_time+"</td>";
			if("0"==jo0.authorize_review){
				str0 +="<td style='color:green;'>"+i18Frame.getno_audit()+"</td>";
			}else if("1"==jo0.authorize_review){
				str0 +="<td style='color:green;'>"+i18Frame.getaudit_pass()+"</td>";
			}else if("2"==jo0.authorize_review){
				str0 +="<td style='color:red;'>"+i18Frame.getpending_audit()+"</td>";
			}else{
				str0 +="<td style='color:gray;'>"+i18Frame.getaudit_no_pass()+"</td>";
			}
			authorize_flag = jo0.authorize_flag;
			if("1"==authorize_flag){
				str0 +="<td style='color:red;'>"+i18Frame.getalready_disabled()+"</td>";
			}else if("0"==authorize_flag){
				str0 +="<td style='color:green;'>"+i18Frame.getalready_enabled()+"</td>";
			}else if("2"==authorize_flag){
				str0 +="<td style='color:gray;'>"+i18Frame.gettimeout_shutdown()+"</td>";
			}else if("3"==authorize_flag){
				str0 +="<td style='color:gray;'>"+i18Frame.getpositive_closing()+"</td>";
			}
			str0 += "<td style='max-width:50px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jo0.authorize_time+"'>"+jo0.authorize_time+"</td>" +
			"<td style='max-width:50px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jo0.applied_name+"("+jo0.applied_id+")'>"+jo0.applied_name+"("+jo0.applied_id+")</td>";
			str0 += "<td>"+jo0.timeslice_day+"</td>";
			$("#authorize tbody").append(str0);
		}
	});
}

function flush1(){
	layer.load(2,{shade:0.3});
	$.ajax({
		type: "POST",
		url: "../../../slice/getAllSliceBy",
		data: {
			"record_key": record_key,
			"comp_key":comp_key
		},
		success: function(returnedData){
			var jar0 = eval("("+returnedData+")");
			var str0 = "";//显示列表
			$("#timeslice tbody").empty();
			for(var j=0;j<jar0.length;j++){
				str0 += "<tr class='text-c'>"+
				"<td>"+(j+1)+"</td>" +
				"<td style='max-width:50px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[j].slice_start_date+"~"+jar0[j].slice_end_date+"'>"+jar0[j].slice_start_date+"~"+jar0[j].slice_end_date+"</td>" +
				"<td style='max-width:50px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[j].slice_start_time+"~"+jar0[j].slice_end_time+"'>"+jar0[j].slice_start_time+"~"+jar0[j].slice_end_time+"</td>";
				if("0"==jar0[j].slice_flag){
					str0 +="<td style='color:green;'>"+i18Frame.getalready_enabled()+"</td>";
				}else if("1"==jar0[j].slice_flag){
					str0 +="<td style='color:red;'>"+i18Frame.getalready_disabled()+"</td>";
				}else if("2"==jar0[j].slice_flag){
					str0 +="<td style='color:gray;'>"+i18Frame.gettimeout_shutdown()+"</td>";
				}else if("3"==jar0[j].slice_flag){
					str0 +="<td style='color:gray;'>"+i18Frame.getpositive_closing()+"</td>";
				}
				if("0"==jar0[j].slice_status){
					str0 +="<td style='color:red;'>"+i18Frame.getwait_authorization()+"</td>";
				}else if("1"==jar0[j].slice_status){
					str0 +="<td style='color:green;'>"+i18Frame.getalready_authorized()+"</td>";
				}
				if(ut_power.indexOf(",wt1_2_3,")>-1){
					str0 +="<td class='f-14 td-manage'>";
					if("0"==authorize_flag){
						if("1"==jar0[j].slice_flag){
							str0 +="<a style='text-decoration:none' class='ml-5' name='"+jar0[j].slice_key+"' onClick='enableSliceBy(this)' title='"+i18Frame.getenable()+"'><i class='Hui-iconfont'>&#xe601;</i></a> ";
						}else if("0"==jar0[j].slice_flag){
							str0 +="<a style='text-decoration:none' class='ml-5' name='"+jar0[j].slice_key+"' onClick='disableSliceBy(this)' title='"+i18Frame.getdisable()+"'><i class='Hui-iconfont'>&#xe631;</i></a> ";
						}
					}
					if("1"==jar0[j].slice_flag||"0"==jar0[j].slice_flag){
						str0 +="<a style='text-decoration:none' class='ml-5' name='"+jar0[j].slice_key+"' onClick='closeSlice(this)' title='"+i18Frame.getpositive_closing()+"'><i class='Hui-iconfont'>&#xe60b;</i></a> ";
					}
				}
			}
			$("#timeslice tbody").append(str0);
			setTimeout(function(){
				layer.closeAll('loading');
			},300);
		}
	});
}

function enableSliceBy(obj){
	var slice_key = obj.getAttribute("name");
	layer.confirm(i18Frame.getdetermine_enable()+'?',{
		btn: [i18Frame.getok(),i18Frame.getcancel()],icon:3,title:i18Frame.getinfo(),closeBtn:1
	},function(){
		$.ajax({
			type: "POST",
			url: "../../../slice/enableSliceBy",
			data: {
				"slice_key": slice_key,
				"comp_key":comp_key
			},
			success: function(returnedData){
				var jo0 = eval("("+returnedData+")");
				if(jo0.check =="true"){
					layer.msg(i18Frame.getenable_success()+'!',{icon:1,shade:0.3,time:1000});
					flush1();
				}else if(jo0.check =="false"){
					layer.msg(i18Frame.getenable_failed()+','+i18Frame.gettask_closed()+'!',{icon:2,shade:0.3,time:1000});
					flush1();
				}else{
					layer.msg(i18Frame.getenable_failed()+','+i18Frame.gettask_no_exist()+'!',{icon:2,shade:0.3,time:1000});
					flush1();
				}
			}
		});
	},function(){
		
	});
};

function disableSliceBy(obj){
	var slice_key = obj.getAttribute("name");
	layer.confirm(i18Frame.getdetermine_disable()+'?',{
		btn: [i18Frame.getok(),i18Frame.getcancel()],icon:3,title:i18Frame.getinfo(),closeBtn:1
	},function(){
		$.ajax({
			type: "POST",
			url: "../../../slice/disableSliceBy",
			data: {
				"slice_key": slice_key,
				"comp_key":comp_key
			},
			success: function(returnedData){
				var jo0 = eval("("+returnedData+")");
				if(jo0.check =="true"){
					layer.msg(i18Frame.getdisable_success()+'!',{icon:1,shade:0.3,time:1000});
					flush1();
				}else if(jo0.check =="false"){
					layer.msg(i18Frame.getdisable_failed()+','+i18Frame.gettask_closed()+'!',{icon:2,shade:0.3,time:1000});
					flush1();
				}else{
					layer.msg(i18Frame.getdisable_failed()+','+i18Frame.gettask_no_exist()+'!',{icon:2,shade:0.3,time:1000});
					flush1();
				}
			}
		});
	},function(){
		
	});
};

function closeSlice(obj){
	var slice_key = obj.getAttribute("name");
	layer.confirm(i18Frame.getdetermine_close()+'?',{
		btn: [i18Frame.getok(),i18Frame.getcancel()],icon:3,title:i18Frame.getinfo(),closeBtn:1
	},function(){
		$.ajax({
			type: "POST",
			url: "../../../slice/closeSlice",
			data: {
				"slice_key": slice_key,
				"comp_key":comp_key
			},
			success: function(returnedData){
				var jo0 = eval("("+returnedData+")");
				if(jo0.check =="true"){
					layer.msg(i18Frame.getclose_success()+'!',{icon:1,shade:0.3,time:1000});
					flush1();
				}else if(jo0.check =="false"){
					layer.msg(i18Frame.getclose_failed()+','+i18Frame.gettask_closed()+'!',{icon:2,shade:0.3,time:1000});
					flush1();
				}else{
					layer.msg(i18Frame.getclose_failed()+','+i18Frame.gettask_no_exist()+'!',{icon:2,shade:0.3,time:1000});
					flush1();
				}
			}
		});
	},function(){
		
	});
};
