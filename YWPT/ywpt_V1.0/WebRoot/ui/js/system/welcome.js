var user_id;
function delAllAuthorize(){
	layer.confirm('确定清空所有授权任务?不可恢复!',{
		btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
	},function(index){
		layer.close(index);
		layer.load(2,{shade:0.3});
		$.ajax({
			type: "POST",
			url: "../../authorize/dropAuthorize",
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				layer.closeAll('loading');
				if(jo0.check =="true"){
					layer.alert('清空授权任务成功!',{icon:1,shade:0.3});
				}
			}
		});
	},function(){
	});
}
function delAllOpenRecord(){
	layer.confirm('确定清空所有开锁记录?不可恢复!',{
		btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
	},function(index){
		layer.close(index);
		layer.load(2,{shade:0.3});
		$.ajax({
			type: "POST",
			url: "../../open/dropOpenRecord",
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				layer.closeAll('loading');
				if(jo0.check =="true"){
					layer.alert('清空开锁记录成功!',{icon:1,shade:0.3});
				}
			}
		});
	},function(){
	});
}
function delAllLog(){
	layer.confirm('确定清空所有系统日志?不可恢复!',{
		btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
	},function(index){
		layer.close(index);
		layer.load(2,{shade:0.3});
		$.ajax({
			type: "POST",
			url: "../../log/delAllLog",
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				layer.closeAll('loading');
				if(jo0.check =="true"){
					layer.alert('清空系统日志成功!',{icon:1,shade:0.3});
				}
			}
		});
	},function(){
	});
}
function delAllKey(){
	layer.confirm('确定清空所有钥匙信息?不可恢复!',{
		btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
	},function(index){
		layer.close(index);
		layer.load(2,{shade:0.3});
		$.ajax({
			type: "POST",
			url: "../../key/delAllKey",
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				layer.closeAll('loading');
				if(jo0.check =="true"){
					layer.alert('清空钥匙信息成功!',{icon:1,shade:0.3});
					$(".key").empty();
					$(".key").append(0);
				}else{
					layer.alert('请先清空所有授权任务及开锁记录!',{icon:7,shade:0.3});
				}
			}
		});
	},function(){
	});
}
function delAllUser(){
	layer.confirm('确定清空所有人员信息?不可恢复!',{
		btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
	},function(index){
		layer.close(index);
		layer.load(2,{shade:0.3});
		$.ajax({
			type: "POST",
			url: "../../user/delAllUser",
			data: {
				"user_id": user_id
			},
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				layer.closeAll('loading');
				if(jo0.check =="true"){
					layer.alert('清空人员信息成功!',{icon:1,shade:0.3});
					$(".user").empty();
					$(".user").append(1);
				}else{
					layer.alert('请先清空所有钥匙信息!',{icon:7,shade:0.3});
				}
			}
		});
	},function(){
	});
}
function delAllBranch(){
	layer.confirm('确定清空所有班组信息?不可恢复!',{
		btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
	},function(index){
		layer.close(index);
		layer.load(2,{shade:0.3});
		$.ajax({
			type: "POST",
			url: "../../branch/delAllBranch",
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				layer.closeAll('loading');
				if(jo0.check =="true"){
					layer.alert('清空班组信息成功!',{icon:1,shade:0.3});
				}else{
					layer.alert('请先清空所有人员信息!',{icon:7,shade:0.3});
				}
			}
		});
	},function(){
	});
}
function delAllCompany(){
	layer.confirm('确定清空所有单位信息?不可恢复!',{
		btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
	},function(index){
		layer.close(index);
		layer.load(2,{shade:0.3});
		$.ajax({
			type: "POST",
			url: "../../company/delAllCompany",
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				layer.closeAll('loading');
				if(jo0.check =="true"){
					layer.alert('清空单位信息成功!',{icon:1,shade:0.3});
				}else{
					layer.alert('请先清空所有班组信息!',{icon:7,shade:0.3});
				}
			}
		});
	},function(){
	});
}
function delAllLock(){
	layer.confirm('确定清空所有锁具信息?不可恢复!',{
		btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
	},function(index){
		layer.close(index);
		layer.load(2,{shade:0.3});
		$.ajax({
			type: "POST",
			url: "../../lock/delAllLock",
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				layer.closeAll('loading');
				if(jo0.check =="true"){
					layer.alert('清空锁具信息成功!',{icon:1,shade:0.3});
					$(".lock").empty();
					$(".lock").append(0);
				}else{
					layer.alert('请先清空所有授权任务及开锁记录!',{icon:7,shade:0.3});
				}
			}
		});
	},function(){
	});
}
function delAllUnit(){
	layer.confirm('确定清空所有单元信息?不可恢复!',{
		btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
	},function(index){
		layer.close(index);
		layer.load(2,{shade:0.3});
		$.ajax({
			type: "POST",
			url: "../../unit/delAllUnit",
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				layer.closeAll('loading');
				if(jo0.check =="true"){
					layer.alert('清空单元信息成功!',{icon:1,shade:0.3});
					$(".unit").empty();
					$(".unit").append(0);
				}else{
					layer.alert('请先清空所有锁具信息!',{icon:7,shade:0.3});
				}
			}
		});
	},function(){
	});
}
function delAllRoute(){
	layer.confirm('确定清空所有线路信息?不可恢复!',{
		btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
	},function(index){
		layer.close(index);
		layer.load(2,{shade:0.3});
		$.ajax({
			type: "POST",
			url: "../../route/delAllRoute",
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				layer.closeAll('loading');
				if(jo0.check =="true"){
					layer.alert('清空线路信息成功!',{icon:1,shade:0.3});
				}else{
					layer.alert('请先清空所有单元信息!',{icon:7,shade:0.3});
				}
			}
		});
	},function(){
	});
}
function delAllArea(){
	layer.confirm('确定清空所有区域信息?不可恢复!',{
		btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
	},function(index){
		layer.close(index);
		layer.load(2,{shade:0.3});
		$.ajax({
			type: "POST",
			url: "../../area/delAllArea",
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				layer.closeAll('loading');
				if(jo0.check =="true"){
					layer.alert('清空区域信息成功!',{icon:1,shade:0.3});
				}else{
					layer.alert('请先清空所有线路信息!',{icon:7,shade:0.3});
				}
			}
		});
	},function(){
	});
}
function delAllLockGroup(){
	layer.confirm('确定清空所有锁组信息?不可恢复!',{
		btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
	},function(index){
		layer.close(index);
		layer.load(2,{shade:0.3});
		$.ajax({
			type: "POST",
			url: "../../group/delAllLockGroup",
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				layer.closeAll('loading');
				if(jo0.check =="true"){
					layer.alert('清空锁组信息成功!',{icon:1,shade:0.3});
				}
			}
		});
	},function(){
	});
}
function delAllUnitType(){
	layer.confirm('确定清空所有单元类型信息?不可恢复!',{
		btn: ['确定','取消'],icon:7,title:'提示',closeBtn:1
	},function(index){
		layer.close(index);
		layer.load(2,{shade:0.3});
		$.ajax({
			type: "POST",
			url: "../../unit_type/delAllUnitType",
			success:function(returnedData){
				var jo0 = eval("("+returnedData+")");
				layer.closeAll('loading');
				if(jo0.check =="true"){
					layer.alert('清空单元类型信息成功!',{icon:1,shade:0.3});
				}else{
					layer.alert('请先清空所有单元信息!',{icon:7,shade:0.3});
				}
			}
		});
	},function(){
	});
}
