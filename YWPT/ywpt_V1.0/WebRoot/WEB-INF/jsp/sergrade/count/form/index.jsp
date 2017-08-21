<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="${root}/compnents/My97DatePicker/WdatePicker.js" type="text/javascript"></script>

<html>
	<head>
		<title>服务商下责任资产数量统计</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	  	
		<script type="text/javascript">
		    var flag;
			function resetFunc(){
				clearValue();
			}
			
			function clearValue(){
				$("#search_factoryName").val("");
			}
			
			function submitState(){
				if(validateForm()){
					if(flag == 1){
						showFlashChar();
					}
				}
			}
			
			//加入iframe自适应高度
			$("#result_form").load(function() {
				$(this).height(0);
				var mainheight = $(this).contents().height() + 10;
				$(this).height(mainheight < 580 ? 580 : mainheight);
			});
			
			function showFlashChar(){
				var screenWidth = screen.width;
		    	queryForm.action = "${root}/sergrade/count/chart/chart/${menuid}/?screenWidth="+(screenWidth-5);
	    		queryForm.target = "result_col";
	    		queryForm.submit();
      		}	
      		
      		
      		//查询条件的必填项验证		
      		function validateForm() {
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
			<h4 class="xtcs_h4" style="margin:0;">运维服务统计--运维资源统计</h4>
			<div class="row-fluid search-area2 mar_b5" style="padding: 6px 0 4px;">
				<form method="post" name="queryForm" action="${root}/sergrade/count/form/search/${menuid}/" onsubmit="return validateForm();" 
				id="queryForm" class="form-inline" target="result_form" style="margin:0; padding:0 6px;">
					<table width="100%">
						<tr>
							<td valign="top">
								<div class="mar_t10">
									 <table>
									   <tr>
						   	 		 	<td><div id="name_div">服务商名称：</div></td>
						   	 		 	<td>
						   	 		 		<div id="state_div">
						   	 		 			<input type="text" id="factoryName" name="search_factoryName" /> 
						   	 		 		</div>
						   	 		 	</td>
						   	 		 	
									   </tr>
									 </table>
								</div>
							</td>
							<td valign="bottom">	
								<input class="btn btn-info" type="submit" onclick="submitState();" value="查 询"/>
								<input class="btn mar_l10" name="reset" type="reset"  onclick="resetFunc();" value="重 置"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<ul class="nav nav-tabs" style="padding-left:10px;margin-bottom:0px;">
				<li  class="active"><a href="#tab1" data-toggle="tab" id="form_tab" style="height:25px;padding-top:0px;"><h4 style="font-size: 14px;font-family: Microsoft YaHei;">统计报表</h4></a></li>
				<li><a href="#tab2" data-toggle="tab" id="columns" style="height:25px;padding-top:0px;"><h4 style="font-size: 14px;font-family: Microsoft YaHei">统计分析图</h4></a></li>
			</ul>
			<div id="result-table">
				<div class="tab-content" style="overflow:hidden; ">
					<div id ="tab1" class="tab-pane mar_5 active" >
						<iframe id="result_form" name="result_form"  frameborder="0" scrolling="auto" width="100%" height="600" src="${root}/sergrade/count/form/blank/"></iframe>
					</div>
					<div id ="tab2" class="tab-pane mar_5" >
						<table width="100%" border="0" cellpadding="0" cellspacing="0" >
							 <tr class="controlBar">
								  <td width="100%">
								  	<div id="flashframe">
								  		<form method="post" name="colForm" action="" id="colForm" class="form-inline" target="result_col" style="margin:0; padding:0 6px;">
								  			<iframe id="result_col" name="result_col"  frameborder="0" scrolling="auto" width="100%" height="500" src=""></iframe>
								  		</form>
								  	</div>
								  </td>
							 </tr>
						</table>
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
			  	     queryForm.action = "${root}/sergrade/count/form/search/${menuid}/";
			  	     queryForm.target = "result_form";
		    		 queryForm.submit();
		    		 flag = 0;
		    		 $("#colForm").remove();
		       });	
		  
		       $("#columns").bind("click", function(){
		       		 //如果未通过验证，跳出方法
		       		 if(!validateForm()){
		       		 	return;
		       		 }
		       		 $("#flashframe").html('<form method="post" name="colForm" action="" id="colForm" class="form-inline" target="result_col" style="margin:0; padding:0 6px;">'+
					   '<iframe id="result_col" name="result_col"  frameborder="0" scrolling="no" width="100%" height="500" src=""></iframe></form>');

				  	 showFlashChar();
				  	 flag = 1;
		       });
		     
	      });
     
  	});
		  
	</script>
</html>