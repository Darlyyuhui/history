var currentpage1=1;
var currentpage;
var comp_key;
var ccomp_key;
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
	var comp_name=trim($(".input-text").val());
	document.getElementById("comp_name").focus();
	$.ajax({
		type: "POST",
		url: "../../../../company/getCompanyByPage",
		data: {
			"currentpage": currentpage,
			"comp_name": comp_name
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
					if(ut_power.indexOf(",wt5_12_3,")>-1){
						str0 += "<tr class='text-c'><td colspan='6' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}else{
						str0 += "<tr class='text-c'><td colspan='5' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}
					str3 +=i18Frame.getdisplay()+" 0 "+i18Frame.getto()+" "+jar0[i].recordCount+" ，"+i18Frame.gettotal()+" "+jar0[i].recordCount+" "+i18Frame.getrow1();
					str1 ="<span><a class='paginate_current'>1</a></span>";
				}else{
					for(var j=0;j<jar0[i].rows.length;j++){
						if(ut_power.indexOf(",wt5_12_3,")>-1){
							if(0==jar0[i].rows[j].apply_key){
								str0 += "<tr class='text-c' style='cursor:pointer;' name='"+jar0[i].rows[j].comp_key+"' ondblclick='updCompany1(this)'>";
							}else{
								str0 += "<tr class='text-c' style='cursor:pointer;' name='"+jar0[i].rows[j].comp_key+"' ondblclick='updCompany(this)'>";
							}
						}else{
							str0 += "<tr class='text-c'>";
						}
						str0 += "<td>"+(1+jar0[i].pageSize*(jar0[i].currentPage-1)+j)+"</td>"+
						"<td>"+jar0[i].rows[j].comp_name+"</td>"+
						"<td>"+jar0[i].rows[j].comp_machine_code+"</td>"+
						"<td>"+jar0[i].rows[j].comp_password+"</td>";
						if(0==jar0[i].rows[j].comp_status){
							str0 += "<td style='color:green;'>已启用</td>";
						}else{
							str0 += "<td style='color:red;'>已停用</td>";
						}
						if(ut_power.indexOf(",wt5_12_3,")>-1){
							str0 += "<td class='f-14 td-manage'>";
							if(0==jar0[i].rows[j].apply_key){
								str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].comp_key+"' onClick='updCompany1(this)' title='"+i18Frame.getedit()+"'><i class='Hui-iconfont'>&#xe6df;</i></a>";
							}else{
								str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].comp_key+"' onClick='updCompany(this)' title='"+i18Frame.getedit()+"'><i class='Hui-iconfont'>&#xe6df;</i></a>";
								if(0==jar0[i].rows[j].comp_status){
									str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].comp_key+"' onClick='disableCompany(this)' title='停用'><i class='Hui-iconfont'>&#xe631;</i></a>";
								}else{
									str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].comp_key+"' onClick='enableCompany(this)' title='启用'><i class='Hui-iconfont'>&#xe601;</i></a>";
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

function updCompany1(obj){
	ccomp_key = obj.getAttribute("name");
	setComp_key(ccomp_key);
	layer.open({
		type: 2,
		title: i18Frame.getedit(),
		shadeClose: true,
		maxmin: false,
		
		area:['530px','230px'],
		content: ['upd_company1.jsp']
	});
}

function updCompany(obj){
	ccomp_key = obj.getAttribute("name");
	setComp_key(ccomp_key);
	layer.open({
		type: 2,
		title: i18Frame.getedit(),
		shadeClose: true,
		maxmin: false,
		
		area:['530px','460px'],
		content: ['upd_company.jsp']
	});
}


function enableCompany(obj){
	comp_key = obj.getAttribute("name");
	currentpage = getCurrentPage();
	layer.confirm('确定启用?',{
		btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
	},function(){
		$.ajax({
			type: "POST",
			url: "../../../../company/enableCompany",
			data:{"comp_key":comp_key},
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				if(jo0.check =="true"){
					layer.msg('启用成功!',{icon:1,shade:0.3,time:1000});
					flush(currentpage);
				}else{
					layer.msg('启用失败!',{icon:2,shade:0.3,time:1000});
				}
			}
		});
	},function(){
		
	});
}

function disableCompany(obj){
	comp_key = obj.getAttribute("name");
	currentpage = getCurrentPage();
	layer.confirm('确定停用?',{
		btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
	},function(){
		$.ajax({
			type: "POST",
			url: "../../../../company/disableCompany",
			data:{"comp_key":comp_key},
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				if(jo0.check =="true"){
					layer.msg('停用成功!',{icon:1,shade:0.3,time:1000});
					flush(currentpage);
				}else{
					layer.msg('停用失败!',{icon:2,shade:0.3,time:1000});
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
function setComp_key(ccomp_key){
	comp_key=ccomp_key;
}
function getComp_key(){
	return comp_key;
}

function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}