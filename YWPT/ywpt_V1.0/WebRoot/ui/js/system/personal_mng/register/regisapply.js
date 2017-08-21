var currentpage1=1;
var currentpage;
var apply_key;
var capply_key;
var user_id;
var ut_power;
var comp_key;
var i18Frame = parent.window.frames["i18nframe"];
var picindex = null;

$(function(){
	user_id = window.parent.$("#user_id").val();
	ut_power = $("#ut_power").val();
	comp_key = $("#comp_key").val();
	$.ajax({
		type: "POST",
		url: "../../../../unit_type/getAllUnit_type",
		data: {
			"comp_key":comp_key
		},
		success: function(returnedData){
			var jar0 = eval("("+returnedData+")");
			var str = "<div class='model-select-box' id='apply_flag' style='width: 147.5px;'>";
			str +="<div class='model-select-text' id='flag_select' data-value='2'>所有</div>"+ 
			"<ul class='model-select-option'>";
			str +="<li data-option='2' class='seleced' title='所有'>所有</li>";
			str +="<li data-option='0' title='已处理'>已处理</li>";
			str +="<li data-option='1' title='待处理'>待处理</li>";
			str +="</ul></div>";
			$(".apply_flag").empty();
			$(".apply_flag").append(str);
			selectModel();
			flagChange();
			flush(currentpage1);
		}
	});
});

