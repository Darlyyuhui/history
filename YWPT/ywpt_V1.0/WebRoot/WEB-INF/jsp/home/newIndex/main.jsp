<%@ page language="java"  pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>
<script src="${root}/compnents/funsionchartstx/jquery-1.9.1.js" type="text/javascript"></script>
<script src="${root}/compnents/funsionchartstx/fusioncharts.js" type="text/javascript"></script>
<script src="${root}/compnents/funsionchartstx/fusioncharts.charts.js" type="text/javascript"></script>
<script src="${root}/compnents/funsionchartstx/fusioncharts.maps.js" type="text/javascript"></script>
<script src="${root}/compnents/funsionchartstx/fusioncharts.widgets.js" type="text/javascript"></script>
<script src="${root}/compnents/funsionchartstx/fusioncharts.gantt.js" type="text/javascript"></script>
<script src="${root}/compnents/funsionchartstx/fusioncharts.powercharts.js" type="text/javascript"></script>

<link href="${root}/css/newIndex/newIndex.css" type="text/css" rel="stylesheet">
<link id="MainCss" href="${root}/css/transport.css" type="text/css" rel="stylesheet">
<link id="TipsCss" rel="stylesheet" href="${root}/cssGreen/tipswindown.css" type="text/css" media="all" />
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display:none;">
  <p id="alert-content" align="center"></p>
</div>
<div class="alert alert-block pull-top alert-success" id="alertsuc-div" style="display:none;">
  <p id="alertsuc-content" align="center"></p>
</div>
<c:if test="${not empty message}">
	<div id="message" class="alert alert-success">
		<button data-dismiss="alert" class="close">×</button>
		<p id="mesg" align="center">${message}</p>
	</div>
	<script>
		setTimeout('$("#message").hide("slow")', 1200);
	</script>
