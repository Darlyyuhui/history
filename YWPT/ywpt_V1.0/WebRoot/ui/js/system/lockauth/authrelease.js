var name_message;
var time_message;
var timeslice_flag;
var alllock_flag;
var start_flag;
var end_flag;
var time_flag;
var user_id;
var comp_key;
var start_date;
var end_date;
var user_value;
var i18Frame = parent.window.frames["i18nframe"];
$(function(){
	user_id = $("#user_id").val();
	comp_key = $("#comp_key").val();
	var now_time = getnowtime();
	var now_date = now_time.split(" ")[0];
	$("#authorize_name").val(i18Frame.getauthrelease()+now_date);
	$("#logmin").val(now_date);
	$("#logmax").val(now_date);
	$("#logmin1").val("00:00:00");
	$("#logmax1").val("23:59:59");
	$(".slice").bind("change",function(){
		timeslice_flag = $(".checked").find(".slice").val();
		if("0" == timeslice_flag){//非时间片任务
			$('#timeslicered').css("display","none");
			$('#timeslicespan').css("display","none");
			$('#timeslice').css("display","none");
		}else{//选择了时间片任务
			$('#timeslicered').css("display","inline");
			$('#timeslicespan').css("display","inline");
			$('#timeslice').css("display","block");
		}
	});
	$(".alllock").bind("change",function(){
		alllock_flag = $(".checked").find(".alllock").val();
		if("0" == alllock_flag){//非全部锁具
			lockIfrme.window.cancelCheckAllNode();
		}else{//全部锁具
			lockIfrme.window.checkAllNode();
		}
	});
});

function Authorize(){
	layer.load(2,{shade:0.3});
	var authorize_name = trim($("#authorize_name").val());
	start_date = $("#logmin").val();
	end_date = $("#logmax").val();
	var start_time = $("#logmin1").val();
	var end_time = $("#logmax1").val();
	timeslice_flag = $(".checked").find(".slice").val();
	alllock_flag = $(".checked").find(".alllock").val();
	var timeslice_day;
	if("0"==timeslice_flag){
		timeslice_day = trim($("#timeslice").val());
	}else if("1"==timeslice_flag){
		timeslice_day = "0";
	}
	user_value = keyIframe.window.Key_key();
	var lock = lockIfrme.window.Lock_key();
	var authorize_remark = $("#authorize_remark").val();
	checkName(authorize_name);
	checkDate(timeslice_flag,timeslice_day,start_date,end_date);
	var nowdate=getnowdate();
	checkStartDate(nowdate,start_date);
	checkEndDate(start_date,end_date);
	checkTime(start_time,end_time);
	if(""==start_date){
		layer.closeAll('loading');
		layer.tips(i18Frame.getchoose_auth_start_date(),'#logmin',{
			tips:3,time:2000
		});
	}else if(""==end_date){
		layer.closeAll('loading');
		layer.tips(i18Frame.getchoose_auth_end_date(),'#logmax',{
			tips:3,time:2000
		});
	}else if("false"==start_flag){
		layer.closeAll('loading');
		layer.tips(i18Frame.getstart_date_greater_now_date(),'#logmin',{
			tips:3,time:2000
		});
	}else if("false"==end_flag){
		layer.closeAll('loading');
		layer.tips(i18Frame.getend_date_greater_start_date(),'#logmax',{
			tips:3,time:2000
		});
	}if(""==start_time){
		layer.closeAll('loading');
		layer.tips(i18Frame.getchoose_auth_start_time(),'#logmin1',{
			tips:3,time:2000
		});
	}else if(""==end_time){
		layer.closeAll('loading');
		layer.tips(i18Frame.getchoose_auth_end_time(),'#logmax1',{
			tips:3,time:2000
		});
	}else if("false"==time_flag){
		layer.closeAll('loading');
		layer.tips(i18Frame.getend_time_greater_start_time(),'#logmax1',{
			tips:3,time:2000
		});
	}else if("null"==name_message){
		layer.closeAll('loading');
		layer.tips(i18Frame.getinput_auditname(),'#authorize_name',{
			tips:3,time:2000
		});
	}else if("error"==name_message){
		layer.closeAll('loading');
		layer.tips('2~32'+i18Frame.getlength(),'#authorize_name',{
			tips:3,time:2000
		});
	}else if("error0"==time_message){
		layer.closeAll('loading');
		layer.tips(i18Frame.gettime_interval(),'#timeslice',{
			tips:3,time:2000
		});
	}else if("error"==time_message){
		layer.closeAll('loading');
		layer.tips(i18Frame.gettime_interval_less_start_end_time(),'#timeslice',{
			tips:3,time:2000
		});
	}else if(100<authorize_remark.length){
		layer.closeAll('loading');
		layer.tips('0~100'+i18Frame.getlength(),'#authorize_remark',{
			tips:3,time:2000
		});
	}else if(0==user_value.length){
		layer.closeAll('loading');
		keyIframe.layer.tips(i18Frame.getchoose_auth_user(),$("#keyIframe").contents().find("#key_id"),{
			tips:3,time:2000
		});
	}else if("1"==alllock_flag&&0==lock.length){
		layer.closeAll('loading');
		lockIfrme.layer.tips(i18Frame.getchoose_auth_lock(),$("#lockIfrme").contents().find("#lock_id"),{
			tips:3,time:2000
		});
	}else{
		$.ajax({
			type: "POST",
			url: "../../../lock/getAllNormalLockCount",
			data: {
				"comp_key":comp_key
			},
			success: function(returnedData1){
				var jo1= eval("("+returnedData1+")");
				if(jo1.check =="true"){
					$.ajax({
						type: "POST",
						url: "../../../lockauthassist/checkLockAuth",
						data: {
							"start_date":start_date,
							"end_date":end_date,
							"user_value":user_value,
							"comp_key":comp_key
						},
						success: function(returnedData){
							var jo0= eval("("+returnedData+")");
							layer.closeAll('loading');
							if(jo0.check =="true"){
								layer.load(2,{shade:0.3});
								$.ajax({
									type: "POST",
									url: "../../../lockauth/insertLockAuth",
									data: {
										"authorize_name":authorize_name,
										"start_date":start_date,
										"end_date":end_date,
										"start_time":start_time,
										"end_time":end_time,
										"user_value":user_value,
										"alllock_flag":alllock_flag,
										"lock":lock,
										"user_id": user_id,
										"timeslice_flag":timeslice_flag,
										"timeslice_day":timeslice_day,
										"authorize_remark":authorize_remark,
										"comp_key":comp_key
									},
									success: function(returnedData){
										var jo0= eval("("+returnedData+")");
										layer.closeAll('loading');
										if(jo0.check =="true"){
											layer.alert(i18Frame.getauth_success()+'!',{
												icon:1,shade:0.3,title:i18Frame.getinfo()
											},function(index){
												var open_click = window.parent.$("#see_lockauth");
												window.parent.openActive(open_click);
											});
										}else{
											layer.alert(i18Frame.getauth_failed()+'!',{icon:2,shade:0.3,title:i18Frame.getinfo()});
											return;
										}
									}
								});
							}else{
								layer.open({
									type: 2,
									title: i18Frame.getconflict_list(),
									shadeClose: false,
									maxmin: false,
									area:['800px','485px'],
									content: ['conflict.jsp']
								});
							}
						}
					});
				}else{
					layer.closeAll('loading');
					lockIfrme.layer.tips(i18Frame.getno_lock(),$("#lockIfrme").contents().find("#lock_id"),{
						tips:3,time:2000
					});
				}
			}
		});
	}
}

