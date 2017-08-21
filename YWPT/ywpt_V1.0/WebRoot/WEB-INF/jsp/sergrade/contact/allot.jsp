<%@ page language="java" pageEncoding="UTF-8"%>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">责任资产配置</h4>
	<form id="inputForm" class="form-horizontal" action="${root}/sergrade/contact/doAllot" method="post" style="margin:0;padding:0;">
		<input type="hidden" name="menuid" value="${menuid}" /> 
		<input type="hidden" name="contactid" value="${contactInfo.id}" />
		<input type="hidden" name="userId" value="${contactInfo.userid}" />
		<input type="hidden" name="page" value="${page}" />
		
		<input type="hidden" id=allotProperty name="allotProperty"/>
		
		<div class="mar_5">
			<div class="tab-pane row-fluid" style="height:100%;">
				<table class="table table-border-rl table-border-bot bukong-table">
					<tr>
						<td class="device_td_bg3">所属公司：</td>
						<td><tags:xiangxuncache keyName="FactoryInfo" id="${contactInfo.factoryid}"></tags:xiangxuncache></td>
					</tr>
					<tr>
						<td class="device_td_bg3">姓 名：</td>
						<td>${contactInfo.userName}</td>
					</tr>
					<tr>
						<td class="device_td_bg3">手 机 号：</td>
						<td>${contactInfo.mobile}</td>
					</tr>
				</table>
			</div>	
			
			<div id="common_div">
				<!-- 选项卡 开始 -->	  
				<ul class="nav nav-tabs" style="padding-left:10px;">
				    <li class="active"><a href="#tab1" data-toggle="tab" style="font-weight: bold;">卡口</a></li>
				    <!-- <li><a href="#tab2" data-toggle="tab" style="font-weight: bold;">监控</a></li> -->
				    <li><a href="#tab3" data-toggle="tab" style="font-weight: bold;">服务器</a></li>
				    <li><a href="#tab4" data-toggle="tab" style="font-weight: bold;">数据库</a></li>
				    <li><a href="#tab5" data-toggle="tab" style="font-weight: bold;">FTP</a></li>
				    <li><a href="#tab6" data-toggle="tab" style="font-weight: bold;">平台</a></li>
				    <li><a href="#tab7" data-toggle="tab" style="font-weight: bold;">机柜</a></li>
				</ul>
				<div class="tab-content" style="overflow:auto">
					<!-- tab1页面 -->
					<div class="tab-pane mar_5 active" id="tab1" style="min-height:500px;">
						<div style="width: 100%;height:500px;overflow-y: auto;overflow-x:hidden;" id="device_div">
							<c:if test="${deviceList == null || deviceList =='[]'}">
								<div class="sort_name_box row" style="height:440px;margin:0 0 5px 0; padding:5px; background-color:white; border:solid 1px #D5D5D5; text-align: center;">
									<font color="red">无卡口设备信息。</font>
								</div>
							</c:if>
					  		<c:if test="${deviceList != null && deviceList !='[]'}">
					  			<p class="sort_line" style="background:#eaf0f3; height:28px;border:solid 1px #D5D5D5;border-bottom: 0;padding-top: 6px;" >
					      			<input type="checkbox" id="all_device" value="-1"style="cursor: hand;margin-top:-2px;margin-left: 6px;" />全选
						      	</p> 
						      	<div class="sort_name_box row" style="height:440px;margin:0 0 5px 0; padding:5px; background-color:white; border:solid 1px #D5D5D5;">
							          <ul class="sort_name_list">
							            <c:forEach items="${deviceList}" var="device">
							            	 <li style="width:120px;height:22px;float:left;overflow:hidden;" title="${device.name}">
								             	<c:if test="${device.hasAllot == true}">
								             		<input type="checkbox" name="select-chk" value="${device.id}" checked="checked"/>${device.name}
								             	</c:if>
								             	<c:if test="${device.hasAllot == false}">
								             		<input type="checkbox" name="select-chk" value="${device.id}" />${device.name}
								             	</c:if>
								             </li>
							            </c:forEach>
							          </ul>
						      	</div>
							</c:if>
						</div> 
					
					</div>
					<!-- tab2页面 -->
					<div class="tab-pane mar_5" style="height:500px;overflow-x:hidden;overflow-y: auto;" id="tab2" >
						<div style="width: 100%;height:500px;overflow-y: auto;overflow-x:hidden;" id="video_div">
							<c:if test="${videoList == null || videoList =='[]'}">
								<div class="sort_name_box row" style="height:440px;margin:0 0 5px 0; padding:5px; background-color:white; border:solid 1px #D5D5D5; text-align: center;">
									<font color="red">无监控设备信息。</font>
								</div>
							</c:if>
					  		<c:if test="${videoList != null && videoList !='[]'}">
					  			<p class="sort_line" style="background:#eaf0f3; height:28px;border:solid 1px #D5D5D5;border-bottom: 0;padding-top: 6px;" >
					      			<input type="checkbox" id="all_video" value="-1"style="cursor: hand;margin-top:-2px;margin-left: 6px;" />全选
						      	</p> 
						      	<div class="sort_name_box row" style="height:440px;margin:0 0 5px 0; padding:5px; background-color:white; border:solid 1px #D5D5D5;">
							          <ul class="sort_name_list">
							            <c:forEach items="${videoList}" var="video">
								             <li style="width:120px;height:22px;float:left;overflow:hidden;" title="${video.name}">
								             	<c:if test="${video.hasAllot == true}">
								             		<input type="checkbox" name="select-chk" value="${video.id}" checked="checked"/>${video.name}
								             	</c:if>
								             	<c:if test="${video.hasAllot == false}">
								             		<input type="checkbox" name="select-chk" value="${video.id}" />${video.name}
								             	</c:if>
								             </li>
							            </c:forEach>
							          </ul>
						      	</div>
							</c:if>
						</div> 
					</div>
					
					<!-- tab3页面 -->
					<div class="tab-pane mar_5" style="height:500px;overflow-x:hidden;overflow-y: auto;" id="tab3" >
						<div style="width: 100%;height:500px;overflow-y: auto;overflow-x:hidden;" id="server_div">
							<c:if test="${serverList == null || serverList =='[]'}">
								<div class="sort_name_box row" style="height:440px;margin:0 0 5px 0; padding:5px; background-color:white; border:solid 1px #D5D5D5; text-align: center;">
									<font color="red">无服务器设备信息。</font>
								</div>
							</c:if>
					  		<c:if test="${serverList != null && serverList !='[]'}">
					  			<p class="sort_line" style="background:#eaf0f3; height:28px;border:solid 1px #D5D5D5;border-bottom: 0;padding-top: 6px;" >
					      			<input type="checkbox" id="all_server" value="-1"style="cursor: hand;margin-top:-2px;margin-left: 6px;" />全选
						      	</p> 
						      	<div class="sort_name_box row" style="height:440px;margin:0 0 5px 0; padding:5px; background-color:white; border:solid 1px #D5D5D5;">
							          <ul class="sort_name_list">
							            <c:forEach items="${serverList}" var="server">
							            	 <li style="width:120px;height:22px;float:left;overflow:hidden;" title="${server.name}">
								             	<c:if test="${server.hasAllot == true}">
								             		<input type="checkbox" name="select-chk" value="${server.id}" checked="checked"/>${server.name}
								             	</c:if>
								             	<c:if test="${server.hasAllot == false}">
								             		<input type="checkbox" name="select-chk" value="${server.id}" />${server.name}
								             	</c:if>
								             </li>
							            </c:forEach>
							          </ul>
						      	</div>
							</c:if>
						</div> 
					</div>
					<!-- tab4页面 -->
					<div class="tab-pane mar_5" style="height:500px;overflow-x:hidden;overflow-y: auto;" id="tab4" >
						<div style="width: 100%;height:500px;overflow-y: auto;overflow-x:hidden;" id="database_div">
							<c:if test="${databaseList == null || databaseList =='[]'}">
								<div class="sort_name_box row" style="height:440px;margin:0 0 5px 0; padding:5px; background-color:white; border:solid 1px #D5D5D5; text-align: center;">
									<font color="red">无数据库信息。</font>
								</div>
							</c:if>
					  		<c:if test="${databaseList != null && databaseList !='[]'}">
					  			<p class="sort_line" style="background:#eaf0f3; height:28px;border:solid 1px #D5D5D5;border-bottom: 0;padding-top: 6px;" >
					      			<input type="checkbox" id="all_database" value="-1"style="cursor: hand;margin-top:-2px;margin-left: 6px;" />全选
						      	</p> 
						      	<div class="sort_name_box row" style="height:440px;margin:0 0 5px 0; padding:5px; background-color:white; border:solid 1px #D5D5D5;">
							          <ul class="sort_name_list">
							            <c:forEach items="${databaseList}" var="database">
								             <li style="width:120px;height:22px;float:left;overflow:hidden;" title="${database.name}">
								             	<c:if test="${database.hasAllot == true}">
								             		<input type="checkbox" name="select-chk" value="${database.id}" checked="checked"/>${database.name}
								             	</c:if>
								             	<c:if test="${database.hasAllot == false}">
								             		<input type="checkbox" name="select-chk" value="${database.id}" />${database.name}
								             	</c:if>
								             </li>
							            </c:forEach>
							          </ul>
						      	</div>
							</c:if>
						</div> 
					</div>
					<!-- tab5页面 -->
					<div class="tab-pane mar_5" style="height:500px;overflow-x:hidden;overflow-y: auto;" id="tab5" >
						<div style="width: 100%;height:500px;overflow-y: auto;overflow-x:hidden;" id="ftp_div">
							<c:if test="${ftpList == null || ftpList =='[]'}">
								<div class="sort_name_box row" style="height:440px;margin:0 0 5px 0; padding:5px; background-color:white; border:solid 1px #D5D5D5; text-align: center;">
									<font color="red">无FTP信息。</font>
								</div>
							</c:if>
					  		<c:if test="${ftpList != null && ftpList !='[]'}">
					  			<p class="sort_line" style="background:#eaf0f3; height:28px;border:solid 1px #D5D5D5;border-bottom: 0;padding-top: 6px;" >
					      			<input type="checkbox" id="all_ftp" value="-1"style="cursor: hand;margin-top:-2px;margin-left: 6px;" />全选
						      	</p> 
						      	<div class="sort_name_box row" style="height:440px;margin:0 0 5px 0; padding:5px; background-color:white; border:solid 1px #D5D5D5;">
							          <ul class="sort_name_list">
							            <c:forEach items="${ftpList}" var="ftp">
							            	 <li style="width:120px;height:22px;float:left;overflow:hidden;" title="${ftp.name}">
								             	<c:if test="${ftp.hasAllot == true}">
								             		<input type="checkbox" name="select-chk" value="${ftp.id}" checked="checked"/>${ftp.name}
								             	</c:if>
								             	<c:if test="${ftp.hasAllot == false}">
								             		<input type="checkbox" name="select-chk" value="${ftp.id}" />${ftp.name}
								             	</c:if>
								             </li>
							            </c:forEach>
							          </ul>
						      	</div>
							</c:if>
						</div> 
					</div>
					<!-- tab6页面 -->
					<div class="tab-pane mar_5" style="height:500px;overflow-x:hidden;overflow-y: auto;" id="tab6" >
						<div style="width: 100%;height:500px;overflow-y: auto;overflow-x:hidden;" id="project_div">
							<c:if test="${projectList == null || projectList =='[]'}">
								<div class="sort_name_box row" style="height:440px;margin:0 0 5px 0; padding:5px; background-color:white; border:solid 1px #D5D5D5; text-align: center;">
									<font color="red">无平台信息。</font>
								</div>
							</c:if>
					  		<c:if test="${projectList != null && projectList !='[]'}">
					  			<p class="sort_line" style="background:#eaf0f3; height:28px;border:solid 1px #D5D5D5;border-bottom: 0;padding-top: 6px;" >
					      			<input type="checkbox" id="all_project" value="-1"style="cursor: hand;margin-top:-2px;margin-left: 6px;" />全选
						      	</p> 
						      	<div class="sort_name_box row" style="height:440px;margin:0 0 5px 0; padding:5px; background-color:white; border:solid 1px #D5D5D5;">
							          <ul class="sort_name_list">
							            <c:forEach items="${projectList}" var="project">
							            	 <li style="width:120px;height:22px;float:left;overflow:hidden;" title="${project.name}">
								             	<c:if test="${project.hasAllot == true}">
								             		<input type="checkbox" name="select-chk" value="${project.id}" checked="checked"/>${project.name}
								             	</c:if>
								             	<c:if test="${project.hasAllot == false}">
								             		<input type="checkbox" name="select-chk" value="${project.id}" />${project.name}
								             	</c:if>
								             </li>
							            </c:forEach>
							          </ul>
						      	</div>
							</c:if>
						</div> 
					</div>
					<!-- tab7页面 -->
					<div class="tab-pane mar_5" style="height:500px;overflow-x:hidden;overflow-y: auto;" id="tab7" >
						<div style="width: 100%;height:500px;overflow-y: auto;overflow-x:hidden;" id="cab_div">
							<c:if test="${cabList == null || cabList =='[]'}">
								<div class="sort_name_box row" style="height:440px;margin:0 0 5px 0; padding:5px; background-color:white; border:solid 1px #D5D5D5; text-align: center;">
									<font color="red">无机柜信息。</font>
								</div>
							</c:if>
					  		<c:if test="${cabList != null && cabList !='[]'}">
					  			<p class="sort_line" style="background:#eaf0f3; height:28px;border:solid 1px #D5D5D5;border-bottom: 0;padding-top: 6px;" >
					      			<input type="checkbox" id="all_cab" value="-1"style="cursor: hand;margin-top:-2px;margin-left: 6px;" />全选
						      	</p> 
						      	<div class="sort_name_box row" style="height:440px;margin:0 0 5px 0; padding:5px; background-color:white; border:solid 1px #D5D5D5;">
							          <ul class="sort_name_list">
							            <c:forEach items="${cabList}" var="cab">
							            	 <li style="width:120px;height:22px;float:left;overflow:hidden;" title="${cab.name}">
								             	<c:if test="${cab.hasAllot == true}">
								             		<input type="checkbox" name="select-chk" value="${cab.id}" checked="checked"/>${cab.name}
								             	</c:if>
								             	<c:if test="${cab.hasAllot == false}">
								             		<input type="checkbox" name="select-chk" value="${cab.id}" />${cab.name}
								             	</c:if>
								             </li>
							            </c:forEach>
							          </ul>
						      	</div>
							</c:if>
						</div> 
					</div>
				</div>
			</div>
		</div>
		<div class="btn_line">
			<button class="btn btn-info mar_r10" type="submit">保存</button>
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
		</div>
	</form>