</c:if>
<table width="100%" style="table-layout:fixed">
  <tr>
    <td valign="top"><div class="mar_r5">
        <div class="content_div tab_box">
          <h4 class="h4_icon"><span><img src="${root}/images/newIndex/world.png"></span>系统图标 <span style="float: right;"><span style="margin-top: -6px;cursor: pointer;"><img title="点击打开功能菜单" id="dropmenu_btn" src="${root}/images/picone/gongneng.png"></span><span style="margin-top: -6px;margin-right: -5px;">功能菜单</span></span></h4>
          <div class="Tabcontent" style="padding:5px 10px;height:85px;">
            <div class="tab_tetail conActive" style="margin-left:0px;">
              <ul class="sys_icon">
                <div class="clear"> </div>
              </ul>
            </div>
          </div>
        </div>
        <div class="dropmenu" style="left:20%;top:50%;margin-top:-210px;">
          <h3 style="margin:0;">功能菜单<a class="close-btn" href="#">关闭</a></h3>
          <div id="tab_nav" style="border 1px solid #ccc;"> <a href="#t_1"><span><img src="${root}/images/picone/yingyong.png"></span>应用设置</a>
            <div id="datainfo"></div>
          </div>
          <div id="tab_content"  style="height:340px;">
            <div id="t_1"  style="height:340px;">
              <!-- 图片列表 begin -->
              <div class="sidebar" style="overflow-y:scroll;">
                <ul class="sub-menu" style="margin-left:0; padding-left:0; text-align: center;">
                  <c:forEach items="${menus}" var="menu" varStatus="m">
                    <li id="2_level_menu"><a id="img_${menu.id}" class="current"> ${menu.name}</a></li>
                    <div class="secMenu" id="submenu_frame_${menu.id}"></div>
                  </c:forEach>
                </ul>
              </div>
              <!-- 左边菜单栏结束 -->
              <div class="right_con"> </div>
              <!-- 右边内容栏结束 -->
            </div>
          </div>
        </div>
        <div class="mar_t8">
          <div class="pull-left" style="width:50%;">
            <div class="mar_r5" style="ba">
              <h4 class="h4_head border_nobot" style="margin:0;"><span><img src="${root}/images/newIndex/user.png"></span>监控项统计</h4>
              <table class="table bg_white" cellpadding="0" cellspacing="0">
                <tr>
                  <td class="device_td_bg2">
         <div style="min-height:200px;margin-bottom:-10px;">
         <div id="chart-column"></div>
         </div>
                  </td>
                </tr>
              </table>
            </div>
          </div>
          <div class="pull-right" style="width:50%;">
            <h4 class="h4_head border_nobot" style="margin:0;"><span><img src="${root}/images/newIndex/screen_sys.png"></span>十日设备故障数</h4>
            <table class="table bg_white" cellpadding="0" cellspacing="0">
              <tr>
                <td class="device_td_bg2">
          <div style="min-height:200px;margin-bottom:-10px;">
                 <div id="chart-column2"></div>
          </div>
                </td>
              </tr>
            </table>
          </div>
          <div class="clear"></div>
        </div>
        <div class="content_div mar_t8">
          <h4 class="h4_head"><span><img src="${root}/images/newIndex/mouse.png"></span>设备监控信息</h4>
          <div class="mar5">
          	<!-- 选项卡 开始 -->	  
			<ul class="nav nav-tabs" style="padding-left:10px;">
				 <c:forEach items="${openInfoList}" var="openInfo" varStatus="status">
				 	<c:if test="${status.index == 0}">
			    		<li class="active">
			    		<input type="hidden" id = "openInfoStatus" value="${openInfo.name}" />
			    	</c:if>
			    	<c:if test="${status.index != 0}">
			    		<li>
			    	</c:if>
				 	<c:if test="${openInfo.name == 'opendevice'}">
			    		<a href="#tab1" data-toggle="tab">卡口</a></li>
			    	</c:if>
			    	<c:if test="${openInfo.name == 'openserver'}">
			    		<a href="#tab3" data-toggle="tab">服务器</a></li>
			    	</c:if>
			    	<c:if test="${openInfo.name == 'opendatabase'}">
			    		<a href="#tab4" data-toggle="tab">数据库</a></li>
			    	</c:if>
			    	<c:if test="${openInfo.name == 'openftp'}">
			    		<a href="#tab5" data-toggle="tab">FTP</a></li>
			    	</c:if>
			    	<c:if test="${openInfo.name == 'openproject'}">
			    		<a href="#tab6" data-toggle="tab">平台</a></li>
			    	</c:if>
			    	<c:if test="${openInfo.name == 'opencabinet'}">
			    		<a href="#tab7" data-toggle="tab">机柜</a></li>
			    	</c:if>
			    </c:forEach>
			</ul>
			<div class="tab-content" style="overflow:auto">
				<!-- 卡口Tab页 -->
				<div class="tab-pane mar_5" id="tab1">
					<div class="tab-pane mar_5" >
						<div id="titletopwrap">
							<table class="table table-striped table-bordered table-condensed table-style" id="titletop">
								<thead>
				                <tr>
				                  	<th style="width:17%">资产编号</th>
									<th style="width:18%">资产名称</th>
									<th style="width:15%">资产IP</th>
									<th style="width:7%">供电状态</th>
									<th style="width:7%">网络状态</th>
									<th style="width:7%">数据状态</th>
									<th style="width:28%">服务厂商</th>
				                </tr>
			              		</thead>
							</table>
						</div>
						<div class="bottom_t" style="height:247px; overflow-y:auto">
							<table class="table table-striped table-bordered table-condensed table-style" id="table">
								
			              		<tbody>
			                <c:forEach items="${device_list.result}" var="device">
			                  <tr>
			                  	<td style="width:18%">
									<div style="height:16px;width:120px;overflow:hidden;" title="${device.assetcode}">${device.assetcode}</div>
								</td>
								<td style="width:18%">
									<div style="height:16px;width:120px;overflow:hidden;" title="${device.assetname}">${device.assetname}</div>
								</td>
								<td style="width:15%">${device.ip}</td>
								<td style="width:7%">
								    <c:if test="${device.powerStatus=='0'}"><IMG src="${root}/images/computerunknown.gif"  title="未知"></c:if>
								    <c:if test="${device.powerStatus=='1'}"><IMG src="${root}/images/computergreen.gif"  title="供电正常"></c:if>
									<c:if test="${device.powerStatus=='3'}"><IMG src="${root}/images/computerred.gif" title="供电异常"></c:if>
								</td>
								<td style="width:7%">
								    <c:if test="${device.netStatus=='1'}"><IMG src="${root}/images/computergreen.gif"  ></c:if>
									<c:if test="${device.netStatus=='3'}"><IMG src="${root}/images/computerred.gif" ></c:if>
								</td>
								<td style="width:7%">
								    <c:if test="${device.dataStatus=='0'}"><IMG src="${root}/images/computerunknown.gif"  title="未接入"></c:if>
								    <c:if test="${device.dataStatus=='1'}"><IMG src="${root}/images/computergreen.gif"  title="数据正常"></c:if>
									<c:if test="${device.dataStatus=='3'}"><IMG src="${root}/images/computerred.gif" title="数据有延迟"></c:if>
								</td>
								<td style="width:28%"><tags:xiangxuncache keyName="FactoryInfo" id="${device.serviceid}"></tags:xiangxuncache></td>
								
			                  </tr>
			                </c:forEach>
			                <c:if test="${device_list.result!=null}">
			                  <c:if test="${fn:length(device_list.result)<8}">
			                     <c:forEach begin="1" end="${8-fn:length(device_list.result)}">
			                         <tr>
			                          <td colspan="9">&nbsp;</td>
			                        </tr>
			                     </c:forEach>
			                  </c:if>
			                </c:if>
			                <c:if test="${device_list.result==null}">
			                  <c:forEach begin="1" end="8">
			                    <tr>
			                      <td colspan="11">&nbsp;</td>
			                    </tr>
			                  </c:forEach>
			                </c:if>
			              </tbody>
							</table>
						</div>	
		            </div>
				</div>
				<!-- 监控Tab页 -->
				<div class="tab-pane mar_5" style="overflow-x:hidden;overflow-y: auto;" id="tab2" >
					<div class="tab-pane mar_5">
	            		<table class="table table-striped table-bordered table-condensed table-style" id="table">
			              <thead>
			                <tr>
			                  	<th>资产编号</th>
								<th>资产名称</th>
								<th>资产IP</th>
								<th>资产状态</th>
								<th>网络状态</th>
								<th>运行状态</th>
								<th>服务厂商</th>
			                </tr>
			              </thead>
			              <tbody>
			                <c:forEach items="${video_list.result}" var="videoInfo">
			                  <tr>
			                  	<td>
									<div style="height:16px;width:120px;overflow:hidden;" title="${videoInfo.assetcode}">${videoInfo.assetcode}</div>
								</td>
								<td>
									<div style="height:16px;width:120px;overflow:hidden;" title="${videoInfo.assetname}">${videoInfo.assetname}</div>
								</td>
								<td>${videoInfo.ip}</td>
								<td><tags:xiangxuncache keyName="Dic" typeCode="assetstatus" id="${videoInfo.assetstatus}"></tags:xiangxuncache></td>
								<td>
								    <c:if test="${videoInfo.netStatus=='1'}"><IMG src="${root}/images/computergreen.gif"  ></c:if>
									<c:if test="${videoInfo.netStatus=='3'}"><IMG src="${root}/images/computerred.gif" ></c:if>
								</td>
								<td>
								    <c:if test="${videoInfo.dataStatus=='1'}"><IMG src="${root}/images/computergreen.gif"  ></c:if>
									<c:if test="${videoInfo.dataStatus=='3'}"><IMG src="${root}/images/computerred.gif" ></c:if>
								</td>
								
								<td><tags:xiangxuncache keyName="FactoryInfo" id="${videoInfo.serviceid}"></tags:xiangxuncache></td>
			                  </tr>
			                </c:forEach>
			                <c:if test="${video_list.result!=null}">
			                  <c:if test="${fn:length(video_list.result)<8}">
			                  <c:forEach begin="1" end="${8-fn:length(video_list.result)}">
			                    <tr>
			                      <td colspan="10">&nbsp;</td>
			                    </tr>
			                  </c:forEach>
			                  </c:if>
			                </c:if>
			                <c:if test="${video_list.result==null}">
			                  <c:forEach begin="1" end="8">
			                    <tr>
			                      <td colspan="11">&nbsp;</td>
			                    </tr>
			                  </c:forEach>
			                </c:if>
			              </tbody>
			            </table>
		            </div>
            	</div>
            	<!-- 服务器Tab页 -->
            	<div class="tab-pane mar_5" style="overflow-x:hidden;overflow-y: auto;" id="tab3" >
            		<div class="tab-pane mar_5">
	            		<table class="table table-striped table-bordered table-condensed table-style" id="table">
			              <thead>
			                <tr>
			                  	<th>资产编号</th>
								<th>资产名称</th>
								<th>资产IP</th>
								<th>资产状态</th>
								<th>CPU状态</th>
								<th>内存状态</th>
								<th>硬盘状态</th>
								<th>服务厂商</th>
			                </tr>
			              </thead>
			              <tbody>
			                <c:forEach items="${server_list.result}" var="server">
			               
			                  <tr>
								<td> 
									<div style="height:16px;width:120px;overflow:hidden;" title="${server.assetcode}">${server.assetcode}</div>
								</td>
								<td>
									<div style="height:16px;width:120px;overflow:hidden;" title="${server.assetname}">${server.assetname}</div>
								</td>
								<td>${server.ip}</td>
								<td><tags:xiangxuncache keyName="Dic" typeCode="assetstatus" id="${server.assetstatus}"></tags:xiangxuncache></td>
								<td>
									<c:if test="${server.cpuStatus=='1'}"><IMG src="${root}/images/computergreen.gif" title="CPU占用60%以下" ></c:if>
									<c:if test="${server.cpuStatus=='2'}"><IMG src="${root}/images/computeryellow.gif" title="CPU占用60%到90%" ></c:if>
									<c:if test="${server.cpuStatus=='3'}"><IMG src="${root}/images/computerred.gif" title="CPU占用90%以上"></c:if>
									
								</td>
								<td>
									<c:if test="${server.memoryStatus=='1'}"><IMG src="${root}/images/computergreen.gif" title="内存占用60%以下"></c:if>
									<c:if test="${server.memoryStatus=='2'}"><IMG src="${root}/images/computeryellow.gif" title="内存占用60%到90%"></c:if>
									<c:if test="${server.memoryStatus=='3'}"><IMG src="${root}/images/computerred.gif" title="内存占用90%以上"></c:if>
									
								</td>
								<td>
									<c:if test="${server.diskStatus=='1'}"><IMG src="${root}/images/computergreen.gif" title="硬盘占用60%以下"></c:if>
									<c:if test="${server.diskStatus=='2'}"><IMG src="${root}/images/computeryellow.gif" title="硬盘占用60%到90%"></c:if>
									<c:if test="${server.diskStatus=='3'}"><IMG src="${root}/images/computerred.gif" title="硬盘占用90%以上"></c:if>
								</td>
								
								<td><tags:xiangxuncache keyName="FactoryInfo" id="${server.serviceid}"></tags:xiangxuncache></td>
			                  </tr>
			                </c:forEach>
			                <c:if test="${server_list.result!=null}">
			                <c:if test="${fn:length(server_list.result)<8}">
			                  <c:forEach begin="1" end="${8-fn:length(server_list.result)}">
			                    <tr>
			                      <td colspan="11">&nbsp;</td>
			                    </tr>
			                  </c:forEach>
			                </c:if>
			                </c:if>
			                <c:if test="${server_list.result==null}">
			                  <c:forEach begin="1" end="8">
			                    <tr>
			                      <td colspan="11">&nbsp;</td>
			                    </tr>
			                  </c:forEach>
			                </c:if>
			              </tbody>
			            </table>
		            </div>
            	</div>
            	
            	<!-- 数据库Tab页 -->
            	<div class="tab-pane mar_5" style="overflow-x:hidden;overflow-y:hidden;" id="tab4" >
            		<div class="tab-pane mar_5" >
	            		<table class="table table-striped table-bordered table-condensed table-style" id="table">
			              <thead>
			                <tr>
			                  	<th>资产编号</th>
								<th>资产名称</th>
								<th>资产IP</th>
								<th>资产状态</th>
								<th>网络状态</th>
								<th>运行状态</th>
								<th>服务厂商</th>
			                </tr>
			              </thead>
			              <tbody>
			                <c:forEach items="${database_list.result}" var="database">
			                  <tr>
			                  	<td>
									<div style="height:16px;width:120px;overflow:hidden;" title="${database.assetcode}">${database.assetcode}</div>
								</td>
								<td>
									<div style="height:16px;width:120px;overflow:hidden;" title="${database.assetname}">${database.assetname}</div>
								</td>
			                 
								<td>${database.ip}</td>
								<td><tags:xiangxuncache keyName="Dic" typeCode="assetstatus" id="${database.assetstatus}"></tags:xiangxuncache></td>
								<td>
								    <c:if test="${database.netStatus=='1'}"><IMG src="${root}/images/computergreen.gif"  ></c:if>
									<c:if test="${database.netStatus=='3'}"><IMG src="${root}/images/computerred.gif" ></c:if>
								</td>
								<td>
								    <c:if test="${database.dataStatus=='1'}"><IMG src="${root}/images/computergreen.gif"  ></c:if>
									<c:if test="${database.dataStatus=='3'}"><IMG src="${root}/images/computerred.gif" ></c:if>
								</td>
								
								<td><tags:xiangxuncache keyName="FactoryInfo" id="${database.serviceid}"></tags:xiangxuncache></td>
			                  </tr>
			                </c:forEach>
			                <c:if test="${database_list.result!=null}">
			                <c:if test="${fn:length(database_list.result)<8}">
			                  <c:forEach begin="1" end="${8-fn:length(database_list.result)}">
			                    <tr>
			                      <td colspan="11">&nbsp;</td>
			                    </tr>
			                  </c:forEach>
			                </c:if>
			                </c:if>
			                <c:if test="${database_list.result==null}">
			                  <c:forEach begin="1" end="8">
			                    <tr>
			                      <td colspan="11">&nbsp;</td>
			                    </tr>
			                  </c:forEach>
			                </c:if>
			              </tbody>
			            </table>
		            </div>
            	</div>
            	<!-- FTPTab页 -->
            	<div class="tab-pane mar_5" style="overflow-x:hidden;overflow-y: auto;" id="tab5" >
            		<div class="tab-pane mar_5">
	            		<table class="table table-striped table-bordered table-condensed table-style" id="table">
			              <thead>
			                <tr>
			                  	<th>资产编号</th>
								<th>资产名称</th>
								<th>资产IP</th>
								<th>资产状态</th>
								<th>网络状态</th>
								<th>运行状态</th>
								<th>服务厂商</th>
			                </tr>
			              </thead>
			              <tbody>
			                <c:forEach items="${ftp_list.result}" var="ftp">
			                  <tr>
			                  	<td>
									<div style="height:16px;width:120px;overflow:hidden;" title="${ftp.assetcode}">${ftp.assetcode}</div>
								</td>
								<td>
									<div style="height:16px;width:120px;overflow:hidden;" title="${ftp.assetname}">${ftp.assetname}</div>
								</td>
								<td>${ftp.ip}</td>
								<td><tags:xiangxuncache keyName="Dic" typeCode="assetstatus" id="${ftp.assetstatus}"></tags:xiangxuncache></td>
								<td>
								    <c:if test="${ftp.netStatus=='1'}"><IMG src="${root}/images/computergreen.gif"  ></c:if>
									<c:if test="${ftp.netStatus=='3'}"><IMG src="${root}/images/computerred.gif" ></c:if>
								</td>
								<td>
								    <c:if test="${ftp.dataStatus=='1'}"><IMG src="${root}/images/computergreen.gif"  ></c:if>
									<c:if test="${ftp.dataStatus=='3'}"><IMG src="${root}/images/computerred.gif" ></c:if>
								</td>
								
								<td><tags:xiangxuncache keyName="FactoryInfo" id="${ftp.serviceid}"></tags:xiangxuncache></td>
			                  </tr>
			                </c:forEach>
			                <c:if test="${ftp_list.result!=null}">
			                <c:if test="${fn:length(ftp_list.result)<8}">
			                  <c:forEach begin="1" end="${8-fn:length(ftp_list.result)}">
			                    <tr>
			                      <td colspan="11">&nbsp;</td>
			                    </tr>
			                  </c:forEach>
			                </c:if>
			                </c:if>
			                <c:if test="${ftp_list.result==null}">
			                  <c:forEach begin="1" end="8">
			                    <tr>
			                      <td colspan="11">&nbsp;</td>
			                    </tr>
			                  </c:forEach>
			                </c:if>
			              </tbody>
			            </table>
		            </div>
            	</div>
            	<!-- 平台Tab页 -->
            	<div class="tab-pane mar_5" style="overflow-x:hidden;overflow-y: auto;" id="tab6" >
            		<div class="tab-pane mar_5">
	            		<table class="table table-striped table-bordered table-condensed table-style" id="table">
			              <thead>
			                <tr>
			                  	<th>资产编号</th>
								<th>资产名称</th>
								<th>资产IP</th>
								<th>资产状态</th>
								<th>网络状态</th>
								<th>运行状态</th>
								<th>服务厂商</th>
			                </tr>
			              </thead>
			              <tbody>
			                <c:forEach items="${project_list.result}" var="project">
			                  <tr>
			                  	<td>
									<div style="height:16px;width:120px;overflow:hidden;" title="${project.assetcode}">${project.assetcode}</div>
								</td>
								<td>
									<div style="height:16px;width:120px;overflow:hidden;" title="${project.assetname}">${project.assetname}</div>
								</td>
								<td>${project.ip}</td>
								<td><tags:xiangxuncache keyName="Dic" typeCode="assetstatus" id="${project.assetstatus}"></tags:xiangxuncache></td>
								<td>
								    <c:if test="${project.netStatus=='1'}"><IMG src="${root}/images/computergreen.gif"  ></c:if>
									<c:if test="${project.netStatus=='3'}"><IMG src="${root}/images/computerred.gif" ></c:if>
								</td>
								<td>
								    <c:if test="${project.dataStatus=='1'}"><IMG src="${root}/images/computergreen.gif"  ></c:if>
									<c:if test="${project.dataStatus=='3'}"><IMG src="${root}/images/computerred.gif" ></c:if>
								</td>
								<td><tags:xiangxuncache keyName="FactoryInfo" id="${project.serviceid}"></tags:xiangxuncache></td>
			                  </tr>
			                </c:forEach>
			                <c:if test="${project_list.result!=null}">
			                  <c:forEach begin="1" end="${8-fn:length(project_list.result)}">
			                    <tr>
			                      <td colspan="11">&nbsp;</td>
			                    </tr>
			                  </c:forEach>
			                </c:if>
			                <c:if test="${project_list.result==null}">
			                  <c:forEach begin="1" end="8">
			                    <tr>
			                      <td colspan="11">&nbsp;</td>
			                    </tr>
			                  </c:forEach>
			                </c:if>
			              </tbody>
			            </table>
		            </div>
            	</div>
            	<!-- 机柜Tab页 -->
            	<div class="tab-pane mar_5" style="overflow-x:hidden;overflow-y: auto;" id="tab7" >
            		<div class="tab-pane mar_5">
	            		<table class="table table-striped table-bordered table-condensed table-style" id="table" >
			              <thead>
			                <tr>
			                  	<th>资产编号</th>
								<th>资产名称</th>
								<th>资产IP</th>
								<th>资产状态</th>
								<th>温度</th>
								<th>湿度</th>
								 <th>网络状态</th>
							<c:if test="${cabinet_pattern=='1'}">
								<th>烟感</th>
								<th>振动</th>
								<th>AC端子</th>
								<th>AC插座</th>
						    </c:if>
								
								<th>供电状态</th>
								<th>服务厂商</th>
			                </tr>
			              </thead>
			              <tbody>
			                <c:forEach items="${cabinet_list.result}" var="cabinet">
			               
			                  <tr>
								<td> 
									<div style="height:16px;width:120px;overflow:hidden;" title="${cabinet.assetcode}">${cabinet.assetcode}</div>
								</td>
								<td>
									<div style="height:16px;width:120px;overflow:hidden;" title="${cabinet.assetname}">${cabinet.assetname}</div>
								</td>
								<td>${cabinet.ip}</td>
								<td><tags:xiangxuncache keyName="Dic" typeCode="assetstatus" id="${cabinet.assetstatus}"></tags:xiangxuncache></td>
								<td>
								    <c:if test="${cabinet.temperature=='2'}"><IMG src="${root}/images/computerunknown.gif" title="未知"></c:if>
									<c:if test="${cabinet.temperature=='1'}"><IMG src="${root}/images/computergreen.gif" title="温度正常"></c:if>
						            <c:if test="${cabinet.temperature=='0'}"><IMG src="${root}/images/computerred.gif" title="温度异常：${cabinet.temperaturevalue}"></c:if>
									
								</td>
								<td>
					                <c:if test="${cabinet.humidity=='2'}"><IMG src="${root}/images/computerunknown.gif" title="未知"></c:if>
									<c:if test="${cabinet.humidity=='1'}"><IMG src="${root}/images/computergreen.gif" title="湿度正常"></c:if>
						            <c:if test="${cabinet.humidity=='0'}"><IMG src="${root}/images/computerred.gif" title="湿度异常:${cabinet.humidityvalue}"></c:if>
									
								</td>
								<td> 
								     <c:if test="${cabinet.netStatus=='2'}"><IMG src="${root}/images/computerunknown.gif" title="未知"></c:if>  
						             <c:if test="${cabinet.netStatus=='1'}"><IMG src="${root}/images/computergreen.gif" title="网络正常"></c:if>
						             <c:if test="${cabinet.netStatus=='0'}"><IMG src="${root}/images/computerred.gif" title="网络异常"></c:if>
					            </td>
					           <c:if test="${cabinet_pattern=='1'}">
								<td>
								    <c:if test="${cabinet.smoke=='2'}"><IMG src="${root}/images/computerunknown.gif" title="未知"></c:if> 
									<c:if test="${cabinet.smoke=='1'}"><IMG src="${root}/images/computergreen.gif" title="烟感正常"></c:if>
						            <c:if test="${cabinet.smoke=='0'}"><IMG src="${root}/images/computerred.gif" title="烟感异常"></c:if>
								</td>
								<td>
								    <c:if test="${cabinet.shock=='2'}"><IMG src="${root}/images/computerunknown.gif" title="未知"></c:if> 
								    <c:if test="${cabinet.shock=='1'}"><IMG src="${root}/images/computergreen.gif" title="振动正常"></c:if>
						            <c:if test="${cabinet.shock=='0'}"><IMG src="${root}/images/computerred.gif" title="振动异常:${cabinet.shockvalue}"></c:if>
								</td>
								<td>
								    <c:if test="${cabinet.ACterminal=='2'}"><IMG src="${root}/images/computerunknown.gif" title="未知"></c:if> 
								    <c:if test="${cabinet.ACterminal=='1'}"><IMG src="${root}/images/computergreen.gif" title="端子正常"></c:if>
						            <c:if test="${cabinet.ACterminal=='0'}"><IMG src="${root}/images/computerred.gif" title="端子异常"></c:if>
								</td>
								<td>
								   <c:if test="${cabinet.ACvoltage=='2'||cabinet.ACcurrent=='2'}"><IMG src="${root}/images/computerunknown.gif" title="未知"></c:if> 
						            <c:if test="${cabinet.ACvoltage=='1'&&cabinet.ACcurrent=='1'}"><IMG src="${root}/images/computergreen.gif" title="电压电流正常"></c:if>
						            <c:if test="${cabinet.ACvoltage=='0'||cabinet.ACcurrent=='0'}"><IMG src="${root}/images/computerred.gif" title="电压或电流异常，电压：${asset.ACvoltagevalue}，电流：${asset.ACcurrentvalue}"></c:if>
					            </td>
					             </c:if>
					            <td>
					                <c:if test="${cabinet.powerStatus=='2'}"><IMG src="${root}/images/computerunknown.gif" title="未知"></c:if> 
									<c:if test="${cabinet.powerStatus=='1'}"><IMG src="${root}/images/computergreen.gif" title="供电正常"></c:if>
									<c:if test="${cabinet.powerStatus=='0'}"><IMG src="${root}/images/computerred.gif" title="已断电"></c:if>
								</td>
										
								<td><tags:xiangxuncache keyName="FactoryInfo" id="${cabinet.serviceid}"></tags:xiangxuncache></td>
								
								
			                  </tr>
			                </c:forEach>
			                <c:if test="${cabinet_list.result!=null}">
			                <c:if test="${fn:length(cabinet_list.result)<8}">
			                  <c:forEach begin="1" end="${8-fn:length(cabinet_list.result)}">
			                    <tr>
			                      <td colspan="13">&nbsp;</td>
			                    </tr>
			                  </c:forEach>
			                </c:if>
			                </c:if>
			                <c:if test="${cabinet_list.result==null}">
			                  <c:forEach begin="1" end="8">
			                    <tr>
			                      <td colspan="13">&nbsp;</td>
			                    </tr>
			                  </c:forEach>
			                </c:if>
			              </tbody>
			            </table>
		            </div>
            	</div>
            	
            	
            	
			</div>
          </div>
        </div>
      </div>
    </td>
    <td width=420 style="width:420px;" valign="top">
       <div class="last_info">
        <p class="info_p p_font"><span><img src="${root}/images/newIndex/kakou.png"></span>整体资产健康度<a href="#" onclick="showMore('alarm')">更多+</a></p>
        <div id="chart-container">222</div>
        <ul class="log_ul ul_first" style="text-align: center;">
            <li>
            </li>
        </ul>
      </div>
     <div class="last_info">
        <p class="info_p p_font"><span><img src="${root}/images/newIndex/kakou.png"></span>事件告警<a href="#" onclick="showMore('alarm')">更多+</a></p>
        <ul class="log_ul ul_first" style="text-align: center;">
          <c:if test="${fn:length(alarmLog_list.result)==0}">
            <li><font color="red">没有数据</font></li>
          </c:if>
        </ul>
        <ul class="alarm_ul">
          <c:forEach items="${alarmLog_list.result}" var="alarm">
            <li>
            	<a href="javascript:alarmLog('${alarm.id}');"> 
            		<cite><img src="${root}/images/newIndex/dot1.png"></cite> 
            		<span class="info_span" style="height:26px;width:140px;overflow:hidden;" title="${alarm.deviceName}">${alarm.deviceName}</span>
            		<span class="info_span" style="height:26px;width:120px;overflow:hidden;color:${alarm.alarmColor} " title="${alarm.eventTypeName}">${alarm.eventTypeName}</span> 
            		<span class="dete" style="float:right;">
              			<fmt:formatDate value="${alarm.alarmTime}" pattern="yyyy-MM-dd HH:mm:ss" />
              		</span> 
              	</a>
           	</li>
          </c:forEach>
        </ul>
      </div>
      <div class="last_info mar_t8">
        <p class="info_p p_font"><span><img src="${root}/images/newIndex/screen_error.png"></span>工单信息<a href="#" onclick="showMore('workorder')">更多+</a></p>
        <ul class="log_ul ul_first" style="text-align: center;">
          <c:if test="${fn:length(workorderlog_list.result)==0}">
            <li><font color="red">没有数据</font></li>
          </c:if>
        </ul>
        <ul class="log_ul">
          <c:forEach items="${workorderlog_list.result}" var="workorder">
          	<li>
            	<a href="javascript:workorderLog('${workorder.id}');"> 
            		<cite><img src="${root}/images/newIndex/dot2.png"></cite> 
            		<span class="info_span" style="height:26px;width:180px;overflow:hidden;" title="${workorder.devicename}">${workorder.devicename}</span> 
            		<span style="height:26px;width:200px;overflow:hidden;">
								${workorder.statusHtml}
					</span>
            		<span class="dete" style="float:right;">
              			<fmt:formatDate value="${workorder.assigntime}" pattern="yyyy-MM-dd HH:mm:ss"/>
              		</span>
              	</a> 
          	</li>
          </c:forEach>
        </ul>
      </div>
      <div class="last_info mar_t8">
        <p class="info_p p_font"><span><img src="${root}/images/newIndex/screen.png"></span>问题信息<a href="#" onclick="showMore('question')">更多+</a></p>
        <ul class="log_ul ul_first" style="text-align: center;">
          <c:if test="${fn:length(questionlog_list.result)==0}">
            <li><font color="red">没有数据</font></li>
          </c:if>
        </ul>
        <ul class="log_ul ul_first">
          <c:forEach items="${questionlog_list.result}" var="log">
            <li>
            	<a href="javascript:questionLog('${log.id}');"> 
            		<cite><img src="${root}/images/newIndex/dot2.png"></cite> 
            		<span class="info_span" style="height:26px;width:120px;overflow:hidden;" title="${log.title}">${log.title}</span> 
            		<span class="dete" style="float:right;">
              			<fmt:formatDate value="${log.insertTime}" pattern="yyyy-MM-dd HH:mm:ss" />
              		</span> 
              	</a>
            </li>
          </c:forEach>
        </ul>
      </div>
      </td>
  </tr>
