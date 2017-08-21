var group_key;
var unit_value = ",";
var ut_power;
var comp_key;
var parentTree = parent.window.frames["treeIframe"];
var i18Frame = parent.parent.window.frames["i18nframe"];
$(document).ready(function (){
	var dk = $(window.parent.document).find("#manageIframe").attr("src");
	var tmp=dk.split("=")[1];
	group_key = tmp.split("g")[1];
	ut_power = $("#ut_power").val();
	comp_key = $("#comp_key").val();
	flush1();
	flush();
});

function flush1(){
	var unit_name = "";
	$.ajax({
		type: "POST",
		url: "../../../../unit/getUnitByGroup",
		data: {
			"group_key": group_key,
			"unit_name": unit_name,
			"comp_key": comp_key
		},
		success:function(returnedData){
			var jar0 = eval("("+returnedData+")");
			if(jar0.length!=0){
				for(var i=0;i<jar0.length;i++){
					unit_value += jar0[i].unit_key+",";
				}
			}
		}
	});
}

function flush(){
	parent.layer.load(2,{shade:0.3});
	var unit_name = trim($(".input-text").val());
	$.ajax({
		type: "POST",
		url: "../../../../unit/getUnitByGroup",
		data: {
			"group_key": group_key,
			"unit_name": unit_name,
			"comp_key": comp_key
		},
		success:function(returnedData){
			var jar0 = eval("("+returnedData+")");
			var str0 = "";//显示列表
			$(".table tbody").empty();
			if(jar0.length==0){
				if(ut_power.indexOf(",wt5_3_2,")>-1){
					str0 += "<tr class='text-c'><td colspan='7' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
				}else{
					str0 += "<tr class='text-c'><td colspan='6' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
				}
			}else{
				for(var i=0;i<jar0.length;i++){
					str0 += "<tr class='text-c'>";
					if(ut_power.indexOf(",wt5_3_2,")>-1){
						str0 += "<td><input type='checkbox' value='"+jar0[i].unit_key+"' name='cbox'/></td>";
					}
					str0 += "<td>"+(1+i)+"</td>"+
					"<td style='max-width:70px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].unit_name+"'>"+jar0[i].unit_name+"</td>" +
					"<td style='max-width:70px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].unit_longitude+"'>"+jar0[i].unit_longitude+"</td>" +
					"<td style='max-width:70px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].unit_latitude+"'>"+jar0[i].unit_latitude+"</td>" +
					"<td style='max-width:70px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].unit_location+"'>"+jar0[i].unit_location+"</td>" +
					"<td style='max-width:70px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].unit_remark+"'>"+jar0[i].unit_remark+"</td></tr>";
				}
			}
			$(".table tbody").append(str0);
			setTimeout(function(){
				parent.layer.closeAll('loading');
			},300);
		}
	});
}

function chooseUnit(){
	parent.layer.open({
		type: 2,
		title: i18Frame.getchooseunit(),
		shadeClose: true,
		maxmin: false,
		
		area:['920px','580px'],
		content: ['choose_unit.jsp']
	});
}

function datadel(){
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
		$("input[type='checkbox']").prop("checked",false);
		parent.layer.alert(i18Frame.getchoose_group_delete_unit()+'!',{icon:7,title:i18Frame.getinfo(),closeBtn:1});
	}else{
		parent.layer.confirm(i18Frame.getdetermine_group_delete_unit()+'?'+i18Frame.getnot_restored()+'!',{
			btn: [i18Frame.getok(),i18Frame.getcancel()],icon:3,title:i18Frame.getinfo(),closeBtn:1
		},function(){
			$.ajax({
				type: "POST",
				url: "../../../../unit/delMoreLockGroup",
				data: {
					"chk_value[]":chk_value,
					"group_key": group_key
				},
				success:function(returnedData){
					var jar0 = eval("("+returnedData+")");
					var info = jar0.check;
	   				var array = info.split(",");
	   				var flag = array[0];
	   				var s_unit_key = array[1];
	   				if("2"==flag){
	   					$("input[type='checkbox']").prop("checked",false);
	   					flush();
	   					delSmallTool(group_key,s_unit_key);
	   					parent.layer.alert(i18Frame.getdelete_success()+'!',{icon:1,shade:0.3,title:i18Frame.getinfo()});
	   				}else if("0"==flag){
	   					$("input[type='checkbox']").prop("checked",false);
	   					parent.layer.alert(i18Frame.getgroup_no_delete_unit()+'!'+i18Frame.getrefresh_try()+'!',{icon:2,shade:0.3,title:i18Frame.getinfo()});
	   				}else{
	   					$("input[type='checkbox']").prop("checked",false);
	   					flush();
	   					delSmallTool(group_key,s_unit_key);
	   					parent.layer.alert(i18Frame.getgroup_part_delete_unit()+','+i18Frame.getthis_no_delete()+'：'+array[2],{icon:7,shade:0.3,title:i18Frame.getinfo()});
	   				}
				}
			});
		},function(){
			$("input[type='checkbox']").prop("checked",false);
		});
	}
}

function addToBuyBill(jar3,j){
	var add_num = 0;//成功添加的数量
	var repeat_num = 0;//重复的数量
	var g_num_help = "";
	var g_name_help = "";
	for(var i=0;i<j;i++){
		if(unit_value.indexOf(","+jar3.rows[i].unit_key+",") == -1){
			add_num++;
			g_num_help += jar3.rows[i].unit_key+";";
			g_name_help += jar3.rows[i].unit_name+";";
			unit_value += jar3.rows[i].unit_key+",";
		}else{
			repeat_num++;
		}
	}
	if("" != g_num_help){
		$.ajax({
			type: "POST",
			url: "../../../../unit/updUnitGroupKey",
			data: {
				"group_key": group_key,
				"unit_keys": g_num_help
			},
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				if(jo0.check =="true"){
					if(0!=add_num){
						flush();
					}
					addNewSmallTool(group_key,g_num_help,g_name_help,add_num);
				}
			}
		});
	}
	return add_num+";"+repeat_num;
}

function addNewSmallTool(group_key,g_num_help,g_name_help,add_num){
	parentTree.addNewSmallTool(group_key,g_num_help,g_name_help,add_num);
}

function delSmallTool(group_key,s_unit_key){
	var unit_key_array = s_unit_key.split(";");
	for( var i = 0; i < (unit_key_array.length-1); i++){
		unit_value = unit_value.replace(","+unit_key_array[i]+",", ",");
	}
	parentTree.delSmallTool(group_key,s_unit_key);
}

//判断一个字符串在另一个字符串中出现了几次。
function countInstances(mainStr, subStr)
{
    var count = 0;
    var offset = 0;
    do
    {
        offset = mainStr.indexOf(subStr, offset);
        if(offset != -1)
        {
            count++;
            offset += subStr.length;
        }
    }while(offset != -1)
    return count;
}

function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}