function checkAuthorize(){
	return 1;
}

function getAllLockFlag(){
	alllock_flag = $(".checked").find(".alllock").val();
	return alllock_flag;
}

function checkName(authorize_name){
	if(""==authorize_name.replace(/\s/g,"")){
		name_message = "null";
	}else{
		if(authorize_name.length<=32&&authorize_name.length>=2){
			name_message = "ok";
			ti=1;
			return true;
		}else{
			name_message = "error";
			ti=0;
			return false;
		}
	}
}

function checkDate(timeslice_flag,timeslice_day,start_time,end_time){
	if("0"==timeslice_flag){
		if(""==timeslice_day){
			time_message = "error0";
		}else{
			var day = parseInt(timeslice_day);
			var bday = getDays(start_time,end_time)+1;
			if(day<1||day>100){
				time_message = "error0";
			}else if(day>bday){
				time_message = "error";
			}else{
				time_message = "ok";
			}
		}
	}else if("1"==timeslice_flag){
		time_message = "ok";
	}
}

function checkStartDate(nowdate,start_date){
	var strSeparator = "-"; //日期分隔符
	var Date1 = nowdate.split(strSeparator);
	var Date2 = start_date.split(strSeparator);
	var oDate1 = new Date(Date1[0], Date1[1]-1, Date1[2]);
    var oDate2 = new Date(Date2[0], Date2[1]-1, Date2[2]);
    if(oDate1.getTime() > oDate2.getTime()){
    	start_flag = "false";
    } else {
    	start_flag = "true";
    }
}