function flush(page){
	layer.load(2,{shade:0.3});
	setCurrentPage(page);
	var apply_comp=trim($("#apply_comp").val());
	var apply_flag = $("#flag_select").attr("data-value");
	document.getElementById("apply_comp").focus();
	$.ajax({
		type: "POST",
		url: "../../../../applyment/getApplymentByPage",
		data: {
			"currentpage": currentpage,
			"apply_comp": apply_comp,
			"apply_flag": apply_flag
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
					if(ut_power.indexOf(",wt5_11_3,")>-1){
						str0 += "<tr class='text-c'><td colspan='9' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}else{
						str0 += "<tr class='text-c'><td colspan='8' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}
					str3 +=i18Frame.getdisplay()+" 0 "+i18Frame.getto()+" "+jar0[i].recordCount+" ，"+i18Frame.gettotal()+" "+jar0[i].recordCount+" "+i18Frame.getrow1();
					str1 ="<span><a class='paginate_current'>1</a></span>";
				}else{
					for(var j=0;j<jar0[i].rows.length;j++){
						if(1==jar0[i].rows[j].apply_flag){
							str0 += "<tr class='text-c' style='cursor:pointer;' name='"+jar0[i].rows[j].apply_key+"' ondblclick='seeApplyment1(this)'>";
						}else{
							str0 += "<tr class='text-c' style='cursor:pointer;' name='"+jar0[i].rows[j].apply_key+"' ondblclick='seeApplyment(this)'>";
						}
						str0 += "<td>"+(1+jar0[i].pageSize*(jar0[i].currentPage-1)+j)+"</td>"+
						"<td style='max-width:50px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].apply_comp+"'>"+jar0[i].rows[j].apply_comp+"</td>"+
						"<td>"+jar0[i].rows[j].apply_machine_code+"</td>";
						if(1==jar0[i].rows[j].apply_flag){
							str0 += "<td style='color:red;'>待处理</td>";
						}else{
							if(0==jar0[i].rows[j].del_flag){
								str0 += "<td style='color:green;'>已处理(通过)</td>";
							}else{
								str0 += "<td style='color:red;'>已处理(拒绝)</td>";
							}
						}
						str0 += "<td style='max-width:50px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].apply_time+"'>"+jar0[i].rows[j].apply_time+"</td>";
						if(""==jar0[i].rows[j].review_id){
							str0 += "<td></td>";
						}else{
							str0 += "<td style='max-width:50px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].review_name+"("+jar0[i].rows[j].review_id+")'>"+jar0[i].rows[j].review_name+"("+jar0[i].rows[j].review_id+")</td>";
						}
						str0 += "<td style='max-width:50px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].review_time+"'>"+jar0[i].rows[j].review_time+"</td>"+
						"<td style='max-width:50px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].rows[j].apply_remark+"'>"+jar0[i].rows[j].apply_remark+"</td>";
						if(ut_power.indexOf(",wt5_11_3,")>-1){
							str0 += "<td class='f-14 td-manage'>";
							if(1==jar0[i].rows[j].apply_flag){
								str0 +="<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].apply_key+"' onClick='seeApplyment1(this)' title='查看'><i class='Hui-iconfont'>&#xe695;</i></a> ";
								str0 +="<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].apply_key+"' onClick='adoptApplyment(this)' title='通过'><i class='Hui-iconfont'>&#xe6e1;</i></a> ";
								str0 +="<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].apply_key+"' onClick='refuseApplyment(this)' title='拒绝'><i class='Hui-iconfont'>&#xe6dd;</i></a> ";
							}else{
								str0 +="<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].apply_key+"' onClick='seeApplyment(this)' title='查看'><i class='Hui-iconfont'>&#xe695;</i></a> ";
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

function seeApplyment(obj){
	capply_key = obj.getAttribute("name");
	setApply_key(capply_key);
	currentpage = getCurrentPage();
	layer.open({
		type: 2,
		title: "查看",
		shadeClose: true,
		maxmin: false,
		
		area:['530px','615px'],
		content: ['register.jsp']
	});
}

function seeApplyment1(obj){
	capply_key = obj.getAttribute("name");
	setApply_key(capply_key);
	currentpage = getCurrentPage();
	layer.open({
		type: 2,
		title: "查看",
		shadeClose: true,
		maxmin: false,
		
		area:['530px','415px'],
		content: ['register1.jsp']
	});
}

function refuseApplyment(obj){
	apply_key = obj.getAttribute("name");
	currentpage = getCurrentPage();
	layer.prompt({title: '拒绝原因', formType: 2,maxlength: 100}, function(text, index){
		if(100<text.length){
			layer.alert('0~100任意内容',{icon:2,shade:0.3,title:'提示'});
		}else{
			$.ajax({
				type: "POST",
				url: "../../../../applyment/refuseApplyment",
				data: {
					"apply_key": apply_key,
					"apply_remark": text,
					"review_id": user_id,
					"comp_key":comp_key
				},
				success: function(returnedData){
					var jo0 = eval("("+returnedData+")");
					if(jo0.check="true"){
						layer.close(index);
						layer.msg('拒绝成功!',{icon:1,shade:0.3,time:1000});
						flush(currentpage);
					}else{
						layer.close(index);
						flush(currentpage);
					}
				}
			});
		}
	});
}

function adoptApplyment(obj){
	apply_key = obj.getAttribute("name");
	currentpage = getCurrentPage();
	$.ajax({
		type: "POST",
		url: "../../../../applyment/getApplymentByKey",
		data: {
			"apply_key":apply_key
		},
		success:function(returnedData1){
			var jo1 = eval("("+returnedData1+")");
			if(0==jo1.apply_flag){
				layer.close(index);
				layer.msg('该申请已被处理!',{icon:7,shade:0.3,time:1000});
				flush(currentpage);
			}else{
				layer.confirm('确定通过该申请?',{
					btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
				},function(){
					$.ajax({
						type: "POST",
						url: "../../../../applyment/adoptApplyment",
						data: {
							"apply_key": apply_key,
							"review_id": user_id,
							"comp_key":comp_key
						},
						success: function(returnedData){
							var jo0 = eval("("+returnedData+")");
							if(jo0.check =="true"){
								layer.msg('通过成功!',{icon:1,shade:0.3,time:1000});
								flush(currentpage);
							}else if(jo0.check =="false"){
								layer.confirm('该申请单位简码与系统中单位有重复,是否拒绝该申请?',{
									btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
								},function(){
									layer.prompt({title: '拒绝原因', formType: 2,maxlength: 100}, function(text, index){
										if(100<text.length){
											layer.alert('0~100任意内容',{icon:2,shade:0.3,title:'提示'});
										}else{
											$.ajax({
												type: "POST",
												url: "../../../../applyment/refuseApplyment",
												data: {
													"apply_key": apply_key,
													"apply_remark": text,
													"review_id": user_id,
													"comp_key":comp_key
												},
												success: function(returnedData){
													var jo0 = eval("("+returnedData+")");
													if(jo0.check="true"){
														layer.close(index);
														layer.msg('拒绝成功!',{icon:1,shade:0.3,time:1000});
														flush(currentpage);
													}else{
														layer.close(index);
														flush(currentpage);
													}
												}
											});
										}
									});
								},function(){
									
								});
							}else{
								layer.close(index);
								layer.msg('该申请已被处理!',{icon:7,shade:0.3,time:1000});
								flush(currentpage);
							}
						}
					});
				},function(){
					
				});
			}
		}
	});
}

function openDefault(){
	layer.close(picindex); //疯狂模式，关闭所有层
	layer.open({
	    type: 1,
	    title: false,
//	    closeBtn: 0,
	    area: ['200px', '200px'],
	    skin: 'layui-layer-nobg', //没有背景色
	    shadeClose: true,
	    content: '<img src="../../../../images/blank.png" />'
	});
}

function flagChange(){
	var $box = $('#apply_flag');
	var $option = $('ul.model-select-option', $box);
	$option.find('li').mousedown(function(){
		flush(currentpage1);
	});
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
function setApply_key(capply_key){
	apply_key=capply_key;
}
function getApply_key(){
	return apply_key;
}

function setPicindex(cindex){
	picindex=cindex;
}
