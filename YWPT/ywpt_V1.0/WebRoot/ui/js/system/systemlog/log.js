var currentpage1=1;
var currentpage;
var ut_power;
$(function(){
	ut_power = $("#ut_power").val();
	flush(currentpage1);
});

function flush(page){
	setCurrentPage(page);
	var start_time = $("#logmin").val();
	var end_time = $("#logmax").val();
	var user_name = trim($("#user_name").val());
	document.getElementById("user_name").focus();
	layer.load(2,{shade:0.3});
	$.ajax({
		type: "POST",
		url: "../../../log/getLogByPage",
		data: {
			"currentpage": currentpage,
			"start_time": start_time,
			"end_time": end_time,
			"user_name": user_name,
			"log_type": 0
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
					if(ut_power.indexOf(",t4_9_2,")>-1){
						str0 += "<tr class='text-c'><td colspan='6' class='null' align='center'>无数据!</td></tr>";
					}else{
						str0 += "<tr class='text-c'><td colspan='5' class='null' align='center'>无数据!</td></tr>";
					}
					str3 +="显示 0 到 "+jar0[i].recordCount+" ，共 "+jar0[i].recordCount+" 条";
					str1 ="<span><a class='paginate_current'>1</a></span>";
				}else{
					for(var j=0;j<jar0[i].rows.length;j++){
						str0 += "<tr class='text-c'>";
						if(ut_power.indexOf(",t4_9_2,")>-1){
							str0 += "<td><input type='checkbox' value='"+jar0[i].rows[j].log_key+"' name='cbox'/></td>";
						}
						str0 += "<td>"+(1+jar0[i].pageSize*(jar0[i].currentPage-1)+j)+"</td>"+
						"<td title='"+jar0[i].rows[j].log_opt+"'>"+jar0[i].rows[j].log_opt+"</td>" +
						"<td style='max-width:90px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].user_name+"("+jar0[i].rows[j].user_id+")'>"+jar0[i].rows[j].user_name+"("+jar0[i].rows[j].user_id+")</td>" +
						"<td title='"+jar0[i].rows[j].log_time+"'>"+jar0[i].rows[j].log_time+"</td>" +
						"<td title='"+jar0[i].rows[j].log_ip+"'>"+jar0[i].rows[j].log_ip+"</td>";
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
		layer.alert('请选择要删除的日志记录!',{icon:7,title:'提示',closeBtn:1});
	}else{
		layer.confirm('确定删除?',{
			btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
		},function(){
			$.ajax({
				type: "POST",
				url: "../../../log/delMoreLog",
				data: {
					"chk_value[]":chk_value
				},
				success:function(returnedData){
					var jo0= eval("("+returnedData+")");
					if(jo0.check=="true"){
						layer.msg('删除成功!',{icon:1,shade:0.3,time:1000});
						$("input[type='checkbox']").prop("checked",false);
						flush(currentpage);
					}else{
						layer.msg('删除失败!',{icon:2,shade:0.3,time:1000});
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
function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}