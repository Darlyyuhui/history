var currentpage1=1;
var androidcurrentpage;
var ioscurrentpage;
var app_id_a;
var capp_id_a;
var app_id_i;
var capp_id_i;
var ut_power;
var comp_key;
var i18Frame = parent.window.frames["i18nframe"];

$(document).ready(function (){
	ut_power = $("#ut_power").val();
	comp_key = $("#comp_key").val();
	androidflush(currentpage1);
	iosflush(currentpage1);
	layui.use(['layer', 'element'], function(){
	  var element = layui.element();
	  
	  //监听Tab切换
	  element.on('tab(demo)', function(data){
		  //flush();
	  });
	});
	$("#android_check").click(function() { 
		var tc_flag = $(this).is(":checked"); 
		var o=document.getElementsByName("androidcbox");
	    for(var i=0;i<o.length;i++){
	        if(tc_flag) 
	        	o[i].checked=true;
	        else 
	        	o[i].checked=false;
	    }
	});
	$("#ios_check").click(function() { 
		var tc_flag = $(this).is(":checked"); 
		var o=document.getElementsByName("ioscbox");
	    for(var i=0;i<o.length;i++){
	        if(tc_flag) 
	        	o[i].checked=true;
	        else 
	        	o[i].checked=false;
	    }
	});
});

