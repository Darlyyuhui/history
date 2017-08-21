<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>故障类型趋势统计</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
		function showDateTag(){
			$("#date_div").html('&nbsp;<input class="Wdate required" style="width: 180px;" name="search_beginDate" id="beginDate" readonly="readonly" type="text" onFocus="WdatePicker({dateFmt:\'yyyy-MM-dd\',alwaysUseStartDate:true,maxDate:\'%y-%M-%d %H-%m-%s\'})"  size="9"/>');
		}

		function resetFunc(){
			$("#date_div").html('&nbsp;<input class="Wdate required" style="width: 180px;" name="search_beginDate" id="beginDate" readonly="readonly" type="text" onFocus="WdatePicker({dateFmt:\'yyyy-MM-dd\',alwaysUseStartDate:true,maxDate:\'%y-%M-%d %H-%m-%s\'})"  size="9"/>');
		}
		
		function showWeekTag(){
			$("#date_div").html('&nbsp;<input class="Wdate required" style="width: 180px;" name="search_beginDate" id="beginDate" readonly="readonly" type="text" onFocus="WdatePicker({dateFmt:\'yyyy-MM-dd\',alwaysUseStartDate:true,isShowWeek:true,maxDate:\'%y-%M-%d %H-%m-%s\'})"  size="9"/>');
		}
		//周显示
		function stateByWeek() {
			showWeekTag();
		}
		
		//按天统计
		function stateByDay() {
			showDateTag();
		}
		
		//按月统计
		function stateByMonth() {
			$("#date_div").html('&nbsp;<input class="Wdate required" style="width: 180px;" name="search_beginDate" id="beginDate" readonly="readonly" type="text" onFocus="WdatePicker({dateFmt:\'yyyy-MM\',alwaysUseStartDate:true,maxDate:\'%y-%M-%d %H-%m-%s\'})"  size="9"/>');
		}
		
		function submitState(){
			var v = $("#queryForm").validate();
			if(!v.checkForm()){
				v.showErrors();
				return;
			}
			
		}
		
		//按时间段统计 
		function stateSpaceTime(){
			$("#date_div").html('&nbsp;<input class="Wdate required" style="width: 180px;" name="search_beginDate" id="beginDate" readonly="readonly" type="text" onFocus="WdatePicker({dateFmt:\'yyyy-MM-dd\',alwaysUseStartDate:true,maxDate:document.getElementById(\'endDate\').value==\'\'?\'%y-%M-%d %H-%m-%s\':document.getElementById(\'endDate\').value})" style="width:90px;"/>'+
			'&nbsp;至&nbsp;<input class="Wdate required" style="width: 180px;" name="search_endDate" id="endDate" readonly="readonly" type="text" onFocus="WdatePicker({dateFmt:\'yyyy-MM-dd\',alwaysUseStartDate:true,startDate:\'%y-%M-01\',maxDate:\'%y-%M-%d %H-%m-%s\',minDate:document.getElementById(\'beginDate\').value})" style="width:90px;"/>');
		}
		
		//加入iframe自适应高度
		$("#result_form").load(function() {
			$(this).height(0);
			var mainheight = $(this).contents().height() + 10;
			$(this).height(mainheight < 580 ? 580 : mainheight);
		});
		
		function showFlashChar(){
		    var screenWidth = screen.width;
		    var stateType = document.getElementsByName("stateType");
    		queryForm.action = "${root}/question/trend/chart/chart/${menuid}/?screenWidth="+screenWidth;
    		queryForm.target = "result_line";
    		queryForm.submit();
	     }
		//查询条件的必填项验证		
		function validateForm() {
			//统计方式
			var stateType;
			//查询统计日期
			var beginDate;
			//自定义时间段查询 结束日期
			var endDate;
			//统计方式 获取单选按钮的value值
			var search_stateType = document.getElementsByName("search_stateType");
			for(var i=0;i<search_stateType.length;i++){
				if(search_stateType[i].checked){
					stateType = search_stateType[i].value;
				}
			}
			
			//按天、周、月统计数据时，如果统计日期为空，提示输入统计日期
			if(stateType == "1" || stateType == "2" || stateType == "3"){
				//统计日期
				beginDate = $("#beginDate").val();
				if (beginDate.length == 0) {
					showMessage("请选择统计日期");
					return false;
				}
			//按时间段统计数据时，如果统计开始日期或者结束日期为空，提示输入统计日期	
			}else if(stateType == "4"){
				//按时间段查询 开始日期
				beginDate = $("#beginDate").val();
				//按时间段查询 结束日期
				endDate = $("#endDate").val();
				if (beginDate.length == 0 || endDate.length == 0) {
					showMessage("请选择统计日期");
					return false;
				}
			}
			//显示数据区域
			$("#result-table").show();
			return true;
		}	
		//日期隐藏
     		function datacontrol(){
     		    $dp.hide();
		}			
