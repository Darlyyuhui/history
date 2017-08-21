var comp_key;
var start_date;
var end_date;
var user_value;
var index = parent.layer.getFrameIndex(window.name);
$(function(){
	comp_key = parent.getCompKey();
	start_date = parent.getStartDate();
	end_date = parent.getEndDate();
	user_value = parent.getUserValue();
	layer.load(2,{shade:0.3});
	$.ajax({
		type: "POST",
		url: "../../../lockauthassist/getConflictLockAuth",
		data: {
			"start_date":start_date,
			"end_date":end_date,
			"user_value":user_value,
			"comp_key":comp_key
		},
		success: function(returnedData){
			var jar0= eval("("+returnedData+")");
			var str0 = "";//显示列表
			$(".table tbody").empty();
			for(var i=0;i<jar0.length;i++){
				str0 += "<tr class='text-c'>";
				str0 += "<td style='display: none;'><input type='checkbox' value='"+jar0[i].record_key+"' name='cbox'/></td>";
				str0 += "<td>"+(1+i)+"</td>"+
				"<td style='max-width:70px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].authorize_name+"'>"+jar0[i].authorize_name+"</td>" +
				"<td style='max-width:70px;white-space:nowrap;overflow:hidden;text-overflow: ellipsis;' title='"+jar0[i].user_name+"("+jar0[i].user_id+")'>"+jar0[i].user_name+"("+jar0[i].user_id+")</td>"+
				"<td title='"+jar0[i].start_date+"~"+jar0[i].end_date+"'>"+jar0[i].start_date+"~"+jar0[i].end_date+"</td>" +
				"<td title='"+jar0[i].start_time+"~"+jar0[i].end_time+"'>"+jar0[i].start_time+"~"+jar0[i].end_time+"</td>" +
				"<td title='"+jar0[i].authorize_time+"'>"+jar0[i].authorize_time+"</td>";
			}
			$(".table tbody").append(str0);
			setTimeout(function(){
				layer.closeAll('loading');
			},300);
		}
	});
	$(".btn-danger").click(function(){
		var obj = document.getElementsByName("cbox");
		var chk_value=[];
		for(var i=0;i<obj.length;i++){
			chk_value.push(obj[i].value);
		}
		$.ajax({
			type: "POST",
			url: "../../../lockauth/closeLockAuthRecord",
			data: {
				"chk_value[]":chk_value,
				"comp_key":comp_key
			},
			success:function(returnedData){
				var jo0= eval("("+returnedData+")");
				if(jo0.check=="true"){
					parent.Authorize();
					parent.layer.close(index);
				}
			}
		});
	});
	
	
	$(".btn-default").click(function(){
		parent.layer.close(index);
	});
});