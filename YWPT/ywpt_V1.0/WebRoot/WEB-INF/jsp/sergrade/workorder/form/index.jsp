<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>服务商运维概况统计</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript">
				function resetFunc(){
					clearValue();
				}
				
				function clearValue(){
					$("#search_factoryName").val("");
				}
				
				function submitState(){
					var v = $("#queryForm").validate();
					if(!v.checkForm()){
						v.showErrors();
						return;
					}
					
				}
				
				//加入iframe自适应高度
				$("#result_form").load(function() {
					$(this).height(0);
					var mainheight = $(this).contents().height() + 10;
					$(this).height(mainheight < 580 ? 580 : mainheight);
				});
				
				function showFlashChar(id){
				    var screenWidth = screen.width;
				    var stateType = document.getElementsByName("stateType");
				    if(id=="columns"){
				    	queryForm.action = "${root}/sergrade/workorder/chart/chart/${menuid}/?screenWidth="+screenWidth;
			    		queryForm.target = "result_col";
			    		queryForm.submit();
			    	}
			    	
			     }
				//查询条件的必填项验证		
				function validateForm() {
					//查询统计日期
					var beginDate;
					//自定义时间段查询 结束日期
					var endDate;
					//按时间段查询 开始日期
					beginDate = $("#beginDate").val();
					//按时间段查询 结束日期
					endDate = $("#endDate").val();
					if (beginDate.length == 0 || endDate.length == 0) {
						showMessage("请选择统计日期");
						return false;
					}else if(beginDate.length > 0 && endDate.length > 0){
						if(beginDate > endDate){
							showMessage("开始日期不能大于结束日期，请重新选择统计日期");
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
	  		<h4 class="xtcs_h4" style="margin:0;">运维服务统计--运维概况统计</h4>
	 	 	<div class="search-area2 mar_b5" style="padding: 6px 0 4px;">
    			<form method="post" name="queryForm" action="${root}/sergrade/workorder/search/${menuid}/" onsubmit="return validateForm();" 
				id="queryForm" class="form-inline" target="result_form" style="margin:0; padding:0 6px;">
    				<table width="100%">
       					<tr>
        					<td><div id="name_div">服务商名称：</div></td>
						 	<td>
						 		<div id="state_div">
						 			<input type="text" id="factoryName" name="search_factoryName" /> 
						 		</div>
						 	</td>
							<td>
								
								<div class="mar_t10">
									 <table>
									   <tr>
									     
									     <td>统计日期：</td>
									     <td>
									     	<div id="date_div">
												<input class="Wdate required" style="width: 180px;" name="search_beginDate" id="beginDate" readonly="readonly" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM',alwaysUseStartDate:true,maxDate:document.getElementById('endDate').value==''?'%y-%M':document.getElementById('endDate').value})" style="width:90px;"/>
												至
												<input class="Wdate required" style="width: 180px;" name="search_endDate" id="endDate" readonly="readonly" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM',alwaysUseStartDate:true,maxDate:'%y-%M',minDate:document.getElementById('beginDate').value})" style="width:90px;"/>
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
					<li><a href="#tab2" data-toggle="tab" id="columns" style="height:25px;padding-top:0px;"><h4 style="font-size: 14px;font-family: Microsoft YaHei">统计分析图</h4></a></li>
				</ul>
				<div id="result-table">
				    <div class="tab-content mar_5" style="overflow:hidden;">
				      <div id ="tab1" class="tab-pane active" >
				        <iframe id="result_form" name="result_form"  frameborder="0" scrolling="auto" width="100%" height="710" src="${root}/sergrade/workorder/form/blank/${menuid}/"></iframe>
				      </div>
				      <div id ="tab2" class="tab-pane" >
				        <iframe id="result_col" name="result_col"  frameborder="0" scrolling="auto" width="100%" height="710" src=""></iframe>
				      </div>
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
				  	     queryForm.action = "${root}/sergrade/workorder/search/${menuid}/";
				  	     queryForm.target = "result_form";
			    		 queryForm.submit();
			       })	
			  
			       $("#columns").bind("click", function(){
			       		 //如果未通过验证，跳出方法
			       		 if(!validateForm()){
			       		 	return;
			       		 }
					  	 showFlashChar("columns");
			       })
		      })
	     
	  	});
		  
	</script>
</html>