</table>

<!-- 告警日志详情弹出框 -->
<div class="modal hide fade"  id="alarmlog-modal">
  	<h4 class="xtcs_h4" style="margin: 0;"><font style="margin-left: 5px;">告警日志详情</font><img src="${root}/images/delete.png" href="#" style="cursor: pointer;float: right;height:20px;width:20px;margin-left:0;font-size: small;vertical-align: middle;margin-top: 5px;color: red;" data-dismiss="modal"/></h4>
  	<div class="modal-body" id="alarmlog_div"></div>
</div>

<!-- 派发工单信息弹出框 -->
<div class="modal hide fade" id="showassign-modal" style="width:760px;">
  	<h4 class="xtcs_h4" style="margin: 0;"><font style="margin-left: 5px;">派发工单信息</font><img src="${root}/images/delete.png" href="#" style="cursor: pointer;float: right;height:20px;width:20px;margin-left:0;font-size: small;vertical-align: middle;margin-top: 5px;color: red;" data-dismiss="modal"/></h4>
  	<div class="modal-body" id="showassign_div"></div>
</div>


<!-- 问题信息详情弹出框 -->
<div class="modal hide fade" id="questionlog-modal">
  	<h4 class="xtcs_h4" style="margin: 0;"><font style="margin-left: 5px;">问题信息详情</font><img src="${root}/images/delete.png" href="#" style="cursor: pointer;float: right;height:20px;width:20px;margin-left:0;font-size: small;vertical-align: middle;margin-top: 5px;color: red;" data-dismiss="modal"/></h4>
  	<div class="modal-body" id="questionlog_div"></div>
