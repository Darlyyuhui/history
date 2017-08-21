var currentpage1=1;
var currentpage;
var now_time;
var recordcount;
var ut_power;
var comp_key;
var i18Frame = parent.window.frames["i18nframe"];
$(function(){
	ut_power = $("#ut_power").val();
	comp_key = $("#comp_key").val();
	now_time = getnowtime();
	$("#logmin").val(getfirsttime(now_time));
	$("#logmax").val(getnowtime());
	flush(currentpage1);
});

function flush(page){
	setCurrentPage(page);
	var start_time = $("#logmin").val();
	var end_time = $("#logmax").val();
	var user_like = trim($("#user_like").val());
	var key_id = trim($("#key_id").val());
	var lock_id = trim($("#lock_id").val());
	document.getElementById("user_like").focus();
	if(""==start_time){
		layer.msg(i18Frame.getchoose_start_date()+'!',{icon:7,shade:0.3,time:1000});
	}else if(""==end_time){
		layer.msg(i18Frame.getchoose_end_date()+'!',{icon:7,shade:0.3,time:1000});
	}else if(start_time>end_time){
		layer.msg(i18Frame.getend_date_greater_start_date(),{icon:7,shade:0.3,time:1000});
	}else{
		layer.load(2,{shade:0.3});
		$.ajax({
			type: "POST",
			url: "../../../open/getOpenRecordByPage",
			data: {
				"currentpage": currentpage,
				"start_time": start_time,
				"end_time": end_time,
				"user_like": user_like,
				"key_id": key_id,
				"lock_id": lock_id,
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
						str0 += "<tr class='text-c'><td colspan='7' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
						str3 +=i18Frame.getdisplay()+" 0 "+i18Frame.getto()+" "+jar0[i].recordCount+" ，"+i18Frame.gettotal()+" "+jar0[i].recordCount+" "+i18Frame.getrow1();
						str1 ="<span><a class='paginate_current'>1</a></span>";
					}else{
						for(var j=0;j<jar0[i].rows.length;j++){
							str0 += "<tr class='text-c'>";
							str0 += "<td>"+(1+jar0[i].pageSize*(jar0[i].currentPage-1)+j)+"</td>"+
							"<td title='"+jar0[i].rows[j].key_id+"'>"+jar0[i].rows[j].key_id+"</td>" +
							"<td style='max-width:90px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].lock_name+"("+jar0[i].rows[j].lock_id+")'>"+jar0[i].rows[j].lock_name+"("+jar0[i].rows[j].lock_id+")</td>" +
							"<td style='max-width:90px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].unit_name+"'>"+jar0[i].rows[j].unit_name+"</td>" +
							"<td style='max-width:90px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].unit_location+"'>"+jar0[i].rows[j].unit_location+"</td>" +
							"<td title='"+jar0[i].rows[j].user_name+"("+jar0[i].rows[j].user_id+")'>"+jar0[i].rows[j].user_name+"("+jar0[i].rows[j].user_id+")</td>" +
							"<td title='"+jar0[i].rows[j].open_time+"'>"+jar0[i].rows[j].open_time+"</td>";
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

function ExportExcel(){
	flush(currentpage);
	if(0==recordcount){
		layer.alert(i18Frame.getno_match_condition_record()+'!',{icon:7,title:i18Frame.getinfo(),closeBtn:1});
	}else{
		layer.confirm(i18Frame.getdetermine_export()+'?',{
			btn: [i18Frame.getok(),i18Frame.getcancel()],icon:3,title:i18Frame.getinfo(),closeBtn:1
		},function(index1){
			var start_time = $("#logmin").val();
			var end_time = $("#logmax").val();
			var user_like = trim($("#user_like").val());
			var key_id = trim($("#key_id").val());
			var lock_id = trim($("#lock_id").val());
			
			$("#form_start").val(start_time);
			$("#form_end").val(end_time);
			$("#form_user").val(user_like);
			$("#form_key").val(key_id);
			$("#form_lock").val(lock_id);
			var url="./../../../export/exportOpenRecord"; 
			$("#hiddenForm").attr("action",url); 
			$("#hiddenForm").submit();
			layer.close(index1);
		},function(){
			
		});
	}
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

function getfirsttime(str){
	var tdate1 = str.split("-");
	var date = new Date(tdate1[0],tdate1[1]-1,tdate1[2]);
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
function setCurrentPage(currentpage1){
	currentpage=currentpage1;
}
function getCurrentPage(){
	return currentpage;
}
function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}