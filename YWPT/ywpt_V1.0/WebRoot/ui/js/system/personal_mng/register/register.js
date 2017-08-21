var apply_key;
var currentpage;
var index = parent.layer.getFrameIndex(window.name);
var i18Frame = parent.parent.window.frames["i18nframe"];
var apply_pic = "";
var picindex = null;

$(function(){
	currentpage = parent.getCurrentPage();
	apply_key = parent.getApply_key();
	
	$.ajax({
		type: "POST",
		url: "../../../../applyment/getApplymentByKey",
		data: {
			"apply_key":apply_key
		},
		success:function(returnedData1){
			var jo1 = eval("("+returnedData1+")");
			$("#apply_comp").val(jo1.apply_comp);
			$("#apply_accont").val(jo1.apply_accont);
			$("#apply_machine_code").val(jo1.apply_machine_code);
			$("#apply_name").val(jo1.apply_name);
			$("#apply_phone").val(jo1.apply_phone);
			$("#apply_email").val(jo1.apply_email);
			$("#apply_time").val(jo1.apply_time);
			$("#review_id").val(jo1.review_name+"("+jo1.review_id+")");
			$("#review_time").val(jo1.review_time);
			$("#apply_remark").val(jo1.apply_remark);
			textarealength("#apply_remark",100);
			apply_pic = "../../../../"+jo1.apply_pic;
		}
	});
	
	$("#ok").click(function(){
		parent.flush(currentpage);
		parent.layer.close(index);
	});
});

function openPic(){
	var help = apply_pic.split(";");
	var width = help[1];
	var height = help[2];
	var wratio = 1200/width;
	var hratio = 500/height;
	var ratio;
	if(wratio<1||hratio<1){
		ratio = Math.min(wratio,hratio);
		width = width*ratio;
		height = height*ratio;
	}
	picindex = parent.layer.open({
	    type: 1,
	    title: false,
//	    closeBtn: 0,
	    area: [width+'px', height+'px'],
	    skin: 'layui-layer-nobg', //没有背景色
	    shadeClose: true,
	    content: '<img src='+help[0]+' onerror="openDefault()" width="'+width+'px" height="'+height+'px"/>'
	});
	parent.setPicindex(picindex);
}