</div>

<!-- 工单信息详情弹出框 -->
<div class="modal hide fade" id="workorderlog-modal" style="width:950px;margin-left:-500px;">
  	<h4 class="xtcs_h4" style="margin: 0;"><font style="margin-left: 5px;">工单信息详情</font><img src="${root}/images/delete.png" href="#" style="cursor: pointer;float: right;height:20px;width:20px;margin-left:0;font-size: small;vertical-align: middle;margin-top: 5px;color: red;" data-dismiss="modal"/></h4>
  	<div class="modal-body" id="workorderlog_div"></div>
</div>
<textarea id="chart-xml" style="display: none">
<chart aboutMenuItemLabel='西安翔迅科技有限公司'  aboutMenuItemLink=''  bgColor='#FBFBFB'  formatNumber='1'  numberSuffix='个'
  rotateYAxisName='0'  baseFont='微软雅黑'  baseFontSize='12'  outCnvBaseFont='微软雅黑'  outCnvBaseFontSize='12'  decimalPrecision='0'  
  chartLeftMargin='5'  shownames='1'  showValues='1'  animation='1'  formatNumberScale='0'  showPercentageValues='0'  startingAngle='45'  
  slicingDistance='1'  enableSmartLabels='1'  enableRotation='1'  >
	    <c:forEach items="${countByTypeList}" var="data">
			<set name='${data.assettype}' value='${data.assetstatus}'/>
		</c:forEach>
