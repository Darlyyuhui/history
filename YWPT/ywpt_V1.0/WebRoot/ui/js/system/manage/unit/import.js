var currentpage;
var comp_key;
var message_value = ["（1）","（2）","（3）","（4）","（5）","（6）","（7）","（8）"];
$(function(){
	currentpage = parent.getCurrentPage();
	comp_key = $("#comp_key").val();
	 $(document).on("change",".input-file",function(){
			var uploadVal=$(this).val();
			$(this).parent().find("#uploadfile-2").val(uploadVal).focus().blur();
	 });
});

function ajaxFileUpload(){
	var filename = $("#file-2").val();
	if(filename.length>0){
		var filetype = filename.substring(filename.length-3,filename.length);
		filetype = filetype.toLowerCase();
		if("xls" == filetype ){
			layer.load(2,{shade:0.3});
			$.ajaxFileUpload({
	            url : '../../../../importExcel/import', //用于文件上传的服务器端请求地址
	            secureuri : false, //一般设置为false
	            fileElementId : 'file-2', //文件上传空间的id属性  <input type="file" id="file" name="file" />
	            type : 'post',
	            dataType : 'json', //返回值类型 一般设置为json
	            data: {
	            	"exceltype": "2",//exceltype为2时表示导入单元信息
	            	"comp_key": comp_key
	            },
	            success : function(data, status) //服务器成功响应处理函数
	            {
	            	var jo0 = eval("("+data+")");
	            	if(jo0[0].message=='y'){
	            		layer.closeAll('loading');
	            		layer.alert('导入成功!',{
							icon:1,shade:0.3,title:'提示',closeBtn: 0
						});
	            		$('#import_list').css({'display':'block'});
	            		$('#import_message').css({'display':'none'});
	            		var str0 = "";
	            		$(".table tbody").empty();
	            		for(var j=0;j<jo0[0].list.length;j++){
							str0 += "<tr class='text-c'>"+
							"<td title='"+jo0[0].list[j].unit_name+"'>"+jo0[0].list[j].unit_name+"</td>" +
							"<td title='"+jo0[0].list[j].type_name+"'>"+jo0[0].list[j].type_name+"</td>" +
							"<td title='"+jo0[0].list[j].unit_longitude+"'>"+jo0[0].list[j].unit_longitude+"</td>" +
							"<td title='"+jo0[0].list[j].unit_latitude+"'>"+jo0[0].list[j].unit_latitude+"</td>" +
							"<td title='"+jo0[0].list[j].unit_location+"'>"+jo0[0].list[j].unit_location+"</td>" +
							"<td title='"+jo0[0].list[j].unit_remark+"'>"+jo0[0].list[j].unit_remark+"</td>";
						}
	            		$(".table tbody").append(str0);
	            		parent.flush(currentpage);
	            		$(".input-file").change();
	            	}else if(jo0[0].message=='n'){
	            		layer.closeAll('loading');
	            		layer.alert('导入失败!',{
							icon:2,shade:0.3,title:'提示',closeBtn: 0
						});
	            		$('#import_list').css({'display':'none'});
	            		$('#import_message').css({'display':'block'});
	            		var str1 = "";
	            		var count = -1;
	            		$("#message").empty();
	            		if(""!=jo0[0].repeat_message){
	            			count++;
	            			str1 += "<thead>" +
	        				"<tr class='text-l'><th>"+message_value[count]+"单元名称重复</th>" +
	        				"</tr></thead><tbody><tr class='text-l'><td>"+jo0[0].repeat_message+"</td><tr></tbody>";
	            		}
	            		if(""!=jo0[0].rows_message){
	            			count++;
	            			str1 += "<thead>" +
	        				"<tr class='text-l'><th>"+message_value[count]+"单元名称不符合要求</th>" +
	        				"</tr></thead><tbody><tr class='text-l'><td>"+jo0[0].rows_message+"</td><tr></tbody>";
	            		}
	            		if(""!=jo0[0].name_message){
	            			count++;
	            			str1 += "<thead>" +
	        				"<tr class='text-l'><th>"+message_value[count]+"单元名称已存在</th>" +
	        				"</tr></thead><tbody><tr class='text-l'><td>"+jo0[0].name_message+"</td><tr></tbody>";
	            		}
	            		if(""!=jo0[0].type_message){
	            			count++;
	            			str1 += "<thead>" +
	        				"<tr class='text-l'><th>"+message_value[count]+"所属单元类型不存在</th>" +
	        				"</tr></thead><tbody><tr class='text-l'><td>"+jo0[0].type_message+"</td><tr></tbody>";
	            		}
	            		if(""!=jo0[0].longitude_message){
	            			count++;
	            			str1 += "<thead>" +
	        				"<tr class='text-l'><th>"+message_value[count]+"经纬度不符合要求</th>" +
	        				"</tr></thead><tbody><tr class='text-l'><td>"+jo0[0].longitude_message+"</td><tr></tbody>";
	            		}
	            		if(""!=jo0[0].location_message){
	            			count++;
	            			str1 += "<thead>" +
	        				"<tr class='text-l'><th>"+message_value[count]+"单元位置不符合要求</th>" +
	        				"</tr></thead><tbody><tr class='text-l'><td>"+jo0[0].location_message+"</td><tr></tbody>";
	            		}
	            		if(""!=jo0[0].remark_message){
	            			count++;
	            			str1 += "<thead>" +
	        				"<tr class='text-l'><th>"+message_value[count]+"备注超过长度限制</th>" +
	        				"</tr></thead><tbody><tr class='text-l'><td>"+jo0[0].remark_message+"</td><tr></tbody>";
	            		}
	            		$("#message").append(str1);
	            		$(".input-file").change();
	            	}else if(jo0[0].message=='f'){
	            		layer.closeAll('loading');
	            		layer.alert('请选择单元表格模板,录入数据后再导入!',{
							icon:7,shade:0.3,title:'提示',closeBtn: 0
						},function(index1){
							layer.close(index1);
		            		$(".input-file").change();
						});
	            	}
	                if (typeof (data.error) != 'undefined') {
	                    if (data.error != '') {
	                        alert(data.error);
	                    } else {
	                        alert(data.msg);
	                    }
	                }
	            },
	            error : function(data, status, e)//服务器响应失败处理函数
	            {
	                alert(e);
	            }
			});

			return false;

		}else{
			layer.msg('请选择xls表格导入!',{icon:2,shade:0.3,time:1000});
			$(".input-file").change();
		}
	}else{
		layer.msg('请选择文件!',{icon:2,shade:0.3,time:1000});
	}
}
function sure(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.flush(currentpage);
	parent.$("input[type='checkbox']").prop("checked",false);
	parent.layer.close(index);
}
