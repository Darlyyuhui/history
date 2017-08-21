var currentpage;
var id_message;
var name_message;
var model_message;
var lock_pic="";
var comp_key;
var unit_key = 0;
var add_name;
var index = parent.layer.getFrameIndex(window.name);
var i18Frame = parent.parent.window.frames["i18nframe"];
var autoComplete=new AutoComplete('unit_name','auto',[]);
var proposals = new Array();

$(function(){
	currentpage = parent.getCurrentPage();
	comp_key = $("#comp_key").val();
	document.getElementById("lock_id").focus();
	
	$("#addlock").click(function(){
		var lock_id = trim($("#lock_id").val());
		lock_id = parseInt(lock_id);
		var lock_name = trim($("#lock_name").val());
		var lock_model = trim($("#lock_model").val());
		var unit_name = trim($("#unit_name").val());
		var lock_remark = $("#lock_remark").val();
		if(0 == lock_pic.length)
			lock_pic = "images/blank.png;200;200";
		checkName();
		checkModel();
		getUnitKey(unit_name);
		if("null" == id_message){
			layer.tips(i18Frame.getpleaseinput()+' '+i18Frame.getlocknum(),'#lock_id',{
				tips:3,time:2000
			});
		}else if("error" == id_message){
			layer.tips('1~99999','#lock_id',{
				tips:3,time:2000
			});
		}else if("has" == id_message){
			layer.tips(i18Frame.getlocknum()+' '+i18Frame.getexists(),'#lock_id',{
				tips:3,time:2000
			});
		}else{
			if("error" == name_message){
				layer.tips('0~20'+i18Frame.getlength(),'#lock_name',{
					tips:3,time:2000
				});
			}else{
				if(0==unit_key){
					layer.tips(i18Frame.getparentunit()+' '+i18Frame.getnonexists(),'#unit_name',{
						tips:3,time:2000
					});
				}else{
					if("error" == model_message){
						layer.tips('0~20'+i18Frame.getlength(),'#lock_model',{
							tips:3,time:2000
						});
					}else{
						if(50<lock_remark.length){
							layer.tips('0~50'+i18Frame.getlength(),'#lock_remark',{
								tips:1,time:2000
							});
						}else{
							parent.layer.load(2,{shade:0.3});
							$.ajax({
								type: "POST",
								url: "../../../../lock/insertLock",
								data: {
									"lock_id": lock_id,
									"lock_name": lock_name,
									"unit_key": unit_key,
									"lock_model": lock_model,
									"lock_pic": lock_pic,
									"lock_remark": lock_remark,
									"comp_key": comp_key
								},
								success:function(returnedData){
									var jo0 = eval("("+returnedData+")");
									if(jo0.check =="true"){
										parent.layer.closeAll('loading');
										parent.layer.msg(i18Frame.getadd()+' '+i18Frame.getsuccess()+'!',{icon:1,shade:0.3,time:1000});
										parent.flush(currentpage);
										parent.$("input[type='checkbox']").prop("checked",false);
										parent.layer.close(index);
									}else{
										parent.layer.closeAll('loading');
										parent.layer.msg(i18Frame.getadd()+' '+i18Frame.getfailed()+'!',{icon:2,shade:0.3,time:1000});
										return;
									}
								}
							});
						}
					}
				}
			}
		}
	});
	
	$(".btn-default").click(function(){
		parent.$("input[type='checkbox']").prop("checked",false);
		parent.layer.close(index);
	});
});

function checkId(){
	var lock_id = trim($("#lock_id").val());
	var reg1 = /^[0-9]*$/;
	if(arr=lock_id.match(reg1)){
		var lock_id1 = parseInt(lock_id);
		lock_id = lock_id1.toString();
		if(""==lock_id.replace(/\s/g,"")){
			id_message = "null";
		}else if(0==lock_id1){
			id_message = "error";
		}else{
			var reg = /^\d{1,5}?$/;
			if(arr=lock_id.match(reg)){
				$.ajax({
					type: "POST",
					url: "../../../../lock/checkId",
					data: {
						"param": lock_id1,
						"comp_key": comp_key
					},
					success: function(returnedData){
						var jo0 = eval("("+returnedData+")");
						if(jo0.status=="y"){
							id_message = "ok";
						}else{
							id_message = "has";
						}
					}
				});
				ti=1;
				return true;
			}else{
				id_message = "error";
				ti=0;
				return false;
			}
		}
	}else{
		id_message = "error";
		ti=0;
		return false;
	}
}

