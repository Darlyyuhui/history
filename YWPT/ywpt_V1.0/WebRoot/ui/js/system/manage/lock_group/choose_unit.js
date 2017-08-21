var currentpage1=1;
var currentpage;
var recordcount;
var cjar;
var ijar;
var comp_key;
var parentIframe = parent.window.frames["manageIframe"];
var parentTree = parent.window.frames["treeIframe"];
var i18Frame = parent.parent.window.frames["i18nframe"];
$(document).ready(function (){
	comp_key = $("#comp_key").val();
	flush(currentpage1);
});

function flush(page){
	setCurrentPage(page);
	var unit_name = trim($(".input-text").val());
	document.getElementById("unit_name").focus();
	$.ajax({
		type: "POST",
		url: "../../../../unit/getUnitByPage",
		data: {
			"currentpage":currentpage,
			"unit_name":unit_name,
			"comp_key":comp_key
		},
		success: function(returnedData){
			var jar0 = eval("("+returnedData+")");
			cjar = jar0[0];
			ijar = jar0[0];
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
						str0 += "<tr class='text-c'>"+
						"<td><input type='checkbox' value='"+jar0[i].rows[j].unit_key+"' name='cbox'/></td>" +
						"<td>"+(1+jar0[i].pageSize*(jar0[i].currentPage-1)+j)+"</td>"+
						"<td style='max-width:110px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].unit_name+"'>"+jar0[i].rows[j].unit_name+"</td>" +
						"<td style='max-width:60px;white-space:nowrap;overflow:hidden;text-align: center;text-overflow: ellipsis;' title='"+jar0[i].rows[j].unit_longitude+"'>"+jar0[i].rows[j].unit_longitude+"</td>" +
						"<td style='max-width:60px;white-space:nowrap;overflow:hidden;text-align: center;text-overflow: ellipsis;' title='"+jar0[i].rows[j].unit_latitude+"'>"+jar0[i].rows[j].unit_latitude+"</td>" +
						"<td style='max-width:70px;white-space:nowrap;overflow:hidden;text-align: center;text-overflow: ellipsis;' title='"+jar0[i].rows[j].unit_location+"'>"+jar0[i].rows[j].unit_location+"</td>" +
						"<td style='max-width:70px;white-space:nowrap;overflow:hidden;text-align: center;text-overflow: ellipsis;' title='"+jar0[i].rows[j].unit_remark+"'>"+jar0[i].rows[j].unit_remark+"</td></tr>";
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

function addUnit(){
	var obj = document.getElementsByName("cbox");
	var chk_value=[];
	var j =0;
	for(var i=0;i<obj.length;i++){
		if(obj[i].checked){
			ijar.rows[j] = cjar.rows[i];
			j++;
		}
	}
	if(j==0){
		layer.alert(i18Frame.getchoose_add_unit()+'!',{icon:7,title:i18Frame.getinfo(),btn: [i18Frame.getok()],closeBtn:1});
	}else{
		var return_num = parentIframe.addToBuyBill(ijar,j);
		var array = new Array();
		array = return_num.split(";");
		var add_num = array[0];
		var repeat_num = array[1];
		if(add_num!=0){
			layer.alert(i18Frame.getsuccess_add_unit()+"："+add_num+"；<br>"+i18Frame.getrepeat_add_unit()+"："+repeat_num+"", {icon: 1,title:i18Frame.getinfo(),btn: [i18Frame.getok()],closeBtn:1});
		}else{
			layer.alert(i18Frame.getsuccess_add_unit()+"："+add_num+"；<br>"+i18Frame.getrepeat_add_unit()+"："+repeat_num+"", {icon: 0,title:i18Frame.getinfo(),btn: [i18Frame.getok()],closeBtn:1});
		}
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