function checkEndDate(start_date,end_date){
	var strSeparator = "-"; //日期分隔符
	var Date1 = start_date.split(strSeparator);
	var Date2 = end_date.split(strSeparator);
	var oDate1 = new Date(Date1[0], Date1[1]-1, Date1[2]);
    var oDate2 = new Date(Date2[0], Date2[1]-1, Date2[2]);
    if(oDate1.getTime() > oDate2.getTime()){
    	end_flag = "false";
    } else {
    	end_flag = "true";
    }
}

function getDays(strDateStart,strDateEnd){
   var strSeparator = "-"; //日期分隔符
   var oDate1;
   var oDate2;
   var iDays;
   oDate1= strDateStart.split(strSeparator);
   oDate2= strDateEnd.split(strSeparator);
   var strDateS = new Date(oDate1[0], oDate1[1]-1, oDate1[2]);
   var strDateE = new Date(oDate2[0], oDate2[1]-1, oDate2[2]);
   iDays = parseInt(Math.abs(strDateS - strDateE ) / 1000 / 60 / 60 /24);//把相差的毫秒数转换为天数 
   return iDays ;
}

function checkTime(start_time,end_time){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	var strSeparator = ":"; //日期分隔符
	var oTime1;
	var oTime2;
	var Millisecond;
	oTime1= start_time.split(strSeparator);
	oTime2= end_time.split(strSeparator);
	var strDateS = new Date(year, month, strDate, oTime1[0], oTime1[1], oTime1[2]);
	var strDateE = new Date(year, month, strDate, oTime2[0], oTime2[1], oTime2[2]);
	if(strDateS.getTime() >= strDateE.getTime()){
		time_flag = "false";
    } else {
    	time_flag = "true";
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
	if (hours >= 0 && hours <= 9) {
		hours = "0" + hours;
	}
	if (minutes >= 0 && minutes <= 9) {
		minutes = "0" + minutes;
	}
	if (seconds >= 0 && seconds <= 9) {
		seconds = "0" + seconds;
	}
	var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate+" "+ hours + seperator2 + minutes + seperator2 + seconds;
	return currentdate;
}

function getnowdate(){
	var date = new Date();
	var seperator1 = "-";
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
	return currentdate;
}

function getCompKey(){
	return comp_key;
}

function getUserValue(){
	return user_value;
}

function getStartDate(){
	return start_date;
}

function getEndDate(){
	return end_date;
}

function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

function HiddenAuth(){
	$("#hid_authorize").css({'display':'block'});
	$("#authorize").css({'display':'none'});
	if(($(window).width() - 70) / 2<400){
		$('#main').css({'max-height':$(window).height()-42,'width':$(window).width()});
		$('#key').css({'left' : 60, 'max-height' : $(window).height() - 77, 'width' : 403 });
		$('#lock').css({ 'left' : 465, 'max-height' : $(window).height() - 77, 'width' : 400});
		$('#authorize').css({ 'height' : $(window).height() - 97});
		$('#hid_authorize').css({ 'height' : $(window).height() - 97});
	}else{
		$('#main').css({'max-height':$(window).height()-42,'width':$(window).width()});
		$('#key').css({'left' : 62, 'max-height' : $(window).height() - 77, 'width' : ($(window).width() - 70) / 2 });
		$('#lock').css({ 'left' : ($(window).width() - 70) / 2 +64, 'max-height' : $(window).height() - 77, 'width' : ($(window).width() - 80) / 2+1 });
		$('#authorize').css({ 'height' : $(window).height() - 97});
		$('#hid_authorize').css({ 'height' : $(window).height() - 97});
	}
}
function ShowAuth(){
	$("#authorize").css({'display':'block'});
	$("#hid_authorize").css({'display':'none'});
	if(($(window).width() - 300) / 2<400){
		$('#main').css({'max-height':$(window).height()-42,'width':$(window).width()});
		$('#key').css({'left' : 280, 'max-height' : $(window).height() - 77, 'width' : 403 });
		$('#lock').css({ 'left' : 685, 'max-height' : $(window).height() - 77, 'width' : 400});
		$('#authorize').css({ 'height' : $(window).height() - 97});
		$('#hid_authorize').css({ 'height' : $(window).height() - 97});
	}else{
		$('#main').css({'max-height':$(window).height()-42,'width':$(window).width()});
		$('#key').css({'left' : 280, 'max-height' : $(window).height() - 77, 'width' : ($(window).width() - 300) / 2+3 });
		$('#lock').css({ 'left' : ($(window).width() - 300) / 2 +285, 'max-height' : $(window).height() - 77, 'width' : ($(window).width() - 300) / 2+5 });
		$('#authorize').css({ 'height' : $(window).height() - 97});
		$('#hid_authorize').css({ 'height' : $(window).height() - 97});
	}
}