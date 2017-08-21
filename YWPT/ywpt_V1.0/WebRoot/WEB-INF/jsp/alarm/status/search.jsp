<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>

<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" 	type="text/css" />
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck-3.5.min.js'/>" type="text/javascript"></script>

<div class="alert alert-block pull-top alert-error" id="alert-div" style="display:none;">
  <p id="alert-content" align="center"></p>
</div>
<div class="alert alert-block pull-top alert-success" id="alertsuc-div" style="display:none;">
  <p id="alertsuc-content" align="center"></p>
</div>
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success">
		<button data-dismiss="alert" class="close">×</button>
		<p id="alert-suc" align="center">${message}</p>
	</div>
	<script>
		$("#message").removeClass("alert-error").addClass("alert-success");
		if(('${message}').indexOf("数据异常") >= 0){
			$("#message").removeClass("alert-success").addClass("alert-error");
		}
		setTimeout('$("#message").hide("slow")', 1200);
	</script>
</c:if>
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
	function reValues(){
		$("#dev-select").val("");
	    $("#assetname").val("");
	    $("#orgNames").val("");
	    $("#search_orgIds").val("");

	    
    }
</script>

<!-- 重置查询输入框结束 -->
<div class="conten_box" style="height:655px;margin-left:180px;">
	<div class="search-area" style="padding:6px 0 4px;">
	  <form class="form-inline" action="${root}/alarm/status/search/${type}/${menuid}/" method="post" style="margin:0;padding:0 6px;">
		<table class="table-pad-td" width="100%">
		  <tr>
		    <td class="search_item_td">资产名称</td>
		    <td style="width: 15%"><input title="支持 资产名称 模糊查询" id="assetname" name="search_assetname" value="${asset.assetname}" maxlength="30" type="text" placeholder="资产名称" /></td>
		     <td class="search_item_td">资产状态</td>
            <td><select id="dev-select" name="search_assetstatus" style="width:76%;">
                <option value="">请选择</option>
                <c:forEach items="${assetstatusList}" var="status">
                   <option value="${status.code}" ${status.code==asset.assetstatus?'selected':''}>${status.name}</option>
                </c:forEach>
              </select></td>
              
              <!-- miaoxu新增 -->
         <c:if test="${type=='device' || type=='server'}">  
            <td class="search_item_td">所属部门</td>
           		<td><input style="width:60%;" type="text" id="orgNames" name="search_orgNames" value="${asset.orgNames}" readonly onclick="showMenu('orgNames');"/>
              	<input type="hidden" id="search_orgIds" name="search_orgIds" value="${asset.orgIds}" />

            </td>
         </c:if>  
              
		    <td colspan="2">
		      <input type="submit" class="btn btn-info" value="查询" style="height:28px;"/>
			  <input onclick="reValues()" type="button" class="btn mar_l10" value="重置" style="height:28px;"/>
		    </td>				  
		  </tr>
		</table>
	  </form>
	  <div id="titlebar" class="btn-group-wrap mar_b5" style="display:block;">
        <div id="opBtns" class="btn-group pull-right">
          <btn:authorBtn menuid="${menuid}" text="导出">
            <button id="exportXls" class="btn btn-small" onclick="exportXls();"><i class="icon-download"></i>导出</button>
          </btn:authorBtn>
          <input type="hidden" id="devicetype" value="${type}">
        </div>
        <div class="clear"></div>
      </div>
	<div class="table_box" style="overflow:auto;">
	   <table class="table table-striped table-bordered table-condensed table-style" id="table">
	   		 <c:if test="${pageList.result[0]==null}"><td><div style="text-align: center;margin-right: 40px;"><font color="red">没有查到结果，请重新查询或者改变查询条件。</font></div></td></c:if>
  		    <c:if test="${pageList.result[0]!=null}">   
			
			<thead>
				<tr>
					<th width="20"></th>
					<th>资产编号</th>
					<th>资产名称</th>
					<th>资产IP</th>
					<c:if test="${type=='device' || type=='server'}">
						<th>所属部门</th>
					</c:if>
					
					
					<c:if test="${type=='server'}">
					<th>CPU状态</th>
					<th>内存状态</th>
					<th>硬盘状态</th>
					</c:if>
					
					<c:if test="${type=='cabinet'}">
					<th>温度</th>
					<th>湿度</th>
					
					<c:if test="${cabinet_pattern=='1'}">
			        <th>烟感</th>
					<th>振动</th>
					<th>AC端子</th>
					<th>AC插座</th>
					</c:if>  
					<th>网络状态</th>
					<th>供电状态</th>
					</c:if>
					
					<c:if test="${type=='device'}">
					<th>供电状态</th>
					<th>网络状态</th>
					<th>运行状态</th>
					</c:if>
					
					<c:if test="${type=='database' || type=='ftp' || type=='project'}">
					<th>网络状态</th>
					<th>运行状态</th>
					</c:if>
					
					<th>服务厂商</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<c:forEach items="${pageList.result}" var="asset">
					<tr >
						<td style="text-align:center;"><input type="checkbox" value="${asset.id}" name="select-chk" /></td>
						<td>${asset.assetcode}</td>
						<td>${asset.assetname}</td>
						<td>${asset.ip}</td>
						<c:if test="${type=='device'|| type=='server'}">		
							<td>${asset.orgName}</td>
						</c:if>
				<c:if test="${type=='device'}">
					<td>
					    <c:if test="${asset.powerStatus=='0'}"><IMG src="${root}/images/computerunknown.gif" title="未知"></c:if>
						<c:if test="${asset.powerStatus=='1'}"><IMG src="${root}/images/computergreen.gif" title="供电正常"></c:if>
						<c:if test="${asset.powerStatus=='3'}"><IMG src="${root}/images/computerred.gif" title="供电异常"></c:if>
					</td>
				</c:if>
				
				
				<c:if test="${type=='cabinet'}">
					<td>
					    <c:if test="${asset.temperature=='2'}"><IMG src="${root}/images/computerunknown.gif" title="未知"></c:if>
						<c:if test="${asset.temperature=='1'}"><IMG src="${root}/images/computergreen.gif" title="温度正常"></c:if>
						<c:if test="${asset.temperature=='0'}"><IMG src="${root}/images/computerred.gif" title="温度异常：${asset.temperaturevalue}"></c:if>
					</td>
					<td>
					    <c:if test="${asset.humidity=='2'}"><IMG src="${root}/images/computerunknown.gif" title="未知"></c:if>
						<c:if test="${asset.humidity=='1'}"><IMG src="${root}/images/computergreen.gif" title="湿度正常"></c:if>
						<c:if test="${asset.humidity=='0'}"><IMG src="${root}/images/computerred.gif" title="湿度异常:${asset.humidityvalue}"></c:if>
					</td>
					<c:if test="${cabinet_pattern=='1'}">
					<td>
					     <c:if test="${asset.smoke=='2'}"><IMG src="${root}/images/computerunknown.gif" title="未知"></c:if>
						<c:if test="${asset.smoke=='1'}"><IMG src="${root}/images/computergreen.gif" title="烟感正常"></c:if>
						<c:if test="${asset.smoke=='0'}"><IMG src="${root}/images/computerred.gif" title="烟感异常"></c:if>
					</td>
					<td>
					    <c:if test="${asset.shock=='2'}"><IMG src="${root}/images/computerunknown.gif" title="未知"></c:if>
						<c:if test="${asset.shock=='1'}"><IMG src="${root}/images/computergreen.gif" title="振动正常"></c:if>
						<c:if test="${asset.shock=='0'}"><IMG src="${root}/images/computerred.gif" title="振动异常:${asset.shockvalue}"></c:if>
					</td>
					<td>
					    <c:if test="${asset.ACterminal=='2'}"><IMG src="${root}/images/computerunknown.gif" title="未知"></c:if>
						<c:if test="${asset.ACterminal=='1'}"><IMG src="${root}/images/computergreen.gif" title="端子正常"></c:if>
						<c:if test="${asset.ACterminal=='0'}"><IMG src="${root}/images/computerred.gif" title="端子异常"></c:if>
					</td>
					<td>
					    <c:if test="${asset.ACvoltage=='2'||asset.ACcurrent=='2'}"><IMG src="${root}/images/computerunknown.gif" title="未知"></c:if> 
						<c:if test="${asset.ACvoltage=='1'&&asset.ACcurrent=='1'}"><IMG src="${root}/images/computergreen.gif" title="电压电流正常"></c:if>
						<c:if test="${asset.ACvoltage=='0'||asset.ACcurrent=='0'}"><IMG src="${root}/images/computerred.gif" title="电压或电流异常，电压：${asset.ACvoltagevalue}，电流：${asset.ACcurrentvalue}"></c:if>
					</td>
					</c:if>
					<td>
					    <c:if test="${asset.netStatus=='2'}"><IMG src="${root}/images/computerunknown.gif" title="未知"></c:if>
						<c:if test="${asset.netStatus=='1'}"><IMG src="${root}/images/computergreen.gif" title="网络正常"></c:if>
						<c:if test="${asset.netStatus=='0'}"><IMG src="${root}/images/computerred.gif" title="网络异常"></c:if>
					</td>
					<td>
					    <c:if test="${asset.powerStatus=='2'}"><IMG src="${root}/images/computerunknown.gif" title="未知"></c:if>
						<c:if test="${asset.powerStatus=='1'}"><IMG src="${root}/images/computergreen.gif" title="供电正常"></c:if>
						<c:if test="${asset.powerStatus=='0'}"><IMG src="${root}/images/computerred.gif" title="已断电"></c:if>
					</td>
					
				</c:if>
			
				    <c:if test="${type=='server'}">
				    <td>
						<c:if test="${asset.cpuStatus=='1'}"><IMG src="${root}/images/computergreen.gif" title="CPU占用60%以下" ></c:if>
						<c:if test="${asset.cpuStatus=='2'}"><IMG src="${root}/images/computeryellow.gif" title="CPU占用60%到90%" ></c:if>
						<c:if test="${asset.cpuStatus=='3'}"><IMG src="${root}/images/computerred.gif" title="CPU占用90%以上"></c:if>
					</td>
					</c:if>
					
					
					<c:if test="${type=='database' || type=='ftp' || type=='project' || type=='device'}">
					<td>
						<c:if test="${asset.netStatus=='0'}"><IMG src="${root}/images/computerunknown.gif" ></c:if>					
					    <c:if test="${asset.netStatus=='1'}"><IMG src="${root}/images/computergreen.gif"  ></c:if>
						<c:if test="${asset.netStatus=='3'}"><IMG src="${root}/images/computerred.gif" ></c:if>
					</td>
					</c:if>
						
						
						
						
					<c:if test="${type=='server'}">
					<td>
						<c:if test="${asset.memoryStatus=='1'}"><IMG src="${root}/images/computergreen.gif" title="内存占用60%以下"></c:if>
						<c:if test="${asset.memoryStatus=='2'}"><IMG src="${root}/images/computeryellow.gif" title="内存占用60%到90%"></c:if>
						<c:if test="${asset.memoryStatus=='3'}"><IMG src="${root}/images/computerred.gif" title="内存占用90%以上"></c:if>
						</td>
					</c:if>
					
					<c:if test="${type=='database' || type=='ftp' || type=='project' || type=='device'}">
					  <td>
					    <c:if test="${asset.dataStatus=='0'}"><IMG src="${root}/images/computerunknown.gif"  title="未知"></c:if>
					    <c:if test="${asset.dataStatus=='1'}"><IMG src="${root}/images/computergreen.gif"  ></c:if>
						<c:if test="${asset.dataStatus=='3'}"><IMG src="${root}/images/computerred.gif" ></c:if>
						</td>
					</c:if>
					<c:if test="${type=='server'}">
					<td>
						<c:if test="${asset.diskStatus=='1'}"><IMG src="${root}/images/computergreen.gif" title="硬盘占用60%以下"></c:if>
						<c:if test="${asset.diskStatus=='2'}"><IMG src="${root}/images/computeryellow.gif" title="硬盘占用60%到90%"></c:if>
						<c:if test="${asset.diskStatus=='3'}"><IMG src="${root}/images/computerred.gif" title="硬盘占用90%以上"></c:if>
					</td>
					</c:if>
				   
				   <c:if test="${type=='cabinet'}">
				   		<td><tags:xiangxuncache keyName="FactoryInfo" id="${asset.serviceid}" ></tags:xiangxuncache></td>
				   </c:if>	
					<c:if test="${type !='cabinet'}">
				   		<td><tags:xiangxuncache keyName="FactoryInfo" id="${asset.factoryId}" ></tags:xiangxuncache></td>
				   </c:if>		
						
						<td class="center">
							<c:if test="${type=='server'}">
								<btn:authorBtn menuid="${menuid}" text="查看">
									<a href="javascript:viewById('${asset.id}')"> <i class="icon-th-list"></i>查看</a>
								</btn:authorBtn>
							</c:if>
							
							<c:if test="${asset.payoutstatus == '0'}">
							<c:if test="${type=='server'}">
								<c:if test="${asset.cpuStatus!='1' || asset.memoryStatus!='1' || asset.diskStatus!='1'}">
									<btn:authorBtn menuid="${menuid}" text="派发工单">
										<a href="javascript:workorderAssign('${asset.id}', '${asset.assettype}')"> <i class="icon-share"></i>派发工单</a>
									</btn:authorBtn>
								</c:if>
								
							</c:if>
							<c:if test="${type=='database' || type=='ftp' || type=='project'|| type=='device'}">
								<c:if test="${asset.netStatus!='1' || asset.dataStatus!='1'}">
									<btn:authorBtn menuid="${menuid}" text="派发工单">
										<a href="javascript:workorderAssign('${asset.id}', '${asset.assettype}')"> <i class="icon-share"></i>派发工单</a>
									</btn:authorBtn>
								</c:if>
							</c:if>
							<c:if test="${type=='cabinet'}">
								<c:if test="${asset.shock!='1' || asset.ACvoltage!='1'||asset.ACcurrent!='1'||asset.ACterminal!='1'||asset.smoke!='1'||asset.humidity!='1'||asset.netStatus!='1'||asset.powerStatus!='1'||asset.temperature!='1'}">
									<btn:authorBtn menuid="${menuid}" text="派发工单">
										<a href="javascript:workorderAssign('${asset.id}', '${asset.assettype}')"> <i class="icon-share"></i>派发工单</a>
									</btn:authorBtn>
								</c:if>
							</c:if>
							</c:if>
							<c:if test="${asset.payoutstatus == '1'}">
								<span style="color: green">已派发</span>
							</c:if>
							<c:if test="${asset.payoutstatus == '2'}">
								<span style="color:#FF9933">遗留中</span>
							</c:if>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${pageList.result!=null}">
					<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
						<tr>
							<td colspan="16">&nbsp;</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
			 </c:if>
		</table>
		<tags:pagination page="${pageList}"></tags:pagination> 
		<div class="clear"></div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#role-content").width($('body').width())
		$("#alert-div").hide();
	    init();
	});
	
	// miaoxu 新增 所属部门 组织机构树
	var orgSetting = {
		check: {
			  enable: true,
			   chkStyle: "checkbox",
			  /* radioType: "level" */ 
			  /* chkStyle: "checkbox",*/
				chkboxType: { "Y": "", "N": "" } ,
		},
		view: {
			  dblClickExpand: false
		},
		data: {
			  simpleData: {
				 enable: true
			  }
		},
		callback: {
			  onClick: onRadioClick,
			  onCheck: onRadioCheck
		}
		
	};
		
   //组织机构树相关函数
	function onRadioClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("orgTreeSpace");
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}
	
	function onRadioCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("orgTreeSpace"),
		nodes = zTree.getCheckedNodes(true),
		v = "";
		var h = "";
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
			h += nodes[i].id + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		var cityObj = $("#orgNames");
		cityObj.attr("value", v);
		
		if (h.length > 0 ) h = h.substring(0, h.length-1);

		var hiddenValue = $("#search_orgIds");
		hiddenValue.attr("value", h);
		
	}
	
	
	function showMenu(selectId) {
		var cityObj = $("#"+selectId);
		var cityOffset = $("#"+selectId).offset();
		if(selectId == 'orgNames'){
		   $("#orgTreeContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		}
		/* if(selectId == 'deviceTypeNames'){
		   $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		} */
		$("body").bind("mousedown",onBodyDown);
	}
	function hideMenu() {
		$("#orgTreeContent").fadeOut("fast");
		/*  $("#menuContent").fadeOut("fast");  */
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!( event.target.id == "menuBtn" || event.target.id == "orgNames" || event.target.id == "menuContent" || event.target.id == "orgTreeContent" ||  $(event.target).parents("#menuContent").length>0 ||  $(event.target).parents("#orgTreeContent").length>0 )) {
			hideMenu();
		}
	}

	 function init(){
		var dept ='${departmentjsonArray}';
	    var departmentNodes = eval("(" + dept + ")");
		//alert(dept);

		orgTree = $.fn.zTree.init($("#orgTreeSpace"),orgSetting,departmentNodes);

		orgTree.expandAll(true);
	}

	 function exportXls(){
		 var type = $("#devicetype").val();
		 var orgId = $("#search_orgIds").val();	
	      window.location.href="${root}/alarm/status/exportXls/${menuid}/"+type+"/?orgId="+orgId;
	 }
	
	function workorderAssign(id, type){
		var page='${current}';
		window.location.href="${root}/alarm/workassign/showassign/"+id+"/"+type+"/${menuid}/?page="+page;
	}
	
	function viewById(id){
		var page='${current}';
		window.location.href="${root}/alarm/status/view/"+id+"/${menuid}/?page="+page;
	}
	
</script>

<!-- miaoxu新增根据部门查资产 -->
<div id="orgTreeContent" class="menuContent" style="display:none; position: absolute; background: #f0f6e4; border: 1px solid #617775; width:175px;overflow-x:scroll; height:260px; overflow-y:scroll;" >
	<ul id="orgTreeSpace" class="ztree" style="margin-top: 0px; width:260px;"></ul>
	
</div>	

<%@include file="/WEB-INF/jsp/common/fooltertags.jspf" %>