function checkName(){
	lock_name = trim($("#lock_name").val());
	if(""==lock_name.replace(/\s/g,"")){
		name_message = "null";
	}else{
		if(lock_name.length<=20&&lock_name.length>=0){
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

function checkModel(){
	lock_model = trim($("#lock_model").val());
	if(lock_model.length<=20&&lock_model.length>=0){
		model_message = "ok";
		ti=1;
		return true;
	}else{
		model_message = "error";
		ti=0;
		return false;
	}
}

function searchUnitName(event){
	var unit_name = trim($("#unit_name").val());
	$.ajax({
		type: "POST",
	    url: "../../../../unit/findUnitNameLike",
	    data: {
	    	"unit_name":unit_name,
	    	"comp_key": comp_key
    	},
	    success: function(returnedData){
	    	var jar = eval( "("+returnedData+")" );
	    	proposals = [];
	    	if(0!=jar.length){
	    		for(var i=0;i<jar.length;i++){
	    			proposals.push(jar[i]);
	    		}
	    	}else{
	    		proposals = [];
	    	}
	    	autoComplete=new AutoComplete('unit_name','auto',proposals);
	    	autoComplete.start(event);
	    }
	});
}

function getUnitKey(unit_name){
	$.ajax({
		type: "POST",
	    url: "../../../../unit/getUnitByName",
	    data: {
	    	"unit_name":unit_name,
	    	"comp_key": comp_key
    	},
	    success: function(returnedData){
	    	var jar = eval( "("+returnedData+")" );
	    	if(null!=jar){
	    		unit_key = jar.unit_key;
	    	}else{
	    		unit_key = 0;
	    	}
	    }
	});
}

function addUnit(){
	autoComplete.hiddenDIV();
	add_name = trim($("#unit_name").val());
	layer.confirm(i18Frame.getdetermine_add_unit()+'?',{
		btn: [i18Frame.getok(),i18Frame.getcancel()],icon:3,title:i18Frame.getinfo(),closeBtn:1
	},function(index1){
		layer.close(index1);
		layer.open({
			type: 2,
			title: i18Frame.getadd()+i18Frame.getparentunit(),
			shadeClose: true,
			maxmin: false,
			
			area:['800px','545px'],
			content: ['add_lockunit.jsp']
		});
	},function(){
		
	});
}

function getAddName(){
	return add_name;
}

function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

function ajaxFileUpload(){
	var filepath = $("#upfile2").val();
	var extStart = filepath.lastIndexOf(".");
	var ext = filepath.substring(extStart,filepath.length).toLowerCase();
	if((ext!=".jpg")&&(ext!=".jpeg")&&(ext!=".bmp")&&(ext!=".png")&&(ext!=".gif")){
		layer.alert(i18Frame.getchoosepic()+"(jpg jpeg bmp png gif)!", {icon: 0, title:i18Frame.getinfo(),btn: [i18Frame.getok()] });
	}else{
		var loading = layer.load(1, {shade: [0.5, '#000', true],offset: ['50%','50%']});
		$.ajaxFileUpload
		(
			{
				url:'../../../../imgUpload/import',//用于文件上传的服务器端请求地址
				type: 'post',
				secureuri:false,//一般设置为false
				fileElementId:'upfile2',//文件上传空间的id属性  <input type="file" id="file" name="file" />
				dataType: 'json',//返回值类型 一般设置为json
				data: {
					"comp_key": comp_key
	            },
				success: function (data, status)  //服务器成功响应处理函数
				{
					layer.close(loading);
					var jo = eval("("+data+")");
					document.getElementById("img_1").src = "../../../../"+jo[0].message1;
					document.getElementById("img_1").style.display = "block";
					document.getElementById("img_1").style.height = "220px";
					document.getElementById("img_1").style.width = "220px";
					lock_pic = jo[0].message1+";"+jo[0].widthHeight;
					if(typeof(data.error) != 'undefined')
					{
						if(data.error != '')
							{
								alert(data.error);
							}else
							{
								alert(data.msg);
							}
						}
							
					},
				error: function (data, status, e)//服务器响应失败处理函数
					{
						layer.close(loading);
						alert(e);
					}
			}
		);
	}
}