</script>
</head>
<body topmargin="0" leftmargin="0">
<div class="alert alert-block pull-top alert-error" id="alert-div" style="display: none;">
  <p id="alert-content" align="center"></p>
</div>
<div id="message" class="alert alert-success" style="display: none;">
  <button data-dismiss="alert" class="close">×</button>
  <p align="center" id="show_message"></p>
</div>
<div class="conten_box">
	<h4 class="xtcs_h4" style="margin:0;">频发故障类型分析--故障类型趋势分析</h4>
    <div class="search-area2 mar_b5" style="padding: 6px 0 4px;">
    	<form method="post" name="queryForm" action="${root}/question/trend/form/search/${menuid}/" onsubmit="return validateForm();" 
					id="queryForm" class="form-inline" target="result_form" style="margin:0; padding:0 6px;">
        	<table width="100%">
          		<tr>
					<td valign="top">
						<div style="margin-top:3px;">
						  <ul class="radio-list">
						    <li>统计方式：</li>
						    <li><input type="radio" onclick="stateByDay();" id="stateType" checked="checked" name="search_stateType" value="1">按天</li>
						    <li><input type="radio" onclick="stateByWeek();" id="stateType" name="search_stateType" value="2">按周 </li>
						    <li><input type="radio" onclick="stateByMonth();" id="stateType" name="search_stateType" value="3">按月</li>
						    <li><input type="radio" onclick="stateSpaceTime();" id="stateType" name="search_stateType" value="4">按时间段</li>
						  </ul>
						  <div class="clear"></div>
						</div>
				
						<div class="mar_t10">
							 <table>
							   <tr>
							     <td>统计日期：</td>
							     <td>
								     <div id="date_div">
										<input class="Wdate required" name="search_beginDate" id="beginDate" readonly="readonly" type="text" style="width: 180px;"
										onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'%y-%M-%d %H-%m-%s'})"/>
									</div>
								</td>
								<td><span style="color: red;"> * </span ></td>
							   </tr>
							 </table>
						</div>
						
					</td>
								
					<td valign="bottom">
						<input class="btn btn-info mar_l10" type="submit"  value="查 询"/>
		    			<input class="btn mar_l10" name="reset" type="reset"  onclick="resetFunc();" value="重 置">
					</td>
							
				</tr>
			</table>
		</form>
	</div>
	
	<ul class="nav nav-tabs" style="padding-left:10px;margin-bottom:0px;">
		<li  class="active"><a href="#tab1" data-toggle="tab" id="form_tab" style="height:25px;padding-top:0px;"><h4 style="font-size: 14px;font-family: Microsoft YaHei">统计报表</h4></a></li>
		<li><a href="#tab2" data-toggle="tab" id="line" style="height:25px;padding-top:0px;"><h4 style="font-size: 14px;font-family: Microsoft YaHei">曲线图</h4></a></li>
	</ul>
	<div id="result-table">
      <div class="tab-content mar_5" style="overflow:hidden;">
        <div id ="tab1" class="tab-pane active">
          <iframe id="result_form" name="result_form"  frameborder="0" scrolling="no" width="100%" height="650" src="${root}/forward/question/trend/form/blank/"></iframe>
        </div>
        <div id ="tab2" class="tab-pane" >
          <iframe id="result_line" name="result_line"  frameborder="0" scrolling="no" width="100%" height="650" src=""></iframe>
        </div>
      </div>
    </div>  
</div>
</body>
	<script type="text/javascript">
	 $(document).ready(function() {
		  $(function(){
		       $("#form_tab").bind("click", function(){
		       		 //如果未通过验证，跳出方法
		       		 if(!validateForm()){
		       		 	return;
		       		 }
			  	     queryForm.action = "${root}/question/trend/form/search/${menuid}/";
			  	     queryForm.target = "result_form";
		    		 queryForm.submit();
		       })	
		  
		       $("#line").bind("click", function(){
		       		 //如果未通过验证，跳出方法
		       		 if(!validateForm()){
		       		 	return;
		       		 }
				  	 showFlashChar();
		       })
	      })
     
  	});
		  
	</script>
</html>