</chart>
</textarea>
<textarea id="chart-xml2" style="display: none">
<chart  aboutMenuItemLabel='西安翔迅科技有限公司'  aboutMenuItemLink='' numberSuffix='个'  bgColor='#FBFBFB'  rotateYAxisName='0'  
baseFont='微软雅黑'  baseFontSize='12'  outCnvBaseFont='微软雅黑'  outCnvBaseFontSize='12'  decimalPrecision='0'  chartLeftMargin='5'  shownames='1'  formatNumberScale='0'  
  numdivlines='4'  areaBorderColor='DAE5F4'  numVDivLines='29'  vDivLineAlpha='30'  showValues='0' >
	    ${categoriexml}
	    <c:forEach items="${datasetss}" var="datasetss" varStatus="k">
				${datasetss.dataXml}
		</c:forEach>
</chart>
</textarea>
<c:forEach items="${menus}" var="menu" varStatus="m">
	<script type="text/javascript">
		var temp = new Array();
		// 点击功能菜单左侧应用（add by kouyunhao 2014-01-02 添加点击当前菜单，其他打开菜单关闭效果）
		$("#img_${menu.id}").click(function(){
			debugger;
			if($("#submenu_frame_${menu.id}").html().length != 0){
				$("#submenu_frame_${menu.id}").html("");
			}else{
				var id = '${menu.id}';
				var name = '${menu.code}';
				if(temp[0] != undefined){
					$("#submenu_frame_"+temp[0]).html("");
					temp.splice(0,1);
				}
				temp.push(id);
				$.getJSON("${root}/desk/deskinfo/get_left_2menu/" + id + "/" + name,
					function(data) {
						$("#submenu_frame_${menu.id}").html(data.content);
					});
			}
		});
	</script>
