<%@ page language="java" pageEncoding="UTF-8"%>
<link href="<c:url value='/compnents/ztree/css/zTreeStyle/zTreeStyle.css'/>" rel="stylesheet" 	type="text/css" />
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.core-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.excheck-3.5.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/js/tree.js'/>" type="text/javascript"></script>
<script src="<c:url value='/compnents/ztree/js/jquery.ztree.exedit-3.5.min.js'/>" type="text/javascript"></script>
<div class="conten_box" style="margin-right:0;">
	<div class="alert alert-block pull-top alert-error" id="alert-div" style="display:none;">
	  <p id="alert-content" align="center"></p>
	</div>
	<form id="inputForm" class="form-horizontal" method="post" style="padding:0;margin:0;">
		<h4 class="xtcs_h4" style="margin-top:0;">数据权限设置(授权成功后需要重新登陆方可生效)</h4>
		<input type="hidden" name="menuid" value="${menuid}"/>
		<input type="hidden" name="page" value="${page}"/>
		<input type="hidden" name="userids" value="${userids}"/>
		<fieldset>
			<div class="row-fluid" style="margin:0;">
				<div class="control-group" style="line-height:24px; padding-top:10px; padding-left:5px;">待授权的账号(姓名)：
				   <c:forEach items="${users}" var="user">
                       <tags:xiangxuncache keyName="Department" id="${user.deptid}"></tags:xiangxuncache>-${user.account}<span>(${user.name})</span>
				   </c:forEach>
				</div>
				<div class="clear"></div>
				<div class="span9 pull-left" style="margin:0;padding:0;">
				    <ul class="nav nav-tabs" id="grantTab" style="padding:0 5px;">
					 	<c:forEach items="${modules}" var="k" varStatus="i">
					  		<c:if test="${k.flag =='1'}">
					  			<li><a href="#li_${k.code}">${k.name}</a></li>
					  		</c:if>
					 	</c:forEach>
					</ul>
					<div class="tab-content" style="margin:0;padding:0;">
						<c:forEach items="${modules}" var="k" varStatus="i">
					  			<c:if test="${k.flag =='1'}">
					  				<div class="tab-pane row-fluid active" id="li_${k.code}"  style="margin:0;padding:0;">
									  	<div class="pull-left" style="width:35%;">
									  	 	<ul id="tree-${k.code}" class="ztree tabTree" style="width:190px;"></ul>
									  	</div> 
									  	<div class="pull-left" style="width:37%; margin-left:10px;">	
									  	 	<h4 class="xtcs_h4" style="margin-top:0; font-size:12px;width:182px;">请将要授权的部门拖入到方框内</h4>
									  	 	<ul id="grant-tree-${k.code}" class="ztree tab-tree-right tree-drag" style="width:190px; margin:0;margin-top:-10px;"></ul>
									  	</div>
									  	<div class="well" style="width:21%;float:right; margin-right:0px; margin-left:0; font-size:12px; padding:5px 10px;">
											<i class="icon-info-sign"></i>拥有权限的部门<p>说明：</p><p>1.请将要授予权限的组织机构节点<strong style="color:blue">拖</strong>入左侧的框内。</p><p>2.如果拥有权限的部门<strong style="color:blue">为空</strong>，则表示登陆用户对<strong style="color:blue">其所属部门及子部门</strong>的数据有查看或操作的权限。</p>
										</div>
								   </div>
					  			</c:if>
					 	</c:forEach>
					</div>
				</div>
				<div class="span3 pull-right" style="margin:0;border:1px #ddd solid; margin-top:35px; height:384px; overflow:auto;">
					<ul id="tree-rec" class="ztree" style="margin:0; padding:0;width:300px;"></ul>
				</div>
			</div>
		</fieldset>
		<div class="btn_line" style="margin-top:5px;">
			<input id="submit_btn" class="btn btn-info mar_r10" type="button" value="确定"  onclick="submitForm()"/>
			<input id="cancel_btn" class="btn" type="button" value="返回" onclick="showList()" />
		</div>
		<input type="hidden" name="crossMenus" id="cross-menus"  value=""/>
		<input type="hidden" name="vioMenus"   id="vio-menus"    value=""/>
		<input type="hidden" name="llMenus"    id="ll-menus"     value=""/>
		<input type="hidden" name="videoMenus" id="video-menus"  value=""/>
		<input type="hidden" name="gpsMenus"   id="gps-menus"    value=""/>
		<input type="hidden" name="blackMenus"   id="black-menus"    value=""/>
		
		<input type="hidden" name="grantCross" id="grant-cross"  value=""/>
		<input type="hidden" name="grantVio"   id="grant-vio"    value=""/>
		<input type="hidden" name="grantLl"    id="grant-ll"     value=""/>
		<input type="hidden" name="grantVideo" id="grant-video"  value=""/>
		<input type="hidden" name="grantGps"   id="grant-gps"    value=""/>
		<input type="hidden" name="grantBlack"   id="grant-black"    value=""/>
	</form>
