var currentpage1=1;
var currentpage;
var unit_key;
var i18Frame = parent.parent.window.frames["i18nframe"];


$(document).ready(function (){
	unit_key = parent.getUnit_key();
	flush(currentpage1);
});

function flush(page){
	parent.layer.load(2,{shade:0.3});
	setCurrentPage(page);
	var lock_like = trim($(".input-text").val());
	$.ajax({
		type: "POST",
		url: "../../../../lock/getLockByUnitPage",
		data: {
			"currentpage":currentpage,
			"unit_key": unit_key,
			"lock_like": lock_like
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
					str0 += "<tr class='text-c'><td colspan='8' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					str3 +=i18Frame.getdisplay()+" 0 "+i18Frame.getto()+" "+jar0[i].recordCount+" ，"+i18Frame.gettotal()+" "+jar0[i].recordCount+" "+i18Frame.getrow1();
					str1 ="<span><a class='paginate_current'>1</a></span>";
				}else{
					for(var j=0;j<jar0[i].rows.length;j++){
						str0 += "<tr class='text-c'>";
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
}

document.onkeydown = function(e){
	e = e ? e : window.event;
    var keycode = e.which ? e.which : e.keyCode;
	if(keycode == 13){
		flush(currentpage1);
	}
};

function setCurrentPage(currentpage1){
	currentpage=currentpage1;
}
function getCurrentPage(){
	return currentpage;
}

function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}