</c:forEach>
<script type="text/javascript">

    var percent = ${percent};
    
    function startlist(){
    	   window.setInterval("getStatusInfo()",5000);
    }
    
    function getStatusInfo(){
    	$.getJSON("${root}/home/newindex/getpercent/?num="+Math.random(),
    		function(data) {
    		  percent = parseInt(data.percent, 5);
    	});
    }
    
    


	$(document).ready(function(){
		getResList();
		getChart1XmlData();
		getChart2XmlData();
		
		//tianbo加载参数
		var openInfoStatus = $("#openInfoStatus").val();
		if(openInfoStatus == "opendevice"){
			$("#tab1").addClass("active");
		}else if(openInfoStatus == "openserver"){
			$("#tab3").addClass("active");
		}else if(openInfoStatus == "opendatabase"){
			$("#tab4").addClass("active");
		}else if(openInfoStatus == "openftp"){
			$("#tab5").addClass("active");
		}else if(openInfoStatus == "openproject"){
			$("#tab6").addClass("active");
		}else if(openInfoStatus == "opencabinet"){
			$("#tab7").addClass("active");
		}
		
		var fusioncharts = new FusionCharts({
            type: 'angulargauge',
            renderAt: 'chart-container',
            width: '99%',
            height: '185',
            dataFormat: 'json',
            dataSource: {
                "chart": {
                    "lowerLimit": "0",
                    "upperLimit": "100",
                    "editMode": "1",
                    "showValue": "1",
                    "valueBelowPivot": "1",
                    "tickValueDistance": "25",
                    "gaugeFillMix": "{dark-30},{light-60},{dark-10}",
                    "gaugeFillRatio": "15",
                    "theme": "fint",
                    "bgColor": "#FBFBFB",
                    "valueFontSize": "14"
                },
                "colorRange": {
                    "color": [{
                        "minValue": "0",
                        "maxValue": "50",
                        "code": "#e44a00"
                    }, {
                        "minValue": "50",
                        "maxValue": "80",
                        "code": "#f8bd19"
                    }, {
                        "minValue": "80",
                        "maxValue": "100",
                        "code": "#6baa01"
                    }]
                },
                "dials": {
                    "dial": [{
                        "id": "crntYr",
                        "value": "18",
                        "showValue": "1",
                        "tooltext": "当前资产健康度为 : $value",
                        "rearExtension": "5"
                    }]
                }
            },
            events: {
                "rendered": function(evtObj, argObj) {
                    evtObj.sender.intervalVar = setInterval(function() {
                        var chartIns = evtObj.sender,
                            prcnt = percent + parseInt(Math.floor(Math.random() * 5), 10);
                            if(prcnt>100){
                            prcnt = 100;
                            }
                        chartIns.feedData("value=" + prcnt);
                    }, 1000);
                },
                "realtimeUpdateComplete": function(evtObj, argObj) {
                    var updtObj = argObj && argObj.updateObject,
                        values = updtObj && updtObj.values,
                        updtValStr = values && values[0],
                        updtVal = updtValStr && parseFloat(updtValStr).toFixed(0),
                        divToUpdate = document.getElementById("score-detail");
                    
                },
                "disposed": function(evtObj, argObj) {
                    clearInterval(evtObj.sender.intervalVar);
                }
            }
        });
		fusioncharts.render();
        startlist();
	});
	
	function getChart1XmlData(){
	    var chart = new FusionCharts("${root}/compnents/fusioncharts/chart/Column3D.swf", "Chart1Id","100%","250");
		var total = '${unfirmSize}';
		if (total == '0') {
			$("#chart-column").html("<center style='margin-top:150px;color:blue'>无数据</center>");
		} else {
			chart.setDataXML($("#chart-xml").val());
			chart.render("chart-column");
		}
	}	
	
	function getChart2XmlData(){
	    var chart = new FusionCharts("/ywpt_v1.0/compnents/fusioncharts/chart/MSLine.swf", "chart2Id", "100%", "250");		   
		var total = '${unfirmSize}';
		if (total == '0') {
			$("#chart-column2").html("<center style='margin-top:150px;color:blue'>无数据</center>");
		} else {
			chart.setDataXML($("#chart-xml2").val());
			chart.render("chart-column2");
		}
	}

	var bg = new Array();
	//鼠标滑过时改变菜单树背景颜色
	function change_bgcolor(id){
		if(bgcol[0] != bg[0]){
			if(bg[0] != undefined){
				$("#"+bg[0]).parent().attr("style","border-bottom: 1px solid #B2B2B2;line-height: 28px;");
				$("#"+bg[0]).attr("style","color:blue;");
				bg.splice(0,1);
			}
			bg.push(id);
			$("#"+bg[0]).parent().attr("style","background:#7BD4FA;border-bottom: 1px solid #B2B2B2;line-height: 28px;");
			$("#"+bg[0]).attr("style","color:white;");
		}else{
			bg.splice(0,1);
			bg.push('');
		}
	}
	
	var subid;
	var subname;
	var content;
	//var picname;
	var bgcol = new Array();
	function changebg_onclick(id){
		if(bgcol[0] != undefined){
			$("#"+bgcol[0]).parent().attr("style","border-bottom: 1px solid #B2B2B2;line-height: 28px;");
			$("#"+bgcol[0]).attr("style","color:blue;");
			bgcol.splice(0,1);
		}
		bgcol.push(id);
		$("#"+bgcol[0]).parent().attr("style","background:#7BD4FA;border-bottom: 1px solid #B2B2B2;line-height: 28px;");
		$("#"+bgcol[0]).attr("style","color:white;");
	}
	
	// 将图标添加到桌面上（点击功能菜单左侧二级菜单）
	function add_to_desk(menuid, name){
		changebg_onclick(menuid);
		var subid;
		var subname;
		var content;
		var resid;
		var picname;
		// 从后台获取三级菜单字符串并解析，点击左侧二级菜单将图标显示在右侧区域
		jQuery.ajax({
				type : "GET",  
				cache: false,
				url: "${root}/desk/deskinfo/get_right_shortcut/" + menuid,
				success:function(data) {
					if(data != "null"){
						subid = new Array();
						subname = new Array();
						content = new Array();
						picname = new Array();
						var contentArr = data.split(",");
						for(var i = 0; i < contentArr.length; i++){
							if(i % 4 == 0){
								subid[i/4] = contentArr[i];
							}
							if(i % 4 == 1){
								subname[(i-1)/4] = contentArr[i];
							}
							if(i % 4 == 2){
								content[(i-2)/4] = contentArr[i];
							}
							if(i % 4 == 3){
								picname[(i-3)/4] = contentArr[i];
							}
						}
						jQuery.ajax({
							type : "GET",  
							cache: false,
							url: "${root}/desk/deskinfo/res_list/",
							success:function(data) {
								var div = '';
								if(data != "null"){
									resid = data.split(",");
									for(var j = 0; j < subid.length; j++){
										var isEq = false;
										for(var m = 0; m < resid.length; m++){
											if(resid[m] == subid[j]){
												isEq = true;
											}
										}
										if(isEq){
											div += '<li id="picc_'+subid[j]+'" style="display:none;float:left;width:70px; margin:0 4px;text-align:center;" href="#" ><div>'+
											'<img style="width:60px; height:50px;" src="${root}/images/picone/mainmenu/threelevel/'+picname[j]+'.png"/></div><a class="nav_name_small" title='+subname[j]+'>'+subname[j]+'</a></li>';
										}else{
											div += '<li id="picc_'+subid[j]+'" style="display:block;float:left;width:70px; margin:0 4px;text-align:center;" href="#" ><div>'+
											'<img style="width:60px; height:50px;" src="${root}/images/picone/mainmenu/threelevel/'+picname[j]+'.png"/></div><a class="nav_name_small" title='+subname[j]+'>'+subname[j]+'</a></li>';
										}
									}
								}else{
									for(var j = 0; j < subid.length; j++){
										div += '<li id="picc_'+subid[j]+'" style="display:block;float:left;width:70px; margin:0 4px;text-align:center;" href="#" ><div>'+
										'<img style="width:60px; height:50px;" src="${root}/images/picone/mainmenu/threelevel/'+picname[j]+'.png"/></div><a class="nav_name_small" title='+subname[j]+'>'+subname[j]+'</a></li>';
									}
								}
								$(".right_con").html("").html(div);
								for(var k = 0; k < subname.length; k++){
									toDesk(subid[k], subname[k], name, content[k], menuid, picname[k]);	
								}
							}
						});
					}
				}
			
		});
	}
	
	// 获取用户自定义快捷方式
	function getResList(){
		jQuery.ajax({
			type : "GET",  
			cache: false,
			url: "${root}/desk/deskinfo/get_list/99",
			success:function(data) {
			/******************************此处需要替换*************/
				$(".sys_icon").html('<li id="default"><img src="${root}/images/picone/mainmenu/threelevel/default.png" title="点击右上角图标添加自定义快捷方式"></li><div class="clear"></div>');
				if(data != "null"){
					var resArr = data.split(",");
					$(".sys_icon").html("");
					for(var i = 0; i < resArr.length; i++){
							var res = resArr[i].split("_")
							var resid = res[0];
							var resname = res[1];
							var name = res[2];
							var url = res[3];
							var menuid = res[4];
							var picname = res[5];
							$(".sys_icon").append('<li id="picc_'+resid+'" onmouseover="showDel(\''+resid+'\')" onmouseout="hideDel(\''+resid+'\')" style="width:70px;height:80px;float:left; margin:8 4px;position:relative; text-align:center;cursor: pointer;" href="#"'+ 
								'onclick="showDel(\''+resid+'\');onDeskClick(\''+resid+'\',\''+resname+'\',\''+name+'\',\''+url+'\',\''+menuid+'\');">'+ 
									'<span style="position:absolute;top:0; right:0;width:12px; height:12px; z-index:1000;"><img id="del_'+resid+'" style="display:none;height:12px;" src="${root}/images/picone/close.png"></span>'+
									'<img style="width:60px; height:50px; margin:0;" src="${root}/images/picone/mainmenu/threelevel/'+picname+'.png"/><div style="width:60px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px; white-space:nowrap;" title='+resname+'>'+resname+'</div></a></li>');
					}
					$(".sys_icon").append('<div class="clear"></div>');
				}
			}
		});
	}
	
	function toDesk(subid, subname, name, content, menuid, picname){
		var url = content+"/"+subid+"/";
		$("#picc_"+subid).click(function(){
			jQuery.ajax({
				type : "GET",  
				cache: false,
				url: "${root}/desk/deskinfo/get_resnum/99",
				success : function(data) {
					if(data > 9){
						alert("最多添加10个");//最多添加10个用户自定义图标
						return;
					}else{
						$("#default").remove();
						$(".sys_icon .clear").remove();
						$(".sys_icon").append('<li id="picc_'+subid+'" onmouseover="showDel(\''+subid+'\')" onmouseout="hideDel(\''+subid+'\')" style="width:70px;height:80px;float:left; margin:8 4px;position:relative; text-align:center;cursor: pointer;" href="#"'+ 
									'onclick="onDeskClick(\''+subid+'\',\''+subname+'\',\''+name+'\',\''+url+'\',\''+menuid+'\')">'+ 
										'<span style="position:absolute;top:0; right:0;width:12px; height:12px;"><img id="del_'+subid+'" style="display:none;height:12px;" src="${root}/images/picone/close.png"></span>'+
										'<img style="width:60px; height:50px; margin:0;" src="${root}/images/picone/mainmenu/threelevel/'+picname+'.png"/><div style="width:60px;overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;height:18px; white-space:nowrap;" title='+subname+'>'+subname+'</div></a></li><div class="clear"></div>');
						addMenu(subid);
						$(this).attr("style","display:none");
					}
				}
			});
			
			//add_menu(scrno, subid);
			//$(this).attr("style","display:none");
		});
	}
	
	function onDeskClick(id, name,pname,url, pid) {
		jQuery.ajax({
			type : "GET",  
			cache: false,
			url: "${root}/desk/deskinfo/get_grandfather_node/" + pid + "/" + name + "/2",
			success:function(data) {
				var info = data.split(",");
				var p_content = info[0];
				var p_name = info[1];
				var p_picname = info[2];
				parent.$("#menu_"+p_content).parent("li").removeClass("sys_nav").addClass("active_nav").siblings(".mainnav_new li").removeClass("active_nav").addClass("sys_nav");
				//parent.$("#"+id).removeClass("nav_li").addClass("curent_nav");
				parent.getsubmenu(p_content,false);
				parent.menuOnclick(id, name,pname,url);
				parent.$("#"+id).removeClass("nav_li").addClass("curent_nav");
			}
		});
	}
	function addMenu(resourceid){
		jQuery.ajax({
			type : "GET",  
			cache: false,
			url: "${root}/desk/deskinfo/add_theme2menu/"+ resourceid,
			success : function(data) {
				$("#datainfo").show();
				if(data.result=="ok"){
	   				 $("#datainfo").html("").html('<font style="color: green;font-size: large;">应用已添加到当前桌面！</font>');
	   			}else{
	   				 $("#datainfo").html("").html('<font style="color: red;font-size: large;">应用添加失败！</font>');
	   			}
	   			setTimeout('$("#datainfo").hide()', 1200);
			}
		});
	}
	
	function del_menu(resourceid){
		$.getJSON("${root}/desk/deskinfo/del_menu/99/"+ resourceid,
			function(data) {
				//alert(data);
				$(this).attr("style","display:inline");
			});
	}
	
	function showDel(resid){
		$("#picc_"+resid).addClass("bgcolor_add");
		$("#del_"+resid).css("display","block");
		$("#del_"+resid).click(function(){
			del_menu(resid);
			$("#picc_"+resid).css("display","none");
			jQuery.ajax({
				type : "GET",  
				cache: false,
				url: "${root}/desk/deskinfo/get_resnum/99",
				success : function(data) {
					if(data == 0){
						/******************************此处需要替换*************/
						$(".sys_icon").html('<li id="default"><img src="${root}/images/picone/mainmenu/threelevel/default.png" title="点击右上角图标添加自定义快捷方式"></li><div class="clear"></div>');
					}
				}
			});
			return false;
		});
		return true;
	}
	
	function hideDel(resid){
		$("#picc_"+resid).removeClass("bgcolor_add");
		$("#del_"+resid).css("display","none");
	}
	
	function showMore(type){
		$.getJSON("${root}/home/newindex/findmore/" + type,
			function(data) {
				parent.menuOnclick(null,null,null,data.url);
				parent.$("#menu_"+data.ppid).parent("li").removeClass("sys_nav").addClass("active_nav").siblings(".mainnav_new li").removeClass("active_nav").addClass("sys_nav");
			});
	}
	
	$("#dropmenu_btn").click(function(){
		$('.dropmenu').show();
	});
	
	$(".close-btn").click(function(){
		$(".dropmenu").css("display","none");
		return false;
	});
	
	//告警日志详情弹出框
	function showAlarmLogDialog(id) {
		var url = "${root}/home/newindex/alarm/log/view/" + id + "/";
		$.ajax( {
			type : 'GET',
			url : url,
			dataType : "text",
			success:function(data) {
				if(data != null){
					debugger;
					$("#alarmlog_div").html(data);
				}
			}
		});
		$('#alarmlog-modal').modal('show');
	}
	
	function alarmLog(id) {
		showAlarmLogDialog(id);
	}
	
	//派发工单弹出框
	function showassignDialog(deviceid,devicetype) {
		debugger;
		var url = "${root}/home/newindex/showassign/" + deviceid + "/" + devicetype + "/";
		$.ajax( {
			type : 'GET',
			url : url,
			dataType : "text",
			success:function(data) {
				if(data != null){
					debugger;
					$("#showassign_div").html(data);
				}
			}
		});
		$('#alarmlog-modal').modal('hide');
		$('#showassign-modal').modal('show');
	}
	
	function showassign(deviceid,devicetype) {
		showassignDialog(deviceid,devicetype);
	}
	
	//问题信息详情弹出框
	function showQuestionLogDialog(id) {
		var url = "${root}/home/newindex/question/log/view/" + id + "/";
		$.ajax( {
			type : 'GET',
			url : url,
			dataType : "text",
			success:function(data) {
				if(data != null){
					$("#questionlog_div").html(data);
				}
			}
		});
		$('#questionlog-modal').modal('show');
	}
	
	function questionLog(id) {
		showQuestionLogDialog(id);
	}
	
	//工单信息详情弹出框
	function showWorkorderLogDialog(id) {
		var url = "${root}/home/newindex/workorder/log/view/" + id + "/";
		$.ajax({
			type : 'GET',
			url : url,
			dataType : "text",
			success:function(data) {
				if(data != null){
					$("#workorderlog_div").html(data);
				}
			}
		});
		$('#workorderlog-modal').modal('show');
	}
	
	function workorderLog(id) {
		showWorkorderLogDialog(id);
	}
	
	function showTelephone(a){
		if(a.value == ''){
			$("#telephone").val("");
			$("#messages").html("");
			return;
		}
		var data = $("#contact option:selected");
		if (data) {
			var json = $(data).attr("userdata");
			var obj = $.parseJSON(json);
			$("#telephone").val(obj.mobile);
			var message = '${factory.name}' + "的" + obj.name + "，你好："+
			"位于[${asset.installplace}]上的IP为[${asset.ip}]的设备[${asset.assetname}]发生故障，请及时维修。";
			$("#messages").html(message);
		}
	}
	
	function doAssign(devicetype){
		debugger;
		input_form.action = "${root}/home/newindex/doAssign/"+devicetype+"/";
		input_form.method = "post";
		input_form.submit();
	}	
</script>
