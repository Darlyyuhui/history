var currentpage1=1;
var currentpage;
var unit_key;
var cunit_key;
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
	layer.load(2,{shade:0.3});
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
					if(ut_power.indexOf(",wt5_2_2,")>-1){
						str0 += "<tr class='text-c'><td colspan='8' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}else if(ut_power.indexOf(",wt5_2_2,")==-1&&ut_power.indexOf(",wt5_2_3,")>-1){
						str0 += "<tr class='text-c'><td colspan='7' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}else{
						str0 += "<tr class='text-c'><td colspan='6' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}
					str3 +=i18Frame.getdisplay()+" 0 "+i18Frame.getto()+" "+jar0[i].recordCount+" ，"+i18Frame.gettotal()+" "+jar0[i].recordCount+" "+i18Frame.getrow1();
					str1 ="<span><a class='paginate_current'>1</a></span>";
				}else{
					for(var j=0;j<jar0[i].rows.length;j++){
						if(ut_power.indexOf(",wt5_2_3,")>-1){
							str0 += "<tr class='text-c' style='cursor:pointer;' name='"+jar0[i].rows[j].unit_key+"' ondblclick='updUnit(this)'>";
						}else{
							str0 += "<tr class='text-c'>";
						}
						if(ut_power.indexOf(",wt5_2_2,")>-1){
							str0 += "<td><input type='checkbox' value='"+jar0[i].rows[j].unit_key+"' name='cbox'/></td>";
						}
						str0 +="<td>"+(1+jar0[i].pageSize*(jar0[i].currentPage-1)+j)+"</td>"+
						"<td style='max-width:110px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].unit_name+"'>"+jar0[i].rows[j].unit_name+"</td>" +
						"<td style='max-width:60px;white-space:nowrap;overflow:hidden;text-align: center;text-overflow: ellipsis;' title='"+jar0[i].rows[j].unit_longitude+"'>"+jar0[i].rows[j].unit_longitude+"</td>" +
						"<td style='max-width:60px;white-space:nowrap;overflow:hidden;text-align: center;text-overflow: ellipsis;' title='"+jar0[i].rows[j].unit_latitude+"'>"+jar0[i].rows[j].unit_latitude+"</td>" +
						"<td style='max-width:70px;white-space:nowrap;overflow:hidden;text-align: center;text-overflow: ellipsis;' title='"+jar0[i].rows[j].unit_location+"'>"+jar0[i].rows[j].unit_location+"</td>" +
						"<td style='max-width:70px;white-space:nowrap;overflow:hidden;text-align: center;text-overflow: ellipsis;' title='"+jar0[i].rows[j].unit_remark+"'>"+jar0[i].rows[j].unit_remark+"</td>";
						if(ut_power.indexOf(",wt5_2_3,")>-1||ut_power.indexOf(",wt5_2_2,")>-1){
							str0 += "<td class='f-14 td-manage'>";
							if(ut_power.indexOf(",wt5_2_3,")>-1){
								str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].unit_key+"' onClick='updUnit(this)' title='"+i18Frame.getedit()+"'><i class='Hui-iconfont'>&#xe6df;</i></a>";
							}
							if(ut_power.indexOf(",wt5_2_2,")>-1){
								str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].unit_key+"' onClick='delUnit(this)' title='"+i18Frame.getdelete()+"'><i class='Hui-iconfont'>&#xe6e2;</i></a>";
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

function addUnit(){
	layer.open({
		type: 2,
		title: i18Frame.getadd(),
		shadeClose: true,
		maxmin: false,
		
		area:['800px','545px'],
		content: ['add_unit.jsp']
	});
}

function updUnit(obj){
	cunit_key = obj.getAttribute("name");
	setUnit_key(cunit_key);
	layer.open({
		type: 2,
		title: i18Frame.getedit(),
		shadeClose: true,
		maxmin: false,
		
		area:['800px','545px'],
		content: ['upd_unit.jsp']
	});
}

function delUnit(obj){
	unit_key = obj.getAttribute("name");
	currentpage = getCurrentPage();
	layer.confirm(i18Frame.getdetermine_delete()+'?',{
		btn: [i18Frame.getok(),i18Frame.getcancel()],icon:3,title:i18Frame.getinfo(),closeBtn:1
	},function(){
		$.ajax({
			type: "POST",
			url: "../../../../unit/delUnitByKey",
			data:{"unit_key":unit_key},
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				if(jo0.check =="true"){
					layer.msg(i18Frame.getdelete_success()+'!',{icon:1,shade:0.3,time:1000});
					$("input[type='checkbox']").prop("checked",false);
					flush(currentpage);
				}else if(jo0.check =="count"){
					layer.alert(i18Frame.getdelete_all_unit_lock()+'!',{icon:7,shade:0.3,title:i18Frame.getinfo()});
					flush(currentpage);
					$("input[type='checkbox']").prop("checked",false);
				}else{
					layer.msg(i18Frame.getdelete_failed()+'!',{icon:2,shade:0.3,time:1000});
				}
			}
		});
	},function(){
		
	});
}

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
		layer.alert(i18Frame.getchoose_delete_unit()+'!',{icon:7,title:i18Frame.getinfo(),closeBtn:1});
	}else{
		layer.confirm(i18Frame.getdetermine_delete()+'?',{
			btn: [i18Frame.getok(),i18Frame.getcancel()],icon:3,title:i18Frame.getinfo(),closeBtn:1
		},function(){
			$.ajax({
				type: "POST",
				url: "../../../../unit/delMoreUnit",
				data: {
					"chk_value[]":chk_value
				},
				success:function(returnedData){
					var jar0 = eval("("+returnedData+")");
					if(jar0.length>0){
						var str0="";
						for(var j=0;j<jar0.length;j++){
							if(j==jar0.length-1){
								str0 +=jar0[j].unit_name;
							}else{
								str0 +=jar0[j].unit_name+",";
							}
						}
						layer.alert(i18Frame.getdelete_all_unit_lock()+':'+str0+'!',{icon:7,shade:0.3,title:i18Frame.getinfo()});
						$("input[type='checkbox']").prop("checked",false);
						flush(currentpage);
					}else{
						layer.msg(i18Frame.getdelete_success()+'!',{icon:1,shade:0.3,time:1000});
						$("input[type='checkbox']").prop("checked",false);
						flush(currentpage);
					}
				}
			});
		},function(){
			$("input[type='checkbox']").prop("checked",false);
		});
	}
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
		layer.alert(i18Frame.getno_unit_info()+'!',{icon:7,title:i18Frame.getinfo(),closeBtn:1});
	}else{
		layer.confirm(i18Frame.getdetermine_export()+'?',{
			btn: [i18Frame.getok(),i18Frame.getcancel()],icon:3,title:i18Frame.getinfo(),closeBtn:1
		},function(index1){
			var unit_name = trim($(".input-text").val());
			
			$("#form_unit").val(unit_name);
			var url="./../../../../export/exportUnit"; 
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
function setUnit_key(cunit_key){
	unit_key=cunit_key;
}
function getUnit_key(){
	return unit_key;
}
function setRoute_key(croute_key){
	route_key=croute_key;
}
function getRoute_key(){
	return route_key;
}
function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}