</div>
  <script type="text/javascript">
	var tree;
	var crossTree, grantCrossTree;
	var vioTree, grantVioTree;
	var videoTree, grantVideoTree;
	var llTree, grantLlTree;
	var gpsTree, grantGpsTree;
	var blackTree, grantBlackTree;
	var firstAsyncSuccessFlag = 0;
	
	function zTreeOnAsyncSuccess(event, treeId, msg) {
		if (firstAsyncSuccessFlag == 0) {
			try {
				// 调用默认展开第一个结点
				var nodes = tree.getNodes();
				tree.expandNode(nodes[0], true);
	
				var childNodes = tree.transformToArray(nodes[0]);
				tree.expandNode(childNodes[1], true);
				var childNodes1 = tree.transformToArray(childNodes[1]);
				tree.checkNode(childNodes1[1], true, true);
				firstAsyncSuccessFlag = 1;
	
				// 让单击过的节点选中
				chooseId = '${user.deptid}';
				chooseNode = tree.getNodeByParam("id", chooseId, null);
				tree.selectNode(chooseNode);
			} catch (err) {
	
			}
		}
	}
	
	function beforeDrag(treeId, treeNodes) {
		for (var i = 0, l = treeNodes.length; i < l; i++) {
			if (treeNodes[i].drag === false) {
				return false;
			}
		}
		return true;
	}
	function beforeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
		return targetNode ? targetNode.drop !== false : true;
	}
	
	function zTreeOnExpand() {
		var mainheight = $("body").height() + 50;
		parent.$("#content-frame").height(mainheight);
	}
	
	$(document).ready(function() {
				var setting = {
					check : {
						enable : true
					},
					data : {
						simpleData : {
							enable : true
						}
					}
				};
				
						// 构造组织机构树
				var orgSetting = {
					dragCopy : true,
					async : {
						enable : true,
						url : "${root}/system/org/showTree/",
						autoParam : ["id"]
					},
					check : {
						enable : false
					},
					data : {
						simpleData : {
							enable : true
						}
					},
					edit : {
						enable : true,
						showRemoveBtn : false,
						showRenameBtn : false,
						drag : {
							isCopy : true,
							isMove : false,
							prev : false,
							next : false,
							inner : false
						}
					},
					callback : {
						onAsyncSuccess : zTreeOnAsyncSuccess,
						onExpand : zTreeOnExpand
					}
				};
				
				var dragSetting = {
					edit : {
						enable : true,
						showRemoveBtn : true,
						showRenameBtn : false,
						drag : {
							isCopy : false,
							isMove : false
						
						}
					},
					data : {
						simpleData : {
							enable : true
						}
					},
					callback : {
						beforeDrag : beforeDrag,
						beforeDrop : beforeDrop
					}
				};
				
				if($("#tree-cross")){
					var crs ='${cross_data}';
					var check ='${grant_cross}';
					var crossNodes = eval("(" + crs + ")");
					crossTree = $.fn.zTree.init($("#tree-cross"), setting, crossNodes);
					//crossTree.expandAll(true);
					if(check.length>0){
						var gcrossNodes = eval("(" + check + ")");
						grantCrossTree = $.fn.zTree.init($("#grant-tree-cross"),dragSetting,gcrossNodes);
						expandFirstNode(grantCrossTree);
					}else{
						grantCrossTree = $.fn.zTree.init($("#grant-tree-cross"),dragSetting);
					}
				}
				
				if($("#tree-vio")){
					var viod ='${vio_data}';
					var vioCheck ='${grant_vio}';
					var vioNodes = eval("(" + viod + ")");
					vioTree = $.fn.zTree.init($("#tree-vio"), setting, vioNodes);
					//vioTree.expandAll(true);
					if(vioCheck.length>0){
						grantVioTree = $.fn.zTree.init($("#grant-tree-vio"),dragSetting,eval("(" + vioCheck + ")"));
						expandFirstNode(grantVioTree);
					}else{
						grantVioTree = $.fn.zTree.init($("#grant-tree-vio"),dragSetting);
					}
				}
				
				if($("#tree-video")){
				    var vidd ='${video_data}';
					var videoNodes = eval("(" + vidd + ")");
					videoTree = $.fn.zTree.init($("#tree-video"), setting, videoNodes);
					//videoTree.expandAll(true);
					var videoCheck ='${grant_video}';
					if(videoCheck.length>0){
						grantVideoTree = $.fn.zTree.init($("#grant-tree-video"), dragSetting,eval("(" + videoCheck + ")"));
						expandFirstNode(grantVideoTree);
					}else{
						grantVideoTree = $.fn.zTree.init($("#grant-tree-video"), dragSetting);
					}	
				}
				
				if($("#tree-count")){
					var countd ='${count_data}';
					var llNodes = eval("(" + countd + ")");
					llTree = $.fn.zTree.init($("#tree-count"), setting, llNodes);
					//llTree.expandAll(true);
					var llCheck ='${grant_count}';
					if(llCheck.length>0){
						grantLlTree = $.fn.zTree.init($("#grant-tree-count"), dragSetting,eval("(" + llCheck + ")"));
						expandFirstNode(grantLlTree);
					}else{
						grantLlTree = $.fn.zTree.init($("#grant-tree-count"), dragSetting);
					}	
				
				}
				
				if($("#tree-gps")){
					var countd ='${gps_data}';
					var gpsNodes = eval("(" + countd + ")");
					gpsTree = $.fn.zTree.init($("#tree-gps"), setting, gpsNodes);
					//gpsTree.expandAll(true);
					var gpsCheck ='${grant_gps}';
					if(gpsCheck.length>0){
						grantGpsTree = $.fn.zTree.init($("#grant-tree-gps"), dragSetting,eval("(" + gpsCheck + ")"));
						expandFirstNode(grantGpsTree);
					}else{
						grantGpsTree = $.fn.zTree.init($("#grant-tree-gps"), dragSetting);
					}	
				
				}
				
				if($("#tree-black")){
					var countd ='${black_data}';
					var blackNodes = eval("(" + countd + ")");
					blackTree = $.fn.zTree.init($("#tree-black"), setting, blackNodes);
					//blackTree.expandAll(true);
					var blackCheck ='${grant_black}';
					if(blackCheck.length>0){
						grantBlackTree = $.fn.zTree.init($("#grant-tree-black"), dragSetting,eval("(" + blackCheck + ")"));
						expandFirstNode(grantBlackTree);
					}else{
						grantBlackTree = $.fn.zTree.init($("#grant-tree-black"), dragSetting);
					}	
				
				}
				
	
				$('#grantTab a:first').addClass('active').tab('show');
				$('#grantTab a').click(function(e) {
					e.preventDefault();
					$(this).tab('show');
				});
				
				tree = $.fn.zTree.init($("#tree-rec"), orgSetting);
			});
	
	function expandFirstNode(tree){
		// 调用默认展开第一个结点
		var nodes = tree.getNodes();
		tree.expandNode(nodes[0], true);

		var childNodes = tree.transformToArray(nodes[0]);
		tree.expandNode(childNodes[1], true);
	}
	
	
	function submitForm() {
		var crossMenu = [];
		var llMenu = [];
		var vioMenu = [];
		var videoMenu = [];
		var gpsMenu = [];
		var blackMenu = [];
		
		var grantCrossRel = [];
		var grantLlRel = [];
		var grantVioRel = [];
		var grantVideoRel = [];
		var grantGpsRel = [];
		var grantBlackRel = [];
	
		/** *获取勾选的菜单** */
		if (crossTree.getCheckedNodes(true).length > 0) {
			$.each(crossTree.getCheckedNodes(true), function(i, n) {
						crossMenu.push(n.id);
			});
		}
	
		if (videoTree.getCheckedNodes(true).length > 0) {
			$.each(videoTree.getCheckedNodes(true), function(i, n) {
						videoMenu.push(n.id);
			});
		}
	
		if (vioTree.getCheckedNodes(true).length > 0) {
			$.each(vioTree.getCheckedNodes(true), function(i, n) {
						vioMenu.push(n.id);
			});
		}
	
		if (llTree.getCheckedNodes(true).length > 0) {
			$.each(llTree.getCheckedNodes(true), function(i, n) {
						llMenu.push(n.id);
			});
		}
		
		if (gpsTree.getCheckedNodes(true).length > 0) {
			$.each(gpsTree.getCheckedNodes(true), function(i, n) {
						gpsMenu.push(n.id);
			});
		}
		
		if (blackTree.getCheckedNodes(true).length > 0) {
			$.each(blackTree.getCheckedNodes(true), function(i, n) {
						blackMenu.push(n.id);
			});
		}
	
		/** *获取授权的部门** */
		if (grantCrossTree.getNodes().length > 0) {
			$.each(grantCrossTree.transformToArray(grantCrossTree.getNodes()), function(i, n) {
						grantCrossRel.push(n.id);
			});
		}
	
		if (grantLlTree.getNodes().length > 0) {
			$.each(grantLlTree.transformToArray(grantLlTree.getNodes()), function(i, n) {
						grantLlRel.push(n.id);
			});
		}
	
		if (grantVioTree.getNodes().length > 0) {
			$.each(grantVioTree.transformToArray(grantVioTree.getNodes()), function(i, n) {
						grantVioRel.push(n.id);
			});
		}
	
		if (grantVideoTree.getNodes().length > 0) {
			$.each(grantVideoTree.transformToArray(grantVideoTree.getNodes()), function(i, n) {
						grantVideoRel.push(n.id);
			});
		}
		
		
		if (grantGpsTree.getNodes().length > 0) {
			$.each(grantGpsTree.transformToArray(grantGpsTree.getNodes()), function(i, n) {
						grantGpsRel.push(n.id);
			});
		}
		
		if (grantBlackTree.getNodes().length > 0) {
			$.each(grantBlackTree.transformToArray(grantBlackTree.getNodes()), function(i, n) {
						grantBlackRel.push(n.id);
			});
		}
		$("#cross-menus").val(crossMenu.join(","));
		$("#vio-menus").val(vioMenu.join(","));
		$("#ll-menus").val(llMenu.join(","));
		$("#video-menus").val(videoMenu.join(","));
		$("#gps-menus").val(gpsMenu.join(","));
		$("#black-menus").val(blackMenu.join(","));
		
		$("#grant-cross").val(grantCrossRel.join(","));
		$("#grant-vio").val(grantVioRel.join(","));
		$("#grant-ll").val(grantLlRel.join(","));
		$("#grant-video").val(grantVideoRel.join(","));
		$("#grant-gps").val(grantGpsRel.join(","));
		$("#grant-black").val(grantBlackRel.join(","));
	    
		var url = "${root}/system/user/doGrantAuthority";
		$("#inputForm").attr("action", url);
		$("#inputForm").submit();
	}
  	function showList() {
		window.location.href = "${root}/system/user/list/${menuid}/";
	}
  </script>

  