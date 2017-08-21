var currentpage;
var record_key;
var user_key;
var comp_key;
var index = parent.layer.getFrameIndex(window.name);
var i18Frame = parent.parent.window.frames["i18nframe"];
$(function(){
	record_key = parent.getRecord_key();
	comp_key = $("#comp_key").val();
	$.ajax({
		type: "POST",
		url: "../../../lockauth/getLockAuthByKey",
		data: {
			"record_key": record_key,
			"comp_key":comp_key
		},
		success:function(returnedData){
			var jo0 = eval("("+returnedData+")");
			$("#logmin").val(jo0.start_date);
			$("#logmax").val(jo0.end_date);
			$("#logmin1").val(jo0.start_time);
			$("#logmax1").val(jo0.end_time);
			$("#authorize_time").val(jo0.authorize_time);
			$("#timeslice").val(jo0.timeslice_day);
			user_key = jo0.user_key;
			if("1"==jo0.authorize_flag){
				$("#authorize_name").val(jo0.authorize_name+"("+i18Frame.getalready_disabled()+")");
			}else if("0"==jo0.authorize_flag){
				$("#authorize_name").val(jo0.authorize_name+"("+i18Frame.getalready_enabled()+")");
			}else if("2"==jo0.authorize_flag){
				$("#authorize_name").val(jo0.authorize_name+"("+i18Frame.gettimeout_shutdown()+")");
			}else if("3"==jo0.authorize_flag){
				$("#authorize_name").val(jo0.authorize_name+"("+i18Frame.getpositive_closing()+")");
			}
			if("0"==jo0.timeslice_flag){
				$("#r1").css({'display':'none'});
				$("#0").iCheck('check');
			}else{
				$("#r0").css({'display':'none'});
				$("#1").iCheck('check');
				$('#timeslicespan').css("display","none");
				$('#timeslice').css("display","none");
			}
			$("#user").val(jo0.applied_name+"("+jo0.applied_id+")");
			$("#authorize_remark").val(jo0.authorize_remark);
			textarealength("#authorize_remark",100);
		}
	});
});

function checkAuthorize(){
	return 0;
}

function sure(){
	parent.$("input[type='checkbox']").prop("checked",false);
	parent.layer.close(index);
}


function ChangePagesize(){
	parent.layer.full(index);
}

function HiddenAuth(){
	$("#hid_authorize").css({'display':'block'});
	$("#authorize").css({'display':'none'});
	$('#main').css({'max-height':$(window).height()-2,'width':$(window).width()});
	$('#authorize').css({ 'height' : $(window).height() - 67});
	$('#hid_authorize').css({ 'height' : $(window).height() - 67});
	if(($(window).width() - 70) / 2<400){
		$('#key').css({'left' : 60, 'max-height' : $(window).height() - 37, 'width' : 403 });
		$('#lock').css({ 'left' : 465, 'max-height' : $(window).height() - 37, 'width' : 400});
	}else{
		$('#key').css({'left' : 62, 'max-height' : $(window).height() - 37, 'width' : ($(window).width() - 70) / 2 });
		$('#lock').css({ 'left' : ($(window).width() - 70) / 2 +64, 'max-height' : $(window).height() - 37, 'width' : ($(window).width() - 80) / 2+1 });
	}
}
function ShowAuth(){
	$("#authorize").css({'display':'block'});
	$("#hid_authorize").css({'display':'none'});
	$('#main').css({'max-height':$(window).height()-2,'width':$(window).width()});
	$('#authorize').css({ 'height' : $(window).height() - 67});
	$('#hid_authorize').css({ 'height' : $(window).height() - 67});
	if(($(window).width() - 300) / 2<400){
		$('#key').css({'left' : 280, 'max-height' : $(window).height() - 37, 'width' : 403 });
		$('#lock').css({ 'left' : 685, 'max-height' : $(window).height() - 37, 'width' : 400});
	}else{
		$('#key').css({'left' : 280, 'max-height' : $(window).height() - 37, 'width' : ($(window).width() - 300) / 2+3 });
		$('#lock').css({ 'left' : ($(window).width() - 300) / 2 +285, 'max-height' : $(window).height() - 37, 'width' : ($(window).width() - 300) / 2+5 });
	}
}