</div>
<script>
	$(document).ready(function() {
		//聚焦第一个输入框
		//为inputForm注册validate函数
		$("#inputForm").validate();
	});
	
	$("#all_device").click(function(){
		var checked = $(this).attr("checked");
	    $("#device_div :checkbox").each(function(){
	   	    if((checked=="checked") || checked){
	  			$(this).attr("checked",checked);
	   	    }else{
	   	    	$(this).removeAttr("checked");
	   	    }
	   });
	});
	
	$("#all_video").click(function(){
		var checked = $(this).attr("checked");
	    $("#video_div :checkbox").each(function(){
	   	    if((checked=="checked") || checked){
	  			$(this).attr("checked",checked);
	   	    }else{
	   	    	$(this).removeAttr("checked");
	   	    }
	   });
	});
	
	$("#all_server").click(function(){
		var checked = $(this).attr("checked");
	    $("#server_div :checkbox").each(function(){
	   	    if((checked=="checked") || checked){
	  			$(this).attr("checked",checked);
	   	    }else{
	   	    	$(this).removeAttr("checked");
	   	    }
	   });
	});
	
	$("#all_database").click(function(){
		var checked = $(this).attr("checked");
	    $("#database_div :checkbox").each(function(){
	   	    if((checked=="checked") || checked){
	  			$(this).attr("checked",checked);
	   	    }else{
	   	    	$(this).removeAttr("checked");
	   	    }
	   });
	});
	
	$("#all_cab").click(function(){
		var checked = $(this).attr("checked");
	    $("#cab_div :checkbox").each(function(){
	   	    if((checked=="checked") || checked){
	  			$(this).attr("checked",checked);
	   	    }else{
	   	    	$(this).removeAttr("checked");
	   	    }
	   });
	});
	
	$("#all_ftp").click(function(){
		var checked = $(this).attr("checked");
	    $("#ftp_div :checkbox").each(function(){
	   	    if((checked=="checked") || checked){
	  			$(this).attr("checked",checked);
	   	    }else{
	   	    	$(this).removeAttr("checked");
	   	    }
	   });
	});
	
	$("#all_project").click(function(){
		var checked = $(this).attr("checked");
	    $("#project_div :checkbox").each(function(){
	   	    if((checked=="checked") || checked){
	  			$(this).attr("checked",checked);
	   	    }else{
	   	    	$(this).removeAttr("checked");
	   	    }
	   });
	});
	
	$("#inputForm").submit(function(){
		var code = "";
		var arr = document.getElementsByName("select-chk");
		for(i = 0; i < arr.length; i++){
			if(arr[i].checked){
				code += arr[i].value + ",";
			}
		}
		$("#allotProperty").val(code.substring(0,code.length-1));
		/* if($("#allotProperty").val() == ''){
			alert("请选择责任设备！");
			return false;
		} */
	});


	function showList() {
		window.location.href = "${root}/sergrade/contact/list/${menuid}/";
	}
</script>