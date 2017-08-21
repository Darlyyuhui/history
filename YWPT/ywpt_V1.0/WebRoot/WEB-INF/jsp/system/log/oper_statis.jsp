<%@ page language="java" pageEncoding="UTF-8"%>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script language="javascript" src="${root}/js/LodopFuncs.js"></script>
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display: none">
	<p id="alert-content" align="center"></p>
</div>
<!-- 重置查询输入框开始 -->
<script type="text/javascript">
	function reValues() {
		$("#beginDate").val("");
		$("#endDate").val("");
	}
</script>
<!-- 重置查询输入框结束 -->
<div class="conten_box">
	<div class="search-area" style="padding: 6px 0 4px;">
		<form class="form-search" action="${root}/system/log/operstatis/${menuid}" method="post"  id="queryForm" style="margin:0; padding:0 6px;">
			<table id="search_table" class="table-pad-td">
				<tr>
				  <td width="64" align="center">开始日期</td>
                  <td><input name="search_startTime" type="text" value="${log.startTime}" class="Wdate" id="beginDate"
                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\',{d:0})}'})"
                           readonly="readonly" style="width: 98%;" placeholder="开始日期"/></td>
                  <td width="64" align="center">结束日期</td>
                  <td><input name="search_endtime" type="text" value="${log.endtime}" class="Wdate" id="endDate"
                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginDate\',{d:0})}'})"
                           readonly="readonly" style="width: 98%;" placeholder="结束日期"/></td>
				  <td style="min-width:140px;">
				    <input type="submit" class="btn btn-info mar_l10" value="查询"/>
					<input onclick="reValues()" type="button" class="btn mar_l10" value="重置"/>
				  </td>
				</tr>
			</table>
		</form>
	</div>
	<div class="table_box">
		<div class="btn-group-wrap mar_b5">
		  <div class="btn-group pull-right">
			<btn:authorBtn menuid="${menuid}" text="导出"><button class="btn btn-small" onclick="doExport();"><i class="icon-download"></i>导出</button></btn:authorBtn>
			<btn:authorBtn menuid="${menuid}" text="打印"><button class="btn btn-small" onclick="doPrint();"><i class="icon-print"></i>打印</button></btn:authorBtn>
		  </div>
		  <div class="clear"></div>
		</div>
		<table class="table table-striped table-bordered table-style" id="table">
			<thead>
				<tr>
					<th>操作员</th>
					<th>违法确认数</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageList.result}" var="log">
					<tr>
						<td>${log.operator}</td>
						<td>${log.counts}</td>
					</tr>
				</c:forEach>
				<c:if test="${pageList.result!=null}">
					<c:forEach begin="1" end="${15-fn:length(pageList.result)}">
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<tags:pagination page="${pageList}"></tags:pagination>
	</div>
</div>

<object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
	<embed id="LODOP_EM" TYPE="application/x-print-lodop" width=0 height=0 PLUGINSPAGE="install_lodop32.exe"></embed>
</object>
<div id="print_div" style="display: none;font-weight:bold;"></div>

<script type="text/javascript">

//导出
function doExport(){
	window.location.href="${root}/system/log/export/doExport/${menuid}/?tag=statis&${searchParams}";
}

//打印
function doPrint(){
	try{ 
		LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'),'${root}');
	 }catch(err){ 
		alert("本机打印控件未安装或需要升级!"); 
	 } 
	$("#print-div").html("");
	var totalSize = "${pageList.totalSize}";
	var pages = 35;
	var pageSize =parseInt((parseInt(totalSize)+pages - 1)/pages);
	
	LODOP.PRINT_INIT("按操作员统计违法确认操作日志信息");
	//设置纸张，横向打印
	//LODOP.SET_PRINT_PAGESIZE(1,2970,2100,"");
	//设置纸张，横向打印,A3
	LODOP.SET_PRINT_PAGESIZE(1,4200,2970,"");
	//设置字体
	LODOP.SET_PRINT_STYLE("FontSize",9);
	LODOP.SET_PRINT_STYLE("Bold",1);
	LODOP.SET_PRINT_STYLE("ItemType",4);
	
	var m=0;
	//添加每一页的数据
	for (var j =1 ; j<=pageSize; j++) {
		$("#print_div").html("");
		var url = "${root}/system/log/print/doPrint/${menuid}/?pageSize="+pages+"&page="+j+"&${searchParams}";
		$("#print_div").load(url,function(){
			printNextpage($(this).html(),m<(pageSize-1),m==(pageSize-1));
			m++;
		}); 
	}
}

//打印下一页
function printNextpage(ht,newpage,show){
	LODOP.ADD_PRINT_HTM(0,0, "100%","100%",ht);
	LODOP.ADD_PRINT_TEXT('97%','50%',83,23,"第#页/共&页");
	LODOP.SET_PRINT_STYLEA(0,"ItemType",2);
	if(newpage){
		LODOP.NewPage();
	}
	if(show){
		//最后一页后预览
		LODOP.SET_PREVIEW_WINDOW(1,0,0,0,0,"");			
	    //LODOP.PRINT_DESIGN();
		LODOP.PREVIEW();
	}
}
</script>