function androidflush(page){
	layer.load(2,{shade:0.3});
	setAndroidCurrentPage(page);
	$.ajax({
		type: "POST",
		url: "../../../../androidversion/getAndroidVersionByPage",
		data: {
			"currentpage": androidcurrentpage
		},
		success:function(returnedData){
			var jar0 = eval("("+returnedData+")");
			var str0 = "";//显示列表
			var str1;//显示页码
			var str3 = "";//显示当前为第几条到第几条数据
			$("#pagin_android").empty();
			$("#androidtbody").empty();
			$("#page_info_android").empty();
			for(var i=0;i<jar0.length;i++){
				if(jar0[i].rows.length==0){
					if(ut_power.indexOf(",wt5_13_2,")>-1){
						str0 += "<tr class='text-c'><td colspan='8' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}else if(ut_power.indexOf(",wt5_13_2,")==-1&&ut_power.indexOf(",wt5_13_3,")>-1){
						str0 += "<tr class='text-c'><td colspan='7' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}else{
						str0 += "<tr class='text-c'><td colspan='6' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}
					str3 +=i18Frame.getdisplay()+" 0 "+i18Frame.getto()+" "+jar0[i].recordCount+" ，"+i18Frame.gettotal()+" "+jar0[i].recordCount+" "+i18Frame.getrow1();
					str1 ="<span><a class='paginate_current'>1</a></span>";
				}else{
					for(var j=0;j<jar0[i].rows.length;j++){
						if(ut_power.indexOf(",wt5_13_3,")>-1){
							str0 += "<tr class='text-c' style='cursor:pointer;' name='"+jar0[i].rows[j].app_id+"' ondblclick='updAndroid(this)'>";
						}else{
							str0 += "<tr class='text-c'>";
						}
						if(ut_power.indexOf(",wt5_13_2,")>-1){
							str0 += "<td><input type='checkbox' value='"+jar0[i].rows[j].app_id+"' name='androidcbox'/></td>";
						}
						str0 += "<td>"+(1+jar0[i].pageSize*(jar0[i].currentPage-1)+j)+"</td>"+
						"<td>"+jar0[i].rows[j].app_name+"</td>"+
						"<td>"+jar0[i].rows[j].apk_name+"</td>"+
						"<td>"+jar0[i].rows[j].ver_name+"</td>"+
						"<td>"+jar0[i].rows[j].ver_code+"</td>"+
						"<td>"+jar0[i].rows[j].app_time+"</td>";
						if(ut_power.indexOf(",wt5_13_3,")>-1||ut_power.indexOf(",wt5_13_2,")>-1){
							str0 += "<td class='f-14 td-manage'>";
							if(ut_power.indexOf(",wt5_13_3,")>-1){
								str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].app_id+"' onClick='updAndroid(this)' title='"+i18Frame.getedit()+"'><i class='Hui-iconfont'>&#xe6df;</i></a>";
							}
							if(ut_power.indexOf(",wt5_13_2,")>-1){
								str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].app_id+"' onClick='delAndroid(this)' title='"+i18Frame.getdelete()+"'><i class='Hui-iconfont'>&#xe6e2;</i></a>";
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
			$("#androidtbody").append(str0);
			$("#pagin_android").append(str1);
			$("#page_info_android").append(str3);
			setTimeout(function(){
				layer.closeAll('loading');
			},300);
		}
	});
}

function iosflush(page){
	layer.load(2,{shade:0.3});
	setIosCurrentPage(page);
	$.ajax({
		type: "POST",
		url: "../../../../iosversion/getIosVersionByPage",
		data: {
			"currentpage": ioscurrentpage
		},
		success:function(returnedData){
			var jar0 = eval("("+returnedData+")");
			var str0 = "";//显示列表
			var str1;//显示页码
			var str3 = "";//显示当前为第几条到第几条数据
			$("#pagin_ios").empty();
			$("#iostbody").empty();
			$("#page_info_ios").empty();
			for(var i=0;i<jar0.length;i++){
				if(jar0[i].rows.length==0){
					if(ut_power.indexOf(",wt5_13_2,")>-1){
						str0 += "<tr class='text-c'><td colspan='8' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}else if(ut_power.indexOf(",wt5_13_2,")==-1&&ut_power.indexOf(",wt5_13_3,")>-1){
						str0 += "<tr class='text-c'><td colspan='7' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}else{
						str0 += "<tr class='text-c'><td colspan='6' class='null' align='center'>"+i18Frame.getnodata()+"</td></tr>";
					}
					str3 +=i18Frame.getdisplay()+" 0 "+i18Frame.getto()+" "+jar0[i].recordCount+" ，"+i18Frame.gettotal()+" "+jar0[i].recordCount+" "+i18Frame.getrow1();
					str1 ="<span><a class='paginate_current'>1</a></span>";
				}else{
					for(var j=0;j<jar0[i].rows.length;j++){
						if(ut_power.indexOf(",wt5_13_3,")>-1){
							str0 += "<tr class='text-c' style='cursor:pointer;' name='"+jar0[i].rows[j].app_id+"' ondblclick='updIos(this)'>";
						}else{
							str0 += "<tr class='text-c'>";
						}
						if(ut_power.indexOf(",wt5_13_2,")>-1){
							str0 += "<td><input type='checkbox' value='"+jar0[i].rows[j].app_id+"' name='ioscbox'/></td>";
						}
						str0 += "<td>"+(1+jar0[i].pageSize*(jar0[i].currentPage-1)+j)+"</td>"+
						"<td>"+jar0[i].rows[j].app_name+"</td>"+
						"<td>"+jar0[i].rows[j].apk_name+"</td>"+
						"<td>"+jar0[i].rows[j].ver_name+"</td>"+
						"<td>"+jar0[i].rows[j].ver_code+"</td>"+
						"<td>"+jar0[i].rows[j].app_time+"</td>";
						if(ut_power.indexOf(",wt5_13_3,")>-1||ut_power.indexOf(",wt5_13_2,")>-1){
							str0 += "<td class='f-14 td-manage'>";
							if(ut_power.indexOf(",wt5_13_3,")>-1){
								str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].app_id+"' onClick='updIos(this)' title='"+i18Frame.getedit()+"'><i class='Hui-iconfont'>&#xe6df;</i></a>";
							}
							if(ut_power.indexOf(",wt5_13_2,")>-1){
								str0 += "<a style='text-decoration:none' class='ml-5' name='"+jar0[i].rows[j].app_id+"' onClick='delIos(this)' title='"+i18Frame.getdelete()+"'><i class='Hui-iconfont'>&#xe6e2;</i></a>";
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
			$("#iostbody").append(str0);
			$("#pagin_ios").append(str1);
			$("#page_info_ios").append(str3);
			setTimeout(function(){
				layer.closeAll('loading');
			},300);
		}
	});
}

function gotoPage(obj){
	page = obj.getAttribute("name");
	var tabtitle = $(".layui-this").text();
	if(tabtitle.indexOf("Android")>-1){
		androidflush(page);
		$("#android_check").prop("checked",false);
	}else if(tabtitle.indexOf("iOS")>-1){
		iosflush(page);
		$("#ios_check").prop("checked",false);
	}
}

document.onkeydown = function(e){
	e = e ? e : window.event;
    var keycode = e.which ? e.which : e.keyCode;
	if(keycode == 13){
		var tabtitle = $(".layui-this").text();
		if(tabtitle.indexOf("Android")>-1){
			androidflush(currentpage1);
		}else if(tabtitle.indexOf("iOS")>-1){
			iosflush(currentpage1);
		}
	}
};

function addAndroid(){
	layer.open({
		type: 2,
		title: "添加Android版本",
		shadeClose: true,
		maxmin: false,
		
		area:['530px','440px'],
		content: ['add_android.jsp']
	});
}

function addIos(){
	layer.open({
		type: 2,
		title: "添加iOS版本",
		shadeClose: true,
		maxmin: false,
		
		area:['530px','440px'],
		content: ['add_ios.jsp']
	});
}

function updAndroid(obj){
	capp_id_a = obj.getAttribute("name");
	setApp_id_a(capp_id_a);
	layer.open({
		type: 2,
		title: "编辑Android版本",
		shadeClose: true,
		maxmin: false,
		
		area:['530px','440px'],
		content: ['upd_android.jsp']
	});
}

function updIos(obj){
	capp_id_i = obj.getAttribute("name");
	setApp_id_i(capp_id_i);
	layer.open({
		type: 2,
		title: "编辑iOS版本",
		shadeClose: true,
		maxmin: false,
		
		area:['530px','440px'],
		content: ['upd_ios.jsp']
	});
}

function delAndroid(obj){
	var app_id = obj.getAttribute("name");
	androidcurrentpage = getAndroidCurrentPage();
	layer.confirm('确定删除?',{
		btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
	},function(){
		$.ajax({
			type: "POST",
			url: "../../../../androidversion/delAndroidVersionByID",
			data:{"app_id":app_id},
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				if(jo0.check =="true"){
					layer.msg('删除成功!',{icon:1,shade:0.3,time:1000});
					$("#android_check").prop("checked",false);
					androidflush(androidcurrentpage);
				}else{
					layer.msg('删除失败!',{icon:2,shade:0.3,time:1000});
				}
			}
		});
	},function(){
		
	});
}

function delIos(obj){
	var app_id = obj.getAttribute("name");
	ioscurrentpage = getIosCurrentPage();
	layer.confirm('确定删除?',{
		btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
	},function(){
		$.ajax({
			type: "POST",
			url: "../../../../iosversion/delIosVersionByID",
			data:{"app_id":app_id},
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				if(jo0.check =="true"){
					layer.msg('删除成功!',{icon:1,shade:0.3,time:1000});
					$("#ios_check").prop("checked",false);
					iosflush(ioscurrentpage);
				}else{
					layer.msg('删除失败!',{icon:2,shade:0.3,time:1000});
				}
			}
		});
	},function(){
		
	});
}

function androiddel(){
	androidcurrentpage = getAndroidCurrentPage();
	var obj = document.getElementsByName("androidcbox");
	var chk_value=[];
	var count =0;
	for(var i=0;i<obj.length;i++){
		if(obj[i].checked){
			count++;
			chk_value.push(obj[i].value);
		}
	}
	if(count==0){
		layer.alert('请选择要删除的Android版本!',{icon:7,title:'提示',closeBtn:1});
	}else{
		layer.confirm('确定删除?',{
			btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
		},function(){
			$.ajax({
				type: "POST",
				url: "../../../../androidversion/delMoreAndroidVersion",
				data: {
					"chk_value[]":chk_value
				},
				success:function(returnedData){
					var jo0 = eval("("+returnedData+")");
					if(jo0.check =="true"){
						layer.msg('删除成功!',{icon:1,shade:0.3,time:1000});
						$("#android_check").prop("checked",false);
						androidflush(androidcurrentpage);
					}else{
						layer.msg('删除失败!',{icon:2,shade:0.3,time:1000});
					}
				}
			});
		},function(){
			$("#android_check").prop("checked",false);
		});
	}
}

function iosdel(){
	ioscurrentpage = getIosCurrentPage();
	var obj = document.getElementsByName("ioscbox");
	var chk_value=[];
	var count =0;
	for(var i=0;i<obj.length;i++){
		if(obj[i].checked){
			count++;
			chk_value.push(obj[i].value);
		}
	}
	if(count==0){
		layer.alert('请选择要删除的iOS版本!',{icon:7,title:'提示',closeBtn:1});
	}else{
		layer.confirm('确定删除?',{
			btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
		},function(){
			$.ajax({
				type: "POST",
				url: "../../../../iosversion/delMoreIosVersion",
				data: {
					"chk_value[]":chk_value
				},
				success:function(returnedData){
					var jo0 = eval("("+returnedData+")");
					if(jo0.check =="true"){
						layer.msg('删除成功!',{icon:1,shade:0.3,time:1000});
						$("#ios_check").prop("checked",false);
						iosflush(ioscurrentpage);
					}else{
						layer.msg('删除失败!',{icon:2,shade:0.3,time:1000});
					}
				}
			});
		},function(){
			$("#ios_check").prop("checked",false);
		});
	}
}

function setAndroidCurrentPage(currentpage1){
	androidcurrentpage=currentpage1;
}
function getAndroidCurrentPage(){
	return androidcurrentpage;
}
function setIosCurrentPage(currentpage1){
	ioscurrentpage=currentpage1;
}
function getIosCurrentPage(){
	return ioscurrentpage;
}
function setApp_id_a(capp_id_a){
	app_id_a=capp_id_a;
}
function getApp_id_a(){
	return app_id_a;
}
function setApp_id_i(capp_id_i){
	app_id_i=capp_id_i;
}
function getApp_id_i(){
	return app_id_i;
}
function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}