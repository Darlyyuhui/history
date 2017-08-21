var currentpage1=1;
var currentpage;
var unit_key;
var cunit_key;
var comp_key;
var indexLayer = null;
var i18Frame = parent.window.frames["i18nframe"];
$(document).ready(function (){
	comp_key = $("#comp_key").val();
	$.ajax({
		type: "POST",
		url: "../../../../unit_type/getAllUnit_type",
		data: {
			"comp_key":comp_key
		},
		success:function(returnedData){
			var jar2 = eval("("+returnedData+")");
			var str2 = "<div class='model-select-box' id='unit_type'>";
			str2 +="<div class='model-select-text' id='type_select' data-value='0'>"+i18Frame.getall()+"</div>"+ 
			"<ul class='model-select-option'>";
			str2 +="<li data-option='0' class='seleced' title='"+i18Frame.getall()+"'>"+i18Frame.getall()+"</li>";
			for(var j=0;j<jar2.length;j++){
				str2 +="<li data-option='"+jar2[j].type_key+"' title='"+jar2[j].type_name+"'>"+jar2[j].type_name+"</li>";
			}
			str2 +="</ul></div>";
			$(".unit_type").empty();
			$(".unit_type").append(str2);
			selectModel();
			Type_Change();
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
	var unit_name = trim($("#unit_name").val());
	var type_key = $("#type_select").attr("data-value");
	document.getElementById("unit_name").focus();
	$.ajax({
		type: "POST",
		url: "../../../../chart/getChartUnitByPage",
		data: {
			"currentpage":currentpage,
			"type_key":type_key,
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
					str0 += "<tr class='text-c'><td colspan='8' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					str3 +=i18Frame.getdisplay()+" 0 "+i18Frame.getto()+" "+jar0[i].recordCount+" ，"+i18Frame.gettotal()+" "+jar0[i].recordCount+" "+i18Frame.getrow1();
					str1 ="<span><a class='paginate_current'>1</a></span>";
				}else{
					for(var j=0;j<jar0[i].rows.length;j++){
						str0 += "<tr class='text-c' style='cursor:pointer;' name='"+jar0[i].rows[j].unit_key+"' ondblclick='seeChart(this)'>"+
						"<td>"+(1+jar0[i].pageSize*(jar0[i].currentPage-1)+j)+"</td>"+
						"<td style='max-width:170px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].unit_name+"'>"+jar0[i].rows[j].unit_name+"</td>" +
						"<td style='max-width:60px;white-space:nowrap;overflow:hidden;text-align: center;text-overflow: ellipsis;' title='"+jar0[i].rows[j].type_name+"'>"+jar0[i].rows[j].type_name+"</td>" +
						"<td style='max-width:60px;white-space:nowrap;overflow:hidden;text-align: center;text-overflow: ellipsis;' title='"+jar0[i].rows[j].unit_longitude+"'>"+jar0[i].rows[j].unit_longitude+"</td>" +
						"<td style='max-width:60px;white-space:nowrap;overflow:hidden;text-align: center;text-overflow: ellipsis;' title='"+jar0[i].rows[j].unit_latitude+"'>"+jar0[i].rows[j].unit_latitude+"</td>" +
						"<td style='max-width:70px;white-space:nowrap;overflow:hidden;text-align: center;text-overflow: ellipsis;' title='"+jar0[i].rows[j].unit_location+"'>"+jar0[i].rows[j].unit_location+"</td>" +
						"<td style='max-width:70px;white-space:nowrap;overflow:hidden;text-align: center;text-overflow: ellipsis;' title='"+jar0[i].rows[j].unit_remark+"'>"+jar0[i].rows[j].unit_remark+"</td>" +
						"<td class='f-14 td-manage'><a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].unit_key+"' onClick='seeChart(this)' title='"+i18Frame.getstatchart()+"'><i class='Hui-iconfont'>&#xe695;</i></a></td>";
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
}

document.onkeydown = function(e){
	e = e ? e : window.event;
    var keycode = e.which ? e.which : e.keyCode;
	if(keycode == 13){
		flush(currentpage1);
	}
};

function seeChart(obj){
	cunit_key = obj.getAttribute("name");
	setUnit_key(cunit_key);
	indexLayer = layer.open({
		type: 2,
		title: i18Frame.getstatchart(),
		shadeClose: true,
		maxmin: false,
		
		area:['530px','535px'],
		content: ['chart.jsp'],
	    success: function(index){
	    	
	    },end: function(){
	    	indexLayer = null;
	    }
	});
	layer.full(indexLayer);
}


function Type_Change(){
	var $box = $('#unit_type');
	var $option = $('ul.model-select-option', $box);
	$option.find('li').mousedown(function(){
		flush(currentpage1);
	});
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
function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
